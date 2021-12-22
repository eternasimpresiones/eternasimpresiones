
package org.gteperu.erp.everest.controller;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain.Sutipodocumento;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service.SutipodocumentoService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/sutipodocumento")
public class	SutipodocumentoRestController{
 @Resource(name = "sutipodocumentoService")
 SutipodocumentoService sutipodocumentoService;
 
 /*
  * Lista tipos de documento registrados en la empresa segun su estado
  */
 @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
 @RequestMapping(value = "/retornaSutipodocumentoPorEstado", method = RequestMethod.POST)
 public ResponseWrapper retornaSutipodocumentoPorEstado(@RequestBody @Validated Sutipodocumento sutipodocumento, HttpServletRequest request) throws Exception {
     
	 HttpSession session = request.getSession();
	    List objtList = new ArrayList();
	    ResponseWrapper obj = new ResponseWrapper();
	    try {
	                    objtList = sutipodocumentoService.retornaSutipodocumentoPorEstado(sutipodocumento);
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
	    	  throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornaSutipodocumentoPorEstado",
		    			e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		   				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),sutipodocumento);
	    } 
	    return obj;
	    
 }
 
 
 
 
 /*
  * retorna todos los datos de la empresa registrada en la sunat mediante su ruc
  * 
  * */
 @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
 @RequestMapping(value = "/retornacomboSutipodocumentoidentidad", method = RequestMethod.POST)
 public ResponseWrapper comboSutipodocumentoidentidad(HttpServletRequest request) throws Exception{
	 HttpSession session = request.getSession();
	 List objtList = new ArrayList();
	 ResponseWrapper obj = new ResponseWrapper();
	 try{
			 objtList = sutipodocumentoService.comboSutipodocumentoidentidad();
             if (objtList != null && objtList.size() > 0) {
                 obj.setMsg(Constantes.msgTransaccionOk);
                 obj.setEstado(Constantes.valTransaccionOk);
                 obj.setAaData(objtList);
             } else {
                 obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
                 obj.setEstado(Constantes.valTransaccionNoEncontro);
                 obj.setAaData(objtList);
             }
             
	 }
	 catch (Exception e) {
		 throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornacomboSutipodocumentoidentidad",
	    			e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	   				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),null);
	}
	 
	 return obj;
	
 }
 


}