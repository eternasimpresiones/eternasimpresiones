package org.gteperu.erp.everest.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.gteperu.erp.everest.domain._TipoNotaCreditoCodigoSunat;
import org.gteperu.erp.everest.mappers.TipoNotaCredito_Cod_SunatMapper;
import org.gteperu.erp.everest.service._TipoNotaCredito_Cod_SunatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("tipoNotaCredito_Cod_SunatService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class _TipoNotaCredito_Cod_SunatServiceImpl implements _TipoNotaCredito_Cod_SunatService{
	@Resource(name = "tipo_nota_credito_cod_sunatMapper")
	TipoNotaCredito_Cod_SunatMapper tipo_nota_credito_cod_sunatMapper;

	@Override
	public List<_TipoNotaCreditoCodigoSunat> retornarTipoNotaCredito_Cod_Sunat() {
		List<_TipoNotaCreditoCodigoSunat> ls_TipoNotaCreditoCodigoSunat = new ArrayList<_TipoNotaCreditoCodigoSunat>();
		try{
			ls_TipoNotaCreditoCodigoSunat = tipo_nota_credito_cod_sunatMapper.retornarTipoNotaCredito_Cod_Sunat();
		}
		catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/retornarTipoNotaCredito_Cod_Sunat => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/retornarTipoNotaCredito_Cod_Sunat" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return ls_TipoNotaCreditoCodigoSunat;
		
	}

}
