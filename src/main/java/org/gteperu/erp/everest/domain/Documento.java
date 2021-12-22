package org.gteperu.erp.everest.domain;

import java.sql.Timestamp;
import java.util.List;

public class Documento {
	private Integer id_doc;
	private Integer id_ingreso;
	private Integer tipo_doc;
	private Integer id_empresa;
	private String cod_prov_cli;
	private String razon_social;
	private String serie_doc;
	private String nro_doc;
	private Integer estado;
	private Integer flg_doc_det;
	private Timestamp fecha_operacion;
	private Timestamp fecha_emision;
	private Timestamp fecha_vcto;
	private String tipo_moneda;
	private Double tipo_cambio;
	private Double tot_doc;
	private Double base_impo;
	private Double total_doc_me;
	private Double total_doc_mn;
	private Timestamp fecha_fin;
	private Timestamp fecha_ini;	
	private List<Det_doc> lsdet_doc;
	private String pdo_mes;
	private String pdo_ano;
	private String dua;
	private Double base_impo_me;
	private Double base_impo_mn;
	private Double costo_prorra;
	private Double total_igv;
	private Double total_isc;
	private Timestamp fecha_ingreso;
	private Integer id_almacen;
	private String direccion_fiscal_cliente;
	private Integer idlocal;
	private Boolean	bdocborrador;
	
	
	public Boolean getBdocborrador() {
		return bdocborrador;
	}
	public void setBdocborrador(Boolean bdocborrador) {
		this.bdocborrador = bdocborrador;
	}
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
	public Integer getId_almacen() {
		return id_almacen;
	}
	public void setId_almacen(Integer id_almacen) {
		this.id_almacen = id_almacen;
	}
	public Double getTotal_igv() {
		return total_igv;
	}
	public Double getTotal_isc() {
		return total_isc;
	}
	public void setTotal_igv(Double total_igv) {
		this.total_igv = total_igv;
	}
	public void setTotal_isc(Double total_isc) {
		this.total_isc = total_isc;
	}
	public Integer getId_doc() {
		return id_doc;
	}
	public void setId_doc(Integer id_doc) {
		this.id_doc = id_doc;
	}
	public Integer getId_ingreso() {
		return id_ingreso;
	}
	public void setId_ingreso(Integer id_ingreso) {
		this.id_ingreso = id_ingreso;
	}
	public Integer getTipo_doc() {
		return tipo_doc;
	}
	public void setTipo_doc(Integer tipo_doc) {
		this.tipo_doc = tipo_doc;
	}
	public Integer getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}
	public String getCod_prov_cli() {
		return cod_prov_cli;
	}
	public void setCod_prov_cli(String cod_prov_cli) {
		this.cod_prov_cli = cod_prov_cli;
	}
	public String getRazon_social() {
		return razon_social;
	}
	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}
	public String getSerie_doc() {
		return serie_doc;
	}
	public void setSerie_doc(String serie_doc) {
		this.serie_doc = serie_doc;
	}
	public String getNro_doc() {
		return nro_doc;
	}
	public void setNro_doc(String nro_doc) {
		this.nro_doc = nro_doc;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public Timestamp getFecha_emision() {
		return fecha_emision;
	}
	public void setFecha_emision(Timestamp fecha_emision) {
		this.fecha_emision = fecha_emision;
	}
	public Timestamp getFecha_vcto() {
		return fecha_vcto;
	}
	public void setFecha_vcto(Timestamp fecha_vcto) {
		this.fecha_vcto = fecha_vcto;
	}
	public Timestamp getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(Timestamp fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public Timestamp getFecha_ini() {
		return fecha_ini;
	}
	public void setFecha_ini(Timestamp fecha_ini) {
		this.fecha_ini = fecha_ini;
	}
	public List<Det_doc> getLsdet_doc() {
		return lsdet_doc;
	}
	public void setLsdet_doc(List<Det_doc> lsdet_doc) {
		this.lsdet_doc = lsdet_doc;
	}
	public Integer getFlg_doc_det() {
		return flg_doc_det;
	}
	public void setFlg_doc_det(Integer flg_doc_det) {
		this.flg_doc_det = flg_doc_det;
	}
	public Timestamp getFecha_operacion() {
		return fecha_operacion;
	}
	public void setFecha_operacion(Timestamp fecha_operacion) {
		this.fecha_operacion = fecha_operacion;
	}
	public String getTipo_moneda() {
		return tipo_moneda;
	}
	public void setTipo_moneda(String tipo_moneda) {
		this.tipo_moneda = tipo_moneda;
	}
	public Double getTipo_cambio() {
		return tipo_cambio;
	}
	public void setTipo_cambio(Double tipo_cambio) {
		this.tipo_cambio = tipo_cambio;
	}
	 
	public Double getTotal_doc_me() {
		return total_doc_me;
	}
	public void setTotal_doc_me(Double total_doc_me) {
		this.total_doc_me = total_doc_me;
	}
	public Double getTotal_doc_mn() {
		return total_doc_mn;
	}
	public void setTotal_doc_mn(Double total_doc_mn) {
		this.total_doc_mn = total_doc_mn;
	}
	public String getPdo_mes() {
		return pdo_mes;
	}
	public void setPdo_mes(String pdo_mes) {
		this.pdo_mes = pdo_mes;
	}
	public String getPdo_ano() {
		return pdo_ano;
	}
	public void setPdo_ano(String pdo_ano) {
		this.pdo_ano = pdo_ano;
	}
	public Double getTot_doc() {
		return tot_doc;
	}
	public void setTot_doc(Double tot_doc) {
		this.tot_doc = tot_doc;
	}
	public Double getBase_impo() {
		return base_impo;
	}
	public void setBase_impo(Double base_impo) {
		this.base_impo = base_impo;
	}
	public String getDua() {
		return dua;
	}
	public void setDua(String dua) {
		this.dua = dua;
	}
	public Double getBase_impo_me() {
		return base_impo_me;
	}
	public void setBase_impo_me(Double base_impo_me) {
		this.base_impo_me = base_impo_me;
	}
	public Double getBase_impo_mn() {
		return base_impo_mn;
	}
	public void setBase_impo_mn(Double base_impo_mn) {
		this.base_impo_mn = base_impo_mn;
	}
	public Double getCosto_prorra() {
		return costo_prorra;
	}
	public void setCosto_prorra(Double costo_prorra) {
		this.costo_prorra = costo_prorra;
	}
	public Timestamp getFecha_ingreso() {
		return fecha_ingreso;
	}
	public void setFecha_ingreso(Timestamp fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}
}

