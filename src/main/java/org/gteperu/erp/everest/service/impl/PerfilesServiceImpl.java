package org.gteperu.erp.everest.service.impl;

import org.gteperu.erp.everest.domain.Modulo;
import org.gteperu.erp.everest.domain.Pagina;
import org.gteperu.erp.everest.domain.Pagination;
import org.springframework.stereotype.Service;
import org.gteperu.erp.everest.domain.Perfiles;
import org.gteperu.erp.everest.mappers.PerfilesMapper;
import org.gteperu.erp.everest.service.PerfilesService;

import java.util.List;
import javax.annotation.Resource;
import java.util.ArrayList;

@Service("perfilesService")
public class PerfilesServiceImpl implements PerfilesService {

    @Resource(name = "perfilesMapper")
    PerfilesMapper perfilesMapper;

    @Override
    public List<Perfiles> retornaPerfiles() {
        List<Perfiles> lsPerfiles = new ArrayList<Perfiles>();
        lsPerfiles = perfilesMapper.retornaPerfiles();
        return lsPerfiles;
    }
   @Override
    public List<Modulo> retornaPaginasMapPorPerfilAgrupado(Perfiles p) {
        return perfilesMapper.retornaPaginasMapPorPerfilAgrupado(p);
    }
    @Override
    public List<Pagina> retornaPaginasMapPorPerfil(Perfiles p) {
        return perfilesMapper.retornaPaginasMapPorPerfil(p);
    }

    @Override
    public List<Perfiles> retornaPerfilesTodas() {
        List<Perfiles> lsPerfiles = new ArrayList<Perfiles>();
        lsPerfiles = perfilesMapper.retornaPerfilesTodas();
        return lsPerfiles;
    }

    @Override
    public List<Perfiles> retornaPerfilesLikePorNombre(Perfiles idperfiles) {
        List<Perfiles> lsPerfiles = new ArrayList<Perfiles>();
        lsPerfiles = perfilesMapper.retornaPerfilesLikePorNombre(idperfiles);
        return lsPerfiles;
    }

    @Override
    public List<Perfiles> retornaPerfilesPorEstado(Perfiles idperfiles) {
        List<Perfiles> lsPerfiles = new ArrayList<Perfiles>();
        Pagination pg = new Pagination();
        pg = perfilesMapper.retornaCantidadList(idperfiles);
        lsPerfiles = perfilesMapper.retornaPerfilesPorEstado(idperfiles);
        if (lsPerfiles != null && lsPerfiles.size() > 0) {
            lsPerfiles.get(0).setCantidad(pg.getCantidad());
        }
        return lsPerfiles;
    }

    @Override
    public Perfiles retornaObjPerfiles(Perfiles idperfiles) {
        return perfilesMapper.retornaObjPerfiles(idperfiles);
    }

    @Override
    public Integer updatePerfiles(Perfiles idperfiles) {
        return perfilesMapper.updatePerfiles(idperfiles);
    }

    @Override
    public Integer insertaPerfiles(Perfiles idperfiles) {
        return perfilesMapper.insertaPerfiles(idperfiles);
    }

    @Override
    public Integer eliminaPerfiles(Perfiles idperfiles) {
        return perfilesMapper.eliminaPerfiles(idperfiles);
    }
}