package org.gteperu.erp.everest.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain._Detalle_Documento_TMP;
import org.gteperu.erp.everest.domain._Producto;
import org.gteperu.erp.everest.mappers.ProductoMapper;
import org.gteperu.erp.everest.service._ProductoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("productoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class _ProductoServiceImpl implements _ProductoService{
	@Resource(name = "productoMapper")
	ProductoMapper productoMapper;



	@Override
	public List<_Producto> listarProducto(_Producto producto) {
		Pagination pg = new Pagination();
		List<_Producto> lsProducto = new ArrayList<_Producto>();
		try{
			pg = productoMapper.retornaCantidadProductoPorEmpresa(producto);
			lsProducto = productoMapper.retornaProducto(producto);
			if (lsProducto != null && lsProducto.size() > 0) {
				lsProducto.get(0).setCantidad(pg.getCantidad());
			}
		}catch(Exception e){
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/listarProducto => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/listarProducto" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return lsProducto;
	}
	
	@Override
	public List<_Producto> listarProductoPorEstado(_Producto producto) {
		List<_Producto> lsProducto = new ArrayList<_Producto>();
		try{
			lsProducto = productoMapper.retornaProductoxEmpresaxEstado(producto);
		}catch(Exception e){
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/listarProductoPorEstado => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/listarProductoPorEstado" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return lsProducto;
	}

	@Override
	public Integer actualizarProducto(_Producto codpro) {
		Integer d = 0;
		try{
			d = productoMapper.updateProducto(codpro);
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " ActualizarProducto. ERROR : " + e.getMessage());
			throw e;
		}
		return d;			
		
	}
	
	@Override
	public Integer updateProductoWithCodigoInterno(_Producto codpro) {
		Integer d = 0;
		try{
			d = productoMapper.updateProductoWithCodigoInterno(codpro);
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " ActualizarProducto. ERROR : " + e.getMessage());
			throw e;
		}
		return d;			
		
	}

	@Override
	public Integer insertarProducto(_Producto codpro) {
		Integer d = 0;
		try {
			codpro.setEstado(1);
			d = productoMapper.insertaProducto(codpro);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+ " InsertaProducto. ERROR : " + e.getMessage());
			throw e;
		} 
		return d;
		
	}

	@Override
	public Integer eliminarProducto(_Producto codpro) {
		Integer d = 0;
		try {
			d = productoMapper.eliminaProducto(codpro);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+ " EliminarProducto. ERROR : " + e.getMessage());
			throw e;
		}
			return d;
	
	}
	@Override
	public _Producto retornaProductoxDocumentoTMP(_Detalle_Documento_TMP doc) {
		_Producto lsProducto = new _Producto();
		try{
			lsProducto = productoMapper.retornaProductoxDocumentoTMP(doc);
		}catch(Exception e){
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/retornaProductoxDocumentoTMP => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/retornaProductoxDocumentoTMP" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return lsProducto;
	}

}
