package org.gteperu.erp.everest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain.Serie;
import org.gteperu.erp.everest.service.TiporeporteService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tipoReporte")
public class TiporeporteRestController {

	@Autowired
	TiporeporteService service;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@GetMapping("/listar")
	public ResponseWrapper listar() throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		try {
			List resp = service.findAll(); 
			if (resp.size() > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionOk);
				response.setAaData(resp);
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionError);
			}
		} catch (Exception e) {
			response.setEstado(Constantes.valTransaccionSinPermiso);
			response.setMsg(Constantes.msgTransaccionError);
			throw new RuntimeException("message: " + e.getMessage());
		}
		return response;
	}

}
