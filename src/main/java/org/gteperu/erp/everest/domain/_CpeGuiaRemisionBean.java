/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gteperu.erp.everest.domain;

import java.util.List;

/**
 *
 * @author jose
 */
public class _CpeGuiaRemisionBean {

    private int ID;
    private String NRO_COMPROBANTE;//T001-00003
    private String FECHA_DOCUMENTO;
    private String COD_TIPO_DOCUMENTO;//TAG (DespatchAdviceTypeCode) 09=guia remision remitente, 31=transportista
    private String NOTA;//ESTO ES UNA GUIA DE REMISION OBSERVACIÓN

    //=====================DATOS DE ANULACION==========================
    private int FLG_ANULADO;//0=NO ES ANULACION, 1=ANULACION
    private String DOC_REFERENCIA_ANU;//DOCUMENTO DE REFERENCIA AL QUE SE ANULARA
    private String COD_TIPO_DOC_REFANU;//TIPO DE DOCUMENTO DE REFENCIA (GUIA REMITENTE, GUIA TRANSPORTISTA)
    //=================DATOS DE ENVIO=================
    private int ITEM_ENVIO;
    private String COD_MOTIVO_TRASLADO;//TAG (HandlingCode) catalogo 20
    private String DESCRIPCION_MOTIVO_TRASLADO;//TAG (Information) catalogo 20
    private String COD_UND_PESO_BRUTO;//TAG (GrossWeightMeasure) catalogo 3
    public double PESO_BRUTO;
    private int TOTAL_BULTOS;//TAG (TotalTransportHandlingUnitQuantity) cantidad total de bultos o palet's
    //=======================DIRECCION PARTIDA-LLEGADA======================
  //-----------LLEGADA (DeliveryAddress)
  private String COD_UBIGEO_DESTINO;
  private String DIRECCION_DESTINO;// TAG (StreetName)
  //-----------PARTIDA (OriginAddress)
  private String COD_UBIGEO_ORIGEN;
  private String DIRECCION_ORIGEN;// TAG (StreetName)

  //=======================DATOS DE LA SUNAT======================
  private int TIPO_PROCESO;
  private String USUARIO_SOL_EMPRESA;
  private String PASS_SOL_EMPRESA;
  private String CONTRA_FIRMA;

  //=======================DATOS DEL CLIENTE======================
  private String NRO_DOCUMENTO_CLIENTE;
  private String RAZON_SOCIAL_CLIENTE;
  private String TIPO_DOCUMENTO_CLIENTE;//6=RUC       

  //=======================DATOS DE LA EMPRESA======================
  private String NRO_DOCUMENTO_EMPRESA;
  private String TIPO_DOCUMENTO_EMPRESA;//6=RUC
  private String RAZON_SOCIAL_EMPRESA;

  
  //=================INICIO DE TRASLADO Y DATOS DEL TRANSPORTISTA=================
  private String COD_MODALIDAD_TRASLADO;//TAG (TransportModeCode) catalogo 18, 01=transporte publico, 02=transporte privado
  private String FECHA_INICIO;//TAG (StartDate)

    //=======================DATOS DEL TRANSPORTISTA PUBLICO======================
    private String NRO_DOCUMENTO_TRANSPORTISTA;
    private String RAZON_SOCIAL_TRANSPORTISTA;
    private String TIPO_DOCUMENTO_TRANSPORTISTA;//6=RUC

    //=========================DATOS VEHICULO, CHOFER, CARROZA - UND TRANSPORTE========================
    private String PLACA_VEHICULO;
    private String COD_TIPO_DOC_CHOFER;//1=DNI
    private String NRO_DOC_CHOFER;//NRO DOCUMENTO DEL CHOFER
    private String PLACA_CARRETA;
    
    //-----------DATOS POR IMPORTACION O COMPRA
    private String NUMERACION_DAM;
    private String COD_TIPO_DOC_PROVEEDOR;
    private String NRO_DOC_PROVEEDOR;
    private String DESCRIPCION_PROVEEDOR;
    private String NRO_CONTENEDOR;
    private Integer ifacturacionPse;


 
  
    /////////detalle////////
    List<_CpeGuiaRemisionDetalleBean> Detalle;

    public int getID() {
        return ID;
    }


	public String getNRO_CONTENEDOR() {
		return NRO_CONTENEDOR;
	}


