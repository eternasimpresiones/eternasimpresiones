/*
*ˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆ
* Creado por     : Jose Zambrano  (25/10/2016)
* Bean = Cpe
*/

package org.gteperu.erp.everest.domain;
import java.util.Date;
import java.util.List;

public class _CpeBean	 {
	private int ID;
        private String TIPO_OPERACION; //CATALOGO 17
	private String FECHA_REGISTRO;
	private int ID_EMPRESA;
	private int ID_CLIENTE_CPE;
	private double TOTAL_GRAVADAS;
	private double TOTAL_INAFECTA;
	private double TOTAL_EXONERADAS;//NUEVO UBL2.1  
	private double TOTAL_GRATUITAS;
	private double TOTAL_PERCEPCIONES;
	private double TOTAL_RETENCIONES;
	private double TOTAL_DETRACCIONES;
	private double TOTAL_BONIFICACIONES;
	private double TOTAL_DESCUENTO;
	private double SUB_TOTAL;
	private double POR_DESCUENTO_GLOBAL;
	private double DESCUENTO_GLOBAL;

        private double POR_IGV;
        private double POR_PERCEPCION;

	private double TOTAL_IGV;
	private double TOTAL_ISC;
        private double TOTAL_EXPORTACION;        
	private double TOTAL_OTR_IMP;
	private double TOTAL;
	private String TOTAL_LETRAS;
	private String NRO_GUIA_REMISION;
        private String FECHA_GUIA_REMISION;//NUEVO UBL2.1   
	private String COD_GUIA_REMISION;
	private String NRO_OTR_COMPROBANTE;
	private String COD_OTR_COMPROBANTE;
	private String TIPO_COMPROBANTE_MODIFICA;
	private String NRO_DOCUMENTO_MODIFICA;
	private String COD_TIPO_MOTIVO;
	private String DESCRIPCION_MOTIVO;
	private String NRO_COMPROBANTE;
	private String FECHA_DOCUMENTO;
        private String FECHA_VTO;//NUEVO UBL2.1        
	private String COD_TIPO_DOCUMENTO;
	private String COD_MONEDA;
	private String NRO_DOCUMENTO_CLIENTE;
	private String RAZON_SOCIAL_CLIENTE;
	private String TIPO_DOCUMENTO_CLIENTE;
        private String DIRECCION_CLIENTE;       
        private String COD_UBIGEO_CLIENTE;//NUEVO UBL2.1   
        private String DEPARTAMENTO_CLIENTE;//NUEVO UBL2.1   
        private String PROVINCIA_CLIENTE;//NUEVO UBL2.1   
        private String DISTRITO_CLIENTE;//NUEVO UBL2.1           
	private String CIUDAD_CLIENTE;
	private String COD_PAIS_CLIENTE;
	private String NRO_DOCUMENTO_EMPRESA;
	private String TIPO_DOCUMENTO_EMPRESA;
	private String NOMBRE_COMERCIAL_EMPRESA;
	private String CODIGO_UBIGEO_EMPRESA;
	private String DIRECCION_EMPRESA;
        private String CONTACTO_EMPRESA;//NUEVO UBL2.1   
	private String DEPARTAMENTO_EMPRESA;
	private String PROVINCIA_EMPRESA;
	private String DISTRITO_EMPRESA;
	private String CODIGO_PAIS_EMPRESA;
	private String RAZON_SOCIAL_EMPRESA;
	private String USUARIO_SOL_EMPRESA;
	private String PASS_SOL_EMPRESA;
	private int TIPO_PROCESO;
	private String COD_RESPUESTA_SUNAT;
	private String DESCRIPCION_RESPUESTA;
        //================anticipo=================
	private int FLG_ANTICIPO;
	private int FLG_REGU_ANTICIPO;
	private String NRO_COMPROBANTE_REF_ANT;
	private String MONEDA_REGU_ANTICIPO;
	private double MONTO_REGU_ANTICIPO;
	private String TIPO_DOCUMENTO_EMP_REGU_ANT;
	private String NRO_DOCUMENTO_EMP_REGU_ANT;
        //================fin anticipo=================
	private String ESTADO;
        private String HASH_CPE;
        private String HASH_CDR;
        private String CORREO;
        //=====================datos adicionales=====================
        private String PASS_FIRMA; //= request.getParameter("txtPASS_FIRMA") == null ? "" : request.getParameter("txtPASS_FIRMA");
        private String CONTRA; //= request.getParameter("txtCONTRA") == null ? "" : request.getParameter("txtCONTRA");
        //================DETALLE===============
        //private Cpe_Datos_ExtrasBean Datos_Extras;
        List<_Cpe_DetalleBean> Detalle;
        
	private int TIPO;
	private String dirDocumentoEmpresaEmisora;
	private String dirFirmaEmpresa;
	
	
	private String DIRECCION_ITINERANTE;
	private String PROVINCIA_ITINERANTE;
	private String DEPARTAMENTO_ITINERANTE;
	private String UBIGEO_ITINERANTE;
	private String DISTRITO_ITINERANTE;
	
	private String COD_MONEDA_ANTICIPO;
	private Integer ifacturacionPse;

	/////////////////////////////////////////////detraccion
	private String TIPO_DETRACCION;
	private Double PORCENTAJE_DETRACCION;
	
