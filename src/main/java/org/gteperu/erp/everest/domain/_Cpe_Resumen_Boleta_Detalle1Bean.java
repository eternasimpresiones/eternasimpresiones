/*
*ˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆ
* Creado por     : Jose Zambrano  (18/02/2017)
* Bean = Cpe_Resumen_Boleta_Detalle
*/

package org.gteperu.erp.everest.domain;
import java.util.Date;

public class _Cpe_Resumen_Boleta_Detalle1Bean {
	private int ID_RESUMEN;
	private int ID_CABECERA;
	private int ITEM;
	private String TIPO_COMPROBANTE;
	private String SERIE_COMPROBANTE;        
        private int STAR_DOCUMENTO;
        private int END_DOCUMENTO;
	private String COD_MONEDA;
	private double TOTAL;
	private double GRAVADA;
	private double ISC;
	private double IGV;
	private double OTROS;
	private int CARGO_X_ASIGNACION;
	private double MONTO_CARGO_X_ASIG;
	private String COD_TIPO_IMPORTE1;
	private double EXONERADO;
	private String COD_TIPO_IMPORTE2;
	private double INAFECTO;
	private String COD_TIPO_IMPORTE3;
	private double EXPORTACION;
	private String COD_TIPO_IMPORTE4;
	private double GRATUITAS;
	private String COD_TIPO_IMPORTE5;
	private String ESTADOS;
	private int TIPO;
	/////////////////////////////////////////////
	public int getID_RESUMEN() {
		return ID_RESUMEN;
	}
	public void setID_RESUMEN(int  ID_RESUMEN) {
		this.ID_RESUMEN = ID_RESUMEN;
	}
	/////////////////////////////////////////////
	public int getID_CABECERA() {
		return ID_CABECERA;
	}
	public void setID_CABECERA(int  ID_CABECERA) {
		this.ID_CABECERA = ID_CABECERA;
	}
	/////////////////////////////////////////////
	public int getITEM() {
		return ITEM;
	}
	public void setITEM(int  ITEM) {
		this.ITEM = ITEM;
	}
	/////////////////////////////////////////////
	public String getTIPO_COMPROBANTE() {
		return TIPO_COMPROBANTE;
	}
	public void setTIPO_COMPROBANTE(String  TIPO_COMPROBANTE) {
		this.TIPO_COMPROBANTE = TIPO_COMPROBANTE;
	}
	/////////////////////////////////////////////
	public String getSERIE_COMPROBANTE() {
		return SERIE_COMPROBANTE;
	}
	public void setSERIE_COMPROBANTE(String SERIE_COMPROBANTE) {
		this.SERIE_COMPROBANTE = SERIE_COMPROBANTE;
	}
        
        /////////////////////////////////////////////
	public int getSTAR_DOCUMENTO() {
		return STAR_DOCUMENTO;
	}
	public void setSTAR_DOCUMENTO(int STAR_DOCUMENTO) {
		this.STAR_DOCUMENTO = STAR_DOCUMENTO;
	}
        /////////////////////////////////////////////
	public int getEND_DOCUMENTO() {
		return END_DOCUMENTO;
	}
	public void setEND_DOCUMENTO(int END_DOCUMENTO) {
		this.END_DOCUMENTO = END_DOCUMENTO;
	}	
	
