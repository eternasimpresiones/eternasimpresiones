package org.gteperu.erp.everest.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;

public class PagoEmpresa {
	
	private Integer id_pagoempresa;
	private Integer id_empresa;
	private Integer meses;
	private Timestamp fechainicio;
	private Timestamp fechafin;
	private Double total;
	private Double cancelado;
	private Timestamp fecharegistro;
	
	private Double deuda;
	
	public Integer getId_pagoempresa() {
		return id_pagoempresa;
	}
	public void setId_pagoempresa(Integer id_pagoempresa) {
		this.id_pagoempresa = id_pagoempresa;
	}
	public Integer getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}
	public Integer getMeses() {
		return meses;
	}
	public void setMeses(Integer meses) {
		this.meses = meses;
	}
	public Timestamp getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(Timestamp fechainicio) {
		this.fechainicio = fechainicio;
	}
	public Timestamp getFechafin() {
		return fechafin;
	}
	public void setFechafin(Timestamp fechafin) {
		this.fechafin = fechafin;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getCancelado() {
		return cancelado;
	}
	public void setCancelado(Double cancelado) {
		this.cancelado = cancelado;
	}
	public Timestamp getFecharegistro() {
		return fecharegistro;
	}
	public void setFecharegistro(Timestamp fecharegistro) {
		this.fecharegistro = fecharegistro;
	}
	public Double getDeuda() {
		return deuda;
	}
	public void setDeuda(Double deuda) {
		this.deuda = deuda;
	}
	
}
