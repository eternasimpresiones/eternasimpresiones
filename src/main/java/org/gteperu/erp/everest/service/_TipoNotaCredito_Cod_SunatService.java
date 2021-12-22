package org.gteperu.erp.everest.service;

import java.util.List;
import org.gteperu.erp.everest.domain.InternalWrapper;
import org.gteperu.erp.everest.domain._ModalidadTrasladoCodigoSunat;
import org.gteperu.erp.everest.domain._MotivoTrasladoCodigoSunat;
import org.gteperu.erp.everest.domain._TipoAfecto;
import org.gteperu.erp.everest.domain._TipoNotaCreditoCodigoSunat;
import org.gteperu.erp.everest.domain._TipoNotaDebitoCodigoSunat;

public interface _TipoNotaCredito_Cod_SunatService {
	
	public List<_TipoNotaCreditoCodigoSunat> retornarTipoNotaCredito_Cod_Sunat();
	
}
