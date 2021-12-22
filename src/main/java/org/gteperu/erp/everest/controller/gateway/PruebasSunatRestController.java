package org.gteperu.erp.everest.controller.gateway;

 
import java.io.Console;
import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gteperu.erp.everest.cpe.CPE;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._DocumentoCpe_DetalleBean;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.domain._Producto;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.mappers.SecuenciaMapper;
import org.gteperu.erp.everest.domain.Auditoria_Sunat;
import org.gteperu.erp.everest.domain.Codigo_qr;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain.Tipo_Operacion_Sunat;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._CpeGuiaRemisionDetalleBean;
import org.gteperu.erp.everest.service.AuditoriaService;
import org.gteperu.erp.everest.service.Auditoria_SunatService;
import org.gteperu.erp.everest.service.Codigo_qrService;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service.Tipo_Operacion_SunatService;
import org.gteperu.erp.everest.service._ClienteService;
import org.gteperu.erp.everest.service._CompanyService;
import org.gteperu.erp.everest.service._Detalle_DocumentoService;
import org.gteperu.erp.everest.service._DocumentoService;
import org.gteperu.erp.everest.service._ProductoService;
import org.gteperu.erp.everest.utils.Constantes;
import org.gteperu.erp.everest.utils.Numero_Letras;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/public/sunat")
public class PruebasSunatRestController {

	@Autowired
 	CPE cpeResource;
 	
 	@Autowired
 	AuditoriaService auditoriaService; 
 	
 	@Autowired
 	_DocumentoService documentoService; 
 	
 	@Autowired
 	_Detalle_DocumentoService detalle_DocumentoService; 
 	
 	@Autowired
	_CompanyService companyService;
 	
 	@Autowired
 	_ClienteService clienteService;
 	
 	@Autowired
 	ParametrosService parametrosService;
 	
 	@Autowired
 	_ProductoService productoService;
 	
 	@Autowired
 	Tipo_Operacion_SunatService tipoOperacionSunatService;
 	
 	@Autowired
 	Auditoria_SunatService auditoria_SunatService;
 	
 	@Autowired
 	Codigo_qrService codigoService;
 	
 	
 	@Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier("sunatReenvioDocJob")
    Job job;

