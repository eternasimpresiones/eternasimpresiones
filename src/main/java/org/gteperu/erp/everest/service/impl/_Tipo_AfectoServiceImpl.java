package org.gteperu.erp.everest.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.gteperu.erp.everest.domain.Det_doc;
import org.gteperu.erp.everest.domain.Documento;
import org.gteperu.erp.everest.domain.InternalWrapper;
import org.gteperu.erp.everest.domain._Producto;
import org.gteperu.erp.everest.domain._TipoAfecto;
import org.gteperu.erp.everest.mappers.ProductoMapper;
import org.gteperu.erp.everest.mappers.TipoAfectoMapper;
import org.gteperu.erp.everest.service._ProductoService;
import org.gteperu.erp.everest.service._Tipo_AfectoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("tipo_afectoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class _Tipo_AfectoServiceImpl implements _Tipo_AfectoService{
	@Resource(name = "tipoafectoMapper")
	TipoAfectoMapper tipoafectoMapper;

	@Override
	public List<_TipoAfecto> retornarTipoAfecto() {
		List<_TipoAfecto> lsTipoAfecto = new ArrayList<_TipoAfecto>();
		lsTipoAfecto = tipoafectoMapper.retornarTipoAfecto();
		return lsTipoAfecto;
	}

}
