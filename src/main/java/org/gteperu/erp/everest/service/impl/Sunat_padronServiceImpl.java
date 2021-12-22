package org.gteperu.erp.everest.service.impl;

import org.gteperu.erp.everest.domain.Modulo;
import org.gteperu.erp.everest.domain.Pagina;
import org.gteperu.erp.everest.domain.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.gteperu.erp.everest.domain.Perfiles;
import org.gteperu.erp.everest.domain.Sunat_padron;
import org.gteperu.erp.everest.mappers.PerfilesMapper;
import org.gteperu.erp.everest.mappers.SunatPadronMapper;
import org.gteperu.erp.everest.service.PerfilesService;
import org.gteperu.erp.everest.service.Sunat_padronService;
import org.gteperu.erp.everest.utils.Constantes;

import java.util.List;
import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class Sunat_padronServiceImpl implements Sunat_padronService {

    @Autowired
    SunatPadronMapper sunatPadronMapper;

    
    @Override
    public Integer insertaRucSunatPadronA(List<Sunat_padron> lsSunatPadron,Sunat_padron sunat) {
    	Integer cat = 0;
        try{
        	if(sunat.getTabla().equals(Constantes.nombreTablaSunatPadronA)){
        		cat = sunatPadronMapper.insertaRucSunatPadronA(lsSunatPadron);
        	}else{
        		cat = sunatPadronMapper.insertaRucSunatPadronB(lsSunatPadron);
        	}
        	
        }catch(Exception e){
        	System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/insertaRucSunatPadron => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/insertaRucSunatPadron" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
        }
        return cat;
    }
    
    @Override
    public Integer insertaRucSunatPadronB(List<Sunat_padron> lsSunatPadron) {
    	Integer cat = 0;
        try{
        	cat = sunatPadronMapper.insertaRucSunatPadronB(lsSunatPadron);
        }catch(Exception e){
        	System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/insertaRucSunatPadron => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/insertaRucSunatPadron" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
        }
        return cat;
    }
    
    @Override
    public Integer eliminaRucSunatPadron(Sunat_padron sunat) {
    	Integer cat = 0;
        try{
        	switch(sunat.getTabla()){
        		case Constantes.nombreTablaSunatPadronA:
        			cat = sunatPadronMapper.eliminaRucSunatPadronA();
        			break;
        		case Constantes.nombreTablaSunatPadronB:
        			cat = sunatPadronMapper.eliminaRucSunatPadronB();
        			break;
        	}
        }catch(Exception e){
        	System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/eliminaRucSunatPadron => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/eliminaRucSunatPadron" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
        }
        return cat;
    }
    
    @Override
    public Sunat_padron retornaTablaPorEstado(Sunat_padron sunat) {
    	Sunat_padron sunat_padron = new Sunat_padron();
        try{
        	sunat_padron = sunatPadronMapper.retornaTablaPorEstado(sunat);
        }catch(Exception e){
        	System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/insertaRucSunatPadron => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/insertaRucSunatPadron" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
        }
        return sunat_padron;
    }
    
    @Override
    public Integer updateEstadoSunatPadron(Sunat_padron sunat){
    	Integer cat = 0;
        try{
        	cat = sunatPadronMapper.updateEstadoSunatPadron(sunat);
        }catch(Exception e){
        	System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/updateEstadoSunatPadron => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/updateEstadoSunatPadron" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
        }
        return cat;
    }
    
    
    @Override
    public Sunat_padron consultaRuc(Sunat_padron sunat) {
    	Sunat_padron sunat_padron = new Sunat_padron();
        try{
        	switch(sunat.getTabla()){
        		case Constantes.nombreTablaSunatPadronA:
        			sunat_padron = sunatPadronMapper.consultaRuc_a(sunat);
        			break;
        		case Constantes.nombreTablaSunatPadronB:
        			sunat_padron = sunatPadronMapper.consultaRuc_b(sunat);
        			break;
        	}
        }catch(Exception e){
        	System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/consultaRuc => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/consultaRuc" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
        }
        return sunat_padron;
    }
   
   
}