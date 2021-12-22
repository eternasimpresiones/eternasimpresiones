package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Fileconceptos;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface FileconceptosMapper {

    @Select( "select idfileconceptos,principal,idfile,idconceptos,estado from fileconceptos ;")
    public List<Fileconceptos> retornaFileconceptos();

    @Select( "select idfileconceptos,principal,idfile,idconceptos,estado from fileconceptos where estado='1';")
    public List<Fileconceptos> retornaFileconceptosTodas();

    public List<Fileconceptos> retornaFileconceptosPorEstado(@Param("fileconceptos") Fileconceptos idfileconceptos);

    public List<Fileconceptos> retornaFileconceptosLikePorNombre(@Param("fileconceptos") Fileconceptos idfileconceptos);

    @Select( "select idfileconceptos,principal,idfile,idconceptos,estado from fileconceptos where idfileconceptos =#{idfileconceptos}")
    public Fileconceptos retornaObjFileconceptos(Fileconceptos idfileconceptos);

    @Update("update fileconceptos set  principal=#{principal}, idfile=#{idfile}, idconceptos=#{idconceptos}, estado=#{estado} where idfileconceptos =#{idfileconceptos};")
    public Integer updateFileconceptos(Fileconceptos idfileconceptos);
    
    @Update("update fileconceptos set  principal=0 where idconceptos=#{idconceptos};")
    public Integer updateFileconceptosPrincipal(Fileconceptos idfileconceptos);

    @Insert(" insert into fileconceptos(principal,idfile,idconceptos,estado)values(#{principal},#{idfile},#{idconceptos},#{estado});")
    public Integer insertaFileconceptos(Fileconceptos idfileconceptos);

    @Delete(" delete from  fileconceptos where idfileconceptos=#{idfileconceptos}; ")
    public Integer eliminaFileconceptos(Fileconceptos idfileconceptos);
    
    @Delete(" update  fileconceptos set principal=0   where idconceptos=#{idconceptos}; ")
    public Integer updateprincipalTodosFileconceptos(Fileconceptos idfileconceptos);
    
 @Delete(" update  fileconceptos set principal=1   where idfileconceptos=#{idfileconceptos}; ")
    public Integer updateprincipalFileconceptos(Fileconceptos idfileconceptos);
    public List<Fileconceptos> retornaFileconceptosPorConcepto(@Param("p") Fileconceptos f);
}