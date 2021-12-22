/*
 * @(#)PaginaRestController.java 1.0 15/08/2016
 *
 * Copyright (c) 2015-2016 Global Technology Experts, Inc.
 * Cll. Mariano de los Santos #197, 3er Piso Of.6, San Isidro, Lima, PERU
 * All rights reserved.
 *
 * Se inició la documentación de los servicios
 */
package org.gteperu.erp.everest.controller;

import org.gteperu.erp.everest.domain.Auditoria;
import org.gteperu.erp.everest.service.PaginaService;
import org.gteperu.erp.everest.domain.Pagina;
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
 * Rest Controller que permite la ejecución de los Servicios Web para las Páginas de un Módulo, tales como:
 * insertar, actualizar, eliminar, buscar por ID de Página, retornar por Estado y filtrar por descripción de Página.
 *
 */
@RestController
@RequestMapping(value = "/api/pagina")
public class PaginaRestController {

    @Resource(name = "paginaService")
    PaginaService paginaService;
    @Resource(name = "auditoriaService")
    AuditoriaService auditoriaService;
    Auditoria auditoria;
    Funciones funciones = new Funciones();

    /**
     * Servicio web que permite registrar como nuevo, actualizar o eliminar: una línea de crédito para un cliente.
     * Se desencadena desde la pestaña Conceptos\Clientes, en el módulo de Ventas.
     *
     * @(#)inserta 1.0 15/08/2016 Se agregó la documentación del servicio [inserta] web para: el Catálogo de Páginas, 
     * rrazuri@gteperu.com
     */
    
    @RequestMapping(value = "/inserta", method = RequestMethod.POST)
    public ResponseWrapper insertaPagina(@RequestBody @Validated Pagina pagina, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        this.auditoria = new Auditoria();
        ResponseWrapper response = new ResponseWrapper();
        try {
    
                //se verifica que el usuario haya iniciado sesión en el sistema, y se registra como log en la tabla Auditoria.
                this.auditoria.setIdempleado(funciones.sacaid(request));
                Integer cat = 0;
                if (pagina.getAccion().equals(Constantes.accionDelet)) {
                    //obtenido el parámetro, el servicio cambiará el estado del objeto afectado
                    if (pagina.getEstado() == 1) {
                        pagina.setEstado(0); //anulado
                    } else {
                        pagina.setEstado(1); //activo
                    }
                    //si en el parámetro acción se envía una D, entonces la operación del servicio será una eliminación
                    this.auditoria.setAccion("D");
                    cat = paginaService.eliminaPagina(pagina);
                } else {
                    if (pagina.getIdpagina() != null && pagina.getIdpagina() > 0) {
                        //si el id del objeto enviado es mayor a cero, entonces la operación será una actualización
                        this.auditoria.setAccion("U");
                        cat = paginaService.updatePagina(pagina);
                    } else {
                        //si el id del registro es cero, entonces la operacion será una inserción
                        this.auditoria.setAccion("I");
                        cat = paginaService.insertaPagina(pagina);
                    }
                }
                //si el registro se realiza correctamente o muestra un error, dicho mensaje se guardará en la tabla de auditoria
                if (cat != null && cat > 0) {
                    this.auditoria.setIdregistro(pagina.getIdpagina());
                    response.setEstado(Constantes.valTransaccionOk);
                    response.setMsg(Constantes.msgTransaccionOk);
                    this.auditoria.setMensaje(Constantes.msgTransaccionOk);
                } else {
                    response.setEstado(Constantes.valTransaccionErrornull);
                    response.setMsg(Constantes.msgTransaccionError);
                    this.auditoria.setMensaje(Constantes.msgTransaccionError);
                }
           
        } catch (Exception e) {
            //En caso de alguna excepción, esta se registrará de igual manera en la tabla Auditoria.
            response.setEstado(Constantes.valTransaccionSinPermiso);
            response.setMsg(Constantes.msgTransaccionError);
               if (e.getMessage().contains("viola la llave foránea")) {
                response.setMsg(Constantes.msgTransaccionErrorForanea);
            } else {
                response.setMsg(Constantes.msgTransaccionError);
            }
            this.auditoria.setAccion(auditoria.getAccion() + "-E");
            this.auditoria.setMensaje(Constantes.msgTransaccionError + e.getMessage());
        } finally {
            //Se registra en la tabla Auditoria, la tabla actual y el servicio web, como referencia y se completa el log del registro.
            this.auditoria.setTabla("pagina");
            this.auditoria.setMetodo("pagina-inserta");
            this.auditoriaService.insertaAuditoria(auditoria);
            //Finalmente se retorma el objeto.
            return response;
        }
    }

//    @RequestMapping(value = "/listTodos", method = RequestMethod.POST)
//    public List<Pagina> listPaginaActivasTodas(Pagina pagina, HttpServletRequest request) throws Exception {
//        HttpSession session = request.getSession();
//        List objtList = new ArrayList();
//        if (session.getAttribute("acceso") != null) {
//            objtList = paginaService.retornaPaginaTodas();
//            return objtList;
//        } else {
//            pagina.setAccion("Si Permiso");
//            objtList.add(pagina);
//            return objtList;
//        }
//    }

