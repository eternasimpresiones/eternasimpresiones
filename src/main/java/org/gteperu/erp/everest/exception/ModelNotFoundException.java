package org.gteperu.erp.everest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ModelNotFoundException extends RuntimeException {

	public ModelNotFoundException(String mensaje) {
		super(mensaje);
		
		int a=0;
		System.out.print("a "+a);
	}
	
	 
	
}
