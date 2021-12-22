package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.sql.Blob;
import java.sql.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Periodo extends Pagination{

    private Integer id_periodo;
    private Integer id_empresa;
    private String mes;
    private String anio;
    private Integer estado;
    private Timestamp fecha_cierre;
    private Timestamp fecha_reapertura;
    
	public Integer getId_periodo() {
		return id_periodo;
	}
	public void setId_periodo(Integer id_periodo) {
		this.id_periodo = id_periodo;
	}
	public Integer getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public Timestamp getFecha_cierre() {
		return fecha_cierre;
	}
	public void setFecha_cierre(Timestamp fecha_reapertura) {
		this.fecha_cierre = fecha_reapertura;
	}
	public Timestamp getFecha_reapertura() {
		return fecha_reapertura;
	}
	public void setFecha_reapertura(Timestamp fecha_reapertura) {
		this.fecha_reapertura = fecha_reapertura;
	}

}