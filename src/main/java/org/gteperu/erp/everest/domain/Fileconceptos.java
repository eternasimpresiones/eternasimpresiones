package org.gteperu.erp.everest.domain;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.sql.Blob;
import java.sql.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class	Fileconceptos{
private	Integer	idfileconceptos;
private	Integer	principal;
private	Integer	idfile;
private	Integer	idconceptos;
private	Integer	estado;
public void	setIdfileconceptos(Integer	idfileconceptos){
this.idfileconceptos=idfileconceptos;}
public	Integer getIdfileconceptos(){
return idfileconceptos;}
public void	setPrincipal(Integer	principal){
this.principal=principal;}
public	Integer getPrincipal(){
return principal;}
public void	setIdfile(Integer	idfile){
this.idfile=idfile;}
public	Integer getIdfile(){
return idfile;}
public void	setIdconceptos(Integer	idconceptos){
this.idconceptos=idconceptos;}
public	Integer getIdconceptos(){
return idconceptos;}
public void	setEstado(Integer	estado){
this.estado=estado;}
public	Integer getEstado(){
return estado;}
 private	Files	files;
 
public void	setFiles(Files	files){
this.files=files;}
public	Files getFiles(){
return files;}

}