/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gteperu.erp.everest.domain;

/**
 *
 * @author jose
 */
public class _CpeGuiaRemisionDetalleBean {
    private int ID_DETALLE;
    private int ID_CABECERA;
    private Integer id_documento;
    private Integer id_empresa;
    private Integer ITEM;
    public String UNIDAD_MEDIDA;
    public double CANTIDAD;
    private int ORDER_ITEM;//TAG (OrderLineReference) se colocara el mismo dato que del item
    public String CODIGO;
    public String DESCRIPCION;
    
    public int getID_DETALLE() {
        return ID_DETALLE;
    }

    public void setID_DETALLE(int ID_DETALLE) {
        this.ID_DETALLE = ID_DETALLE;
    }

    public int getID_CABECERA() {
        return ID_CABECERA;
    }

    public void setID_CABECERA(int ID_CABECERA) {
        this.ID_CABECERA = ID_CABECERA;
    }



    public Integer getId_documento() {
		return id_documento;
	}

	public void setId_documento(Integer id_documento) {
		this.id_documento = id_documento;
	}

	public Integer getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}


	public Integer getITEM() {
		return ITEM;
	}

	public void setITEM(Integer iTEM) {
		ITEM = iTEM;
	}

	public String getUNIDAD_MEDIDA() {
        return UNIDAD_MEDIDA;
    }

    public void setUNIDAD_MEDIDA(String UNIDAD_MEDIDA) {
        this.UNIDAD_MEDIDA = UNIDAD_MEDIDA;
    }

    public double getCANTIDAD() {
        return CANTIDAD;
    }

    public void setCANTIDAD(double CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
    }

    public int getORDER_ITEM() {
        return ORDER_ITEM;
    }

    public void setORDER_ITEM(int ORDER_ITEM) {
        this.ORDER_ITEM = ORDER_ITEM;
    }

    public String getCODIGO() {
        return CODIGO;
    }

    public void setCODIGO(String CODIGO) {
        this.CODIGO = CODIGO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

}
