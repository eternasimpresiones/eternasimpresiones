package org.gteperu.erp.everest.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.mail.ApplicationMailer;
import org.gteperu.erp.everest.mappers.ClienteMapper;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service._CompanyService;
import org.gteperu.erp.everest.service._DocumentoService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping(value = "/api/correo")
public class _CorreoRestController {
	@Resource(name = "parametrosService")
	ParametrosService parametrosService;
	
	@Resource(name = "companyService")
	_CompanyService companyService;
	
	@Resource(name = "documentoService")
	_DocumentoService documentoService;
	
	@Resource(name = "dataSource")
	private DriverManagerDataSource dataSource;
	
	@Resource(name = "mailService")
	ApplicationMailer mailService;
	
	@Autowired
	ClienteMapper clienteMapper;
	
	/*WS que envia un correo al cliente , le adjunta el cpe(pdf,xml) y cdr(xml)
	 * 
	 * envia por correo electronico al cliente  la factura generada
	 * Integer id_documento
	 * 
	 * 
	 */
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/envioCPE", method = RequestMethod.POST)
	public ResponseWrapper enviarEmail(@RequestBody _DocumentoCpe documento, HttpServletResponse response) throws IOException,JRException, Exception {
		byte[] file = null;
		ResponseWrapper res = new ResponseWrapper();
		try {
			_Parametros parametro = new _Parametros();
			_Clientes cliente = new _Clientes();
			List<_Parametros> lsPar = new ArrayList<>();
			String email="";
			String password="";
			_DocumentoCpe doc = new _DocumentoCpe();
			Integer id_empresa=documento.getId_empresa();
			Integer id_documento=documento.getId_documento();
 			String extension = ".XML";
			String rutamatriz = "",rutajasper="";// ruta donde copiara los formatos y
									// genrara los direcotiro para el logo
									// de la empresa, imagens de los
									// conceptos, y formatos de reportes.
			String rutareportes = "";
			String rutafile="";
			String reportedoc = Constantes.rutaFactura;
 			String asunco_correo = "Envio de comprobante electronico";
			String nombredoc_xml="";
			String nombrecdr_xml="";
 			String separador = System.getProperty("file.separator");
			
 			String correo_destino = documento.getEmail();
			documento=documentoService.retornaDocumentoId(documento);
			doc=documentoService.retornaDocumentoCPEXML(documento);
			cliente = clienteMapper.retornaClientexId(documento.getId_cliente());

			if(documento.getEstado() == Constantes.estaoDocumentoDeBaja){
				String[] parts = documento.getFecha_baja().split("-");
				switch(documento.getCod_tipo_documento()){
				case "03":
					nombredoc_xml = doc.getCompany().getNro_documento_empresa()+"-"+Constantes.resumenDiarioSunat + "-" +parts[0]+parts[1]+parts[2]+"-"+documento.getSecuencia()+extension;

					nombrecdr_xml = "R-"+doc.getCompany().getNro_documento_empresa()+"-"+Constantes.resumenDiarioSunat + "-" +parts[0]+parts[1]+parts[2]+"-"+documento.getSecuencia()+extension;
					break;
				case "01":
					nombredoc_xml = doc.getCompany().getNro_documento_empresa()+"-"+Constantes.comunicaciónBajaSunat + "-" +parts[0]+parts[1]+parts[2]+"-"+documento.getSecuencia()+extension;

					nombrecdr_xml = "R-"+doc.getCompany().getNro_documento_empresa()+"-"+Constantes.comunicaciónBajaSunat + "-" +parts[0]+parts[1]+parts[2]+"-"+documento.getSecuencia()+extension;
					break;
				case "07":
					if(documento.getTipo_comprobante_modifica().equals("03")){
						nombredoc_xml = doc.getCompany().getNro_documento_empresa()+"-"+Constantes.resumenDiarioSunat + "-" +parts[0]+parts[1]+parts[2]+"-"+documento.getSecuencia()+extension;

						nombrecdr_xml = "R-"+doc.getCompany().getNro_documento_empresa()+"-"+Constantes.resumenDiarioSunat + "-" +parts[0]+parts[1]+parts[2]+"-"+documento.getSecuencia()+extension;
					}else{
						nombredoc_xml = doc.getCompany().getNro_documento_empresa()+"-"+Constantes.comunicaciónBajaSunat + "-" +parts[0]+parts[1]+parts[2]+"-"+documento.getSecuencia()+extension;

						nombrecdr_xml = "R-"+doc.getCompany().getNro_documento_empresa()+"-"+Constantes.comunicaciónBajaSunat + "-" +parts[0]+parts[1]+parts[2]+"-"+documento.getSecuencia()+extension;
					}
					break;
				case "08":
					if(documento.getTipo_comprobante_modifica().equals("03")){
						nombredoc_xml = doc.getCompany().getNro_documento_empresa()+"-"+Constantes.resumenDiarioSunat + "-" +parts[0]+parts[1]+parts[2]+"-"+documento.getSecuencia()+extension;

						nombrecdr_xml = "R-"+doc.getCompany().getNro_documento_empresa()+"-"+Constantes.resumenDiarioSunat + "-" +parts[0]+parts[1]+parts[2]+"-"+documento.getSecuencia()+extension;
					}else{
						nombredoc_xml = doc.getCompany().getNro_documento_empresa()+"-"+Constantes.comunicaciónBajaSunat + "-" +parts[0]+parts[1]+parts[2]+"-"+documento.getSecuencia()+extension;

						nombrecdr_xml = "R-"+doc.getCompany().getNro_documento_empresa()+"-"+Constantes.comunicaciónBajaSunat + "-" +parts[0]+parts[1]+parts[2]+"-"+documento.getSecuencia()+extension;
					}
					break;
				}


			}else{
				nombredoc_xml = doc.getCompany().getNro_documento_empresa()+"-"+documento.getCod_tipo_documento() + "-" +documento.getSerie_comprobante()+"-"+documento.getNro_comprobante()+extension;

				nombrecdr_xml = "R-"+doc.getCompany().getNro_documento_empresa()+"-"+documento.getCod_tipo_documento() + "-" +documento.getSerie_comprobante()+"-"+documento.getNro_comprobante()+extension;
			}

 	 		id_documento=documento.getId_documento();
						 
			if(documento.getCod_tipo_documento().equals(Constantes.tipoDocFactura)) {
				rutafile="FACTURA";
				
			}
			if(documento.getCod_tipo_documento().equals(Constantes.tipoDocBoleta)) {
				rutafile="BOLETA";
			}
			if(documento.getCod_tipo_documento().equals(Constantes.tipoDocNotaCredito)) {
				rutafile="NC";
			}
			if(documento.getCod_tipo_documento().equals(Constantes.tipoDocNotaDebito)) {
				rutafile="ND";
			}
			if(documento.getCod_tipo_documento().equals(Constantes.tipoDocGuiaRemisionRemitente)||documento.getCod_tipo_documento().equals(Constantes.tipoDocGuiaRemisionTransportista)) {
				rutafile="GUIA";
			}
			if(documento.getCod_tipo_documento().equals(Constantes.tipoDocPercepcion)) {
				rutafile="RTC";
			}
			if(documento.getCod_tipo_documento().equals(Constantes.tipoDocRetencion)) {
				rutafile="PCP";
			}  
			
			parametro.setGrupo(Constantes.codigoGrupoIniParametros);
			parametro.setId_empresa(0);
			lsPar = parametrosService.retornaObjParametrosPorGrupoe(parametro);
			if (lsPar != null && lsPar.size() > 0) {
				
				for (int i = 0; i < lsPar.size(); i++) {
					if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
						rutamatriz=lsPar.get(i).getValor();
	 				}
					if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaJasper)) {
						rutajasper=lsPar.get(i).getValor();
	 				}
			 	}
				
