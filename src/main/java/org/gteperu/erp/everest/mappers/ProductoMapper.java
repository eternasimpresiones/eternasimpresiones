package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain._Detalle_Documento_TMP;
import org.gteperu.erp.everest.domain._Producto;
import org.springframework.stereotype.Repository;

@Repository("productoMapper")
public interface ProductoMapper {
		/*@Select(" select * from producto where id_empresa=#{id_empresa};")select count(*) as cantidad from producto where id_empresa = 1
		public List<_Producto> retornaProducto( _Producto codpro);*/
	
		@Select("select count(*) as cantidad from producto where id_empresa = #{id_empresa}")
		public Pagination retornaCantidadProductoPorEmpresa(_Producto codpro);
		
		public List<_Producto> retornaProducto(@Param ("codpro") _Producto codpro);
		
		@Select(" select * from producto where id_empresa=#{id_empresa} and estado=#{estado};")
		public List<_Producto> retornaProductoxEmpresaxEstado(_Producto codpro);
		
		@Update(" update producto set id_empresa=#{id_empresa},codigo_producto_interno=#{codigo_producto_interno},nombre_producto=#{nombre_producto},"
				+ "codigo_sunat=#{codigo_sunat},igv_afecto=#{igv_afecto},descripcion=#{descripcion},valor_precio_venta=#{valor_precio_venta},valor_precio_compra=#{valor_precio_compra},estado=#{estado},isc_afecto=#{isc_afecto},"
				+ "id_unidad_med=#{id_unidad_med}, id_param_tipomoneda=#{id_param_tipomoneda}"
				+ "where id_producto=#{id_producto}; ")
		public Integer updateProducto(_Producto codpro);
				
		@Insert(" insert into producto(id_empresa,codigo_producto_interno,nombre_producto,codigo_sunat,igv_afecto,descripcion,valor_precio_venta,valor_precio_compra,estado,isc_afecto, id_unidad_med, id_param_tipomoneda)"
				+ "values(#{id_empresa},#{codigo_producto_interno},#{nombre_producto},#{codigo_sunat},#{igv_afecto},#{descripcion},#{valor_precio_venta},#{valor_precio_compra},#{estado},#{isc_afecto}, #{id_unidad_med}, #{id_param_tipomoneda});")
		public Integer insertaProducto(_Producto codpro);

		@Delete(" delete from  producto where id_producto=#{id_producto}; ")
		public Integer eliminaProducto(_Producto codpro);
		
		
		@Update(" update producto set id_empresa=#{id_empresa},codigo_producto_interno=#{codigo_producto_interno},nombre_producto=#{nombre_producto},"
				+ "codigo_sunat=#{codigo_sunat},igv_afecto=#{igv_afecto},descripcion=#{descripcion},valor_precio_venta=#{valor_precio_venta},valor_precio_compra=#{valor_precio_compra},estado=#{estado}"
				+ "where codigo_producto_interno=#{codigo_producto_interno}; ")
		public Integer updateProductoWithCodigoInterno(_Producto codpro);
		
		@Select(" select id_producto from producto where id_empresa=#{iid_empresa} and codigo_producto_interno=#{scodigo} and "
				+ "codigo_sunat=#{scod_sunat};")
		public _Producto retornaProductoxDocumentoTMP(_Detalle_Documento_TMP doc);
}