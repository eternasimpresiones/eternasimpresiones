/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gteperu.erp.everest.cpe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.crypto.MarshalException;
import javax.xml.parsers.ParserConfigurationException;

import org.gteperu.erp.everest.domain.Auditoria_Sunat;
import org.gteperu.erp.everest.domain.Cuota;
import org.gteperu.erp.everest.domain._CpeBean;
import org.gteperu.erp.everest.domain._CpeGuiaRemisionBean;
import org.gteperu.erp.everest.domain._CpeGuiaRemisionDetalleBean;
import org.gteperu.erp.everest.domain._Cpe_BajaBean;
import org.gteperu.erp.everest.domain._Cpe_Baja_DetalleBean;
import org.gteperu.erp.everest.domain._Cpe_DetalleBean;
import org.gteperu.erp.everest.domain._Cpe_Resumen_BoletaBean;
import org.gteperu.erp.everest.domain._Cpe_Resumen_Boleta_DetalleBean;
import org.gteperu.erp.everest.domain._Cpe_RetencionPercepcionBean;
import org.gteperu.erp.everest.domain._Cpe_RetencionPercepcion_DetalleBean;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._DocumentoCpe_DetalleBean;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.domain._Retencion;
import org.gteperu.erp.everest.mappers.ParametrosMapper;
import org.gteperu.erp.everest.service.Auditoria_SunatService;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service._CompanyService;
import org.gteperu.erp.everest.utils.Constantes;
import org.gteperu.erp.everest.utils.Numero_Letras;
import org.gteperu.erp.everest.utils.VariablesGlobales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

/**
 *
 * @author developer2
 */
@Service
public class CPE {

	// @Resource(name = "companyService")
	@Autowired
	_CompanyService companyService;

	// @Resource(name = "clienteService")
//	@Autowired
//	_ClienteService clienteService;

	@Resource(name = "parametrosService")
	ParametrosService parametrosService;

	@Autowired
	ParametrosMapper mapperParametros;

	@Autowired
	Auditoria_SunatService auditoriaSunatService;

	static VariablesGlobales VarGlo = new VariablesGlobales();
	private static CPESunatUBL21 CPESunat = new CPESunatUBL21();
	private static Signature Signature = new Signature();
	private static sunat sunat = new sunat();
	// private static pdf pdf = new pdf();
	private static _CpeBean Cpe;
	private static _Cpe_DetalleBean Cpe_Detalle;
	String separador = System.getProperty("file.separator");

//    private static CpeBean Cpe_Retencion;
//    private static Cpe_DetalleBean Cpe_Retencion_Detalle;
	/**
	 * @param args the command line arguments
	 */
	// EJEMPLOS DE COMPROBANTES (BOLETA, FACTURA, NOTA CREDITO, NOTA DEBITO)
	// https://gist.github.com/giansalex/53d3b6dadb5305ee95928a854ee3abc4
	public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, ParserConfigurationException, SAXException, MarshalException,
			KeyStoreException, IOException, CertificateException, Exception {
		String ruc = "20523799623";
		String UsuSol = "MODDATOS";
		String PassSol = "moddatos";
		String PassFirma = "20523799623";
		// String RutaCPE =
		// "20100066603-03-BB11-750";//"F:\\cert\\20100066603-03-BB11-750.ZIP";

		// String RutaRta = "F:\\cert\\";
		// String NombreArchvoRta = "";//"R-20100066603-03-BB11-750";
		Cpe = new _CpeBean();
		Cpe.setTIPO_OPERACION("0101");
		// Cpe.setTIPO_OPERACION("0104");
		Cpe.setTOTAL_GRAVADAS(625);
		Cpe.setTOTAL_INAFECTA(0);
		Cpe.setTOTAL_EXONERADAS(0);
		Cpe.setTOTAL_GRATUITAS(0);
		Cpe.setTOTAL_PERCEPCIONES(0);
		Cpe.setTOTAL_RETENCIONES(0);
		Cpe.setTOTAL_DETRACCIONES(0);
		Cpe.setTOTAL_BONIFICACIONES(0);
		Cpe.setTOTAL_DESCUENTO(0);
		Cpe.setSUB_TOTAL(625);
		Cpe.setPOR_IGV(18);// UBL2.1
		Cpe.setTOTAL_IGV(112.50);
		Cpe.setTOTAL_ISC(0);
		Cpe.setTOTAL_OTR_IMP(0);
		Cpe.setTOTAL(737.50);
		Cpe.setTOTAL_LETRAS("SETECIENTOS TREINTA Y SIETE CON 50/100 SOLES");
		Cpe.setNRO_GUIA_REMISION("");
		Cpe.setCOD_GUIA_REMISION("");
		Cpe.setNRO_OTR_COMPROBANTE("");
		Cpe.setCOD_OTR_COMPROBANTE("");
		Cpe.setNRO_COMPROBANTE("B020-8000");
		Cpe.setFECHA_DOCUMENTO("2018-10-11");
		Cpe.setFECHA_VTO("2018-10-11");
		Cpe.setCOD_TIPO_DOCUMENTO("03");// 01=factura, 03=boleta, 07=nota credito, 08=nota debito
////        //=================nota credito================
//        Cpe.setTIPO_COMPROBANTE_MODIFICA("03");
//        Cpe.setNRO_DOCUMENTO_MODIFICA("B001-1");
//        Cpe.setCOD_TIPO_MOTIVO("01");
//        Cpe.setDESCRIPCION_MOTIVO("ANULACION DE LA OPERACION");
		// =================nota debiyo================
////        Cpe.setTIPO_COMPROBANTE_MODIFICA("01");
////	Cpe.setNRO_DOCUMENTO_MODIFICA("F001-1");
////	Cpe.setCOD_TIPO_MOTIVO("01");
////	Cpe.setDESCRIPCION_MOTIVO("INCREMENTO DE PRECIO");
		// ============================= ====
		Cpe.setCOD_MONEDA("PEN");
		Cpe.setNRO_DOCUMENTO_CLIENTE("00000000");
		Cpe.setRAZON_SOCIAL_CLIENTE("CLIENTES VARIOS");
		Cpe.setTIPO_DOCUMENTO_CLIENTE("1");// 1=DNI, 6=RUC
		// ================================
		Cpe.setCOD_UBIGEO_CLIENTE("");// NUEVO UBL2.1
		Cpe.setDEPARTAMENTO_CLIENTE("");// NUEVO UBL2.1
		Cpe.setPROVINCIA_CLIENTE("");// NUEVO UBL2.1
		Cpe.setDISTRITO_CLIENTE("");// NUEVO UBL2.1
		// ===============================
		Cpe.setCIUDAD_CLIENTE("LIMA");
		Cpe.setCOD_PAIS_CLIENTE("PE");
		Cpe.setNRO_DOCUMENTO_EMPRESA("20100066603");
		Cpe.setTIPO_DOCUMENTO_EMPRESA("6");
		Cpe.setNOMBRE_COMERCIAL_EMPRESA("CREV PERU COMERCIAL");
		Cpe.setCODIGO_UBIGEO_EMPRESA("070104");
		Cpe.setDIRECCION_EMPRESA("PSJ HUAMPANI");
		Cpe.setDEPARTAMENTO_EMPRESA("LIMA");
		Cpe.setPROVINCIA_EMPRESA("LIMA");
		Cpe.setDISTRITO_EMPRESA("CHACLACAYO");
		Cpe.setCODIGO_PAIS_EMPRESA("PE");
		Cpe.setRAZON_SOCIAL_EMPRESA("CREVPERU S.A.");
		Cpe.setUSUARIO_SOL_EMPRESA("MODDATOS");
		Cpe.setPASS_SOL_EMPRESA("moddatos");
		Cpe.setTIPO_PROCESO(1);// 1=PRODUCCION,2=HOMOLOGACION,3=PRUEBA
		// ========================REGULARIZACION ANTICIPO========================
////        Cpe.setFLG_REGU_ANTICIPO(1);
////        Cpe.setNRO_COMPROBANTE_REF_ANT("F000-13334");
////        Cpe.setMONEDA_REGU_ANTICIPO("PEN");
////        Cpe.setMONTO_REGU_ANTICIPO(737.50);
////        Cpe.setTIPO_DOCUMENTO_EMP_REGU_ANT("6");
////        Cpe.setNRO_DOCUMENTO_EMP_REGU_ANT("20100066603");

		List<_Cpe_DetalleBean> lstCpe_Detalle = null;
		lstCpe_Detalle = new ArrayList<_Cpe_DetalleBean>();

		Cpe_Detalle = new _Cpe_DetalleBean();
		Cpe_Detalle.setITEM(1);
		Cpe_Detalle.setUNIDAD_MEDIDA("NIU");
		Cpe_Detalle.setCANTIDAD(1);
		Cpe_Detalle.setPRECIO(625.00);
		Cpe_Detalle.setIMPORTE(625.00);// PRECIO X CANTIDAD
		Cpe_Detalle.setPRECIO_TIPO_CODIGO("01");
		Cpe_Detalle.setIGV(112.50);
		Cpe_Detalle.setISC(0);
		Cpe_Detalle.setCOD_TIPO_OPERACION("10");
		Cpe_Detalle.setCODIGO("0001");
		Cpe_Detalle.setDESCRIPCION("PRUEBA");
		Cpe_Detalle.setPRECIO_SIN_IMPUESTO(625.00);
		Cpe_Detalle.setCOD_SUNAT("");
		lstCpe_Detalle.add(Cpe_Detalle);

		// String NombreCPE = "20100066603-03-BB11-750";//"20100066603-03-BB11-750.ZIP";
		/*
		 * String[] xx = CPESunat(3, ruc, UsuSol, PassSol, PassFirma, Cpe,
		 * lstCpe_Detalle);
		 * System.out.println("\n\n=============RESPUESTA SUNAT============");
		 * System.out.println("flg_rta: " + xx[0] + " \ncod_sunat: " + xx[1] +
		 * " \nmsj_sunat: " + xx[2] + " \nhash_cdr: " + xx[3] + " \nhash_cpe: " +
		 * xx[4]);
		 */
	}

