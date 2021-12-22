package org.gteperu.erp.everest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gteperu.erp.everest.domain.Regimen_sunat;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service.Regimen_sunatSevice;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/regimen_sunat")
public class RegimenSunatRestController {
	
	@Autowired
	Regimen_sunatSevice regimenService;
	
	/*
	 * Registra nuevo regimen Sunat
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/insertRegimen", method = RequestMethod.POST)
	public ResponseWrapper insertRegimen(@RequestBody @Validated Regimen_sunat regimen, HttpServletRequest request)
			throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		try {
			
            Integer cat = 0;
            cat = regimenService.insertRegimen(regimen);
            
            if (cat != null && cat > 0) {
                response.setEstado(Constantes.valTransaccionOk);
                response.setMsg(Constantes.msgTransaccionInsertarClienteOk);
            } else {
                response.setEstado(Constantes.valTransaccionErrornull);
                response.setMsg(Constantes.msgTransaccionInsertarClienteError);
                throw new Exception(Constantes.msgTransaccionError);
            }
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" insertarCliente. ERROR : "+e.getMessage());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/insertaCliente",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),regimen);
		} 
		return response;
		
	}
	
	/*
	 * Retorna una lista de todos los registros de Regimen registrados 
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/listarRegimen", method = RequestMethod.POST)
	public ResponseWrapper listarRegimen(@RequestBody @Validated Regimen_sunat regimen, HttpServletRequest request) throws Exception {
			HttpSession session = request.getSession();
			List objtList = new ArrayList();
			ResponseWrapper obj = new ResponseWrapper();
				try {
					objtList = regimenService.listarRegimen(regimen);
					if (objtList != null && objtList.size() > 0) {
						obj.setEstado(Constantes.valTransaccionOk);
						obj.setAaData(objtList);
					} else {
						obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
						obj.setEstado(Constantes.valTransaccionNoEncontro);
						obj.setAaData(objtList);
					}
				
		} catch (Exception e) {
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/listarCliente",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),regimen);
		} 
		return obj;
		
	}

}
