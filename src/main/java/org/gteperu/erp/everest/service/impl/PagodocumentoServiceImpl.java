package org.gteperu.erp.everest.service.impl;

import java.util.List;

import org.gteperu.erp.everest.domain.Pagodocumento;
import org.gteperu.erp.everest.mappers.PagodocumentoMapper;
import org.gteperu.erp.everest.service.PagodocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagodocumentoServiceImpl implements PagodocumentoService{

	@Autowired
	PagodocumentoMapper mapper;

	@Override
	public Integer insertar(Pagodocumento pagodocumento) {
		return mapper.insertar(pagodocumento);
	}

	@Override
	public Integer actualizar(Pagodocumento pagodocumento) {
		return mapper.actualizar(pagodocumento);
	}

	@Override
	public Integer eliminar(Pagodocumento pagodocumento) {
		return mapper.eliminar(pagodocumento);
	}

	@Override
	public List<Pagodocumento> listarPorDocumento(Pagodocumento pagodocumento) {
		return mapper.listarPorDocumento(pagodocumento);
	}
}
