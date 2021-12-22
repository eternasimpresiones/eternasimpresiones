package org.gteperu.erp.everest.domain;

import java.sql.Timestamp;

public class Dashboard extends Pagination {
	
	private Integer id_empresa;
	private Integer idlocal;
	private Integer idcliente;

	
	private Double total=0.0;

 	private Timestamp fecha_inicio;
	private Timestamp fecha_fin;
	
	private String fecha_inicio_tmp;
	private String fecha_fin_tmp;

	private String ano;
	private String mes;
	
	private String direccionFiscalCliente;
	private String nroDocCliente;
	private String razonSocialCliente;
	
	
	
	public Integer getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(Integer idcliente) {
		this.idcliente = idcliente;
	}

	public String getDireccionFiscalCliente() {
		return direccionFiscalCliente;
	}

	public void setDireccionFiscalCliente(String direccionFiscalCliente) {
		this.direccionFiscalCliente = direccionFiscalCliente;
	}

	public String getNroDocCliente() {
		return nroDocCliente;
	}

	public void setNroDocCliente(String nroDocCliente) {
		this.nroDocCliente = nroDocCliente;
	}

	public String getRazonSocialCliente() {
		return razonSocialCliente;
	}

	public void setRazonSocialCliente(String razonSocialCliente) {
		this.razonSocialCliente = razonSocialCliente;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}



	public Integer getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}

	public Integer getIdlocal() {
		return idlocal;
	}

	public void setIdlocal(Integer idlocal) {
		this.idlocal = idlocal;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Timestamp getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Timestamp fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Timestamp getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Timestamp fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public String getFecha_inicio_tmp() {
		return fecha_inicio_tmp;
	}

	public void setFecha_inicio_tmp(String fecha_inicio_tmp) {
		this.fecha_inicio_tmp = fecha_inicio_tmp;
	}

	public String getFecha_fin_tmp() {
		return fecha_fin_tmp;
	}

	public void setFecha_fin_tmp(String fecha_fin_tmp) {
		this.fecha_fin_tmp = fecha_fin_tmp;
	}

	@Override
	public String toString() {
		return "Dashboard [id_empresa=" + id_empresa + ", idlocal=" + idlocal + ", fecha_inicio=" 
					+ fecha_inicio + ", fecha_fin=" + fecha_fin + ""
					+ ", ano=" + ano + ", mes=" + mes	 + "]";
	}
	
	 
	
	
}
