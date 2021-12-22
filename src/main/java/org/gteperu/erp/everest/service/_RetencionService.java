package org.gteperu.erp.everest.service;

import java.util.List;

import org.gteperu.erp.everest.domain._Cpe_RetencionPercepcionBean;
import org.gteperu.erp.everest.domain._Retencion;
import org.gteperu.erp.everest.domain._RetencionExcel;


public interface _RetencionService {


	public Integer insertarDocumentoRetencion(_Retencion doc);
	public List<_Retencion> lsRetencionPercepcion(_Retencion retencion);
	public Integer actualizarRetencionEstadoRechazado(_Retencion retencion);
	public List<_Retencion> lsRetencionOPercepcionxEstado(_Retencion retencion);
	public Integer cantidadRetePerc(_Retencion documento);
	public List<_RetencionExcel> lsRetencionPercepcionExcel(_Retencion retencion);

    public Integer eliminarDocumentoRetencion(_Retencion documento) ;
	
}