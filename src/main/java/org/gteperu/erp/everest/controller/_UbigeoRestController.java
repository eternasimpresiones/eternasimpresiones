
package org.gteperu.erp.everest.controller;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.gteperu.erp.everest.service._Prod_Cod_SunatService;
import org.gteperu.erp.everest.service._UbigeoService;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain.Ubigeo;
import org.gteperu.erp.everest.domain._ProductoCodigoSunat;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/ubigeo")
public class	_UbigeoRestController{
 @Resource(name = "ubigeoService")
 _UbigeoService ubigeoService;
 
 
 /*
  * Retorna lista de departamentos 
  * 
  * */
 @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
 @RequestMapping(value = "/retornarDepartamento", method = RequestMethod.POST)
 public ResponseWrapper retornarDepartamento(@RequestBody @Validated Ubigeo ubigeo, HttpServletRequest request) throws Exception {
     
	 HttpSession session = request.getSession();
	    List objtList = new ArrayList();
	    ResponseWrapper obj = new ResponseWrapper();
	    try {
	                 
	                    objtList = ubigeoService.retornaObjDepartamento(ubigeo);
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
	    	  throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornarDepartamento",
		  				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		  				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),ubigeo);
	    }
	    return obj;
	    
 }
 
 
 /*
  * Retorna lista de provincia por departamento 
  * 
  * */
 @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
 @RequestMapping(value = "/retornarProvincia", method = RequestMethod.POST)
 public ResponseWrapper retornarProvincia(@RequestBody @Validated Ubigeo ubigeo, HttpServletRequest request) throws Exception {
     
	 HttpSession session = request.getSession();
	    List objtList = new ArrayList();
	    ResponseWrapper obj = new ResponseWrapper();
	    try {
	                
	                    objtList = ubigeoService.retornaObjProvincia(ubigeo);
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
	    	  throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornarProvincia",
		  				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		  				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),ubigeo);
	    }
	    return obj;
	    
 }
 
 /*
  * Retorna lista de distritos por departamento y provincia 
  * 
  * */
 @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
 @RequestMapping(value = "/retornarDistrito", method = RequestMethod.POST)
 public ResponseWrapper retornarDistrito(@RequestBody @Validated Ubigeo ubigeo, HttpServletRequest request) throws Exception {
     
	 HttpSession session = request.getSession();
	    List objtList = new ArrayList();
	    ResponseWrapper obj = new ResponseWrapper();
	    try {
	                    objtList = ubigeoService.retornaObjDistrito(ubigeo);
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
	    	  throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornarDistrito",
		  				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		  				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),ubigeo);
	    }
	    return obj;
	    
 }
 
 /*
  * Retorna el codigo de la tabla ubigeo segun su distrito , provincia y departamento
  */
 @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
 @RequestMapping(value = "/retornaObjUbigeoxCodigo", method = RequestMethod.POST)
 public ResponseWrapper retornaObjUbigeoxCodigo(@RequestBody @Validated String ubigeo, HttpServletRequest request) throws Exception {
     
	 HttpSession session = request.getSession();
	 Ubigeo ubi = new Ubigeo();
	    ResponseWrapper obj = new ResponseWrapper();
	    try {
	                	 ubi = ubigeoService.retornaObjUbigeoxCodigo(ubigeo);
	                    if (ubi != null) {
	                        obj.setMsg(Constantes.msgTransaccionOk);
	                        obj.setEstado(Constantes.valTransaccionOk);
	                        obj.setDefaultObj(ubi);
	                    } else {
	                        obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
	                        obj.setEstado(Constantes.valTransaccionNoEncontro);
	                        obj.setDefaultObj(ubi);
	                    }
	              
	     } catch (Exception e) {
	    	  throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornaObjUbigeoxCodigo",
		  				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		  				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),ubigeo);
	    }
	    return obj;
	    
 }
}