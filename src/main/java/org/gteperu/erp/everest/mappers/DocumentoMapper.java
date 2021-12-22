package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._DocumentoCpe_Excel;
import org.gteperu.erp.everest.domain.Pagination;
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
public interface DocumentoMapper {
	
	/*@Insert(" insert into documento(id_empresa,total_gravadas,total_inafecta,total_exoneradas,"
			+ "total_gratuitas,total_percepciones,total_retenciones,total_detracciones,total_bonificaciones,"
			+ "total_descuento,sub_total,id_cliente,serie_comprobante,nro_comprobante,fecha_documento,fecha_vto,cod_moneda,estado,observacion,cod_tipo_documento,tipo_operacion,total_igv, total_isc, total_otr_imp, total,estado_pagado,tipo_cambio,porcentaje_descuento,tipo_comprobante_modifica,nro_documento_modifica,cod_tipo_motivo,descripcion_motivo,total_letras) "
			+ "values(#{id_empresa},#{total_gravadas},#{total_inafecta},#{total_exoneradas},"
			+ "#{total_gratuitas},#{total_percepciones},#{total_retenciones},#{total_detracciones},#{total_bonificaciones},"
			+ "#{total_descuento},#{sub_total},#{id_cliente_cpe},#{serie_comprobante},#{nro_comprobante},#{fecha_documento},#{fecha_vto},#{cod_moneda},#{estado},#{observacion},#{cod_tipo_documento},#{tipo_operacion},#{total_igv},#{total_isc},#{total_otr_imp},#{total},#{estado_pagado},#{tipo_cambio},#{porcentaje_descuento},#{tipo_comprobante_modifica},#{nro_documento_modifica},#{cod_tipo_motivo},#{descripcion_motivo},#{total_letras});")
    @SelectKey(statement = "select currval('sec_documento') as id_documento", keyProperty = "id_documento", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "id_documento", flushCache = FlushCachePolicy.TRUE)
    public Integer insertarDocumento(_DocumentoCpe documento);*/
	
	@Insert(" insert into documento(id_empresa,total_gravadas,total_inafecta,total_exoneradas,"
			+ "total_gratuitas,total_percepciones,total_retenciones,total_detracciones,total_bonificaciones,"
			+ "total_descuento,sub_total,id_cliente,serie_comprobante,nro_comprobante,fecha_documento,fecha_vto,cod_moneda,estado,observacion,cod_tipo_documento,tipo_operacion,total_igv, total_isc, total_otr_imp, total,estado_pagado,tipo_cambio,porcentaje_descuento,total_letras,"
			+ "nro_documento_modifica,tipo_comprobante_modifica,cod_tipo_motivo,descripcion_motivo,peso_bruto,cod_motivo_traslado,cod_modalidad_traslado,"
			+ "descripcion_motivo_traslado,cod_ubigeo_origen,cod_ubigeo_destino,direccion_origen,direccion_destino,cod_tipo_doc_chofer,nro_doc_chofer,placa_vehiculo,fecha_inicio_traslado,tipo_documento_transportista,nro_documento_transportista,"
			+ "nro_contenedor,notas_documento,terminos_condiciones_doc,tipo_detraccion,porcentaje_detraccion,direccion_fiscal_cliente,idlocal,bdocborrador,cod_forma_pago)"
			+ "values(#{id_empresa},#{total_gravadas},#{total_inafecta},#{total_exoneradas},"
			+ "#{total_gratuitas},#{total_percepciones},#{total_retenciones},#{total_detracciones},#{total_bonificaciones},"
			+ "#{total_descuento},#{sub_total},#{id_cliente_cpe},#{serie_comprobante},#{nro_comprobante},#{fecha_documento},#{fecha_vto},#{cod_moneda},#{estado},#{observacion},#{cod_tipo_documento},#{tipo_operacion},#{total_igv},#{total_isc},#{total_otr_imp},#{total},#{estado_pagado},#{tipo_cambio},#{porcentaje_descuento},"
			+ "#{total_letras},#{nro_documento_modifica},#{tipo_comprobante_modifica},#{cod_tipo_motivo},#{descripcion_motivo},#{peso_bruto},#{cod_motivo_traslado},#{cod_modalidad_traslado},"
			+ "#{descripcion_motivo_traslado},#{cod_ubigeo_origen},#{cod_ubigeo_destino},#{direccion_origen},#{direccion_destino},#{cod_tipo_doc_chofer},#{nro_doc_chofer},"
			+ "#{placa_vehiculo},#{fecha_inicio_traslado},#{tipo_documento_transportista},#{nro_documento_transportista},#{nro_contenedor},#{notas_documento},#{terminos_condiciones_doc}"
			+ ",#{tipo_detraccion},#{porcentaje_detraccion},#{direccion_fiscal_cliente},#{idlocal},#{bdocborrador},#{cod_forma_pago});")
    @SelectKey(statement = "select currval('sec_documento') as id_documento", keyProperty = "id_documento", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "id_documento", flushCache = FlushCachePolicy.TRUE)
    public Integer insertarDocumento(_DocumentoCpe documento);

