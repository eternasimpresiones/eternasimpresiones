package org.gteperu.erp.everest.domain;

public class _Producto extends Pagination {

	private Integer id_producto;
	private Integer id_empresa;

	private String codigo_producto_interno;
	private String nombre_producto;
	private String codigo_sunat;
	private String igv_afecto;
	private String isc_afecto;
	private String descripcion;
	private Double valor_precio_venta;
	private Double valor_precio_compra;
	private Integer estado;
	private String desc_sunat;

	private Integer id_unidad_med;
	private Integer id_param_tipomoneda;

	public String getIsc_afecto() {
		return isc_afecto;
	}

	public void setIsc_afecto(String isc_afecto) {
		this.isc_afecto = isc_afecto;
	}

	public Integer getId_producto() {
		return id_producto;
	}

	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}

	public Integer getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}

	public String getCodigo_producto_interno() {
		return codigo_producto_interno;
	}

	public void setCodigo_producto_interno(String codigo_producto_interno) {
		this.codigo_producto_interno = codigo_producto_interno;
	}

	public String getNombre_producto() {
		return nombre_producto;
	}

	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}

	public String getCodigo_sunat() {
		return codigo_sunat;
	}

	public void setCodigo_sunat(String codigo_sunat) {
		this.codigo_sunat = codigo_sunat;
	}

	public String getIgv_afecto() {
		return igv_afecto;
	}

	public void setIgv_afecto(String igv_afecto) {
		this.igv_afecto = igv_afecto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getValor_precio_venta() {
		return valor_precio_venta;
	}

	public void setValor_precio_venta(Double valor_precio_venta) {
		this.valor_precio_venta = valor_precio_venta;
	}

	public Double getValor_precio_compra() {
		return valor_precio_compra;
	}

	public void setValor_precio_compra(Double valor_precio_compra) {
		this.valor_precio_compra = valor_precio_compra;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getDesc_sunat() {
		return desc_sunat;
	}

	public void setDesc_sunat(String desc_sunat) {
		this.desc_sunat = desc_sunat;
	}

	public Integer getId_unidad_med() {
		return id_unidad_med;
	}

	public void setId_unidad_med(Integer id_unidad_med) {
		this.id_unidad_med = id_unidad_med;
	}

	public Integer getId_param_tipomoneda() {
		return id_param_tipomoneda;
	}

	public void setId_param_tipomoneda(Integer id_param_tipomoneda) {
		this.id_param_tipomoneda = id_param_tipomoneda;
	}

}
