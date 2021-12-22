package org.gteperu.erp.everest.service;

import org.gteperu.erp.everest.domain.Modulo;
import org.gteperu.erp.everest.domain.Pagina;
import org.gteperu.erp.everest.domain.Perfiles;
import org.gteperu.erp.everest.domain.Sutipodocumento;

import java.util.List;

public interface SutipodocumentoService {
	
	 public List<Sutipodocumento> retornaSutipodocumentoPorEstado(Sutipodocumento sutipodocumento);
	 
	 public List<Sutipodocumento> comboSutipodocumentoidentidad();
	    public List<Sutipodocumento> listarDocumentosPublicos(Sutipodocumento sutipodocumento);

}