	//////forma de pago
	private String COD_TIPO_PAGO;
	private Integer NRO_CUOTAS;
	private List<Cuota> LSCUOTAS;
	
	public int getID() {
		return ID;
	}
	
	public double getPOR_PERCEPCION() {
		return POR_PERCEPCION;
	}



	public String getCOD_MONEDA_ANTICIPO() {
		return COD_MONEDA_ANTICIPO;
	}

	public void setCOD_MONEDA_ANTICIPO(String cOD_MONEDA_ANTICIPO) {
		COD_MONEDA_ANTICIPO = cOD_MONEDA_ANTICIPO;
	}

	public String getDIRECCION_ITINERANTE() {
		return DIRECCION_ITINERANTE;
	}

	public void setDIRECCION_ITINERANTE(String dIRECCION_ITINERANTE) {
		DIRECCION_ITINERANTE = dIRECCION_ITINERANTE;
	}

	public String getPROVINCIA_ITINERANTE() {
		return PROVINCIA_ITINERANTE;
	}

	public void setPROVINCIA_ITINERANTE(String pROVINCIA_ITINERANTE) {
		PROVINCIA_ITINERANTE = pROVINCIA_ITINERANTE;
	}

	public String getDEPARTAMENTO_ITINERANTE() {
		return DEPARTAMENTO_ITINERANTE;
	}

	public void setDEPARTAMENTO_ITINERANTE(String dEPARTAMENTO_ITINERANTE) {
		DEPARTAMENTO_ITINERANTE = dEPARTAMENTO_ITINERANTE;
	}

	public String getUBIGEO_ITINERANTE() {
		return UBIGEO_ITINERANTE;
	}

	public void setUBIGEO_ITINERANTE(String uBIGEO_ITINERANTE) {
		UBIGEO_ITINERANTE = uBIGEO_ITINERANTE;
	}

	public String getDISTRITO_ITINERANTE() {
		return DISTRITO_ITINERANTE;
	}

	public void setDISTRITO_ITINERANTE(String dISTRITO_ITINERANTE) {
		DISTRITO_ITINERANTE = dISTRITO_ITINERANTE;
	}

	public void setPOR_PERCEPCION(double pOR_PERCEPCION) {
		POR_PERCEPCION = pOR_PERCEPCION;
	}



	public double getDESCUENTO_GLOBAL() {
		return DESCUENTO_GLOBAL;
	}

	public void setDESCUENTO_GLOBAL(double dESCUENTO_GLOBAL) {
		DESCUENTO_GLOBAL = dESCUENTO_GLOBAL;
	}

