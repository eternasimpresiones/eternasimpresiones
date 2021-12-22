package org.gteperu.erp.everest.batch;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.gteperu.erp.everest.service.TipodetraccionService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class TipoCambioTasklet implements Tasklet,StepExecutionListener{
	
	
	@Autowired 
	TipodetraccionService tipodetraccionService;

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		try {
			tipodetraccionService.registrarTipoCambio();
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		return RepeatStatus.FINISHED;
	}
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return null;
	}

}
