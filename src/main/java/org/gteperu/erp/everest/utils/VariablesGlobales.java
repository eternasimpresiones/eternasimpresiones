/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gteperu.erp.everest.utils;

//import com.google.zxing.WriterException;
import java.awt.image.BufferedImage;
import javax.mail.internet.MimeUtility;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @web http://jc-mouse.blogspot.com/
 * @author Mouse
 */
public class VariablesGlobales {

	// ===================PRODUCCION===================
	public String rutaXMLCPE_PRO = "";
	public String rutaXMLCDR_PRO = "";
	public String rutaFirma_PRO = "";
	public String rutaPDF_PRO = "";
	// ===================HOMOLOGACION===================
	public String rutaXMLCPE_HO = "";
	public String rutaXMLCDR_HO = "";
	public String rutaFirma_HO = "";
	public String rutaPDF_HO = "";
	// ===================PRUEBA - BETA===================
	public String rutaXMLCPE_BE = "";
	public String rutaXMLCDR_BE = "";
	public String rutaFirma_BE = "";
	public String passFirma_BE = "";
	public String rutaPDF_BE = "";

	// ===================OTRAS RUTAS================
	public String rutaLogo = "";
	public String rutaCodigoBarra = "";
	public String urlCodigoBarra = "";
	public String rutaJasper = "";

	public String usuarioFtp = "";
	public String contraFtp = "";

	// ===================RUTAS CONEXION A WEB-SERVICE SUNAT================
	public String rutaWSCPE_PD = "";
	public String rutaWSCPE_HO = "";
	public String rutaWSCPE_BE = "";
	public String rutaWSGUIA_PD = "";

	// ---------------------------------------------
	public String rutaWSCPE_RTPC_PD = "";
	public String rutaWSCPE_RTPC_BE = "";
	// ---------------------------------------------
	public String rutaWSCPE_CONFAC = "";

	public VariablesGlobales() {
	}

//=====================================CODIFICAR Y DECODIFICAR ARCHIVOS ENVIADOS, RECIBIDOS A SUNAT=========================================
//https://coderanch.com/t/517954/Web-Services/java/access-byte-array-web-method
	public String encode(String RutaCPE) throws Exception {
		FileInputStream fis = new FileInputStream(RutaCPE);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int c = 0;
		while ((c = fis.read()) != -1) {
			baos.write(c);
		}
		fis.close();
		byte[] byteReturn = baos.toByteArray();

		ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
		OutputStream b64os = MimeUtility.encode(baos2, "base64");
		b64os.write(byteReturn);
		b64os.close();
		return baos2.toString();
	}

	public String decode(String Rptarchivo, String nombreArchivo) throws Exception {
		byte[] recibeArchivoBytes = null;
		recibeArchivoBytes = Rptarchivo.getBytes();

		ByteArrayInputStream bais = new ByteArrayInputStream(recibeArchivoBytes);
		InputStream b64is = MimeUtility.decode(bais, "base64");
		byte[] tmp = new byte[recibeArchivoBytes.length];
		int n = b64is.read(tmp);
		byte[] res = new byte[n];
		System.arraycopy(tmp, 0, res, 0, n);

		try {
			OutputStream out = new FileOutputStream(nombreArchivo + ".ZIP");
			out.write(res);
			out.close();
		} catch (Exception ex) {
			// ok = false;
			System.out.println("el  error es " + ex);
		} finally {
		}

		return "ok";
	}

	// POSIBLE SOLUCION
	// http://www.programcreek.com/java-api-examples/index.php?class=javax.xml.soap.SOAPMessage&method=setProperty
	///////////////////////////////////// COMPRIMIR DE DESCOMPRIMIR
	// ZIP///////////////////////////////////
	private static final int BUFFER_SIZE = 1024;

	public void add_unzip(String archivoOriginal, String archivoZip) {
		// https://www.youtube.com/watch?v=niUYQus1c5I
		try {
			ZipFile archivo = new ZipFile(archivoZip);
			ArrayList lista = new ArrayList();
			lista.add(new File(archivoOriginal));
			ZipParameters parametros = new ZipParameters();
			parametros.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parametros.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			archivo.addFiles(lista, parametros);
		} catch (ZipException ex) {
			ex.printStackTrace();
		}
	}

