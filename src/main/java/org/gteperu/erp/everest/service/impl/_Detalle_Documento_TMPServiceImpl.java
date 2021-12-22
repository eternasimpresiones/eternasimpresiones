package org.gteperu.erp.everest.service.impl;

import java.util.Date;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._Detalle_Documento_TMP;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._Documento_TMP;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.domain._ValidacionTMP;
import org.gteperu.erp.everest.mappers.DetalleDocumentoTMPMapper;
import org.gteperu.erp.everest.mappers.DocumentoTMPMapper;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service._CompanyService;
import org.gteperu.erp.everest.service._Detalle_Documento_TMPService;
import org.gteperu.erp.everest.service._Documento_TMPService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("documentoTMPService")
public class _Detalle_Documento_TMPServiceImpl implements _Detalle_Documento_TMPService {
 	
    @Resource(name = "detalleDocumentoTMPMapper")
    DetalleDocumentoTMPMapper detalleDocumentoTMPMapper;
    
    @Override
    public Integer insertarDetalleDocumentoTMP(_Detalle_Documento_TMP documento) {
    		Integer auxiliar = 0;
		try {
			auxiliar = detalleDocumentoTMPMapper.insertaDetalleDocumentoTMP(documento);
		} catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/insertarDetalleDocumentoTMP => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/insertarDetalleDocumentoTMP" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return auxiliar;
    }
}