
package org.gteperu.erp.everest.controller;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.gteperu.erp.everest.domain._Producto;
import org.gteperu.erp.everest.domain._TipoAfecto;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service._ProductoService;
import org.gteperu.erp.everest.service._Tipo_AfectoService;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
@RestController
@RequestMapping(value = "/api/tipo_afecto")
public class	_Tipo_AfectoRestController{
 @Resource(name = "tipo_afectoService")
 _Tipo_AfectoService tipo_afectoService;
 
 /*
  * Lista todos los tipo afecto existentes en la empresa
  */
 @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
 @RequestMapping(value = "/retornarTipoAfecto", method = RequestMethod.POST)
 public ResponseWrapper retornarProducto(@RequestBody @Validated _TipoAfecto tipoAfecto, HttpServletRequest request) throws Exception {
     
	 HttpSession session = request.getSession();
	    List objtList = new ArrayList();
	    ResponseWrapper obj = new ResponseWrapper();
	    try {
	                    objtList = tipo_afectoService.retornarTipoAfecto();
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
	    	  throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornarTipoAfecto",
		  				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		  				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),tipoAfecto);
	    }
	    return obj;
	    
 }

}