package org.gteperu.erp.everest.batch;

import java.util.ArrayList;
import java.util.List;

import org.gteperu.erp.everest.cpe.CPE;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._Retencion;
import org.gteperu.erp.everest.service.AuditoriaService;
import org.gteperu.erp.everest.service.Auditoria_SunatService;
import org.gteperu.erp.everest.service.Codigo_qrService;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service.SerieService;
import org.gteperu.erp.everest.service.Tipo_Operacion_SunatService;
import org.gteperu.erp.everest.service._ClienteService;
import org.gteperu.erp.everest.service._CompanyService;
import org.gteperu.erp.everest.service._Detalle_DocumentoService;
import org.gteperu.erp.everest.service._Detalle_RetencionService;
import org.gteperu.erp.everest.service._DocumentoService;
import org.gteperu.erp.everest.service._RetencionService;
import org.gteperu.erp.everest.service._SecuenciaService;
import org.gteperu.erp.everest.service._UbigeoService;
import org.gteperu.erp.everest.threads.ReenvioDocSunatThread;
import org.gteperu.erp.everest.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class Sunat_reenvio_documentosTaskletBatch implements Tasklet,StepExecutionListener{

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

	
	private static final Logger LOG = LoggerFactory.getLogger(Sunat_reenvio_documentosTaskletBatch.class);



	public Sunat_reenvio_documentosTaskletBatch(CPE cpeResource, _DocumentoService documentoService,
			_RetencionService retencionService, _CompanyService companyService, _ClienteService clienteService,
			ParametrosService parametrosService, Tipo_Operacion_SunatService tipoOperacionSunatService,
			Auditoria_SunatService auditoria_SunatService, Codigo_qrService codigoService) {
		super();
		this.cpeResource = cpeResource;
		this.documentoService = documentoService;
		this.retencionService = retencionService;
		this.companyService = companyService;
		this.clienteService = clienteService;
		this.parametrosService = parametrosService;
		this.tipoOperacionSunatService = tipoOperacionSunatService;
		this.auditoria_SunatService = auditoria_SunatService;
		this.codigoService = codigoService;
	}


	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("ENTRA");
		List<_DocumentoCpe> lsdocumentos = new ArrayList<_DocumentoCpe>();
		List<_Retencion> lsretencion = new ArrayList<_Retencion>();

		_DocumentoCpe doc = new _DocumentoCpe();
		_Retencion retencion = new _Retencion();
		doc.setEstado(Integer.valueOf(Constantes.estadoSinEnvio));
		lsdocumentos = documentoService.listarDocumentoxEstado(doc);
		doc.setEstado(Integer.valueOf(Constantes.estadoRechazadoParcial));
		lsdocumentos.addAll(documentoService.listarDocumentoxEstado(doc));
		doc.setEstado(Integer.valueOf(Constantes.estadoSinRespuesta));
		lsdocumentos.addAll(documentoService.listarDocumentoxEstado(doc));

		retencion.setEstado(Integer.valueOf(Constantes.estadoSinEnvio));
		lsretencion = retencionService.lsRetencionOPercepcionxEstado(retencion);
		retencion.setEstado(Integer.valueOf(Constantes.estadoRechazadoParcial));
		lsretencion.addAll(retencionService.lsRetencionOPercepcionxEstado(retencion));
		retencion.setEstado(Integer.valueOf(Constantes.estadoSinRespuesta));
		lsretencion.addAll(retencionService.lsRetencionOPercepcionxEstado(retencion));

		for(_DocumentoCpe documento: lsdocumentos) {
			ReenvioDocSunatThread hilo = new ReenvioDocSunatThread(cpeResource,tipoOperacionSunatService,auditoria_SunatService,
					codigoService,documentoService,retencionService,companyService,clienteService,parametrosService);
			hilo.setDocumento(documento);
			hilo.start();
		}
		
		for(_Retencion ret : lsretencion) {
			ReenvioDocSunatThread hilo = new ReenvioDocSunatThread(cpeResource,tipoOperacionSunatService,auditoria_SunatService,
					codigoService,documentoService,retencionService,companyService,clienteService,parametrosService);
			hilo.setRetencionDoc(ret);
			hilo.start();
		}
		
		return null;
	}

	
	@Override
	public void beforeStep(StepExecution arg0) {
		
	}



	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return ExitStatus.COMPLETED;
	}

}
