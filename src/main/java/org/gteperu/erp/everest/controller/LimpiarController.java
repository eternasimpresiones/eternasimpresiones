package org.gteperu.erp.everest.controller;


import java.util.List;

import org.gteperu.erp.everest.domain._ProductoCodigoSunat;
import org.gteperu.erp.everest.mappers.LimpiarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/limpiar")
public class LimpiarController {
	
	@Autowired
	LimpiarMapper mapper;
	
	@GetMapping("/todo")
	public String limpiar(){
		try {
			List<_ProductoCodigoSunat> lsProd = mapper.listarProductos();
			
			lsProd.forEach( prod -> {
				String codigo = prod.getCodigo_producto_cod_sunat().replace(" ","");
				if(!codigo.equalsIgnoreCase(prod.getCodigo_producto_cod_sunat())) {
					prod.setCodigo_producto_cod_sunat(codigo);
					mapper.actualizarCodigo(prod);
				}
			});
			return "OK";
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}
}
