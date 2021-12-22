/*
*ˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆ
* Creado por     : Jose Zambrano  (28/10/2016)
* Bean = Cpe_Baja
 */
package org.gteperu.erp.everest.domain;

import java.util.List;

public class _Cpe_BajaBean {

    private int ID_BAJA;
    private String FECHA_REGISTRO;
    private int ID_EMPRESA;
    private String NRO_DOCUMENTO_EMPRESA;
    private String RAZON_SOCIAL;
    private String TIPO_DOCUMENTO;
    private String CODIGO;
    private String SERIE;
    private String SECUENCIA;
    private String FECHA_REFERENCIA;
    private String FECHA_BAJA;
    private int TIPO_PROCESO;
    private String TICKET;
    private String ESTADO;
    private int TIPO;

    private String COD_RESPUESTA;
    private String DESCRIPCION_RESPUESTA;
    private String HASH_CPE;
    private String HASH_CDR;
    
    List<_Cpe_Baja_DetalleBean> Detalle;
    
    private String dirDocumentoEmpresaEmisora;
    private Integer ifacturacionPse;
    
    
    public String getDirDocumentoEmpresaEmisora() {
		return dirDocumentoEmpresaEmisora;
	}

	public void setDirDocumentoEmpresaEmisora(String dirDocumentoEmpresaEmisora) {
		this.dirDocumentoEmpresaEmisora = dirDocumentoEmpresaEmisora;
	}

	public int getID_BAJA() {
        return ID_BAJA;
    }

    public void setID_BAJA(int ID_BAJA) {
        this.ID_BAJA = ID_BAJA;
    }
    /////////////////////////////////////////////

    public String getFECHA_REGISTRO() {
        return FECHA_REGISTRO;
    }

    public void setFECHA_REGISTRO(String FECHA_REGISTRO) {
        this.FECHA_REGISTRO = FECHA_REGISTRO;
    }
    /////////////////////////////////////////////

    public int getID_EMPRESA() {
        return ID_EMPRESA;
    }

    public void setID_EMPRESA(int ID_EMPRESA) {
        this.ID_EMPRESA = ID_EMPRESA;
    }
    /////////////////////////////////////////////

    public String getNRO_DOCUMENTO_EMPRESA() {
        return NRO_DOCUMENTO_EMPRESA;
    }

    public void setNRO_DOCUMENTO_EMPRESA(String NRO_DOCUMENTO_EMPRESA) {
        this.NRO_DOCUMENTO_EMPRESA = NRO_DOCUMENTO_EMPRESA;
    }
    /////////////////////////////////////////////

    public String getRAZON_SOCIAL() {
        return RAZON_SOCIAL;
    }

    public void setRAZON_SOCIAL(String RAZON_SOCIAL) {
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

    public void setCODIGO(String CODIGO) {
        this.CODIGO = CODIGO;
    }
    /////////////////////////////////////////////

    public String getSERIE() {
        return SERIE;
    }

    public void setSERIE(String SERIE) {
        this.SERIE = SERIE;
    }
    /////////////////////////////////////////////

    public String getSECUENCIA() {
        return SECUENCIA;
    }

    public void setSECUENCIA(String SECUENCIA) {
        this.SECUENCIA = SECUENCIA;
    }
    /////////////////////////////////////////////

    public String getFECHA_REFERENCIA() {
        return FECHA_REFERENCIA;
    }

    public void setFECHA_REFERENCIA(String FECHA_REFERENCIA) {
        this.FECHA_REFERENCIA = FECHA_REFERENCIA;
    }
    /////////////////////////////////////////////

    public String getFECHA_BAJA() {
        return FECHA_BAJA;
    }

    public void setFECHA_BAJA(String FECHA_BAJA) {
        this.FECHA_BAJA = FECHA_BAJA;
    }
    /////////////////////////////////////////////

    public int getTIPO_PROCESO() {
        return TIPO_PROCESO;
    }

    public void setTIPO_PROCESO(int TIPO_PROCESO) {
        this.TIPO_PROCESO = TIPO_PROCESO;
    }
    /////////////////////////////////////////////

    public String getTICKET() {
        return TICKET;
    }

    public void setTICKET(String TICKET) {
        this.TICKET = TICKET;
    }
    /////////////////////////////////////////////

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
    
    ///////////////////campos aumentados//////////////////////////

    public int getTIPO() {
        return TIPO;
    }

    public void setTIPO(int TIPO) {
        this.TIPO = TIPO;
    }

    public String getCOD_RESPUESTA() {
        return COD_RESPUESTA;
    }

    public void setCOD_RESPUESTA(String COD_RESPUESTA) {
        this.COD_RESPUESTA = COD_RESPUESTA;
    }

    public String getDESCRIPCION_RESPUESTA() {
        return DESCRIPCION_RESPUESTA;
    }

    public void setDESCRIPCION_RESPUESTA(String DESCRIPCION_RESPUESTA) {
        this.DESCRIPCION_RESPUESTA = DESCRIPCION_RESPUESTA;
    }

    public String getHASH_CPE() {
        return HASH_CPE;
    }

    public void setHASH_CPE(String HASH_CPE) {
        this.HASH_CPE = HASH_CPE;
    }

    public String getHASH_CDR() {
        return HASH_CDR;
    }

    public void setHASH_CDR(String HASH_CDR) {
        this.HASH_CDR = HASH_CDR;
    }
    
    ///////////////////DETALLE//////////////////////////
    public List<_Cpe_Baja_DetalleBean> getDetalle() {
     return Detalle;
    }
    public void setDetalle(List<_Cpe_Baja_DetalleBean> Detalle) {
		this.Detalle = Detalle;
    }

	public Integer getIfacturacionPse() {
		return ifacturacionPse;
	}

	public void setIfacturacionPse(Integer ifacturacionPse) {
		this.ifacturacionPse = ifacturacionPse;
	}
    
    
}
