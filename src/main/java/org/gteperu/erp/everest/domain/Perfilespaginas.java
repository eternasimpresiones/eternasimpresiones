package org.gteperu.erp.everest.domain;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.sql.Blob;
import java.sql.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class	Perfilespaginas{
private	Integer	idperfilespaginas;
private	Integer	id_perfiles;
private	Integer	idpagina;
private	Integer	estado;
private String accion;
private Integer idagrupadormodulos;

    public Integer getIdagrupadormodulos() {
        return idagrupadormodulos;
    }

    public void setIdagrupadormodulos(Integer idagrupadormodulos) {
        this.idagrupadormodulos = idagrupadormodulos;
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

public void	setIdperfilespaginas(Integer	idperfilespaginas){
this.idperfilespaginas=idperfilespaginas;}
public	Integer getIdperfilespaginas(){
return idperfilespaginas;}
 
public Integer getId_perfiles() {
	return id_perfiles;
}

public void setId_perfiles(Integer id_perfiles) {
	this.id_perfiles = id_perfiles;
}

public void	setIdpagina(Integer	idpagina){
this.idpagina=idpagina;}
public	Integer getIdpagina(){
return idpagina;}

private	Pagina	pagina;
private	Perfiles	perfiles;
public void	setPagina(Pagina	pagina){
this.pagina=pagina;}
public	Pagina getPagina(){
return pagina;}
public void	setPerfiles(Perfiles	perfiles){
this.perfiles=perfiles;}
public	Perfiles getPerfiles(){
return perfiles;}

}