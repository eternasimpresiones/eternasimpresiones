package org.gteperu.erp.everest.controller;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain.Tipo_Operacion_Sunat;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.mappers.SecuenciaMapper;
import org.gteperu.erp.everest.service._CompanyService;
import org.gteperu.erp.everest.service._DocumentoService;
import org.gteperu.erp.everest.service._SecuenciaService;
import org.gteperu.erp.everest.service.Auditoria_SunatService;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service.Tipo_Operacion_SunatService;
import org.gteperu.erp.everest.service._ClienteService;
import org.gteperu.erp.everest.utils.Constantes;
import org.gteperu.erp.everest.domain.Auditoria_Sunat;
import org.gteperu.erp.everest.domain.Periodo;
import org.gteperu.erp.everest.domain.Reporte;
import org.apache.commons.io.FilenameUtils;
import org.gteperu.erp.everest.cpe.CPE;

@RestController
@RequestMapping(value = "/api/documento")
public class _DocumentoRestController {
	@Resource(name = "documentoService")
	_DocumentoService documentoService;
	@Resource(name = "companyService")
	_CompanyService companyService;
	@Resource(name = "clienteService")
	_ClienteService clienteService;

	@Autowired
	Tipo_Operacion_SunatService tipoOperacionSunatService;

	@Autowired
	Auditoria_SunatService auditoria_SunatService;

	@Autowired
	ParametrosService parametrosService;