	public void setNRO_CONTENEDOR(String nRO_CONTENEDOR) {
		NRO_CONTENEDOR = nRO_CONTENEDOR;
	}


	public String getNUMERACION_DAM() {
		return NUMERACION_DAM;
	}


	public void setNUMERACION_DAM(String nUMERACION_DAM) {
		NUMERACION_DAM = nUMERACION_DAM;
	}


	public String getCOD_TIPO_DOC_PROVEEDOR() {
		return COD_TIPO_DOC_PROVEEDOR;
	}

	public void setCOD_TIPO_DOC_PROVEEDOR(String cOD_TIPO_DOC_PROVEEDOR) {
		COD_TIPO_DOC_PROVEEDOR = cOD_TIPO_DOC_PROVEEDOR;
	}

	public String getNRO_DOC_PROVEEDOR() {
		return NRO_DOC_PROVEEDOR;
	}

	public void setNRO_DOC_PROVEEDOR(String nRO_DOC_PROVEEDOR) {
		NRO_DOC_PROVEEDOR = nRO_DOC_PROVEEDOR;
	}

	public String getDESCRIPCION_PROVEEDOR() {
		return DESCRIPCION_PROVEEDOR;
	}

	public void setDESCRIPCION_PROVEEDOR(String dESCRIPCION_PROVEEDOR) {
		DESCRIPCION_PROVEEDOR = dESCRIPCION_PROVEEDOR;
	}

	public void setID(int ID) {
        this.ID = ID;
    }

    public String getNRO_COMPROBANTE() {
        return NRO_COMPROBANTE;
    }

    public void setNRO_COMPROBANTE(String NRO_COMPROBANTE) {
        this.NRO_COMPROBANTE = NRO_COMPROBANTE;
    }

    public String getFECHA_DOCUMENTO() {
        return FECHA_DOCUMENTO;
    }

    public void setFECHA_DOCUMENTO(String FECHA_DOCUMENTO) {
        this.FECHA_DOCUMENTO = FECHA_DOCUMENTO;
    }

    public String getCOD_TIPO_DOCUMENTO() {
        return COD_TIPO_DOCUMENTO;
    }

    public void setCOD_TIPO_DOCUMENTO(String COD_TIPO_DOCUMENTO) {
        this.COD_TIPO_DOCUMENTO = COD_TIPO_DOCUMENTO;
    }

    public String getNOTA() {
        return NOTA;
    }

    public void setNOTA(String NOTA) {
        this.NOTA = NOTA;
    }

    public int getFLG_ANULADO() {
        return FLG_ANULADO;
    }

    public void setFLG_ANULADO(int FLG_ANULADO) {
        this.FLG_ANULADO = FLG_ANULADO;
    }

    public String getDOC_REFERENCIA_ANU() {
        return DOC_REFERENCIA_ANU;
    }

    public void setDOC_REFERENCIA_ANU(String DOC_REFERENCIA_ANU) {
        this.DOC_REFERENCIA_ANU = DOC_REFERENCIA_ANU;
    }

    public String getCOD_TIPO_DOC_REFANU() {
        return COD_TIPO_DOC_REFANU;
    }

    public void setCOD_TIPO_DOC_REFANU(String COD_TIPO_DOC_REFANU) {
        this.COD_TIPO_DOC_REFANU = COD_TIPO_DOC_REFANU;
    }

    public int getITEM_ENVIO() {
        return ITEM_ENVIO;
    }

    public void setITEM_ENVIO(int ITEM_ENVIO) {
        this.ITEM_ENVIO = ITEM_ENVIO;
    }

    public String getCOD_MOTIVO_TRASLADO() {
        return COD_MOTIVO_TRASLADO;
    }

    public void setCOD_MOTIVO_TRASLADO(String COD_MOTIVO_TRASLADO) {
        this.COD_MOTIVO_TRASLADO = COD_MOTIVO_TRASLADO;
    }

    public String getDESCRIPCION_MOTIVO_TRASLADO() {
        return DESCRIPCION_MOTIVO_TRASLADO;
    }

    public void setDESCRIPCION_MOTIVO_TRASLADO(String DESCRIPCION_MOTIVO_TRASLADO) {
        this.DESCRIPCION_MOTIVO_TRASLADO = DESCRIPCION_MOTIVO_TRASLADO;
    }

