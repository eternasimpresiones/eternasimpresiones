package org.gteperu.erp.everest.service.impl;

import java.util.List;

import org.gteperu.erp.everest.domain.Metodopago;
import org.gteperu.erp.everest.mappers.MetodopagoMapper;
import org.gteperu.erp.everest.service.MetodopagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetodopagoServiceImpl implements MetodopagoService{

	@Autowired
	MetodopagoMapper mapper;

	@Override
	public List<Metodopago> listarMetodopago() {
		return mapper.listarMetodopago();
	}
}
