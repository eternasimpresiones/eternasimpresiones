package org.gteperu.erp.everest.service.impl;

import java.sql.Timestamp;

import org.gteperu.erp.everest.domain.TipoCambio;
import org.gteperu.erp.everest.mappers.TipoCambioMapper;
import org.gteperu.erp.everest.service.TipoCambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoCambioServiceImpl implements TipoCambioService{

	@Autowired
	TipoCambioMapper mapper;

	@Override
	public Integer insertar(TipoCambio tc) {
		return mapper.insertar(tc);
	}

	@Override
	public TipoCambio encontrarPorFecha(Timestamp fechapublicacion) {
		return mapper.encontrarPorFecha(fechapublicacion);
	}

	@Override
	public boolean existePorFecha(Timestamp fechapublicacion) {
		return mapper.existePorFecha(fechapublicacion);
	}
}
