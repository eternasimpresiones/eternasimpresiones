/*
 * @(#)ModuloRestController.java 1.0 14/08/2016
 *
 * Copyright (c) 2015-2016 Global Technology Experts, Inc.
 * Cll. Mariano de los Santos #197, 3er Piso Of.6, San Isidro, Lima, PERU
 * All rights reserved.
 *
 * Se inició la documentación de los servicios
 */
package org.gteperu.erp.everest.controller;

import org.gteperu.erp.everest.domain.Auditoria;
import org.gteperu.erp.everest.service.ModuloService;
import org.gteperu.erp.everest.domain.Modulo;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.service.AuditoriaService;
import org.gteperu.erp.everest.utils.Constantes;

import javax.annotation.Resource;

/**
 * Rest Controller que permite la ejecución de los Servicios Web para los módulos de la aplicación, tales como insertar,
 * actualizar, eliminar, buscar por ID de Módulo, retornar por Estado y filtrar por nombre de Módulo.
 *
 */
@RestController
@RequestMapping(value = "/api/modulo")
public class ModuloRestController {

    @Resource(name = "moduloService")
    ModuloService moduloService;
    @Resource(name = "auditoriaService")
    AuditoriaService auditoriaService;
    Auditoria auditoria;
    Funciones funciones = new Funciones();

    /**
     * Servicio web que permite registrar como nuevo, actualizar o eliminar: un módulo funcional de la aplicación.
     * Se desencadena desde la pestaña Administración\Modulos, en el módulo de Administración.
     *
     * @(#)inserta 1.0 14/08/2016 Se agregó la documentación del servicio [inserta] web para: el Catálogo de Módulos,
     * rrazuri@gteperu.com
     */
    
    @RequestMapping(value = "/inserta", method = RequestMethod.POST)
    public ResponseWrapper insertaModulo(@RequestBody @Validated Modulo modulo, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        
        ResponseWrapper response = new ResponseWrapper();
        try {
                //se verifica que el usuario haya iniciado sesión en el sistema, y se registra como log en la tabla Auditoria.
                //this.auditoria.setIdempleado(funciones.sacaid(request));
                Integer cat = 0;
                if (modulo.getAccion().equals(Constantes.accionDelet)) {
                    //obtenido el parámetro, el servicio cambiará el estado del objeto afectado
                    if (modulo.getEstado() == 1) {
                        modulo.setEstado(0); //anulado
                    } else {
                        modulo.setEstado(1); //activo
                    }
                    //si en el parámetro acción se envía una D, entonces la operación del servicio será una eliminación
                    
                    cat = moduloService.eliminaModulo(modulo);
                } else {
                    if (modulo.getIdmodulo() != null && modulo.getIdmodulo() > 0) {
                        //si el id del objeto enviado es mayor a cero, entonces la operación será una actualización
                        
                        cat = moduloService.updateModulo(modulo);
                    } else {
                        //si el id del registro es cero, entonces la operacion será una inserción
                       
                        cat = moduloService.insertaModulo(modulo);
                    }
                }
                //si el registro se realiza correctamente o muestra un error, dicho mensaje se guardará en la tabla de auditoria
                if (cat != null && cat > 0) {
                    response.setEstado(Constantes.valTransaccionOk);
                    response.setMsg(Constantes.msgTransaccionOk);
                } else {
                    response.setEstado(Constantes.valTransaccionErrornull);
                    response.setMsg(Constantes.msgTransaccionError);
                }
      
        } catch (Exception e) {
            //En caso de alguna excepción, esta se registrará de igual manera en la tabla Auditoria.
            response.setEstado(Constantes.valTransaccionSinPermiso);
            response.setMsg(Constantes.msgTransaccionError);
            this.auditoria.setAccion(auditoria.getAccion() + "-E");
              if (e.getMessage().contains("viola la llave foránea")) {
                response.setMsg(Constantes.msgTransaccionErrorForanea);
            } else {
                response.setMsg(Constantes.msgTransaccionError);
            }
            this.auditoria.setMensaje(Constantes.msgTransaccionError + e.getMessage());
        } finally {
            //Se registra en la tabla Auditoria, la tabla actual y el servicio web, como referencia y se completa el log del registro.
            this.auditoria.setTabla("modulo");
            this.auditoria.setMetodo("modulo-inserta");
            this.auditoriaService.insertaAuditoria(auditoria);
            //Finalmente se retorma el objeto.
            return response;
        }
    }

//    @RequestMapping(value = "/listTodos", method = RequestMethod.POST)
//    public List<Modulo> listModuloActivasTodas(Modulo modulo, HttpServletRequest request) throws Exception {
//        HttpSession session = request.getSession();
//        List objtList = new ArrayList();
//        if (session.getAttribute("acceso") != null) {
//            objtList = moduloService.retornaModuloTodas();
//            return objtList;
//        } else {
//            modulo.setAccion("Si Permiso");
//            objtList.add(modulo);
//            return objtList;
//        }
//    }

