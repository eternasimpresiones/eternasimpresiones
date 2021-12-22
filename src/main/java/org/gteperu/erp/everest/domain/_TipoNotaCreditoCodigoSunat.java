package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class _TipoNotaCreditoCodigoSunat extends Pagination {

    private Integer id_tipo_nota_credito_cod_sunat;
    private String codigo_nota_credito_cod_sunat;
    private String descripcion_nota_credito_cod_sunat;
    
	public Integer getId_tipo_nota_credito_cod_sunat() {
		return id_tipo_nota_credito_cod_sunat;
	}
	public void setId_tipo_nota_credito_cod_sunat(Integer id_tipo_nota_credito_cod_sunat) {
		this.id_tipo_nota_credito_cod_sunat = id_tipo_nota_credito_cod_sunat;
	}
	public String getCodigo_nota_credito_cod_sunat() {
		return codigo_nota_credito_cod_sunat;
	}
	public void setCodigo_nota_credito_cod_sunat(String codigo_nota_credito_cod_sunat) {
		this.codigo_nota_credito_cod_sunat = codigo_nota_credito_cod_sunat;
	}
	public String getDescripcion_nota_credito_cod_sunat() {
		return descripcion_nota_credito_cod_sunat;
	}
	public void setDescripcion_nota_credito_cod_sunat(String descripcion_nota_credito_cod_sunat) {
		this.descripcion_nota_credito_cod_sunat = descripcion_nota_credito_cod_sunat;
	}
    
	
}