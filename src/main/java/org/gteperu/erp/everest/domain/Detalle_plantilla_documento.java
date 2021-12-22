package org.gteperu.erp.everest.domain;

public class Detalle_plantilla_documento {

	private Integer iid_detalle_plantilla_documento;
	private Integer iid_plantilla_documento;
	private Integer iitem;
	private double dcantidad;
	private double dprecio;
	private double dimporte;
	private String scodigo;
	private String sdescripcion;
	private String scod_tipo_operacion;
	private String sunidad_medida;
	private String desc_unidad_med;
	
	
	public String getDesc_unidad_med() {
		return desc_unidad_med;
	}

	public void setDesc_unidad_med(String desc_unidad_med) {
		this.desc_unidad_med = desc_unidad_med;
	}

	private double iigv;
	private double itotal;

	private Integer id_producto;
	private String snombreproducto;

	public double getIigv() {
		return iigv;
	}

	public void setIigv(double iigv) {
		this.iigv = iigv;
	}

	public double getItotal() {
		return itotal;
	}

	public void setItotal(double itotal) {
		this.itotal = itotal;
	}

	public void setItotal(Integer itotal) {
		this.itotal = itotal;
	}

	public String getSunidad_medida() {
		return sunidad_medida;
	}

	public void setSunidad_medida(String sunidad_medida) {
		this.sunidad_medida = sunidad_medida;
	}

	public Integer getIid_plantilla_documento() {
		return iid_plantilla_documento;
	}

	public void setIid_plantilla_documento(Integer iid_plantilla_documento) {
		this.iid_plantilla_documento = iid_plantilla_documento;
	}

	public Integer getIid_detalle_plantilla_documento() {
		return iid_detalle_plantilla_documento;
	}

	public void setIid_detalle_plantilla_documento(Integer iid_detalle_plantilla_documento) {
		this.iid_detalle_plantilla_documento = iid_detalle_plantilla_documento;
	}

	public Integer getIitem() {
		return iitem;
	}

	public void setIitem(Integer iitem) {
		this.iitem = iitem;
	}

	public double getDcantidad() {
		return dcantidad;
	}

	public void setDcantidad(double dcantidad) {
		this.dcantidad = dcantidad;
	}

	public double getDprecio() {
		return dprecio;
	}

	public void setDprecio(double dprecio) {
		this.dprecio = dprecio;
	}

	public double getDimporte() {
		return dimporte;
	}

	public void setDimporte(double dimporte) {
		this.dimporte = dimporte;
	}

	public String getScodigo() {
		return scodigo;
	}

	public void setScodigo(String scodigo) {
		this.scodigo = scodigo;
	}

	public String getSdescripcion() {
		return sdescripcion;
	}

	public void setSdescripcion(String sdescripcion) {
		this.sdescripcion = sdescripcion;
	}

	public String getScod_tipo_operacion() {
		return scod_tipo_operacion;
	}

	public void setScod_tipo_operacion(String scod_tipo_operacion) {
		this.scod_tipo_operacion = scod_tipo_operacion;
	}

	public Integer getId_producto() {
		return id_producto;
	}

	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}

	public String getSnombreproducto() {
		return snombreproducto;
	}

	public void setSnombreproducto(String snombreproducto) {
		this.snombreproducto = snombreproducto;
	}

}
