/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gteperu.erp.everest.cpe;

import org.gteperu.erp.everest.utils.VariablesGlobales;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.xml.crypto.MarshalException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

/**
 *
 * @author developer2
 */
public class Signature {

    //http://stackoverflow.com/questions/32862184/how-to-get-certificate-serial-number-from-p12-in-java
    /**
     * @param args the command line arguments
     */
    static VariablesGlobales VarGlo = new VariablesGlobales();

    public static void main(String[] args) throws FileNotFoundException,
            NoSuchAlgorithmException,
            InvalidAlgorithmParameterException,
            ParserConfigurationException,
            SAXException,
            MarshalException,
            KeyStoreException,
            IOException,
            CertificateException,
            Exception {

        int flg_firma = 0;//(1=factura,boleta,nc,nd)<====>(0=retencion, percepcion)

        String rutaXML = "H:\\CPE\\BETA\\20100078792-20-R002-2251";
        String rutaFirma = "H:\\CPE\\FIRMABETA\\CD AVON 2017.pfx";
        String passFirma = "Avon2017";
        //String passFirma = "123456";-----contraseña firma de prueba
        
        //String nomArchivo = "20100078792-20-R002-41372";

        Signature(flg_firma, rutaXML, rutaFirma, passFirma);
    }

	public static int Signature(int flg_firma, String rutaXML, String rutaFirma, String passFirma) throws FileNotFoundException,
            NoSuchAlgorithmException,
            InvalidAlgorithmParameterException,
            ParserConfigurationException,
            SAXException,
            MarshalException,
            KeyStoreException,
            IOException,
            CertificateException,
            Exception {
		try{
			
		
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
        Reference ref = fac.newReference("", fac.newDigestMethod(DigestMethod.SHA1, null), Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)), null, null);

        SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE,
                (C14NMethodParameterSpec) null),
                fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                Collections.singletonList(ref));

        // TODO code application logic here
        KeyStore p12 = KeyStore.getInstance("pkcs12");
        //p12.load(new FileInputStream(VarGlo.rutaFirma + "\\20100078792.pfx"), "123456".toCharArray());
        p12.load(new FileInputStream(rutaFirma), passFirma.toCharArray());
        Enumeration e = p12.aliases();
        String alias = (String) e.nextElement();
        System.out.println("Alias certifikata:" + alias);

        KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) p12.getEntry(alias, new KeyStore.PasswordProtection(passFirma.toCharArray()));
        X509Certificate cert = (X509Certificate) keyEntry.getCertificate();

        System.out.println("cert name:" + cert.getSubjectX500Principal().getName());
        System.out.println("cert serial number: " + cert.getSerialNumber());

        KeyInfoFactory kif = fac.getKeyInfoFactory();
        List x509Content = new ArrayList();
        x509Content.add(cert.getSubjectX500Principal().getName());
        x509Content.add(cert);
        X509Data xd = kif.newX509Data(x509Content);
        KeyInfo ki = (KeyInfo) kif.newKeyInfo(Collections.singletonList(xd));

//        byte[] encodedBytes = cert.getEncoded();//"Test".getBytes();
//        System.out.println("cert hast:" + encode(encodedBytes));
//        System.out.println("\n\n\n cert signature:" + encode(cert.getSignature()));        
//        MessageDigest md = MessageDigest.getInstance("SHA-1");
//        md.update(cert.getEncoded());
//        System.out.println("\n\n\n cert signature:" + encode(md.digest()));
//        System.out.println("\n\n\n cert signature:" + cert.getSubjectDN());
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        //Document doc = dbf.newDocumentBuilder().parse(new FileInputStream(ArchivoRuta + ".XML"));//origen

        //Document doc = dbf.newDocumentBuilder().parse(new FileInputStream("F:\\cert\\BoletaB11.xml"));//origen
        //Document doc = dbf.newDocumentBuilder().parse(new FileInputStream(VarGlo.rutaXMLCPE + "\\BoletaB11.xml"));//origen
        Document doc = dbf.newDocumentBuilder().parse(new FileInputStream(rutaXML + ".XML"), "ISO-8859-1");//origen
        doc.getDocumentElement().normalize();

        DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getDocumentElement().getElementsByTagNameNS("*", "ExtensionContent").item(flg_firma));
        javax.xml.crypto.dsig.XMLSignature signature = fac.newXMLSignature(si, (KeyInfo) ki, null, "SignatureSP", null);
        signature.sign(dsc);

        OutputStream os = new FileOutputStream(rutaXML + ".XML");
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
//        trans.setOutputProperty(OutputKeys.INDENT, "yes");
//        trans.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        trans.transform(new DOMSource(doc), new StreamResult(os));

        ///////////////////////ZIPEAMOS///////////////////////
        //VarGlo.Zippear("D:\\ARCHIVOS\\" + nom_archivo + ".XML", "D:\\ARCHIVOS\\" + nom_archivo + ".ZIP");
        VarGlo.add_unzip(rutaXML + ".XML", rutaXML + ".ZIP");
		}catch(Exception e){
			System.err.println("ERROR: " + Signature.class.getName() + "/generaDocumentoPrueba/Signature => " + e.getMessage());
			System.err.println( "/generaDocumentoPrueba/Signature" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			//throw new RuntimeException();
		}
        return 1;
    }

}
