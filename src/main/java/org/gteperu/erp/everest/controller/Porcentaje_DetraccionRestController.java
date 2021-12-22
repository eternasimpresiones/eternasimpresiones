/*
 * @(#)PerfilesRestController.java 1.0 15/08/2016
 *
 * Copyright (c) 2015-2016 Global Technology Experts, Inc.
 * Cll. Mariano de los Santos #197, 3er Piso Of.6, San Isidro, Lima, PERU
 * All rights reserved.
 *
 * Se inició la documentación de los servicios
 */
package org.gteperu.erp.everest.controller;

import org.gteperu.erp.everest.service.PorcentajeDetraccionService;
import org.gteperu.erp.everest.domain.PorcentajeDetraccion;

import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.utils.Constantes;


/**
 * Rest Controller que permite la ejecución de los Servicios Web para los
 * Perfiles de un usuario, tales como: insertar, actualizar, eliminar, buscar
 * por ID de Perfil, retornar por Estado, y filtrar por nombre de Perfil.
 *
 */
@RestController
@RequestMapping(value = "/api/porcentajeDetraccion")
public class Porcentaje_DetraccionRestController {

    @Autowired
    PorcentajeDetraccionService porcentajeDetraccion;
   

    /**
     * Servicio web que permite retornar todos los porcentajes de detraccion 
     * 
     * MAD 
     * 
     * 11-03-2020
     */
    @RequestMapping(value = "/listarPorcentaje", method = RequestMethod.POST)
    public ResponseWrapper listarPorcentaje(@RequestBody @Validated PorcentajeDetraccion procentajeDetraccion, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        List objtList = new ArrayList();
        ResponseWrapper obj = new ResponseWrapper();
        try {
                        objtList = porcentajeDetraccion.listarPorcentajeDetraccion();
                        if (objtList != null && objtList.size() > 0) {
                            obj.setMsg(Constantes.msgTransaccionOk);
                            obj.setEstado(Constantes.valTransaccionOk);
                            obj.setAaData(objtList);
                        } else {
                            obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
                            obj.setEstado(Constantes.valTransaccionNoEncontro);
                            obj.setAaData(objtList);
                        }

        } catch (Exception e) {
            obj.setMsg(Constantes.msgTransaccionErrorNull);
            obj.setEstado(Constantes.valTransaccionErrornull);
            obj.setAaData(objtList);
            throw new RuntimeException();
        } finally {
            //Finalmente se retorma el objeto.
            return obj;
        }

    }

    
}