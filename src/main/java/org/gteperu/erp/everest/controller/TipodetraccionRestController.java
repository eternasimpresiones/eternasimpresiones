
package org.gteperu.erp.everest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gteperu.erp.everest.domain.Bancoempresa;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain.Tipodetraccion;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.mappers.TipodetraccionMapper;
import org.gteperu.erp.everest.service.BancoService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/tipodetraccion")
public class TipodetraccionRestController {
	@Resource(name = "tipodetraccionMapper")
	TipodetraccionMapper tipodetraccionMapper;

  	 

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/listartipodetraccion", method = RequestMethod.GET)
	public ResponseWrapper listartipodetraccion(HttpServletRequest request)
			throws Exception {

 		List objtList = new ArrayList();
		ResponseWrapper obj = new ResponseWrapper();
		try {

			objtList = tipodetraccionMapper.retornaBanco();
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
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listartipodetraccion",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					null);
		}
		return obj;

	}

	 
}