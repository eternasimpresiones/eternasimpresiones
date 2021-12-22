/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gteperu.erp.everest.cpe;

import org.gteperu.erp.everest.domain._CpeGuiaRemisionBean;
import org.gteperu.erp.everest.domain._CpeGuiaRemisionDetalleBean;
import org.gteperu.erp.everest.domain.Cuota;
import org.gteperu.erp.everest.domain._CpeBean;
import org.gteperu.erp.everest.domain._Cpe_BajaBean;
import org.gteperu.erp.everest.domain._Cpe_Baja_DetalleBean;
import org.gteperu.erp.everest.domain._Cpe_DetalleBean;
import org.gteperu.erp.everest.domain._Cpe_Resumen_BoletaBean;
import org.gteperu.erp.everest.domain._Cpe_Resumen_Boleta_Detalle1Bean;
import org.gteperu.erp.everest.domain._Cpe_Resumen_Boleta_DetalleBean;
import org.gteperu.erp.everest.domain._Cpe_RetencionPercepcionBean;
import org.gteperu.erp.everest.domain._Cpe_RetencionPercepcion_DetalleBean;
import org.gteperu.erp.everest.domain._Cpe_RrBean;
import org.gteperu.erp.everest.domain._Cpe_Rr_DetalleBean;
import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

/**
 *
 * @author jose
 */
public class CPESunatUBL21 {

