package org.gteperu.erp.everest.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

 import org.gteperu.erp.everest.domain.Tipocuentabancaria;
import org.gteperu.erp.everest.mappers.TipoCuentaBancariaMapper;
import org.gteperu.erp.everest.service.TipoCuentaBancariaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("tipocuentabancariaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TipoCuentaBancariaServiceImpl implements TipoCuentaBancariaService{
	@Resource(name = "tipoCuentaBancariaMapper")
	TipoCuentaBancariaMapper tipoCuentaBancariaMapper;
 
	@Override
	public List<Tipocuentabancaria> retornaTipocuentabancariaTodas() {
 		List<Tipocuentabancaria> lsProducto = new ArrayList<Tipocuentabancaria>();
		try{
 			lsProducto = tipoCuentaBancariaMapper.retornaTipocuentabancariaTodas();
		}catch(Exception e){
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/retornaTipocuentabancariaTodas => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/retornaTipocuentabancariaTodas nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return lsProducto;
	}
	
	 
}
