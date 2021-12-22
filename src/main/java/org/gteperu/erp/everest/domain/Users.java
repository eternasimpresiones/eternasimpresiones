/**
 *
 */
package org.gteperu.erp.everest.domain;

//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;

/**
 * @author Eduardo
 *
 */
public class Users {

    private String name;
    private String password;
    private String email;
    private String username;
    private Integer id_usuarios;
    private Integer estado;
    private Userperfiles userperfiles;
    
    private String accion;
    private Integer idperfiles;
    private Integer id_empresa;
    private _Company company;
    private Perfiles perfiles;
    private String cod_empresa;
    private String role;


	public Perfiles getPerfil() {
		return perfiles;
	}
	public void setPerfil(Perfiles perfiles) {
		this.perfiles = perfiles;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public _Company getCompany() {
		return company;
	}
	public void setCompany(_Company company) {
		this.company = company;
	}
	public String getCod_empresa() {
		return cod_empresa;
	}
	public void setCod_empresa(String cod_empresa) {
		this.cod_empresa = cod_empresa;
	}
	 
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId_usuarios() {
		return id_usuarios;
	}
	public void setId_usuarios(Integer id_usuarios) {
		this.id_usuarios = id_usuarios;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public Userperfiles getUserperfiles() {
		return userperfiles;
	}
	public void setUserperfiles(Userperfiles userperfiles) {
		this.userperfiles = userperfiles;
	}
	public Perfiles getPerfiles() {
		return perfiles;
	}
	public void setPerfiles(Perfiles perfiles) {
		this.perfiles = perfiles;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public Integer getIdperfiles() {
		return idperfiles;
	}
	public void setIdperfiles(Integer idperfiles) {
		this.idperfiles = idperfiles;
	}
	public Integer getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}

}
