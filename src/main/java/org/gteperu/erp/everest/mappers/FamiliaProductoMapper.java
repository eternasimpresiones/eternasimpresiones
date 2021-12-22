package org.gteperu.erp.everest.mappers;

import java.util.List;  


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.gteperu.erp.everest.domain.FamiliaProducto;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FamiliaProductoMapper {
	
	@Insert("INSERT INTO familia_producto (descripcion_familia,cta_compras,cta_costo_venta,porcentaje_detrac,cta_mercaderia,id_empresa,id_cuenta_detracc) VALUES ("
			+ "#{descripcion_familia},#{cta_compras},#{cta_costo_venta},#{porcentaje_detrac},#{cta_mercaderia},#{id_empresa},#{id_cuenta_detracc});")
	@SelectKey(statement = "select currval('sec_familia_producto') as id_familia_producto", keyProperty = "id_familia_producto", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "id_familia_producto", flushCache = FlushCachePolicy.TRUE)
	public Integer insertaFamiliaProducto(FamiliaProducto familiaproducto);
	
	@Select("SELECT * FROM familia_producto WHERE id_empresa=#{id_empresa};")
	public List<FamiliaProducto> retornaFamiliaProdxEmp(FamiliaProducto familiaproducto); 
	
	@Update("update familia_producto set descripcion_familia=#{descripcion_familia},cta_compras=#{cta_compras},"
			+ "cta_costo_venta=#{cta_costo_venta},porcentaje_detrac=#{porcentaje_detrac},"
			+ "cta_mercaderia=#{cta_mercaderia},id_empresa=#{id_empresa},id_cuenta_detracc=#{id_cuenta_detracc} "
			+ "where id_familia_producto=#{id_familia_producto};")
	public Integer updateFamiliaProducto(FamiliaProducto familiaproducto);
	
	
	
}
