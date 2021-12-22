package org.gteperu.erp.everest.domain;

import java.sql.Timestamp;

public class _DocumentoCpe_Excel {

	private String serie_comprobante;
	private String nro_comprobante;
	private String descripcion_tipdocumento;
	private String razon_social;
	private String nro_doc;
	private Timestamp fecha_documento;
	private Timestamp fecha_vto;
	private String descripcion_tipoperacion;
	private Double total_exoneradas;
	private Double sub_total;
	private Double total;
	private Double tipo_cambio;
	private String tipo_comprobante_modifica;
	private String nro_documento_modifica;
	private String direccion_fiscal_cliente;
	private Integer idlocal;

	
	 
	public String getDireccion_fiscal_cliente() {
		return direccion_fiscal_cliente;
	}
	public void setDireccion_fiscal_cliente(String direccion_fiscal_cliente) {
		this.direccion_fiscal_cliente = direccion_fiscal_cliente;
	}
	public Integer getIdlocal() {
		return idlocal;
	}
	public void setIdlocal(Integer idlocal) {
		this.idlocal = idlocal;
	}
	public String getSerie_comprobante() {
		return serie_comprobante;
	}
	public void setSerie_comprobante(String serie_comprobante) {
		this.serie_comprobante = serie_comprobante;
	}
	public String getNro_comprobante() {
		return nro_comprobante;
	}
	public void setNro_comprobante(String nro_comprobante) {
		this.nro_comprobante = nro_comprobante;
	}
	public String getDescripcion_tipdocumento() {
		return descripcion_tipdocumento;
	}
	public void setDescripcion_tipdocumento(String descripcion_tipdocumento) {
		this.descripcion_tipdocumento = descripcion_tipdocumento;
	}
	public String getRazon_social() {
		return razon_social;
	}
	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}
	public String getNro_doc() {
		return nro_doc;
	}
	public void setNro_doc(String nro_doc) {
		this.nro_doc = nro_doc;
	}
	public Timestamp getFecha_documento() {
		return fecha_documento;
	}
	public void setFecha_documento(Timestamp fecha_documento) {
		this.fecha_documento = fecha_documento;
	}
	public Timestamp getFecha_vto() {
		return fecha_vto;
	}
	public void setFecha_vto(Timestamp fecha_vto) {
		this.fecha_vto = fecha_vto;
	}
	public String getDescripcion_tipoperacion() {
		return descripcion_tipoperacion;
	}
	public void setDescripcion_tipoperacion(String descripcion_tipoperacion) {
		this.descripcion_tipoperacion = descripcion_tipoperacion;
	}
	public Double getTotal_exoneradas() {
		return total_exoneradas;
	}
	public void setTotal_exoneradas(Double total_exoneradas) {
		this.total_exoneradas = total_exoneradas;
	}
	public Double getSub_total() {
		return sub_total;
	}
	public void setSub_total(Double sub_total) {
		this.sub_total = sub_total;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getTipo_cambio() {
		return tipo_cambio;
	}
	public void setTipo_cambio(Double tipo_cambio) {
		this.tipo_cambio = tipo_cambio;
	}
	public String getTipo_comprobante_modifica() {
		return tipo_comprobante_modifica;
	}
	public void setTipo_comprobante_modifica(String tipo_comprobante_modifica) {
		this.tipo_comprobante_modifica = tipo_comprobante_modifica;
	}
	public String getNro_documento_modifica() {
		return nro_documento_modifica;
	}
	public void setNro_documento_modifica(String nro_documento_modifica) {
		this.nro_documento_modifica = nro_documento_modifica;
	}
	
	
	
}