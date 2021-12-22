package org.gteperu.erp.everest.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class _DocumentoCpe {

	private Integer ambiente_operacion;
	private Integer id_documento;
	private Integer id_empresa;
	private String tipo_operacion; // CATALOGO 17
	private double total_gravadas;// *
	private double total_inafecta;// *
	private double total_exoneradas;// NUEVO UBL2.1//*
	private double total_gratuitas;// *
	private double total_percepciones;// *
	private double total_retenciones;// *
	private double total_detracciones;// *
	private double total_exportacion;// *

	private double total_bonificaciones;// *
	private double total_descuento;// *
	private double sub_total;// *
	private double por_igv;
	private double total_igv;
	private double total_isc;
	private double total_otr_imp;
	private double total;
	private String total_letras;
	private String nro_guia_remision;
	private Timestamp fecha_documento;// *
	private Timestamp fecha_vto;// NUEVO UBL2.1 //*
	private String cod_tipo_documento;
	private String cod_moneda;// *
	private String nro_documento_cliente;
	private String razon_social_cliente;
	private String tipo_documento_cliente;
	private String cod_ubigeo_cliente;// NUEVO UBL2.1
	private String departamento_cliente;// NUEVO UBL2.1
	private String provincia_cliente;// NUEVO UBL2.1
	private String distrito_cliente;// NUEVO UBL2.1
	private String ciudad_cliente;
	private String cod_pais_cliente;
	private String nro_documento_empresa;
	private String nombre_comercial_empresa;
	private String nro_doc_cliente;
	private String tipo_documento_empresa;
	private String nombre_comercial_cliente;
	private String codigo_ubigeo_empresa;
	private String direccion_empresa;
	private String departamento_empresa;
	private String provincia_empresa;
	private String distrito_empresa;
	private String codigo_pais_empresa;
	private String razon_social_empresa;
	private int tipo_proceso;
	private String cod_guia_remision;
	private String nro_otr_comprobante;
	private String cod_otr_comprobante;
	private Integer id_cliente;
	private String notas_documento;
	private String terminos_condiciones_doc;

	// Adicionales para factura
	private String serie_comprobante; // *
	private String nro_comprobante; // *
	private Integer ifacturacionPse;
	// NC ó ND
	private String tipo_comprobante_modifica;
	private String nro_documento_modifica;
	private String cod_tipo_motivo;
	private String descripcion_motivo;
	private double descuento_global;
	private Integer id_documento_ref;

	// DAR BAJA FACTURA - NC - ND
	private String fecha_referencia;
	private String fecha_baja;
	private String motivo_baja;
	private Integer secuencia;

	// GUIA REMISION
	private String nota_guia;
	private String cod_motivo_traslado;
	private String descripcion_motivo_traslado;// TAG (Information) catalogo 20
	private String cod_und_peso_bruto;// TAG (GrossWeightMeasure) catalogo 3
	public double peso_bruto;
	private int total_bultos;
	private String nro_contenedor;

	// =================INICIO DE TRASLADO Y DATOS DEL
	// TRANSPORTISTA=================
	private String cod_modalidad_traslado;// TAG (TransportModeCode) catalogo 18, 01=transporte publico, 02=transporte
											// privado
	private String fecha_inicio_traslado;// TAG (StartDate)
	// =======================DATOS DEL TRANSPORTISTA PUBLICO======================
	private String nro_documento_transportista;
	private String razon_social_transportista;
	private String tipo_documento_transportista;// 6=RUC
	// =========================DATOS VEHICULO, CHOFER, CARROZA - UND
	// TRANSPORTE========================
	private String placa_vehiculo;
	private String cod_tipo_doc_chofer;// 1=DNI
	private String nro_doc_chofer;// NRO DOCUMENTO DEL CHOFER
	private String placa_carreta;
	// =======================DIRECCION PARTIDA-LLEGADA======================
	// -----------LLEGADA (DeliveryAddress)
	private String cod_ubigeo_destino;
	private String direccion_destino;// TAG (StreetName)
	// -----------PARTIDA (OriginAddress)
	private String cod_ubigeo_origen;
	private String direccion_origen;// TAG (StreetName)
	// =====================DATOS DE ANULACION==========================
	private String guia_referencia_anu;// DOCUMENTO DE REFERENCIA AL QUE SE ANULARA
	private String cod_tipo_guia_refanu;// TIPO DE DOCUMENTO DE REFENCIA (GUIA REMITENTE, GUIA TRANSPORTISTA)

	// RETENCION PERCEPCION

	public double valor_percepcion;

	private String fecha_registro;
	private Timestamp tfecha_registro;

	private int id_cliente_cpe;
	private String cod_ubigeo_empresa;
	private String zona_urbanizacion_empresa;
	private String cod_pais_empresa;
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
	private String tipo_retencion;
	private double porcentaje_retencion;
	private String nota;
	private String moneda;

	private String cod_respuesta_sunat;
	private String descripcion_sunat;
	private String hash_cpe;
	private String hash_cdr;
	private int tipo;
	private String correo;
	private String moneda_pago_neto;
	private String moneda_retenida;
	private double total_retencion;
	private double neto_retencion;
	private String fecha_pago;
	private String nro_pago;
	private Double importe_pago;
	private Double importe_retenido;
	private Double importe_neto_pagado;
	private String fecha_retencion;

	private String moneda_cobro_neto;
	private String moneda_percibida;
	private double total_percepcion;
	private double neto_percepcion;
	private String fecha_cobro;
	private String nro_cobro;
	private Double importe_cobro;
	private Double importe_percibido;
	private Double importe_neto_cobrado;
	private String fecha_percepcion;

//	    private Integer id_tipo_nota_debito_cod_sunat;
	private String codigo_nota_debito_cod_sunat;
	private String descripcion_nota_debito_cod_sunat;

	// -----------DATOS POR IMPORTACION O COMPRA
	private String numeracion_dam;

	private String direccion_itinerante;
	private String provincia_itinerante;
	private String departamento_itinerante;
	private String ubigeo_itinerante;
	private String distrito_itinerante;

	private String nro_comprobante_ref_ant;
	private String nro_documento_emp_regu_ant;
	private String tipo_documento_emp_regu_ant;
	private String cod_moneda_anticipo;
	private double monto_regu_anticipo;

	// DETALLE
	private List<_DocumentoCpe_DetalleBean> ls_documentoCpe;

	// INGRESAR RUTA ARCHIVO
	private String ruta_archivo;

	// DETRACCION
	private Double porcentaje_detraccion;
	private String tipo_detraccion;

	// LISTADO DE PAGINA
	private Integer ind_pag;
	private Integer can_pag;
	private Integer offset;
	private Integer limit;
	private Timestamp fecha_inicio;
	private Timestamp fecha_fin;

	private String nombreTipoComprobante;

	private String nro_doc;
	private String razon_social;

	private String direccion_fiscal_cliente;
	private Integer idlocal;
	private Boolean bflagactualizaserie = false;
	private Boolean bflageditarnum = false;
	private Integer idsutipodocumento;
	private String keyclient;

	private Boolean bdocborrador;
	private Boolean bdocborradortmp;

	private List<Pagodocumento> lsPagodocumento;
	//cuotas
	private String cod_forma_pago;
	private List<Cuota> lscuotas;

	public Boolean getBdocborradortmp() {
		return bdocborradortmp;
	}

	public void setBdocborradortmp(Boolean bdocborradortmp) {
		this.bdocborradortmp = bdocborradortmp;
	}

	public Boolean getBdocborrador() {
		return bdocborrador;
	}

	public void setBdocborrador(Boolean bdocborrador) {
		this.bdocborrador = bdocborrador;
	}

	public String getNombreTipoComprobante() {
		return nombreTipoComprobante;
	}

	public void setNombreTipoComprobante(String nombreTipoComprobante) {
		this.nombreTipoComprobante = nombreTipoComprobante;
	}

	public Timestamp getTfecha_registro() {
		return tfecha_registro;
	}

	public void setTfecha_registro(Timestamp tfecha_registro) {
		this.tfecha_registro = tfecha_registro;
	}

	public Integer getIdlocal() {
		return idlocal;
	}

	public void setIdlocal(Integer idlocal) {
		this.idlocal = idlocal;
	}

	public String getDireccion_fiscal_cliente() {
		return direccion_fiscal_cliente;
	}

	public void setDireccion_fiscal_cliente(String direccion_fiscal_cliente) {
		this.direccion_fiscal_cliente = direccion_fiscal_cliente;
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

	public String getCod_moneda_anticipo() {
		return cod_moneda_anticipo;
	}

	public void setCod_moneda_anticipo(String cod_moneda_anticipo) {
		this.cod_moneda_anticipo = cod_moneda_anticipo;
	}

	public String getNro_comprobante_ref_ant() {
		return nro_comprobante_ref_ant;
	}

	public void setNro_comprobante_ref_ant(String nro_comprobante_ref_ant) {
		this.nro_comprobante_ref_ant = nro_comprobante_ref_ant;
	}

	public String getNro_documento_emp_regu_ant() {
		return nro_documento_emp_regu_ant;
	}

	public void setNro_documento_emp_regu_ant(String nro_documento_emp_regu_ant) {
		this.nro_documento_emp_regu_ant = nro_documento_emp_regu_ant;
	}

	public String getTipo_documento_emp_regu_ant() {
		return tipo_documento_emp_regu_ant;
	}

	public void setTipo_documento_emp_regu_ant(String tipo_documento_emp_regu_ant) {
		this.tipo_documento_emp_regu_ant = tipo_documento_emp_regu_ant;
	}

	public double getMonto_regu_anticipo() {
		return monto_regu_anticipo;
	}

	public void setMonto_regu_anticipo(double monto_regu_anticipo) {
		this.monto_regu_anticipo = monto_regu_anticipo;
	}

	public String getDireccion_itinerante() {
		return direccion_itinerante;
	}

	public void setDireccion_itinerante(String direccion_itinerante) {
		this.direccion_itinerante = direccion_itinerante;
	}

	public String getProvincia_itinerante() {
		return provincia_itinerante;
	}

	public void setProvincia_itinerante(String provincia_itinerante) {
		this.provincia_itinerante = provincia_itinerante;
	}

	public String getDepartamento_itinerante() {
		return departamento_itinerante;
	}

	public void setDepartamento_itinerante(String departamento_itinerante) {
		this.departamento_itinerante = departamento_itinerante;
	}

	public String getUbigeo_itinerante() {
		return ubigeo_itinerante;
	}

	public void setUbigeo_itinerante(String ubigeo_itinerante) {
		this.ubigeo_itinerante = ubigeo_itinerante;
	}

	public String getDistrito_itinerante() {
		return distrito_itinerante;
	}

	public void setDistrito_itinerante(String distrito_itinerante) {
		this.distrito_itinerante = distrito_itinerante;
	}

	public double getValor_percepcion() {
		return valor_percepcion;
	}

	public void setValor_percepcion(double valor_percepcion) {
		this.valor_percepcion = valor_percepcion;
	}

	public double getDescuento_global() {
		return descuento_global;
	}

	public void setDescuento_global(double descuento_global) {
		this.descuento_global = descuento_global;
	}

	public Double getPorcentaje_detraccion() {
		return porcentaje_detraccion;
	}

	public void setPorcentaje_detraccion(Double porcentaje_detraccion) {
		this.porcentaje_detraccion = porcentaje_detraccion;
	}

	public String getTipo_detraccion() {
		return tipo_detraccion;
	}

	public void setTipo_detraccion(String tipo_detraccion) {
		this.tipo_detraccion = tipo_detraccion;
	}

	public String getMoneda_cobro_neto() {
		return moneda_cobro_neto;
	}

	public double getTotal_exportacion() {
		return total_exportacion;
	}

	public void setTotal_exportacion(double total_exportacion) {
		this.total_exportacion = total_exportacion;
	}

	public void setMoneda_cobro_neto(String moneda_cobro_neto) {
		this.moneda_cobro_neto = moneda_cobro_neto;
	}

	public String getMoneda_percibida() {
		return moneda_percibida;
	}

	public void setMoneda_percibida(String moneda_percibida) {
		this.moneda_percibida = moneda_percibida;
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

	public String getFecha_cobro() {
		return fecha_cobro;
	}

	public void setFecha_cobro(String fecha_cobro) {
		this.fecha_cobro = fecha_cobro;
	}

	public String getNro_cobro() {
		return nro_cobro;
	}

	public void setNro_cobro(String nro_cobro) {
		this.nro_cobro = nro_cobro;
	}

	public Double getImporte_cobro() {
		return importe_cobro;
	}

	public void setImporte_cobro(Double importe_cobro) {
		this.importe_cobro = importe_cobro;
	}

	public Double getImporte_percibido() {
		return importe_percibido;
	}

	public void setImporte_percibido(Double importe_percibido) {
		this.importe_percibido = importe_percibido;
	}

	public Double getImporte_neto_cobrado() {
		return importe_neto_cobrado;
	}

	public void setImporte_neto_cobrado(Double importe_neto_cobrado) {
		this.importe_neto_cobrado = importe_neto_cobrado;
	}

	public String getFecha_percepcion() {
		return fecha_percepcion;
	}

	public void setFecha_percepcion(String fecha_percepcion) {
		this.fecha_percepcion = fecha_percepcion;
	}

	public String getMoneda_pago_neto() {
		return moneda_pago_neto;
	}

	public void setMoneda_pago_neto(String moneda_pago_neto) {
		this.moneda_pago_neto = moneda_pago_neto;
	}

	public String getMoneda_retenida() {
		return moneda_retenida;
	}

	public void setMoneda_retenida(String moneda_retenida) {
		this.moneda_retenida = moneda_retenida;
	}

	public String getFecha_pago() {
		return fecha_pago;
	}

	public void setFecha_pago(String fecha_pago) {
		this.fecha_pago = fecha_pago;
	}

	public String getNro_pago() {
		return nro_pago;
	}

	public void setNro_pago(String nro_pago) {
		this.nro_pago = nro_pago;
	}

	public Double getImporte_pago() {
		return importe_pago;
	}

	public void setImporte_pago(Double importe_pago) {
		this.importe_pago = importe_pago;
	}

	public Double getImporte_retenido() {
		return importe_retenido;
	}

	public void setImporte_retenido(Double importe_retenido) {
		this.importe_retenido = importe_retenido;
	}

	public Double getImporte_neto_pagado() {
		return importe_neto_pagado;
	}

	public void setImporte_neto_pagado(Double importe_neto_pagado) {
		this.importe_neto_pagado = importe_neto_pagado;
	}

	public String getFecha_retencion() {
		return fecha_retencion;
	}

	public void setFecha_retencion(String fecha_retencion) {
		this.fecha_retencion = fecha_retencion;
	}

	public String getCodigo_nota_debito_cod_sunat() {
		return codigo_nota_debito_cod_sunat;
	}

	public void setCodigo_nota_debito_cod_sunat(String codigo_nota_debito_cod_sunat) {
		this.codigo_nota_debito_cod_sunat = codigo_nota_debito_cod_sunat;
	}

	public String getDescripcion_nota_debito_cod_sunat() {
		return descripcion_nota_debito_cod_sunat;
	}

	public void setDescripcion_nota_debito_cod_sunat(String descripcion_nota_debito_cod_sunat) {
		this.descripcion_nota_debito_cod_sunat = descripcion_nota_debito_cod_sunat;
	}

	public String getNro_contenedor() {
		return nro_contenedor;
	}

	public void setNro_contenedor(String nro_contenedor) {
		this.nro_contenedor = nro_contenedor;
	}

	public String getNumeracion_dam() {
		return numeracion_dam;
	}

	public void setNumeracion_dam(String numeracion_dam) {
		this.numeracion_dam = numeracion_dam;
	}

	public String getRuta_archivo() {
		return ruta_archivo;
	}

	public void setRuta_archivo(String ruta_archivo) {
		this.ruta_archivo = ruta_archivo;
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Integer getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}

	public String getMotivo_baja() {
		return motivo_baja;
	}

	public void setMotivo_baja(String motivo_baja) {
		this.motivo_baja = motivo_baja;
	}

	public List<_DocumentoCpe_DetalleBean> getLs_documentoCpe() {
		return ls_documentoCpe;
	}

	public void setLs_documentoCpe(List<_DocumentoCpe_DetalleBean> ls_documentoCpe) {
		this.ls_documentoCpe = ls_documentoCpe;
	}

	public Integer getId_documento() {
		return id_documento;
	}

	public void setId_documento(Integer id_documento) {
		this.id_documento = id_documento;
	}

	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}

	public String getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public int getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(int id_empresa) {
		this.id_empresa = id_empresa;
	}

	public int getId_cliente_cpe() {
		return id_cliente_cpe;
	}

	public void setId_cliente_cpe(int id_cliente_cpe) {
		this.id_cliente_cpe = id_cliente_cpe;
	}

	public String getCod_ubigeo_empresa() {
		return cod_ubigeo_empresa;
	}

	public void setCod_ubigeo_empresa(String cod_ubigeo_empresa) {
		this.cod_ubigeo_empresa = cod_ubigeo_empresa;
	}

	public String getZona_urbanizacion_empresa() {
		return zona_urbanizacion_empresa;
	}

	public void setZona_urbanizacion_empresa(String zona_urbanizacion_empresa) {
		this.zona_urbanizacion_empresa = zona_urbanizacion_empresa;
	}

	public String getCod_pais_empresa() {
		return cod_pais_empresa;
	}

	public void setCod_pais_empresa(String cod_pais_empresa) {
		this.cod_pais_empresa = cod_pais_empresa;
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

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
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

	public String getGuia_referencia_anu() {
		return guia_referencia_anu;
	}

	public void setGuia_referencia_anu(String guia_referencia_anu) {
		this.guia_referencia_anu = guia_referencia_anu;
	}

	public String getCod_tipo_guia_refanu() {
		return cod_tipo_guia_refanu;
	}

	public void setCod_tipo_guia_refanu(String cod_tipo_guia_refanu) {
		this.cod_tipo_guia_refanu = cod_tipo_guia_refanu;
	}

	public String getCod_modalidad_traslado() {
		return cod_modalidad_traslado;
	}

	public void setCod_modalidad_traslado(String cod_modalidad_traslado) {
		this.cod_modalidad_traslado = cod_modalidad_traslado;
	}

	public String getFecha_inicio_traslado() {
		return fecha_inicio_traslado;
	}

	public void setFecha_inicio_traslado(String fecha_inicio_traslado) {
		this.fecha_inicio_traslado = fecha_inicio_traslado;
	}

	public String getNro_documento_transportista() {
		return nro_documento_transportista;
	}

	public void setNro_documento_transportista(String nro_documento_transportista) {
		this.nro_documento_transportista = nro_documento_transportista;
	}

	public String getRazon_social_transportista() {
		return razon_social_transportista;
	}

	public void setRazon_social_transportista(String razon_social_transportista) {
		this.razon_social_transportista = razon_social_transportista;
	}

	public String getTipo_documento_transportista() {
		return tipo_documento_transportista;
	}

	public void setTipo_documento_transportista(String tipo_documento_transportista) {
		this.tipo_documento_transportista = tipo_documento_transportista;
	}

	public String getPlaca_vehiculo() {
		return placa_vehiculo;
	}

	public void setPlaca_vehiculo(String placa_vehiculo) {
		this.placa_vehiculo = placa_vehiculo;
	}

	public String getCod_tipo_doc_chofer() {
		return cod_tipo_doc_chofer;
	}

	public void setCod_tipo_doc_chofer(String cod_tipo_doc_chofer) {
		this.cod_tipo_doc_chofer = cod_tipo_doc_chofer;
	}

	public String getNro_doc_chofer() {
		return nro_doc_chofer;
	}

	public void setNro_doc_chofer(String nro_doc_chofer) {
		this.nro_doc_chofer = nro_doc_chofer;
	}

	public String getPlaca_carreta() {
		return placa_carreta;
	}

	public void setPlaca_carreta(String placa_carreta) {
		this.placa_carreta = placa_carreta;
	}

	public String getCod_ubigeo_destino() {
		return cod_ubigeo_destino;
	}

	public void setCod_ubigeo_destino(String cod_ubigeo_destino) {
		this.cod_ubigeo_destino = cod_ubigeo_destino;
	}

	public String getDireccion_destino() {
		return direccion_destino;
	}

	public void setDireccion_destino(String direccion_destino) {
		this.direccion_destino = direccion_destino;
	}

	public String getCod_ubigeo_origen() {
		return cod_ubigeo_origen;
	}

	public void setCod_ubigeo_origen(String cod_ubigeo_origen) {
		this.cod_ubigeo_origen = cod_ubigeo_origen;
	}

	public String getDireccion_origen() {
		return direccion_origen;
	}

	public void setDireccion_origen(String direccion_origen) {
		this.direccion_origen = direccion_origen;
	}

	public String getCod_motivo_traslado() {
		return cod_motivo_traslado;
	}

	public void setCod_motivo_traslado(String cod_motivo_traslado) {
		this.cod_motivo_traslado = cod_motivo_traslado;
	}

	public String getDescripcion_motivo_traslado() {
		return descripcion_motivo_traslado;
	}

	public void setDescripcion_motivo_traslado(String descripcion_motivo_traslado) {
		this.descripcion_motivo_traslado = descripcion_motivo_traslado;
	}

	public String getCod_und_peso_bruto() {
		return cod_und_peso_bruto;
	}

	public void setCod_und_peso_bruto(String cod_und_peso_bruto) {
		this.cod_und_peso_bruto = cod_und_peso_bruto;
	}

	public double getPeso_bruto() {
		return peso_bruto;
	}

	public void setPeso_bruto(double peso_bruto) {
		this.peso_bruto = peso_bruto;
	}

	public int getTotal_bultos() {
		return total_bultos;
	}

	public void setTotal_bultos(int total_bultos) {
		this.total_bultos = total_bultos;
	}

	public String getNota_guia() {
		return nota_guia;
	}

	public void setNota_guia(String nota_guia) {
		this.nota_guia = nota_guia;
	}

	public String getCod_pais_cliente() {
		return cod_pais_cliente;
	}

	public void setCod_pais_cliente(String cod_pais_cliente) {
		this.cod_pais_cliente = cod_pais_cliente;
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

	public String getTipo_operacion() {
		return tipo_operacion;
	}

	public void setTipo_operacion(String tipo_operacion) {
		this.tipo_operacion = tipo_operacion;
	}

	public double getTotal_gravadas() {
		return total_gravadas;
	}

	public void setTotal_gravadas(double total_gravadas) {
		this.total_gravadas = total_gravadas;
	}

	public double getTotal_inafecta() {
		return total_inafecta;
	}

	public void setTotal_inafecta(double total_inafecta) {
		this.total_inafecta = total_inafecta;
	}

	public double getTotal_exoneradas() {
		return total_exoneradas;
	}

	public void setTotal_exoneradas(double total_exoneradas) {
		this.total_exoneradas = total_exoneradas;
	}

	public double getTotal_gratuitas() {
		return total_gratuitas;
	}

	public void setTotal_gratuitas(double total_gratuitas) {
		this.total_gratuitas = total_gratuitas;
	}

	public double getTotal_percepciones() {
		return total_percepciones;
	}

	public void setTotal_percepciones(double total_percepciones) {
		this.total_percepciones = total_percepciones;
	}

	public double getTotal_retenciones() {
		return total_retenciones;
	}

	public void setTotal_retenciones(double total_retenciones) {
		this.total_retenciones = total_retenciones;
	}

	public double getTotal_detracciones() {
		return total_detracciones;
	}

	public void setTotal_detracciones(double total_detracciones) {
		this.total_detracciones = total_detracciones;
	}

	public double getTotal_bonificaciones() {
		return total_bonificaciones;
	}

	public void setTotal_bonificaciones(double total_bonificaciones) {
		this.total_bonificaciones = total_bonificaciones;
	}

	public double getTotal_descuento() {
		return total_descuento;
	}

	public void setTotal_descuento(double total_descuento) {
		this.total_descuento = total_descuento;
	}

	public double getSub_total() {
		return sub_total;
	}

	public void setSub_total(double sub_total) {
		this.sub_total = sub_total;
	}

	public double getPor_igv() {
		return por_igv;
	}

	public void setPor_igv(double por_igv) {
		this.por_igv = por_igv;
	}

	public double getTotal_igv() {
		return total_igv;
	}

	public void setTotal_igv(double total_igv) {
		this.total_igv = total_igv;
	}

	public double getTotal_isc() {
		return total_isc;
	}

	public void setTotal_isc(double total_isc) {
		this.total_isc = total_isc;
	}

	public double getTotal_otr_imp() {
		return total_otr_imp;
	}

	public void setTotal_otr_imp(double total_otr_imp) {
		this.total_otr_imp = total_otr_imp;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getTotal_letras() {
		return total_letras;
	}

	public void setTotal_letras(String total_letras) {
		this.total_letras = total_letras;
	}

	public String getNro_guia_remision() {
		return nro_guia_remision;
	}

	public void setNro_guia_remision(String nro_guia_remision) {
		this.nro_guia_remision = nro_guia_remision;
	}

	public Timestamp getFecha_documento() {
		return fecha_documento;
	}

	public void setFecha_documento(Timestamp fecha_documento) {
		this.fecha_documento = fecha_documento;
	}

	public Timestamp getFecha_vto() {
		return fecha_vto;
	}

	public void setFecha_vto(Timestamp fecha_vto) {
		this.fecha_vto = fecha_vto;
	}

	public String getCod_tipo_documento() {
		return cod_tipo_documento;
	}

	public void setCod_tipo_documento(String cod_tipo_documento) {
		this.cod_tipo_documento = cod_tipo_documento;
	}

	public String getCod_moneda() {
		return cod_moneda;
	}

	public void setCod_moneda(String cod_moneda) {
		this.cod_moneda = cod_moneda;
	}

	public String getNro_documento_cliente() {
		return nro_documento_cliente;
	}

	public void setNro_documento_cliente(String nro_documento_cliente) {
		this.nro_documento_cliente = nro_documento_cliente;
	}

	public String getRazon_social_cliente() {
		return razon_social_cliente;
	}

	public void setRazon_social_cliente(String razon_social_cliente) {
		this.razon_social_cliente = razon_social_cliente;
	}

	public String getTipo_documento_cliente() {
		return tipo_documento_cliente;
	}

	public void setTipo_documento_cliente(String tipo_documento_cliente) {
		this.tipo_documento_cliente = tipo_documento_cliente;
	}

	public String getCod_ubigeo_cliente() {
		return cod_ubigeo_cliente;
	}

	public void setCod_ubigeo_cliente(String cod_ubigeo_cliente) {
		this.cod_ubigeo_cliente = cod_ubigeo_cliente;
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

	public String getCiudad_cliente() {
		return ciudad_cliente;
	}

	public void setCiudad_cliente(String ciudad_cliente) {
		this.ciudad_cliente = ciudad_cliente;
	}

	public String getNro_doc_cliente() {
		return nro_doc_cliente;
	}

	public void setNro_doc_cliente(String nro_doc_cliente) {
		this.nro_doc_cliente = nro_doc_cliente;
	}

	public String getTipo_documento_empresa() {
		return tipo_documento_empresa;
	}

	public void setTipo_documento_empresa(String tipo_documento_empresa) {
		this.tipo_documento_empresa = tipo_documento_empresa;
	}

	public String getNombre_comercial_cliente() {
		return nombre_comercial_cliente;
	}

	public void setNombre_comercial_cliente(String nombre_comercial_cliente) {
		this.nombre_comercial_cliente = nombre_comercial_cliente;
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

	public String getCodigo_pais_empresa() {
		return codigo_pais_empresa;
	}

	public void setCodigo_pais_empresa(String codigo_pais_empresa) {
		this.codigo_pais_empresa = codigo_pais_empresa;
	}

	public String getRazon_social_empresa() {
		return razon_social_empresa;
	}

	public void setRazon_social_empresa(String razon_social_empresa) {
		this.razon_social_empresa = razon_social_empresa;
	}

	public int getTipo_proceso() {
		return tipo_proceso;
	}

	public void setTipo_proceso(int tipo_proceso) {
		this.tipo_proceso = tipo_proceso;
	}

	public String getCod_guia_remision() {
		return cod_guia_remision;
	}

	public void setCod_guia_remision(String cod_guia_remision) {
		this.cod_guia_remision = cod_guia_remision;
	}

	public String getNro_otr_comprobante() {
		return nro_otr_comprobante;
	}

	public void setNro_otr_comprobante(String nro_otr_comprobante) {
		this.nro_otr_comprobante = nro_otr_comprobante;
	}

	public String getCod_otr_comprobante() {
		return cod_otr_comprobante;
	}

	public void setCod_otr_comprobante(String cod_otr_comprobante) {
		this.cod_otr_comprobante = cod_otr_comprobante;
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

	public String getTipo_comprobante_modifica() {
		return tipo_comprobante_modifica;
	}

	public void setTipo_comprobante_modifica(String tipo_comprobante_modifica) {
		this.tipo_comprobante_modifica = tipo_comprobante_modifica;
	}

	public String getNro_documento_modifica() {
		return nro_documento_modifica;
	}

	public void setNro_documento_modifica(String nro_documento_modifica) {
		this.nro_documento_modifica = nro_documento_modifica;
	}

	public String getCod_tipo_motivo() {
		return cod_tipo_motivo;
	}

	public void setCod_tipo_motivo(String cod_tipo_motivo) {
		this.cod_tipo_motivo = cod_tipo_motivo;
	}

	public String getDescripcion_motivo() {
		return descripcion_motivo;
	}

	public void setDescripcion_motivo(String descripcion_motivo) {
		this.descripcion_motivo = descripcion_motivo;
	}

	public String getFecha_referencia() {
		return fecha_referencia;
	}

	public void setFecha_referencia(String fecha_referencia) {
		this.fecha_referencia = fecha_referencia;
	}

	public String getFecha_baja() {
		return fecha_baja;
	}

	public void setFecha_baja(String fecha_baja) {
		this.fecha_baja = fecha_baja;
	}

	public List<_DocumentoCpe_DetalleBean> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<_DocumentoCpe_DetalleBean> detalle) {
		this.detalle = detalle;
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

	// ================DETALLE===============
	// private Cpe_Datos_ExtrasBean Datos_Extras;
	List<_DocumentoCpe_DetalleBean> detalle;
	private String ruc_empresaEmisora;
	private String userSol_empresaEmisora;
	private String passSol_empresaEmisora;
	private String passFirma_empresaEmisora;

	private List<_CpeGuiaRemisionDetalleBean> detalle_Guia;

	private String observacion;
	private Integer estado;
	private _Clientes clientes;
	private String dirDocumentoEmpresaEmisora;
	private _Company company;
	private Integer estado_pagado;
	private Double tipo_cambio;
	private Integer id_usuario;
	private Double porcentaje_descuento;

	// Datos extras para envío de email
	private String asunto;
	private String email;

	public Double getPorcentaje_descuento() {
		return porcentaje_descuento;
	}

	public void setPorcentaje_descuento(Double porcentaje_descuento) {
		this.porcentaje_descuento = porcentaje_descuento;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Integer getEstado_pagado() {
		return estado_pagado;
	}

	public void setEstado_pagado(Integer estado_pagado) {
		this.estado_pagado = estado_pagado;
	}

	public Double getTipo_cambio() {
		return tipo_cambio;
	}

	public void setTipo_cambio(Double tipo_cambio) {
		this.tipo_cambio = tipo_cambio;
	}

	public _Company getCompany() {
		return company;
	}

	public void setCompany(_Company company) {
		this.company = company;
	}

	public String getDirDocumentoEmpresaEmisora() {
		return dirDocumentoEmpresaEmisora;
	}

	public void setDirDocumentoEmpresaEmisora(String dirDocumentoEmpresaEmisora) {
		this.dirDocumentoEmpresaEmisora = dirDocumentoEmpresaEmisora;
	}

	public _Clientes getClientes() {
		return clientes;
	}

	public void setClientes(_Clientes clientes) {
		this.clientes = clientes;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public List<_CpeGuiaRemisionDetalleBean> getDetalle_Guia() {
		return detalle_Guia;
	}

	public void setDetalle_Guia(List<_CpeGuiaRemisionDetalleBean> detalle_Guia) {
		this.detalle_Guia = detalle_Guia;
	}

	public String getNotas_documento() {
		return notas_documento;
	}

	public void setNotas_documento(String notas_documento) {
		this.notas_documento = notas_documento;
	}

	public String getTerminos_condiciones_doc() {
		return terminos_condiciones_doc;
	}

	public void setTerminos_condiciones_doc(String terminos_condiciones_doc) {
		this.terminos_condiciones_doc = terminos_condiciones_doc;
	}

	@Override
	public String toString() {
		return "_DocumentoCpe [total_gravadas=" + total_gravadas + ", total_inafecta=" + total_inafecta
				+ ", total_exoneradas=" + total_exoneradas + ", total_gratuitas=" + total_gratuitas
				+ ", total_percepciones=" + total_percepciones + ", total_retenciones=" + total_retenciones
				+ ", total_detracciones=" + total_detracciones + ", total_exportacion=" + total_exportacion
				+ ", total_bonificaciones=" + total_bonificaciones + ", total_descuento=" + total_descuento
				+ ", sub_total=" + sub_total + ", total_igv=" + total_igv + ", total_isc=" + total_isc
				+ ", total_otr_imp=" + total_otr_imp + ", total=" + total + ", total_letras=" + total_letras + "]";
	}

	public String getNro_doc() {
		return nro_doc;
	}

	public void setNro_doc(String nro_doc) {
		this.nro_doc = nro_doc;
	}

	public String getRazon_social() {
		return razon_social;
	}

	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
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

	public Boolean getBflagactualizaserie() {
		return bflagactualizaserie;
	}

	public void setBflagactualizaserie(Boolean bflagactualizaserie) {
		this.bflagactualizaserie = bflagactualizaserie;
	}

	public Boolean getBflageditarnum() {
		return bflageditarnum;
	}

	public void setBflageditarnum(Boolean bflageditarnum) {
		this.bflageditarnum = bflageditarnum;
	}

	public Integer getIdsutipodocumento() {
		return idsutipodocumento;
	}

	public void setIdsutipodocumento(Integer idsutipodocumento) {
		this.idsutipodocumento = idsutipodocumento;
	}

	public String getKeyclient() {
		return keyclient;
	}

	public void setKeyclient(String keyclient) {
		this.keyclient = keyclient;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Pagodocumento> getLsPagodocumento() {
		return lsPagodocumento;
	}

	public void setLsPagodocumento(List<Pagodocumento> lsPagodocumento) {
		this.lsPagodocumento = lsPagodocumento;
	}

	public String getCod_forma_pago() {
		return cod_forma_pago;
	}

	public void setCod_forma_pago(String cod_forma_pago) {
		this.cod_forma_pago = cod_forma_pago;
	}


	public List<Cuota> getLscuotas() {
		return lscuotas;
	}

	public void setLscuotas(List<Cuota> lscuotas) {
		this.lscuotas = lscuotas;
	}

	public Integer getId_documento_ref() {
		return id_documento_ref;
	}

	public void setId_documento_ref(Integer id_documento_ref) {
		this.id_documento_ref = id_documento_ref;
	}

}