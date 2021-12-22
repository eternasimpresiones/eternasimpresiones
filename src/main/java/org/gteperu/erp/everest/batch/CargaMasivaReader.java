package org.gteperu.erp.everest.batch;

import java.util.List;

import org.gteperu.erp.everest.domain._Documento_TMP;
import org.gteperu.erp.everest.service._Documento_TMPService;
import org.gteperu.erp.everest.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class CargaMasivaReader implements ItemReader<List<_Documento_TMP>>, StepExecutionListener {

	//Elemento responsable de leer datos de una fuente  de datos(bd,fichero,etc)
	
	
	private static final Logger LOG = LoggerFactory.getLogger(CargaMasivaReader.class);
	_Documento_TMPService documento_TMPService;
	_Documento_TMP doc = new _Documento_TMP();
	public CargaMasivaReader(_Documento_TMPService documento_TMPService){
		this.documento_TMPService = documento_TMPService;
	}
	
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		//Se ejecuta antes del metodo read
			LOG.info("Read DocumentoTMP Inicializado");
	
	}

	@Override
	public List<_Documento_TMP> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		String line = "";
		List<_Documento_TMP> lsdocumentos = null;
		try{
			lsdocumentos = documento_TMPService.retornaLsDocumentoTMP();
			if(lsdocumentos.size()>0) {
				return lsdocumentos;				
			}else {
				lsdocumentos = null;
				return lsdocumentos;				

			}
			
		}catch(Exception e){
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/read => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/read" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw new RuntimeException();
		}
	}
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		//Se ejecuta despues del metodo read
		LOG.info("Carga masiva Reader ended.");
		return ExitStatus.COMPLETED;
	}

	

	
	
}
