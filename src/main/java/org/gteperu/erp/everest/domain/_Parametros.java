package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lowagie.text.pdf.ColumnText;

import java.sql.Timestamp;
import java.util.jar.Attributes.Name;
import java.sql.Blob;
import java.sql.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class _Parametros extends Pagination{

    private Integer id_parametros;
    private String descripcion;
    private String grupo;
    private String codigo;
    private String valor;
    private Integer estado;
    private String nombre;
    private Integer idclinica;
      private String accion;
    private Integer id_empresa;
    private Boolean tipo_cuenta;
	private String tipo1;
	private String tipo2;
	private String tipo3;
	private String tipo4;
	private String tipo5;
	private String name;
 	private String cuenta_contable;

 	
 	
	public String getName() {
		return name;
	}
	public String getCuenta_contable() {
		return cuenta_contable;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCuenta_contable(String cuenta_contable) {
		this.cuenta_contable = cuenta_contable;
	}
	public String getTipo1() {
		return tipo1;
	}
	public String getTipo2() {
		return tipo2;
	}
	public String getTipo3() {
		return tipo3;
	}
	public String getTipo4() {
		return tipo4;
	}
	public String getTipo5() {
		return tipo5;
	}
	public void setTipo1(String tipo1) {
		this.tipo1 = tipo1;
	}
	public void setTipo2(String tipo2) {
		this.tipo2 = tipo2;
	}
	public void setTipo3(String tipo3) {
		this.tipo3 = tipo3;
	}
	public void setTipo4(String tipo4) {
		this.tipo4 = tipo4;
	}
	public void setTipo5(String tipo5) {
		this.tipo5 = tipo5;
	}
	public Boolean getTipo_cuenta() {
		return tipo_cuenta;
	}
	public void setTipo_cuenta(Boolean tipo_cuenta) {
		this.tipo_cuenta = tipo_cuenta;
	}
	public Integer getId_parametros() {
		return id_parametros;
	}
	public void setId_parametros(Integer id_parametros) {
		this.id_parametros = id_parametros;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getIdclinica() {
		return idclinica;
	}
	public void setIdclinica(Integer idclinica) {
		this.idclinica = idclinica;
	}
	 
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public Integer getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}
	
	 

    
}