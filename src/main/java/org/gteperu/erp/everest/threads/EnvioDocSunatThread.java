package org.gteperu.erp.everest.threads;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gteperu.erp.everest.cpe.CPE;
import org.gteperu.erp.everest.domain.Auditoria_Sunat;
import org.gteperu.erp.everest.domain.Codigo_qr;
import org.gteperu.erp.everest.domain.Tipo_Operacion_Sunat;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._Cpe_RetencionPercepcionBean;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.domain._Retencion;
import org.gteperu.erp.everest.service.Auditoria_SunatService;
import org.gteperu.erp.everest.service.Codigo_qrService;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service.SerieService;
import org.gteperu.erp.everest.service.Tipo_Operacion_SunatService;
import org.gteperu.erp.everest.service._DocumentoService;
import org.gteperu.erp.everest.service._RetencionService;
import org.gteperu.erp.everest.utils.Constantes;
import org.gteperu.erp.everest.domain.Serie; 

public class EnvioDocSunatThread extends Thread {

	private _DocumentoCpe documento;
	private _Company company;
	private _Clientes cliente;
	private _Retencion retencionDoc;
	private Tipo_Operacion_Sunat tipo_Operacion_Sunat;

	private CPE cpeResource;
	private Tipo_Operacion_SunatService tipoOperacionSunatService;
	private Auditoria_SunatService auditoria_SunatService;
	private Codigo_qrService codigoService;
	private _DocumentoService documentoService;
	private _RetencionService retencionService;
	private ParametrosService parametrosService;
	private SerieService serieService;

	

