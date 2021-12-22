package org.gteperu.erp.everest.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.gteperu.erp.everest.domain._ProductoCodigoSunat;
import org.gteperu.erp.everest.mappers.Prod_Cod_SunatMapper;
import org.gteperu.erp.everest.service._Prod_Cod_SunatService;
import org.gteperu.erp.everest.service._ProductoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("prod_cod_sunatService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class _Prod_Cod_SunatServiceImpl implements _Prod_Cod_SunatService{
	@Resource(name = "producto_cod_sunatMapper")
	Prod_Cod_SunatMapper producto_cod_sunatMapper;

	@Override
	public List<_ProductoCodigoSunat> retornarProductoCodigoSunat(_ProductoCodigoSunat _ProductoCodigoSunat) {
		List<_ProductoCodigoSunat> lsProductoCodigoSunat = new ArrayList<_ProductoCodigoSunat>();
		try{
			lsProductoCodigoSunat = producto_cod_sunatMapper.retornarProductoCodigoSunat(_ProductoCodigoSunat);
		}
		catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/retornarProductoCodigoSunat => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/retornarProductoCodigoSunat" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return lsProductoCodigoSunat;
		
	}

	@Override
	public Integer actualizarCodigoProductoSunat() {
		try {
			Integer result = 0;
		List<_ProductoCodigoSunat> lsProductos = producto_cod_sunatMapper.retornarListaProductoCodigoSunat();
		
		for (_ProductoCodigoSunat _prod : lsProductos) {
			_prod.setCodigo_producto_cod_sunat(_prod.getCodigo_producto_cod_sunat().replace(" ", ""));
			result = producto_cod_sunatMapper.actualizarCodigoProductos(_prod);
			System.out.println(result);
		}
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
