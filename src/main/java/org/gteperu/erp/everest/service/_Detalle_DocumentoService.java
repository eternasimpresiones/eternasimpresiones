package org.gteperu.erp.everest.service;

import java.util.List;

import org.gteperu.erp.everest.domain.InternalWrapper;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._DocumentoCpe_DetalleBean;
import org.gteperu.erp.everest.domain._CpeGuiaRemisionDetalleBean;



public interface _Detalle_DocumentoService {

	public List<_DocumentoCpe_DetalleBean> listarDocumento_Detalle();
	
	public Integer insertarDocumento_Detalle(_DocumentoCpe_DetalleBean documento_detalle);
 
	public Integer actualizarDocumento_Detalle(_DocumentoCpe_DetalleBean id_documento_detalle);

	public Integer eliminarDocumento_Detalle(_DocumentoCpe_DetalleBean id_documento_detalle);
	
	public Integer insertarDocumento_DetallePorLista(List<_DocumentoCpe_DetalleBean> lsdocumento_detalle);
	public Integer insertarDocumento_Detalle_GuiaPorLista(List<_CpeGuiaRemisionDetalleBean> lsdocumento_detalle);

	
}
