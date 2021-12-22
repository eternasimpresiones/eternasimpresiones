package org.gteperu.erp.everest.threads;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gteperu.erp.everest.cpe.CPE;
import org.gteperu.erp.everest.domain.Auditoria_Sunat;
import org.gteperu.erp.everest.domain.Codigo_qr;
import org.gteperu.erp.everest.domain.Serie;
import org.gteperu.erp.everest.domain.Tipo_Operacion_Sunat;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._Cpe_RetencionPercepcionBean;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._DocumentoCpe_DetalleBean;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.domain._Retencion;
import org.gteperu.erp.everest.service.Auditoria_SunatService;
import org.gteperu.erp.everest.service.Codigo_qrService;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service.SerieService;
import org.gteperu.erp.everest.service.Tipo_Operacion_SunatService;
import org.gteperu.erp.everest.service._ClienteService;
import org.gteperu.erp.everest.service._CompanyService;
import org.gteperu.erp.everest.service._DocumentoService;
import org.gteperu.erp.everest.service._RetencionService;
import org.gteperu.erp.everest.utils.Constantes;
import org.gteperu.erp.everest.utils.Numero_Letras;

public class ReenvioDocSunatThread extends Thread {

	

	
	private _DocumentoCpe documento;
	private _Retencion retencionDoc;

	private CPE cpeResource;
	private Tipo_Operacion_SunatService tipoOperacionSunatService;
	private Auditoria_SunatService auditoria_SunatService;
	private Codigo_qrService codigoService;
	private _DocumentoService documentoService;
	private _RetencionService retencionService;
	private _CompanyService companyService;
	private _ClienteService clienteService;
	private ParametrosService parametrosService;

	public ReenvioDocSunatThread(CPE cpeResource, Tipo_Operacion_SunatService tipoOperacionSunatService,
			Auditoria_SunatService auditoria_SunatService, Codigo_qrService codigoService,
			_DocumentoService documentoService, _RetencionService retencionService, _CompanyService companyService,
			_ClienteService clienteService, ParametrosService parametrosService) {
		super();
		this.cpeResource = cpeResource;
		this.tipoOperacionSunatService = tipoOperacionSunatService;
		this.auditoria_SunatService = auditoria_SunatService;
		this.codigoService = codigoService;
		this.documentoService = documentoService;
		this.retencionService = retencionService;
		this.companyService = companyService;
		this.clienteService = clienteService;
		this.parametrosService = parametrosService;
	}





