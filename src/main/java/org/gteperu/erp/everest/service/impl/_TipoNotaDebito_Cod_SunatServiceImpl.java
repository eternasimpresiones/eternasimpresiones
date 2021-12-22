package org.gteperu.erp.everest.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.gteperu.erp.everest.domain._ModalidadTrasladoCodigoSunat;
import org.gteperu.erp.everest.domain._MotivoTrasladoCodigoSunat;
import org.gteperu.erp.everest.domain._ProductoCodigoSunat;
import org.gteperu.erp.everest.domain._TipoNotaDebitoCodigoSunat;
import org.gteperu.erp.everest.mappers.ModalidadTraslado_Cod_SunatMapper;
import org.gteperu.erp.everest.mappers.MotivoTraslado_Cod_SunatMapper;
import org.gteperu.erp.everest.mappers.Prod_Cod_SunatMapper;
import org.gteperu.erp.everest.mappers.TipoNotaDebito_Cod_SunatMapper;
import org.gteperu.erp.everest.service._ModalidadTraslado_Cod_SunatService;
import org.gteperu.erp.everest.service._MotivoTraslado_Cod_SunatService;
import org.gteperu.erp.everest.service._Prod_Cod_SunatService;
import org.gteperu.erp.everest.service._TipoNotaDebito_Cod_SunatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("tipoNotaDebito_Cod_SunatService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class _TipoNotaDebito_Cod_SunatServiceImpl implements _TipoNotaDebito_Cod_SunatService{
	@Resource(name = "tipo_nota_debito_cod_sunatMapper")
	TipoNotaDebito_Cod_SunatMapper tipo_nota_debito_cod_sunatMapper;

	@Override
	public List<_TipoNotaDebitoCodigoSunat> retornarTipoNotaDebito_Cod_Sunat() {
		List<_TipoNotaDebitoCodigoSunat> ls_TipoNotaDebitoCodigoSunat = new ArrayList<_TipoNotaDebitoCodigoSunat>();
		try{
			ls_TipoNotaDebitoCodigoSunat = tipo_nota_debito_cod_sunatMapper.retornarTipoNotaDebito_Cod_Sunat();
		}
		catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/retornarTipoNotaDebito_Cod_Sunat => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/retornarTipoNotaDebito_Cod_Sunat" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return ls_TipoNotaDebitoCodigoSunat;
		
	}

}
