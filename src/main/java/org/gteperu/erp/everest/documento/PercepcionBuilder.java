package org.gteperu.erp.everest.documento;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.gteperu.erp.everest.cpe.CPE;
import org.gteperu.erp.everest.domain.Local;
import org.gteperu.erp.everest.domain.Serie;
import org.gteperu.erp.everest.domain.Tipo_Operacion_Sunat;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._Cpe_RetencionPercepcion_DetalleBean;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._DocumentoCpe_DetalleBean;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.domain._Retencion;
import org.gteperu.erp.everest.mappers.ParametrosMapper;
import org.gteperu.erp.everest.service.Auditoria_SunatService;
import org.gteperu.erp.everest.service.Codigo_qrService;
import org.gteperu.erp.everest.service.LocalService;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service.SerieService;
import org.gteperu.erp.everest.service.Tipo_Operacion_SunatService;
import org.gteperu.erp.everest.service._ClienteService;
import org.gteperu.erp.everest.service._CompanyService;
import org.gteperu.erp.everest.service._Detalle_DocumentoService;
import org.gteperu.erp.everest.service._Detalle_RetencionService;
import org.gteperu.erp.everest.service._DocumentoService;
import org.gteperu.erp.everest.service._RetencionService;
import org.gteperu.erp.everest.threads.EnvioDocSunatThread;
import org.gteperu.erp.everest.utils.Constantes;
import org.gteperu.erp.everest.utils.Numero_Letras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PercepcionBuilder extends ProcesadorDocumento {

	@Autowired
	private _CompanyService companyService;

	@Autowired
	private _ClienteService clienteService;

	@Autowired
	private CPE cpeResource;

	@Autowired
	private Auditoria_SunatService auditoria_SunatService;

	@Autowired
	private Codigo_qrService codigoService;

	@Autowired
	private _DocumentoService documentoService;

	@Autowired
	private _RetencionService retencionService;

	@Autowired
	private Tipo_Operacion_SunatService tipoOperacionSunatService;

	@Autowired
	private ParametrosMapper parametrosMapper;
	
	@Autowired
	ParametrosService parametrosService;

	@Autowired
	private _Detalle_RetencionService detalle_RetencionService;
	@Autowired
	private LocalService localService;
	@Autowired
	private SerieService serieService;
	
	private Local local;
	private _Company company;
	private _Clientes cliente;
	private _Parametros parametro;
	private SimpleDateFormat dateFormat;



	@Override
	protected void crearDocumento(String estadoDocumento) {

		getRetencion().setRuc_empresaEmisora(company.getNro_documento_empresa());
		getRetencion().setUserSol_empresaEmisora(company.getUsuario_sol_empresa());
		getRetencion().setPassSol_empresaEmisora(company.getPass_sol_empresa());
		getRetencion().setPassFirma_empresaEmisora(company.getPass_firma_empresa());
		getRetencion().setNro_documento_empresa(company.getNro_documento_empresa());
		getRetencion().setTipo_documento_empresa(company.getTipo_doc_empresa());
		getRetencion().setNombre_comercial_empresa(company.getNombre_comercial_empresa());
		getRetencion().setCod_ubigeo_empresa(company.getUbigeo().getCodigo());
		getRetencion().setDireccion_empresa(company.getDireccion_empresa());
		getRetencion().setDepartamento_empresa(company.getUbigeo().getDepartamento());
		getRetencion().setProvincia_empresa(company.getUbigeo().getProvincia());
		getRetencion().setDistrito_empresa(company.getUbigeo().getDistrito());
		getRetencion().setRazon_social_empresa(company.getRazon_social_empresa());
		getRetencion().setIfacturacionPse(company.getIfacturacionpse());

		getRetencion().setTipo_documento_proveedor(cliente.getTipo_doc());
		getRetencion().setNro_documento_proveedor(cliente.getNro_doc());
		getRetencion().setNombre_comercial_proveedor(cliente.getRazon_comercial());
		getRetencion().setDireccion_proveedor(cliente.getDireccion_fiscal());
		getRetencion().setCod_ubigeo_proveedor(cliente.getUbigeo().getCodigo());
		getRetencion().setZona_urbanizacion_proveedor("");
		getRetencion().setCiudad_proveedor("");
		getRetencion().setDepartamento_proveedor(cliente.getUbigeo().getDepartamento());
		getRetencion().setDistrito_proveedor(cliente.getUbigeo().getDistrito());
		getRetencion().setProvincia_proveedor(cliente.getUbigeo().getProvincia());
		getRetencion().setPais_proveedor("PE");// obligatorio (varia dependiendo del pais, revisar catalogo 04)
		getRetencion().setRazon_social_proveedor(cliente.getRazon_social());

		getRetencion().setFecha_documento(new Timestamp(System.currentTimeMillis()));

		getRetencion().setTipo_documento_cliente(cliente.getTipo_doc());
		getRetencion().setNro_documento_cliente(cliente.getNro_doc());
		getRetencion().setNombre_comercial_cliente(cliente.getRazon_comercial());
		getRetencion().setDireccion_cliente(cliente.getDireccion_fiscal());
		getRetencion().setCod_ubigeo_cliente(cliente.getUbigeo().getCodigo());
		getRetencion().setZona_urbanizacion_cliente("");
		getRetencion().setCiudad_cliente("");
		getRetencion().setDepartamento_cliente(cliente.getUbigeo().getDepartamento());
		getRetencion().setDistrito_cliente(cliente.getUbigeo().getDistrito());
		getRetencion().setProvincia_cliente(cliente.getUbigeo().getProvincia());
		getRetencion().setPais_cliente("PE");// obligatorio (varia dependiendo del pais, revisar catalogo 04)
		getRetencion().setRazon_social_cliente(cliente.getRazon_social());
		
		getRetencion().setAmbiente_operacion(company.getAmbiente_operacion());

		getRetencion().setDirDocumentoEmpresaEmisora(getRutaDocEmpresaEmisora());
		String fecha_emision = dateFormat.format(new Date(getRetencion().getFecha_emision().getTime()));
		getRetencion().setFecha_documento_str(fecha_emision);
		getRetencion().setFecha_emision_str(fecha_emision);
		getRetencion().setEstado(Integer.valueOf(estadoDocumento));
		getRetencion()
				.setNro_comprobante(getRetencion().getSerie_comprobante() + "-" + getRetencion().getNro_comprobante());
	}

	@Override
	protected void crearDetalles() throws Exception {
		for (_DocumentoCpe doc : getRetencion().getLsDocumento()) {
			_Cpe_RetencionPercepcion_DetalleBean detalle = new _Cpe_RetencionPercepcion_DetalleBean();
			String fec_doc = dateFormat.format(new Date(doc.getFecha_documento().getTime()));

			detalle.setId_retencion(getRetencion().getId_retencion());

			detalle.setCOD_TIPO_DOCUMENTO(doc.getCod_tipo_documento());
			detalle.setNRO_DOCUMENTO(doc.getSerie_comprobante() + "-" + doc.getNro_comprobante());
			detalle.setFECHA_DOCUMENTO(fec_doc);
			detalle.setCOD_MONEDA(doc.getCod_moneda());
			detalle.setMONTO_TOTAL(doc.getImporte_cobro());

			detalle.setNRO_DOC_COBRO(doc.getNro_cobro());
			detalle.setFECHA_COBRO(doc.getFecha_cobro().substring(0, 10));
			detalle.setMONEDA_PERCIBIDO(doc.getMoneda_percibida());
			detalle.setMONTO_PERCIBIDO(doc.getImporte_percibido());
			detalle.setFECHA_PERCEPCION(doc.getFecha_percepcion().substring(0, 10));
			detalle.setMONEDA_COBRO_NETO(doc.getMoneda_cobro_neto());
			detalle.setMONTO_COBRO_NETO(doc.getImporte_neto_cobrado());
			detalle_RetencionService.insertarDetalleDocumentoRetencion(detalle);
		}

	}

	@Override
	protected void enviarDocSunat() {
		EnvioDocSunatThread hilo = new EnvioDocSunatThread(cpeResource, tipoOperacionSunatService,
				auditoria_SunatService, codigoService, documentoService, retencionService,parametrosService,serieService);
		hilo.setRetencionDoc(getRetencion());
		hilo.setCliente(cliente);
		hilo.setCompany(company);
		hilo.start();
	}

	@Override
	protected String getRutaDocEmpresaEmisora() {
		String separador = System.getProperty("file.separator");
		String rutabase = "";
		String dirEmpresaEmisora = "";	
		rutabase= parametro.getValor();

		dirEmpresaEmisora = rutabase +separador+ company.getNro_documento_empresa()
				+ separador + "PCP";

		File carpetaDocumentoEmpresaEmisora = new File(dirEmpresaEmisora);
		if (!carpetaDocumentoEmpresaEmisora.exists()) {
			carpetaDocumentoEmpresaEmisora.mkdirs();
			System.out.println("Directorio creado => " + dirEmpresaEmisora);
		}
		return dirEmpresaEmisora;
	}

	@Override
	protected Integer procesarDocumento() throws Exception {
		//Cuando se este procesando un documento borrador
		if(getDocumento().getBdocborradortmp()!=null
			 &&getDocumento().getBdocborradortmp()) {
					if(getDocumento().getId_documento()!=null) {
						eliminarDocumentoBorrador();
					}
			}
		crearDocumento(Constantes.estadoSinEnvio);
		retencionService.insertarDocumentoRetencion(getRetencion());
		crearDetalles();
//		enviarDocSunat();
		return 1;
	}
	
	@Override
	protected Integer procesarDocumentoBorrador() throws Exception {
		if(getDocumento().getId_documento()!=null) {
			eliminarDocumentoBorrador();
		}
		crearDocumento(Constantes.estadoDocBorrador);
		Integer resp=retencionService.insertarDocumentoRetencion(getRetencion());
		crearDetalles();
  		 
		if(resp>0) {
		 if(getDocumento().getBflageditarnum()!=null&&!getDocumento().getBflageditarnum()) {
			Serie s = new Serie();
			s.setIdlocal(getDocumento().getIdlocal());
			s.setIdsutipodocumento(getDocumento().getIdsutipodocumento());
			s = serieService.retornaSeriexTipoDocyLocal(s);
			s.setSnumero(String.format("%8s",String.valueOf(Integer.valueOf(s.getSnumero())+1)).replace(' ','0'));
			serieService.actualizarSerie(s);	
			}
		}
		return 1; 
	}
	

	@Override
	protected void initialize() throws Exception {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		company = new _Company();
		company.setId_empresa(getRetencion().getId_empresa());
		company = companyService.retornaEmpresaPorIdempresa(company);

		if(getDocumento().getIdlocal()!=null) {

			local = new Local();
			local.setId(getDocumento().getIdlocal());
			local=localService.retornaLocalPorId(local);
			company.setDireccion_empresa(local.getDireccion());
		}
		
		cliente = new _Clientes();
		cliente.setId_cliente(getRetencion().getId_cliente());
		cliente = clienteService.retornaClientePorIdcliente(cliente);

		parametro = parametrosMapper.selecValorParametroPorCodigo(Constantes.codigoParametroRutaMatriz);
	}
	
	@Override
	protected Integer eliminarDocumentoBorrador() {
		_Retencion  documentoBorradorOld= new _Retencion();
		Integer rpta=0,rpta2=0;
		try { 
			documentoBorradorOld.setId_retencion(getDocumento().getId_documento());
			  rpta=retencionService.eliminarDocumentoRetencion(documentoBorradorOld);
			  _Cpe_RetencionPercepcion_DetalleBean detalleBorradorOld = new _Cpe_RetencionPercepcion_DetalleBean();
			detalleBorradorOld.setId_retencion((getDocumento().getId_documento()));
			  rpta2=detalle_RetencionService.eliminarDetalleDocumentoRetencion(detalleBorradorOld);
			System.out.println(" Borrador old eliminado "+rpta + " "+rpta2);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" eliminarDocumentoBorrador. ERROR : "+e.getMessage() + " Linea: " + e.getStackTrace()[0].getLineNumber());
			throw e;
		}
	 	return rpta2;
	 }
	
 }
