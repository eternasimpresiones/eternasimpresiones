package org.gteperu.erp.everest.domain;

public class Regimen_sunat {

	private Integer id_regimen_sunat;
	private String codigo;
	private String descripcion;
	private Double porcentaje;
	private String grupo;
	
	
	public Integer getId_regimen_sunat() {
		return id_regimen_sunat;
	}
	public void setId_regimen_sunat(Integer id_regimen_sunat) {
		this.id_regimen_sunat = id_regimen_sunat;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	
	
}
