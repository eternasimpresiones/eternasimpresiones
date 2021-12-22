package org.gteperu.erp.everest.domain;

public class Sutipodocumento {
	private Integer idsutipodocumento;
	private String codigosunat;
	private String descripcion;
	private String longmax;
	private String abrv;
	private Integer estado;
	private String prefijo_sunat;
	private String descripcionpublica;

	
	

	public String getDescripcionpublica() {
		return descripcionpublica;
	}
	public void setDescripcionpublica(String descripcionpublica) {
		this.descripcionpublica = descripcionpublica;
	}
	public String getPrefijo_sunat() {
		return prefijo_sunat;
	}
	public void setPrefijo_sunat(String prefijo_sunat) {
		this.prefijo_sunat = prefijo_sunat;
	}
	public Integer getIdsutipodocumento() {
		return idsutipodocumento;
	}
	public void setIdsutipodocumento(Integer idsutipodocumento) {
		this.idsutipodocumento = idsutipodocumento;
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
	public String getLongmax() {
		return longmax;
	}
	public void setLongmax(String longmax) {
		this.longmax = longmax;
	}
	public String getAbrv() {
		return abrv;
	}
	public void setAbrv(String abrv) {
		this.abrv = abrv;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	
	
}