    /**
     * Servicio web que permite retornar todos los módulos de la aplicación, según su estado (si es 1; son las activas, si es 0; son 
     * las anuladas) esta opción permitirá visualizar todos los Módulos creados para la Aplicación, en la pestaña de Administración\Modulos, 
     * del módulo de Administración.
     * @(#)list 1.0 14/08/2016 Se agregó la documentación del servicio [list] web para: el Catálogo de Módulos,
     * rrazuri@gteperu.com
     */
    
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseWrapper listModuloActivas(@RequestBody @Validated Modulo modulo, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        List objtList = new ArrayList();
        this.auditoria = new Auditoria();
        ResponseWrapper obj = new ResponseWrapper();
        try {
         
                //se verifica que el usuario haya iniciado sesión en el sistema.
                if (modulo.getAccion() != null) {
                    //si el atributo acción es diferente de nulo, se iniciará el servicio web.
                    try {
                        //Se obtiene el estado del objeto a través del atributo acción, que se usará como parámetro en el servicio web.
                        int val = Integer.parseInt(modulo.getAccion());
                        modulo.setEstado(val);
                        objtList = moduloService.retornaModuloPorEstado(modulo);
                        if (objtList != null && objtList.size() > 0) {
                            //si el registro se realizó correctamente se mostrará un mensaje de transacción correcta en pantalla
                            obj.setMsg(Constantes.msgTransaccionOk);
                            obj.setEstado(Constantes.valTransaccionOk);
                            obj.setAaData(objtList);
                            obj.setCantidad(((Modulo)objtList.get(0)).getCantidad());
                        } else {
                            //en caso contrario se mostrará un mensaje de parámetro erróneo en pantalla
                            obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
                            obj.setEstado(Constantes.valTransaccionNoEncontro);
                            obj.setAaData(objtList);
                        }
                    } catch (Exception e) {
                        //en caso de alguna otra excepción se mostrará el mensaje en pantalla
                        obj.setMsg(Constantes.msgTransaccionErrorNull + "Convirtiendo a numero");
                        obj.setEstado(Constantes.valTransaccionErrornull);
                        obj.setAaData(objtList);
                    }


                } else {
                }

          
        } catch (Exception e) {
            //En caso de alguna excepción, esta se registrará de igual manera en la tabla Auditoria.
            this.auditoria.setIdempleado(funciones.sacaid(request));
            this.auditoria.setTabla("modulo");
            this.auditoria.setMetodo("modulo-list");
            this.auditoria.setMensaje(Constantes.msgTransaccionError + e.getMessage());
            this.auditoria.setAccion("L-modulo");
            this.auditoriaService.insertaAuditoria(auditoria);
            obj.setMsg(Constantes.msgTransaccionErrorNull);
            obj.setEstado(Constantes.valTransaccionErrornull);
            obj.setAaData(objtList);

        } finally {
            //Finalmente se retorma el objeto.
            return obj;
        }

    }

