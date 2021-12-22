
/*
*ˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆ
* Creado por     : Jose Zambrano  (30/09/2016)
* Bean = Cpe_Detalle
*/

package org.gteperu.erp.everest.domain;

import java.util.Date;

public class _DocumentoCpe_DetalleBean {
	private int item;// *
	private String unidad_medida;// *
	private String desc_unidad_med;
	private double cantidad;// *
	private double precio;// *
	private double aumento_unitario;// *
	private double igv_aumento;// *

	private double importe;// *
	private String precio_tipo_codigo;// *
	private double igv;// *
	private double isc;// *
	private String cod_tipo_operacion;// *
	private String codigo;// *
	private String descripcion;// *
	private double precio_sin_impuesto;
	private String cod_sunat;
	private double aumento_valor_unitario;// *

	private Integer id_documento;
	private Integer id_detalle_documento;
	private double total_inafecta;
	private Integer id_empresa;
	private double por_igv;// *

	private Integer id_producto;
	
	

	public String getDesc_unidad_med() {
		return desc_unidad_med;
	}

	public void setDesc_unidad_med(String desc_unidad_med) {
		this.desc_unidad_med = desc_unidad_med;
	}

	private _Producto producto;

	public double getPor_igv() {
		return por_igv;
	}

	public void setPor_igv(double por_igv) {
		this.por_igv = por_igv;
	}

	public double getAumento_unitario() {
		return aumento_unitario;
	}

	public void setAumento_unitario(double aumento_unitario) {
		this.aumento_unitario = aumento_unitario;
	}

	public double getIgv_aumento() {
		return igv_aumento;
	}

	public void setIgv_aumento(double igv_aumento) {
		this.igv_aumento = igv_aumento;
	}

	public double getAumento_valor_unitario() {
		return aumento_valor_unitario;
	}

	public void setAumento_valor_unitario(double aumento_valor_unitario) {
		this.aumento_valor_unitario = aumento_valor_unitario;
	}

	public Integer getId_detalle_documento() {
		return id_detalle_documento;
	}

	public void setId_detalle_documento(Integer id_detalle_documento) {
		this.id_detalle_documento = id_detalle_documento;
	}

	public double getTotal_inafecta() {
		return total_inafecta;
	}

	public void setTotal_inafecta(double total_inafecta) {
		this.total_inafecta = total_inafecta;
	}

	public Integer getId_documento() {
		return id_documento;
	}

	public void setId_documento(Integer id_documento) {
		this.id_documento = id_documento;
	}

	public Integer getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}

	public void setCod_sunat(String cod_sunat) {
		this.cod_sunat = cod_sunat;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public String getUnidad_medida() {
		return unidad_medida;
	}

	public void setUnidad_medida(String unidad_medida) {
		this.unidad_medida = unidad_medida;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getPrecio_tipo_codigo() {
		return precio_tipo_codigo;
	}

	public void setPrecio_tipo_codigo(String precio_tipo_codigo) {
		this.precio_tipo_codigo = precio_tipo_codigo;
	}

	public double getIgv() {
		return igv;
	}

	public void setIgv(double igv) {
		this.igv = igv;
	}

	public double getIsc() {
		return isc;
	}

	public void setIsc(double isc) {
		this.isc = isc;
	}

	public String getCod_tipo_operacion() {
		return cod_tipo_operacion;
	}

	public void setCod_tipo_operacion(String cod_tipo_operacion) {
		this.cod_tipo_operacion = cod_tipo_operacion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio_sin_impuesto() {
		return precio_sin_impuesto;
	}

	public void setPrecio_sin_impuesto(double precio_sin_impuesto) {
		this.precio_sin_impuesto = precio_sin_impuesto;
	}

	public String getCod_sunat() {
		return cod_sunat;
	}

	public Integer getId_producto() {
		return id_producto;
	}

	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}

	public _Producto getProducto() {
		return producto;
	}

	public void setProducto(_Producto producto) {
		this.producto = producto;
	}

}