 	public List<_DocumentoCpe> listarDocumento(@Param ("docu") _DocumentoCpe docu);
 	
 	public List<_DocumentoCpe> listarDocumentoPublico(@Param ("docu") _DocumentoCpe docu);

 	
 	
 	public List<_DocumentoCpe> retornaDocumentoPorIddocumentoExcel(@Param ("docu") _DocumentoCpe docu);
 	
 	public Integer cantidadDocumentos(@Param ("doc") _DocumentoCpe docu);
 	
 	public List<_DocumentoCpe> listarDocumentoxEstado(@Param ("docu") _DocumentoCpe docu);

 	 	
	@Select("SELECT * FROM documento WHERE id_documento=#{id_documento};")
 	public _DocumentoCpe retornaDocumentoId(_DocumentoCpe docu);
	
	@Select("select nro_comprobante from documento where id_empresa=#{id_empresa} order by CAST ( nro_comprobante AS integer ) desc limit 1")
 	public String retornaultimonrocomprobante(_DocumentoCpe docu);

	public _DocumentoCpe retornaDocumentoPorId(@Param ("docu") _DocumentoCpe docu);
	
	public _DocumentoCpe retornaDocumentoPorSerieNum(@Param ("docu") _DocumentoCpe docu);
	 	
	public _DocumentoCpe retornaDocumentoCPEXML(@Param ("docu") _DocumentoCpe docu);
    
    @Update("update documento set id_empresa=#{id_empresa}, total_gravadas=#{total_gravadas}, total_inafecta=#{total_inafecta}, total_exoneradas=#{total_exoneradas},"
    		+ "total_gratuitas=#{total_gratuitas},total_percepciones=#{total_percepciones},total_retenciones=#{total_retenciones},total_detracciones=#{total_detracciones},total_bonificaciones=#{total_bonificaciones},"
    		+ "total_descuento=#{total_descuento},total=#{total},total_igv=#{total_igv},total_isc=#{total_isc},sub_total=#{sub_total},motivo_baja=#{motivo_baja},"
    		+ "fecha_baja=#{fecha_baja},estado=#{estado},secuencia=#{secuencia},notas_documento=#{notas_documento},terminos_condiciones_doc=#{terminos_condiciones_doc},cod_forma_pago=#{cod_forma_pago} where id_documento=#{id_documento};")
	public Integer actualizarDocumento(_DocumentoCpe id_documento);
    
//    @Update("update documento set id_empresa=#{id_empresa}, total_gravadas=#{total_gravadas}, total_inafecta=#{total_inafecta}, total_exoneradas=#{total_exoneradas},"
//    		+ "total_gratuitas=#{total_gratuitas},total_percepciones=#{total_percepciones},total_retenciones=#{total_retenciones},total_detracciones=#{total_detracciones},total_bonificaciones=#{total_bonificaciones},"
//    		+ "total_descuento=#{total_descuento},sub_total=#{sub_total},id_cliente=#{id_cliente_cpe},serie_comprobante=#{serie_comprobante},nro_comprobante=#{nro_comprobante},fecha_documento=#{fecha_documento},fecha_vto=#{fecha_vto},cod_moneda=#{cod_moneda},estado=#{estado},observacion=#{observacion},cod_tipo_documento=#{cod_tipo_documento}, tipo_operacion=#{tipo_operacion}, total_igv=#{total_igv}, total_isc=#{total_isc}, total_otr_imp=#{total_otr_imp},total=#{total}, "
//    		+ "estado_pagado=#{estado_pagado},tipo_cambio=#{tipo_cambio}, porcentaje_descuento=#{porcentaje_descuento},tipo_comprobante_modifica=#{tipo_comprobante_modifica},nro_documento_modifica=#{nro_documento_modifica},cod_tipo_motivo=#{cod_tipo_motivo},descripcion_motivo=#{descripcion_motivo},total_letras=#{total_letras},motivo_baja=#{motivo_baja},fecha_baja=#{fecha_baja},#{notas_documento},#{terminos_condiciones_doc} where id_documento=#{id_documento};")
//	public Integer actualizarDocumentoEstadoRechazado(_DocumentoCpe id_documento);
    @Update("update documento set estado=#{estado} where id_documento=#{id_documento};")
	public Integer actualizarDocumentoEstadoRechazado(_DocumentoCpe id_documento);
    
