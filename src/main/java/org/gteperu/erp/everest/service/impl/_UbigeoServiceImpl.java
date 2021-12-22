package org.gteperu.erp.everest.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.gteperu.erp.everest.domain.Ubigeo;
import org.gteperu.erp.everest.domain._ProductoCodigoSunat;
import org.gteperu.erp.everest.mappers.Prod_Cod_SunatMapper;
import org.gteperu.erp.everest.mappers.UbigeoMapper;
import org.gteperu.erp.everest.service._Prod_Cod_SunatService;
import org.gteperu.erp.everest.service._UbigeoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("ubigeoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class _UbigeoServiceImpl implements _UbigeoService{
	@Resource(name = "ubigeoMapper")
	UbigeoMapper ubigeoMapper;

	@Override
	public List<Ubigeo> retornaObjDepartamento(Ubigeo ubigeo) {
		List<Ubigeo> lsDepartamento = new ArrayList<Ubigeo>();
		try{
			lsDepartamento = ubigeoMapper.retornaObjDepartamento();
		}
		catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/retornaObjDepartamento => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/retornaObjDepartamento" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return lsDepartamento;
		
	}
	
	@Override
	public List<Ubigeo> retornaObjProvincia(Ubigeo ubigeo) {
		List<Ubigeo> lsProvincia = new ArrayList<Ubigeo>();
		try{
			lsProvincia = ubigeoMapper.retornaObjProvincia(ubigeo);
		}
		catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/retornaObjProvincia => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/retornaObjProvincia" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return lsProvincia;
		
	}
	
	@Override
	public List<Ubigeo> retornaObjDistrito(Ubigeo ubigeo) {
		List<Ubigeo> lsDistrito = new ArrayList<Ubigeo>();
		try{
			lsDistrito = ubigeoMapper.retornaObjDistrito(ubigeo);
		}
		catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/retornaObjDistrito => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/retornaObjDistrito" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return lsDistrito;
		
	}
	
	@Override
	public Ubigeo retornaObjUbigeoxCodigo(String codigo) {
		Ubigeo obbj = new Ubigeo();
		try{
			obbj = ubigeoMapper.retornaObjUbigeoxCodigo(codigo);
		}
		catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/retornaObjDistrito => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/retornaObjDistrito" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return obbj;
		
	}

}
