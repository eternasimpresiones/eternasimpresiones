package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.Subgrupo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Delete;
import java.util.List;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface SubgrupoMapper {

    @Select( "select idsubgrupo,idgrupo,codigo,descripcion,estado,fecharegistro from subgrupo ;")
    public List<Subgrupo> retornaSubgrupo();

    @Select( "select idsubgrupo,idgrupo,codigo,descripcion,estado,fecharegistro from subgrupo where estado='1';")
    public List<Subgrupo> retornaSubgrupoTodas();

    public Subgrupo retornaSubgrupoPorId(@Param("subgrupo") Subgrupo idsubgrupo);

    public List<Subgrupo> retornaSubgrupoPorGrupo(@Param("subgrupo") Subgrupo idsubgrupo);

    public List<Subgrupo> retornaSubgrupoPorEstado(@Param("subgrupo") Subgrupo idsubgrupo);

    public List<Subgrupo> retornaSubgrupoPorList(@Param("ls") List<String> idsubgrupo,@Param("idempresa")Integer idempresa);

    public List<Subgrupo> retornaSubgrupoLikePorNombre(@Param("subgrupo") Subgrupo idsubgrupo);

 
    public Subgrupo retornaObjSubgrupo(@Param("subgrupo") Subgrupo idsubgrupo);

    @Update("update subgrupo set  idgrupo=#{idgrupo}, codigo=#{codigo}, descripcion=#{descripcion}, estado=#{estado} where idsubgrupo =#{idsubgrupo};")
    public Integer updateSubgrupo(Subgrupo idsubgrupo);

    @Insert(" insert into subgrupo(idgrupo,codigo,descripcion,estado)values(#{idgrupo},#{codigo},#{descripcion},#{estado});")
    @SelectKey(statement = "select currval('sec_subgrupo') as idsubgrupo", keyProperty = "idsubgrupo", before = false, resultType = int.class)
    @Options(useGeneratedKeys = true, keyProperty = "subgrupo", flushCache = FlushCachePolicy.TRUE)
    public Integer insertaSubgrupo(Subgrupo idsubgrupo);

    @Delete(" delete from  subgrupo where idsubgrupo=#{idsubgrupo}; ")
    public Integer eliminaSubgrupo(Subgrupo idsubgrupo);
    
    public Pagination retornaCantidadList(@Param("subgrupo") Subgrupo idsubgrupo);
}