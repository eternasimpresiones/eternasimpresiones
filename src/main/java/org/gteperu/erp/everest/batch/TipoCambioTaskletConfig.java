package org.gteperu.erp.everest.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableBatchProcessing
public class TipoCambioTaskletConfig {

	@Autowired
	public JobBuilderFactory jobbuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepbuilderFactory;
	
	@Bean
	@Primary
	public Tasklet crearTipoCambioTasklet() {
		return new TipoCambioTasklet();
	}
	
	@Bean
	public Step tipoCambioTaskletStep(){
		return stepbuilderFactory
				.get("tipoCambioTaskletStep")
				.tasklet(crearTipoCambioTasklet())
				.build();
	}
	
	@Bean
	public Job tipoCambioJob() {
		return jobbuilderFactory
				.get("tipoCambioJob")
				.start(this.tipoCambioTaskletStep())
				.build();
	}
}
