package org.gteperu.erp.everest.service;

import java.util.List;

import org.gteperu.erp.everest.domain.FamiliaProducto;


public interface FamiliaProductoService {
	
	public Integer insertaFamiliaProducto(FamiliaProducto familiaproducto);
	
	public List<FamiliaProducto> retornaFamiliaProdxEmp(FamiliaProducto familiaproducto);
	
	public Integer updateFamiliaProducto(FamiliaProducto familiaproducto);
}
