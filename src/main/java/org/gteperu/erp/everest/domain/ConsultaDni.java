package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsultaDni {
private boolean success;
 private String error;
 
 private String DNI;
 private String Nombres;
 private String FechaNacimiento;
 private String ApellidoMaterno;





public String getFechaNacimiento() {
	return FechaNacimiento;
}
public void setFechaNacimiento(String fechaNacimiento) {
	FechaNacimiento = fechaNacimiento;
}
public String getApellidoMaterno() {
	return ApellidoMaterno;
}
public void setApellidoMaterno(String apellidoMaterno) {
	ApellidoMaterno = apellidoMaterno;
}
public String getDNI() {
	return DNI;
}
public void setDNI(String dNI) {
	DNI = dNI;
}
public String getNombres() {
	return Nombres;
}
public void setNombres(String nombres) {
	Nombres = nombres;
}
public String getError() {
	return error;
}
public void setError(String error) {
	this.error = error;
}
public boolean isSuccess() {
	return success;
}
public void setSuccess(boolean success) {
	this.success = success;
}
 
}
