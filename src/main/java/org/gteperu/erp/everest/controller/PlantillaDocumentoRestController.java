package org.gteperu.erp.everest.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gteperu.erp.everest.domain.Plantilla_documento;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service.PlantillaDocumentoService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plantillaDocumento")
public class PlantillaDocumentoRestController {

	@Autowired
	PlantillaDocumentoService plantillaDocService;
	
	/*Erick Pimentel Lopez  20/07/20
	 * 
	 * REQESP PD1-Service registra nueva plantilla documento con detalle en la empresa seleccionado
	 * 
	 * PlantillaDocumento			
			iid_empresa		integer
			iid_cliente ,		integer
			scod_moneda		integer
			sobservacion		string
			scod_tipo_documento		string
			snonmbre		string
			stipo_operacion 		string
		lsDetallePlantillaDocumento		List<DetallePlantillaDocumento>
				iitem ,	integer
				dcantidad	double
				dprecio	double
				dimporte	double
				scodigo	string
				sdescripcion	string
				scod_tipo_operacion	string
				sunidad_medida	string
	 * 
	 * */
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/registrarNuevaPlantilla", method = RequestMethod.POST)
	public ResponseWrapper RegistrarNuevaPlantillaDocumento(@RequestBody @Validated Plantilla_documento objPlantilla, HttpServletRequest request)
			throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		Integer valor = 0;
		try {
			valor=plantillaDocService.insertarPlantillaDocumento(objPlantilla);
			if (valor != 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionInsertarPlantillaDocumentoOk);
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionInsertarPlantillaDocumentoError);
				throw new Exception(Constantes.msgTransaccionInsertarPlantillaDocumentoError);
			}
			
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " registrarNuevaPlantilla. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/registrarNuevaPlantilla",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),objPlantilla);
		} 
		return response;
	}
	/* Erick Pimentel Lopez  20/07/20
	 * 
	 * REQESP PD2-Service actualiza plantilla documento seleccionada con detalle en la empresa seleccionado
	 * 
	 * PlantillaDocumento			PlantillaDocumento
			iid_pantilla_documento		integer
			iid_empresa		integer
			iid_cliente ,		integer
			scod_moneda		integer
			sobservacion		string
			scod_tipo_documento		string
			stipo_operacion		string
			snonmbre		string
			
			lsDetallePlantillaDocumento		List<DetallePlantillaDocumento>
					iitem ,	integer
					dcantidad	double
					dprecio	double
					dimporte	double
					scodigo	string
					sdescripcion	string
					scod_tipo_operacion	string
					iid_pantilla_documento	integer
	 * 
	 * */
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/actualizarPlantillaDocumento", method = RequestMethod.POST)
	public ResponseWrapper ActualizarPlantillaDocumento(@RequestBody @Validated Plantilla_documento objPlantilla, HttpServletRequest request)
			throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		Integer valor = 0;
		try {
			valor=plantillaDocService.actualizarPlantillaDocumento(objPlantilla);
			if (valor != 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionActualizarPlantillaDocumentoOk);
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionInsertarClienteError);
				throw new Exception(Constantes.msgTransaccionActualizarPlantillaDocumentoError);
			}
			
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " actualizarPlantillaDocumento. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/actualizarPlantillaDocumento",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),objPlantilla);
		} 
		return response;
	}
	/*Erick Pimentel Lopez 20/07/20
	 * 
	 * REQESP PD3-Elimina Plantilla documentos mas sus detalle anidados
	 * 
	 * PlantillaDocumento			PlantillaDocumento	
			iid_pantilla_documento		integer	Obl.
	 * 
	 * */
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/eliminarPlantillaDocumento", method = RequestMethod.POST)
	public ResponseWrapper EliminarPlantillaDocumento(@RequestBody @Validated Plantilla_documento objPlantilla, HttpServletRequest request)
			throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		Integer valor = 0;
		try {
			valor=plantillaDocService.eliminarPlantillaDocumento(objPlantilla.getIid_plantilla_documento());
			if (valor != 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionEliminarPlantillaDocumentoOk);
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionEliminarPlantillaDocumentoError);
				throw new Exception(Constantes.msgTransaccionEliminarPlantillaDocumentoError);
			}
			
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " eliminarPlantillaDocumento. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/eliminarPlantillaDocumento",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),objPlantilla);
		} 
		return response;
	}
	/*Erick Pimentel Lopez 20/07/20
	 * 
	 * REQESP PD4 - Lista Plantillas y sus detalle segun la empresa con respectiva paginacion 
	 * 
	 * PlantillaDocumento		PlantillaDocumento
			iid_empresa	integer
			iid_cliente ,	integer
			scod_tipo_documento	string
			offset	integer
			limit	integer
	 * 
	 * 
	 * */
	
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/listarPlantilla", method = RequestMethod.POST)
	public ResponseWrapper ListarPlantilla(@RequestBody @Validated Plantilla_documento objPlantilla, HttpServletRequest request)
			throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		List objtList = new ArrayList();
		try {
			objtList=plantillaDocService.listarPlantillas(objPlantilla);
			if (objtList.size()>0 && objtList!=null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionListarPlantillaDocumentoOk);
				response.setAaData(objtList);
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionFiltroPlantillaNoEncontrada);
				response.setAaData(objtList);
			}
			
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listarPlantilla. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/listarPlantilla",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),objPlantilla);
		} 
		return response;
	}
	
	/*Erick Pimentel Lopez 20/07/20
	 * 
	 * REQESP PD5 - Lista Todas las Plantillas documentos registrados en la empresa
	 * 
	 * PlantillaDocumento		PlantillaDocumento
				iid_empresa	integer
				scod_tipo_documento	string
				iid_cliente ,	integer
	 * 
	 * */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/listarPlantillaTodas", method = RequestMethod.POST)
	public ResponseWrapper ListarPlantillasTodas(@RequestBody @Validated Plantilla_documento objPlantilla, HttpServletRequest request)
			throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		List objtList = new ArrayList();
		try {
			objtList=plantillaDocService.listarPlantillasTodas(objPlantilla);
			if (objtList.size()>0 && objtList!=null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionListarPlantillaDocumentoOk);
				response.setAaData(objtList);
			} else {
				response.setEstado(Constantes.valTransaccionNoEncontro);
				response.setMsg(Constantes.msgTransaccionFiltroPlantillaNoEncontrada);
				response.setAaData(objtList);
				//throw new Exception(Constantes.msgTransaccionFiltroPlantillaNoEncontrada);
			}
			
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listarPlantillaTodas. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/listarPlantillaTodas",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),objPlantilla);
		} 
		return response;
	}
	
}
