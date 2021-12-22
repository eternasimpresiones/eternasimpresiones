package org.gteperu.erp.everest.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class _Documento_TMP {

	private Integer iid_documento_tmp;
	private Integer iid_empresa;
	private String sserie_comprobante; 
	private String snro_comprobante;
	private String stipo_documento;
	private String stipo_documento_cliente;
	private String snumero_documento_cliente;
	private String srazon_social_cliente;
	private String sdireccion_cliente;
	private String scod_moneda;
	private Double dsub_total;

	private Double dtotal_inafecta;
	private Double dtotal_exoneradas;
	private Double dtotal_gratuitas;
	private Double dtotal_igv;
	private Double dtotal_percepciones;
	private Double dtotal;
	private Timestamp tfecha_documento;
	private String semail_cliente;
	private String stipo_operacion;
	private String stipo_nota_debito;
	private String stipo_nota_credito;
	private String sdescripcion_motivo;
	private String stipo_comprobante_modifica;
	private String snro_documento_modifica;
	private Timestamp tfecha_documento_referencia;
	private String splaca_vehiculo;
	private Double dtotal_detracciones;
	private Double dporcentaje_detraccion;
	private String smsj_registro;

	private List<_Detalle_Documento_TMP> lsdetalle;

	

	public String getSmsj_registro() {
		return smsj_registro;
	}

	public void setSmsj_registro(String smsj_registro) {
		this.smsj_registro = smsj_registro;
	}

	public Integer getIid_documento_tmp() {
		return iid_documento_tmp;
	}

	public void setIid_documento_tmp(Integer iid_documento_tmp) {
		this.iid_documento_tmp = iid_documento_tmp;
	}

	public Integer getIid_empresa() {
		return iid_empresa;
	}

	public void setIid_empresa(Integer iid_empresa) {
		this.iid_empresa = iid_empresa;
	}

	public String getSserie_comprobante() {
		return sserie_comprobante;
	}

	public void setSserie_comprobante(String sserie_comprobante) {
		this.sserie_comprobante = sserie_comprobante;
	}

	public String getSnro_comprobante() {
		return snro_comprobante;
	}

	public void setSnro_comprobante(String snro_comprobante) {
		this.snro_comprobante = snro_comprobante;
	}

	public String getStipo_documento() {
		return stipo_documento;
	}

	public void setStipo_documento(String stipo_documento) {
		this.stipo_documento = stipo_documento;
	}

	public String getStipo_documento_cliente() {
		return stipo_documento_cliente;
	}

	public void setStipo_documento_cliente(String stipo_documento_cliente) {
		this.stipo_documento_cliente = stipo_documento_cliente;
	}

	public String getSnumero_documento_cliente() {
		return snumero_documento_cliente;
	}

	public void setSnumero_documento_cliente(String snumero_documento_cliente) {
		this.snumero_documento_cliente = snumero_documento_cliente;
	}

	public String getSrazon_social_cliente() {
		return srazon_social_cliente;
	}

	public void setSrazon_social_cliente(String srazon_social_cliente) {
		this.srazon_social_cliente = srazon_social_cliente;
	}

	public String getSdireccion_cliente() {
		return sdireccion_cliente;
	}

	public void setSdireccion_cliente(String sdireccion_cliente) {
		this.sdireccion_cliente = sdireccion_cliente;
	}

	public String getScod_moneda() {
		return scod_moneda;
	}

	public void setScod_moneda(String scod_moneda) {
		this.scod_moneda = scod_moneda;
	}

	public Double getDsub_total() {
		return dsub_total;
	}

	public void setDsub_total(Double dsub_total) {
		this.dsub_total = dsub_total;
	}

	public Double getDtotal_inafecta() {
		return dtotal_inafecta;
	}

	public void setDtotal_inafecta(Double dtotal_inafecta) {
		this.dtotal_inafecta = dtotal_inafecta;
	}

	public Double getDtotal_exoneradas() {
		return dtotal_exoneradas;
	}

	public void setDtotal_exoneradas(Double dtotal_exoneradas) {
		this.dtotal_exoneradas = dtotal_exoneradas;
	}

	public Double getDtotal_gratuitas() {
		return dtotal_gratuitas;
	}

	public void setDtotal_gratuitas(Double dtotal_gratuitas) {
		this.dtotal_gratuitas = dtotal_gratuitas;
	}

	public Double getDtotal_igv() {
		return dtotal_igv;
	}

	public void setDtotal_igv(Double dtotal_igv) {
		this.dtotal_igv = dtotal_igv;
	}

	public Double getDtotal_percepciones() {
		return dtotal_percepciones;
	}

	public void setDtotal_percepciones(Double dtotal_percepciones) {
		this.dtotal_percepciones = dtotal_percepciones;
	}

	public Double getDtotal() {
		return dtotal;
	}

	public void setDtotal(Double dtotal) {
		this.dtotal = dtotal;
	}

	public Timestamp getTfecha_documento() {
		return tfecha_documento;
	}

	public void setTfecha_documento(Timestamp tfecha_documento) {
		this.tfecha_documento = tfecha_documento;
	}

	public String getSemail_cliente() {
		return semail_cliente;
	}

	public void setSemail_cliente(String semail_cliente) {
		this.semail_cliente = semail_cliente;
	}

	public String getStipo_operacion() {
		return stipo_operacion;
	}

	public void setStipo_operacion(String stipo_operacion) {
		this.stipo_operacion = stipo_operacion;
	}

	public String getStipo_nota_debito() {
		return stipo_nota_debito;
	}

	public void setStipo_nota_debito(String stipo_nota_debito) {
		this.stipo_nota_debito = stipo_nota_debito;
	}

	public String getStipo_nota_credito() {
		return stipo_nota_credito;
	}

	public void setStipo_nota_credito(String stipo_nota_credito) {
		this.stipo_nota_credito = stipo_nota_credito;
	}

	public String getSdescripcion_motivo() {
		return sdescripcion_motivo;
	}

	public void setSdescripcion_motivo(String sdescripcion_motivo) {
		this.sdescripcion_motivo = sdescripcion_motivo;
	}

	public String getStipo_comprobante_modifica() {
		return stipo_comprobante_modifica;
	}

	public void setStipo_comprobante_modifica(String stipo_comprobante_modifica) {
		this.stipo_comprobante_modifica = stipo_comprobante_modifica;
	}

	public String getSnro_documento_modifica() {
		return snro_documento_modifica;
	}

	public void setSnro_documento_modifica(String snro_documento_modifica) {
		this.snro_documento_modifica = snro_documento_modifica;
	}

	public Timestamp getTfecha_documento_referencia() {
		return tfecha_documento_referencia;
	}

	public void setTfecha_documento_referencia(Timestamp tfecha_documento_referencia) {
		this.tfecha_documento_referencia = tfecha_documento_referencia;
	}

	public String getSplaca_vehiculo() {
		return splaca_vehiculo;
	}

	public void setSplaca_vehiculo(String splaca_vehiculo) {
		this.splaca_vehiculo = splaca_vehiculo;
	}

	public Double getDtotal_detracciones() {
		return dtotal_detracciones;
	}

	public void setDtotal_detracciones(Double dtotal_detracciones) {
		this.dtotal_detracciones = dtotal_detracciones;
	}

	public Double getDporcentaje_detraccion() {
		return dporcentaje_detraccion;
	}

	public void setDporcentaje_detraccion(Double dporcentaje_detraccion) {
		this.dporcentaje_detraccion = dporcentaje_detraccion;
	}

	public List<_Detalle_Documento_TMP> getLsdetalle() {
		return lsdetalle;
	}

	public void setLsdetalle(List<_Detalle_Documento_TMP> lsdetalle) {
		this.lsdetalle = lsdetalle;
	}

	
	
}