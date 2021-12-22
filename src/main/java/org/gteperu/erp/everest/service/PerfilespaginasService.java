
package org.gteperu.erp.everest.service;
import org.gteperu.erp.everest.domain.Perfilespaginas;
import java.util.List;
public interface	PerfilespaginasService	{
public List<Perfilespaginas>	retornaPerfilespaginas(); 
public List<Perfilespaginas>	retornaPerfilespaginasTodas (); 
public List<Perfilespaginas>	retornaPerfilespaginasPorEstado (Perfilespaginas idperfilespaginas);
public List<Perfilespaginas>	retornaPerfilespaginasPorPerfil (Perfilespaginas idperfilespaginas);
public List<Perfilespaginas>	retornaPerfilespaginasLikePorNombre (Perfilespaginas idperfilespaginas);
public Perfilespaginas retornaObjPerfilespaginasPorIdPerIdPagina(Perfilespaginas idperfilespaginas);

public Perfilespaginas	retornaObjPerfilespaginas(Perfilespaginas idperfilespaginas);
public Integer 	updatePerfilespaginas(Perfilespaginas idperfilespaginas);
public Integer 	insertaPerfilespaginas(Perfilespaginas idperfilespaginas);
public Integer 	insertaPerfilespaginaslist(List<Perfilespaginas> idperfilespaginas);
public Integer 	eliminaPerfilespaginas(Perfilespaginas idperfilespaginas);
}