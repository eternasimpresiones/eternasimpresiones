package org.gteperu.erp.everest.controller.gateway;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

//import org.gteperu.erp.everest.controller.Funciones; 
import org.gteperu.erp.everest.domain.Reporte;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain.Sutipodocumento;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service.CaptchaService;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service.SutipodocumentoService;
import org.gteperu.erp.everest.service._DocumentoService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
@RestController
@RequestMapping(value = "/public/documentocliente")
public class DocumentoClienteRestController {
 
	
	 @Resource(name = "sutipodocumentoService")
	 SutipodocumentoService sutipodocumentoService;
 
	 
	 @Resource(name = "documentoService")
		_DocumentoService documentoService;
	 
		@Resource(name = "dataSource")
		private DataSource dataSource;
		
		@Resource(name = "parametrosService")
		ParametrosService parametrosService;
		
		@Resource(name = "captchaService")
		CaptchaService captchaService;
	 
	/*
	  * Lista tipos de documento registrados en la empresa segun su estado
	  */
	 @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	 @RequestMapping(value = "/listarTipoDocumentosPublicos", method = RequestMethod.POST)
	 public ResponseWrapper listarDocumentosPublicos(@RequestBody @Validated Sutipodocumento sutipodocumento, HttpServletRequest request) throws Exception {
	     
		 HttpSession session = request.getSession();
		    List objtList = new ArrayList();
		    ResponseWrapper obj = new ResponseWrapper();
		    try {
		                    objtList = sutipodocumentoService.listarDocumentosPublicos(sutipodocumento);
		                    if (objtList != null && objtList.size() > 0) {
		                        obj.setMsg(Constantes.msgTransaccionOk);
		                        obj.setEstado(Constantes.valTransaccionOk);
		                        obj.setAaData(objtList);
		                    } else {
		                        obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
		                        obj.setEstado(Constantes.valTransaccionNoEncontro);
		                        obj.setAaData(objtList);
		                    }
		               
		      } catch (Exception e) {
		    	  throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/listarDocumentosPublicos",
			    			e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
			   				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),sutipodocumento);
		    } 
		    return obj;
		    
	 }

	 
		/*
		 * retorna documento consultado por cliente
		 * 
		 * string cod_tipo_documento
		 * string nro_comprobante
		 * string serie_comprobante
		 * double total
		 * timestamp fecha_documento
		 * string nro_documento_empresa
		 */
		@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
		@RequestMapping(value = "/retornaDocumentoPublico", method = RequestMethod.POST)
		public ResponseWrapper listarDocumentoPublico(@RequestBody @Validated _DocumentoCpe documento, HttpServletRequest request)
				throws Exception {
			List lsDocumento = new ArrayList();
		 ResponseWrapper obj = new ResponseWrapper();
			try {
				if(captchaService.verify(documento.getKeyclient())) {
					lsDocumento = documentoService.listarDocumentoPublico(documento);
					if (lsDocumento != null && lsDocumento.size() > 0) {
						obj.setEstado(Constantes.valTransaccionOk);
						obj.setAaData(lsDocumento);
					} else {
						obj.setMsg(Constantes.msgTransaccionDocumentoNoExiste);
						obj.setEstado(Constantes.valTransaccionNoEncontro);
						obj.setAaData(lsDocumento);
					}				
				}else {
					obj.setMsg(Constantes.msgTransaccionCaptchaError);
					obj.setEstado(Constantes.valTransaccionNoEncontro);
				}
		 } catch (Exception e) {
				System.out.println(this.getClass().getSimpleName() + " listarDocumentoPublico. ERROR : " + e.getMessage());
				throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/listarDocumentoPublico",
		 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),documento);
			}
			return obj;
			
		}
		
		/*
		 *genera un reporte pf que permite visualizar los documetos electronicos descargados
		
		 *Integer iddocumento 
      	  Integer	id_local 
      	  String   cod_tipo_documento
		 *
		 */

	 	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		@RequestMapping(value = "/descargapdfdocumento", method = RequestMethod.POST)
		public @ResponseBody byte[] ReportePDF(@RequestBody Reporte reporte)
				throws JRException, Exception {
			byte[] file = null;
			try {
				_Parametros par = new _Parametros();
				_DocumentoCpe doc2 = new _DocumentoCpe();
				_DocumentoCpe doc = new _DocumentoCpe();

				doc2.setId_documento(reporte.getIddocumento());
				doc = documentoService.retornaDocumentoId(doc2);
	 			String separador=System.getProperty("file.separator");

				List<_Parametros> lsPar = new ArrayList<>();
	 
				par.setGrupo(Constantes.codigoGrupoIniParametros);
				par.setId_empresa(Constantes.parametroIdEmpresaGlobal);
				lsPar = parametrosService.retornaObjParametrosPorGrupoe(par);

				String rutareportes = "";
				String rutamatriz = "";
				for (int i = 0; i < lsPar.size(); i++) {
					if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
						rutamatriz=lsPar.get(i).getValor();
	 				}
					if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaJasper)) {
						rutareportes=lsPar.get(i).getValor();
	 				}
				}
				String dir_base = rutamatriz  + separador +  rutareportes;
				System.out.println(dir_base + " BASE REPORTES");
				System.out.println("SUBREPORT_DIR " + dir_base +separador+ Constantes.rutaSubDetalle );
				System.out.println("SUBREPORTTOTALBANCO_DIR " + dir_base +separador+ Constantes.rutaSubTotalBancos );
				System.out.println("SUBREPORTCUENTABANCO_DIR " + dir_base +separador+ Constantes.rutaSubCuentasBancos );
				System.out.println("SUBREPORTBANCOFACTURA_DIR " + dir_base +separador+ Constantes.rutaSubBancoFactura );
				
				try {

					JasperPrint print = null;
	 				Map<String, Object> parametor = new HashMap<String, Object>();
					parametor.put("iddocumentos", reporte.getIddocumento());
				 	parametor.put("SUBREPORT_DIR", dir_base +separador+ Constantes.rutaSubDetalle);
				 	parametor.put("SUBREPORTTOTALBANCO_DIR", dir_base +separador+ Constantes.rutaSubTotalBancos);
				 	parametor.put("SUBREPORTCUENTABANCO_DIR", dir_base +separador+ Constantes.rutaSubCuentasBancos);
				 	parametor.put("SUBREPORTBANCOFACTURA_DIR", dir_base +separador+ Constantes.rutaSubBancoFactura);
					parametor.put("idlocal",reporte.getId_local());

				 	Connection cn = dataSource.getConnection();
	 		 
				 
					if (!reporte.getCod_tipo_documento().equals("09")) {
						if (doc.getEstado().equals(1)) {
							switch(reporte.getCod_tipo_documento()) {
							case "01":
							case "03":
								print = JasperFillManager.fillReport(
										new FileInputStream(new File(dir_base +separador+ Constantes.rutaFactura)), parametor, cn);
								break;
							case "07":
							case "08":
								print = JasperFillManager.fillReport(
										new FileInputStream(new File(dir_base +separador+ Constantes.rutaNCND)), parametor, cn);
								break;
							}

						} else {
							print = JasperFillManager.fillReport(
									new FileInputStream(new File(dir_base +separador+ Constantes.rutaAnulada)), parametor, cn);
						}
					} else {
						print = JasperFillManager.fillReport(
								new FileInputStream(new File(dir_base +separador+ Constantes.rutaRemision)), parametor, cn);
					}
					file = JasperExportManager.exportReportToPdf(print);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			} catch (SQLException e) {
				System.out.println("" + e.getMessage());
				throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/pdf",
						e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
								+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
								+ e.getStackTrace()[0].getLineNumber(),
						reporte);
			}
			return file;

	 	}
	 	
	 	/*
		 * descarga el pdf del documento (factura, boleta , nc ,nd)
		 * 
		 * 
		 *	Integer  iddocumento 
         *	String	tipo_comprobante
		 */
		@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		@RequestMapping(value = "/descargarDocumentoCPE", method = RequestMethod.POST)
		public @ResponseBody ResponseWrapper descargarDocumentoCPE(@RequestBody Reporte reporte,
				HttpServletResponse response) throws Exception {
	 		byte[] file = null;
			_Parametros parametro = new _Parametros();
			List<_Parametros> lsPar = new ArrayList<>();
			String rutamatriz = "";
			String file_name = "";
			String separador=System.getProperty("file.separator");

			ResponseWrapper obj = new ResponseWrapper();
			try {
				parametro.setGrupo(Constantes.codigoGrupoIniParametros);
				parametro.setId_empresa(0);
				lsPar = parametrosService.retornaObjParametrosPorGrupoe(parametro);

				for (int i = 0; i < lsPar.size(); i++) {
					if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
						rutamatriz=lsPar.get(i).getValor();
	 				}
				}
				_DocumentoCpe documento = new _DocumentoCpe();
				_DocumentoCpe doc = new _DocumentoCpe();
				documento.setId_documento(reporte.getIddocumento());
				documento = documentoService.retornaDocumentoCPEXML(documento);
				doc = documentoService.retornaDocumentoId(documento);

				String tipo_doc = "";
				if (documento.getCod_tipo_documento().equals(Constantes.tipoDocFactura)) {
					tipo_doc = "FACTURA";
				}
				if (documento.getCod_tipo_documento().equals(Constantes.tipoDocBoleta)) {
					tipo_doc = "BOLETA";
				}
				if (documento.getCod_tipo_documento().equals(Constantes.tipoDocNotaCredito)) {
					tipo_doc = "NC";
				}
				if (documento.getCod_tipo_documento().equals(Constantes.tipoDocNotaDebito)) {
					tipo_doc = "ND";
				}
				if(documento.getCod_tipo_documento().equals(Constantes.tipoDocGuiaRemisionRemitente)||documento.getCod_tipo_documento().equals(Constantes.tipoDocGuiaRemisionTransportista)) {
					tipo_doc="GUIA";

				}
				if (documento.getCod_tipo_documento().equals(Constantes.tipoDocPercepcion)) {
					tipo_doc = "RTC";
				}
				if (documento.getCod_tipo_documento().equals(Constantes.tipoDocRetencion)) {
					tipo_doc = "PCP";
				}

				String extension = ".XML";
				String NombreCPE = "";
				String NombreCDR = "";
				String dirDocumentoEmpresa = "";
				if (reporte.getTipo_comprobante().equals(Constantes.codigoComprobantePagoElectronico)) {
					if (doc.getEstado() == Constantes.estaoDocumentoDeBaja) {
						String[] parts = doc.getFecha_baja().split("-");
						switch (documento.getCod_tipo_documento()) {
						case "03":
							NombreCPE = documento.getCompany().getNro_documento_empresa() + "-"
									+ Constantes.resumenDiarioSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
									+ doc.getSecuencia() + extension; // "20100066603-03-BB11-750";//"20100066603-03-BB11-750.ZIP";
							file_name = documento.getCompany().getNro_documento_empresa() + "-"
									+ Constantes.resumenDiarioSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
									+ doc.getSecuencia() + extension;
							break;
						case "01":
							NombreCPE = documento.getCompany().getNro_documento_empresa() + "-"
									+ Constantes.comunicaciónBajaSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
									+ doc.getSecuencia() + extension;

							file_name = documento.getCompany().getNro_documento_empresa() + "-"
									+ Constantes.comunicaciónBajaSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
									+ doc.getSecuencia() + extension;
							break;
						case "07":
							if (doc.getTipo_comprobante_modifica().equals("03")) {
								NombreCPE = documento.getCompany().getNro_documento_empresa() + "-"
										+ Constantes.resumenDiarioSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
										+ doc.getSecuencia() + extension;

								file_name = "R-" + documento.getCompany().getNro_documento_empresa() + "-"
										+ Constantes.resumenDiarioSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
										+ doc.getSecuencia() + extension;
							} else {
								NombreCPE = documento.getCompany().getNro_documento_empresa() + "-"
										+ Constantes.comunicaciónBajaSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
										+ doc.getSecuencia() + extension;

								file_name = documento.getCompany().getNro_documento_empresa() + "-"
										+ Constantes.comunicaciónBajaSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
										+ doc.getSecuencia() + extension;
							}
							break;
						case "08":
							if (doc.getTipo_comprobante_modifica().equals("03")) {
								NombreCPE = documento.getCompany().getNro_documento_empresa() + "-"
										+ Constantes.resumenDiarioSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
										+ doc.getSecuencia() + extension;

								file_name = documento.getCompany().getNro_documento_empresa() + "-"
										+ Constantes.resumenDiarioSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
										+ doc.getSecuencia() + extension;
							} else {
								NombreCPE = documento.getCompany().getNro_documento_empresa() + "-"
										+ Constantes.comunicaciónBajaSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
										+ doc.getSecuencia() + extension;

								file_name = documento.getCompany().getNro_documento_empresa() + "-"
										+ Constantes.comunicaciónBajaSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
										+ doc.getSecuencia() + extension;
							}
							break;
						}

					} else {
						NombreCPE = documento.getCompany().getNro_documento_empresa() + "-"
								+ documento.getCod_tipo_documento() + "-" + documento.getSerie_comprobante() + "-"
								+ documento.getNro_comprobante() + extension; // "20100066603-03-BB11-750";//"20100066603-03-BB11-750.ZIP";
						file_name = documento.getCompany().getNro_documento_empresa() + "-"
								+ documento.getCod_tipo_documento() + "-" + documento.getSerie_comprobante() + "-"
								+ documento.getNro_comprobante() + extension;
					}
					 
					dirDocumentoEmpresa = rutamatriz +separador + documento.getCompany().getNro_documento_empresa()
							+ separador + tipo_doc + separador + NombreCPE;
					dirDocumentoEmpresa = dirDocumentoEmpresa.replace("|", separador);
				} else {

					if (doc.getEstado() == Constantes.estaoDocumentoDeBaja) {
						String[] parts = doc.getFecha_baja().split("-");
						switch (documento.getCod_tipo_documento()) {
						case "03":
							NombreCDR = "R-" + documento.getCompany().getNro_documento_empresa() + "-"
									+ Constantes.resumenDiarioSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
									+ doc.getSecuencia() + extension; // "20100066603-03-BB11-750";//"20100066603-03-BB11-750.ZIP";
							file_name = "R-" + documento.getCompany().getNro_documento_empresa() + "-"
									+ Constantes.resumenDiarioSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
									+ doc.getSecuencia() + extension;
							break;
						case "01":
							NombreCDR = "R-" + documento.getCompany().getNro_documento_empresa() + "-"
									+ Constantes.comunicaciónBajaSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
									+ doc.getSecuencia() + extension;

							file_name = "R-" + documento.getCompany().getNro_documento_empresa() + "-"
									+ Constantes.comunicaciónBajaSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
									+ doc.getSecuencia() + extension;
							break;
						case "07":
							if (doc.getTipo_comprobante_modifica().equals("03")) {
								NombreCDR = "R-" + documento.getCompany().getNro_documento_empresa() + "-"
										+ Constantes.resumenDiarioSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
										+ doc.getSecuencia() + extension;

								file_name = "R-" + documento.getCompany().getNro_documento_empresa() + "-"
										+ Constantes.resumenDiarioSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
										+ doc.getSecuencia() + extension;
							} else {
								NombreCDR = "R-" + documento.getCompany().getNro_documento_empresa() + "-"
										+ Constantes.comunicaciónBajaSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
										+ doc.getSecuencia() + extension;

								file_name = "R-" + documento.getCompany().getNro_documento_empresa() + "-"
										+ Constantes.comunicaciónBajaSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
										+ doc.getSecuencia() + extension;
							}
							break;
						case "08":
							if (doc.getTipo_comprobante_modifica().equals("03")) {
								NombreCDR = "R-" + documento.getCompany().getNro_documento_empresa() + "-"
										+ Constantes.resumenDiarioSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
										+ doc.getSecuencia() + extension;

								file_name = "R-" + documento.getCompany().getNro_documento_empresa() + "-"
										+ Constantes.resumenDiarioSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
										+ doc.getSecuencia() + extension;
							} else {
								NombreCDR = "R-" + documento.getCompany().getNro_documento_empresa() + "-"
										+ Constantes.comunicaciónBajaSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
										+ doc.getSecuencia() + extension;

								file_name = "R-" + documento.getCompany().getNro_documento_empresa() + "-"
										+ Constantes.comunicaciónBajaSunat + "-" + parts[0] + parts[1] + parts[2] + "-"
										+ doc.getSecuencia() + extension;
							}
							break;
						}

					} else {
						NombreCDR = "R-" + documento.getCompany().getNro_documento_empresa() + "-"
								+ documento.getCod_tipo_documento() + "-" + documento.getSerie_comprobante() + "-"
								+ documento.getNro_comprobante() + extension; // "20100066603-03-BB11-750";//"20100066603-03-BB11-750.ZIP";
						file_name = "R-" + documento.getCompany().getNro_documento_empresa() + "-"
								+ documento.getCod_tipo_documento() + "-" + documento.getSerie_comprobante() + "-"
								+ documento.getNro_comprobante() + extension;
					}

					dirDocumentoEmpresa = rutamatriz + separador + documento.getCompany().getNro_documento_empresa()
							+ separador + tipo_doc + separador + NombreCDR;
					dirDocumentoEmpresa = dirDocumentoEmpresa.replace("|", separador);

				}

				file = Files.readAllBytes(new File(dirDocumentoEmpresa).toPath());
				if (file != null && file.length > 0) {
					obj.setEstado(Constantes.valTransaccionOk);
					obj.setMsg(Constantes.msgTransaccionOk);
					obj.setFile(file);
					obj.setFile_nombre(file_name);
				} else {
					obj.setMsg(Constantes.msgTransaccionDescargaError);
					obj.setEstado(Constantes.valTransaccionErrornull);
				}

			} catch (Exception e) {
				System.out.println("Error descargarDocumentoCPE => " + e.getMessage());
				throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/descargarDocumentoCPE",
						e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
								+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
								+ e.getStackTrace()[0].getLineNumber(),
						reporte);
			}
			return obj;
		}

	
		
		  
	        
	        
	        
			@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
			@RequestMapping(value = "/validaCapcha", method = RequestMethod.POST)
			public ResponseWrapper validaCapcha(@RequestBody @Validated String recaptchaCliente  , HttpServletRequest request)
					throws Exception {
 			 ResponseWrapper obj = new ResponseWrapper();
				try {
					boolean captchaVerified = captchaService.verify(recaptchaCliente);
				        if(!captchaVerified) {
				        	obj.setMsg("Capcha incorrecto");
				        	obj.setEstado(Constantes.valTransaccionNoEncontro);
 				        }else {
							obj.setEstado(Constantes.valTransaccionOk);
				        	obj.setMsg("Capcha correcto");
 				        }
			  } catch (Exception e) {
					System.out.println(this.getClass().getSimpleName() + " validaCapcha. ERROR : " + e.getMessage());
					throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/validaCapcha",
			 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
			 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),recaptchaCliente);
				}
				return obj;
			 }
			
			
			
}