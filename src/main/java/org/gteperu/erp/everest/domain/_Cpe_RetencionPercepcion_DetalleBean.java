/*
*ˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆ
* Creado por     : Jose Zambrano  (08/12/2016)
* Bean = Cpe_Retencion_Detalle
*/

package org.gteperu.erp.everest.domain;
import java.util.Date;

public class _Cpe_RetencionPercepcion_DetalleBean {
	private int id__retencion_detalle;
	private int id_retencion;

	private int ID_CABECERA;
	private String COD_TIPO_DOCUMENTO;
	private String NRO_DOCUMENTO;
	private String FECHA_DOCUMENTO;
	private String COD_MONEDA;
	private double MONTO_TOTAL;
	private String FECHA_PAGO;
	private String NRO_DOC_PAGO;
	private String COD_MONEDA_PAGO;
	private double MONTO_PAGO;
	//
	private String FECHA_COBRO;
	private String NRO_DOC_COBRO;
	private String COD_MONEDA_COBRO;
	private double MONTO_COBRO;
	private String FECHA_PERCEPCION;
	private String MONEDA_PERCIBIDO;
	private double MONTO_PERCIBIDO;
	private String MONEDA_COBRO_NETO;
	private double MONTO_COBRO_NETO;
	//
	private String FECHA_RETENIDA;
	private String MONEDA_RETENIDA;
	private double MONTO_RETENIDO;
	private String MONEDA_PAGO_NETO;
	private double MONTO_PAGO_NETO;
	private String MONEDA_ORIGEN_TC;
	private String MONEDA_DESTINO_TC;
	private double PORCENTAJE_TC;
	private String FECHA_TC;
	private int TIPO;
	/////////////////////////////////////////////

	/////////////////////////////////////////////
	
	
	
	
	
