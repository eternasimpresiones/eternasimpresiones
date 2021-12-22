package org.gteperu.erp.everest.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.domain._Producto;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service.ExcelService;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service._CompanyService;
import org.gteperu.erp.everest.service._DocumentoService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="api/excel")
public class ExcelRestController {
	
	@Resource(name="parametrosService")
	ParametrosService parametrosService;
	
	@Resource(name="companyService")
	_CompanyService companyService;

	@Resource(name = "documentoService")
	_DocumentoService documentoService;

	@Resource(name = "excelService")
	ExcelService excelService;
	
	
/*
 * dsecarga el excel base de regisrro masivo de productos
 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/ExcelModeloBaseProducto", method = RequestMethod.POST)				 
	 public @ResponseBody byte[] ExcelModeloBaseProducto(@RequestBody @Validated _Producto producto,  HttpServletRequest request) throws Exception {
		byte[] data = null;
		try {
			_Parametros parametro = new _Parametros();
			List<_Parametros> lsPar = new ArrayList<>();
 			
 			String separador = System.getProperty("file.separator");
			
			parametro.setGrupo(Constantes.codigoGrupoIniParametros);
			parametro.setId_empresa(Constantes.parametroIdEmpresaGlobal);
			lsPar=parametrosService.retornaObjParametrosPorGrupoe(parametro);
			
			String rutamatriz = "",rutaexcel="";
 			String excel_base = Constantes.nombreExcelBaseProducto;
			
			for (int i = 0; i < lsPar.size(); i++) {
				if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
					rutamatriz=lsPar.get(i).getValor();
 				}
				if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaExcel)) {
					rutaexcel=lsPar.get(i).getValor();
 				}
		 	}
			
	        String appPath2 = rutamatriz + separador + rutaexcel+separador+excel_base;
	        
	        Path path = Paths.get(appPath2);
	        data = Files.readAllBytes(path);
	        return data;
	     } catch (IOException e) {
	         System.out.println("Error en el Excel" + e.getMessage());
	         throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/ExcelModeloBaseProducto",
		  				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		  				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),producto);
	     }
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/ExcelModeloBaseCliente", method = RequestMethod.POST)				 
	 public @ResponseBody byte[] ExcelModeloBaseCliente(@RequestBody @Validated _Clientes cliente,  HttpServletRequest request) throws Exception {
		byte[] data = null;
		try {
			_Parametros parametro = new _Parametros();
			List<_Parametros> lsPar = new ArrayList<>();
			String separador=System.getProperty("file.separator");
			 parametro.setGrupo(Constantes.codigoGrupoIniParametros);
			parametro.setId_empresa(Constantes.parametroIdEmpresaGlobal);
			lsPar=parametrosService.retornaObjParametrosPorGrupoe(parametro);
			
			String rutamatriz = "",rutaexcel="";
 			String excel_base = Constantes.nombreExcelBaseCliente;
			
			for (int i = 0; i < lsPar.size(); i++) {
				if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
					rutamatriz=lsPar.get(i).getValor();
 				}
				if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaExcel)) {
					rutaexcel=lsPar.get(i).getValor();
 				}
		 	}
			
	        String appPath2 = rutamatriz+ separador +rutaexcel+separador+ excel_base;
	        
	        Path path = Paths.get(appPath2);
	        data = Files.readAllBytes(path);
	        return data;
	     } catch (IOException e) {
	    	 System.out.println("Error en el Excel" + e.getMessage());
	         throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/ExcelModeloBaseCliente",
		  				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		  				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),cliente);
	     }
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/ExcelAyudaDocumentosFac", method = RequestMethod.GET)				 
	 public @ResponseBody byte[] ExcelAyudaDocumentosFac(HttpServletRequest request) throws Exception {
		byte[] data = null;
		try {
			_Parametros parametro = new _Parametros();
			List<_Parametros> lsPar = new ArrayList<>();
			String separador=System.getProperty("file.separator");
			
			parametro.setGrupo(Constantes.codigoGrupoIniParametros);
			parametro.setId_empresa(Constantes.parametroIdEmpresaGlobal);
			lsPar=parametrosService.retornaObjParametrosPorGrupoe(parametro);
			
			String rutamatriz = "",rutaexcel="";
 			String excel_base = Constantes.nombreExcelAyudaDocumentoFac;
			
			for (int i = 0; i < lsPar.size(); i++) {
				if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
					rutamatriz=lsPar.get(i).getValor();
 				}
				if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaExcel)) {
					rutaexcel=lsPar.get(i).getValor();
 				}
		 	}
			
	        String appPath2 = rutamatriz + separador +rutaexcel+separador+ excel_base;
	        
	        Path path = Paths.get(appPath2);
	        data = Files.readAllBytes(path);
	        return data;
	     } catch (IOException e) {
	    	 System.out.println("Error en el Excel" + e.getMessage());
	         throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/ExcelModeloBaseDocumentosFac",
		  				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		  				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),null);
	     }
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/ExcelPlantillaDocumentosFac", method = RequestMethod.GET)				 
	 public @ResponseBody byte[] ExcelPlantillaDocumentosFac(HttpServletRequest request) throws Exception {
		byte[] data = null;
		try {
			_Parametros parametro = new _Parametros();
			List<_Parametros> lsPar = new ArrayList<>();
			String separador=System.getProperty("file.separator");
		 	parametro.setGrupo(Constantes.codigoGrupoIniParametros);
			parametro.setId_empresa(Constantes.parametroIdEmpresaGlobal);
			lsPar=parametrosService.retornaObjParametrosPorGrupoe(parametro);
			
			String rutamatriz = "",rutaexcel="";
 			String excel_base = Constantes.plantillaExcelDocumentoFac;
			
			for (int i = 0; i < lsPar.size(); i++) {
				if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
					rutamatriz=lsPar.get(i).getValor();
 				}
				if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaExcel)) {
					rutaexcel=lsPar.get(i).getValor();
 				}
		 	}
			
	        String appPath2 = rutamatriz + separador+rutaexcel+separador+ excel_base;
	        
	        Path path = Paths.get(appPath2);
	        data = Files.readAllBytes(path);
	        return data;
	     } catch (IOException e) {
	    	 System.out.println("Error en el Excel" + e.getMessage());
	         throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/ExcelPlantillaDocumentosFac",
		  				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		  				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),null);
	     }
	}
	
	
	@RequestMapping(value = "/excelreporteventa", method = RequestMethod.POST)
	public @ResponseBody byte[] ExcelReporteVenta(@RequestBody _DocumentoCpe documentos, HttpServletRequest request)
			throws ParseException, IOException {
		byte[] data = null;
		List<_DocumentoCpe> lsExcel = new ArrayList();
		_Company company = new _Company();
		try {
		 
			
			lsExcel = documentoService.listarDocumentoExcelAll(documentos);
			company.setId_empresa(documentos.getId_empresa());
			company=companyService.retornaEmpresaPorIdempresa(company);
             
			
			String separador = System.getProperty("file.separator");
			_Parametros parametro = new _Parametros();
			List<_Parametros> lsPar = new ArrayList<>();
 		 	parametro.setGrupo(Constantes.codigoGrupoIniParametros);
			parametro.setId_empresa(Constantes.parametroIdEmpresaGlobal);
			lsPar=parametrosService.retornaObjParametrosPorGrupoe(parametro);
			
			String rutamatriz = "",rutaexcel="";
 			String excel_base = Constantes.reporteExcelDocumentoFac;
 			String excel_base_salida = Constantes.reporteExcelDocumentoFacOutput;

			for (int i = 0; i < lsPar.size(); i++) {
				if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
					rutamatriz=lsPar.get(i).getValor();
 				}
				if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaExcel)) {
					rutaexcel=lsPar.get(i).getValor();
 				}
		 	}
			
			
	        String appPath = rutamatriz + separador+rutaexcel+separador+ excel_base;

	        
	        
			String pathExcelSalida = rutamatriz + separador+rutaexcel+separador+ excel_base_salida;


			XSSFWorkbook wb = excelService.excelReporteVenta(appPath, lsExcel,company);

			File excelSalida = new File(pathExcelSalida);
			FileOutputStream output_file = new FileOutputStream(excelSalida);
			wb.write(output_file);
			output_file.close();
			Path path = Paths.get(pathExcelSalida);
			data = Files.readAllBytes(path);
			excelSalida.delete();
		} catch (IOException e) {
			System.out.println("" + e.getMessage());
			throw new IOException("Error en el Excel");
		}
		return data;
	}
}
