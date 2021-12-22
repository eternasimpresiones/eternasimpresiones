package org.gteperu.erp.everest.service.impl;

import org.gteperu.erp.everest.domain.Modulo;
import org.gteperu.erp.everest.domain.Pagina;
import org.gteperu.erp.everest.domain.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.gteperu.erp.everest.domain.Perfiles;
import org.gteperu.erp.everest.domain.PorcentajeDetraccion;
import org.gteperu.erp.everest.mappers.PerfilesMapper;
import org.gteperu.erp.everest.mappers.PorcentajeDetraccionMapper;
import org.gteperu.erp.everest.service.PerfilesService;
import org.gteperu.erp.everest.service.PorcentajeDetraccionService;

import java.util.List;
import javax.annotation.Resource;
import java.util.ArrayList;

@Service("porcentajeDetraccionService")
public class PorcentajeDetraccionServiceImpl implements PorcentajeDetraccionService {

	@Autowired
	PorcentajeDetraccionMapper porcentajeDetraccionMapper;

    @Override
    public List<PorcentajeDetraccion> listarPorcentajeDetraccion() {
        List<PorcentajeDetraccion> lsPorcentajeDetraccion = new ArrayList<PorcentajeDetraccion>();
        try {
        	lsPorcentajeDetraccion = porcentajeDetraccionMapper.listarPorcentajeDetraccion();
        	
        }catch(Exception e) {
        	e.getMessage();
        }
        return lsPorcentajeDetraccion;
    }
   
}