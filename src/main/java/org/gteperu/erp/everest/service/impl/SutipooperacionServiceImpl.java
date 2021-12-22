package org.gteperu.erp.everest.service.impl;

import org.gteperu.erp.everest.domain.Modulo;
import org.gteperu.erp.everest.domain.Pagina;
import org.gteperu.erp.everest.domain.Pagination;
import org.springframework.stereotype.Service;
import org.gteperu.erp.everest.domain.Perfiles;
import org.gteperu.erp.everest.domain.Sutipodocumento;
import org.gteperu.erp.everest.domain.Sutipooperacion;
import org.gteperu.erp.everest.mappers.PerfilesMapper;
import org.gteperu.erp.everest.mappers.SutipodocumentoMapper;
import org.gteperu.erp.everest.mappers.SutipooperacionMapper;
import org.gteperu.erp.everest.service.PerfilesService;
import org.gteperu.erp.everest.service.SutipodocumentoService;
import org.gteperu.erp.everest.service.SutipooperacionService;

import java.util.List;
import javax.annotation.Resource;
import java.util.ArrayList;

@Service("sutipooperacionService")
public class SutipooperacionServiceImpl implements SutipooperacionService {

    @Resource(name = "sutipooperacionMapper")
    SutipooperacionMapper sutipooperacionMapper;

    @Override
    public List<Sutipooperacion> retornaSutipooperacionPorEstado(Sutipooperacion sutipooperacion) {
        List<Sutipooperacion> lssutipooperacion = new ArrayList<Sutipooperacion>();
        try{
        	lssutipooperacion = sutipooperacionMapper.retornaSutipooperacionPorEstado(sutipooperacion);
        }catch(Exception e){
        	System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/retornaSutipooperacionPorEstado => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/retornaSutipooperacionPorEstado" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
        }
        
        return lssutipooperacion;
    }
    
}