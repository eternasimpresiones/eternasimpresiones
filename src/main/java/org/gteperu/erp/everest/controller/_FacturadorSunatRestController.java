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
import org.gteperu.erp.everest.domain.Pagodocumento;
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
import org.gteperu.erp.everest.service.PagodocumentoService;
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
@RequestMapping(value = "/api/sunat")
public class _FacturadorSunatRestController {

	@Autowired
	CPE cpeResource;

	@Autowired
	_SecuenciaService _secuenciaService;

	@Autowired
	AuditoriaService auditoriaService;

	@Autowired
	_DocumentoService documentoService;

	@Autowired
	_Detalle_RetencionService detalle_RetencionService;

	@Autowired
	_RetencionService retencionService;

	@Autowired
	_Detalle_DocumentoService detalle_DocumentoService;

	@Autowired
	_CompanyService companyService;

	@Autowired
	_ClienteService clienteService;

	@Autowired
	_UbigeoService ubigeoService;

	@Autowired
	ParametrosService parametrosService;

	@Autowired
	Tipo_Operacion_SunatService tipoOperacionSunatService;

	@Autowired
	Auditoria_SunatService auditoria_SunatService;

	@Autowired
	Codigo_qrService codigoService;

	@Autowired
	CPE cpeService;

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	@Qualifier("sunatPadronJob")
	Job job;

	@Autowired
	DocumentoCpeFactory factory;

	@Autowired
	_Documento_TMPService documento_TMPService;

	@Autowired
	_Detalle_Documento_TMPService detalle_Documento_TMPService;
	
