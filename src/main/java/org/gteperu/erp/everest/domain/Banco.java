package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.sql.Blob;
import java.sql.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Banco extends Pagination {

    private Integer idbanco;
    private String nombre;
    private Integer estado;
    private Timestamp fecharegistro;
    private String accion;
    private String codigosunat;
    private String abreviatura;

    
    public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getCodigosunat() {
		return codigosunat;
	}

	public void setCodigosunat(String codigosunat) {
		this.codigosunat = codigosunat;
	}

	public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public void setIdbanco(Integer idbanco) {
        this.idbanco = idbanco;
    }

    public Integer getIdbanco() {
        return idbanco;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
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
}