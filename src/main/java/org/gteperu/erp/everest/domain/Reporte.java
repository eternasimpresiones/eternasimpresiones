package org.gteperu.erp.everest.domain;

public class Reporte {

	private Integer iddocumento;
	private String tipo_comprobante;
	private String cod_tipo_documento;
	private Integer id_local;
	private String keyclient;

	public String getTipo_comprobante() {
		return tipo_comprobante;
	}

	public void setTipo_comprobante(String tipo_comprobante) {
		this.tipo_comprobante = tipo_comprobante;
	}

	public Integer getIddocumento() {
		return iddocumento;
	}

	public void setIddocumento(Integer iddocumento) {
		this.iddocumento = iddocumento;
	}

	public String getCod_tipo_documento() {
		return cod_tipo_documento;
	}

	public Integer getId_local() {
		return id_local;
	}

	public void setId_local(Integer id_local) {
		this.id_local = id_local;
	}

	public void setCod_tipo_documento(String cod_tipo_documento) {
		this.cod_tipo_documento = cod_tipo_documento;
	}

	public String getKeyclient() {
		return keyclient;
	}

	public void setKeyclient(String keyclient) {
		this.keyclient = keyclient;
	}
	
}
