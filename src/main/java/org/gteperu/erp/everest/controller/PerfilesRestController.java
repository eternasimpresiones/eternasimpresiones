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

import org.gteperu.erp.everest.domain.Auditoria;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain.Modulo;
import org.gteperu.erp.everest.domain.Pagina;
import org.gteperu.erp.everest.service.PerfilesService;
import org.gteperu.erp.everest.domain.Perfiles;
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
import org.apache.ibatis.session.SqlSessionException;

/**
 * Rest Controller que permite la ejecución de los Servicios Web para los
 * Perfiles de un usuario, tales como: insertar, actualizar, eliminar, buscar
 * por ID de Perfil, retornar por Estado, y filtrar por nombre de Perfil.
 *
 */
@RestController
@RequestMapping(value = "/api/perfiles")
public class PerfilesRestController {

    @Resource(name = "perfilesService")
    PerfilesService perfilesService;
    @Resource(name = "auditoriaService")
    AuditoriaService auditoriaService;
    Auditoria auditoria;
    Funciones funciones = new Funciones();

    /**
     * Servicio web que permite registrar como nuevo, actualizar o eliminar: un
     * Perfil de Usuario. Se desencadena desde la pestaña
     * Administración\Perfiles, en el módulo de Usuarios Cuenta.
     *
     * @(#)inserta 1.0 15/08/2016 Se agregó la documentación del servicio
     * [inserta] web para: el Catálogo de Perfiles, rrazuri@gteperu.com
     */
    @RequestMapping(value = "/inserta", method = RequestMethod.POST)
    public ResponseWrapper insertaPerfiles(@RequestBody @Validated Perfiles perfiles, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        this.auditoria = new Auditoria();
        ResponseWrapper response = new ResponseWrapper();
        try {
                  //se verifica que el usuario haya iniciado sesión en el sistema, y se registra como log en la tabla Auditoria.
                Integer cat = 0;
                if (perfiles.getAccion().equals(Constantes.accionDelet)) {
                    //obtenido el parámetro, el servicio cambiará el estado del objeto afectado
                    if (perfiles.getEstado() == 1) {
                        perfiles.setEstado(0); //anulado
                    } else {
                        perfiles.setEstado(1); //activo
                    }
                    //si en el parámetro acción se envía una D, entonces la operación del servicio será una eliminación
                    this.auditoria.setAccion("D");
                    cat = perfilesService.eliminaPerfiles(perfiles);
                } else {
                    if (perfiles.getId_perfiles() != null && perfiles.getId_perfiles() > 0) {
                        //si el id del objeto enviado es mayor a cero, entonces la operación será una actualización
                        this.auditoria.setAccion("U");
                        cat = perfilesService.updatePerfiles(perfiles);
                    } else {
                        //si el id del registro es cero, entonces la operacion será una inserción
                        this.auditoria.setAccion("I");
                        cat = perfilesService.insertaPerfiles(perfiles);
                    }
                }
                //si el registro se realiza correctamente o muestra un error, dicho mensaje se guardará en la tabla de auditoria
                if (cat != null && cat > 0) {
                    this.auditoria.setIdregistro(perfiles.getId_perfiles());
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
            try {
                this.auditoria.setTabla("perfiles");
                this.auditoria.setMetodo("perfiles-inserta");
                //this.auditoriaService.insertaAuditoria(auditoria);
            } catch (Exception e) {
                response.setEstado(Constantes.valTransaccionSinPermiso);
                response.setMsg(Constantes.msgTransaccionError);
            }
            //Se registra en la tabla Auditoria, la tabla actual y el servicio web, como referencia y se completa el log del registro.

            //Finalmente se retorma el objeto.
            return response;
        }
    }

//    @RequestMapping(value = "/listTodos", method = RequestMethod.POST)
//    public List<Perfiles> listPerfilesActivasTodas(Perfiles perfiles, HttpServletRequest request) throws Exception {
//        HttpSession session = request.getSession();
//        List objtList = new ArrayList();
//        if (session.getAttribute("acceso") != null) {
//            objtList = perfilesService.retornaPerfilesTodas();
//            return objtList;
//        } else {
//            perfiles.setAccion("Si Permiso");
//            objtList.add(perfiles);
//            return objtList;
//        }
//    }
    /**
     * Servicio web que permite retornar todos los perfiles de Usuario, según su
     * estado (si es 1; son los activos, si es 0; son los anulados) esta opción
     * permitirá visualizar todos los Perfiles creados para los Usuarios, en la
     * pestaña de Administración\Perfiles, en el módulo de Usuarios Cuentas.
     *
     * @(#)list 1.0 15/08/2016 Se agregó la documentación del servicio [list]
     * web para: el Catálogo de Perfiles, rrazuri@gteperu.com
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseWrapper listPerfilesActivas(@RequestBody @Validated Perfiles perfiles, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        List objtList = new ArrayList();
        this.auditoria = new Auditoria();
        ResponseWrapper obj = new ResponseWrapper();
        try {
       
                //se verifica que el usuario haya iniciado sesión en el sistema.
                if (perfiles.getAccion() != null) {
                    //si el atributo acción es diferente de nulo, se iniciará el servicio web.
                    try {
                        //Se obtiene el estado del objeto a través del atributo acción, que se usará como parámetro en el servicio web.
                        int val = Integer.parseInt(perfiles.getAccion());
                        perfiles.setEstado(val);
                        objtList = perfilesService.retornaPerfilesPorEstado(perfiles);
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
                            obj.setCantidad(((Perfiles)objtList.get(0)).getCantidad());
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
            this.auditoria.setTabla("perfiles");
            this.auditoria.setMetodo("perfiles-list");
            this.auditoria.setMensaje(Constantes.msgTransaccionError + e.getMessage());
            this.auditoria.setAccion("L-perfiles");
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
     * Servicio web que permite filtar por el nombre de un Perfil, esta opción
     * permitirá realizar búsquedas de perfiles de usuarios, ingresando el
     * nombre del mismo en la pestaña de Administración\Perfiles, en el módulo
     * de Administración.
     *
     * @(#)like 1.0 15/08/2016 Se agregó la documentación del servicio [like]
     * web para: el Catálogo de Perfiles, rrazuri@gteperu.com
     */
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public ResponseWrapper listPerfilesPorLikeNombre(@RequestBody @Validated Perfiles perfiles, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        List objtList = new ArrayList();
        this.auditoria = new Auditoria();
        ResponseWrapper obj = new ResponseWrapper();
        try {
      
                //se verifica que el usuario haya iniciado sesión en el sistema.
                if (perfiles.getAccion() != null) {
                    //si el atributo acción es diferente de nulo, se iniciará el servicio web.
                    try {
                        //Se obtiene el nombre del objeto a través del atributo acción, que se usará como parámetro en el servicio web.
                        perfiles.setNombres(perfiles.getAccion());
                        perfiles.setIdempresa(perfiles.getIdempresa());
                        objtList = perfilesService.retornaPerfilesLikePorNombre(perfiles);
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
            this.auditoria.setTabla("perfiles");
            this.auditoria.setMetodo("perfiles-like");
            this.auditoria.setMensaje(Constantes.msgTransaccionError + e.getMessage());
            this.auditoria.setAccion("LK-perfiles");
            obj.setMsg(Constantes.msgTransaccionErrorNull);
            obj.setEstado(Constantes.valTransaccionErrornull);
            obj.setAaData(objtList);

        } finally {
            //Finalmente se retorna el Objeto.
            return obj;
        }

    }

    /**
     * Servicio web que permite encontrar un perfil de usuario, el cual utiliza
     * como parámetro el Id para encontrar el mismo. Esta opción es utilizada
     * antes de actualizar cualquiera de los campos o eliminar un Perfil, ya que
     * necesito conocer el Id del mismo para realizar la acción. Se desencadena
     * desde la pestaña Administración\Perfiles\Actualizar||Eliminar, en el
     * módulo de Usuarios Cuentas.
     *
     * @(#)finid 1.0 15/08/2016 Se agregó la documentación del servicio [finid]
     * web para: el Catálogo de Perfiles, rrazuri@gteperu.com
     */
    @RequestMapping(value = "/finid", method = RequestMethod.POST)
    public ResponseWrapper retornaPerfilesPorId(@RequestBody @Validated Perfiles perfiles, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        ResponseWrapper obj = new ResponseWrapper();
        Perfiles c = new Perfiles();
        this.auditoria = new Auditoria();
        try {
 
                //se verifica que el usuario haya iniciado sesión en el sistema.
                c.setId_perfiles(perfiles.getId_perfiles());
                //Se obtiene el Id del objeto, que se usará como parámetro en el servicio web.
                c = perfilesService.retornaObjPerfiles(c);
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
            this.auditoria.setTabla("perfiles");
            this.auditoria.setMetodo("perfiles-finid");
            this.auditoria.setMensaje(Constantes.msgTransaccionError + e.getMessage());
            this.auditoria.setAccion("FD-perfiles");
            obj.setEstado(Constantes.valTransaccionError);
            obj.setMsg(Constantes.msgTransaccionError);
        } finally {
            //Finalmente se retorna el Objeto.
            return obj;
        }

    }

    /**
     * Servicio web que permite listar todos los perfiles con sus
     * correspondientes páginas de aplicación, esta opción permitirá visualizar
     * todos los Perfiles creados con sus páginas asignadas, en la pestaña de
     * Administración\Perfiles, en el módulo de Usuarios Cuentas.
     *
     * @(#)list 1.0 15/08/2016 Se agregó la documentación del servicio [list]
     * web para: el Catálogo de Perfiles, rrazuri@gteperu.com
     */
    @RequestMapping(value = "/paginaporperfil", method = RequestMethod.POST)
    public List<Pagina> listPaginaPorPerfil(@RequestBody @Validated Perfiles perfiles, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        List objtList = new ArrayList();
        Perfiles c = new Perfiles();

  
            //se verifica que el usuario haya iniciado sesión en el sistema y luego se envía el objeto para que inicie el servicio web.
            return perfilesService.retornaPaginasMapPorPerfil(perfiles);
    }
    @RequestMapping(value = "/groupoptions", method = RequestMethod.POST)
    public List<Modulo> listPaginaPorPerfilGroup(@RequestBody @Validated Perfiles perfiles, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        List objtList = new ArrayList();
        return perfilesService.retornaPaginasMapPorPerfilAgrupado(perfiles);
     
    }
    
    
    
    /////Eduardo
    
    @RequestMapping(value = "/listPerfiles", method = RequestMethod.POST)
	public ResponseWrapper listPerfiles(@RequestBody @Validated Perfiles perfiles, HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		List objtList = new ArrayList();
		ResponseWrapper obj = new ResponseWrapper();
		try {
			
				try {
					objtList = perfilesService.retornaPerfiles();
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
					obj.setMsg(Constantes.msgTransaccionErrorNull + "Convirtiendo a numero");
					obj.setEstado(Constantes.valTransaccionErrornull);
					obj.setAaData(objtList);
				}
			

		} catch (Exception e) {
			obj.setMsg(Constantes.msgTransaccionErrorNull);
			obj.setEstado(Constantes.valTransaccionErrornull);
			obj.setAaData(objtList);
		} finally {
			return obj;
		}

	}
}