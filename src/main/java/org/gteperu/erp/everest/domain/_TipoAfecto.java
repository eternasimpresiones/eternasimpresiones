
package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class _TipoAfecto {
    public Integer id_tipo_afecto;
    public String codigo_afecto;
    public String descripcion_afecto;
    
	public Integer getId_tipo_afecto() {
		return id_tipo_afecto;
	}
	public void setId_tipo_afecto(Integer id_tipo_afecto) {
		this.id_tipo_afecto = id_tipo_afecto;
	}
	public String getCodigo_afecto() {
		return codigo_afecto;
	}
	public void setCodigo_afecto(String codigo_afecto) {
		this.codigo_afecto = codigo_afecto;
	}
	public String getDescripcion_afecto() {
		return descripcion_afecto;
	}
	public void setDescripcion_afecto(String descripcion_afecto) {
		this.descripcion_afecto = descripcion_afecto;
	}
    
}
