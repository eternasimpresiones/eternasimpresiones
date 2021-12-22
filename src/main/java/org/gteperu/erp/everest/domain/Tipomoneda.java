package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.sql.Blob;
import java.sql.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tipomoneda extends Pagination {
	
	private Integer idtipomoneda;
	private String descripcion;
	private Integer estado;
	private Timestamp fecharegistro;
	private String simbolo;
	private String accion;
	private String codigoalfabetico;

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public void setIdtipomoneda(Integer idtipomoneda) {
		this.idtipomoneda = idtipomoneda;
	}

	public Integer getIdtipomoneda() {
		return idtipomoneda;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setFecharegistro(Timestamp fecharegistro) {
		this.fecharegistro = fecharegistro;
	}

	public Timestamp getFecharegistro() {
		return fecharegistro;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public String getCodigoalfabetico() {
		return codigoalfabetico;
	}

	public void setCodigoalfabetico(String codigoalfabetico) {
		this.codigoalfabetico = codigoalfabetico;
	}

}