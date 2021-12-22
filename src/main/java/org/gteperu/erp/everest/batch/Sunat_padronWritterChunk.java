


package org.gteperu.erp.everest.batch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.gteperu.erp.everest.domain.Sunat_padron;
import org.gteperu.erp.everest.service.Sunat_padronService;
import org.gteperu.erp.everest.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;


public class Sunat_padronWritterChunk implements ItemWriter<Sunat_padron>, StepExecutionListener  {

	private static final Logger LOG = LoggerFactory.getLogger(Sunat_padronWritterChunk.class);
	
	Sunat_padronService sunat_padronService;
	
	
	Long insersiones = 0L;
	Integer _cleangarbage = 0;
	Sunat_padron su = new Sunat_padron();
	
	public Sunat_padronWritterChunk(Sunat_padronService sunat_padronService){
		this.sunat_padronService = sunat_padronService;
	}
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		su.setEstado(false);
		su = sunat_padronService.retornaTablaPorEstado(su);
		if(su!=null){
			Integer truncate = sunat_padronService.eliminaRucSunatPadron(su);
		}else{
			System.out.println("Error en la tabla sunat_padron");
			throw new RuntimeException(Constantes.msgErrorSunatPadron);
		}
		LOG.info("Line Writer initialized.");
	}

	@Override
	public void write(List<? extends Sunat_padron> lsSunatPadrons) throws Exception {
		Integer cat = 0;
		List<Sunat_padron> lssunat = new ArrayList<Sunat_padron>();
		try{
			lssunat.addAll(lsSunatPadrons);
			cat = sunat_padronService.insertaRucSunatPadronA(lssunat,su);
			insersiones ++;
			_cleangarbage++;
			lssunat.clear();
			System.out.println("TERMINO DE INSERTAR LA LISTA");
			System.out.println("Insersion nro :" + insersiones);
			if(_cleangarbage == 150 ){
				_cleangarbage = 0;
				//limpia la memoria basura
				Runtime garbage = Runtime.getRuntime();
		        System.out.println("Memoria libre antes de limpieza: " + garbage.freeMemory());
		        garbage.gc();
		        System.out.println("Memoria libre tras la limpieza: "+ garbage.freeMemory());
			}
			/*if(_cleangarbage == 7 ){
				System.out.println("Errorrrrrrrrr");
				throw new RuntimeException();
			}*/
		}catch(Exception e){
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/write => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/write" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw new RuntimeException();
		}
	}
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		//se pasa el parametro al listener
		if(su!=null){
			stepExecution.getJobExecution().getExecutionContext().put("sunat_wrapper", su);		
		}
		//limpieza de variables
		insersiones = 0L;
		_cleangarbage = 0;
		su = new Sunat_padron();
		//limpieza de espacio
		Runtime garbage = Runtime.getRuntime();
        System.out.println("Memoria libre antes de limpieza: " + garbage.freeMemory());
        garbage.gc();
        System.out.println("Memoria libre tras la limpieza: "+ garbage.freeMemory());
        //log
        LOG.info("Line Writer ended.");
        return ExitStatus.COMPLETED;
	}

	

}
