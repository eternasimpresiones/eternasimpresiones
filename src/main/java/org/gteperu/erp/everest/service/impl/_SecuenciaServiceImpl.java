package org.gteperu.erp.everest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.mappers.DocumentoMapper;
import org.gteperu.erp.everest.mappers.SecuenciaMapper;
import org.gteperu.erp.everest.service._DocumentoService;
import org.gteperu.erp.everest.service._SecuenciaService;

import java.util.List;
import javax.annotation.Resource;
import java.util.ArrayList;

@Service("secuenciaService")
public class _SecuenciaServiceImpl implements _SecuenciaService {
	
	@Autowired
    SecuenciaMapper secuenciaMapper;
    

	 @Override
		public Integer selectSecuenciaDarbaja()
	 { 
		 Integer  sec=0;
		 	try {
		 		sec = secuenciaMapper.selectSecuenciaDarBaja();
			} catch (Exception e) {
				System.out.println(this.getClass().getSimpleName()+" retornaDocumentoId. ERROR : "+e.getMessage());
				throw e;
			}
			return sec;
			
	    }
	 
	 @Override
		public Integer selectSecuenciaResumen()
	 { 
		 Integer  sec=0;
		 	try {
		 		sec = secuenciaMapper.selectSecuenciaResumen();
			} catch (Exception e) {
				System.out.println(this.getClass().getSimpleName()+" retornaDocumentoId. ERROR : "+e.getMessage());
				throw e;
			}
			return sec;
			
	    }

    
}