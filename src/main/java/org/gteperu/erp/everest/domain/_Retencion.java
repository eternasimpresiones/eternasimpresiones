package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class _Retencion {
	private Integer ambiente_operacion;

	private Integer id_empresa;
	private Integer id_cliente;
	private Integer id_retencion;

	private String periodo;
	private String cod_tipo_documento;
	private String descripcion_tipo_documento;
	private String numero_documento;
	private String razon_social;
	private String regimen_retencion;
	private String observacion;
	private Timestamp fecha_emision;
	private String fecha_emision_str;
	private Boolean venta_interna;
	private Boolean combustible;
	private Boolean retencion;
	private Boolean contribuyente;
	private List<_DocumentoCpe> lsDocumento;

	private String ruc_empresaEmisora;
	private String userSol_empresaEmisora;
	private String passSol_empresaEmisora;
	private String passFirma_empresaEmisora;
	private Timestamp fecha_documento;// *
	private String fecha_documento_str;
	private String fecha_registro;
	private String serie_comprobante; // *
	private String nro_comprobante; // *
	private String tipo_documento_empresa;
	private String nro_documento_empresa;
	private String nombre_comercial_empresa;
	private String cod_ubigeo_empresa;
	private String direccion_empresa;
	private String zona_urbanizacion_empresa;
	private String departamento_empresa;
	private String provincia_empresa;
	private String distrito_empresa;
	private String cod_pais_empresa;
	private String razon_social_empresa;
	//
	private String tipo_documento_proveedor;
	private String nro_documento_proveedor;
	private String nombre_comercial_proveedor;
	private String direccion_proveedor;
	private String cod_ubigeo_proveedor;
	private String zona_urbanizacion_proveedor;
	private String ciudad_proveedor;
	private String departamento_proveedor;
	private String provincia_proveedor;
	private String distrito_proveedor;
	private String pais_proveedor;
	private String razon_social_proveedor;
	//
	private String tipo_documento_cliente;
	private String nro_documento_cliente;
	private String nombre_comercial_cliente;
	private String direccion_cliente;
	private String cod_ubigeo_cliente;
	private String zona_urbanizacion_cliente;
	private String ciudad_cliente;
	private String departamento_cliente;
	private String provincia_cliente;
	private String distrito_cliente;
	private String pais_cliente;
	private String razon_social_cliente;

	private String tipo_percepcion;
	private double porcentaje_percepcion;
	private double total_percepcion;
	private double neto_percepcion;
	private String moneda_total_percibido;
	private String moneda_total_cobrado;
	//
	private String tipo_retencion;
	private double porcentaje_retencion;
	private String nota;
	private String cod_moneda;
	private double total_retencion;
	private double neto_retencion;
	private String cod_respuesta_sunat;
	private String descripcion_sunat;
	private String hash_cpe;
	private String hash_cdr;
	private int tipo;
	private String correo;
	private int tipo_proceso;
	private String dirDocumentoEmpresaEmisora;
	private Integer estado;

	private _Clientes clientes;
	private List<_Cpe_RetencionPercepcion_DetalleBean> ls_RetencionPercepcion;

	// LISTADO DE PAGINA
	private Integer ind_pag;
	private Integer can_pag;
	private Integer offset;
	private Integer limit;
	private Timestamp fecha_inicio;
	private Timestamp fecha_fin;

	///////////////////

	private String fechaini_str;
	private String fechafin_str;
	private Integer ifacturacionPse;
	private Boolean bdocborrador;

	
	public Boolean getBdocborrador() {
		return bdocborrador;
	}

	public void setBdocborrador(Boolean bdocborrador) {
		this.bdocborrador = bdocborrador;
	}

	public String getFechaini_str() {
		return fechaini_str;
	}

	public void setFechaini_str(String fechaini_str) {
		this.fechaini_str = fechaini_str;
	}

	public String getFechafin_str() {
		return fechafin_str;
	}

	public void setFechafin_str(String fechafin_str) {
		this.fechafin_str = fechafin_str;
	}

	//
	public Integer getInd_pag() {
		return ind_pag;
	}

	public void setInd_pag(Integer ind_pag) {
		this.ind_pag = ind_pag;
	}

	public Integer getCan_pag() {
		return can_pag;
	}

	public void setCan_pag(Integer can_pag) {
		this.can_pag = can_pag;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Timestamp getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Timestamp fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Timestamp getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Timestamp fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public String getFecha_emision_str() {
		return fecha_emision_str;
	}

	public void setFecha_emision_str(String fecha_emision_str) {
		this.fecha_emision_str = fecha_emision_str;
	}

	public String getFecha_documento_str() {
		return fecha_documento_str;
	}

	public void setFecha_documento_str(String fecha_documento_str) {
		this.fecha_documento_str = fecha_documento_str;
	}

	public Integer getId_retencion() {
		return id_retencion;
	}

	public void setId_retencion(Integer id_retencion) {
		this.id_retencion = id_retencion;
	}

	public List<_Cpe_RetencionPercepcion_DetalleBean> getLs_RetencionPercepcion() {
		return ls_RetencionPercepcion;
	}

	public void setLs_RetencionPercepcion(List<_Cpe_RetencionPercepcion_DetalleBean> ls_RetencionPercepcion) {
		this.ls_RetencionPercepcion = ls_RetencionPercepcion;
	}

	public _Clientes getClientes() {
		return clientes;
	}

	public void setClientes(_Clientes clientes) {
		this.clientes = clientes;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getTipo_documento_cliente() {
		return tipo_documento_cliente;
	}

	public void setTipo_documento_cliente(String tipo_documento_cliente) {
		this.tipo_documento_cliente = tipo_documento_cliente;
	}

	public String getNro_documento_cliente() {
		return nro_documento_cliente;
	}

	public void setNro_documento_cliente(String nro_documento_cliente) {
		this.nro_documento_cliente = nro_documento_cliente;
	}

	public String getNombre_comercial_cliente() {
		return nombre_comercial_cliente;
	}

	public void setNombre_comercial_cliente(String nombre_comercial_cliente) {
		this.nombre_comercial_cliente = nombre_comercial_cliente;
	}

	public String getDireccion_cliente() {
		return direccion_cliente;
	}

	public void setDireccion_cliente(String direccion_cliente) {
		this.direccion_cliente = direccion_cliente;
	}

	public String getCod_ubigeo_cliente() {
		return cod_ubigeo_cliente;
	}

	public void setCod_ubigeo_cliente(String cod_ubigeo_cliente) {
		this.cod_ubigeo_cliente = cod_ubigeo_cliente;
	}

	public String getZona_urbanizacion_cliente() {
		return zona_urbanizacion_cliente;
	}

	public void setZona_urbanizacion_cliente(String zona_urbanizacion_cliente) {
		this.zona_urbanizacion_cliente = zona_urbanizacion_cliente;
	}

	public String getCiudad_cliente() {
		return ciudad_cliente;
	}

	public void setCiudad_cliente(String ciudad_cliente) {
		this.ciudad_cliente = ciudad_cliente;
	}

	public String getDepartamento_cliente() {
		return departamento_cliente;
	}

	public void setDepartamento_cliente(String departamento_cliente) {
		this.departamento_cliente = departamento_cliente;
	}

	public String getProvincia_cliente() {
		return provincia_cliente;
	}

	public void setProvincia_cliente(String provincia_cliente) {
		this.provincia_cliente = provincia_cliente;
	}

	public String getDistrito_cliente() {
		return distrito_cliente;
	}

	public void setDistrito_cliente(String distrito_cliente) {
		this.distrito_cliente = distrito_cliente;
	}

	public String getPais_cliente() {
		return pais_cliente;
	}

	public void setPais_cliente(String pais_cliente) {
		this.pais_cliente = pais_cliente;
	}

	public String getRazon_social_cliente() {
		return razon_social_cliente;
	}

	public void setRazon_social_cliente(String razon_social_cliente) {
		this.razon_social_cliente = razon_social_cliente;
	}

	public String getTipo_percepcion() {
		return tipo_percepcion;
	}

	public void setTipo_percepcion(String tipo_percepcion) {
		this.tipo_percepcion = tipo_percepcion;
	}

	public double getPorcentaje_percepcion() {
		return porcentaje_percepcion;
	}

	public void setPorcentaje_percepcion(double porcentaje_percepcion) {
		this.porcentaje_percepcion = porcentaje_percepcion;
	}

	public double getTotal_percepcion() {
		return total_percepcion;
	}

	public void setTotal_percepcion(double total_percepcion) {
		this.total_percepcion = total_percepcion;
	}

	public double getNeto_percepcion() {
		return neto_percepcion;
	}

	public void setNeto_percepcion(double neto_percepcion) {
		this.neto_percepcion = neto_percepcion;
	}

	public String getMoneda_total_percibido() {
		return moneda_total_percibido;
	}

	public void setMoneda_total_percibido(String moneda_total_percibido) {
		this.moneda_total_percibido = moneda_total_percibido;
	}

	public String getMoneda_total_cobrado() {
		return moneda_total_cobrado;
	}

	public void setMoneda_total_cobrado(String moneda_total_cobrado) {
		this.moneda_total_cobrado = moneda_total_cobrado;
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getDirDocumentoEmpresaEmisora() {
		return dirDocumentoEmpresaEmisora;
	}

	public void setDirDocumentoEmpresaEmisora(String dirDocumentoEmpresaEmisora) {
		this.dirDocumentoEmpresaEmisora = dirDocumentoEmpresaEmisora;
	}

	public Integer getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}

	public String getRuc_empresaEmisora() {
		return ruc_empresaEmisora;
	}

	public void setRuc_empresaEmisora(String ruc_empresaEmisora) {
		this.ruc_empresaEmisora = ruc_empresaEmisora;
	}

	public String getUserSol_empresaEmisora() {
		return userSol_empresaEmisora;
	}

	public void setUserSol_empresaEmisora(String userSol_empresaEmisora) {
		this.userSol_empresaEmisora = userSol_empresaEmisora;
	}

	public String getPassSol_empresaEmisora() {
		return passSol_empresaEmisora;
	}

	public void setPassSol_empresaEmisora(String passSol_empresaEmisora) {
		this.passSol_empresaEmisora = passSol_empresaEmisora;
	}

	public String getPassFirma_empresaEmisora() {
		return passFirma_empresaEmisora;
	}

	public void setPassFirma_empresaEmisora(String passFirma_empresaEmisora) {
		this.passFirma_empresaEmisora = passFirma_empresaEmisora;
	}

	public Timestamp getFecha_documento() {
		return fecha_documento;
	}

	public void setFecha_documento(Timestamp fecha_documento) {
		this.fecha_documento = fecha_documento;
	}

	public String getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public String getSerie_comprobante() {
		return serie_comprobante;
	}

	public void setSerie_comprobante(String serie_comprobante) {
		this.serie_comprobante = serie_comprobante;
	}

	public String getNro_comprobante() {
		return nro_comprobante;
	}

	public void setNro_comprobante(String nro_comprobante) {
		this.nro_comprobante = nro_comprobante;
	}

	public String getTipo_documento_empresa() {
		return tipo_documento_empresa;
	}

	public void setTipo_documento_empresa(String tipo_documento_empresa) {
		this.tipo_documento_empresa = tipo_documento_empresa;
	}

	public String getNro_documento_empresa() {
		return nro_documento_empresa;
	}

	public void setNro_documento_empresa(String nro_documento_empresa) {
		this.nro_documento_empresa = nro_documento_empresa;
	}

	public String getNombre_comercial_empresa() {
		return nombre_comercial_empresa;
	}

	public void setNombre_comercial_empresa(String nombre_comercial_empresa) {
		this.nombre_comercial_empresa = nombre_comercial_empresa;
	}

	public String getCod_ubigeo_empresa() {
		return cod_ubigeo_empresa;
	}

	public void setCod_ubigeo_empresa(String cod_ubigeo_empresa) {
		this.cod_ubigeo_empresa = cod_ubigeo_empresa;
	}

	public String getDireccion_empresa() {
		return direccion_empresa;
	}

	public void setDireccion_empresa(String direccion_empresa) {
		this.direccion_empresa = direccion_empresa;
	}

	public String getZona_urbanizacion_empresa() {
		return zona_urbanizacion_empresa;
	}

	public void setZona_urbanizacion_empresa(String zona_urbanizacion_empresa) {
		this.zona_urbanizacion_empresa = zona_urbanizacion_empresa;
	}

	public String getDepartamento_empresa() {
		return departamento_empresa;
	}

	public void setDepartamento_empresa(String departamento_empresa) {
		this.departamento_empresa = departamento_empresa;
	}

	public String getProvincia_empresa() {
		return provincia_empresa;
	}

	public void setProvincia_empresa(String provincia_empresa) {
		this.provincia_empresa = provincia_empresa;
	}

	public String getDistrito_empresa() {
		return distrito_empresa;
	}

	public void setDistrito_empresa(String distrito_empresa) {
		this.distrito_empresa = distrito_empresa;
	}

	public String getCod_pais_empresa() {
		return cod_pais_empresa;
	}

	public void setCod_pais_empresa(String cod_pais_empresa) {
		this.cod_pais_empresa = cod_pais_empresa;
	}

	public String getRazon_social_empresa() {
		return razon_social_empresa;
	}

	public void setRazon_social_empresa(String razon_social_empresa) {
		this.razon_social_empresa = razon_social_empresa;
	}

	public String getTipo_documento_proveedor() {
		return tipo_documento_proveedor;
	}

	public void setTipo_documento_proveedor(String tipo_documento_proveedor) {
		this.tipo_documento_proveedor = tipo_documento_proveedor;
	}

	public String getNro_documento_proveedor() {
		return nro_documento_proveedor;
	}

	public void setNro_documento_proveedor(String nro_documento_proveedor) {
		this.nro_documento_proveedor = nro_documento_proveedor;
	}

	public String getNombre_comercial_proveedor() {
		return nombre_comercial_proveedor;
	}

	public void setNombre_comercial_proveedor(String nombre_comercial_proveedor) {
		this.nombre_comercial_proveedor = nombre_comercial_proveedor;
	}

	public String getDireccion_proveedor() {
		return direccion_proveedor;
	}

	public void setDireccion_proveedor(String direccion_proveedor) {
		this.direccion_proveedor = direccion_proveedor;
	}

	public String getCod_ubigeo_proveedor() {
		return cod_ubigeo_proveedor;
	}

	public void setCod_ubigeo_proveedor(String cod_ubigeo_proveedor) {
		this.cod_ubigeo_proveedor = cod_ubigeo_proveedor;
	}

	public String getZona_urbanizacion_proveedor() {
		return zona_urbanizacion_proveedor;
	}

	public void setZona_urbanizacion_proveedor(String zona_urbanizacion_proveedor) {
		this.zona_urbanizacion_proveedor = zona_urbanizacion_proveedor;
	}

	public String getCiudad_proveedor() {
		return ciudad_proveedor;
	}

	public void setCiudad_proveedor(String ciudad_proveedor) {
		this.ciudad_proveedor = ciudad_proveedor;
	}

	public String getDepartamento_proveedor() {
		return departamento_proveedor;
	}

	public void setDepartamento_proveedor(String departamento_proveedor) {
		this.departamento_proveedor = departamento_proveedor;
	}

	public String getProvincia_proveedor() {
		return provincia_proveedor;
	}

	public void setProvincia_proveedor(String provincia_proveedor) {
		this.provincia_proveedor = provincia_proveedor;
	}

	public String getDistrito_proveedor() {
		return distrito_proveedor;
	}

	public void setDistrito_proveedor(String distrito_proveedor) {
		this.distrito_proveedor = distrito_proveedor;
	}

	public String getPais_proveedor() {
		return pais_proveedor;
	}

	public void setPais_proveedor(String pais_proveedor) {
		this.pais_proveedor = pais_proveedor;
	}

	public String getRazon_social_proveedor() {
		return razon_social_proveedor;
	}

	public void setRazon_social_proveedor(String razon_social_proveedor) {
		this.razon_social_proveedor = razon_social_proveedor;
	}

	public String getTipo_retencion() {
		return tipo_retencion;
	}

	public void setTipo_retencion(String tipo_retencion) {
		this.tipo_retencion = tipo_retencion;
	}

	public double getPorcentaje_retencion() {
		return porcentaje_retencion;
	}

	public void setPorcentaje_retencion(double porcentaje_retencion) {
		this.porcentaje_retencion = porcentaje_retencion;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getCod_moneda() {
		return cod_moneda;
	}

	public void setCod_moneda(String cod_moneda) {
		this.cod_moneda = cod_moneda;
	}

	public double getTotal_retencion() {
		return total_retencion;
	}

	public void setTotal_retencion(double total_retencion) {
		this.total_retencion = total_retencion;
	}

	public double getNeto_retencion() {
		return neto_retencion;
	}

	public void setNeto_retencion(double neto_retencion) {
		this.neto_retencion = neto_retencion;
	}

	public String getCod_respuesta_sunat() {
		return cod_respuesta_sunat;
	}

	public void setCod_respuesta_sunat(String cod_respuesta_sunat) {
		this.cod_respuesta_sunat = cod_respuesta_sunat;
	}

	public String getDescripcion_sunat() {
		return descripcion_sunat;
	}

	public void setDescripcion_sunat(String descripcion_sunat) {
		this.descripcion_sunat = descripcion_sunat;
	}

	public String getHash_cpe() {
		return hash_cpe;
	}

	public void setHash_cpe(String hash_cpe) {
		this.hash_cpe = hash_cpe;
	}

	public String getHash_cdr() {
		return hash_cdr;
	}

	public void setHash_cdr(String hash_cdr) {
		this.hash_cdr = hash_cdr;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getTipo_proceso() {
		return tipo_proceso;
	}

	public void setTipo_proceso(int tipo_proceso) {
		this.tipo_proceso = tipo_proceso;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getCod_tipo_documento() {
		return cod_tipo_documento;
	}

	public void setCod_tipo_documento(String cod_tipo_documento) {
		this.cod_tipo_documento = cod_tipo_documento;
	}

	public String getDescripcion_tipo_documento() {
		return descripcion_tipo_documento;
	}

	public void setDescripcion_tipo_documento(String descripcion_tipo_documento) {
		this.descripcion_tipo_documento = descripcion_tipo_documento;
	}

	public String getNumero_documento() {
		return numero_documento;
	}

	public void setNumero_documento(String numero_documento) {
		this.numero_documento = numero_documento;
	}

	public String getRazon_social() {
		return razon_social;
	}

	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}

	public String getRegimen_retencion() {
		return regimen_retencion;
	}

	public void setRegimen_retencion(String regimen_retencion) {
		this.regimen_retencion = regimen_retencion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Timestamp getFecha_emision() {
		return fecha_emision;
	}

	public void setFecha_emision(Timestamp fecha_emision) {
		this.fecha_emision = fecha_emision;
	}

	public Boolean getVenta_interna() {
		return venta_interna;
	}

	public void setVenta_interna(Boolean venta_interna) {
		this.venta_interna = venta_interna;
	}

	public Boolean getCombustible() {
		return combustible;
	}

	public void setCombustible(Boolean combustible) {
		this.combustible = combustible;
	}

	public Boolean getRetencion() {
		return retencion;
	}

	public void setRetencion(Boolean retencion) {
		this.retencion = retencion;
	}

	public Boolean getContribuyente() {
		return contribuyente;
	}

	public void setContribuyente(Boolean contribuyente) {
		this.contribuyente = contribuyente;
	}

	public List<_DocumentoCpe> getLsDocumento() {
		return lsDocumento;
	}

	public void setLsDocumento(List<_DocumentoCpe> lsDocumento) {
		this.lsDocumento = lsDocumento;
	}

	public Integer getAmbiente_operacion() {
		return ambiente_operacion;
	}

	public void setAmbiente_operacion(Integer ambiente_operacion) {
		this.ambiente_operacion = ambiente_operacion;
	}

	public Integer getIfacturacionPse() {
		return ifacturacionPse;
	}

	public void setIfacturacionPse(Integer ifacturacionPse) {
		this.ifacturacionPse = ifacturacionPse;
	}
	
}