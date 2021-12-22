/*
*ˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆ
* Creado por     : Jose Zambrano  (30/09/2016)
* Bean = Cpe_Detalle
*/

package org.gteperu.erp.everest.domain;
import java.util.Date;

public class _Cpe_DetalleBean {
	private int ID_DETALLE;
	private int ID_CABECERA;
	private int ITEM;
	private String UNIDAD_MEDIDA;
	private double CANTIDAD;
	private double PRECIO;
	private double IMPORTE;
	private String PRECIO_TIPO_CODIGO;
	private double IGV;
        private double ISC;
	private String COD_TIPO_OPERACION;
	private String CODIGO;
        private String COD_SUNAT;
	private String DESCRIPCION;
        private String MONEDA;
        private double DESCUENTO;
        private double SUB_TOTAL;
        private double PRECIO_SIN_IMPUESTO;
	private int TIPO;
	/////////////////////////////////////////////
	public int getID_DETALLE() {
		return ID_DETALLE;
	}
	public void setID_DETALLE(int  ID_DETALLE) {
		this.ID_DETALLE = ID_DETALLE;
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
	public String getUNIDAD_MEDIDA() {
		return UNIDAD_MEDIDA;
	}
	public void setUNIDAD_MEDIDA(String  UNIDAD_MEDIDA) {
		this.UNIDAD_MEDIDA = UNIDAD_MEDIDA;
	}
	/////////////////////////////////////////////
	public double getCANTIDAD() {
		return CANTIDAD;
	}
	public void setCANTIDAD(double  CANTIDAD) {
		this.CANTIDAD = CANTIDAD;
	}
	/////////////////////////////////////////////
	public double getPRECIO() {
		return PRECIO;
	}
	public void setPRECIO(double  PRECIO) {
		this.PRECIO = PRECIO;
	}
	/////////////////////////////////////////////
	public double getIMPORTE() {
		return IMPORTE;
	}
	public void setIMPORTE(double  IMPORTE) {
		this.IMPORTE = IMPORTE;
	}
	/////////////////////////////////////////////
	public String getPRECIO_TIPO_CODIGO() {
		return PRECIO_TIPO_CODIGO;
	}
	public void setPRECIO_TIPO_CODIGO(String  PRECIO_TIPO_CODIGO) {
		this.PRECIO_TIPO_CODIGO = PRECIO_TIPO_CODIGO;
	}
	/////////////////////////////////////////////
	public double getIGV() {
		return IGV;
	}
	public void setIGV(double  IGV) {
		this.IGV = IGV;
	}
        /////////////////////////////////////////////
	public double getISC() {
		return ISC;
	}
	public void setISC(double ISC) {
		this.ISC = ISC;
	}
	/////////////////////////////////////////////
	public String getCOD_TIPO_OPERACION() {
		return COD_TIPO_OPERACION;
	}
	public void setCOD_TIPO_OPERACION(String  COD_TIPO_OPERACION) {
		this.COD_TIPO_OPERACION = COD_TIPO_OPERACION;
	}
        /////////////////////////////////////////////
	public String getCOD_SUNAT() {
		return COD_SUNAT;
	}
	public void setCOD_SUNAT(String COD_SUNAT) {
		this.COD_SUNAT = COD_SUNAT;
	}
	/////////////////////////////////////////////
	public String getCODIGO() {
		return CODIGO;
	}
	public void setCODIGO(String  CODIGO) {
		this.CODIGO = CODIGO;
	}
	/////////////////////////////////////////////
	public String getDESCRIPCION() {
		return DESCRIPCION;
	}
	public void setDESCRIPCION(String  DESCRIPCION) {
		this.DESCRIPCION = DESCRIPCION;
	}
        /////////////////////////////////////////////
	public String getMONEDA() {
		return MONEDA;
	}
	public void setMONEDA(String MONEDA) {
		this.MONEDA = MONEDA;
	}
        /////////////////////////////////////////////
	public double getPRECIO_SIN_IMPUESTO() {
		return PRECIO_SIN_IMPUESTO;
	}
	public void setPRECIO_SIN_IMPUESTO(double PRECIO_SIN_IMPUESTO) {
		this.PRECIO_SIN_IMPUESTO = PRECIO_SIN_IMPUESTO;
	}
        /////////////////////////////////////////////
	public double getSUB_TOTAL() {
		return SUB_TOTAL;
	}
	public void setSUB_TOTAL(double SUB_TOTAL) {
		this.SUB_TOTAL = SUB_TOTAL;
	}
        /////////////////////////////////////////////
	public double getDESCUENTO() {
		return DESCUENTO;
	}
	public void setDESCUENTO(double DESCUENTO) {
		this.DESCUENTO = DESCUENTO;
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