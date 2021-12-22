package org.gteperu.erp.everest.controller;

import java.util.Date;
import java.util.List;

import org.gteperu.erp.everest.domain.Pagodocumento;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service.MetodopagoService;
import org.gteperu.erp.everest.service.PagodocumentoService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pagodocumento")
public class PagodocumentoRestController {

	@Autowired
	PagodocumentoService service;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listarPorDocumento")
	public ResponseWrapper listarPorDocumento(@RequestBody Pagodocumento pagodocumento) throws Exception {
		ResponseWrapper obj = new ResponseWrapper();
		try {
			List resp = service.listarPorDocumento(pagodocumento);
			if (resp != null && resp.size() > 0) {
				obj.setMsg(Constantes.msgTransaccionOk);
				obj.setEstado(Constantes.valTransaccionOk);
				obj.setAaData(resp);
			} else {
				obj.setMsg(Constantes.msgTransaccionError);
				obj.setEstado(Constantes.valTransaccionError);
			}

		} catch (Exception e) {
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listarPorDocumento",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					null);
		}
		return obj;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/insertar")
	public ResponseWrapper insertar(@RequestBody Pagodocumento pagodocumento) throws Exception {
		ResponseWrapper obj = new ResponseWrapper();
		try {
			Integer resp = service.insertar(pagodocumento);
			if (resp != null && resp > 0) {
				obj.setMsg(Constantes.msgTransaccionOk);
				obj.setEstado(Constantes.valTransaccionOk);
			} else {
				obj.setMsg(Constantes.msgTransaccionError);
				obj.setEstado(Constantes.valTransaccionError);
			}

		} catch (Exception e) {
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/insertar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					null);
		}
		return obj;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/actualizar")
	public ResponseWrapper actualizar(@RequestBody Pagodocumento pagodocumento) throws Exception {
		ResponseWrapper obj = new ResponseWrapper();
		try {
			Integer resp = service.actualizar(pagodocumento);
			if (resp != null && resp > 0) {
				obj.setMsg(Constantes.msgTransaccionOk);
				obj.setEstado(Constantes.valTransaccionOk);
			} else {
				obj.setMsg(Constantes.msgTransaccionError);
				obj.setEstado(Constantes.valTransaccionError);
			}

		} catch (Exception e) {
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/actualizar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					null);
		}
		return obj;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/eliminar")
	public ResponseWrapper eliminar(@RequestBody Pagodocumento pagodocumento) throws Exception {
		ResponseWrapper obj = new ResponseWrapper();
		try {
			Integer resp = service.eliminar(pagodocumento);
			if (resp != null && resp > 0) {
				obj.setMsg(Constantes.msgTransaccionOk);
				obj.setEstado(Constantes.valTransaccionOk);
			} else {
				obj.setMsg(Constantes.msgTransaccionError);
				obj.setEstado(Constantes.valTransaccionError);
			}

		} catch (Exception e) {
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/eliminar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					null);
		}
		return obj;
	}
}
