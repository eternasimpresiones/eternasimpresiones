package org.gteperu.erp.everest.domain;

import java.io.Serializable;

public class Sunat_padron implements  Serializable{
	private String  id_sunat_padron;
	private String ruc;
	private String contenido;
	private Integer id_sunat_padron_i;
	private String tabla;
	private Boolean estado;
	
	
	public Sunat_padron() {
	}
	public Sunat_padron(String id_sunat_padron, String ruc, String contenido) {
		this.id_sunat_padron = id_sunat_padron;
		this.ruc = ruc;
		this.contenido = contenido;
	}
	
	
	
	public String getTabla() {
		return tabla;
	}
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	public Integer getId_sunat_padron_i() {
		return id_sunat_padron_i;
	}
	public void setId_sunat_padron_i(Integer id_sunat_padron_i) {
		this.id_sunat_padron_i = id_sunat_padron_i;
	}
	public String getId_sunat_padron() {
		return id_sunat_padron;
	}
	public void setId_sunat_padron(String id_sunat_padron) {
		this.id_sunat_padron = id_sunat_padron;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
}
	