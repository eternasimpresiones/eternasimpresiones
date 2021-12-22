package org.gteperu.erp.everest.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.gteperu.erp.everest.domain.FamiliaProducto;
import org.gteperu.erp.everest.domain._Producto;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.service.FamiliaProductoService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/familiaProducto")
public class FamiliaPorductoRestController {
	@Resource(name="familiaProductoService")
	FamiliaProductoService familiaProductoService;
	
	/* Web Service para inserta un familia producto
	 * 
	 * id_empresa	integer
	 * descripcion_familia	string
	 * cta_asi_vta_debe	string
	 * cta_asi_vta_haber	string
	 * porcentaje_detrac	double
	 * cta_mercaderia	string
	 * 
	 * JPM	02/07/2019
	 * 
	 */
	/*@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@RequestMapping(value="/insertaFamiliaProducto")
	public ResponseWrapper insertaFamiliaProducto(@RequestBody @Validated FamiliaProducto familiaproducto,HttpServletRequest request) throws Exception{
		try{
			ResponseWrapper response=new ResponseWrapper();
			Integer sta=familiaProductoService.insertaFamiliaProducto(familiaproducto);
			if(sta>0){
				response.setEstado(Constantes.valTransaccionOk);*/
				//response.setMsg(Constantes.msgInsertaFamiliaOk);
				/*return response;
			}else{
				System.out.println(this.getClass().getSimpleName() + "insertaFamiliaProducto");*/
				//throw new Exception(Constantes.msgInsertaFamiliaError);
			/*}
		}catch(Exception e){
			System.out.println(this.getClass().getSimpleName() + "/insertaFamiliaProducto" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw new Exception(Constantes.msgTransaccionError);
		}
	}*/
	
	/* Web Service para listar las familias producto de una empresa
	 * 
	 * id_empresa	integer
	 * 
	 * JPM	02/07/2019
	 * 
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value="/listarFamiliaProducto")
	public ResponseWrapper listarFamiliaProducto(@RequestBody @Validated FamiliaProducto familiaproducto,HttpServletRequest request) throws Exception{
		try{
			ResponseWrapper response=new ResponseWrapper();
			List lsfp=familiaProductoService.retornaFamiliaProdxEmp(familiaproducto);
			//if(lsfp!=null){
				if(lsfp!=null && lsfp.size()>0){
					response.setEstado(Constantes.valTransaccionOk);
					//.setMsg(Constantes.msgListaFamiliaOk);
					response.setAaData(lsfp);
					return response;
				}else{
					response.setEstado(Constantes.valTransaccionNoEncontro);
					//response.setMsg(Constantes.msgListaFamiliaVacia);
					response.setAaData(lsfp);
					return response;
				}				
			/*}else{
				System.out.println(this.getClass().getSimpleName() + "/retornaFamiliaProdxEmp");
				throw new Exception(Constantes.msgTransaccionError);
			}*/
		}catch(Exception e){
			System.out.println(this.getClass().getSimpleName() + "/listarFamiliaProducto. Linea nro :"+e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/listarFamiliaProducto",
	  				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	  				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),familiaproducto);
		}
	}
	

	
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/actualizarFamiliaProducto", method = RequestMethod.POST)
	public ResponseWrapper updateFamiliaProducto(@RequestBody @Validated FamiliaProducto familiaproducto, HttpServletRequest request) throws Exception {
	    
	    ResponseWrapper response = new ResponseWrapper();
	    try {
	            Integer cat = 0;
	          
	                
	                    cat = familiaProductoService.updateFamiliaProducto(familiaproducto);
	                
	            if (cat != null && cat > 0) {
	                response.setEstado(Constantes.valTransaccionOk);
	                response.setMsg("Familia Producto actualizado correctamente");
	            } else {
	                response.setEstado(Constantes.valTransaccionErrornull);
	                response.setMsg(Constantes.msgTransaccionError);
	            }
	    } catch (Exception e) {
	    	System.out.println(this.getClass().getSimpleName()+ " actualizarFamiliaProducto. ERROR : " + e.getMessage());
	    	System.out.println(this.getClass().getSimpleName() + "/actualizarFamiliaProducto" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
	    	throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/actualizarFamiliaProducto",
	  				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	  				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),familiaproducto);
	    }
	    return response;
	    
	}
	
	
	
}
