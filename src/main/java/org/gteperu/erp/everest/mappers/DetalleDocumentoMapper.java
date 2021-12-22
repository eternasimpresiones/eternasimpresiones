package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain._DocumentoCpe_DetalleBean;
import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain._CpeGuiaRemisionDetalleBean;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Delete;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DetalleDocumentoMapper {

	@Insert(" insert into detalle_documento(id_documento,id_empresa, id_producto,item,total_inafecta,unidad_medida,cantidad,precio,importe,precio_tipo_codigo,igv,codigo,descripcion)"
			+ "values(#{id_documento},#{id_empresa}, #{id_producto},#{item},#{total_inafecta},#{unidad_medida},#{cantidad},#{precio},#{importe},#{precio_tipo_codigo},#{igv},#{codigo},#{descripcion});")
	@SelectKey(statement = "select currval('sec_detalle_documento') as id_detalle_documento", keyProperty = "id_detalle_documento", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "id_detalle_documento", flushCache = FlushCachePolicy.TRUE)
	public Integer insertarDocumento_Detalle(_DocumentoCpe_DetalleBean detalle_doc);

	@Select("select * from detalle_documento")
	public List<_DocumentoCpe_DetalleBean> listarDocumento_Detalle();

	@Update("update detalle_documento set id_documento=#{id_documento}, id_empresa=#{id_empresa}, id_producto=#{id_producto}, item=#{item},total_inafecta=#{total_inafecta},"
			+ "unidad_medida=#{unidad_medida}, cantidad=#{cantidad}, precio=#{precio}, importe=#{importe},"
			+ "precio_tipo_codigo=#{precio_tipo_codigo}, igv=#{igv},isc=#{isc}, codigo=#{codigo}, descripcion=#{descripcion},cod_sunat =#{cod_sunat},cod_tipo_operacion=#{cod_tipo_operacion}  where id_detalle_documento=#{id_detalle_documento};")
	public Integer actualizarDocumento_Detalle(_DocumentoCpe_DetalleBean detalle_doc);

	@Delete(" delete from detalle_documento where id_detalle_documento=#{id_detalle_documento}; ")
	public Integer eliminarDocumento_Detalle(_DocumentoCpe_DetalleBean detalle_doc);

	@Delete(" delete from detalle_documento where id_documento=#{id_documento}; ")
	public Integer eliminarDocumento_DetallePorIdDocumento(_DocumentoCpe_DetalleBean detalle_doc);

	public Integer insertaDetalleDocumentoPorLista(@Param("list") List<_DocumentoCpe_DetalleBean> lsdetalle_doc);

	@Insert(" insert into detalle_documento(id_documento,id_empresa, id_producto,item,total_inafecta,unidad_medida,cantidad,precio,importe,precio_tipo_codigo,igv,codigo,descripcion,isc,cod_sunat,cod_tipo_operacion)"
			+ "values(#{id_documento},#{id_empresa}, #{id_producto},#{item},#{total_inafecta},#{unidad_medida},#{cantidad},#{precio},#{importe},#{precio_tipo_codigo},#{igv},#{codigo},#{descripcion},#{isc},#{cod_sunat},#{cod_tipo_operacion});")
	@SelectKey(statement = "select currval('sec_detalle_documento') as id_detalle_documento", keyProperty = "id_detalle_documento", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "id_detalle_documento", flushCache = FlushCachePolicy.TRUE)
	public Integer insertarDocumentoDetalle(_DocumentoCpe_DetalleBean detalle_doc);

	public Integer insertaDetalleDocumentoGuiaPorLista(@Param("list") List<_CpeGuiaRemisionDetalleBean> lsdetalle_doc);

}