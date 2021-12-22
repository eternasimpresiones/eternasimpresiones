package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.sql.Blob;
import java.sql.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Permisos extends Pagination{

    private Integer idpermisos;
    private Integer idempleado;
    private Integer idmotivopermiso;
    private String descripcion;
    private Integer estado;
    private Date fechapermiso;
    private Timestamp fecharegistro;
    private String accion;
    private Integer aprobado;
    private Timestamp fechaaprobacion;
    private Integer usuario;
    private String comentarioaprobacion;

    public String getComentarioaprobacion() {
        return comentarioaprobacion;
    }

    public void setComentarioaprobacion(String comentarioaprobacion) {
        this.comentarioaprobacion = comentarioaprobacion;
    }

    public Integer getAprobado() {
        return aprobado;
    }

    public void setAprobado(Integer aprobado) {
        this.aprobado = aprobado;
    }

    public Timestamp getFechaaprobacion() {
        return fechaaprobacion;
    }

    public void setFechaaprobacion(Timestamp fechaaprobacion) {
        this.fechaaprobacion = fechaaprobacion;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public void setIdpermisos(Integer idpermisos) {
        this.idpermisos = idpermisos;
    }

    public Integer getIdpermisos() {
        return idpermisos;
    }

    public void setIdempleado(Integer idempleado) {
        this.idempleado = idempleado;
    }

    public Integer getIdempleado() {
        return idempleado;
    }

    public void setIdmotivopermiso(Integer idmotivopermiso) {
        this.idmotivopermiso = idmotivopermiso;
    }

    public Integer getIdmotivopermiso() {
        return idmotivopermiso;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getEstado() {
        return estado;
    }

    public Date getFechapermiso() {
        return fechapermiso;
    }

    public void setFechapermiso(Date fechapermiso) {
        this.fechapermiso = fechapermiso;
    }

    public void setFecharegistro(Timestamp fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public Timestamp getFecharegistro() {
        return fecharegistro;
    }
    private Empleado empleado;
 
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    
}