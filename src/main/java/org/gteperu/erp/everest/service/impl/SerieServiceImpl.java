package org.gteperu.erp.everest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.Serie;
import org.gteperu.erp.everest.mappers.SerieMapper;
import org.gteperu.erp.everest.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("serieService")
public class SerieServiceImpl implements SerieService{

	@Autowired
	SerieMapper mapper;

	@Override
	public Integer insertarSerie(Serie s) {
		return mapper.insertarSerie(s);
	}

	@Override
	public Integer actualizarSerie(Serie s) {
		return mapper.actualizarSerie(s);
	}

	@Override
	public Integer eliminarSerie(Integer id) {
		return mapper.eliminarSerie(id);
	}

	@Override
	public List<Serie> listarSerie(Serie s) {
        List<Serie> ls = new ArrayList<>();
        Pagination pg = new Pagination();
        pg = mapper.listarcantidadSerie(s);
        ls = mapper.listarSerie(s);
        if (ls != null && ls.size() > 0) {
            ls.get(0).setCantidad(pg.getCantidad());
        }
		return ls;
	}
	
	
	
	@Override
	public List<Serie> listarSerieRepedita(Serie s) {
        List<Serie> ls = new ArrayList<>();
        ls = mapper.listarSerieRepedita(s);
        return ls;
	}
	
	
	@Override
	public Serie retornaSeriexTipoDoc(Integer id) {
		return mapper.retornaSeriexTipoDoc(id);
	}
	
	@Override
	public Serie retornaSeriexTipoDocLocal(Integer idlocal,Integer idsutipodocumento) {
		return mapper.retornaSeriexTipoDocLocal(idlocal,idsutipodocumento);
	}
	@Override
	public Serie retornaSeriexTipoDocyLocal(Serie s) {
		return mapper.retornaSeriexTipoDocyLocal(s);
	}
}