	/////////////////////////////////////////////
	public String getCOD_MONEDA() {
		return COD_MONEDA;
	}
	public void setCOD_MONEDA(String  COD_MONEDA) {
		this.COD_MONEDA = COD_MONEDA;
	}
	/////////////////////////////////////////////
	public double getTOTAL() {
		return TOTAL;
	}
	public void setTOTAL(double  TOTAL) {
		this.TOTAL = TOTAL;
	}
	/////////////////////////////////////////////
	public double getGRAVADA() {
		return GRAVADA;
	}
	public void setGRAVADA(double  GRAVADA) {
		this.GRAVADA = GRAVADA;
	}
	/////////////////////////////////////////////
	public double getISC() {
		return ISC;
	}
	public void setISC(double  ISC) {
		this.ISC = ISC;
	}
	/////////////////////////////////////////////
	public double getIGV() {
		return IGV;
	}
	public void setIGV(double  IGV) {
		this.IGV = IGV;
	}
	/////////////////////////////////////////////
	public double getOTROS() {
		return OTROS;
	}
	public void setOTROS(double  OTROS) {
		this.OTROS = OTROS;
	}
	/////////////////////////////////////////////
	public int getCARGO_X_ASIGNACION() {
		return CARGO_X_ASIGNACION;
	}
	public void setCARGO_X_ASIGNACION(int  CARGO_X_ASIGNACION) {
		this.CARGO_X_ASIGNACION = CARGO_X_ASIGNACION;
	}
	/////////////////////////////////////////////
	public double getMONTO_CARGO_X_ASIG() {
		return MONTO_CARGO_X_ASIG;
	}
	public void setMONTO_CARGO_X_ASIG(double  MONTO_CARGO_X_ASIG) {
		this.MONTO_CARGO_X_ASIG = MONTO_CARGO_X_ASIG;
	}
	/////////////////////////////////////////////
	public String getCOD_TIPO_IMPORTE1() {
		return COD_TIPO_IMPORTE1;
	}
	public void setCOD_TIPO_IMPORTE1(String  COD_TIPO_IMPORTE1) {
		this.COD_TIPO_IMPORTE1 = COD_TIPO_IMPORTE1;
	}
	/////////////////////////////////////////////
	public double getEXONERADO() {
		return EXONERADO;
	}
	public void setEXONERADO(double  EXONERADO) {
		this.EXONERADO = EXONERADO;
	}
	/////////////////////////////////////////////
	public String getCOD_TIPO_IMPORTE2() {
		return COD_TIPO_IMPORTE2;
	}
	public void setCOD_TIPO_IMPORTE2(String  COD_TIPO_IMPORTE2) {
		this.COD_TIPO_IMPORTE2 = COD_TIPO_IMPORTE2;
	}
	/////////////////////////////////////////////
	public double getINAFECTO() {
		return INAFECTO;
	}
	public void setINAFECTO(double  INAFECTO) {
		this.INAFECTO = INAFECTO;
	}
	/////////////////////////////////////////////
	public String getCOD_TIPO_IMPORTE3() {
		return COD_TIPO_IMPORTE3;
	}
	public void setCOD_TIPO_IMPORTE3(String  COD_TIPO_IMPORTE3) {
		this.COD_TIPO_IMPORTE3 = COD_TIPO_IMPORTE3;
	}
	/////////////////////////////////////////////
	public double getEXPORTACION() {
		return EXPORTACION;
	}
	public void setEXPORTACION(double  EXPORTACION) {
		this.EXPORTACION = EXPORTACION;
	}
	/////////////////////////////////////////////
	public String getCOD_TIPO_IMPORTE4() {
		return COD_TIPO_IMPORTE4;
	}
	public void setCOD_TIPO_IMPORTE4(String  COD_TIPO_IMPORTE4) {
		this.COD_TIPO_IMPORTE4 = COD_TIPO_IMPORTE4;
	}
	/////////////////////////////////////////////
	public double getGRATUITAS() {
		return GRATUITAS;
	}
	public void setGRATUITAS(double  GRATUITAS) {
		this.GRATUITAS = GRATUITAS;
	}
	/////////////////////////////////////////////
	public String getCOD_TIPO_IMPORTE5() {
		return COD_TIPO_IMPORTE5;
	}
	public void setCOD_TIPO_IMPORTE5(String  COD_TIPO_IMPORTE5) {
		this.COD_TIPO_IMPORTE5 = COD_TIPO_IMPORTE5;
	}
	/////////////////////////////////////////////
	public String getESTADOS() {
		return ESTADOS;
	}
	public void setESTADOS(String  ESTADOS) {
		this.ESTADOS = ESTADOS;
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