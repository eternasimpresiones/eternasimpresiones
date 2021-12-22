package org.gteperu.erp.everest.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.gteperu.erp.everest.domain.Unidades_medida;
import org.gteperu.erp.everest.mappers.UnidadmedidaMapper;
import org.gteperu.erp.everest.service.Unidades_medidaService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@Service("unidades_medidaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class Unidades_medidaServiceImpl implements Unidades_medidaService {
	@Resource(name = "unidadmedidaMapper")
	UnidadmedidaMapper unidadmedidaMapper;
	
	@Override
	public List<Unidades_medida> retornaAllUnidadesMedida(Unidades_medida unidadesMedida) {
		List<Unidades_medida> lsUnidadesMedida = new ArrayList<Unidades_medida>();
		lsUnidadesMedida = unidadmedidaMapper.retornaAllUnidadesMedida();
		return lsUnidadesMedida;
	}

	@Override
	public List<Unidades_medida> retornaUnidadesMedida(Unidades_medida unidadesMedida) {
		List<Unidades_medida> lsUnidadesMedida = new ArrayList<Unidades_medida>();
		try{
			lsUnidadesMedida = unidadmedidaMapper.retornaUnidadesMedida(unidadesMedida);
		}catch (Exception e){
			throw e;
		}
			return lsUnidadesMedida;
	
	}

	@Override
	public Integer updateUnidadesMedida(Unidades_medida coduni) {
		Integer d = 0;
		try{
			d = unidadmedidaMapper.updateUnidadesMedida(coduni);
		}catch (Exception e){
			throw e;
		}
			return d;
}

	@Override
	public Integer insertaUnidadesMedida(Unidades_medida coduni) {
		Integer d = 0;
		try {
			d = unidadmedidaMapper.insertaUnidadesMedida(coduni);
		} catch (Exception e) {
			throw e;
		} 
			return d;
		
	}

//	@Override
//	public Integer eliminaUnidadesMedida(Unidades_medida coduni) {
//		Integer d = 0;
//		try {
//			d = unidadmedidaMapper.eliminaUnidadesMedida(coduni);
//		} catch (Exception e) {
//			throw new RuntimeException();
//		} finally {
//			return d;
//	}
//	}
	
}
