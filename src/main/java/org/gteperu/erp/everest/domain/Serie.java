package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Serie extends Pagination{

	private Integer iidserie;
	private Integer idsutipodocumento;
	private String sserie;
	private String snumero;
	private Sutipodocumento tipodocfacturacion;
	private Integer idlocal;
	private String descripcion;
	

	
 

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getIdlocal() {
		return idlocal;
	}

	public void setIdlocal(Integer idlocal) {
		this.idlocal = idlocal;
	}

	public Integer getIidserie() {
		return iidserie;
	}

	public void setIidserie(Integer iidserie) {
		this.iidserie = iidserie;
	}

 

	public Integer getIdsutipodocumento() {
		return idsutipodocumento;
	}

	public void setIdsutipodocumento(Integer idsutipodocumento) {
		this.idsutipodocumento = idsutipodocumento;
	}

	public String getSserie() {
		return sserie;
	}

	public void setSserie(String sserie) {
		this.sserie = sserie;
	}

	public String getSnumero() {
		return snumero;
	}

	public void setSnumero(String snumero) {
		this.snumero = snumero;
	}

	public Sutipodocumento getTipodocfacturacion() {
		return tipodocfacturacion;
	}

	public void setTipodocfacturacion(Sutipodocumento tipodocfacturacion) {
		this.tipodocfacturacion = tipodocfacturacion;
	}

 

}