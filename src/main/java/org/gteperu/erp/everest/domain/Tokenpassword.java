package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.sql.Blob;
import java.sql.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tokenpassword {

    private Integer idtokenpassword;
    private Integer idusuario;
    private Timestamp fechahorainicio;
    private Timestamp fechahorafin;
    private String token;
    private String estado;
    private Integer tipo;
    private Timestamp fechaconsumo;

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public void setIdtokenpassword(Integer idtokenpassword) {
        this.idtokenpassword = idtokenpassword;
    }

    public Integer getIdtokenpassword() {
        return idtokenpassword;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setFechahorainicio(Timestamp fechahorainicio) {
        this.fechahorainicio = fechahorainicio;
    }

    public Timestamp getFechahorainicio() {
        return fechahorainicio;
    }

    public void setFechahorafin(Timestamp fechahorafin) {
        this.fechahorafin = fechahorafin;
    }

    public Timestamp getFechahorafin() {
        return fechahorafin;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public Timestamp getFechaconsumo() {
        return fechaconsumo;
    }

    public void setFechaconsumo(Timestamp fechaconsumo) {
        this.fechaconsumo = fechaconsumo;
    }
    private Empleado usuario;

    public void setUsuario(Empleado usuario) {
        this.usuario = usuario;
    }

    public Empleado getUsuario() {
        return usuario;
    }
}