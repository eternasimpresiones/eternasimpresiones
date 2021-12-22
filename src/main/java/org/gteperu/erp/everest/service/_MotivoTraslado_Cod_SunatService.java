package org.gteperu.erp.everest.service;

import java.util.List;
import org.gteperu.erp.everest.domain.InternalWrapper;
import org.gteperu.erp.everest.domain._ModalidadTrasladoCodigoSunat;
import org.gteperu.erp.everest.domain._MotivoTrasladoCodigoSunat;
import org.gteperu.erp.everest.domain._TipoAfecto;

public interface _MotivoTraslado_Cod_SunatService {
	
	public List<_MotivoTrasladoCodigoSunat> retornarMotivoTrasladoCodigoSunat(_MotivoTrasladoCodigoSunat MotivoTrasladoCodigoSunat);
	
}
