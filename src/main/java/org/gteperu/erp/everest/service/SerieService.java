package org.gteperu.erp.everest.service;

import java.util.List;

import org.gteperu.erp.everest.domain.Serie;

 
public interface SerieService {
	
	public Integer insertarSerie(Serie s);
	public Integer actualizarSerie(Serie s);
	public Integer eliminarSerie(Integer id);
	public List<Serie> listarSerie(Serie s);
	public Serie retornaSeriexTipoDoc(Integer id);
	public List<Serie> listarSerieRepedita(Serie s);
	public Serie retornaSeriexTipoDocLocal(Integer idlocal,Integer idsutipodocumento);
	public Serie retornaSeriexTipoDocyLocal(Serie s);

}
