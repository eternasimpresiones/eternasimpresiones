package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Pagina;
import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.Perfiles;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface PaginaMapper {

    @Select( "select idpagina,idmodulo,descripcion,url,parametros,icono,estado from pagina ;")
    public List<Pagina> retornaPagina();

    @Select( "select idpagina,idmodulo,descripcion,url,parametros,icono,estado from pagina where estado='1';")
    public List<Pagina> retornaPaginaTodas();

    public List<Pagina> retornaPaginaPorEstado(@Param("pagina") Pagina idpagina);

    public List<Pagina> retornaPaginaLikePorNombre(@Param("pagina") Pagina idpagina);

    @Select( "select idpagina,idmodulo,descripcion,url,parametros,icono,estado from pagina where idpagina =#{idpagina}")
    public Pagina retornaObjPagina(Pagina idpagina);

    @Update("update pagina set  idmodulo=#{idmodulo}, descripcion=#{descripcion}, url=#{url}, parametros=#{parametros}, icono=#{icono}, estado=#{estado} where idpagina =#{idpagina};")
    public Integer updatePagina(Pagina idpagina);

    @Insert(" insert into pagina(idmodulo,descripcion,url,parametros,icono,estado)values(#{idmodulo},#{descripcion},#{url},#{parametros},#{icono},#{estado});")
    public Integer insertaPagina(Pagina idpagina);

    @Delete(" delete from  pagina where idpagina=#{idpagina}; ")
    public Integer eliminaPagina(Pagina idpagina);

    public List<Pagina> retornaPaginasMapPorPerfil(Perfiles p);
    
    public Pagination retornaCantidadList(@Param("pagina") Pagina idpagina);
}