package org.gteperu.erp.everest.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class _TipoDocumento extends Pagination{

    public Integer id_tipo_doc_sunat;
    public String codigo_sunat;
    public String descripcion;
	public Integer getId_tipo_doc_sunat() {
		return id_tipo_doc_sunat;
	}
	public void setId_tipo_doc_sunat(Integer id_tipo_doc_sunat) {
		this.id_tipo_doc_sunat = id_tipo_doc_sunat;
	}
	public String getCodigo_sunat() {
		return codigo_sunat;
	}
	public void setCodigo_sunat(String codigo_sunat) {
		this.codigo_sunat = codigo_sunat;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
  
    
}
