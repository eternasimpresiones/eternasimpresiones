package org.gteperu.erp.everest.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.gteperu.erp.everest.domain._ModalidadTrasladoCodigoSunat;
import org.gteperu.erp.everest.domain._ProductoCodigoSunat;
import org.gteperu.erp.everest.mappers.ModalidadTraslado_Cod_SunatMapper;
import org.gteperu.erp.everest.mappers.Prod_Cod_SunatMapper;
import org.gteperu.erp.everest.service._ModalidadTraslado_Cod_SunatService;
import org.gteperu.erp.everest.service._Prod_Cod_SunatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("modalidadTraslado_Cod_SunatService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class _ModalidadTraslado_Cod_SunatServiceImpl implements _ModalidadTraslado_Cod_SunatService{
	@Resource(name = "modalidadtraslado_cod_sunatMapper")
	ModalidadTraslado_Cod_SunatMapper modalidadtraslado_cod_sunatMapper;

	@Override
	public List<_ModalidadTrasladoCodigoSunat> retornarModalidadTrasladoCodigoSunat(_ModalidadTrasladoCodigoSunat ModalidadTrasladoCodigoSunat) {
		List<_ModalidadTrasladoCodigoSunat> lsModalidadTrasladoCodigoSunat = new ArrayList<_ModalidadTrasladoCodigoSunat>();
		try{
			lsModalidadTrasladoCodigoSunat = modalidadtraslado_cod_sunatMapper.retornarModalidadTrasladoCodigoSunat(ModalidadTrasladoCodigoSunat);
		}
		catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/retornarModalidadTrasladoCodigoSunat => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/retornarModalidadTrasladoCodigoSunat" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		
			return lsModalidadTrasladoCodigoSunat;
		
	}

}
