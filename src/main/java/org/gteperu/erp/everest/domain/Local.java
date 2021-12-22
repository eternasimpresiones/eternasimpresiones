package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.sql.Blob;
import java.sql.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Local extends Pagination{

    private Integer id;
    private String nombre; 
    private String direccion; 
    private Integer estado;
    private String accion;
  
    private Integer idempresa;
    private Empresa empresa;

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

   

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

 

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

     
}