	@Autowired
	PagodocumentoService pagodocuService;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.POST, value = "/pruebaDescargaURL")
	public ResponseWrapper pruebaDescargaURL(@RequestBody @Validated ResponseWrapper responseWrapper,
			HttpServletRequest request) {
		ResponseWrapper obj_respuesta = new ResponseWrapper();
		List objtList = new ArrayList();
		String separadorOS = System.getProperty("file.separator");
		try {
			jobLauncher.run(job,
					new JobParametersBuilder().addLong("timestamp", System.currentTimeMillis()).toJobParameters());
			System.out.print("Se ejecuto el job");

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + "/pruebaDescargaURL" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			System.out.print("Error " + e.getMessage());
		}
		return obj_respuesta;
	}

	/*
	 * Registro de Documento electronico factura,boleta,nota debito o nota de
	 * credito
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.POST, value = "/generaDocumento")
	public ResponseWrapper generaDocumento(@RequestBody @Validated DocumentoDTO dto, HttpServletRequest request)
			throws Exception {

		try {
			ResponseWrapper response = new ResponseWrapper();
			Integer resp = 0;
			factory.createBuilder(dto);

			if (dto.getDocumentoCpe().getBdocborrador() != null && dto.getDocumentoCpe().getBdocborrador()) {
				resp = factory.procesarDocumentoBorrador();
			} else {
				resp = factory.procesarDocumento();
			}

			registraPagodocumento(dto.getDocumentoCpe());
			response.setEstado(resp);
			response.setMsg(Constantes.msgTransaccionOk);

			return response;
		} catch (Exception e) {
			System.out.println("\n" + this.getClass().getSimpleName() + "/generaDocumento" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new RuntimeException("message: " + e.getMessage());

		}
	}

	private void registraPagodocumento(_DocumentoCpe documentoCpe) {
		if (documentoCpe.getLsPagodocumento() != null) {
			for (Pagodocumento pd : documentoCpe.getLsPagodocumento()) {
				pd.setIddocumento(documentoCpe.getId_documento());
				pagodocuService.insertar(pd);
			}
		}

	}

	/*
	 * Verifica validez del documento electronico de la sunat
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.POST, value = "/consultaEstadodocumento")
	public ResponseWrapper consultaEstadoDocumento(@RequestBody @Validated _DocumentoCpe documento,
			HttpServletRequest request) throws Exception {
		String[] rpta_sunat = null;
		ResponseWrapper obj_respuesta = new ResponseWrapper();
		Boolean campos_incompletos = false;
		Integer inserta_documento = 0;
		Integer resp = 0;
		Integer inserta_detalle_document = 0;
		_Company company = new _Company();
		try {
			company.setId_empresa(documento.getId_empresa());
			company = companyService.retornaEmpresaPorIdempresa(company);

			String ruc = company.getNro_documento_empresa();
			String UsuSol = company.getUsuario_sol_empresa();
			String PassSol = company.getPass_sol_empresa();
			String PassFirma = company.getPass_firma_empresa();
			documento.setRuc_empresaEmisora(ruc);
			documento.setUserSol_empresaEmisora(UsuSol);
			documento.setPassSol_empresaEmisora(PassSol);
			documento.setPassFirma_empresaEmisora(PassFirma);
			documento.setAmbiente_operacion(company.getAmbiente_operacion());
			rpta_sunat = cpeResource.consultaEstadoDocumento(documento);
			obj_respuesta.setEstado(Integer.parseInt(rpta_sunat[0]));
			obj_respuesta.setMsg(rpta_sunat[2]);
			if (rpta_sunat[1].equals("0003") && documento.getEstado().intValue() == 1) {
				documento.setEstado(2);
				resp = documentoService.actualizarDocumento(documento);
				obj_respuesta.setEstado(2);

			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + "/consultaEstadodocumento" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/consultaEstadodocumento",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					documento);
		}
		return obj_respuesta;
	}

	/*
	 * web service que se comunica con la sunat y reemplaza la guía seleccionada
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.POST, value = "/reemplazarGuia")
	public ResponseWrapper reemplazarGuia(@RequestBody @Validated _DocumentoCpe documento, HttpServletRequest request)
			throws Exception {
		String[] rpta_sunat = null;
		ResponseWrapper obj_respuesta = new ResponseWrapper();
		_Parametros parametro = new _Parametros();
		List<_Parametros> lsPar = new ArrayList<>();
		Tipo_Operacion_Sunat tipo_Operacion_Sunat = new Tipo_Operacion_Sunat();
		Integer inserta_documento = 0;
		Integer actualiza_documento = 0;
		Integer inserta_detalle_documento = 0;
		Integer actualiza_detalle_documento = 0;
		try {
			String directorio = System.getProperty("user.dir");
			String separador = System.getProperty("file.separator");

			parametro.setGrupo(Constantes.codigoGrupoIniParametros);
			parametro.setId_empresa(0);
			lsPar = parametrosService.retornaObjParametrosPorGrupoe(parametro);

			_Company company = new _Company();
			company.setId_empresa(documento.getId_empresa());
			company = companyService.retornaEmpresaPorIdempresa(company);

			_Clientes cliente = new _Clientes();
			cliente.setId_cliente(documento.getId_cliente_cpe());
			cliente = clienteService.retornaClientePorIdcliente(cliente);
			documento.setRuc_empresaEmisora(company.getNro_documento_empresa());
			documento.setUserSol_empresaEmisora(company.getUsuario_sol_empresa());
			documento.setPassSol_empresaEmisora(company.getPass_sol_empresa());
			documento.setPassFirma_empresaEmisora(company.getPass_firma_empresa());
			documento.setNro_documento_empresa(company.getNro_documento_empresa());
			documento.setTipo_documento_empresa(company.getTipo_doc_empresa());
			documento.setNombre_comercial_empresa(company.getNombre_comercial_empresa());
			documento.setCodigo_ubigeo_empresa(company.getUbigeo().getCodigo());
			documento.setDireccion_empresa(company.getDireccion_empresa());
			documento.setDepartamento_empresa(company.getUbigeo().getDepartamento());
			documento.setProvincia_empresa(company.getUbigeo().getProvincia());
			documento.setDistrito_empresa(company.getUbigeo().getDistrito());
			documento.setRazon_social_empresa(company.getRazon_social_empresa());

			documento.setNro_documento_cliente(cliente.getNro_doc());
			documento.setTipo_documento_cliente(cliente.getTipo_doc());
			documento.setRazon_social_cliente(cliente.getRazon_social());
			documento.setCod_ubigeo_cliente(cliente.getUbigeo().getCodigo());
			documento.setDepartamento_cliente(cliente.getUbigeo().getDepartamento());
			documento.setProvincia_cliente(cliente.getUbigeo().getProvincia());
			documento.setDistrito_cliente(cliente.getUbigeo().getDistrito());
			documento.setCiudad_cliente(cliente.getUbigeo().getProvincia());

			Numero_Letras num_to_letras = new Numero_Letras();
			String total_letritas = num_to_letras.Convertir(Double.toString(documento.getTotal()), true,documento.getCod_moneda());
			documento.setTotal_letras(total_letritas);

			String rutabase = "";
			String tipo_doc = "";
			String dirEmpresaEmisora = "";
			for (int i = 0; i < lsPar.size(); i++) {
				if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaBase)) {
					String[] dir = lsPar.get(i).getValor().split("\\|");
//						 +separador+ dir[2];
					rutabase = dir[0] + separador + dir[1] + separador + dir[2];
					// trae la ruta base donde se creaara el directorio para la empresa.
					break;
				}
			}
			dirEmpresaEmisora = directorio + separador + rutabase + separador + company.getNro_documento_empresa();

			File carpetaEmpresaEmisora = new File(dirEmpresaEmisora);
			System.out.println("carpetaEmpresaEmisora.exists() : " + carpetaEmpresaEmisora.exists());
			if (!carpetaEmpresaEmisora.exists()) {
				carpetaEmpresaEmisora.mkdir();
				System.out.println("Directorio creado => " + dirEmpresaEmisora);
			}

			if (documento.getCod_tipo_documento().equals(Constantes.tipoDocGuiaRemisionRemitente)) {
				tipo_doc = "GUIA REMISION";
				tipo_Operacion_Sunat.setCodigo(Constantes.codigoDeclaracionGuiaRemisionRemitente);
			} else {
				tipo_doc = "GUIA TRANSPORTISTA";
				tipo_Operacion_Sunat.setCodigo(Constantes.codigoDeclaracionGuiaRemisionTransportista);
			}

			dirEmpresaEmisora = dirEmpresaEmisora + File.separator + tipo_doc;
			File carpetaDocumentoEmpresaEmisora = new File(dirEmpresaEmisora);
			if (!carpetaDocumentoEmpresaEmisora.exists()) {
				carpetaDocumentoEmpresaEmisora.mkdir();
				System.out.println("Directorio creado => " + dirEmpresaEmisora);
			}

			documento.setDirDocumentoEmpresaEmisora(dirEmpresaEmisora);

			rpta_sunat = cpeResource.reemplazarGuia(documento);

			obj_respuesta.setEstado(Integer.parseInt(rpta_sunat[1]));

			if (rpta_sunat[0].equals("1")) {
				System.out.print("Exito " + rpta_sunat[2]);
			} else {
				System.out.print("Error " + rpta_sunat[2]);
			}

			if (rpta_sunat[0].equals("1")) {
				documento.setEstado(Integer.parseInt(Constantes.estadoDeclaradoAceptado));
				// SETEA EL NRO_COMPROBANTE DE LA RESPUESTA SUNAT
				String[] frase_parts = rpta_sunat[2].split(",");
				String frase = frase_parts[0];
				int posicionUltimoEspacio = 0;
				posicionUltimoEspacio = frase.lastIndexOf(" ");
				String comprobante = frase.substring(posicionUltimoEspacio + 1, frase.length());
				String[] comprobante_parts = comprobante.split("-");
				String nro_comprobante = comprobante_parts[1];
				documento.setNro_comprobante(nro_comprobante);

				Integer count_item = 1;

				if (documento.getDetalle() != null && documento.getDetalle().size() > 0) {
					for (_DocumentoCpe_DetalleBean item : documento.getDetalle()) {
						item.setId_documento(documento.getId_documento());
						item.setId_empresa(documento.getId_empresa());
						item.setItem(count_item);
						count_item++;
					}
				} else if (documento.getDetalle_Guia() != null && documento.getDetalle_Guia().size() > 0) {
					for (_CpeGuiaRemisionDetalleBean item : documento.getDetalle_Guia()) {
						item.setId_documento(documento.getId_documento());
						item.setId_empresa(documento.getId_empresa());
						item.setITEM(count_item);
						count_item++;
					}
				}
			} else {

				if (Integer.parseInt(rpta_sunat[1]) > 2000 && Integer.parseInt(rpta_sunat[1]) < 4000) {
					documento.setEstado(Integer.parseInt(Constantes.estadoRechazadoTotal));
				} else if (Integer.parseInt(rpta_sunat[1]) > 100 && Integer.parseInt(rpta_sunat[1]) < 2000) {
					documento.setEstado(Integer.parseInt(Constantes.estadoRechazadoParcial));
				} else {
					documento.setEstado(Integer.parseInt(Constantes.estadoSinRespuesta));
				}
			}

			if (documento.getId_documento() != null && documento.getId_documento() > 0) {
				actualiza_documento = documentoService.actualizarDocumentoEstadoRechazado(documento);
			} else {
				inserta_documento = documentoService.insertarDocumento(documento);
			}
			if (inserta_documento > 0) {
				inserta_detalle_documento = detalle_DocumentoService
						.insertarDocumento_Detalle_GuiaPorLista(documento.getDetalle_Guia());
			} else {
				if (actualiza_documento > 0) {
					Integer elimina_detalle_documento = 0;
					_DocumentoCpe_DetalleBean documento_detalle = new _DocumentoCpe_DetalleBean();
					documento_detalle.setId_documento(documento.getId_documento());
					elimina_detalle_documento = detalle_DocumentoService.eliminarDocumento_Detalle(documento_detalle);
					if (elimina_detalle_documento > 0) {
						for (_DocumentoCpe_DetalleBean item : documento.getDetalle()) {
							inserta_detalle_documento = detalle_DocumentoService
									.insertarDocumento_DetallePorLista(documento.getDetalle());
							// actualiza_detalle_documento =
							// detalle_DocumentoService.actualizarDocumento_Detalle(item);
						}
					}
				}
			}

			// INSERTA AUDITORIA
			if ((inserta_documento > 0 && inserta_detalle_documento > 0)
					|| (actualiza_documento > 0 && inserta_detalle_documento > 0)) {
				tipo_Operacion_Sunat = tipoOperacionSunatService
						.retornaObjTipoOperacionSunatPorCodigo(tipo_Operacion_Sunat);
				Auditoria_Sunat auditoria_Sunat = new Auditoria_Sunat();

				auditoria_Sunat.setId_documento(documento.getId_documento());
				auditoria_Sunat.setFlag_respuesta_sunat(rpta_sunat[0]);
				auditoria_Sunat.setCodigo_respuesta_sunat(rpta_sunat[1]);
				auditoria_Sunat.setMensaje_respuesta_sunat(rpta_sunat[2]);
				auditoria_Sunat.setId_tipo_operacion(tipo_Operacion_Sunat.getId_tipo_operacion_sunat());
				auditoria_Sunat.setId_usuario(documento.getId_usuario());
				auditoria_Sunat.setFecha_registro(new Timestamp(System.currentTimeMillis()));
				Integer inserta_auditoria = auditoria_SunatService.insertaAuditoria_Sunat(auditoria_Sunat);
				System.out.print("\nSE INSERTO AUDITORIA_SUNAT => " + inserta_auditoria.toString());
			}

			obj_respuesta.setEstado(Integer.parseInt(rpta_sunat[0]));
			obj_respuesta.setMsg(rpta_sunat[2]);

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + "/reemplazarGuia" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/reemplazarGuia",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					documento);
		}
		return obj_respuesta;
	}

	/*
	 * Da de baja a un documento electronico
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/darBajaDocumento", method = RequestMethod.POST)
	public ResponseWrapper darBajaDocumento(@RequestBody @Validated _DocumentoCpe documento, HttpServletRequest request)
			throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		_DocumentoCpe doc = new _DocumentoCpe();
		_Company emp = new _Company();
		String darBaja = "";
		Integer resp = 0;
		Integer sec = 0;
		String separador = System.getProperty("file.separator");

		Tipo_Operacion_Sunat tipo_Operacion_Sunat = new Tipo_Operacion_Sunat();
		_Parametros parametro = new _Parametros();
		List<_Parametros> lsPar = new ArrayList<>();
		try {
			parametro.setGrupo(Constantes.codigoGrupoIniParametros);
			parametro.setId_empresa(0);
			lsPar = parametrosService.retornaObjParametrosPorGrupoe(parametro);

			doc = documentoService.retornaDocumentoId(documento);
			emp.setId_empresa(doc.getId_empresa());
			emp = companyService.retornaEmpresaPorIdempresa(emp);

			documento.setRuc_empresaEmisora(emp.getNro_documento_empresa());
			documento.setUserSol_empresaEmisora(emp.getUsuario_sol_empresa());
			documento.setPassSol_empresaEmisora(emp.getPass_sol_empresa());
			documento.setPassFirma_empresaEmisora(emp.getPass_firma_empresa());
			documento.setNro_documento_empresa(emp.getNro_documento_empresa());
			documento.setRazon_social_empresa(emp.getRazon_social_empresa());
			documento.setTipo_documento_empresa(emp.getTipo_doc_empresa());
			documento.setIfacturacionPse(emp.getIfacturacionpse());
			documento.setFecha_referencia(doc.getFecha_documento().toString().substring(0, 10));
			documento.setCod_tipo_documento(doc.getCod_tipo_documento());
			documento.setSerie_comprobante(doc.getSerie_comprobante());
			documento.setNro_comprobante(doc.getNro_comprobante());
			documento.setDescripcion_motivo(documento.getObservacion());
			documento.setFecha_baja(documento.getFecha_baja().toString().substring(0, 10));
			documento.setAmbiente_operacion(emp.getAmbiente_operacion());
			sec = _secuenciaService.selectSecuenciaDarbaja();
			documento.setSecuencia(sec);
			String rutamatriz = "";
			String tipo_doc = "";
			String dirEmpresaEmisora = "";

			for (int i = 0; i < lsPar.size(); i++) {
				if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
					rutamatriz = lsPar.get(i).getValor();
					break;
				}
			}

			dirEmpresaEmisora = rutamatriz + separador + emp.getNro_documento_empresa();
			System.out.println("DIRECCION EMPRESA EMISORA DAR BAJA: " + dirEmpresaEmisora);

			if (documento.getCod_tipo_documento().equals(Constantes.tipoDocFactura)) {
				tipo_doc = "FACTURA";
				tipo_Operacion_Sunat.setCodigo(Constantes.codigoAnulacionFactura);
			}
			if (documento.getCod_tipo_documento().equals(Constantes.tipoDocBoleta)) {
				tipo_doc = "BOLETA";
				tipo_Operacion_Sunat.setCodigo(Constantes.codigoAnulacionBoleta);
			}
			if (documento.getCod_tipo_documento().equals(Constantes.tipoDocNotaCredito)) {
				tipo_doc = "NC";
				tipo_Operacion_Sunat.setCodigo(Constantes.codigoAnulacionNotaCredito);
			}
			if (documento.getCod_tipo_documento().equals(Constantes.tipoDocNotaDebito)) {
				tipo_doc = "ND";
				tipo_Operacion_Sunat.setCodigo(Constantes.codigoAnulacionNotaDebito);
			}
			if (documento.getCod_tipo_documento().equals(Constantes.tipoDocGuiaRemisionRemitente)) {
				tipo_doc = "GUIA";
				tipo_Operacion_Sunat.setCodigo(Constantes.codigoAnulacionGuiaRemisionRemitente);
			}
			if (documento.getCod_tipo_documento().equals(Constantes.tipoDocGuiaRemisionTransportista)) {
				tipo_doc = "GUIA";
				tipo_Operacion_Sunat.setCodigo(Constantes.codigoAnulacionGuiaRemisionTransportista);
			}
			if (documento.getCod_tipo_documento().equals(Constantes.tipoDocPercepcion)) {
				tipo_doc = "RTC";
			}
			if (documento.getCod_tipo_documento().equals(Constantes.tipoDocRetencion)) {
				tipo_doc = "PCP";
			}

			dirEmpresaEmisora = dirEmpresaEmisora + separador + tipo_doc;
			System.out.println("DIRECCION EMPRESA EMISORA DAR BAJA FINAL: " + dirEmpresaEmisora);

			documento.setDirDocumentoEmpresaEmisora(dirEmpresaEmisora);

			darBaja = cpeResource.darBajaDocumento(documento);
			String[] parts = darBaja.split("--");

			tipo_Operacion_Sunat = tipoOperacionSunatService
					.retornaObjTipoOperacionSunatPorCodigo(tipo_Operacion_Sunat);
			Auditoria_Sunat auditoria_Sunat = new Auditoria_Sunat();

			String msgrpaSunat = parts[1] + '-' + parts[2];
			auditoria_Sunat.setId_documento(doc.getId_documento());
			auditoria_Sunat.setCodigo_respuesta_sunat(parts[0]);
			auditoria_Sunat.setMensaje_respuesta_sunat(msgrpaSunat);
			auditoria_Sunat.setId_tipo_operacion(tipo_Operacion_Sunat.getId_tipo_operacion_sunat());
			auditoria_Sunat.setId_usuario(documento.getId_usuario());
			auditoria_Sunat.setFecha_registro(new Timestamp(System.currentTimeMillis()));
			Integer inserta_auditoria = auditoria_SunatService.insertaAuditoria_Sunat(auditoria_Sunat);
			System.out.print("SE INSERTO ANULACION AUDITORIA_SUNAT => " + inserta_auditoria.toString());

			if (parts[1].equals("1") && parts[2].equals("1")) {
				doc.setEstado(Integer.parseInt(Constantes.estadoDocAnulado));
				doc.setFecha_baja(documento.getFecha_baja());
				doc.setMotivo_baja(documento.getObservacion());
				doc.setSecuencia(documento.getSecuencia());
				resp = documentoService.actualizarDocumento(doc);
				if (resp == 1) {
					response.setEstado(Integer.parseInt(parts[1]));
					response.setMsg(darBaja);
				} else {
					response.setEstado(Constantes.valTransaccionErrornull);
					response.setMsg(Constantes.msgTransaccionDocumentoDeBajaError);
					throw new Exception(Constantes.msgTransaccionError);

				}
			} else {
				response.setEstado(Integer.parseInt(parts[1]));
				response.setMsg(darBaja);
			}

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " DARDEBAJA. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/darBajaDocumento",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					documento);
		}
		return response;

	}

	/*
	 * Visualiza resumen de datos de la boleta seleccionada
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/resumenBoletas", method = RequestMethod.POST)
	public ResponseWrapper resumenBoletas(@RequestBody @Validated List<_DocumentoCpe> lsdocumento,
			HttpServletRequest request) throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		_DocumentoCpe doc = new _DocumentoCpe();
		_Company emp = new _Company();
		String darBaja = "";
		Integer resp = 0;
		Integer sec = 0;
		String directorio = System.getProperty("user.dir");
		String separador = System.getProperty("file.separator");

		Tipo_Operacion_Sunat tipo_Operacion_Sunat = new Tipo_Operacion_Sunat();
		_Parametros parametro = new _Parametros();
		List<_Parametros> lsPar = new ArrayList<>();
		List<_DocumentoCpe> lsDocSunat = new ArrayList<_DocumentoCpe>();
		_Clientes cliente = new _Clientes();
		try {
			parametro.setGrupo(Constantes.codigoGrupoIniParametros);
			parametro.setId_empresa(0);
			lsPar = parametrosService.retornaObjParametrosPorGrupoe(parametro);

			for (_DocumentoCpe documento : lsdocumento) {

				doc = documentoService.retornaDocumentoId(documento);
				emp.setId_empresa(doc.getId_empresa());
				emp = companyService.retornaEmpresaPorIdempresa(emp);
				cliente = clienteService.retornaClientexId(doc.getId_cliente());

				documento.setRuc_empresaEmisora(emp.getNro_documento_empresa());
				documento.setUserSol_empresaEmisora(emp.getUsuario_sol_empresa());
				documento.setPassSol_empresaEmisora(emp.getPass_sol_empresa());
				documento.setPassFirma_empresaEmisora(emp.getPass_firma_empresa());
				documento.setNro_documento_empresa(emp.getNro_documento_empresa());
				documento.setRazon_social_empresa(emp.getRazon_social_empresa());
				documento.setTipo_documento_empresa(emp.getTipo_doc_empresa());
				documento.setFecha_referencia(doc.getFecha_documento().toString().substring(0, 10));
				documento.setSerie_comprobante(doc.getSerie_comprobante());
				documento.setNro_doc_cliente(cliente.getNro_doc());
				documento.setTipo_documento_cliente(cliente.getTipo_doc());
				documento.setCod_moneda(doc.getCod_moneda());

				documento.setCod_tipo_documento(doc.getCod_tipo_documento());
				documento.setNro_comprobante(doc.getNro_comprobante());
				documento.setDescripcion_motivo(lsdocumento.get(0).getObservacion());

				documento.setTotal_gravadas(doc.getTotal_gravadas());
				documento.setTotal_inafecta(doc.getTotal_inafecta());
				documento.setTotal_exoneradas(doc.getTotal_exoneradas());
				documento.setTotal_gratuitas(doc.getTotal_gratuitas());
				documento.setTotal_percepciones(doc.getTotal_percepciones());
				documento.setTotal_retenciones(doc.getTotal_retenciones());
				documento.setTotal_detracciones(doc.getTotal_detracciones());
				documento.setTotal_bonificaciones(doc.getTotal_bonificaciones());
				documento.setTotal_descuento(doc.getTotal_descuento());
				documento.setSub_total(doc.getSub_total());
				documento.setTotal_igv(doc.getTotal_igv());
				documento.setTotal_isc(doc.getTotal_isc());
				documento.setTotal_otr_imp(doc.getTotal_otr_imp());
				documento.setTotal(doc.getTotal());
				documento.setTipo_cambio(doc.getTipo_cambio());

				documento.setFecha_baja(new Timestamp(System.currentTimeMillis()).toString().substring(0, 10));
				sec = _secuenciaService.selectSecuenciaResumen();
				documento.setSecuencia(sec);
				String rutabase = "";
				String tipo_doc = "";
				String dirEmpresaEmisora = "";
				for (int i = 0; i < lsPar.size(); i++) {
					if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaBase)) {
						String[] dir = lsPar.get(i).getValor().split("\\|");
						rutabase = dir[0] + separador + dir[1] + separador + dir[2];
						break;
					}
				}

				if (documento.getCod_tipo_documento().equals(Constantes.tipoDocBoleta)) {
					tipo_doc = "BOLETA";
					tipo_Operacion_Sunat.setCodigo(Constantes.codigoAnulacionBoleta);
				}
				if (documento.getCod_tipo_documento().equals(Constantes.tipoDocNotaCredito)) {
					tipo_doc = "NC";
					tipo_Operacion_Sunat.setCodigo(Constantes.codigoAnulacionNotaCredito);
				}
				if (documento.getCod_tipo_documento().equals(Constantes.tipoDocNotaDebito)) {
					tipo_doc = "ND";
					tipo_Operacion_Sunat.setCodigo(Constantes.codigoAnulacionNotaDebito);
				}
				dirEmpresaEmisora = directorio + separador + rutabase + separador + emp.getNro_documento_empresa()
						+ separador + tipo_doc;

				documento.setDirDocumentoEmpresaEmisora(dirEmpresaEmisora);
				lsDocSunat.add(documento);
			}

			darBaja = cpeService.resumenBoletas(lsDocSunat);
			String[] parts = darBaja.split("--");

			tipo_Operacion_Sunat = tipoOperacionSunatService
					.retornaObjTipoOperacionSunatPorCodigo(tipo_Operacion_Sunat);
			Auditoria_Sunat auditoria_Sunat = new Auditoria_Sunat();

			for (_DocumentoCpe documento : lsDocSunat) {

				if (parts[1].equals("1") && parts[2].equals("1")) {
					String msgrpaSunat = parts[1] + '-' + parts[2];
					auditoria_Sunat.setId_documento(documento.getId_documento());
					auditoria_Sunat.setCodigo_respuesta_sunat(parts[0]);
					auditoria_Sunat.setMensaje_respuesta_sunat(msgrpaSunat);
					auditoria_Sunat.setId_tipo_operacion(tipo_Operacion_Sunat.getId_tipo_operacion_sunat());
					auditoria_Sunat.setId_usuario(lsdocumento.get(0).getId_usuario());
					auditoria_Sunat.setFecha_registro(new Timestamp(System.currentTimeMillis()));
					Integer inserta_auditoria = auditoria_SunatService.insertaAuditoria_Sunat(auditoria_Sunat);
					System.out.print("SE INSERTO ANULACION AUDITORIA_SUNAT => " + inserta_auditoria.toString());

					doc.setEstado(Constantes.estaoDocumentoDeBaja);
					doc.setFecha_baja(documento.getFecha_baja());
					doc.setMotivo_baja(documento.getObservacion());
					doc.setSecuencia(documento.getSecuencia());
					resp = documentoService.actualizarDocumento(doc);
				} else {
					response.setEstado(Integer.parseInt(parts[1]));
					response.setMsg(darBaja);
				}
			}

			if (resp == 1) {
				response.setEstado(Integer.parseInt(parts[1]));
				response.setMsg(darBaja);
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionDocumentoDeBajaError);
				throw new Exception(Constantes.msgTransaccionError);

			}

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " resumenBoletas. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/resumenBoletas",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					lsdocumento);
		}
		return response;

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/cargarArchivoDocumentosMasivo", method = RequestMethod.POST, consumes = {
			"multipart/form-data" })
	public @ResponseBody Object handleFileUpload3(@RequestPart("id") Integer id,
			@RequestPart("files") MultipartFile[] files, HttpServletRequest request) throws Exception {
		MultipartFile file = null;
		ResponseWrapper response = new ResponseWrapper();
		Integer empresa_actualizada = 0;
		Boolean bol = false;
		Boolean flgNull = false;
		Integer cat = 0;
		List lsdoc = new ArrayList<>();
		XSSFWorkbook wb = new XSSFWorkbook();

		try {
			if (files.length != 0) {
				wb = documento_TMPService.armaWorkBook(files, id);
				lsdoc = documento_TMPService.armaListaDocumentosconExcel(wb, id);
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
			System.out.println(
					this.getClass().getSimpleName() + " cargarArchivoDocumentosMasivo. ERROR : " + e.getMessage());
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
	public @ResponseBody Object insertaListaDocumentosMasivo(@RequestBody @Validated List<_Documento_TMP> lsdocumento,
			HttpServletRequest request) throws Exception {
		MultipartFile file = null;
		ResponseWrapper response = new ResponseWrapper();
		Integer empresa_actualizada = 0;
		Boolean bol = false;
		Boolean flgNull = false;
		Integer cat = 0;

		try {
			for (_Documento_TMP doc : lsdocumento) {
				_ValidacionTMP validar = new _ValidacionTMP();
				if (doc.getStipo_documento().equals(Constantes.tipoDocNotaCredito)) {
					validar = documento_TMPService.validarNotaCreditoTMP(doc);
				} else {
					validar = documento_TMPService.validarFacturaBoletaTMP(doc);
				}
				if (validar.getIcodrespuesta().equals(Constantes.valTransaccionOk)) {
					cat = documento_TMPService.insertarDocumentoTMP(doc);
					for (_Detalle_Documento_TMP det : doc.getLsdetalle()) {
						det.setIid_documento_tmp(doc.getIid_documento_tmp());
						cat = detalle_Documento_TMPService.insertarDetalleDocumentoTMP(det);
					}
				} else {
					response.setEstado(Constantes.valTransaccionErrornull);
					response.setMsg(validar.getSmensaje());
					throw new Exception(Constantes.msgTransaccionError);
				}

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
			System.out.println(
					this.getClass().getSimpleName() + " insertaListaDocumentosMasivo. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/insertaListaDocumentosMasivo" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/insertaListaDocumentosMasivo",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					lsdocumento);
		}

		return response;
	}

	/*
	 * Registro de Documento electronico factura,boleta,nota debito o nota de
	 * credito
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.POST, value = "/validarFecha")
	public ResponseWrapper validarFecha(@RequestBody @Validated _DocumentoCpe doc, HttpServletRequest request)
			throws Exception {
		Boolean fechaValida = null;
		try {
			ResponseWrapper response = new ResponseWrapper();
			_Parametros parametro = new _Parametros();
			parametro.setCodigo(Constantes.codParametroLimiteEnvio);
			parametro = parametrosService.retornaObjParametrosPorCodigo(parametro);
			fechaValida = documentoService.validarDias(doc, parametro.getValor());

			if (fechaValida) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgFechaValida);
			} else {
				response.setEstado(Constantes.valTransaccionNoEncontro);
				response.setMsg("Fecha sobrepasa limite de " + parametro.getValor() + " dias de envio.");
			}
			return response;
		} catch (Exception e) {
			System.out.println("\n" + this.getClass().getSimpleName() + "/validarFecha" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new RuntimeException("message: " + e.getMessage());

		}
	}

}