    /**
     * Servicio web que permite filtar por el nombre de un Módulo, esta opción permitirá realizar búsquedas de módulos 
     * funcionales de la aplicación, ingresando el nombre del mismo en el Catálogo de Modulos, del módulo de Administración.
     *
     * @(#)like 1.0 14/08/2016 Se agregó la documentación del servicio [like] web para: el Catálogo de Módulos,
     * rrazuri@gteperu.com
     */
    
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public ResponseWrapper listModuloPorLikeNombre(@RequestBody @Validated Modulo modulo, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        List objtList = new ArrayList();
        this.auditoria = new Auditoria();
        ResponseWrapper obj = new ResponseWrapper();
        try {
         
                //se verifica que el usuario haya iniciado sesión en el sistema.
                if (modulo.getAccion() != null) {
                    //si el atributo acción es diferente de nulo, se iniciará el servicio web.
                    try {
                        //Se obtiene la descripción del objeto a través del atributo acción, que se usará como parámetro en el servicio web.
                        modulo.setDescripcion(modulo.getAccion());
                        objtList = moduloService.retornaModuloLikePorNombre(modulo);
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
                        //en caso de alguna otra excepción se mostrará el mensaje en pantalla
                        obj.setMsg(Constantes.msgTransaccionErrorNull + "Convirtiendo a numero");
                        obj.setEstado(Constantes.valTransaccionErrornull);
                        obj.setAaData(objtList);
                    }
                } else {
                }

        } catch (Exception e) {
            //En caso de alguna excepción, esta se registrará de igual manera en la tabla Auditoria.
            this.auditoria.setIdempleado(funciones.sacaid(request));
            this.auditoria.setTabla("modulo");
            this.auditoria.setMetodo("modulo-like");
            this.auditoria.setMensaje(Constantes.msgTransaccionError + e.getMessage());
            this.auditoria.setAccion("LK-modulo");
            this.auditoriaService.insertaAuditoria(auditoria);
            obj.setMsg(Constantes.msgTransaccionErrorNull);
            obj.setEstado(Constantes.valTransaccionErrornull);
            obj.setAaData(objtList);

        } finally {
            //Finalmente se retorna el Objeto.
            return obj;
        }

    }

    /**
     * Servicio web que permite encontrar un módulo, el cual utiliza como parámetro el Id para encontrar el mismo. 
     * Esta opción es utilizada antes de actualizar cualquiera de los campos o eliminar un Módulo, ya que necesito conocer el Id 
     * del mismo para realizar la acción. Se desencadena desde el Catálogo de Módulos\Actualizar||Eliminar módulo, en el módulo
     * de Administración.
     * @(#)finid 1.0 14/08/2016 Se agregó la documentación del servicio [finid] web para: el Catálogo de Módulos,
     * rrazuri@gteperu.com
     */
    
    @RequestMapping(value = "/finid", method = RequestMethod.POST)
    public ResponseWrapper retornaModuloPorId(@RequestBody @Validated Modulo modulo, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        ResponseWrapper obj = new ResponseWrapper();
        Modulo c = new Modulo();
        this.auditoria = new Auditoria();
        try {
       
                //se verifica que el usuario haya iniciado sesión en el sistema.
                
                //Se obtiene el Id del objeto, que se usará como parámetro en el servicio web.
                c = moduloService.retornaObjModulo(modulo);
                if (c != null) {
                    //si el objeto es diferente de nulo, se mostrará un mensaje de transacción correcta en pantalla
                    obj.setDefaultObj(c);
                    obj.setEstado(Constantes.valTransaccionOk);
                    obj.setMsg(Constantes.msgTransaccionOk);
                } else {
                    //en caso contrario se mostrará un mensaje de transacción errónea en pantalla
                    obj.setEstado(Constantes.valTransaccionNoEncontro);
                    obj.setMsg(Constantes.msgTransaccionNoEncontrada);
                }
           
        } catch (Exception e) {
            //En caso de alguna excepción, esta se registrará de igual manera en la tabla Auditoria.
            this.auditoria.setIdempleado(funciones.sacaid(request));
            this.auditoria.setTabla("modulo");
            this.auditoria.setMetodo("modulo-finid");
            this.auditoria.setMensaje(Constantes.msgTransaccionError + e.getMessage());
            this.auditoria.setAccion("FD-modulo");
            this.auditoriaService.insertaAuditoria(auditoria);
            obj.setEstado(Constantes.valTransaccionError);
            obj.setMsg(Constantes.msgTransaccionError);
        } finally {
            //Finalmente se retorna el Objeto.
            return obj;
        }

    }
}