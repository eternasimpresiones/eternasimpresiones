package org.gteperu.erp.everest.batch;


import java.util.List;

import org.gteperu.erp.everest.controller._FacturadorSunatRestController;
import org.gteperu.erp.everest.domain.Sunat_padron;
import org.gteperu.erp.everest.domain._Documento_TMP;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service.Sunat_padronService;
import org.gteperu.erp.everest.service._ClienteService;
import org.gteperu.erp.everest.service._Detalle_DocumentoService;
import org.gteperu.erp.everest.service._DocumentoService;
import org.gteperu.erp.everest.service._Documento_TMPService;
import org.gteperu.erp.everest.service._ProductoService;
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
public class CargaMasivaBatchConfiguration {
	
	@Autowired
	public JobBuilderFactory jobbuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepbuilderFactory;

	@Autowired
	public _Documento_TMPService documento_TMPService;
	
	@Autowired
	public _ClienteService clienteService;
	@Autowired
	public _ProductoService productoService;
	@Autowired
	public _DocumentoService documentoService;
	@Autowired
	public _Detalle_DocumentoService detalle_DocumentoService;
	@Autowired
	public _FacturadorSunatRestController facturador;
	
	@Bean
    public ItemReader<List<_Documento_TMP>> funcReader() {
        return new CargaMasivaReader(documento_TMPService);
    }
	
	@Bean
	public ItemWriter<List<_Documento_TMP>> funcWritter() {
		return new CargaMasivaWritter(clienteService,productoService,documentoService,detalle_DocumentoService,documento_TMPService,facturador);
	}

	@Bean
	protected Step cargaDocumentosStep(
			ItemReader<List<_Documento_TMP>> reader ,
			ItemWriter<List<_Documento_TMP>> writer
			) {
		return stepbuilderFactory.get("cargaDocumentosStep")
				.<List<_Documento_TMP>,List<_Documento_TMP>> chunk(0)
				.reader(reader)
				.writer(writer)
				.build();
	}
	
	
	@Bean(name="cargaDocumentosJob")
	public Job job(CargaMasivaListener listener) {
		return jobbuilderFactory
				.get("cargaDocumentos_Job")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.start(this.cargaDocumentosStep(funcReader(),funcWritter()))
				.build();
	}
	

}
