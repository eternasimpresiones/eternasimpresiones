package org.gteperu.erp.everest.service.impl;

import org.gteperu.erp.everest.domain.Modulo;
import org.gteperu.erp.everest.domain.Pagina;
import org.gteperu.erp.everest.domain.Pagination;
import org.springframework.stereotype.Service;
import org.gteperu.erp.everest.domain.Perfiles;
import org.gteperu.erp.everest.domain.Sutipodocumento;
import org.gteperu.erp.everest.mappers.PerfilesMapper;
import org.gteperu.erp.everest.mappers.SutipodocumentoMapper;
import org.gteperu.erp.everest.service.PerfilesService;
import org.gteperu.erp.everest.service.SutipodocumentoService;

import java.util.List;
import javax.annotation.Resource;
import java.util.ArrayList;

@Service("sutipodocumentoService")
public class SutipodocumentoServiceImpl implements SutipodocumentoService {

    @Resource(name = "sutipodocumentoMapper")
    SutipodocumentoMapper sutipodocumentoMapper;

    @Override
    public List<Sutipodocumento> retornaSutipodocumentoPorEstado(Sutipodocumento sutipodocumento) {
        List<Sutipodocumento> lsSutipodocumento = new ArrayList<Sutipodocumento>();
        try{
        	if (sutipodocumento.getCodigosunat() == null) {
        		lsSutipodocumento = sutipodocumentoMapper.retornaSutipodocumentoPorEstado(sutipodocumento);				
			} else {
        		lsSutipodocumento = sutipodocumentoMapper.retornaSutipodocumentoPorEstadoUnico(sutipodocumento);				
			}
        }catch(Exception e){
        	System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/retornaSutipodocumentoPorEstado => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/retornaSutipodocumentoPorEstado" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
        }
        
        return lsSutipodocumento;
    }

    
    @Override
    public List<Sutipodocumento> listarDocumentosPublicos(Sutipodocumento sutipodocumento) {
        List<Sutipodocumento> lsSutipodocumento = new ArrayList<Sutipodocumento>();
        try{
        	lsSutipodocumento = sutipodocumentoMapper.listarDocumentosPublicos();
        }catch(Exception e){
        	System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/listarDocumentosPublicos => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/listarDocumentosPublicos" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
        }
        
        return lsSutipodocumento;
    }
    
	@Override
	public List<Sutipodocumento> comboSutipodocumentoidentidad() {
		List<Sutipodocumento> lscombo = new ArrayList<Sutipodocumento>();
        try{
        	lscombo = sutipodocumentoMapper.comboSutipodocumentoidentidad();
        }catch(Exception e){
        	System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/comboSutipodocumentoidentidad => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/comboSutipodocumentoidentidad" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
        }
        
        return lscombo;
	}
    
}