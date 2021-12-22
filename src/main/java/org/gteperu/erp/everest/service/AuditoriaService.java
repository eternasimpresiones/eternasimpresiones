package org.gteperu.erp.everest.service;

import org.gteperu.erp.everest.domain.Auditoria;
import java.util.List;

public interface AuditoriaService {

    public List<Auditoria> retornaAuditoriaTodas(Auditoria idauditoria);

    public List<Auditoria> retornaAuditoriaPorFiltro(Auditoria idauditoria);

    public Auditoria retornaObjAuditoria(Auditoria idauditoria);

    public Integer insertaAuditoria(Auditoria idauditoria);
    
   
}