package org.gteperu.erp.everest.service;

import java.util.List;

import org.gteperu.erp.everest.domain.Cuota;

 
public interface CuotaService {
	
	public Integer insertarCuota(Cuota s);
	public Integer actualizarCuota(Cuota s);
	public Integer eliminarCuota(Integer id);
	public List<Cuota> listarCuotaxidocumento(Integer id_documento);

}
