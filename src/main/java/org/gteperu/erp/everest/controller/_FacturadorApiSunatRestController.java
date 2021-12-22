package org.gteperu.erp.everest.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.gteperu.erp.everest.cpe.CPE;
import org.gteperu.erp.everest.documento.DocumentoCpeFactory;
import org.gteperu.erp.everest.documento.DocumentoDTO;
import org.gteperu.erp.everest.domain.Auditoria_Sunat;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain.Tipo_Operacion_Sunat;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._CpeGuiaRemisionDetalleBean;
import org.gteperu.erp.everest.domain._Detalle_Documento_TMP;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._DocumentoCpe_DetalleBean;
import org.gteperu.erp.everest.domain._Documento_TMP;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.domain._ValidacionTMP;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service.AuditoriaService;
import org.gteperu.erp.everest.service.Auditoria_SunatService;
import org.gteperu.erp.everest.service.Codigo_qrService;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service.Tipo_Operacion_SunatService;
import org.gteperu.erp.everest.service._ClienteService;
import org.gteperu.erp.everest.service._CompanyService;
import org.gteperu.erp.everest.service._Detalle_DocumentoService;
import org.gteperu.erp.everest.service._Detalle_Documento_TMPService;
import org.gteperu.erp.everest.service._Detalle_RetencionService;
import org.gteperu.erp.everest.service._DocumentoService;
import org.gteperu.erp.everest.service._Documento_TMPService;
import org.gteperu.erp.everest.service._RetencionService;
import org.gteperu.erp.everest.service._SecuenciaService;
import org.gteperu.erp.everest.service._UbigeoService;
import org.gteperu.erp.everest.utils.Constantes;
import org.gteperu.erp.everest.utils.Numero_Letras;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/facturadorapi")
public class _FacturadorApiSunatRestController {
	@Autowired
	CPE cpeResource;
	@Autowired
	AuditoriaService auditoriaService;
	@Autowired
	_DocumentoService documentoService;
	@Autowired
	_Detalle_DocumentoService detalle_DocumentoService;
	@Autowired
	ParametrosService parametrosService;
	@Autowired
	Auditoria_SunatService auditoria_SunatService;
	@Autowired
	CPE cpeService;
	@Autowired
	DocumentoCpeFactory factory;
	@Autowired
	_Documento_TMPService documento_TMPService;
	@Autowired
	_Detalle_Documento_TMPService detalle_Documento_TMPService; 
  
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/cargarArchivoDocumentosMasivo", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public @ResponseBody Object handleFileUpload3(@RequestPart("id") Integer id,
			@RequestPart("files") MultipartFile[] files, HttpServletRequest request) throws Exception {
		MultipartFile file = null;
		ResponseWrapper response = new ResponseWrapper();
		Integer empresa_actualizada = 0;
		Boolean bol = false;
		Boolean flgNull = false;
		Integer cat = 0;
		List lsdoc= new ArrayList<>();
    	XSSFWorkbook wb = new XSSFWorkbook();

		try {
			if (files.length != 0) {
				wb = documento_TMPService.armaWorkBook(files, id);
				lsdoc = documento_TMPService.armaListaDocumentosconExcel(wb,id);
			}
			if (lsdoc != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionOk);
				response.setAaData(lsdoc);
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionError);
				throw new Exception(Constantes.msgTransaccionError);
				// agregar logica para eliminar carpeta de la empresa creada
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " cargarArchivoDocumentosMasivo. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/cargarArchivoDocumentosMasivo" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/cargarArchivoDocumentosMasivo",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					id);
		}

		return response;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/insertaListaDocumentosMasivo", method = RequestMethod.POST)
	public @ResponseBody Object insertaListaDocumentosMasivo(@RequestBody @Validated _Documento_TMP documento,
			HttpServletRequest request) throws Exception {
		MultipartFile file = null;
		ResponseWrapper response = new ResponseWrapper();
		Integer empresa_actualizada = 0;
		Boolean bol = false;
		Boolean flgNull = false;
		Integer cat = 0;

 		try {
 					_ValidacionTMP validar = new _ValidacionTMP();
					if(documento.getStipo_documento().equals(Constantes.tipoDocNotaCredito)) {
						validar = documento_TMPService.validarNotaCreditoTMP(documento);						
					}else {
						validar = documento_TMPService.validarFacturaBoletaTMP(documento);						
					}
					if(validar.getIcodrespuesta().equals(Constantes.valTransaccionOk)) {
						cat = documento_TMPService.insertarDocumentoTMP(documento);
						for(_Detalle_Documento_TMP det:documento.getLsdetalle()) {
							det.setIid_documento_tmp(documento.getIid_documento_tmp());
							cat = detalle_Documento_TMPService.insertarDetalleDocumentoTMP(det);
						}
					}else {
						response.setEstado(Constantes.valTransaccionErrornull);
						response.setMsg(validar.getSmensaje());
						throw new Exception(Constantes.msgTransaccionError);
					}
					
				 
			

			if (cat != null && cat > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionOk);
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionError);
				throw new Exception(Constantes.msgTransaccionError);
				// agregar logica para eliminar carpeta de la empresa creada
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " insertaListaDocumentosMasivo. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/insertaListaDocumentosMasivo" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/insertaListaDocumentosMasivo",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
							documento);
		}

		return response;
	}
	
	
	
}
