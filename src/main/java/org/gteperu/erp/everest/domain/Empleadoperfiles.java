package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.util.List;
import java.sql.Blob;
import java.sql.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Empleadoperfiles {

    private Integer idempleadoperfiles;
    private Integer idperfiles;
    private Integer idempleado;
    private List<Empresa> lsEmpresasmarcadas;
    private Integer estado;
    private String accion;

    public void setIdempleadoperfiles(Integer idempleadoperfiles) {
        this.idempleadoperfiles = idempleadoperfiles;
    }

    public Integer getIdempleadoperfiles() {
        return idempleadoperfiles;
    }

    public void setIdperfiles(Integer idperfiles) {
        this.idperfiles = idperfiles;
    }

    public Integer getIdperfiles() {
        return idperfiles;
    }

    public void setIdempleado(Integer idempleado) {
        this.idempleado = idempleado;
    }

    public Integer getIdempleado() {
        return idempleado;
    }

    public List<Empresa> getLsEmpresasmarcadas() {
		return lsEmpresasmarcadas;
	}

	public void setLsEmpresasmarcadas(List<Empresa> lsEmpresasmarcadas) {
		this.lsEmpresasmarcadas = lsEmpresasmarcadas;
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
    private Empleado empleado;
    private Perfiles perfiles;

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setPerfiles(Perfiles perfiles) {
        this.perfiles = perfiles;
    }

    public Perfiles getPerfiles() {
        return perfiles;
    }
}