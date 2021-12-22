/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gteperu.erp.everest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

/**
 *
 * @author GT-GPACHECO
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Emailorden {

    Integer idCentrodecosotos;
    String nombreCentrodecostos;
    String productoDetalle;
     List<Responsable> responsable;
    String orden;
    public void agregarlista(Responsable a){
        responsable.add(a);
    }

    public List<Responsable> getResponsable() {
        return responsable;
    }

    public void setResponsable(List<Responsable> responsable) {
        this.responsable = responsable;
    }
   

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public Integer getIdCentrodecosotos() {
        return idCentrodecosotos;
    }

    public void setIdCentrodecosotos(Integer idCentrodecosotos) {
        this.idCentrodecosotos = idCentrodecosotos;
    }

    public String getNombreCentrodecostos() {
        return nombreCentrodecostos;
    }

    public void setNombreCentrodecostos(String nombreCentrodecostos) {
        this.nombreCentrodecostos = nombreCentrodecostos;
    }

    public String getProductoDetalle() {
        return productoDetalle;
    }

    public void setProductoDetalle(String productoDetalle) {
        this.productoDetalle = productoDetalle;
    }

 

}
