package org.gteperu.erp.everest.service;

import org.gteperu.erp.everest.domain.Modulo;
import org.gteperu.erp.everest.domain.Pagina;
import org.gteperu.erp.everest.domain.Perfiles;
import org.gteperu.erp.everest.domain.Sutipodocumento;
import org.gteperu.erp.everest.domain.Sutipooperacion;

import java.util.List;

public interface SutipooperacionService {
	
	public List<Sutipooperacion> retornaSutipooperacionPorEstado(Sutipooperacion sutipooperacion);	
}