package org.gteperu.erp.everest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.gteperu.erp.everest.domain.Dashboard;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service.DashboardService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="api/dashboard")
public class DashboardRestController {
	
	@Resource(name="dashboardService")
	DashboardService dashboardService;
	
	 

/*
 * String 	ano
 * Integer	idempresa 
 * Integer	idlocal 
 * SM
 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/facturacionMensual", method = RequestMethod.POST)				 
	public ResponseWrapper facturacionMensual(@RequestBody @Validated Dashboard dash, HttpServletRequest request)
			throws Exception {

 		List objtList = new ArrayList();
		ResponseWrapper obj = new ResponseWrapper();
		try {

			objtList = dashboardService.facturacionMensual(dash);
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
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/facturacionMensual",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
							dash);
		}
		return obj;

	}

	/*
	 * String 	ano
	 * String 	mes
	 * Integer	idempresa 
	 * Integer	idlocal 
	 * SM
	 */
		@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
		@RequestMapping(value = "/facturacionClientesMensual", method = RequestMethod.POST)				 
		public ResponseWrapper facturacionClientesMensual(@RequestBody @Validated Dashboard dash, HttpServletRequest request)
				throws Exception {

	 		List objtList = new ArrayList();
			ResponseWrapper obj = new ResponseWrapper();
			try {

				objtList = dashboardService.facturacionClientesMensual(dash);
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
				throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/facturacionMensual",
						e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
								+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
								+ e.getStackTrace()[0].getLineNumber(),
								dash);
			}
			return obj;

		}

		
		
	 
}
