
package org.gteperu.erp.everest.controller;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain.Sutipodocumento;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._Retencion;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service.SutipodocumentoService;
import org.gteperu.erp.everest.service._RetencionService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/retencion")
public class	RetencionPercepcionRestController{
 @Resource(name = "retencionService")
 _RetencionService retencionService;
 
 
 /*
  * Retorna los documentos de retencion o percepcion registrados dentro de la empresa seleccionada
  */
 @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
 @RequestMapping(value = "/lsRetencionPercepcion", method = RequestMethod.POST)
 public ResponseWrapper lsRetencionPercepcion(@RequestBody @Validated _Retencion retencion, HttpServletRequest request) throws Exception {
     
	 HttpSession session = request.getSession();
	    List lsPerce = new ArrayList();
	    List lsExcel = new ArrayList();
	    List lsGlobal = new ArrayList();
	    ResponseWrapper obj = new ResponseWrapper();
	    try {
	    				lsPerce = retencionService.lsRetencionPercepcion(retencion);
	                    lsExcel = retencionService.lsRetencionPercepcionExcel(retencion);
	                    lsGlobal.add(lsPerce);
	                    lsGlobal.add(lsExcel);
	                    if (lsPerce != null && lsPerce.size() > 0) {
	                        obj.setMsg(Constantes.msgTransaccionOk);
	                        obj.setEstado(Constantes.valTransaccionOk);
	                        obj.setAaData(lsGlobal);
	                    } else {
	                        obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
	                        obj.setEstado(Constantes.valTransaccionNoEncontro);
	                        obj.setAaData(lsPerce);
	                    }
	               
	      } catch (Exception e) {
	    	  throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/lsRetencionPercepcion",
		    			e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		   				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),retencion);
	    } 
	    return obj;
	    
 }
 
 /*
  * Web service que retorna la cantidad de documentos creados como retenciones o percepciones
  */
 	@RequestMapping(value = "/cantidadRetePerc", method = RequestMethod.POST)
	public ResponseWrapper cantidadRetePerc(@RequestBody @Validated _Retencion retencion,
			HttpServletRequest request) throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		try {
			Integer cat = 0;
			cat = retencionService.cantidadRetePerc(retencion);

			if (cat != null && cat > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionCantidadRetencionPercepcionOk);
				response.setCantidad(cat);
			} else {
				response.setEstado(Constantes.valTransaccionNoEncontro);
				response.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
				response.setCantidad(cat);
			}
		} catch (Exception e) {
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/cantidadDocumentos",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),retencion);
		} 
		return response;
	}

	
}
