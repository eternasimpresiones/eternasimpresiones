package org.gteperu.erp.everest.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.gteperu.erp.everest.domain.Local;
import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.mappers.LocalMapper;
import org.gteperu.erp.everest.service.LocalService;
import org.springframework.stereotype.Service;

@Service("localService")
public class LocalServiceImpl implements LocalService {

    @Resource(name = "localMapper")
    LocalMapper localMapper;

    

    @Override
    public List<Local> retornaLocalTodas(Local local) {
        List<Local> lsLocal = new ArrayList<Local>();
        lsLocal = localMapper.retornaLocalTodas(local);
        return lsLocal;
    }

 

    @Override
    public List<Local> retornaLocalPorEstado(Local id) {
        List<Local> lsLocal = new ArrayList<Local>();
        Pagination pg = new Pagination();
        pg = localMapper.retornaCantidadList(id);
        lsLocal = localMapper.retornaLocalPorEstado(id);
        if (lsLocal != null && lsLocal.size() > 0) {
            lsLocal.get(0).setCantidad(pg.getCantidad());
        }
        return lsLocal;
    }
    
    @Override
    public Local retornaLocalPorId(Local local) { 
          local = localMapper.retornaLocalPorId(local);
          return local;
    }
    
    @Override
    public Integer updateLocal(Local id) {
        return localMapper.updateLocal(id);
    }

    @Override
    public Integer insertaLocal(Local id) {
        return localMapper.insertaLocal(id);
    }

    @Override
    public Integer eliminaLocal(Local id) {
        return localMapper.eliminaLocal(id);
    }
}