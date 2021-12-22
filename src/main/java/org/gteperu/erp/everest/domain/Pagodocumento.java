package org.gteperu.erp.everest.domain;

import java.sql.Timestamp;

public class Pagodocumento {

	private Integer idpagodocumento;
	private String referencia;
	private Double monto;
	private Timestamp fechapago;

	private Integer idmetodopago;
	private Integer iddocumento;

	private Metodopago metodopago;

	public Integer getIdpagodocumento() {
		return idpagodocumento;
	}

	public void setIdpagodocumento(Integer idpagodocumento) {
		this.idpagodocumento = idpagodocumento;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Timestamp getFechapago() {
		return fechapago;
	}

	public void setFechapago(Timestamp fechapago) {
		this.fechapago = fechapago;
	}

	public Integer getIdmetodopago() {
		return idmetodopago;
	}

	public void setIdmetodopago(Integer idmetodopago) {
		this.idmetodopago = idmetodopago;
	}

	public Integer getIddocumento() {
		return iddocumento;
	}

	public void setIddocumento(Integer iddocumento) {
		this.iddocumento = iddocumento;
	}

	public Metodopago getMetodopago() {
		return metodopago;
	}

	public void setMetodopago(Metodopago metodopago) {
		this.metodopago = metodopago;
	}

}