    @Delete(" delete from documento where id_documento=#{id_documento}; ")
	public Integer eliminarDocumento(_DocumentoCpe id_documento);
    
    @Select("select id_documento, id_empresa, cod_tipo_documento, nro_comprobante, serie_comprobante from documento where id_empresa=#{id_empresa} ")
 	public List<_DocumentoCpe> retornalsNroDocumento(_DocumentoCpe docu);
 	
 	//RUTA ARCHIVO
 	@Insert("update documento set ruta_archivo = #{ruta_archivo} where id_documento = #{id_documento}")
 	public Integer actualizarRutaArchivo(_DocumentoCpe docu);
 	
	public _DocumentoCpe retornaRutaArchivo(@Param ("documento") _DocumentoCpe docu);
	
	//RETENCION - FACTURA
	public _DocumentoCpe retornaDocumentoPorSerieNumIdEmpresa(@Param ("docu") _DocumentoCpe docu);
	
	@Select("select * from documento where id_empresa=#{id_empresa} and estado = 1 order by serie_comprobante asc")
 	public List<_DocumentoCpe> retornalsNroDocumentoEstado(_DocumentoCpe docu);
 	
 	@Select("select * from documento where id_empresa=#{id_empresa} and cod_tipo_documento = #{cod_tipo_documento} and estado = 1 order by serie_comprobante asc")
 	public List<_DocumentoCpe> retornalsNroDocumentoEstadoTipoDocu(_DocumentoCpe docu);
 	
 	@Select("select * from documento where id_empresa=#{id_empresa} and idlocal=#{idlocal} and cod_tipo_documento = #{cod_tipo_documento} and estado = 1 order by serie_comprobante asc")
 	public List<_DocumentoCpe> retornalsNroDocumentoLocalEstadoTipoDocu(_DocumentoCpe docu);
 	
 	@Select("select  distinct serie_comprobante from documento where id_empresa = #{id_empresa} and  estado = 1 order by serie_comprobante asc")
 	public List<_DocumentoCpe> retornalsSerieComprobateUnico(_DocumentoCpe docu);

 	@Select("SELECT DISTINCT ON (serie_comprobante) serie_comprobante , * from documento "
 			+ "where id_empresa=#{id_empresa} and idlocal=#{idlocal} and cod_tipo_documento = #{cod_tipo_documento}"
 			+ "and estado = 1 order by serie_comprobante asc")
 	public List<_DocumentoCpe> retornaPorTipoDocumento(_DocumentoCpe docu);
 	
 	@Select("SELECT * from documento "
 			+ "where id_empresa=#{id_empresa} and idlocal=#{idlocal} and serie_comprobante = #{serie_comprobante}"
 			+ "and estado = 1 order by nro_comprobante asc")
 	public List<_DocumentoCpe> retornaPorSerieComprobante(_DocumentoCpe docu);

}