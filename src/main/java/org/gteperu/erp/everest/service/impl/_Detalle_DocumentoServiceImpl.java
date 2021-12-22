package org.gteperu.erp.everest.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.gteperu.erp.everest.domain._CpeGuiaRemisionDetalleBean;
import org.gteperu.erp.everest.domain._DocumentoCpe_DetalleBean;
import org.gteperu.erp.everest.mappers.DetalleDocumentoMapper;
import org.gteperu.erp.everest.service._Detalle_DocumentoService;
import org.springframework.stereotype.Service;

@Service("detalledocumentoService")
public class _Detalle_DocumentoServiceImpl implements _Detalle_DocumentoService {

    @Resource(name = "detalleDocumentoMapper")
    DetalleDocumentoMapper detalleDocumentoMapper;
    
    @Override
    public Integer insertarDocumento_Detalle(_DocumentoCpe_DetalleBean documento_detalle) {
    	
    		Integer auxiliar = 0;

		try {
		//	auxiliar = detalledocumentoMapper.insertarDocumento_Detalle(documento_detalle);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" insertarDocumento_Detalle. ERROR : "+e.getMessage());
			throw e;
		} 
		return auxiliar;
		
    }

    @Override
    public List<_DocumentoCpe_DetalleBean> listarDocumento_Detalle(){
    	
        	List<_DocumentoCpe_DetalleBean> lsCliente = new ArrayList<_DocumentoCpe_DetalleBean>();

		try {
		//	lsCliente = detalledocumentoMapper.listarDocumento_Detalle();
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" listarDocumento_Detalle. ERROR : "+e.getMessage());
			throw e;
		}
			return lsCliente;
		
    }
    
    @Override
    public Integer actualizarDocumento_Detalle(_DocumentoCpe_DetalleBean id_documento_detalle) {
		Integer update=0;
		try{
			update = detalleDocumentoMapper.actualizarDocumento_Detalle(id_documento_detalle);
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " actualizarDocumento_Detalle. ERROR : " + e.getMessage());
			throw e;
		}
		return update;	
		
	}
    
    @Override
    public Integer eliminarDocumento_Detalle(_DocumentoCpe_DetalleBean id_documento_detalle) {
    	
			Integer delete=0;
			
		try{
			delete = detalleDocumentoMapper.eliminarDocumento_DetallePorIdDocumento(id_documento_detalle);
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " eliminarDocumento_Detalle. ERROR : " + e.getMessage());
			throw e;
		}
		return delete;	
			
	}
    
    
    @Override
    public Integer insertarDocumento_DetallePorLista(List<_DocumentoCpe_DetalleBean> lsdocumento_detalle) {
    		Integer auxiliar = 0;
		try {
			//	auxiliar = detalleDocumentoMapper.insertaDetalleDocumentoPorLista(lsdocumento_detalle);
				
				for(_DocumentoCpe_DetalleBean detalle : lsdocumento_detalle  ) {
					auxiliar = 0;
					auxiliar=detalleDocumentoMapper.insertarDocumentoDetalle(detalle);

				}
				return auxiliar;
				/*if(auxiliar == lsdocumento_detalle.size()) {
					return 1;
				}else {
					return 0;
				}*/
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" insertarDocumento_Detalle. ERROR : "+e.getMessage());
			throw e;
		}
		
    }
    
    @Override
    public Integer insertarDocumento_Detalle_GuiaPorLista(List<_CpeGuiaRemisionDetalleBean> lsdocumento_detalle) {
    		Integer auxiliar = 0;
		try {
				auxiliar = detalleDocumentoMapper.insertaDetalleDocumentoGuiaPorLista(lsdocumento_detalle);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" insertarDocumento_Detalle. ERROR : "+e.getMessage());
			throw e;
		}
		return auxiliar;
		
    }
    
}