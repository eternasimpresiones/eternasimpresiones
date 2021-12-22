package org.gteperu.erp.everest.service;

import java.util.List;

import org.gteperu.erp.everest.domain._Detalle_Documento_TMP;
import org.gteperu.erp.everest.domain._Producto;

public interface _ProductoService {
	
	public List<_Producto> listarProducto(_Producto producto);
	
	public Integer insertarProducto(_Producto producto);
	
	public Integer actualizarProducto(_Producto cod_producto);
	
	public Integer eliminarProducto(_Producto cod_producto);
	
	public Integer updateProductoWithCodigoInterno(_Producto cod_producto);

	public List<_Producto> listarProductoPorEstado(_Producto producto);
	
	public _Producto retornaProductoxDocumentoTMP(_Detalle_Documento_TMP doc);

	
}
