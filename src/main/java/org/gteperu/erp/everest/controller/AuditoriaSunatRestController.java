/*
 * @(#)AuditoriaRestController.java 1.0 20/10/2016
 *
 * Copyright (c) 2015-2016 Global Technology Experts, Inc.
 * Cll. Mariano de los Santos #197, 3er Piso Of.6, San Isidro, Lima, PERU
 * All rights reserved.
 *
 * Se inició la documentación de los servicios
 */
package org.gteperu.erp.everest.controller;

import org.gteperu.erp.everest.domain.Auditoria;
import org.gteperu.erp.everest.domain.Auditoria_Sunat;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.service.AuditoriaService;
import org.gteperu.erp.everest.service.Auditoria_SunatService;
import org.gteperu.erp.everest.utils.Constantes;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/auditoriasunat")
public class AuditoriaSunatRestController {

    @Resource(name = "auditoriaSunatService")
    Auditoria_SunatService auditoriaSunatService;
    Funciones funciones = new Funciones();

    /**
     * Servicio web que permite retornar una auditoria según el id de documento
     * 
     * Integer id_documento
     * 
     * EA 17-02-2021
     */
    @RequestMapping(value = "/listxIddocumento/{id}", method = RequestMethod.GET)
    public ResponseWrapper listxIddocumento(@PathVariable("id") Integer id, HttpServletRequest request)
            throws Exception {

    	Auditoria_Sunat objtList = new Auditoria_Sunat();
        ResponseWrapper obj = new ResponseWrapper();
        try {

                objtList = auditoriaSunatService.retornaAuditoriaSunatxiddocumento(id);
                if (objtList != null) {
                    // si el registro se realizó correctamente se mostrará un mensaje de transacción
                    // correcta en pantalla
                    obj.setMsg(Constantes.msgTransaccionOk);
                    obj.setEstado(Constantes.valTransaccionOk);
                    obj.setDefaultObj(objtList);
                } else {
                    // en caso contrario se mostrará un mensaje de parámetro erróneo en pantalla
                    obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
                    obj.setEstado(Constantes.valTransaccionNoEncontro);
                }


        } catch (Exception e) {
            // En caso de alguna excepción, esta se mostrará de igual manera en el cliente.
            obj.setMsg(Constantes.msgTransaccionErrorNull);
            obj.setEstado(Constantes.valTransaccionNoEncontro);

        }
        return obj;

    }

}
