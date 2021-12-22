package org.gteperu.erp.everest.domain;

public class EmpresaSunat {
	public String ruc;
	public String nombreorazonsocial;
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
	public String departamento;
	public String manzana;
	public String kilometro;
	public String provincia;
	public String distrito;
 	
	public String getCondiciondedomicilio() {
		return condiciondedomicilio;
	}
	public void setCondiciondedomicilio(String condiciondedomicilio) {
		this.condiciondedomicilio = condiciondedomicilio;
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
	public EmpresaSunat() {
		super();
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getNombreorazonsocial() {
		return nombreorazonsocial;
	}
	public void setNombreorazonsocial(String nombreorazonsocial) {
		this.nombreorazonsocial = nombreorazonsocial;
	}
	public String getEstadodelcontribuyente() {
		return estadodelcontribuyente;
	}
	public void setEstadodelcontribuyente(String estadodelcontribuyente) {
		this.estadodelcontribuyente = estadodelcontribuyente;
	}
	public String getcondiciondedomicilio() {
		return condiciondedomicilio;
	}
	public void setcondiciondedomicilio(String condiciondedomicilio) {
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
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
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
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Empresa [");
		if (ruc != null) {
			builder.append("ruc=");
			builder.append(ruc);
			builder.append(", ");
		}
		if (nombreorazonsocial != null) {
			builder.append("nombreorazonsocial=");
			builder.append(nombreorazonsocial);
			builder.append(", ");
		}
		if (estadodelcontribuyente != null) {
			builder.append("estadodelcontribuyente=");
			builder.append(estadodelcontribuyente);
			builder.append(", ");
		}
		if (condiciondedomicilio != null) {
			builder.append("condiciondedomicilio=");
			builder.append(condiciondedomicilio);
			builder.append(", ");
		}
		if (ubigeo != null) {
			builder.append("ubigeo=");
			builder.append(ubigeo);
			builder.append(", ");
		}
		if (tipodevia != null) {
			builder.append("tipodevia=");
			builder.append(tipodevia);
			builder.append(", ");
		}
		if (nombredevia != null) {
			builder.append("nombredevia=");
			builder.append(nombredevia);
			builder.append(", ");
		}
		if (codigodezona != null) {
			builder.append("codigodezona=");
			builder.append(codigodezona);
			builder.append(", ");
		}
		if (tipodezona != null) {
			builder.append("tipodezona=");
			builder.append(tipodezona);
			builder.append(", ");
		}
		if (numero != null) {
			builder.append("numero=");
			builder.append(numero);
			builder.append(", ");
		}
		if (interior != null) {
			builder.append("interior=");
			builder.append(interior);
			builder.append(", ");
		}
		if (lote != null) {
			builder.append("lote=");
			builder.append(lote);
			builder.append(", ");
		}
		if (departamento != null) {
			builder.append("departamento=");
			builder.append(departamento);
			builder.append(", ");
		}
		if (manzana != null) {
			builder.append("manzana=");
			builder.append(manzana);
			builder.append(", ");
		}
		if (kilometro != null) {
			builder.append("kilometro=");
			builder.append(kilometro);
		}
		builder.append("]");
		return builder.toString();
	}
	public EmpresaSunat(String ruc, String nombreorazonsocial, String estadodelcontribuyente, String condiciondedomicilio,
			String ubigeo, String tipodevia, String nombredevia, String codigodezona, String tipodezona, String numero,
			String interior, String lote, String departamento, String manzana, String kilometro) {
		super();
		this.ruc = ruc;
		this.nombreorazonsocial = nombreorazonsocial;
		this.estadodelcontribuyente = estadodelcontribuyente;
		this.condiciondedomicilio = condiciondedomicilio;
		this.ubigeo = ubigeo;
		this.tipodevia = tipodevia;
		this.nombredevia = nombredevia;
		this.codigodezona = codigodezona;
		this.tipodezona = tipodezona;
		this.numero = numero;
		this.interior = interior;
		this.lote = lote;
		this.departamento = departamento;
		this.manzana = manzana;
		this.kilometro = kilometro;
	}
	
}
