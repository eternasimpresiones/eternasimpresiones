package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Empleado   implements Serializable {

	private Integer id;
	private String apellido_paterno;
	private String apellido_materno;
	private String nombres;
	private String userId;
	private String email;
	private String password;
	private Integer estado;
	private String accion;
	private Boolean auth;
	private Date fechacumpleanios;
	private String fechacumpleaniosStr;
 	private Integer idarea;
 	private Integer idcargo;
 	private String documentoidentidad;
	private Integer idsutipodocumentoidentidad;
	private Integer estadoregistrado;
	private Integer idempresa;
	private Integer idtipoempleado;
	private List<Empresa> lsEmpresasEmpleado;
 	private Perfiles perfiles;
	private Integer idperfiles;
	private String username;
	private String role;
	private String nombre_perfil;
	

	public String getNombre_perfil() {
		return nombre_perfil;
	}

	public void setNombre_perfil(String nombre_perfil) {
		this.nombre_perfil = nombre_perfil;
	}

	public Integer getIdperfiles() {
		return idperfiles;
	}

	public void setIdperfiles(Integer idperfiles) {
		this.idperfiles = idperfiles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	 
	public List<Empresa> getLsEmpresasEmpleado() {
		return lsEmpresasEmpleado;
	}

	public void setLsEmpresasEmpleado(List<Empresa> lsEmpresasEmpleado) {
		this.lsEmpresasEmpleado = lsEmpresasEmpleado;
	}

	public Integer getIdtipoempleado() {
		return idtipoempleado;
	}

	public void setIdtipoempleado(Integer idtipoempleado) {
		this.idtipoempleado = idtipoempleado;
	}

	private Empresa empresa;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Integer getIdempresa() {
		return idempresa;
	}

	public void setIdempresa(Integer idempresa) {
		this.idempresa = idempresa;
	}

	public Integer getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getAuth() {
		return auth;
	}

	public void setAuth(Boolean auth) {
		this.auth = auth;
	}

	public Integer getEstadoregistrado() {
		return estadoregistrado;
	}

	public void setEstadoregistrado(Integer estadoregistrado) {
		this.estadoregistrado = estadoregistrado;
	}

	public String getDocumentoidentidad() {
		return documentoidentidad;
	}

	public void setDocumentoidentidad(String documentoidentidad) {
		this.documentoidentidad = documentoidentidad;
	}

	 
	public Integer getIdsutipodocumentoidentidad() {
		return idsutipodocumentoidentidad;
	}

	public void setIdsutipodocumentoidentidad(Integer idsutipodocumentoidentidad) {
		this.idsutipodocumentoidentidad = idsutipodocumentoidentidad;
	}

	 

	public Integer getIdarea() {
		return idarea;
	}

	public void setIdarea(Integer idarea) {
		this.idarea = idarea;
	}

 

	public Integer getIdcargo() {
		return idcargo;
	}

	public void setIdcargo(Integer idcargo) {
		this.idcargo = idcargo;
	}

	 

	public Date getFechacumpleanios() {
		return fechacumpleanios;
	}

	public String getFechacumpleaniosStr() {
		return fechacumpleaniosStr;
	}

	public void setFechacumpleaniosStr(String fechacumpleaniosStr) {
		this.fechacumpleaniosStr = fechacumpleaniosStr;
	}

	public void setFechacumpleanios(Date fechacumpleanios) {
		this.fechacumpleanios = fechacumpleanios;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}

	public String getApellido_paterno() {
		return apellido_paterno;
	}

	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}

	public String getApellido_materno() {
		return apellido_materno;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getNombres() {
		return nombres;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public Perfiles getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(Perfiles perfiles) {
		this.perfiles = perfiles;
	}

}
