

package org.gteperu.erp.everest.service.impl;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.gteperu.erp.everest.domain.Auditoria;
import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.mappers.AuditoriaMapper;
import org.gteperu.erp.everest.service.AuditoriaService;
import org.gteperu.erp.everest.service.BatchService;


@Service
public class BatchServiceImpl implements BatchService {

	@Autowired
    JobLauncher jobLauncher;
    
    @Autowired
    @Qualifier("sunatPadronJob")
    Job sunatPadronJob;
    
    @Autowired
    @Qualifier("sunatReenvioDocJob")
    Job sunatReenvioDocJob;
    
    @Autowired
    @Qualifier("cargaDocumentosJob")
    Job cargaDocumentosJob;
    
    @Override
    @Scheduled(cron=" 00 0 01 * * ? ")
    public void SunatReenvioDocs() throws Exception {
    	try{
    		jobLauncher.run(sunatReenvioDocJob, new JobParametersBuilder()
    				.addLong("timestamp", System.currentTimeMillis())
    				.toJobParameters());
    		System.out.print("Se ejecuto el job");
    	}catch(Exception e){
    		System.out.print(e.getMessage());
    		throw e;
    	}
    	
    }
    @Override
    @Scheduled(cron=" 0 00 01 * * ? ")
    public void SunatPadronRuc() throws Exception {
    	try{
    		jobLauncher.run(sunatPadronJob, new JobParametersBuilder()
    				.addLong("timestamp", System.currentTimeMillis())
    				.toJobParameters());
    		System.out.print("Se ejecuto el job");
    	}catch(Exception e){
    		System.out.print(e.getMessage());
    		throw e;
    	}
    	
    }
    
    @Override
  @Scheduled(cron=" 0 00 01 * * ? ")
  public void cargaDocumentosJob() throws Exception {
  	try{
  		jobLauncher.run(cargaDocumentosJob, new JobParametersBuilder()
  				.addLong("timestamp", System.currentTimeMillis())
  				.toJobParameters());
  		System.out.print("Se ejecuto el cargaDocumentosJob");
  	}catch(Exception e){
  		System.out.print(e.getMessage());
  		throw e;
  	}
  	
  }

}