	public void extrac_unzip(String source, String destination) {
		try {
			ZipFile zipFile = new ZipFile(source);
//            if (zipFile.isEncrypted()) {
//                zipFile.setPassword(password);
//            }
			zipFile.extractAll(destination);
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}

	public void Rutas() throws Exception {
		Properties prop = null;
		InputStream entrada = null;
		String ruta_app = System.getProperty("user.dir");
		String separador = System.getProperty("file.separator");
		try {
			// entrada = new FileInputStream("rutas.properties");

			// cargamos el archivo de propiedades
			prop = new Properties();
			// prop.load(entrada);
			prop.load(VariablesGlobales.class.getClassLoader().getResourceAsStream("rutas.properties"));
			rutaXMLCPE_PRO = prop.getProperty("rutaXMLCPE_PRO");
			rutaXMLCDR_PRO = prop.getProperty("rutaXMLCDR_PRO");
			rutaFirma_PRO = prop.getProperty("rutaFirma_PRO");
			rutaPDF_PRO = prop.getProperty("rutaPDF_PRO");

			// rutaXMLCPE_HO = prop.getProperty("rutaXMLCPE_HO");
			// rutaXMLCDR_HO = prop.getProperty("rutaXMLCDR_HO");
			// rutaFirma_HO = prop.getProperty("rutaFirma_HO");
			// rutaPDF_HO = prop.getProperty("rutaPDF_HO");

			// rutaXMLCPE_BE = (ruta_app +
			// prop.getProperty("rutaXMLCPE_BE")).replace("/",separador);
			// rutaXMLCDR_BE = (ruta_app + prop.getProperty("rutaXMLCDR_BE")).replace("/",
			// separador);
			// rutaFirma_BE = (ruta_app + prop.getProperty("rutaFirma_BE")).replace("/",
			// separador);
			// passFirma_BE = prop.getProperty("passFirma_BE");
			// rutaPDF_BE = (ruta_app + prop.getProperty("rutaPDF_BE")).replace("/",
			// separador);

			// =================OTRAS RUTAS===============
			// rutaLogo = prop.getProperty("rutaLogo");
			// rutaCodigoBarra = prop.getProperty("rutaCodigoBarra");
			// urlCodigoBarra = prop.getProperty("urlCodigoBarra");
			// urlCodigoBarra = prop.getProperty("urlCodigoBarra");

			// usuarioFtp = prop.getProperty("usuarioFtp");
			// contraFtp = prop.getProperty("contraFtp");

			// ==========RUTAS CONEXION A WEB-SERVICE SUNAT===========
			rutaWSCPE_PD = prop.getProperty("rutaWSCPE_PD");
			rutaWSCPE_HO = prop.getProperty("rutaWSCPE_HO");
			rutaWSCPE_BE = prop.getProperty("rutaWSCPE_BE");
			rutaWSGUIA_PD = prop.getProperty("rutaWSGUIA_PD");

			rutaJasper = (ruta_app + prop.getProperty("rutaJasper")).replace("/", separador);
			// ---------------------------------------------
			rutaWSCPE_RTPC_PD = prop.getProperty("rutaWSCPE_RTPC_PD");
			rutaWSCPE_RTPC_BE = prop.getProperty("rutaWSCPE_RTPC_BE");
			// ---------------------------------------------
			rutaWSCPE_CONFAC = prop.getProperty("rutaWSCPE_CONFAC");

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " Rutas. ERROR : " + e.getMessage() + " Linea: "
					+ e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();

			throw e;
		}
	}

	public String valorXML(String rutaArchivo, String Nspace, String TagName)
			throws SAXException, IOException, ParserConfigurationException {
		String rta = "";
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			Document doc = dbf.newDocumentBuilder().parse(new FileInputStream(rutaArchivo + ".XML"), "ISO-8859-1");// origen
			doc.getDocumentElement().normalize();
			if (Nspace.length() > 0) {
				rta = doc.getDocumentElement().getElementsByTagNameNS("*", TagName).item(0).getTextContent();// cbc:ResponseCode
			} else {
				rta = doc.getDocumentElement().getElementsByTagName(TagName).item(0).getTextContent();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rta;
	}

	public String FechaActual() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaActual = new Date();
		String fechaConFormato = sdf.format(fechaActual);
		System.out.println(fechaConFormato);
		return fechaConFormato;
	}

	public String ValidarCaracteresInv(String texto) {
		String cadena = texto;
		cadena = cadena.replace("!", "");
		// cadena = cadena.replace("'"""'","");
		cadena = cadena.replace("#", "");
		cadena = cadena.replace("$", "");
		cadena = cadena.replace("%", "");
		cadena = cadena.replace("&", "");
		cadena = cadena.replace("'", "");
		cadena = cadena.replace("(", "");
		cadena = cadena.replace(")", "");
		cadena = cadena.replace("*", "");
		cadena = cadena.replace("+", "");
		// cadena = cadena.replace(",", "");
		cadena = cadena.replace("-", "");
		cadena = cadena.replace(".", "");
		cadena = cadena.replace("/", "");
		cadena = cadena.replace("<", "");
		cadena = cadena.replace("=", "");
		cadena = cadena.replace(">", "");
		cadena = cadena.replace("?", "");
		cadena = cadena.replace("@", "");
		cadena = cadena.replace("[", "");
		cadena = cadena.replace("\\", "");
		cadena = cadena.replace("]", "");
		cadena = cadena.replace("^", "");
		cadena = cadena.replace("_", "");
		cadena = cadena.replace("`", "");
		cadena = cadena.replace("{", "");
		cadena = cadena.replace("|", "");
		cadena = cadena.replace("}", "");
		cadena = cadena.replace("~", "");
		cadena = cadena.replace("Â¡", "");
		cadena = cadena.replace("Â¢", "");
		cadena = cadena.replace("Â£", "");
		cadena = cadena.replace("Â¤", "");
		cadena = cadena.replace("Â¥", "");
		cadena = cadena.replace("Â¦", "");
		cadena = cadena.replace("Â§", "");
		cadena = cadena.replace("Â¨", "");
		cadena = cadena.replace("Â©", "");
		cadena = cadena.replace("Âª", "");
		cadena = cadena.replace("Â«", "");
		cadena = cadena.replace("Â¬", "");
		cadena = cadena.replace("Â®", "");
		cadena = cadena.replace("Â°", "");
		cadena = cadena.replace("Â±", "");
		cadena = cadena.replace("Â²", "");
		cadena = cadena.replace("Â³", "");
		cadena = cadena.replace("Â´", "");
		cadena = cadena.replace("Âµ", "");
		cadena = cadena.replace("Â¶", "");
		cadena = cadena.replace("Â·", "");
		cadena = cadena.replace("Â¸", "");
		cadena = cadena.replace("Â¹", "");
		cadena = cadena.replace("Âº", "");
		cadena = cadena.replace("Â»", "");
		cadena = cadena.replace("Â¼", "");
		cadena = cadena.replace("Â½", "");
		cadena = cadena.replace("Â¾", "");
		cadena = cadena.replace("Â¿", "");
		cadena = cadena.replace("Ã€", "A");
		cadena = cadena.replace("Ã�", "A");
		cadena = cadena.replace("Ã‚", "A");
		cadena = cadena.replace("Ãƒ", "A");
		cadena = cadena.replace("Ã„", "A");
		cadena = cadena.replace("Ã…", "A");
		cadena = cadena.replace("Ã†", "");
		cadena = cadena.replace("Ã‡", "");
		cadena = cadena.replace("Ãˆ", "E");
		cadena = cadena.replace("Ã‰", "E");
		cadena = cadena.replace("ÃŠ", "E");
		cadena = cadena.replace("Ã‹", "E");
		cadena = cadena.replace("ÃŒ", "I");
		cadena = cadena.replace("Ã�", "I");
		cadena = cadena.replace("ÃŽ", "I");
		cadena = cadena.replace("Ã�", "I");
		cadena = cadena.replace("Ã�", "");
		cadena = cadena.replace("Ã‘", "N");
		cadena = cadena.replace("Ã’", "O");
		cadena = cadena.replace("Ã“", "O");
		cadena = cadena.replace("Ã”", "O");
		cadena = cadena.replace("Ã•", "O");
		cadena = cadena.replace("Ã–", "O");
		cadena = cadena.replace("Ã—", "");
		cadena = cadena.replace("Ã˜", "");
		cadena = cadena.replace("Ã™", "U");
		cadena = cadena.replace("Ãš", "U");
		cadena = cadena.replace("Ã›", "U");
		cadena = cadena.replace("Ãœ", "U");
		cadena = cadena.replace("Ã�", "Y");
		cadena = cadena.replace("Ãž", "");
		cadena = cadena.replace("ÃŸ", "");
		cadena = cadena.replace("Ã ", "a");
		cadena = cadena.replace("Ã¡", "a");
		cadena = cadena.replace("Ã¢", "a");
		cadena = cadena.replace("Ã£", "a");
		cadena = cadena.replace("Ã¤", "a");
		cadena = cadena.replace("Ã¥", "a");
		cadena = cadena.replace("Ã¦", "");
		cadena = cadena.replace("Ã§", "");
		cadena = cadena.replace("Ã¨", "e");
		cadena = cadena.replace("Ã©", "e");
		cadena = cadena.replace("Ãª", "e");
		cadena = cadena.replace("Ã«", "e");
		cadena = cadena.replace("Ã¬", "i");
		cadena = cadena.replace("Ã­", "i");
		cadena = cadena.replace("Ã®", "i");
		cadena = cadena.replace("Ã¯", "i");
		cadena = cadena.replace("Ã°", "o");
		cadena = cadena.replace("Ã±", "n");
		cadena = cadena.replace("Ã²", "o");
		cadena = cadena.replace("Ã³", "o");
		cadena = cadena.replace("Ã´", "o");
		cadena = cadena.replace("Ãµ", "o");
		cadena = cadena.replace("Ã¶", "o");
		cadena = cadena.replace("Ã·", "");
		cadena = cadena.replace("Ã¸", "");
		cadena = cadena.replace("Ã¹", "u");
		cadena = cadena.replace("Ãº", "u");
		cadena = cadena.replace("Ã»", "u");
		cadena = cadena.replace("Ã¼", "u");
		cadena = cadena.replace("Ã½", "y");
		cadena = cadena.replace("Ã¾", "");
		cadena = cadena.replace("Ã¿", "");
		cadena = cadena.replace("Å’", "");
		cadena = cadena.replace("Å“", "");
		cadena = cadena.replace("Å ", "");
		cadena = cadena.replace("Å¡", "");
		cadena = cadena.replace("Å¸", "");
		cadena = cadena.replace("Æ’", "");
		cadena = cadena.replace("â€“", "");
		cadena = cadena.replace("â€”", "");
		cadena = cadena.replace("â€˜", "");
		cadena = cadena.replace("â€™", "");
		cadena = cadena.replace("â€š", "");
		cadena = cadena.replace("â€œ", "");
		cadena = cadena.replace("â€�", "");
		cadena = cadena.replace("â€ž", "");
		cadena = cadena.replace("â€ ", "");
		cadena = cadena.replace("â€¡", "");
		cadena = cadena.replace("â€¢", "");
		cadena = cadena.replace("â€¦", "");
		cadena = cadena.replace("â€°", "");
		cadena = cadena.replace("â‚¬", "");
		cadena = cadena.replace("â„¢", "");
		return cadena;
	}

//    public void Reporte(String RUC, String tipoComprobante, String nro_comprobante, int id, int TIPO_PROCESO) {
//        try {
//            Rutas();
//            String reporteIMP = rutaJasper + File.separator + RUC + "_" + tipoComprobante + ".jasper";
//            File reportFile;
//            String filename = "";
//            String logo = rutaLogo + File.separator + RUC + ".png";
//            Conexion cn = new Conexion();
//
//            String NombreCPE = RUC + "-" + tipoComprobante + "-" + nro_comprobante + ".PDF";
//            String RutaPDF = "";
//            if (TIPO_PROCESO == 3) {//EXPORTAR PDF (3=BETA)
//                RutaPDF = rutaPDF_BE + File.separator + NombreCPE;// "\\20100066603-03-BB11-750";
//            }
//            if (TIPO_PROCESO == 2) {//2=HOMOLOGACION
//                RutaPDF = rutaPDF_HO + File.separator + NombreCPE;// "\\20100066603-03-BB11-750";
//            }
//            if (TIPO_PROCESO == 1) {//1=PRODUCCION
//                RutaPDF = rutaPDF_PRO + File.separator + NombreCPE;// "\\20100066603-03-BB11-750";
//            }
//
//            try {
//                //==============================RUTA DEL REPORTE===========================
//                reportFile = new File(reporteIMP);
//                if (!reportFile.exists()) {//verifica si hay reporte personalizado
//                    //si no tengo personalizado agarro el estandar de acuerdo al tipo dee documento
//                    if (tipoComprobante.equals("01")) {
//                        filename = rutaJasper + File.separator + "Factura.jasper";
//                    } else if (tipoComprobante.equals("03")) {
//                        filename = rutaJasper + File.separator + "Boleta.jasper";
//                    } else if (tipoComprobante.equals("07")) {
//                        filename = rutaJasper + File.separator + "NotaCredito.jasper";
//                    } else if (tipoComprobante.equals("08")) {
//                        filename = rutaJasper + File.separator + "NotaDebito.jasper";
//                    }
//                } else {
//                    filename = rutaJasper + File.separator + RUC + "_" + tipoComprobante + ".jasper";
//                }
//
//                //==============================RUTA DEL LOGO===========================
//                File LogoFile = new File(logo);
//                if (!LogoFile.exists()) {//verifica si hay LOGO PERSONALIZADO
//                    logo = rutaLogo + File.separator + "LOGOBLANCO.png";
//                } else {
//                    logo = rutaLogo + File.separator + RUC + ".png";
//                }
//            } catch (Exception e) {
//                //filename = "reportes/" + filename + ".jasper";
//            }
//            reportFile = new File(filename);
//
//            Map jasperParameter = new HashMap();
//            jasperParameter.put("ID", id);
//            jasperParameter.put("rutaLogo", logo);
//            jasperParameter.put("urlCodigoBarra", urlCodigoBarra);
//
//            JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(), jasperParameter, cn.getConexion());
//            JasperExportManager.exportReportToPdfFile(jasperPrint, RutaPDF);
//        } catch (Exception exception) {
//            System.out.println(exception);
//        }
//    }

	public BufferedImage RetornaFoto(String ruta) throws IOException {
		File f = new File(ruta);
		BufferedImage bi = ImageIO.read(f);
		return bi;
	}
}
