package org.gteperu.erp.everest.domain;

public class FamiliaProducto {

	private Integer id_familia_producto;
	private String descripcion_familia;
	private String cta_compras;
	private String cta_costo_venta;
	private Double porcentaje_detrac;
	private String cta_mercaderia;
	private Integer id_empresa;
	private String id_cuenta_detracc;

	
	
	
	
	
	public String getId_cuenta_detracc() {
		return id_cuenta_detracc;
	}
	public void setId_cuenta_detracc(String id_cuenta_detracc) {
		this.id_cuenta_detracc = id_cuenta_detracc;
	}
	public Integer getId_familia_producto() {
		return id_familia_producto;
	}
	public void setId_familia_producto(Integer id_familia_producto) {
		this.id_familia_producto = id_familia_producto;
	}
	public String getDescripcion_familia() {
		return descripcion_familia;
	}
	public void setDescripcion_familia(String descripcion_familia) {
		this.descripcion_familia = descripcion_familia;
	}
	 
	public String getCta_compras() {
		return cta_compras;
	}
	public String getCta_costo_venta() {
		return cta_costo_venta;
	}
	public void setCta_compras(String cta_compras) {
		this.cta_compras = cta_compras;
	}
	public void setCta_costo_venta(String cta_costo_venta) {
		this.cta_costo_venta = cta_costo_venta;
	}
	public Double getPorcentaje_detrac() {
		return porcentaje_detrac;
	}
	public void setPorcentaje_detrac(Double porcentaje_detrac) {
		this.porcentaje_detrac = porcentaje_detrac;
	}
	public Integer getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}
	public String getCta_mercaderia() {
		return cta_mercaderia;
	}
	public void setCta_mercaderia(String cta_mercaderia) {
		this.cta_mercaderia = cta_mercaderia;
	}
 
}
