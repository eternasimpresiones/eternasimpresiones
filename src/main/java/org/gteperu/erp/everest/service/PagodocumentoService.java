package org.gteperu.erp.everest.service;

import java.util.List;

import org.gteperu.erp.everest.domain.Pagodocumento;

public interface PagodocumentoService {

    public Integer insertar(Pagodocumento pagodocumento);
	public Integer actualizar(Pagodocumento pagodocumento);
	public Integer eliminar(Pagodocumento pagodocumento);
    public List<Pagodocumento> listarPorDocumento(Pagodocumento pagodocumento);

}
