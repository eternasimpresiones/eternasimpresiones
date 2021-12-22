/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gteperu.erp.everest.cpe;

import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
/*=============================================*/
import java.io.StringWriter;
/*==================================*/
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import org.gteperu.erp.everest.utils.VariablesGlobales;
import java.io.File;
import org.w3c.dom.NodeList;

public class sunat {

    static VariablesGlobales VarGlo = new VariablesGlobales();

    /**
     * Starting point for the SAAJ - SOAP Client Testing
     */
    //FIRMAR DOCUMENTO XML
    //https://gist.github.com/jsbsantos/4118253
    //http://www.oracle.com/technetwork/articles/javase/dig-signature-api-140772.html
    public static void main(String[] args) {
        try {

            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server                          
            URL url
                    = new URL(new URL("https://e-beta.sunat.gob.pe:443/ol-ti-itcpfegem-beta/billService"),
                            "",
                            new URLStreamHandler() {
                        @Override
                        protected URLConnection openConnection(URL url) throws IOException {
                            URL target = new URL(url.toString());
                            URLConnection connection = target.openConnection();
                            // Connection settings
                            connection.setConnectTimeout(10000); // 10 sec
                            connection.setReadTimeout(60000); // 1 min
                            return (connection);
                        }
                    });
            String ruc = "10447915125";
            String UsuSol = "MODDATOS";
            String PassSol = "moddatos";
            String RutaCPE = "H:\\CPE\\BETA\\10447915125-01-F001-5079";
            String NombreCPE = "10447915125-01-F001-5079";
            String RutaRta = "H:\\CPE\\BETA\\";
            String NombreArchvoRta = "R-10447915125-01-F001-5079";

            //SOAPMessage soapResponse = soapConnection.call(CPEsendBill(ruc, UsuSol, PassSol, NombreCPE, RutaCPE), url);
            SOAPMessage soapResponse = soapConnection.call(CPEsendBill_StatusCDR(ruc, UsuSol, PassSol, NombreCPE), url);

            // Process the SOAP Response
            printSOAPResponse(soapResponse);
            /*mensaje elemento*/
            SOAPBody body = soapResponse.getSOAPBody();

            //System.out.println("respuesta: " + body.getElementsByTagName("applicationResponse").item(0).getTextContent());
            String str1 = body.getElementsByTagName("applicationResponse").item(0).getTextContent();

            VarGlo.decode(str1, RutaRta + NombreArchvoRta);

            System.out.println(str1);
            /*fin mensaje elemento*/
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("" + e.getMessage());
            e.printStackTrace();
        }
    }

    /*
     PARA CARGAR CATALOGO DE COMISIONES
     ========GetCommissions=========
     */
    private static SOAPMessage CPEsendBillprueba(String ruc, String UsuarioSol, String PassSol, String NombreCPE, String RutaCPE) throws Exception {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        //soapMessage.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "iso-8859-1");
        //soapMessage.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "UTF-8");
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI_ser = "http://service.sunat.gob.pe";
        String serverURI_wsse = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.setPrefix("soapenv");
        envelope.addNamespaceDeclaration("ser", serverURI_ser);
        envelope.addNamespaceDeclaration("wsse", serverURI_wsse);

        SOAPHeader Header = envelope.getHeader();
        Header.setPrefix("soapenv");

        SOAPElement Security = Header.addChildElement("Security", "wsse");//:
        SOAPElement UsernameToken = Security.addChildElement("UsernameToken", "wsse");//wsse:UsernameToken

        SOAPElement Username = UsernameToken.addChildElement("Username", "wsse").addTextNode(ruc + UsuarioSol);//"20173014971MODDATOS");
        SOAPElement Password = UsernameToken.addChildElement("Password", "wsse").addTextNode(PassSol);//"moddatos");

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        soapBody.setPrefix("soapenv");

        SOAPElement sendBill = soapBody.addChildElement("sendBill", "ser");
        SOAPElement fileName = sendBill.addChildElement("fileName").addTextNode(NombreCPE + ".ZIP");

        String ZIP64 = VarGlo.encode(RutaCPE + ".ZIP");
        SOAPElement contentFile = sendBill.addChildElement("contentFile").addTextNode(ZIP64);

        soapMessage.saveChanges();
        /// Print the request message /
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();
        return soapMessage;
    }

