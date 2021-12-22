package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.gteperu.erp.everest.domain.Documentos;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain._Cpe_RetencionPercepcionBean;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._Producto;
import org.gteperu.erp.everest.domain._Retencion;
import org.gteperu.erp.everest.domain._RetencionExcel;
import org.springframework.stereotype.Repository;

@Repository("retencionMapper")
public interface RetencionMapper {

				
	@Insert(" insert into retencion(id_empresa,total_retencion,neto_retencion,"
			+ "id_cliente,cod_tipo_documento,fecha_registro,nro_comprobante,"
			+ "fecha_documento,zona_urbanizacion_empresa,cod_pais_empresa,zona_urbanizacion_proveedor,ciudad_proveedor,"
			+ "pais_proveedor,tipo_retencion,porcentaje_retencion,nota,moneda,tipo,estado,"
			+ "total_percepcion,neto_percepcion,zona_urbanizacion_cliente,ciudad_cliente,pais_cliente,"
			+ "tipo_percepcion,porcentaje_percepcion,bdocborrador)"
			+ "values(#{id_empresa},#{total_retencion},#{neto_retencion},"
			+ "#{id_cliente},#{cod_tipo_documento},#{fecha_emision_str},#{nro_comprobante},"
			+ "#{fecha_documento_str},#{zona_urbanizacion_empresa},#{cod_pais_empresa},#{zona_urbanizacion_proveedor},#{ciudad_proveedor},"
			+ "#{pais_proveedor},#{tipo_retencion},#{porcentaje_retencion},#{nota},#{cod_moneda},#{tipo},#{estado},"
			+ "#{total_percepcion}, #{neto_percepcion}, #{zona_urbanizacion_cliente}, #{ciudad_cliente}, #{pais_cliente},"
			+ "#{tipo_percepcion}, #{porcentaje_percepcion},#{bdocborrador});")
    @SelectKey(statement = "select currval('sec_retencion') as id_retencion", keyProperty = "id_retencion", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "id_retencion", flushCache = FlushCachePolicy.TRUE)
    public Integer insertarDocumentoRetencion(_Retencion documento);
    
//	@Select("select * from retencion where cod_tipo_documento = #{cod_tipo_documento} and id_empresa = #{id_empresa}")
	public List<_Retencion> lsRetencionOPercepcion (@Param("rete") _Retencion retencion);
	
	public List<_RetencionExcel> lsRetencionOPercepcionExcel (@Param("rete") _Retencion retencion);
	
    @Update("update retencion set estado=#{estado} where id_retencion=#{id_retencion};")
	public Integer actualizarRetencionEstadoRechazado(_Retencion id_documento);
    
	public List<_Retencion> lsRetencionOPercepcionxEstado (@Param("rete") _Retencion retencion);
	
 	public Integer cantidadRetePerc(@Param("rete") _Retencion retencion);
 	
 	
 	 @Delete(" delete from retencion where id_retencion=#{id_retencion}; ")
 	public Integer eliminarDocumentoRetencion(_Retencion id_documento);
    
    

}