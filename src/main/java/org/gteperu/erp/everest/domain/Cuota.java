package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cuota {

    private String nombre;
    private Double monto_pago;
    private String fecha_cuota_str;
    private Timestamp fecha_cuota;
    
    
    private Integer idcuota;
    private Integer id_documento;
    
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getMonto_pago() {
		return monto_pago;
	}
	public void setMonto_pago(Double monto_pago) {
		this.monto_pago = monto_pago;
	}
	public String getFecha_cuota_str() {
		return fecha_cuota_str;
	}
	public void setFecha_cuota_str(String fecha_cuota_str) {
		this.fecha_cuota_str = fecha_cuota_str;
	}
	public Timestamp getFecha_cuota() {
		return fecha_cuota;
	}
	public void setFecha_cuota(Timestamp fecha_cuota) {
		this.fecha_cuota = fecha_cuota;
	}
	public Integer getIdcuota() {
		return idcuota;
	}
	public void setIdcuota(Integer idcuota) {
		this.idcuota = idcuota;
	}
	public Integer getId_documento() {
		return id_documento;
	}
	public void setId_documento(Integer id_documento) {
		this.id_documento = id_documento;
	}


    
}