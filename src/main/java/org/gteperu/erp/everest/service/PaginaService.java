package org.gteperu.erp.everest.service;

import org.gteperu.erp.everest.domain.Pagina;
import org.gteperu.erp.everest.domain.Perfiles;
import java.util.List;

public interface PaginaService {

    public List<Pagina> retornaPagina();

    public List<Pagina> retornaPaginaTodas();

    public List<Pagina> retornaPaginaPorEstado(Pagina idpagina);

    public List<Pagina> retornaPaginaLikePorNombre(Pagina idpagina);

    public List<Pagina> retornaPaginasMapPorPerfil(Perfiles p);

    public Pagina retornaObjPagina(Pagina idpagina);

    public Integer updatePagina(Pagina idpagina);

    public Integer insertaPagina(Pagina idpagina);

    public Integer eliminaPagina(Pagina idpagina);
}