	public double getPOR_DESCUENTO_GLOBAL() {
		return POR_DESCUENTO_GLOBAL;
	}
	public void setPOR_DESCUENTO_GLOBAL(double POR_DESCUENTO_GLOBAL) {
		this.POR_DESCUENTO_GLOBAL = POR_DESCUENTO_GLOBAL;
	}
	public String getDirDocumentoEmpresaEmisora() {
		return dirDocumentoEmpresaEmisora;
	}
	public void setDirDocumentoEmpresaEmisora(String dirDocumentoEmpresaEmisora) {
		this.dirDocumentoEmpresaEmisora = dirDocumentoEmpresaEmisora;
	}
	public void setID(int  ID) {
		this.ID = ID;
	}
        /////////////////////////////////////////////
	public String getTIPO_OPERACION() {
		return TIPO_OPERACION;
	}
	public void setTIPO_OPERACION(String TIPO_OPERACION) {
		this.TIPO_OPERACION = TIPO_OPERACION;
	}
	/////////////////////////////////////////////
	public String getFECHA_REGISTRO() {
		return FECHA_REGISTRO;
	}
	public void setFECHA_REGISTRO(String  FECHA_REGISTRO) {
		this.FECHA_REGISTRO = FECHA_REGISTRO;
	}
	/////////////////////////////////////////////
	public int getID_EMPRESA() {
		return ID_EMPRESA;
	}
	public void setID_EMPRESA(int  ID_EMPRESA) {
		this.ID_EMPRESA = ID_EMPRESA;
	}
	/////////////////////////////////////////////
	public int getID_CLIENTE_CPE() {
		return ID_CLIENTE_CPE;
	}
	public void setID_CLIENTE_CPE(int  ID_CLIENTE_CPE) {
		this.ID_CLIENTE_CPE = ID_CLIENTE_CPE;
	}
	/////////////////////////////////////////////
	public double getTOTAL_GRAVADAS() {
		return TOTAL_GRAVADAS;
	}
	public void setTOTAL_GRAVADAS(double  TOTAL_GRAVADAS) {
		this.TOTAL_GRAVADAS = TOTAL_GRAVADAS;
	}
	/////////////////////////////////////////////
	public double getTOTAL_INAFECTA() {
		return TOTAL_INAFECTA;
	}
	public void setTOTAL_INAFECTA(double  TOTAL_INAFECTA) {
		this.TOTAL_INAFECTA = TOTAL_INAFECTA;
	}
	/////////////////////////////////////////////
	public double getTOTAL_EXONERADAS() {
		return TOTAL_EXONERADAS;
	}
	public void setTOTAL_EXONERADAS(double  TOTAL_EXONERADAS) {
		this.TOTAL_EXONERADAS = TOTAL_EXONERADAS;
	}
	/////////////////////////////////////////////
	public double getTOTAL_GRATUITAS() {
		return TOTAL_GRATUITAS;
	}
	public void setTOTAL_GRATUITAS(double  TOTAL_GRATUITAS) {
		this.TOTAL_GRATUITAS = TOTAL_GRATUITAS;
	}
	/////////////////////////////////////////////
	public double getTOTAL_PERCEPCIONES() {
		return TOTAL_PERCEPCIONES;
	}
	public void setTOTAL_PERCEPCIONES(double  TOTAL_PERCEPCIONES) {
		this.TOTAL_PERCEPCIONES = TOTAL_PERCEPCIONES;
	}
	/////////////////////////////////////////////
	public double getTOTAL_RETENCIONES() {
		return TOTAL_RETENCIONES;
	}
	public void setTOTAL_RETENCIONES(double  TOTAL_RETENCIONES) {
		this.TOTAL_RETENCIONES = TOTAL_RETENCIONES;
	}
	/////////////////////////////////////////////
	public double getTOTAL_DETRACCIONES() {
		return TOTAL_DETRACCIONES;
	}
	public void setTOTAL_DETRACCIONES(double  TOTAL_DETRACCIONES) {
		this.TOTAL_DETRACCIONES = TOTAL_DETRACCIONES;
	}
	/////////////////////////////////////////////
	public double getTOTAL_BONIFICACIONES() {
		return TOTAL_BONIFICACIONES;
	}
	public void setTOTAL_BONIFICACIONES(double  TOTAL_BONIFICACIONES) {
		this.TOTAL_BONIFICACIONES = TOTAL_BONIFICACIONES;
	}
	/////////////////////////////////////////////
	public double getTOTAL_DESCUENTO() {
		return TOTAL_DESCUENTO;
	}
	public void setTOTAL_DESCUENTO(double  TOTAL_DESCUENTO) {
		this.TOTAL_DESCUENTO = TOTAL_DESCUENTO;
	}
	/////////////////////////////////////////////
	public double getSUB_TOTAL() {
		return SUB_TOTAL;
	}
	public void setSUB_TOTAL(double  SUB_TOTAL) {
		this.SUB_TOTAL = SUB_TOTAL;
	}
        /////////////////////////////////////////////
	public double getPOR_IGV() {
		return POR_IGV;
	}
	public void setPOR_IGV(double POR_IGV) {
		this.POR_IGV = POR_IGV;
	}
	/////////////////////////////////////////////
	public double getTOTAL_IGV() {
		return TOTAL_IGV;
	}
	public void setTOTAL_IGV(double  TOTAL_IGV) {
		this.TOTAL_IGV = TOTAL_IGV;
	}
	/////////////////////////////////////////////
	public double getTOTAL_ISC() {
		return TOTAL_ISC;
	}
	public void setTOTAL_ISC(double  TOTAL_ISC) {
		this.TOTAL_ISC = TOTAL_ISC;
	}
        /////////////////////////////////////////////
	public double getTOTAL_EXPORTACION() {
		return TOTAL_EXPORTACION;
	}
	public void setTOTAL_EXPORTACION(double TOTAL_EXPORTACION) {
		this.TOTAL_EXPORTACION = TOTAL_EXPORTACION;
	}
	/////////////////////////////////////////////
	public double getTOTAL_OTR_IMP() {
		return TOTAL_OTR_IMP;
	}
	public void setTOTAL_OTR_IMP(double  TOTAL_OTR_IMP) {
		this.TOTAL_OTR_IMP = TOTAL_OTR_IMP;
	}
	/////////////////////////////////////////////
	public double getTOTAL() {
		return TOTAL;
	}
	public void setTOTAL(double  TOTAL) {
		this.TOTAL = TOTAL;
	}
	/////////////////////////////////////////////
	public String getTOTAL_LETRAS() {
		return TOTAL_LETRAS;
	}
	public void setTOTAL_LETRAS(String  TOTAL_LETRAS) {
		this.TOTAL_LETRAS = TOTAL_LETRAS;
	}
	/////////////////////////////////////////////
	public String getNRO_GUIA_REMISION() {
		return NRO_GUIA_REMISION;
	}
	public void setNRO_GUIA_REMISION(String  NRO_GUIA_REMISION) {
		this.NRO_GUIA_REMISION = NRO_GUIA_REMISION;
	}
        /////////////////////////////////////////////
	public String getFECHA_GUIA_REMISION() {
		return FECHA_GUIA_REMISION;
	}
	public void setFECHA_GUIA_REMISION(String  FECHA_GUIA_REMISION) {
		this.FECHA_GUIA_REMISION = FECHA_GUIA_REMISION;
	}        
	/////////////////////////////////////////////
	public String getCOD_GUIA_REMISION() {
		return COD_GUIA_REMISION;
	}
	public void setCOD_GUIA_REMISION(String  COD_GUIA_REMISION) {
		this.COD_GUIA_REMISION = COD_GUIA_REMISION;
	}
	/////////////////////////////////////////////
	public String getNRO_OTR_COMPROBANTE() {
		return NRO_OTR_COMPROBANTE;
	}
	public void setNRO_OTR_COMPROBANTE(String  NRO_OTR_COMPROBANTE) {
		this.NRO_OTR_COMPROBANTE = NRO_OTR_COMPROBANTE;
	}
	/////////////////////////////////////////////
	public String getCOD_OTR_COMPROBANTE() {
		return COD_OTR_COMPROBANTE;
	}
	public void setCOD_OTR_COMPROBANTE(String  COD_OTR_COMPROBANTE) {
		this.COD_OTR_COMPROBANTE = COD_OTR_COMPROBANTE;
	}
	/////////////////////////////////////////////
	public String getTIPO_COMPROBANTE_MODIFICA() {
		return TIPO_COMPROBANTE_MODIFICA;
	}
	public void setTIPO_COMPROBANTE_MODIFICA(String  TIPO_COMPROBANTE_MODIFICA) {
		this.TIPO_COMPROBANTE_MODIFICA = TIPO_COMPROBANTE_MODIFICA;
	}
	/////////////////////////////////////////////
	public String getNRO_DOCUMENTO_MODIFICA() {
		return NRO_DOCUMENTO_MODIFICA;
	}
	public void setNRO_DOCUMENTO_MODIFICA(String  NRO_DOCUMENTO_MODIFICA) {
		this.NRO_DOCUMENTO_MODIFICA = NRO_DOCUMENTO_MODIFICA;
	}
	/////////////////////////////////////////////
	public String getCOD_TIPO_MOTIVO() {
		return COD_TIPO_MOTIVO;
	}
	public void setCOD_TIPO_MOTIVO(String  COD_TIPO_MOTIVO) {
		this.COD_TIPO_MOTIVO = COD_TIPO_MOTIVO;
	}
	/////////////////////////////////////////////
	public String getDESCRIPCION_MOTIVO() {
		return DESCRIPCION_MOTIVO;
	}
	public void setDESCRIPCION_MOTIVO(String  DESCRIPCION_MOTIVO) {
		this.DESCRIPCION_MOTIVO = DESCRIPCION_MOTIVO;
	}
	/////////////////////////////////////////////
	public String getNRO_COMPROBANTE() {
		return NRO_COMPROBANTE;
	}
	public void setNRO_COMPROBANTE(String  NRO_COMPROBANTE) {
		this.NRO_COMPROBANTE = NRO_COMPROBANTE;
	}
	/////////////////////////////////////////////
	public String getFECHA_DOCUMENTO() {
		return FECHA_DOCUMENTO;
	}
	public void setFECHA_DOCUMENTO(String  FECHA_DOCUMENTO) {
		this.FECHA_DOCUMENTO = FECHA_DOCUMENTO;
	}
        /////////////////////////////////////////////
	public String getFECHA_VTO() {
		return FECHA_VTO;
	}
	public void setFECHA_VTO(String  FECHA_VTO) {
		this.FECHA_VTO = FECHA_VTO;
	}
	/////////////////////////////////////////////
	public String getCOD_TIPO_DOCUMENTO() {
		return COD_TIPO_DOCUMENTO;
	}
	public void setCOD_TIPO_DOCUMENTO(String  COD_TIPO_DOCUMENTO) {
		this.COD_TIPO_DOCUMENTO = COD_TIPO_DOCUMENTO;
	}
	/////////////////////////////////////////////
	public String getCOD_MONEDA() {
		return COD_MONEDA;
	}
	public void setCOD_MONEDA(String  COD_MONEDA) {
		this.COD_MONEDA = COD_MONEDA;
	}
	/////////////////////////////////////////////
	public String getNRO_DOCUMENTO_CLIENTE() {
		return NRO_DOCUMENTO_CLIENTE;
	}
	public void setNRO_DOCUMENTO_CLIENTE(String  NRO_DOCUMENTO_CLIENTE) {
		this.NRO_DOCUMENTO_CLIENTE = NRO_DOCUMENTO_CLIENTE;
	}
	/////////////////////////////////////////////
	public String getRAZON_SOCIAL_CLIENTE() {
		return RAZON_SOCIAL_CLIENTE;
	}
	public void setRAZON_SOCIAL_CLIENTE(String  RAZON_SOCIAL_CLIENTE) {
		this.RAZON_SOCIAL_CLIENTE = RAZON_SOCIAL_CLIENTE;
	}
        	/////////////////////////////////////////////
	public String getCOD_UBIGEO_CLIENTE() {
		return COD_UBIGEO_CLIENTE;
	}
	public void setCOD_UBIGEO_CLIENTE(String  COD_UBIGEO_CLIENTE) {
		this.COD_UBIGEO_CLIENTE = COD_UBIGEO_CLIENTE;
	}
        /////////////////////////////////////////////
	public String getDEPARTAMENTO_CLIENTE() {
		return DEPARTAMENTO_CLIENTE;
	}
	public void setDEPARTAMENTO_CLIENTE(String DEPARTAMENTO_CLIENTE) {
		this.DEPARTAMENTO_CLIENTE = DEPARTAMENTO_CLIENTE;
	}
        /////////////////////////////////////////////
	public String getPROVINCIA_CLIENTE() {
		return PROVINCIA_CLIENTE;
	}
	public void setPROVINCIA_CLIENTE(String PROVINCIA_CLIENTE) {
		this.PROVINCIA_CLIENTE = PROVINCIA_CLIENTE;
	}
        /////////////////////////////////////////////
	public String getDISTRITO_CLIENTE() {
		return DISTRITO_CLIENTE;
	}
	public void setDISTRITO_CLIENTE(String DISTRITO_CLIENTE) {
		this.DISTRITO_CLIENTE = DISTRITO_CLIENTE;
	}
	/////////////////////////////////////////////
	public String getTIPO_DOCUMENTO_CLIENTE() {
		return TIPO_DOCUMENTO_CLIENTE;
	}
	public void setTIPO_DOCUMENTO_CLIENTE(String  TIPO_DOCUMENTO_CLIENTE) {
		this.TIPO_DOCUMENTO_CLIENTE = TIPO_DOCUMENTO_CLIENTE;
	}
        /////////////////////////////////////////////
	public String getDIRECCION_CLIENTE() {
		return DIRECCION_CLIENTE;
	}
	public void setDIRECCION_CLIENTE(String DIRECCION_CLIENTE) {
		this.DIRECCION_CLIENTE = DIRECCION_CLIENTE;
	}
	/////////////////////////////////////////////
	public String getCIUDAD_CLIENTE() {
		return CIUDAD_CLIENTE;
	}
	public void setCIUDAD_CLIENTE(String  CIUDAD_CLIENTE) {
		this.CIUDAD_CLIENTE = CIUDAD_CLIENTE;
	}
	/////////////////////////////////////////////
	public String getCOD_PAIS_CLIENTE() {
		return COD_PAIS_CLIENTE;
	}
	public void setCOD_PAIS_CLIENTE(String  COD_PAIS_CLIENTE) {
		this.COD_PAIS_CLIENTE = COD_PAIS_CLIENTE;
	}
	/////////////////////////////////////////////
	public String getNRO_DOCUMENTO_EMPRESA() {
		return NRO_DOCUMENTO_EMPRESA;
	}
	public void setNRO_DOCUMENTO_EMPRESA(String  NRO_DOCUMENTO_EMPRESA) {
		this.NRO_DOCUMENTO_EMPRESA = NRO_DOCUMENTO_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getTIPO_DOCUMENTO_EMPRESA() {
		return TIPO_DOCUMENTO_EMPRESA;
	}
	public void setTIPO_DOCUMENTO_EMPRESA(String  TIPO_DOCUMENTO_EMPRESA) {
		this.TIPO_DOCUMENTO_EMPRESA = TIPO_DOCUMENTO_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getNOMBRE_COMERCIAL_EMPRESA() {
		return NOMBRE_COMERCIAL_EMPRESA;
	}
	public void setNOMBRE_COMERCIAL_EMPRESA(String  NOMBRE_COMERCIAL_EMPRESA) {
		this.NOMBRE_COMERCIAL_EMPRESA = NOMBRE_COMERCIAL_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getCODIGO_UBIGEO_EMPRESA() {
		return CODIGO_UBIGEO_EMPRESA;
	}
	public void setCODIGO_UBIGEO_EMPRESA(String  CODIGO_UBIGEO_EMPRESA) {
		this.CODIGO_UBIGEO_EMPRESA = CODIGO_UBIGEO_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getDIRECCION_EMPRESA() {
		return DIRECCION_EMPRESA;
	}
	public void setDIRECCION_EMPRESA(String  DIRECCION_EMPRESA) {
		this.DIRECCION_EMPRESA = DIRECCION_EMPRESA;
	}
        /////////////////////////////////////////////
	public String getCONTACTO_EMPRESA() {
		return CONTACTO_EMPRESA;
	}
	public void setCONTACTO_EMPRESA(String CONTACTO_EMPRESA) {
		this.CONTACTO_EMPRESA = CONTACTO_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getDEPARTAMENTO_EMPRESA() {
		return DEPARTAMENTO_EMPRESA;
	}
	public void setDEPARTAMENTO_EMPRESA(String  DEPARTAMENTO_EMPRESA) {
		this.DEPARTAMENTO_EMPRESA = DEPARTAMENTO_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getPROVINCIA_EMPRESA() {
		return PROVINCIA_EMPRESA;
	}
	public void setPROVINCIA_EMPRESA(String  PROVINCIA_EMPRESA) {
		this.PROVINCIA_EMPRESA = PROVINCIA_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getDISTRITO_EMPRESA() {
		return DISTRITO_EMPRESA;
	}
	public void setDISTRITO_EMPRESA(String  DISTRITO_EMPRESA) {
		this.DISTRITO_EMPRESA = DISTRITO_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getCODIGO_PAIS_EMPRESA() {
		return CODIGO_PAIS_EMPRESA;
	}
	public void setCODIGO_PAIS_EMPRESA(String  CODIGO_PAIS_EMPRESA) {
		this.CODIGO_PAIS_EMPRESA = CODIGO_PAIS_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getRAZON_SOCIAL_EMPRESA() {
		return RAZON_SOCIAL_EMPRESA;
	}
	public void setRAZON_SOCIAL_EMPRESA(String  RAZON_SOCIAL_EMPRESA) {
		this.RAZON_SOCIAL_EMPRESA = RAZON_SOCIAL_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getUSUARIO_SOL_EMPRESA() {
		return USUARIO_SOL_EMPRESA;
	}
	public void setUSUARIO_SOL_EMPRESA(String  USUARIO_SOL_EMPRESA) {
		this.USUARIO_SOL_EMPRESA = USUARIO_SOL_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getPASS_SOL_EMPRESA() {
		return PASS_SOL_EMPRESA;
	}
	public void setPASS_SOL_EMPRESA(String  PASS_SOL_EMPRESA) {
		this.PASS_SOL_EMPRESA = PASS_SOL_EMPRESA;
	}
	/////////////////////////////////////////////
	public int getTIPO_PROCESO() {
		return TIPO_PROCESO;
	}
	public void setTIPO_PROCESO(int  TIPO_PROCESO) {
		this.TIPO_PROCESO = TIPO_PROCESO;
	}
	/////////////////////////////////////////////
	public String getCOD_RESPUESTA_SUNAT() {
		return COD_RESPUESTA_SUNAT;
	}
	public void setCOD_RESPUESTA_SUNAT(String  COD_RESPUESTA_SUNAT) {
		this.COD_RESPUESTA_SUNAT = COD_RESPUESTA_SUNAT;
	}
	/////////////////////////////////////////////
	public String getDESCRIPCION_RESPUESTA() {
		return DESCRIPCION_RESPUESTA;
	}
	public void setDESCRIPCION_RESPUESTA(String  DESCRIPCION_RESPUESTA) {
		this.DESCRIPCION_RESPUESTA = DESCRIPCION_RESPUESTA;
	}
	/////////////////////////////////////////////
	public int getFLG_ANTICIPO() {
		return FLG_ANTICIPO;
	}
	public void setFLG_ANTICIPO(int  FLG_ANTICIPO) {
		this.FLG_ANTICIPO = FLG_ANTICIPO;
	}
	/////////////////////////////////////////////
	public int getFLG_REGU_ANTICIPO() {
		return FLG_REGU_ANTICIPO;
	}
	public void setFLG_REGU_ANTICIPO(int  FLG_REGU_ANTICIPO) {
		this.FLG_REGU_ANTICIPO = FLG_REGU_ANTICIPO;
	}
	/////////////////////////////////////////////
	public String getNRO_COMPROBANTE_REF_ANT() {
		return NRO_COMPROBANTE_REF_ANT;
	}
	public void setNRO_COMPROBANTE_REF_ANT(String  NRO_COMPROBANTE_REF_ANT) {
		this.NRO_COMPROBANTE_REF_ANT = NRO_COMPROBANTE_REF_ANT;
	}
	/////////////////////////////////////////////
	public String getMONEDA_REGU_ANTICIPO() {
		return MONEDA_REGU_ANTICIPO;
	}
	public void setMONEDA_REGU_ANTICIPO(String  MONEDA_REGU_ANTICIPO) {
		this.MONEDA_REGU_ANTICIPO = MONEDA_REGU_ANTICIPO;
	}
	/////////////////////////////////////////////
	public double getMONTO_REGU_ANTICIPO() {
		return MONTO_REGU_ANTICIPO;
	}
	public void setMONTO_REGU_ANTICIPO(double  MONTO_REGU_ANTICIPO) {
		this.MONTO_REGU_ANTICIPO = MONTO_REGU_ANTICIPO;
	}
	/////////////////////////////////////////////
	public String getTIPO_DOCUMENTO_EMP_REGU_ANT() {
		return TIPO_DOCUMENTO_EMP_REGU_ANT;
	}
	public void setTIPO_DOCUMENTO_EMP_REGU_ANT(String  TIPO_DOCUMENTO_EMP_REGU_ANT) {
		this.TIPO_DOCUMENTO_EMP_REGU_ANT = TIPO_DOCUMENTO_EMP_REGU_ANT;
	}
	/////////////////////////////////////////////
	public String getNRO_DOCUMENTO_EMP_REGU_ANT() {
		return NRO_DOCUMENTO_EMP_REGU_ANT;
	}
	public void setNRO_DOCUMENTO_EMP_REGU_ANT(String  NRO_DOCUMENTO_EMP_REGU_ANT) {
		this.NRO_DOCUMENTO_EMP_REGU_ANT = NRO_DOCUMENTO_EMP_REGU_ANT;
	}
	/////////////////////////////////////////////
	public String getESTADO() {
		return ESTADO;
	}
	public void setESTADO(String  ESTADO) {
		this.ESTADO = ESTADO;
	}
        /////////////////////////////////////////////
	public String getHASH_CPE() {
		return HASH_CPE;
	}
	public void setHASH_CPE(String HASH_CPE) {
		this.HASH_CPE = HASH_CPE;
	}
        /////////////////////////////////////////////
	public String getHASH_CDR() {
		return HASH_CDR;
	}
	public void setHASH_CDR(String HASH_CDR) {
		this.HASH_CDR = HASH_CDR;
	}
        /////////////////////////////////////////////
	public String getCORREO() {
		return CORREO;
	}
	public void setCORREO(String CORREO) {
		this.CORREO = CORREO;
	}
        //=====================datos adicionales=====================
        /////////////////////////////////////////////
	public String getPASS_FIRMA() {
		return PASS_FIRMA;
	}
	public void setPASS_FIRMA(String PASS_FIRMA) {
		this.PASS_FIRMA = PASS_FIRMA;
	}
        /////////////////////////////////////////////
	public String getCONTRA() {
		return CONTRA;
	}
	public void setCONTRA(String CONTRA) {
		this.CONTRA = CONTRA;
	}
        /////////////////////////////////////////////
//	public Cpe_Datos_ExtrasBean getDatos_Extras() {
//		return Datos_Extras;
//	}
//	public void setDatos_Extras(Cpe_Datos_ExtrasBean Datos_Extras) {
//		this.Datos_Extras = Datos_Extras;
//	}
        ///////////////////DETALLE//////////////////////////
	public List<_Cpe_DetalleBean> getDetalle() {
		return Detalle;
	}
	public void setDetalle(List<_Cpe_DetalleBean> Detalle) {
		this.Detalle = Detalle;
	}
        
	/////////////////////////////////////////////
	///////////////////campos aumentados//////////////////////////
	public int getTIPO() {
		return TIPO;
	}
	public void setTIPO(int TIPO) {
		this.TIPO = TIPO;
	}

	public String getDirFirmaEmpresa() {
		return dirFirmaEmpresa;
	}

	public void setDirFirmaEmpresa(String dirFirmaEmpresa) {
		this.dirFirmaEmpresa = dirFirmaEmpresa;
	}
	

	public Integer getIfacturacionPse() {
		return ifacturacionPse;
	}

	public void setIfacturacionPse(Integer ifacturacionPse) {
		this.ifacturacionPse = ifacturacionPse;
	}

	@Override
	public String toString() {
		return "_CpeBean [ID=" + ID + "\nTIPO_OPERACION=" + TIPO_OPERACION + "\nFECHA_REGISTRO=" + FECHA_REGISTRO
				+ "\nID_EMPRESA=" + ID_EMPRESA + "\nID_CLIENTE_CPE=" + ID_CLIENTE_CPE + "\nTOTAL_GRAVADAS="
				+ TOTAL_GRAVADAS + "\nTOTAL_INAFECTA=" + TOTAL_INAFECTA + "\nTOTAL_EXONERADAS=" + TOTAL_EXONERADAS
				+ "\nTOTAL_GRATUITAS=" + TOTAL_GRATUITAS + "\nTOTAL_PERCEPCIONES=" + TOTAL_PERCEPCIONES
				+ "\nTOTAL_RETENCIONES=" + TOTAL_RETENCIONES + "\nTOTAL_DETRACCIONES=" + TOTAL_DETRACCIONES
				+ "\nTOTAL_BONIFICACIONES=" + TOTAL_BONIFICACIONES + "\nTOTAL_DESCUENTO=" + TOTAL_DESCUENTO
				+ "\nSUB_TOTAL=" + SUB_TOTAL + "\nPOR_DESCUENTO_GLOBAL=" + POR_DESCUENTO_GLOBAL + "\nDESCUENTO_GLOBAL="
				+ DESCUENTO_GLOBAL + "\nPOR_IGV=" + POR_IGV + "\nPOR_PERCEPCION=" + POR_PERCEPCION + "\nTOTAL_IGV="
				+ TOTAL_IGV + "\nTOTAL_ISC=" + TOTAL_ISC + "\nTOTAL_EXPORTACION=" + TOTAL_EXPORTACION
				+ "\nTOTAL_OTR_IMP=" + TOTAL_OTR_IMP + "\nTOTAL=" + TOTAL + "\nTOTAL_LETRAS=" + TOTAL_LETRAS
				+ "\nNRO_GUIA_REMISION=" + NRO_GUIA_REMISION + "\nFECHA_GUIA_REMISION=" + FECHA_GUIA_REMISION
				+ "\nCOD_GUIA_REMISION=" + COD_GUIA_REMISION + "\nNRO_OTR_COMPROBANTE=" + NRO_OTR_COMPROBANTE
				+ "\nCOD_OTR_COMPROBANTE=" + COD_OTR_COMPROBANTE + "\nTIPO_COMPROBANTE_MODIFICA="
				+ TIPO_COMPROBANTE_MODIFICA + "\nNRO_DOCUMENTO_MODIFICA=" + NRO_DOCUMENTO_MODIFICA
				+ "\nCOD_TIPO_MOTIVO=" + COD_TIPO_MOTIVO + "\nDESCRIPCION_MOTIVO=" + DESCRIPCION_MOTIVO
				+ "\nNRO_COMPROBANTE=" + NRO_COMPROBANTE + "\nFECHA_DOCUMENTO=" + FECHA_DOCUMENTO + "\nFECHA_VTO="
				+ FECHA_VTO + "\nCOD_TIPO_DOCUMENTO=" + COD_TIPO_DOCUMENTO + "\nCOD_MONEDA=" + COD_MONEDA
				+ "\nNRO_DOCUMENTO_CLIENTE=" + NRO_DOCUMENTO_CLIENTE + "\nRAZON_SOCIAL_CLIENTE=" + RAZON_SOCIAL_CLIENTE
				+ "\nTIPO_DOCUMENTO_CLIENTE=" + TIPO_DOCUMENTO_CLIENTE + "\nDIRECCION_CLIENTE=" + DIRECCION_CLIENTE
				+ "\nCOD_UBIGEO_CLIENTE=" + COD_UBIGEO_CLIENTE + "\nDEPARTAMENTO_CLIENTE=" + DEPARTAMENTO_CLIENTE
				+ "\n, PROVINCIA_CLIENTE=" + PROVINCIA_CLIENTE + "\nDISTRITO_CLIENTE=" + DISTRITO_CLIENTE
				+ "\nCIUDAD_CLIENTE=" + CIUDAD_CLIENTE + "\nCOD_PAIS_CLIENTE=" + COD_PAIS_CLIENTE
				+ "\nNRO_DOCUMENTO_EMPRESA=" + NRO_DOCUMENTO_EMPRESA + "\nTIPO_DOCUMENTO_EMPRESA="
				+ TIPO_DOCUMENTO_EMPRESA + "\nNOMBRE_COMERCIAL_EMPRESA=" + NOMBRE_COMERCIAL_EMPRESA
				+ "\nCODIGO_UBIGEO_EMPRESA=" + CODIGO_UBIGEO_EMPRESA + "\nDIRECCION_EMPRESA=" + DIRECCION_EMPRESA
				+ "\nCONTACTO_EMPRESA=" + CONTACTO_EMPRESA + "\nDEPARTAMENTO_EMPRESA=" + DEPARTAMENTO_EMPRESA
				+ "\nPROVINCIA_EMPRESA=" + PROVINCIA_EMPRESA + "\nDISTRITO_EMPRESA=" + DISTRITO_EMPRESA
				+ "\nCODIGO_PAIS_EMPRESA=" + CODIGO_PAIS_EMPRESA + "\nRAZON_SOCIAL_EMPRESA=" + RAZON_SOCIAL_EMPRESA
				+ "\nUSUARIO_SOL_EMPRESA=" + USUARIO_SOL_EMPRESA + "\nPASS_SOL_EMPRESA=" + PASS_SOL_EMPRESA
				+ "\nTIPO_PROCESO=" + TIPO_PROCESO + "\nCOD_RESPUESTA_SUNAT=" + COD_RESPUESTA_SUNAT
				+ "\nDESCRIPCION_RESPUESTA=" + DESCRIPCION_RESPUESTA + "\nFLG_ANTICIPO=" + FLG_ANTICIPO
				+ "\nFLG_REGU_ANTICIPO=" + FLG_REGU_ANTICIPO + "\nNRO_COMPROBANTE_REF_ANT=" + NRO_COMPROBANTE_REF_ANT
				+ "\nMONEDA_REGU_ANTICIPO=" + MONEDA_REGU_ANTICIPO + "\nMONTO_REGU_ANTICIPO=" + MONTO_REGU_ANTICIPO
				+ "\nTIPO_DOCUMENTO_EMP_REGU_ANT=" + TIPO_DOCUMENTO_EMP_REGU_ANT + "\nNRO_DOCUMENTO_EMP_REGU_ANT="
				+ NRO_DOCUMENTO_EMP_REGU_ANT + "\nESTADO=" + ESTADO + "\nHASH_CPE=" + HASH_CPE + "\nHASH_CDR="
				+ HASH_CDR + ", CORREO=" + CORREO + "\nPASS_FIRMA=" + PASS_FIRMA + "\nCONTRA=" + CONTRA + "\nDetalle="
				+ Detalle + "\nTIPO=" + TIPO + "\ndirDocumentoEmpresaEmisora=" + dirDocumentoEmpresaEmisora
				+ "\nDIRECCION_ITINERANTE=" + DIRECCION_ITINERANTE + "\nPROVINCIA_ITINERANTE=" + PROVINCIA_ITINERANTE
				+ "\nDEPARTAMENTO_ITINERANTE=" + DEPARTAMENTO_ITINERANTE + "\nUBIGEO_ITINERANTE=" + UBIGEO_ITINERANTE
				+ "\nDISTRITO_ITINERANTE=" + DISTRITO_ITINERANTE + "\nCOD_MONEDA_ANTICIPO=" + COD_MONEDA_ANTICIPO + "]";
	}

	public String getTIPO_DETRACCION() {
		return TIPO_DETRACCION;
	}

	public void setTIPO_DETRACCION(String tIPO_DETRACCION) {
		TIPO_DETRACCION = tIPO_DETRACCION;
	}

	public Double getPORCENTAJE_DETRACCION() {
		return PORCENTAJE_DETRACCION;
	}

	public void setPORCENTAJE_DETRACCION(Double pORCENTAJE_DETRACCION) {
		PORCENTAJE_DETRACCION = pORCENTAJE_DETRACCION;
	}

	public String getCOD_TIPO_PAGO() {
		return COD_TIPO_PAGO;
	}

	public void setCOD_TIPO_PAGO(String cOD_TIPO_PAGO) {
		COD_TIPO_PAGO = cOD_TIPO_PAGO;
	}

	public Integer getNRO_CUOTAS() {
		return NRO_CUOTAS;
	}

	public void setNRO_CUOTAS(Integer nRO_CUOTAS) {
		NRO_CUOTAS = nRO_CUOTAS;
	}

	public List<Cuota> getLSCUOTAS() {
		return LSCUOTAS;
	}

	public void setLSCUOTAS(List<Cuota> lSCUOTAS) {
		LSCUOTAS = lSCUOTAS;
	}

	

	
	
	
	
}