package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

 @JsonInclude(JsonInclude.Include.NON_NULL)
 
public class Tokenslicitaciones {

	private Integer idtokenslicitaciones;
	private Integer idproveedor;
	private Integer duracion;
	private String token;
	private String password;
	private _Clientes clientes;
	private String estado;
	private String estadoproceso;
	private Integer idpresupuesto;
	private Timestamp fecharegistro;
	private Timestamp fechaultimoenvio;
 	
	
	public Timestamp getFechaultimoenvio() {
		return fechaultimoenvio;
	}
	public void setFechaultimoenvio(Timestamp fechaultimoenvio) {
		this.fechaultimoenvio = fechaultimoenvio;
	}
	public String getEstadoproceso() {
		return estadoproceso;
	}
	public void setEstadoproceso(String estadoproceso) {
		this.estadoproceso = estadoproceso;
	}
	public _Clientes getClientes() {
		return clientes;
	}
	public void setClientes(_Clientes clientes) {
		this.clientes = clientes;
	}
	 
	public Integer getIdtokenslicitaciones() {
		return idtokenslicitaciones;
	}
	public void setIdtokenslicitaciones(Integer idtokenslicitaciones) {
		this.idtokenslicitaciones = idtokenslicitaciones;
	}
	public Integer getIdproveedor() {
		return idproveedor;
	}
	public void setIdproveedor(Integer idproveedor) {
		this.idproveedor = idproveedor;
	}
	public Integer getDuracion() {
		return duracion;
	}
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getIdpresupuesto() {
		return idpresupuesto;
	}
	public void setIdpresupuesto(Integer idpresupuesto) {
		this.idpresupuesto = idpresupuesto;
	}
	public Timestamp getFecharegistro() {
		return fecharegistro;
	}
	public void setFecharegistro(Timestamp fecharegistro) {
		this.fecharegistro = fecharegistro;
	}
	
	
}

