package org.gteperu.erp.everest.service.impl;

import java.util.List;

import org.gteperu.erp.everest.domain.Tiporeporte;
import org.gteperu.erp.everest.mappers.TiporeporteMapper;
import org.gteperu.erp.everest.service.TiporeporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TiporeporteServiceImpl implements TiporeporteService{

	@Autowired
	TiporeporteMapper mapper;

	@Override
	public List<Tiporeporte> findAll() {
		return mapper.findAll();
	}
}
