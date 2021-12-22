package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class _RetencionExcel {
	
	private String nro_doc;
	private String razon_social;
	private String fecha_documento;
	private String nro_comprobante;
	private String descripcion;
	private Double porcentaje_percepcion;
	private Double total_percepcion;
	private Double neto_percepcion;
	private String nota;
	
	public String getNro_doc() {
		return nro_doc;
	}
	public void setNro_doc(String nro_doc) {
		this.nro_doc = nro_doc;
	}
	public String getRazon_social() {
		return razon_social;
	}
	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}
	public String getFecha_documento() {
		return fecha_documento;
	}
	public void setFecha_documento(String fecha_documento) {
		this.fecha_documento = fecha_documento;
	}
	public String getNro_comprobante() {
		return nro_comprobante;
	}
	public void setNro_comprobante(String nro_comprobante) {
		this.nro_comprobante = nro_comprobante;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPorcentaje_percepcion() {
		return porcentaje_percepcion;
	}
	public void setPorcentaje_percepcion(Double porcentaje_percepcion) {
		this.porcentaje_percepcion = porcentaje_percepcion;
	}
	public Double getTotal_percepcion() {
		return total_percepcion;
	}
	public void setTotal_percepcion(Double total_percepcion) {
		this.total_percepcion = total_percepcion;
	}
	public Double getNeto_percepcion() {
		return neto_percepcion;
	}
	public void setNeto_percepcion(Double neto_percepcion) {
		this.neto_percepcion = neto_percepcion;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	
	
}