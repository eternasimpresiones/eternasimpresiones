package org.gteperu.erp.everest.batch;


import org.gteperu.erp.everest.domain.Sunat_padron;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service.Sunat_padronService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class Sunat_padronBatchConfiguration {
	
	@Autowired
	public JobBuilderFactory jobbuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepbuilderFactory;

	@Autowired
	public ParametrosService parametrosService;
	
	@Autowired
	public Sunat_padronService sunat_padronService;
	
	
	@Bean
    public ItemReader<Sunat_padron> itemReader() {
        return new Sunat_padronReaderChunk();
    }
	
	@Bean
	public ItemWriter<Sunat_padron> itemWritter() {
		return new Sunat_padronWritterChunk(sunat_padronService);
	}

	
	@Bean
	public Step sunatPadronDescarga(){
		return stepbuilderFactory
				.get("sunatPadronDescarga_Step")
				.tasklet(new Sunat_padron_descargaTaskletBatch(parametrosService))
				.build();
	}
	
	@Bean
	protected Step sunatPadronStep(
			ItemReader<Sunat_padron> reader ,
			ItemWriter<Sunat_padron> writer
			) {
		return stepbuilderFactory.get("sunatPadronStep")
				.<Sunat_padron,Sunat_padron> chunk(10000)
				.reader(reader)
				.writer(writer)
				.build();
	}
	
	
	@Bean(name="sunatPadronJob")
	public Job job(Sunat_padronJobListener listener) {
		return jobbuilderFactory
				.get("sunatPadron_Job")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.start(this.sunatPadronDescarga())
				.next(this.sunatPadronStep(itemReader(),itemWritter()))
				.build();
	}
	

}
