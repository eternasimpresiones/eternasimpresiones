package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class _Company extends Pagination {

	private Integer ambiente_operacion;
	private Integer max_usuarios;
	private Integer id_empresa;

	private String nro_documento_empresa;
	private String tipo_doc_empresa;
	private String nombre_comercial_empresa;
	private String codigo_ubigeo_empresa;
	private String direccion_empresa;
	private String razon_social_empresa;
	private String usuario_sol_empresa;
	private String pass_sol_empresa;
	private String pass_firma_empresa;
	private Integer idubigeo;
	private String cod_ubigeo;
	private String telefono;
	private String website;
	private String celular;
	private String email;
	private Integer iidtiporeporte;

	private Integer idempresa;

	public Integer getIdempresa() {
		return idempresa;
	}

	public void setIdempresa(Integer idempresa) {
		this.idempresa = idempresa;
	}

	public String getCod_ubigeo() {
		return cod_ubigeo;
	}

	public void setCod_ubigeo(String cod_ubigeo) {
		this.cod_ubigeo = cod_ubigeo;
	}

	private Integer estado;
	private String logo;
	private String urlimagen;
	private String urlarchivo;
	private String archivo;
	private String urlfirma;
	private Ubigeo ubigeo;
	private char tipo_empresa;
	private Timestamp fecha_expiracion;
	private Boolean estadoEmpresa;
	private String ruc;
	private Integer ifacturacionpse;

	// Association
	private Sutipodocumento documentoidentidad;

	public Boolean getEstadoEmpresa() {
		return estadoEmpresa;
	}

	public void setEstadoEmpresa(Boolean estadoEmpresa) {
		this.estadoEmpresa = estadoEmpresa;
	}

	public char getTipo_empresa() {
		return tipo_empresa;
	}

	public void setTipo_empresa(char tipo_empresa) {
		this.tipo_empresa = tipo_empresa;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public String getUrlarchivo() {
		return urlarchivo;
	}

	public void setUrlarchivo(String urlarchivo) {
		this.urlarchivo = urlarchivo;
	}

	public String getUrlfirma() {
		return urlfirma;
	}

	public void setUrlfirma(String urlfirma) {
		this.urlfirma = urlfirma;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getUrlimagen() {
		return urlimagen;
	}

	public void setUrlimagen(String urlimagen) {
		this.urlimagen = urlimagen;
	}

	public Ubigeo getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(Ubigeo ubigeo) {
		this.ubigeo = ubigeo;
	}

	public Integer getIdubigeo() {
		return idubigeo;
	}

	public void setIdubigeo(Integer idubigeo) {
		this.idubigeo = idubigeo;
	}

	public String getPass_firma_empresa() {
		return pass_firma_empresa;
	}

	public void setPass_firma_empresa(String pass_firma_empresa) {
		this.pass_firma_empresa = pass_firma_empresa;
	}

	public Integer getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}

	public String getNro_documento_empresa() {
		return nro_documento_empresa;
	}

	public void setNro_documento_empresa(String nro_documento_empresa) {
		this.nro_documento_empresa = nro_documento_empresa;
	}

	public String getTipo_doc_empresa() {
		return tipo_doc_empresa;
	}

	public void setTipo_doc_empresa(String tipo_doc_empresa) {
		this.tipo_doc_empresa = tipo_doc_empresa;
	}

	public String getNombre_comercial_empresa() {
		return nombre_comercial_empresa;
	}

	public void setNombre_comercial_empresa(String nombre_comercial_empresa) {
		this.nombre_comercial_empresa = nombre_comercial_empresa;
	}

	public String getCodigo_ubigeo_empresa() {
		return codigo_ubigeo_empresa;
	}

	public void setCodigo_ubigeo_empresa(String codigo_ubigeo_empresa) {
		this.codigo_ubigeo_empresa = codigo_ubigeo_empresa;
	}

	public String getDireccion_empresa() {
		return direccion_empresa;
	}

	public void setDireccion_empresa(String direccion_empresa) {
		this.direccion_empresa = direccion_empresa;
	}

	public String getRazon_social_empresa() {
		return razon_social_empresa;
	}

	public void setRazon_social_empresa(String razon_social_empresa) {
		this.razon_social_empresa = razon_social_empresa;
	}

	public String getUsuario_sol_empresa() {
		return usuario_sol_empresa;
	}

	public void setUsuario_sol_empresa(String usuario_sol_empresa) {
		this.usuario_sol_empresa = usuario_sol_empresa;
	}

	public String getPass_sol_empresa() {
		return pass_sol_empresa;
	}

	public void setPass_sol_empresa(String pass_sol_empresa) {
		this.pass_sol_empresa = pass_sol_empresa;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Timestamp getFecha_expiracion() {
		return fecha_expiracion;
	}

	public void setFecha_expiracion(Timestamp fecha_expiracion) {
		this.fecha_expiracion = fecha_expiracion;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public Integer getAmbiente_operacion() {
		return ambiente_operacion;
	}

	public void setAmbiente_operacion(Integer ambiente_operacion) {
		this.ambiente_operacion = ambiente_operacion;
	}

	public Integer getIfacturacionpse() {
		return ifacturacionpse;
	}

	public void setIfacturacionpse(Integer ifacturacionpse) {
		this.ifacturacionpse = ifacturacionpse;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getMax_usuarios() {
		return max_usuarios;
	}

	public void setMax_usuarios(Integer max_usuarios) {
		this.max_usuarios = max_usuarios;
	}

	public Sutipodocumento getDocumentoidentidad() {
		return documentoidentidad;
	}

	public void setDocumentoidentidad(Sutipodocumento documentoidentidad) {
		this.documentoidentidad = documentoidentidad;
	}

	public Integer getIidtiporeporte() {
		return iidtiporeporte;
	}

	public void setIidtiporeporte(Integer iidtiporeporte) {
		this.iidtiporeporte = iidtiporeporte;
	}

}