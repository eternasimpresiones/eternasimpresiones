package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Modulo;
import org.gteperu.erp.everest.domain.Pagina;
import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.Perfiles;
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
public interface PerfilesMapper {

    @Select( "select id_perfiles,nombres,estado,ambito from perfiles where estado = 1")
    public List<Perfiles> retornaPerfiles();

    @Select( "select id_perfiles,nombres,estado,ambito from perfiles where estado='1';")
    public List<Perfiles> retornaPerfilesTodas();
    
    public List<Pagina> retornaPaginasMapPorPerfil(Perfiles p);
    public List<Modulo> retornaPaginasMapPorPerfilAgrupado(Perfiles p);
    public List<Perfiles> retornaPerfilesPorEstado(@Param("perfiles") Perfiles idperfiles);
    
    public List<Perfiles> retornaPerfilesLikePorNombre(@Param("perfiles") Perfiles idperfiles);
    
    @Select( "select id_perfiles,nombres,estado,ambito from perfiles where id_perfiles =#{id_perfiles}")
    public Perfiles retornaObjPerfiles(Perfiles idperfiles);

    @Update("update perfiles set  nombres=#{nombres}, estado=#{estado}, ambito=#{ambito} where id_perfiles =#{id_perfiles};")
    public Integer updatePerfiles(Perfiles idperfiles);

    @Insert(" insert into perfiles(idempresa,nombres,estado,ambito)values(#{idempresa},#{nombres},#{estado},#{ambito});")
    @SelectKey(statement = "select currval('sec_perfiles') as id_perfiles", keyProperty = "id_perfiles", before = false, resultType = int.class)
    @Options(useGeneratedKeys = true, keyProperty = "id_perfiles", flushCache = FlushCachePolicy.TRUE)
    public Integer insertaPerfiles(Perfiles idperfiles);

    @Delete("delete from  perfiles where id_perfiles=#{id_perfiles}; ")
    public Integer eliminaPerfiles(Perfiles idperfiles);
    
    public Pagination retornaCantidadList(@Param("perfiles") Perfiles idperfiles);
}