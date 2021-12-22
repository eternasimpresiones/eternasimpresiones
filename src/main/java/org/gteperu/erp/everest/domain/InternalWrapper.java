package org.gteperu.erp.everest.domain;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class InternalWrapper {
	
 private Map<Integer,Double> lspro_cu;
	private Double costo_sndet_total;
	private Double tipocambio;
 	 private List<Documento> lsdocu;
	private String tipomon;
	private Documento documento;
	private String tipo_moneda_provision;
	private Timestamp fecha_salida_pivote;
	private Integer		id_producto;
	private Integer		id_almacen;
	private Timestamp	fecha_operacion;
	private String		moneda_operacion;
	
	
	
	
	 
	public Integer getId_producto() {
		return id_producto;
	}
	public Integer getId_almacen() {
		return id_almacen;
	}
	public Timestamp getFecha_operacion() {
		return fecha_operacion;
	}
	public String getMoneda_operacion() {
		return moneda_operacion;
	}
	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}
	public void setId_almacen(Integer id_almacen) {
		this.id_almacen = id_almacen;
	}
	public void setFecha_operacion(Timestamp fecha_operacion) {
		this.fecha_operacion = fecha_operacion;
	}
	public void setMoneda_operacion(String moneda_operacion) {
		this.moneda_operacion = moneda_operacion;
	}
	public Timestamp getFecha_salida_pivote() {
		return fecha_salida_pivote;
	}
	public void setFecha_salida_pivote(Timestamp fecha_salida_pivote) {
		this.fecha_salida_pivote = fecha_salida_pivote;
	}
	public String getTipo_moneda_provision() {
		return tipo_moneda_provision;
	}
	public void setTipo_moneda_provision(String tipo_moneda_provision) {
		this.tipo_moneda_provision = tipo_moneda_provision;
	}
	 
	public Double getCosto_sndet_total() {
		return costo_sndet_total;
	}
	public void setCosto_sndet_total(Double costo_sndet_total) {
		this.costo_sndet_total = costo_sndet_total;
	}
	public Map<Integer, Double> getLspro_cu() {
		return lspro_cu;
	}
	public void setLspro_cu(Map<Integer, Double> lspro_cu) {
		this.lspro_cu = lspro_cu;
	}
	public Double getTipocambio() {
		return tipocambio;
	}
	public void setTipocambio(Double tipocambio) {
		this.tipocambio = tipocambio;
	}
	 
	public List<Documento> getLsdocu() {
		return lsdocu;
	}
	public void setLsdocu(List<Documento> lsdocu) {
		this.lsdocu = lsdocu;
	}
	public String getTipomon() {
		return tipomon;
	}
	public void setTipomon(String tipomon) {
		this.tipomon = tipomon;
	}
	public Documento getDocumento() {
		return documento;
	}
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}	
}