    private static final Logger LOG = LoggerFactory.getLogger(PruebasSunatRestController.class);
    
    
 	@RequestMapping(method = RequestMethod.POST, value = "/pruebaDescargaURL")
	public ResponseWrapper pruebaDescargaURL(@RequestBody @Validated _Producto responseWrapper,
		HttpServletRequest request){
 		ResponseWrapper obj_respuesta= new ResponseWrapper();
		List objtList = new ArrayList();
		String separadorOS = System.getProperty("file.separator");
		try{
			List<_Producto> x = new ArrayList<_Producto>();
//			jobLauncher.run(job, new JobParametersBuilder()
//    				.addLong("timestamp", System.currentTimeMillis())
//    				.toJobParameters());
//    		System.out.print("Se ejecuto el job");
			
			x  = productoService.listarProducto(responseWrapper);
			
	 	} catch(Exception e){
			System.out.println(this.getClass().getSimpleName() + "/pruebaDescargaURL" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			System.out.print("Error " + e.getMessage());
		}
		return obj_respuesta;
	}
 	
 	
 	@RequestMapping(method = RequestMethod.POST, value = "/pruebasLogs")
	public ResponseWrapper pruebasLogs(@RequestBody @Validated _Clientes responseWrapper,
		HttpServletRequest request) throws Exception {
 		ResponseWrapper obj_respuesta= new ResponseWrapper();
		List objtList = new ArrayList();
		String separadorOS = System.getProperty("file.separator");
		System.out.println(request.getRemoteHost());
		System.out.println(request.getRemoteAddr());
		System.out.println(request.getRemotePort());
		System.out.println(request.getRemoteUser());
		try{
				_Parametros pp = new _Parametros(); 
				parametrosService.pruebafail(pp);
				//throw new RuntimeException();
				
//			if(true){
//				throw new RuntimeException("FUE MI ERROR");
//			}
	 	}catch(Exception e){
	 		throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/pruebaDescargaURL",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),responseWrapper);
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/facturacion")
	public void facturacion(HttpServletRequest request, HttpServletResponse response){
		String[] args = null;
		try{
			
		Integer idarea_pueba=1;
	
		//	cpeResource.main(args);
		} catch(Exception e){
			System.out.print("Error facturacion " + e.getMessage());
		}
	 
	}
	
	
	 
	 
	@RequestMapping(method = RequestMethod.POST, value = "/generaDocumento")
	public ResponseWrapper generaDocumento(@RequestBody @Validated _DocumentoCpe documento,
		HttpServletRequest request){
		String[]  rpta_sunat = null;
		ResponseWrapper obj_respuesta= new ResponseWrapper();
		Boolean campos_incompletos=false;
		Integer inserta_documento = 0;
		Integer actualiza_documento = 0;
		Integer inserta_detalle_documento = 0;
		Integer actualiza_detalle_documento = 0;
		_Parametros parametro = new _Parametros();
			String directorio=System.getProperty("user.dir");
			String separador=System.getProperty("file.separator");
		List<_Parametros> lsPar = new ArrayList<>();
		Tipo_Operacion_Sunat tipo_Operacion_Sunat = new Tipo_Operacion_Sunat();
		try{
		 	switch(documento.getCod_tipo_documento()) {
			case Constantes.tipoDocFactura:
						;break;
			case Constantes.tipoDocBoleta:
						;break;
			case Constantes.tipoDocNotaCredito:
						 		 if(documento.getTipo_comprobante_modifica()==null)
								 {
									 campos_incompletos=true;
								 }
								 if(documento.getNro_documento_modifica()==null)
								 {
									 campos_incompletos=true;
								 }
								 //if(documento.getCod_tipo_motivo()==null)
								 //{
									// campos_incompletos=true;
								 //}
								 if(documento.getDescripcion_motivo()==null)
								 {
									 campos_incompletos=true;
								 }
			 			;break;
			case Constantes.tipoDocNotaDebito:
								 if(documento.getTipo_comprobante_modifica()==null)
								 {
									 campos_incompletos=true;
								 }
								 if(documento.getNro_documento_modifica()==null)
								 {
									 campos_incompletos=true;
								 }
								 if(documento.getCod_tipo_motivo()==null)
								 {
									 campos_incompletos=true;
								 }
								 if(documento.getDescripcion_motivo()==null)
								 {
									 campos_incompletos=true;
								 }
										;break;
			default:
						;break;
		}
		 	
		 	
		 	
	 		if(!campos_incompletos) {
	 			
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
				documento.setFecha_documento(new Timestamp(System.currentTimeMillis()));
				
				Numero_Letras num_to_letras = new Numero_Letras();
				String total_letritas = num_to_letras.Convertir(Double.toString(documento.getTotal()), true,documento.getCod_moneda());
				documento.setTotal_letras(total_letritas);
				
				
				String rutabase = ""; 
				String tipo_doc="";
				String dirEmpresaEmisora="";
				for (int i = 0; i < lsPar.size(); i++) {
	    		  	if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaBase)) {
						String[] dir=lsPar.get(i).getValor().split("\\|");
//						 +separador+ dir[2];
						rutabase = dir[0] +separador+ dir[1] +separador+ dir[2];	    					// trae la ruta base donde se creaara el directorio para la empresa.
	    		  		break;
	    			}	
	    		}
				dirEmpresaEmisora = rutabase + company.getNro_documento_empresa();
				File carpetaEmpresaEmisora = new File(dirEmpresaEmisora);
				if (!carpetaEmpresaEmisora.exists()) {
					carpetaEmpresaEmisora.mkdir();
				    System.out.println("Directorio creado => " + dirEmpresaEmisora);
				}
				
				if(documento.getCod_tipo_documento().equals(Constantes.tipoDocFactura)) {
					tipo_doc="FACTURA";
					tipo_Operacion_Sunat.setCodigo(Constantes.codigoDeclaracionFactura);
				}
				if(documento.getCod_tipo_documento().equals(Constantes.tipoDocBoleta)) {
					tipo_doc="BOLETA";
					tipo_Operacion_Sunat.setCodigo(Constantes.codigoDeclaracionBoleta);
				}
				if(documento.getCod_tipo_documento().equals(Constantes.tipoDocNotaCredito)) {
					tipo_doc="NC";
					tipo_Operacion_Sunat.setCodigo(Constantes.codigoDeclaracionNotaCredito);
				}
				if(documento.getCod_tipo_documento().equals(Constantes.tipoDocNotaDebito)) {
					tipo_doc="ND";
					tipo_Operacion_Sunat.setCodigo(Constantes.codigoDeclaracionNotaDebito);
				}
				if(documento.getCod_tipo_documento().equals(Constantes.tipoDocGuiaRemisionRemitente)) {
					tipo_doc="GUIA";
					tipo_Operacion_Sunat.setCodigo(Constantes.codigoDeclaracionGuiaRemisionRemitente);
				}
				if(documento.getCod_tipo_documento().equals(Constantes.tipoDocGuiaRemisionTransportista)) {
					tipo_doc="GUIA";
					tipo_Operacion_Sunat.setCodigo(Constantes.codigoDeclaracionGuiaRemisionTransportista);
				}
				if(documento.getCod_tipo_documento().equals(Constantes.tipoDocPercepcion)) {
					tipo_doc="RTC";
				}
				if(documento.getCod_tipo_documento().equals(Constantes.tipoDocRetencion)) {
					tipo_doc="PCP";
				}
				
				dirEmpresaEmisora = directorio+separador+rutabase+separador+company.getNro_documento_empresa();
				File carpetaDocumentoEmpresaEmisora = new File(dirEmpresaEmisora);
				if (!carpetaDocumentoEmpresaEmisora.exists()) {
					carpetaDocumentoEmpresaEmisora.mkdir();
				    System.out.println("Directorio creado => " + dirEmpresaEmisora);
				}
				
				documento.setDirDocumentoEmpresaEmisora(dirEmpresaEmisora);
	 			
			//rpta_sunat=cpeResource.generaDocumento(documento);
	 			rpta_sunat = cpeResource.generaDocumentoPrueba(documento);
	 			if(rpta_sunat[0].equals("1")){
	 				System.out.print("Exito " + rpta_sunat[2]);
	 			}else{
	 				System.out.print("Error " + rpta_sunat[2]);
	 			}
	 			
			if(rpta_sunat[0].equals("1")){
				documento.setEstado(Integer.parseInt(Constantes.estadoDeclaradoAceptado));
				//SETEA EL NRO_COMPROBANTE DE LA RESPUESTA SUNAT
				String[] frase_parts = rpta_sunat[2].split(",");
				String frase = frase_parts[0];
				int posicionUltimoEspacio = 0;
		        posicionUltimoEspacio = frase.lastIndexOf (" ");
		        String comprobante = frase.substring(posicionUltimoEspacio+1, frase.length());
		        String[] comprobante_parts = comprobante.split("-");
		        String nro_comprobante = comprobante_parts[1];
		        documento.setNro_comprobante(nro_comprobante);
		        
		        Integer count_item = 1;
		        if(documento.getId_documento()!=null && documento.getId_documento()>0){
		        	actualiza_documento = documentoService.actualizarDocumentoEstadoRechazado(documento);
		        }else{
		        	inserta_documento = documentoService.insertarDocumento(documento);
		        }
		        
		        if(documento.getDetalle()!=null && documento.getDetalle().size()>0){
		        	for(_DocumentoCpe_DetalleBean item : documento.getDetalle()){
						item.setId_documento(documento.getId_documento());
						item.setId_empresa(documento.getId_empresa());
						item.setItem(count_item);
						count_item++;
					}
		        }
			}else{
//				documento.setEstado(Integer.parseInt(Constantes.estadoDeclaradoRechazado));
			}
			

	        if(inserta_documento>0){
        		inserta_detalle_documento = detalle_DocumentoService.insertarDocumento_DetallePorLista(documento.getDetalle());
        	}else{
        		if(actualiza_documento>0){
        			Integer elimina_detalle_documento = 0;
        			_DocumentoCpe_DetalleBean documento_detalle = new _DocumentoCpe_DetalleBean();
        			documento_detalle.setId_documento(documento.getId_documento());
        			elimina_detalle_documento = detalle_DocumentoService.eliminarDocumento_Detalle(documento_detalle);
        			if(elimina_detalle_documento>0){
        				for(_DocumentoCpe_DetalleBean item : documento.getDetalle()){
        					inserta_detalle_documento = detalle_DocumentoService.insertarDocumento_DetallePorLista(documento.getDetalle());
            				//actualiza_detalle_documento = detalle_DocumentoService.actualizarDocumento_Detalle(item);
            			}
        			}
        		}
        	}
	        
	        //INSERTA AUDITORIA
	        if((inserta_documento>0 && inserta_detalle_documento>0) || (actualiza_documento>0 && inserta_detalle_documento>0)){
	        	tipo_Operacion_Sunat = tipoOperacionSunatService.retornaObjTipoOperacionSunatPorCodigo(tipo_Operacion_Sunat);
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
	 			
	 			
	 			String separar=" | ";
	 			String compa;
	 			String tipodocu;
	 			String seriedocu;
	 			String nro_compro;
	 			Double igv;
	 			Double importe_total;
	 			Timestamp fechatimestamp;
	 			String tipodoccli;
	 			String num_doc;
	 			String fechas;
	 			String datos;
	 			String hash;
	 			
	 			compa=company.getNro_documento_empresa();
	 			tipodocu=documento.getCod_tipo_documento();
	 			seriedocu=documento.getSerie_comprobante();
	 			nro_compro=documento.getNro_comprobante();
	 			igv=documento.getTotal_igv();
	 			importe_total=documento.getTotal();
	 			
	 			fechatimestamp=documento.getFecha_documento();
	 			Date fecha = new Date(fechatimestamp.getTime());
	 			DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	 			fechas=formatoFecha.format(fecha);
	 			
	 			tipodoccli=cliente.getTipo_doc();
	 			num_doc=documento.getNro_documento_cliente();
	 			hash=rpta_sunat[3];
	 			
	 			
	 			Codigo_qr codigo=new Codigo_qr();
	 			
	 			datos=compa+separar+tipodocu+separar+seriedocu+separar+nro_compro+separar+igv+separar
	 					+importe_total+separar+fechas+separar+tipodoccli+separar+num_doc+separar+hash+separar;
	 			
	 			if(documento.getId_documento()!=null) {
	 				codigo.setId_documento(documento.getId_documento());
		 			codigo.setValor(datos);
		 			codigoService.insertarCodigoqr(codigo);
	 			}
	 				
	        }
	    
			obj_respuesta.setEstado(Integer.parseInt(rpta_sunat[0]));
			obj_respuesta.setMsg(rpta_sunat[2]);
			}
	 	} catch(Exception e){
			System.out.println("\n"+this.getClass().getSimpleName() + "/generaDocumento" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			System.out.print("\nError " + e.getMessage());
		}
		return obj_respuesta;
	}
	

	
	@RequestMapping(method = RequestMethod.POST, value = "/consultaEstadodocumento")
	public ResponseWrapper consultaEstadoDocumento(@RequestBody @Validated _DocumentoCpe documento,
		HttpServletRequest request){
		String[]  rpta_sunat = null;
		ResponseWrapper obj_respuesta= new ResponseWrapper();
		Boolean campos_incompletos=false;
		Integer inserta_documento = 0;
		Integer resp=0;
		Integer inserta_detalle_document = 0;
		_Company company = new _Company();
		try{
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
			  
			  rpta_sunat = cpeResource.consultaEstadoDocumento(documento);
			  obj_respuesta.setEstado(Integer.parseInt(rpta_sunat[0]));
			  obj_respuesta.setMsg(rpta_sunat[2]);
			  if(rpta_sunat[1].equals("0003") && documento.getEstado().intValue()==1){
				  documento.setEstado(2);
					resp = documentoService.actualizarDocumento(documento);
					obj_respuesta.setEstado(2);

			  }
	 	} catch(Exception e){
			System.out.println(this.getClass().getSimpleName() + "/generaDocumento" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			System.out.print("Error " + e.getMessage());
		}
		return obj_respuesta;
	}
	

	 
	@RequestMapping(method = RequestMethod.GET, value = "/darBajaDocumento")
	public ResponseWrapper darBajaDocumento(@RequestBody @Validated _DocumentoCpe documento,
		HttpServletRequest request){
		String  rpta_sunat = null;
		ResponseWrapper obj_respuesta= new ResponseWrapper();
		Boolean campos_incompletos=false;
		

		try{

		 	switch(documento.getCod_tipo_documento()) {
			case Constantes.tipoDocFactura:
				rpta_sunat=cpeResource.darBajaDocumento(documento);
						;break;
			case Constantes.tipoDocBoleta:
						;break;
			case Constantes.tipoDocNotaCredito:
				rpta_sunat=cpeResource.darBajaDocumento(documento);
						;break;
			case Constantes.tipoDocNotaDebito:
				rpta_sunat=cpeResource.darBajaDocumento(documento);
						;break;
			default:
						;break;
		}
	 		 
			rpta_sunat=cpeResource.generaDocumento(documento);
			obj_respuesta.setEstado(Integer.parseInt(rpta_sunat));
			 
	 	} catch(Exception e){
			System.out.println(this.getClass().getSimpleName() +  "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			System.out.print("Error " + e.getMessage());
		}
		return obj_respuesta;
	}
	
	
	
	 
	@RequestMapping(method = RequestMethod.POST, value = "/registroGuia")
	public ResponseWrapper registroGuia(@RequestBody @Validated _DocumentoCpe documento,
		HttpServletRequest request){
		String[]  rpta_sunat = null;
		ResponseWrapper obj_respuesta= new ResponseWrapper();
		_Parametros parametro = new _Parametros();
		List<_Parametros> lsPar = new ArrayList<>();
		Tipo_Operacion_Sunat tipo_Operacion_Sunat = new Tipo_Operacion_Sunat();
		Integer inserta_documento = 0;
		Integer actualiza_documento = 0;
		Integer inserta_detalle_documento = 0;
		Integer actualiza_detalle_documento = 0;
 		try{
 			String directorio=System.getProperty("user.dir");
 			String separador=System.getProperty("file.separator");
 			
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
			String total_letritas = num_to_letras.Convertir(Double.toString(documento.getTotal()), true, documento.getCod_moneda());
			documento.setTotal_letras(total_letritas);
			
			String rutabase = ""; 
			String tipo_doc="";
			String dirEmpresaEmisora="";
			for (int i = 0; i < lsPar.size(); i++) {
    		  	if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaBase)) {
					String[] dir=lsPar.get(i).getValor().split("\\|");
//					 +separador+ dir[2];
					rutabase = dir[0] +separador+ dir[1] +separador+ dir[2];
    					// trae la ruta base donde se creaara el directorio para la empresa.
    		  		break;
    			}	
    		}
			dirEmpresaEmisora = directorio+separador+rutabase+separador+company.getNro_documento_empresa();

			File carpetaEmpresaEmisora = new File(dirEmpresaEmisora);
			System.out.println("carpetaEmpresaEmisora.exists() : "+carpetaEmpresaEmisora.exists());
			if (!carpetaEmpresaEmisora.exists()) {
				carpetaEmpresaEmisora.mkdir();
			    System.out.println("Directorio creado => " + dirEmpresaEmisora);
			}
			
			if(documento.getCod_tipo_documento().equals(Constantes.tipoDocGuiaRemisionRemitente)) {
				tipo_doc="GUIA REMISION";
				tipo_Operacion_Sunat.setCodigo(Constantes.codigoDeclaracionGuiaRemisionRemitente);
			}else{
				tipo_doc="GUIA TRANSPORTISTA";
				tipo_Operacion_Sunat.setCodigo(Constantes.codigoDeclaracionGuiaRemisionTransportista);
			}
			
			dirEmpresaEmisora = dirEmpresaEmisora + File.separator + tipo_doc;
			File carpetaDocumentoEmpresaEmisora = new File(dirEmpresaEmisora);
			if (!carpetaDocumentoEmpresaEmisora.exists()) {
				carpetaDocumentoEmpresaEmisora.mkdir();
			    System.out.println("Directorio creado => " + dirEmpresaEmisora);
			}
			
			documento.setDirDocumentoEmpresaEmisora(dirEmpresaEmisora);
 			
			
		  rpta_sunat=cpeResource.registroGuia(documento);
			obj_respuesta.setEstado(Integer.parseInt(rpta_sunat[1]));
			
 			if(rpta_sunat[0].equals("1")){
 				System.out.print("Exito " + rpta_sunat[2]);
 			}else{
 				System.out.print("Error " + rpta_sunat[2]);
 			}
 			
		if(rpta_sunat[0].equals("1")){
			documento.setEstado(Integer.parseInt(Constantes.estadoDeclaradoAceptado));
			//SETEA EL NRO_COMPROBANTE DE LA RESPUESTA SUNAT
			String[] frase_parts = rpta_sunat[2].split(",");
			String frase = frase_parts[0];
			int posicionUltimoEspacio = 0;
	        posicionUltimoEspacio = frase.lastIndexOf (" ");
	        String comprobante = frase.substring(posicionUltimoEspacio+1, frase.length());
	        String[] comprobante_parts = comprobante.split("-");
	        String nro_comprobante = comprobante_parts[1];
	        documento.setNro_comprobante(nro_comprobante);
	        
	        Integer count_item = 1;
	        if(documento.getId_documento()!=null && documento.getId_documento()>0){
	        	actualiza_documento = documentoService.actualizarDocumentoEstadoRechazado(documento);
	        }else{
	        	inserta_documento = documentoService.insertarDocumento(documento);
	        }
	        
	        if(documento.getDetalle()!=null && documento.getDetalle().size()>0){
	        	for(_DocumentoCpe_DetalleBean item : documento.getDetalle()){
					item.setId_documento(documento.getId_documento());
					item.setId_empresa(documento.getId_empresa());
					item.setItem(count_item);
					count_item++;
				}
	        }else if(documento.getDetalle_Guia()!=null && documento.getDetalle_Guia().size()>0){
	        	for(_CpeGuiaRemisionDetalleBean item : documento.getDetalle_Guia()){
					item.setId_documento(documento.getId_documento());
					item.setId_empresa(documento.getId_empresa());
					item.setITEM(count_item);
					count_item++;
				}
	        }
		}else{
//			documento.setEstado(Integer.parseInt(Constantes.estadoDeclaradoRechazado));
		}
		

        if(inserta_documento>0){
    		inserta_detalle_documento = detalle_DocumentoService.insertarDocumento_Detalle_GuiaPorLista(documento.getDetalle_Guia());
    	}else{
    		if(actualiza_documento>0){
    			Integer elimina_detalle_documento = 0;
    			_DocumentoCpe_DetalleBean documento_detalle = new _DocumentoCpe_DetalleBean();
    			documento_detalle.setId_documento(documento.getId_documento());
    			elimina_detalle_documento = detalle_DocumentoService.eliminarDocumento_Detalle(documento_detalle);
    			if(elimina_detalle_documento>0){
    				for(_DocumentoCpe_DetalleBean item : documento.getDetalle()){
    					inserta_detalle_documento = detalle_DocumentoService.insertarDocumento_DetallePorLista(documento.getDetalle());
        				//actualiza_detalle_documento = detalle_DocumentoService.actualizarDocumento_Detalle(item);
        			}
    			}
    		}
    	}
        
        //INSERTA AUDITORIA
        if((inserta_documento>0 && inserta_detalle_documento>0) || (actualiza_documento>0 && inserta_detalle_documento>0)){
        	tipo_Operacion_Sunat = tipoOperacionSunatService.retornaObjTipoOperacionSunatPorCodigo(tipo_Operacion_Sunat);
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
			 
	 	} catch(Exception e){
			System.out.println(this.getClass().getSimpleName() + "/registroGuia" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			System.out.print("Error " + e.getMessage());
		}
		return obj_respuesta;
	}
	
	 
	@RequestMapping(method = RequestMethod.POST, value = "/reemplazarGuia")
		public ResponseWrapper reemplazarGuia(@RequestBody @Validated _DocumentoCpe documento,
			HttpServletRequest request){
			String[]  rpta_sunat = null;
			ResponseWrapper obj_respuesta= new ResponseWrapper();
			_Parametros parametro = new _Parametros();
			List<_Parametros> lsPar = new ArrayList<>();
			Tipo_Operacion_Sunat tipo_Operacion_Sunat = new Tipo_Operacion_Sunat();
			Integer inserta_documento = 0;
			Integer actualiza_documento = 0;
			Integer inserta_detalle_documento = 0;
			Integer actualiza_detalle_documento = 0;
	 		try{
	 			String directorio=System.getProperty("user.dir");
	 			String separador=System.getProperty("file.separator");
	 			
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
				String total_letritas = num_to_letras.Convertir(Double.toString(documento.getTotal()), true, documento.getCod_moneda());
				documento.setTotal_letras(total_letritas);
				
				String rutabase = ""; 
				String tipo_doc="";
				String dirEmpresaEmisora="";
				for (int i = 0; i < lsPar.size(); i++) {
	    		  	if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaBase)) {
						String[] dir=lsPar.get(i).getValor().split("\\|");
//						 +separador+ dir[2];
						rutabase = dir[0] +separador+ dir[1] +separador+ dir[2];
	    					// trae la ruta base donde se creaara el directorio para la empresa.
	    		  		break;
	    			}	
	    		}
				dirEmpresaEmisora = directorio+separador+rutabase+separador+company.getNro_documento_empresa();

				File carpetaEmpresaEmisora = new File(dirEmpresaEmisora);
				System.out.println("carpetaEmpresaEmisora.exists() : "+carpetaEmpresaEmisora.exists());
				if (!carpetaEmpresaEmisora.exists()) {
					carpetaEmpresaEmisora.mkdir();
				    System.out.println("Directorio creado => " + dirEmpresaEmisora);
				}
				
				if(documento.getCod_tipo_documento().equals(Constantes.tipoDocGuiaRemisionRemitente)) {
					tipo_doc="GUIA REMISION";
					tipo_Operacion_Sunat.setCodigo(Constantes.codigoDeclaracionGuiaRemisionRemitente);
				}else{
					tipo_doc="GUIA TRANSPORTISTA";
					tipo_Operacion_Sunat.setCodigo(Constantes.codigoDeclaracionGuiaRemisionTransportista);
				}
				
				dirEmpresaEmisora = dirEmpresaEmisora + File.separator + tipo_doc;
				File carpetaDocumentoEmpresaEmisora = new File(dirEmpresaEmisora);
				if (!carpetaDocumentoEmpresaEmisora.exists()) {
					carpetaDocumentoEmpresaEmisora.mkdir();
				    System.out.println("Directorio creado => " + dirEmpresaEmisora);
				}
				
				documento.setDirDocumentoEmpresaEmisora(dirEmpresaEmisora);
	 			
			  rpta_sunat=cpeResource.reemplazarGuia(documento);				
				
				obj_respuesta.setEstado(Integer.parseInt(rpta_sunat[1]));
				
	 			if(rpta_sunat[0].equals("1")){
	 				System.out.print("Exito " + rpta_sunat[2]);
	 			}else{
	 				System.out.print("Error " + rpta_sunat[2]);
	 			}
	 			
			if(rpta_sunat[0].equals("1")){
				documento.setEstado(Integer.parseInt(Constantes.estadoDeclaradoAceptado));
				//SETEA EL NRO_COMPROBANTE DE LA RESPUESTA SUNAT
				String[] frase_parts = rpta_sunat[2].split(",");
				String frase = frase_parts[0];
				int posicionUltimoEspacio = 0;
		        posicionUltimoEspacio = frase.lastIndexOf (" ");
		        String comprobante = frase.substring(posicionUltimoEspacio+1, frase.length());
		        String[] comprobante_parts = comprobante.split("-");
		        String nro_comprobante = comprobante_parts[1];
		        documento.setNro_comprobante(nro_comprobante);
		        
		        Integer count_item = 1;
		        if(documento.getId_documento()!=null && documento.getId_documento()>0){
		        	actualiza_documento = documentoService.actualizarDocumentoEstadoRechazado(documento);
		        }else{
		        	inserta_documento = documentoService.insertarDocumento(documento);
		        }
		        
		        if(documento.getDetalle()!=null && documento.getDetalle().size()>0){
		        	for(_DocumentoCpe_DetalleBean item : documento.getDetalle()){
						item.setId_documento(documento.getId_documento());
						item.setId_empresa(documento.getId_empresa());
						item.setItem(count_item);
						count_item++;
					}
		        }else if(documento.getDetalle_Guia()!=null && documento.getDetalle_Guia().size()>0){
		        	for(_CpeGuiaRemisionDetalleBean item : documento.getDetalle_Guia()){
						item.setId_documento(documento.getId_documento());
						item.setId_empresa(documento.getId_empresa());
						item.setITEM(count_item);
						count_item++;
					}
		        }
			}else{
//				documento.setEstado(Integer.parseInt(Constantes.estadoDeclaradoRechazado));
			}
			

	        if(inserta_documento>0){
	    		inserta_detalle_documento = detalle_DocumentoService.insertarDocumento_Detalle_GuiaPorLista(documento.getDetalle_Guia());
	    	}else{
	    		if(actualiza_documento>0){
	    			Integer elimina_detalle_documento = 0;
	    			_DocumentoCpe_DetalleBean documento_detalle = new _DocumentoCpe_DetalleBean();
	    			documento_detalle.setId_documento(documento.getId_documento());
	    			elimina_detalle_documento = detalle_DocumentoService.eliminarDocumento_Detalle(documento_detalle);
	    			if(elimina_detalle_documento>0){
	    				for(_DocumentoCpe_DetalleBean item : documento.getDetalle()){
	    					inserta_detalle_documento = detalle_DocumentoService.insertarDocumento_DetallePorLista(documento.getDetalle());
	        				//actualiza_detalle_documento = detalle_DocumentoService.actualizarDocumento_Detalle(item);
	        			}
	    			}
	    		}
	    	}
	        
	        //INSERTA AUDITORIA
	        if((inserta_documento>0 && inserta_detalle_documento>0) || (actualiza_documento>0 && inserta_detalle_documento>0)){
	        	tipo_Operacion_Sunat = tipoOperacionSunatService.retornaObjTipoOperacionSunatPorCodigo(tipo_Operacion_Sunat);
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
				 
		 	} catch(Exception e){
				System.out.println(this.getClass().getSimpleName() + "/registroGuia" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
				System.out.print("Error " + e.getMessage());
			}
			return obj_respuesta;
		}
	 

	 
		@RequestMapping(method = RequestMethod.GET, value = "/generaDocumentoRTPC")
		public ResponseWrapper generaDocumentoRTPC(@RequestBody @Validated _DocumentoCpe documento,
			HttpServletRequest request){
			String  rpta_sunat = null;
			ResponseWrapper obj_respuesta= new ResponseWrapper();
			Boolean campos_incompletos=false;
			try{
//		   	rpta_sunat=cpeResource.generaDocumentoRTPC(documento);
				obj_respuesta.setEstado(Integer.parseInt(rpta_sunat));
				 
		 	} catch(Exception e){
				System.out.println(this.getClass().getSimpleName() + "/generaDocumento" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
				System.out.print("Error " + e.getMessage());
			}
			return obj_respuesta;
		}
		


}
