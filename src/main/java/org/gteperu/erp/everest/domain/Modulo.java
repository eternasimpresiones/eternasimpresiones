package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Modulo extends Pagination{

    private Integer idmodulo;
    private String descripcion;
    private String icono;
    private String raiz;
    private Integer estado;
    private String accion;
    private List<Pagina> lsPaginas;

    public List<Pagina> getLsPaginas() {
        return lsPaginas;
    }

    public void setLsPaginas(List<Pagina> lsPaginas) {
        this.lsPaginas = lsPaginas;
    }

    public String getRaiz() {
        return raiz;
    }

    public void setRaiz(String raiz) {
        this.raiz = raiz;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
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
}