	@Override
	public void run() {
		// TODO Auto-generated method stub
		String[]  rpta_sunat = null;
		Integer actualiza_documento = 0;
		_Cpe_RetencionPercepcionBean retencion = new _Cpe_RetencionPercepcionBean();
		String directorio=System.getProperty("user.dir");
		String separador=System.getProperty("file.separator");
		String tipo_doc="",rutamatriz="";
		Boolean campos_incompletos=false;
		List<_Parametros> lsPar = new ArrayList<>();
		_Parametros parametro = new _Parametros();
		Tipo_Operacion_Sunat tipo_Operacion_Sunat = new Tipo_Operacion_Sunat();
			try {
				String rutabase = ""; 
				String dirEmpresaEmisora="";
				for (int i = 0; i < lsPar.size(); i++) {
					if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
						rutamatriz=lsPar.get(i).getValor();
						break;
	 				}
	    		}
				if(documento != null) {
					

		 			_Company company = new _Company();
		    		company.setId_empresa(documento.getId_empresa());
		    		company = companyService.retornaEmpresaPorIdempresa(company);
		    		
		    		_Clientes cliente = new _Clientes();
					cliente.setId_cliente(documento.getId_cliente_cpe());
					cliente = clienteService.retornaClientePorIdcliente(cliente);
					
					documento.setEstado(Integer.parseInt(Constantes.estadoSinRespuesta));
					actualiza_documento = documentoService.actualizarDocumentoEstadoRechazado(documento);
					
		 			parametro.setGrupo(Constantes.codigoGrupoIniParametros);
		    		parametro.setId_empresa(0);
		    		lsPar = parametrosService.retornaObjParametrosPorGrupoe(parametro);
					
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
					
					
					dirEmpresaEmisora = rutamatriz+separador+company.getNro_documento_empresa();
					
					Serie s = new Serie();
					s.setIdlocal(documento.getIdlocal());
					s.setIdsutipodocumento(documento.getIdsutipodocumento());

						switch(documento.getCod_tipo_documento()) {
						case Constantes.tipoDocFactura:
						case Constantes.tipoDocBoleta:
						case Constantes.tipoDocNotaCredito:
						case Constantes.tipoDocNotaDebito:
							
							if(documento.getCod_tipo_documento() == Constantes.tipoDocNotaCredito) {
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
								 
									tipo_doc="NC";
									tipo_Operacion_Sunat.setCodigo(Constantes.codigoDeclaracionNotaCredito);
							}
							
							if(documento.getCod_tipo_documento() == Constantes.tipoDocNotaDebito) {
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
								 if(documento.getCod_tipo_motivo().equals("02")){
										Double total = 0.0;
										Double igv = 0.0;
										for(_DocumentoCpe_DetalleBean det : documento.getDetalle()){
											det.setPrecio(det.getAumento_unitario());
											det.setIgv(det.getIgv_aumento());
											total=total + det.getPrecio();
											igv = igv + det.getIgv();
										}
										total = total + igv;
										total = Math.round(total*100.0)/100.0;
										igv = Math.round(igv*100.0)/100.0;

										documento.setTotal(total);
										documento.setTotal_igv(igv);	

								 }
								 if(documento.getDescripcion_motivo()==null)
								 {
									 campos_incompletos=true;
								 }
								 
									tipo_doc="ND";
									tipo_Operacion_Sunat.setCodigo(Constantes.codigoDeclaracionNotaDebito);
							}
							if(documento.getCod_tipo_documento().equals(Constantes.tipoDocFactura)) {
								tipo_doc="FACTURA";
								tipo_Operacion_Sunat.setCodigo(Constantes.codigoDeclaracionFactura);
							}
							if(documento.getCod_tipo_documento().equals(Constantes.tipoDocBoleta)) {
								tipo_doc="BOLETA";
								tipo_Operacion_Sunat.setCodigo(Constantes.codigoDeclaracionBoleta);
							}
							
							if(!campos_incompletos) {
								
								documento.setFecha_documento(new Timestamp(System.currentTimeMillis()));
								dirEmpresaEmisora = rutamatriz+separador+company.getNro_documento_empresa()+separador+tipo_doc;

 								File carpetaDocumentoEmpresaEmisora = new File(dirEmpresaEmisora);
								if (!carpetaDocumentoEmpresaEmisora.exists()) {
									carpetaDocumentoEmpresaEmisora.mkdirs();
								    System.out.println("Directorio creado => " + dirEmpresaEmisora);
								}
								
								documento.setDirDocumentoEmpresaEmisora(dirEmpresaEmisora);
								
								Auditoria_Sunat auditoria_Sunat = new Auditoria_Sunat();

								auditoria_Sunat.setId_documento(documento.getId_documento());
								auditoria_Sunat.setFlag_respuesta_sunat("0");
								auditoria_Sunat.setCodigo_respuesta_sunat("0");
								auditoria_Sunat.setMensaje_respuesta_sunat("SIN RESPUESTA");
								auditoria_Sunat.setId_tipo_operacion(tipo_Operacion_Sunat.getId_tipo_operacion_sunat());
								auditoria_Sunat.setId_usuario(documento.getId_usuario());
								auditoria_Sunat.setFecha_registro(new Timestamp(System.currentTimeMillis()));
								Integer inserta_auditoria = auditoria_SunatService.actualizarAuditoria_Sunat(auditoria_Sunat);
								System.out.print("\nSE INSERTO AUDITORIA_SUNAT => " + inserta_auditoria.toString());
								
							rpta_sunat = cpeResource.generaDocumentoPrueba(documento);
							String[] frase_parts = rpta_sunat[2].split(",");
							String frase = frase_parts[0];
							int posicionUltimoEspacio = 0;
							posicionUltimoEspacio = frase.lastIndexOf (" ");
							String comprobante = frase.substring(posicionUltimoEspacio+1, frase.length());
							String[] comprobante_parts = comprobante.split("-");
							String nro_comprobante = comprobante_parts[1];
							documento.setNro_comprobante(nro_comprobante);
							if(rpta_sunat[0].equals("1")){
								System.out.print("Exito " + rpta_sunat[2]);
								
								
								String separar="|";
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
										+importe_total+separar+fechas+separar+tipodoccli+separar+num_doc+separar+hash;
								
								if(documento.getId_documento()!=null) {
									codigo.setId_documento(documento.getId_documento());
									codigo.setValor(datos);
									codigoService.insertarCodigoqr(codigo);
								}
								
								documento.setEstado(Integer.parseInt(Constantes.estadoDeclaradoAceptado));
								actualiza_documento = documentoService.actualizarDocumentoEstadoRechazado(documento);
							}else{
								System.out.print("Error " + rpta_sunat[2]);
								
								if(Integer.parseInt(rpta_sunat[1])>2000 && Integer.parseInt(rpta_sunat[1])<4000) {
									documento.setEstado(Integer.parseInt(Constantes.estadoRechazadoTotal));
									actualiza_documento = documentoService.actualizarDocumentoEstadoRechazado(documento);
								}else if(Integer.parseInt(rpta_sunat[1])>100 && Integer.parseInt(rpta_sunat[1])<2000) {
									documento.setEstado(Integer.parseInt(Constantes.estadoRechazadoParcial));
									actualiza_documento = documentoService.actualizarDocumentoEstadoRechazado(documento);
								}else {
									documento.setEstado(Integer.parseInt(Constantes.estadoSinRespuesta));
									actualiza_documento = documentoService.actualizarDocumentoEstadoRechazado(documento);
								}
							}
						}
							break;
						case "09":
						case "31":


							if(documento.getCod_tipo_documento().equals(Constantes.tipoDocGuiaRemisionRemitente)) {
								tipo_doc="GUIA REMISION";
								tipo_Operacion_Sunat.setCodigo(Constantes.codigoDeclaracionGuiaRemisionRemitente);
							}else{
								tipo_doc="GUIA TRANSPORTISTA";
								tipo_Operacion_Sunat.setCodigo(Constantes.codigoDeclaracionGuiaRemisionTransportista);
							}
							dirEmpresaEmisora = rutamatriz+separador+company.getNro_documento_empresa()+separador+tipo_doc;
							File carpetaDocumentoEmpresaEmisora = new File(dirEmpresaEmisora);
							if (!carpetaDocumentoEmpresaEmisora.exists()) {
								carpetaDocumentoEmpresaEmisora.mkdir();
							    System.out.println("Directorio creado => " + dirEmpresaEmisora);
							}
							
							documento.setDirDocumentoEmpresaEmisora(dirEmpresaEmisora);
				 										
							
							rpta_sunat=cpeResource.registroGuia(documento);
							
							if(rpta_sunat[0].equals("1")){
								System.out.print("Exito " + rpta_sunat[2]);
								documento.setEstado(Integer.parseInt(Constantes.estadoDeclaradoAceptado));
								actualiza_documento = documentoService.actualizarDocumentoEstadoRechazado(documento);
							}else{
								System.out.print("Error " + rpta_sunat[2]);
								if(Integer.parseInt(rpta_sunat[1])>2000 && Integer.parseInt(rpta_sunat[1])<4000) {
									documento.setEstado(Integer.parseInt(Constantes.estadoRechazadoTotal));
									actualiza_documento = documentoService.actualizarDocumentoEstadoRechazado(documento);
								}else if(Integer.parseInt(rpta_sunat[1])>100 && Integer.parseInt(rpta_sunat[1])<2000) {
									documento.setEstado(Integer.parseInt(Constantes.estadoRechazadoParcial));
									actualiza_documento = documentoService.actualizarDocumentoEstadoRechazado(documento);
								}else {
									documento.setEstado(Integer.parseInt(Constantes.estadoSinRespuesta));
									actualiza_documento = documentoService.actualizarDocumentoEstadoRechazado(documento);
								}
							}
							
							break;
						}

					//INSERTA AUDITORIA

						
						tipo_Operacion_Sunat = tipoOperacionSunatService.retornaObjTipoOperacionSunatPorCodigo(tipo_Operacion_Sunat);
						Auditoria_Sunat auditoria_Sunat = new Auditoria_Sunat();
						
						auditoria_Sunat.setId_documento(documento.getId_documento());
						auditoria_Sunat.setFlag_respuesta_sunat(rpta_sunat[0]);
						auditoria_Sunat.setCodigo_respuesta_sunat(rpta_sunat[1]);
						auditoria_Sunat.setMensaje_respuesta_sunat(rpta_sunat[2]);
						auditoria_Sunat.setId_tipo_operacion(tipo_Operacion_Sunat.getId_tipo_operacion_sunat());
						auditoria_Sunat.setId_usuario(documento.getId_usuario());
						auditoria_Sunat.setFecha_registro(new Timestamp(System.currentTimeMillis()));
						Integer inserta_auditoria = auditoria_SunatService.actualizarAuditoria_Sunat(auditoria_Sunat);
						System.out.print("\nSE INSERTO AUDITORIA_SUNAT => " + inserta_auditoria.toString());
						
					
				
				}else {
					
		 			_Company company = new _Company();
		    		company.setId_empresa(retencionDoc.getId_empresa());
		    		company = companyService.retornaEmpresaPorIdempresa(company);
		    		
		    		_Clientes cliente = new _Clientes();
					cliente.setId_cliente(retencionDoc.getId_cliente());
					cliente = clienteService.retornaClientePorIdcliente(cliente);
					
					retencionDoc.setEstado(Integer.parseInt(Constantes.estadoSinRespuesta));
					actualiza_documento = retencionService.actualizarRetencionEstadoRechazado(retencionDoc);
					
		 			parametro.setGrupo(Constantes.codigoGrupoIniParametros);
		    		parametro.setId_empresa(0);
		    		lsPar = parametrosService.retornaObjParametrosPorGrupoe(parametro);
		    		
		    		retencionDoc.setRuc_empresaEmisora(company.getNro_documento_empresa());
		    		retencionDoc.setUserSol_empresaEmisora(company.getUsuario_sol_empresa());
		    		retencionDoc.setPassSol_empresaEmisora(company.getPass_sol_empresa());
		    		retencionDoc.setPassFirma_empresaEmisora(company.getPass_firma_empresa());
		    		retencionDoc.setNro_documento_empresa(company.getNro_documento_empresa());
		    		retencionDoc.setTipo_documento_empresa(company.getTipo_doc_empresa());
		    		retencionDoc.setNombre_comercial_empresa(company.getNombre_comercial_empresa());
		    		retencionDoc.setCod_ubigeo_empresa(company.getUbigeo().getCodigo());
		    		retencionDoc.setDireccion_empresa(company.getDireccion_empresa());
		    		retencionDoc.setDepartamento_empresa(company.getUbigeo().getDepartamento());
		    		retencionDoc.setProvincia_empresa(company.getUbigeo().getProvincia());
					retencionDoc.setDistrito_empresa(company.getUbigeo().getDistrito());
					retencionDoc.setRazon_social_empresa(company.getRazon_social_empresa());
					retencionDoc.setTipo_documento_proveedor(cliente.getTipo_doc());
					retencionDoc.setNro_documento_proveedor(cliente.getNro_doc());
					retencionDoc.setNombre_comercial_proveedor(cliente.getRazon_comercial());
					retencionDoc.setDireccion_proveedor(cliente.getDireccion_fiscal());
					retencionDoc.setCod_ubigeo_proveedor(cliente.getUbigeo().getCodigo());
					retencionDoc.setZona_urbanizacion_proveedor("");
					retencionDoc.setCiudad_proveedor("");
					retencionDoc.setDepartamento_proveedor(cliente.getUbigeo().getDepartamento());
					retencionDoc.setDistrito_proveedor(cliente.getUbigeo().getDistrito());
					retencionDoc.setProvincia_proveedor(cliente.getUbigeo().getProvincia());
					retencionDoc.setPais_proveedor("PE");//obligatorio (varia dependiendo del pais, revisar catalogo 04)
					retencionDoc.setRazon_social_proveedor(cliente.getRazon_social());
					
					retencionDoc.setFecha_documento(new Timestamp(System.currentTimeMillis()));
					
 					tipo_doc="";
					dirEmpresaEmisora = rutamatriz+separador+company.getNro_documento_empresa() ;

 					File carpetaEmpresaEmisora = new File(dirEmpresaEmisora);
					if (!carpetaEmpresaEmisora.exists()) {
						carpetaEmpresaEmisora.mkdir();
					    System.out.println("Directorio creado => " + dirEmpresaEmisora);
					}
					
					if(retencionDoc.getCod_tipo_documento().equals(Constantes.tipoDocPercepcion)) {
						tipo_doc="PCP";
						retencionDoc.setTipo_documento_cliente(cliente.getTipo_doc());
						retencionDoc.setNro_documento_cliente(cliente.getNro_doc());
						retencionDoc.setNombre_comercial_cliente(cliente.getRazon_comercial());
						retencionDoc.setDireccion_cliente(cliente.getDireccion_fiscal());
						retencionDoc.setCod_ubigeo_cliente(cliente.getUbigeo().getCodigo());
						retencionDoc.setZona_urbanizacion_cliente("");
						retencionDoc.setCiudad_cliente("");
						retencionDoc.setDepartamento_cliente(cliente.getUbigeo().getDepartamento());
						retencionDoc.setDistrito_cliente(cliente.getUbigeo().getDistrito());
						retencionDoc.setProvincia_cliente(cliente.getUbigeo().getProvincia());
						retencionDoc.setPais_cliente("PE");//obligatorio (varia dependiendo del pais, revisar catalogo 04)
						retencionDoc.setRazon_social_cliente(cliente.getRazon_social());
						

					}
					if(retencionDoc.getCod_tipo_documento().equals(Constantes.tipoDocRetencion)) {
						tipo_doc="RTC";

						retencionDoc.setTipo_documento_proveedor(cliente.getTipo_doc());
						retencionDoc.setNro_documento_proveedor(cliente.getNro_doc());
						retencionDoc.setNombre_comercial_proveedor(cliente.getRazon_comercial());
						retencionDoc.setDireccion_proveedor(cliente.getDireccion_fiscal());
						retencionDoc.setCod_ubigeo_proveedor(cliente.getUbigeo().getCodigo());
						retencionDoc.setZona_urbanizacion_proveedor("");
						retencionDoc.setCiudad_proveedor("");
						retencionDoc.setDepartamento_proveedor(cliente.getUbigeo().getDepartamento());
						retencionDoc.setDistrito_proveedor(cliente.getUbigeo().getDistrito());
						retencionDoc.setProvincia_proveedor(cliente.getUbigeo().getProvincia());
						retencionDoc.setPais_proveedor("PE");//obligatorio (varia dependiendo del pais, revisar catalogo 04)
						retencionDoc.setRazon_social_proveedor(cliente.getRazon_social());
					}
					dirEmpresaEmisora = rutamatriz+separador+company.getNro_documento_empresa()+separador+tipo_doc;

 					File carpetaDocumentoEmpresaEmisora = new File(dirEmpresaEmisora);
					if (!carpetaDocumentoEmpresaEmisora.exists()) {
						carpetaDocumentoEmpresaEmisora.mkdir();
					    System.out.println("Directorio creado => " + dirEmpresaEmisora);
					}
					
					retencionDoc.setDirDocumentoEmpresaEmisora(dirEmpresaEmisora);
					
					
					retencion = null;
					retencion=cpeResource.generaDocumentoRTPC(retencionDoc);
		 			if(retencion.getRpta_sunat()[0].equals("1")){
						System.out.print("Exito " + retencion.getRpta_sunat()[2]);
						retencionDoc.setEstado(Integer.parseInt(Constantes.estadoDeclaradoAceptado));
						actualiza_documento = retencionService.actualizarRetencionEstadoRechazado(retencionDoc);
					}else{
						System.out.print("Error " + retencion.getRpta_sunat()[2]);
						if(Integer.parseInt(rpta_sunat[1])>2000 && Integer.parseInt(rpta_sunat[1])<4000) {
							retencionDoc.setEstado(Integer.parseInt(Constantes.estadoRechazadoTotal));
							actualiza_documento = retencionService.actualizarRetencionEstadoRechazado(retencionDoc);
						}else if(Integer.parseInt(rpta_sunat[1])>100 && Integer.parseInt(rpta_sunat[1])<2000) {
							retencionDoc.setEstado(Integer.parseInt(Constantes.estadoRechazadoParcial));
							actualiza_documento = retencionService.actualizarRetencionEstadoRechazado(retencionDoc);
						}else {
							retencionDoc.setEstado(Integer.parseInt(Constantes.estadoSinRespuesta));
							actualiza_documento = retencionService.actualizarRetencionEstadoRechazado(retencionDoc);
						}
					}
					
				
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}





	public _CompanyService getCompanyService() {
		return companyService;
	}





	public void setCompanyService(_CompanyService companyService) {
		this.companyService = companyService;
	}

	public _DocumentoCpe getDocumento() {
		return documento;
	}





	public void setDocumento(_DocumentoCpe documento) {
		this.documento = documento;
	}





	public _Retencion getRetencionDoc() {
		return retencionDoc;
	}





	public void setRetencionDoc(_Retencion retencionDoc) {
		this.retencionDoc = retencionDoc;
	}





	public _ClienteService getClienteService() {
		return clienteService;
	}





	public void setClienteService(_ClienteService clienteService) {
		this.clienteService = clienteService;
	}





	public ParametrosService getParametrosService() {
		return parametrosService;
	}





	public void setParametrosService(ParametrosService parametrosService) {
		this.parametrosService = parametrosService;
	}





	public CPE getCpeResource() {
		return cpeResource;
	}




	public void setCpeResource(CPE cpeResource) {
		this.cpeResource = cpeResource;
	}




	public Tipo_Operacion_SunatService getTipoOperacionSunatService() {
		return tipoOperacionSunatService;
	}




	public void setTipoOperacionSunatService(Tipo_Operacion_SunatService tipoOperacionSunatService) {
		this.tipoOperacionSunatService = tipoOperacionSunatService;
	}




	public Auditoria_SunatService getAuditoria_SunatService() {
		return auditoria_SunatService;
	}




	public void setAuditoria_SunatService(Auditoria_SunatService auditoria_SunatService) {
		this.auditoria_SunatService = auditoria_SunatService;
	}




	public Codigo_qrService getCodigoService() {
		return codigoService;
	}




	public void setCodigoService(Codigo_qrService codigoService) {
		this.codigoService = codigoService;
	}




	public _DocumentoService getDocumentoService() {
		return documentoService;
	}




	public void setDocumentoService(_DocumentoService documentoService) {
		this.documentoService = documentoService;
	}




	public _RetencionService getRetencionService() {
		return retencionService;
	}




	public void setRetencionService(_RetencionService retencionService) {
		this.retencionService = retencionService;
	}

	
	
	
	
	
}