	@Autowired
	_SecuenciaService _secuenciaService;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/insertaDocumento", method = RequestMethod.POST)
	public ResponseWrapper insertaDocumento(@RequestBody @Validated _DocumentoCpe documento, HttpServletRequest request)
			throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		try {

			Integer cat = 0;
			cat = documentoService.insertarDocumento(documento);

			if (cat != null && cat > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionInsertarDocumentoOk);
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionInsertarDocumentoError);
				throw new Exception(Constantes.msgTransaccionError);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " insertaDocumento. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/insertaDocumento",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					documento);
		}
		return response;

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/retornaLsNroDocumento", method = RequestMethod.POST)
	public ResponseWrapper retornaLsNroDocumento(@RequestBody @Validated _DocumentoCpe documento,
			HttpServletRequest request) throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		List objtList = new ArrayList();

		try {
			objtList = documentoService.retornalsNroDocumento(documento);
			if (objtList != null && objtList.size() > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(objtList);
			} else {
				response.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
				response.setEstado(Constantes.valTransaccionNoEncontro);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " retornaLsNroDocumento. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/retornaLsNroDocumento",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					documento);
		}
		return response;

	}

	/*
	 * retorna los documentos electronicos segun la empresa seleccionada
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/listarDocumento", method = RequestMethod.POST)
	public ResponseWrapper listarDocumento(@RequestBody @Validated _DocumentoCpe documento, HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		List lsDocumento = new ArrayList();

		List lsGlobal = new ArrayList();
		Object objd = new Object();
		ResponseWrapper obj = new ResponseWrapper();
		try {

			lsDocumento = documentoService.listarDocumento(documento);
			lsGlobal.add(lsDocumento);
			if (lsDocumento != null && lsDocumento.size() > 0) {
				obj.setEstado(Constantes.valTransaccionOk);
				obj.setAaData(lsGlobal);
			} else {
				obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
				obj.setEstado(Constantes.valTransaccionNoEncontro);
				obj.setAaData(lsDocumento);
			}

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listarDocumento. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listarDocumento",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					documento);
		}
		return obj;

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/actualizarDocumento", method = RequestMethod.POST)
	public ResponseWrapper actualizarDocumento(@RequestBody @Validated _DocumentoCpe documento,
			HttpServletRequest request) throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		try {
			Integer cat = 0;
			cat = documentoService.actualizarDocumento(documento);
			if (cat != null && cat > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionActualizarDocumentoOk);
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionActualizarDocumentoError);
				throw new Exception(Constantes.msgTransaccionError);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " actualizarDocumento. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listarDocumento",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					documento);
		}
		return response;

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/eliminarDocumento", method = RequestMethod.POST)
	public ResponseWrapper eliminarDocumento(@RequestBody @Validated _DocumentoCpe documento,
			HttpServletRequest request) throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		try {
			Integer cat = 0;
			cat = documentoService.eliminarDocumento(documento);

			if (cat != null && cat > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionEliminarDocumentoOk);
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionEliminarDocumentoError);
				throw new Exception(Constantes.msgTransaccionError);
			}
		} catch (Exception e) {
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/eliminarDocumento",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					documento);
		}
		return response;
	}
	/*
	 * Retorna ultimo numero de comprobante electronico segun el documento
	 */

	public ResponseWrapper retornaultimonrocomprobante(@RequestBody @Validated _DocumentoCpe documento,
			HttpServletRequest request) throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		String nroDocumento;
		try {
			nroDocumento = documentoService.retornaultimonrocomprobante(documento);
			if (nroDocumento != null && nroDocumento != "") {
				response.setMsg(nroDocumento);
				response.setEstado(Constantes.valTransaccionOk);
			} else {
				response.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
				response.setEstado(Constantes.valTransaccionNoEncontro);
			}
		} catch (Exception e) {
			System.out.println(
					this.getClass().getSimpleName() + " retornaultimonrocomprobante. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/retornaultimonrocomprobante",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					documento);
		}
		return response;

	}

	/*
	 * Retorna un documento electronico segun la empresa y el id del documento
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/retornaDocumentoPorId", method = RequestMethod.POST)
	public ResponseWrapper retornaDocumentoPorId(@RequestBody @Validated _DocumentoCpe documento,
			HttpServletRequest request) throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		_DocumentoCpe documentoCpe = new _DocumentoCpe();
		try {
			documentoCpe = documentoService.retornaDocumentoPorId(documento);
			if (documentoCpe != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setDefaultObj(documentoCpe);
			} else {
				response.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
				response.setEstado(Constantes.valTransaccionNoEncontro);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " retornaDocumentoPorId. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/retornaDocumentoPorId",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					documento);
		}
		return response;

	}
	/*
	 * Lista series de documentos electronicos segun la empresa
	 */

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/retornaDocumentoPorSerieNum", method = RequestMethod.POST)
	public ResponseWrapper retornaDocumentoPorSerieNum(@RequestBody @Validated _DocumentoCpe documento,
			HttpServletRequest request) throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		_DocumentoCpe documentoCpe = new _DocumentoCpe();
		try {
			documentoCpe = documentoService.retornaDocumentoPorSerieNum(documento);
			if (documentoCpe != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setDefaultObj(documentoCpe);
			} else {
				response.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
				response.setEstado(Constantes.valTransaccionNoEncontro);
			}
		} catch (Exception e) {
			System.out.println(
					this.getClass().getSimpleName() + " retornaDocumentoPorSerieNum. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/retornaDocumentoPorSerieNum",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					documento);
		}
		return response;

	}

	/*
	 * Inserta archivo anexado a un documento electronico
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/insertarRutaArchivo", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public @ResponseBody Object handleFileUpload(@RequestPart("documento") _DocumentoCpe documento,
			@RequestPart("files") MultipartFile[] files, HttpServletRequest request) throws Exception {
		MultipartFile file = null;
		Integer documento_registrada = 0;
		_DocumentoCpe ruta_archivo = new _DocumentoCpe();
		String directorio = System.getProperty("user.dir");
		String separador = System.getProperty("file.separator");
		ResponseWrapper response = new ResponseWrapper();
		_Parametros parametro = new _Parametros();
		List<_Parametros> lsPar = new ArrayList<>();
		parametro.setGrupo(Constantes.codigoGrupoIniParametros);
		parametro.setId_empresa(0);

		String rutabase = "";
		String rutafiles = "";
		lsPar = parametrosService.retornaObjParametrosPorGrupoe(parametro);

		for (int i = 0; i < lsPar.size(); i++) {
			if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaBase)) {
				String[] dir = lsPar.get(i).getValor().split("\\|");
				rutabase = dir[0] + separador + dir[1] + separador + dir[2];
			}
			if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaFiles)) {
				String[] dir = lsPar.get(i).getValor().split("\\|");
				rutafiles = dir[0];
			}
		}

		String dir_base = directorio + separador + rutabase + separador + rutafiles;
		System.out.println(dir_base);

		ruta_archivo = documentoService.RetornaRutaArchivo(documento);
		String serie_compro = ruta_archivo.getSerie_comprobante();
		String nro_compro = ruta_archivo.getNro_comprobante();
		String nro_doc_empresa = ruta_archivo.getCompany().getNro_documento_empresa();

		try {
			if (files.length != 0) {
				file = files[0];
				file.getSize();
				byte[] bytes = file.getBytes();
				System.out.println("DIR EMPRESA: " + dir_base + nro_doc_empresa);
				File dirempresa = new File(dir_base + nro_doc_empresa);
				if (dirempresa.mkdirs()) {
					System.out.println("Directory is created");
				} else {
					System.out.println("Directory cannot be created");
				}
				// crea archivo del logo
				String fileName = nro_doc_empresa + "-" + serie_compro + nro_compro + "."
						+ FilenameUtils.getExtension(file.getOriginalFilename());
				String path = "";
				path = dir_base + nro_doc_empresa + separador + fileName;
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
				stream.write(bytes);
				stream.close();
				documento.setRuta_archivo(path);
				documento_registrada = documentoService.ActualizarRutaArchivo(documento);
			}
		} catch (Exception e) {
			response.setMsg(Constantes.msgTransaccionInsertarClienteError);
			System.out.println(this.getClass().getSimpleName() + " insertarRutaArchivo. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/insertarRutaArchivo",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					documento);
		}
		return response;
	}

	/*
	 * descarga el xml de un documento electornico
	 */
	@RequestMapping(value = "/descargarRutaArchivo", method = RequestMethod.POST)
	public @ResponseBody byte[] descargarRutaArchivo(@RequestBody @Validated _DocumentoCpe documento,
			HttpServletRequest request) throws Exception {
		byte[] data = null;
		_DocumentoCpe doc = new _DocumentoCpe();
		try {

			doc = documentoService.retornaDocumentoId(documento);
			String ruta = doc.getRuta_archivo();
			Path path = Paths.get(ruta);
			data = Files.readAllBytes(path);
			return data;

		} catch (Exception ex) {
			System.out.println("" + ex.getMessage());
			throw new IOException("Error en el Excel");
		}
	}

	@RequestMapping(value = "/retornaDocumentoPorSerieNumIdEmpresa", method = RequestMethod.POST)
	public ResponseWrapper retornaDocumentoPorSerieNumIdEmpresa(@RequestBody @Validated _DocumentoCpe documento,
			HttpServletRequest request) throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		_DocumentoCpe documentoCpe = new _DocumentoCpe();
		try {
			documentoCpe = documentoService.retornaDocumentoPorSerieNumIdEmpresa(documento);
			if (documentoCpe != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setDefaultObj(documentoCpe);
			} else {
				response.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
				response.setEstado(Constantes.valTransaccionNoEncontro);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " insertarDocumento. ERROR : " + e.getMessage());
			response.setMsg(Constantes.msgTransaccionInsertarDocumentoError);
			response.setEstado(Constantes.valTransaccionSinPermiso);
		} finally {
			return response;
		}
	}

	@RequestMapping(value = "/retornalsNroDocumentoEstado", method = RequestMethod.POST)
	public ResponseWrapper retornalsNroDocumentoEstado(@RequestBody @Validated _DocumentoCpe documento,
			HttpServletRequest request) throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		List objtList = new ArrayList();
		try {
			objtList = documentoService.retornalsNroDocumentoEstado(documento);
			if (objtList != null && objtList.size() > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(objtList);
			} else {
				response.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
				response.setEstado(Constantes.valTransaccionNoEncontro);
			}
		} catch (Exception e) {
			System.out.println(
					this.getClass().getSimpleName() + " retornalsNroDocumentoEstado. ERROR : " + e.getMessage());
			response.setMsg(Constantes.msgTransaccionInsertarDocumentoError);
			response.setEstado(Constantes.valTransaccionSinPermiso);
		} finally {
			return response;
		}
	}

	/*
	 * Lista nros de los documentos electronicos segun la empresa
	 */
	@RequestMapping(value = "/retornalsNroDocumentoEstadoTipoDocu", method = RequestMethod.POST)
	public ResponseWrapper retornalsNroDocumentoEstadoTipoDocu(@RequestBody @Validated _DocumentoCpe documento,
			HttpServletRequest request) throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		List objtList = new ArrayList();
		try {
			objtList = documentoService.retornalsNroDocumentoEstadoTipoDocu(documento);
			if (objtList != null && objtList.size() > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(objtList);
			} else {
				response.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
				response.setEstado(Constantes.valTransaccionNoEncontro);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " retornalsNroDocumentoEstadoTipoDocu. ERROR : "
					+ e.getMessage());
			response.setMsg(Constantes.msgTransaccionInsertarDocumentoError);
			response.setEstado(Constantes.valTransaccionSinPermiso);
		} finally {
			return response;
		}
	}

	@RequestMapping(value = "/retornalsSerieComprobateUnico", method = RequestMethod.POST)
	public ResponseWrapper retornalsSerieComprobateUnico(@RequestBody @Validated _DocumentoCpe documento,
			HttpServletRequest request) throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		List objtList = new ArrayList();

		try {
			objtList = documentoService.retornalsSerieComprobateUnico(documento);
			if (objtList != null && objtList.size() > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(objtList);
			} else {
				response.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
				response.setEstado(Constantes.valTransaccionNoEncontro);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " insertarDocumento. ERROR : " + e.getMessage());
			response.setMsg(Constantes.msgTransaccionInsertarDocumentoError);
			response.setEstado(Constantes.valTransaccionSinPermiso);
		}
		return response;

	}

	/*
	 * Retorna cantidad de documentos electronicos registrados en la empresa
	 * seleccionada
	 */
	@RequestMapping(value = "/cantidadDocumentos", method = RequestMethod.POST)
	public ResponseWrapper cantidadDocumentos(@RequestBody @Validated _DocumentoCpe documento,
			HttpServletRequest request) throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		try {
			Integer cat = 0;
			cat = documentoService.cantidadDocumentos(documento);
			if (cat != null && cat > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionCantidadDocumentoOk);
				response.setCantidad(cat);
			} else {
				response.setEstado(Constantes.valTransaccionNoEncontro);
				response.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
				response.setCantidad(cat);
			}
		} catch (Exception e) {
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/cantidadDocumentos",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					documento);
		}
		return response;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/retornaPorTipoDocumento", method = RequestMethod.POST)
	public ResponseWrapper retornaPorTipoDocumento(@RequestBody @Validated _DocumentoCpe documento) throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		try {
			List objtList = documentoService.retornaPorTipoDocumento(documento);
			if (objtList != null && objtList.size() > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(objtList);
			} else {
				response.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
				response.setEstado(Constantes.valTransaccionNoEncontro);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " retornaPorTipoDocumento. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/retornaPorTipoDocumento",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					documento);
		}
		return response;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/retornaPorSerieComprobante", method = RequestMethod.POST)
	public ResponseWrapper retornaPorSerieComprobante(@RequestBody @Validated _DocumentoCpe documento) throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		try {
			List objtList = documentoService.retornaPorSerieComprobante(documento);
			if (objtList != null && objtList.size() > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(objtList);
			} else {
				response.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
				response.setEstado(Constantes.valTransaccionNoEncontro);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " retornaPorSerieComprobante. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/retornaPorSerieComprobante",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					documento);
		}
		return response;
	}

}
