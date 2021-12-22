package org.gteperu.erp.everest.batch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.gteperu.erp.everest.domain.Sunat_padron;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.utils.Constantes;
import org.gteperu.erp.everest.utils.ReadFileSunat;
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

public class Sunat_padronReaderChunk implements ItemReader<Sunat_padron>, StepExecutionListener {

	private static final Logger LOG = LoggerFactory.getLogger(Sunat_padronReaderChunk.class);
	
	BufferedReader br;
	
	private ReadFileSunat sunat = new ReadFileSunat();
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		try {
			ExecutionContext executionContext = stepExecution
					.getJobExecution()
					.getExecutionContext();
			String ruta_padron_txt =  executionContext.getString("ruta_padron_txt", "");
			br = new BufferedReader(new FileReader(ruta_padron_txt));
			//br = new BufferedReader(new FileReader("C:\\ProyectoDeCero\\sunat\\padron_reducido_ruc_prueba.txt"));
			LOG.info("Read File Inicializado");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public Sunat_padron read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		String line = "";
		Sunat_padron sunat_padron = null;
		try{
			line = br.readLine();
			if(line!=null){
				sunat_padron = sunat.readLine(line);
				if(sunat_padron.getId_sunat_padron()!=null){
					 return sunat_padron;
				 }else{
					 line = br.readLine();
					 sunat_padron = sunat.readLine(line);
					 return sunat_padron;
				 }
			}
			return sunat_padron;
			
		}catch(Exception e){
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/read => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/read" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw new RuntimeException();
		}
	}
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		try {
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		LOG.info("Sunat_padron Reader ended.");
		return ExitStatus.COMPLETED;
	}

	

	
	
}
