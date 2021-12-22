package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain._TipoDocumento;
import org.gteperu.erp.everest.domain._TipoAfecto;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
public interface AgrupadormodulospaginasMapper {

    @Insert("insert into agrupadormodulospaginas (idagrupadormodulos,idpagina,estado)values(#{idagrupadormodulos},#{idpagina},#{estado});")
    public Integer insertaAgrupadormodulospaginas(_TipoAfecto agrupadormodulospaginas);

    @Update("update agrupadormodulospaginas set  idagrupadormodulos=#{idagrupadormodulos}, idpagina=#{idpagina}, estado=#{estado} where idagrupadormodulospaginas =#{idagrupadormodulospaginas};")
    public Integer actualizaAgrupadormodulospaginas(_TipoAfecto agrupadormodulospaginas);

    @Delete("delete from  agrupadormodulospaginas where idagrupadormodulospaginas =#{idagrupadormodulospaginas}; ")
    public Integer eliminaAgrupadormodulospaginas(_TipoAfecto agrupadormodulospaginas);

    @Select("select idagrupadormodulospaginas,idagrupadormodulos,idpagina,estado from agrupadormodulospaginas where idagrupadormodulospaginas =#{idagrupadormodulospaginas}")
    public _TipoAfecto retornaObjAgrupadormodulospaginas(_TipoAfecto agrupadormodulospaginas);

    @Select("select idagrupadormodulospaginas,idagrupadormodulos,idpagina,estado from agrupadormodulospaginas ;")
    public List<_TipoAfecto> retornaAgrupadormodulospaginas();

    public List<_TipoAfecto> retornaAgrupadormodulospaginasPorModuloList(@Param("list")List<_TipoDocumento> lsagr);

    @Select("select idagrupadormodulospaginas,idagrupadormodulos,idpagina,estado from agrupadormodulospaginas where estado = '1';")
    public List<_TipoAfecto> retornaAgrupadormodulospaginasActivas();

    public List<_TipoAfecto> retornaAgrupadormodulospaginasPorEstado(_TipoAfecto agrupadormodulospaginas);

    public List<_TipoAfecto> retornaAgrupadormodulospaginasLikePorNombre(_TipoAfecto agrupadormodulospaginas);

    @Select("select idagrupadormodulospaginas,idagrupadormodulos,idpagina,estado from agrupadormodulospaginas where idagrupadormodulos =#{idagrupadormodulos} and idpagina=#{idpagina}")
    public _TipoAfecto retornaObjAgrupadormodulospaginasPorIdAgruIdPagina(_TipoAfecto agrupadormodulospaginas);
}
