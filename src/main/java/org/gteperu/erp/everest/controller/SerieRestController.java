package org.gteperu.erp.everest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain.Serie;
import org.gteperu.erp.everest.service.SerieService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/serie")
public class SerieRestController {

	@Autowired
	SerieService service;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listar")
	public ResponseWrapper listar(@RequestBody @Validated Serie s,HttpServletRequest request) throws Exception {
		ResponseWrapper response = new ResponseWrapper();
        List objtList = new ArrayList();

		try {
			  objtList = service.listarSerie(s);
			if (objtList.size() > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionOk);
				response.setAaData(objtList);
				response.setCantidad(((Serie) objtList.get(0)).getCantidad());
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
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/insertar")
	public ResponseWrapper insertar(@RequestBody @Validated Serie serie, HttpServletRequest request)
			throws Exception {
		ResponseWrapper response = new ResponseWrapper();
        List lsSerieRepedita = new ArrayList();

		try {			
			
			lsSerieRepedita=service.listarSerieRepedita(serie);
			if(lsSerieRepedita!=null&&lsSerieRepedita.size()>0) {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgSerieRepetida);
				return response;
			}
			
			int resp = service.insertarSerie(serie);
			if (resp > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionOk);
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
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PutMapping("/actualizar")
	public ResponseWrapper actualizar(@RequestBody @Validated Serie serie, HttpServletRequest request)
			throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		List lsSerieRepedita = new ArrayList();

		try {			
			
			lsSerieRepedita=service.listarSerieRepedita(serie);
			if(lsSerieRepedita!=null&&lsSerieRepedita.size()>0) {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgSerieRepetida);
				return response;
			}		
			int resp = service.actualizarSerie(serie);
			if (resp > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionOk);
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
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DeleteMapping("/eliminar/{id}")
	public ResponseWrapper eliminar(@PathVariable("id") Integer id, HttpServletRequest request)
			throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		try {
			int resp = service.eliminarSerie(id);
			if (resp > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionOk);
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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@GetMapping("/retornaSeriexTipoDoc/{id}")
	public ResponseWrapper retornaSeriexTipoDoc(@PathVariable("id") Integer id,HttpServletRequest request) throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		
		try {
			Serie resp = service.retornaSeriexTipoDoc(id);
			if (resp != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionOk);
				response.setDefaultObj(resp);
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
	
	 
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@GetMapping("/retornaSeriexTipoDocLocal/{idlocal}/{idsutipodocumento}")
	public ResponseWrapper retornaSeriexTipoDocLocal(@PathVariable("idlocal") Integer idlocal,
			@PathVariable("idsutipodocumento") Integer idsutipodocumento ,HttpServletRequest request) throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		
		try {
			Serie resp = service.retornaSeriexTipoDocLocal(idlocal,idsutipodocumento);
			if (resp != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionOk);
				response.setDefaultObj(resp);
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgSerieNoEncontrada);
			}
		} catch (Exception e) {
			response.setEstado(Constantes.valTransaccionSinPermiso);
			response.setMsg(Constantes.msgTransaccionError);
			throw new RuntimeException("message: " + e.getMessage());
		}
		return response;
	}
}
