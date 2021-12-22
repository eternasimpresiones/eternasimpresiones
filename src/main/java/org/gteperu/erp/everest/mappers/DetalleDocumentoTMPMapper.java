package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain._Detalle_Documento_TMP;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Delete;

@Mapper
public interface DetalleDocumentoTMPMapper {

	@Insert(" insert into detalle_documento_tmp(iid_documento_tmp,iitem,dcantidad,sunidad_medida,scodigo,sdescripcion,dprecio,"
			+ "scod_tipo_operacion,digv,scod_sunat,dimporte)"
			+ "values(#{iid_documento_tmp},#{iitem},#{dcantidad},#{sunidad_medida},#{scodigo},#{sdescripcion},"
			+ "#{dprecio},#{scod_tipo_operacion},#{digv},#{scod_sunat},#{dimporte});")
	@SelectKey(statement = "select currval('sec_detalle_documento_tmp') as iid_detalle_documento_tmp", keyProperty = "iid_detalle_documento_tmp", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "iid_detalle_documento_tmp", flushCache = FlushCachePolicy.TRUE)
	public Integer insertaDetalleDocumentoTMP(_Detalle_Documento_TMP doc);

}