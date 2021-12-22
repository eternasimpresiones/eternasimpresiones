package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.sql.Blob;
import java.sql.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class _MotivoTrasladoCodigoSunat extends Pagination {

    private Integer id_motivos_tras_cod_sunat;
    private String cod_motivos_tras_cod_sunat;
    private String descripcion_motivos_tras_cod_sunat;
    
	public Integer getId_motivos_tras_cod_sunat() {
		return id_motivos_tras_cod_sunat;
	}
	public void setId_motivos_tras_cod_sunat(Integer id_motivos_tras_cod_sunat) {
		this.id_motivos_tras_cod_sunat = id_motivos_tras_cod_sunat;
	}
	public String getCod_motivos_tras_cod_sunat() {
		return cod_motivos_tras_cod_sunat;
	}
	public void setCod_motivos_tras_cod_sunat(String cod_motivos_tras_cod_sunat) {
		this.cod_motivos_tras_cod_sunat = cod_motivos_tras_cod_sunat;
	}
	public String getDescripcion_motivos_tras_cod_sunat() {
		return descripcion_motivos_tras_cod_sunat;
	}
	public void setDescripcion_motivos_tras_cod_sunat(String descripcion_motivos_tras_cod_sunat) {
		this.descripcion_motivos_tras_cod_sunat = descripcion_motivos_tras_cod_sunat;
	}
    
    
}