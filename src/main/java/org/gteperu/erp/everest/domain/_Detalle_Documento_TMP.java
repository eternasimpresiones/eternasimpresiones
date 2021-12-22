package org.gteperu.erp.everest.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class _Detalle_Documento_TMP {

	private Integer iid_detalle_documento_tmp;
	private Integer iid_documento_tmp;
	private Integer iitem; 
	private Double dcantidad;
	private String sunidad_medida;
	private String scodigo;
	private String sdescripcion;
	private Double dprecio;
	private String scod_tipo_operacion;
	private Double digv;
	private String scod_sunat;

	private Double dimporte;
	
	private Integer iid_empresa;


	public Integer getIid_empresa() {
		return iid_empresa;
	}

	public void setIid_empresa(Integer iid_empresa) {
		this.iid_empresa = iid_empresa;
	}

	public Integer getIid_detalle_documento_tmp() {
		return iid_detalle_documento_tmp;
	}

	public void setIid_detalle_documento_tmp(Integer iid_detalle_documento_tmp) {
		this.iid_detalle_documento_tmp = iid_detalle_documento_tmp;
	}

	public Integer getIid_documento_tmp() {
		return iid_documento_tmp;
	}

	public void setIid_documento_tmp(Integer iid_documento_tmp) {
		this.iid_documento_tmp = iid_documento_tmp;
	}

	public Integer getIitem() {
		return iitem;
	}

	public void setIitem(Integer iitem) {
		this.iitem = iitem;
	}

	public Double getDcantidad() {
		return dcantidad;
	}

	public void setDcantidad(Double dcantidad) {
		this.dcantidad = dcantidad;
	}

	public String getSunidad_medida() {
		return sunidad_medida;
	}

	public void setSunidad_medida(String sunidad_medida) {
		this.sunidad_medida = sunidad_medida;
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

	public Double getDprecio() {
		return dprecio;
	}

	public void setDprecio(Double dprecio) {
		this.dprecio = dprecio;
	}

	public String getScod_tipo_operacion() {
		return scod_tipo_operacion;
	}

	public void setScod_tipo_operacion(String scod_tipo_operacion) {
		this.scod_tipo_operacion = scod_tipo_operacion;
	}

	public Double getDigv() {
		return digv;
	}

	public void setDigv(Double digv) {
		this.digv = digv;
	}

	public String getScod_sunat() {
		return scod_sunat;
	}

	public void setScod_sunat(String scod_sunat) {
		this.scod_sunat = scod_sunat;
	}

	public Double getDimporte() {
		return dimporte;
	}

	public void setDimporte(Double dimporte) {
		this.dimporte = dimporte;
	}


}