package org.gteperu.erp.everest.mappers;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Param;
import org.gteperu.erp.everest.domain.TipoCambio;

public interface TipoCambioMapper {

	@Insert("INSERT INTO public.tipo_cambio(compra, venta, codsunat, fechapublicacion)"
			+ "	VALUES (#{compra}, #{venta}, #{codsunat}, #{fechapublicacion});")
	@SelectKey(statement = "select currval('sec_tipo_cambio') as idtipocambio", keyProperty = "idtipocambio", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "idtipocambio", flushCache = FlushCachePolicy.TRUE)
	public Integer insertar(TipoCambio tc);
	
	@Select("SELECT * FROM public.tipo_cambio where fechapublicacion=#{fechapublicacion}")
	public TipoCambio encontrarPorFecha(@Param("fechapublicacion") Timestamp fechapublicacion);
	
	@Select("SELECT EXISTS (SELECT * FROM public.tipo_cambio where fechapublicacion=#{fechapublicacion})")
	public boolean existePorFecha(@Param("fechapublicacion") Timestamp fechapublicacion);
}
