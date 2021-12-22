/*
 * @(#)PerfilespaginasRestController.java 1.0 15/08/2016
 *
 * Copyright (c) 2015-2016 Global Technology Experts, Inc.
 * Cll. Mariano de los Santos #197, 3er Piso Of.6, San Isidro, Lima, PERU
 * All rights reserved.
 *
 * Se inició la documentación de los servicios
 */
package org.gteperu.erp.everest.controller;

import org.gteperu.erp.everest.domain.Auditoria;
import org.gteperu.erp.everest.domain.Pagina;
import org.gteperu.erp.everest.domain.Perfiles;
import org.gteperu.erp.everest.service.PerfilespaginasService;
import org.gteperu.erp.everest.domain.Perfilespaginas;
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
import org.gteperu.erp.everest.service.PaginaService;
import org.gteperu.erp.everest.service.PerfilesService;
import org.gteperu.erp.everest.utils.Constantes;

import javax.annotation.Resource;

/**
 * Rest Controller que permite la ejecución de los Servicios Web para los
 * Perfiles con sus Páginas, tales como: insertar, actualizar, eliminar, buscar
 * por ID de Perfil-Páginas, retornar por Estado, y seleccionar Páginas por
 * Perfil.
 *
 */
@RestController
@RequestMapping(value = "/api/perfilespaginas")
public class PerfilespaginasRestController {

    @Resource(name = "perfilespaginasService")
    PerfilespaginasService perfilespaginasService;
    @Resource(name = "paginaService")
    PaginaService paginaService;
    @Resource(name = "auditoriaService")
    AuditoriaService auditoriaService;
    @Resource(name = "perfilesService")
    PerfilesService perfilesService;
    Auditoria auditoria;
    Funciones funciones = new Funciones();

