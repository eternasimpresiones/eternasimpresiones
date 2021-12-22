/*
*ˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆ
* Creado por     : Jose Zambrano  (28/10/2016)
* Bean = Cpe_Baja_Detalle
 */
package org.gteperu.erp.everest.domain;

import java.util.Date;

public class _Cpe_Baja_DetalleBean {

    private int ID_EMPRESA;
    private int ID_BAJA_CPE;
    private int ID_BAJA;
    private int ITEM;
    private String TIPO_COMPROBANTE;
    private String SERIE;
    private String NUMERO;
    private String DESCRIPCION;
    private int ID_DOCUMENTO_VENTA;

    //=================DATOS CABECERA================
    private String NRO_DOCUMENTO_EMPRESA;
    private String RAZON_SOCIAL;
    private String CODIGO;
    private String SERIE_CAB;
    private String SECUENCIA;
    private String FECHA_REFERENCIA;
    private String FECHA_BAJA;
    private String TICKET;

    private int TIPO;
    /////////////////////////////////////////////

    public int getID_EMPRESA() {
        return ID_EMPRESA;
    }

    public void setID_EMPRESA(int ID_EMPRESA) {
        this.ID_EMPRESA = ID_EMPRESA;
    }
    /////////////////////////////////////////////

    public int getID_BAJA_CPE() {
        return ID_BAJA_CPE;
    }

    public void setID_BAJA_CPE(int ID_BAJA_CPE) {
        this.ID_BAJA_CPE = ID_BAJA_CPE;
    }
    /////////////////////////////////////////////

    public int getID_BAJA() {
        return ID_BAJA;
    }

    public void setID_BAJA(int ID_BAJA) {
        this.ID_BAJA = ID_BAJA;
    }
    /////////////////////////////////////////////

    public int getITEM() {
        return ITEM;
    }

    public void setITEM(int ITEM) {
        this.ITEM = ITEM;
    }
    /////////////////////////////////////////////

    public String getTIPO_COMPROBANTE() {
        return TIPO_COMPROBANTE;
    }

    public void setTIPO_COMPROBANTE(String TIPO_COMPROBANTE) {
        this.TIPO_COMPROBANTE = TIPO_COMPROBANTE;
    }
    /////////////////////////////////////////////

    public String getSERIE() {
        return SERIE;
    }

    public void setSERIE(String SERIE) {
        this.SERIE = SERIE;
    }
    /////////////////////////////////////////////

    public String getNUMERO() {
        return NUMERO;
    }

    public void setNUMERO(String NUMERO) {
        this.NUMERO = NUMERO;
    }
    /////////////////////////////////////////////

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    //=================DATOS CABECERA================        
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

    public String getCODIGO() {
        return CODIGO;
    }

    public void setCODIGO(String CODIGO) {
        this.CODIGO = CODIGO;
    }
    /////////////////////////////////////////////

    public String getSERIE_CAB() {
        return SERIE_CAB;
    }

    public void setSERIE_CAB(String SERIE_CAB) {
        this.SERIE_CAB = SERIE_CAB;
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

    public String getTICKET() {
        return TICKET;
    }

    public void setTICKET(String TICKET) {
        this.TICKET = TICKET;
    }

    /////////////////////////////////////////////
    ///////////////////campos aumentados//////////////////////////
    public int getTIPO() {
        return TIPO;
    }

    public void setTIPO(int TIPO) {
        this.TIPO = TIPO;
    }

    public int getID_DOCUMENTO_VENTA() {
        return ID_DOCUMENTO_VENTA;
    }

    public void setID_DOCUMENTO_VENTA(int ID_DOCUMENTO_VENTA) {
        this.ID_DOCUMENTO_VENTA = ID_DOCUMENTO_VENTA;
    }
    
    
}
