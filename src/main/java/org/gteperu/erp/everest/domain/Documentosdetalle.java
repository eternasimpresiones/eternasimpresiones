package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.sql.Blob;
import java.sql.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Documentosdetalle {

    private Integer iddocumentosdetalle;
    private Integer iddocumentos;
    private Integer idordenguia;
    private Integer idconcepto;
    private String guiacodigo;
    private String guiaserie;
    private String producto;
    private Integer idunidadmedida;
    private String nameUnidad;
    private Double costounitario;
    private Double importe;
    private Double impuesto;
    private Double subtotal;
    private Timestamp fecharegistro;
    private Integer cantidad;
    private Integer cantidadfacturada;
    private Integer cantidadguiada;
    private String accion;
    private Integer estado;
    private Integer estadoguiado;
    private Integer idclasificadorcostos;
    private String nameCCostos;
    private Integer idordencompradetalle;
    private Integer comodineliminado;
    private Integer idoordendecompra;
    private Integer idordenguiadetalle;
    private Integer idnotapedidodetalle;
     private Integer cantidadaguiarofacturar;
    private Integer afectoinafecto;
     private Integer conceptoincluyeigv;
private Integer diasentrega;

    public Integer getDiasentrega() {
	return diasentrega;
}

public void setDiasentrega(Integer diasentrega) {
	this.diasentrega = diasentrega;
}

	public Integer getConceptoincluyeigv() {
        return conceptoincluyeigv;
    }

    public void setConceptoincluyeigv(Integer conceptoincluyeigv) {
        this.conceptoincluyeigv = conceptoincluyeigv;
    }

    public Integer getIdnotapedidodetalle() {
        return idnotapedidodetalle;
    }

    public void setIdnotapedidodetalle(Integer idnotapedidodetalle) {
        this.idnotapedidodetalle = idnotapedidodetalle;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Double impuesto) {
        this.impuesto = impuesto;
    }

    public Integer getAfectoinafecto() {
        return afectoinafecto;
    }

   

    public void setAfectoinafecto(Integer afectoinafecto) {
        this.afectoinafecto = afectoinafecto;
    }

    public Integer getCantidadguiada() {
        return cantidadguiada;
    }

    public void setCantidadguiada(Integer cantidadguiada) {
        this.cantidadguiada = cantidadguiada;
    }

    public Integer getEstadoguiado() {
        return estadoguiado;
    }

    public void setEstadoguiado(Integer estadoguiado) {
        this.estadoguiado = estadoguiado;
    }

    public Integer getCantidadaguiarofacturar() {
        return cantidadaguiarofacturar;
    }

    public void setCantidadaguiarofacturar(Integer cantidadaguiarofacturar) {
        this.cantidadaguiarofacturar = cantidadaguiarofacturar;
    }

    public Documentos getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Documentos documentos) {
        this.documentos = documentos;
    }

    

    public Integer getCantidadfacturada() {
        return cantidadfacturada;
    }

    public void setCantidadfacturada(Integer cantidadfacturada) {
        this.cantidadfacturada = cantidadfacturada;
    }

    public Integer getIdordenguiadetalle() {
        return idordenguiadetalle;
    }

    public void setIdordenguiadetalle(Integer idordenguiadetalle) {
        this.idordenguiadetalle = idordenguiadetalle;
    }

    public String getGuiacodigo() {
        return guiacodigo;
    }

    public void setGuiacodigo(String guiacodigo) {
        this.guiacodigo = guiacodigo;
    }

    public String getGuiaserie() {
        return guiaserie;
    }

    public void setGuiaserie(String guiaserie) {
        this.guiaserie = guiaserie;
    }

    public Integer getIdordenguia() {
        return idordenguia;
    }

    public void setIdordenguia(Integer idordenguia) {
        this.idordenguia = idordenguia;
    }

    public Integer getIdoordendecompra() {
        return idoordendecompra;
    }

    public void setIdoordendecompra(Integer idoordendecompra) {
        this.idoordendecompra = idoordendecompra;
    }

    public Integer getComodineliminado() {
        return comodineliminado;
    }

    public void setComodineliminado(Integer comodineliminado) {
        this.comodineliminado = comodineliminado;
    }

    public Integer getIdordencompradetalle() {
        return idordencompradetalle;
    }

    public void setIdordencompradetalle(Integer idordencompradetalle) {
        this.idordencompradetalle = idordencompradetalle;
    }

    public Integer getIdclasificadorcostos() {
        return idclasificadorcostos;
    }

    public void setIdclasificadorcostos(Integer idclasificadorcostos) {
        this.idclasificadorcostos = idclasificadorcostos;
    }

    public String getNameCCostos() {
        return nameCCostos;
    }

    public void setNameCCostos(String nameCCostos) {
        this.nameCCostos = nameCCostos;
    }

    public String getNameUnidad() {
        return nameUnidad;
    }

    public void setNameUnidad(String nameUnidad) {
        this.nameUnidad = nameUnidad;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public void setIddocumentosdetalle(Integer iddocumentosdetalle) {
        this.iddocumentosdetalle = iddocumentosdetalle;
    }

    public Integer getIddocumentosdetalle() {
        return iddocumentosdetalle;
    }

    public void setIddocumentos(Integer iddocumentos) {
        this.iddocumentos = iddocumentos;
    }

    public Integer getIddocumentos() {
        return iddocumentos;
    }

    public void setIdconcepto(Integer idconcepto) {
        this.idconcepto = idconcepto;
    }

    public Integer getIdconcepto() {
        return idconcepto;
    }

    public void setIdunidadmedida(Integer idunidadmedida) {
        this.idunidadmedida = idunidadmedida;
    }

    public Integer getIdunidadmedida() {
        return idunidadmedida;
    }

    public void setCostounitario(Double costounitario) {
        this.costounitario = costounitario;
    }

    public Double getCostounitario() {
        return costounitario;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Double getImporte() {
        return importe;
    }

    public void setFecharegistro(Timestamp fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public Timestamp getFecharegistro() {
        return fecharegistro;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCantidad() {
        return cantidad;
    }
     private Documentos documentos;
    private Unidadmedida unidadmedida;

     

    public void setFacturas(Documentos documentos) {
        this.documentos = documentos;
    }

    public Documentos getFacturas() {
        return documentos;
    }

    public void setUnidadmedida(Unidadmedida unidadmedida) {
        this.unidadmedida = unidadmedida;
    }

    public Unidadmedida getUnidadmedida() {
        return unidadmedida;
    }
}