    public int GenerarXML_NC(String RutaXml, _CpeBean Cpe, List<_Cpe_DetalleBean> lstCpe_Detalle) {
        try {
        	System.out.println(Cpe.toString());
            String xmlCPE = "";
            xmlCPE = "<?xml version='1.0' encoding='UTF-8'?>\n"
                    + "<CreditNote xmlns='urn:oasis:names:specification:ubl:schema:xsd:CreditNote-2' "
                    + "xmlns:cac='urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2' "
                    + "xmlns:cbc='urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2' "
                    + "xmlns:ccts='urn:un:unece:uncefact:documentation:2' "
                    + "xmlns:ds='http://www.w3.org/2000/09/xmldsig#' "
                    + "xmlns:ext='urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2' "
                    + "xmlns:qdt='urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2' "
                    + "xmlns:sac='urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1' "
                    + "xmlns:udt='urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2' "
                    + "xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>\n"
                    + "    <ext:UBLExtensions>\n"
                    + "        <ext:UBLExtension>\n"
                    + "            <ext:ExtensionContent>\n"
                    + "            </ext:ExtensionContent>\n"
                    + "        </ext:UBLExtension>\n"
                    + "    </ext:UBLExtensions>\n"
                    + "    <cbc:UBLVersionID>2.1</cbc:UBLVersionID>\n"
                    + "    <cbc:CustomizationID>2.0</cbc:CustomizationID>\n"
                    + "	<cbc:ProfileID>" + Cpe.getTIPO_OPERACION() + "</cbc:ProfileID>\n"
                    + "    <cbc:ID>" + Cpe.getNRO_COMPROBANTE() + "</cbc:ID>\n"
                    + "    <cbc:IssueDate>" + Cpe.getFECHA_DOCUMENTO() + "</cbc:IssueDate>\n"
                    + "    <cbc:IssueTime>00:00:00</cbc:IssueTime>\n"
                    + "    <cbc:DocumentCurrencyCode>" + Cpe.getCOD_MONEDA() + "</cbc:DocumentCurrencyCode>\n";

            if (!Cpe.getNRO_OTR_COMPROBANTE().equals("")) {
                xmlCPE = xmlCPE + "\n"
                        + "            <cac:OrderReference>\n"
                        + "               <cbc:ID>" + Cpe.getNRO_OTR_COMPROBANTE() + "</cbc:ID>\n"
                        + "            </cac:OrderReference>\n";
            }
            if (!Cpe.getNRO_GUIA_REMISION().equals("")) {
                xmlCPE = xmlCPE + "\n"
                        + "            <cac:DespatchDocumentReference>\n"
                        + "		<cbc:ID>" + Cpe.getNRO_GUIA_REMISION() + "</cbc:ID>\n"
                        + "		<cbc:IssueDate>" + Cpe.getFECHA_GUIA_REMISION() + "</cbc:IssueDate>\n"
                        + "		<cbc:DocumentTypeCode listAgencyName='PE:SUNAT' listName='Tipo de Documento' listURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo01'>" + Cpe.getCOD_GUIA_REMISION() + "</cbc:DocumentTypeCode>\n"
                        + "            </cac:DespatchDocumentReference>\n";
            }
            if (Cpe.getFLG_REGU_ANTICIPO() == 1) {
                /*
                 catalogo12 sunat
                 02=FACTURA X ANTICIPO
                 03=BOLETA X ANTICIPO
                 */
                String tipodocRelacionado = "";
                if (Cpe.getCOD_TIPO_DOCUMENTO().equals("01")) {
                    tipodocRelacionado = "02";
                } else {
                    tipodocRelacionado = "03";
                }
                xmlCPE = xmlCPE + "<cac:AdditionalDocumentReference>\n"
                        + "<cbc:ID>" + Cpe.getNRO_COMPROBANTE_REF_ANT() + "</cbc:ID>\n"
                        + "<cbc:DocumentTypeCode listAgencyName='PE:SUNAT' listName='Documento Relacionado' listURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo12'>" + tipodocRelacionado + "</cbc:DocumentTypeCode>\n"
                        + "<cbc:DocumentStatusCode listName='Anticipo' listAgencyName='PE:SUNAT'>" + Cpe.getNRO_COMPROBANTE_REF_ANT() + "</cbc:DocumentStatusCode>\n"
                        + "<cac:IssuerParty>\n"
                        + "<cac:PartyIdentification>\n"
                        + "<cbc:ID schemeID='" + Cpe.getTIPO_DOCUMENTO_EMP_REGU_ANT() + "' schemeName='Documento de Identidad' schemeAgencyName='PE:SUNAT' schemeURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06'>" + Cpe.getNRO_DOCUMENTO_EMP_REGU_ANT() + "</cbc:ID>\n"
                        + "</cac:PartyIdentification>\n"
                        + "</cac:IssuerParty>\n"
                        + "</cac:AdditionalDocumentReference>";
            }
            xmlCPE = xmlCPE + "    <cac:DiscrepancyResponse>\n"
                    + "        <cbc:ReferenceID>" + Cpe.getNRO_DOCUMENTO_MODIFICA() + "</cbc:ReferenceID>\n"
                    + "        <cbc:ResponseCode>" + Cpe.getCOD_TIPO_MOTIVO() + "</cbc:ResponseCode>\n"
                    + "        <cbc:Description><![CDATA[" + Cpe.getDESCRIPCION_MOTIVO() + "]]></cbc:Description>\n"
                    + "    </cac:DiscrepancyResponse>\n"
                    + "    <cac:BillingReference>\n"
                    + "        <cac:InvoiceDocumentReference>\n"
                    + "            <cbc:ID>" + Cpe.getNRO_DOCUMENTO_MODIFICA() + "</cbc:ID>\n"
                    + "            <cbc:DocumentTypeCode>" + Cpe.getTIPO_COMPROBANTE_MODIFICA() + "</cbc:DocumentTypeCode>\n"
                    + "        </cac:InvoiceDocumentReference>\n"
                    + "    </cac:BillingReference>\n"
                    + "    <cac:Signature>\n"
                    + "        <cbc:ID>IDSignST</cbc:ID>\n"
                    + "        <cac:SignatoryParty>\n"
                    + "            <cac:PartyIdentification>\n"
                    + "                <cbc:ID>" + Cpe.getNRO_DOCUMENTO_EMPRESA() + "</cbc:ID>\n"
                    + "            </cac:PartyIdentification>\n"
                    + "            <cac:PartyName>\n"
                    + "                <cbc:Name><![CDATA[" + Cpe.getRAZON_SOCIAL_EMPRESA() + "]]></cbc:Name>\n"
                    + "            </cac:PartyName>\n"
                    + "        </cac:SignatoryParty>\n"
                    + "        <cac:DigitalSignatureAttachment>\n"
                    + "            <cac:ExternalReference>\n"
                    + "                <cbc:URI>#SignatureSP</cbc:URI>\n"
                    + "            </cac:ExternalReference>\n"
                    + "        </cac:DigitalSignatureAttachment>\n"
                    + "    </cac:Signature>\n"
                    + "    <cac:AccountingSupplierParty>\n"
                    + "        <cac:Party>\n"
                    + "            <cac:PartyIdentification>\n"
                    + "                <cbc:ID schemeID='" + Cpe.getTIPO_DOCUMENTO_EMPRESA() + "' schemeName='SUNAT:Identificador de Documento de Identidad' schemeAgencyName='PE:SUNAT' schemeURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06'>" + Cpe.getNRO_DOCUMENTO_EMPRESA() + "</cbc:ID>\n"
                    + "            </cac:PartyIdentification>\n"
                    + "            <cac:PartyName>\n"
                    + "                <cbc:Name><![CDATA[" + Cpe.getNOMBRE_COMERCIAL_EMPRESA() + "]]></cbc:Name>\n"
                    + "            </cac:PartyName>\n"
                    + "            <cac:PartyLegalEntity>\n"
                    + "<cbc:RegistrationName><![CDATA[" + Cpe.getRAZON_SOCIAL_EMPRESA() + "]]></cbc:RegistrationName>\n"
                    + "                <cac:RegistrationAddress>\n"
                    + "                    <cbc:AddressTypeCode>0001</cbc:AddressTypeCode>\n"
                    + "                </cac:RegistrationAddress>\n"
                    + "            </cac:PartyLegalEntity>\n"
                    + "        </cac:Party>\n"
                    + "    </cac:AccountingSupplierParty>\n"
                    + "    <cac:AccountingCustomerParty>\n"
                    + "        <cac:Party>\n"
                    + "            <cac:PartyIdentification>\n"
                    + "                <cbc:ID schemeID='" + Cpe.getTIPO_DOCUMENTO_CLIENTE() + "' schemeName='SUNAT:Identificador de Documento de Identidad' schemeAgencyName='PE:SUNAT' schemeURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06'>" + Cpe.getNRO_DOCUMENTO_CLIENTE() + "</cbc:ID>\n"
                    + "            </cac:PartyIdentification>\n"
                    + "            <cac:PartyLegalEntity>\n"
                    + "<cbc:RegistrationName><![CDATA[" + Cpe.getRAZON_SOCIAL_CLIENTE() + "]]></cbc:RegistrationName>\n"
                    + "            </cac:PartyLegalEntity>\n"
                    + "        </cac:Party>\n"
                    + "    </cac:AccountingCustomerParty>\n";

            if(Cpe.getTIPO_COMPROBANTE_MODIFICA().equals("01") && Cpe.getCOD_TIPO_MOTIVO().equals("13")) {
            	xmlCPE = xmlCPE + "<cac:PaymentTerms>\n"
                        +"<cbc:ID>FormaPago</cbc:ID>\n"
                        +"<cbc:PaymentMeansID>Credito</cbc:PaymentMeansID>\n"
                        +"<cbc:Amount currencyID='" + Cpe.getCOD_MONEDA() + "'>"+Cpe.getTOTAL()+"</cbc:Amount>\n"
                    +"</cac:PaymentTerms>\n";
            	for(Cuota c:Cpe.getLSCUOTAS()) {
                	xmlCPE = xmlCPE + "<cac:PaymentTerms>\n"
                            +"<cbc:ID>FormaPago</cbc:ID>\n"
                            +"<cbc:PaymentMeansID>"+c.getNombre()+"</cbc:PaymentMeansID>\n"
                            +"<cbc:Amount currencyID='" + Cpe.getCOD_MONEDA() + "'>"+c.getMonto_pago()+"</cbc:Amount>\n"
                            +"<cbc:PaymentDueDate>"+c.getFecha_cuota_str()+"</cbc:PaymentDueDate>\n"
                        +"</cac:PaymentTerms>\n";
            	}
            }
            if (Cpe.getTOTAL_DETRACCIONES() > 0) {
                xmlCPE = xmlCPE + "<cac:PaymentTerms>\n"
                        + "<cbc:Amount currencyID='PEN'>1.42</cbc:Amount>\n"
                        + "</cac:PaymentTerms>\n";
            }

            if (Cpe.getFLG_REGU_ANTICIPO() == 1) {
                xmlCPE = xmlCPE + "<cac:PrepaidPayment>\n"
                        + "<cbc:ID schemeName='Anticipo' schemeAgencyName='PE:SUNAT'>" + Cpe.getNRO_COMPROBANTE_REF_ANT() + "</cbc:ID>\n"
                        + "<cbc:PaidAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getMONTO_REGU_ANTICIPO() + "</cbc:PaidAmount>\n"
                        + "</cac:PrepaidPayment>\n";
            }

            xmlCPE = xmlCPE + "<cac:TaxTotal>\n"
                    + "<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_IGV() + "</cbc:TaxAmount>\n";
            
            if (Cpe.getTOTAL_GRAVADAS() > 0) {
                    xmlCPE = xmlCPE + "<cac:TaxSubtotal>\n"
                    + "			<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_GRAVADAS() + "</cbc:TaxableAmount>\n"
                    + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_IGV() + "</cbc:TaxAmount>\n"
                    + "			<cac:TaxCategory>\n"
                    + "				<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>S</cbc:ID>\n"
                    + "				<cac:TaxScheme>\n"
                    + "					<cbc:ID schemeID='UN/ECE 5153' schemeAgencyID='6'>1000</cbc:ID>\n"
                    + "					<cbc:Name>IGV</cbc:Name>\n"
                    + "					<cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>\n"
                    + "				</cac:TaxScheme>\n"
                    + "			</cac:TaxCategory>\n"
                    + "		</cac:TaxSubtotal>";
            }

            if (Cpe.getTOTAL_ISC() > 0) {
                xmlCPE = xmlCPE + "\n"
                        + "                <cac:TaxSubtotal>\n"
                        + "			<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_ISC() + "</cbc:TaxableAmount>\n"
                        + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_ISC() + "</cbc:TaxAmount>\n"
                        + "			<cac:TaxCategory>\n"
                        + "				<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>S</cbc:ID>\n"
                        + "				<cac:TaxScheme>\n"
                        + "					<cbc:ID schemeID='UN/ECE 5153' schemeAgencyID='6'>2000</cbc:ID>\n"
                        + "					<cbc:Name>ISC</cbc:Name>\n"
                        + "					<cbc:TaxTypeCode>EXC</cbc:TaxTypeCode>\n"
                        + "				</cac:TaxScheme>\n"
                        + "			</cac:TaxCategory>\n"
                        + "		</cac:TaxSubtotal>";
            }
            //CAMPO NUEVO
            if (Cpe.getTOTAL_EXPORTACION() > 0) {
                xmlCPE = xmlCPE + "\n"
                        + "                <cac:TaxSubtotal>\n"
                        + "			<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_EXPORTACION() + "</cbc:TaxableAmount>\n"
                        + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0.00</cbc:TaxAmount>\n"
                        + "			<cac:TaxCategory>\n"
                        + "				<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>G</cbc:ID>\n"
                        + "				<cac:TaxScheme>\n"
                        + "					<cbc:ID schemeID='UN/ECE 5153' schemeAgencyID='6'>9995</cbc:ID>\n"
                        + "					<cbc:Name>EXP</cbc:Name>\n"
                        + "					<cbc:TaxTypeCode>FRE</cbc:TaxTypeCode>\n"
                        + "				</cac:TaxScheme>\n"
                        + "			</cac:TaxCategory>\n"
                        + "		</cac:TaxSubtotal>";
            }
            if (Cpe.getTOTAL_GRATUITAS() > 0) {
                xmlCPE = xmlCPE + "\n"
                        + "                <cac:TaxSubtotal>\n"
                        + "			<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_GRATUITAS() + "</cbc:TaxableAmount>\n"
                        + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0.00</cbc:TaxAmount>\n"
                        + "			<cac:TaxCategory>\n"
                        + "				<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>Z</cbc:ID>\n"
                        + "				<cac:TaxScheme>\n"
                        + "					<cbc:ID schemeID='UN/ECE 5153' schemeAgencyID='6'>9996</cbc:ID>\n"
                        + "					<cbc:Name>GRA</cbc:Name>\n"
                        + "					<cbc:TaxTypeCode>FRE</cbc:TaxTypeCode>\n"
                        + "				</cac:TaxScheme>\n"
                        + "			</cac:TaxCategory>\n"
                        + "		</cac:TaxSubtotal>";
            }
            if (Cpe.getTOTAL_EXONERADAS() > 0) {
                xmlCPE = xmlCPE + "\n"
                        + "                <cac:TaxSubtotal>\n"
                        + "			<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_EXONERADAS() + "</cbc:TaxableAmount>\n"
                        + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0.00</cbc:TaxAmount>\n"
                        + "			<cac:TaxCategory>\n"
                        + "				<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>E</cbc:ID>\n"
                        + "				<cac:TaxScheme>\n"
                        + "					<cbc:ID schemeID='UN/ECE 5153' schemeAgencyID='6'>9997</cbc:ID>\n"
                        + "					<cbc:Name>EXO</cbc:Name>\n"
                        + "					<cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>\n"
                        + "				</cac:TaxScheme>\n"
                        + "			</cac:TaxCategory>\n"
                        + "		</cac:TaxSubtotal>";
            }
            if (Cpe.getTOTAL_INAFECTA() > 0) {
                xmlCPE = xmlCPE + "\n"
                        + "                <cac:TaxSubtotal>\n"
                        + "			<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_INAFECTA() + "</cbc:TaxableAmount>\n"
                        + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0.00</cbc:TaxAmount>\n"
                        + "			<cac:TaxCategory>\n"
                        + "				<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>O</cbc:ID>\n"
                        + "				<cac:TaxScheme>\n"
                        + "					<cbc:ID schemeID='UN/ECE 5153' schemeAgencyID='6'>9998</cbc:ID>\n"
                        + "					<cbc:Name>INA</cbc:Name>\n"
                        + "					<cbc:TaxTypeCode>FRE</cbc:TaxTypeCode>\n"
                        + "				</cac:TaxScheme>\n"
                        + "			</cac:TaxCategory>\n"
                        + "		</cac:TaxSubtotal>";
            }
            if (Cpe.getTOTAL_OTR_IMP() > 0) {
                xmlCPE = xmlCPE + "\n"
                        + "                <cac:TaxSubtotal>\n"
                        + "			<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_OTR_IMP() + "</cbc:TaxableAmount>\n"
                        + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_OTR_IMP() + "</cbc:TaxAmount>\n"
                        + "			<cac:TaxCategory>\n"
                        + "				<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>S</cbc:ID>\n"
                        + "				<cac:TaxScheme>\n"
                        + "					<cbc:ID schemeID='UN/ECE 5153' schemeAgencyID='6'>9999</cbc:ID>\n"
                        + "					<cbc:Name>OTR</cbc:Name>\n"
                        + "					<cbc:TaxTypeCode>OTH</cbc:TaxTypeCode>\n"
                        + "				</cac:TaxScheme>\n"
                        + "			</cac:TaxCategory>\n"
                        + "		</cac:TaxSubtotal>";
            }

            xmlCPE = xmlCPE + "\n"
                    + "       </cac:TaxTotal>\n"
                    + "	<cac:LegalMonetaryTotal>\n";
            if (Cpe.getFLG_REGU_ANTICIPO() == 1) {
                xmlCPE = xmlCPE + "<cbc:PrepaidAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getMONTO_REGU_ANTICIPO() + "</cbc:PrepaidAmount>\n"
                        + "           <cbc:PayableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0.00</cbc:PayableAmount>\n";
            } else {
                xmlCPE = xmlCPE + "<cbc:PayableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL() + "</cbc:PayableAmount>\n";
            }
            xmlCPE = xmlCPE + "	</cac:LegalMonetaryTotal>\n";

            for (int i = 0; i < lstCpe_Detalle.size(); i++) {
                _Cpe_DetalleBean Cpe_Detalle = lstCpe_Detalle.get(i);
                if (Cpe_Detalle.getCOD_TIPO_OPERACION().equals("10")) {
                    xmlCPE = xmlCPE + "<cac:CreditNoteLine>\n"
                            + "        <cbc:ID>" + Cpe_Detalle.getITEM() + "</cbc:ID>\n"
                            + "<cbc:CreditedQuantity unitCode='" + Cpe_Detalle.getUNIDAD_MEDIDA() + "'>" + Cpe_Detalle.getCANTIDAD() + "</cbc:CreditedQuantity>\n"
                            + "<cbc:LineExtensionAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:LineExtensionAmount>\n"
                            + "        <cac:PricingReference>\n"
                            + "            <cac:AlternativeConditionPrice>\n"
                            + "<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO() + "</cbc:PriceAmount>\n"
                            + "                <cbc:PriceTypeCode>" + Cpe_Detalle.getPRECIO_TIPO_CODIGO() + "</cbc:PriceTypeCode>\n"
                            + "            </cac:AlternativeConditionPrice>\n"
                            + "        </cac:PricingReference>\n"
                            + "        <cac:TaxTotal>\n"
                            + "<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIGV() + "</cbc:TaxAmount>\n"
                            + "            <cac:TaxSubtotal>\n"
                            + "<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:TaxableAmount>\n"
                            + "<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIGV() + "</cbc:TaxAmount>\n"
                            + "                <cac:TaxCategory>\n"
                            + "                 <cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>E</cbc:ID>\n"
                            + "                    <cbc:Percent>" + Cpe.getPOR_IGV() + "</cbc:Percent>\n"
                            + "<cbc:TaxExemptionReasonCode>" + Cpe_Detalle.getCOD_TIPO_OPERACION() + "</cbc:TaxExemptionReasonCode>\n"
                            + "                    <cac:TaxScheme>\n"
                            + "                        <cbc:ID>1000</cbc:ID>\n"
                            + "                        <cbc:Name>IGV</cbc:Name>\n"
                            + "                        <cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>\n"
                            + "                    </cac:TaxScheme>\n"
                            + "                </cac:TaxCategory>\n"
                            + "            </cac:TaxSubtotal>\n"
                            + "        </cac:TaxTotal>\n"
                            + "        <cac:Item>\n"
                            + "<cbc:Description><![CDATA[" + Cpe_Detalle.getDESCRIPCION() + "]]></cbc:Description>\n"
                            + "            <cac:SellersItemIdentification>\n"
                            + "                <cbc:ID><![CDATA[" + Cpe_Detalle.getCODIGO() + "]]></cbc:ID>\n"
                            + "            </cac:SellersItemIdentification>\n";
                            if (!Cpe_Detalle.getCOD_SUNAT().equals("")) {
                                xmlCPE = xmlCPE + "<cac:CommodityClassification>\n"
			                    + "<cbc:ItemClassificationCode listID='UNSPSC' listAgencyName='GS1 US' \n"
			                    + "listName='Item Classification'>" + Cpe_Detalle.getCOD_SUNAT() + "</cbc:ItemClassificationCode>\n"
			                    + "</cac:CommodityClassification>";
                            }
                             xmlCPE = xmlCPE + "</cac:Item>\n"
                            + "        <cac:Price>\n"
                            + "<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO() + "</cbc:PriceAmount>\n"
                            + "        </cac:Price>\n"
                            + "    </cac:CreditNoteLine>";
                }
                if (Cpe_Detalle.getCOD_TIPO_OPERACION().equals("20")) {
                    xmlCPE = xmlCPE + "<cac:CreditNoteLine>\n"
                            + "        <cbc:ID>" + Cpe_Detalle.getITEM() + "</cbc:ID>\n"
                            + "<cbc:CreditedQuantity unitCode='" + Cpe_Detalle.getUNIDAD_MEDIDA() + "'>" + Cpe_Detalle.getCANTIDAD() + "</cbc:CreditedQuantity>\n"
                            + "<cbc:LineExtensionAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:LineExtensionAmount>\n"
                            + "        <cac:PricingReference>\n"
                            + "            <cac:AlternativeConditionPrice>\n"
                            + "<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO() + "</cbc:PriceAmount>\n"
                            + "                <cbc:PriceTypeCode>" + Cpe_Detalle.getPRECIO_TIPO_CODIGO() + "</cbc:PriceTypeCode>\n"
                            + "            </cac:AlternativeConditionPrice>\n"
                            + "        </cac:PricingReference>\n"
                            + "        <cac:TaxTotal>\n"
                            + "<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                            + "            <cac:TaxSubtotal>\n"
                            + "<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:TaxableAmount>\n"
                            + "<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                            + "                <cac:TaxCategory>\n"
                            + "                    <cbc:Percent>0</cbc:Percent>\n"
                            + "<cbc:TaxExemptionReasonCode>" + Cpe_Detalle.getCOD_TIPO_OPERACION() + "</cbc:TaxExemptionReasonCode>\n"
                            + "                    <cac:TaxScheme>\n"
                            + "                        <cbc:ID>9997</cbc:ID>\n"
                            + "                        <cbc:Name>EXO</cbc:Name>\n"
                            + "                        <cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>\n"
                            + "                    </cac:TaxScheme>\n"
                            + "                </cac:TaxCategory>\n"
                            + "            </cac:TaxSubtotal>\n"
                            + "        </cac:TaxTotal>\n"
                            + "        <cac:Item>\n"
                            + "<cbc:Description><![CDATA[" + Cpe_Detalle.getDESCRIPCION() + "]]></cbc:Description>\n"
                            + "            <cac:SellersItemIdentification>\n"
                            + "                <cbc:ID><![CDATA[" + Cpe_Detalle.getCODIGO() + "]]></cbc:ID>\n"
                            + "            </cac:SellersItemIdentification>\n";
                            if (!Cpe_Detalle.getCOD_SUNAT().equals("")) {
                                xmlCPE = xmlCPE + "<cac:CommodityClassification>\n"
			                    + "<cbc:ItemClassificationCode listID='UNSPSC' listAgencyName='GS1 US' \n"
			                    + "listName='Item Classification'>" + Cpe_Detalle.getCOD_SUNAT() + "</cbc:ItemClassificationCode>\n"
			                    + "</cac:CommodityClassification>";
                            }
                             xmlCPE = xmlCPE + "</cac:Item>\n"
                            + "        <cac:Price>\n"
                            + "<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO() + "</cbc:PriceAmount>\n"
                            + "        </cac:Price>\n"
                            + "    </cac:CreditNoteLine>";
                }
                 if (Cpe_Detalle.getCOD_TIPO_OPERACION().equals("30")) {
                    xmlCPE = xmlCPE + "<cac:CreditNoteLine>\n"
                            + "        <cbc:ID>" + Cpe_Detalle.getITEM() + "</cbc:ID>\n"
                            + "<cbc:CreditedQuantity unitCode='" + Cpe_Detalle.getUNIDAD_MEDIDA() + "'>" + Cpe_Detalle.getCANTIDAD() + "</cbc:CreditedQuantity>\n"
                            + "<cbc:LineExtensionAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:LineExtensionAmount>\n"
                            + "        <cac:PricingReference>\n"
                            + "            <cac:AlternativeConditionPrice>\n"
                            + "                <cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO() + "</cbc:PriceAmount>\n"
                            + "                <cbc:PriceTypeCode listName='Tipo de Precio' listAgencyName='PE:SUNAT' listURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo16'>01</cbc:PriceTypeCode>\n"
                            + "            </cac:AlternativeConditionPrice>\n"
                            + "        </cac:PricingReference>\n"
                            + "        <cac:TaxTotal>\n"
                            + "<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0.00</cbc:TaxAmount>\n"
                            + "            <cac:TaxSubtotal>\n"
                            + "<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:TaxableAmount>\n"
                            + "<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0.00</cbc:TaxAmount>\n"
                            + "                <cac:TaxCategory>\n"
                            + "			<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>O</cbc:ID>\n"
                            + "                    <cbc:Percent>0</cbc:Percent>\n"
                            + "                    <cbc:TaxExemptionReasonCode listAgencyName='PE:SUNAT' listName='SUNAT:Codigo de Tipo de Afectación del IGV' listURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo07'>" + Cpe_Detalle.getCOD_TIPO_OPERACION() + "</cbc:TaxExemptionReasonCode>\n"
                            + "                    <cac:TaxScheme>\n"
                            + "                        <cbc:ID schemeID='UN/ECE 5153' schemeName='Tax Scheme Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>9998</cbc:ID>\n"
                            + "                        <cbc:Name>INA</cbc:Name>\n"
                            + "                        <cbc:TaxTypeCode>FRE</cbc:TaxTypeCode>\n"
                            + "                    </cac:TaxScheme>\n"
                            + "                </cac:TaxCategory>\n"
                            + "            </cac:TaxSubtotal>\n"
                            + "        </cac:TaxTotal>\n"
                            + "        <cac:Item>\n"
                            + "<cbc:Description><![CDATA[" + Cpe_Detalle.getDESCRIPCION() + "]]></cbc:Description>\n"
                            + "            <cac:SellersItemIdentification>\n"
                            + "                <cbc:ID><![CDATA[" + Cpe_Detalle.getCODIGO() + "]]></cbc:ID>\n"
                            + "            </cac:SellersItemIdentification>\n";
                            if (!Cpe_Detalle.getCOD_SUNAT().equals("")) {
                                xmlCPE = xmlCPE + "<cac:CommodityClassification>\n"
			                    + "<cbc:ItemClassificationCode listID='UNSPSC' listAgencyName='GS1 US' \n"
			                    + "listName='Item Classification'>" + Cpe_Detalle.getCOD_SUNAT() + "</cbc:ItemClassificationCode>\n"
			                    + "</cac:CommodityClassification>";
                            }
                             xmlCPE = xmlCPE + "</cac:Item>\n"
                            + "        <cac:Price>\n"
                            + "<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO_SIN_IMPUESTO() + "</cbc:PriceAmount>\n"
                            + "        </cac:Price>\n"
                            + "    </cac:CreditNoteLine>";
                }
                 if (Cpe_Detalle.getCOD_TIPO_OPERACION().equals("31")) {
                    xmlCPE = xmlCPE + "<cac:CreditNoteLine>\n"
                            + "        <cbc:ID>" + Cpe_Detalle.getITEM() + "</cbc:ID>\n"
                            + "<cbc:CreditedQuantity unitCode='" + Cpe_Detalle.getUNIDAD_MEDIDA() + "'>" + Cpe_Detalle.getCANTIDAD() + "</cbc:CreditedQuantity>\n"
//                            + "<cbc:LineExtensionAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:LineExtensionAmount>\n"
                            + "<cbc:LineExtensionAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + 0.0 + "</cbc:LineExtensionAmount>\n"
                            + "        <cac:PricingReference>\n"
                            + "            <cac:AlternativeConditionPrice>\n"
                            + "<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + 0.0 + "</cbc:PriceAmount>\n"
//                                    + "<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO() + "</cbc:PriceAmount>\n"
                            + "                <cbc:PriceTypeCode>02</cbc:PriceTypeCode>\n"
                            + "            </cac:AlternativeConditionPrice>\n"
                            + "        </cac:PricingReference>\n"
                            + "        <cac:TaxTotal>\n"
                            + "<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                            + "            <cac:TaxSubtotal>\n"
//                            + "<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:TaxableAmount>\n"
							+ "<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + 0.0 + "</cbc:TaxableAmount>\n"
                            + "<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                            + "                <cac:TaxCategory>\n"
                            + "			<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>Z</cbc:ID>\n"
                            + "                    <cbc:Percent>0</cbc:Percent>\n"
                            + "<cbc:TaxExemptionReasonCode>" + Cpe_Detalle.getCOD_TIPO_OPERACION() + "</cbc:TaxExemptionReasonCode>\n"
                            + "                    <cac:TaxScheme>\n"
                            + "                        <cbc:ID>9996</cbc:ID>\n"
                            + "                        <cbc:Name>GRA</cbc:Name>\n"
                            + "                        <cbc:TaxTypeCode>FRE</cbc:TaxTypeCode>\n"
                            + "                    </cac:TaxScheme>\n"
                            + "                </cac:TaxCategory>\n"
                            + "            </cac:TaxSubtotal>\n"
                            + "        </cac:TaxTotal>\n"
                            + "        <cac:Item>\n"
                            + "<cbc:Description><![CDATA[" + Cpe_Detalle.getDESCRIPCION() + "]]></cbc:Description>\n"
                            + "            <cac:SellersItemIdentification>\n"
                            + "                <cbc:ID><![CDATA[" + Cpe_Detalle.getCODIGO() + "]]></cbc:ID>\n"
                            + "            </cac:SellersItemIdentification>\n";
                            if (!Cpe_Detalle.getCOD_SUNAT().equals("")) {
                                xmlCPE = xmlCPE + "<cac:CommodityClassification>\n"
			                    + "<cbc:ItemClassificationCode listID='UNSPSC' listAgencyName='GS1 US' \n"
			                    + "listName='Item Classification'>" + Cpe_Detalle.getCOD_SUNAT() + "</cbc:ItemClassificationCode>\n"
			                    + "</cac:CommodityClassification>";
                            }
                             xmlCPE = xmlCPE + "</cac:Item>\n"
                            + "        <cac:Price>\n"
                            + "<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO() + "</cbc:PriceAmount>\n"
                            + "        </cac:Price>\n"
                            + "    </cac:CreditNoteLine>";
                }
                if (Cpe_Detalle.getCOD_TIPO_OPERACION().equals("40")) {
                    xmlCPE = xmlCPE + "<cac:CreditNoteLine>\n"
                            + "        <cbc:ID>" + Cpe_Detalle.getITEM() + "</cbc:ID>\n"
                            + "<cbc:CreditedQuantity unitCode='" + Cpe_Detalle.getUNIDAD_MEDIDA() + "'>" + Cpe_Detalle.getCANTIDAD() + "</cbc:CreditedQuantity>\n"
                            + "<cbc:LineExtensionAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:LineExtensionAmount>\n"
                            + "        <cac:PricingReference>\n"
                            + "            <cac:AlternativeConditionPrice>\n"
                            + "<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO() + "</cbc:PriceAmount>\n"
                            + "                <cbc:PriceTypeCode>" + Cpe_Detalle.getPRECIO_TIPO_CODIGO() + "</cbc:PriceTypeCode>\n"
                            + "            </cac:AlternativeConditionPrice>\n"
                            + "        </cac:PricingReference>\n"
                            + "        <cac:TaxTotal>\n"
                            + "<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                            + "            <cac:TaxSubtotal>\n"
                            + "<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:TaxableAmount>\n"
                            + "<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                            + "                <cac:TaxCategory>\n"
                            + "                    <cbc:Percent>0</cbc:Percent>\n"
                            + "<cbc:TaxExemptionReasonCode>" + Cpe_Detalle.getCOD_TIPO_OPERACION() + "</cbc:TaxExemptionReasonCode>\n"
                            + "                    <cac:TaxScheme>\n"
                            + "                        <cbc:ID>9995</cbc:ID>\n"
                            + "                        <cbc:Name>EXP</cbc:Name>\n"
                            + "                        <cbc:TaxTypeCode>FRE</cbc:TaxTypeCode>\n"
                            + "                    </cac:TaxScheme>\n"
                            + "                </cac:TaxCategory>\n"
                            + "            </cac:TaxSubtotal>\n"
                            + "        </cac:TaxTotal>\n"
                            + "        <cac:Item>\n"
                            + "<cbc:Description><![CDATA[" + Cpe_Detalle.getDESCRIPCION() + "]]></cbc:Description>\n"
                            + "            <cac:SellersItemIdentification>\n"
                            + "                <cbc:ID><![CDATA[" + Cpe_Detalle.getCODIGO() + "]]></cbc:ID>\n"
                            + "            </cac:SellersItemIdentification>\n";
                            if (!Cpe_Detalle.getCOD_SUNAT().equals("")) {
                                xmlCPE = xmlCPE + "<cac:CommodityClassification>\n"
			                    + "<cbc:ItemClassificationCode listID='UNSPSC' listAgencyName='GS1 US' \n"
			                    + "listName='Item Classification'>" + Cpe_Detalle.getCOD_SUNAT() + "</cbc:ItemClassificationCode>\n"
			                    + "</cac:CommodityClassification>";
                            }
                             xmlCPE = xmlCPE + "</cac:Item>\n"
                            + "        <cac:Price>\n"
                            + "<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO() + "</cbc:PriceAmount>\n"
                            + "        </cac:Price>\n"
                            + "    </cac:CreditNoteLine>";
                }
            }

            xmlCPE = xmlCPE + "\n</CreditNote>";

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlCPE)));
            // Use a Transformer for output
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(RutaXml + ".XML"));
            transformer.transform(source, result);

            System.out.println("\nXML DOM Created Successfully..");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    public int GenerarXML_ND(String RutaXml, _CpeBean Cpe, List<_Cpe_DetalleBean> lstCpe_Detalle) {
        try {
            String xmlCPE = "";
            xmlCPE = "<?xml version='1.0' encoding='UTF-8'?>\n"
                    + "<DebitNote xmlns='urn:oasis:names:specification:ubl:schema:xsd:DebitNote-2' xmlns:cac='urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2' xmlns:cbc='urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2' xmlns:ccts='urn:un:unece:uncefact:documentation:2' xmlns:ds='http://www.w3.org/2000/09/xmldsig#' xmlns:ext='urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2' xmlns:qdt='urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2' xmlns:sac='urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1' xmlns:udt='urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>\n"
                    + "    <ext:UBLExtensions>\n"
                    + "        <ext:UBLExtension>\n"
                    + "            <ext:ExtensionContent>\n"
                    + "            </ext:ExtensionContent>\n"
                    + "        </ext:UBLExtension>\n"
                    + "    </ext:UBLExtensions>\n"
                    + "    <cbc:UBLVersionID>2.1</cbc:UBLVersionID>\n"
                    + "    <cbc:CustomizationID>2.0</cbc:CustomizationID>\n"
                    + "    <cbc:ProfileID>" + Cpe.getTIPO_OPERACION() + "</cbc:ProfileID>\n"
                    + "    <cbc:ID>" + Cpe.getNRO_COMPROBANTE() + "</cbc:ID>\n"
                    + "    <cbc:IssueDate>" + Cpe.getFECHA_DOCUMENTO() + "</cbc:IssueDate>\n"
                    + "    <cbc:IssueTime>00:00:00</cbc:IssueTime>\n"
                    + "    <cbc:DocumentCurrencyCode>" + Cpe.getCOD_MONEDA() + "</cbc:DocumentCurrencyCode>\n";

            if (!Cpe.getNRO_OTR_COMPROBANTE().equals("")) {
                xmlCPE = xmlCPE + "\n"
                        + "            <cac:OrderReference>\n"
                        + "               <cbc:ID>" + Cpe.getNRO_OTR_COMPROBANTE() + "</cbc:ID>\n"
                        + "            </cac:OrderReference>\n";
            }
            if (!Cpe.getNRO_GUIA_REMISION().equals("")) {
                xmlCPE = xmlCPE + "\n"
                        + "            <cac:DespatchDocumentReference>\n"
                        + "		<cbc:ID>" + Cpe.getNRO_GUIA_REMISION() + "</cbc:ID>\n"
                        + "		<cbc:IssueDate>" + Cpe.getFECHA_GUIA_REMISION() + "</cbc:IssueDate>\n"
                        + "		<cbc:DocumentTypeCode listAgencyName='PE:SUNAT' listName='Tipo de Documento' listURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo01'>" + Cpe.getCOD_GUIA_REMISION() + "</cbc:DocumentTypeCode>\n"
                        + "            </cac:DespatchDocumentReference>\n";
            }
            if (Cpe.getFLG_REGU_ANTICIPO() == 1) {
                /*
                 catalogo12 sunat
                 02=FACTURA X ANTICIPO
                 03=BOLETA X ANTICIPO
                 */
                String tipodocRelacionado = "";
                if (Cpe.getCOD_TIPO_DOCUMENTO().equals("01")) {
                    tipodocRelacionado = "02";
                } else {
                    tipodocRelacionado = "03";
                }
                xmlCPE = xmlCPE + "<cac:AdditionalDocumentReference>\n"
                        + "<cbc:ID>" + Cpe.getNRO_COMPROBANTE_REF_ANT() + "</cbc:ID>\n"
                        + "<cbc:DocumentTypeCode listAgencyName='PE:SUNAT' listName='Documento Relacionado' listURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo12'>" + tipodocRelacionado + "</cbc:DocumentTypeCode>\n"
                        + "<cbc:DocumentStatusCode listName='Anticipo' listAgencyName='PE:SUNAT'>" + Cpe.getNRO_COMPROBANTE_REF_ANT() + "</cbc:DocumentStatusCode>\n"
                        + "<cac:IssuerParty>\n"
                        + "<cac:PartyIdentification>\n"
                        + "<cbc:ID schemeID='" + Cpe.getTIPO_DOCUMENTO_EMP_REGU_ANT() + "' schemeName='Documento de Identidad' schemeAgencyName='PE:SUNAT' schemeURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06'>" + Cpe.getNRO_DOCUMENTO_EMP_REGU_ANT() + "</cbc:ID>\n"
                        + "</cac:PartyIdentification>\n"
                        + "</cac:IssuerParty>\n"
                        + "</cac:AdditionalDocumentReference>";
            }
            xmlCPE = xmlCPE + "<cac:DiscrepancyResponse>\n"
                    + "        <cbc:ReferenceID>" + Cpe.getNRO_DOCUMENTO_MODIFICA() + "</cbc:ReferenceID>\n"
                    + "        <cbc:ResponseCode>" + Cpe.getCOD_TIPO_MOTIVO() + "</cbc:ResponseCode>\n"
                    + "        <cbc:Description><![CDATA[" + Cpe.getDESCRIPCION_MOTIVO() + "]]></cbc:Description>\n"
                    + "    </cac:DiscrepancyResponse>\n"
                    + "    <cac:BillingReference>\n"
                    + "        <cac:InvoiceDocumentReference>\n"
                    + "            <cbc:ID>" + Cpe.getNRO_DOCUMENTO_MODIFICA() + "</cbc:ID>\n"
                    + "            <cbc:DocumentTypeCode>" + Cpe.getTIPO_COMPROBANTE_MODIFICA() + "</cbc:DocumentTypeCode>\n"
                    + "        </cac:InvoiceDocumentReference>\n"
                    + "    </cac:BillingReference>\n"
                    + "    <cac:Signature>\n"
                    + "        <cbc:ID>IDSignST</cbc:ID>\n"
                    + "        <cac:SignatoryParty>\n"
                    + "            <cac:PartyIdentification>\n"
                    + "                <cbc:ID>" + Cpe.getNRO_DOCUMENTO_EMPRESA() + "</cbc:ID>\n"
                    + "            </cac:PartyIdentification>\n"
                    + "            <cac:PartyName>\n"
                    + "                <cbc:Name><![CDATA[" + Cpe.getRAZON_SOCIAL_EMPRESA() + "]]></cbc:Name>\n"
                    + "            </cac:PartyName>\n"
                    + "        </cac:SignatoryParty>\n"
                    + "        <cac:DigitalSignatureAttachment>\n"
                    + "            <cac:ExternalReference>\n"
                    + "                <cbc:URI>#SignatureSP</cbc:URI>\n"
                    + "            </cac:ExternalReference>\n"
                    + "        </cac:DigitalSignatureAttachment>\n"
                    + "    </cac:Signature>\n"
                    + "    <cac:AccountingSupplierParty>\n"
                    + "        <cac:Party>\n"
                    + "            <cac:PartyIdentification>\n"
                    + "                <cbc:ID schemeID='" + Cpe.getTIPO_DOCUMENTO_EMPRESA() + "' schemeName='SUNAT:Identificador de Documento de Identidad' schemeAgencyName='PE:SUNAT' schemeURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06'>" + Cpe.getNRO_DOCUMENTO_EMPRESA() + "</cbc:ID>\n"
                    + "            </cac:PartyIdentification>\n"
                    + "            <cac:PartyName>\n"
                    + "                <cbc:Name><![CDATA[" + Cpe.getNOMBRE_COMERCIAL_EMPRESA() + "]]></cbc:Name>\n"
                    + "            </cac:PartyName>\n"
                    + "            <cac:PartyLegalEntity>\n"
                    + "                <cbc:RegistrationName><![CDATA[" + Cpe.getRAZON_SOCIAL_EMPRESA() + "]]></cbc:RegistrationName>\n"
                    + "                <cac:RegistrationAddress>\n"
                    + "                    <cbc:AddressTypeCode>0001</cbc:AddressTypeCode>\n"
                    + "                </cac:RegistrationAddress>\n"
                    + "            </cac:PartyLegalEntity>\n"
                    + "        </cac:Party>\n"
                    + "    </cac:AccountingSupplierParty>\n"
                    + "    <cac:AccountingCustomerParty>\n"
                    + "        <cac:Party>\n"
                    + "            <cac:PartyIdentification>\n"
                    + "                <cbc:ID schemeID='" + Cpe.getTIPO_DOCUMENTO_CLIENTE() + "' schemeName='SUNAT:Identificador de Documento de Identidad' schemeAgencyName='PE:SUNAT' schemeURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06'>" + Cpe.getNRO_DOCUMENTO_CLIENTE() + "</cbc:ID>\n"
                    + "            </cac:PartyIdentification>\n"
                    + "            <cac:PartyLegalEntity>\n"
                    + "<cbc:RegistrationName><![CDATA[" + Cpe.getRAZON_SOCIAL_CLIENTE() + "]]></cbc:RegistrationName>\n"
                    + "            </cac:PartyLegalEntity>\n"
                    + "        </cac:Party>\n"
                    + "    </cac:AccountingCustomerParty>\n";

            if (Cpe.getTOTAL_DETRACCIONES() > 0) {
                xmlCPE = xmlCPE + "<cac:PaymentTerms>\n"
                        + "<cbc:Amount currencyID='PEN'>1.42</cbc:Amount>\n"
                        + "</cac:PaymentTerms>";
            }

            if (Cpe.getFLG_REGU_ANTICIPO() == 1) {
                xmlCPE = xmlCPE + "<cac:PrepaidPayment>\n"
                        + "<cbc:ID schemeName='Anticipo' schemeAgencyName='PE:SUNAT'>" + Cpe.getNRO_COMPROBANTE_REF_ANT() + "</cbc:ID>\n"
                        + "<cbc:PaidAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getMONTO_REGU_ANTICIPO() + "</cbc:PaidAmount>\n"
                        + "</cac:PrepaidPayment>\n";
            }

            xmlCPE = xmlCPE + "<cac:TaxTotal>\n"
                    + "		<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_IGV() + "</cbc:TaxAmount>\n";
            
            if (Cpe.getTOTAL_EXPORTACION() == 0) {
                    xmlCPE = xmlCPE + "<cac:TaxSubtotal>\n"
                    + "			<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_GRAVADAS() + "</cbc:TaxableAmount>\n"
                    + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_IGV() + "</cbc:TaxAmount>\n"
                    + "			<cac:TaxCategory>\n"
                    + "				<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>S</cbc:ID>\n"
                    + "				<cac:TaxScheme>\n"
                    + "					<cbc:ID schemeID='UN/ECE 5153' schemeAgencyID='6'>1000</cbc:ID>\n"
                    + "					<cbc:Name>IGV</cbc:Name>\n"
                    + "					<cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>\n"
                    + "				</cac:TaxScheme>\n"
                    + "			</cac:TaxCategory>\n"
                    + "		</cac:TaxSubtotal>";
            }
            
            if (Cpe.getTOTAL_ISC() > 0) {
                xmlCPE = xmlCPE + "\n"
                        + "                <cac:TaxSubtotal>\n"
                        + "			<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_ISC() + "</cbc:TaxableAmount>\n"
                        + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_ISC() + "</cbc:TaxAmount>\n"
                        + "			<cac:TaxCategory>\n"
                        + "				<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>S</cbc:ID>\n"
                        + "				<cac:TaxScheme>\n"
                        + "					<cbc:ID schemeID='UN/ECE 5153' schemeAgencyID='6'>2000</cbc:ID>\n"
                        + "					<cbc:Name>ISC</cbc:Name>\n"
                        + "					<cbc:TaxTypeCode>EXC</cbc:TaxTypeCode>\n"
                        + "				</cac:TaxScheme>\n"
                        + "			</cac:TaxCategory>\n"
                        + "		</cac:TaxSubtotal>";
            }
            //CAMPO NUEVO
            if (Cpe.getTOTAL_EXPORTACION() > 0) {
                xmlCPE = xmlCPE + "\n"
                        + "                <cac:TaxSubtotal>\n"
                        + "			<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_EXPORTACION() + "</cbc:TaxableAmount>\n"
                        + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0.00</cbc:TaxAmount>\n"
                        + "			<cac:TaxCategory>\n"
                        + "				<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>G</cbc:ID>\n"
                        + "				<cac:TaxScheme>\n"
                        + "					<cbc:ID schemeID='UN/ECE 5153' schemeAgencyID='6'>9995</cbc:ID>\n"
                        + "					<cbc:Name>EXP</cbc:Name>\n"
                        + "					<cbc:TaxTypeCode>FRE</cbc:TaxTypeCode>\n"
                        + "				</cac:TaxScheme>\n"
                        + "			</cac:TaxCategory>\n"
                        + "		</cac:TaxSubtotal>";
            }
            if (Cpe.getTOTAL_GRATUITAS() > 0) {
                xmlCPE = xmlCPE + "\n"
                        + "                <cac:TaxSubtotal>\n"
                        + "			<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_GRATUITAS() + "</cbc:TaxableAmount>\n"
                        + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0.00</cbc:TaxAmount>\n"
                        + "			<cac:TaxCategory>\n"
                        + "				<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>Z</cbc:ID>\n"
                        + "				<cac:TaxScheme>\n"
                        + "					<cbc:ID schemeID='UN/ECE 5153' schemeAgencyID='6'>9996</cbc:ID>\n"
                        + "					<cbc:Name>GRA</cbc:Name>\n"
                        + "					<cbc:TaxTypeCode>FRE</cbc:TaxTypeCode>\n"
                        + "				</cac:TaxScheme>\n"
                        + "			</cac:TaxCategory>\n"
                        + "		</cac:TaxSubtotal>";
            }
            if (Cpe.getTOTAL_EXONERADAS() > 0) {
                xmlCPE = xmlCPE + "\n"
                        + "                <cac:TaxSubtotal>\n"
                        + "			<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_EXONERADAS() + "</cbc:TaxableAmount>\n"
                        + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0.00</cbc:TaxAmount>\n"
                        + "			<cac:TaxCategory>\n"
                        + "				<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>E</cbc:ID>\n"
                        + "				<cac:TaxScheme>\n"
                        + "					<cbc:ID schemeID='UN/ECE 5153' schemeAgencyID='6'>9997</cbc:ID>\n"
                        + "					<cbc:Name>EXO</cbc:Name>\n"
                        + "					<cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>\n"
                        + "				</cac:TaxScheme>\n"
                        + "			</cac:TaxCategory>\n"
                        + "		</cac:TaxSubtotal>";
            }
            if (Cpe.getTOTAL_INAFECTA() > 0) {
                xmlCPE = xmlCPE + "\n"
                        + "                <cac:TaxSubtotal>\n"
                        + "			<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_INAFECTA() + "</cbc:TaxableAmount>\n"
                        + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0.00</cbc:TaxAmount>\n"
                        + "			<cac:TaxCategory>\n"
                        + "				<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>O</cbc:ID>\n"
                        + "				<cac:TaxScheme>\n"
                        + "					<cbc:ID schemeID='UN/ECE 5153' schemeAgencyID='6'>9998</cbc:ID>\n"
                        + "					<cbc:Name>INA</cbc:Name>\n"
                        + "					<cbc:TaxTypeCode>FRE</cbc:TaxTypeCode>\n"
                        + "				</cac:TaxScheme>\n"
                        + "			</cac:TaxCategory>\n"
                        + "		</cac:TaxSubtotal>";
            }
            if (Cpe.getTOTAL_OTR_IMP() > 0) {
                xmlCPE = xmlCPE + "\n"
                        + "                <cac:TaxSubtotal>\n"
                        + "			<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_OTR_IMP() + "</cbc:TaxableAmount>\n"
                        + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_OTR_IMP() + "</cbc:TaxAmount>\n"
                        + "			<cac:TaxCategory>\n"
                        + "				<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>S</cbc:ID>\n"
                        + "				<cac:TaxScheme>\n"
                        + "					<cbc:ID schemeID='UN/ECE 5153' schemeAgencyID='6'>9999</cbc:ID>\n"
                        + "					<cbc:Name>OTR</cbc:Name>\n"
                        + "					<cbc:TaxTypeCode>OTH</cbc:TaxTypeCode>\n"
                        + "				</cac:TaxScheme>\n"
                        + "			</cac:TaxCategory>\n"
                        + "		</cac:TaxSubtotal>";
            }
            //TOTAL=GRAVADA+IGV+EXONERADA\n" +
            //NO ENTRA GRATUITA(INAFECTA) NI DESCUENTO\n" +
            //SUB_TOTAL=PRECIO(SIN IGV) * CANTIDAD\n" +
            xmlCPE = xmlCPE + "\n"
                    + "       </cac:TaxTotal>\n"
                    + "	<cac:RequestedMonetaryTotal>\n";
            if (Cpe.getFLG_REGU_ANTICIPO() == 1) {
                xmlCPE = xmlCPE + "<cbc:PrepaidAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getMONTO_REGU_ANTICIPO() + "</cbc:PrepaidAmount>\n"
                        + "           <cbc:PayableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0.00</cbc:PayableAmount>\n";
            } else {
                xmlCPE = xmlCPE + "<cbc:PayableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL() + "</cbc:PayableAmount>\n";
            }

            xmlCPE = xmlCPE + "	</cac:RequestedMonetaryTotal>\n";
            for (int i = 0; i < lstCpe_Detalle.size(); i++) {
                _Cpe_DetalleBean Cpe_Detalle = lstCpe_Detalle.get(i);
             if (Cpe_Detalle.getCOD_TIPO_OPERACION().equals("10")) {
                xmlCPE = xmlCPE + "\n"
                        + "    <cac:DebitNoteLine>\n"
                        + "        <cbc:ID>" + Cpe_Detalle.getITEM() + "</cbc:ID>\n"
                        + "<cbc:DebitedQuantity unitCode='" + Cpe_Detalle.getUNIDAD_MEDIDA() + "'>" + Cpe_Detalle.getCANTIDAD() + "</cbc:DebitedQuantity>\n"
                        + "<cbc:LineExtensionAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:LineExtensionAmount>\n"
                        + "        <cac:PricingReference>\n"
                        + "            <cac:AlternativeConditionPrice>\n"
                        + "<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO() + "</cbc:PriceAmount>\n"
                        + "<cbc:PriceTypeCode>" + Cpe_Detalle.getPRECIO_TIPO_CODIGO() + "</cbc:PriceTypeCode>\n"
                        + "            </cac:AlternativeConditionPrice>\n"
                        + "        </cac:PricingReference>\n"
                        + "        <cac:TaxTotal>		\n"
                        + "<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIGV() + "</cbc:TaxAmount>\n"
                        + "            <cac:TaxSubtotal>\n"
                        + "                <cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:TaxableAmount>\n"
                        + "                <cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIGV() + "</cbc:TaxAmount>\n"
                        + "                <cac:TaxCategory>\n"
                        + "                    <cbc:Percent>" + Cpe.getPOR_IGV() + "</cbc:Percent>\n"
                        + "<cbc:TaxExemptionReasonCode>" + Cpe_Detalle.getCOD_TIPO_OPERACION() + "</cbc:TaxExemptionReasonCode>\n"
                        + "                    <cac:TaxScheme>\n"
                        + "                        <cbc:ID>1000</cbc:ID>\n"
                        + "                        <cbc:Name>IGV</cbc:Name>\n"
                        + "                        <cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>\n"
                        + "                    </cac:TaxScheme>\n"
                        + "                </cac:TaxCategory>\n"
                        + "            </cac:TaxSubtotal>\n"
                        + "        </cac:TaxTotal>\n"
                        + "		\n"
                        + "<cac:Item>\n"
                        + "<cbc:Description><![CDATA[" + Cpe_Detalle.getDESCRIPCION() + "]]></cbc:Description>\n"
                        + "            <cac:SellersItemIdentification>\n"
                        + "                <cbc:ID><![CDATA[" + Cpe_Detalle.getCODIGO() + "]]></cbc:ID>\n"
                        + "            </cac:SellersItemIdentification>\n";
                            if (!Cpe_Detalle.getCOD_SUNAT().equals("")) {
                                xmlCPE = xmlCPE + "<cac:CommodityClassification>\n"
			                    + "<cbc:ItemClassificationCode listID='UNSPSC' listAgencyName='GS1 US' \n"
			                    + "listName='Item Classification'>" + Cpe_Detalle.getCOD_SUNAT() + "</cbc:ItemClassificationCode>\n"
			                    + "</cac:CommodityClassification>";
                            }
                             xmlCPE = xmlCPE + "</cac:Item>\n"
                        + "<cac:Price>\n"
                        + "<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO() + "</cbc:PriceAmount>\n"
                        + "</cac:Price>\n"
                        + "    </cac:DebitNoteLine>";
                }
             if (Cpe_Detalle.getCOD_TIPO_OPERACION().equals("20")) {
                xmlCPE = xmlCPE + "\n"
                        + "    <cac:DebitNoteLine>\n"
                        + "        <cbc:ID>" + Cpe_Detalle.getITEM() + "</cbc:ID>\n"
                        + "<cbc:DebitedQuantity unitCode='" + Cpe_Detalle.getUNIDAD_MEDIDA() + "'>" + Cpe_Detalle.getCANTIDAD() + "</cbc:DebitedQuantity>\n"
                        + "<cbc:LineExtensionAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:LineExtensionAmount>\n"
                        + "        <cac:PricingReference>\n"
                        + "            <cac:AlternativeConditionPrice>\n"
                        + "<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO() + "</cbc:PriceAmount>\n"
                        + "<cbc:PriceTypeCode>" + Cpe_Detalle.getPRECIO_TIPO_CODIGO() + "</cbc:PriceTypeCode>\n"
                        + "            </cac:AlternativeConditionPrice>\n"
                        + "        </cac:PricingReference>\n"
                        + "        <cac:TaxTotal>		\n"
                        + "<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                        + "            <cac:TaxSubtotal>\n"
                        + "                <cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:TaxableAmount>\n"
                        + "                <cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                        + "                <cac:TaxCategory>\n"
                        + "                    <cbc:Percent>0</cbc:Percent>\n"
                        + "<cbc:TaxExemptionReasonCode>" + Cpe_Detalle.getCOD_TIPO_OPERACION() + "</cbc:TaxExemptionReasonCode>\n"
                        + "                    <cac:TaxScheme>\n"
                        + "                        <cbc:ID>9997</cbc:ID>\n"
                        + "                        <cbc:Name>EXO</cbc:Name>\n"
                        + "                        <cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>\n"
                        + "                    </cac:TaxScheme>\n"
                        + "                </cac:TaxCategory>\n"
                        + "            </cac:TaxSubtotal>\n"
                        + "        </cac:TaxTotal>\n"
                        + "		\n"
                        + "<cac:Item>\n"
                        + "<cbc:Description><![CDATA[" + Cpe_Detalle.getDESCRIPCION() + "]]></cbc:Description>\n"
                        + "            <cac:SellersItemIdentification>\n"
                        + "                <cbc:ID><![CDATA[" + Cpe_Detalle.getCODIGO() + "]]></cbc:ID>\n"
                        + "            </cac:SellersItemIdentification>\n";
                            if (!Cpe_Detalle.getCOD_SUNAT().equals("")) {
                                xmlCPE = xmlCPE + "<cac:CommodityClassification>\n"
			                    + "<cbc:ItemClassificationCode listID='UNSPSC' listAgencyName='GS1 US' \n"
			                    + "listName='Item Classification'>" + Cpe_Detalle.getCOD_SUNAT() + "</cbc:ItemClassificationCode>\n"
			                    + "</cac:CommodityClassification>";
                            }
                             xmlCPE = xmlCPE + "</cac:Item>\n"
                        + "<cac:Price>\n"
                        + "<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO() + "</cbc:PriceAmount>\n"
                        + "</cac:Price>\n"
                        + "    </cac:DebitNoteLine>";
                }
             if (Cpe_Detalle.getCOD_TIPO_OPERACION().equals("30")) {
                xmlCPE = xmlCPE + "\n"
                        + "    <cac:DebitNoteLine>\n"
                        + "        <cbc:ID>" + Cpe_Detalle.getITEM() + "</cbc:ID>\n"
                        + "<cbc:DebitedQuantity unitCode='" + Cpe_Detalle.getUNIDAD_MEDIDA() + "'>" + Cpe_Detalle.getCANTIDAD() + "</cbc:DebitedQuantity>\n"
                        + "<cbc:LineExtensionAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:LineExtensionAmount>\n"
                        + "        <cac:PricingReference>\n"
                        + "            <cac:AlternativeConditionPrice>\n"
                        + "<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO() + "</cbc:PriceAmount>\n"
                        + "<cbc:PriceTypeCode>" + Cpe_Detalle.getPRECIO_TIPO_CODIGO() + "</cbc:PriceTypeCode>\n"
                        + "            </cac:AlternativeConditionPrice>\n"
                        + "        </cac:PricingReference>\n"
                        + "        <cac:TaxTotal>		\n"
                        + "<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                        + "            <cac:TaxSubtotal>\n"
                        + "                <cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:TaxableAmount>\n"
                        + "                <cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                        + "                <cac:TaxCategory>\n"
                        + "                    <cbc:Percent>0</cbc:Percent>\n"
                        + "<cbc:TaxExemptionReasonCode>" + Cpe_Detalle.getCOD_TIPO_OPERACION() + "</cbc:TaxExemptionReasonCode>\n"
                        + "                    <cac:TaxScheme>\n"
                        + "                        <cbc:ID>9998</cbc:ID>\n"
                        + "                        <cbc:Name>INA</cbc:Name>\n"
                        + "                        <cbc:TaxTypeCode>FRE</cbc:TaxTypeCode>\n"
                        + "                    </cac:TaxScheme>\n"
                        + "                </cac:TaxCategory>\n"
                        + "            </cac:TaxSubtotal>\n"
                        + "        </cac:TaxTotal>\n"
                        + "		\n"
                        + "<cac:Item>\n"
                        + "<cbc:Description><![CDATA[" + Cpe_Detalle.getDESCRIPCION() + "]]></cbc:Description>\n"
                        + "            <cac:SellersItemIdentification>\n"
                        + "                <cbc:ID><![CDATA[" + Cpe_Detalle.getCODIGO() + "]]></cbc:ID>\n"
                        + "            </cac:SellersItemIdentification>\n";
                            if (!Cpe_Detalle.getCOD_SUNAT().equals("")) {
                                xmlCPE = xmlCPE + "<cac:CommodityClassification>\n"
			                    + "<cbc:ItemClassificationCode listID='UNSPSC' listAgencyName='GS1 US' \n"
			                    + "listName='Item Classification'>" + Cpe_Detalle.getCOD_SUNAT() + "</cbc:ItemClassificationCode>\n"
			                    + "</cac:CommodityClassification>";
                            }
                             xmlCPE = xmlCPE + "</cac:Item>\n"
                        + "<cac:Price>\n"
                        + "<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO_SIN_IMPUESTO() + "</cbc:PriceAmount>\n"
                        + "</cac:Price>\n"
                        + "    </cac:DebitNoteLine>";
                }
              if (Cpe_Detalle.getCOD_TIPO_OPERACION().equals("31")) {
                xmlCPE = xmlCPE + "\n"
                        + "    <cac:DebitNoteLine>\n"
                        + "        <cbc:ID>" + Cpe_Detalle.getITEM() + "</cbc:ID>\n"
                        + "<cbc:DebitedQuantity unitCode='" + Cpe_Detalle.getUNIDAD_MEDIDA() + "'>" + Cpe_Detalle.getCANTIDAD() + "</cbc:DebitedQuantity>\n"
                        + "<cbc:LineExtensionAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:LineExtensionAmount>\n"
                        + "        <cac:PricingReference>\n"
                        + "            <cac:AlternativeConditionPrice>\n"
                        + "<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO() + "</cbc:PriceAmount>\n"
                        + "<cbc:PriceTypeCode>02</cbc:PriceTypeCode>\n"
                        + "            </cac:AlternativeConditionPrice>\n"
                        + "        </cac:PricingReference>\n"
                        + "        <cac:TaxTotal>		\n"
                        + "<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                        + "            <cac:TaxSubtotal>\n"
                        + "                <cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:TaxableAmount>\n"
                        + "                <cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                        + "                <cac:TaxCategory>\n"
                        + "                    <cbc:Percent>0</cbc:Percent>\n"
                        + "<cbc:TaxExemptionReasonCode>" + Cpe_Detalle.getCOD_TIPO_OPERACION() + "</cbc:TaxExemptionReasonCode>\n"
                        + "                    <cac:TaxScheme>\n"
                        + "                        <cbc:ID>9996</cbc:ID>\n"
                        + "                        <cbc:Name>GRA</cbc:Name>\n"
                        + "                        <cbc:TaxTypeCode>FRE</cbc:TaxTypeCode>\n"
                        + "                    </cac:TaxScheme>\n"
                        + "                </cac:TaxCategory>\n"
                        + "            </cac:TaxSubtotal>\n"
                        + "        </cac:TaxTotal>\n"
                        + "		\n"
                        + "<cac:Item>\n"
                        + "<cbc:Description><![CDATA[" + Cpe_Detalle.getDESCRIPCION() + "]]></cbc:Description>\n"
                        + "            <cac:SellersItemIdentification>\n"
                        + "                <cbc:ID><![CDATA[" + Cpe_Detalle.getCODIGO() + "]]></cbc:ID>\n"
                        + "            </cac:SellersItemIdentification>\n";
                            if (!Cpe_Detalle.getCOD_SUNAT().equals("")) {
                                xmlCPE = xmlCPE + "<cac:CommodityClassification>\n"
			                    + "<cbc:ItemClassificationCode listID='UNSPSC' listAgencyName='GS1 US' \n"
			                    + "listName='Item Classification'>" + Cpe_Detalle.getCOD_SUNAT() + "</cbc:ItemClassificationCode>\n"
			                    + "</cac:CommodityClassification>";
                            }
                             xmlCPE = xmlCPE + "</cac:Item>\n"
                        + "<cac:Price>\n"
                        + "<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:PriceAmount>\n"
                        + "</cac:Price>\n"
                        + "    </cac:DebitNoteLine>";
                }
              if (Cpe_Detalle.getCOD_TIPO_OPERACION().equals("40")) {
                xmlCPE = xmlCPE + "\n"
                        + "    <cac:DebitNoteLine>\n"
                        + "        <cbc:ID>" + Cpe_Detalle.getITEM() + "</cbc:ID>\n"
                        + "<cbc:DebitedQuantity unitCode='" + Cpe_Detalle.getUNIDAD_MEDIDA() + "'>" + Cpe_Detalle.getCANTIDAD() + "</cbc:DebitedQuantity>\n"
                        + "<cbc:LineExtensionAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:LineExtensionAmount>\n"
                        + "        <cac:PricingReference>\n"
                        + "            <cac:AlternativeConditionPrice>\n"
                        + "<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO() + "</cbc:PriceAmount>\n"
                        + "<cbc:PriceTypeCode>" + Cpe_Detalle.getPRECIO_TIPO_CODIGO() + "</cbc:PriceTypeCode>\n"
                        + "            </cac:AlternativeConditionPrice>\n"
                        + "        </cac:PricingReference>\n"
                        + "        <cac:TaxTotal>		\n"
                        + "<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                        + "            <cac:TaxSubtotal>\n"
                        + "                <cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:TaxableAmount>\n"
                        + "                <cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                        + "                <cac:TaxCategory>\n"
                        + "                    <cbc:Percent>0</cbc:Percent>\n"
                        + "<cbc:TaxExemptionReasonCode>" + Cpe_Detalle.getCOD_TIPO_OPERACION() + "</cbc:TaxExemptionReasonCode>\n"
                        + "                    <cac:TaxScheme>\n"
                        + "                        <cbc:ID>9995</cbc:ID>\n"
                        + "                        <cbc:Name>EXP</cbc:Name>\n"
                        + "                        <cbc:TaxTypeCode>FRE</cbc:TaxTypeCode>\n"
                        + "                    </cac:TaxScheme>\n"
                        + "                </cac:TaxCategory>\n"
                        + "            </cac:TaxSubtotal>\n"
                        + "        </cac:TaxTotal>\n"
                        + "		\n"
                        + "<cac:Item>\n"
                        + "<cbc:Description><![CDATA[" + Cpe_Detalle.getDESCRIPCION() + "]]></cbc:Description>\n"
                        + "            <cac:SellersItemIdentification>\n"
                        + "                <cbc:ID><![CDATA[" + Cpe_Detalle.getCODIGO() + "]]></cbc:ID>\n"
                        + "            </cac:SellersItemIdentification>\n";
                            if (!Cpe_Detalle.getCOD_SUNAT().equals("")) {
                                xmlCPE = xmlCPE + "<cac:CommodityClassification>\n"
			                    + "<cbc:ItemClassificationCode listID='UNSPSC' listAgencyName='GS1 US' \n"
			                    + "listName='Item Classification'>" + Cpe_Detalle.getCOD_SUNAT() + "</cbc:ItemClassificationCode>\n"
			                    + "</cac:CommodityClassification>";
                            }
                             xmlCPE = xmlCPE + "</cac:Item>\n"
                        + "<cac:Price>\n"
                        + "<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO_SIN_IMPUESTO() + "</cbc:PriceAmount>\n"
                        + "</cac:Price>\n"
                        + "    </cac:DebitNoteLine>";
                }
            }
            xmlCPE = xmlCPE + "</DebitNote>";

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlCPE)));
            // Use a Transformer for output
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(RutaXml + ".XML"));
            transformer.transform(source, result);

            System.out.println("\nXML DOM Created Successfully..");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    public int GenerarXMLCPE(String RutaXml, _CpeBean Cpe, List<_Cpe_DetalleBean> lstCpe_Detalle) {
        try {
            String xmlCPE = "";
            xmlCPE = "<?xml version='1.0' encoding='utf-8'?>\n"
                    + "<Invoice xmlns='urn:oasis:names:specification:ubl:schema:xsd:Invoice-2' xmlns:cac='urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2' xmlns:cbc='urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2' xmlns:ccts='urn:un:unece:uncefact:documentation:2' xmlns:ds='http://www.w3.org/2000/09/xmldsig#' xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:ext='urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2' xmlns:qdt='urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2' xmlns:udt='urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>\n"
                    + "	<ext:UBLExtensions>\n"
                    + "		<ext:UBLExtension>\n"
                    + "			<ext:ExtensionContent>\n"
                    + "			</ext:ExtensionContent>\n"
                    + "		</ext:UBLExtension>\n"
                    + "	</ext:UBLExtensions>\n"
                    + "	<cbc:UBLVersionID>2.1</cbc:UBLVersionID>\n"
                    + "	<cbc:CustomizationID schemeAgencyName='PE:SUNAT'>2.0</cbc:CustomizationID>\n"
                    + "	<cbc:ProfileID schemeName='Tipo de Operacion' schemeAgencyName='PE:SUNAT' schemeURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo51'>" + Cpe.getTIPO_OPERACION() + "</cbc:ProfileID>\n"
                    + "	<cbc:ID>" + Cpe.getNRO_COMPROBANTE() + "</cbc:ID>\n"
                    + "	<cbc:IssueDate>" + Cpe.getFECHA_DOCUMENTO() + "</cbc:IssueDate>\n"
                    + "	<cbc:IssueTime>00:00:00</cbc:IssueTime>\n"
                    + "	<cbc:DueDate>" + Cpe.getFECHA_VTO() + "</cbc:DueDate>\n"
                    + "	<cbc:InvoiceTypeCode listAgencyName='PE:SUNAT' listName='Tipo de Documento' listURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo01' listID='" + Cpe.getTIPO_OPERACION() + "' name='Tipo de Operacion' listSchemeURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo51'>" + Cpe.getCOD_TIPO_DOCUMENTO() + "</cbc:InvoiceTypeCode>\n";
            if (!Cpe.getTOTAL_LETRAS().equals("")) {
                xmlCPE = xmlCPE + "\n"
                        + "<cbc:Note languageLocaleID='1000'>" + Cpe.getTOTAL_LETRAS() + "</cbc:Note>\n";
            }
            if (Cpe.getTIPO_OPERACION().equals("0105")) {
                xmlCPE = xmlCPE + "\n"
                        + "<cbc:Note languageLocaleID='2005'>" + "Venta realizada por emisor itinerante" + "</cbc:Note>\n";
            }
            if (Cpe.getTIPO_OPERACION().equals("2001")) {
                xmlCPE = xmlCPE + "\n"
                        + "<cbc:Note languageLocaleID='2000'>" + "COMPROBANTE DE PERCEPCIÓN" + "</cbc:Note>\n";
            }
            if (Cpe.getTOTAL_GRATUITAS() > 0) {
                xmlCPE = xmlCPE + "\n"
                        + "<cbc:Note languageLocaleID='1002'>" + "TRANSFERENCIA GRATUITA DE UN BIEN Y/O SERVICIO PRESTADO GRATUITAMENTE" + "</cbc:Note>\n";
            }
            xmlCPE = xmlCPE + "\n"
                    + "       <cbc:DocumentCurrencyCode listID='ISO 4217 Alpha' listName='Currency' listAgencyName='United Nations Economic Commission for Europe'>" + Cpe.getCOD_MONEDA() + "</cbc:DocumentCurrencyCode>\n"
                    + "            <cbc:LineCountNumeric>" + lstCpe_Detalle.size() + "</cbc:LineCountNumeric>\n";
            if (!Cpe.getNRO_OTR_COMPROBANTE().equals("")) {
                xmlCPE = xmlCPE + "\n"
                        + "            <cac:OrderReference>\n"
                        + "               <cbc:ID>" + Cpe.getNRO_OTR_COMPROBANTE() + "</cbc:ID>\n"
                        + "            </cac:OrderReference>\n";
            }
            if (!Cpe.getNRO_GUIA_REMISION().equals("")) {
                xmlCPE = xmlCPE + "\n"
                        + "            <cac:DespatchDocumentReference>\n"
                        + "		<cbc:ID>" + Cpe.getNRO_GUIA_REMISION() + "</cbc:ID>\n"
                        + "		<cbc:IssueDate>" + Cpe.getFECHA_GUIA_REMISION() + "</cbc:IssueDate>\n"
                        + "		<cbc:DocumentTypeCode listAgencyName='PE:SUNAT' listName='Tipo de Documento' listURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo01'>" + Cpe.getCOD_GUIA_REMISION() + "</cbc:DocumentTypeCode>\n"
                        + "            </cac:DespatchDocumentReference>\n";
            }
            if (Cpe.getFLG_REGU_ANTICIPO() == 1) {
                /*
                 catalogo12 sunat
                 02=FACTURA X ANTICIPO
                 03=BOLETA X ANTICIPO
                 */
                String tipodocRelacionado = "";
                if (Cpe.getCOD_TIPO_DOCUMENTO().equals("01")) {
                    tipodocRelacionado = "02";
                } else {
                    tipodocRelacionado = "03";
                }
                xmlCPE = xmlCPE + "<cac:AdditionalDocumentReference>\n"
                        + "<cbc:ID>" + Cpe.getNRO_COMPROBANTE_REF_ANT() + "</cbc:ID>\n"
                        + "<cbc:DocumentTypeCode listAgencyName='PE:SUNAT' listName='Documento Relacionado' listURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo12'>" + tipodocRelacionado + "</cbc:DocumentTypeCode>\n"
                        + "<cbc:DocumentStatusCode listName='Anticipo' listAgencyName='PE:SUNAT'>" + Cpe.getNRO_COMPROBANTE_REF_ANT() + "</cbc:DocumentStatusCode>\n"
                        + "<cac:IssuerParty>\n"
                        + "<cac:PartyIdentification>\n"
                        + "<cbc:ID schemeID='" + Cpe.getTIPO_DOCUMENTO_EMP_REGU_ANT() + "' schemeName='Documento de Identidad' schemeAgencyName='PE:SUNAT' schemeURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06'>" + Cpe.getNRO_DOCUMENTO_EMP_REGU_ANT() + "</cbc:ID>\n"
                        + "</cac:PartyIdentification>\n"
                        + "</cac:IssuerParty>\n"
                        + "</cac:AdditionalDocumentReference>";
            }
            xmlCPE = xmlCPE + "\n"
                    + "            <cac:Signature>\n"
                    + "		<cbc:ID>" + Cpe.getNRO_COMPROBANTE() + "</cbc:ID>\n"
                    + "		<cac:SignatoryParty>\n"
                    + "			<cac:PartyIdentification>\n"
                    + "				<cbc:ID>" + Cpe.getNRO_DOCUMENTO_EMPRESA() + "</cbc:ID>\n"
                    + "			</cac:PartyIdentification>\n"
                    + "			<cac:PartyName>\n"
                    + "				<cbc:Name>" + Cpe.getRAZON_SOCIAL_EMPRESA() + "</cbc:Name>\n"
                    + "			</cac:PartyName>\n"
                    + "		</cac:SignatoryParty>\n"
                    + "		<cac:DigitalSignatureAttachment>\n"
                    + "			<cac:ExternalReference>\n"
                    + "				<cbc:URI>#" + Cpe.getNRO_COMPROBANTE() + "</cbc:URI>\n"
                    + "			</cac:ExternalReference>\n"
                    + "		</cac:DigitalSignatureAttachment>\n"
                    + "	</cac:Signature>\n"
                    + "	<cac:AccountingSupplierParty>\n"
                    + "		<cac:Party>\n"
                    + "			<cac:PartyIdentification>\n"
                    + "				<cbc:ID schemeID='" + Cpe.getTIPO_DOCUMENTO_EMPRESA() + "' schemeName='Documento de Identidad' schemeAgencyName='PE:SUNAT' schemeURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06'>" + Cpe.getNRO_DOCUMENTO_EMPRESA() + "</cbc:ID>\n"
                    + "			</cac:PartyIdentification>\n"
                    + "			<cac:PartyName>\n"
                    + "				<cbc:Name><![CDATA[" + Cpe.getNOMBRE_COMERCIAL_EMPRESA() + "]]></cbc:Name>\n"
                    + "			</cac:PartyName>\n"
                    + "			<cac:PartyTaxScheme>\n"
                    + "				<cbc:RegistrationName><![CDATA[" + Cpe.getRAZON_SOCIAL_EMPRESA() + "]]></cbc:RegistrationName>\n"
                    + "				<cbc:CompanyID schemeID='" + Cpe.getTIPO_DOCUMENTO_EMPRESA() + "' schemeName='SUNAT:Identificador de Documento de Identidad' schemeAgencyName='PE:SUNAT' schemeURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06'>" + Cpe.getNRO_DOCUMENTO_EMPRESA() + "</cbc:CompanyID>\n"
                    + "				<cac:TaxScheme>\n"
                    + "					<cbc:ID schemeID='" + Cpe.getTIPO_DOCUMENTO_EMPRESA() + "' schemeName='SUNAT:Identificador de Documento de Identidad' schemeAgencyName='PE:SUNAT' schemeURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06'>" + Cpe.getNRO_DOCUMENTO_EMPRESA() + "</cbc:ID>\n"
                    + "				</cac:TaxScheme>\n"
                    + "			</cac:PartyTaxScheme>\n"
                    + "			<cac:PartyLegalEntity>\n"
                    + "				<cbc:RegistrationName><![CDATA[" + Cpe.getRAZON_SOCIAL_EMPRESA() + "]]></cbc:RegistrationName>\n"
                    + "				<cac:RegistrationAddress>\n"
                    + "					<cbc:ID schemeName='Ubigeos' schemeAgencyName='PE:INEI' />\n"
                    + "					<cbc:AddressTypeCode listAgencyName='PE:SUNAT' listName='Establecimientos anexos'>0000</cbc:AddressTypeCode>\n"
                    + "					<cbc:CityName><![CDATA[" + Cpe.getDEPARTAMENTO_EMPRESA() + "]]></cbc:CityName>\n"
                    + "					<cbc:CountrySubentity><![CDATA[" + Cpe.getPROVINCIA_EMPRESA() + "]]></cbc:CountrySubentity>\n"
                    + "					<cbc:District><![CDATA[" + Cpe.getDISTRITO_EMPRESA() + "]]></cbc:District>\n"
                    + "					<cac:AddressLine>\n"
                    + "						<cbc:Line><![CDATA[" + Cpe.getDIRECCION_EMPRESA() + "]]></cbc:Line>\n"
                    + "					</cac:AddressLine>\n"
                    + "					<cac:Country>\n"
                    + "						<cbc:IdentificationCode listID='ISO 3166-1' listAgencyName='United Nations Economic Commission for Europe' listName='Country'>" + Cpe.getCODIGO_PAIS_EMPRESA() + "</cbc:IdentificationCode>\n"
                    + "					</cac:Country>\n"
                    + "				</cac:RegistrationAddress>\n"
                    + "			</cac:PartyLegalEntity>\n"
                    + "			<cac:Contact>\n"
                    + "				<cbc:Name><![CDATA[" + Cpe.getCONTACTO_EMPRESA() + "]]></cbc:Name>\n"
                    + "			</cac:Contact>\n"
                    + "		</cac:Party>\n"
                    + "	</cac:AccountingSupplierParty>\n";
                    if (Cpe.getTIPO_DOCUMENTO_CLIENTE() != "0") {
                        xmlCPE = xmlCPE+ "	<cac:AccountingCustomerParty>\n"
                                + "		<cac:Party>\n"
                                + "			<cac:PartyIdentification>\n"
                                + "				<cbc:ID schemeID='" + Cpe.getTIPO_DOCUMENTO_CLIENTE() + "' schemeName='Documento de Identidad' schemeAgencyName='PE:SUNAT' schemeURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06'>" + Cpe.getNRO_DOCUMENTO_CLIENTE() + "</cbc:ID>\n"
                                + "			</cac:PartyIdentification>\n"
                                + "			<cac:PartyName>\n"
                                + "				<cbc:Name><![CDATA[" + Cpe.getRAZON_SOCIAL_CLIENTE() + "]]></cbc:Name>\n"
                                + "			</cac:PartyName>\n"
                        		
                        		+ "			<cac:PartyTaxScheme>\n"
                                + "				<cbc:RegistrationName><![CDATA[" + Cpe.getRAZON_SOCIAL_CLIENTE() + "]]></cbc:RegistrationName>\n"
                                + "				<cbc:CompanyID schemeID='" + Cpe.getTIPO_DOCUMENTO_CLIENTE() + "' schemeName='SUNAT:Identificador de Documento de Identidad' schemeAgencyName='PE:SUNAT' schemeURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06'>" + Cpe.getNRO_DOCUMENTO_CLIENTE() + "</cbc:CompanyID>\n"
                                + "				<cac:TaxScheme>\n"
                                + "					<cbc:ID schemeID='" + Cpe.getTIPO_DOCUMENTO_CLIENTE() + "' schemeName='SUNAT:Identificador de Documento de Identidad' schemeAgencyName='PE:SUNAT' schemeURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06'>" + Cpe.getNRO_DOCUMENTO_CLIENTE() + "</cbc:ID>\n"
                                + "				</cac:TaxScheme>\n"
                                + "			</cac:PartyTaxScheme>\n"
                                + "			<cac:PartyLegalEntity>\n"
                                + "				<cbc:RegistrationName><![CDATA[" + Cpe.getRAZON_SOCIAL_CLIENTE() + "]]></cbc:RegistrationName>\n"
                                + "				<cac:RegistrationAddress>\n"
                                + "					<cbc:ID schemeName='Ubigeos' schemeAgencyName='PE:INEI'>" + Cpe.getCOD_UBIGEO_CLIENTE() + "</cbc:ID>\n"
                                + "					<cbc:CityName><![CDATA[" + Cpe.getDEPARTAMENTO_CLIENTE() + "]]></cbc:CityName>\n"
                                + "					<cbc:CountrySubentity><![CDATA[" + Cpe.getPROVINCIA_CLIENTE() + "]]></cbc:CountrySubentity>\n"
                                + "					<cbc:District><![CDATA[" + Cpe.getDISTRITO_CLIENTE() + "]]></cbc:District>\n"
                                + "					<cac:AddressLine>\n"
                                + "						<cbc:Line><![CDATA[" + Cpe.getDIRECCION_CLIENTE() + "]]></cbc:Line>\n"
                                + "					</cac:AddressLine>\n"
                                + "					<cac:Country>\n"
                                + "						<cbc:IdentificationCode listID='ISO 3166-1' listAgencyName='United Nations Economic Commission for Europe' listName='Country'>" + Cpe.getCOD_PAIS_CLIENTE() + "</cbc:IdentificationCode>\n"
                                + "					</cac:Country>\n"
                                + "				</cac:RegistrationAddress>\n"
                                + "			</cac:PartyLegalEntity>\n"
                                + "		</cac:Party>\n"
                                + "	</cac:AccountingCustomerParty>\n";
                    }else {
                    	xmlCPE = xmlCPE+ "	<cac:AccountingCustomerParty>\n"
                                + "		<cac:Party>\n"
                                + "			<cac:PartyIdentification>\n"
                                + "				<cbc:ID schemeID='" + Cpe.getTIPO_DOCUMENTO_CLIENTE() + "' schemeName='Documento de Identidad' schemeAgencyName='PE:SUNAT' schemeURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06'>" + Cpe.getNRO_DOCUMENTO_CLIENTE() + "</cbc:ID>\n"
                                + "			</cac:PartyIdentification>\n"
                                + "			<cac:PartyName>\n"
                                + "				<cbc:Name><![CDATA[" + Cpe.getRAZON_SOCIAL_CLIENTE() + "]]></cbc:Name>\n"
                                + "			</cac:PartyName>\n"
                                + "			<cac:PartyLegalEntity>\n"
                                + "				<cbc:RegistrationName><![CDATA[" + Cpe.getRAZON_SOCIAL_CLIENTE() + "]]></cbc:RegistrationName>\n"
                                + "			</cac:PartyLegalEntity>\n";
                    }

                    
                    
            
            if (Cpe.getTIPO_OPERACION().equals("0105")) {
                xmlCPE = xmlCPE + "\n"
                  + "<cac:DeliveryTerms>\n"
                    + "<cac:DeliveryLocation\n>"
                      + "<cac:Address>\n"
                        + "<cbc:StreetName>"+Cpe.getDIRECCION_ITINERANTE()+"</cbc:StreetName>\n"
                        + "<cbc:CitySubdivisionName>"+""+"</cbc:CitySubdivisionName>\n"//urbanizacion
                        + "<cbc:CityName>"+Cpe.getPROVINCIA_ITINERANTE()+"</cbc:CityName>\n"
                        + "<cbc:CountrySubentity>"+Cpe.getDEPARTAMENTO_ITINERANTE()	+"</cbc:CountrySubentity>\n"
                        + "<cbc:CountrySubentityCode>"+Cpe.getUBIGEO_ITINERANTE()+"</cbc:CountrySubentityCode>\n"
                        + "<cbc:District>"+Cpe.getDISTRITO_ITINERANTE()+"</cbc:District>\n"
                        + "<cac:Country>\n"
                        + "<cbc:IdentificationCode listID='ISO 3166-1' listAgencyName='United Nations Economic Commission for Europe' listName='Country'>"+"PE"+"</cbc:IdentificationCode>\n"
                        + "</cac:Country>\n"
                      + "</cac:Address>\n"
                    + "</cac:DeliveryLocation>\n"
                   + "</cac:DeliveryTerms>\n";
            }
            if (Cpe.getDESCUENTO_GLOBAL() > 0  && Cpe.getTOTAL_GRAVADAS()>0) {
                xmlCPE = xmlCPE + "<cac:AllowanceCharge>\n"
                		+ "<cbc:ChargeIndicator>false</cbc:ChargeIndicator>"
                		+ "<cbc:AllowanceChargeReasonCode>"+"00"+"</cbc:AllowanceChargeReasonCode>\n"
                		+ "<cbc:MultiplierFactorNumeric>"+Cpe.getPOR_DESCUENTO_GLOBAL()+"</cbc:MultiplierFactorNumeric>\n"
                		+ "<cbc:Amount currencyID='PEN'>"+Cpe.getDESCUENTO_GLOBAL()+"</cbc:Amount>\n"
                		+ "<cbc:BaseAmount currencyID='PEN'>"+Cpe.getTOTAL_GRAVADAS()+"</cbc:BaseAmount>\n"
                	+ "</cac:AllowanceCharge>\n";
            }
            
            if (Cpe.getTOTAL_PERCEPCIONES() > 0) {
                xmlCPE = xmlCPE + "<cac:AllowanceCharge>\n"
                		+ "<cbc:ChargeIndicator>true</cbc:ChargeIndicator>"
                		+ "<cbc:AllowanceChargeReasonCode>"+"51"+"</cbc:AllowanceChargeReasonCode>\n"
                		+ "<cbc:MultiplierFactorNumeric>"+Cpe.getPOR_PERCEPCION()+"</cbc:MultiplierFactorNumeric>\n"
                		+ "<cbc:Amount currencyID='PEN'>"+Cpe.getTOTAL_PERCEPCIONES()+"</cbc:Amount>\n"
                		+ "<cbc:BaseAmount currencyID='PEN'>"+Cpe.getSUB_TOTAL()+"</cbc:BaseAmount>\n"
                	+ "</cac:AllowanceCharge>\n";
            }

            if (Cpe.getTOTAL_DETRACCIONES() > 0) {
                xmlCPE = xmlCPE + "<cac:PaymentTerms>\n"
                		+"<cbc:PaymentMeansID schemeName='SUNAT:Codigo de detraccion' schemeAgencyName='PE:SUNAT' "
                		+ "schemeURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo54'>"+Cpe.getTIPO_DETRACCION()+"</cbc:PaymentMeansID>"
                		+"<cbc:PaymentPercent>"+Cpe.getPORCENTAJE_DETRACCION()+"</cbc:PaymentPercent>"
                        + "<cbc:Amount currencyID='"+Cpe.getCOD_MONEDA()+"'>"+Cpe.getTOTAL_DETRACCIONES()+"</cbc:Amount>\n"
                        + "</cac:PaymentTerms>\n";
            }

            if (Cpe.getFLG_REGU_ANTICIPO() == 1) {
                xmlCPE = xmlCPE + "<cac:PrepaidPayment>\n"
                        + "<cbc:ID schemeName='Anticipo' schemeAgencyName='PE:SUNAT'>" + Cpe.getNRO_COMPROBANTE_REF_ANT() + "</cbc:ID>\n"
                        + "<cbc:PaidAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getMONTO_REGU_ANTICIPO() + "</cbc:PaidAmount>\n"
                        + "</cac:PrepaidPayment>\n";
            }
            if(Cpe.getCOD_TIPO_DOCUMENTO().equals("01")) {
                if(Cpe.getCOD_TIPO_PAGO().equals("CONT")) {
                	xmlCPE = xmlCPE + "<cac:PaymentTerms>\n"
                    +"<cbc:ID>FormaPago</cbc:ID>\n"
                    +"<cbc:PaymentMeansID>Contado</cbc:PaymentMeansID>\n"
                    +"<cbc:Amount currencyID='" + Cpe.getCOD_MONEDA() + "'>"+Cpe.getTOTAL()+"</cbc:Amount>\n"
                +"</cac:PaymentTerms>\n";
                }else {
                	xmlCPE = xmlCPE + "<cac:PaymentTerms>\n"
                            +"<cbc:ID>FormaPago</cbc:ID>\n"
                            +"<cbc:PaymentMeansID>Credito</cbc:PaymentMeansID>\n"
                            +"<cbc:Amount currencyID='" + Cpe.getCOD_MONEDA() + "'>"+Cpe.getTOTAL()+"</cbc:Amount>\n"
                        +"</cac:PaymentTerms>\n";
                	for(Cuota c:Cpe.getLSCUOTAS()) {
                    	xmlCPE = xmlCPE + "<cac:PaymentTerms>\n"
                                +"<cbc:ID>FormaPago</cbc:ID>\n"
                                +"<cbc:PaymentMeansID>"+c.getNombre()+"</cbc:PaymentMeansID>\n"
                                +"<cbc:Amount currencyID='" + Cpe.getCOD_MONEDA() + "'>"+c.getMonto_pago()+"</cbc:Amount>\n"
                                +"<cbc:PaymentDueDate>"+c.getFecha_cuota_str()+"</cbc:PaymentDueDate>\n"
                            +"</cac:PaymentTerms>\n";
                	}
                }
            }


            xmlCPE = xmlCPE + "<cac:TaxTotal>\n"
                    + "		<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_IGV() + "</cbc:TaxAmount>\n";
            
            if (Cpe.getTOTAL_GRAVADAS() > 0) {
                    xmlCPE = xmlCPE + "<cac:TaxSubtotal>\n"
                    + "			<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_GRAVADAS() + "</cbc:TaxableAmount>\n"
                    + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_IGV() + "</cbc:TaxAmount>\n"
                    + "			<cac:TaxCategory>\n"
                    + "				<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>S</cbc:ID>\n"
                    + "				<cac:TaxScheme>\n"
                    + "					<cbc:ID schemeID='UN/ECE 5153' schemeAgencyID='6'>1000</cbc:ID>\n"
                    + "					<cbc:Name>IGV</cbc:Name>\n"
                    + "					<cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>\n"
                    + "				</cac:TaxScheme>\n"
                    + "			</cac:TaxCategory>\n"
                    + "		</cac:TaxSubtotal>";
            }
            
            if (Cpe.getTOTAL_ISC() > 0) {
                xmlCPE = xmlCPE + "\n"
                        + "                <cac:TaxSubtotal>\n"
                        + "			<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_ISC() + "</cbc:TaxableAmount>\n"
                        + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_ISC() + "</cbc:TaxAmount>\n"
                        + "			<cac:TaxCategory>\n"
                        + "				<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>S</cbc:ID>\n"
                        + "				<cac:TaxScheme>\n"
                        + "					<cbc:ID schemeID='UN/ECE 5153' schemeAgencyID='6'>2000</cbc:ID>\n"
                        + "					<cbc:Name>ISC</cbc:Name>\n"
                        + "					<cbc:TaxTypeCode>EXC</cbc:TaxTypeCode>\n"
                        + "				</cac:TaxScheme>\n"
                        + "			</cac:TaxCategory>\n"
                        + "		</cac:TaxSubtotal>";
            }
            //CAMPO NUEVO
            if (Cpe.getTOTAL_EXPORTACION() > 0) {
                xmlCPE = xmlCPE + "\n"
                        + "                <cac:TaxSubtotal>\n"
                        + "			<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_EXPORTACION() + "</cbc:TaxableAmount>\n"
                        + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0.00</cbc:TaxAmount>\n"
                        + "			<cac:TaxCategory>\n"
                        + "				<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>G</cbc:ID>\n"
                        + "				<cac:TaxScheme>\n"
                        + "					<cbc:ID schemeID='UN/ECE 5153' schemeAgencyID='6'>9995</cbc:ID>\n"
                        + "					<cbc:Name>EXP</cbc:Name>\n"
                        + "					<cbc:TaxTypeCode>FRE</cbc:TaxTypeCode>\n"
                        + "				</cac:TaxScheme>\n"
                        + "			</cac:TaxCategory>\n"
                        + "		</cac:TaxSubtotal>";
            }
            if (Cpe.getTOTAL_GRATUITAS() > 0) {
                xmlCPE = xmlCPE + "\n"
                        + "                <cac:TaxSubtotal>\n"
                        + "			<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_GRATUITAS() + "</cbc:TaxableAmount>\n"
                        + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0.00</cbc:TaxAmount>\n"
                        + "			<cac:TaxCategory>\n"
                        + "				<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>Z</cbc:ID>\n"
                        + "				<cac:TaxScheme>\n"
                        + "					<cbc:ID schemeID='UN/ECE 5153' schemeAgencyID='6'>9996</cbc:ID>\n"
                        + "					<cbc:Name>GRA</cbc:Name>\n"
                        + "					<cbc:TaxTypeCode>FRE</cbc:TaxTypeCode>\n"
                        + "				</cac:TaxScheme>\n"
                        + "			</cac:TaxCategory>\n"
                        + "		</cac:TaxSubtotal>";
            }
            if (Cpe.getTOTAL_EXONERADAS() > 0) {
                xmlCPE = xmlCPE + "\n"
                        + "                <cac:TaxSubtotal>\n"
                        + "			<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_EXONERADAS() + "</cbc:TaxableAmount>\n"
                        + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0.00</cbc:TaxAmount>\n"
                        + "			<cac:TaxCategory>\n"
                        + "				<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>E</cbc:ID>\n"
                        + "				<cac:TaxScheme>\n"
                        + "					<cbc:ID schemeID='UN/ECE 5153' schemeAgencyID='6'>9997</cbc:ID>\n"
                        + "					<cbc:Name>EXO</cbc:Name>\n"
                        + "					<cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>\n"
                        + "				</cac:TaxScheme>\n"
                        + "			</cac:TaxCategory>\n"
                        + "		</cac:TaxSubtotal>";
            }
            if (Cpe.getTOTAL_INAFECTA() > 0) {
                xmlCPE = xmlCPE + "\n"
                        + "                <cac:TaxSubtotal>\n"
                        + "			<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_INAFECTA() + "</cbc:TaxableAmount>\n"
                        + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0.00</cbc:TaxAmount>\n"
                        + "			<cac:TaxCategory>\n"
                        + "				<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>O</cbc:ID>\n"
                        + "				<cac:TaxScheme>\n"
                        + "					<cbc:ID schemeID='UN/ECE 5153' schemeAgencyID='6'>9998</cbc:ID>\n"
                        + "					<cbc:Name>INA</cbc:Name>\n"
                        + "					<cbc:TaxTypeCode>FRE</cbc:TaxTypeCode>\n"
                        + "				</cac:TaxScheme>\n"
                        + "			</cac:TaxCategory>\n"
                        + "		</cac:TaxSubtotal>";
            }
            if (Cpe.getTOTAL_OTR_IMP() > 0) {
                xmlCPE = xmlCPE + "\n"
                        + "                <cac:TaxSubtotal>\n"
                        + "			<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_OTR_IMP() + "</cbc:TaxableAmount>\n"
                        + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_OTR_IMP() + "</cbc:TaxAmount>\n"
                        + "			<cac:TaxCategory>\n"
                        + "				<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>S</cbc:ID>\n"
                        + "				<cac:TaxScheme>\n"
                        + "					<cbc:ID schemeID='UN/ECE 5153' schemeAgencyID='6'>9999</cbc:ID>\n"
                        + "					<cbc:Name>OTR</cbc:Name>\n"
                        + "					<cbc:TaxTypeCode>OTH</cbc:TaxTypeCode>\n"
                        + "				</cac:TaxScheme>\n"
                        + "			</cac:TaxCategory>\n"
                        + "		</cac:TaxSubtotal>";
            }
            //TOTAL=GRAVADA+IGV+EXONERADA\n" +
            //NO ENTRA GRATUITA(INAFECTA) NI DESCUENTO\n" +
            //SUB_TOTAL=PRECIO(SIN IGV) * CANTIDAD\n" +
            xmlCPE = xmlCPE + "\n"
                    + "       </cac:TaxTotal>\n"
                    + "	<cac:LegalMonetaryTotal>\n";
            if (Cpe.getFLG_REGU_ANTICIPO() == 1) {
                xmlCPE = xmlCPE + "<cbc:PrepaidAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getMONTO_REGU_ANTICIPO() + "</cbc:PrepaidAmount>\n"
                        + "           <cbc:PayableAmount currencyID='" + Cpe.getMONEDA_REGU_ANTICIPO() + "'>" +Cpe.getTOTAL()+"</cbc:PayableAmount>\n";
            } else {
                xmlCPE = xmlCPE + "<cbc:AllowanceTotalAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL_DESCUENTO() + "</cbc:AllowanceTotalAmount>\n";
                xmlCPE = xmlCPE + "<cbc:PayableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe.getTOTAL() + "</cbc:PayableAmount>\n";
            }

            xmlCPE = xmlCPE + "	</cac:LegalMonetaryTotal>\n";
            for (int i = 0; i < lstCpe_Detalle.size(); i++) {
                _Cpe_DetalleBean Cpe_Detalle = lstCpe_Detalle.get(i);
                if (Cpe_Detalle.getCOD_TIPO_OPERACION()  .equals("10")) {
                    xmlCPE = xmlCPE + "<cac:InvoiceLine>\n"
                            + "		<cbc:ID>" + Cpe_Detalle.getITEM() + "</cbc:ID>\n"
                            + "		<cbc:InvoicedQuantity unitCode='" + Cpe_Detalle.getUNIDAD_MEDIDA() + "' unitCodeListID='UN/ECE rec 20' unitCodeListAgencyName='United Nations Economic Commission for Europe'>" + Cpe_Detalle.getCANTIDAD() + "</cbc:InvoicedQuantity>\n"
                            + "		<cbc:LineExtensionAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:LineExtensionAmount>\n"
                            + "		<cac:PricingReference>\n"
                            + "			<cac:AlternativeConditionPrice>\n"
                            + "				<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO() + "</cbc:PriceAmount>\n"
                            + "				<cbc:PriceTypeCode listName='Tipo de Precio' listAgencyName='PE:SUNAT' listURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo16'>" + Cpe_Detalle.getPRECIO_TIPO_CODIGO() + "</cbc:PriceTypeCode>\n"
                            + "			</cac:AlternativeConditionPrice>\n"
                            + "		</cac:PricingReference>\n"
                            + "		<cac:TaxTotal>\n"
                            + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIGV() + "</cbc:TaxAmount>\n"
                            + "			<cac:TaxSubtotal>\n"
                            + "				<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:TaxableAmount>\n"
                            + "				<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIGV() + "</cbc:TaxAmount>\n"
                            + "				<cac:TaxCategory>\n"
                            + "					<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>S</cbc:ID>\n"
                            + "					<cbc:Percent>" + Cpe.getPOR_IGV() + "</cbc:Percent>\n"
                            + "					<cbc:TaxExemptionReasonCode listAgencyName='PE:SUNAT' listName='SUNAT:Codigo de Tipo de Afectación del IGV' listURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo07'>" + Cpe_Detalle.getCOD_TIPO_OPERACION() + "</cbc:TaxExemptionReasonCode>\n"
                            + "					<cac:TaxScheme>\n"
                            + "						<cbc:ID schemeID='UN/ECE 5153' schemeName='Tax Scheme Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>1000</cbc:ID>\n"
                            + "						<cbc:Name>IGV</cbc:Name>\n"
                            + "						<cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>\n"
                            + "					</cac:TaxScheme>\n"
                            + "				</cac:TaxCategory>\n"
                            + "			</cac:TaxSubtotal>\n"
                            + "		</cac:TaxTotal>\n"
                            + "		<cac:Item>\n"
                            + "			<cbc:Description><![CDATA[" + Cpe_Detalle.getDESCRIPCION() + "]]></cbc:Description>\n"
                            + "			<cac:SellersItemIdentification>\n"
                            + "				<cbc:ID><![CDATA[" + Cpe_Detalle.getCODIGO() + "]]></cbc:ID>\n"
                            + "			</cac:SellersItemIdentification>\n";
                            if (!Cpe_Detalle.getCOD_SUNAT().equals("")) {
                                xmlCPE = xmlCPE + "<cac:CommodityClassification>\n"
			                    + "<cbc:ItemClassificationCode listID='UNSPSC' listAgencyName='GS1 US' \n"
			                    + "listName='Item Classification'>" + Cpe_Detalle.getCOD_SUNAT() + "</cbc:ItemClassificationCode>\n"
			                    + "</cac:CommodityClassification>";
                            }
                             xmlCPE = xmlCPE + "</cac:Item>\n"
                            + "		<cac:Price>\n"
                            + "			<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO_SIN_IMPUESTO() + "</cbc:PriceAmount>\n"
                            + "		</cac:Price>\n"
                            + "	</cac:InvoiceLine>";
                }
                if (Cpe_Detalle.getCOD_TIPO_OPERACION().equals("20")) {
                    xmlCPE = xmlCPE + "<cac:InvoiceLine>\n"
                            + "		<cbc:ID>" + Cpe_Detalle.getITEM() + "</cbc:ID>\n"
                            + "		<cbc:InvoicedQuantity unitCode='" + Cpe_Detalle.getUNIDAD_MEDIDA() + "' unitCodeListID='UN/ECE rec 20' unitCodeListAgencyName='United Nations Economic Commission for Europe'>" + Cpe_Detalle.getCANTIDAD() + "</cbc:InvoicedQuantity>\n"
                            + "		<cbc:LineExtensionAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:LineExtensionAmount>\n"
                            + "		<cac:PricingReference>\n"
                            + "			<cac:AlternativeConditionPrice>\n"
                            + "				<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO() + "</cbc:PriceAmount>\n"
                            + "				<cbc:PriceTypeCode listName='Tipo de Precio' listAgencyName='PE:SUNAT' listURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo16'>" + Cpe_Detalle.getPRECIO_TIPO_CODIGO() + "</cbc:PriceTypeCode>\n"
                            + "			</cac:AlternativeConditionPrice>\n"
                            + "		</cac:PricingReference>\n"
                            + "		<cac:TaxTotal>\n"
                            + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                            + "			<cac:TaxSubtotal>\n"
                            + "				<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:TaxableAmount>\n"
                            + "				<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                            + "				<cac:TaxCategory>\n"
                            + "					<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>E</cbc:ID>\n"
                            + "					<cbc:Percent>0</cbc:Percent>\n"
                            + "					<cbc:TaxExemptionReasonCode listAgencyName='PE:SUNAT' listName='SUNAT:Codigo de Tipo de Afectación del IGV' listURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo07'>" + Cpe_Detalle.getCOD_TIPO_OPERACION() + "</cbc:TaxExemptionReasonCode>\n"
                            + "					<cac:TaxScheme>\n"
                            + "						<cbc:ID schemeID='UN/ECE 5153' schemeName='Tax Scheme Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>9997</cbc:ID>\n"
                            + "						<cbc:Name>EXO</cbc:Name>\n"
                            + "						<cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>\n"
                            + "					</cac:TaxScheme>\n"
                            + "				</cac:TaxCategory>\n"
                            + "			</cac:TaxSubtotal>\n"
                            + "		</cac:TaxTotal>\n"
                            + "		<cac:Item>\n"
                            + "			<cbc:Description><![CDATA[" + Cpe_Detalle.getDESCRIPCION() + "]]></cbc:Description>\n"
                            + "			<cac:SellersItemIdentification>\n"
                            + "				<cbc:ID><![CDATA[" + Cpe_Detalle.getCODIGO() + "]]></cbc:ID>\n"
                            + "			</cac:SellersItemIdentification>\n";
                            if (!Cpe_Detalle.getCOD_SUNAT().equals("")) {
                                xmlCPE = xmlCPE + "<cac:CommodityClassification>\n"
			                    + "<cbc:ItemClassificationCode listID='UNSPSC' listAgencyName='GS1 US' \n"
			                    + "listName='Item Classification'>" + Cpe_Detalle.getCOD_SUNAT() + "</cbc:ItemClassificationCode>\n"
			                    + "</cac:CommodityClassification>";
                            }
                             xmlCPE = xmlCPE + "</cac:Item>\n"
                            + "		<cac:Price>\n"
                            + "			<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO_SIN_IMPUESTO() + "</cbc:PriceAmount>\n"
                            + "		</cac:Price>\n"
                            + "	</cac:InvoiceLine>";
                }
                if (Cpe_Detalle.getCOD_TIPO_OPERACION().equals("30")) {
                    xmlCPE = xmlCPE + "<cac:InvoiceLine>\n"
                            + "		<cbc:ID>" + Cpe_Detalle.getITEM() + "</cbc:ID>\n"
                            + "		<cbc:InvoicedQuantity unitCode='" + Cpe_Detalle.getUNIDAD_MEDIDA() + "' unitCodeListID='UN/ECE rec 20' unitCodeListAgencyName='United Nations Economic Commission for Europe'>" + Cpe_Detalle.getCANTIDAD() + "</cbc:InvoicedQuantity>\n"
                            + "		<cbc:LineExtensionAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:LineExtensionAmount>\n"
                            + "		<cac:PricingReference>\n"
                            + "			<cac:AlternativeConditionPrice>\n"
                            + "				<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO() + "</cbc:PriceAmount>\n"
                            + "				<cbc:PriceTypeCode listName='Tipo de Precio' listAgencyName='PE:SUNAT' listURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo16'>01</cbc:PriceTypeCode>\n"
                            + "			</cac:AlternativeConditionPrice>\n"
                            + "		</cac:PricingReference>\n"
                            + "		<cac:TaxTotal>\n"
                            + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                            + "			<cac:TaxSubtotal>\n"
                            + "				<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:TaxableAmount>\n"
                            + "				<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                            + "				<cac:TaxCategory>\n"
                            + "					<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>O</cbc:ID>\n"
                            + "					<cbc:Percent>" + Cpe.getPOR_IGV() + "</cbc:Percent>\n"
                            + "					<cbc:TaxExemptionReasonCode listAgencyName='PE:SUNAT' listName='SUNAT:Codigo de Tipo de Afectación del IGV' listURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo07'>" + Cpe_Detalle.getCOD_TIPO_OPERACION() + "</cbc:TaxExemptionReasonCode>\n"
                            + "					<cac:TaxScheme>\n"
                            + "						<cbc:ID schemeID='UN/ECE 5153' schemeName='Tax Scheme Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>9998</cbc:ID>\n"
                            + "						<cbc:Name>INA</cbc:Name>\n"
                            + "						<cbc:TaxTypeCode>FRE</cbc:TaxTypeCode>\n"
                            + "					</cac:TaxScheme>\n"
                            + "				</cac:TaxCategory>\n"
                            + "			</cac:TaxSubtotal>\n"
                            + "		</cac:TaxTotal>\n"
                            + "		<cac:Item>\n"
                            + "			<cbc:Description><![CDATA[" + Cpe_Detalle.getDESCRIPCION() + "]]></cbc:Description>\n"
                            + "			<cac:SellersItemIdentification>\n"
                            + "				<cbc:ID><![CDATA[" + Cpe_Detalle.getCODIGO() + "]]></cbc:ID>\n"
                            + "			</cac:SellersItemIdentification>\n";
                            if (!Cpe_Detalle.getCOD_SUNAT().equals("")) {
                                xmlCPE = xmlCPE + "<cac:CommodityClassification>\n"
			                    + "<cbc:ItemClassificationCode listID='UNSPSC' listAgencyName='GS1 US' \n"
			                    + "listName='Item Classification'>" + Cpe_Detalle.getCOD_SUNAT() + "</cbc:ItemClassificationCode>\n"
			                    + "</cac:CommodityClassification>";
                            }
                             xmlCPE = xmlCPE + "</cac:Item>\n"
                            + "		<cac:Price>\n"
                            + "			<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO() + "</cbc:PriceAmount>\n"
                            + "		</cac:Price>\n"
                            + "	</cac:InvoiceLine>";
                }

                if (!Cpe_Detalle.getCOD_TIPO_OPERACION().equals("10") && !Cpe_Detalle.getCOD_TIPO_OPERACION().equals("20") && !Cpe_Detalle.getCOD_TIPO_OPERACION().equals("30") && !Cpe_Detalle.getCOD_TIPO_OPERACION().equals("40")) {
                    xmlCPE = xmlCPE + "<cac:InvoiceLine>\n"
                            + "		<cbc:ID>" + Cpe_Detalle.getITEM() + "</cbc:ID>\n"
                            + "		<cbc:InvoicedQuantity unitCode='" + Cpe_Detalle.getUNIDAD_MEDIDA() + "' unitCodeListID='UN/ECE rec 20' unitCodeListAgencyName='United Nations Economic Commission for Europe'>" + Cpe_Detalle.getCANTIDAD() + "</cbc:InvoicedQuantity>\n"
                            + "		<cbc:LineExtensionAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:LineExtensionAmount>\n"
                            + "		<cac:PricingReference>\n"
                            + "			<cac:AlternativeConditionPrice>\n"
                            + "				<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:PriceAmount>\n"
                            + "				<cbc:PriceTypeCode listName='Tipo de Precio' listAgencyName='PE:SUNAT' listURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo16'>02</cbc:PriceTypeCode>\n"
                            + "			</cac:AlternativeConditionPrice>\n"
                            + "		</cac:PricingReference>\n"
                            + "		<cac:TaxTotal>\n"
                            + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                            + "			<cac:TaxSubtotal>\n"
                            + "				<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:TaxableAmount>\n"
                            + "				<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                            + "				<cac:TaxCategory>\n"
                            + "					<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>E</cbc:ID>\n"
                            + "					<cbc:Percent>" + Cpe.getPOR_IGV() + "</cbc:Percent>\n"
                            + "					<cbc:TaxExemptionReasonCode listAgencyName='PE:SUNAT' listName='SUNAT:Codigo de Tipo de Afectación del IGV' listURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo07'>" + Cpe_Detalle.getCOD_TIPO_OPERACION() + "</cbc:TaxExemptionReasonCode>\n"
                            + "					<cac:TaxScheme>\n"
                            + "						<cbc:ID schemeID='UN/ECE 5153' schemeName='Tax Scheme Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>9996</cbc:ID>\n"
                            + "						<cbc:Name>GRA</cbc:Name>\n"
                            + "						<cbc:TaxTypeCode>FRE</cbc:TaxTypeCode>\n"
                            + "					</cac:TaxScheme>\n"
                            + "				</cac:TaxCategory>\n"
                            + "			</cac:TaxSubtotal>\n"
                            + "		</cac:TaxTotal>\n"
                            + "		<cac:Item>\n"
                            + "			<cbc:Description><![CDATA[" + Cpe_Detalle.getDESCRIPCION() + "]]></cbc:Description>\n"
                            + "			<cac:SellersItemIdentification>\n"
                            + "				<cbc:ID><![CDATA[" + Cpe_Detalle.getCODIGO() + "]]></cbc:ID>\n"
                            + "			</cac:SellersItemIdentification>\n";
                            if (!Cpe_Detalle.getCOD_SUNAT().equals("")) {
                                xmlCPE = xmlCPE + "<cac:CommodityClassification>\n"
			                    + "<cbc:ItemClassificationCode listID='UNSPSC' listAgencyName='GS1 US' \n"
			                    + "listName='Item Classification'>" + Cpe_Detalle.getCOD_SUNAT() + "</cbc:ItemClassificationCode>\n"
			                    + "</cac:CommodityClassification>";
                            }
                             xmlCPE = xmlCPE + "</cac:Item>\n"
                            + "		<cac:Price>\n"
                            + "			<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:PriceAmount>\n"
                            + "		</cac:Price>\n"
                            + "	</cac:InvoiceLine>";
                }
                if (Cpe_Detalle.getCOD_TIPO_OPERACION().equals("40")) {
                    xmlCPE = xmlCPE + "<cac:InvoiceLine>\n"
                            + "		<cbc:ID>" + Cpe_Detalle.getITEM() + "</cbc:ID>\n"
                            + "		<cbc:InvoicedQuantity unitCode='" + Cpe_Detalle.getUNIDAD_MEDIDA() + "' unitCodeListID='UN/ECE rec 20' unitCodeListAgencyName='United Nations Economic Commission for Europe'>" + Cpe_Detalle.getCANTIDAD() + "</cbc:InvoicedQuantity>\n"
                            + "		<cbc:LineExtensionAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:LineExtensionAmount>\n"
                            + "		<cac:PricingReference>\n"
                            + "			<cac:AlternativeConditionPrice>\n"
                            + "				<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO() + "</cbc:PriceAmount>\n"
                            + "				<cbc:PriceTypeCode listName='Tipo de Precio' listAgencyName='PE:SUNAT' listURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo16'>" + Cpe_Detalle.getPRECIO_TIPO_CODIGO() + "</cbc:PriceTypeCode>\n"
                            + "			</cac:AlternativeConditionPrice>\n"
                            + "		</cac:PricingReference>\n"
                            + "		<cac:TaxTotal>\n"
                            + "			<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                            + "			<cac:TaxSubtotal>\n"
                            + "				<cbc:TaxableAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getIMPORTE() + "</cbc:TaxableAmount>\n"
                            + "				<cbc:TaxAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>0</cbc:TaxAmount>\n"
                            + "				<cac:TaxCategory>\n"
                            + "					<cbc:ID schemeID='UN/ECE 5305' schemeName='Tax Category Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>G</cbc:ID>\n"
                            + "					<cbc:Percent>0</cbc:Percent>\n"
                            + "					<cbc:TaxExemptionReasonCode listAgencyName='PE:SUNAT' listName='SUNAT:Codigo de Tipo de Afectación del IGV' listURI='urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo07'>" + Cpe_Detalle.getCOD_TIPO_OPERACION() + "</cbc:TaxExemptionReasonCode>\n"
                            + "					<cac:TaxScheme>\n"
                            + "						<cbc:ID schemeID='UN/ECE 5153' schemeName='Tax Scheme Identifier' schemeAgencyName='United Nations Economic Commission for Europe'>9995</cbc:ID>\n"
                            + "						<cbc:Name>EXP</cbc:Name>\n"
                            + "						<cbc:TaxTypeCode>FRE</cbc:TaxTypeCode>\n"
                            + "					</cac:TaxScheme>\n"
                            + "				</cac:TaxCategory>\n"
                            + "			</cac:TaxSubtotal>\n"
                            + "		</cac:TaxTotal>\n"
                            + "		<cac:Item>\n"
                            + "			<cbc:Description><![CDATA[" + Cpe_Detalle.getDESCRIPCION() + "]]></cbc:Description>\n"
                            + "			<cac:SellersItemIdentification>\n"
                            + "				<cbc:ID><![CDATA[" + Cpe_Detalle.getCODIGO() + "]]></cbc:ID>\n"
                            + "			</cac:SellersItemIdentification>\n";
                            if (!Cpe_Detalle.getCOD_SUNAT().equals("")) {
                                xmlCPE = xmlCPE + "<cac:CommodityClassification>\n"
			                    + "<cbc:ItemClassificationCode listID='UNSPSC' listAgencyName='GS1 US' \n"
			                    + "listName='Item Classification'>" + Cpe_Detalle.getCOD_SUNAT() + "</cbc:ItemClassificationCode>\n"
			                    + "</cac:CommodityClassification>";
                            }
                             xmlCPE = xmlCPE + "</cac:Item>\n"
                            + "		<cac:Price>\n"
                            + "			<cbc:PriceAmount currencyID='" + Cpe.getCOD_MONEDA() + "'>" + Cpe_Detalle.getPRECIO_SIN_IMPUESTO() + "</cbc:PriceAmount>\n"
                            + "		</cac:Price>\n"
                            + "	</cac:InvoiceLine>";
                }

            }
            xmlCPE = xmlCPE + "</Invoice>";

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlCPE)));
            // Use a Transformer for output
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(RutaXml + ".XML"));
            transformer.transform(source, result);

            System.out.println("\nXML DOM Created Successfully..");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
    
    public int GenerarXMLGUIA_REMISION(String RutaXml, _CpeGuiaRemisionBean Cpe, List<_CpeGuiaRemisionDetalleBean> lstCpe_Detalle) {
        try {
            String xmlCPE = "";
            xmlCPE = "<?xml version='1.0' encoding='iso-8859-1'?>\n" +
                "<DespatchAdvice xmlns:ds='http://www.w3.org/2000/09/xmldsig#' xmlns:cbc='urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2' xmlns:qdt='urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2' xmlns:ccts='urn:un:unece:uncefact:documentation:2' xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:udt='urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2' xmlns:ext='urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:cac='urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2' xmlns:sac='urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1' xmlns='urn:oasis:names:specification:ubl:schema:xsd:DespatchAdvice-2'>\n" +
                "  <ext:UBLExtensions>\n" +
                "    <ext:UBLExtension>\n" +
                "      <ext:ExtensionContent>\n"+
                "      </ext:ExtensionContent>\n" +
                "    </ext:UBLExtension>\n" +
                "  </ext:UBLExtensions>\n" +
                "  <cbc:UBLVersionID>2.1</cbc:UBLVersionID>\n" +
                "  <cbc:CustomizationID>1.0</cbc:CustomizationID>\n" +
                "  <cbc:ID>" + Cpe.getNRO_COMPROBANTE() + "</cbc:ID>\n" +
                "  <cbc:IssueDate>" + Cpe.getFECHA_DOCUMENTO() + "</cbc:IssueDate>\n" +
                "  <cbc:DespatchAdviceTypeCode>" + Cpe.getCOD_TIPO_DOCUMENTO() + "</cbc:DespatchAdviceTypeCode>\n" +
                "  <cbc:Note><![CDATA[" + Cpe.getNOTA() + "]]></cbc:Note>\n";
                    
            if (Cpe.getCOD_MOTIVO_TRASLADO().equals("08")) {
                xmlCPE = xmlCPE + " <cac:AdditionalDocumentReference>\n"+
                " 	<cbc:ID>"+ Cpe.getNUMERACION_DAM() +"</cbc:ID>\n"+
                " 	<cbc:DocumentTypeCode>"+ "01" +"</cbc:DocumentTypeCode>\n"+
             " </cac:AdditionalDocumentReference>\n";
                
                }
            
                if (Cpe.getFLG_ANULADO() == 1) {
                 xmlCPE = xmlCPE + "<cac:OrderReference>\n" +
                            "<cbc:ID>" + Cpe.getDOC_REFERENCIA_ANU() + "</cbc:ID>\n" +
                            "<cbc:OrderTypeCode>" + Cpe.getCOD_TIPO_DOC_REFANU() + "</cbc:OrderTypeCode>\n" +
                            "</cac:OrderReference>\n";
                }
    
                 xmlCPE = xmlCPE + "  <cac:DespatchSupplierParty>\n" +
                "    <cbc:CustomerAssignedAccountID schemeID='" + Cpe.getTIPO_DOCUMENTO_EMPRESA() + "'>" + Cpe.getNRO_DOCUMENTO_EMPRESA() + "</cbc:CustomerAssignedAccountID>\n" +
                "    <cac:Party>\n" +
                "      <cac:PartyLegalEntity>\n" +
                "        <cbc:RegistrationName><![CDATA[" + Cpe.getRAZON_SOCIAL_EMPRESA() + "]]></cbc:RegistrationName>\n" +
                "      </cac:PartyLegalEntity>\n" +
                "    </cac:Party>\n" +
                "  </cac:DespatchSupplierParty>\n" +
                "  <cac:DeliveryCustomerParty>\n" +
                "    <cbc:CustomerAssignedAccountID schemeID='" + Cpe.getTIPO_DOCUMENTO_CLIENTE() + "'>" + Cpe.getNRO_DOCUMENTO_CLIENTE() + "</cbc:CustomerAssignedAccountID>\n" +
                "    <cac:Party>\n" +
                "      <cac:PartyLegalEntity>\n" +
                "        <cbc:RegistrationName><![CDATA[" + Cpe.getRAZON_SOCIAL_CLIENTE() + "]]></cbc:RegistrationName>\n" +
                "      </cac:PartyLegalEntity>\n" +
                "    </cac:Party>\n" +
                "  </cac:DeliveryCustomerParty>\n";
                 if (Cpe.getCOD_MOTIVO_TRASLADO().equals("02") || Cpe.getCOD_MOTIVO_TRASLADO().equals("08")) {
                     xmlCPE = xmlCPE + "  <cac:SellerSupplierParty>\n" +
                             "    <cbc:CustomerAssignedAccountID schemeID='" + Cpe.getCOD_TIPO_DOC_PROVEEDOR() + "'>" + Cpe.getNRO_DOC_PROVEEDOR() + "</cbc:CustomerAssignedAccountID>\n" +
                             "    <cac:Party>\n" +
                             "      <cac:PartyLegalEntity>\n" +
                             "        <cbc:RegistrationName><![CDATA[" + Cpe.getDESCRIPCION_PROVEEDOR() + "]]></cbc:RegistrationName>\n" +
                             "      </cac:PartyLegalEntity>\n" +
                             "    </cac:Party>\n" +
                             "  </cac:SellerSupplierParty>\n";
                     }
                
                 xmlCPE = xmlCPE + " <cac:Shipment>\n" +
                "    <cbc:ID>" + Cpe.getITEM_ENVIO() + "</cbc:ID>\n" +
                "    <cbc:HandlingCode>" + Cpe.getCOD_MOTIVO_TRASLADO() + "</cbc:HandlingCode>\n" +
                "    <cbc:Information>" + Cpe.getDESCRIPCION_MOTIVO_TRASLADO() + "</cbc:Information>\n" +
                "    <cbc:GrossWeightMeasure unitCode='" + Cpe.getCOD_UND_PESO_BRUTO() + "'>" + Cpe.getPESO_BRUTO() + "</cbc:GrossWeightMeasure>\n" +
                "    <cbc:TotalTransportHandlingUnitQuantity>" + Cpe.getTOTAL_BULTOS() + "</cbc:TotalTransportHandlingUnitQuantity>\n" +
                "    <cac:ShipmentStage>\n" +
                "      <cbc:TransportModeCode>" + Cpe.getCOD_MODALIDAD_TRASLADO() + "</cbc:TransportModeCode>\n" +
                "      <cac:TransitPeriod>\n" +
                "        <cbc:StartDate>" + Cpe.getFECHA_INICIO() + "</cbc:StartDate>\n" +
                "      </cac:TransitPeriod>\n" +
                "      <cac:CarrierParty>\n" +
                "        <cac:PartyIdentification>\n" +
                "          <cbc:ID schemeID='" + Cpe.getTIPO_DOCUMENTO_TRANSPORTISTA() + "'>" + Cpe.getNRO_DOCUMENTO_TRANSPORTISTA() + "</cbc:ID>\n" +
                "        </cac:PartyIdentification>\n" +
                "        <cac:PartyName>\n" +
                "          <cbc:Name><![CDATA[" + Cpe.getRAZON_SOCIAL_TRANSPORTISTA() + "]]></cbc:Name>\n" +
                "        </cac:PartyName>\n" +
                "      </cac:CarrierParty>\n" +
                "      <cac:TransportMeans>\n" +
                "        <cac:RoadTransport>\n" +
                "          <cbc:LicensePlateID>" + Cpe.getPLACA_VEHICULO() + "</cbc:LicensePlateID>\n" +
                "        </cac:RoadTransport>\n" +
                "      </cac:TransportMeans>\n" +
                "      <cac:DriverPerson>\n" +
                "        <cbc:ID schemeID='" + Cpe.getCOD_TIPO_DOC_CHOFER() + "'>" + Cpe.getNRO_DOC_CHOFER() + "</cbc:ID>\n" +
                "      </cac:DriverPerson>\n" +
                "    </cac:ShipmentStage>\n" +
                "    <cac:Delivery>\n" +
                "      <cac:DeliveryAddress>\n" +
                "        <cbc:ID>" + Cpe.getCOD_UBIGEO_DESTINO() + "</cbc:ID>\n" +
                "        <cbc:StreetName><![CDATA[" + Cpe.getDIRECCION_DESTINO() + "]]></cbc:StreetName>\n" +
                "      </cac:DeliveryAddress>\n" +
                "    </cac:Delivery>\n";
                if (!Cpe.getPLACA_CARRETA().equals("")) {
                xmlCPE = xmlCPE + "<cac:TransportHandlingUnit>\n" +
                "      <cbc:ID>" + Cpe.getPLACA_VEHICULO() + "</cbc:ID>\n" +
                "      <cac:TransportEquipment>\n" +
                "        <cbc:ID>" + Cpe.getPLACA_CARRETA() + "</cbc:ID>\n" +
                "      </cac:TransportEquipment>\n" +
                "    </cac:TransportHandlingUnit>\n";
                }
                if (Cpe.getCOD_MOTIVO_TRASLADO().equals("08")) {
                xmlCPE = xmlCPE + "<cac:TransportHandlingUnit>\n" +
                "      <cac:TransportEquipment>\n" +
                "        <cbc:ID>" + Cpe.getNRO_CONTENEDOR() + "</cbc:ID>\n" +
                "      </cac:TransportEquipment>\n" +
                "    </cac:TransportHandlingUnit>\n";
                }
               xmlCPE = xmlCPE + "<cac:OriginAddress>\n" +
                "      <cbc:ID>" + Cpe.getCOD_UBIGEO_ORIGEN() + "</cbc:ID>\n" +
                "      <cbc:StreetName><![CDATA[" + Cpe.getDIRECCION_ORIGEN() + "]]></cbc:StreetName>\n" +
                "    </cac:OriginAddress>\n" +
                "  </cac:Shipment>\n";
               
              for (int i = 0; i < lstCpe_Detalle.size(); i++) {
                _CpeGuiaRemisionDetalleBean Cpe_Detalle = lstCpe_Detalle.get(i);
                xmlCPE = xmlCPE + "<cac:DespatchLine>\n" +
                "    <cbc:ID>" + Cpe_Detalle.getITEM() + "</cbc:ID>\n" +
                "    <cbc:DeliveredQuantity unitCode='" + Cpe_Detalle.getUNIDAD_MEDIDA() + "'>" + Cpe_Detalle.getCANTIDAD() + "</cbc:DeliveredQuantity>\n" +
                "    <cac:OrderLineReference>\n" +
                "      <cbc:LineID>" + Cpe_Detalle.getORDER_ITEM() + "</cbc:LineID>\n" +
                "    </cac:OrderLineReference>\n" +
                "    <cac:Item>\n" +
                "      <cbc:Name><![CDATA[" + Cpe_Detalle.getDESCRIPCION() + "]]></cbc:Name>\n" +
                "      <cac:SellersItemIdentification>\n" +
                "        <cbc:ID>" + Cpe_Detalle.getCODIGO() + "</cbc:ID>\n" +
                "      </cac:SellersItemIdentification>\n" +
                "    </cac:Item>\n" +
                "  </cac:DespatchLine>\n";
              }
              xmlCPE = xmlCPE + "</DespatchAdvice>";

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlCPE)));
            // Use a Transformer for output
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(RutaXml + ".XML"));
            transformer.transform(source, result);

            System.out.println("\nXML DOM Created Successfully..");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    public int GenerarXMLCPE_RT(String RutaXml, _Cpe_RetencionPercepcionBean Cpe_Retencion, List<_Cpe_RetencionPercepcion_DetalleBean> lstCpe_Retencion_Detalle) {
        DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        try {
            icBuilder = icFactory.newDocumentBuilder();
            Document doc = icBuilder.newDocument();

            doc.setXmlVersion("1.0");
            Element mainRootElement = doc.createElementNS("urn:sunat:names:specification:ubl:peru:schema:xsd:Retention-1", "Retention");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:cac", "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:cbc", "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:ccts", "urn:un:unece:uncefact:documentation:2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:ext", "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:qdt", "urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:sac", "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:udt", "urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");

            doc.appendChild(mainRootElement);

            /*
             =====================EXTENCION PARA COLOCAR LA FIRMA====================
             */
            Element UBLExtensions1 = doc.createElement("ext:UBLExtensions");
            Element UBLExtensionFirma = doc.createElement("ext:UBLExtension");
            Element ExtensionContentFirma = doc.createElement("ext:ExtensionContent");
            UBLExtensionFirma.appendChild(ExtensionContentFirma);
            UBLExtensions1.appendChild(UBLExtensionFirma);
            mainRootElement.appendChild(UBLExtensions1);

            /*
             ==================DATOS GENERALES (VERSION,NRO DOCUMENTO,FECHA,TIPO DOCUMENTO,MONEDA)======================
             */
            Element UBLVersionID = doc.createElement("cbc:UBLVersionID");
            UBLVersionID.appendChild(doc.createTextNode("2.0"));
            mainRootElement.appendChild(UBLVersionID);

            Element CustomizationID = doc.createElement("cbc:CustomizationID");
            CustomizationID.appendChild(doc.createTextNode("1.0"));
            mainRootElement.appendChild(CustomizationID);

            /*
             ==================SIGNATURE (DATOS EMPRESA Y OTROS)======================
             */
            Element Signature = doc.createElement("cac:Signature");
            Element IDSignature = doc.createElement("cbc:ID");
            IDSignature.appendChild(doc.createTextNode(Cpe_Retencion.getNRO_COMPROBANTE()));
            Signature.appendChild(IDSignature);

            Element SignatoryParty = doc.createElement("cac:SignatoryParty");

            //==============================RUC/DNI DE LA EMPRESA EMISORA=============================
            Element PartyIdentification = doc.createElement("cac:PartyIdentification");
            Element ID_PartyIdentification = doc.createElement("cbc:ID");//RUC DEL EMISOR
            ID_PartyIdentification.appendChild(doc.createTextNode(Cpe_Retencion.getNRO_DOCUMENTO_EMPRESA()));//RUC DEL EMISOR
            PartyIdentification.appendChild(ID_PartyIdentification);
            SignatoryParty.appendChild(PartyIdentification);
            //==============================RAZON SOCIAL DE LA EMPRESA EMISORA=============================
            Element PartyName = doc.createElement("cac:PartyName");
            Element Name = doc.createElement("cbc:Name");
            Name.appendChild(doc.createCDATASection(Cpe_Retencion.getRAZON_SOCIAL_EMPRESA()));
            PartyName.appendChild(Name);
            SignatoryParty.appendChild(PartyName);

            //=========================ADICIONAMOS RUC/RAZON SOCIAL=========================
            Signature.appendChild(SignatoryParty);

            //==============================IDENTIFICACION DEL DOCUMENTO AGREGANDO #=============================
            Element DigitalSignatureAttachment = doc.createElement("cac:DigitalSignatureAttachment");
            Element ExternalReference = doc.createElement("cac:ExternalReference");
            Element URI = doc.createElement("cbc:URI");
            URI.appendChild(doc.createTextNode(Cpe_Retencion.getNRO_COMPROBANTE()));
            ExternalReference.appendChild(URI);
            DigitalSignatureAttachment.appendChild(ExternalReference);
            Signature.appendChild(DigitalSignatureAttachment);

            mainRootElement.appendChild(Signature);

            //======================================INORMACION DEL COMPROBANTE Y DE LA EMPRESA=======================================
            Element IDNRO_DOCUMENTO = doc.createElement("cbc:ID");
            IDNRO_DOCUMENTO.appendChild(doc.createTextNode(Cpe_Retencion.getNRO_COMPROBANTE()));
            mainRootElement.appendChild(IDNRO_DOCUMENTO);

            Element IssueDate = doc.createElement("cbc:IssueDate");
            IssueDate.appendChild(doc.createTextNode(Cpe_Retencion.getFECHA_DOCUMENTO()));
            mainRootElement.appendChild(IssueDate);

            /////////////////////////////////////////////////////////////////////////////////////////
            //=======================DATOS DE LA EMPRESA=======================                        
            Element AgentParty = doc.createElement("cac:AgentParty");
            //=========================TIPO Y NRO DCUMENTO=========================
            Element PartyIdentificationEmpresa = doc.createElement("cac:PartyIdentification");
            Element IDPartyEmpresa = doc.createElement("cbc:ID");
            IDPartyEmpresa.setAttribute("schemeID", Cpe_Retencion.getTIPO_DOCUMENTO_EMPRESA());//TIPO DOCUMENTO 
            IDPartyEmpresa.appendChild(doc.createTextNode(Cpe_Retencion.getNRO_DOCUMENTO_EMPRESA()));//RUC            
            PartyIdentificationEmpresa.appendChild(IDPartyEmpresa);
            AgentParty.appendChild(PartyIdentificationEmpresa);

            //=========================RAZON SOCIAL=========================
            Element PartyNameEmpresa = doc.createElement("cac:PartyName");
            Element NameEmpresa = doc.createElement("cbc:Name");
            NameEmpresa.appendChild(doc.createCDATASection(Cpe_Retencion.getNOMBRE_COMERCIAL_EMPRESA()));//RUC            
            PartyNameEmpresa.appendChild(NameEmpresa);
            AgentParty.appendChild(PartyNameEmpresa);

            Element PostalAddress = doc.createElement("cac:PostalAddress");

            Element ID_PostalAddress = doc.createElement("cbc:ID");
            ID_PostalAddress.appendChild(doc.createTextNode(Cpe_Retencion.getCOD_UBIGEO_EMPRESA()));//CODIGO UBIGEO
            PostalAddress.appendChild(ID_PostalAddress);

            Element StreetName = doc.createElement("cbc:StreetName");
            StreetName.appendChild(doc.createCDATASection(Cpe_Retencion.getDIRECCION_EMPRESA()));//
            PostalAddress.appendChild(StreetName);

            Element CitySubdivisionName = doc.createElement("cbc:CitySubdivisionName");
            CitySubdivisionName.appendChild(doc.createCDATASection(""));//URBANIZAION, ZONA, AAHH
            PostalAddress.appendChild(CitySubdivisionName);

            Element CityName = doc.createElement("cbc:CityName");
            CityName.appendChild(doc.createCDATASection(Cpe_Retencion.getDEPARTAMENTO_EMPRESA()));//DEPARTAMENTO
            PostalAddress.appendChild(CityName);

            Element CountrySubentity = doc.createElement("cbc:CountrySubentity");
            CountrySubentity.appendChild(doc.createCDATASection(Cpe_Retencion.getPROVINCIA_EMPRESA()));//PROVINCIA
            PostalAddress.appendChild(CountrySubentity);

            Element District = doc.createElement("cbc:District");
            District.appendChild(doc.createCDATASection(Cpe_Retencion.getDISTRITO_EMPRESA()));//DISTRITO
            PostalAddress.appendChild(District);

            Element Country = doc.createElement("cac:Country");
            Element IdentificationCode = doc.createElement("cbc:IdentificationCode");
            IdentificationCode.appendChild(doc.createTextNode(Cpe_Retencion.getCOD_PAIS_EMPRESA()));//
            Country.appendChild(IdentificationCode);
            PostalAddress.appendChild(Country);

            AgentParty.appendChild(PostalAddress);

            //==============================RAZON SOCIAL============================
            Element PartyLegalEntity = doc.createElement("cac:PartyLegalEntity");
            Element RegistrationName = doc.createElement("cbc:RegistrationName");
            RegistrationName.appendChild(doc.createCDATASection(Cpe_Retencion.getRAZON_SOCIAL_EMPRESA()));//RAZON SOCIAL
            PartyLegalEntity.appendChild(RegistrationName);
            AgentParty.appendChild(PartyLegalEntity);

            mainRootElement.appendChild(AgentParty);
            //================================FIN DATOS DE LA EMPRESA=================================

            /////////////////////////////////////////////////////////////////////////////////////////
            //=======================DATOS DEL PROVEEDOR=======================                        
            Element ReceiverParty = doc.createElement("cac:ReceiverParty");
            //=========================TIPO Y NRO DCUMENTO=========================
            Element PartyIdentificationProveedor = doc.createElement("cac:PartyIdentification");
            Element IDPartyProveedor = doc.createElement("cbc:ID");
            IDPartyProveedor.setAttribute("schemeID", Cpe_Retencion.getTIPO_DOCUMENTO_PROVEEDOR());//TIPO DOCUMENTO 
            IDPartyProveedor.appendChild(doc.createTextNode(Cpe_Retencion.getNRO_DOCUMENTO_PROVEEDOR()));//RUC            
            PartyIdentificationProveedor.appendChild(IDPartyProveedor);
            ReceiverParty.appendChild(PartyIdentificationProveedor);

            //=========================RAZON SOCIAL=========================
            Element PartyNameProveedor = doc.createElement("cac:PartyName");
            Element NameProveedor = doc.createElement("cbc:Name");
            NameProveedor.appendChild(doc.createCDATASection(Cpe_Retencion.getNOMBRE_COMERCIAL_PROVEEDOR()));//RUC            
            PartyNameProveedor.appendChild(NameProveedor);
            ReceiverParty.appendChild(PartyNameProveedor);

            Element PostalAddressProveedor = doc.createElement("cac:PostalAddress");

            Element ID_PostalAddressProveedor = doc.createElement("cbc:ID");
            ID_PostalAddressProveedor.appendChild(doc.createTextNode(Cpe_Retencion.getCOD_UBIGEO_PROVEEDOR()));//CODIGO UBIGEO
            PostalAddressProveedor.appendChild(ID_PostalAddressProveedor);

            Element StreetNameProveedor = doc.createElement("cbc:StreetName");
            StreetNameProveedor.appendChild(doc.createCDATASection(Cpe_Retencion.getDIRECCION_PROVEEDOR()));//
            PostalAddressProveedor.appendChild(StreetNameProveedor);

            Element CitySubdivisionNameProveedor = doc.createElement("cbc:CitySubdivisionName");
            CitySubdivisionNameProveedor.appendChild(doc.createCDATASection(""));//URBANIZAION, ZONA, AAHH
            PostalAddressProveedor.appendChild(CitySubdivisionNameProveedor);

            Element CityNameProveedor = doc.createElement("cbc:CityName");
            CityNameProveedor.appendChild(doc.createCDATASection(Cpe_Retencion.getDEPARTAMENTO_PROVEEDOR()));//DEPARTAMENTO
            PostalAddressProveedor.appendChild(CityNameProveedor);

            Element CountrySubentityProveedor = doc.createElement("cbc:CountrySubentity");
            CountrySubentityProveedor.appendChild(doc.createCDATASection(Cpe_Retencion.getPROVINCIA_PROVEEDOR()));//PROVINCIA
            PostalAddressProveedor.appendChild(CountrySubentityProveedor);

            Element DistrictProveedor = doc.createElement("cbc:District");
            DistrictProveedor.appendChild(doc.createCDATASection(Cpe_Retencion.getDISTRITO_PROVEEDOR()));//DISTRITO
            PostalAddressProveedor.appendChild(DistrictProveedor);

            Element CountryProveedor = doc.createElement("cac:Country");
            Element IdentificationCodeProveedor = doc.createElement("cbc:IdentificationCode");
            IdentificationCodeProveedor.appendChild(doc.createTextNode(Cpe_Retencion.getPAIS_PROVEEDOR()));//
            CountryProveedor.appendChild(IdentificationCodeProveedor);
            PostalAddressProveedor.appendChild(CountryProveedor);

            ReceiverParty.appendChild(PostalAddressProveedor);

            //==============================RAZON SOCIAL============================
            Element PartyLegalEntityProveedor = doc.createElement("cac:PartyLegalEntity");
            Element RegistrationNameProveedor = doc.createElement("cbc:RegistrationName");
            RegistrationNameProveedor.appendChild(doc.createCDATASection(Cpe_Retencion.getRAZON_SOCIAL_PROVEEDOR()));//RAZON SOCIAL
            PartyLegalEntityProveedor.appendChild(RegistrationNameProveedor);
            ReceiverParty.appendChild(PartyLegalEntityProveedor);

            mainRootElement.appendChild(ReceiverParty);

            //==============================TOTALES PORCENTAJES E IMPUESTOS=============================   
            Element SUNATRetentionSystemCode = doc.createElement("sac:SUNATRetentionSystemCode");
            SUNATRetentionSystemCode.appendChild(doc.createTextNode(Cpe_Retencion.getTIPO_RETENCION()));
            mainRootElement.appendChild(SUNATRetentionSystemCode);

            Element SUNATRetentionPercent = doc.createElement("sac:SUNATRetentionPercent");
            SUNATRetentionPercent.appendChild(doc.createTextNode(Double.toString(Cpe_Retencion.getPORCENTAJE_RETENCION())));
            mainRootElement.appendChild(SUNATRetentionPercent);

            Element Note = doc.createElement("cbc:Note");
            Note.appendChild(doc.createTextNode(Cpe_Retencion.getNOTA()));
            mainRootElement.appendChild(Note);

            Element TotalInvoiceAmount = doc.createElement("cbc:TotalInvoiceAmount");
            TotalInvoiceAmount.setAttribute("currencyID", Cpe_Retencion.getMONEDA());
            TotalInvoiceAmount.appendChild(doc.createTextNode(Double.toString(Cpe_Retencion.getTOTAL_RETENCION())));
            mainRootElement.appendChild(TotalInvoiceAmount);

            Element SUNATTotalPaid = doc.createElement("sac:SUNATTotalPaid");
            SUNATTotalPaid.setAttribute("currencyID", Cpe_Retencion.getMONEDA());
            SUNATTotalPaid.appendChild(doc.createTextNode(Double.toString(Cpe_Retencion.getNETO_RETENCION())));
            mainRootElement.appendChild(SUNATTotalPaid);
            //=========================================================================================
            //=================================INVOICE LINE===================================
            //=================================DETALLE DE PRODUCTOS===================================
            for (int i = 0; i < lstCpe_Retencion_Detalle.size(); i++) {
                _Cpe_RetencionPercepcion_DetalleBean Cpe_Retencion_Detalle = lstCpe_Retencion_Detalle.get(i);
                Element SUNATRetentionDocumentReference = doc.createElement("sac:SUNATRetentionDocumentReference");

                Element IDCOMPROBANTE = doc.createElement("cbc:ID");
                IDCOMPROBANTE.setAttribute("schemeID", Cpe_Retencion_Detalle.getCOD_TIPO_DOCUMENTO());
                IDCOMPROBANTE.appendChild(doc.createTextNode(Cpe_Retencion_Detalle.getNRO_DOCUMENTO()));
                SUNATRetentionDocumentReference.appendChild(IDCOMPROBANTE);

                Element IssueDateCOMPROBANTE = doc.createElement("cbc:IssueDate");
                IssueDateCOMPROBANTE.appendChild(doc.createTextNode(Cpe_Retencion_Detalle.getFECHA_DOCUMENTO()));
                SUNATRetentionDocumentReference.appendChild(IssueDateCOMPROBANTE);

                Element TotalInvoiceAmountCOMPROBANTE = doc.createElement("cbc:TotalInvoiceAmount");
                TotalInvoiceAmountCOMPROBANTE.setAttribute("currencyID", Cpe_Retencion_Detalle.getCOD_MONEDA());
                TotalInvoiceAmountCOMPROBANTE.appendChild(doc.createTextNode(Double.toString(Cpe_Retencion_Detalle.getMONTO_TOTAL())));
                SUNATRetentionDocumentReference.appendChild(TotalInvoiceAmountCOMPROBANTE);

                //==========================DATOS REFERENTES AL PAGO=========================
                Element PaymentPAGO = doc.createElement("cac:Payment");

                Element IDPAGO = doc.createElement("cbc:ID");
                IDPAGO.appendChild(doc.createTextNode(Cpe_Retencion_Detalle.getNRO_DOC_PAGO()));
                PaymentPAGO.appendChild(IDPAGO);

                Element PaidAmount = doc.createElement("cbc:PaidAmount");
                PaidAmount.setAttribute("currencyID", Cpe_Retencion_Detalle.getCOD_MONEDA());
                PaidAmount.appendChild(doc.createTextNode(Double.toString(Cpe_Retencion_Detalle.getMONTO_TOTAL())));
                PaymentPAGO.appendChild(PaidAmount);

                Element PaidDate = doc.createElement("cbc:PaidDate");
                PaidDate.appendChild(doc.createTextNode(Cpe_Retencion_Detalle.getFECHA_PAGO()));
                PaymentPAGO.appendChild(PaidDate);

                SUNATRetentionDocumentReference.appendChild(PaymentPAGO);

                //==========================INFORMACION DE LA RETENCION=========================
                Element SUNATRetentionInformation = doc.createElement("sac:SUNATRetentionInformation");

                Element SUNATRetentionAmount = doc.createElement("sac:SUNATRetentionAmount");
                SUNATRetentionAmount.setAttribute("currencyID", Cpe_Retencion_Detalle.getMONEDA_RETENIDA());
                SUNATRetentionAmount.appendChild(doc.createTextNode(Double.toString(Cpe_Retencion_Detalle.getMONTO_RETENIDO())));
                SUNATRetentionInformation.appendChild(SUNATRetentionAmount);

                Element SUNATRetentionDate = doc.createElement("sac:SUNATRetentionDate");
                SUNATRetentionDate.appendChild(doc.createTextNode(Cpe_Retencion_Detalle.getFECHA_RETENIDA()));
                SUNATRetentionInformation.appendChild(SUNATRetentionDate);

                Element SUNATNetTotalPaid = doc.createElement("sac:SUNATNetTotalPaid");
                SUNATNetTotalPaid.setAttribute("currencyID", Cpe_Retencion_Detalle.getMONEDA_PAGO_NETO());
                SUNATNetTotalPaid.appendChild(doc.createTextNode(Double.toString(Cpe_Retencion_Detalle.getMONTO_PAGO_NETO())));
                SUNATRetentionInformation.appendChild(SUNATNetTotalPaid);

                SUNATRetentionDocumentReference.appendChild(SUNATRetentionInformation);

                mainRootElement.appendChild(SUNATRetentionDocumentReference);
            }//FIN DE BUCLE

            // output DOM XML to console 
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
            StreamResult console = new StreamResult(System.out);
            transformer.transform(source, console);

            DOMSource domSource = new DOMSource(doc);
            //TransformerFactory tFactory = TransformerFactory.newInstance();
            FileWriter out = new FileWriter(RutaXml + ".XML");
            //Transformer transformer = tFactory.newTransformer();
            //transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(domSource, new StreamResult(out));

            System.out.println("\nXML DOM Created Successfully..");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    public int GenerarXMLCPE_PC(String RutaXml, _Cpe_RetencionPercepcionBean Cpe_Retencion, List<_Cpe_RetencionPercepcion_DetalleBean> lstCpe_Retencion_Detalle) {
        DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        try {
            icBuilder = icFactory.newDocumentBuilder();
            Document doc = icBuilder.newDocument();

            doc.setXmlVersion("1.0");
            Element mainRootElement = doc.createElementNS("urn:sunat:names:specification:ubl:peru:schema:xsd:Perception-1", "Perception");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:cac", "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:cbc", "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:ccts", "urn:un:unece:uncefact:documentation:2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:ext", "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:qdt", "urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:sac", "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:udt", "urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");

            doc.appendChild(mainRootElement);

            /*
             =====================EXTENCION PARA COLOCAR LA FIRMA====================
             */
            Element UBLExtensions1 = doc.createElement("ext:UBLExtensions");
            Element UBLExtensionFirma = doc.createElement("ext:UBLExtension");
            Element ExtensionContentFirma = doc.createElement("ext:ExtensionContent");
            UBLExtensionFirma.appendChild(ExtensionContentFirma);
            UBLExtensions1.appendChild(UBLExtensionFirma);
            mainRootElement.appendChild(UBLExtensions1);

            /*
             ==================DATOS GENERALES (VERSION,NRO DOCUMENTO,FECHA,TIPO DOCUMENTO,MONEDA)======================
             */
            Element UBLVersionID = doc.createElement("cbc:UBLVersionID");
            UBLVersionID.appendChild(doc.createTextNode("2.0"));
            mainRootElement.appendChild(UBLVersionID);

            Element CustomizationID = doc.createElement("cbc:CustomizationID");
            CustomizationID.appendChild(doc.createTextNode("1.0"));
            mainRootElement.appendChild(CustomizationID);

            /*
             ==================SIGNATURE (DATOS EMPRESA Y OTROS)======================
             */
            Element Signature = doc.createElement("cac:Signature");
            Element IDSignature = doc.createElement("cbc:ID");
            IDSignature.appendChild(doc.createTextNode(Cpe_Retencion.getNRO_COMPROBANTE()));
            Signature.appendChild(IDSignature);

            Element SignatoryParty = doc.createElement("cac:SignatoryParty");

            //==============================RUC/DNI DE LA EMPRESA EMISORA=============================
            Element PartyIdentification = doc.createElement("cac:PartyIdentification");
            Element ID_PartyIdentification = doc.createElement("cbc:ID");//RUC DEL EMISOR
            ID_PartyIdentification.appendChild(doc.createTextNode(Cpe_Retencion.getNRO_DOCUMENTO_EMPRESA()));//RUC DEL EMISOR
            PartyIdentification.appendChild(ID_PartyIdentification);
            SignatoryParty.appendChild(PartyIdentification);
            //==============================RAZON SOCIAL DE LA EMPRESA EMISORA=============================
            Element PartyName = doc.createElement("cac:PartyName");
            Element Name = doc.createElement("cbc:Name");
            Name.appendChild(doc.createCDATASection(Cpe_Retencion.getRAZON_SOCIAL_EMPRESA()));
            PartyName.appendChild(Name);
            SignatoryParty.appendChild(PartyName);

            //=========================ADICIONAMOS RUC/RAZON SOCIAL=========================
            Signature.appendChild(SignatoryParty);

            //==============================IDENTIFICACION DEL DOCUMENTO AGREGANDO #=============================
            Element DigitalSignatureAttachment = doc.createElement("cac:DigitalSignatureAttachment");
            Element ExternalReference = doc.createElement("cac:ExternalReference");
            Element URI = doc.createElement("cbc:URI");
            URI.appendChild(doc.createTextNode(Cpe_Retencion.getNRO_COMPROBANTE()));
            ExternalReference.appendChild(URI);
            DigitalSignatureAttachment.appendChild(ExternalReference);
            Signature.appendChild(DigitalSignatureAttachment);

            mainRootElement.appendChild(Signature);

            //======================================INORMACION DEL COMPROBANTE Y DE LA EMPRESA=======================================
            Element IDNRO_DOCUMENTO = doc.createElement("cbc:ID");
            IDNRO_DOCUMENTO.appendChild(doc.createTextNode(Cpe_Retencion.getNRO_COMPROBANTE()));
            mainRootElement.appendChild(IDNRO_DOCUMENTO);

            Element IssueDate = doc.createElement("cbc:IssueDate");
            IssueDate.appendChild(doc.createTextNode(Cpe_Retencion.getFECHA_DOCUMENTO()));
            mainRootElement.appendChild(IssueDate);

            /////////////////////////////////////////////////////////////////////////////////////////
            //=======================DATOS DE LA EMPRESA=======================                        
            Element AgentParty = doc.createElement("cac:AgentParty");
            //=========================TIPO Y NRO DCUMENTO=========================
            Element PartyIdentificationEmpresa = doc.createElement("cac:PartyIdentification");
            Element IDPartyEmpresa = doc.createElement("cbc:ID");
            IDPartyEmpresa.setAttribute("schemeID", Cpe_Retencion.getTIPO_DOCUMENTO_EMPRESA());//TIPO DOCUMENTO 
            IDPartyEmpresa.appendChild(doc.createTextNode(Cpe_Retencion.getNRO_DOCUMENTO_EMPRESA()));//RUC            
            PartyIdentificationEmpresa.appendChild(IDPartyEmpresa);
            AgentParty.appendChild(PartyIdentificationEmpresa);

            //=========================RAZON SOCIAL=========================
            Element PartyNameEmpresa = doc.createElement("cac:PartyName");
            Element NameEmpresa = doc.createElement("cbc:Name");
            NameEmpresa.appendChild(doc.createCDATASection(Cpe_Retencion.getNOMBRE_COMERCIAL_EMPRESA()));//RUC            
            PartyNameEmpresa.appendChild(NameEmpresa);
            AgentParty.appendChild(PartyNameEmpresa);

            Element PostalAddress = doc.createElement("cac:PostalAddress");

            Element ID_PostalAddress = doc.createElement("cbc:ID");
            ID_PostalAddress.appendChild(doc.createTextNode(Cpe_Retencion.getCOD_UBIGEO_EMPRESA()));//CODIGO UBIGEO
            PostalAddress.appendChild(ID_PostalAddress);

            Element StreetName = doc.createElement("cbc:StreetName");
            StreetName.appendChild(doc.createCDATASection(Cpe_Retencion.getDIRECCION_EMPRESA()));//
            PostalAddress.appendChild(StreetName);

            Element CitySubdivisionName = doc.createElement("cbc:CitySubdivisionName");
            CitySubdivisionName.appendChild(doc.createCDATASection(""));//URBANIZAION, ZONA, AAHH
            PostalAddress.appendChild(CitySubdivisionName);

            Element CityName = doc.createElement("cbc:CityName");
            CityName.appendChild(doc.createCDATASection(Cpe_Retencion.getDEPARTAMENTO_EMPRESA()));//DEPARTAMENTO
            PostalAddress.appendChild(CityName);

            Element CountrySubentity = doc.createElement("cbc:CountrySubentity");
            CountrySubentity.appendChild(doc.createCDATASection(Cpe_Retencion.getPROVINCIA_EMPRESA()));//PROVINCIA
            PostalAddress.appendChild(CountrySubentity);

            Element District = doc.createElement("cbc:District");
            District.appendChild(doc.createCDATASection(Cpe_Retencion.getDISTRITO_EMPRESA()));//DISTRITO
            PostalAddress.appendChild(District);

            Element Country = doc.createElement("cac:Country");
            Element IdentificationCode = doc.createElement("cbc:IdentificationCode");
            IdentificationCode.appendChild(doc.createTextNode(Cpe_Retencion.getCOD_PAIS_EMPRESA()));//
            Country.appendChild(IdentificationCode);
            PostalAddress.appendChild(Country);

            AgentParty.appendChild(PostalAddress);

            //==============================RAZON SOCIAL============================
            Element PartyLegalEntity = doc.createElement("cac:PartyLegalEntity");
            Element RegistrationName = doc.createElement("cbc:RegistrationName");
            RegistrationName.appendChild(doc.createCDATASection(Cpe_Retencion.getRAZON_SOCIAL_EMPRESA()));//RAZON SOCIAL
            PartyLegalEntity.appendChild(RegistrationName);
            AgentParty.appendChild(PartyLegalEntity);

            mainRootElement.appendChild(AgentParty);
            //================================FIN DATOS DE LA EMPRESA=================================

            /////////////////////////////////////////////////////////////////////////////////////////
            //=======================DATOS DEL CLIENTE=======================                        
            Element ReceiverParty = doc.createElement("cac:ReceiverParty");
            //=========================TIPO Y NRO DCUMENTO=========================
            Element PartyIdentificationProveedor = doc.createElement("cac:PartyIdentification");
            Element IDPartyProveedor = doc.createElement("cbc:ID");
            IDPartyProveedor.setAttribute("schemeID", Cpe_Retencion.getTIPO_DOCUMENTO_CLIENTE());//TIPO DOCUMENTO 
            IDPartyProveedor.appendChild(doc.createTextNode(Cpe_Retencion.getNRO_DOCUMENTO_CLIENTE()));//RUC            
            PartyIdentificationProveedor.appendChild(IDPartyProveedor);
            ReceiverParty.appendChild(PartyIdentificationProveedor);

            //=========================RAZON SOCIAL=========================
            Element PartyNameProveedor = doc.createElement("cac:PartyName");
            Element NameProveedor = doc.createElement("cbc:Name");
            NameProveedor.appendChild(doc.createCDATASection(Cpe_Retencion.getNOMBRE_COMERCIAL_CLIENTE()));//RUC            
            PartyNameProveedor.appendChild(NameProveedor);
            ReceiverParty.appendChild(PartyNameProveedor);

            Element PostalAddressProveedor = doc.createElement("cac:PostalAddress");

            Element ID_PostalAddressProveedor = doc.createElement("cbc:ID");
            ID_PostalAddressProveedor.appendChild(doc.createTextNode(Cpe_Retencion.getCOD_UBIGEO_CLIENTE()));//CODIGO UBIGEO
            PostalAddressProveedor.appendChild(ID_PostalAddressProveedor);

            Element StreetNameProveedor = doc.createElement("cbc:StreetName");
            StreetNameProveedor.appendChild(doc.createCDATASection(Cpe_Retencion.getDIRECCION_CLIENTE()));//
            PostalAddressProveedor.appendChild(StreetNameProveedor);

            Element CitySubdivisionNameProveedor = doc.createElement("cbc:CitySubdivisionName");
            CitySubdivisionNameProveedor.appendChild(doc.createCDATASection(""));//URBANIZAION, ZONA, AAHH
            PostalAddressProveedor.appendChild(CitySubdivisionNameProveedor);

            Element CityNameProveedor = doc.createElement("cbc:CityName");
            CityNameProveedor.appendChild(doc.createCDATASection(Cpe_Retencion.getDEPARTAMENTO_CLIENTE()));//DEPARTAMENTO
            PostalAddressProveedor.appendChild(CityNameProveedor);

            Element CountrySubentityProveedor = doc.createElement("cbc:CountrySubentity");
            CountrySubentityProveedor.appendChild(doc.createCDATASection(Cpe_Retencion.getPROVINCIA_CLIENTE()));//PROVINCIA
            PostalAddressProveedor.appendChild(CountrySubentityProveedor);

            Element DistrictProveedor = doc.createElement("cbc:District");
            DistrictProveedor.appendChild(doc.createCDATASection(Cpe_Retencion.getDISTRITO_CLIENTE()));//DISTRITO
            PostalAddressProveedor.appendChild(DistrictProveedor);

            Element CountryProveedor = doc.createElement("cac:Country");
            Element IdentificationCodeProveedor = doc.createElement("cbc:IdentificationCode");
            IdentificationCodeProveedor.appendChild(doc.createTextNode(Cpe_Retencion.getPAIS_CLIENTE()));//
            CountryProveedor.appendChild(IdentificationCodeProveedor);
            PostalAddressProveedor.appendChild(CountryProveedor);

            ReceiverParty.appendChild(PostalAddressProveedor);

            //==============================RAZON SOCIAL============================
            Element PartyLegalEntityProveedor = doc.createElement("cac:PartyLegalEntity");
            Element RegistrationNameProveedor = doc.createElement("cbc:RegistrationName");
            RegistrationNameProveedor.appendChild(doc.createCDATASection(Cpe_Retencion.getRAZON_SOCIAL_CLIENTE()));//RAZON SOCIAL
            PartyLegalEntityProveedor.appendChild(RegistrationNameProveedor);
            ReceiverParty.appendChild(PartyLegalEntityProveedor);

            mainRootElement.appendChild(ReceiverParty);

            //==============================TOTALES PORCENTAJES E IMPUESTOS=============================   
            Element SUNATPerceptionSystemCode = doc.createElement("sac:SUNATPerceptionSystemCode");
            SUNATPerceptionSystemCode.appendChild(doc.createTextNode(Cpe_Retencion.getTIPO_PERCEPCION()));
            mainRootElement.appendChild(SUNATPerceptionSystemCode);
            //<sac:SUNATPerceptionSystemCode>01</sac:SUNATPerceptionSystemCode>             

            Element SUNATPerceptionPercent = doc.createElement("sac:SUNATPerceptionPercent");
            SUNATPerceptionPercent.appendChild(doc.createTextNode(Double.toString(Cpe_Retencion.getPORCENTAJE_PERCEPCION())));
            mainRootElement.appendChild(SUNATPerceptionPercent);
            //<sac:SUNATPerceptionPercent>2</sac:SUNATPerceptionPercent> 

            Element Note = doc.createElement("cbc:Note");
            Note.appendChild(doc.createTextNode(Cpe_Retencion.getNOTA()));
            mainRootElement.appendChild(Note);

            Element TotalInvoiceAmount = doc.createElement("cbc:TotalInvoiceAmount");
            TotalInvoiceAmount.setAttribute("currencyID", Cpe_Retencion.getMONEDA());
            TotalInvoiceAmount.appendChild(doc.createTextNode(Double.toString(Cpe_Retencion.getTOTAL_PERCEPCION())));
            mainRootElement.appendChild(TotalInvoiceAmount);
            // <cbc:TotalInvoiceAmount currencyID="PEN">344.00</cbc:TotalInvoiceAmount> 

            Element SUNATTotalCashed = doc.createElement("sac:SUNATTotalCashed");
            SUNATTotalCashed.setAttribute("currencyID", Cpe_Retencion.getMONEDA());
            SUNATTotalCashed.appendChild(doc.createTextNode(Double.toString(Cpe_Retencion.getNETO_PERCEPCION())));
            mainRootElement.appendChild(SUNATTotalCashed);
            //<sac:SUNATTotalCashed currencyID="PEN">17544.00</sac:SUNATTotalCashed> 

            //=========================================================================================
            //=================================INVOICE LINE===================================
            //=================================DETALLE DE PRODUCTOS===================================
            for (int i = 0; i < lstCpe_Retencion_Detalle.size(); i++) {
                _Cpe_RetencionPercepcion_DetalleBean Cpe_Retencion_Detalle = lstCpe_Retencion_Detalle.get(i);
                Element SUNATPerceptionDocumentReference = doc.createElement("sac:SUNATPerceptionDocumentReference");

                Element IDCOMPROBANTE = doc.createElement("cbc:ID");
                IDCOMPROBANTE.setAttribute("schemeID", Cpe_Retencion_Detalle.getCOD_TIPO_DOCUMENTO());
                IDCOMPROBANTE.appendChild(doc.createTextNode(Cpe_Retencion_Detalle.getNRO_DOCUMENTO()));
                SUNATPerceptionDocumentReference.appendChild(IDCOMPROBANTE);

                Element IssueDateCOMPROBANTE = doc.createElement("cbc:IssueDate");
                IssueDateCOMPROBANTE.appendChild(doc.createTextNode(Cpe_Retencion_Detalle.getFECHA_DOCUMENTO()));
                SUNATPerceptionDocumentReference.appendChild(IssueDateCOMPROBANTE);

                Element TotalInvoiceAmountCOMPROBANTE = doc.createElement("cbc:TotalInvoiceAmount");
                TotalInvoiceAmountCOMPROBANTE.setAttribute("currencyID", Cpe_Retencion_Detalle.getCOD_MONEDA());
                TotalInvoiceAmountCOMPROBANTE.appendChild(doc.createTextNode(Double.toString(Cpe_Retencion_Detalle.getMONTO_TOTAL())));
                SUNATPerceptionDocumentReference.appendChild(TotalInvoiceAmountCOMPROBANTE);

                //==========================DATOS REFERENTES AL COBRO=========================
                Element PaymentPAGO = doc.createElement("cac:Payment");

                Element IDPAGO = doc.createElement("cbc:ID");
                IDPAGO.appendChild(doc.createTextNode(Cpe_Retencion_Detalle.getNRO_DOC_COBRO()));
                PaymentPAGO.appendChild(IDPAGO);

                Element PaidAmount = doc.createElement("cbc:PaidAmount");
                PaidAmount.setAttribute("currencyID", Cpe_Retencion_Detalle.getCOD_MONEDA());
                PaidAmount.appendChild(doc.createTextNode(Double.toString(Cpe_Retencion_Detalle.getMONTO_TOTAL())));
                PaymentPAGO.appendChild(PaidAmount);

                Element PaidDate = doc.createElement("cbc:PaidDate");
                PaidDate.appendChild(doc.createTextNode(Cpe_Retencion_Detalle.getFECHA_COBRO()));
                PaymentPAGO.appendChild(PaidDate);

                SUNATPerceptionDocumentReference.appendChild(PaymentPAGO);

                //==========================INFORMACION DE LA PERCEPCION=========================
                Element SUNATPerceptionInformation = doc.createElement("sac:SUNATPerceptionInformation");

                Element SUNATPerceptionAmount = doc.createElement("sac:SUNATPerceptionAmount");
                SUNATPerceptionAmount.setAttribute("currencyID", Cpe_Retencion_Detalle.getMONEDA_PERCIBIDO());
                SUNATPerceptionAmount.appendChild(doc.createTextNode(Double.toString(Cpe_Retencion_Detalle.getMONTO_PERCIBIDO())));
                SUNATPerceptionInformation.appendChild(SUNATPerceptionAmount);

                Element SUNATRetentionDate = doc.createElement("sac:SUNATPerceptionDate");
                SUNATRetentionDate.appendChild(doc.createTextNode(Cpe_Retencion_Detalle.getFECHA_COBRO()));
                SUNATPerceptionInformation.appendChild(SUNATRetentionDate);

                Element SUNATNetTotalCashed = doc.createElement("sac:SUNATNetTotalCashed");
                SUNATNetTotalCashed.setAttribute("currencyID", Cpe_Retencion_Detalle.getMONEDA_COBRO_NETO());
                SUNATNetTotalCashed.appendChild(doc.createTextNode(Double.toString(Cpe_Retencion_Detalle.getMONTO_COBRO_NETO())));
                SUNATPerceptionInformation.appendChild(SUNATNetTotalCashed);

                SUNATPerceptionDocumentReference.appendChild(SUNATPerceptionInformation);

                mainRootElement.appendChild(SUNATPerceptionDocumentReference);
            }//FIN DE BUCLE

            // output DOM XML to console 
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
            StreamResult console = new StreamResult(System.out);
            transformer.transform(source, console);

            DOMSource domSource = new DOMSource(doc);
            //TransformerFactory tFactory = TransformerFactory.newInstance();
            FileWriter out = new FileWriter(RutaXml + ".XML");
            //Transformer transformer = tFactory.newTransformer();
            //transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(domSource, new StreamResult(out));

            System.out.println("\nXML DOM Created Successfully..");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    public int GenerarXMLCPE_RR_BAJA(String RutaXml, _Cpe_RrBean Cpe_Rr, List<_Cpe_Rr_DetalleBean> lstCpe_Rr_Detalle) {
        DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        try {
            icBuilder = icFactory.newDocumentBuilder();
            Document doc = icBuilder.newDocument();

            doc.setXmlVersion("1.0");
            Element mainRootElement = doc.createElementNS("urn:sunat:names:specification:ubl:peru:schema:xsd:VoidedDocuments-1", "VoidedDocuments");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:cac", "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:cbc", "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:ext", "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:sac", "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            doc.appendChild(mainRootElement);

            //=======================UBL EXTENSIONCONTEN PARA FIRMA=========================
            Element UBLExtensions1 = doc.createElement("ext:UBLExtensions");
            Element UBLExtensionFirma = doc.createElement("ext:UBLExtension");
            Element ExtensionContentFirma = doc.createElement("ext:ExtensionContent");
            UBLExtensionFirma.appendChild(ExtensionContentFirma);
            UBLExtensions1.appendChild(UBLExtensionFirma);
            mainRootElement.appendChild(UBLExtensions1);

            /*
             ==================DATOS GENERALES (VERSION,NRO DOCUMENTO,FECHA,TIPO DOCUMENTO,MONEDA)======================
             */
            Element UBLVersionID = doc.createElement("cbc:UBLVersionID");
            UBLVersionID.appendChild(doc.createTextNode("2.0"));
            mainRootElement.appendChild(UBLVersionID);

            Element CustomizationID = doc.createElement("cbc:CustomizationID");
            CustomizationID.appendChild(doc.createTextNode("1.0"));
            mainRootElement.appendChild(CustomizationID);

            Element IDNRO_DOCUMENTO = doc.createElement("cbc:ID");
            IDNRO_DOCUMENTO.appendChild(doc.createTextNode(Cpe_Rr.getCODIGO() + "-" + Cpe_Rr.getSERIE() + "-" + Cpe_Rr.getSECUENCIA()));
            mainRootElement.appendChild(IDNRO_DOCUMENTO);

            //==========================FECHA DEL DOCUMENTO==========================
            Element ReferenceDate = doc.createElement("cbc:ReferenceDate");
            ReferenceDate.appendChild(doc.createTextNode(Cpe_Rr.getFECHA_REFERENCIA()));
            mainRootElement.appendChild(ReferenceDate);

            //==========================FECHA EN LA QUE SE DA DE BAJA==========================
            Element IssueDate = doc.createElement("cbc:IssueDate");
            IssueDate.appendChild(doc.createTextNode(Cpe_Rr.getFECHA_DOCUMENTO()));
            mainRootElement.appendChild(IssueDate);

            /*
             ==================SIGNATURE (DATOS EMPRESA Y OTROS)======================
             */
            Element Signature = doc.createElement("cac:Signature");
            Element IDSignature = doc.createElement("cbc:ID");
            IDSignature.appendChild(doc.createTextNode("IDSignKG"));
            Signature.appendChild(IDSignature);

            Element SignatoryParty = doc.createElement("cac:SignatoryParty");

            //==============================RUC/DNI DE LA EMPRESA RESPONSABLE=============================
            Element PartyIdentification = doc.createElement("cac:PartyIdentification");
            Element ID_PartyIdentification = doc.createElement("cbc:ID");//RUC EMPRESA RESPONSABLE
            ID_PartyIdentification.appendChild(doc.createTextNode(Cpe_Rr.getNRO_DOCUMENTO_EMPRESA()));//EMPRESA RESPONSABLE
            PartyIdentification.appendChild(ID_PartyIdentification);
            SignatoryParty.appendChild(PartyIdentification);
            //==============================RAZON SOCIAL DE LA EMPRESA RESPONSABLE=============================
            Element PartyName = doc.createElement("cac:PartyName");
            Element Name = doc.createElement("cbc:Name");
            Name.appendChild(doc.createTextNode(Cpe_Rr.getRAZON_SOCIAL()));
            PartyName.appendChild(Name);
            SignatoryParty.appendChild(PartyName);

            //=========================ADICIONAMOS RUC/RAZON SOCIAL=========================
            Signature.appendChild(SignatoryParty);

            //==============================IDENTIFICACION DEL DOCUMENTO AGREGANDO #=============================
            Element DigitalSignatureAttachment = doc.createElement("cac:DigitalSignatureAttachment");
            Element ExternalReference = doc.createElement("cac:ExternalReference");
            Element URI = doc.createElement("cbc:URI");
            URI.appendChild(doc.createTextNode("#" + Cpe_Rr.getSERIE() + "-" + Cpe_Rr.getSECUENCIA()));
            //URI.appendChild(doc.createTextNode("#SignatureErickOrlando"));
            ExternalReference.appendChild(URI);
            DigitalSignatureAttachment.appendChild(ExternalReference);
            Signature.appendChild(DigitalSignatureAttachment);

            mainRootElement.appendChild(Signature);

            //==============================INFORMACION DE LA EMPRESA RESPONSABLE=============================
            Element AccountingSupplierParty = doc.createElement("cac:AccountingSupplierParty");

            Element CustomerAssignedAccountID = doc.createElement("cbc:CustomerAssignedAccountID");
            CustomerAssignedAccountID.appendChild(doc.createTextNode(Cpe_Rr.getNRO_DOCUMENTO_EMPRESA()));//RUC
            AccountingSupplierParty.appendChild(CustomerAssignedAccountID);//RUC

            Element AdditionalAccountID = doc.createElement("cbc:AdditionalAccountID");
            AdditionalAccountID.appendChild(doc.createTextNode(Cpe_Rr.getTIPO_DOCUMENTO()));//TIPO DOCUMENTO ES RUC DE LA EMPRESA 6
            AccountingSupplierParty.appendChild(AdditionalAccountID);

            //=================NOMBRE LEGAL DE LA EMPRESA(RAZON SOCIAL)===============
            Element Party = doc.createElement("cac:Party");
            Element PartyLegalEntity = doc.createElement("cac:PartyLegalEntity");
            Element RegistrationName = doc.createElement("cbc:RegistrationName");
            RegistrationName.appendChild(doc.createTextNode(Cpe_Rr.getRAZON_SOCIAL()));//RAZON SOCIAL
            PartyLegalEntity.appendChild(RegistrationName);
            Party.appendChild(PartyLegalEntity);

            AccountingSupplierParty.appendChild(Party);
            mainRootElement.appendChild(AccountingSupplierParty);

            //===================DETALLE DE LOS DOCUMENTOS A DAR DE BAJA====================
            for (int i = 0; i < lstCpe_Rr_Detalle.size(); i++) {
                _Cpe_Rr_DetalleBean Cpe_Rr_Detalle = lstCpe_Rr_Detalle.get(i);
                Element VoidedDocumentsLine = doc.createElement("sac:VoidedDocumentsLine");

                Element LineID = doc.createElement("cbc:LineID");
                LineID.appendChild(doc.createTextNode(Integer.toString(Cpe_Rr_Detalle.getITEM())));
                VoidedDocumentsLine.appendChild(LineID);

                Element DocumentTypeCode = doc.createElement("cbc:DocumentTypeCode");
                DocumentTypeCode.appendChild(doc.createTextNode(Cpe_Rr_Detalle.getCOD_TIPO_DOCUMENTO()));
                VoidedDocumentsLine.appendChild(DocumentTypeCode);

                Element DocumentSerialID = doc.createElement("sac:DocumentSerialID");
                DocumentSerialID.appendChild(doc.createTextNode(Cpe_Rr_Detalle.getSERIE()));
                VoidedDocumentsLine.appendChild(DocumentSerialID);

                Element DocumentNumberID = doc.createElement("sac:DocumentNumberID");
                DocumentNumberID.appendChild(doc.createTextNode(Cpe_Rr_Detalle.getNUMERO()));
                VoidedDocumentsLine.appendChild(DocumentNumberID);

                Element VoidReasonDescription = doc.createElement("sac:VoidReasonDescription");
                VoidReasonDescription.appendChild(doc.createTextNode(Cpe_Rr_Detalle.getDESCRIPCION()));
                VoidedDocumentsLine.appendChild(VoidReasonDescription);

                /*
                 <sac:VoidedDocumentsLine>
                 <cbc:LineID>1</cbc:LineID>
                 <cbc:DocumentTypeCode>20</cbc:DocumentTypeCode>
                 <sac:DocumentSerialID>R002</sac:DocumentSerialID>
                 <sac:DocumentNumberID>46818</sac:DocumentNumberID>
                 <sac:VoidReasonDescription>Pago Anulado No procede</sac:VoidReasonDescription>
                 </sac:VoidedDocumentsLine>
                
                 /*
                 <cbc:LineID>1</cbc:LineID>
                 <cbc:DocumentTypeCode>01</cbc:DocumentTypeCode>
                 <sac:DocumentSerialID>FF01</sac:DocumentSerialID>
                 <sac:DocumentNumberID>901</sac:DocumentNumberID>
                 <sac:VoidReasonDescription>ANULACIONDE LA FACTURA 1</sac:VoidReasonDescription>
                 */
                mainRootElement.appendChild(VoidedDocumentsLine);
            }

            // output DOM XML to console 
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
            StreamResult console = new StreamResult(System.out);
            transformer.transform(source, console);

            DOMSource domSource = new DOMSource(doc);
            //TransformerFactory tFactory = TransformerFactory.newInstance();
            //File folder = new File("x:\\devtroce\\java");
            FileWriter out = new FileWriter(RutaXml + ".XML");
            //Transformer transformer = tFactory.newTransformer();
            //transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(domSource, new StreamResult(out));

            System.out.println("\nXML DOM Created Successfully..");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    public int GenerarXMLBAJA(String RutaXml, _Cpe_BajaBean Cpe_Baja, List<_Cpe_Baja_DetalleBean> lstCpe_BajaDetalle) {
        DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        try {
            icBuilder = icFactory.newDocumentBuilder();
            Document doc = icBuilder.newDocument();

            doc.setXmlVersion("1.0");
            Element mainRootElement = doc.createElementNS("urn:sunat:names:specification:ubl:peru:schema:xsd:VoidedDocuments-1", "VoidedDocuments");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:cac", "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:cbc", "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:ext", "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:sac", "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            doc.appendChild(mainRootElement);

            //=======================UBL EXTENSIONCONTEN PARA FIRMA=========================
            Element UBLExtensions1 = doc.createElement("ext:UBLExtensions");
            Element UBLExtensionFirma = doc.createElement("ext:UBLExtension");
            Element ExtensionContentFirma = doc.createElement("ext:ExtensionContent");
            UBLExtensionFirma.appendChild(ExtensionContentFirma);
            UBLExtensions1.appendChild(UBLExtensionFirma);
            mainRootElement.appendChild(UBLExtensions1);

            /*
             ==================DATOS GENERALES (VERSION,NRO DOCUMENTO,FECHA,TIPO DOCUMENTO,MONEDA)======================
             */
            Element UBLVersionID = doc.createElement("cbc:UBLVersionID");
            UBLVersionID.appendChild(doc.createTextNode("2.0"));
            mainRootElement.appendChild(UBLVersionID);

            Element CustomizationID = doc.createElement("cbc:CustomizationID");
            CustomizationID.appendChild(doc.createTextNode("1.0"));
            mainRootElement.appendChild(CustomizationID);

            Element IDNRO_DOCUMENTO = doc.createElement("cbc:ID");
            IDNRO_DOCUMENTO.appendChild(doc.createTextNode(Cpe_Baja.getCODIGO() + "-" + Cpe_Baja.getSERIE() + "-" + Cpe_Baja.getSECUENCIA()));
            mainRootElement.appendChild(IDNRO_DOCUMENTO);

            //==========================FECHA DEL DOCUMENTO==========================
            Element ReferenceDate = doc.createElement("cbc:ReferenceDate");
            ReferenceDate.appendChild(doc.createTextNode(Cpe_Baja.getFECHA_REFERENCIA()));
            mainRootElement.appendChild(ReferenceDate);

            //==========================FECHA EN LA QUE SE DA DE BAJA==========================
            Element IssueDate = doc.createElement("cbc:IssueDate");
            IssueDate.appendChild(doc.createTextNode(Cpe_Baja.getFECHA_BAJA()));
            mainRootElement.appendChild(IssueDate);

            /*
             ==================SIGNATURE (DATOS EMPRESA Y OTROS)======================
             */
            Element Signature = doc.createElement("cac:Signature");
            Element IDSignature = doc.createElement("cbc:ID");
            IDSignature.appendChild(doc.createTextNode("IDSignKG"));
            Signature.appendChild(IDSignature);

            Element SignatoryParty = doc.createElement("cac:SignatoryParty");

            //==============================RUC/DNI DE LA EMPRESA RESPONSABLE=============================
            Element PartyIdentification = doc.createElement("cac:PartyIdentification");
            Element ID_PartyIdentification = doc.createElement("cbc:ID");//RUC EMPRESA RESPONSABLE
            ID_PartyIdentification.appendChild(doc.createTextNode(Cpe_Baja.getNRO_DOCUMENTO_EMPRESA()));//EMPRESA RESPONSABLE
            PartyIdentification.appendChild(ID_PartyIdentification);
            SignatoryParty.appendChild(PartyIdentification);
            //==============================RAZON SOCIAL DE LA EMPRESA RESPONSABLE=============================
            Element PartyName = doc.createElement("cac:PartyName");
            Element Name = doc.createElement("cbc:Name");
            Name.appendChild(doc.createTextNode(Cpe_Baja.getRAZON_SOCIAL()));
            PartyName.appendChild(Name);
            SignatoryParty.appendChild(PartyName);

            //=========================ADICIONAMOS RUC/RAZON SOCIAL=========================
            Signature.appendChild(SignatoryParty);

            //==============================IDENTIFICACION DEL DOCUMENTO AGREGANDO #=============================
            Element DigitalSignatureAttachment = doc.createElement("cac:DigitalSignatureAttachment");
            Element ExternalReference = doc.createElement("cac:ExternalReference");
            Element URI = doc.createElement("cbc:URI");
            URI.appendChild(doc.createTextNode("#" + Cpe_Baja.getSERIE() + "-" + Cpe_Baja.getSECUENCIA()));
            //URI.appendChild(doc.createTextNode("#SignatureErickOrlando"));
            ExternalReference.appendChild(URI);
            DigitalSignatureAttachment.appendChild(ExternalReference);
            Signature.appendChild(DigitalSignatureAttachment);

            mainRootElement.appendChild(Signature);

            //==============================INFORMACION DE LA EMPRESA RESPONSABLE=============================
            Element AccountingSupplierParty = doc.createElement("cac:AccountingSupplierParty");

            Element CustomerAssignedAccountID = doc.createElement("cbc:CustomerAssignedAccountID");
            CustomerAssignedAccountID.appendChild(doc.createTextNode(Cpe_Baja.getNRO_DOCUMENTO_EMPRESA()));//RUC
            AccountingSupplierParty.appendChild(CustomerAssignedAccountID);//RUC

            Element AdditionalAccountID = doc.createElement("cbc:AdditionalAccountID");
            AdditionalAccountID.appendChild(doc.createTextNode(Cpe_Baja.getTIPO_DOCUMENTO()));//TIPO DOCUMENTO ES RUC DE LA EMPRESA 6
            AccountingSupplierParty.appendChild(AdditionalAccountID);

            //=================NOMBRE LEGAL DE LA EMPRESA(RAZON SOCIAL)===============
            Element Party = doc.createElement("cac:Party");
            Element PartyLegalEntity = doc.createElement("cac:PartyLegalEntity");
            Element RegistrationName = doc.createElement("cbc:RegistrationName");
            RegistrationName.appendChild(doc.createCDATASection(Cpe_Baja.getRAZON_SOCIAL()));//RAZON SOCIAL
            PartyLegalEntity.appendChild(RegistrationName);
            Party.appendChild(PartyLegalEntity);

            AccountingSupplierParty.appendChild(Party);
            mainRootElement.appendChild(AccountingSupplierParty);

            //===================DETALLE DE LOS DOCUMENTOS A DAR DE BAJA====================
            for (int i = 0; i < lstCpe_BajaDetalle.size(); i++) {
                _Cpe_Baja_DetalleBean Cpe_BajaDetalle = lstCpe_BajaDetalle.get(i);
                Element VoidedDocumentsLine = doc.createElement("sac:VoidedDocumentsLine");

                Element LineID = doc.createElement("cbc:LineID");
                LineID.appendChild(doc.createTextNode(Integer.toString(Cpe_BajaDetalle.getITEM())));
                VoidedDocumentsLine.appendChild(LineID);

                Element DocumentTypeCode = doc.createElement("cbc:DocumentTypeCode");
                DocumentTypeCode.appendChild(doc.createTextNode(Cpe_BajaDetalle.getTIPO_COMPROBANTE()));
                VoidedDocumentsLine.appendChild(DocumentTypeCode);

                Element DocumentSerialID = doc.createElement("sac:DocumentSerialID");
                DocumentSerialID.appendChild(doc.createTextNode(Cpe_BajaDetalle.getSERIE()));
                VoidedDocumentsLine.appendChild(DocumentSerialID);

                Element DocumentNumberID = doc.createElement("sac:DocumentNumberID");
                DocumentNumberID.appendChild(doc.createTextNode(Cpe_BajaDetalle.getNUMERO()));
                VoidedDocumentsLine.appendChild(DocumentNumberID);

                Element VoidReasonDescription = doc.createElement("sac:VoidReasonDescription");
                VoidReasonDescription.appendChild(doc.createCDATASection(Cpe_BajaDetalle.getDESCRIPCION()));
                VoidedDocumentsLine.appendChild(VoidReasonDescription);

                /*                
                 <cbc:LineID>1</cbc:LineID>
                 <cbc:DocumentTypeCode>01</cbc:DocumentTypeCode>
                 <sac:DocumentSerialID>FF01</sac:DocumentSerialID>
                 <sac:DocumentNumberID>901</sac:DocumentNumberID>
                 <sac:VoidReasonDescription>ANULACIONDE LA FACTURA 1</sac:VoidReasonDescription>
                 */
                mainRootElement.appendChild(VoidedDocumentsLine);
            }

            // output DOM XML to console 
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
           // StreamResult console = new StreamResult(System.out);
            StreamResult result = new StreamResult(new File(RutaXml + ".XML"));

            transformer.transform(source, result);

            
          
            
            
            DOMSource domSource = new DOMSource(doc);
            //TransformerFactory tFactory = TransformerFactory.newInstance();
            //File folder = new File("x:\\devtroce\\java");
            FileWriter out = new FileWriter(RutaXml + ".XML");
            //Transformer transformer = tFactory.newTransformer();
            //transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(domSource, new StreamResult(out));

            System.out.println("\nXML DOM Created Successfully..");

        } catch (Exception e) {
        	System.out.println(this.getClass().getSimpleName() + " GenerarXMLBAJA. ERROR : " + e.getMessage()
			+ " Linea: " + e.getStackTrace()[0].getLineNumber());
        }
        return 1;
    }

    public int GenerarXMLRESUMEN(String RutaXml, _Cpe_Resumen_BoletaBean Cpe_Resumen_Boleta, List<_Cpe_Resumen_Boleta_DetalleBean> lstCpe_Resumen_Boleta_Detalle) {
        DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        try {
            icBuilder = icFactory.newDocumentBuilder();
            Document doc = icBuilder.newDocument();

            doc.setXmlVersion("1.0");
            Element mainRootElement = doc.createElementNS("urn:sunat:names:specification:ubl:peru:schema:xsd:SummaryDocuments-1", "SummaryDocuments");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:cac", "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:cbc", "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:ext", "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:sac", "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:qdt", "urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:udt", "urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2");

            //mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            doc.appendChild(mainRootElement);

            //=======================UBL EXTENSIONCONTEN PARA FIRMA=========================
            Element UBLExtensions1 = doc.createElement("ext:UBLExtensions");
            Element UBLExtensionFirma = doc.createElement("ext:UBLExtension");
            Element ExtensionContentFirma = doc.createElement("ext:ExtensionContent");
            UBLExtensionFirma.appendChild(ExtensionContentFirma);
            UBLExtensions1.appendChild(UBLExtensionFirma);
            mainRootElement.appendChild(UBLExtensions1);

            /*
             ==================DATOS GENERALES (VERSION,NRO DOCUMENTO,FECHA,TIPO DOCUMENTO,MONEDA)======================
             */
            Element UBLVersionID = doc.createElement("cbc:UBLVersionID");
            UBLVersionID.appendChild(doc.createTextNode("2.0"));
            mainRootElement.appendChild(UBLVersionID);

            Element CustomizationID = doc.createElement("cbc:CustomizationID");
            CustomizationID.appendChild(doc.createTextNode("1.1"));
            mainRootElement.appendChild(CustomizationID);

            Element IDNRO_DOCUMENTO = doc.createElement("cbc:ID");
            IDNRO_DOCUMENTO.appendChild(doc.createTextNode(Cpe_Resumen_Boleta.getCODIGO() + "-" + Cpe_Resumen_Boleta.getSERIE() + "-" + Cpe_Resumen_Boleta.getSECUENCIA()));
            mainRootElement.appendChild(IDNRO_DOCUMENTO);

            //==========================FECHA DEL DOCUMENTO==========================
            Element ReferenceDate = doc.createElement("cbc:ReferenceDate");
            ReferenceDate.appendChild(doc.createTextNode(Cpe_Resumen_Boleta.getFECHA_REFERENCIA()));
            mainRootElement.appendChild(ReferenceDate);

            //==========================FECHA EN LA QUE SE DA DE BAJA==========================
            Element IssueDate = doc.createElement("cbc:IssueDate");
            IssueDate.appendChild(doc.createTextNode(Cpe_Resumen_Boleta.getFECHA_DOCUMENTO()));
            mainRootElement.appendChild(IssueDate);

            /*
             ==================SIGNATURE (DATOS EMPRESA Y OTROS)======================
             */
            Element Signature = doc.createElement("cac:Signature");
            Element IDSignature = doc.createElement("cbc:ID");
            IDSignature.appendChild(doc.createTextNode(Cpe_Resumen_Boleta.getCODIGO() + "-" + Cpe_Resumen_Boleta.getSERIE() + "-" + Cpe_Resumen_Boleta.getSECUENCIA()));
            Signature.appendChild(IDSignature);

            Element SignatoryParty = doc.createElement("cac:SignatoryParty");

            //==============================RUC/DNI DE LA EMPRESA RESPONSABLE=============================
            Element PartyIdentification = doc.createElement("cac:PartyIdentification");
            Element ID_PartyIdentification = doc.createElement("cbc:ID");//RUC EMPRESA RESPONSABLE
            ID_PartyIdentification.appendChild(doc.createTextNode(Cpe_Resumen_Boleta.getNRO_DOCUMENTO_EMPRESA()));//EMPRESA RESPONSABLE
            PartyIdentification.appendChild(ID_PartyIdentification);
            SignatoryParty.appendChild(PartyIdentification);
            //==============================RAZON SOCIAL DE LA EMPRESA RESPONSABLE=============================
            Element PartyName = doc.createElement("cac:PartyName");
            Element Name = doc.createElement("cbc:Name");
            Name.appendChild(doc.createCDATASection(Cpe_Resumen_Boleta.getRAZON_SOCIAL()));
            PartyName.appendChild(Name);
            SignatoryParty.appendChild(PartyName);

            //=========================ADICIONAMOS RUC/RAZON SOCIAL=========================
            Signature.appendChild(SignatoryParty);

            //==============================IDENTIFICACION DEL DOCUMENTO AGREGANDO #=============================
            Element DigitalSignatureAttachment = doc.createElement("cac:DigitalSignatureAttachment");
            Element ExternalReference = doc.createElement("cac:ExternalReference");
            Element URI = doc.createElement("cbc:URI");
            URI.appendChild(doc.createTextNode(Cpe_Resumen_Boleta.getCODIGO() + "-" + Cpe_Resumen_Boleta.getSERIE() + "-" + Cpe_Resumen_Boleta.getSECUENCIA()));
            //URI.appendChild(doc.createTextNode("#SignatureErickOrlando"));
            ExternalReference.appendChild(URI);
            DigitalSignatureAttachment.appendChild(ExternalReference);
            Signature.appendChild(DigitalSignatureAttachment);

            mainRootElement.appendChild(Signature);

            //==============================INFORMACION DE LA EMPRESA RESPONSABLE=============================
            Element AccountingSupplierParty = doc.createElement("cac:AccountingSupplierParty");

            Element CustomerAssignedAccountID = doc.createElement("cbc:CustomerAssignedAccountID");
            CustomerAssignedAccountID.appendChild(doc.createTextNode(Cpe_Resumen_Boleta.getNRO_DOCUMENTO_EMPRESA()));//RUC
            AccountingSupplierParty.appendChild(CustomerAssignedAccountID);//RUC

            Element AdditionalAccountID = doc.createElement("cbc:AdditionalAccountID");
            AdditionalAccountID.appendChild(doc.createTextNode(Cpe_Resumen_Boleta.getTIPO_DOCUMENTO()));//TIPO DOCUMENTO ES RUC DE LA EMPRESA 6
            AccountingSupplierParty.appendChild(AdditionalAccountID);

            //=================NOMBRE LEGAL DE LA EMPRESA(RAZON SOCIAL)===============
            Element Party = doc.createElement("cac:Party");
            Element PartyLegalEntity = doc.createElement("cac:PartyLegalEntity");
            Element RegistrationName = doc.createElement("cbc:RegistrationName");
            RegistrationName.appendChild(doc.createCDATASection(Cpe_Resumen_Boleta.getRAZON_SOCIAL()));//RAZON SOCIAL
            PartyLegalEntity.appendChild(RegistrationName);
            Party.appendChild(PartyLegalEntity);

            AccountingSupplierParty.appendChild(Party);
            mainRootElement.appendChild(AccountingSupplierParty);

            //===================DETALLE DE LOS DOCUMENTOS QUE SE SUBIRAN DE FORMA RESUMIDA====================
            for (int i = 0; i < lstCpe_Resumen_Boleta_Detalle.size(); i++) {
                _Cpe_Resumen_Boleta_DetalleBean Cpe_Resumen_Boleta_Detalle = lstCpe_Resumen_Boleta_Detalle.get(i);
                Element SummaryDocumentsLine = doc.createElement("sac:SummaryDocumentsLine");

                Element LineID = doc.createElement("cbc:LineID");
                LineID.appendChild(doc.createTextNode(Integer.toString(Cpe_Resumen_Boleta_Detalle.getITEM())));
                SummaryDocumentsLine.appendChild(LineID);

                Element DocumentTypeCode = doc.createElement("cbc:DocumentTypeCode");
                DocumentTypeCode.appendChild(doc.createTextNode(Cpe_Resumen_Boleta_Detalle.getTIPO_COMPROBANTE()));
                SummaryDocumentsLine.appendChild(DocumentTypeCode);

                Element comprobanteID = doc.createElement("cbc:ID");
                comprobanteID.appendChild(doc.createTextNode(Cpe_Resumen_Boleta_Detalle.getNRO_COMPROBANTE()));
                SummaryDocumentsLine.appendChild(comprobanteID);

                //==========================DATOS DEL CLIENTE TIPO Y NRO DOCUMENTO=============================
                Element AccountingCustomerParty = doc.createElement("cac:AccountingCustomerParty");

                Element CustomerAssignedAccountID_DET = doc.createElement("cbc:CustomerAssignedAccountID");
                CustomerAssignedAccountID_DET.appendChild(doc.createTextNode(Cpe_Resumen_Boleta_Detalle.getNRO_DOCUMENTO()));//RUC
                AccountingCustomerParty.appendChild(CustomerAssignedAccountID_DET);

                Element AdditionalAccountID_DET = doc.createElement("cbc:AdditionalAccountID");
                AdditionalAccountID_DET.appendChild(doc.createTextNode(Cpe_Resumen_Boleta_Detalle.getTIPO_DOCUMENTO()));//RUC
                AccountingCustomerParty.appendChild(AdditionalAccountID_DET);

                SummaryDocumentsLine.appendChild(AccountingCustomerParty);

                //==========================DATOS DE REFERENCIA CUANDO ES UN UNA (NOTA CREDITO, NOTA DEBITO)=============================
                if (Cpe_Resumen_Boleta_Detalle.getTIPO_COMPROBANTE().equals("07")
                        || Cpe_Resumen_Boleta_Detalle.getTIPO_COMPROBANTE().equals("08")) {
                    Element BillingReference = doc.createElement("cac:BillingReference");
                    Element InvoiceDocumentReference = doc.createElement("cac:InvoiceDocumentReference");

                    Element ID_DOCUMENTO_REF = doc.createElement("cbc:ID");
                    ID_DOCUMENTO_REF.appendChild(doc.createTextNode(Cpe_Resumen_Boleta_Detalle.getNRO_COMPROBANTE_REF()));//RUC
                    InvoiceDocumentReference.appendChild(ID_DOCUMENTO_REF);

                    Element DocumentTypeCode_REF = doc.createElement("cbc:DocumentTypeCode");
                    DocumentTypeCode_REF.appendChild(doc.createTextNode(Cpe_Resumen_Boleta_Detalle.getTIPO_COMPROBANTE_REF()));//RUC
                    InvoiceDocumentReference.appendChild(DocumentTypeCode_REF);

                    BillingReference.appendChild(InvoiceDocumentReference);
                    SummaryDocumentsLine.appendChild(BillingReference);
                }

//              <cac:BillingReference>
//			<cac:InvoiceDocumentReference>
//				<cbc:ID>B001-00000001</cbc:ID>
//				<cbc:DocumentTypeCode>03</cbc:DocumentTypeCode>
//			</cac:InvoiceDocumentReference>
//		</cac:BillingReference>
                //==========================STATUS DETALLE COMPROBANTE=============================
                Element Status = doc.createElement("cac:Status");
                Element ConditionCode = doc.createElement("cbc:ConditionCode");
                ConditionCode.appendChild(doc.createTextNode(Cpe_Resumen_Boleta_Detalle.getSTATU()));//
                Status.appendChild(ConditionCode);
                SummaryDocumentsLine.appendChild(Status);

                //==========================TOTAL DETALLE COMPROBANTE=============================
                if (Cpe_Resumen_Boleta_Detalle.getGRAVADA() >= 0) {
                    Element TotalAmount = doc.createElement("sac:TotalAmount");
                    TotalAmount.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                    TotalAmount.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getTOTAL())));
                    SummaryDocumentsLine.appendChild(TotalAmount);

                    //====================================OPERACIONES GRAVADAS CATALOGO-TABLA 11=======================================
                    Element BillingPaymentGravado = doc.createElement("sac:BillingPayment");

                    Element PaidAmountGravado = doc.createElement("cbc:PaidAmount");
                    PaidAmountGravado.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                    PaidAmountGravado.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getGRAVADA())));
                    BillingPaymentGravado.appendChild(PaidAmountGravado);

                    Element InstructionIDGravado = doc.createElement("cbc:InstructionID");
                    InstructionIDGravado.appendChild(doc.createTextNode("01"));//GRAVADO TABLA - CATALOGO 11
                    BillingPaymentGravado.appendChild(InstructionIDGravado);

                    SummaryDocumentsLine.appendChild(BillingPaymentGravado);
                }
                //====================================OPERACIONES EXONERADA CATALOGO-TABLA 11=======================================
                if (Cpe_Resumen_Boleta_Detalle.getEXONERADO() > 0) {
                    Element BillingPaymentExonerado = doc.createElement("sac:BillingPayment");

                    Element PaidAmountExonerado = doc.createElement("cbc:PaidAmount");
                    PaidAmountExonerado.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                    PaidAmountExonerado.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getEXONERADO())));
                    BillingPaymentExonerado.appendChild(PaidAmountExonerado);

                    Element InstructionIDExonerado = doc.createElement("cbc:InstructionID");
                    InstructionIDExonerado.appendChild(doc.createTextNode("02"));
                    BillingPaymentExonerado.appendChild(InstructionIDExonerado);

                    SummaryDocumentsLine.appendChild(BillingPaymentExonerado);
                }
                //====================================OPERACIONES INAFECTO CATALOGO-TABLA 11=======================================
                if (Cpe_Resumen_Boleta_Detalle.getINAFECTO() > 0) {
                    Element BillingPaymentInafecto = doc.createElement("sac:BillingPayment");

                    Element PaidAmountInafecto = doc.createElement("cbc:PaidAmount");
                    PaidAmountInafecto.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                    PaidAmountInafecto.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getINAFECTO())));
                    BillingPaymentInafecto.appendChild(PaidAmountInafecto);

                    Element InstructionIDInafecto = doc.createElement("cbc:InstructionID");
                    InstructionIDInafecto.appendChild(doc.createTextNode("03"));
                    BillingPaymentInafecto.appendChild(InstructionIDInafecto);

                    SummaryDocumentsLine.appendChild(BillingPaymentInafecto);
                }
                //====================================OPERACIONES EXPORTACION CATALOGO-TABLA 11=======================================
                if (Cpe_Resumen_Boleta_Detalle.getEXPORTACION() > 0) {
                    Element BillingPaymentExportacion = doc.createElement("sac:BillingPayment");

                    Element PaidAmountExportacion = doc.createElement("cbc:PaidAmount");
                    PaidAmountExportacion.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                    PaidAmountExportacion.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getEXPORTACION())));
                    BillingPaymentExportacion.appendChild(PaidAmountExportacion);

                    Element InstructionIDExportacion = doc.createElement("cbc:InstructionID");
                    InstructionIDExportacion.appendChild(doc.createTextNode("04"));
                    BillingPaymentExportacion.appendChild(InstructionIDExportacion);

                    SummaryDocumentsLine.appendChild(BillingPaymentExportacion);
                }
                //====================================OPERACIONES GRATUITAS CATALOGO-TABLA 11=======================================
                if (Cpe_Resumen_Boleta_Detalle.getGRATUITAS() > 0) {
                    Element BillingPaymentGratuitas = doc.createElement("sac:BillingPayment");

                    Element PaidAmountGratuitas = doc.createElement("cbc:PaidAmount");
                    PaidAmountGratuitas.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                    PaidAmountGratuitas.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getGRATUITAS())));
                    BillingPaymentGratuitas.appendChild(PaidAmountGratuitas);

                    Element InstructionIDGratuitas = doc.createElement("cbc:InstructionID");
                    InstructionIDGratuitas.appendChild(doc.createTextNode("05"));
                    BillingPaymentGratuitas.appendChild(InstructionIDGratuitas);

                    SummaryDocumentsLine.appendChild(BillingPaymentGratuitas);
                }

                //====================================INDICATOR DE CARGA=======================================
                if (Cpe_Resumen_Boleta_Detalle.getMONTO_CARGO_X_ASIG() > 0) {
                    String StrChargeIndicator = "true";
                    if (Cpe_Resumen_Boleta_Detalle.getCARGO_X_ASIGNACION() == 1) {
                        StrChargeIndicator = "true";
                    } else {
                        StrChargeIndicator = "false";
                    }

                    Element AllowanceCharge = doc.createElement("cac:AllowanceCharge");

                    Element ChargeIndicator = doc.createElement("cbc:ChargeIndicator");
                    ChargeIndicator.appendChild(doc.createTextNode(StrChargeIndicator));
                    AllowanceCharge.appendChild(ChargeIndicator);

                    Element AmountChargeIndicator = doc.createElement("cbc:Amount");
                    AmountChargeIndicator.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                    AmountChargeIndicator.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getMONTO_CARGO_X_ASIG())));
                    AllowanceCharge.appendChild(AmountChargeIndicator);

                    SummaryDocumentsLine.appendChild(AllowanceCharge);
                }
                //====================================TOTAL ISC=======================================
                if (Cpe_Resumen_Boleta_Detalle.getISC() > 0) {
                    Element TaxTotalISC = doc.createElement("cac:TaxTotal");

                    Element TaxAmountISC = doc.createElement("cbc:TaxAmount");
                    TaxAmountISC.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                    TaxAmountISC.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getISC())));
                    TaxTotalISC.appendChild(TaxAmountISC);

                    Element TaxSubtotalISC = doc.createElement("cac:TaxSubtotal");

                    Element TaxAmountISC_TaxSubtotal = doc.createElement("cbc:TaxAmount");
                    TaxAmountISC_TaxSubtotal.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                    TaxAmountISC_TaxSubtotal.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getISC())));
                    TaxSubtotalISC.appendChild(TaxAmountISC_TaxSubtotal);

                    Element TaxCategoryISC = doc.createElement("cac:TaxCategory");
                    Element TaxSchemeISC = doc.createElement("cac:TaxScheme");

                    Element IDISC = doc.createElement("cbc:ID");
                    IDISC.appendChild(doc.createTextNode("2000"));
                    TaxSchemeISC.appendChild(IDISC);

                    Element NameISC = doc.createElement("cbc:Name");
                    NameISC.appendChild(doc.createTextNode("ISC"));
                    TaxSchemeISC.appendChild(NameISC);

                    Element TaxTypeCodeISC = doc.createElement("cbc:TaxTypeCode");
                    TaxTypeCodeISC.appendChild(doc.createTextNode("EXC"));
                    TaxSchemeISC.appendChild(TaxTypeCodeISC);

                    TaxCategoryISC.appendChild(TaxSchemeISC);
                    TaxSubtotalISC.appendChild(TaxCategoryISC);
                    TaxTotalISC.appendChild(TaxSubtotalISC);

                    SummaryDocumentsLine.appendChild(TaxTotalISC);
                }
                //====================================TOTAL IGV=======================================
                Element TaxTotalIGV = doc.createElement("cac:TaxTotal");

                Element TaxAmountIGV = doc.createElement("cbc:TaxAmount");
                TaxAmountIGV.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                TaxAmountIGV.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getIGV())));
                TaxTotalIGV.appendChild(TaxAmountIGV);

                Element TaxSubtotalIGV = doc.createElement("cac:TaxSubtotal");

                Element TaxAmountIGV_TaxSubtotal = doc.createElement("cbc:TaxAmount");
                TaxAmountIGV_TaxSubtotal.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                TaxAmountIGV_TaxSubtotal.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getIGV())));
                TaxSubtotalIGV.appendChild(TaxAmountIGV_TaxSubtotal);

                Element TaxCategoryIGV = doc.createElement("cac:TaxCategory");
                Element TaxSchemeIGV = doc.createElement("cac:TaxScheme");

                Element IDIGV = doc.createElement("cbc:ID");
                IDIGV.appendChild(doc.createTextNode("1000"));
                TaxSchemeIGV.appendChild(IDIGV);

                Element NameIGV = doc.createElement("cbc:Name");
                NameIGV.appendChild(doc.createTextNode("IGV"));
                TaxSchemeIGV.appendChild(NameIGV);

                Element TaxTypeCodeIGV = doc.createElement("cbc:TaxTypeCode");
                TaxTypeCodeIGV.appendChild(doc.createTextNode("VAT"));
                TaxSchemeIGV.appendChild(TaxTypeCodeIGV);

                TaxCategoryIGV.appendChild(TaxSchemeIGV);
                TaxSubtotalIGV.appendChild(TaxCategoryIGV);
                TaxTotalIGV.appendChild(TaxSubtotalIGV);

                SummaryDocumentsLine.appendChild(TaxTotalIGV);
                mainRootElement.appendChild(SummaryDocumentsLine);
            }

            // output DOM XML to console 
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
            StreamResult console = new StreamResult(System.out);
            transformer.transform(source, console);

            DOMSource domSource = new DOMSource(doc);
            //TransformerFactory tFactory = TransformerFactory.newInstance();
            //File folder = new File("x:\\devtroce\\java");
            FileWriter out = new FileWriter(RutaXml + ".XML");
            //Transformer transformer = tFactory.newTransformer();
            //transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(domSource, new StreamResult(out));

            System.out.println("\nXML DOM Created Successfully..");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    public int GenerarXMLRESUMEN_V1_0(String RutaXml, _Cpe_Resumen_BoletaBean Cpe_Resumen_Boleta, List<_Cpe_Resumen_Boleta_Detalle1Bean> lstCpe_Resumen_Boleta_Detalle) {
        DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        try {
            icBuilder = icFactory.newDocumentBuilder();
            Document doc = icBuilder.newDocument();

            doc.setXmlVersion("1.0");
            Element mainRootElement = doc.createElementNS("urn:sunat:names:specification:ubl:peru:schema:xsd:SummaryDocuments-1", "SummaryDocuments");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:cac", "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:cbc", "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:ext", "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:sac", "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:qdt", "urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:udt", "urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2");

            //mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            doc.appendChild(mainRootElement);

            //=======================UBL EXTENSIONCONTEN PARA FIRMA=========================
            Element UBLExtensions1 = doc.createElement("ext:UBLExtensions");
            Element UBLExtensionFirma = doc.createElement("ext:UBLExtension");
            Element ExtensionContentFirma = doc.createElement("ext:ExtensionContent");
            UBLExtensionFirma.appendChild(ExtensionContentFirma);
            UBLExtensions1.appendChild(UBLExtensionFirma);
            mainRootElement.appendChild(UBLExtensions1);

            /*
             ==================DATOS GENERALES (VERSION,NRO DOCUMENTO,FECHA,TIPO DOCUMENTO,MONEDA)======================
             */
            Element UBLVersionID = doc.createElement("cbc:UBLVersionID");
            UBLVersionID.appendChild(doc.createTextNode("2.0"));
            mainRootElement.appendChild(UBLVersionID);

            Element CustomizationID = doc.createElement("cbc:CustomizationID");
            CustomizationID.appendChild(doc.createTextNode("1.0"));
            mainRootElement.appendChild(CustomizationID);

            Element IDNRO_DOCUMENTO = doc.createElement("cbc:ID");
            IDNRO_DOCUMENTO.appendChild(doc.createTextNode(Cpe_Resumen_Boleta.getCODIGO() + "-" + Cpe_Resumen_Boleta.getSERIE() + "-" + Cpe_Resumen_Boleta.getSECUENCIA()));
            mainRootElement.appendChild(IDNRO_DOCUMENTO);

            //==========================FECHA DEL DOCUMENTO==========================
            Element ReferenceDate = doc.createElement("cbc:ReferenceDate");
            ReferenceDate.appendChild(doc.createTextNode(Cpe_Resumen_Boleta.getFECHA_REFERENCIA()));
            mainRootElement.appendChild(ReferenceDate);

            //==========================FECHA EN LA QUE SE DA DE BAJA==========================
            Element IssueDate = doc.createElement("cbc:IssueDate");
            IssueDate.appendChild(doc.createTextNode(Cpe_Resumen_Boleta.getFECHA_DOCUMENTO()));
            mainRootElement.appendChild(IssueDate);

            /*
             ==================SIGNATURE (DATOS EMPRESA Y OTROS)======================
             */
            Element Signature = doc.createElement("cac:Signature");
            Element IDSignature = doc.createElement("cbc:ID");
            IDSignature.appendChild(doc.createTextNode(Cpe_Resumen_Boleta.getCODIGO() + "-" + Cpe_Resumen_Boleta.getSERIE() + "-" + Cpe_Resumen_Boleta.getSECUENCIA()));
            Signature.appendChild(IDSignature);

            Element SignatoryParty = doc.createElement("cac:SignatoryParty");

            //==============================RUC/DNI DE LA EMPRESA RESPONSABLE=============================
            Element PartyIdentification = doc.createElement("cac:PartyIdentification");
            Element ID_PartyIdentification = doc.createElement("cbc:ID");//RUC EMPRESA RESPONSABLE
            ID_PartyIdentification.appendChild(doc.createTextNode(Cpe_Resumen_Boleta.getNRO_DOCUMENTO_EMPRESA()));//EMPRESA RESPONSABLE
            PartyIdentification.appendChild(ID_PartyIdentification);
            SignatoryParty.appendChild(PartyIdentification);
            //==============================RAZON SOCIAL DE LA EMPRESA RESPONSABLE=============================
            Element PartyName = doc.createElement("cac:PartyName");
            Element Name = doc.createElement("cbc:Name");
            Name.appendChild(doc.createTextNode(Cpe_Resumen_Boleta.getRAZON_SOCIAL()));
            PartyName.appendChild(Name);
            SignatoryParty.appendChild(PartyName);

            //=========================ADICIONAMOS RUC/RAZON SOCIAL=========================
            Signature.appendChild(SignatoryParty);

            //==============================IDENTIFICACION DEL DOCUMENTO AGREGANDO #=============================
            Element DigitalSignatureAttachment = doc.createElement("cac:DigitalSignatureAttachment");
            Element ExternalReference = doc.createElement("cac:ExternalReference");
            Element URI = doc.createElement("cbc:URI");
            URI.appendChild(doc.createTextNode(Cpe_Resumen_Boleta.getCODIGO() + "-" + Cpe_Resumen_Boleta.getSERIE() + "-" + Cpe_Resumen_Boleta.getSECUENCIA()));
            //URI.appendChild(doc.createTextNode("#SignatureErickOrlando"));
            ExternalReference.appendChild(URI);
            DigitalSignatureAttachment.appendChild(ExternalReference);
            Signature.appendChild(DigitalSignatureAttachment);

            mainRootElement.appendChild(Signature);

            //==============================INFORMACION DE LA EMPRESA RESPONSABLE=============================
            Element AccountingSupplierParty = doc.createElement("cac:AccountingSupplierParty");

            Element CustomerAssignedAccountID = doc.createElement("cbc:CustomerAssignedAccountID");
            CustomerAssignedAccountID.appendChild(doc.createTextNode(Cpe_Resumen_Boleta.getNRO_DOCUMENTO_EMPRESA()));//RUC
            AccountingSupplierParty.appendChild(CustomerAssignedAccountID);//RUC

            Element AdditionalAccountID = doc.createElement("cbc:AdditionalAccountID");
            AdditionalAccountID.appendChild(doc.createTextNode(Cpe_Resumen_Boleta.getTIPO_DOCUMENTO()));//TIPO DOCUMENTO ES RUC DE LA EMPRESA 6
            AccountingSupplierParty.appendChild(AdditionalAccountID);

            //=================NOMBRE LEGAL DE LA EMPRESA(RAZON SOCIAL)===============
            Element Party = doc.createElement("cac:Party");
            Element PartyLegalEntity = doc.createElement("cac:PartyLegalEntity");
            Element RegistrationName = doc.createElement("cbc:RegistrationName");
            RegistrationName.appendChild(doc.createTextNode(Cpe_Resumen_Boleta.getRAZON_SOCIAL()));//RAZON SOCIAL
            PartyLegalEntity.appendChild(RegistrationName);
            Party.appendChild(PartyLegalEntity);

            AccountingSupplierParty.appendChild(Party);
            mainRootElement.appendChild(AccountingSupplierParty);

            //===================DETALLE DE LOS DOCUMENTOS QUE SE SUBIRAN DE FORMA RESUMIDA====================
            for (int i = 0; i < lstCpe_Resumen_Boleta_Detalle.size(); i++) {
                _Cpe_Resumen_Boleta_Detalle1Bean Cpe_Resumen_Boleta_Detalle = lstCpe_Resumen_Boleta_Detalle.get(i);
                Element SummaryDocumentsLine = doc.createElement("sac:SummaryDocumentsLine");

                Element LineID = doc.createElement("cbc:LineID");
                LineID.appendChild(doc.createTextNode(Integer.toString(Cpe_Resumen_Boleta_Detalle.getITEM())));
                SummaryDocumentsLine.appendChild(LineID);

                Element DocumentTypeCode = doc.createElement("cbc:DocumentTypeCode");
                DocumentTypeCode.appendChild(doc.createTextNode(Cpe_Resumen_Boleta_Detalle.getTIPO_COMPROBANTE()));
                SummaryDocumentsLine.appendChild(DocumentTypeCode);

                Element DocumentSerialID = doc.createElement("sac:DocumentSerialID");
                DocumentSerialID.appendChild(doc.createTextNode(Cpe_Resumen_Boleta_Detalle.getSERIE_COMPROBANTE()));
                SummaryDocumentsLine.appendChild(DocumentSerialID);

                Element StartDocumentNumberID = doc.createElement("sac:StartDocumentNumberID");
                StartDocumentNumberID.appendChild(doc.createTextNode(Integer.toString(Cpe_Resumen_Boleta_Detalle.getSTAR_DOCUMENTO())));
                SummaryDocumentsLine.appendChild(StartDocumentNumberID);

                Element EndDocumentNumberID = doc.createElement("sac:EndDocumentNumberID");
                EndDocumentNumberID.appendChild(doc.createTextNode(Integer.toString(Cpe_Resumen_Boleta_Detalle.getEND_DOCUMENTO())));
                SummaryDocumentsLine.appendChild(EndDocumentNumberID);

                //==========================TOTAL DETALLE COMPROBANTE=============================
                Element TotalAmount = doc.createElement("sac:TotalAmount");
                TotalAmount.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                TotalAmount.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getTOTAL())));
                SummaryDocumentsLine.appendChild(TotalAmount);

                //====================================OPERACIONES GRAVADAS CATALOGO-TABLA 11=======================================
                Element BillingPaymentGravado = doc.createElement("sac:BillingPayment");

                Element PaidAmountGravado = doc.createElement("cbc:PaidAmount");
                PaidAmountGravado.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                PaidAmountGravado.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getGRAVADA())));
                BillingPaymentGravado.appendChild(PaidAmountGravado);

                Element InstructionIDGravado = doc.createElement("cbc:InstructionID");
                InstructionIDGravado.appendChild(doc.createTextNode("01"));//GRAVADO TABLA - CATALOGO 11
                BillingPaymentGravado.appendChild(InstructionIDGravado);

                SummaryDocumentsLine.appendChild(BillingPaymentGravado);

                //====================================OPERACIONES EXONERADA CATALOGO-TABLA 11=======================================
                if (Cpe_Resumen_Boleta_Detalle.getEXONERADO() > 0) {
                    Element BillingPaymentExonerado = doc.createElement("sac:BillingPayment");

                    Element PaidAmountExonerado = doc.createElement("cbc:PaidAmount");
                    PaidAmountExonerado.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                    PaidAmountExonerado.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getEXONERADO())));
                    BillingPaymentExonerado.appendChild(PaidAmountExonerado);

                    Element InstructionIDExonerado = doc.createElement("cbc:InstructionID");
                    InstructionIDExonerado.appendChild(doc.createTextNode("02"));
                    BillingPaymentExonerado.appendChild(InstructionIDExonerado);

                    SummaryDocumentsLine.appendChild(BillingPaymentExonerado);
                }
                //====================================OPERACIONES INAFECTO CATALOGO-TABLA 11=======================================
                if (Cpe_Resumen_Boleta_Detalle.getINAFECTO() > 0) {
                    Element BillingPaymentInafecto = doc.createElement("sac:BillingPayment");

                    Element PaidAmountInafecto = doc.createElement("cbc:PaidAmount");
                    PaidAmountInafecto.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                    PaidAmountInafecto.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getINAFECTO())));
                    BillingPaymentInafecto.appendChild(PaidAmountInafecto);

                    Element InstructionIDInafecto = doc.createElement("cbc:InstructionID");
                    InstructionIDInafecto.appendChild(doc.createTextNode("03"));
                    BillingPaymentInafecto.appendChild(InstructionIDInafecto);

                    SummaryDocumentsLine.appendChild(BillingPaymentInafecto);
                }
                //====================================OPERACIONES EXPORTACION CATALOGO-TABLA 11=======================================
                if (Cpe_Resumen_Boleta_Detalle.getEXPORTACION() > 0) {
                    Element BillingPaymentExportacion = doc.createElement("sac:BillingPayment");

                    Element PaidAmountExportacion = doc.createElement("cbc:PaidAmount");
                    PaidAmountExportacion.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                    PaidAmountExportacion.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getEXPORTACION())));
                    BillingPaymentExportacion.appendChild(PaidAmountExportacion);

                    Element InstructionIDExportacion = doc.createElement("cbc:InstructionID");
                    InstructionIDExportacion.appendChild(doc.createTextNode("04"));
                    BillingPaymentExportacion.appendChild(InstructionIDExportacion);

                    SummaryDocumentsLine.appendChild(BillingPaymentExportacion);
                }
                //====================================OPERACIONES GRATUITAS CATALOGO-TABLA 11=======================================
                if (Cpe_Resumen_Boleta_Detalle.getGRATUITAS() > 0) {
                    Element BillingPaymentGratuitas = doc.createElement("sac:BillingPayment");

                    Element PaidAmountGratuitas = doc.createElement("cbc:PaidAmount");
                    PaidAmountGratuitas.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                    PaidAmountGratuitas.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getGRATUITAS())));
                    BillingPaymentGratuitas.appendChild(PaidAmountGratuitas);

                    Element InstructionIDGratuitas = doc.createElement("cbc:InstructionID");
                    InstructionIDGratuitas.appendChild(doc.createTextNode("05"));
                    BillingPaymentGratuitas.appendChild(InstructionIDGratuitas);

                    SummaryDocumentsLine.appendChild(BillingPaymentGratuitas);
                }

                //====================================INDICATOR DE CARGA=======================================
                String StrChargeIndicator = "true";
                if (Cpe_Resumen_Boleta_Detalle.getCARGO_X_ASIGNACION() == 1) {
                    StrChargeIndicator = "true";
                } else {
                    StrChargeIndicator = "false";
                }

                Element AllowanceCharge = doc.createElement("cac:AllowanceCharge");

                Element ChargeIndicator = doc.createElement("cbc:ChargeIndicator");
                ChargeIndicator.appendChild(doc.createTextNode(StrChargeIndicator));
                AllowanceCharge.appendChild(ChargeIndicator);

                Element AmountChargeIndicator = doc.createElement("cbc:Amount");
                AmountChargeIndicator.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                AmountChargeIndicator.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getMONTO_CARGO_X_ASIG())));
                AllowanceCharge.appendChild(AmountChargeIndicator);

                SummaryDocumentsLine.appendChild(AllowanceCharge);

                //====================================TOTAL ISC=======================================
                Element TaxTotalISC = doc.createElement("cac:TaxTotal");

                Element TaxAmountISC = doc.createElement("cbc:TaxAmount");
                TaxAmountISC.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                TaxAmountISC.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getISC())));
                TaxTotalISC.appendChild(TaxAmountISC);

                Element TaxSubtotalISC = doc.createElement("cac:TaxSubtotal");

                Element TaxAmountISC_TaxSubtotal = doc.createElement("cbc:TaxAmount");
                TaxAmountISC_TaxSubtotal.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                TaxAmountISC_TaxSubtotal.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getISC())));
                TaxSubtotalISC.appendChild(TaxAmountISC_TaxSubtotal);

                Element TaxCategoryISC = doc.createElement("cac:TaxCategory");
                Element TaxSchemeISC = doc.createElement("cac:TaxScheme");

                Element IDISC = doc.createElement("cbc:ID");
                IDISC.appendChild(doc.createTextNode("2000"));
                TaxSchemeISC.appendChild(IDISC);

                Element NameISC = doc.createElement("cbc:Name");
                NameISC.appendChild(doc.createTextNode("ISC"));
                TaxSchemeISC.appendChild(NameISC);

                Element TaxTypeCodeISC = doc.createElement("cbc:TaxTypeCode");
                TaxTypeCodeISC.appendChild(doc.createTextNode("EXC"));
                TaxSchemeISC.appendChild(TaxTypeCodeISC);

                TaxCategoryISC.appendChild(TaxSchemeISC);
                TaxSubtotalISC.appendChild(TaxCategoryISC);
                TaxTotalISC.appendChild(TaxSubtotalISC);

                SummaryDocumentsLine.appendChild(TaxTotalISC);

                //====================================TOTAL IGV=======================================
                Element TaxTotalIGV = doc.createElement("cac:TaxTotal");

                Element TaxAmountIGV = doc.createElement("cbc:TaxAmount");
                TaxAmountIGV.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                TaxAmountIGV.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getIGV())));
                TaxTotalIGV.appendChild(TaxAmountIGV);

                Element TaxSubtotalIGV = doc.createElement("cac:TaxSubtotal");

                Element TaxAmountIGV_TaxSubtotal = doc.createElement("cbc:TaxAmount");
                TaxAmountIGV_TaxSubtotal.setAttribute("currencyID", Cpe_Resumen_Boleta_Detalle.getCOD_MONEDA());//MONEDA
                TaxAmountIGV_TaxSubtotal.appendChild(doc.createTextNode(Double.toString(Cpe_Resumen_Boleta_Detalle.getIGV())));
                TaxSubtotalIGV.appendChild(TaxAmountIGV_TaxSubtotal);

                Element TaxCategoryIGV = doc.createElement("cac:TaxCategory");
                Element TaxSchemeIGV = doc.createElement("cac:TaxScheme");

                Element IDIGV = doc.createElement("cbc:ID");
                IDIGV.appendChild(doc.createTextNode("1000"));
                TaxSchemeIGV.appendChild(IDIGV);

                Element NameIGV = doc.createElement("cbc:Name");
                NameIGV.appendChild(doc.createTextNode("IGV"));
                TaxSchemeIGV.appendChild(NameIGV);

                Element TaxTypeCodeIGV = doc.createElement("cbc:TaxTypeCode");
                TaxTypeCodeIGV.appendChild(doc.createTextNode("VAT"));
                TaxSchemeIGV.appendChild(TaxTypeCodeIGV);

                TaxCategoryIGV.appendChild(TaxSchemeIGV);
                TaxSubtotalIGV.appendChild(TaxCategoryIGV);
                TaxTotalIGV.appendChild(TaxSubtotalIGV);

                SummaryDocumentsLine.appendChild(TaxTotalIGV);
                mainRootElement.appendChild(SummaryDocumentsLine);
            }

            // output DOM XML to console 
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
            StreamResult console = new StreamResult(System.out);
            transformer.transform(source, console);

            DOMSource domSource = new DOMSource(doc);
            //TransformerFactory tFactory = TransformerFactory.newInstance();
            //File folder = new File("x:\\devtroce\\java");
            FileWriter out = new FileWriter(RutaXml + ".XML");
            //Transformer transformer = tFactory.newTransformer();
            //transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(domSource, new StreamResult(out));

            System.out.println("\nXML DOM Created Successfully..");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    public int GenerarXMLCPE_BETA2(String RutaXml) {
        DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        try {
            icBuilder = icFactory.newDocumentBuilder();
            Document doc = icBuilder.newDocument();

            doc.setXmlVersion("1.0");

            Element mainRootElement = doc.createElementNS("urn:oasis:names:specification:ubl:schema:xsd:Invoice-2", "Invoice");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:sac", "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:cac", "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:cbc", "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:udt", "urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:ccts", "urn:un:unece:uncefact:documentation:2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:ext", "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:qdt", "urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:schemaLocation", "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            doc.appendChild(mainRootElement);

            Element UBLExtensions1 = doc.createElement("ext:UBLExtensions");
            Element UBLExtension1 = doc.createElement("ext:UBLExtension");
            Element ExtensionContent = doc.createElement("ext:ExtensionContent");
            Element AdditionalInformation = doc.createElement("sac:AdditionalInformation");
            /*
             Total valor de venta  - operaciones gravadas    1,407.29 
             Total valor de venta  - operaciones inafectas  
             Total valor de venta - operaciones exoneradas  
             Total valor de venta - operaciones gratuitas 48.00 
             */

            Element AdditionalMonetaryTotal1001 = doc.createElement("sac:AdditionalMonetaryTotal");//SUB TOTAL = OPE.GRAVADAS
            Element ID1001 = doc.createElement("cbc:ID");
            Element PayableAmount1001 = doc.createElement("cbc:PayableAmount");

            Element AdditionalMonetaryTotal1002 = doc.createElement("sac:AdditionalMonetaryTotal");//TOTAL OPERACIONES INAFECTAS
            Element ID1002 = doc.createElement("cbc:ID");
            Element PayableAmount1002 = doc.createElement("cbc:PayableAmount");

            Element AdditionalMonetaryTotal1003 = doc.createElement("sac:AdditionalMonetaryTotal");//TOTAL OPERACIONES EXONERADAS
            Element ID1003 = doc.createElement("cbc:ID");
            Element PayableAmount1003 = doc.createElement("cbc:PayableAmount");

            Element AdditionalMonetaryTotal1004 = doc.createElement("sac:AdditionalMonetaryTotal");//TOTAL OPERACIONES GRATUITAS
            Element ID1004 = doc.createElement("cbc:ID");
            Element PayableAmount1004 = doc.createElement("cbc:PayableAmount");

            Element AdditionalMonetaryTotal2001 = doc.createElement("sac:AdditionalMonetaryTotal");//TOTAL PERCEPCIONES
            Element ID2001 = doc.createElement("cbc:ID");
            Element PayableAmount2001 = doc.createElement("cbc:PayableAmount");

            Element AdditionalMonetaryTotal2002 = doc.createElement("sac:AdditionalMonetaryTotal");//TOTAL RETENCIONES
            Element ID2002 = doc.createElement("cbc:ID");
            Element PayableAmount2002 = doc.createElement("cbc:PayableAmount");

            Element AdditionalMonetaryTotal2003 = doc.createElement("sac:AdditionalMonetaryTotal");//TOTAL DETRACCIONES
            Element ID2003 = doc.createElement("cbc:ID");
            Element PayableAmount2003 = doc.createElement("cbc:PayableAmount");

            Element AdditionalMonetaryTotal2004 = doc.createElement("sac:AdditionalMonetaryTotal");//TOTAL BONIFICACIONES
            Element ID2004 = doc.createElement("cbc:ID");
            Element PayableAmount2004 = doc.createElement("cbc:PayableAmount");

            Element AdditionalMonetaryTotal2005 = doc.createElement("sac:AdditionalMonetaryTotal");//TOTAL DESCUENTO
            Element ID2005 = doc.createElement("cbc:ID");
            Element PayableAmount2005 = doc.createElement("cbc:PayableAmount");

            //====================(CODIGO 1001) 15 - Total valor de venta - operaciones gravadas (SUB TOTAL SIN IGV)======================  
            AdditionalMonetaryTotal1001.appendChild(ID1001);
            ID1001.appendChild(doc.createTextNode("1001"));//CODIGO                    
            AdditionalMonetaryTotal1001.appendChild(PayableAmount1001);
            PayableAmount1001.setAttribute("currencyID", "PEN");//MONEDA
            PayableAmount1001.appendChild(doc.createTextNode(Double.toString(625.00)));//MONTO -- 1407.29
            AdditionalInformation.appendChild(AdditionalMonetaryTotal1001);
            //====================(CODIGO 1002) 16 - Total valor de venta - operaciones inafectas====================== 
            AdditionalMonetaryTotal1002.appendChild(ID1002);
            ID1002.appendChild(doc.createTextNode("1002"));//CODIGO                    
            AdditionalMonetaryTotal1002.appendChild(PayableAmount1002);
            PayableAmount1002.setAttribute("currencyID", "PEN");//MONEDA
            PayableAmount1002.appendChild(doc.createTextNode(Double.toString(0.00)));//MONTO - 48.00
            AdditionalInformation.appendChild(AdditionalMonetaryTotal1002);

            //====================(CODIGO 1003) 17 - Total valor de venta - operaciones exoneradas====================== 
            AdditionalMonetaryTotal1003.appendChild(ID1003);
            ID1003.appendChild(doc.createTextNode("1003"));//CODIGO                    
            AdditionalMonetaryTotal1003.appendChild(PayableAmount1003);
            PayableAmount1003.setAttribute("currencyID", "PEN");//MONEDA
            PayableAmount1003.appendChild(doc.createTextNode(Double.toString(0.00)));//MONTO
            AdditionalInformation.appendChild(AdditionalMonetaryTotal1003);

            //====================(CODIGO 1004) 18 - Total valor de venta - operaciones gratuitas======================  
            AdditionalMonetaryTotal1004.appendChild(ID1004);
            ID1004.appendChild(doc.createTextNode("1004"));//CODIGO                    
            AdditionalMonetaryTotal1004.appendChild(PayableAmount1004);
            PayableAmount1004.setAttribute("currencyID", "PEN");//MONEDA
            PayableAmount1004.appendChild(doc.createTextNode(Double.toString(0.00)));//MONTO 
            AdditionalInformation.appendChild(AdditionalMonetaryTotal1004);

            //====================(CODIGO 2001) Total PERCEPCIONES======================  
            /*
             if (Cpe.getTOTAL_PERCEPCIONES() > 0) {
             AdditionalMonetaryTotal2001.appendChild(ID2001);
             ID2001.appendChild(doc.createTextNode("2001"));//CODIGO                    
             AdditionalMonetaryTotal2001.appendChild(PayableAmount2001);
             PayableAmount2001.setAttribute("currencyID", Cpe.getCOD_MONEDA());//MONEDA
             PayableAmount2001.appendChild(doc.createTextNode(Double.toString(Cpe.getTOTAL_PERCEPCIONES())));//MONTO  
             AdditionalInformation.appendChild(AdditionalMonetaryTotal2001);
             }
             //====================(CODIGO 2002) Total RETENCIONES======================  
             if (Cpe.getTOTAL_RETENCIONES() > 0) {
             AdditionalMonetaryTotal2002.appendChild(ID2002);
             ID2002.appendChild(doc.createTextNode("2002"));//CODIGO                    
             AdditionalMonetaryTotal2002.appendChild(PayableAmount2002);
             PayableAmount2002.setAttribute("currencyID", Cpe.getCOD_MONEDA());//MONEDA
             PayableAmount2002.appendChild(doc.createTextNode(Double.toString(Cpe.getTOTAL_RETENCIONES())));//MONTO  
             AdditionalInformation.appendChild(AdditionalMonetaryTotal2002);
             }
            
             //====================(CODIGO 2003) Total DETRACCIONES====================== 
             if (Cpe.getTOTAL_DETRACCIONES() > 0) {
             AdditionalMonetaryTotal2003.appendChild(ID2003);
             ID2003.appendChild(doc.createTextNode("2003"));//CODIGO                    
             AdditionalMonetaryTotal2003.appendChild(PayableAmount2003);
             PayableAmount2003.setAttribute("currencyID", Cpe.getCOD_MONEDA());//MONEDA
             PayableAmount2003.appendChild(doc.createTextNode(Double.toString(Cpe.getTOTAL_DETRACCIONES())));//MONTO  
             AdditionalInformation.appendChild(AdditionalMonetaryTotal2003);
             }
             //====================(CODIGO 2004) Total BONIFICACIONES====================== 
             if (Cpe.getTOTAL_BONIFICACIONES() > 0) {
             AdditionalMonetaryTotal2004.appendChild(ID2004);
             ID2004.appendChild(doc.createTextNode("2004"));//CODIGO                    
             AdditionalMonetaryTotal2004.appendChild(PayableAmount2004);
             PayableAmount2004.setAttribute("currencyID", Cpe.getCOD_MONEDA());//MONEDA
             PayableAmount2004.appendChild(doc.createTextNode(Double.toString(Cpe.getTOTAL_BONIFICACIONES())));//MONTO  
             AdditionalInformation.appendChild(AdditionalMonetaryTotal2004);
             }
             //====================(CODIGO 2005) Total Descuento======================  
             if (Cpe.getTOTAL_DESCUENTO() > 0) {
             AdditionalMonetaryTotal2005.appendChild(ID2005);
             ID2005.appendChild(doc.createTextNode("2005"));//CODIGO                    
             AdditionalMonetaryTotal2005.appendChild(PayableAmount2005);
             PayableAmount2005.setAttribute("currencyID", Cpe.getCOD_MONEDA());//MONEDA
             PayableAmount2005.appendChild(doc.createTextNode(Double.toString(Cpe.getTOTAL_DESCUENTO())));//MONTO  
             AdditionalInformation.appendChild(AdditionalMonetaryTotal2005);
             }
             */
            //================================================================================
            ExtensionContent.appendChild(AdditionalInformation);
            UBLExtension1.appendChild(ExtensionContent);
            UBLExtensions1.appendChild(UBLExtension1);

            //===============================LEYENDA (MONTO TOTAL EN LETRAS)==============================
            Element AdditionalProperty = doc.createElement("sac:AdditionalProperty");
            Element ID1000L = doc.createElement("cbc:ID");
            Element Value1000L = doc.createElement("cbc:Value");

            //====================EN LETRAS LOS TOTALES == (CODIGO 1000) 15 - Total valor de venta - operaciones gravadas MONTO TOTAL=SUB.TOTAL+IGV======================  
            AdditionalProperty.appendChild(ID1000L);
            ID1000L.appendChild(doc.createTextNode("1000"));//CODIGO                    
            AdditionalProperty.appendChild(Value1000L);
            Value1000L.appendChild(doc.createTextNode("SETECIENTOS TREINTA Y SIETE CON 50/100 SOLES"));//MONTO    
            AdditionalInformation.appendChild(AdditionalProperty);
            //===============================LEYENDA (SI GENERA COMPROBANTE PERCEPCION)==============================
            /*
             if (Cpe.getTOTAL_PERCEPCIONES() > 0) {
             Element AdditionalProperty2000 = doc.createElement("sac:AdditionalProperty");
             Element ID2000L = doc.createElement("cbc:ID");
             Element Value2000L = doc.createElement("cbc:Value");
             //====================(CODIGO 2000) LEYENDA COMPROBANTE DE PERCEPCION======================  
             AdditionalProperty2000.appendChild(ID2000L);
             ID2000L.appendChild(doc.createTextNode("2000"));//CODIGO                    
             AdditionalProperty2000.appendChild(Value2000L);
             Value2000L.appendChild(doc.createTextNode("COMPROBANTE DE PERCEPCION"));//MONTO   
             AdditionalInformation.appendChild(AdditionalProperty2000);
             }
             */
            ExtensionContent.appendChild(AdditionalInformation);
            UBLExtension1.appendChild(ExtensionContent);
            UBLExtensions1.appendChild(UBLExtension1);

            /*
             =====================EXTENCION PARA COLOCAR LA FIRMA====================
             */
            Element UBLExtensionFirma = doc.createElement("ext:UBLExtension");
            Element ExtensionContentFirma = doc.createElement("ext:ExtensionContent");
            ExtensionContentFirma.appendChild(doc.createTextNode(""));
            UBLExtensionFirma.appendChild(ExtensionContentFirma);
            UBLExtensions1.appendChild(UBLExtensionFirma);
            mainRootElement.appendChild(UBLExtensions1);

            /*
             ==================DATOS GENERALES (VERSION,NRO DOCUMENTO,FECHA,TIPO DOCUMENTO,MONEDA)======================
             */
            Element UBLVersionID = doc.createElement("cbc:UBLVersionID");
            UBLVersionID.appendChild(doc.createTextNode("2.0"));
            mainRootElement.appendChild(UBLVersionID);

            Element CustomizationID = doc.createElement("cbc:CustomizationID");
            CustomizationID.appendChild(doc.createTextNode("1.0"));
            mainRootElement.appendChild(CustomizationID);

            Element IDNRO_DOCUMENTO = doc.createElement("cbc:ID");
            IDNRO_DOCUMENTO.appendChild(doc.createTextNode("BB11-750"));
            mainRootElement.appendChild(IDNRO_DOCUMENTO);

            Element IssueDate = doc.createElement("cbc:IssueDate");
            IssueDate.appendChild(doc.createTextNode("2016-09-28"));
            mainRootElement.appendChild(IssueDate);

            Element InvoiceTypeCode = doc.createElement("cbc:InvoiceTypeCode");
            InvoiceTypeCode.appendChild(doc.createTextNode("03"));//TIPO DOCUMENTO ====> 03=BOLETA,01=FACTURA            
            mainRootElement.appendChild(InvoiceTypeCode);

            Element DocumentCurrencyCode = doc.createElement("cbc:DocumentCurrencyCode");
            DocumentCurrencyCode.appendChild(doc.createTextNode("PEN"));//MONEDA DE LA BOLETA/FACTURA
            mainRootElement.appendChild(DocumentCurrencyCode);

            /*
             ==================SIGNATURE (DATOS EMPRESA Y OTROS)======================
             */
            Element Signature = doc.createElement("cac:Signature");
            Element IDSignature = doc.createElement("cbc:ID");
            IDSignature.appendChild(doc.createTextNode("SFBB11-750"));//ID DEL DOCUMENTO ES(NOMENCLATURA + NUMERO DE DOCUMENTO)
            Signature.appendChild(IDSignature);

            Element SignatoryParty = doc.createElement("cac:SignatoryParty");

            //==============================RUC/DNI DE LA EMPRESA (EMISORA)=============================
            Element PartyIdentification = doc.createElement("cac:PartyIdentification");
            Element ID_PartyIdentification = doc.createElement("cbc:ID");//RUC DEL EMISORA
            ID_PartyIdentification.appendChild(doc.createTextNode("20100066603"));//RUC DEL EMISORA
            PartyIdentification.appendChild(ID_PartyIdentification);
            SignatoryParty.appendChild(PartyIdentification);
            //==============================RAZON SOCIAL DE LA EMPRESA CLIENTE=============================
            Element PartyName = doc.createElement("cac:PartyName");
            Element Name = doc.createElement("cbc:Name");
            Name.appendChild(doc.createTextNode("CREVPERU S.A."));
            PartyName.appendChild(Name);
            SignatoryParty.appendChild(PartyName);

            //=========================ADICIONAMOS RUC/RAZON SOCIAL=========================
            Signature.appendChild(SignatoryParty);

            //==============================IDENTIFICACION DEL DOCUMENTO AGREGANDO #=============================
            Element DigitalSignatureAttachment = doc.createElement("cac:DigitalSignatureAttachment");
            Element ExternalReference = doc.createElement("cac:ExternalReference");
            Element URI = doc.createElement("cbc:URI");
            URI.appendChild(doc.createTextNode("#" + "SFBB11-750"));
            ExternalReference.appendChild(URI);
            DigitalSignatureAttachment.appendChild(ExternalReference);
            Signature.appendChild(DigitalSignatureAttachment);

            mainRootElement.appendChild(Signature);

            //==============================IDENTIFICACION DE LA EMPRESA VENDEDORA(AccountingSupplierParty) =============================
            Element AccountingSupplierParty = doc.createElement("cac:AccountingSupplierParty");

            Element CustomerAssignedAccountID = doc.createElement("cbc:CustomerAssignedAccountID");
            CustomerAssignedAccountID.appendChild(doc.createTextNode("20100066603"));//RUC
            AccountingSupplierParty.appendChild(CustomerAssignedAccountID);//RUC

            Element AdditionalAccountID = doc.createElement("cbc:AdditionalAccountID");
            AdditionalAccountID.appendChild(doc.createTextNode("6"));//TIPO DOCUMENTO ES RUC DE LA EMPRESA 6
            AccountingSupplierParty.appendChild(AdditionalAccountID);

            //==============================INFORMACION DE LA EMPRESA QUE VENDE=============================
            Element Party = doc.createElement("cac:Party");

            Element PartyName_AccountingSupplierParty = doc.createElement("cac:PartyName");
            Element Name_AccountingSupplierParty = doc.createElement("cbc:Name");
            Name_AccountingSupplierParty.appendChild(doc.createTextNode("NOM COMERCIAL CREVPERU S.A"));//NOMBRE COMERCIAL
            PartyName_AccountingSupplierParty.appendChild(Name_AccountingSupplierParty);
            Party.appendChild(PartyName_AccountingSupplierParty);

            Element PostalAddress = doc.createElement("cac:PostalAddress");

            Element ID_PostalAddress = doc.createElement("cbc:ID");
            ID_PostalAddress.appendChild(doc.createTextNode("070104"));//CODIGO UBIGEO
            PostalAddress.appendChild(ID_PostalAddress);

            Element StreetName = doc.createElement("cbc:StreetName");
            StreetName.appendChild(doc.createTextNode("JR CHACLACAYO 121"));//
            PostalAddress.appendChild(StreetName);

            Element CitySubdivisionName = doc.createElement("cbc:CitySubdivisionName");
            CitySubdivisionName.appendChild(doc.createTextNode(""));//URBANIZAION, ZONA, AAHH
            PostalAddress.appendChild(CitySubdivisionName);

            Element CityName = doc.createElement("cbc:CityName");
            CityName.appendChild(doc.createTextNode("LIMA"));//DEPARTAMENTO
            PostalAddress.appendChild(CityName);

            Element CountrySubentity = doc.createElement("cbc:CountrySubentity");
            CountrySubentity.appendChild(doc.createTextNode("LIMA"));//PROVINCIA
            PostalAddress.appendChild(CountrySubentity);

            Element District = doc.createElement("cbc:District");
            District.appendChild(doc.createTextNode("LIMA"));//DISTRITO
            PostalAddress.appendChild(District);

            Element Country = doc.createElement("cac:Country");
            Element IdentificationCode = doc.createElement("cbc:IdentificationCode");
            IdentificationCode.appendChild(doc.createTextNode("PE"));//
            Country.appendChild(IdentificationCode);
            PostalAddress.appendChild(Country);

            Party.appendChild(PostalAddress);

            Element PartyLegalEntity = doc.createElement("cac:PartyLegalEntity");
            Element RegistrationName = doc.createElement("cbc:RegistrationName");
            RegistrationName.appendChild(doc.createTextNode("CREVPERU S.A."));//RAZON SOCIAL
            PartyLegalEntity.appendChild(RegistrationName);
            Party.appendChild(PartyLegalEntity);

            AccountingSupplierParty.appendChild(Party);
            mainRootElement.appendChild(AccountingSupplierParty);

            //==============================INFORMACION DEL CLIENTE=============================         
            Element AccountingCustomerParty = doc.createElement("cac:AccountingCustomerParty");

            Element CustomerAssignedAccountID_CLIENTE = doc.createElement("cbc:CustomerAssignedAccountID");
            CustomerAssignedAccountID_CLIENTE.appendChild(doc.createTextNode("10702144"));//DOUMENTO
            AccountingCustomerParty.appendChild(CustomerAssignedAccountID_CLIENTE);//NRO DOUMENTO

            Element AdditionalAccountID_CLIENTE = doc.createElement("cbc:AdditionalAccountID");
            AdditionalAccountID_CLIENTE.appendChild(doc.createTextNode("1"));//TIPO DOCUMENTO EMPRESA 1(DNI/RUC)
            AccountingCustomerParty.appendChild(AdditionalAccountID_CLIENTE);

            //==============================INFORMACION ADICINAL DEL CLIENTE=============================         
            Element Party_CLIENTE = doc.createElement("cac:Party");

            Element PartyLegalEntity_CLIENTE = doc.createElement("cac:PartyLegalEntity");

            Element RegistrationName_CLIENTE = doc.createElement("cbc:RegistrationName");
            RegistrationName_CLIENTE.appendChild(doc.createTextNode("JORGE LOPEZ"));//NOMBRE DEL CLIENTE O RAZON SOCIAL
            PartyLegalEntity_CLIENTE.appendChild(RegistrationName_CLIENTE);//

            Element RegistrationAddress = doc.createElement("cac:RegistrationAddress");

            Element StreetName_CLIENTE = doc.createElement("cbc:StreetName");
            StreetName_CLIENTE.appendChild(doc.createTextNode("CHACLACAYO"));//CIUDAD==DEPARTAMENTO
            RegistrationAddress.appendChild(StreetName_CLIENTE);//CIUDAD

            Element Country_CLIENTE = doc.createElement("cac:Country");

            Element IdentificationCode_CLIENTE = doc.createElement("cbc:IdentificationCode");
            IdentificationCode_CLIENTE.appendChild(doc.createTextNode("PE"));//PAIS
            Country_CLIENTE.appendChild(IdentificationCode_CLIENTE);//PAIS            
            RegistrationAddress.appendChild(Country_CLIENTE);
            PartyLegalEntity_CLIENTE.appendChild(RegistrationAddress);

            Party_CLIENTE.appendChild(PartyLegalEntity_CLIENTE);
            AccountingCustomerParty.appendChild(Party_CLIENTE);
            mainRootElement.appendChild(AccountingCustomerParty);

            //====================================================IGV===================================================
            //==============================TOTALES IGV (IMPUESTOS) DE LA FACTURA EN MONTO=============================   
            Element TaxTotal = doc.createElement("cac:TaxTotal");

            Element TaxAmount = doc.createElement("cbc:TaxAmount");
            TaxAmount.setAttribute("currencyID", "PEN");//MONEDA 
            TaxAmount.appendChild(doc.createTextNode(Double.toString(112.50)));//TOTAL
            TaxTotal.appendChild(TaxAmount);//CIUDAD

            //==============================SUB TOTALES (IMPUESTOS) Y SUS CODIGOS============================= 
            Element TaxSubtotal = doc.createElement("cac:TaxSubtotal");

            Element TaxAmount_SUBTOTAL = doc.createElement("cbc:TaxAmount");
            TaxAmount_SUBTOTAL.setAttribute("currencyID", "PEN");//MONEDA SUB TOTAL
            TaxAmount_SUBTOTAL.appendChild(doc.createTextNode(Double.toString(112.50)));//TOTAL
            TaxSubtotal.appendChild(TaxAmount_SUBTOTAL);//

            Element TaxCategory = doc.createElement("cac:TaxCategory");
            Element TaxScheme = doc.createElement("cac:TaxScheme");

            Element ID_TOTALES = doc.createElement("cbc:ID");
            ID_TOTALES.appendChild(doc.createTextNode("1000"));//
            TaxScheme.appendChild(ID_TOTALES);//

            Element Name_TOTALES = doc.createElement("cbc:Name");
            Name_TOTALES.appendChild(doc.createTextNode("IGV"));//
            TaxScheme.appendChild(Name_TOTALES);//

            Element TaxTypeCode_TOTALES = doc.createElement("cbc:TaxTypeCode");
            TaxTypeCode_TOTALES.appendChild(doc.createTextNode("VAT"));//
            TaxScheme.appendChild(TaxTypeCode_TOTALES);//

            TaxCategory.appendChild(TaxScheme);//            
            TaxSubtotal.appendChild(TaxCategory);//            
            TaxTotal.appendChild(TaxSubtotal);//            
            mainRootElement.appendChild(TaxTotal);

            //====================================================ISC===================================================
            //==============================TOTALES ISC (IMPUESTOS)=============================   
            /*
             Element TaxTotal_ISC = doc.createElement("cac:TaxTotal");

             Element TaxAmount_ISC = doc.createElement("cbc:TaxAmount");
             TaxAmount_ISC.setAttribute("currencyID", Cpe.getCOD_MONEDA());//MONEDA 
             TaxAmount_ISC.appendChild(doc.createTextNode(Double.toString(Cpe.getTOTAL_ISC())));//TOTAL
             TaxTotal_ISC.appendChild(TaxAmount_ISC);//

             //==============================SUB TOTALES (IMPUESTOS) Y SUS CODIGOS============================= 
             Element TaxSubtotal_ISC = doc.createElement("cac:TaxSubtotal");

             Element TaxAmount_SUBTOTAL_ISC = doc.createElement("cbc:TaxAmount");
             TaxAmount_SUBTOTAL_ISC.setAttribute("currencyID", Cpe.getCOD_MONEDA());//MONEDA SUB TOTAL
             TaxAmount_SUBTOTAL_ISC.appendChild(doc.createTextNode(Double.toString(Cpe.getTOTAL_ISC())));//TOTAL
             TaxSubtotal_ISC.appendChild(TaxAmount_SUBTOTAL_ISC);//

             Element TaxCategory_ISC = doc.createElement("cac:TaxCategory");
             Element TaxScheme_ISC = doc.createElement("cac:TaxScheme");

             Element ID_TOTALES_ISC = doc.createElement("cbc:ID");
             ID_TOTALES_ISC.appendChild(doc.createTextNode("2000"));//
             TaxScheme_ISC.appendChild(ID_TOTALES_ISC);//

             Element Name_TOTALES_ISC = doc.createElement("cbc:Name");
             Name_TOTALES_ISC.appendChild(doc.createTextNode("ISC"));//
             TaxScheme_ISC.appendChild(Name_TOTALES_ISC);//

             Element TaxTypeCode_TOTALES_ISC = doc.createElement("cbc:TaxTypeCode");
             TaxTypeCode_TOTALES_ISC.appendChild(doc.createTextNode("EXC"));//
             TaxScheme_ISC.appendChild(TaxTypeCode_TOTALES_ISC);//

             TaxCategory_ISC.appendChild(TaxScheme_ISC);//            
             TaxSubtotal_ISC.appendChild(TaxCategory_ISC);//            
             TaxTotal_ISC.appendChild(TaxSubtotal_ISC);//            
             mainRootElement.appendChild(TaxTotal_ISC);

             //====================================================OTROS IMPUESTOS===================================================
             //==============================TOTALES OTROS IMPUESTOS (IMPUESTOS)=============================  
             if (Cpe.getTOTAL_OTR_IMP() > 0) {
             Element TaxTotal_OTR = doc.createElement("cac:TaxTotal");

             Element TaxAmount_OTR = doc.createElement("cbc:TaxAmount");
             TaxAmount_OTR.setAttribute("currencyID", Cpe.getCOD_MONEDA());// 
             TaxAmount_OTR.appendChild(doc.createTextNode(Double.toString(Cpe.getTOTAL_OTR_IMP())));//
             TaxTotal_OTR.appendChild(TaxAmount_OTR);//

             //==============================SUB TOTALES (IMPUESTOS) Y SUS CODIGOS============================= 
             Element TaxSubtotal_OTR = doc.createElement("cac:TaxSubtotal");

             Element TaxAmount_SUBTOTAL_OTR = doc.createElement("cbc:TaxAmount");
             TaxAmount_SUBTOTAL_OTR.setAttribute("currencyID", Cpe.getCOD_MONEDA());//
             TaxAmount_SUBTOTAL_OTR.appendChild(doc.createTextNode(Double.toString(Cpe.getTOTAL_OTR_IMP())));//
             TaxSubtotal_OTR.appendChild(TaxAmount_SUBTOTAL_OTR);//

             Element TaxCategory_OTR = doc.createElement("cac:TaxCategory");
             Element TaxScheme_OTR = doc.createElement("cac:TaxScheme");

             Element ID_TOTALES_OTR = doc.createElement("cbc:ID");
             ID_TOTALES_OTR.appendChild(doc.createTextNode("9999"));//
             TaxScheme_OTR.appendChild(ID_TOTALES_OTR);//

             Element Name_TOTALES_OTR = doc.createElement("cbc:Name");
             Name_TOTALES_OTR.appendChild(doc.createTextNode("OTROS"));//
             TaxScheme_OTR.appendChild(Name_TOTALES_OTR);//

             Element TaxTypeCode_TOTALES_OTR = doc.createElement("cbc:TaxTypeCode");
             TaxTypeCode_TOTALES_OTR.appendChild(doc.createTextNode("OTH"));//
             TaxScheme_OTR.appendChild(TaxTypeCode_TOTALES_OTR);//

             TaxCategory_OTR.appendChild(TaxScheme_OTR);//            
             TaxSubtotal_OTR.appendChild(TaxCategory_OTR);//            
             TaxTotal_OTR.appendChild(TaxSubtotal_OTR);//            
             mainRootElement.appendChild(TaxTotal_OTR);
             }
             */
            //=================================SUB TOTAL, IGV, TOTAL (EN MONTOS)===================================
            Element LegalMonetaryTotal = doc.createElement("cac:LegalMonetaryTotal");

            Element LineExtensionAmount = doc.createElement("cbc:LineExtensionAmount");
            LineExtensionAmount.setAttribute("currencyID", "PEN");//MONEDA SUB TOTAL
            LineExtensionAmount.appendChild(doc.createTextNode(Double.toString(625.00)));//SUB TOTAL
            LegalMonetaryTotal.appendChild(LineExtensionAmount);//

            Element TaxExclusiveAmount = doc.createElement("cbc:TaxExclusiveAmount");
            TaxExclusiveAmount.setAttribute("currencyID", "PEN");//MONEDA IGV
            TaxExclusiveAmount.appendChild(doc.createTextNode(Double.toString(112.50)));//IGV
            LegalMonetaryTotal.appendChild(TaxExclusiveAmount);//

            Element PayableAmount = doc.createElement("cbc:PayableAmount");
            PayableAmount.setAttribute("currencyID", "PEN");//MONEDA TOTAL
            PayableAmount.appendChild(doc.createTextNode(Double.toString(737.50)));//TOTAL
            LegalMonetaryTotal.appendChild(PayableAmount);//

            mainRootElement.appendChild(LegalMonetaryTotal);

            //=========================================================================================
            //=================================INVOICE LINE===================================
            //=================================DETALLE DE PRODUCTOS===================================
            //for (int i = 0; i < lstCpe_Detalle.size(); i++) {
            //Cpe_DetalleBean Cpe_Detalle = lstCpe_Detalle.get(i);
            Element InvoiceLine = doc.createElement("cac:InvoiceLine");

            Element ID_DETALLE = doc.createElement("cbc:ID");//ITEM CORRELATIVO
            ID_DETALLE.appendChild(doc.createTextNode(Integer.toString(1)));//ITEM CORRELATIVO DE DETALLE(ID PRODUCTO)
            InvoiceLine.appendChild(ID_DETALLE);//

            //=====================UNIDAD MEDIDA Y CANTIDAD======================
            Element InvoicedQuantity = doc.createElement("cbc:InvoicedQuantity");
            InvoicedQuantity.setAttribute("unitCode", "NIU");//UNIDAD MEDIDA
            InvoicedQuantity.appendChild(doc.createTextNode(Double.toString(1)));//CANTIDAD
            InvoiceLine.appendChild(InvoicedQuantity);//

            //=====================MONEDA Y PRECIO======================
            Element LineExtensionAmount_PRECIO = doc.createElement("cbc:LineExtensionAmount");//
            LineExtensionAmount_PRECIO.setAttribute("currencyID", "PEN");
            LineExtensionAmount_PRECIO.appendChild(doc.createTextNode(Double.toString(625.00)));//PRECIO O SUB.TOTAL,
            InvoiceLine.appendChild(LineExtensionAmount_PRECIO);//

            //===============================================================================
            Element PricingReference = doc.createElement("cac:PricingReference");
            Element AlternativeConditionPrice = doc.createElement("cac:AlternativeConditionPrice");

            Element PriceAmount = doc.createElement("cbc:PriceAmount");//
            PriceAmount.setAttribute("currencyID", "PEN");
            PriceAmount.appendChild(doc.createTextNode(Double.toString(737.50)));//IMPORTE O TOTAL(PRECIO * CANTIDAD)
            AlternativeConditionPrice.appendChild(PriceAmount);//

            Element PriceTypeCode = doc.createElement("cbc:PriceTypeCode");//
            PriceTypeCode.appendChild(doc.createTextNode("01"));//ANEXO 8 CATALOGO 01=SIGNIFICA IMPORTE TOTAL(APLICADO YA IGV,ISC,OTROS)            
            AlternativeConditionPrice.appendChild(PriceTypeCode);//                      

            PricingReference.appendChild(AlternativeConditionPrice);//            
            InvoiceLine.appendChild(PricingReference);//

            //===============================================IMPUESTOS==================================================
            //========================================IMPUESTO DETALLE IGV========================================
            Element TaxTotal_DETALLE = doc.createElement("cac:TaxTotal");

            Element TaxAmount_DETALLE = doc.createElement("cbc:TaxAmount");
            TaxAmount_DETALLE.setAttribute("currencyID", "PEN");//MONEDA IGV
            TaxAmount_DETALLE.appendChild(doc.createTextNode(Double.toString(112.50)));// IGV
            TaxTotal_DETALLE.appendChild(TaxAmount_DETALLE);//

            Element TaxSubtotal_DETALLE = doc.createElement("cac:TaxSubtotal");

            Element TaxAmount_DETALLE_ST = doc.createElement("cbc:TaxAmount");
            TaxAmount_DETALLE_ST.setAttribute("currencyID", "PEN");//MONEDA IGV
            TaxAmount_DETALLE_ST.appendChild(doc.createTextNode(Double.toString(112.50)));// IGV
            TaxSubtotal_DETALLE.appendChild(TaxAmount_DETALLE_ST);//

            Element TaxCategory_DETALLE_ST = doc.createElement("cac:TaxCategory");

            //=================================REVISAR CODIGO 10 QUE SIGNIFICA===============================
            Element TaxExemptionReasonCode = doc.createElement("cbc:TaxExemptionReasonCode");//
            TaxExemptionReasonCode.appendChild(doc.createTextNode("10"));//10==GRAVADO OPERACION ONEROSA            
            TaxCategory_DETALLE_ST.appendChild(TaxExemptionReasonCode);//   

            Element TaxScheme_DETALLE_ST = doc.createElement("cac:TaxScheme");

            //====CODIGO IGV
            Element ID_IGV_DETALLE = doc.createElement("cbc:ID");//
            ID_IGV_DETALLE.appendChild(doc.createTextNode("1000"));//CODIGO IGV            
            TaxScheme_DETALLE_ST.appendChild(ID_IGV_DETALLE);//  

            //====CODIGO IGV
            Element Name_IGV_DETALLE = doc.createElement("cbc:Name");//
            Name_IGV_DETALLE.appendChild(doc.createTextNode("IGV"));//CODIGO IGV            
            TaxScheme_DETALLE_ST.appendChild(Name_IGV_DETALLE);//  

            //====CODIGO CODIGO 2 IGV(ALGO INTERNO)
            Element TaxTypeCode_IGV_DETALLE = doc.createElement("cbc:TaxTypeCode");//
            TaxTypeCode_IGV_DETALLE.appendChild(doc.createTextNode("VAT"));//CODIGO IGV            
            TaxScheme_DETALLE_ST.appendChild(TaxTypeCode_IGV_DETALLE);// 

            TaxCategory_DETALLE_ST.appendChild(TaxScheme_DETALLE_ST);//   
            TaxSubtotal_DETALLE.appendChild(TaxCategory_DETALLE_ST);//

            TaxTotal_DETALLE.appendChild(TaxSubtotal_DETALLE);//         
            InvoiceLine.appendChild(TaxTotal_DETALLE);//  

            //========================================IMPUESTO DETALLE ISC========================================
            /*
             if (Cpe_Detalle.getISC() > 0) {
             Element TaxTotal_DETALLE_ISC = doc.createElement("cac:TaxTotal");

             Element TaxAmount_DETALLE_ISC = doc.createElement("cbc:TaxAmount");
             TaxAmount_DETALLE_ISC.setAttribute("currencyID", Cpe.getCOD_MONEDA());//MONEDA IGV
             TaxAmount_DETALLE_ISC.appendChild(doc.createTextNode(Double.toString(Cpe_Detalle.getISC())));// IGV
             TaxTotal_DETALLE_ISC.appendChild(TaxAmount_DETALLE_ISC);//

             Element TaxSubtotal_DETALLE_ISC = doc.createElement("cac:TaxSubtotal");

             Element TaxAmount_DETALLE_ST_ISC = doc.createElement("cbc:TaxAmount");
             TaxAmount_DETALLE_ST_ISC.setAttribute("currencyID", Cpe.getCOD_MONEDA());//MONEDA IGV
             TaxAmount_DETALLE_ST_ISC.appendChild(doc.createTextNode(Double.toString(Cpe_Detalle.getIGV())));// IGV
             TaxSubtotal_DETALLE_ISC.appendChild(TaxAmount_DETALLE_ST_ISC);//

             Element TaxCategory_DETALLE_ST_ISC = doc.createElement("cac:TaxCategory");

             //=================================REVISAR CODIGO 10 QUE SIGNIFICA===============================
             //<cbc:TierRange>02</cbc:TierRange> 
             Element TierRange_ISC = doc.createElement("cbc:TierRange");//
             TierRange_ISC.appendChild(doc.createTextNode("02"));//02==AUN NO ENTIENDO
             TaxCategory_DETALLE_ST_ISC.appendChild(TierRange_ISC);//   

             Element TaxScheme_DETALLE_ST_ISC = doc.createElement("cac:TaxScheme");

             //====CODIGO IGV
             Element ID_IGV_DETALLE_ISC = doc.createElement("cbc:ID");//
             ID_IGV_DETALLE_ISC.appendChild(doc.createTextNode("2000"));//CODIGO IGV            
             TaxScheme_DETALLE_ST_ISC.appendChild(ID_IGV_DETALLE_ISC);//  

             //====CODIGO IGV
             Element Name_IGV_DETALLE_ISC = doc.createElement("cbc:Name");//
             Name_IGV_DETALLE_ISC.appendChild(doc.createTextNode("ISC"));//CODIGO IGV            
             TaxScheme_DETALLE_ST_ISC.appendChild(Name_IGV_DETALLE_ISC);//  

             //====CODIGO CODIGO 2 IGV(ALGO INTERNO)
             Element TaxTypeCode_IGV_DETALLE_ISC = doc.createElement("cbc:TaxTypeCode");//
             TaxTypeCode_IGV_DETALLE_ISC.appendChild(doc.createTextNode("EXC"));//CODIGO IGV            
             TaxScheme_DETALLE_ST_ISC.appendChild(TaxTypeCode_IGV_DETALLE_ISC);// 

             TaxCategory_DETALLE_ST_ISC.appendChild(TaxScheme_DETALLE_ST_ISC);//   
             TaxSubtotal_DETALLE_ISC.appendChild(TaxCategory_DETALLE_ST_ISC);//

             TaxTotal_DETALLE_ISC.appendChild(TaxSubtotal_DETALLE_ISC);//         
             InvoiceLine.appendChild(TaxTotal_DETALLE_ISC);// 
             }
             */
            //======================CODIGO Y DESCIPCION DEL PRODUCTO=======================
            Element Item = doc.createElement("cac:Item");

            Element Description = doc.createElement("cbc:Description");//
            Description.appendChild(doc.createTextNode("PRUEBA"));// descripcion         
            Item.appendChild(Description);//

            Element SellersItemIdentification = doc.createElement("cac:SellersItemIdentification");
            Element ID_CODIGO_INTERNO_DETALLE = doc.createElement("cbc:ID");//
            ID_CODIGO_INTERNO_DETALLE.appendChild(doc.createTextNode("C0001"));//CODIGO INTERNO            
            SellersItemIdentification.appendChild(ID_CODIGO_INTERNO_DETALLE);//
            Item.appendChild(SellersItemIdentification);//

            InvoiceLine.appendChild(Item);// 

            //======================PRECIO VENTA SIN IGV=======================
            Element Price = doc.createElement("cac:Price");

            Element PriceAmount_DETALLE_ST = doc.createElement("cbc:PriceAmount");
            PriceAmount_DETALLE_ST.setAttribute("currencyID", "PEN");//
            PriceAmount_DETALLE_ST.appendChild(doc.createTextNode(Double.toString(625.00)));//PRECIO O SUB.TOTAL
            Price.appendChild(PriceAmount_DETALLE_ST);//
            InvoiceLine.appendChild(Price);// 

            mainRootElement.appendChild(InvoiceLine);

            //}//FIN DE BUCLE
            // output DOM XML to console 
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
            StreamResult console = new StreamResult(System.out);
            transformer.transform(source, console);

            DOMSource domSource = new DOMSource(doc);
            //TransformerFactory tFactory = TransformerFactory.newInstance();
            //FileWriter out = new FileWriter(VarGlo.rutaXMLCPE+"\\BoletaB11-2.xml");//
            FileWriter out = new FileWriter(RutaXml + ".XML");
            //Transformer transformer = tFactory.newTransformer();
            //transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(domSource, new StreamResult(out));

            System.out.println("\nXML DOM Created Successfully..");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

}
