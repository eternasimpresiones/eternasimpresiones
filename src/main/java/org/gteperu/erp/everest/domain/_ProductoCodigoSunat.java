package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.sql.Blob;
import java.sql.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class _ProductoCodigoSunat extends Pagination {

    private Integer id_producto_cod_sunat;
    private String codigo_producto_cod_sunat;
    private String descripcion_producto_cod_sunat;
    
	public Integer getId_producto_cod_sunat() {
		return id_producto_cod_sunat;
	}
	public void setId_producto_cod_sunat(Integer id_producto_cod_sunat) {
		this.id_producto_cod_sunat = id_producto_cod_sunat;
	}
	public String getCodigo_producto_cod_sunat() {
		return codigo_producto_cod_sunat;
	}
	public void setCodigo_producto_cod_sunat(String codigo_producto_cod_sunat) {
		this.codigo_producto_cod_sunat = codigo_producto_cod_sunat;
	}
	public String getDescripcion_producto_cod_sunat() {
		return descripcion_producto_cod_sunat;
	}
	public void setDescripcion_producto_cod_sunat(String descripcion_producto_cod_sunat) {
		this.descripcion_producto_cod_sunat = descripcion_producto_cod_sunat;
	}
   
}