package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tipodetraccion extends Pagination {

    private Integer iidtipodetraccion;
    private String sdescripcion;
    private String scodigo;
    private String sporcentaje;
    
	public Integer getIidtipodetraccion() {
		return iidtipodetraccion;
	}
	public void setIidtipodetraccion(Integer iidtipodetraccion) {
		this.iidtipodetraccion = iidtipodetraccion;
	}
	public String getSdescripcion() {
		return sdescripcion;
	}
	public void setSdescripcion(String sdescripcion) {
		this.sdescripcion = sdescripcion;
	}
	public String getScodigo() {
		return scodigo;
	}
	public void setScodigo(String scodigo) {
		this.scodigo = scodigo;
	}
	public String getSporcentaje() {
		return sporcentaje;
	}
	public void setSporcentaje(String sporcentaje) {
		this.sporcentaje = sporcentaje;
	}

    
}