    public String[] ConexionCPEBeta(String ruc, String UsuarioSol, String PassSol, String NombreCPE, String RutaCPE, String RutaCDR, String RutaXMLCDR, String RutaWS) {
        String[] Retorno = new String[5];
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server                          
            URL url
                    //= new URL(new URL("https://e-beta.sunat.gob.pe:443/ol-ti-itcpfegem-beta/billService"),
                    = new URL(new URL(RutaWS),
                            "",
                            new URLStreamHandler() {
                        @Override
                        protected URLConnection openConnection(URL url) throws IOException {
                        	URL target = new URL(url.toString());
                            //URLConnection connection = target.openConnection();
                        	URLConnection connection = target.openConnection();
                            // Connection settings
                            connection.setConnectTimeout(10000); // 10 sec
                            connection.setReadTimeout(60000); // 1 min
                            return (connection);
                        }
                    });

            SOAPMessage soapResponse = soapConnection.call(CPEsendBill(ruc, UsuarioSol, PassSol, NombreCPE, RutaCPE), url);

            // Process the SOAP Response
            printSOAPResponse(soapResponse);
            /*mensaje elemento*/
            SOAPBody body = soapResponse.getSOAPBody();

            //System.out.println("respuesta: " + body.getElementsByTagName("applicationResponse").item(0).getTextContent());
            String str1 = "";

            if (NombreCPE.substring(12, 14).equals("RA") || NombreCPE.substring(12, 14).equals("RC")) {//==============PARA LOS DETALLES SOLO (TICKET)
                NodeList Rta = body.getElementsByTagName("ticket");
                if (Rta.getLength() > 0) {
                    str1 = body.getElementsByTagName("ticket").item(0).getTextContent();
                    //VarGlo.decode(str1, RutaCDR);
                    Retorno[0] = "1";//todo ok
                    Retorno[1] = "0";
                    Retorno[2] = str1;
                    Retorno[3] = "";//NO TIENE HAST CDR
                    Retorno[4] = "";//NO TIENE HAST CPE
                } else {
                    System.out.println("el elemento no existe");
                    Retorno[0] = "0";//todo ok
                    Retorno[1] = body.getElementsByTagName("faultcode").item(0).getTextContent().replace("soap-env:Client.", "");
                    Retorno[2] = body.getElementsByTagName("faultstring").item(0).getTextContent();
                    Retorno[3] = "";//NO TIENE HAST CDR
                    Retorno[4] = "";//NO TIENE HAST CPE
                }
            } else {//==========PARA (BOLETAS,FACTURAS,NOTA CREDITO, NOTA DEBITO)
                NodeList Rta = body.getElementsByTagName("applicationResponse");
                if (Rta.getLength() > 0) {
                    str1 = body.getElementsByTagName("applicationResponse").item(0).getTextContent();
                    VarGlo.decode(str1, RutaCDR);//DECODIFICAMOS DE BASE 64
                    VarGlo.extrac_unzip(RutaCDR + ".ZIP", RutaXMLCDR);//EXTRAC ZIP

                    Retorno[0] = "1";//todo ok
                    Retorno[1] = VarGlo.valorXML(RutaCDR, "*", "ResponseCode");///OPTENEMOS ALGUN CODIGO SUNAT DEL CDRXML
                    Retorno[2] = VarGlo.valorXML(RutaCDR, "*", "Description").toUpperCase();///OPTENEMOS ALGUN DESCRIPCION SUNAT DEL CDRXML
                    Retorno[3] = VarGlo.valorXML(RutaCDR, "", "DigestValue");//HAST CDR
                    Retorno[4] = VarGlo.valorXML(RutaCPE, "", "DigestValue");//HAST CPE
                } else {
                    System.out.println("el elemento no existe");
                    Retorno[0] = "0";//todo ok
                    Retorno[1] = body.getElementsByTagName("faultcode").item(0).getTextContent().replace("soap-env:Client.", "");
                    Retorno[2] = body.getElementsByTagName("faultstring").item(0).getTextContent();
                    Retorno[3] = "";//NO TIENE HAST CDR
                    Retorno[4] = "";//NO TIENE HAST CPE
                }
            }
            //System.out.println(str1);
            /*fin mensaje elemento*/
            soapConnection.close();
        } catch (Exception e) {
        	System.out.println(this.getClass().getSimpleName() + " ConexionCPEBeta. ERROR : " + e.getMessage()
			+ " Linea: " + e.getStackTrace()[0].getLineNumber());
            Retorno[0] = "0";//todo ok
            Retorno[1] = "0";
            Retorno[2] = "Error Conectarse a la SUNAT: " + e.getMessage();
            Retorno[3] = "";//NO TIENE HAST CDR
            Retorno[4] = "";//NO TIENE HAST CPE
            //e.printStackTrace();
        } finally {
            //==================ELIMINAMOS FICHERO QUE ENVIAMOS
            File fichero = new File(RutaCPE + ".ZIP");
            fichero.delete();
            //==================ELIMINAMOS FICHERO QUE ENVIAMOS
            File ficheroRta = new File(RutaCDR + ".ZIP");
            ficheroRta.delete();
            //==================ELIMINAMOS FICHERO QUE ENVIAMOS
            File ficheroDummy = new File(RutaXMLCDR + "dummy");
            ficheroDummy.delete();
        }
        return Retorno;
    }

    public String[] ConexionCPEHomologacion(String ruc, String UsuarioSol, String PassSol, String NombreCPE, String RutaCPE, String RutaCDR, String RutaXMLCDR, String RutaWS) {
        String[] Retorno = new String[5];
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server                          
            URL url
                    //= new URL(new URL("https://e-beta.sunat.gob.pe:443/ol-ti-itcpfegem-beta/billService"),
                    = new URL(new URL(RutaWS),
                            "",
                            new URLStreamHandler() {
                        @Override
                        protected URLConnection openConnection(URL url) throws IOException {
                            URL target = new URL(url.toString());
                            URLConnection connection = target.openConnection();
                            // Connection settings
                            connection.setConnectTimeout(10000); // 10 sec
                            connection.setReadTimeout(60000); // 1 min
                            return (connection);
                        }
                    });

            SOAPMessage soapResponse = soapConnection.call(CPEsendBill(ruc, UsuarioSol, PassSol, NombreCPE, RutaCPE), url);

            // Process the SOAP Response
            printSOAPResponse(soapResponse);
            /*mensaje elemento*/
            SOAPBody body = soapResponse.getSOAPBody();

            //System.out.println("respuesta: " + body.getElementsByTagName("applicationResponse").item(0).getTextContent());
            String str1 = "";
            if (NombreCPE.substring(12, 14).equals("RA") || NombreCPE.substring(12, 14).equals("RC")) {//==============PARA LOS DETALLES SOLO (TICKET)
                NodeList Rta = body.getElementsByTagName("ticket");
                if (Rta.getLength() > 0) {
                    str1 = body.getElementsByTagName("ticket").item(0).getTextContent();
                    //VarGlo.decode(str1, RutaCDR);
                    Retorno[0] = "1";//todo ok
                    Retorno[1] = "0";
                    Retorno[2] = str1;
                    Retorno[3] = "";//NO TIENE HAST CDR
                    Retorno[4] = "";//NO TIENE HAST CPE
                } else {
                    System.out.println("el elemento no existe");
                    Retorno[0] = "0";//todo ok
                    Retorno[1] = body.getElementsByTagName("faultcode").item(0).getTextContent();
                    Retorno[2] = body.getElementsByTagName("faultstring").item(0).getTextContent();
                    Retorno[3] = "";//NO TIENE HAST CDR
                    Retorno[4] = "";//NO TIENE HAST CPE
                }
            } else {//==========PARA (BOLETAS,FACTURAS,NOTA CREDITO, NOTA DEBITO)
                NodeList Rta = body.getElementsByTagName("applicationResponse");
                if (Rta.getLength() > 0) {
                    str1 = body.getElementsByTagName("applicationResponse").item(0).getTextContent();
                    VarGlo.decode(str1, RutaCDR);//DECODIFICAMOS DE BASE 64
                    VarGlo.extrac_unzip(RutaCDR + ".ZIP", RutaXMLCDR);//EXTRAC ZIP

                    Retorno[0] = "1";//todo ok
                    Retorno[1] = VarGlo.valorXML(RutaCDR, "*", "ResponseCode");///OPTENEMOS ALGUN CODIGO SUNAT DEL CDRXML
                    Retorno[2] = VarGlo.valorXML(RutaCDR, "*", "Description").toUpperCase();///OPTENEMOS ALGUN DESCRIPCION SUNAT DEL CDRXML
                    Retorno[3] = VarGlo.valorXML(RutaCDR, "", "DigestValue");//HAST CDR
                    Retorno[4] = VarGlo.valorXML(RutaCPE, "", "DigestValue");//HAST CPE
                } else {
                    System.out.println("el elemento no existe");
                    Retorno[0] = "0";//todo ok
                    Retorno[1] = body.getElementsByTagName("faultcode").item(0).getTextContent();
                    Retorno[2] = body.getElementsByTagName("faultstring").item(0).getTextContent();
                    Retorno[3] = "";//NO TIENE HAST CDR
                    Retorno[4] = "";//NO TIENE HAST CPE
                }
            }
            //System.out.println(str1);
            /*fin mensaje elemento*/
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("" + e.getMessage());
            Retorno[0] = "0";//todo ok
            Retorno[1] = "0";
            Retorno[2] = "Error Conectarse a la SUNAT: " + e.getMessage();
            Retorno[3] = "";//NO TIENE HAST CDR
            Retorno[4] = "";//NO TIENE HAST CPE
            //e.printStackTrace();
        } finally {
            //==================ELIMINAMOS FICHERO QUE ENVIAMOS
            File fichero = new File(RutaCPE + ".ZIP");
            fichero.delete();
            //==================ELIMINAMOS FICHERO QUE ENVIAMOS
            File ficheroRta = new File(RutaCDR + ".ZIP");
            ficheroRta.delete();
            //==================ELIMINAMOS FICHERO QUE ENVIAMOS
            File ficheroDummy = new File(RutaXMLCDR + "dummy");
            ficheroDummy.delete();
        }
        return Retorno;
    }

    public String[] ConexionCPEProduccion(String ruc, String UsuarioSol, String PassSol, String NombreCPE, String RutaCPE, String RutaCDR, String RutaXMLCDR, String RutaWS) {
        String[] Retorno = new String[5];
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server                          
            URL url
                    //= new URL(new URL("https://e-beta.sunat.gob.pe:443/ol-ti-itcpfegem-beta/billService"),
                    = new URL(new URL(RutaWS),
                            "",
                            new URLStreamHandler() {
                        @Override
                        protected URLConnection openConnection(URL url) throws IOException {
                            URL target = new URL(url.toString());
                            URLConnection connection = target.openConnection();
                            // Connection settings
                            connection.setConnectTimeout(10000); // 10 sec
                            connection.setReadTimeout(60000); // 1 min
                            return (connection);
                        }
                    });

            SOAPMessage soapResponse = soapConnection.call(CPEsendBill(ruc, UsuarioSol, PassSol, NombreCPE, RutaCPE), url);

            // Process the SOAP Response
            printSOAPResponse(soapResponse);
            /*mensaje elemento*/
            SOAPBody body = soapResponse.getSOAPBody();

            //System.out.println("respuesta: " + body.getElementsByTagName("applicationResponse").item(0).getTextContent());
            String str1 = "";
            if (NombreCPE.substring(12, 14).equals("RA") || NombreCPE.substring(12, 14).equals("RC")) {//==============PARA LOS DETALLES SOLO (TICKET)
                NodeList Rta = body.getElementsByTagName("ticket");
                if (Rta.getLength() > 0) {
                    str1 = body.getElementsByTagName("ticket").item(0).getTextContent();
                    //VarGlo.decode(str1, RutaCDR);
                    Retorno[0] = "1";//todo ok
                    Retorno[1] = "0";
                    Retorno[2] = str1;
                    Retorno[3] = "";//NO TIENE HAST CDR
                    Retorno[4] = "";//NO TIENE HAST CPE
                } else {
                    System.out.println("el elemento no existe");
                    Retorno[0] = "0";//todo ok
                    Retorno[1] = body.getElementsByTagName("faultcode").item(0).getTextContent().replace("soap-env:Client.", "");
                    Retorno[2] = body.getElementsByTagName("faultstring").item(0).getTextContent();
                    Retorno[3] = "";//NO TIENE HAST CDR
                    Retorno[4] = "";//NO TIENE HAST CPE
                }
            } else {//==========PARA (BOLETAS,FACTURAS,NOTA CREDITO, NOTA DEBITO)
                NodeList Rta = body.getElementsByTagName("applicationResponse");
                if (Rta.getLength() > 0) {
                    str1 = body.getElementsByTagName("applicationResponse").item(0).getTextContent();
                    VarGlo.decode(str1, RutaCDR);//DECODIFICAMOS DE BASE 64
                    VarGlo.extrac_unzip(RutaCDR + ".ZIP", RutaXMLCDR);//EXTRAC ZIP

                    Retorno[0] = "1";//todo ok
                    Retorno[1] = VarGlo.valorXML(RutaCDR, "*", "ResponseCode");///OPTENEMOS ALGUN CODIGO SUNAT DEL CDRXML
                    Retorno[2] = VarGlo.valorXML(RutaCDR, "*", "Description").toUpperCase();///OPTENEMOS ALGUN DESCRIPCION SUNAT DEL CDRXML
                    Retorno[3] = VarGlo.valorXML(RutaCDR, "", "DigestValue");//HAST CDR
                    Retorno[4] = VarGlo.valorXML(RutaCPE, "", "DigestValue");//HAST CPE
                } else {
                    System.out.println("el elemento no existe");
                    Retorno[0] = "0";//todo ok
                    Retorno[1] = body.getElementsByTagName("faultcode").item(0).getTextContent().replace("soap-env:Client.", "");
                    Retorno[2] = body.getElementsByTagName("faultstring").item(0).getTextContent();
                    Retorno[3] = "";//NO TIENE HAST CDR
                    Retorno[4] = "";//NO TIENE HAST CPE
                }
            }
            //System.out.println(str1);
            /*fin mensaje elemento*/
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("" + e.getMessage());
            Retorno[0] = "0";//todo ok
            Retorno[1] = "0";
            Retorno[2] = "Error Conectarse a la SUNAT: " + e.getMessage();
            Retorno[3] = "";//NO TIENE HAST CDR
            Retorno[4] = "";//NO TIENE HAST CPE
            //e.printStackTrace();
        } finally {
            //==================ELIMINAMOS FICHERO QUE ENVIAMOS
            File fichero = new File(RutaCPE + ".ZIP");
            fichero.delete();
            //==================ELIMINAMOS FICHERO QUE ENVIAMOS
            File ficheroRta = new File(RutaCDR + ".ZIP");
            ficheroRta.delete();
            //==================ELIMINAMOS FICHERO QUE ENVIAMOS
            File ficheroDummy = new File(RutaXMLCDR + "dummy");
            ficheroDummy.delete();
        }
        return Retorno;
    }

    //=================================COMPROBANTES DE RETENCION Y PERCEPCIO============================
    public String[] ConexionCPEBeta_RTPC(String ruc, String UsuarioSol, String PassSol, String NombreCPE, String RutaCPE, String RutaCDR, String RutaXMLCDR, String RutaWS) {
        String[] Retorno = new String[5];
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server                          
            URL url
                    //= new URL(new URL("https://e-beta.sunat.gob.pe/ol-ti-itemision-otroscpe-gem-beta/billService"),
                    = new URL(new URL(RutaWS),
                            "",
                            new URLStreamHandler() {
                        @Override
                        protected URLConnection openConnection(URL url) throws IOException {
                            URL target = new URL(url.toString());
                            URLConnection connection = target.openConnection();
                            // Connection settings
                            connection.setConnectTimeout(10000); // 10 sec
                            connection.setReadTimeout(60000); // 1 min
                            return (connection);
                        }
                    });

            SOAPMessage soapResponse = soapConnection.call(CPEsendBill(ruc, UsuarioSol, PassSol, NombreCPE, RutaCPE), url);

            // Process the SOAP Response
            printSOAPResponse(soapResponse);
            /*mensaje elemento*/
            SOAPBody body = soapResponse.getSOAPBody();

            //System.out.println("respuesta: " + body.getElementsByTagName("applicationResponse").item(0).getTextContent());
            String str1 = "";

            if (NombreCPE.substring(12, 14).equals("RA") || NombreCPE.substring(12, 14).equals("RC") || NombreCPE.substring(12, 14).equals("RR")) {//==============PARA LOS DETALLES SOLO (TICKET)
                NodeList Rta = body.getElementsByTagName("ticket");
                if (Rta.getLength() > 0) {
                    str1 = body.getElementsByTagName("ticket").item(0).getTextContent();
                    //VarGlo.decode(str1, RutaCDR);
                    Retorno[0] = "1";//todo ok
                    Retorno[1] = "0";
                    Retorno[2] = str1;
                    Retorno[3] = "";//NO TIENE HAST CDR
                    Retorno[4] = "";//NO TIENE HAST CPE
                } else {
                    System.out.println("el elemento no existe");
                    Retorno[0] = "0";//todo ok
                    Retorno[1] = body.getElementsByTagName("faultstring").item(0).getTextContent();
                    Retorno[2] = body.getElementsByTagName("message").item(0).getTextContent();
                    Retorno[3] = "";//NO TIENE HAST CDR
                    Retorno[4] = "";//NO TIENE HAST CPE
                }
            } else {//==========PARA (BOLETAS,FACTURAS,NOTA CREDITO, NOTA DEBITO)
                NodeList Rta = body.getElementsByTagName("applicationResponse");
                if (Rta.getLength() > 0) {
                    str1 = body.getElementsByTagName("applicationResponse").item(0).getTextContent();
                    VarGlo.decode(str1, RutaCDR);//DECODIFICAMOS DE BASE 64
                    VarGlo.extrac_unzip(RutaCDR + ".ZIP", RutaXMLCDR);//EXTRAC ZIP

                    Retorno[0] = "1";//todo ok
                    Retorno[1] = VarGlo.valorXML(RutaCDR, "*", "ResponseCode");///OPTENEMOS ALGUN CODIGO SUNAT DEL CDRXML
                    Retorno[2] = VarGlo.valorXML(RutaCDR, "*", "Description").toUpperCase();///OPTENEMOS ALGUN DESCRIPCION SUNAT DEL CDRXML
                    Retorno[3] = VarGlo.valorXML(RutaCDR, "", "DigestValue");//HAST CDR
                    Retorno[4] = VarGlo.valorXML(RutaCPE, "", "DigestValue");//HAST CPE
                } else {
                    System.out.println("el elemento no existe");
                    Retorno[0] = "0";//todo ok
                    Retorno[1] = body.getElementsByTagName("faultstring").item(0).getTextContent();
                    Retorno[2] = body.getElementsByTagName("message").item(0).getTextContent();
                    Retorno[3] = "";//NO TIENE HAST CDR
                    Retorno[4] = "";//NO TIENE HAST CPE
                }
            }
            //System.out.println(str1);
            /*fin mensaje elemento*/
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("" + e.getMessage());
            Retorno[0] = "0";//todo ok
            Retorno[1] = "0";
            Retorno[2] = "Error Conectarse a la SUNAT: " + e.getMessage();
            Retorno[3] = "";//NO TIENE HAST CDR
            Retorno[4] = "";//NO TIENE HAST CPE
            //e.printStackTrace();
        } finally {
            //==================ELIMINAMOS FICHERO QUE ENVIAMOS
            File fichero = new File(RutaCPE + ".ZIP");
            fichero.delete();
            //==================ELIMINAMOS FICHERO QUE ENVIAMOS
            File ficheroRta = new File(RutaCDR + ".ZIP");
            ficheroRta.delete();
            //==================ELIMINAMOS FICHERO QUE ENVIAMOS
            File ficheroDummy = new File(RutaXMLCDR + "dummy");
            ficheroDummy.delete();
        }
        return Retorno;
    }

    public String[] ConexionCPEProduccion_RTPC(String ruc, String UsuarioSol, String PassSol, String NombreCPE, String RutaCPE, String RutaCDR, String RutaXMLCDR, String RutaWS) {
        String[] Retorno = new String[5];
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server                          
            URL url
                    //= new URL(new URL("https://www.sunat.gob.pe/ol-ti-itemision-otroscpe-gem/billService"),
                    = new URL(new URL(RutaWS),
                            "",
                            new URLStreamHandler() {
                        @Override
                        protected URLConnection openConnection(URL url) throws IOException {
                            URL target = new URL(url.toString());
                            URLConnection connection = target.openConnection();
                            // Connection settings
                            connection.setConnectTimeout(10000); // 10 sec
                            connection.setReadTimeout(60000); // 1 min
                            return (connection);
                        }
                    });

            SOAPMessage soapResponse = soapConnection.call(CPEsendBill(ruc, UsuarioSol, PassSol, NombreCPE, RutaCPE), url);

            // Process the SOAP Response
            printSOAPResponse(soapResponse);
            /*mensaje elemento*/
            SOAPBody body = soapResponse.getSOAPBody();

            //System.out.println("respuesta: " + body.getElementsByTagName("applicationResponse").item(0).getTextContent());
            String str1 = "";
            if (NombreCPE.substring(12, 14).equals("RA")) {//==============PARA LOS DETALLES SOLO (TICKET)
                NodeList Rta = body.getElementsByTagName("ticket");
                if (Rta.getLength() > 0) {
                    str1 = body.getElementsByTagName("ticket").item(0).getTextContent();
                    //VarGlo.decode(str1, RutaCDR);
                    Retorno[0] = "1";//todo ok
                    Retorno[1] = "0";
                    Retorno[2] = str1;
                    Retorno[3] = "";//NO TIENE HAST CDR
                    Retorno[4] = "";//NO TIENE HAST CPE
                } else {
                    System.out.println("el elemento no existe");
                    Retorno[0] = "0";//todo ok
                    Retorno[1] = body.getElementsByTagName("faultstring").item(0).getTextContent();
                    Retorno[2] = body.getElementsByTagName("message").item(0).getTextContent();
                    Retorno[3] = "";//NO TIENE HAST CDR
                    Retorno[4] = "";//NO TIENE HAST CPE
                }
            } else {//==========PARA (BOLETAS,FACTURAS,NOTA CREDITO, NOTA DEBITO)
                NodeList Rta = body.getElementsByTagName("applicationResponse");
                if (Rta.getLength() > 0) {
                    str1 = body.getElementsByTagName("applicationResponse").item(0).getTextContent();
                    VarGlo.decode(str1, RutaCDR);//DECODIFICAMOS DE BASE 64
                    VarGlo.extrac_unzip(RutaCDR + ".ZIP", RutaXMLCDR);//EXTRAC ZIP

                    Retorno[0] = "1";//todo ok
                    Retorno[1] = VarGlo.valorXML(RutaCDR, "*", "ResponseCode");///OPTENEMOS ALGUN CODIGO SUNAT DEL CDRXML
                    Retorno[2] = VarGlo.valorXML(RutaCDR, "*", "Description").toUpperCase();///OPTENEMOS ALGUN DESCRIPCION SUNAT DEL CDRXML
                    Retorno[3] = VarGlo.valorXML(RutaCDR, "", "DigestValue");//HAST CDR
                    Retorno[4] = VarGlo.valorXML(RutaCPE, "", "DigestValue");//HAST CPE
                } else {
                    System.out.println("el elemento no existe");
                    Retorno[0] = "0";//todo ok
                    Retorno[1] = body.getElementsByTagName("faultstring").item(0).getTextContent();
                    Retorno[2] = body.getElementsByTagName("message").item(0).getTextContent();
                    Retorno[3] = "";//NO TIENE HAST CDR
                    Retorno[4] = "";//NO TIENE HAST CPE
                }
            }
            //System.out.println(str1);
            /*fin mensaje elemento*/
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("" + e.getMessage());
            Retorno[0] = "0";//todo ok
            Retorno[1] = "0";
            Retorno[2] = "Error Conectarse a la SUNAT: " + e.getMessage();
            Retorno[3] = "";//NO TIENE HAST CDR
            Retorno[4] = "";//NO TIENE HAST CPE
            //e.printStackTrace();
        } finally {
            //==================ELIMINAMOS FICHERO QUE ENVIAMOS
            File fichero = new File(RutaCPE + ".ZIP");
            fichero.delete();
            //==================ELIMINAMOS FICHERO QUE ENVIAMOS
            File ficheroRta = new File(RutaCDR + ".ZIP");
            ficheroRta.delete();
            //==================ELIMINAMOS FICHERO QUE ENVIAMOS
            File ficheroDummy = new File(RutaXMLCDR + "dummy");
            ficheroDummy.delete();
        }
        return Retorno;
    }

    //======ENVIO DOCUMENTOS (BOLETA, FACTURA, NOTA CREDITO, NOTA DEBITO, ANULADOS, RESUMEN BOLETA)=========================================
    private static SOAPMessage CPEsendBill(String ruc, String UsuarioSol, String PassSol, String NombreCPE, String RutaCPE) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        //soapMessage.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "iso-8859-1");
        //soapMessage.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "UTF-8");
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI_ser = "http://service.sunat.gob.pe";
        String serverURI_wsse = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.setPrefix("soapenv");
        envelope.addNamespaceDeclaration("ser", serverURI_ser);
        envelope.addNamespaceDeclaration("wsse", serverURI_wsse);

        SOAPHeader Header = envelope.getHeader();
        Header.setPrefix("soapenv");

        SOAPElement Security = Header.addChildElement("Security", "wsse");//:
        SOAPElement UsernameToken = Security.addChildElement("UsernameToken", "wsse");//wsse:UsernameToken

        SOAPElement Username = UsernameToken.addChildElement("Username", "wsse").addTextNode(ruc + UsuarioSol);//"20173014971MODDATOS");
        SOAPElement Password = UsernameToken.addChildElement("Password", "wsse").addTextNode(PassSol);//"moddatos");

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        soapBody.setPrefix("soapenv");

        SOAPElement sendBill = null;
        if (NombreCPE.substring(12, 14).equals("RA") || NombreCPE.substring(12, 14).equals("RC") || NombreCPE.substring(12, 14).equals("RR")) {
            sendBill = soapBody.addChildElement("sendSummary", "ser");
            //System.out.println("RTA: "+NombreCPE.substring(12, 14));
        } else {
            sendBill = soapBody.addChildElement("sendBill", "ser");
        }

        //SOAPElement sendBill = soapBody.addChildElement("sendBill", "ser");
        SOAPElement fileName = sendBill.addChildElement("fileName").addTextNode(NombreCPE + ".ZIP");

        String ZIP64 = VarGlo.encode(RutaCPE + ".ZIP");
        SOAPElement contentFile = sendBill.addChildElement("contentFile").addTextNode(ZIP64);

        soapMessage.saveChanges();
        /// Print the request message /
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();
        return soapMessage;
    }

    //===========================consulta ticket=============================
    private static SOAPMessage ConsultaTicket(String ruc, String UsuarioSol, String PassSol, String NroTicket) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI_ser = "http://service.sunat.gob.pe";
        String serverURI_wsse = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.setPrefix("soapenv");
        envelope.addNamespaceDeclaration("ser", serverURI_ser);
        envelope.addNamespaceDeclaration("wsse", serverURI_wsse);

        SOAPHeader Header = envelope.getHeader();
        Header.setPrefix("soapenv");

        SOAPElement Security = Header.addChildElement("Security", "wsse");//:
        SOAPElement UsernameToken = Security.addChildElement("UsernameToken", "wsse");//wsse:UsernameToken

        SOAPElement Username = UsernameToken.addChildElement("Username", "wsse").addTextNode(ruc + UsuarioSol);//"20173014971MODDATOS");
        SOAPElement Password = UsernameToken.addChildElement("Password", "wsse").addTextNode(PassSol);//"moddatos");

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        soapBody.setPrefix("soapenv");

        SOAPElement getStatus = soapBody.addChildElement("getStatus", "ser");
        SOAPElement ticket = getStatus.addChildElement("ticket").addTextNode(NroTicket);

        soapMessage.saveChanges();
        /// Print the request message /
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();
        return soapMessage;
    }

    public String[] ConexionCPEConsultaTicket(String ruc, String UsuarioSol, String PassSol, String NroTicket, String RutaCPE, String RutaCDR, String RutaXMLCDR, String RutaWS) {
        String[] Retorno = new String[5];
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server                          
            URL url
                    //= new URL(new URL("https://e-beta.sunat.gob.pe:443/ol-ti-itcpfegem-beta/billService"),
                    = new URL(new URL(RutaWS),
                            "",
                            new URLStreamHandler() {
                        @Override
                        protected URLConnection openConnection(URL url) throws IOException {
                            URL target = new URL(url.toString());
                            URLConnection connection = target.openConnection();
                            // Connection settings
                            connection.setConnectTimeout(10000); // 10 sec
                            connection.setReadTimeout(60000); // 1 min
                            return (connection);
                        }
                    });

            SOAPMessage soapResponse = soapConnection.call(ConsultaTicket(ruc, UsuarioSol, PassSol, NroTicket), url);

            // Process the SOAP Response
            printSOAPResponse(soapResponse);
            /*mensaje elemento*/
            SOAPBody body = soapResponse.getSOAPBody();

            //System.out.println("respuesta: " + body.getElementsByTagName("applicationResponse").item(0).getTextContent());
            int codigoRta;
            String msjRta = "";
            NodeList Rta = body.getElementsByTagName("statusCode");

            if (Rta.getLength() > 0) {
                codigoRta = Integer.parseInt(body.getElementsByTagName("statusCode").item(0).getTextContent());
                msjRta = body.getElementsByTagName("content").item(0).getTextContent();
                if (codigoRta >= 0 && codigoRta <= 99) {

                    VarGlo.decode(msjRta, RutaCDR);//DECODIFICAMOS DE BASE 64
                    VarGlo.extrac_unzip(RutaCDR + ".ZIP", RutaXMLCDR);//EXTRAC ZIP
                    if (codigoRta == 0) {//cuando sunat lo recibio
                        Retorno[0] = "1";
                        Retorno[1] = VarGlo.valorXML(RutaCDR, "*", "ResponseCode");///OPTENEMOS ALGUN CODIGO SUNAT DEL CDRXML
                        Retorno[2] = VarGlo.valorXML(RutaCDR, "*", "Description").toUpperCase();///OPTENEMOS ALGUN DESCRIPCION SUNAT DEL CDRXML
                        Retorno[3] = VarGlo.valorXML(RutaCDR, "", "DigestValue");//HAST CDR
                        Retorno[4] = VarGlo.valorXML(RutaCPE, "", "DigestValue");//NO TIENE HAST CPE
                    } else {//tiene errores o esta en proceso
                        Retorno[0] = "0";//error
                        Retorno[1] = VarGlo.valorXML(RutaCDR, "*", "ResponseCode");///OPTENEMOS ALGUN CODIGO SUNAT DEL CDRXML
                        Retorno[2] = VarGlo.valorXML(RutaCDR, "*", "Description").toUpperCase();///OPTENEMOS ALGUN DESCRIPCION SUNAT DEL CDRXML
                        Retorno[3] = VarGlo.valorXML(RutaCDR, "", "DigestValue");//HAST CDR
                        Retorno[4] = VarGlo.valorXML(RutaCPE, "", "DigestValue");//NO TIENE HAST CPE
                    }
                } else {//errores de sunat x el cual no acepta
                    Retorno[0] = "0";//error
                    Retorno[1] = Integer.toString(codigoRta);
                    Retorno[2] = msjRta;
                    Retorno[3] = "";//NO TIENE HAST CDR
                    Retorno[4] = "";//NO TIENE HAST CPE
                }
            } else {//errores desconocidos
                Retorno[0] = "0";//todo ok
                Retorno[1] = "0000";
                Retorno[2] = "Error desconocido";
                Retorno[3] = "";//NO TIENE HAST CDR
                Retorno[4] = "";//NO TIENE HAST CPE
            }

            //System.out.println(str1);
            /*fin mensaje elemento*/
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("" + e.getMessage());
            Retorno[0] = "0";//todo ok
            Retorno[1] = "0000";
            Retorno[2] = "Error Conectarse a la SUNAT: " + e.getMessage();
            Retorno[3] = "";//NO TIENE HAST CDR
            Retorno[4] = "";//NO TIENE HAST CPE
            //e.printStackTrace();
        } finally {
            //==================ELIMINAMOS FICHERO QUE ENVIAMOS
            File ficheroRta = new File(RutaCDR + ".ZIP");
            ficheroRta.delete();
            //==================ELIMINAMOS FICHERO QUE ENVIAMOS
            File ficheroDummy = new File(RutaXMLCDR + "dummy");
            ficheroDummy.delete();
        }
        return Retorno;
    }

    //===========================consulta estado factura=============================
    private static SOAPMessage ConsultaEstadoFactura(String ruc, String UsuarioSol, String PassSol, String rucCliente, String tipoDocumento, String serie, String numero) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI_ser = "http://service.sunat.gob.pe";
        String serverURI_wsse = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.setPrefix("soapenv");
        envelope.addNamespaceDeclaration("ser", serverURI_ser);
        envelope.addNamespaceDeclaration("wsse", serverURI_wsse);

        SOAPHeader Header = envelope.getHeader();
        Header.setPrefix("soapenv");

        SOAPElement Security = Header.addChildElement("Security", "wsse");//:
        SOAPElement UsernameToken = Security.addChildElement("UsernameToken", "wsse");//wsse:UsernameToken

        SOAPElement Username = UsernameToken.addChildElement("Username", "wsse").addTextNode(ruc + UsuarioSol);//"20173014971MODDATOS");
        SOAPElement Password = UsernameToken.addChildElement("Password", "wsse").addTextNode(PassSol);//"moddatos");

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        soapBody.setPrefix("soapenv");

        SOAPElement getStatus = soapBody.addChildElement("getStatus", "ser");
        SOAPElement rucComprobante = getStatus.addChildElement("rucComprobante").addTextNode(ruc);
        SOAPElement tipoComprobante = getStatus.addChildElement("tipoComprobante").addTextNode(tipoDocumento);
        SOAPElement serieComprobante = getStatus.addChildElement("serieComprobante").addTextNode(serie);
        SOAPElement numeroComprobante = getStatus.addChildElement("numeroComprobante").addTextNode(numero);
        /*
         <ser:getStatus>
         <rucComprobante>1028308796</rucComprobante>
         <tipoComprobante>01</tipoComprobante>
         <serieComprobante>f213</serieComprobante>
         <numeroComprobante>12345</numeroComprobante>
         </ser:getStatus>
         */

        soapMessage.saveChanges();
        /// Print the request message /
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();
        return soapMessage;
    }

    public String[] ConexionCPEConsultaEstadoFactura(String ruc, String UsuarioSol, String PassSol, String rucCliente, String tipoDocumento, String serie, String numero, String RutaWS) {
        String[] Retorno = new String[5];
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server                          
            URL url
                    //= new URL(new URL("https://e-beta.sunat.gob.pe:443/ol-ti-itcpfegem-beta/billService"),
                    = new URL(new URL(RutaWS),
                            "",
                            new URLStreamHandler() {
                        @Override
                        protected URLConnection openConnection(URL url) throws IOException {
                            URL target = new URL(url.toString());
                            URLConnection connection = target.openConnection();
                            // Connection settings
                            connection.setConnectTimeout(10000); // 10 sec
                            connection.setReadTimeout(60000); // 1 min
                            return (connection);
                        }
                    });

            SOAPMessage soapResponse = soapConnection.call(ConsultaEstadoFactura("20523799623", UsuarioSol, PassSol, rucCliente, tipoDocumento, serie, numero), url);

            // Process the SOAP Response
            printSOAPResponse(soapResponse);
            /*mensaje elemento*/
            SOAPBody body = soapResponse.getSOAPBody();

            String codigoRta = "";
            String msjRta = "";
            NodeList Rta = body.getElementsByTagName("statusCode");

            if (Rta.getLength() > 0) {
                codigoRta = body.getElementsByTagName("statusCode").item(0).getTextContent();
                msjRta = body.getElementsByTagName("statusMessage").item(0).getTextContent();

                Retorno[0] = "1";//error
                Retorno[1] = codigoRta;
                Retorno[2] = msjRta;
                Retorno[3] = "";//NO TIENE HAST CDR
                Retorno[4] = "";//NO TIENE HAST CPE

            } else {//errores desconocidos
                Retorno[0] = "0";//todo ok
                Retorno[1] = "0000";
                Retorno[2] = "Error desconocido";
                Retorno[3] = "";//NO TIENE HAST CDR
                Retorno[4] = "";//NO TIENE HAST CPE
            }

            //System.out.println(str1);
            /*fin mensaje elemento*/
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("" + e.getMessage());
            Retorno[0] = "0";//todo ok
            Retorno[1] = "0000";
            Retorno[2] = "Error Conectarse a la SUNAT: " + e.getMessage();
            Retorno[3] = "";//NO TIENE HAST CDR
            Retorno[4] = "";//NO TIENE HAST CPE
            //e.printStackTrace();
        } finally {

        }
        return Retorno;
    }

    //=================================VERIFICAR TICKETS (ANULADOS O BAJA, RESUMEN BOLETAS)========================================
    private static SOAPMessage CPEsendBill_StatusCDR(String ruc, String UsuarioSol, String PassSol, String NroTicket) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI_ser = "http://service.sunat.gob.pe";
        String serverURI_wsse = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
        String serverURI_xsi = "http://www.w3.org/2001/XMLSchema-instance";
        String serverURI_xsd = "http://www.w3.org/2001/XMLSchema";
        String serverURI_enc = "http://schemas.xmlsoap.org/soap/encoding/";
        String serverURI_env = "http://schemas.xmlsoap.org/soap/encoding/";

        //<SOAP-ENV:Header xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope">
        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.setPrefix("soapenv");
        envelope.addNamespaceDeclaration("m", serverURI_ser);
        envelope.addNamespaceDeclaration("SOAP-ENC", serverURI_enc);
        envelope.addNamespaceDeclaration("wsse", serverURI_wsse);
        envelope.addNamespaceDeclaration("xsi", serverURI_xsi);
        envelope.addNamespaceDeclaration("xsd", serverURI_xsd);

        SOAPHeader Header = envelope.getHeader();
        Header.addNamespaceDeclaration("soapenv", serverURI_env);
        Header.setPrefix("soapenv");

        SOAPElement Security = Header.addChildElement("Security", "wsse");//:
        SOAPElement UsernameToken = Security.addChildElement("UsernameToken", "wsse");//wsse:UsernameToken

        SOAPElement Username = UsernameToken.addChildElement("Username", "wsse").addTextNode(ruc + UsuarioSol);
        SOAPElement Password = UsernameToken.addChildElement("Password", "wsse").addTextNode(PassSol);//"moddatos");
