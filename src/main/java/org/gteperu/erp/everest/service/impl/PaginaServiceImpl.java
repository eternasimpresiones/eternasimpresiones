package org.gteperu.erp.everest.service.impl;

import org.springframework.stereotype.Service;
import org.gteperu.erp.everest.domain.Pagina;
import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.Perfiles;
import org.gteperu.erp.everest.mappers.PaginaMapper;
import org.gteperu.erp.everest.service.PaginaService;

import java.util.List;
import javax.annotation.Resource;
import java.util.ArrayList;

@Service("paginaService")
public class PaginaServiceImpl implements PaginaService {

    @Resource(name = "paginaMapper")
    PaginaMapper paginaMapper;

    @Override
    public List<Pagina> retornaPagina() {
        List<Pagina> lsPagina = new ArrayList<Pagina>();
        lsPagina = paginaMapper.retornaPagina();
        return lsPagina;
    }

    @Override
    public List<Pagina> retornaPaginaTodas() {
        List<Pagina> lsPagina = new ArrayList<Pagina>();
        lsPagina = paginaMapper.retornaPaginaTodas();
        return lsPagina;
    }

    @Override
    public List<Pagina> retornaPaginaLikePorNombre(Pagina idpagina) {
        List<Pagina> lsPagina = new ArrayList<Pagina>();
        lsPagina = paginaMapper.retornaPaginaLikePorNombre(idpagina);
        return lsPagina;
    }

    @Override
    public List<Pagina> retornaPaginaPorEstado(Pagina idpagina) {
        List<Pagina> lsPagina = new ArrayList<Pagina>();
        Pagination pg = new Pagination();
        pg = paginaMapper.retornaCantidadList(idpagina);
        lsPagina = paginaMapper.retornaPaginaPorEstado(idpagina);
        if (lsPagina != null && lsPagina.size() > 0) {
            lsPagina.get(0).setCantidad(pg.getCantidad());
        }
        return lsPagina;
    }

    @Override
    public Pagina retornaObjPagina(Pagina idpagina) {
        return paginaMapper.retornaObjPagina(idpagina);
    }

    @Override
    public Integer updatePagina(Pagina idpagina) {
        return paginaMapper.updatePagina(idpagina);
    }

    @Override
    public Integer insertaPagina(Pagina idpagina) {
        return paginaMapper.insertaPagina(idpagina);
    }

    @Override
    public Integer eliminaPagina(Pagina idpagina) {
        return paginaMapper.eliminaPagina(idpagina);
    }

    @Override
    public List<Pagina> retornaPaginasMapPorPerfil(Perfiles p) {
        return paginaMapper.retornaPaginasMapPorPerfil(p);

    }
}