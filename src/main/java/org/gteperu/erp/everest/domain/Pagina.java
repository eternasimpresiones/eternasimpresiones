package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import java.sql.Blob;
import java.sql.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pagina extends Pagination{

    private Integer idpagina;
    private Integer idmodulo;
    private String descripcion;
    private String url;
    private String parametros;
    private String icono;
    private Integer estado;
    private String accion;
    private String nameModulo;
    private Integer siperfil;
    private Integer idperfil;
    private Modulo modulo;
    private Integer idagrupadormodulos;

    public Integer getIdagrupadormodulos() {
        return idagrupadormodulos;
    }

    public void setIdagrupadormodulos(Integer idagrupadormodulos) {
        this.idagrupadormodulos = idagrupadormodulos;
    }
    
    public String getNameModulo() {
        return nameModulo;
    }

    public void setNameModulo(String nameModulo) {
        this.nameModulo = nameModulo;
    }

    public Integer getSiperfil() {
        return siperfil;
    }

    public void setSiperfil(Integer siperfil) {
        this.siperfil = siperfil;
    }

    public Integer getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(Integer idperfil) {
        this.idperfil = idperfil;
    }

    public void setIdpagina(Integer idpagina) {
        this.idpagina = idpagina;
    }

    public Integer getIdpagina() {
        return idpagina;
    }

    public void setIdmodulo(Integer idmodulo) {
        this.idmodulo = idmodulo;
    }

    public Integer getIdmodulo() {
        return idmodulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setParametros(String parametros) {
        this.parametros = parametros;
    }

    public String getParametros() {
        return parametros;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getIcono() {
        return icono;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }


    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Modulo getModulo() {
        return modulo;
    }
}