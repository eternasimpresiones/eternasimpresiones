package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.sql.Blob;
import java.sql.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Auditoria extends Pagination {

    private Integer idauditoria;
    private String mensaje;
    private String tabla;
    private String accion;
    private Integer idregistro;
    private Timestamp fecharegistro;
    private String fecharegistrostr;
    private Timestamp fechafiltro;
    private String fechafiltrostr;
    private String idempleado;
    private String metodo;
    private String nempleado;
    private Integer estado;
    private Empleado empleado;

    public Integer getIdauditoria() {
        return idauditoria;
    }

    public void setIdauditoria(Integer idauditoria) {
        this.idauditoria = idauditoria;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Integer getIdregistro() {
        return idregistro;
    }

    public void setIdregistro(Integer idregistro) {
        this.idregistro = idregistro;
    }

    public Timestamp getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Timestamp fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public String getFecharegistrostr() {
        return fecharegistrostr;
    }

    public void setFecharegistrostr(String fecharegistrostr) {
        this.fecharegistrostr = fecharegistrostr;
    }

    public Timestamp getFechafiltro() {
        return fechafiltro;
    }

    public void setFechafiltro(Timestamp fechafiltro) {
        this.fechafiltro = fechafiltro;
    }

    public String getFechafiltrostr() {
        return fechafiltrostr;
    }

    public void setFechafiltrostr(String fechafiltrostr) {
        this.fechafiltrostr = fechafiltrostr;
    }

    public String getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(String idempleado) {
        this.idempleado = idempleado;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getNempleado() {
        return nempleado;
    }

    public void setNempleado(String nempleado) {
        this.nempleado = nempleado;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}