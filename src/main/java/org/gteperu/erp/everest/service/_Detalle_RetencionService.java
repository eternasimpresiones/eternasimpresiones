package org.gteperu.erp.everest.service;

import org.gteperu.erp.everest.domain._Cpe_RetencionPercepcion_DetalleBean;


public interface _Detalle_RetencionService {


	public Integer insertarDetalleDocumentoRetencion(_Cpe_RetencionPercepcion_DetalleBean doc);

    public Integer eliminarDetalleDocumentoRetencion(_Cpe_RetencionPercepcion_DetalleBean documento);

}