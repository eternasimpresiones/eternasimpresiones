/*
 * @(#)ParametrosRestController.java 1.0 15/08/2016
 *
 * Copyright (c) 2015-2016 Global Technology Experts, Inc.
 * Cll. Mariano de los Santos #197, 3er Piso Of.6, San Isidro, Lima, PERU
 * All rights reserved.
 *
 * Se inició la documentación de los servicios
 */
package org.gteperu.erp.everest.controller;

import org.gteperu.erp.everest.domain.Auditoria;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.exception.ExceptionResponse;

import java.util.List;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.service.AuditoriaService;
import org.gteperu.erp.everest.utils.Constantes;

import javax.annotation.Resource;

/**
 * Rest Controller que permite la ejecución de los Servicios Web para los
 * Parámetros de la Aplicación, tales como: insertar, actualizar, eliminar,
 * buscar por ID de Parámetro, retornar por Grupo y Estado, y filtrar por
 * descripción del Parámetro.
 *
 */
@RestController
@RequestMapping(value = "/api/parametros")
public class ParametrosRestController {


    @Resource(name = "parametrosService")
    ParametrosService parametrosService;
    @Resource(name = "auditoriaService")
    AuditoriaService auditoriaService;
    Auditoria auditoria;
    Funciones funciones = new Funciones();
    
    
    
