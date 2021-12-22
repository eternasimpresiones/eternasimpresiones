package org.gteperu.erp.everest.domain;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.sql.Blob;
import java.sql.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class	Tipocuentabancaria extends Pagination{
private	Integer	idtipocuentabancaria;
private	String	nombre;
private	Integer	estado;
private	Timestamp	fecharegistro;
private String accion;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

public void	setIdtipocuentabancaria(Integer	idtipocuentabancaria){
this.idtipocuentabancaria=idtipocuentabancaria;}
public	Integer getIdtipocuentabancaria(){
return idtipocuentabancaria;}
public void	setNombre(String	nombre){
this.nombre=nombre;}
public	String getNombre(){
return nombre;}
public void	setEstado(Integer	estado){
this.estado=estado;}
public	Integer getEstado(){
return estado;}
public void	setFecharegistro(Timestamp	fecharegistro){
this.fecharegistro=fecharegistro;}
public	Timestamp getFecharegistro(){
return fecharegistro;}

}