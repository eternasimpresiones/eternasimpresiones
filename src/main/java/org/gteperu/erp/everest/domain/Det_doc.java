package org.gteperu.erp.everest.domain;

public class Det_doc {
	
	private Integer id_det_doc;
	private Integer id_doc;
	private Integer id_producto;
	////////////////
	private Double costo_unitario;
	private Double costo_unitario_me;
	private Double costo_unitario_mn;
	//////////////////
	
	private String cuenta_contable;
	private Double cant_regist_docu;
	private _Producto producto;
	
	private Double cant_regist_docu_aux;
	
	private String pdo_mes;
	private String pdo_ano;
	
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
	public Double getCant_regist_docu_aux() {
		return cant_regist_docu_aux;
	}
	public void setCant_regist_docu_aux(Double cant_regist_docu_aux) {
		this.cant_regist_docu_aux = cant_regist_docu_aux;
	}
	public Integer getId_det_doc() {
		return id_det_doc;
	}
	public void setId_det_doc(Integer id_det_doc) {
		this.id_det_doc = id_det_doc;
	}
	public Integer getId_doc() {
		return id_doc;
	}
	public void setId_doc(Integer id_doc) {
		this.id_doc = id_doc;
	}
	public Integer getId_producto() {
		return id_producto;
	}
	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}
	public String getCuenta_contable() {
		return cuenta_contable;
	}
	public void setCuenta_contable(String cuenta_contable) {
		this.cuenta_contable = cuenta_contable;
	}
	public Double getCant_regist_docu() {
		return cant_regist_docu;
	}
	public void setCant_regist_docu(Double cant_regist_docu) {
		this.cant_regist_docu = cant_regist_docu;
	}
	public _Producto getProducto() {
		return producto;
	}
	public void setProducto(_Producto producto) {
		this.producto = producto;
	}
	public Double getCosto_unitario_me() {
		return costo_unitario_me;
	}
	public void setCosto_unitario_me(Double costo_unitario_me) {
		this.costo_unitario_me = costo_unitario_me;
	}
	public Double getCosto_unitario_mn() {
		return costo_unitario_mn;
	}
	public void setCosto_unitario_mn(Double costo_unitario_mn) {
		this.costo_unitario_mn = costo_unitario_mn;
	}
	public Double getCosto_unitario() {
		return costo_unitario;
	}
	public void setCosto_unitario(Double costo_unitario) {
		this.costo_unitario = costo_unitario;
	}
}
