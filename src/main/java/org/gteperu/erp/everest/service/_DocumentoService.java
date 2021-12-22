package org.gteperu.erp.everest.service;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.gteperu.erp.everest.domain.InternalWrapper;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._DocumentoCpe_Excel;
import org.springframework.web.multipart.MultipartFile;

public interface _DocumentoService {

	public List<_DocumentoCpe> listarDocumento(_DocumentoCpe documento);

	public List<_DocumentoCpe> listarDocumentoPublico(_DocumentoCpe doc);

	public List<_DocumentoCpe> listarDocumentoxEstado(_DocumentoCpe documento);

	public Integer insertarDocumento(_DocumentoCpe documento);

	public Integer actualizarDocumento(_DocumentoCpe id_documento);

	public Integer actualizarDocumentoEstadoRechazado(_DocumentoCpe id_documento);

	public Integer eliminarDocumento(_DocumentoCpe id_documento);

	public _DocumentoCpe retornaDocumentoId(_DocumentoCpe documento);

	public _DocumentoCpe retornaDocumentoCPEXML(_DocumentoCpe documento);

	public String retornaultimonrocomprobante(_DocumentoCpe id_documento);

	public _DocumentoCpe retornaDocumentoPorId(_DocumentoCpe id_documento);

	public _DocumentoCpe retornaDocumentoPorSerieNum(_DocumentoCpe id_documento);

	public List<_DocumentoCpe> retornalsNroDocumento(_DocumentoCpe id_documento);

	public Integer ActualizarRutaArchivo(_DocumentoCpe doc);

	public _DocumentoCpe RetornaRutaArchivo(_DocumentoCpe doc);

	public _DocumentoCpe retornaDocumentoPorSerieNumIdEmpresa(_DocumentoCpe id_documento);

	public List<_DocumentoCpe> retornalsNroDocumentoEstado(_DocumentoCpe id_documento);

	public List<_DocumentoCpe> retornalsSerieComprobateUnico(_DocumentoCpe id_documento);

	public List<_DocumentoCpe> retornalsNroDocumentoEstadoTipoDocu(_DocumentoCpe id_documento);

	public Integer cantidadDocumentos(_DocumentoCpe documento);

	public List<_DocumentoCpe> listarDocumentoExcel(_DocumentoCpe doc);

	public List<_DocumentoCpe> listarDocumentoExcelAll(_DocumentoCpe doc);

	public Boolean validarDias(_DocumentoCpe id_documento, String diasLimite);

	public List<_DocumentoCpe> retornaPorTipoDocumento(_DocumentoCpe docu);

	public List<_DocumentoCpe> retornaPorSerieComprobante(_DocumentoCpe docu);

}
