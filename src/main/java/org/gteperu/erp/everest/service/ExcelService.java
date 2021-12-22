package org.gteperu.erp.everest.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._DocumentoCpe;

public interface ExcelService {
	
 
	public XSSFWorkbook excelReporteVenta(String appPath, List<_DocumentoCpe> lista,_Company company) throws IOException;
	
}