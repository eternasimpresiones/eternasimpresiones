package org.gteperu.erp.everest.domain;

import java.sql.Timestamp;

public class TipoCambio {

	private Integer idtipocambio;
	private Double compra;
	private Double venta;
	private Timestamp fechapublicacion;
	private String codsunat;

	public Integer getIdtipocambio() {
		return idtipocambio;
	}

	public void setIdtipocambio(Integer idtipocambio) {
		this.idtipocambio = idtipocambio;
	}

	public Double getCompra() {
		return compra;
	}

	public void setCompra(Double compra) {
		this.compra = compra;
	}

	public Double getVenta() {
		return venta;
	}

	public void setVenta(Double venta) {
		this.venta = venta;
	}

	public Timestamp getFechapublicacion() {
		return fechapublicacion;
	}

	public void setFechapublicacion(Timestamp fechapublicacion) {
		this.fechapublicacion = fechapublicacion;
	}

	public String getCodsunat() {
		return codsunat;
	}

	public void setCodsunat(String codsunat) {
		this.codsunat = codsunat;
	}

}
