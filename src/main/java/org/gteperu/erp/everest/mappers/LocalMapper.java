package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Local;
import org.gteperu.erp.everest.domain.Pagination;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface LocalMapper {
    @Insert(" insert into local(idempresa,nombre,direccion,estado)"
    		+ "values(#{idempresa},#{nombre},#{direccion},#{estado});")
    @SelectKey(statement = "select currval('local_seq') as id", keyProperty = "id", before = false, resultType = int.class)
    @Options(useGeneratedKeys = true, keyProperty = "id", flushCache = FlushCachePolicy.TRUE)
    public Integer insertaLocal(Local id);

    @Update("update local set nombre=#{nombre}"
    		+ ",direccion=#{direccion} where id =#{id};")
    public Integer updateLocal(Local id);
    
    @Delete(" delete from  local where id=#{id}; ")
    public Integer eliminaLocal(Local id);
    
    
    public List<Local> retornaLocalPorEstado(@Param("local") Local id);

   
    @Select( "select * from local where idempresa=#{idempresa} ;")
    public List<Local> retornaLocalTodas(Local id);
 
    @Select( "select * from local where  id=#{id};")
    public  Local  retornaLocalPorId(Local id);
    
    public Pagination retornaCantidadList(@Param("local") Local id);
}