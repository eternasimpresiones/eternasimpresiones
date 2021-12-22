package org.gteperu.erp.everest.controller;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._ModalidadTrasladoCodigoSunat;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.domain._Producto;
import org.gteperu.erp.everest.domain._ProductoCodigoSunat;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service._CompanyService;
import org.gteperu.erp.everest.service._ModalidadTraslado_Cod_SunatService;
import org.gteperu.erp.everest.service._Prod_Cod_SunatService;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service._ClienteService;
import org.gteperu.erp.everest.utils.Constantes;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.gteperu.erp.everest.domain.Periodo;

@RestController
@RequestMapping(value = "/api/modalidad_tras_cod_sunat")
public class _ModalidadTraslado_Cod_SunatRestController {
	 @Resource(name = "modalidadTraslado_Cod_SunatService")
	 _ModalidadTraslado_Cod_SunatService modalidadTraslado_Cod_SunatService;
	
	 /*
	  * retorna el codigo sunat de la modalidad de traslado
	  */
	 @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	 @RequestMapping(value = "/retornarModalidadCodigoSunat", method = RequestMethod.POST)
	 public ResponseWrapper retornarModalidadCodigoSunat(@RequestBody @Validated _ModalidadTrasladoCodigoSunat ModalidadTrasladoCodigoSunat, HttpServletRequest request) throws Exception {
	     
		 HttpSession session = request.getSession();
		    List objtList = new ArrayList();
		    ResponseWrapper obj = new ResponseWrapper();
		    try {
		                 
		                    objtList = modalidadTraslado_Cod_SunatService.retornarModalidadTrasladoCodigoSunat(ModalidadTrasladoCodigoSunat);
		                    if (objtList != null && objtList.size() > 0) {
		                        obj.setMsg(Constantes.msgTransaccionOk);
		                        obj.setEstado(Constantes.valTransaccionOk);
		                        obj.setAaData(objtList);
		                    } else {
		                        obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
		                        obj.setEstado(Constantes.valTransaccionNoEncontro);
		                        obj.setAaData(objtList);
		                    }
		               
		      } catch (Exception e) {
		    	  throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornarModalidadCodigoSunat",
			 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
			 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),ModalidadTrasladoCodigoSunat);
		    }
		    return obj;
		    
	 }
	
}