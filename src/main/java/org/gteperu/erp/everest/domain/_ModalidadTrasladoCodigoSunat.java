package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.sql.Blob;
import java.sql.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class _ModalidadTrasladoCodigoSunat extends Pagination {

    private Integer id_modalidad_tras_cod_sunat;
    private String cod_modalidad_tras_cod_sunat;
    private String descripcion_modalidad_tras_cod_sunat;
    
	public Integer getId_modalidad_tras_cod_sunat() {
		return id_modalidad_tras_cod_sunat;
	}
	public void setId_modalidad_tras_cod_sunat(Integer id_modalidad_tras_cod_sunat) {
		this.id_modalidad_tras_cod_sunat = id_modalidad_tras_cod_sunat;
	}
	public String getCod_modalidad_tras_cod_sunat() {
		return cod_modalidad_tras_cod_sunat;
	}
	public void setCod_modalidad_tras_cod_sunat(String cod_modalidad_tras_cod_sunat) {
		this.cod_modalidad_tras_cod_sunat = cod_modalidad_tras_cod_sunat;
	}
	public String getDescripcion_modalidad_tras_cod_sunat() {
		return descripcion_modalidad_tras_cod_sunat;
	}
	public void setDescripcion_modalidad_tras_cod_sunat(String descripcion_modalidad_tras_cod_sunat) {
		this.descripcion_modalidad_tras_cod_sunat = descripcion_modalidad_tras_cod_sunat;
	}
   
}