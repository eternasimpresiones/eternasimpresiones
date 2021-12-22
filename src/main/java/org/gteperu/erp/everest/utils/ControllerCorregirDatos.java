package org.gteperu.erp.everest.utils;

import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.service._Prod_Cod_SunatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/util/corregir")
public class ControllerCorregirDatos {
	
	@Autowired
	_Prod_Cod_SunatService service;
 	
	@GetMapping("/codprod")
	public ResponseWrapper corregirCodigoSunat() throws Exception{
		ResponseWrapper response = new ResponseWrapper();	
		try {
			Integer result = service.actualizarCodigoProductoSunat();
		if(result == 1) {
			response.setEstado(Constantes.valTransaccionOk);
			response.setMsg("Codigos actualizados correctamente");
		}else {
			response.setEstado(Constantes.valTransaccionError);
			response.setMsg("Error al actualizar los codigos");
		}
		
		return response;
		}catch(Exception e) {
			throw e;
		}
	}
}
