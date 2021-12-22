package org.gteperu.erp.everest.domain.theme;

public class Migradoc {
private String fecha;
private String serie;
private String numero;
private Integer idserie;
private String ruc;
private String razonsocial;
private Integer idproveedor;
private String glosa;
private Integer correcto;
private Integer moneda;
private Integer idsutipocomprovantepagodocumento;
private String codigosunat;

public String getCodigosunat() {
	return codigosunat;
}
public void setCodigosunat(String codigosunat) {
	this.codigosunat = codigosunat;
}
public Integer getIdsutipocomprovantepagodocumento() {
	return idsutipocomprovantepagodocumento;
}
public void setIdsutipocomprovantepagodocumento(Integer idsutipocomprovantepagodocumento) {
	this.idsutipocomprovantepagodocumento = idsutipocomprovantepagodocumento;
}
public Integer getMoneda() {
	return moneda;
}
public void setMoneda(Integer moneda) {
	this.moneda = moneda;
}
public Integer getCorrecto() {
	return correcto;
}
public void setCorrecto(Integer correcto) {
	this.correcto = correcto;
}
public String getFecha() {
	return fecha;
}
public void setFecha(String fecha) {
	this.fecha = fecha;
}
public String getSerie() {
	return serie;
}
public void setSerie(String serie) {
	this.serie = serie;
}
public String getNumero() {
	return numero;
}
public void setNumero(String numero) {
	this.numero = numero;
}
public Integer getIdserie() {
	return idserie;
}
public void setIdserie(Integer idserie) {
	this.idserie = idserie;
}
public String getRuc() {
	return ruc;
}
public void setRuc(String ruc) {
	this.ruc = ruc;
}
public String getRazonsocial() {
	return razonsocial;
}
public void setRazonsocial(String razonsocial) {
	this.razonsocial = razonsocial;
}

public Integer getIdproveedor() {
	return idproveedor;
}
public void setIdproveedor(Integer idproveedor) {
	this.idproveedor = idproveedor;
}
public String getGlosa() {
	return glosa;
}
public void setGlosa(String glosa) {
	this.glosa = glosa;
}
public Double getInafecta() {
	return inafecta;
}
public void setInafecta(Double inafecta) {
	this.inafecta = inafecta;
}
public Double getBaseimponible() {
	return baseimponible;
}
public void setBaseimponible(Double baseimponible) {
	this.baseimponible = baseimponible;
}
public Double getImpuesto() {
	return impuesto;
}
public void setImpuesto(Double impuesto) {
	this.impuesto = impuesto;
}
public Double getTotal() {
	return total;
}
public void setTotal(Double total) {
	this.total = total;
}
private Double inafecta;
private Double baseimponible;
private Double impuesto;
private Double total;


}
