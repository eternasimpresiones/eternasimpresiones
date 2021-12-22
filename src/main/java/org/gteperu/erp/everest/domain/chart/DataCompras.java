package org.gteperu.erp.everest.domain.chart;

public class DataCompras {
	private Integer grupo;
	private String textm;
	private Integer cantidad;
	private String titulo;
	private String totales;
	private Double porcentaje;

	public Integer getGrupo() {
		return grupo;
	}

	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}

	public String getTextm() {
		return textm;
	}

	public void setTextm(String textm) {
		this.textm = textm;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTotales() {
		return totales;
	}

	public void setTotales(String totales) {
		this.totales = totales;
	}

	public Double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}

}