    public String getCOD_UND_PESO_BRUTO() {
        return COD_UND_PESO_BRUTO;
    }

    public void setCOD_UND_PESO_BRUTO(String COD_UND_PESO_BRUTO) {
        this.COD_UND_PESO_BRUTO = COD_UND_PESO_BRUTO;
    }

    public double getPESO_BRUTO() {
        return PESO_BRUTO;
    }

    public void setPESO_BRUTO(double PESO_BRUTO) {
        this.PESO_BRUTO = PESO_BRUTO;
    }

    public int getTOTAL_BULTOS() {
        return TOTAL_BULTOS;
    }

    public void setTOTAL_BULTOS(int TOTAL_BULTOS) {
        this.TOTAL_BULTOS = TOTAL_BULTOS;
    }

    public String getCOD_MODALIDAD_TRASLADO() {
        return COD_MODALIDAD_TRASLADO;
    }

    public void setCOD_MODALIDAD_TRASLADO(String COD_MODALIDAD_TRASLADO) {
        this.COD_MODALIDAD_TRASLADO = COD_MODALIDAD_TRASLADO;
    }

    public String getFECHA_INICIO() {
        return FECHA_INICIO;
    }

    public void setFECHA_INICIO(String FECHA_INICIO) {
        this.FECHA_INICIO = FECHA_INICIO;
    }

    public String getNRO_DOCUMENTO_TRANSPORTISTA() {
        return NRO_DOCUMENTO_TRANSPORTISTA;
    }

    public void setNRO_DOCUMENTO_TRANSPORTISTA(String NRO_DOCUMENTO_TRANSPORTISTA) {
        this.NRO_DOCUMENTO_TRANSPORTISTA = NRO_DOCUMENTO_TRANSPORTISTA;
    }

    public String getRAZON_SOCIAL_TRANSPORTISTA() {
        return RAZON_SOCIAL_TRANSPORTISTA;
    }

    public void setRAZON_SOCIAL_TRANSPORTISTA(String RAZON_SOCIAL_TRANSPORTISTA) {
        this.RAZON_SOCIAL_TRANSPORTISTA = RAZON_SOCIAL_TRANSPORTISTA;
    }

    public String getTIPO_DOCUMENTO_TRANSPORTISTA() {
        return TIPO_DOCUMENTO_TRANSPORTISTA;
    }

    public void setTIPO_DOCUMENTO_TRANSPORTISTA(String TIPO_DOCUMENTO_TRANSPORTISTA) {
        this.TIPO_DOCUMENTO_TRANSPORTISTA = TIPO_DOCUMENTO_TRANSPORTISTA;
    }

    public String getPLACA_VEHICULO() {
        return PLACA_VEHICULO;
    }

    public void setPLACA_VEHICULO(String PLACA_VEHICULO) {
        this.PLACA_VEHICULO = PLACA_VEHICULO;
    }

    public String getCOD_TIPO_DOC_CHOFER() {
        return COD_TIPO_DOC_CHOFER;
    }

    public void setCOD_TIPO_DOC_CHOFER(String COD_TIPO_DOC_CHOFER) {
        this.COD_TIPO_DOC_CHOFER = COD_TIPO_DOC_CHOFER;
    }

    public String getNRO_DOC_CHOFER() {
        return NRO_DOC_CHOFER;
    }

    public void setNRO_DOC_CHOFER(String NRO_DOC_CHOFER) {
        this.NRO_DOC_CHOFER = NRO_DOC_CHOFER;
    }

    public String getPLACA_CARRETA() {
        return PLACA_CARRETA;
    }

    public void setPLACA_CARRETA(String PLACA_CARRETA) {
        this.PLACA_CARRETA = PLACA_CARRETA;
    }

    public String getNRO_DOCUMENTO_CLIENTE() {
        return NRO_DOCUMENTO_CLIENTE;
    }

    public void setNRO_DOCUMENTO_CLIENTE(String NRO_DOCUMENTO_CLIENTE) {
        this.NRO_DOCUMENTO_CLIENTE = NRO_DOCUMENTO_CLIENTE;
    }

    public String getRAZON_SOCIAL_CLIENTE() {
        return RAZON_SOCIAL_CLIENTE;
    }

    public void setRAZON_SOCIAL_CLIENTE(String RAZON_SOCIAL_CLIENTE) {
        this.RAZON_SOCIAL_CLIENTE = RAZON_SOCIAL_CLIENTE;
    }

