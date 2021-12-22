
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
import org.gteperu.erp.everest.service._TipoNotaDebito_Cod_SunatService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/tipo_nota_debito_cod_sunat")
public class	_TipoNotaDebito_Cod_SunatRestController{
 @Resource(name = "tipoNotaDebito_Cod_SunatService")
 _TipoNotaDebito_Cod_SunatService tipoNotaDebito_Cod_SunatService;

 /*
  * retorna el tipo (para la sunat) de nota de debito a registrar
  */
 @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
 @RequestMapping(value = "/retornarTipoNotaDebito_Cod_Sunat", method = RequestMethod.POST)
 public ResponseWrapper retornarTipoNotaDebito_Cod_Sunat(HttpServletRequest request) throws Exception{
	 HttpSession session = request.getSession();
	 List objtList = new ArrayList();
	 ResponseWrapper obj = new ResponseWrapper();
	 try{
		 
			 objtList = tipoNotaDebito_Cod_SunatService.retornarTipoNotaDebito_Cod_Sunat();
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
		 throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornarTipoNotaDebito_Cod_Sunat",
	  				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	  				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),null);
	}
	return obj;
	
 }
 


}