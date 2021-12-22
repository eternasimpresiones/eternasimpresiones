package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Perfilespaginas;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface PerfilespaginasMapper {

    @Select( "select perfilesespaginas,id_perfiles,idpagina,estado from perfilespaginas ;")
    public List<Perfilespaginas> retornaPerfilespaginas();

    @Select( "select perfilesespaginas,id_perfiles,idpagina,estado from perfilespaginas where estado='1';")
    public List<Perfilespaginas> retornaPerfilespaginasTodas();

    @Select( "select perfilesespaginas,idagrupadormodulos,id_perfiles,idpagina,estado from perfilespaginas where id_perfiles=#{id_perfiles};")
    public List<Perfilespaginas> retornaPerfilespaginasPorPerfil(Perfilespaginas p);

    public List<Perfilespaginas> retornaPerfilespaginasPorEstado(@Param("perfilespaginas") Perfilespaginas perfilesespaginas);

    public List<Perfilespaginas> retornaPerfilespaginasLikePorNombre(@Param("perfilespaginas") Perfilespaginas perfilesespaginas);

    @Select( "select perfilesespaginas,id_perfiles,idpagina,estado from perfilespaginas where idperfilesespaginas =#{idperfilesespaginas}")
    public Perfilespaginas retornaObjPerfilespaginas(Perfilespaginas idperfilesespaginas);

    @Update("update perfilespaginas set  id_perfiles=#{id_perfiles}, idpagina=#{idpagina}, estado=#{estado} where idperfilesespaginas =#{idperfilesespaginas};")
    public Integer updatePerfilespaginas(Perfilespaginas idperfilesespaginas);

    @Insert(" insert into perfilespaginas(id_perfiles,idpagina,estado,idagrupadormodulos)values(#{id_perfiles},#{idpagina},#{estado},#{idagrupadormodulos});")
    public Integer insertaPerfilespaginas(Perfilespaginas perfilesespaginas);
public Integer 	insertaPerfilespaginaslist(@Param("list")List<Perfilespaginas> perfilesespaginas);
    @Delete(" delete from  perfilespaginas where idperfilesespaginas=#{idperfilesespaginas}; ")
    public Integer eliminaPerfilespaginas(Perfilespaginas idperfilesespaginas);

    @Select( "select idperfilesespaginas,id_perfiles,idpagina,estado from perfilespaginas where id_perfiles =#{id_perfiles} and idpagina=#{idpagina} and idagrupadormodulos=#{idagrupadormodulos}")
    public Perfilespaginas retornaObjPerfilespaginasPorIdPerIdPagina(Perfilespaginas idperfilesespaginas);
}