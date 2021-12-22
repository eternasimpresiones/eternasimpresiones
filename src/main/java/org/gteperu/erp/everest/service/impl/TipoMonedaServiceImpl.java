package org.gteperu.erp.everest.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.gteperu.erp.everest.domain.Tipomoneda;
import org.gteperu.erp.everest.mappers.TipoMonedaMapper;
import org.gteperu.erp.everest.service.TipoMonedaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("tipomonedaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TipoMonedaServiceImpl implements TipoMonedaService{
	@Resource(name = "tipoMonedaMapper")
	TipoMonedaMapper tipoMonedaMapper;
 
	@Override
	public List<Tipomoneda> retornaTipoMonedaTodas() {
 		List<Tipomoneda> lsProducto = new ArrayList<Tipomoneda>();
		try{
 			lsProducto = tipoMonedaMapper.retornaTipoMonedaTodas();
		}catch(Exception e){
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/retornaTipocuentabancariaTodas => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/retornaTipoMonedaTodas nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return lsProducto;
	}
	
	 
}
