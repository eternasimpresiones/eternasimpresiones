package org.gteperu.erp.everest.service;

 import java.util.List;

import org.gteperu.erp.everest.domain.Local;

public interface LocalService {

 
    public List<Local> retornaLocalTodas(Local id);

    public List<Local> retornaLocalPorEstado(Local id);

    public Local retornaLocalPorId(Local id);

     public Integer updateLocal(Local id);

    public Integer insertaLocal(Local id);

    public Integer eliminaLocal(Local id);
}