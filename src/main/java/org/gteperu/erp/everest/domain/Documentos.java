package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Documentos extends _Parametros {

	private Integer iddocumentos;
	private Integer idtipomoneda;
	private Integer idcondicionespago;
	private Integer idclasificadorcostos;
	private Integer idcliente;
	private Double subtotal;
	private Double impuesto;
	private Double descuento;
	private Double total;
	private Integer idseriedocumentos;
	private String comentario;
	private Integer estado;
	private Integer estadotesoreria;
	private Integer estadofiltro;
	private Timestamp fecharegistro;
	private Timestamp fechapago;
	private String fecharegistrostr;
	private Integer diaspago;
	private String fechapagostr;
	private String codigo;
	private String accion;
	private String serie;
	private String glosa;
	private Integer idoordendecompra;
	private String numero;
	private Integer idsutipocomprovantepagodocumento;
 	private List<Documentosdetalle> lsDetalle;
	private Integer documentoreferencia;
	private Integer tipodocasociado;
	private Integer idempresa;
	private Integer idcreditoclientes;
	private Integer entregadias;
	private Timestamp fechaentrega;
	private String fechaentregastr;
	private Integer porcentajeanticipo;
 	private Double montocancelado;
	private Double subtotalcancelado;
	private Double impuestocancelado;
	private Integer estadosalida;
	private Integer idperiodo;
	 	private Integer idsutipooperacion;
	private Integer notadebitocredito;
	private Integer tipooperacion;
	private Double tipodecambio;
	private Integer idnotapedido;
	private Integer idordencompra;
	private Integer anio;
	private Integer idcuenta;
	private Integer numnuevo;
	private Integer idproyecto;
	private Integer estadodetraccion;
	public Integer getEstadoretencion() {
		return estadoretencion;
	}

	public void setEstadoretencion(Integer estadoretencion) {
		this.estadoretencion = estadoretencion;
	}

	private Integer estadopercepcion;
	private Integer estadoretencion;
	private Documentos documentosref;
	private List<Documentos> lsNotasCreditoDebito;
 	private Integer idtipoconcepto;
	


	public Integer getEstadodetraccion() {
		return estadodetraccion;
	}

	public void setEstadodetraccion(Integer estadodetraccion) {
		this.estadodetraccion = estadodetraccion;
	}

	public Integer getEstadopercepcion() {
		return estadopercepcion;
	}

	public void setEstadopercepcion(Integer estadopercepcion) {
		this.estadopercepcion = estadopercepcion;
	}
	
	public Integer getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(Integer idcuenta) {
		this.idcuenta = idcuenta;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getIdproyecto() {
		return idproyecto;
	}

	public void setIdproyecto(Integer idproyecto) {
		this.idproyecto = idproyecto;
	}
	public Integer getIdtipoconcepto() {
		return idtipoconcepto;
	}

	public void setIdtipoconcepto(Integer idtipoconcepto) {
		this.idtipoconcepto = idtipoconcepto;
	}

	public Integer getNumnuevo() {
		return numnuevo;
	}

	public void setNumnuevo(Integer numnuevo) {
		this.numnuevo = numnuevo;
	}

	public List<Documentos> getLsNotasCreditoDebito() {
		return lsNotasCreditoDebito;
	}

	public void setLsNotasCreditoDebito(List<Documentos> lsNotasCreditoDebito) {
		this.lsNotasCreditoDebito = lsNotasCreditoDebito;
	}

	public Documentos getDocumentosref() {
		return documentosref;
	}

	public Integer getIdordencompra() {
		return idordencompra;
	}

	public void setIdordencompra(Integer idordencompra) {
		this.idordencompra = idordencompra;
	}

	public Integer getIdnotapedido() {
		return idnotapedido;
	}

	public void setIdnotapedido(Integer idnotapedido) {
		this.idnotapedido = idnotapedido;
	}

	public void setDocumentosref(Documentos documentosref) {
		this.documentosref = documentosref;
	}

	public Double getTipodecambio() {
		return tipodecambio;
	}

	public void setTipodecambio(Double tipodecambio) {
		this.tipodecambio = tipodecambio;
	}

	public Integer getTipooperacion() {
		return tipooperacion;
	}

	public void setTipooperacion(Integer tipooperacion) {
		this.tipooperacion = tipooperacion;
	}

	public Integer getNotadebitocredito() {
		return notadebitocredito;
	}

	public void setNotadebitocredito(Integer notadebitocredito) {
		this.notadebitocredito = notadebitocredito;
	}

 
	public Integer getIdsutipooperacion() {
		return idsutipooperacion;
	}

	public void setIdsutipooperacion(Integer idsutipooperacion) {
		this.idsutipooperacion = idsutipooperacion;
	}

	 
	public Integer getIdperiodo() {
		return idperiodo;
	}

 
	public void setIdperiodo(Integer idperiodo) {
		this.idperiodo = idperiodo;
	}

	public Integer getEstadosalida() {
		return estadosalida;
	}

	public void setEstadosalida(Integer estadosalida) {
		this.estadosalida = estadosalida;
	}

	public Double getSubtotalcancelado() {
		return subtotalcancelado;
	}

	public void setSubtotalcancelado(Double subtotalcancelado) {
		this.subtotalcancelado = subtotalcancelado;
	}

	public Double getImpuestocancelado() {
		return impuestocancelado;
	}

	public void setImpuestocancelado(Double impuestocancelado) {
		this.impuestocancelado = impuestocancelado;
	}

	public Double getMontocancelado() {
		return montocancelado;
	}

	public void setMontocancelado(Double montocancelado) {
		this.montocancelado = montocancelado;
	}

	public Integer getPorcentajeanticipo() {
		return porcentajeanticipo;
	}

	public void setPorcentajeanticipo(Integer porcentajeanticipo) {
		this.porcentajeanticipo = porcentajeanticipo;
	}

	public Integer getEstadotesoreria() {
		return estadotesoreria;
	}

	public void setEstadotesoreria(Integer estadotesoreria) {
		this.estadotesoreria = estadotesoreria;
	}

	public Integer getIdseriedocumentos() {
		return idseriedocumentos;
	}

	public void setIdseriedocumentos(Integer idseriedocumentos) {
		this.idseriedocumentos = idseriedocumentos;
	}

	public Integer getEstadofiltro() {
		return estadofiltro;
	}

	public void setEstadofiltro(Integer estadofiltro) {
		this.estadofiltro = estadofiltro;
	}

	public Integer getEntregadias() {
		return entregadias;
	}

	public void setEntregadias(Integer entregadias) {
		this.entregadias = entregadias;
	}

	public Timestamp getFechaentrega() {
		return fechaentrega;
	}

	public void setFechaentrega(Timestamp fechaentrega) {
		this.fechaentrega = fechaentrega;
	}

	public String getFechaentregastr() {
		return fechaentregastr;
	}

	public void setFechaentregastr(String fechaentregastr) {
		this.fechaentregastr = fechaentregastr;
	}

	public Integer getDiaspago() {
		return diaspago;
	}

	public Integer getIdcreditoclientes() {
		return idcreditoclientes;
	}

	public void setIdcreditoclientes(Integer idcreditoclientes) {
		this.idcreditoclientes = idcreditoclientes;
	}

	public void setDiaspago(Integer diaspago) {
		this.diaspago = diaspago;
	}

	public String getFecharegistrostr() {
		return fecharegistrostr;
	}

	public void setFecharegistrostr(String fecharegistrostr) {
		this.fecharegistrostr = fecharegistrostr;
	}

	public String getFechapagostr() {
		return fechapagostr;
	}

	public void setFechapagostr(String fechapagostr) {
		this.fechapagostr = fechapagostr;
	}

	public Timestamp getFechapago() {
		return fechapago;
	}

	public void setFechapago(Timestamp fechapago) {
		this.fechapago = fechapago;
	}

	 

	public Integer getIdempresa() {
		return idempresa;
	}

	public void setIdempresa(Integer idempresa) {
		this.idempresa = idempresa;
	}

	public Integer getTipodocasociado() {
		return tipodocasociado;
	}

	public void setTipodocasociado(Integer tipodocasociado) {
		this.tipodocasociado = tipodocasociado;
	}

	public Integer getDocumentoreferencia() {
		return documentoreferencia;
	}

	public void setDocumentoreferencia(Integer documentoreferencia) {
		this.documentoreferencia = documentoreferencia;
	}

	public Integer getIdoordendecompra() {
		return idoordendecompra;
	}

	public void setIdoordendecompra(Integer idoordendecompra) {
		this.idoordendecompra = idoordendecompra;
	}

	public List<Documentosdetalle> getLsDetalle() {
		return lsDetalle;
	}

	public void setLsDetalle(List<Documentosdetalle> lsDetalle) {
		this.lsDetalle = lsDetalle;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getGlosa() {
		return glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	public Integer getIdsutipocomprovantepagodocumento() {
		return idsutipocomprovantepagodocumento;
	}

	public void setIdsutipocomprovantepagodocumento(Integer idsutipocomprovantepagodocumento) {
		this.idsutipocomprovantepagodocumento = idsutipocomprovantepagodocumento;
	}

	 

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public void setIddocumentos(Integer iddocumentos) {
		this.iddocumentos = iddocumentos;
	}

	public Integer getIddocumentos() {
		return iddocumentos;
	}

	public void setIdtipomoneda(Integer idtipomoneda) {
		this.idtipomoneda = idtipomoneda;
	}

	public Integer getIdtipomoneda() {
		return idtipomoneda;
	}

	public void setIdcondicionespago(Integer idcondicionespago) {
		this.idcondicionespago = idcondicionespago;
	}

	public Integer getIdcondicionespago() {
		return idcondicionespago;
	}

	public void setIdclasificadorcostos(Integer idclasificadorcostos) {
		this.idclasificadorcostos = idclasificadorcostos;
	}

	public Integer getIdclasificadorcostos() {
		return idclasificadorcostos;
	}

	public void setIdcliente(Integer idcliente) {
		this.idcliente = idcliente;
	}

	public Integer getIdcliente() {
		return idcliente;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
	}

	public Double getImpuesto() {
		return impuesto;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotal() {
		return total;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setFecharegistro(Timestamp fecharegistro) {
		this.fecharegistro = fecharegistro;
	}

	public Timestamp getFecharegistro() {
		return fecharegistro;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	 
 	private _Clientes clientes;
 	private Empresa empresa;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

 
	public void setClientes(_Clientes clientes) {
		this.clientes = clientes;
	}

	public _Clientes getClientes() {
		return clientes;
	}

	 
}