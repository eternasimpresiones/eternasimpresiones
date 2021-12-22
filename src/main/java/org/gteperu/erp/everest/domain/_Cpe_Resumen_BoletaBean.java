/*
*ˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆ
* Creado por     : Jose Zambrano  (18/02/2017)
* Bean = Cpe_Resumen_Boleta
*/

package org.gteperu.erp.everest.domain;
import java.util.Date;
import java.util.List;

public class _Cpe_Resumen_BoletaBean {
	private int ID_RESUMEN;
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
	private String COD_RESPUESTA_SUNAT;
	private String DESCRIPCION_RESPUESTA;
	private String HASH_CPE;
	private String HASH_CDR;
	private String ESTADO;
        private int TIPO_RESUMEN;
        List<_Cpe_Resumen_Boleta_DetalleBean> Detalle;
	private int TIPO;
	/////////////////////////////////////////////
	public int getID_RESUMEN() {
		return ID_RESUMEN;
	}
	public void setID_RESUMEN(int  ID_RESUMEN) {
		this.ID_RESUMEN = ID_RESUMEN;
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
	public void setTIPO_DOCUMENTO(String  TIPO_DOCUMENTO) {
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
	public String getHASH_CPE() {
		return HASH_CPE;
	}
	public void setHASH_CPE(String  HASH_CPE) {
		this.HASH_CPE = HASH_CPE;
	}
	/////////////////////////////////////////////
	public String getHASH_CDR() {
		return HASH_CDR;
	}
	public void setHASH_CDR(String  HASH_CDR) {
		this.HASH_CDR = HASH_CDR;
	}
	/////////////////////////////////////////////
	public String getESTADO() {
		return ESTADO;
	}
	public void setESTADO(String  ESTADO) {
		this.ESTADO = ESTADO;
	}
        public int getTIPO_RESUMEN() {
		return TIPO_RESUMEN;
	}
	public void setTIPO_RESUMEN(int TIPO_RESUMEN) {
		this.TIPO_RESUMEN = TIPO_RESUMEN;
	}
        ///////////////////DETALLE//////////////////////////
	public List<_Cpe_Resumen_Boleta_DetalleBean> getDetalle() {
		return Detalle;
	}
	public void setDetalle(List<_Cpe_Resumen_Boleta_DetalleBean> Detalle) {
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
}