    public String getTIPO_DOCUMENTO_CLIENTE() {
        return TIPO_DOCUMENTO_CLIENTE;
    }

    public void setTIPO_DOCUMENTO_CLIENTE(String TIPO_DOCUMENTO_CLIENTE) {
        this.TIPO_DOCUMENTO_CLIENTE = TIPO_DOCUMENTO_CLIENTE;
    }

    public String getNRO_DOCUMENTO_EMPRESA() {
        return NRO_DOCUMENTO_EMPRESA;
    }

    public void setNRO_DOCUMENTO_EMPRESA(String NRO_DOCUMENTO_EMPRESA) {
        this.NRO_DOCUMENTO_EMPRESA = NRO_DOCUMENTO_EMPRESA;
    }

    public String getTIPO_DOCUMENTO_EMPRESA() {
        return TIPO_DOCUMENTO_EMPRESA;
    }

    public void setTIPO_DOCUMENTO_EMPRESA(String TIPO_DOCUMENTO_EMPRESA) {
        this.TIPO_DOCUMENTO_EMPRESA = TIPO_DOCUMENTO_EMPRESA;
    }

    public String getRAZON_SOCIAL_EMPRESA() {
        return RAZON_SOCIAL_EMPRESA;
    }

    public void setRAZON_SOCIAL_EMPRESA(String RAZON_SOCIAL_EMPRESA) {
        this.RAZON_SOCIAL_EMPRESA = RAZON_SOCIAL_EMPRESA;
    }

    public String getCOD_UBIGEO_DESTINO() {
        return COD_UBIGEO_DESTINO;
    }

    public void setCOD_UBIGEO_DESTINO(String COD_UBIGEO_DESTINO) {
        this.COD_UBIGEO_DESTINO = COD_UBIGEO_DESTINO;
    }

    public String getDIRECCION_DESTINO() {
        return DIRECCION_DESTINO;
    }

    public void setDIRECCION_DESTINO(String DIRECCION_DESTINO) {
        this.DIRECCION_DESTINO = DIRECCION_DESTINO;
    }

    public String getCOD_UBIGEO_ORIGEN() {
        return COD_UBIGEO_ORIGEN;
    }

    public void setCOD_UBIGEO_ORIGEN(String COD_UBIGEO_ORIGEN) {
        this.COD_UBIGEO_ORIGEN = COD_UBIGEO_ORIGEN;
    }

    public String getDIRECCION_ORIGEN() {
        return DIRECCION_ORIGEN;
    }

    public void setDIRECCION_ORIGEN(String DIRECCION_ORIGEN) {
        this.DIRECCION_ORIGEN = DIRECCION_ORIGEN;
    }

    public int getTIPO_PROCESO() {
        return TIPO_PROCESO;
    }

    public void setTIPO_PROCESO(int TIPO_PROCESO) {
        this.TIPO_PROCESO = TIPO_PROCESO;
    }

    public String getUSUARIO_SOL_EMPRESA() {
        return USUARIO_SOL_EMPRESA;
    }

    public void setUSUARIO_SOL_EMPRESA(String USUARIO_SOL_EMPRESA) {
        this.USUARIO_SOL_EMPRESA = USUARIO_SOL_EMPRESA;
    }

    public String getPASS_SOL_EMPRESA() {
        return PASS_SOL_EMPRESA;
    }

    public void setPASS_SOL_EMPRESA(String PASS_SOL_EMPRESA) {
        this.PASS_SOL_EMPRESA = PASS_SOL_EMPRESA;
    }

    public String getCONTRA_FIRMA() {
        return CONTRA_FIRMA;
    }

    public void setCONTRA_FIRMA(String CONTRA_FIRMA) {
        this.CONTRA_FIRMA = CONTRA_FIRMA;
    }

    public List<_CpeGuiaRemisionDetalleBean> getDetalle() {
        return Detalle;
    }

    public void setDetalle(List<_CpeGuiaRemisionDetalleBean> Detalle) {
        this.Detalle = Detalle;
    }


	public Integer getIfacturacionPse() {
		return ifacturacionPse;
	}


	public void setIfacturacionPse(Integer ifacturacionPse) {
		this.ifacturacionPse = ifacturacionPse;
	}

}
