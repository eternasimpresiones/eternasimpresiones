package org.gteperu.erp.everest.controller;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._DocumentoCpe_DetalleBean;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service._CompanyService;
import org.gteperu.erp.everest.service._Detalle_DocumentoService;
import org.gteperu.erp.everest.service._DocumentoService;
import org.gteperu.erp.everest.service._ClienteService;
import org.gteperu.erp.everest.utils.Constantes;
import org.gteperu.erp.everest.domain.Periodo;

@RestController
@RequestMapping(value = "/api/documento")
public class _Detalle_DocumentoRestController {
	@Resource(name = "detalledocumentoService")
	_Detalle_DocumentoService detalledocumentoService;
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/insertaDetalle_Documento", method = RequestMethod.POST)
	public ResponseWrapper insertaDetalle_Documento(@RequestBody @Validated _DocumentoCpe_DetalleBean documento_detalle, HttpServletRequest request)
			throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		try {
			
            Integer cat = 0;
            cat = detalledocumentoService.insertarDocumento_Detalle(documento_detalle);
            
            if (cat != null && cat > 0) {
                response.setEstado(Constantes.valTransaccionOk);
                response.setMsg(Constantes.msgTransaccionInsertarDocumentoDetalleOk);
            } else {
                response.setEstado(Constantes.valTransaccionErrornull);
                response.setMsg(Constantes.msgTransaccionInsertarDocumentoDetalleError);
                throw new Exception(Constantes.msgTransaccionError);
            }
    } catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" insertaDetalle_Documento. ERROR : "+e.getMessage());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/insertaDetalle_Documento",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),documento_detalle);
		}
		return response;
		
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/listarDetalle_Documento", method = RequestMethod.POST)
	public ResponseWrapper listarDetalle_Documento(@RequestBody @Validated _DocumentoCpe_DetalleBean documento_detalle, HttpServletRequest request) throws Exception {
			HttpSession session = request.getSession();
			List objtList = new ArrayList();
			ResponseWrapper obj = new ResponseWrapper();
		try {
				
					objtList = detalledocumentoService.listarDocumento_Detalle();
					if (objtList != null && objtList.size() > 0) {
						obj.setEstado(Constantes.valTransaccionOk);
						obj.setAaData(objtList);
					} else {
						obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
						obj.setEstado(Constantes.valTransaccionNoEncontro);
						obj.setAaData(objtList);
					}
				
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" listarDetalle_Documento. ERROR : "+e.getMessage());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/listarDetalle_Documento",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),documento_detalle);
		}
		return obj;
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	 @RequestMapping(value = "/actualizarDetalle_Documento", method = RequestMethod.POST)
		public ResponseWrapper actualizarDetalle_Documento(@RequestBody @Validated _DocumentoCpe_DetalleBean documento_detalle, HttpServletRequest request) throws Exception {
		    	ResponseWrapper response = new ResponseWrapper();
		    	String cod_emp;
		    try {
		    			Integer cat = 0;
		    			cat = detalledocumentoService.actualizarDocumento_Detalle(documento_detalle);
		    		if (cat != null && cat>0) {
		                response.setEstado(Constantes.valTransaccionOk);
		                response.setMsg(Constantes.msgTransaccionActualizarDocumentoDetalleOk);
		            } else {
		                response.setEstado(Constantes.valTransaccionErrornull);
		                response.setMsg(Constantes.msgTransaccionActualizarDocumentoDetalleError);
		                throw new Exception(Constantes.msgTransaccionError);
		            }
		    } catch (Exception e) {
		    	System.out.println(this.getClass().getSimpleName()+ " actualizarDetalle_Documento. ERROR : " + e.getMessage());
		    	throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/actualizarDetalle_Documento",
		 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),documento_detalle);
		        
		    }
		    return response;
		    
		}
	 
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/eliminarDetalle_Documento", method = RequestMethod.POST)
		public ResponseWrapper eliminarDetalle_Documento(@RequestBody @Validated _DocumentoCpe_DetalleBean documento_detalle, HttpServletRequest request) throws Exception {
		    			
				ResponseWrapper response = new ResponseWrapper();
		    try {
			            Integer cat = 0;
			            cat = detalledocumentoService.eliminarDocumento_Detalle(documento_detalle);
		               
		            if (cat != null && cat > 0) {
		                response.setEstado(Constantes.valTransaccionOk);
		                response.setMsg(Constantes.msgTransaccionEliminarDocumentoDetalleOk);
		            } else {
		                response.setEstado(Constantes.valTransaccionErrornull);
		                response.setMsg(Constantes.msgTransaccionEliminarDocumentoDetalleError);
		                throw new Exception(Constantes.msgTransaccionError);
		            }
		    } catch (Exception e) {
		    	System.out.println(this.getClass().getSimpleName()+ " eliminarDetalle_Documento. ERROR : " + e.getMessage());
		    	throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/eliminarDetalle_Documento",
		 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),documento_detalle);		        
		    }
		    return response;
		    
		}
}