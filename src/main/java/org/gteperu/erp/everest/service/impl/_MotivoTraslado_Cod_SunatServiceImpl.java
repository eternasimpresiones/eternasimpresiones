package org.gteperu.erp.everest.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.gteperu.erp.everest.domain._ModalidadTrasladoCodigoSunat;
import org.gteperu.erp.everest.domain._MotivoTrasladoCodigoSunat;
import org.gteperu.erp.everest.domain._ProductoCodigoSunat;
import org.gteperu.erp.everest.mappers.ModalidadTraslado_Cod_SunatMapper;
import org.gteperu.erp.everest.mappers.MotivoTraslado_Cod_SunatMapper;
import org.gteperu.erp.everest.mappers.Prod_Cod_SunatMapper;
import org.gteperu.erp.everest.service._ModalidadTraslado_Cod_SunatService;
import org.gteperu.erp.everest.service._MotivoTraslado_Cod_SunatService;
import org.gteperu.erp.everest.service._Prod_Cod_SunatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("motivoTraslado_Cod_SunatService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class _MotivoTraslado_Cod_SunatServiceImpl implements _MotivoTraslado_Cod_SunatService{
	@Resource(name = "motivotraslado_cod_sunatMapper")
	MotivoTraslado_Cod_SunatMapper motivotraslado_cod_sunatMapper;

	@Override
	public List<_MotivoTrasladoCodigoSunat> retornarMotivoTrasladoCodigoSunat(_MotivoTrasladoCodigoSunat MotivoTrasladoCodigoSunat) {
		List<_MotivoTrasladoCodigoSunat> lsMotivoTrasladoCodigoSunat = new ArrayList<_MotivoTrasladoCodigoSunat>();
		try{
			lsMotivoTrasladoCodigoSunat = motivotraslado_cod_sunatMapper.retornarMotivoTrasladoCodigoSunat(MotivoTrasladoCodigoSunat);
		}
		catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/retornarModalidadTrasladoCodigoSunat => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/retornarModalidadTrasladoCodigoSunat" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return lsMotivoTrasladoCodigoSunat;
		
	}

}
