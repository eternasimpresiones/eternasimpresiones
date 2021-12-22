package org.gteperu.erp.everest.domain;

public class ClienteRuc {

	private boolean success;
	private String error;
	private String nombre_o_razon_social;
	private String estado_del_contribuyente;
	private String direccion;
	private String departamento;
	private String provincia;
	private String distrito;
	private String condicion_de_domicilio;
	private String ruc;
 
	
 	public String estadodelcontribuyente;
	public String condiciondedomicilio;
	public String ubigeo;
	public String tipodevia;
	public String nombredevia;
	public String codigodezona;
	public String tipodezona;
	public String numero;
	public String interior;
	public String lote;
 	public String manzana;
	public String kilometro;
 	

	@Override
	public String toString() {
		return "ClienteRuc [success=" + success + ", error=" + error + ", nombre_o_razon_social="
				+ nombre_o_razon_social + ", estado_del_contribuyente=" + estado_del_contribuyente + ", direccion="
				+ direccion + ", departamento=" + departamento + ", provincia=" + provincia + ", distrito=" + distrito
				+ ", condicion_de_domicilio=" + condicion_de_domicilio + ", ruc=" + ruc + ", estadodelcontribuyente="
				+ estadodelcontribuyente + ", condiciondedomicilio=" + condiciondedomicilio + ", ubigeo=" + ubigeo
				+ ", tipodevia=" + tipodevia + ", nombredevia=" + nombredevia + ", codigodezona=" + codigodezona
				+ ", tipodezona=" + tipodezona + ", numero=" + numero + ", interior=" + interior + ", lote=" + lote
				+ ", manzana=" + manzana + ", kilometro=" + kilometro + "]";
	}

	public String getEstadodelcontribuyente() {
		return estadodelcontribuyente;
	}

	public void setEstadodelcontribuyente(String estadodelcontribuyente) {
		this.estadodelcontribuyente = estadodelcontribuyente;
	}

	public String getCondiciondedomicilio() {
		return condiciondedomicilio;
	}

	public void setCondiciondedomicilio(String condiciondedomicilio) {
		this.condiciondedomicilio = condiciondedomicilio;
	}

	public String getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}

	public String getTipodevia() {
		return tipodevia;
	}

	public void setTipodevia(String tipodevia) {
		this.tipodevia = tipodevia;
	}

	public String getNombredevia() {
		return nombredevia;
	}

	public void setNombredevia(String nombredevia) {
		this.nombredevia = nombredevia;
	}

	public String getCodigodezona() {
		return codigodezona;
	}

	public void setCodigodezona(String codigodezona) {
		this.codigodezona = codigodezona;
	}

	public String getTipodezona() {
		return tipodezona;
	}

	public void setTipodezona(String tipodezona) {
		this.tipodezona = tipodezona;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getInterior() {
		return interior;
	}

	public void setInterior(String interior) {
		this.interior = interior;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getManzana() {
		return manzana;
	}

	public void setManzana(String manzana) {
		this.manzana = manzana;
	}

	public String getKilometro() {
		return kilometro;
	}

	public void setKilometro(String kilometro) {
		this.kilometro = kilometro;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getNombre_o_razon_social() {
		return nombre_o_razon_social;
	}

	public void setNombre_o_razon_social(String nombre_o_razon_social) {
		this.nombre_o_razon_social = nombre_o_razon_social;
	}

	public String getEstado_del_contribuyente() {
		return estado_del_contribuyente;
	}

	public void setEstado_del_contribuyente(String estado_del_contribuyente) {
		this.estado_del_contribuyente = estado_del_contribuyente;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getCondicion_de_domicilio() {
		return condicion_de_domicilio;
	}

	public void setCondicion_de_domicilio(String condicion_de_domicilio) {
		this.condicion_de_domicilio = condicion_de_domicilio;
	}

}
