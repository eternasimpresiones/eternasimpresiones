package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.gteperu.erp.everest.domain.Detalle_plantilla_documento;
import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.Plantilla_documento;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;

@Mapper
public interface PlantillaDocumentoMapper {
	
	@Insert("insert into plantilla_documento(iid_empresa,iid_cliente,scod_moneda,sobservacion,scod_tipo_documento,snonmbre,stipo_operacion) "
					+ "values(#{iid_empresa},#{iid_cliente},#{scod_moneda},#{sobservacion},#{scod_tipo_documento},#{snonmbre},#{stipo_operacion})")
	@SelectKey(statement = "select currval('sec_plantilla_doc') as iid_plantilla_documento", 
	keyProperty = "iid_plantilla_documento", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "iid_plantilla_documento", flushCache = FlushCachePolicy.TRUE)
	public Integer registrarPlantillaDocumento(Plantilla_documento objPlantillaDoc);
	
	@Update("update plantilla_documento set iid_empresa=#{iid_empresa}, iid_cliente=#{iid_cliente}, "
			+ "scod_moneda=#{scod_moneda},sobservacion=#{sobservacion}, scod_tipo_documento=#{scod_tipo_documento}, stipo_operacion=#{stipo_operacion}, snonmbre=#{snonmbre} where iid_plantilla_documento =#{iid_plantilla_documento};")
	public Integer actualizarPlantillaDocumento(Plantilla_documento objPlantillaDoc);
	
	@Delete("delete from plantilla_documento where iid_plantilla_documento=#{iid_plantilla_documento};")
	public Integer eliminarPlantillaDocumento(Integer objdetallePlantillaDoc);
	
	public List<Plantilla_documento> listarPlantillaTodas(@Param("param") Plantilla_documento plantilla);
	
	public List<Plantilla_documento> retornaPlantilla(@Param("param") Plantilla_documento plantilla);
	
	public Pagination retornaCantidadList(@Param("plantilla") Plantilla_documento plantilla);
	
	@Insert("insert into detalle_plantilla_documento(iid_plantilla_documento,id_producto,iitem,dcantidad,dprecio,dimporte,scodigo,sdescripcion,scod_tipo_operacion,sunidad_medida,iigv,itotal) "
			+ "values(#{iid_plantilla_documento}, #{id_producto},#{iitem},#{dcantidad},#{dprecio},#{dimporte},#{scodigo},#{sdescripcion},#{scod_tipo_operacion},#{sunidad_medida},#{iigv},#{itotal})")
	@SelectKey(statement = "select currval('sec_detalle_plantilla_documento') as iid_detalle_plantilla_documento", 
	keyProperty = "iid_detalle_plantilla_documento", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "iid_detalle_plantilla_documento", flushCache = FlushCachePolicy.TRUE)
	public Integer registrarDetallePlantillaDocumento(Detalle_plantilla_documento objdetallePlantillaDoc);
	
	@Delete("delete from detalle_plantilla_documento where iid_plantilla_documento=#{iid_plantilla_documento};")
	public Integer eliminarDetallePlantillaDocumento(Integer objdetallePlantillaDoc);
	
	
	
	

}
