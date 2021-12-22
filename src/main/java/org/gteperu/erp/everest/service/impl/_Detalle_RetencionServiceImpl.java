package org.gteperu.erp.everest.service.impl;

import org.springframework.stereotype.Service;
import org.gteperu.erp.everest.domain._Cpe_RetencionPercepcionBean;
import org.gteperu.erp.everest.domain._Cpe_RetencionPercepcion_DetalleBean;
import org.gteperu.erp.everest.mappers.Detalle_RetencionMapper;
import org.gteperu.erp.everest.service._Detalle_RetencionService;

import java.util.List;
import javax.annotation.Resource;
import java.util.ArrayList;

@Service("detalle_retencionService")
public class _Detalle_RetencionServiceImpl implements _Detalle_RetencionService {

    @Resource(name = "detalle_RetencionMapper")
    Detalle_RetencionMapper detalle_RetencionMapper;
    
    @Override
    public Integer insertarDetalleDocumentoRetencion(_Cpe_RetencionPercepcion_DetalleBean documento) {
    		Integer auxiliar = 0;
		try {
			auxiliar = detalle_RetencionMapper.insertarDetalleDocumentoRetencion(documento);
		} catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/insertarDocumento => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/insertarDocumento" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return auxiliar;
    }
    
    
    @Override
    public Integer eliminarDetalleDocumentoRetencion(_Cpe_RetencionPercepcion_DetalleBean documento) {
    		Integer auxiliar = 0;
		try {
			auxiliar = detalle_RetencionMapper.eliminarDetalleDocumentoRetencion(documento);
		} catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/eliminarDetalleDocumentoRetencion => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/eliminarDetalleDocumentoRetencion" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return auxiliar;
    }
}