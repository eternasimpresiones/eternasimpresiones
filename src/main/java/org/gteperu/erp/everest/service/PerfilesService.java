package org.gteperu.erp.everest.service;

import org.gteperu.erp.everest.domain.Modulo;
import org.gteperu.erp.everest.domain.Pagina;
import org.gteperu.erp.everest.domain.Perfiles;
import java.util.List;

public interface PerfilesService {

	public List<Perfiles> retornaPerfiles();

	public List<Perfiles> retornaPerfilesTodas();

	public List<Perfiles> retornaPerfilesPorEstado(Perfiles idperfiles);

	public List<Perfiles> retornaPerfilesLikePorNombre(Perfiles idperfiles);

	public List<Pagina> retornaPaginasMapPorPerfil(Perfiles p);

	public List<Modulo> retornaPaginasMapPorPerfilAgrupado(Perfiles p);

	public Perfiles retornaObjPerfiles(Perfiles idperfiles);

	public Integer updatePerfiles(Perfiles idperfiles);

	public Integer insertaPerfiles(Perfiles idperfiles);

	public Integer eliminaPerfiles(Perfiles idperfiles);
}