package org.gteperu.erp.everest.batch;

import org.gteperu.erp.everest.domain.Sunat_padron;
import org.gteperu.erp.everest.service.Sunat_padronService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sunat_padronJobListener extends JobExecutionListenerSupport{

	
	private static final Logger LOG = LoggerFactory.getLogger(Sunat_padronJobListener.class);
	
	@Autowired
	Sunat_padronService sunat_padronService;
	
	@Override
	public void afterJob(JobExecution jobExecution){
		if(jobExecution.getStatus() == BatchStatus.COMPLETED){
			Integer update_sunat_true = 0;
			Integer update_sunat_false = 0;
			Sunat_padron sunat_false = (Sunat_padron) jobExecution.getExecutionContext().get("sunat_wrapper");
			if(sunat_false!=null){
				Sunat_padron sunat_true = new Sunat_padron(); 
				sunat_true.setEstado(true);
				sunat_true = sunat_padronService.retornaTablaPorEstado(sunat_true);
				if(sunat_true!=null){
					sunat_true.setEstado(!sunat_true.getEstado());
					sunat_false.setEstado(!sunat_false.getEstado());
					update_sunat_true = sunat_padronService.updateEstadoSunatPadron(sunat_true);
					update_sunat_false = sunat_padronService.updateEstadoSunatPadron(sunat_false);
				}
				System.out.println("Actualizacion de la tabla :" + sunat_true.getTabla() + "----"+ "estado: " + sunat_true.getEstado());
				System.out.println("Actualizacion de la tabla :" + sunat_false.getTabla() + "----"+ "estado: " + sunat_false.getEstado());
			}
			LOG.info("FINALIZO EL JOB!!");
		}
		if(jobExecution.getStatus() == BatchStatus.FAILED){
			LOG.info("FINALIZO EL JOB CON ERRORES!");
		}
	}
	
	
}
