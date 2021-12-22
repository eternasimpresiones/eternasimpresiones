package org.gteperu.erp.everest.service;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.gteperu.erp.everest.domain._Documento_TMP;
import org.gteperu.erp.everest.domain._ValidacionTMP;
import org.springframework.web.multipart.MultipartFile;


public interface _Documento_TMPService {

	public List<_Documento_TMP> armaListaDocumentosconExcel(XSSFWorkbook wb,Integer id);
	public _ValidacionTMP validarFacturaBoletaTMP(_Documento_TMP doc);
	public _ValidacionTMP validarNotaCreditoTMP(_Documento_TMP doc);
	public XSSFWorkbook armaWorkBook(MultipartFile[] files,Integer id);
	public Integer insertarDocumentoTMP(_Documento_TMP doc);
	public List<_Documento_TMP> retornaLsDocumentoTMP();
	public Integer eliminaDocumentoTMP(_Documento_TMP doc);
	public Integer actualizaMsgRegistro(_Documento_TMP doc);

	


}
