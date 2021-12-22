package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
 
import org.gteperu.erp.everest.domain._ProductoCodigoSunat;
import org.springframework.stereotype.Repository;

@Repository("producto_cod_sunatMapper")
public interface Prod_Cod_SunatMapper {
	
		@Select("select * from producto_cod_sunat where descripcion_producto_cod_sunat like concat('%',#{descripcion_producto_cod_sunat},'%')")
		public List<_ProductoCodigoSunat> retornarProductoCodigoSunat(_ProductoCodigoSunat productoCodigoSunat);
		
		@Update("update producto_cod_sunat set codigo_producto_cod_sunat = #{codigo_producto_cod_sunat} where id_producto_cod_sunat=#{id_producto_cod_sunat}")
		public Integer actualizarCodigoProductos(_ProductoCodigoSunat productoCodigoSunat);
		
		@Select("select * from producto_cod_sunat")
		public List<_ProductoCodigoSunat> retornarListaProductoCodigoSunat();
		
}