package org.gteperu.erp.everest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.Cuota;
import org.gteperu.erp.everest.mappers.CuotaMapper;
import org.gteperu.erp.everest.service.CuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cuotaService")
public class CuotaServiceImpl implements CuotaService{

	@Autowired
	CuotaMapper mapper;

	@Override
	public Integer insertarCuota(Cuota s) {
		return mapper.insertarCuota(s);
	}

	@Override
	public Integer actualizarCuota(Cuota s) {
		return mapper.actualizarCuota(s);
	}

	@Override
	public Integer eliminarCuota(Integer id) {
		return mapper.eliminarCuota(id);
	}

	@Override
	public List<Cuota> listarCuotaxidocumento(Integer id_documento) {
        List<Cuota> ls = new ArrayList<>();
        ls = mapper.listarCuotaxidocumento(id_documento);
		return ls;
	}
	
	
}
