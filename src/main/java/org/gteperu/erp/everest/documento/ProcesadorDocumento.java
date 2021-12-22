package org.gteperu.erp.everest.documento;

import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._Retencion;


public abstract class ProcesadorDocumento{
	
	private _DocumentoCpe documentocpe;
	private _Retencion retencion;
	
	protected _DocumentoCpe getDocumento() {
		return documentocpe;
	}
	
	protected void setDocumentoCpe(_DocumentoCpe doc) {
		this.documentocpe = doc;
	}
	
	protected void setRetencion(_Retencion retencion) {
		this.retencion = retencion;
	}
	
	protected _Retencion getRetencion() {
		return retencion;
	}
	
	protected abstract void initialize() throws Exception;

	protected abstract void crearDocumento(String estadoDocumento) throws Exception;
	
	protected abstract void crearDetalles() throws Exception;
	
	protected abstract void enviarDocSunat() throws Exception;
	
	protected abstract String getRutaDocEmpresaEmisora();
	
	protected abstract Integer procesarDocumento() throws Exception;

	protected abstract Integer procesarDocumentoBorrador() throws Exception;
	
	protected abstract Integer eliminarDocumentoBorrador() throws Exception;


}
