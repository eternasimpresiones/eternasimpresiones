package org.gteperu.erp.everest.batch;
import org.gteperu.erp.everest.cpe.CPE;
import org.gteperu.erp.everest.domain.Sunat_padron;
import org.gteperu.erp.everest.service.Auditoria_SunatService;
import org.gteperu.erp.everest.service.Codigo_qrService;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service.SerieService;
import org.gteperu.erp.everest.service.Sunat_padronService;
import org.gteperu.erp.everest.service.Tipo_Operacion_SunatService;
import org.gteperu.erp.everest.service._ClienteService;
import org.gteperu.erp.everest.service._CompanyService;
import org.gteperu.erp.everest.service._DocumentoService;
import org.gteperu.erp.everest.service._RetencionService;
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
public class Sunat_reenvioBatchConfiguration {
	@Autowired
	public JobBuilderFactory jobbuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepbuilderFactory;
	
	@Autowired
 	CPE cpeResource;

 	@Autowired
 	_DocumentoService documentoService; 
 	
 	@Autowired
 	_RetencionService retencionService; 
 	
 	@Autowired
	_CompanyService companyService;
 	
 	@Autowired
 	_ClienteService clienteService;
 	
 	@Autowired
 	ParametrosService parametrosService;
 	
 	@Autowired
 	Tipo_Operacion_SunatService tipoOperacionSunatService;
 	
 	@Autowired
 	Auditoria_SunatService auditoria_SunatService;
 	
 	@Autowired
 	Codigo_qrService codigoService;

	
	@Bean
	protected Step sunatReenvioDoc()
			{
			return stepbuilderFactory
				.get("sunatReenvioDoc_Step")
				.tasklet(new Sunat_reenvio_documentosTaskletBatch(cpeResource,documentoService,retencionService,companyService,clienteService,parametrosService,tipoOperacionSunatService,auditoria_SunatService,codigoService))
				.build();
	}
	
	
	@Bean(name="sunatReenvioDocJob")
	public Job job(Sunat_reenvioListener listener) {
		return jobbuilderFactory
				.get("sunatReenvioDoc_Job")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.start(this.sunatReenvioDoc())
				.build();
	}
}
