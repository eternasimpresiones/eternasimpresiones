/*
 * @(#)LocalRestController.java 1.0 14/08/2016
 *
 * Copyright (c) 2015-2016 Global Technology Experts, Inc.
 * Cll. Mariano de los Santos #197, 3er Piso Of.6, San Isidro, Lima, PERU
 * All rights reserved.
 *
 * Se inició la documentación de los servicios
 */
package org.gteperu.erp.everest.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.gteperu.erp.everest.domain.Auditoria;
import org.gteperu.erp.everest.domain.Local;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service.AuditoriaService;
import org.gteperu.erp.everest.service.LocalService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller que permite la ejecución de los Servicios Web para los Locales de una Empresa, tales como insertar,
 * actualizar, eliminar, buscar por ID de Local, retornar por Estado y filtrar por nombre de Local.
 *
 */
@RestController
@RequestMapping(value = "/api/local")
public class LocalRestController {

    @Resource(name = "localService")
    LocalService localService;
     
    /**
     * Servicio web que permite registrar como nuevo, actualizar o eliminar: una línea de crédito para un cliente.
     * Se desencadena desde la pestaña Conceptos\Clientes, en el módulo de Ventas.
     *
     * @(#)inserta 1.0 14/08/2016 Se agregó la documentación del servicio [inserta] web para: el Catálogo de Locales,
     * fchuquilind@gmail.com
     */
    
    @RequestMapping(value = "/insertaLocal", method = RequestMethod.POST)
    public ResponseWrapper insertaLocal(@RequestBody @Validated Local local, HttpServletRequest request) throws Exception {
         ResponseWrapper response = new ResponseWrapper();
        try {
                Integer cat = 0;
                          cat = localService.insertaLocal(local);
                 if (cat != null && cat > 0) {
                     response.setEstado(Constantes.valTransaccionOk);
                    response.setMsg(Constantes.msgTransaccionOk);
                    response.setDefaultObj(local);
                 } else {
                    response.setEstado(Constantes.valTransaccionErrornull);
                    response.setMsg(Constantes.msgTransaccionError);
                 }
            
        } catch (Exception e) {
    		System.out.println(this.getClass().getSimpleName() + " insertaLocal. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/insertaLocal" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/insertaLocal",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
							local);
        } 		
        return response;
    }
 
    /**
     * Servicio web que permite registrar como nuevo, actualizar o eliminar: una línea de crédito para un cliente.
     * Se desencadena desde la pestaña Conceptos\Clientes, en el módulo de Ventas.
     *
     * @(#)inserta 1.0 14/08/2016 Se agregó la documentación del servicio [inserta] web para: el Catálogo de Locales,
     * fchuquilind@gmail.com
     */
    
    @RequestMapping(value = "/updateLocal", method = RequestMethod.POST)
    public ResponseWrapper updateLocal(@RequestBody @Validated Local local, HttpServletRequest request) throws Exception {
        
         ResponseWrapper response = new ResponseWrapper();
        try {
            
                 Integer cat = 0;
                  cat = localService.updateLocal(local);
                 if (cat != null && cat > 0) {
                     response.setEstado(Constantes.valTransaccionOk);
                    response.setMsg(Constantes.msgTransaccionOk);
                    response.setDefaultObj(local);
                 } else {
                    response.setEstado(Constantes.valTransaccionErrornull);
                    response.setMsg(Constantes.msgTransaccionError);
                 }
            
        } catch (Exception e) {
    		System.out.println(this.getClass().getSimpleName() + " updateLocal. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/updateLocal" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/updateLocal",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
							local);
        } 		
        return response;
    }

    
    @RequestMapping(value = "/eliminaLocal", method = RequestMethod.POST)
    public ResponseWrapper eliminaLocal(@RequestBody @Validated Local local, HttpServletRequest request) throws Exception {
        
         ResponseWrapper response = new ResponseWrapper();
        try {
        		Integer cat = 0;
        		cat = localService.eliminaLocal(local);
                //si el registro se realiza correctamente o muestra un error, dicho mensaje se guardará en la tabla de auditoria
                if (cat != null && cat > 0) {
                     response.setEstado(Constantes.valTransaccionOk);
                    response.setMsg(Constantes.msgTransaccionOk);
                    response.setDefaultObj(local);
                 } else {
                    response.setEstado(Constantes.valTransaccionErrornull);
                    response.setMsg(Constantes.msgTransaccionError);
                 }
            
        } catch (Exception e) {
    		System.out.println(this.getClass().getSimpleName() + " eliminaLocal. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/eliminaLocal" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/eliminaLocal",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
							local);
        } 		
        return response;

    }
    /**
     * Servicio web que permite retornar todos los locales de una empresa cliente, según su estado (si es 1; son las habilitadas, si es 0; son 
     * las inhabilitadas) esta opción permitirá visualizar todos los Locales creados para un Empresa, en la pestaña de Catálogos\Locales, del 
     * módulo de Configuración.
     * @(#)list 1.0 14/08/2016 Se agregó la documentación del servicio [list] web para: el Catálogo de Locales,
     * fchuquilind@gmail.com
     */
    
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseWrapper listLocalActivas(@RequestBody @Validated Local local, HttpServletRequest request) throws Exception {
        
        List objtList = new ArrayList();
         ResponseWrapper obj = new ResponseWrapper();
        try {
                      //Se obtiene el estado del objeto a través del atributo acción, que se usará como parámetro en el servicio web.
                         objtList = localService.retornaLocalPorEstado(local);
                        if (objtList != null && objtList.size() > 0) {
                            //si el registro se realizó correctamente se mostrará un mensaje de transacción correcta en pantalla
                            obj.setMsg(Constantes.msgTransaccionOk);
                            obj.setEstado(Constantes.valTransaccionOk);
                            obj.setAaData(objtList);
                            obj.setCantidad(((Local)objtList.get(0)).getCantidad());

                        } else {
                            //en caso contrario se mostrará un mensaje de parámetro erróneo en pantalla
                            obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
                            obj.setEstado(Constantes.valTransaccionNoEncontro);
                            obj.setAaData(objtList);
                        }
          } catch (Exception e) {
    		System.out.println(this.getClass().getSimpleName() + " list. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/list" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/list",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
							local);
        } 		
        return obj;

    }

    @RequestMapping(value = "/retornaLocalTodas", method = RequestMethod.POST)
    public ResponseWrapper retornaLocalTodas(@RequestBody @Validated Local local, HttpServletRequest request) throws Exception {
        
        List objtList = new ArrayList();
         ResponseWrapper obj = new ResponseWrapper();
        try {

            //Se obtiene el estado del objeto a través del atributo acción, que se usará como parámetro en el servicio web.
               objtList = localService.retornaLocalTodas(local);
              if (objtList != null && objtList.size() > 0) {
                  //si el registro se realizó correctamente se mostrará un mensaje de transacción correcta en pantalla
                  obj.setMsg(Constantes.msgTransaccionOk);
                  obj.setEstado(Constantes.valTransaccionOk);
                  obj.setAaData(objtList);
              } else {
                  //en caso contrario se mostrará un mensaje de parámetro erróneo en pantalla
                  obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
                  obj.setEstado(Constantes.valTransaccionNoEncontro);
                  obj.setAaData(objtList);
              }
        
        } catch (Exception e) {
    		System.out.println(this.getClass().getSimpleName() + " retornaLocalTodas. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/retornaLocalTodas" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/retornaLocalTodas",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
							local);
        } 		
        return obj;

    }
 
}