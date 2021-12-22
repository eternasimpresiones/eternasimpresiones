package org.gteperu.erp.everest.service;

import java.util.List;

import org.gteperu.erp.everest.domain.Unidades_medida;


public interface Unidades_medidaService {
	public List<Unidades_medida> retornaAllUnidadesMedida(Unidades_medida unidadesMedida);
	
	public List<Unidades_medida> retornaUnidadesMedida(Unidades_medida unidadesMedida);
	
	public Integer updateUnidadesMedida(Unidades_medida unidadesMedida);
	
	public Integer insertaUnidadesMedida(Unidades_medida unidadesMedida);
	
//	public Integer eliminaUnidadesMedida(Unidades_medida unidadesMedida);
	

}