				rutareportes = rutamatriz + separador + rutajasper ;
 				
				_Company emp = new _Company();
				emp.setId_empresa(id_empresa);  
				emp = companyService.retornaEmpresaPorIdempresa(emp);
				
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 
				JasperPrint print = null;
				Map<String, Object> parametor = new HashMap<String, Object>();
				String rutaImagen = rutamatriz + separador+emp.getNro_documento_empresa()+separador+emp.getLogo();

				parametor.put("iddocumentos",id_documento);
			//	parametor.put("url",rutaImagen);
				parametor.put("SUBREPORT_DIR",rutareportes +separador+ Constantes.rutaSubDetalle);
				parametor.put("idlocal",documento.getIdlocal());
				parametor.put("RUTA_BASE", rutareportes + separador);
				response.setHeader("Content-Disposition", "inline; filename=file.pdf");
				response.setContentType("application/pdf");
				
				Connection cn = dataSource.getConnection();
				print = JasperFillManager.fillReport(new FileInputStream(new File(rutareportes +separador+ reportedoc)),parametor, cn);
				
				//file = JasperExportManager.exportReportToPdf(print);
				
				              // C://ArchivosCPE//10708111911//FACTURAS//
				String dirprin = rutamatriz + separador+emp.getNro_documento_empresa()+ separador +rutafile+ separador;
				File dirc = new File(dirprin);
				dirc.mkdirs();	
	 			File freporte_documento = new File(dirprin + id_documento + ".pdf");
				JasperExportManager.exportReportToPdfFile(print, freporte_documento.getPath());
				
		 		String ruta_xml=rutamatriz + separador+emp.getNro_documento_empresa()+ separador +rutafile+ separador;
				File fdoc_xml = new File(ruta_xml+nombredoc_xml);
				File fcdr_xml = new File(ruta_xml+nombrecdr_xml);
				
				
				_Parametros par_correo=new _Parametros();
				par_correo.setCodigo(Constantes.codigoParametroEmailFact);
				par_correo.setId_empresa(0);
				par_correo=parametrosService.retornaObjParametrosPorCosigoe(par_correo);
				if(par_correo==null){}else{
					email=par_correo.getValor();
					password=par_correo.getNombre();
				}
				
			 	int envio_correo = mailService.enviarMailCPE(correo_destino,asunco_correo,
						 freporte_documento,fdoc_xml,fcdr_xml,email,password,documento,emp,cliente);
				
				if (envio_correo == 1) {
					res.setEstado(1);					
					res.setMsg("Se envio el correo");
				} else {
					res.setEstado(0);
					res.setMsg("Error enviando el correo");
				}
			}

		} catch (SQLException e) {
			System.out.println("" + e.getMessage());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/envioCPE",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),documento);
		}
		return res;
	}
	 
}