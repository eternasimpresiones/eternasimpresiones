package org.gteperu.erp.everest.service;

import java.util.List;
import org.gteperu.erp.everest.domain.InternalWrapper;
import org.gteperu.erp.everest.domain._ProductoCodigoSunat;
import org.gteperu.erp.everest.domain._TipoAfecto;

public interface _Prod_Cod_SunatService {
	
	public List<_ProductoCodigoSunat> retornarProductoCodigoSunat(_ProductoCodigoSunat _ProductoCodigoSunat);
	
	public Integer actualizarCodigoProductoSunat();
	
}
