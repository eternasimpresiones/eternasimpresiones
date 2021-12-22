package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class _Clientes extends Pagination {

	private Integer id_cliente;
	private Integer id_empresa;

	private String tipo_doc;
	private String nro_doc;
	private String razon_social;
	private String razon_comercial;
	private String email;
	private String direccion_fiscal;
	private Integer idubigeo;
	private Ubigeo ubigeo;
	private String movil;
    private String fijo;
    private String cuenta_detraccion;
    private String cod_ubigeo;
	private String direccion_fiscal2;
	private String direccion_fiscal3;
	
	//Association
    private Sutipodocumento Sutipodocumento;
	private String scontrasena;

	public String getScontrasena() {
		return scontrasena;
	}
	public void setScontrasena(String scontrasena) {
		this.scontrasena = scontrasena;
	}
	public String getDireccion_fiscal2() {
		return direccion_fiscal2;
	}
	public void setDireccion_fiscal2(String direccion_fiscal2) {
		this.direccion_fiscal2 = direccion_fiscal2;
	}
	public String getDireccion_fiscal3() {
		return direccion_fiscal3;
	}
	public void setDireccion_fiscal3(String direccion_fiscal3) {
		this.direccion_fiscal3 = direccion_fiscal3;
	}
	public Sutipodocumento getSutipodocumento() {
		return Sutipodocumento;
	}
	public void setSutipodocumento(Sutipodocumento sutipodocumento) {
		Sutipodocumento = sutipodocumento;
	}
	public Integer getIdubigeo() {
		return idubigeo;
	}
	public void setIdubigeo(Integer idubigeo) {
		this.idubigeo = idubigeo;
	}
	public Ubigeo getUbigeo() {
		return ubigeo;
	}
	public void setUbigeo(Ubigeo ubigeo) {
		this.ubigeo = ubigeo;
	}
	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	public Integer getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}
	public String getTipo_doc() {
		return tipo_doc;
	}
	public void setTipo_doc(String tipo_doc) {
		this.tipo_doc = tipo_doc;
	}
	public String getNro_doc() {
		return nro_doc;
	}
	public void setNro_doc(String nro_doc) {
		this.nro_doc = nro_doc;
	}
	public String getRazon_social() {
		return razon_social;
	}
	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}
	public String getRazon_comercial() {
		return razon_comercial;
	}
	public void setRazon_comercial(String razon_comercial) {
		this.razon_comercial = razon_comercial;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDireccion_fiscal() {
		return direccion_fiscal;
	}
	public void setDireccion_fiscal(String direccion_fiscal) {
		this.direccion_fiscal = direccion_fiscal;
	}
	public String getMovil() {
		return movil;
	}
	public void setMovil(String movil) {
		this.movil = movil;
	}
	public String getFijo() {
		return fijo;
	}
	public void setFijo(String fijo) {
		this.fijo = fijo;
	}
	public String getCuenta_detraccion() {
		return cuenta_detraccion;
	}
	public void setCuenta_detraccion(String cuenta_detraccion) {
		this.cuenta_detraccion = cuenta_detraccion;
	}
	public String getCod_ubigeo() {
		return cod_ubigeo;
	}
	public void setCod_ubigeo(String cod_ubigeo) {
		this.cod_ubigeo = cod_ubigeo;
	}
	
}