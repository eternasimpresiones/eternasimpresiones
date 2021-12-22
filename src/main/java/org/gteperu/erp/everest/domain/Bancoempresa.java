package org.gteperu.erp.everest.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bancoempresa extends Pagination {
     
    private Integer idbancoempresa;
    private Integer idempresa;
    private Integer idbanco;
    private String numerocuenta;
    private String numerocuentainterbancaria;
    private Integer idtipocuentabancaria;
    private Integer idtipomoneda;
     private Timestamp fechaapertura;
    private String fechaaperturastr;
    private Integer estado;
    private Timestamp fecharegistro;
    private String filtro;
    private String accion;
     private Integer ambitocuenta;
     private Integer cantidad;
     private Banco banco;
     private Tipomoneda tipomoneda;
     private Tipocuentabancaria tipocuentabancaria;
     private Boolean breporte;

     
    public Boolean getBreporte() {
		return breporte;
	}

	public void setBreporte(Boolean breporte) {
		this.breporte = breporte;
	}

	public Tipomoneda getTipomoneda() {
		return tipomoneda;
	}

	public void setTipomoneda(Tipomoneda tipomoneda) {
		this.tipomoneda = tipomoneda;
	}

	public Tipocuentabancaria getTipocuentabancaria() {
		return tipocuentabancaria;
	}

	public void setTipocuentabancaria(Tipocuentabancaria tipocuentabancaria) {
		this.tipocuentabancaria = tipocuentabancaria;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getFiltro() {
        return filtro;
    }

    public Integer getAmbitocuenta() {
        return ambitocuenta;
    }

    public void setAmbitocuenta(Integer ambitocuenta) {
        this.ambitocuenta = ambitocuenta;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }
    
    

    public String getFechaaperturastr() {
        return fechaaperturastr;
    }

    public void setFechaaperturastr(String fechaaperturastr) {
        this.fechaaperturastr = fechaaperturastr;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public void setIdbancoempresa(Integer idbancoempresa) {
        this.idbancoempresa = idbancoempresa;
    }

    public Integer getIdbancoempresa() {
        return idbancoempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdbanco(Integer idbanco) {
        this.idbanco = idbanco;
    }

    public Integer getIdbanco() {
        return idbanco;
    }

    public void setNumerocuenta(String numerocuenta) {
        this.numerocuenta = numerocuenta;
    }

    public String getNumerocuenta() {
        return numerocuenta;
    }

    public void setNumerocuentainterbancaria(String numerocuentainterbancaria) {
        this.numerocuentainterbancaria = numerocuentainterbancaria;
    }

    public String getNumerocuentainterbancaria() {
        return numerocuentainterbancaria;
    }

    public void setIdtipocuentabancaria(Integer idtipocuentabancaria) {
        this.idtipocuentabancaria = idtipocuentabancaria;
    }

    public Integer getIdtipocuentabancaria() {
        return idtipocuentabancaria;
    }

    public void setIdtipomoneda(Integer idtipomoneda) {
        this.idtipomoneda = idtipomoneda;
    }

    public Integer getIdtipomoneda() {
        return idtipomoneda;
    }

    

    public void setFechaapertura(Timestamp fechaapertura) {
        this.fechaapertura = fechaapertura;
    }

    public Timestamp getFechaapertura() {
        return fechaapertura;
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
     private Empresa empresa;
   
    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

   
    
}