package org.gteperu.erp.everest.domain;

public class Sutipooperacion {
	private Integer id_sutipooperacion;
	private String codigosunat;
	private String descripcion;
	private Integer estado;
	
	
	public Integer getId_sutipooperacion() {
		return id_sutipooperacion;
	}
	public void setId_sutipooperacion(Integer id_sutipooperacion) {
		this.id_sutipooperacion = id_sutipooperacion;
	}
	public String getCodigosunat() {
		return codigosunat;
	}
	public void setCodigosunat(String codigosunat) {
		this.codigosunat = codigosunat;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
}
