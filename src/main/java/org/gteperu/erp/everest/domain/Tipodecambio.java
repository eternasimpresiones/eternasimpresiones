package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Date;
import java.sql.Timestamp;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tipodecambio extends Pagination{

    private Integer idtipocambio;
    private Integer monedaorigen;
    private Integer monedadestino;
    private Double precio;
    private Double compra;
    private Timestamp fecharegistro;
    private Timestamp fechapublicacion;
    private String fechapublicacionStr;
    private String accion;
    private Integer estado;
    private String filtro;
   private String descripcion;
    private Integer idcuenta;
    private Integer tipo;
private Integer dia;
  

	public Integer getDia() {
	return dia;
}

public void setDia(Integer dia) {
	this.dia = dia;
}

	public String getFechapublicacionStr() {
		return fechapublicacionStr;
	}

	public void setFechapublicacionStr(String fechapublicacionStr) {
		this.fechapublicacionStr = fechapublicacionStr;
	}

	public Integer getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(Integer idcuenta) {
        this.idcuenta = idcuenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Double getCompra() {
        return compra;
    }

    public void setCompra(Double compra) {
        this.compra = compra;
    }

    public String getFecharegistroString() {
        return fecharegistroString;
    }

   

    public void setFecharegistroString(String fecharegistroString) {
        this.fecharegistroString = fecharegistroString;
    }
    private String fecharegistroString;

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

    public Integer getIdtipocambio() {
        return idtipocambio;
    }

    public void setIdtipocambio(Integer idtipocambio) {
        this.idtipocambio = idtipocambio;
    }

    public Integer getMonedaorigen() {
        return monedaorigen;
    }

    public void setMonedaorigen(Integer monedaorigen) {
        this.monedaorigen = monedaorigen;
    }

    public Integer getMonedadestino() {
        return monedadestino;
    }

    public void setMonedadestino(Integer monedadestino) {
        this.monedadestino = monedadestino;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Timestamp getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Timestamp fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public Timestamp getFechapublicacion() {
        return fechapublicacion;
    }

    public void setFechapublicacion(Timestamp fechapublicacion) {
        this.fechapublicacion = fechapublicacion;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    
}
