package org.gteperu.erp.everest.service.impl;

import java.util.Date;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._Detalle_Documento_TMP;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._Documento_TMP;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.domain._ValidacionTMP;
import org.gteperu.erp.everest.mappers.DocumentoTMPMapper;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service._CompanyService;
import org.gteperu.erp.everest.service._Documento_TMPService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("documento_TMPService")
public class _Documento_TMPServiceImpl implements _Documento_TMPService {
 	@Autowired
 	ParametrosService parametrosService;
 	@Autowired
	_CompanyService companyService;
 	
    @Resource(name = "documentoTMPMapper")
    DocumentoTMPMapper documentoTMPMapper;
    
    @Override
    public List<_Documento_TMP> armaListaDocumentosconExcel(XSSFWorkbook wb,Integer id) {
    	List<_Documento_TMP> lsdocumentoCpe = new ArrayList<_Documento_TMP>();
    	_Documento_TMP doc = new _Documento_TMP();
		
		try{
			XSSFSheet worksheet = wb.getSheetAt(0);
			XSSFSheet wsdetalle = wb.getSheetAt(1);

			for (int i = 2; i <= worksheet.getLastRowNum(); i++) {
		    	List<_Detalle_Documento_TMP> lsdetalles = new ArrayList<_Detalle_Documento_TMP>();

				XSSFRow fil1 = worksheet.getRow(i);

				if (fil1 != null) {

					XSSFCell A1 = fil1.getCell(0);
					XSSFCell B1 = fil1.getCell(1);
					XSSFCell C1 = fil1.getCell(2);
					XSSFCell D1 = fil1.getCell(3);
					XSSFCell E1 = fil1.getCell(4);
					XSSFCell F1 = fil1.getCell(5);
					XSSFCell G1 = fil1.getCell(6);
					XSSFCell H1 = fil1.getCell(7);
					XSSFCell I1 = fil1.getCell(8);
					XSSFCell J1 = fil1.getCell(9);
					XSSFCell K1 = fil1.getCell(10);
					XSSFCell L1 = fil1.getCell(11);
					XSSFCell M1 = fil1.getCell(12);
					XSSFCell N1 = fil1.getCell(13);
					XSSFCell O1 = fil1.getCell(14);
					XSSFCell P1 = fil1.getCell(15);
					XSSFCell Q1 = fil1.getCell(16);
					XSSFCell R1 = fil1.getCell(17);
					XSSFCell S1 = fil1.getCell(18);
					XSSFCell T1 = fil1.getCell(19);
					XSSFCell U1 = fil1.getCell(20);
					XSSFCell V1 = fil1.getCell(21);
					XSSFCell W1 = fil1.getCell(22);
					XSSFCell X1 = fil1.getCell(23);
					XSSFCell Y1 = fil1.getCell(24);
					XSSFCell Z1 = fil1.getCell(25);
					XSSFCell AA1 = fil1.getCell(26);
					
					if(A1 != null) {
						doc.setSserie_comprobante(A1.getStringCellValue());						
					}
					if(B1 != null) {
						doc.setSnro_comprobante(B1.getStringCellValue());						
					}
					doc.setIid_empresa(id);
					if(C1 != null) {
						doc.setStipo_documento(C1.getStringCellValue());						
					}
					if(D1 != null) {						
						doc.setStipo_documento_cliente(D1.getStringCellValue());
					}
					if(E1 != null) {
						doc.setSnumero_documento_cliente(E1.getStringCellValue());						
					}
					if(F1 != null) {
						doc.setSrazon_social_cliente(F1.getStringCellValue());						
					}
					if(G1 != null) {
						doc.setSdireccion_cliente(G1.getStringCellValue());						
					}
					if(H1 != null) {
						doc.setStipo_nota_debito(H1.getStringCellValue());						
					}
					if(I1 != null) {
						doc.setStipo_nota_credito(I1.getStringCellValue());						
					}
					if(J1 != null) {
						doc.setSdescripcion_motivo(J1.getStringCellValue());						
					}
					if(K1 != null) {
						doc.setScod_moneda(K1.getStringCellValue().toUpperCase());						
					}
					if(L1 != null) {
						doc.setDsub_total(L1.getNumericCellValue());						
					}
					if(M1 != null) {
						doc.setDtotal_inafecta(M1.getNumericCellValue());						
					}
					if(N1 != null) {
						doc.setDtotal_exoneradas(N1.getNumericCellValue());						
					}
					if(O1 != null) {
						doc.setDtotal_gratuitas(O1.getNumericCellValue());						
					}
					if(P1 != null) {
						doc.setDtotal_igv(P1.getNumericCellValue());						
					}
					if(Q1 != null) {
						doc.setDtotal_percepciones(Q1.getNumericCellValue());						
					}
					if(R1 != null) {
						doc.setDtotal(R1.getNumericCellValue());						
					}
					DateFormat formatter;
					formatter = new SimpleDateFormat("dd-MM-yyyy");
					if(S1 != null) {
						Date date = (Date) formatter.parse(S1.getStringCellValue());
						java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
						doc.setTfecha_documento(timeStampDate);						
					}
					if(T1 != null) {
						doc.setSplaca_vehiculo(T1.getStringCellValue());						
					}else {
						doc.setSplaca_vehiculo("");
					}
					if(U1 != null) {
						doc.setSemail_cliente(U1.getStringCellValue());						
					}
					if(V1 != null) {
						doc.setStipo_operacion(V1.getStringCellValue());						
					}
					if(W1 != null) {
						doc.setDtotal_detracciones(W1.getNumericCellValue());						
					}
					if(X1 != null) {
						doc.setDporcentaje_detraccion(X1.getNumericCellValue());						
					}
					if(Y1 != null) {
						doc.setStipo_comprobante_modifica(Y1.getStringCellValue());						
					}
					if(Z1 != null) {
						doc.setSnro_documento_modifica(Z1.getStringCellValue());						
					}
					if(AA1 != null) {
						Date docref = (Date) formatter.parse(AA1.getStringCellValue());
						java.sql.Timestamp timedocref = new Timestamp(docref.getTime());
						doc.setTfecha_documento_referencia(timedocref);						
					}
					for(int j = 2; j <= wsdetalle.getLastRowNum(); j++) {
						_Detalle_Documento_TMP det = new _Detalle_Documento_TMP();
						XSSFRow det1 = wsdetalle.getRow(j);
						if(det1!=null) {

						String identificador = doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante();
						XSSFCell A2 = det1.getCell(0);
						if(identificador.equals(A2.getStringCellValue())) {
							XSSFCell B2 = det1.getCell(1);
							XSSFCell C2 = det1.getCell(2);
							XSSFCell D2 = det1.getCell(3);
							XSSFCell E2 = det1.getCell(4);
							XSSFCell F2 = det1.getCell(5);
							XSSFCell G2 = det1.getCell(6);
							XSSFCell H2 = det1.getCell(7);
							XSSFCell I2 = det1.getCell(8);
							XSSFCell J2 = det1.getCell(9);
							XSSFCell K2 = det1.getCell(10);
							XSSFCell L2 = det1.getCell(11);
							if(B2 != null) {
								det.setIitem((int)B2.getNumericCellValue());															
							}
							if(C2 != null) {								
								det.setDcantidad(C2.getNumericCellValue());
							}
							if(D2 != null) {
								det.setSunidad_medida(D2.getStringCellValue());								
							}
							if(E2 != null) {
								det.setScodigo(E2.getStringCellValue());								
							}else {
								det.setScodigo("");								
							}
							if(F2 != null) {
								det.setSdescripcion(F2.getStringCellValue());								
							}else {
								det.setSdescripcion("");								
							}
							if(G2 != null) {
								det.setDprecio(G2.getNumericCellValue());								
							}
							if(H2 != null) {
								det.setDimporte(H2.getNumericCellValue());								
							}
							if(I2 != null) {
								det.setScod_tipo_operacion(I2.getStringCellValue());								
							}
							if(J2 != null) {
								det.setScod_tipo_operacion(J2.getStringCellValue());								
							}
							if(K2 != null) {
								det.setDigv(K2.getNumericCellValue());								
							}
							if(L2 != null) {
								det.setScod_sunat(L2.getStringCellValue());								
							}else {
								det.setScod_sunat("");								
							}
							
							lsdetalles.add(det);
						}
						
					}
					}
					doc.setLsdetalle(lsdetalles);
				}
				lsdocumentoCpe.add(doc);
			}

		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " armaListaDocumentosconExcel. ERROR : " + e.getMessage());
			throw e;
		}finally{
			return lsdocumentoCpe;	
		}
	}
    
    @Override
    public XSSFWorkbook armaWorkBook(MultipartFile[] files,Integer id) {
    	XSSFWorkbook wb = new XSSFWorkbook();
		_Parametros parametro = new _Parametros();
		List<_Parametros> lsPar = new ArrayList<>();
		MultipartFile file = null;
		Boolean bol = false;
		String rutamatriz = "",rutaexcel="";

		try{
			file = files[0];
			file.getSize();
			byte[] bytes = file.getBytes();
 			String separador = System.getProperty("file.separator");

			parametro.setGrupo(Constantes.codigoGrupoIniParametros);
			parametro.setId_empresa(Constantes.parametroIdEmpresaGlobal);
			lsPar = parametrosService.retornaObjParametrosPorGrupoe(parametro);

			for (int i = 0; i < lsPar.size(); i++) {
				if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
					rutamatriz=lsPar.get(i).getValor();
 				}
				if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaExcel)) {
					rutaexcel=lsPar.get(i).getValor();
 				}
			}
			
			_Company emp = new _Company();
			emp.setId_empresa(id);
			emp = companyService.retornaEmpresaPorIdempresa(emp);
			
			String ruta =rutamatriz + separador + rutaexcel+ separador+ emp.getNro_documento_empresa() + ".xlsx";
			
			bol = Files.deleteIfExists(Paths.get(ruta));
			bol = false;
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(ruta)));
			stream.write(bytes);
			stream.close();

			FileInputStream fsIP = new FileInputStream(ruta);

			wb = new XSSFWorkbook(fsIP);
			bol = Files.deleteIfExists(Paths.get(ruta));

		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " armaWorkBook. ERROR : " + e.getMessage());
			throw e;

		}finally{
			return wb;	
		}
	}
		
    
    @Override
    public _ValidacionTMP validarFacturaBoletaTMP(_Documento_TMP doc) {
    	_ValidacionTMP response = new _ValidacionTMP();
		try{
			if(doc.getSserie_comprobante()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo serie no debe estar vacio en el documento con correlativo: "+doc.getSnro_comprobante());
				return response;
			}
			if(doc.getSnro_comprobante()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo correlativo no debe estar vacio en el documento con serie: "+doc.getSserie_comprobante());
				return response;
			}
			if(doc.getStipo_documento()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo Tipo documento no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;
			}
			if(doc.getStipo_documento_cliente()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo tipo documento identidad receptor no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;
			}
			if(doc.getSnumero_documento_cliente()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo ruc receptor no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;
			}
			if(doc.getSrazon_social_cliente()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo Raz. social receptor no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;
			}
			if(doc.getSdireccion_cliente()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo dirección receptor no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;
			}
			if(doc.getScod_moneda()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo tipo de moneda no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getDsub_total()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo monto neto sin igv no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getDtotal_inafecta()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo monto total operacion inafecto no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getDtotal_exoneradas()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo monto total operacion exonerado no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getDtotal_gratuitas()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo monto total operacion gratuita no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getDtotal_igv()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo total igv no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getDtotal_percepciones()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo monto percepcion no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getDtotal()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo monto total no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getTfecha_documento()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo fecha emisión no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getSplaca_vehiculo()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo número de placa no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getSemail_cliente()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo email cliente no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getStipo_operacion()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo tipo operación no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getDtotal_detracciones()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo monto detracción no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getDporcentaje_detraccion()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo porcentaje detracción no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			for(_Detalle_Documento_TMP det:doc.getLsdetalle()) {
				if(det.getIitem()==null) {
					response.setIcodrespuesta(Constantes.valTransaccionError);
					response.setSmensaje("El campo item no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
					return response;					
				}
				if(det.getDcantidad()==null) {
					response.setIcodrespuesta(Constantes.valTransaccionError);
					response.setSmensaje("El campo unidad de medida no debe estar vacio en el item"+det.getIitem()+" del documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
					return response;				
				}
				if(det.getSunidad_medida()==null) {
					response.setIcodrespuesta(Constantes.valTransaccionError);
					response.setSmensaje("El campo unidad de medida no debe estar vacio en el item"+det.getIitem()+" del documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
					return response;					
				}
				if(det.getScodigo()==null) {
					response.setIcodrespuesta(Constantes.valTransaccionError);
					response.setSmensaje("El campo código del producto no debe estar vacio en el item"+det.getIitem()+" del documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
					return response;				
				}
				if(det.getSdescripcion()==null) {
					response.setIcodrespuesta(Constantes.valTransaccionError);
					response.setSmensaje("El campo descripción del producto no debe estar vacio en el item"+det.getIitem()+" del documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
					return response;				
				}
				if(det.getDprecio()==null) {
					response.setIcodrespuesta(Constantes.valTransaccionError);
					response.setSmensaje("El campo precio no debe estar vacio en el item"+det.getIitem()+" del documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
					return response;				
				}
				if(det.getDimporte()==null) {
					response.setIcodrespuesta(Constantes.valTransaccionError);
					response.setSmensaje("El campo subtotal no debe estar vacio en el item"+det.getIitem()+" del documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
					return response;				
				}
				if(det.getScod_tipo_operacion()==null) {
					response.setIcodrespuesta(Constantes.valTransaccionError);
					response.setSmensaje("El campo unidad de medida no debe estar vacio en el item"+det.getIitem()+" del documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
					return response;				
				}
				if(det.getDigv()==null) {
					response.setIcodrespuesta(Constantes.valTransaccionError);
					response.setSmensaje("El campo IGV no debe estar vacio en el item"+det.getIitem()+" del documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
					return response;				
				}
			/*	if(det.getScod_sunat()==null) {
					response.setIcodrespuesta(Constantes.valTransaccionError);
					response.setSmensaje("El campo código sunat no debe estar vacio en el item"+det.getIitem()+" del documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
					return response;				
				}*/
			}
			
			response.setIcodrespuesta(Constantes.valTransaccionOk);
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " validarFacturaBoletaTMP. ERROR : " + e.getMessage());
			throw e;

		}finally{
			return response;	
		}
	}
    @Override
    public _ValidacionTMP validarNotaCreditoTMP(_Documento_TMP doc) {
    	_ValidacionTMP response = new _ValidacionTMP();
		try{
			if(doc.getSserie_comprobante()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo serie no debe estar vacio");
				return response;
			}
			if(doc.getSnro_comprobante()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo serie no debe estar vacio en el documento con serie: "+doc.getSserie_comprobante());
				return response;
			}
			if(doc.getStipo_documento()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo Tipo documento no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;
			}
			if(doc.getStipo_documento_cliente()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo tipo documento identidad receptor no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;
			}
			if(doc.getSnumero_documento_cliente()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo ruc receptor no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;
			}
			if(doc.getSrazon_social_cliente()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo Raz. social receptor no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;
			}
			if(doc.getSdireccion_cliente()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo dirección receptor no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;
			}
//			if(doc.getStipo_documento().equals(Constantes.tipoDocNotaDebito)) {
//				if(doc.getStipo_nota_debito()==null) {
//					response.setIcodrespuesta(Constantes.valTransaccionError);
//					response.setSmensaje("El campo tipo de nota debito no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
//					return response;					
//				}
//			}
			if(doc.getStipo_nota_credito()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo tipo de nota credito no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getSdescripcion_motivo()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo descripcion del motivo de NC o ND no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getScod_moneda()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo tipo de moneda no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getDsub_total()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo monto neto sin igv no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getDtotal_inafecta()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo monto total operacion inafecto no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getDtotal_exoneradas()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo monto total operacion exonerado no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getDtotal_gratuitas()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo monto total operacion gratuita no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getDtotal_igv()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo total igv no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getDtotal_percepciones()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo monto percepcion no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getDtotal()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo monto total no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getTfecha_documento()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo fecha emisión no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getSplaca_vehiculo()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo número de placa no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getSemail_cliente()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo email cliente no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getStipo_operacion()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo tipo operación no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getDtotal_detracciones()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo monto detracción no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getDporcentaje_detraccion()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo porcentaje detracción no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			for(_Detalle_Documento_TMP det:doc.getLsdetalle()) {
				if(det.getIitem()==null) {
					response.setIcodrespuesta(Constantes.valTransaccionError);
					response.setSmensaje("El campo item no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
					return response;					
				}
				if(det.getDcantidad()==null) {
					response.setIcodrespuesta(Constantes.valTransaccionError);
					response.setSmensaje("El campo unidad de medida no debe estar vacio en el item"+det.getIitem()+" del documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
					return response;				
				}
				if(det.getSunidad_medida()==null) {
					response.setIcodrespuesta(Constantes.valTransaccionError);
					response.setSmensaje("El campo unidad de medida no debe estar vacio en el item"+det.getIitem()+" del documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
					return response;					
				}
				if(det.getScodigo()==null) {
					response.setIcodrespuesta(Constantes.valTransaccionError);
					response.setSmensaje("El campo código del producto no debe estar vacio en el item"+det.getIitem()+" del documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
					return response;				
				}
				if(det.getSdescripcion()==null) {
					response.setIcodrespuesta(Constantes.valTransaccionError);
					response.setSmensaje("El campo descripción del producto no debe estar vacio en el item"+det.getIitem()+" del documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
					return response;				
				}
				if(det.getDprecio()==null) {
					response.setIcodrespuesta(Constantes.valTransaccionError);
					response.setSmensaje("El campo precio no debe estar vacio en el item"+det.getIitem()+" del documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
					return response;				
				}
				if(det.getDimporte()==null) {
					response.setIcodrespuesta(Constantes.valTransaccionError);
					response.setSmensaje("El campo subtotal no debe estar vacio en el item"+det.getIitem()+" del documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
					return response;				
				}
				if(det.getScod_tipo_operacion()==null) {
					response.setIcodrespuesta(Constantes.valTransaccionError);
					response.setSmensaje("El campo unidad de medida no debe estar vacio en el item"+det.getIitem()+" del documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
					return response;				
				}
				if(det.getDigv()==null) {
					response.setIcodrespuesta(Constantes.valTransaccionError);
					response.setSmensaje("El campo IGV no debe estar vacio en el item"+det.getIitem()+" del documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
					return response;				
				}
				if(det.getScod_sunat()==null) {
					response.setIcodrespuesta(Constantes.valTransaccionError);
					response.setSmensaje("El campo código sunat no debe estar vacio en el item"+det.getIitem()+" del documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
					return response;				
				}
			}
			if(doc.getStipo_comprobante_modifica()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo Tpo. Doc. Referencia no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getSnro_documento_modifica()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo serie y correlativo referencia no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			if(doc.getTfecha_documento_referencia()==null) {
				response.setIcodrespuesta(Constantes.valTransaccionError);
				response.setSmensaje("El campo fecha referencia no debe estar vacio en el documento: "+doc.getSserie_comprobante()+"-"+doc.getSnro_comprobante());
				return response;					
			}
			
			
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " validarNotaCreditoTMP. ERROR : " + e.getMessage());
			throw e;

		}finally{
			return response;	
		}
	}
    

    
    @Override
    public Integer insertarDocumentoTMP(_Documento_TMP documento) {
    		Integer auxiliar = 0;
		try {
			auxiliar = documentoTMPMapper.insertaDocumentoTMP(documento);
		} catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/insertarDocumentoTMP => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/insertarDocumentoTMP" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return auxiliar;
    }
    @Override
    public List<_Documento_TMP> retornaLsDocumentoTMP() {
    	List<_Documento_TMP> auxiliar = new ArrayList<_Documento_TMP>();
		try {
			auxiliar = documentoTMPMapper.retornaLsDocumentoTMP();
		} catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/retornaLsDocumentoTMP => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/retornaLsDocumentoTMP" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return auxiliar;
    }
    
    @Override
    public Integer eliminaDocumentoTMP(_Documento_TMP doc) {
    	Integer auxiliar = 0;
		try {
			auxiliar = documentoTMPMapper.eliminaDocumentoTMP(doc);
		} catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/eliminaDocumentoTMP => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/eliminaDocumentoTMP" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return auxiliar;
    }
    
    @Override
    public Integer actualizaMsgRegistro(_Documento_TMP doc) {
    	Integer auxiliar = 0;
		try {
			auxiliar = documentoTMPMapper.actualizaMsgRegistro(doc);
		} catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/eliminaDocumentoTMP => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/eliminaDocumentoTMP" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return auxiliar;
    }
}