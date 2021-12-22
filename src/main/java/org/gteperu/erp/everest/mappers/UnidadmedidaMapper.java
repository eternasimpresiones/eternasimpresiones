package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.Unidades_medida;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface UnidadmedidaMapper {

//    @Select( "select idunidadmedida,codigo,descripcion,cantidad,estado,fecharegistro from unidadmedida ;")
//    public List<Unidadmedida> retornaUnidadmedida();
//
//    @Select( "select idunidadmedida,codigo,descripcion,cantidad,estado,fecharegistro from unidadmedida where estado='1';")
//    public List<Unidadmedida> retornaUnidadmedidaTodas();
//
//    public List<Unidadmedida> retornaUnidadmedidaPorEstado(@Param("unidadmedida") Unidadmedida idunidadmedida);
//
//    public List<Unidadmedida> retornaUnidadmedidaLikePorNombre(@Param("unidadmedida") Unidadmedida idunidadmedida);
//
//    public List<Unidadmedida> retornaUnidadmedidaPorLista(@Param("ls") List<String> idunidadmedida);
//
//    @Select( "select idunidadmedida,codigo,descripcion,cantidad,estado,fecharegistro from unidadmedida where idunidadmedida =#{idunidadmedida}")
//    public Unidadmedida retornaObjUnidadmedida(Unidadmedida idunidadmedida);
//
//    @Update("update unidadmedida set  codigo=#{codigo}, descripcion=#{descripcion}, cantidad=#{cantidad}, estado=#{estado} where idunidadmedida =#{idunidadmedida};")
//    public Integer updateUnidadmedida(Unidadmedida idunidadmedida);
//
//    @Insert(" insert into unidadmedida(codigo,descripcion,cantidad,estado)values(#{codigo},#{descripcion},#{cantidad},#{estado});")
//    public Integer insertaUnidadmedida(Unidadmedida idunidadmedida);
//
//    @Delete(" delete from  unidadmedida where idunidadmedida=#{idunidadmedida}; ")
//    public Integer eliminaUnidadmedida(Unidadmedida idunidadmedida);
//    
//    public Pagination retornaCantidadList(@Param("unidadmedida") Unidadmedida idunidadmedida);
//    
//    
    ////////Mapper del otro backend
    
    
    
    
	@Select("select id_unidad_med,desc_unidad_med,abrv_unidad_med from unidades_medida;")
	public List<Unidades_medida> retornaAllUnidadesMedida();
	
	@Select("select id_unidad_med,desc_unidad_med,abrv_unidad_med from unidades_medida where id_unidad_med=#{id_unidad_med};")
	public List<Unidades_medida> retornaUnidadesMedida(Unidades_medida unidadesMedida);
	
	@Update("update unidades_medida set  id_unidad_med=#{id_unidad_med}, desc_unidad_med=#{desc_unidad_med}, abrv_unidad_med=#{abrv_unidad_med} where id_unidad_med =#{id_unidad_med};")
	public Integer updateUnidadesMedida(Unidades_medida unidadesMedida);
			
	@Insert(" insert into unidades_medida(id_unidad_med,desc_unidad_med,abrv_unidad_med)values(#{id_unidad_med},#{desc_unidad_med},#{abrv_unidad_med});")
	public Integer insertaUnidadesMedida(Unidades_medida unidadesMedida);

//	@Delete(" delete from  unidades_medida where coduni=#{coduni}; ")
//	public Integer eliminaUnidadesMedida(Unidades_medida unidadesMedida);
}