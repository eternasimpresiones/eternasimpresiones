package org.gteperu.erp.everest.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Plantilla_documento extends Pagination{

	private Integer iid_plantilla_documento;
	private Integer iid_empresa;
	private Integer iid_cliente;
	private String scod_moneda;
	private String sobservacion;
	private String scod_tipo_documento;
	private String snonmbre;
	private String stipo_operacion;
	private List<Detalle_plantilla_documento> lsDetallePlantillaDocumento;
	private _Clientes Cliente;
	
	
	public _Clientes getCliente() {
		return Cliente;
	}
	public void setCliente(_Clientes cliente) {
		Cliente = cliente;
	}
	public Integer getIid_cliente() {
		return iid_cliente;
	}
	public void setIid_cliente(Integer iid_cliente) {
		this.iid_cliente = iid_cliente;
	}
	public Integer getIid_plantilla_documento() {
		return iid_plantilla_documento;
	}
	public void setIid_plantilla_documento(Integer iid_plantilla_documento) {
		this.iid_plantilla_documento = iid_plantilla_documento;
	}
	public List<Detalle_plantilla_documento> getLsDetallePlantillaDocumento() {
		return lsDetallePlantillaDocumento;
	}
	public void setLsDetallePlantillaDocumento(List<Detalle_plantilla_documento> lsDetallePlantillaDocumento) {
		this.lsDetallePlantillaDocumento = lsDetallePlantillaDocumento;
	}
	
	public String getSnonmbre() {
		return snonmbre;
	}
	public void setSnonmbre(String snonmbre) {
		this.snonmbre = snonmbre;
	}
	public Integer getIid_empresa() {
		return iid_empresa;
	}
	public void setIid_empresa(Integer iid_empresa) {
		this.iid_empresa = iid_empresa;
	}


	public String getScod_moneda() {
		return scod_moneda;
	}
	public void setScod_moneda(String scod_moneda) {
		this.scod_moneda = scod_moneda;
	}
	public String getSobservacion() {
		return sobservacion;
	}
	public void setSobservacion(String sobservacion) {
		this.sobservacion = sobservacion;
	}
	public String getScod_tipo_documento() {
		return scod_tipo_documento;
	}
	public void setScod_tipo_documento(String scod_tipo_documento) {
		this.scod_tipo_documento = scod_tipo_documento;
	}
	public String getStipo_operacion() {
		return stipo_operacion;
	}
	public void setStipo_operacion(String stipo_operacion) {
		this.stipo_operacion = stipo_operacion;
	}
	
	
	
}