	public int getID_CABECERA() {
		return ID_CABECERA;
	}
	public String getFECHA_COBRO() {
		return FECHA_COBRO;
	}
	public void setFECHA_COBRO(String fECHA_COBRO) {
		FECHA_COBRO = fECHA_COBRO;
	}
	public String getNRO_DOC_COBRO() {
		return NRO_DOC_COBRO;
	}
	public void setNRO_DOC_COBRO(String nRO_DOC_COBRO) {
		NRO_DOC_COBRO = nRO_DOC_COBRO;
	}
	public String getCOD_MONEDA_COBRO() {
		return COD_MONEDA_COBRO;
	}
	public void setCOD_MONEDA_COBRO(String cOD_MONEDA_COBRO) {
		COD_MONEDA_COBRO = cOD_MONEDA_COBRO;
	}
	public double getMONTO_COBRO() {
		return MONTO_COBRO;
	}
	public void setMONTO_COBRO(double mONTO_COBRO) {
		MONTO_COBRO = mONTO_COBRO;
	}
	public String getFECHA_PERCEPCION() {
		return FECHA_PERCEPCION;
	}
	public void setFECHA_PERCEPCION(String fECHA_PERCEPCION) {
		FECHA_PERCEPCION = fECHA_PERCEPCION;
	}
	public String getMONEDA_PERCIBIDO() {
		return MONEDA_PERCIBIDO;
	}
	public void setMONEDA_PERCIBIDO(String mONEDA_PERCIBIDO) {
		MONEDA_PERCIBIDO = mONEDA_PERCIBIDO;
	}
	public double getMONTO_PERCIBIDO() {
		return MONTO_PERCIBIDO;
	}
	public void setMONTO_PERCIBIDO(double mONTO_PERCIBIDO) {
		MONTO_PERCIBIDO = mONTO_PERCIBIDO;
	}
	public String getMONEDA_COBRO_NETO() {
		return MONEDA_COBRO_NETO;
	}
	public void setMONEDA_COBRO_NETO(String mONEDA_COBRO_NETO) {
		MONEDA_COBRO_NETO = mONEDA_COBRO_NETO;
	}
	public double getMONTO_COBRO_NETO() {
		return MONTO_COBRO_NETO;
	}
	public void setMONTO_COBRO_NETO(double mONTO_COBRO_NETO) {
		MONTO_COBRO_NETO = mONTO_COBRO_NETO;
	}
	public int getId_retencion() {
		return id_retencion;
	}
	public void setId_retencion(int id_retencion) {
		this.id_retencion = id_retencion;
	}
	public int getId__retencion_detalle() {
		return id__retencion_detalle;
	}
	public void setId__retencion_detalle(int id__retencion_detalle) {
		this.id__retencion_detalle = id__retencion_detalle;
	}
	public void setID_CABECERA(int  ID_CABECERA) {
		this.ID_CABECERA = ID_CABECERA;
	}
	/////////////////////////////////////////////
	public String getCOD_TIPO_DOCUMENTO() {
		return COD_TIPO_DOCUMENTO;
	}
	public void setCOD_TIPO_DOCUMENTO(String  COD_TIPO_DOCUMENTO) {
		this.COD_TIPO_DOCUMENTO = COD_TIPO_DOCUMENTO;
	}
	/////////////////////////////////////////////
	public String getNRO_DOCUMENTO() {
		return NRO_DOCUMENTO;
	}
	public void setNRO_DOCUMENTO(String  NRO_DOCUMENTO) {
		this.NRO_DOCUMENTO = NRO_DOCUMENTO;
	}
	/////////////////////////////////////////////
	public String getFECHA_DOCUMENTO() {
		return FECHA_DOCUMENTO;
	}
	public void setFECHA_DOCUMENTO(String FECHA_DOCUMENTO) {
		this.FECHA_DOCUMENTO = FECHA_DOCUMENTO;
	}
	/////////////////////////////////////////////
	public String getCOD_MONEDA() {
		return COD_MONEDA;
	}
	public void setCOD_MONEDA(String  COD_MONEDA) {
		this.COD_MONEDA = COD_MONEDA;
	}
	/////////////////////////////////////////////
	public double getMONTO_TOTAL() {
		return MONTO_TOTAL;
	}
	public void setMONTO_TOTAL(double  MONTO_TOTAL) {
		this.MONTO_TOTAL = MONTO_TOTAL;
	}
	/////////////////////////////////////////////
	public String getFECHA_PAGO() {
		return FECHA_PAGO;
	}
	public void setFECHA_PAGO(String  FECHA_PAGO) {
		this.FECHA_PAGO = FECHA_PAGO;
	}
	/////////////////////////////////////////////
	public String getNRO_DOC_PAGO() {
		return NRO_DOC_PAGO;
	}
	public void setNRO_DOC_PAGO(String  NRO_DOC_PAGO) {
		this.NRO_DOC_PAGO = NRO_DOC_PAGO;
	}
	/////////////////////////////////////////////
	public String getCOD_MONEDA_PAGO() {
		return COD_MONEDA_PAGO;
	}
	public void setCOD_MONEDA_PAGO(String  COD_MONEDA_PAGO) {
		this.COD_MONEDA_PAGO = COD_MONEDA_PAGO;
	}
	/////////////////////////////////////////////
	public double getMONTO_PAGO() {
		return MONTO_PAGO;
	}
	public void setMONTO_PAGO(double  MONTO_PAGO) {
		this.MONTO_PAGO = MONTO_PAGO;
	}
	/////////////////////////////////////////////
	public String getFECHA_RETENIDA() {
		return FECHA_RETENIDA;
	}
	public void setFECHA_RETENIDA(String  FECHA_RETENIDA) {
		this.FECHA_RETENIDA = FECHA_RETENIDA;
	}
	/////////////////////////////////////////////
	public String getMONEDA_RETENIDA() {
		return MONEDA_RETENIDA;
	}
	public void setMONEDA_RETENIDA(String  MONEDA_RETENIDA) {
		this.MONEDA_RETENIDA = MONEDA_RETENIDA;
	}
	/////////////////////////////////////////////
	public double getMONTO_RETENIDO() {
		return MONTO_RETENIDO;
	}
	public void setMONTO_RETENIDO(double  MONTO_RETENIDO) {
		this.MONTO_RETENIDO = MONTO_RETENIDO;
	}
	/////////////////////////////////////////////
	public String getMONEDA_PAGO_NETO() {
		return MONEDA_PAGO_NETO;
	}
	public void setMONEDA_PAGO_NETO(String  MONEDA_PAGO_NETO) {
		this.MONEDA_PAGO_NETO = MONEDA_PAGO_NETO;
	}
	/////////////////////////////////////////////
	public double getMONTO_PAGO_NETO() {
		return MONTO_PAGO_NETO;
	}
	public void setMONTO_PAGO_NETO(double  MONTO_PAGO_NETO) {
		this.MONTO_PAGO_NETO = MONTO_PAGO_NETO;
	}
	/////////////////////////////////////////////
	public String getMONEDA_ORIGEN_TC() {
		return MONEDA_ORIGEN_TC;
	}
	public void setMONEDA_ORIGEN_TC(String  MONEDA_ORIGEN_TC) {
		this.MONEDA_ORIGEN_TC = MONEDA_ORIGEN_TC;
	}
	/////////////////////////////////////////////
	public String getMONEDA_DESTINO_TC() {
		return MONEDA_DESTINO_TC;
	}
	public void setMONEDA_DESTINO_TC(String  MONEDA_DESTINO_TC) {
		this.MONEDA_DESTINO_TC = MONEDA_DESTINO_TC;
	}
	/////////////////////////////////////////////
	public double getPORCENTAJE_TC() {
		return PORCENTAJE_TC;
	}
	public void setPORCENTAJE_TC(double  PORCENTAJE_TC) {
		this.PORCENTAJE_TC = PORCENTAJE_TC;
	}
	/////////////////////////////////////////////
	public String getFECHA_TC() {
		return FECHA_TC;
	}
	public void setFECHA_TC(String  FECHA_TC) {
		this.FECHA_TC = FECHA_TC;
	}
	/////////////////////////////////////////////
	///////////////////campos aumentados//////////////////////////
	public int getTIPO() {
		return TIPO;
	}
	public void setTIPO(int TIPO) {
		this.TIPO = TIPO;
	}
}