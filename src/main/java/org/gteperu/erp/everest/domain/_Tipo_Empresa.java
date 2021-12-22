package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class _Tipo_Empresa {

	
    private Integer id_tipo_empresa;
    private String descripcion;
    private char cod_tipo_empresa;
    
    
    
	public Integer getId_tipo_empresa() {
		return id_tipo_empresa;
	}
	public void setId_tipo_empresa(Integer id_tipo_empresa) {
		this.id_tipo_empresa = id_tipo_empresa;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public char getCod_tipo_empresa() {
		return cod_tipo_empresa;
	}
	public void setCod_tipo_empresa(char cod_tipo_empresa) {
		this.cod_tipo_empresa = cod_tipo_empresa;
	}

    
    

}