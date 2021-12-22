package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.gteperu.erp.everest.domain._ProductoCodigoSunat;

@Mapper
public interface LimpiarMapper {
	
	@Select("SELECT * FROM producto_cod_sunat")
	public List<_ProductoCodigoSunat> listarProductos();
	
	@Update("UPDATE producto_cod_sunat SET codigo_producto_cod_sunat=#{codigo_producto_cod_sunat} "
			+ "WHERE id_producto_cod_sunat=#{id_producto_cod_sunat}")
	public Integer actualizarCodigo(_ProductoCodigoSunat prod);
}
