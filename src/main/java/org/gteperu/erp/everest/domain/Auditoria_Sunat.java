package org.gteperu.erp.everest.domain;

import java.sql.Timestamp;

public class Auditoria_Sunat {
	private Integer id_auditoria_sunat;
	private Integer id_documento;
	private Integer id_tipo_operacion;
	private String codigo_respuesta_sunat;
	private String mensaje_respuesta_sunat;
	private String flag_respuesta_sunat;
	private Integer id_usuario;
	private Timestamp fecha_registro;
	
	
	
	public String getFlag_respuesta_sunat() {
		return flag_respuesta_sunat;
	}
	public void setFlag_respuesta_sunat(String flag_respuesta_sunat) {
		this.flag_respuesta_sunat = flag_respuesta_sunat;
	}
	public Integer getId_auditoria_sunat() {
		return id_auditoria_sunat;
	}
	public void setId_auditoria_sunat(Integer id_auditoria_sunat) {
		this.id_auditoria_sunat = id_auditoria_sunat;
	}
	public Integer getId_documento() {
		return id_documento;
	}
	public void setId_documento(Integer id_documento) {
		this.id_documento = id_documento;
	}
	public Integer getId_tipo_operacion() {
		return id_tipo_operacion;
	}
	public void setId_tipo_operacion(Integer id_tipo_operacion) {
		this.id_tipo_operacion = id_tipo_operacion;
	}
	public String getCodigo_respuesta_sunat() {
		return codigo_respuesta_sunat;
	}
	public void setCodigo_respuesta_sunat(String codigo_respuesta_sunat) {
		this.codigo_respuesta_sunat = codigo_respuesta_sunat;
	}
	public String getMensaje_respuesta_sunat() {
		return mensaje_respuesta_sunat;
	}
	public void setMensaje_respuesta_sunat(String mensaje_respuesta_sunat) {
		this.mensaje_respuesta_sunat = mensaje_respuesta_sunat;
	}
	public Integer getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}
	public Timestamp getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(Timestamp fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	
	
}
