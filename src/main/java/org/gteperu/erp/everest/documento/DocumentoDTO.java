package org.gteperu.erp.everest.documento;

import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._Retencion;

public class DocumentoDTO {
	
	private _DocumentoCpe documentoCpe;
	private _Retencion retencion;
	
	public _DocumentoCpe getDocumentoCpe() {
		return documentoCpe;
	}
	public void setDocumentoCpe(_DocumentoCpe documentoCpe) {
		this.documentoCpe = documentoCpe;
	}
	public _Retencion getRetencion() {
		return retencion;
	}
	public void setRetencion(_Retencion retencion) {
		this.retencion = retencion;
	}	
}
