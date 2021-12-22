
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
@RequestMapping(value = "/api/unidadesdemedida")
public class	Unidades_medidaRestController{
 @Resource(name = "unidades_medidaService")
 Unidades_medidaService unidades_medidaService;
 
 /*
  * Retorna todas las unidades de medida
  */
 @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
 @RequestMapping(value = "/retornaAllUnidadesMedida", method = RequestMethod.POST)
    public ResponseWrapper retornaAllUnidadesMedida(@RequestBody @Validated Unidades_medida unidadesMedida, HttpServletRequest request) throws Exception {
        
	 HttpSession session = request.getSession();
	    List objtList = new ArrayList();
	    ResponseWrapper obj = new ResponseWrapper();
	    try {
	                    objtList = unidades_medidaService.retornaAllUnidadesMedida(unidadesMedida);
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
	    	  throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornaAllUnidadesMedida",
		    			e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		   				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),unidadesMedida);
	    }
	    return obj;
	    
    }
 
 @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
 @RequestMapping(value = "/retornaUnidadesMedida", method = RequestMethod.POST)
 public ResponseWrapper retornaUnidadesMedida(@RequestBody @Validated Unidades_medida unidadesMedida, HttpServletRequest request) throws Exception {
     
	 HttpSession session = request.getSession();
	    List objtList = new ArrayList();
	    ResponseWrapper obj = new ResponseWrapper();
	    try {
	                    objtList = unidades_medidaService.retornaUnidadesMedida(unidadesMedida);
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
	    	  throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornaUnidadesMedida",
		    			e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		   				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),unidadesMedida);
	    }
	    return obj;
	    
 }

 
// @RequestMapping(value = "/inserta", method = RequestMethod.POST)
// public ResponseWrapper inserta(@RequestBody @Validated Unidades_medida unidadesMedida, HttpServletRequest request) throws Exception {
//     
//	 HttpSession session = request.getSession();
//	    List objtList = new ArrayList();
//	    ResponseWrapper obj = new ResponseWrapper();
//	    try {
//            Integer cat = 0;
//            if (unidadesMedida.getAccion().equals(Constantes.accionDelet)) {
//             /*   if (unidadesMedida.getEstado() == 1) {
//               	 unidadesMedida.setEstado(0);
//                } else {
//               	 unidadesMedida.setEstado(1);
//                }*/
//                cat = unidades_medidaService.eliminaUnidadesMedida(unidadesMedida);
//            } else {
//           	 if (unidadesMedida.getCoduni() != null) {
//                    cat = unidades_medidaService.updateUnidadesMedida(unidadesMedida);
//                } else {
//                    cat = unidades_medidaService.insertaUnidadesMedida(unidadesMedida);
//                }
//            }
//            if (cat != null && cat > 0) {
//            	obj.setEstado(Constantes.valTransaccionOk);
//            	obj.setMsg(Constantes.msgTransaccionOk);
//            } else {
//            	obj.setEstado(Constantes.valTransaccionErrornull);
//                obj.setMsg(Constantes.msgTransaccionError);
//            }
//    }catch (Exception e) {
//	        obj.setMsg(Constantes.msgTransaccionErrorNull);
//	        obj.setEstado(Constantes.valTransaccionErrornull);
//	        obj.setAaData(objtList);
//	    } finally {
//	        return obj;
//	    }
// }



}