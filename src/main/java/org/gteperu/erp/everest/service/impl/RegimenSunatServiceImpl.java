package org.gteperu.erp.everest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.gteperu.erp.everest.domain.Regimen_sunat;
import org.gteperu.erp.everest.mappers.Regimen_sunatMapper;
import org.gteperu.erp.everest.service.Regimen_sunatSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("RegimenService")
public class RegimenSunatServiceImpl implements Regimen_sunatSevice {

	@Autowired
	Regimen_sunatMapper regimenSunatMapper;

	@Override
	public Integer insertRegimen(Regimen_sunat regimen) {
		Integer auxiliar = 0;

		try {
			auxiliar = regimenSunatMapper.insertRegimen(regimen);
			} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" insertRegimen. ERROR : "+e.getMessage());
			throw e; //bien
			//throw new Exception(); //mal
		} 
		return auxiliar;
	}

	@Override
	public List<Regimen_sunat> listarRegimen(Regimen_sunat regimen) {
		 List<Regimen_sunat> lsRegimen = new ArrayList<Regimen_sunat>();
			try {
				lsRegimen = regimenSunatMapper.listarRegimen(regimen);
			} catch (Exception e) {
				System.out.println(this.getClass().getSimpleName()+" listarRegimen. ERROR : "+e.getMessage());
				throw e;
			}
			return lsRegimen;
	}
	
	
}
