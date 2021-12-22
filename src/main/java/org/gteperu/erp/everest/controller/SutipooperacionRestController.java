
package org.gteperu.erp.everest.controller;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gteperu.erp.everest.domain.Unidades_medida;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain.Sutipodocumento;
import org.gteperu.erp.everest.domain.Sutipooperacion;
import org.gteperu.erp.everest.service.SutipodocumentoService;
import org.gteperu.erp.everest.service.SutipooperacionService;
import org.gteperu.erp.everest.service.Unidades_medidaService;
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
@RequestMapping(value = "/api/sutipooperacion")
public class	SutipooperacionRestController{
 @Resource(name = "sutipooperacionService")
 SutipooperacionService sutipooperacionService;
 
 /*
  * Lista todos los tipo operacion segun su estado
  */
 @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
 @RequestMapping(value = "/retornasutioperacionPorEstado", method = RequestMethod.POST)
 public ResponseWrapper retornasutioperacionPorEstado(@RequestBody @Validated Sutipooperacion sutipooperacion, HttpServletRequest request) throws Exception {
     
	 HttpSession session = request.getSession();
	    List objtList = new ArrayList();
	    ResponseWrapper obj = new ResponseWrapper();
	    try {
	                 
	                    objtList = sutipooperacionService.retornaSutipooperacionPorEstado(sutipooperacion);
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
	    	  throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornasutioperacionPorEstado",
		    			e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		   				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),sutipooperacion);
	    }
	    return obj;
	    
 }




}