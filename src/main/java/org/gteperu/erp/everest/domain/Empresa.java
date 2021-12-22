package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Empresa extends Pagination {

    private Integer idempresa;
    private String razonsocial;
    private String ruc;
    private String direccioncomercial;
    private Integer estado;
    private Timestamp fecharegistro;
    private String accion;
    private Empleado empleado;
     private Integer idcuenta;
    private String telefono;
    private String email;
    private String website;
    private String nombrecomercial;
    private String urlimagen;
    private String logo;
     private String celular;
    //private Integer idubigeo;
    //private Ubigeo ubigeo;
    private Integer idsuactividadcomercial;
    private Integer idtipomoneda;
    private Integer idsuregimenempresa;
    private Integer idempresamatriz;
    private Integer idmatriz;
    private List<Empresa> lsEmpresa;
    private List<Empleado> lsEmpleado;
 private String anexo;

    public String getAnexo() {
	return anexo;
}

public void setAnexo(String anexo) {
	this.anexo = anexo;
}

	public String getNombrecomercial() {
		return nombrecomercial;
	}

	public void setNombrecomercial(String nombrecomercial) {
		this.nombrecomercial = nombrecomercial;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public List<Empresa> getLsEmpresa() {
        return lsEmpresa;
    }

    public void setLsEmpresa(List<Empresa> lsEmpresa) {
        this.lsEmpresa = lsEmpresa;
    }

   

    public List<Empleado> getLsEmpleado() {
        return lsEmpleado;
    }

    public void setLsEmpleado(List<Empleado> lsEmpleado) {
        this.lsEmpleado = lsEmpleado;
    }

   

    public Integer getIdsuactividadcomercial() {
        return idsuactividadcomercial;
    }

    public void setIdsuactividadcomercial(Integer idsuactividadcomercial) {
        this.idsuactividadcomercial = idsuactividadcomercial;
    }

    public Integer getIdtipomoneda() {
        return idtipomoneda;
    }

    public void setIdtipomoneda(Integer idtipomoneda) {
        this.idtipomoneda = idtipomoneda;
    }

    public Integer getIdsuregimenempresa() {
        return idsuregimenempresa;
    }

    public void setIdsuregimenempresa(Integer idsuregimenempresa) {
        this.idsuregimenempresa = idsuregimenempresa;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUrlimagen() {
        return urlimagen;
    }

    public void setUrlimagen(String urlimagen) {
        this.urlimagen = urlimagen;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(Integer idcuenta) {
        this.idcuenta = idcuenta;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRuc() {
        return ruc;
    }

    public void setDireccioncomercial(String direccioncomercial) {
        this.direccioncomercial = direccioncomercial;
    }

    public String getDireccioncomercial() {
        return direccioncomercial;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setFecharegistro(Timestamp fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public Timestamp getFecharegistro() {
        return fecharegistro;
    }
 
	public Integer getIdmatriz() {
		return idmatriz;
	}

	public void setIdmatriz(Integer idmatriz) {
		this.idmatriz = idmatriz;
	}

	public Integer getIdempresamatriz() {
		return idempresamatriz;
	}

	public void setIdempresamatriz(Integer idempresamatriz) {
		this.idempresamatriz = idempresamatriz;
	}
}