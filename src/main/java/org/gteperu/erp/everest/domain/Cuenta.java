package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cuenta extends Pagination {

    public Integer idcuenta;
    public String nombrecuenta;
    public Integer tipocuenta;
    public Timestamp fechainicio;
    public String fechainiciostr;
    public Timestamp fechatermino;
    public String fechaterminostr;
    public Integer estado;
    public Integer nroempresas;
    public Integer nrousuarios;
    public String accion;
    public List<Empresa> lsEmpresa;
    private String telefono;
    private Integer idplanescuenta;
    private Integer idubigeo;
    private Empresa empresa;
    private Integer estadoregistro;
    private Integer tipoplan;
    private String rucmatriz;
    private String email;
    private String contacto;
    

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public Integer getTipoplan() {
        return tipoplan;
    }

    public void setTipoplan(Integer tipoplan) {
        this.tipoplan = tipoplan;
    }

    public String getRucmatriz() {
		return rucmatriz;
	}

	public void setRucmatriz(String rucmatriz) {
		this.rucmatriz = rucmatriz;
	}

    public Integer getEstadoregistro() {
        return estadoregistro;
    }

    public void setEstadoregistro(Integer estadoregistro) {
        this.estadoregistro = estadoregistro;
    }

    public List<Empresa> getLsEmpresa() {
        return lsEmpresa;
    }

    public void setLsEmpresa(List<Empresa> lsEmpresa) {
        this.lsEmpresa = lsEmpresa;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Integer getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(Integer idcuenta) {
        this.idcuenta = idcuenta;
    }

    public String getNombrecuenta() {
        return nombrecuenta;
    }

    public void setNombrecuenta(String nombrecuenta) {
        this.nombrecuenta = nombrecuenta;
    }

    public Integer getTipocuenta() {
        return tipocuenta;
    }

    public void setTipocuenta(Integer tipocuenta) {
        this.tipocuenta = tipocuenta;
    }

    public Timestamp getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Timestamp fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getFechainiciostr() {
        return fechainiciostr;
    }

    public void setFechainiciostr(String fechainiciostr) {
        this.fechainiciostr = fechainiciostr;
    }

    public Timestamp getFechatermino() {
        return fechatermino;
    }

    public void setFechatermino(Timestamp fechatermino) {
        this.fechatermino = fechatermino;
    }

    public String getFechaterminostr() {
        return fechaterminostr;
    }

    public void setFechaterminostr(String fechaterminostr) {
        this.fechaterminostr = fechaterminostr;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getNroempresas() {
        return nroempresas;
    }

    public void setNroempresas(Integer nroempresas) {
        this.nroempresas = nroempresas;
    }

    public Integer getNrousuarios() {
        return nrousuarios;
    }

    public void setNrousuarios(Integer nrousuarios) {
        this.nrousuarios = nrousuarios;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getIdplanescuenta() {
        return idplanescuenta;
    }

    public void setIdplanescuenta(Integer idplanescuenta) {
        this.idplanescuenta = idplanescuenta;
    }

    public Integer getIdubigeo() {
        return idubigeo;
    }

    public void setIdubigeo(Integer idubigeo) {
        this.idubigeo = idubigeo;
    }
}