	// COPY GENERARDOCUMENTO
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String[] generaDocumentoPrueba(_DocumentoCpe doc) throws Exception {
		String[] rpta_sunat = null;
		String ruc = "";
		String UsuSol = "";
		String PassSol = "";
		String PassFirma = "";
		try {
			ruc = doc.getRuc_empresaEmisora();
			UsuSol = doc.getUserSol_empresaEmisora();
			PassSol = doc.getPassSol_empresaEmisora();
			PassFirma = doc.getPassFirma_empresaEmisora();

			_CpeBean Cpe = new _CpeBean();
			
			Cpe.setTIPO_DETRACCION(doc.getTipo_detraccion());
			Cpe.setPORCENTAJE_DETRACCION(doc.getPorcentaje_detraccion());
			Cpe.setTIPO_OPERACION(doc.getTipo_operacion()); // Tipo Factura : 0101 Venta Interna | 0104 Venta
															// Interna-Anticipos
			Cpe.setTOTAL_GRAVADAS(Math.round(doc.getTotal_gravadas() * 100.0) / 100.0);
			Cpe.setTOTAL_INAFECTA(Math.round(doc.getTotal_inafecta() * 100.0) / 100.0);
			Cpe.setTOTAL_EXONERADAS(Math.round(doc.getTotal_exoneradas() * 100.0) / 100.0);
			Cpe.setTOTAL_EXPORTACION(Math.round(doc.getTotal_exportacion() * 100.0) / 100.0);
			Cpe.setTOTAL_GRATUITAS(Math.round(doc.getTotal_gratuitas() * 100.0) / 100.0);
			Cpe.setPOR_PERCEPCION(doc.getTotal_percepcion() / 100);
			Cpe.setPOR_PERCEPCION(Math.round(Cpe.getPOR_PERCEPCION() * 100.0) / 100.0);
			Cpe.setTOTAL_PERCEPCIONES(Math.round(doc.getValor_percepcion() * 100.0) / 100.0);

			Cpe.setTOTAL_RETENCIONES(Math.round(doc.getTotal_retenciones() * 100.0) / 100.0);
			Cpe.setTOTAL_DETRACCIONES(Math.round(doc.getTotal_detracciones() * 100.0) / 100.0);
			Cpe.setTOTAL_BONIFICACIONES(Math.round(doc.getTotal_bonificaciones() * 100.0) / 100.0);
			Cpe.setDESCUENTO_GLOBAL(Math.round(doc.getDescuento_global() * 100.0) / 100.0);
			Cpe.setPOR_DESCUENTO_GLOBAL((doc.getDescuento_global() * 100 / doc.getSub_total()) / 100);
			Cpe.setPOR_DESCUENTO_GLOBAL(Math.round(Cpe.getPOR_DESCUENTO_GLOBAL() * 100.0) / 100.0);
			Cpe.setTOTAL_DESCUENTO(Math.round(doc.getTotal_descuento() * 100.0) / 100.0);
			Cpe.setSUB_TOTAL(Math.round(doc.getSub_total() * 100.0) / 100.0);
			Cpe.setPOR_IGV(Math.round(doc.getPor_igv() * 100.0) / 100.0);// UBL2.1
			Cpe.setTOTAL_IGV(Math.round(doc.getTotal_igv() * 100.0) / 100.0);
			Cpe.setTOTAL_ISC(Math.round(doc.getTotal_isc() * 100.0) / 100.0);
			Cpe.setTOTAL_OTR_IMP(Math.round(doc.getTotal_otr_imp() * 100.0) / 100.0);
			Cpe.setTOTAL(Math.round(doc.getTotal() * 100.0) / 100.0);
			/// converitr a letras
			Numero_Letras num_to_letras = new Numero_Letras();
			String total_letritas = num_to_letras.Convertir(Double.toString(doc.getTotal()), true, doc.getCod_moneda());
			Cpe.setTOTAL_LETRAS(total_letritas);

			// campos vacios
			Cpe.setNRO_GUIA_REMISION(""); // ""
			Cpe.setCOD_GUIA_REMISION("");// ""
			Cpe.setNRO_OTR_COMPROBANTE("");// ""
			Cpe.setCOD_OTR_COMPROBANTE("");// ""

			Cpe.setNRO_COMPROBANTE(doc.getSerie_comprobante() + "-" + doc.getNro_comprobante());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String fecha_documento = dateFormat.format(new Date(doc.getFecha_documento().getTime()));
			Cpe.setFECHA_DOCUMENTO(fecha_documento);
			String fecha_vto = dateFormat.format(new Date(doc.getFecha_vto().getTime()));
			Cpe.setFECHA_VTO(fecha_vto);
			Cpe.setCOD_TIPO_DOCUMENTO(doc.getCod_tipo_documento());
			Cpe.setDirDocumentoEmpresaEmisora(doc.getDirDocumentoEmpresaEmisora());

////        //=================nota credito================
			if (doc.getCod_tipo_documento().equals(Constantes.tipoDocNotaCredito)) {
				Cpe.setTIPO_COMPROBANTE_MODIFICA(doc.getTipo_comprobante_modifica());
				Cpe.setNRO_DOCUMENTO_MODIFICA(doc.getNro_documento_modifica());
				Cpe.setCOD_TIPO_MOTIVO(doc.getCod_tipo_motivo());
				Cpe.setDESCRIPCION_MOTIVO(doc.getDescripcion_motivo());
			}
			// =================nota credito================
////        //=================nota debito================
			if (doc.getCod_tipo_documento().equals(Constantes.tipoDocNotaDebito)) {
				Cpe.setTIPO_COMPROBANTE_MODIFICA(doc.getTipo_comprobante_modifica());
				Cpe.setNRO_DOCUMENTO_MODIFICA(doc.getNro_documento_modifica());
				Cpe.setCOD_TIPO_MOTIVO(doc.getCod_tipo_motivo());
				Cpe.setDESCRIPCION_MOTIVO(doc.getDescripcion_motivo());
			}
			// =================nota debito================
			Cpe.setCOD_MONEDA(doc.getCod_moneda());

			Cpe.setNRO_DOCUMENTO_CLIENTE(doc.getNro_documento_cliente());
			Cpe.setRAZON_SOCIAL_CLIENTE(doc.getRazon_social_cliente());
			Cpe.setTIPO_DOCUMENTO_CLIENTE(doc.getTipo_documento_cliente());// 1=DNI, 6=RUC
			// ================================
			Cpe.setCOD_UBIGEO_CLIENTE(doc.getCod_ubigeo_cliente());// NUEVO UBL2.1
			Cpe.setDEPARTAMENTO_CLIENTE(doc.getDepartamento_cliente());// NUEVO UBL2.1
			Cpe.setPROVINCIA_CLIENTE(doc.getProvincia_cliente());// NUEVO UBL2.1
			Cpe.setDISTRITO_CLIENTE(doc.getDistrito_cliente());// NUEVO UBL2.1
			// ===============================

			Cpe.setCIUDAD_CLIENTE(doc.getCiudad_cliente());
			Cpe.setCOD_PAIS_CLIENTE("PE");/// peru: 9589

			Cpe.setNRO_DOCUMENTO_EMPRESA(doc.getNro_documento_empresa());
			Cpe.setTIPO_DOCUMENTO_EMPRESA(doc.getTipo_documento_empresa());
			Cpe.setNOMBRE_COMERCIAL_EMPRESA(doc.getNombre_comercial_empresa());

			// crear nuevo campo en company
			Cpe.setCODIGO_UBIGEO_EMPRESA(doc.getCodigo_ubigeo_empresa());
			Cpe.setDIRECCION_EMPRESA(doc.getDireccion_empresa());

			// tabla ubigeo
			Cpe.setDEPARTAMENTO_EMPRESA(doc.getDepartamento_empresa());
			Cpe.setPROVINCIA_EMPRESA(doc.getProvincia_empresa());
			Cpe.setDISTRITO_EMPRESA(doc.getDistrito_empresa());
			/////////
			Cpe.setCODIGO_PAIS_EMPRESA("PE"); // observacion

			Cpe.setRAZON_SOCIAL_EMPRESA(doc.getRazon_social_empresa());

			Cpe.setUSUARIO_SOL_EMPRESA(doc.getUserSol_empresaEmisora());
			Cpe.setPASS_SOL_EMPRESA(doc.getPassSol_empresaEmisora());
			Cpe.setTIPO_PROCESO(doc.getAmbiente_operacion());// 1=PRODUCCION,2=HOMOLOGACION,3=PRUEBA
			Cpe.setIfacturacionPse(doc.getIfacturacionPse());// 1=PSE,0=PROPIO

			Cpe.setDirDocumentoEmpresaEmisora(doc.getDirDocumentoEmpresaEmisora());
			Cpe.setCOD_TIPO_PAGO(doc.getCod_forma_pago());
			Cpe.setLSCUOTAS(doc.getLscuotas());
			switch(Cpe.getCOD_TIPO_DOCUMENTO()) {
			case Constantes.tipoDocFactura:
				if(Cpe.getCOD_TIPO_PAGO().equals(Constantes.codPagoCredito)) {
					for(Cuota c:Cpe.getLSCUOTAS()) {
						c.setFecha_cuota_str(c.getFecha_cuota().toString().substring(0, 10));
						c.setMonto_pago(Math.round(c.getMonto_pago() * 100.0) / 100.0);
					}
				}
				break;
			case Constantes.tipoDocNotaCredito:
				if(Cpe.getTIPO_COMPROBANTE_MODIFICA().equals(Constantes.tipoDocFactura)) {
					if(Cpe.getCOD_TIPO_PAGO().equals(Constantes.codPagoCredito)) {
						for(Cuota c:Cpe.getLSCUOTAS()) {
							c.setFecha_cuota_str(c.getFecha_cuota().toString().substring(0, 10));
							c.setMonto_pago(Math.round(c.getMonto_pago() * 100.0) / 100.0);
						}
					}
					
				}
				break;
				
			}


			if (Cpe.getTIPO_OPERACION().equals("0105")) {
				Cpe.setDIRECCION_ITINERANTE(doc.getDireccion_itinerante());
				Cpe.setPROVINCIA_ITINERANTE(doc.getProvincia_itinerante());
				Cpe.setDEPARTAMENTO_ITINERANTE(doc.getDepartamento_itinerante());
				Cpe.setUBIGEO_ITINERANTE(doc.getUbigeo_itinerante());
				Cpe.setDISTRITO_ITINERANTE(doc.getDistrito_itinerante());

			}
			if (Cpe.getTIPO_OPERACION().equals("0104")) {
				Cpe.setFLG_REGU_ANTICIPO(1);
				Cpe.setNRO_COMPROBANTE_REF_ANT(doc.getNro_comprobante_ref_ant());
				Cpe.setTIPO_DOCUMENTO_EMP_REGU_ANT(doc.getTipo_documento_emp_regu_ant());
				Cpe.setNRO_DOCUMENTO_EMP_REGU_ANT(doc.getNro_documento_emp_regu_ant());
				Cpe.setCOD_MONEDA_ANTICIPO(doc.getCod_moneda_anticipo());
				Cpe.setMONTO_REGU_ANTICIPO(doc.getMonto_regu_anticipo());

			}

			List<_DocumentoCpe_DetalleBean> lsdoc_Detalle = null;
			lsdoc_Detalle = doc.getDetalle();
			List<_Cpe_DetalleBean> lstCpe_Detalle = null;
			lstCpe_Detalle = new ArrayList<_Cpe_DetalleBean>();

			// solo debe tener 1 detalle?
			Integer item = 0;
			for (_DocumentoCpe_DetalleBean detalle : lsdoc_Detalle) {
				Cpe_Detalle = new _Cpe_DetalleBean();
				// Cpe_Detalle.setITEM(detalle.getItem());
				item++;
				Cpe_Detalle.setITEM(item);
				Cpe_Detalle.setUNIDAD_MEDIDA(detalle.getUnidad_medida());
				Cpe_Detalle.setCANTIDAD(detalle.getCantidad());
				Cpe_Detalle.setPRECIO(Math.round(detalle.getPrecio() * 100.0) / 100.0);
				Cpe_Detalle.setIMPORTE(Math.round(detalle.getImporte() * 100.0) / 100.0);// PRECIO X CANTIDAD
				Cpe_Detalle.setPRECIO_TIPO_CODIGO(detalle.getPrecio_tipo_codigo());
				Cpe_Detalle.setIGV(Math.round(detalle.getIgv() * 100.0) / 100.0);
				Cpe_Detalle.setISC(Math.round(detalle.getIsc() * 100.0) / 100.0);
				Cpe_Detalle.setCOD_TIPO_OPERACION(detalle.getCod_tipo_operacion());
				Cpe_Detalle.setCODIGO(detalle.getCodigo());
				Cpe_Detalle.setDESCRIPCION(detalle.getDescripcion());
				Cpe_Detalle.setPRECIO_SIN_IMPUESTO(Math.round(detalle.getPrecio_sin_impuesto() * 100.0) / 100.0);
				Cpe_Detalle.setCOD_SUNAT(detalle.getCod_sunat());
				lstCpe_Detalle.add(Cpe_Detalle);
			}
			String[] xx = CPESunat(Cpe.getTIPO_PROCESO(), ruc, UsuSol, PassSol, PassFirma, Cpe, lstCpe_Detalle);
			System.out.println("\n\n=============RESPUESTA SUNAT============");
			System.out.println("flg_rta: " + xx[0] + " \ncod_sunat: " + xx[1] + " \nmsj_sunat: " + xx[2]
					+ " \nhash_cdr: " + xx[3] + " \nhash_cpe: " + xx[4]);
			// rpta_sunat=xx[0];
			rpta_sunat = xx;

		} catch (Exception e) {
			System.err.println(
					"ERROR: " + this.getClass().getSimpleName() + "/generaDocumentoPrueba => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/generaDocumentoPrueba" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			Auditoria_Sunat auditoria = new Auditoria_Sunat();
			auditoria.setMensaje_respuesta_sunat(e.getMessage() + "Linea " + e.getStackTrace()[0].getLineNumber());
			auditoria.setId_documento(doc.getId_documento());
			auditoriaSunatService.insertaAuditoria_Sunat(auditoria);

			throw e;
		}
		return rpta_sunat;

		// return rpta_sunat;
		// return null;
	}

	// FACTURA - BOLETAS - ND - NC
	////////////////
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String generaDocumento(_DocumentoCpe doc) throws FileNotFoundException, NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, ParserConfigurationException, SAXException, MarshalException,
			KeyStoreException, IOException, CertificateException, Exception {

		String rpta_sunat = null;

		try {

			// ruc de empresa que va a generar cpe
			String ruc = doc.getRuc_empresaEmisora();
			String UsuSol = doc.getUserSol_empresaEmisora();
			String PassSol = doc.getPassSol_empresaEmisora();
			String PassFirma = doc.getPassFirma_empresaEmisora();
			// String RutaCPE =
			// "20100066603-03-BB11-750";//"F:\\cert\\20100066603-03-BB11-750.ZIP";
			// String RutaRta = "F:\\cert\\";
			// String NombreArchvoRta = "";//"R-20100066603-03-BB11-750";
			_CpeBean Cpe = new _CpeBean();
			Cpe.setTIPO_OPERACION(doc.getTipo_operacion()); // Tipo Factura : 0101 Venta Interna | 0104 Venta
															// Interna-Anticipos
			Cpe.setTOTAL_GRAVADAS(doc.getTotal_gravadas());
			Cpe.setTOTAL_INAFECTA(doc.getTotal_inafecta());
			Cpe.setTOTAL_EXONERADAS(doc.getTotal_exoneradas());
			Cpe.setTOTAL_GRATUITAS(doc.getTotal_gratuitas());
			Cpe.setTOTAL_PERCEPCIONES(doc.getTotal_percepciones());
			Cpe.setTOTAL_RETENCIONES(doc.getTotal_retenciones());
			Cpe.setTOTAL_DETRACCIONES(doc.getTotal_detracciones());
			Cpe.setTOTAL_BONIFICACIONES(doc.getTotal_bonificaciones());
			Cpe.setTOTAL_DESCUENTO(doc.getTotal_descuento());
			Cpe.setSUB_TOTAL(doc.getSub_total());
			Cpe.setPOR_IGV(doc.getPor_igv());// UBL2.1
			Cpe.setTOTAL_IGV(doc.getTotal_igv());
			Cpe.setTOTAL_ISC(doc.getTotal_isc());
			Cpe.setTOTAL_OTR_IMP(doc.getTotal_otr_imp());
			Cpe.setTOTAL(doc.getTotal());
			Cpe.setTOTAL_LETRAS(doc.getTotal_letras());
			Cpe.setNRO_GUIA_REMISION(doc.getNro_guia_remision()); // ""
			Cpe.setCOD_GUIA_REMISION(doc.getCod_guia_remision());// ""
			Cpe.setNRO_OTR_COMPROBANTE(doc.getNro_otr_comprobante());// ""
			Cpe.setCOD_OTR_COMPROBANTE(doc.getCod_otr_comprobante());// ""
			Cpe.setNRO_COMPROBANTE(doc.getSerie_comprobante() + "-" + doc.getNro_comprobante());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String fecha_documento = dateFormat.format(new Date(doc.getFecha_documento().getTime()));
			Cpe.setFECHA_DOCUMENTO(fecha_documento);
			String fecha_vto = dateFormat.format(new Date(doc.getFecha_vto().getTime()));
			Cpe.setFECHA_VTO(fecha_vto);
			Cpe.setCOD_TIPO_DOCUMENTO(doc.getCod_tipo_documento());// 01=factura, 03=boleta, 07=nota credito, 08=nota
																	// debito
			Cpe.setCOD_MONEDA(doc.getCod_moneda());

			Cpe.setNRO_DOCUMENTO_CLIENTE(doc.getNro_documento_cliente());
			Cpe.setRAZON_SOCIAL_CLIENTE(doc.getRazon_social_cliente());
			Cpe.setTIPO_DOCUMENTO_CLIENTE(doc.getTipo_documento_cliente());// 1=DNI, 6=RUC
			// ================================
			Cpe.setCOD_UBIGEO_CLIENTE(doc.getCod_ubigeo_cliente());// NUEVO UBL2.1
			Cpe.setDEPARTAMENTO_CLIENTE(doc.getDepartamento_cliente());// NUEVO UBL2.1
			Cpe.setPROVINCIA_CLIENTE(doc.getProvincia_cliente());// NUEVO UBL2.1
			Cpe.setDISTRITO_CLIENTE(doc.getDistrito_cliente());// NUEVO UBL2.1
			// ===============================
			Cpe.setCIUDAD_CLIENTE(doc.getCiudad_cliente());

			Cpe.setCOD_PAIS_CLIENTE(doc.getCod_pais_cliente());
			Cpe.setNRO_DOCUMENTO_EMPRESA(doc.getNro_documento_empresa());
			Cpe.setTIPO_DOCUMENTO_EMPRESA(doc.getTipo_documento_empresa());
			Cpe.setNOMBRE_COMERCIAL_EMPRESA(doc.getNombre_comercial_empresa());
			Cpe.setCODIGO_UBIGEO_EMPRESA(doc.getCodigo_ubigeo_empresa());
			Cpe.setDIRECCION_EMPRESA(doc.getDireccion_empresa());
			Cpe.setDEPARTAMENTO_EMPRESA(doc.getDepartamento_empresa());
			Cpe.setPROVINCIA_EMPRESA(doc.getProvincia_empresa());
			Cpe.setDISTRITO_EMPRESA(doc.getDistrito_empresa());
			Cpe.setCODIGO_PAIS_EMPRESA(doc.getCodigo_pais_empresa());
			Cpe.setRAZON_SOCIAL_EMPRESA(doc.getRazon_social_empresa());
			Cpe.setUSUARIO_SOL_EMPRESA(doc.getUserSol_empresaEmisora());
			Cpe.setPASS_SOL_EMPRESA(doc.getPassSol_empresaEmisora());
			// Cpe.setTIPO_PROCESO(doc.getTipo_proceso());//1=PRODUCCION,2=HOMOLOGACION,3=PRUEBA
			Cpe.setTIPO_PROCESO(doc.getAmbiente_operacion());
			List<_DocumentoCpe_DetalleBean> lsdoc_Detalle = null;
			lsdoc_Detalle = doc.getDetalle();
			List<_Cpe_DetalleBean> lstCpe_Detalle = null;
			lstCpe_Detalle = new ArrayList<_Cpe_DetalleBean>();

			// solo debe tener 1 detalle?
			for (_DocumentoCpe_DetalleBean detalle : lsdoc_Detalle) {
				Cpe_Detalle = new _Cpe_DetalleBean();
				Cpe_Detalle.setITEM(detalle.getItem());
				Cpe_Detalle.setUNIDAD_MEDIDA(detalle.getUnidad_medida());
				Cpe_Detalle.setCANTIDAD(detalle.getCantidad());
				Cpe_Detalle.setPRECIO(detalle.getPrecio());
				Cpe_Detalle.setIMPORTE(detalle.getImporte());// PRECIO X CANTIDAD
				Cpe_Detalle.setPRECIO_TIPO_CODIGO(detalle.getPrecio_tipo_codigo());
				Cpe_Detalle.setIGV(detalle.getIgv());
				Cpe_Detalle.setISC(detalle.getIsc());
				Cpe_Detalle.setCOD_TIPO_OPERACION(detalle.getCod_tipo_operacion());
				Cpe_Detalle.setCODIGO(detalle.getCodigo());
				Cpe_Detalle.setDESCRIPCION(detalle.getDescripcion());
				Cpe_Detalle.setPRECIO_SIN_IMPUESTO(detalle.getPrecio_sin_impuesto());
				Cpe_Detalle.setCOD_SUNAT(detalle.getCod_sunat());
				lstCpe_Detalle.add(Cpe_Detalle);
			}
			String[] xx = CPESunat(3, ruc, UsuSol, PassSol, PassFirma, Cpe, lstCpe_Detalle);
			System.out.println("\n\n=============RESPUESTA SUNAT============");
			System.out.println("flg_rta: " + xx[0] + " \ncod_sunat: " + xx[1] + " \nmsj_sunat: " + xx[2]
					+ " \nhash_cdr: " + xx[3] + " \nhash_cpe: " + xx[4]);
			rpta_sunat = xx[1];
		} catch (Exception e) {
			System.err.println("ERROR: " + this.getClass().getSimpleName() + "/generaDocumento => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/generaDocumento" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return rpta_sunat;

	}
	/////////////
	// FIN - FACTURA

	// CPE RETENCION - PERCEPCION
	////////////////
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public _Cpe_RetencionPercepcionBean generaDocumentoRTPC(_Retencion doc) throws FileNotFoundException,
			NoSuchAlgorithmException, InvalidAlgorithmParameterException, ParserConfigurationException, SAXException,
			MarshalException, KeyStoreException, IOException, CertificateException, Exception {
		_Cpe_RetencionPercepcionBean Cpertpc = new _Cpe_RetencionPercepcionBean();
		try {

			// ruc de empresa que va a generar cpe
			String ruc = doc.getRuc_empresaEmisora();
			String UsuSol = doc.getUserSol_empresaEmisora();
			String PassSol = doc.getPassSol_empresaEmisora();
			String PassFirma = doc.getPassFirma_empresaEmisora();
			// String RutaCPE =
			// "20100066603-03-BB11-750";//"F:\\cert\\20100066603-03-BB11-750.ZIP";
			// String RutaRta = "F:\\cert\\";
			// String NombreArchvoRta = "";//"R-20100066603-03-BB11-750";

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String fecha_documento = dateFormat.format(new Date(doc.getFecha_documento().getTime()));
			if (doc.getFecha_emision_str() == null) {

				String fecha_emision = dateFormat.format(new Date(doc.getFecha_emision().getTime()));
				Cpertpc.setFECHA_REGISTRO(fecha_emision);
			} else {
				Cpertpc.setFECHA_REGISTRO(doc.getFecha_emision_str());

			}

			Cpertpc.setCOD_TIPO_DOCUMENTO(doc.getCod_tipo_documento());
			Cpertpc.setNRO_COMPROBANTE(doc.getNro_comprobante());
			Cpertpc.setFECHA_DOCUMENTO(fecha_documento);
			Cpertpc.setTIPO_DOCUMENTO_EMPRESA(doc.getTipo_documento_empresa());
			Cpertpc.setNRO_DOCUMENTO_EMPRESA(doc.getNro_documento_empresa());
			Cpertpc.setNOMBRE_COMERCIAL_EMPRESA(doc.getNombre_comercial_empresa());
			Cpertpc.setDIRECCION_EMPRESA(doc.getDireccion_empresa());
			Cpertpc.setCOD_UBIGEO_EMPRESA(doc.getCod_ubigeo_empresa());
			Cpertpc.setZONA_URBANIZACION_EMPRESA("");
			Cpertpc.setDEPARTAMENTO_EMPRESA(doc.getDepartamento_empresa());
			Cpertpc.setPROVINCIA_EMPRESA(doc.getProvincia_empresa());
			Cpertpc.setDISTRITO_EMPRESA(doc.getDistrito_empresa());
			Cpertpc.setCOD_PAIS_EMPRESA("PE");
			Cpertpc.setRAZON_SOCIAL_EMPRESA(doc.getRazon_social_empresa());
			List<_DocumentoCpe> lsdoc_Detalle = null;
			lsdoc_Detalle = new ArrayList<_DocumentoCpe>();
			lsdoc_Detalle = doc.getLsDocumento();
			////
			Cpertpc.setID_EMPRESA(doc.getId_empresa());

//			Cpertpc.setTOTAL_RETENCION(doc.getTotal_retencion());
//			Cpertpc.setNETO_RETENCION(doc.getNeto_retencion());

			Cpertpc.setID_CLIENTE_CPE(doc.getId_cliente());
//			Cpertpc.setNOTA(doc.getNota());
			Cpertpc.setIfacturacionPse(doc.getIfacturacionPse());// 1=PSE,0=PROPIO

			List<_Cpe_RetencionPercepcion_DetalleBean> lstCpe_Detalle = null;
			lstCpe_Detalle = new ArrayList<_Cpe_RetencionPercepcion_DetalleBean>();
			if (doc.getCod_tipo_documento().equals(Constantes.tipoDocPercepcion)) {
				Cpertpc.setTIPO_DOCUMENTO_CLIENTE(doc.getTipo_documento_cliente());
				Cpertpc.setNRO_DOCUMENTO_CLIENTE(doc.getNro_documento_cliente());
				Cpertpc.setNOMBRE_COMERCIAL_CLIENTE(doc.getNombre_comercial_cliente());
				Cpertpc.setDIRECCION_CLIENTE(doc.getDireccion_cliente());
				Cpertpc.setCOD_UBIGEO_CLIENTE(doc.getCod_ubigeo_cliente());
				Cpertpc.setZONA_URBANIZACION_CLIENTE(doc.getZona_urbanizacion_cliente());
				Cpertpc.setCIUDAD_CLIENTE(doc.getCiudad_cliente());
				Cpertpc.setDEPARTAMENTO_CLIENTE(doc.getDepartamento_cliente());
				Cpertpc.setPROVINCIA_CLIENTE(doc.getProvincia_cliente());
				Cpertpc.setDISTRITO_CLIENTE(doc.getDistrito_cliente());
				Cpertpc.setPAIS_CLIENTE(doc.getPais_cliente());
				Cpertpc.setRAZON_SOCIAL_CLIENTE(doc.getRazon_social_cliente());

				Cpertpc.setTIPO_PERCEPCION(doc.getTipo_percepcion());// 01, 02 o 03 (catalogo 22)
				Cpertpc.setPORCENTAJE_PERCEPCION(doc.getPorcentaje_percepcion());
				Cpertpc.setNOTA("");
				Cpertpc.setMONEDA("PEN");
				doc.setTotal_percepcion(Math.round(doc.getTotal_percepcion() * 100.0) / 100.0);
				doc.setNeto_percepcion(Math.round(doc.getNeto_percepcion() * 100.0) / 100.0);
				Cpertpc.setTOTAL_PERCEPCION(doc.getTotal_percepcion());
				Cpertpc.setNETO_PERCEPCION(doc.getNeto_percepcion());

				if (doc.getLs_RetencionPercepcion() == null) {
					// este campo se llena unicamente cuando el documento ya se registro y se está
					// retornando sus campos de la base de datos

					for (_DocumentoCpe detalle : lsdoc_Detalle) {
						String fec_doc = dateFormat.format(new Date(detalle.getFecha_documento().getTime()));

						_Cpe_RetencionPercepcion_DetalleBean Cpe_rtcDetalle = new _Cpe_RetencionPercepcion_DetalleBean();
						Cpe_rtcDetalle.setCOD_TIPO_DOCUMENTO(detalle.getCod_tipo_documento());
						Cpe_rtcDetalle
								.setNRO_DOCUMENTO(detalle.getSerie_comprobante() + "-" + detalle.getNro_comprobante());
						Cpe_rtcDetalle.setFECHA_DOCUMENTO(fec_doc);
						Cpe_rtcDetalle.setCOD_MONEDA(detalle.getCod_moneda());
						Cpe_rtcDetalle.setMONTO_TOTAL(detalle.getImporte_cobro());

						Cpe_rtcDetalle.setNRO_DOC_COBRO(detalle.getNro_cobro());
						Cpe_rtcDetalle.setFECHA_COBRO(detalle.getFecha_cobro().substring(0, 10));
						Cpe_rtcDetalle.setMONEDA_PERCIBIDO(detalle.getMoneda_percibida());
						Cpe_rtcDetalle.setMONTO_PERCIBIDO(detalle.getImporte_percibido());
						Cpe_rtcDetalle.setFECHA_PERCEPCION(detalle.getFecha_percepcion().substring(0, 10));
						Cpe_rtcDetalle.setMONEDA_COBRO_NETO(detalle.getMoneda_cobro_neto());
						Cpe_rtcDetalle.setMONTO_COBRO_NETO(detalle.getImporte_neto_cobrado());

//					 Cpe_rtcDetalle.setNRO_DOC_PAGO(detalle.getNro_cobro());
//					 Cpe_rtcDetalle.setFECHA_PAGO(detalle.getFecha_cobro().substring(0, 10));
//					 Cpe_rtcDetalle.setMONEDA_RETENIDA(detalle.getMoneda_percibida());
//					 Cpe_rtcDetalle.setMONTO_RETENIDO(detalle.getImporte_percibido());
//					 Cpe_rtcDetalle.setFECHA_RETENIDA(detalle.getFecha_percepcion().substring(0, 10));
//					 Cpe_rtcDetalle.setMONEDA_PAGO_NETO(detalle.getMoneda_cobro_neto());
//					 Cpe_rtcDetalle.setMONTO_PAGO_NETO(detalle.getImporte_neto_cobrado());

						lstCpe_Detalle.add(Cpe_rtcDetalle);
					}
				} else {

					lstCpe_Detalle = doc.getLs_RetencionPercepcion();
				}
			}
			if (doc.getCod_tipo_documento().equals(Constantes.tipoDocRetencion)) {

				Cpertpc.setTIPO_DOCUMENTO_PROVEEDOR(doc.getTipo_documento_proveedor());
				Cpertpc.setNRO_DOCUMENTO_PROVEEDOR(doc.getNro_documento_proveedor());
				Cpertpc.setNOMBRE_COMERCIAL_PROVEEDOR(doc.getNombre_comercial_proveedor());
				Cpertpc.setDIRECCION_PROVEEDOR(doc.getDireccion_proveedor());
				Cpertpc.setCOD_UBIGEO_PROVEEDOR(doc.getCod_ubigeo_proveedor());

				Cpertpc.setZONA_URBANIZACION_PROVEEDOR(doc.getZona_urbanizacion_proveedor());
				Cpertpc.setCIUDAD_PROVEEDOR(doc.getCiudad_proveedor());

				Cpertpc.setDEPARTAMENTO_PROVEEDOR(doc.getDepartamento_proveedor());
				Cpertpc.setPROVINCIA_PROVEEDOR(doc.getProvincia_proveedor());
				Cpertpc.setDISTRITO_PROVEEDOR(doc.getDistrito_proveedor());

				Cpertpc.setPAIS_PROVEEDOR(doc.getPais_proveedor());

				Cpertpc.setRAZON_SOCIAL_PROVEEDOR(doc.getRazon_social_proveedor());

				Cpertpc.setTIPO_RETENCION(doc.getTipo_retencion());// 01 o 03 (catalogo 23)
				Cpertpc.setPORCENTAJE_RETENCION(doc.getPorcentaje_retencion());

				Cpertpc.setNOTA("");
				Cpertpc.setMONEDA("PEN");
				doc.setTotal_retencion(Math.round(doc.getTotal_retencion() * 100.0) / 100.0);
				doc.setNeto_retencion(Math.round(doc.getNeto_retencion() * 100.0) / 100.0);
				Cpertpc.setTOTAL_RETENCION(doc.getTotal_retencion());
				Cpertpc.setNETO_RETENCION(doc.getNeto_retencion());
				if (doc.getLs_RetencionPercepcion() == null) {
					// este campo se llena unicamente cuando el documento ya se registro y se está
					// retornando sus campos de la base de datos

					// solo debe tener 1 detalle?
					for (_DocumentoCpe detalle : lsdoc_Detalle) {
						String fec_doc = dateFormat.format(new Date(detalle.getFecha_documento().getTime()));

						_Cpe_RetencionPercepcion_DetalleBean Cpe_rtcDetalle = new _Cpe_RetencionPercepcion_DetalleBean();
						Cpe_rtcDetalle.setCOD_TIPO_DOCUMENTO(detalle.getCod_tipo_documento());
						Cpe_rtcDetalle
								.setNRO_DOCUMENTO(detalle.getSerie_comprobante() + "-" + detalle.getNro_comprobante());
						Cpe_rtcDetalle.setFECHA_DOCUMENTO(fec_doc);
						Cpe_rtcDetalle.setCOD_MONEDA(detalle.getCod_moneda());
						Cpe_rtcDetalle.setMONTO_TOTAL(detalle.getImporte_pago());

						Cpe_rtcDetalle.setNRO_DOC_PAGO(detalle.getNro_pago());//
						Cpe_rtcDetalle.setFECHA_PAGO(detalle.getFecha_pago().substring(0, 10));//
						Cpe_rtcDetalle.setMONEDA_RETENIDA(detalle.getMoneda_retenida());///
						Cpe_rtcDetalle.setMONTO_RETENIDO(detalle.getImporte_retenido());
						Cpe_rtcDetalle.setFECHA_RETENIDA(detalle.getFecha_retencion().substring(0, 10));
						Cpe_rtcDetalle.setMONEDA_PAGO_NETO(detalle.getMoneda_pago_neto());
						Cpe_rtcDetalle.setMONTO_PAGO_NETO(detalle.getImporte_neto_pagado());

						lstCpe_Detalle.add(Cpe_rtcDetalle);
					}
				} else {

					lstCpe_Detalle = doc.getLs_RetencionPercepcion();
				}
			}

//			Cpertpc.setCOD_RESPUESTA_SUNAT(doc.getCod_respuesta_sunat());
//			Cpertpc.setDESCRIPCION_SUNAT(doc.getDescripcion_sunat());
//			Cpertpc.setHASH_CDR(doc.getHash_cdr());//
//			Cpertpc.setHASH_CPE(doc.getHash_cpe());//
			Cpertpc.setTIPO(doc.getTipo());
			if (doc.getCorreo() != null) {
				Cpertpc.setCORREO(doc.getCorreo());
			} else {
				Cpertpc.setCORREO("");
			}
			Cpertpc.setDirDocumentoEmpresaEmisora(doc.getDirDocumentoEmpresaEmisora());
			Cpertpc.setTIPO_PROCESO(doc.getAmbiente_operacion());// 1=PRODUCCION,2=HOMOLOGACION,3=PRUEBA

			Cpertpc.setLsdetalle(lstCpe_Detalle);

			String[] xx = CPESunat_RTPC(Cpertpc.getTIPO_PROCESO(), ruc, UsuSol, PassSol, PassFirma, Cpertpc,
					lstCpe_Detalle);
			System.out.println("\n\n=============RESPUESTA SUNAT============");
			System.out.println("flg_rta: " + xx[0] + " \ncod_sunat: " + xx[1] + " \nmsj_sunat: " + xx[2]
					+ " \nhash_cdr: " + xx[3] + " \nhash_cpe: " + xx[4]);
			Cpertpc.setRpta_sunat(xx);
		} catch (Exception e) {
			System.err
					.println("ERROR: " + this.getClass().getSimpleName() + "/generaDocumentoRTPC => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/generaDocumentoRTPC" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return Cpertpc;

	}
	///////////// ---------------------------------------------------------------------------------------------------------------------------------------
	// FIN - FACTURA

	// DAR BAJA FACTURAS
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String darBajaDocumento(_DocumentoCpe doc) throws Exception {

		try {
			_Cpe_BajaBean Cpe_Baja = new _Cpe_BajaBean();

			String rpt_sunat = "";

			String ruc = doc.getRuc_empresaEmisora();
			String UsuSol = doc.getUserSol_empresaEmisora();
			String PassSol = doc.getPassSol_empresaEmisora();
			String PassFirma = doc.getPassFirma_empresaEmisora();

			// DIRECTORIO
			Cpe_Baja.setDirDocumentoEmpresaEmisora(doc.getDirDocumentoEmpresaEmisora());

			Cpe_Baja.setNRO_DOCUMENTO_EMPRESA(doc.getNro_documento_empresa());
			Cpe_Baja.setRAZON_SOCIAL(doc.getRazon_social_empresa());// *
			Cpe_Baja.setTIPO_DOCUMENTO(doc.getTipo_documento_empresa());// tipo doc empresa : ruc
			Cpe_Baja.setCODIGO(Constantes.comunicaciónBajaSunat); // Comunicación de Baja(SUNAT) : RA
			String[] parts = doc.getFecha_baja().split("-");

			Cpe_Baja.setSERIE(parts[0] + parts[1] + parts[2]); // año +mes+dia de baja ?
			Cpe_Baja.setSECUENCIA(doc.getSecuencia().toString()); // ??
			Cpe_Baja.setFECHA_REFERENCIA(doc.getFecha_referencia()); // FECHA DOC REF?
			Cpe_Baja.setFECHA_BAJA(doc.getFecha_baja());
			Cpe_Baja.setIfacturacionPse(doc.getIfacturacionPse());
			List<_Cpe_Baja_DetalleBean> lstCpe_BajaDetalle = new ArrayList<_Cpe_Baja_DetalleBean>();
			// FACTURA ANULADA ITEM 1
			_Cpe_Baja_DetalleBean Cpe_Baja_Detalle = new _Cpe_Baja_DetalleBean();
			Cpe_Baja_Detalle.setITEM(1); // si es nc ó nd item =2 ?
											// o es que se pueden dar de baja a mas de 1 doc a la vez
			Cpe_Baja_Detalle.setTIPO_COMPROBANTE(doc.getCod_tipo_documento()); // FACTURA 01 | ND 08 | NC 07
			Cpe_Baja_Detalle.setSERIE(doc.getSerie_comprobante());
			Cpe_Baja_Detalle.setNUMERO(doc.getNro_comprobante());
			Cpe_Baja_Detalle.setDESCRIPCION(doc.getDescripcion_motivo());
			lstCpe_BajaDetalle.add(Cpe_Baja_Detalle);
			Integer flg_tipo = doc.getAmbiente_operacion();

			String[] xx = CPEBaja(flg_tipo, ruc, UsuSol, PassSol, PassFirma, Cpe_Baja, lstCpe_BajaDetalle);
			rpt_sunat = xx[2] + '-' + xx[0] + "--" + xx[0] + "--" + xx[0];
			System.out.println("\n\n=============RESPUESTA DAR DE BAJA SUNAT============");
			System.out.println("flg_rta: " + xx[0] + " \ncod_sunat: " + xx[1] + " \nmsj_sunat: " + xx[2]
					+ " \nhash_cdr: " + xx[3] + " \nhash_cpe: " + xx[4]);

			if (flg_tipo != 3) {
				if (doc.getCod_tipo_documento().equals("01") || doc.getCod_tipo_documento().equals("07")
						|| doc.getCod_tipo_documento().equals("08")) {
// 	                rpt_sunat=xx[2];
					String nroTicket = xx[2];
					String nomCPE = ruc + "-" + Cpe_Baja.getCODIGO() + "-" + Cpe_Baja.getSERIE() + "-"
							+ Cpe_Baja.getSECUENCIA();

					String[] RtaTicket = CPESunatConsultaTicket(1, ruc, UsuSol, PassSol, nomCPE, nroTicket, Cpe_Baja);
					rpt_sunat = xx[2] + '-' + RtaTicket[2] + "--" + RtaTicket[0] + "--" + xx[0];
					System.out.println("\n\n=============RESPUESTA CONSULTA TICKET DE DAR DE BAJA SUNAT============");
					System.out.println("flg_rta: " + RtaTicket[0] + " \ncod_sunat: " + RtaTicket[1] + " \nmsj_sunat: "
							+ RtaTicket[2] + " \nhash_cdr: " + RtaTicket[3] + " \nhash_cpe: " + RtaTicket[4]);

				} else {
					rpt_sunat = xx[2] + '-' + xx[0] + "--" + xx[0] + "--" + xx[0];
				}
			}
			return rpt_sunat;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " darBajaDocumento. ERROR : " + e.getMessage()
					+ " Linea: " + e.getStackTrace()[0].getLineNumber());
			throw e;
		}
	}

	/////////////////
	// FIN DAR BAJA

	// REGISTRO GUIA
////////////////
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String[] registroGuia(_DocumentoCpe doc) throws FileNotFoundException, NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, ParserConfigurationException, SAXException, MarshalException,
			KeyStoreException, IOException, CertificateException, Exception {

		String[] rpta_sunat = null;
		try {

			// ruc de empresa que va a generar cpe
			String ruc = doc.getRuc_empresaEmisora();
			String UsuSol = doc.getUserSol_empresaEmisora();
			String PassSol = doc.getPassSol_empresaEmisora();
			String PassFirma = doc.getPassFirma_empresaEmisora();

			_CpeGuiaRemisionBean Cpe_guia = new _CpeGuiaRemisionBean();

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String fecha_documento = dateFormat.format(new Date(doc.getFecha_documento().getTime()));
			// , List<CpeGuiaRemisionDetalleBean> lstCpe_Detalle
			Cpe_guia.setNRO_COMPROBANTE(doc.getSerie_comprobante() + "-" + doc.getNro_comprobante());
			Cpe_guia.setFECHA_DOCUMENTO(fecha_documento);
			Cpe_guia.setCOD_TIPO_DOCUMENTO(doc.getCod_tipo_documento());
			if (doc.getNota_guia() == null) {
				Cpe_guia.setNOTA("");//

			} else {
				Cpe_guia.setNOTA(doc.getNota_guia());//
			}
			Cpe_guia.setITEM_ENVIO(1);
			Cpe_guia.setCOD_MOTIVO_TRASLADO(doc.getCod_motivo_traslado());
			if (doc.getDescripcion_motivo_traslado() == null) {
				Cpe_guia.setDESCRIPCION_MOTIVO_TRASLADO("");
			} else {
				Cpe_guia.setDESCRIPCION_MOTIVO_TRASLADO(doc.getDescripcion_motivo_traslado());

			}
			Cpe_guia.setCOD_UND_PESO_BRUTO("KGM");// kgm
			Cpe_guia.setPESO_BRUTO(doc.getPeso_bruto());
			Cpe_guia.setTOTAL_BULTOS(doc.getTotal_bultos());////
			Cpe_guia.setCOD_MODALIDAD_TRASLADO(doc.getCod_modalidad_traslado());
			Cpe_guia.setFECHA_INICIO(doc.getFecha_inicio_traslado().substring(0, 10));
			// publico
			if (doc.getNro_documento_transportista() == null) {
				Cpe_guia.setNRO_DOCUMENTO_TRANSPORTISTA("");
			} else {
				Cpe_guia.setNRO_DOCUMENTO_TRANSPORTISTA(doc.getNro_documento_transportista());

			}
			if (doc.getRazon_social_transportista() == null) {
				Cpe_guia.setRAZON_SOCIAL_TRANSPORTISTA("");
			} else {
				Cpe_guia.setRAZON_SOCIAL_TRANSPORTISTA(doc.getRazon_social_transportista());

			}
			if (doc.getTipo_documento_transportista() == null) {
				Cpe_guia.setTIPO_DOCUMENTO_TRANSPORTISTA("");
			} else {
				Cpe_guia.setTIPO_DOCUMENTO_TRANSPORTISTA(doc.getTipo_documento_transportista());

			}

			// privado
			Cpe_guia.setPLACA_VEHICULO(doc.getPlaca_vehiculo());
			Cpe_guia.setCOD_TIPO_DOC_CHOFER(doc.getCod_tipo_doc_chofer());
			Cpe_guia.setNRO_DOC_CHOFER(doc.getNro_doc_chofer());
			//
			Cpe_guia.setPLACA_CARRETA("");//
			Cpe_guia.setCOD_UBIGEO_DESTINO(doc.getCod_ubigeo_destino());
			Cpe_guia.setDIRECCION_DESTINO(doc.getDireccion_destino());
			Cpe_guia.setCOD_UBIGEO_ORIGEN(doc.getCod_ubigeo_origen());
			Cpe_guia.setDIRECCION_ORIGEN(doc.getDireccion_origen());
			Cpe_guia.setUSUARIO_SOL_EMPRESA(doc.getUserSol_empresaEmisora());
			Cpe_guia.setPASS_SOL_EMPRESA(doc.getPassSol_empresaEmisora());
			Cpe_guia.setTIPO_PROCESO(doc.getAmbiente_operacion());// 1=PRODUCCION,2=HOMOLOGACION,3=PRUEBA
			// anula guia
			Cpe_guia.setFLG_ANULADO(0);
			Cpe_guia.setDOC_REFERENCIA_ANU("");
			Cpe_guia.setCOD_TIPO_DOC_REFANU("");
			//
			Cpe_guia.setNRO_DOCUMENTO_EMPRESA(doc.getNro_documento_empresa());
			Cpe_guia.setNRO_DOCUMENTO_CLIENTE(doc.getNro_documento_cliente());
			Cpe_guia.setRAZON_SOCIAL_EMPRESA(doc.getRazon_social_empresa());
			Cpe_guia.setRAZON_SOCIAL_CLIENTE(doc.getRazon_social_cliente());
			Cpe_guia.setTIPO_DOCUMENTO_CLIENTE(doc.getTipo_documento_cliente());
			Cpe_guia.setTIPO_DOCUMENTO_EMPRESA(doc.getTipo_documento_empresa());
			Cpe_guia.setIfacturacionPse(doc.getIfacturacionPse());

			Cpe_guia.setNUMERACION_DAM(doc.getNumeracion_dam());
			Cpe_guia.setCOD_TIPO_DOC_PROVEEDOR(doc.getTipo_documento_proveedor());
			Cpe_guia.setNRO_DOC_PROVEEDOR(doc.getNro_documento_proveedor());
			Cpe_guia.setDESCRIPCION_PROVEEDOR(doc.getNombre_comercial_proveedor());
			Cpe_guia.setNRO_CONTENEDOR(doc.getNro_contenedor());

			List<_CpeGuiaRemisionDetalleBean> lsdoc_Detalle = new ArrayList<_CpeGuiaRemisionDetalleBean>();
//  			lsdoc_Detalle=doc.getDetalle_Guia();
			Integer i = 1;
			for (_CpeGuiaRemisionDetalleBean detalle_guia : doc.getDetalle_Guia()) {
				_CpeGuiaRemisionDetalleBean Cpe_Guia_Detalle = new _CpeGuiaRemisionDetalleBean();
				Cpe_Guia_Detalle.setITEM(i);
				Cpe_Guia_Detalle.setUNIDAD_MEDIDA(detalle_guia.getUNIDAD_MEDIDA());
				Cpe_Guia_Detalle.setCANTIDAD(detalle_guia.getCANTIDAD());
				Cpe_Guia_Detalle.setORDER_ITEM(Cpe_Guia_Detalle.getITEM());
				Cpe_Guia_Detalle.setCODIGO(detalle_guia.getCODIGO());
				Cpe_Guia_Detalle.setDESCRIPCION(detalle_guia.getDESCRIPCION());
				lsdoc_Detalle.add(Cpe_Guia_Detalle);
				i++;
			}
			String[] xx = CPEGuiaRemision(Cpe_guia.getTIPO_PROCESO(), ruc, UsuSol, PassSol, PassFirma, Cpe_guia,
					lsdoc_Detalle, doc.getDirDocumentoEmpresaEmisora());
			System.out.println("\n\n=============RESPUESTA SUNAT============");
			System.out.println("flg_rta: " + xx[0] + " \ncod_sunat: " + xx[1] + " \nmsj_sunat: " + xx[2]
					+ " \nhash_cdr: " + xx[3] + " \nhash_cpe: " + xx[4]);
			rpta_sunat = xx;
		} catch (Exception e) {
			System.err.println("ERROR: " + this.getClass().getSimpleName() + "/registroGuia => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/registroGuia" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return rpta_sunat;
	}
	///////////////
	// FIN REGISTRO GUIA

	// REEMPLAZAR GUIA
////////////////
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String[] reemplazarGuia(_DocumentoCpe doc) throws FileNotFoundException, NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, ParserConfigurationException, SAXException, MarshalException,
			KeyStoreException, IOException, CertificateException, Exception {

		String[] rpta_sunat = null;
		try {

			// ruc de empresa que va a generar cpe
			String ruc = doc.getRuc_empresaEmisora();
			String UsuSol = doc.getUserSol_empresaEmisora();
			String PassSol = doc.getPassSol_empresaEmisora();
			String PassFirma = doc.getPassFirma_empresaEmisora();

			_CpeGuiaRemisionBean Cpe_guia = new _CpeGuiaRemisionBean();

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String fecha_documento = dateFormat.format(new Date(doc.getFecha_documento().getTime()));
			// , List<CpeGuiaRemisionDetalleBean> lstCpe_Detalle
			Cpe_guia.setNRO_COMPROBANTE(doc.getSerie_comprobante() + "-" + doc.getNro_comprobante());
			Cpe_guia.setFECHA_DOCUMENTO(fecha_documento);
			Cpe_guia.setCOD_TIPO_DOCUMENTO(doc.getCod_tipo_documento());
			if (doc.getNota_guia() == null) {
				Cpe_guia.setNOTA("");//

			} else {
				Cpe_guia.setNOTA(doc.getNota_guia());//
			}
			Cpe_guia.setITEM_ENVIO(1);
			Cpe_guia.setCOD_MOTIVO_TRASLADO(doc.getCod_motivo_traslado());

			if (doc.getDescripcion_motivo_traslado() == null) {
				Cpe_guia.setDESCRIPCION_MOTIVO_TRASLADO("");
			} else {
				Cpe_guia.setDESCRIPCION_MOTIVO_TRASLADO(doc.getDescripcion_motivo_traslado());

			}

			Cpe_guia.setCOD_UND_PESO_BRUTO("KGM");
			Cpe_guia.setPESO_BRUTO(doc.getPeso_bruto());
			Cpe_guia.setTOTAL_BULTOS(doc.getTotal_bultos());
			Cpe_guia.setCOD_MODALIDAD_TRASLADO(doc.getCod_modalidad_traslado());
			Cpe_guia.setFECHA_INICIO(doc.getFecha_inicio_traslado().substring(0, 10));
			if (doc.getNro_documento_transportista() == null) {
				Cpe_guia.setNRO_DOCUMENTO_TRANSPORTISTA("");
			} else {
				Cpe_guia.setNRO_DOCUMENTO_TRANSPORTISTA(doc.getNro_documento_transportista());

			}
			if (doc.getNro_documento_transportista() == null) {
				Cpe_guia.setRAZON_SOCIAL_TRANSPORTISTA("");
			} else {
				Cpe_guia.setRAZON_SOCIAL_TRANSPORTISTA(doc.getRazon_social_transportista());

			}
			if (doc.getNro_documento_transportista() == null) {
				Cpe_guia.setTIPO_DOCUMENTO_TRANSPORTISTA("");
			} else {
				Cpe_guia.setTIPO_DOCUMENTO_TRANSPORTISTA(doc.getTipo_documento_transportista());

			}
			Cpe_guia.setPLACA_VEHICULO(doc.getPlaca_vehiculo());
			Cpe_guia.setCOD_TIPO_DOC_CHOFER(doc.getCod_tipo_doc_chofer());
			Cpe_guia.setNRO_DOC_CHOFER(doc.getNro_doc_chofer());
			Cpe_guia.setPLACA_CARRETA("");
			Cpe_guia.setCOD_UBIGEO_DESTINO(doc.getCod_ubigeo_destino());
			Cpe_guia.setDIRECCION_DESTINO(doc.getDireccion_destino());
			Cpe_guia.setCOD_UBIGEO_ORIGEN(doc.getCod_ubigeo_origen());
			Cpe_guia.setDIRECCION_ORIGEN(doc.getDireccion_origen());
			Cpe_guia.setUSUARIO_SOL_EMPRESA(doc.getUserSol_empresaEmisora());
			Cpe_guia.setPASS_SOL_EMPRESA(doc.getPassSol_empresaEmisora());
			Cpe_guia.setTIPO_PROCESO(doc.getAmbiente_operacion());// 1=PRODUCCION,2=HOMOLOGACION,3=PRUEBA

			Cpe_guia.setFLG_ANULADO(1);// 0=NO ES ANULACION, 1=ANULACION
			Cpe_guia.setDOC_REFERENCIA_ANU(doc.getNro_documento_modifica());// doc referencia que se anulara
			Cpe_guia.setCOD_TIPO_DOC_REFANU("09"); // tipo de guia (remitente, transportista)
			Cpe_guia.setNRO_DOCUMENTO_EMPRESA(doc.getNro_documento_empresa());
			Cpe_guia.setNRO_DOCUMENTO_CLIENTE(doc.getNro_documento_cliente());
			Cpe_guia.setRAZON_SOCIAL_EMPRESA(doc.getRazon_social_empresa());
			Cpe_guia.setRAZON_SOCIAL_CLIENTE(doc.getRazon_social_cliente());
			Cpe_guia.setTIPO_DOCUMENTO_CLIENTE(doc.getTipo_documento_cliente());
			Cpe_guia.setTIPO_DOCUMENTO_EMPRESA(doc.getTipo_documento_empresa());

			Cpe_guia.setNUMERACION_DAM(doc.getNumeracion_dam());
			Cpe_guia.setCOD_TIPO_DOC_PROVEEDOR(doc.getTipo_documento_proveedor());
			Cpe_guia.setNRO_DOC_PROVEEDOR(doc.getNro_documento_proveedor());
			Cpe_guia.setDESCRIPCION_PROVEEDOR(doc.getNombre_comercial_proveedor());
			Cpe_guia.setNRO_CONTENEDOR("256322");
			List<_CpeGuiaRemisionDetalleBean> lsdoc_Detalle = new ArrayList<_CpeGuiaRemisionDetalleBean>();
//		lsdoc_Detalle=doc.getDetalle_Guia();
			Integer i = 1;
			for (_CpeGuiaRemisionDetalleBean detalle_guia : doc.getDetalle_Guia()) {
				_CpeGuiaRemisionDetalleBean Cpe_Guia_Detalle = new _CpeGuiaRemisionDetalleBean();
				Cpe_Guia_Detalle.setITEM(i);
				Cpe_Guia_Detalle.setUNIDAD_MEDIDA(detalle_guia.getUNIDAD_MEDIDA());
				Cpe_Guia_Detalle.setCANTIDAD(detalle_guia.getCANTIDAD());
				Cpe_Guia_Detalle.setORDER_ITEM(Cpe_Guia_Detalle.getITEM());
				Cpe_Guia_Detalle.setCODIGO(detalle_guia.getCODIGO());
				Cpe_Guia_Detalle.setDESCRIPCION(detalle_guia.getDESCRIPCION());
				lsdoc_Detalle.add(Cpe_Guia_Detalle);
				i++;
			}
			String[] xx = CPEGuiaRemision(Cpe_guia.getTIPO_PROCESO(), ruc, UsuSol, PassSol, PassFirma, Cpe_guia,
					lsdoc_Detalle, doc.getDirDocumentoEmpresaEmisora());
			System.out.println("\n\n=============RESPUESTA SUNAT============");
			System.out.println("flg_rta: " + xx[0] + " \ncod_sunat: " + xx[1] + " \nmsj_sunat: " + xx[2]
					+ " \nhash_cdr: " + xx[3] + " \nhash_cpe: " + xx[4]);
			rpta_sunat = xx;
		} catch (Exception e) {
			System.err.println("ERROR: " + this.getClass().getSimpleName() + "/reemplazarGuia => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/reemplazarGuia" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return rpta_sunat;
	}
///////////////
//FIN	REGISTRO GUIA

//CONSULTAR ESTADO DOCUMENTO
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String[] consultaEstadoDocumento(_DocumentoCpe doc) throws Exception {
		String rpt_sunat[] = null;
		try {

			String ruc = doc.getRuc_empresaEmisora();
			String UsuSol = doc.getUserSol_empresaEmisora();
			String PassSol = doc.getPassSol_empresaEmisora();
			String PassFirma = doc.getPassFirma_empresaEmisora();
			String tipo_doc, serie, nro;
			tipo_doc = doc.getCod_tipo_documento();// 01=factura, 03=boleta, 07=nota credito, 08=nota debito
			serie = doc.getSerie_comprobante();
			nro = doc.getNro_comprobante();
			String[] xx = CPESunatConsultaEstadoFactura(doc.getAmbiente_operacion(), ruc, UsuSol, PassSol, PassFirma,
					tipo_doc, serie, nro);
			System.out.println("\n\n=============RESPUESTA CONSULTA ESTADO DOC SUNAT============");
			System.out.println("flg_rta: " + xx[0] + " \ncod_sunat: " + xx[1] + " \nmsj_sunat: " + xx[2]
					+ " \nhash_cdr: " + xx[3] + " \nhash_cpe: " + xx[4]);

			rpt_sunat = xx;
		} catch (Exception e) {
			System.err.println(
					"ERROR: " + this.getClass().getSimpleName() + "/consultaEstadoDocumento => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/consultaEstadoDocumento" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw e;
		}

		return rpt_sunat;
	}

/////////////////
// FIN DAR BAJA     

	// DAR BAJA FACTURAS
//        public static void main(String[] args) throws Exception {
//            String ruc = "20100066603";
//            String UsuSol = "MODDATOS";
//            String PassSol = "moddatos";
//            String PassFirma = "123456";
	//
//            Cpe_BajaBean Cpe_Baja = new Cpe_BajaBean();
//            Cpe_Baja.setNRO_DOCUMENTO_EMPRESA("20100066603");
//            Cpe_Baja.setRAZON_SOCIAL("CREVPERU S.A.");
//            Cpe_Baja.setTIPO_DOCUMENTO("6");
//            Cpe_Baja.setCODIGO("RA");
//            Cpe_Baja.setSERIE("20161029");
//            Cpe_Baja.setSECUENCIA("1");
//            Cpe_Baja.setFECHA_REFERENCIA("2016-10-28");
//            Cpe_Baja.setFECHA_BAJA("2016-10-29");
//            Cpe_Baja_DetalleBean Cpe_Baja_Detalle = null;
//            List<Cpe_Baja_DetalleBean> lstCpe_BajaDetalle = null;
//            lstCpe_BajaDetalle = new ArrayList<Cpe_Baja_DetalleBean>();
//            //FACTURA ANULADA ITEM 1
//            Cpe_Baja_Detalle = new Cpe_Baja_DetalleBean();
//            Cpe_Baja_Detalle.setITEM(1);
//            Cpe_Baja_Detalle.setTIPO_COMPROBANTE("01");
//            Cpe_Baja_Detalle.setSERIE("F001");
//            Cpe_Baja_Detalle.setNUMERO("750");
//            Cpe_Baja_Detalle.setDESCRIPCION("ERROR DE DIGITACION");
//            lstCpe_BajaDetalle.add(Cpe_Baja_Detalle);
	//
//            //NOTA CREDITO ANULADA ITEM 2
//            Cpe_Baja_Detalle = new Cpe_Baja_DetalleBean();
//            Cpe_Baja_Detalle.setITEM(2);
//            Cpe_Baja_Detalle.setTIPO_COMPROBANTE("07");//NOTA CREDITO
//            Cpe_Baja_Detalle.setSERIE("F100");
//            Cpe_Baja_Detalle.setNUMERO("666");
//            Cpe_Baja_Detalle.setDESCRIPCION("ERROR DE DIGITACION");
//            lstCpe_BajaDetalle.add(Cpe_Baja_Detalle);
	//
//            String[] xx = CPEBaja(3, ruc, UsuSol, PassSol, PassFirma, Cpe_Baja, lstCpe_BajaDetalle);
	//
//            System.out.println("\n\n=============RESPUESTA RESUMEN BOLETA SUNAT============");
//            System.out.println("flg_rta: " + xx[0] + " \ncod_sunat: " + xx[1] + " \nmsj_sunat: " + xx[2] + " \nhash_cdr: " + xx[3] + " \nhash_cpe: " + xx[4]);
	//
//            String nroTicket = xx[2];
//            String nomCPE = ruc + "-" + Cpe_Baja.getCODIGO() + "-" + Cpe_Baja.getSERIE() + "-" + Cpe_Baja.getSECUENCIA();
	//
//            String[] RtaTicket = CPESunatConsultaTicket(3, ruc, UsuSol, PassSol, nomCPE, nroTicket);
//            System.out.println("\n\n=============RESPUESTA TICKET RESUMEN BOLETA SUNAT============");
//            System.out.println("flg_rta: " + RtaTicket[0] + " \ncod_sunat: " + RtaTicket[1] + " \nmsj_sunat: " + RtaTicket[2] + " \nhash_cdr: " + RtaTicket[3] + " \nhash_cpe: " + RtaTicket[4]);
//        }

//        public static void main(String[] args) throws Exception {
//            String ruc = "20100066603";
//            String UsuSol = "MODDATOS";
//            String PassSol = "moddatos";
//            String PassFirma = "123456";
//        }

//RESUMEN DE BOLETAS 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String resumenBoletas(List<_DocumentoCpe> lsdoc) throws FileNotFoundException, NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, ParserConfigurationException, SAXException, MarshalException,
			KeyStoreException, IOException, CertificateException, Exception {
		String rpt_sunat = null;

		try {

			String ruc = lsdoc.get(0).getRuc_empresaEmisora();
			String UsuSol = lsdoc.get(0).getUserSol_empresaEmisora();
			String PassSol = lsdoc.get(0).getPassSol_empresaEmisora();
			String PassFirma = lsdoc.get(0).getPassFirma_empresaEmisora();

			_Cpe_Resumen_BoletaBean CpeResumen = new _Cpe_Resumen_BoletaBean();
			_Cpe_Resumen_Boleta_DetalleBean Cpe_Resumen_Boleta_Detalle;

			CpeResumen.setNRO_DOCUMENTO_EMPRESA(lsdoc.get(0).getNro_documento_empresa());
			CpeResumen.setRAZON_SOCIAL(lsdoc.get(0).getRazon_social_empresa());
			CpeResumen.setTIPO_DOCUMENTO(lsdoc.get(0).getTipo_documento_empresa());
			CpeResumen.setCODIGO("RC");
			String[] parts = lsdoc.get(0).getFecha_baja().split("-");

			CpeResumen.setSERIE(parts[0] + parts[1] + parts[2]);
			CpeResumen.setSECUENCIA(lsdoc.get(0).getSecuencia().toString());
			CpeResumen.setFECHA_REFERENCIA(lsdoc.get(0).getFecha_referencia());
			CpeResumen.setFECHA_DOCUMENTO(lsdoc.get(0).getFecha_baja());

			List<_Cpe_Resumen_Boleta_DetalleBean> lstCpe_ResumenDetalle = null;
			lstCpe_ResumenDetalle = new ArrayList<_Cpe_Resumen_Boleta_DetalleBean>();

			int i = 1;
			for (_DocumentoCpe doc : lsdoc) {
				Cpe_Resumen_Boleta_Detalle = new _Cpe_Resumen_Boleta_DetalleBean();
				Cpe_Resumen_Boleta_Detalle.setITEM(i);
				Cpe_Resumen_Boleta_Detalle.setTIPO_COMPROBANTE(doc.getCod_tipo_documento());
				Cpe_Resumen_Boleta_Detalle
						.setNRO_COMPROBANTE(doc.getSerie_comprobante() + "-" + doc.getNro_comprobante());
				Cpe_Resumen_Boleta_Detalle.setTIPO_DOCUMENTO(doc.getTipo_documento_cliente());
				Cpe_Resumen_Boleta_Detalle.setNRO_DOCUMENTO(doc.getNro_doc_cliente());
				Cpe_Resumen_Boleta_Detalle.setTIPO_COMPROBANTE_REF(doc.getTipo_comprobante_modifica());
				Cpe_Resumen_Boleta_Detalle.setNRO_COMPROBANTE_REF(doc.getNro_documento_modifica());
				Cpe_Resumen_Boleta_Detalle.setSTATU("3");
				Cpe_Resumen_Boleta_Detalle.setCOD_MONEDA(doc.getCod_moneda());
				Cpe_Resumen_Boleta_Detalle.setTOTAL(doc.getTotal());
				Cpe_Resumen_Boleta_Detalle.setGRAVADA(doc.getTotal_gravadas());
				Cpe_Resumen_Boleta_Detalle.setISC(doc.getTotal_isc());
				Cpe_Resumen_Boleta_Detalle.setIGV(doc.getTotal_igv());
				Cpe_Resumen_Boleta_Detalle.setOTROS(doc.getTotal_otr_imp());
				Cpe_Resumen_Boleta_Detalle.setCARGO_X_ASIGNACION(1);
				Cpe_Resumen_Boleta_Detalle.setMONTO_CARGO_X_ASIG(0);
				Cpe_Resumen_Boleta_Detalle.setEXONERADO(doc.getTotal_exoneradas());
				Cpe_Resumen_Boleta_Detalle.setINAFECTO(doc.getTotal_inafecta());
				Cpe_Resumen_Boleta_Detalle.setEXPORTACION(0);
				Cpe_Resumen_Boleta_Detalle.setGRATUITAS(doc.getTotal_gratuitas());
				lstCpe_ResumenDetalle.add(Cpe_Resumen_Boleta_Detalle);
				i++;
			}
			_Cpe_BajaBean cpe = new _Cpe_BajaBean();
			cpe.setDirDocumentoEmpresaEmisora(lsdoc.get(0).getDirDocumentoEmpresaEmisora());
			String dir = lsdoc.get(0).getDirDocumentoEmpresaEmisora();

			String[] xx = CPEResumenBoletas(1, ruc, UsuSol, PassSol, PassFirma, CpeResumen, lstCpe_ResumenDetalle, dir);

			System.out.println("\n\n=============RESPUESTA RESUMEN BOLETA SUNAT============");
			System.out.println("flg_rta: " + xx[0] + " \ncod_sunat: " + xx[1] + " \nmsj_sunat: " + xx[2]
					+ " \nhash_cdr: " + xx[3] + " \nhash_cpe: " + xx[4]);

			String nroTicket = xx[2];
			String nomCPE = ruc + "-" + CpeResumen.getCODIGO() + "-" + CpeResumen.getSERIE() + "-"
					+ CpeResumen.getSECUENCIA();

			String[] RtaTicket = CPESunatConsultaTicket(1, ruc, UsuSol, PassSol, nomCPE, nroTicket, cpe);
			System.out.println("\n\n=============RESPUESTA TICKET RESUMEN BOLETA SUNAT============");
			System.out.println("flg_rta: " + RtaTicket[0] + " \ncod_sunat: " + RtaTicket[1] + " \nmsj_sunat: "
					+ RtaTicket[2] + " \nhash_cdr: " + RtaTicket[3] + " \nhash_cpe: " + RtaTicket[4]);
			rpt_sunat = xx[2] + '-' + RtaTicket[2] + "--" + RtaTicket[0] + "--" + xx[0];
			String rpt_sunat_estado[] = null;
			if (RtaTicket[1].equals("0000")) {
				rpt_sunat_estado = consultaEstadoDocumento(lsdoc.get(0));
				if (rpt_sunat_estado[1].equals("0001")) {
					rpt_sunat = xx[2] + '-' + "EL COMPROBANTE" + CpeResumen.getCODIGO() + "-" + CpeResumen.getSERIE()
							+ "-" + CpeResumen.getSECUENCIA() + " HA SIDO ACEPTADO" + "--" + rpt_sunat_estado[0] + "--"
							+ xx[0];
				}

			}

		} catch (Exception e) {
			System.err.println("ERROR: " + this.getClass().getSimpleName() + "/resumenBoletas => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/resumenBoletas" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return rpt_sunat;

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String[] CPEGuiaRemision(int flg_tipo, String ruc, String UsuarioSol, String PassSol, String passFirma,
			_CpeGuiaRemisionBean Cpe, List<_CpeGuiaRemisionDetalleBean> lstCpe_Detalle, String ruta)
			throws FileNotFoundException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
			ParserConfigurationException, SAXException, MarshalException, KeyStoreException, IOException,
			CertificateException, Exception {
		List<_Parametros> lsPar = new ArrayList<>();
		_Parametros par = new _Parametros();
		par.setGrupo(Constantes.codigoGrupoIniParametros);
		par.setId_empresa(Constantes.parametroIdEmpresaGlobal);
		lsPar = parametrosService.retornaObjParametrosPorGrupoe(par);
		String rutamatriz = "";
		for (int i = 0; i < lsPar.size(); i++) {
			if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
				rutamatriz = lsPar.get(i).getValor();
				break;
			}
		}
		String[] Rta = null;
		String[] credenciales = null;
		int flg_firma = 0;// 0=factura,boleta,nc,nd====0=retencion
		// String NombreCPE = Cpe.getNRO_DOCUMENTO_EMPRESA() + "-" +
		// Cpe.getCOD_TIPO_DOCUMENTO() + "-" + Cpe.getNRO_COMPROBANTE();
		// //"20100066603-03-BB11-750";//"20100066603-03-BB11-750.ZIP";
		String NombreCPE = ruc + "-" + Cpe.getCOD_TIPO_DOCUMENTO() + "-" + Cpe.getNRO_COMPROBANTE(); // "20100066603-03-BB11-750";//"20100066603-03-BB11-750.ZIP";
//		VarGlo.Rutas();
		// CPESunat.GenerarXMLCPE_BETA2(rutaXMLCPE);
		/*
		 * 3=PRUEBA O BETA 2=HOMOLOGACION 1=PRODUCCION
		 */
		if (flg_tipo == 3) {// 3=PRUEBA O BETA
			String rutaXMLCPE = ruta + File.separator + NombreCPE;// "\\20100066603-03-BB11-750";
			String rutaZIPCDR = ruta + File.separator + "R-" + NombreCPE;
			String rutaXMLCDR = ruta + File.separator;
			String rutaWSCPE_BE = mapperParametros.selecValorParametroPorCodigo(Constantes.codWSGUIA_BE).getValor();
			String rutaFirma = this.rutaFirma(Cpe.getIfacturacionPse(), rutamatriz, ruc);
			System.out.println("PRUEBA");
			if (Cpe.getCOD_TIPO_DOCUMENTO().equals("09")) {
				CPESunat.GenerarXMLGUIA_REMISION(rutaXMLCPE, Cpe, lstCpe_Detalle);
				// GenerarXMLGUIA_REMISION(String RutaXml, CpeGuiaRemisionBean Cpe,
				// List<CpeGuiaRemisionDetalleBean> lstCpe_Detalle) {
			} else {

			}
			credenciales = this.credencialesFacturacion(Cpe.getIfacturacionPse(), ruc, UsuarioSol, PassSol, passFirma);
			ruc = credenciales[0];
			UsuarioSol = credenciales[1];
			PassSol = credenciales[2];
			passFirma = credenciales[3];
			Signature.Signature(flg_firma, rutaXMLCPE, rutaFirma, passFirma);
			Rta = sunat.ConexionCPEBeta(ruc, UsuarioSol, PassSol, NombreCPE, rutaXMLCPE, rutaZIPCDR, rutaXMLCDR,
					rutaWSCPE_BE);
			/*
			 * public String[] ConexionCPEBeta( String ruc, String UsuarioSol, String
			 * PassSol, String NombreCPE, String RutaCPE, String RutaCDR, String RutaXMLCDR,
			 * String RutaWS) {
			 */
		}
		if (flg_tipo == 1) {// 1=PRODUCCION
			String rutaXMLCPE = ruta + File.separator + NombreCPE;// "\\20100066603-03-BB11-750";
			String rutaZIPCDR = ruta + File.separator + "R-" + NombreCPE;
			String rutaXMLCDR = ruta + File.separator;
			String rutaWSCPE_PD =  mapperParametros.selecValorParametroPorCodigo(Constantes.codWSGUIA_PD).getValor();
			String rutaFirma = this.rutaFirma(Cpe.getIfacturacionPse(), rutamatriz, ruc);
			System.out.println("PRODUCCION");
			if (Cpe.getCOD_TIPO_DOCUMENTO().equals("09")) {
				CPESunat.GenerarXMLGUIA_REMISION(rutaXMLCPE, Cpe, lstCpe_Detalle);
				// GenerarXMLGUIA_REMISION(String RutaXml, CpeGuiaRemisionBean Cpe,
				// List<CpeGuiaRemisionDetalleBean> lstCpe_Detalle) {
			} else {

			}
			credenciales = this.credencialesFacturacion(Cpe.getIfacturacionPse(), ruc, UsuarioSol, PassSol, passFirma);
			ruc = credenciales[0];
			UsuarioSol = credenciales[1];
			PassSol = credenciales[2];
			passFirma = credenciales[3];
			Signature.Signature(flg_firma, rutaXMLCPE, rutaFirma, passFirma);
			Rta = sunat.ConexionCPEProduccion(ruc, UsuarioSol, PassSol, NombreCPE, rutaXMLCPE, rutaZIPCDR, rutaXMLCDR,
					rutaWSCPE_PD);
		}
		return Rta;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String[] CPESunat(int flg_tipo, String ruc, String UsuarioSol, String PassSol, String passFirma,
			_CpeBean Cpe, List<_Cpe_DetalleBean> lstCpe_Detalle) throws FileNotFoundException, NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, ParserConfigurationException, SAXException, MarshalException,
			KeyStoreException, IOException, CertificateException, Exception {
		//TODO: correct
		String[] Rta = null;
		List<_Parametros> lsPar = new ArrayList<>();
		_Parametros par = new _Parametros();
		par.setGrupo(Constantes.codigoGrupoIniParametros);
		par.setId_empresa(Constantes.parametroIdEmpresaGlobal);
		lsPar = parametrosService.retornaObjParametrosPorGrupoe(par);
		String rutamatriz = "";
		for (int i = 0; i < lsPar.size(); i++) {
			if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
				rutamatriz = lsPar.get(i).getValor();
				break;
			}
		}

		// String rutabase =
		// mapperParametros.selecValorParametroPorCodigo(Constantes.codigoParametroRutaBase).getValor().replace("|",
		// File.separator);
		String[] credenciales = null;

		int flg_firma = 0;// 0=factura,boleta,nc,nd====0=retencion

		// String NombreCPE = Cpe.getNRO_DOCUMENTO_EMPRESA() + "-" +
		// Cpe.getCOD_TIPO_DOCUMENTO() + "-" + Cpe.getNRO_COMPROBANTE();
		// //"20100066603-03-BB11-750";//"20100066603-03-BB11-750.ZIP";
		String NombreCPE = ruc + "-" + Cpe.getCOD_TIPO_DOCUMENTO() + "-" + Cpe.getNRO_COMPROBANTE(); // "20100066603-03-BB11-750";//"20100066603-03-BB11-750.ZIP";
		// VarGlo.Rutas();
		// CPESunat.GenerarXMLCPE_BETA2(rutaXMLCPE);
		/*
		 * 3=PRUEBA O BETA 2=HOMOLOGACION 1=PRODUCCION
		 */

		if (flg_tipo == 3) {// 3=PRUEBA O BETA
			System.out.println("-----AMBIENTE PRUEBAS------");

			String rutaXMLCPE = Cpe.getDirDocumentoEmpresaEmisora() + separador + NombreCPE;// "\\20100066603-03-BB11-750";
			String rutaZIPCDR = Cpe.getDirDocumentoEmpresaEmisora() + separador + "R-" + NombreCPE;
			String rutaXMLCDR = Cpe.getDirDocumentoEmpresaEmisora() + separador;
			String rutaWSCPE_BE = mapperParametros.selecValorParametroPorCodigo(Constantes.codWSCPE_BE).getValor();
			String rutaFirma = this.rutaFirma(Cpe.getIfacturacionPse(), rutamatriz, ruc);

			if (Cpe.getCOD_TIPO_DOCUMENTO().equals("01") || Cpe.getCOD_TIPO_DOCUMENTO().equals("03")
					|| Cpe.getCOD_TIPO_DOCUMENTO().equals("12")) {
				CPESunat.GenerarXMLCPE(rutaXMLCPE, Cpe, lstCpe_Detalle);
			} else if (Cpe.getCOD_TIPO_DOCUMENTO().equals("07")) {
				CPESunat.GenerarXML_NC(rutaXMLCPE, Cpe, lstCpe_Detalle);
			} else if (Cpe.getCOD_TIPO_DOCUMENTO().equals("08")) {
				CPESunat.GenerarXML_ND(rutaXMLCPE, Cpe, lstCpe_Detalle);
			}
			credenciales = this.credencialesFacturacion(Cpe.getIfacturacionPse(), ruc, UsuarioSol, PassSol, passFirma);
			ruc = credenciales[0];
			UsuarioSol = credenciales[1];
			PassSol = credenciales[2];
			passFirma = credenciales[3];

			Signature.Signature(flg_firma, rutaXMLCPE, rutaFirma, passFirma);
			Rta = sunat.ConexionCPEBeta(ruc, UsuarioSol, PassSol, NombreCPE, rutaXMLCPE, rutaZIPCDR, rutaXMLCDR,
					rutaWSCPE_BE);
			/*
			 * public String[] ConexionCPEBeta( String ruc, String UsuarioSol, String
			 * PassSol, String NombreCPE, String RutaCPE, String RutaCDR, String RutaXMLCDR,
			 * String RutaWS) {
			 */
		}

		if (flg_tipo == 1) {// 1=PRODUCCION
			System.out.println("-----AMBIENTE PRODUCCION------");

			String rutaXMLCPE = Cpe.getDirDocumentoEmpresaEmisora() + separador + NombreCPE;// "\\20100066603-03-BB11-750";
			String rutaZIPCDR = Cpe.getDirDocumentoEmpresaEmisora() + separador + "R-" + NombreCPE;
			String rutaXMLCDR = Cpe.getDirDocumentoEmpresaEmisora() + separador;
			String rutaWSCPE_PD = mapperParametros.selecValorParametroPorCodigo(Constantes.codWSCPE_PD).getValor();
			String rutaFirma = this.rutaFirma(Cpe.getIfacturacionPse(), rutamatriz, ruc);
			if (Cpe.getCOD_TIPO_DOCUMENTO().equals("01") || Cpe.getCOD_TIPO_DOCUMENTO().equals("03")
					|| Cpe.getCOD_TIPO_DOCUMENTO().equals("12")) {
				CPESunat.GenerarXMLCPE(rutaXMLCPE, Cpe, lstCpe_Detalle);
			} else if (Cpe.getCOD_TIPO_DOCUMENTO().equals("07")) {
				CPESunat.GenerarXML_NC(rutaXMLCPE, Cpe, lstCpe_Detalle);
			} else if (Cpe.getCOD_TIPO_DOCUMENTO().equals("08")) {
				CPESunat.GenerarXML_ND(rutaXMLCPE, Cpe, lstCpe_Detalle);
			}
			credenciales = this.credencialesFacturacion(Cpe.getIfacturacionPse(), ruc, UsuarioSol, PassSol, passFirma);
			ruc = credenciales[0];
			UsuarioSol = credenciales[1];
			PassSol = credenciales[2];
			passFirma = credenciales[3];
			Signature.Signature(flg_firma, rutaXMLCPE, rutaFirma, passFirma);
			Rta = sunat.ConexionCPEProduccion(ruc, UsuarioSol, PassSol, NombreCPE, rutaXMLCPE, rutaZIPCDR, rutaXMLCDR,
					rutaWSCPE_PD);
		}
		return Rta;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String[] CPESunat_RTPC(int flg_tipo, String ruc, String UsuarioSol, String PassSol, String passFirma,
			_Cpe_RetencionPercepcionBean Cpe_Retencion,
			List<_Cpe_RetencionPercepcion_DetalleBean> lstCpe_Retencion_Detalle) throws FileNotFoundException,
			NoSuchAlgorithmException, InvalidAlgorithmParameterException, ParserConfigurationException, SAXException,
			MarshalException, KeyStoreException, IOException, CertificateException, Exception {

		List<_Parametros> lsPar = new ArrayList<>();
		_Parametros par = new _Parametros();
		par.setGrupo(Constantes.codigoGrupoIniParametros);
		par.setId_empresa(Constantes.parametroIdEmpresaGlobal);
		lsPar = parametrosService.retornaObjParametrosPorGrupoe(par);
		String rutamatriz = "";
		for (int i = 0; i < lsPar.size(); i++) {
			if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
				rutamatriz = lsPar.get(i).getValor();
				break;
			}
		}

		String[] Rta = null;
		String rutabase = mapperParametros.selecValorParametroPorCodigo(Constantes.codigoParametroRutaBase).getValor()
				.replace("|", File.separator);
		String[] credenciales = null;
		String ruta_app = System.getProperty("user.dir");
		int flg_firma = 0;// 1=factura,boleta,nc,nd====0=retencion

		String NombreCPE = ruc + "-" + Cpe_Retencion.getCOD_TIPO_DOCUMENTO() + "-" + Cpe_Retencion.getNRO_COMPROBANTE(); // "20100066603-03-BB11-750";//"20100066603-03-BB11-750.ZIP";
		VarGlo.Rutas();
		// CPESunat.GenerarXMLCPE_BETA2(rutaXMLCPE);
		/*
		 * 3=PRUEBA O BETA 2=HOMOLOGACION 1=PRODUCCION
		 */
		if (flg_tipo == 3) {// 3=PRUEBA O BETA
			String rutaXMLCPE = Cpe_Retencion.getDirDocumentoEmpresaEmisora() + File.separator + NombreCPE;// "\\20100066603-03-BB11-750";
			String rutaZIPCDR = Cpe_Retencion.getDirDocumentoEmpresaEmisora() + File.separator + "R-" + NombreCPE;
			String rutaXMLCDR = Cpe_Retencion.getDirDocumentoEmpresaEmisora() + File.separator;
			String rutaWSCPE_RTPC_BE = mapperParametros.selecValorParametroPorCodigo(Constantes.codWSRTPC_BE)
					.getValor();
			;

			String rutaFirma = this.rutaFirma(Cpe.getIfacturacionPse(), rutamatriz, ruc);

			if (Cpe_Retencion.getCOD_TIPO_DOCUMENTO().equals("20")) { // Retencion
				CPESunat.GenerarXMLCPE_RT(rutaXMLCPE, Cpe_Retencion, lstCpe_Retencion_Detalle);
			} else if (Cpe_Retencion.getCOD_TIPO_DOCUMENTO().equals("40")) { // Percepcion
				CPESunat.GenerarXMLCPE_PC(rutaXMLCPE, Cpe_Retencion, lstCpe_Retencion_Detalle);
			}
			credenciales = this.credencialesFacturacion(Cpe.getIfacturacionPse(), ruc, UsuarioSol, PassSol, passFirma);
			ruc = credenciales[0];
			UsuarioSol = credenciales[1];
			PassSol = credenciales[2];
			passFirma = credenciales[3];
			Signature.Signature(flg_firma, rutaXMLCPE, rutaFirma, passFirma);
			Rta = sunat.ConexionCPEBeta_RTPC(ruc, UsuarioSol, PassSol, NombreCPE, rutaXMLCPE, rutaZIPCDR, rutaXMLCDR,
					rutaWSCPE_RTPC_BE);
		}
		if (flg_tipo == 1) {// 1=PRODUCCION
			String rutaXMLCPE = Cpe_Retencion.getDirDocumentoEmpresaEmisora() + File.separator + NombreCPE;// "\\20100066603-03-BB11-750";
			String rutaZIPCDR = Cpe_Retencion.getDirDocumentoEmpresaEmisora() + File.separator + "R-" + NombreCPE;
			String rutaXMLCDR = Cpe_Retencion.getDirDocumentoEmpresaEmisora() + File.separator;
			String rutaFirma = this.rutaFirma(Cpe.getIfacturacionPse(), rutamatriz, ruc);

			String rutaWSCPE_RTPC_PD = VarGlo.rutaWSCPE_RTPC_PD;

			if (Cpe_Retencion.getCOD_TIPO_DOCUMENTO().equals("20")) {
				CPESunat.GenerarXMLCPE_RT(rutaXMLCPE, Cpe_Retencion, lstCpe_Retencion_Detalle);
			} else if (Cpe_Retencion.getCOD_TIPO_DOCUMENTO().equals("40")) {
				CPESunat.GenerarXMLCPE_PC(rutaXMLCPE, Cpe_Retencion, lstCpe_Retencion_Detalle);
			}
			credenciales = this.credencialesFacturacion(Cpe.getIfacturacionPse(), ruc, UsuarioSol, PassSol, passFirma);
			ruc = credenciales[0];
			UsuarioSol = credenciales[1];
			PassSol = credenciales[2];
			passFirma = credenciales[3];
			Signature.Signature(flg_firma, rutaXMLCPE, rutaFirma, passFirma);
			Rta = sunat.ConexionCPEProduccion_RTPC(ruc, UsuarioSol, PassSol, NombreCPE, rutaXMLCPE, rutaZIPCDR,
					rutaXMLCDR, rutaWSCPE_RTPC_PD);
		}
		return Rta;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String[] CPEBaja(int flg_tipo, String ruc, String UsuarioSol, String PassSol, String passFirma,
			_Cpe_BajaBean Cpe_Baja, List<_Cpe_Baja_DetalleBean> lstCpe_BajaDetalle) throws FileNotFoundException,
			NoSuchAlgorithmException, InvalidAlgorithmParameterException, ParserConfigurationException, SAXException,
			MarshalException, KeyStoreException, IOException, CertificateException, Exception {

		try {
			List<_Parametros> lsPar = new ArrayList<>();
			_Parametros par = new _Parametros();
			par.setGrupo(Constantes.codigoGrupoIniParametros);
			par.setId_empresa(Constantes.parametroIdEmpresaGlobal);
			lsPar = parametrosService.retornaObjParametrosPorGrupoe(par);
			String rutamatriz = this.obtenerParametroCodigo(lsPar, Constantes.codigoParametroRutaMatriz).getValor();

			String[] Rta = null;
			String[] credenciales = null;
			int flg_firma = 0;// COMUNICACION DE BAJA 0
			// Nombre del archivo ZIP: 20100066603-RA-20110522.ZIP
			// Nombre del archivo XML: 20100066603-RA-20110522.XML
			String NombreCPE = Cpe_Baja.getNRO_DOCUMENTO_EMPRESA() + "-" + Cpe_Baja.getCODIGO() + "-"
					+ Cpe_Baja.getSERIE() + "-" + Cpe_Baja.getSECUENCIA();

			/*
			 * 3=PRUEBA O BETA 2=HOMOLOGACION 1=PRODUCCION
			 */
			if (flg_tipo == 3) {// 3=PRUEBA O BETA
				System.out.println("ENTRA A DAR BAJA PRUEBA");
				// String rutaXMLCPE = VarGlo.rutaXMLCPE_BE + File.separator + NombreCPE;//
				// "\\20100066603-03-BB11-750";
				// String rutaZIPCDR = VarGlo.rutaXMLCDR_BE + File.separator + "R-" + NombreCPE;
				// String rutaWSCPE_BE = VarGlo.rutaWSCPE_BE;
				// String rutaWSCPE_RTPC_BE = VarGlo.rutaWSCPE_RTPC_BE;

				// NUEVAS RUTAS
				String rutaXMLCPE = Cpe_Baja.getDirDocumentoEmpresaEmisora() + separador + NombreCPE;// "\\20100066603-03-BB11-750";
				String rutaZIPCDR = Cpe_Baja.getDirDocumentoEmpresaEmisora() + separador + "R-" + NombreCPE;
				String rutaWSCPE_BE = this.obtenerParametroCodigo(lsPar, "WSCPE_BE").getValor();
				String rutaWSCPE_RTPC_BE = this.obtenerParametroCodigo(lsPar, "WSCPE_RTPC_BE").getValor();
				String rutaFirma = this.rutaFirma(Cpe_Baja.getIfacturacionPse(), rutamatriz, ruc);

				credenciales = this.credencialesFacturacion(Cpe_Baja.getIfacturacionPse(), ruc, UsuarioSol, PassSol,
						passFirma);
				ruc = credenciales[0];
				UsuarioSol = credenciales[1];
				PassSol = credenciales[2];
				passFirma = credenciales[3];
				CPESunat.GenerarXMLBAJA(rutaXMLCPE, Cpe_Baja, lstCpe_BajaDetalle);
				Signature.Signature(flg_firma, rutaXMLCPE, rutaFirma, passFirma);
				if (Cpe_Baja.getCODIGO().equals("RR")) {// SERVIDOR DE RETENCION
					Rta = sunat.ConexionCPEBeta_RTPC(ruc, UsuarioSol, PassSol, NombreCPE, rutaXMLCPE, rutaZIPCDR, "",
							rutaWSCPE_RTPC_BE);
				} else {// SERVIDOR DE FATURAS, BOLETAS, NOTA CREDITO, NOTA DBITO
					Rta = sunat.ConexionCPEBeta(ruc, UsuarioSol, PassSol, NombreCPE, rutaXMLCPE, rutaZIPCDR, "",
							rutaWSCPE_BE);
				}
			}
			if (flg_tipo == 2) {// 2=HOMOLOGACION
				System.out.println("ENTRA A DAR BAJA HOMOLOGACION");

				String rutaXMLCPE = VarGlo.rutaXMLCPE_HO + File.separator + NombreCPE;// "\\20100066603-03-BB11-750";
				String rutaZIPCDR = VarGlo.rutaXMLCDR_HO + File.separator + "R-" + NombreCPE;
				String rutaFirma = VarGlo.rutaFirma_HO + File.separator + ruc + ".pfx";
				String rutaWSCPE_HO = VarGlo.rutaWSCPE_HO;

				CPESunat.GenerarXMLBAJA(rutaXMLCPE, Cpe_Baja, lstCpe_BajaDetalle);
				Signature.Signature(flg_firma, rutaXMLCPE, rutaFirma, passFirma);
				Rta = sunat.ConexionCPEHomologacion(ruc, UsuarioSol, PassSol, NombreCPE, rutaXMLCPE, rutaZIPCDR, "",
						rutaWSCPE_HO);
			}
			if (flg_tipo == 1) {// 1=PRODUCCION
				System.out.println("ENTRA A DAR BAJA PRODUCCION");

				// String rutaXMLCPE = VarGlo.rutaXMLCPE_PRO + File.separator + NombreCPE;//
				// "\\20100066603-03-BB11-750";
				// String rutaZIPCDR = VarGlo.rutaXMLCDR_PRO + File.separator + "R-" +
				// NombreCPE;
				String rutaXMLCPE = Cpe_Baja.getDirDocumentoEmpresaEmisora() + File.separator + NombreCPE;// "\\20100066603-03-BB11-750";
				String rutaZIPCDR = Cpe_Baja.getDirDocumentoEmpresaEmisora() + File.separator + "R-" + NombreCPE;
				String rutaWSCPE_PD = this.obtenerParametroCodigo(lsPar, "WSCPE_PD").getValor();
				String rutaWSCPE_RTPC_PD = this.obtenerParametroCodigo(lsPar, "WSCPE_RTPC_PD").getValor();
				String rutaFirma = this.rutaFirma(Cpe_Baja.getIfacturacionPse(), rutamatriz, ruc);

				credenciales = this.credencialesFacturacion(Cpe.getIfacturacionPse(), ruc, UsuarioSol, PassSol,
						passFirma);
				ruc = credenciales[0];
				UsuarioSol = credenciales[1];
				PassSol = credenciales[2];
				passFirma = credenciales[3];
				CPESunat.GenerarXMLBAJA(rutaXMLCPE, Cpe_Baja, lstCpe_BajaDetalle);
				Signature.Signature(flg_firma, rutaXMLCPE, rutaFirma, passFirma);
				if (Cpe_Baja.getCODIGO().equals("RR")) {// SERVIDOR DE RETENCION, PERCEPCION
					Rta = sunat.ConexionCPEProduccion_RTPC(ruc, UsuarioSol, PassSol, NombreCPE, rutaXMLCPE, rutaZIPCDR,
							"", rutaWSCPE_RTPC_PD);
				} else {// SERVIDOR DE FATURAS, BOLETAS, NOTA CREDITO, NOTA DBITO
					Rta = sunat.ConexionCPEProduccion(ruc, UsuarioSol, PassSol, NombreCPE, rutaXMLCPE, rutaZIPCDR, "",
							rutaWSCPE_PD);
				}
			}
			return Rta;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " CPEBaja. ERROR : " + e.getMessage()
					+ " Linea: " + e.getStackTrace()[0].getLineNumber());
			throw e;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public static String[] CPEResumenBoletas(int flg_tipo, String ruc, String UsuarioSol, String PassSol,
			String passFirma, _Cpe_Resumen_BoletaBean Cpe_Resumen_Boleta,
			List<_Cpe_Resumen_Boleta_DetalleBean> lstCpe_Resumen_Boleta_Detalle, String dir)
			throws FileNotFoundException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
			ParserConfigurationException, SAXException, MarshalException, KeyStoreException, IOException,
			CertificateException, Exception {
		String[] Rta = null;
		int flg_firma = 0;// COMUNICACION DE BAJA 0
		// Nombre del archivo ZIP: 20100066603-RA-20110522.ZIP
		// Nombre del archivo XML: 20100066603-RA-20110522.XML
		String NombreCPE = Cpe_Resumen_Boleta.getNRO_DOCUMENTO_EMPRESA() + "-" + Cpe_Resumen_Boleta.getCODIGO() + "-"
				+ Cpe_Resumen_Boleta.getSERIE() + "-" + Cpe_Resumen_Boleta.getSECUENCIA();

		VarGlo.Rutas();
		/*
		 * 3=PRUEBA O BETA 2=HOMOLOGACION 1=PRODUCCION
		 */
		if (flg_tipo == 3) {// 3=PRUEBA O BETA
			String rutaXMLCPE = VarGlo.rutaXMLCPE_BE + File.separator + NombreCPE;// "\\20100066603-03-BB11-750";
			String rutaZIPCDR = VarGlo.rutaXMLCDR_BE + File.separator + "R-" + NombreCPE;
			String rutaWSCPE_BE = VarGlo.rutaWSCPE_BE;

			String rutaFirma = VarGlo.rutaFirma_BE; // + "\\" + ruc + ".pfx";
			passFirma = VarGlo.passFirma_BE;
			CPESunat.GenerarXMLRESUMEN(rutaXMLCPE, Cpe_Resumen_Boleta, lstCpe_Resumen_Boleta_Detalle);
			Signature.Signature(flg_firma, rutaXMLCPE, rutaFirma, passFirma);
			Rta = sunat.ConexionCPEBeta(ruc, UsuarioSol, PassSol, NombreCPE, rutaXMLCPE, rutaZIPCDR, "", rutaWSCPE_BE);
		}
		if (flg_tipo == 2) {// 2=HOMOLOGACION
			String rutaXMLCPE = VarGlo.rutaXMLCPE_HO + File.separator + NombreCPE;// "\\20100066603-03-BB11-750";
			String rutaZIPCDR = VarGlo.rutaXMLCDR_HO + File.separator + "R-" + NombreCPE;
			String rutaFirma = VarGlo.rutaFirma_HO + File.separator + ruc + ".pfx";
			String rutaWSCPE_HO = VarGlo.rutaWSCPE_HO;

			CPESunat.GenerarXMLRESUMEN(rutaXMLCPE, Cpe_Resumen_Boleta, lstCpe_Resumen_Boleta_Detalle);
			Signature.Signature(flg_firma, rutaXMLCPE, rutaFirma, passFirma);
			Rta = sunat.ConexionCPEHomologacion(ruc, UsuarioSol, PassSol, NombreCPE, rutaXMLCPE, rutaZIPCDR, "",
					rutaWSCPE_HO);
		}
		if (flg_tipo == 1) {// 1=PRODUCCION
			String rutaXMLCPE = dir + File.separator + NombreCPE;// "\\20100066603-03-BB11-750";
			String rutaZIPCDR = dir + File.separator + "R-" + NombreCPE;
			String rutaFirma = VarGlo.rutaFirma_PRO + File.separator + ruc + File.separator + ruc + ".pfx";
			String rutaWSCPE_PD = VarGlo.rutaWSCPE_PD;

			CPESunat.GenerarXMLRESUMEN(rutaXMLCPE, Cpe_Resumen_Boleta, lstCpe_Resumen_Boleta_Detalle);
			Signature.Signature(flg_firma, rutaXMLCPE, rutaFirma, passFirma);
			Rta = sunat.ConexionCPEProduccion(ruc, UsuarioSol, PassSol, NombreCPE, rutaXMLCPE, rutaZIPCDR, "",
					rutaWSCPE_PD);
		}
		return Rta;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public static String[] CPESunatConsultaTicket(int flg_tipo, String ruc, String UsuarioSol, String PassSol,
			String nomCPE, String Ticket, _Cpe_BajaBean Cpe_Baja) throws FileNotFoundException,
			NoSuchAlgorithmException, InvalidAlgorithmParameterException, ParserConfigurationException, SAXException,
			MarshalException, KeyStoreException, IOException, CertificateException, Exception {
		String[] Rta = null;

		String NombreCPE = nomCPE; // ejemplo: "20100066603-03-BB11-750";
		VarGlo.Rutas();
		/*
		 * 3=PRUEBA O BETA 2=HOMOLOGACION 1=PRODUCCION
		 */
		if (flg_tipo == 3) {// 3=PRUEBA O BETA
			String rutaXMLCPE = VarGlo.rutaXMLCPE_BE + File.separator + NombreCPE;// "\\20100066603-03-BB11-750";
			String rutaZIPCDR = VarGlo.rutaXMLCDR_BE + File.separator + "R-" + NombreCPE;
			String rutaXMLCDR = VarGlo.rutaXMLCDR_BE + File.separator;
			String rutaWSCPE_BE = VarGlo.rutaWSCPE_BE;

			Rta = sunat.ConexionCPEConsultaTicket(ruc, UsuarioSol, PassSol, Ticket, rutaXMLCPE, rutaZIPCDR, rutaXMLCDR,
					rutaWSCPE_BE);
		}
		if (flg_tipo == 2) {// 2=HOMOLOGACION
			String rutaXMLCPE = VarGlo.rutaXMLCPE_HO + File.separator + NombreCPE;// "\\20100066603-03-BB11-750";
			String rutaZIPCDR = VarGlo.rutaXMLCDR_HO + File.separator + "R-" + NombreCPE;
			String rutaXMLCDR = VarGlo.rutaXMLCDR_HO + File.separator;
			String rutaWSCPE_HO = VarGlo.rutaWSCPE_HO;

			Rta = sunat.ConexionCPEConsultaTicket(ruc, UsuarioSol, PassSol, Ticket, rutaXMLCPE, rutaZIPCDR, rutaXMLCDR,
					rutaWSCPE_HO);
		}
		if (flg_tipo == 1) {// 1=PRODUCCION
			String rutaXMLCPE = Cpe_Baja.getDirDocumentoEmpresaEmisora() + File.separator + NombreCPE;// "\\20100066603-03-BB11-750";
			String rutaZIPCDR = Cpe_Baja.getDirDocumentoEmpresaEmisora() + File.separator + "R-" + NombreCPE;
			String rutaXMLCDR = Cpe_Baja.getDirDocumentoEmpresaEmisora() + File.separator;
			String rutaWSCPE_PD = VarGlo.rutaWSCPE_PD;
			Rta = sunat.ConexionCPEConsultaTicket(ruc, UsuarioSol, PassSol, Ticket, rutaXMLCPE, rutaZIPCDR, rutaXMLCDR,
					rutaWSCPE_PD);
		}
		return Rta;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String[] CPESunatConsultaEstadoFactura(int flg_tipo, String ruc, String UsuarioSol, String PassSol,
			String rucCliente, String tipoDocumento, String serie, String numero) throws FileNotFoundException,
			NoSuchAlgorithmException, InvalidAlgorithmParameterException, ParserConfigurationException, SAXException,
			MarshalException, KeyStoreException, IOException, CertificateException, Exception {
		String[] Rta = null;
		try {

			VarGlo.Rutas();

			String rutaWSCPE_CONFAC = VarGlo.rutaWSCPE_CONFAC;
			Rta = sunat.ConexionCPEConsultaEstadoFactura(ruc, UsuarioSol, PassSol, rucCliente, tipoDocumento, serie,
					numero, rutaWSCPE_CONFAC);

		} catch (Exception e) {
			System.err.println("ERROR: " + this.getClass().getSimpleName() + "/CPESunatConsultaEstadoFactura => "
					+ e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/CPESunatConsultaEstadoFactura" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw e;
		}

		return Rta;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public static String[] obtenerDatosXML(int flg_tipo, String ruc, _CpeBean Cpe,
			List<_Cpe_DetalleBean> lstCpe_Detalle) throws FileNotFoundException, NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, ParserConfigurationException, SAXException, MarshalException,
			KeyStoreException, IOException, CertificateException, Exception {
		String[] Rta = null;
		int flg_firma = 0;// 1=factura,boleta,nc,nd====0=retencion
		// String NombreCPE = Cpe.getNRO_DOCUMENTO_EMPRESA() + "-" +
		// Cpe.getCOD_TIPO_DOCUMENTO() + "-" + Cpe.getNRO_COMPROBANTE();
		// //"20100066603-03-BB11-750";//"20100066603-03-BB11-750.ZIP";
		String NombreCPE = ruc + "-" + Cpe.getCOD_TIPO_DOCUMENTO() + "-" + Cpe.getNRO_COMPROBANTE(); // "20100066603-03-BB11-750";//"20100066603-03-BB11-750.ZIP";
		VarGlo.Rutas();
		// CPESunat.GenerarXMLCPE_BETA2(rutaXMLCPE);
		/*
		 * 3=PRUEBA O BETA 2=HOMOLOGACION 1=PRODUCCION
		 */
		if (flg_tipo == 3) {// 3=PRUEBA O BETA
			String rutaXMLCPE = VarGlo.rutaXMLCPE_BE + File.separator + NombreCPE;// "\\20100066603-03-BB11-750";
			// String rutaZIPCDR = VarGlo.rutaXMLCDR_BE + File.separator + "R-" + NombreCPE;
			String rutaXMLCDR = VarGlo.rutaXMLCDR_BE + File.separator + "R-" + NombreCPE;
			// String rutaWSCPE_BE = VarGlo.rutaWSCPE_BE;

			Rta = sunat.obtenerDatosXML(rutaXMLCPE, rutaXMLCDR);
		}
		if (flg_tipo == 2) {// 2=HOMOLOGACION
			String rutaXMLCPE = VarGlo.rutaXMLCPE_HO + File.separator + NombreCPE;// "\\20100066603-03-BB11-750";
			String rutaXMLCDR = VarGlo.rutaXMLCDR_HO + File.separator + "R-" + NombreCPE;
			Rta = sunat.obtenerDatosXML(rutaXMLCPE, rutaXMLCDR);
		}
		if (flg_tipo == 1) {// 1=PRODUCCION
			String rutaXMLCPE = VarGlo.rutaXMLCPE_PRO + File.separator + NombreCPE;// "\\20100066603-03-BB11-750";
			// String rutaZIPCDR = VarGlo.rutaXMLCDR_PRO + File.separator + "R-" +
			// NombreCPE;
			String rutaXMLCDR = VarGlo.rutaXMLCDR_PRO + File.separator + "R-" + NombreCPE;
			Rta = sunat.obtenerDatosXML(rutaXMLCPE, rutaXMLCDR);
		}

		return Rta;
	}

	public String rutaFirma(Integer flag, String rutabase, String ruc) {
		String rutaFirma = "";
		if (flag == Constantes.codFacturacionPse) {
			rutaFirma = rutabase + separador + Constantes.carpetaPSE + separador + Constantes.rucPSE + ".pfx";
		} else {
			rutaFirma = rutabase + separador + ruc + separador + ruc + ".pfx";
		}

		return rutaFirma;
	}

	public String[] credencialesFacturacion(Integer flag, String ruc, String UsuarioSol, String PassSol,
			String passFirma) {
		String[] credenciales = { "", "", "", "" };
		if (flag == Constantes.codFacturacionPse) {
			credenciales = mapperParametros.selecValorParametroPorCodigo(Constantes.codParametroAccesosPSE).getValor()
					.split("\\|");
		} else {
			credenciales[0] = ruc;
			credenciales[1] = UsuarioSol;
			credenciales[2] = PassSol;
			credenciales[3] = passFirma;
		}
		return credenciales;
	}
	
	public _Parametros obtenerParametroCodigo(List<_Parametros> lsPar, String codigo) {
		for (int i = 0; i < lsPar.size(); i++) {
			if (lsPar.get(i).getCodigo().equals(codigo)) {
				return  lsPar.get(i);
			}
		}
		return null;
	}
}
