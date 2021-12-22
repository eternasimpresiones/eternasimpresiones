package org.gteperu.erp.everest.service;

import java.util.List;
import org.gteperu.erp.everest.domain.InternalWrapper;
import org.gteperu.erp.everest.domain.Ubigeo;
import org.gteperu.erp.everest.domain._ProductoCodigoSunat;
import org.gteperu.erp.everest.domain._TipoAfecto;

public interface _UbigeoService {
	
	public List<Ubigeo> retornaObjDepartamento(Ubigeo ubigeo);
	
	public List<Ubigeo> retornaObjProvincia(Ubigeo ubigeo);
	
	public List<Ubigeo> retornaObjDistrito(Ubigeo ubigeo);
	
	public Ubigeo retornaObjUbigeoxCodigo(String codigo);

	
	
}
