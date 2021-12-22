package org.gteperu.erp.everest.utils;

import org.gteperu.erp.everest.domain.Sunat_padron;

public class ReadFileSunat {
	
	public Sunat_padron readLine(String content) {
		 String[] line = content.split("\\|");
	        if (line == null){
	        	return null;
	        }
	        
	        if (line[0].equals("RUC")){
	        	return new Sunat_padron();
	        }
	          
	        return new Sunat_padron(
	          line[0],
	          line[0],
	          content
	        );
	}
	
}
