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
public class Userperfiles {

    private Integer id_user_perfiles;
    private Integer id_perfiles;
    private Integer id_usuarios;
    
    private String username;
    private Integer estado;
    private String accion;
    private Userempresa userempresa;
    private Perfiles perfiles;
    
	public Perfiles getPerfiles() {
		return perfiles;
	}
	public void setPerfiles(Perfiles perfiles) {
		this.perfiles = perfiles;
	}
	 
	public Integer getId_user_perfiles() {
		return id_user_perfiles;
	}
	public void setId_user_perfiles(Integer id_user_perfiles) {
		this.id_user_perfiles = id_user_perfiles;
	}
	public Integer getId_perfiles() {
		return id_perfiles;
	}
	public void setId_perfiles(Integer id_perfiles) {
		this.id_perfiles = id_perfiles;
	}
	public Integer getId_usuarios() {
		return id_usuarios;
	}
	public void setId_usuarios(Integer id_usuarios) {
		this.id_usuarios = id_usuarios;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public Userempresa getUserempresa() {
		return userempresa;
	}
	public void setUserempresa(Userempresa userempresa) {
		this.userempresa = userempresa;
	}
    
    
}
