package org.gteperu.erp.everest.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.gteperu.erp.everest.domain.Banco; 
import org.gteperu.erp.everest.mappers.BancoMapper;
import org.gteperu.erp.everest.service.BancoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("bancoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class BancoServiceImpl implements BancoService{
	@Resource(name = "bancoMapper")
	BancoMapper bancoMapper;
 
	@Override
	public List<Banco> retornaBancoTodas() {
 		List<Banco> lsProducto = new ArrayList<Banco>();
		try{
 			lsProducto = bancoMapper.retornaBancoTodas();
		}catch(Exception e){
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/retornaCantidadBancoEmpresaPorEmpresa => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/retornaBancoTodas" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return lsProducto;
	}
	
	 
}