    /*
     * Registro parametros globales
     * Parametros 
     * String	descripcion
     * String	valor
     *  
     * 
     * RO
     * 
     * 
     * 
     * 22-05-2019
     */
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    @RequestMapping(value = "/insertarGlobal", method = RequestMethod.POST)
    public ResponseWrapper insertarGlobal(@RequestBody @Validated _Parametros parametros, HttpServletRequest request) throws Exception {
        
     ResponseWrapper response = new ResponseWrapper();
   	 List<_Parametros> lsParam= new ArrayList<_Parametros>();

        try {
        	Integer cat = 0;
        		if(parametros.getTipo_cuenta()){ // parametro de tipo cuenta 
        			parametros.setGrupo(Constantes.GrpCuentas);
        		}else	{
//                Constantes.grupoGlobales
                //Parametros par = new Parametros();
                 	 parametros.setGrupo(Constantes.grupoGlobales);
                    lsParam = parametrosService.listarUltimoRegistroGlobal(parametros);
                    if(lsParam.size()>0)
                    {   String string = lsParam.get(0).getCodigo();
                    String[] parts = string.split("-");
                     int aux = Integer.parseInt(parts[1]) + 1;
                     parametros.setCodigo(parts[0]+'-'+aux);
                    } else{
                    	 parametros.setCodigo(Constantes.codigoGlobal+"-1");
                    }
        		}
                    
                        parametros.setEstado(1);
                        parametros.setId_empresa(0);
                    cat = parametrosService.insertaRegistroGlobal(parametros);
                    
        		
                if (cat != null && cat > 0) {
                    response.setEstado(Constantes.valTransaccionOk);
                    response.setMsg(Constantes.msgGrupoParametros);
                    //System.out.println(this.getClass().getName() + "/insertarGlobal");
                    //System.out.println(this.getClass().getSimpleName() + "/insertarGlobal");
                } else {
                    response.setEstado(Constantes.valTransaccionErrornull);
                    response.setMsg(Constantes.msgGrupoParametrosErr);
                    throw new Exception(Constantes.msgTransaccionError);
                }
        } catch (Exception e) {
       	 //System.out.println(this.getClass().getName() + "/insertarGlobal" + e.getMessage());
       	 System.out.println(this.getClass().getSimpleName() + "/insertarGlobal" + "linea nro:" +  e.getStackTrace()[0].getLineNumber());
       	 throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/insertarGlobal",
  				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
  				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),parametros);
        }
        return response;
        
    }
    
    /*
     * Registro parametros de una empresa
     * Parametros 
     * 
     * String	nombre
     * String	valor
     * String	descripcion
     * Integer	id_empresa
     * Boolean	tipo_cuenta
     * String  codigo ( solo cuando el parametro sea de tipo cuenta)
     * 
      * RO
     * 22-05-2019
     * 
     * SM
     * 20-06-2019
     */
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    @RequestMapping(value = "/insertarEmpresa", method = RequestMethod.POST)
    public ResponseWrapper insertarEmpresa(@RequestBody @Validated _Parametros parametros, HttpServletRequest request) throws Exception {
        
        ResponseWrapper response = new ResponseWrapper();
   	 List<_Parametros> lsParam= new ArrayList<_Parametros>();

        try {
                Integer cat = 0;
//                Constantes.grupoGlobales
                //Parametros par = new Parametros();
                
                if(parametros.getTipo_cuenta()){ // parametro de tipo cuenta 
                		  parametros.setGrupo(Constantes.GrpCuentas);
                		  
                 	}else{
                			parametros.setGrupo(Constantes.grupoEmpresa);
		                    lsParam = parametrosService.listarUltimoRegistroEmpresa(parametros);
		                    
		                    if(lsParam.size()>0){   
		                    	String string = lsParam.get(0).getCodigo();
		                    	String[] parts = string.split("-");
		                    	int aux = Integer.parseInt(parts[1]) + 1;
		                    	parametros.setCodigo(parts[0]+'-'+aux);
		                    } else{
		                    	parametros.setCodigo(Constantes.codigoEmpresa+"-1");
		                    }
                 		}
                    
                      parametros.setEstado(1);
                    cat = parametrosService.insertaRegistroEmpresa(parametros);
                    
               
                if (cat != null && cat > 0) {
                    response.setEstado(Constantes.valTransaccionOk);
                    response.setMsg(Constantes.msgGrupoParametros);
                } else {
                    response.setEstado(Constantes.valTransaccionErrornull);
                    response.setMsg(Constantes.msgGrupoParametrosErr);
                    throw new Exception(Constantes.msgTransaccionError);
                }
        } catch (Exception e) {
        	 throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/insertarEmpresa",
       				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
       				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),parametros);
        }
        return response;
        
    }
    
    /*
     * Actualizar parametros de una empresa
     * 
     * id_parametros
     * 
     * RO 
     * 11/07/2019
     */
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    @RequestMapping(value = "/updateparamEmpGlb", method = RequestMethod.POST)
    public ResponseWrapper updateparamEmpresa(@RequestBody @Validated _Parametros parametros, HttpServletRequest request) throws Exception {
    	ResponseWrapper response = new ResponseWrapper();
 	   
	    try {
            Integer cat = 0;
            if (parametros.getId_parametros()!=null){
	            cat = parametrosService.actualizarParamEmpGlb(parametros);	            	
            }
            if (cat != null && cat>0) {
                response.setEstado(Constantes.valTransaccionOk);
              //  response.setMsg(Constantes.msgTransaccionAlmacenActualizarOK);
            } else {
                response.setEstado(Constantes.valTransaccionErrornull);
              //  response.setMsg(Constantes.msgTransaccionAlmacenActualizarError);
                throw new Exception(Constantes.msgTransaccionError);
            }
	    } catch (Exception e) {
	    	System.out.println(this.getClass().getSimpleName()+ " updateparamEmpGlb. ERROR : " + e.getMessage());
	    	System.out.println(this.getClass().getSimpleName() + "/updateparamEmpGlb" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
	    	throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/updateparamEmpGlb",
    			e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
   				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),parametros);
        }
        return response;
        

    }

    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/retornalsPorEmpresa", method = RequestMethod.POST)
	public ResponseWrapper retornalsPorEmpresa(@RequestBody @Validated _Parametros parametros,
			HttpServletRequest request) throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		List lsAlmc = new ArrayList();
		Constantes co = new Constantes();

		try {
			lsAlmc = parametrosService.retornalsPorEmpresa(parametros);

			if (lsAlmc != null && lsAlmc.size() > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lsAlmc);
			} else {
				response.setEstado(Constantes.valTransaccionNoEncontro);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " retornalsPorEmpresa. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/retornalsPorEmpresa" + "Linea nro : "+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornalsPorEmpresa",
	    			e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	   				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),parametros);
		}
		return response;
		

	}

	/*
	 * Listar Parametros Globales
	 * 
	 * Se envian parametros nulos retorna todos los atributos en una lista y que
	 * pertenezcan al grupo PARAMGLOBALES
	 * 
	 * Sin Parametros
	 * 
	 * 
	 * PMC
	 * 
	 * 04/06/2019
	 * 
	 */
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/retornalsGlobales", method = RequestMethod.POST)
	public ResponseWrapper retornalsGlobales(@RequestBody @Validated _Parametros parametros, HttpServletRequest request) throws Exception {
			
			ResponseWrapper response = new ResponseWrapper();
			List lsAlmc = new ArrayList();
			Constantes co = new Constantes();
	       
	                   try {
	                	   
	                	//   parametros.setGrupo(co.grupoGlobales);
	   		                parametros.setId_empresa(0);
	                		   lsAlmc = parametrosService.retornalsGlobales(parametros);
			                
	                   	
	                   	
	                   	if (lsAlmc != null && lsAlmc.size() > 0) {
	                           response.setEstado(Constantes.valTransaccionOk);
	                           response.setAaData(lsAlmc);
	                       } else {
	                           response.setEstado(Constantes.valTransaccionNoEncontro);
	                       }
	                   } catch (Exception e) {
	                   	System.out.println(this.getClass().getSimpleName()+ " retornalsGlobales. ERROR : " + e.getMessage());
	                   	System.out.println(this.getClass().getSimpleName() + "/retornalsGlobales" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
	                   	throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornalsGlobales",
	        	    			e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	        	   				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),parametros);
	                   } 
	           return response;
	       

	   } 
    
	/*  Listar Parametros tipo Cuenta por Empresa
	  *  
	  * Se envia como parametro el id_empresa
	  * retorna todos los atributos en una lista segun el id_empresa
	  * y que pertenezcan al grupo CUENTAS
	  * 
	  * 
	  * Parametros
	  * Integer 	id_empresa
	  * 
	  * 08/07/2019
	  * JPM
	  * 
	  */
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/retornalsCuentasxEmpr", method = RequestMethod.POST)
	public ResponseWrapper retornalsCuentasxEmpr(@RequestBody @Validated _Parametros parametros,
			HttpServletRequest request) throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		List lsAlmc = new ArrayList();
		Constantes co = new Constantes();

		try {

			parametros.setGrupo(co.GrpCuentas);

			lsAlmc = parametrosService.retornalsCuentasxEmpr(parametros);

			if (lsAlmc != null && lsAlmc.size() > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lsAlmc);
			} else {
				response.setEstado(Constantes.valTransaccionNoEncontro);
				response.setAaData(lsAlmc);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " retornalsCuentasxEmpr. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/retornalsCuentasxEmpr" + "Linea nro : "+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornalsCuentasxEmpr",
	    			e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	   				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),parametros);
		}
		return response;
		

	}

	/*
	 * Conseguir valor global de IGV
	 * 
	 * No se envia ningun parametro
	 * 
	 * 08/07/2019 JPM
	 * 
	 */
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/retornaValorIGV", method = RequestMethod.POST)
	public ResponseWrapper retornaValorIGV(@RequestBody @Validated _Parametros parametros, HttpServletRequest request)
			throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			parametros.setCodigo(Constantes.CodIGVGlob);
			parametros.setGrupo(Constantes.grupoGlobales);

			_Parametros obj = parametrosService.retornaValIGV(parametros);

			if (obj != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setDefaultObj(obj);
				return response;
			} else {
				response.setEstado(Constantes.valTransaccionNoEncontro);
				throw new Exception(Constantes.msgValorIGVNoEncontro);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " retornaValorIGV. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/retornaValorIGV" + "Linea nro : "+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornaValorIGV",
	    			e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	   				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),parametros);
		}
	}

	/*
	 * Conseguir valor global de ISC
	 * 
	 * No se envia ningun parametro
	 * 
	 * 08/07/2019 JPM
	 * 
	 */
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/retornaValorISC", method = RequestMethod.POST)
	public ResponseWrapper retornaValorISC(@RequestBody @Validated _Parametros parametros, HttpServletRequest request)
			throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		try {
			parametros.setCodigo(Constantes.CodISCGlob);
			parametros.setGrupo(Constantes.grupoGlobales);

			_Parametros obj = parametrosService.retornaValISC(parametros);

			if (obj != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setDefaultObj(obj);
			} else {
				response.setEstado(Constantes.valTransaccionNoEncontro);
				//throw new Exception(Constantes.msgValorISCNoEncontro);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " retornaValorISC. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/retornaValorISC" + "Linea nro : "+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornaValorISC",
	    			e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	   				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),parametros);
		}
		return response;
	}

	/*
	 * Listar tipo de monedas segun el grupo
	 * 
	 * Se envia como parametros => grupo="MONEDA"
	 * 
	 * 12/11/2019
	 * 
	 * MAD
	 * 
	 */
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/retornaTipoMoneda", method = RequestMethod.POST)
	public ResponseWrapper retornaTipoMoneda(@RequestBody @Validated _Parametros parametros, HttpServletRequest request)
			throws Exception {
		
		ResponseWrapper response = new ResponseWrapper();
		List objList = new ArrayList();
		Constantes co = new Constantes();
		try {
			objList = parametrosService.retornaParametrosPorGrupo(parametros);
			if (objList != null && objList.size() > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(objList);
			} else {
				response.setEstado(Constantes.valTransaccionNoEncontro);
				response.setAaData(objList);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " /retornaTipoMoneda. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/retornaTipoMoneda" + "Linea nro : "+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornaTipoMoneda",
	    			e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	   				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),parametros);
		} 
		return response;
		

	}
    
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/retornaValorPorIdEmpresaCodigoGrupo", method = RequestMethod.POST)
	public ResponseWrapper retornaValorPorIdEmpresaCodigoGrupo(@RequestBody @Validated _Parametros parametros, HttpServletRequest request)
			throws Exception {
		
		ResponseWrapper response = new ResponseWrapper();
		_Parametros obj = new _Parametros();
		try {
			parametros.setCodigo(Constantes.codigoParametroPorcentajeRetencion);
			parametros.setGrupo(Constantes.codigoGrupoEMPParametros);
			
			obj = parametrosService.retornaValorPorIdEmpresaCodigoGrupo(parametros);
			if (obj != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setDefaultObj(obj);
			} else {
				response.setEstado(Constantes.valTransaccionNoEncontro);
				response.setDefaultObj(obj);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " /retornaValorPorIdEmpresaCodigoGrupo. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/retornaValorPorIdEmpresaCodigoGrupo" + "Linea nro : "+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornaValorPorIdEmpresaCodigoGrupo",
	    			e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	   				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),parametros);
		} 
		return response;
		

	}
    
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
   	@RequestMapping(value = "/retornaDescripcionValorPorIdEmpresaGrupo", method = RequestMethod.POST)
   	public ResponseWrapper retornaDescripcionValorPorIdEmpresaGrupo(@RequestBody @Validated _Parametros parametros, HttpServletRequest request)
   			throws Exception {
   		
   		ResponseWrapper response = new ResponseWrapper();
   		List objList = new ArrayList();
   		try {
   			parametros.setGrupo(Constantes.codigoGrupoEMPParametros);
   			objList = parametrosService.retornaDescripcionValorPorIdEmpresaGrupo(parametros);
   			if (objList != null && objList.size() > 0) {
   				response.setEstado(Constantes.valTransaccionOk);
   				response.setAaData(objList);
   			} else {
   				response.setEstado(Constantes.valTransaccionNoEncontro);
   				response.setAaData(objList);
   			}
   		} catch (Exception e) {
   			System.out.println(this.getClass().getSimpleName() + " /retornaDescripcionValorPorIdEmpresaGrupo. ERROR : " + e.getMessage());
   			System.out.println(this.getClass().getSimpleName() + "/retornaDescripcionValorPorIdEmpresaGrupo" + "Linea nro : "+ e.getStackTrace()[0].getLineNumber());
   			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornaDescripcionValorPorIdEmpresaGrupo",
   	    			e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
   	   				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),parametros);
   		} 
   		return response;
   	}
    
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    @RequestMapping(value = "/actualizarValorPorIdEmpresaIdParametros", method = RequestMethod.POST)
    public ResponseWrapper actualizarValorPorIdEmpresaIdParametros(@RequestBody @Validated _Parametros parametros, HttpServletRequest request) throws Exception {
    	ResponseWrapper response = new ResponseWrapper();
 	   
	    try {
            Integer cat = 0;
            if (parametros.getId_parametros()!=null){
	            cat = parametrosService.actualizarValorPorIdEmpresaIdParametros(parametros);	            	
            }
            if (cat != null && cat>0) {
                response.setEstado(Constantes.valTransaccionOk);
              //  response.setMsg(Constantes.msgTransaccionAlmacenActualizarOK);
            } else {
                response.setEstado(Constantes.valTransaccionErrornull);
              //  response.setMsg(Constantes.msgTransaccionAlmacenActualizarError);
                throw new Exception(Constantes.msgTransaccionError);
            }
	    } catch (Exception e) {
	    	System.out.println(this.getClass().getSimpleName()+ " actualizarValorPorIdEmpresaIdParametros. ERROR : " + e.getMessage());
	    	System.out.println(this.getClass().getSimpleName() + "/actualizarValorPorIdEmpresaIdParametros" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
	    	throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/actualizarValorPorIdEmpresaIdParametros",
    			e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
   				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),parametros);
        }
        return response;
    }
    
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    @RequestMapping(value = "/eliminaParametroValor", method = RequestMethod.POST)
    public ResponseWrapper eliminaParametroValor(@RequestBody @Validated _Parametros parametros, HttpServletRequest request) throws Exception {
    	ResponseWrapper response = new ResponseWrapper();
 	   
	    try {
            Integer cat = 0;
	        cat = parametrosService.eliminaParametros(parametros);	    
	        
            if (cat != null && cat>0) {
                response.setEstado(Constantes.valTransaccionOk);
              //  response.setMsg(Constantes.msgTransaccionAlmacenActualizarOK);
            } else {
                response.setEstado(Constantes.valTransaccionErrornull);
              //  response.setMsg(Constantes.msgTransaccionAlmacenActualizarError);
                throw new Exception(Constantes.msgTransaccionError);
            }
	    } catch (Exception e) {
	    	System.out.println(this.getClass().getSimpleName()+ " eliminaParametroValor. ERROR : " + e.getMessage());
	    	System.out.println(this.getClass().getSimpleName() + "/eliminaParametroValor" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
	    	throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/eliminaParametroValor",
    			e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
   				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),parametros);
        }
        return response;
        

    }
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/retornaParametroConsultaEstadoSunat", method = RequestMethod.GET)
	public ResponseWrapper retornaParametroConsultaEstadoSunat(HttpServletRequest request)
			throws Exception {
		
		ResponseWrapper response = new ResponseWrapper();
		_Parametros obj = new _Parametros();
		try {
			obj.setCodigo(Constantes.codWSCPE_VED);
			obj.setGrupo(Constantes.codigoGrupoIniParametros);
			obj.setId_empresa(Constantes.parametroIdEmpresaGlobal);
			obj = parametrosService.retornaValorPorIdEmpresaCodigoGrupo(obj);
			if (obj != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setDefaultObj(obj);
			} else {
				response.setEstado(Constantes.valTransaccionNoEncontro);
				response.setDefaultObj(obj);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " /retornaValorPorIdEmpresaCodigoGrupo. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/retornaValorPorIdEmpresaCodigoGrupo" + "Linea nro : "+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornaValorPorIdEmpresaCodigoGrupo",
	    			e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	   				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),"");
		} 
		return response;
		

	}
}