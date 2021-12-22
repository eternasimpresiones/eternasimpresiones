/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

import org.gteperu.erp.everest.domain.theme.Migradoc;

/**
 *
 * @author gteperu
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWrapper {

	Object defaultObj;
	List<?> aaData;
 	List<_Clientes> lsclientes;
 	List<Migradoc> lsMigradocs;
 	private Integer estado;
	private String msg;
	private String token;
	private String page;
	private Integer cantidad;
	private Documentos documento;
	private String pass;
    private Boolean ok;
 	private String tipodeCambio;
 	private byte[] file;
	//Tipo de cambio Sunat
 	
 	//FILES
 	private String file_nombre;
    private List<ConsultaDni> DatosPerson;
    
    private EmpresaSunat empsun;
    private ClienteRuc clienteRuc;

    
    

	public ClienteRuc getClienteRuc() {
		return clienteRuc;
	}

	public void setClienteRuc(ClienteRuc clienteRuc) {
		this.clienteRuc = clienteRuc;
	}

	public EmpresaSunat getEmpsun() {
		return empsun;
	}

	public void setEmpsun(EmpresaSunat empsun) {
		this.empsun = empsun;
	}

	public List<ConsultaDni> getDatosPerson() {
		return DatosPerson;
	}

	public void setDatosPerson(List<ConsultaDni> datosPerson) {
		DatosPerson = datosPerson;
	}

	public String getFile_nombre() {
		return file_nombre;
	}

	public void setFile_nombre(String file_nombre) {
		this.file_nombre = file_nombre;
	}

	public String getTipodeCambio() {
		return tipodeCambio;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public void setTipodeCambio(String tipodeCambio) {
		this.tipodeCambio = tipodeCambio;
	}

 

	public Boolean getOk() {
		return ok;
	}

	public void setOk(Boolean ok) {
		this.ok = ok;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	public Documentos getDocumento() {
		return documento;
	}

	public void setDocumento(Documentos documento) {
		this.documento = documento;
	}

	public List<Migradoc> getLsMigradocs() {
		return lsMigradocs;
	}

	public void setLsMigradocs(List<Migradoc> lsMigradocs) {
		this.lsMigradocs = lsMigradocs;
	}

	 

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	 
	public List<_Clientes> getLsclientes() {
		return lsclientes;
	}

	public void setLsclientes(List<_Clientes> lsclientes) {
		this.lsclientes = lsclientes;
	}

	 

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getDefaultObj() {
		return defaultObj;
	}

	public void setDefaultObj(Object defaultObj) {
		this.defaultObj = defaultObj;
	}

	public List<?> getAaData() {
		return aaData;
	}

	public void setAaData(List<?> aaData) {
		this.aaData = aaData;
	}

}
