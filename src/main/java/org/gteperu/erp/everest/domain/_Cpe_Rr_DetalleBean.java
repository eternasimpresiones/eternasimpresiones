/*
*ˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆ
* Creado por     : Jose Zambrano  (13/12/2016)
* Bean = Cpe_Rr_Detalle
*/

package org.gteperu.erp.everest.domain;
import java.util.Date;

public class _Cpe_Rr_DetalleBean {
	private int ID_CPE_RR_DETALLE;
	private int ID_CABECERA;
	private int ITEM;
	private String COD_TIPO_DOCUMENTO;
	private String SERIE;
	private String NUMERO;
	private String DESCRIPCION;
	private int TIPO;
	/////////////////////////////////////////////
	public int getID_CPE_RR_DETALLE() {
		return ID_CPE_RR_DETALLE;
	}
	public void setID_CPE_RR_DETALLE(int  ID_CPE_RR_DETALLE) {
		this.ID_CPE_RR_DETALLE = ID_CPE_RR_DETALLE;
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
	public String getCOD_TIPO_DOCUMENTO() {
		return COD_TIPO_DOCUMENTO;
	}
	public void setCOD_TIPO_DOCUMENTO(String  COD_TIPO_DOCUMENTO) {
		this.COD_TIPO_DOCUMENTO = COD_TIPO_DOCUMENTO;
	}
	/////////////////////////////////////////////
	public String getSERIE() {
		return SERIE;
	}
	public void setSERIE(String  SERIE) {
		this.SERIE = SERIE;
	}
	/////////////////////////////////////////////
	public String getNUMERO() {
		return NUMERO;
	}
	public void setNUMERO(String  NUMERO) {
		this.NUMERO = NUMERO;
	}
	/////////////////////////////////////////////
	public String getDESCRIPCION() {
		return DESCRIPCION;
	}
	public void setDESCRIPCION(String  DESCRIPCION) {
		this.DESCRIPCION = DESCRIPCION;
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