package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Perfiles extends Pagination{

    private Integer id_perfiles;
    private String nombres;
    private Integer estado;
    private String accion;
    private Integer ambito;
    private Integer idagrupadormodulos;
    private Integer idempresa;

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public Integer getIdagrupadormodulos() {
        return idagrupadormodulos;
    }

    public void setIdagrupadormodulos(Integer idagrupadormodulos) {
        this.idagrupadormodulos = idagrupadormodulos;
    }

  

    public Integer getId_perfiles() {
		return id_perfiles;
	}

	public void setId_perfiles(Integer id_perfiles) {
		this.id_perfiles = id_perfiles;
	}

	public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNombres() {
        return nombres;
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

    public void setAmbito(Integer ambito) {
        this.ambito = ambito;
    }

    public Integer getAmbito() {
        return ambito;
    }
}