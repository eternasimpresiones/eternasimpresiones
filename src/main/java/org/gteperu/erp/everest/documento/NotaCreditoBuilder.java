package org.gteperu.erp.everest.documento;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import org.gteperu.erp.everest.cpe.CPE;
import org.gteperu.erp.everest.domain.Cuota;
import org.gteperu.erp.everest.domain.Local;
import org.gteperu.erp.everest.domain.Serie;
import org.gteperu.erp.everest.domain.Tipo_Operacion_Sunat;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._DocumentoCpe_DetalleBean;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.mappers.CuotaMapper;
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
import org.gteperu.erp.everest.service._DocumentoService;
import org.gteperu.erp.everest.service._RetencionService;
import org.gteperu.erp.everest.threads.EnvioDocSunatThread;
import org.gteperu.erp.everest.utils.Constantes;
import org.gteperu.erp.everest.utils.Numero_Letras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotaCreditoBuilder extends ProcesadorDocumento{
	
	@Autowired
	private _CompanyService companyService;

	@Autowired
	private _ClienteService clienteService;

	@Autowired
	private _Detalle_DocumentoService detalle_DocumentoService;

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
	private SerieService serieService;
	@Autowired
	ParametrosService parametrosService;
	@Autowired
	private LocalService localService;
    @Autowired
    private CuotaMapper cuotaMapper;
    
	private Local local;
	private _Company company;
	private _Clientes cliente;
	private _Parametros parametro;
	private Tipo_Operacion_Sunat tipo_Operacion_Sunat;
	

	@Override
	protected void crearDocumento(String estadoDocumento)   throws Exception{
		if(getDocumento().getTipo_comprobante_modifica() == null ||
				getDocumento().getNro_documento_modifica() == null || 
				getDocumento().getDescripcion_motivo() == null) {
			throw new Exception("La nota de credito tiene campos que estan incompletos");
		}
		getDocumento().setRuc_empresaEmisora(company.getNro_documento_empresa());
		getDocumento().setUserSol_empresaEmisora(company.getUsuario_sol_empresa());
		getDocumento().setPassSol_empresaEmisora(company.getPass_sol_empresa());
		getDocumento().setPassFirma_empresaEmisora(company.getPass_firma_empresa());
		getDocumento().setNro_documento_empresa(company.getNro_documento_empresa());
		getDocumento().setTipo_documento_empresa(company.getTipo_doc_empresa());
		getDocumento().setNombre_comercial_empresa(company.getNombre_comercial_empresa());
		getDocumento().setCodigo_ubigeo_empresa(company.getUbigeo().getCodigo());
		getDocumento().setDireccion_empresa(company.getDireccion_empresa());
		getDocumento().setDepartamento_empresa(company.getUbigeo().getDepartamento());
		getDocumento().setProvincia_empresa(company.getUbigeo().getProvincia());
		getDocumento().setDistrito_empresa(company.getUbigeo().getDistrito());
		getDocumento().setRazon_social_empresa(company.getRazon_social_empresa());
		getDocumento().setIfacturacionPse(company.getIfacturacionpse());

		getDocumento().setNro_documento_cliente(cliente.getNro_doc());
		getDocumento().setTipo_documento_cliente(cliente.getTipo_doc());
		getDocumento().setRazon_social_cliente(cliente.getRazon_social());
		getDocumento().setCod_ubigeo_cliente(cliente.getUbigeo().getCodigo());
		getDocumento().setDepartamento_cliente(cliente.getUbigeo().getDepartamento());
		getDocumento().setProvincia_cliente(cliente.getUbigeo().getProvincia());
		getDocumento().setDistrito_cliente(cliente.getUbigeo().getDistrito());
		getDocumento().setCiudad_cliente(cliente.getUbigeo().getProvincia());
		//getDocumento().setFecha_documento(new Timestamp(System.currentTimeMillis()));
		
		getDocumento().setAmbiente_operacion(company.getAmbiente_operacion());

		Numero_Letras num_to_letras = new Numero_Letras();
		String total_letritas = num_to_letras.Convertir(Double.toString(getDocumento().getTotal()), true, getDocumento().getCod_moneda());
		getDocumento().setTotal_letras(total_letritas);
		getDocumento().setDirDocumentoEmpresaEmisora(this.getRutaDocEmpresaEmisora());
		getDocumento().setEstado(Integer.parseInt(estadoDocumento));
	}

	@Override
	protected void crearDetalles() {
		Integer count_item = 1;
		if (getDocumento().getDetalle() != null && getDocumento().getDetalle().size() > 0) {
			for (_DocumentoCpe_DetalleBean item : getDocumento().getDetalle()) {
				item.setId_documento(getDocumento().getId_documento());
				item.setId_empresa(getDocumento().getId_empresa());
				item.setItem(count_item);
				count_item++;
			}
		}

	}

	@Override
	protected void enviarDocSunat() {
		EnvioDocSunatThread hilo = new EnvioDocSunatThread(cpeResource, tipoOperacionSunatService,
				auditoria_SunatService, codigoService, documentoService, retencionService,parametrosService,serieService);
		hilo.setDocumento(getDocumento());
		hilo.setCliente(cliente);
		hilo.setCompany(company);
		hilo.setTipo_Operacion_Sunat(tipo_Operacion_Sunat);
		hilo.start();
	}

	@Override
	protected String getRutaDocEmpresaEmisora() {
		String separador = System.getProperty("file.separator");
		String rutabase = "";
		String dirEmpresaEmisora = "";	
		rutabase= parametro.getValor();

		dirEmpresaEmisora = rutabase + separador + company.getNro_documento_empresa()
				+ separador + "NC";
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
		documentoService.insertarDocumento(getDocumento());
		crearDetalles();
		if(getDocumento().getTipo_comprobante_modifica().equals("01")) {			
			if(getDocumento().getCod_forma_pago().equals(Constantes.codPagoCredito)) {
				for(Cuota c:getDocumento().getLscuotas()) {
					c.setId_documento(getDocumento().getId_documento());
					cuotaMapper.insertarCuota(c);						
				}
			}
		}
		Integer resp = detalle_DocumentoService.insertarDocumento_DetallePorLista(getDocumento().getDetalle());
		enviarDocSunat();
		return resp;
	}

	@Override
	protected Integer procesarDocumentoBorrador() throws Exception {
		if(getDocumento().getId_documento()!=null) {
			eliminarDocumentoBorrador();
		}
		crearDocumento(Constantes.estadoDocBorrador);
		documentoService.insertarDocumento(this.getDocumento());
		crearDetalles();
		Integer resp = detalle_DocumentoService.insertarDocumento_DetallePorLista(this.getDocumento().getDetalle());
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
		return resp;
	}
	
	@Override
	protected void initialize() throws Exception {
		tipo_Operacion_Sunat = new Tipo_Operacion_Sunat();
		tipo_Operacion_Sunat.setCodigo(Constantes.codigoDeclaracionNotaCredito);
		
		company = new _Company();
		company.setId_empresa(getDocumento().getId_empresa());
		company = companyService.retornaEmpresaPorIdempresa(company);
		
		if(getDocumento().getIdlocal()!=null) {

			local = new Local();
			local.setId(getDocumento().getIdlocal());
			local=localService.retornaLocalPorId(local);
			company.setDireccion_empresa(local.getDireccion());
		}
		
		cliente = new _Clientes();
		cliente.setId_cliente(getDocumento().getId_cliente_cpe());
		cliente = clienteService.retornaClientePorIdcliente(cliente);
		
		parametro = parametrosMapper.selecValorParametroPorCodigo(Constantes.codigoParametroRutaMatriz);	
	}
	
	@Override
	protected Integer eliminarDocumentoBorrador() {
		_DocumentoCpe documentoBorradorOld= new _DocumentoCpe();
		Integer rpta=0,rpta2=0;
		try { 
			documentoBorradorOld.setId_documento(getDocumento().getId_documento());
			  rpta=documentoService.eliminarDocumento(documentoBorradorOld);
			_DocumentoCpe_DetalleBean detalleBorradorOld = new _DocumentoCpe_DetalleBean();
			detalleBorradorOld.setId_documento(getDocumento().getId_documento());
			  rpta2=detalle_DocumentoService.eliminarDocumento_Detalle(detalleBorradorOld);
			System.out.println(" Borrador old eliminado "+rpta + " "+rpta2);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" eliminarDocumentoBorrador. ERROR : "+e.getMessage() + " Linea: " + e.getStackTrace()[0].getLineNumber());
			throw e;
		}
	 	return rpta2;
	 }
}
