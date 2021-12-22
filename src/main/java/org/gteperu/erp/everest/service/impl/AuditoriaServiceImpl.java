package org.gteperu.erp.everest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.gteperu.erp.everest.domain.Auditoria;
import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.mappers.AuditoriaMapper;
import org.gteperu.erp.everest.service.AuditoriaService;

import java.util.List;
import javax.annotation.Resource;
import java.util.ArrayList;

@Service("auditoriaService")
public class AuditoriaServiceImpl implements AuditoriaService {

    @Resource(name = "auditoriaMapper")
    AuditoriaMapper auditoriaMapper;
    
    

	@Override
    public List<Auditoria> retornaAuditoriaTodas(Auditoria idauditoria) {
        List<Auditoria> lsAuditoria = new ArrayList<Auditoria>();
        Pagination pg = new Pagination();
        pg = auditoriaMapper.retornaCantidadList(idauditoria);
        lsAuditoria = auditoriaMapper.retornaAuditoriaTodas(idauditoria);
        if (lsAuditoria != null && lsAuditoria.size() > 0) {
            lsAuditoria.get(0).setCantidad(pg.getCantidad());
        }
        return lsAuditoria;
    }

    @Override
    public List<Auditoria> retornaAuditoriaPorFiltro(Auditoria idauditoria) {
        List<Auditoria> lsAuditoria = new ArrayList<Auditoria>();
        Pagination pg = new Pagination();
        pg = auditoriaMapper.retornaCantidadList(idauditoria);
        lsAuditoria = auditoriaMapper.retornaAuditoriaPorFiltro(idauditoria);
        if (lsAuditoria != null && lsAuditoria.size() > 0) {
            lsAuditoria.get(0).setCantidad(pg.getCantidad());
        }
        return lsAuditoria;
    }

    @Override
    public Auditoria retornaObjAuditoria(Auditoria idauditoria) {
        return auditoriaMapper.retornaObjAuditoria(idauditoria);
    }

    @Override
    public Integer insertaAuditoria(Auditoria idauditoria) {
        return auditoriaMapper.insertaAuditoria(idauditoria);
    }
}