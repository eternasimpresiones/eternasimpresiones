
package org.gteperu.erp.everest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain.Tipocuentabancaria;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service.TipoCuentaBancariaService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/tipocuentabancaria")
public class TipoCuentaBancariaRestController {
	@Resource(name = "tipocuentabancariaService")
	TipoCuentaBancariaService tipocuentabancariaService;

  	 

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/retornaTipocuentabancariaTodas", method = RequestMethod.POST)
	public ResponseWrapper retornaTipocuentabancariaTodas(@RequestBody @Validated Tipocuentabancaria producto, HttpServletRequest request)
			throws Exception {

 		List objtList = new ArrayList();
		ResponseWrapper obj = new ResponseWrapper();
		try {

			objtList = tipocuentabancariaService.retornaTipocuentabancariaTodas();
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
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/retornaTipocuentabancariaTodas",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					producto);
		}
		return obj;

	}

	 
}