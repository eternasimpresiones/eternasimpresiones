package org.gteperu.erp.everest.controller;

import java.util.Date;
import java.util.List;

import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service.MetodopagoService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/metodopago")
public class MetodopagoRestController {

	@Autowired
	MetodopagoService service;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@GetMapping("/listar")
	public ResponseWrapper listar() throws Exception {
		ResponseWrapper obj = new ResponseWrapper();
		try {
			List resp = service.listarMetodopago();
			if (resp != null && resp.size() > 0) {
				obj.setMsg(Constantes.msgTransaccionOk);
				obj.setEstado(Constantes.valTransaccionOk);
				obj.setAaData(resp);
			} else {
				obj.setMsg(Constantes.msgTransaccionError);
				obj.setEstado(Constantes.valTransaccionError);
			}

		} catch (Exception e) {
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(), null);
		}
		return obj;
	}
}
