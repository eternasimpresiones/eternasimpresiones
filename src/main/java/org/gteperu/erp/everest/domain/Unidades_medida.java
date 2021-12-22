package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Unidades_medida {
	
	private Integer id_unidad_med;
	private String desc_unidad_med;
	private String abrv_unidad_med;
	
	public Integer getId_unidad_med() {
		return id_unidad_med;
	}
	public void setId_unidad_med(Integer id_unidad_med) {
		this.id_unidad_med = id_unidad_med;
	}
	public String getDesc_unidad_med() {
		return desc_unidad_med;
	}
	public void setDesc_unidad_med(String desc_unidad_med) {
		this.desc_unidad_med = desc_unidad_med;
	}
	public String getAbrv_unidad_med() {
		return abrv_unidad_med;
	}
	public void setAbrv_unidad_med(String abrv_unidad_med) {
		this.abrv_unidad_med = abrv_unidad_med;
	}

}
