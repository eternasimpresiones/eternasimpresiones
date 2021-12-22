
package org.gteperu.erp.everest.controller;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.gteperu.erp.everest.service._Prod_Cod_SunatService;
import org.gteperu.erp.everest.domain.ResponseWrapper;
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
@RequestMapping(value = "/api/prod_cod_sunat")
public class	_Prod_Cod_SunatRestController{
 @Resource(name = "prod_cod_sunatService")
 _Prod_Cod_SunatService prod_cod_sunatService;
 
 /*
  * retorna el codigo sunat de un producto 
  */
 @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
 @RequestMapping(value = "/retornarProductoCodigoSunat", method = RequestMethod.POST)
 public ResponseWrapper retornarProductoCodigoSunat(@RequestBody @Validated _ProductoCodigoSunat _ProductoCodigoSunat, HttpServletRequest request) throws Exception {
     
	 HttpSession session = request.getSession();
	    List objtList = new ArrayList();
	    ResponseWrapper obj = new ResponseWrapper();
	    try {
	                    objtList = prod_cod_sunatService.retornarProductoCodigoSunat(_ProductoCodigoSunat);
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
	    	  throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/cargarArchivoProducto",
		  				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		  				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),_ProductoCodigoSunat);
	    }
	    return obj;
	    
 }

}