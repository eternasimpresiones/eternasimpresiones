package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Files;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface FilesMapper {

    @Select( "select idfile,descripcion,estado,ruta,tabla,size,nombre from files ;")
    public List<Files> retornaFiles();

    @Select( "select idfile,descripcion,estado,ruta,tabla,size,nombre from files where estado='1';")
    public List<Files> retornaFilesTodas();

    public List<Files> retornaFilesPorEstado(@Param("files") Files idfile);

    public List<Files> retornaFilesLikePorNombre(@Param("files") Files idfile);

    @Select( "select idfile,descripcion,estado,ruta,tabla,size,nombre from files where idfile =#{idfile}")
    public Files retornaObjFiles(Files idfile);

    @Update("update files set  descripcion=#{descripcion}, estado=#{estado}, ruta=#{ruta}, tabla=#{tabla}, size=#{size}, nombre=#{nombre} where idfile =#{idfile};")
    public Integer updateFiles(Files idfile);

    @Insert(" insert into files(descripcion,estado,ruta,tabla,size,nombre)values(#{descripcion},#{estado},#{ruta},#{tabla},#{size},#{nombre});")
    @SelectKey(statement = "select currval('sec_file') as idfile", keyProperty = "idfile", before = false, resultType = int.class)
    @Options(useGeneratedKeys = true, keyProperty = "idfile", flushCache =FlushCachePolicy.TRUE)
    public Integer insertaFiles(Files idfile);

    @Delete(" delete from  files where idfile=#{idfile}; ")
    public Integer eliminaFiles(Files idfile);
}