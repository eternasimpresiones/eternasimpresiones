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
public class CargaMasivaListener extends JobExecutionListenerSupport{

	
	private static final Logger LOG = LoggerFactory.getLogger(CargaMasivaListener.class);
	
//	@Autowired
//	Sunat_padronService sunat_padronService;
	
	@Override
	public void afterJob(JobExecution jobExecution){
		if(jobExecution.getStatus() == BatchStatus.COMPLETED){
			LOG.info("FINALIZO EL JOB!!");
		}
		if(jobExecution.getStatus() == BatchStatus.FAILED){
			LOG.info("FINALIZO EL JOB CON ERRORES!");
		}
	}
	
	
}
