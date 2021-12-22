package org.gteperu.erp.everest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.gteperu.erp.everest.domain.FamiliaProducto;
import org.gteperu.erp.everest.domain._Producto;
import org.gteperu.erp.everest.mappers.FamiliaProductoMapper;
import org.gteperu.erp.everest.service.FamiliaProductoService;
import org.springframework.stereotype.Service;

@Service("familiaProductoService")
public class FamiliaProductoServiceImpl implements FamiliaProductoService {
	
	@Resource(name="familiaProductoMapper")
	FamiliaProductoMapper familiaProductoMapper;
	
	@Override
	public Integer insertaFamiliaProducto(FamiliaProducto familiaproducto) {
		try{
			Integer sta=familiaProductoMapper.insertaFamiliaProducto(familiaproducto);
			if(sta>0){return sta;}else{System.out.println(this.getClass().getSimpleName()+" insertaFamiliaProducto");return sta;}
		}catch(Exception e){
			System.out.println(this.getClass().getSimpleName()+" insertaFamiliaProducto. ERROR : "+e.getMessage());
			throw e;
		}
	}

	@Override
	public List<FamiliaProducto> retornaFamiliaProdxEmp(FamiliaProducto familiaproducto) {
		try{
			List<FamiliaProducto> lsfp=familiaProductoMapper.retornaFamiliaProdxEmp(familiaproducto);
			if(lsfp!=null){return lsfp;}else{System.out.println(this.getClass().getSimpleName()+" retornaFamiliaProdxEmp");return lsfp;}
		}catch(Exception e){
			System.out.println(this.getClass().getSimpleName()+" retornaFamiliaProdxEmp. ERROR : "+e.getMessage());
			throw e;
		}
	}
	
	
	
	
	@Override
	public Integer updateFamiliaProducto(FamiliaProducto familiaproducto) {
		Integer d = 0;
		try{
			d = familiaProductoMapper.updateFamiliaProducto(familiaproducto);
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " ActualizarFamilia. ERROR : " + e.getMessage());
			throw e;
		}
		return d;			
		
	}
	
}