    /**
     * Servicio web que permite internamente registrar como nuevo, actualizar o
     * eliminar: un detalle de Perfiles y Páginas. Se desencadena desde las
     * acciones y eventos que se originen desde la pestaña
     * Administración\Perfiles, en el módulo de Usuarios Cuenta.
     *
     * @(#)inserta 1.0 15/08/2016 Se agregó la documentación del servicio
     * [inserta] web para: el detalle de Perfiles y Páginas, rrazuri@gteperu.com
     */
    @RequestMapping(value = "/inserta", method = RequestMethod.POST)
    public ResponseWrapper insertaPerfilespaginas(@RequestBody @Validated Perfilespaginas perfilespaginas, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        this.auditoria = new Auditoria();
        ResponseWrapper response = new ResponseWrapper();
        try {

            //se verifica que el usuario haya iniciado sesión en el sistema, y se registra como log en la tabla Auditoria.
            Integer cat = 0;
            if (perfilespaginas.getAccion().equals(Constantes.accionDelet)) {
                //obtenido el parámetro, el servicio cambiará el estado del objeto afectado
                if (perfilespaginas.getEstado() == 1) {
                    perfilespaginas.setEstado(0); //anulado
                } else {
                    perfilespaginas.setEstado(1); //activo
                }
                //si en el parámetro acción se envía una D, entonces la operación del servicio será una eliminación
                this.auditoria.setAccion("D");
                cat = perfilespaginasService.eliminaPerfilespaginas(perfilespaginas);
            } else {
                if (perfilespaginas.getIdperfilespaginas() != null && perfilespaginas.getIdperfilespaginas() > 0) {
                    //si el id del objeto enviado es mayor a cero, entonces la operación será una actualización
                    this.auditoria.setAccion("U");
                    cat = perfilespaginasService.updatePerfilespaginas(perfilespaginas);
                } else {
                    //si el id del registro es cero, entonces la operacion será una inserción
                    this.auditoria.setAccion("I");
                    cat = perfilespaginasService.insertaPerfilespaginas(perfilespaginas);
                }
            }
            //si el registro se realiza correctamente o muestra un error, dicho mensaje se guardará en la tabla de auditoria
            if (cat != null && cat > 0) {
                this.auditoria.setIdregistro(perfilespaginas.getIdperfilespaginas());
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
            if (e.getMessage().contains("viola la llave foránea")) {
                response.setMsg(Constantes.msgTransaccionErrorForanea);
            } else {
                response.setMsg(Constantes.msgTransaccionError);
            }
            this.auditoria.setAccion(auditoria.getAccion() + "-E");
            this.auditoria.setMensaje(Constantes.msgTransaccionError + e.getMessage());
        } finally {
            //Se registra en la tabla Auditoria, la tabla actual y el servicio web, como referencia y se completa el log del registro.
            this.auditoria.setTabla("perfilespaginas");
            this.auditoria.setMetodo("perfilespaginas-inserta");
            this.auditoria.setIdempleado(funciones.sacaid(request));
            this.auditoriaService.insertaAuditoria(auditoria);
            //Finalmente se retorma el objeto.
            return response;
        }
    }

//    @RequestMapping(value = "/listTodos", method = RequestMethod.POST)
//    public List<Perfilespaginas> listPerfilespaginasActivasTodas(Perfilespaginas perfilespaginas, HttpServletRequest request) throws Exception {
//        HttpSession session = request.getSession();
//        List objtList = new ArrayList();
//        if (session.getAttribute("acceso") != null) {
//            objtList = perfilespaginasService.retornaPerfilespaginasTodas();
//            return objtList;
//        } else {
//            perfilespaginas.setAccion("Si Permiso");
//            objtList.add(perfilespaginas);
//            return objtList;
//        }
//    }
    /**
     * Servicio web que permite retornar todos los detalles de perfiles y
     * páginas, según su estado (si es 1; son los activos, si es 0; son los
     * anulados) esta opción permitirá visualizar todos los detalles de Perfiles
     * y páginas creados, en la pestaña de Administración\Perfiles, en el módulo
     * de Usuarios Cuentas.
     *
     * @(#)list 1.0 15/08/2016 Se agregó la documentación del servicio [list]
     * web para: el detalle de Perfiles y Páginas, rrazuri@gteperu.com
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseWrapper listPerfilespaginasActivas(@RequestBody @Validated Perfilespaginas perfilespaginas, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        List objtList = new ArrayList();
        this.auditoria = new Auditoria();
        ResponseWrapper obj = new ResponseWrapper();
        try {

            //se verifica que el usuario haya iniciado sesión en el sistema.
            if (perfilespaginas.getAccion() != null) {
                //si el atributo acción es diferente de nulo, se iniciará el servicio web.
                try {
                    //Se obtiene el estado del objeto a través del atributo acción, que se usará como parámetro en el servicio web.
                    int val = Integer.parseInt(perfilespaginas.getAccion());
                    perfilespaginas.setEstado(val);
                    objtList = perfilespaginasService.retornaPerfilespaginasPorEstado(perfilespaginas);
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
            this.auditoria.setTabla("perfilespaginas");
            this.auditoria.setMetodo("perfilespaginas-list");
            this.auditoria.setMensaje(Constantes.msgTransaccionError + e.getMessage());
            this.auditoria.setAccion("L-perfilespaginas");
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
     * Servicio web que permite encontrar un detalle de perfil y páginas, el
     * cual utiliza como parámetro el Id para encontrar el mismo. Esta opción es
     * utilizada antes de actualizar cualquiera de los campos o eliminar del
     * detalle, ya que necesito conocer el Id del mismo para realizar la acción.
     * Se desencadena internamente desde la pestaña
     * Administración\Perfiles\Actualizar||Eliminar, en el módulo de Usuarios
     * Cuentas.
     *
     * @(#)finid 1.0 15/08/2016 Se agregó la documentación del servicio [finid]
     * web para: el detalle de Perfiles y Páginas, rrazuri@gteperu.com
     */
    @RequestMapping(value = "/finid/{id}", method = RequestMethod.GET)
    public ResponseWrapper retornaPerfilespaginasPorId(@PathVariable("id") Integer id, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        ResponseWrapper obj = new ResponseWrapper();
        Perfilespaginas c = new Perfilespaginas();
        this.auditoria = new Auditoria();
        try {

            //se verifica que el usuario haya iniciado sesión en el sistema.
            c.setIdperfilespaginas(id);
            //Se obtiene el Id del objeto, que se usará como parámetro en el servicio web.
            c = perfilespaginasService.retornaObjPerfilespaginas(c);
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
            this.auditoria.setTabla("perfilespaginas");
            this.auditoria.setMetodo("perfilespaginas-finid");
            this.auditoria.setMensaje(Constantes.msgTransaccionError + e.getMessage());
            this.auditoria.setAccion("FD-perfilespaginas");
            obj.setEstado(Constantes.valTransaccionError);
            obj.setMsg(Constantes.msgTransaccionError);
        } finally {
            //Finalmente se retorna el Objeto.
            return obj;
        }

    }

    /**
     * Servicio web que permite registrar o eliminar las páginas de un perfil de
     * usuario del sistema, y luego retorna todas las páginas agrupadas por
     * perfil, reutilizando un servicio desde paginaRestController.java. Se
     * desencadena desde la pestaña Administración\Perfiles, en el módulo de
     * Usuarios Cuentas.
     *
     * @(#)insertaretorna 1.0 15/08/2016 Se agregó la documentacion del servicio
     * [insertaretorna] web para: el detalle de Perfiles y Páginas,
     * rrazuri@gteperu.com
     */
    @RequestMapping(value = "/insertaretorna", method = RequestMethod.POST)
    public List<Pagina> insertaPerfilespaginasREtorna(@RequestBody @Validated Perfilespaginas perfilespaginas, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        List objtList = new ArrayList();
        this.auditoria = new Auditoria();
        try {


            //se verifica que el usuario haya iniciado sesión en el sistema.
            Integer cat = 0;
            if (perfilespaginas.getEstado().equals("D")) {
                //si en el estado se envía una D, entonces la operación del servicio será una eliminación
                this.auditoria.setAccion("D");
                cat = perfilespaginasService.eliminaPerfilespaginas(perfilespaginas);
            } else {
                //en caso contrario se retornará la página asociada al perfil que lo contenga, desde perfiles y páginas.
                Perfilespaginas ppf = new Perfilespaginas();
                ppf = perfilespaginasService.retornaObjPerfilespaginasPorIdPerIdPagina(perfilespaginas);
                if (ppf != null) {
                    //si el objeto existe en el agrupador de módulos y páginas, se eliminará.
                    this.auditoria.setAccion("D");
                    cat = perfilespaginasService.eliminaPerfilespaginas(ppf);
                } else {
                    //en caso contrario, se insertará como nuevo regristro en el agrupador de módulos y páginas.
                    this.auditoria.setAccion("I");
                    cat = perfilespaginasService.insertaPerfilespaginas(perfilespaginas);
                }
            }
            //si el registro se realiza correctamente o muestra un error, dicho mensaje se guardará en la tabla de auditoria
            if (cat != null && cat > 0) {
                //se registra el estado del objeto como 1.
                perfilespaginas.setEstado(1);
                Perfiles p = new Perfiles();
                p.setId_perfiles(perfilespaginas.getId_perfiles());
                p.setIdagrupadormodulos(perfilespaginas.getIdagrupadormodulos());
                //se retorna todas las páginas mapeadas por perfil
                objtList = perfilesService.retornaPaginasMapPorPerfilAgrupado(p);
                this.auditoria.setIdregistro(perfilespaginas.getIdperfilespaginas());
                this.auditoria.setMensaje(Constantes.msgTransaccionOk);

            } else {
                perfilespaginas.setEstado(0);
                this.auditoria.setMensaje(Constantes.msgTransaccionError);
            }

        } catch (Exception e) {
            //En caso de alguna excepción, el estado del objeto se registra como 1 y se completa el log en la tabla Auditoria.
            perfilespaginas.setEstado(1);
            this.auditoria.setAccion(auditoria.getAccion() + "-E");
            this.auditoria.setMensaje(Constantes.msgTransaccionError + e.getMessage());
        } finally {
            //Se registra en la tabla Auditoria, la tabla actual y el servicio web, como referencia y se completa el log del registro.
            this.auditoria.setTabla("perfilespaginas");
            this.auditoria.setMetodo("perfilespaginas-insertaretorna");
            this.auditoria.setIdempleado(funciones.sacaid(request));
            //this.auditoriaService.insertaAuditoria(auditoria);
            //Finalmente se retorma el objeto.
            return objtList;
        }
    }

    /**
     * Servicio web que permite identificar y marcar las páginas que tiene
     * asignado cada perfil registrado en el sistema se desencadena desde la
     * pestaña Administración\Perfiles, en el módulo de Usuarios Cuentas.
     *
     * @(#)check/{id} 1.0 15/08/2016 Se agregó la documentacion del servicio
     * [check/{id}] web para: el detalle de Perfiles y Páginas,
     * rrazuri@gteperu.com
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String listPaginaPorPerfilChek(@RequestBody @Validated Perfilespaginas perfilespaginas, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        List<Pagina> objtList = new ArrayList();
        Perfiles c = new Perfiles();
        StringBuilder d = new StringBuilder();

        //se verifica que el usuario haya iniciado sesión en el sistema, y se setean los valores de los Id.
        c.setId_perfiles(perfilespaginas.getId_perfiles());
        c.setIdagrupadormodulos(perfilespaginas.getIdagrupadormodulos());
        //se envía todo el objeto incluyendo los Id's como parámetro para que inicie el servicio web.
        objtList = paginaService.retornaPaginasMapPorPerfil(c);
        //se inicializa el objeto StringBuilder con la cabecera del jason que retornará como resultado c/u de las páginas con/sin check
        d.append("{\"checked\":false,\"items\":{");
        for (int i = 0; i < objtList.size(); i++) {
            //se recorre todo el tamaño del objeto que contienen a cada agrupador de módulos con sus páginas.
            Pagina object = objtList.get(i);
            boolean f = true;
            if (object.getSiperfil() == 1) {
                //si la página pertenece al perfil asignado, la marcará con el check
                f = true;
            } else {
                //en caso contrario la dejará sin marcar
                f = false;
            }
            //se agrega la página al jason con los valores que se definieron incialmente seguido de una coma, para continuar agregando las demás
            d.append("\"" + object.getIdpagina() + "\":" + f + ",");
        }
        if (d.length() > 1) {
            //Si la longitud del objeto es mayor a 1 (no está vacío), elimina el caracter del último índice (",")
            d.deleteCharAt(d.length() - 1);
        }
        //cierra la cadena del jason, en el objeto.
        d.append("}}");

        //Finalmente retorna el objeto cadena.
        return d.toString();
    }
}