//        SOAPElement Username = UsernameToken.addChildElement("Username", "wsse").addTextNode("20100066603MODDATOS");
//        SOAPElement Password = UsernameToken.addChildElement("Password", "wsse").addTextNode("moddatos");

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        soapBody.setPrefix("soapenv");

        SOAPElement getStatusCdr = soapBody.addChildElement("getStatusCdr", "m");
        getStatusCdr.addNamespaceDeclaration("m", serverURI_ser);

        SOAPElement rucComprobante = getStatusCdr.addChildElement("rucComprobante").addTextNode("20520485750");
        SOAPElement tipoComprobante = getStatusCdr.addChildElement("tipoComprobante").addTextNode("01");
        SOAPElement serieComprobante = getStatusCdr.addChildElement("serieComprobante").addTextNode("FF02");
        SOAPElement numeroComprobante = getStatusCdr.addChildElement("numeroComprobante").addTextNode("125");

//        <rucComprobante>20520485750</rucComprobante> 
//        <tipoComprobante>01</tipoComprobante> 
//        <serieComprobante>FF02</serieComprobante> 
//        <numeroComprobante>125</numeroComprobante>
        //SOAPElement ticket = getStatusCdr.addChildElement("ticket").addTextNode(NroTicket);
        soapMessage.saveChanges();
        /// Print the request message /
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();
        return soapMessage;
    }

    //=======================verificamos mensaje y hash para actualizar=======================
    public String[] obtenerDatosXML(String RutaCPE, String RutaCDR) {
        String[] Retorno = new String[5];
        try {
            Retorno[0] = "1";//todo ok
            Retorno[1] = VarGlo.valorXML(RutaCDR, "*", "ResponseCode");///OPTENEMOS ALGUN CODIGO SUNAT DEL CDRXML
            Retorno[2] = VarGlo.valorXML(RutaCDR, "*", "Description").toUpperCase();///OPTENEMOS ALGUN DESCRIPCION SUNAT DEL CDRXML
            Retorno[3] = VarGlo.valorXML(RutaCDR, "", "DigestValue");//HAST CDR
            Retorno[4] = VarGlo.valorXML(RutaCPE, "", "DigestValue");//HAST CPE
        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        } 
        return Retorno;
    }

    /**
     * Method used to print the SOAP Response
     */
    private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.print("\nResponse SOAP Message = ");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(System.out);
        //StreamResult result = new StreamResult(writer);
        transformer.transform(sourceContent, result);

        String strResult = writer.toString();
        //GuardarComision(strResult);
    }

}
