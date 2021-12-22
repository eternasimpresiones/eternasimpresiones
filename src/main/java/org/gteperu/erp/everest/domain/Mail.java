/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gteperu.erp.everest.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author fchuquilin
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mail {

    private String email;
    private Integer iddoc;
    private Integer valint;
    private Integer vigencia;
    private String msg;
    private String subject;
    private String codigo;
    private String extra;
 	private _Clientes clientes;
	private String fecha;
	
	
	
	
	
    public Integer getVigencia() {
		return vigencia;
	}

	public void setVigencia(Integer vigencia) {
		this.vigencia = vigencia;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public _Clientes getClientes() {
		return clientes;
	}

	public void setClientes(_Clientes clientes) {
		this.clientes = clientes;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	 

	public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getValint() {
		return valint;
	}

	public void setValint(Integer valint) {
		this.valint = valint;
	}

	public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIddoc() {
        return iddoc;
    }

    public void setIddoc(Integer iddoc) {
        this.iddoc = iddoc;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
