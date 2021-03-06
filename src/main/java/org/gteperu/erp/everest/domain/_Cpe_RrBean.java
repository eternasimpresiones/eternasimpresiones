/*
*ˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆ
* Creado por     : Jose Zambrano  (13/12/2016)
* Bean = Cpe_Rr
*/

package org.gteperu.erp.everest.domain;
import java.util.Date;

public class _Cpe_RrBean {
	private int ID_CPE_RR;
	private String FECHA_REGISTRO;
	private int ID_EMPRESA;
	private String NRO_DOCUMENTO_EMPRESA;
	private String RAZON_SOCIAL;
	private String TIPO_DOCUMENTO;
	private String CODIGO;
	private String SERIE;
	private String SECUENCIA;
	private String FECHA_REFERENCIA;
	private String FECHA_DOCUMENTO;
	private int TIPO_PROCESO;
	private String TICKET;
	private String ESTADO;
	private int TIPO;
	/////////////////////////////////////////////
	public int getID_CPE_RR() {
		return ID_CPE_RR;
	}
	public void setID_CPE_RR(int  ID_CPE_RR) {
		this.ID_CPE_RR = ID_CPE_RR;
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
	public String getNRO_DOCUMENTO_EMPRESA() {
		return NRO_DOCUMENTO_EMPRESA;
	}
	public void setNRO_DOCUMENTO_EMPRESA(String  NRO_DOCUMENTO_EMPRESA) {
		this.NRO_DOCUMENTO_EMPRESA = NRO_DOCUMENTO_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getRAZON_SOCIAL() {
		return RAZON_SOCIAL;
	}
	public void setRAZON_SOCIAL(String  RAZON_SOCIAL) {
		this.RAZON_SOCIAL = RAZON_SOCIAL;
	}
	/////////////////////////////////////////////
	public String getTIPO_DOCUMENTO() {
		return TIPO_DOCUMENTO;
	}
	public void setTIPO_DOCUMENTO(String TIPO_DOCUMENTO) {
		this.TIPO_DOCUMENTO = TIPO_DOCUMENTO;
	}
	/////////////////////////////////////////////
	public String getCODIGO() {
		return CODIGO;
	}
	public void setCODIGO(String  CODIGO) {
		this.CODIGO = CODIGO;
	}
	/////////////////////////////////////////////
	public String getSERIE() {
		return SERIE;
	}
	public void setSERIE(String  SERIE) {
		this.SERIE = SERIE;
	}
	/////////////////////////////////////////////
	public String getSECUENCIA() {
		return SECUENCIA;
	}
	public void setSECUENCIA(String  SECUENCIA) {
		this.SECUENCIA = SECUENCIA;
	}
	/////////////////////////////////////////////
	public String getFECHA_REFERENCIA() {
		return FECHA_REFERENCIA;
	}
	public void setFECHA_REFERENCIA(String  FECHA_REFERENCIA) {
		this.FECHA_REFERENCIA = FECHA_REFERENCIA;
	}
	/////////////////////////////////////////////
	public String getFECHA_DOCUMENTO() {
		return FECHA_DOCUMENTO;
	}
	public void setFECHA_DOCUMENTO(String  FECHA_DOCUMENTO) {
		this.FECHA_DOCUMENTO = FECHA_DOCUMENTO;
	}
	/////////////////////////////////////////////
	public int getTIPO_PROCESO() {
		return TIPO_PROCESO;
	}
	public void setTIPO_PROCESO(int  TIPO_PROCESO) {
		this.TIPO_PROCESO = TIPO_PROCESO;
	}
	/////////////////////////////////////////////
	public String getTICKET() {
		return TICKET;
	}
	public void setTICKET(String  TICKET) {
		this.TICKET = TICKET;
	}
	/////////////////////////////////////////////
	public String getESTADO() {
		return ESTADO;
	}
	public void setESTADO(String  ESTADO) {
		this.ESTADO = ESTADO;
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