	public EnvioDocSunatThread(CPE cpeResource, Tipo_Operacion_SunatService tipoOperacionSunatService,
			Auditoria_SunatService auditoria_SunatService, Codigo_qrService codigoService,
			_DocumentoService documentoService, _RetencionService retencionService, ParametrosService parametrosService,SerieService serieService) {
		super();
		this.cpeResource = cpeResource;
		this.tipoOperacionSunatService = tipoOperacionSunatService;
		this.auditoria_SunatService = auditoria_SunatService;
		this.codigoService = codigoService;
		this.documentoService = documentoService;
		this.retencionService = retencionService;
		this.parametrosService=parametrosService;
		this.serieService=serieService;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String[] rpta_sunat = null;
		Integer actualiza_documento = 0;
		_Cpe_RetencionPercepcionBean retencion = new _Cpe_RetencionPercepcionBean();
		String rutamatriz="";	
		String separador = System.getProperty("file.separator");
		_Parametros parametro = new _Parametros();
		List<_Parametros> lsPar = new ArrayList<>();
		parametro.setGrupo(Constantes.codigoGrupoIniParametros);
		parametro.setId_empresa(Constantes.parametroIdEmpresaGlobal);
		lsPar = parametrosService.retornaObjParametrosPorGrupoe(parametro);
				for (int i = 0; i < lsPar.size(); i++) {
					if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
						rutamatriz=lsPar.get(i).getValor();
						break;
						}
				 }
		try {
			Serie s = new Serie();
			s.setIdlocal(documento.getIdlocal());
			s.setIdsutipodocumento(documento.getIdsutipodocumento());
			
			tipo_Operacion_Sunat = tipoOperacionSunatService
					.retornaObjTipoOperacionSunatPorCodigo(tipo_Operacion_Sunat);
			
			documento.setEstado(Integer.parseInt(Constantes.estadoSinRespuesta));
			actualiza_documento = documentoService.actualizarDocumentoEstadoRechazado(documento);
			
			if(documento.getBflageditarnum()!=null&&!documento.getBflageditarnum()) {
				//No autoimcrementa la serie para los documentos no enviados (ya se autoimcremento cuando se registro)
				//No autoincrementara serie para documentos borrador
				s = serieService.retornaSeriexTipoDocyLocal(s);
				s.setSnumero(String.format("%8s",String.valueOf(Integer.valueOf(s.getSnumero())+1)).replace(' ','0'));
				serieService.actualizarSerie(s);							
			}
			
			if (documento != null) {

				switch (documento.getCod_tipo_documento()) {
				case "01":
				case "03":
				case "07":
				case "08":
					
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
					if (rpta_sunat[0].equals("1")) {
						String[] frase_parts = rpta_sunat[2].split(",");
						String frase = frase_parts[0];
						int posicionUltimoEspacio = 0;
						posicionUltimoEspacio = frase.lastIndexOf(" ");
						String comprobante = frase.substring(posicionUltimoEspacio + 1, frase.length());
						String[] comprobante_parts = comprobante.split("-");
						String nro_comprobante = comprobante_parts[1];
						documento.setNro_comprobante(nro_comprobante);
						System.out.print("Exito " + rpta_sunat[2]);

						String separar = "|";
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

						compa = company.getNro_documento_empresa();
						tipodocu = documento.getCod_tipo_documento();
						seriedocu = documento.getSerie_comprobante();
						nro_compro = documento.getNro_comprobante();
						igv = documento.getTotal_igv();
						importe_total = documento.getTotal();

						fechatimestamp = documento.getFecha_documento();
						Date fecha = new Date(fechatimestamp.getTime());
						DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
						fechas = formatoFecha.format(fecha);

						tipodoccli = cliente.getTipo_doc();
						num_doc = documento.getNro_documento_cliente();
						hash = rpta_sunat[3];

						Codigo_qr codigo = new Codigo_qr();

						datos = compa + separar + tipodocu + separar + seriedocu + separar + nro_compro + separar + igv
								+ separar + importe_total + separar + fechas + separar + tipodoccli + separar + num_doc
								+ separar + hash;

						if (documento.getId_documento() != null) {
							codigo.setId_documento(documento.getId_documento());
							codigo.setValor(datos);
							codigoService.insertarCodigoqr(codigo);
						}

						documento.setEstado(Integer.parseInt(Constantes.estadoDeclaradoAceptado));
						actualiza_documento = documentoService.actualizarDocumentoEstadoRechazado(documento);

						
					} else {
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
				case "09": // break;
				case "31":
					if(documento.getBflagactualizaserie() && !documento.getBflageditarnum()) {
						s = serieService.retornaSeriexTipoDocyLocal(s);
						s.setSnumero(String.format("%8s",String.valueOf(Integer.valueOf(s.getSnumero())+1)).replace(' ','0'));
						serieService.actualizarSerie(s);							
					}
//					documento.setDirDocumentoEmpresaEmisora(rutamatriz + separador + documento.getNro_documento_empresa() + separador + "GUIA REMISION");
					rpta_sunat = cpeResource.registroGuia(documento);

					if (rpta_sunat[0].equals("1")) {
						System.out.print("Exito " + rpta_sunat[2]);
						documento.setEstado(Integer.parseInt(Constantes.estadoDeclaradoAceptado));
						actualiza_documento = documentoService.actualizarDocumentoEstadoRechazado(documento);
					} else {
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
			} else {
				retencion = null;
				retencion = cpeResource.generaDocumentoRTPC(retencionDoc);
				if (retencion.getRpta_sunat()[0].equals("1")) {
					System.out.print("Exito " + rpta_sunat[2]);
					retencionDoc.setEstado(Integer.parseInt(Constantes.estadoDeclaradoAceptado));
					actualiza_documento = retencionService.actualizarRetencionEstadoRechazado(retencionDoc);
				} else {
					System.out.print("Error " + rpta_sunat[2]);
					
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
			// INSERTA AUDITORIA

			if (retencionDoc == null) {


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

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public _DocumentoCpe getDocumento() {
		return documento;
	}

	public void setDocumento(_DocumentoCpe documento) {
		this.documento = documento;
	}

	public _Company getCompany() {
		return company;
	}

	public void setCompany(_Company company) {
		this.company = company;
	}

	public _Clientes getCliente() {
		return cliente;
	}

	public void setCliente(_Clientes cliente) {
		this.cliente = cliente;
	}

	public _Retencion getRetencionDoc() {
		return retencionDoc;
	}

	public void setRetencionDoc(_Retencion retencionDoc) {
		this.retencionDoc = retencionDoc;
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

	public Tipo_Operacion_Sunat getTipo_Operacion_Sunat() {
		return tipo_Operacion_Sunat;
	}

	public void setTipo_Operacion_Sunat(Tipo_Operacion_Sunat tipo_Operacion_Sunat) {
		this.tipo_Operacion_Sunat = tipo_Operacion_Sunat;
	}

}
