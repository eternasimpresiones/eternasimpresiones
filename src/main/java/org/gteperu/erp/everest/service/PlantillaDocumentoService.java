package org.gteperu.erp.everest.service;

import java.util.List;

import org.gteperu.erp.everest.domain.Plantilla_documento;

public interface PlantillaDocumentoService {

	public Integer insertarPlantillaDocumento(Plantilla_documento objPlantilla);
	
	public Integer actualizarPlantillaDocumento(Plantilla_documento objPlantilla);
	
	public Integer eliminarPlantillaDocumento(Integer objPlantilla);
	
	public List<Plantilla_documento> listarPlantillasTodas(Plantilla_documento objPlantilla);
	
	public List<Plantilla_documento> listarPlantillas(Plantilla_documento objPlantilla);
}