    /**
     * Servicio web que permite retornar todas las páginas ´de la aplicación, según su estado (si es 1; son las activas, si es 0; 
     * son las anuladas) esta opción permitirá visualizar todas las Páginas creadas en la Aplicación, en la pestaña de Administración\
     * Páginas, del módulo de Administración.
     * @(#)list 1.0 15/08/2016 Se agregó la documentación del servicio [list] web para: el Catálogo de Páginas,
     * rrazuri@gteperu.com
     */
    
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseWrapper listPaginaActivas(@RequestBody @Validated Pagina pagina, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        List objtList = new ArrayList();
        this.auditoria = new Auditoria();
        ResponseWrapper obj = new ResponseWrapper();
        try {

                //se verifica que el usuario haya iniciado sesión en el sistema.
                if (pagina.getAccion() != null) {
                    //si el atributo acción es diferente de nulo, se iniciará el servicio web.
                    try {
                        //Se obtiene el estado del objeto a través del atributo acción, que se usará como parámetro en el servicio web.
                        int val = Integer.parseInt(pagina.getAccion());
                        pagina.setEstado(val);
                        objtList = paginaService.retornaPaginaPorEstado(pagina);
                        if (objtList != null && objtList.size() > 0) {
                            //si el registro se realizó correctamente se mostrará un mensaje de transacción correcta en pantalla
                            obj.setMsg(Constantes.msgTransaccionOk);
                            obj.setEstado(Constantes.valTransaccionOk);
                            obj.setAaData(objtList);
                            obj.setCantidad(((Pagina)objtList.get(0)).getCantidad());
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
            this.auditoria.setTabla("pagina");
            this.auditoria.setMetodo("pagina-list");
            this.auditoria.setMensaje(Constantes.msgTransaccionError + e.getMessage());
            this.auditoria.setAccion("L-paginas");
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
     * Servicio web que permite filtar por la descripción de una Página, esta opción permitirá realizar búsquedas de páginas de 
     * aplicación, ingresando la descripción de la misma en el Catálogo de Páginas, del módulo de Administración.
     *
     * @(#)like 1.0 15/08/2016 Se agregó la documentación del servicio [like] web para: el Catálogo de Páginas,
     * rrazuri@gteperu.com
     */
    
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public ResponseWrapper listPaginaPorLikeNombre(@RequestBody @Validated Pagina pagina, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        List objtList = new ArrayList();
        this.auditoria = new Auditoria();
        ResponseWrapper obj = new ResponseWrapper();
        try {
  
                //se verifica que el usuario haya iniciado sesión en el sistema.
                if (pagina.getAccion() != null) {
                    //si el atributo acción es diferente de nulo, se iniciará el servicio web.
                    try {
                        //Se envía todo el objeto incluyendo la descripción, que se usará como parámetro en el servicio web.
                        objtList = paginaService.retornaPaginaLikePorNombre(pagina);
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
                    }
                } else {
                }

      
        } catch (Exception e) {
            //En caso de alguna excepción, esta se registrará de igual manera en la tabla Auditoria.
            this.auditoria.setIdempleado(funciones.sacaid(request));
            this.auditoria.setTabla("pagina");
            this.auditoria.setMetodo("pagina-like");
            this.auditoria.setMensaje(Constantes.msgTransaccionError + e.getMessage());
            this.auditoria.setAccion("LK-pagina");
            this.auditoriaService.insertaAuditoria(auditoria);
            obj.setMsg(Constantes.msgTransaccionErrorNull);
            obj.setEstado(Constantes.valTransaccionErrornull);

        } finally {
            //Finalmente se retorna el Objeto.
            return obj;
        }

    }

    /**
     * Servicio web que permite encontrar una página, el cual utiliza como parámetro el Id para encontrar la misma. 
     * Esta opción es utilizada antes de actualizar cualquiera de los campos o eliminar una Página, ya que necesito conocer el Id 
     * de la misma para realizar la acción. Se desencadena desde el Catálogo de Páginas\Actualizar||Eliminar página, en el 
     * módulo de Administración.
     * @(#)finid 1.0 15/08/2016 Se agregó la documentación del servicio [finid] web para: el Catálogo de Páginas,
     * rrazuri@gteperu.com
     */
    
    @RequestMapping(value = "/finid", method = RequestMethod.POST)
    public ResponseWrapper retornaPaginaPorId(@RequestBody @Validated Pagina pagina, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        ResponseWrapper obj = new ResponseWrapper();
        Pagina c = new Pagina();
        this.auditoria = new Auditoria();
        try {

                //se verifica que el usuario haya iniciado sesión en el sistema.
             
                //Se obtiene el Id del objeto, que se usará como parámetro en el servicio web.
                c = paginaService.retornaObjPagina(pagina);
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
            this.auditoria.setTabla("pagina");
            this.auditoria.setMetodo("pagina-finid");
            this.auditoria.setMensaje(Constantes.msgTransaccionError + e.getMessage());
            this.auditoria.setAccion("FD-pagina");
            this.auditoriaService.insertaAuditoria(auditoria);
            obj.setEstado(Constantes.valTransaccionError);
            obj.setMsg(Constantes.msgTransaccionError);
        } finally {
            //Finalmente se retorna el Objeto.
            return obj;
        }

    }
}