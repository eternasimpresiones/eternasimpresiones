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
public class Userempresa {

    private Integer id_user_empresa;
    private String username;
    private Integer id_usuarios;
    private Integer id_empresa;
    private _Company company;
    
	 
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	 
	public Integer getId_user_empresa() {
		return id_user_empresa;
	}
	public void setId_user_empresa(Integer id_user_empresa) {
		this.id_user_empresa = id_user_empresa;
	}
	public Integer getId_usuarios() {
		return id_usuarios;
	}
	public void setId_usuarios(Integer id_usuarios) {
		this.id_usuarios = id_usuarios;
	}
	public Integer getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}
	public _Company getCompany() {
		return company;
	}
	public void setCompany(_Company company) {
		this.company = company;
	}
}
