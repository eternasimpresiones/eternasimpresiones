package org.gteperu.erp.everest.domain;

public class PorcentajeDetraccion {

	
	private Integer id_porcentaje_detraccion;
	private String descripcion;
	private Double porcentaje;
	
	private String accion;
	
	
	
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public Integer getId_porcentaje_detraccion() {
		return id_porcentaje_detraccion;
	}
	public void setId_porcentaje_detraccion(Integer id_porcentaje_detraccion) {
		this.id_porcentaje_detraccion = id_porcentaje_detraccion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	
	
	
}
