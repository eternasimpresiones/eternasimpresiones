package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.gteperu.erp.everest.domain.Banco;
import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain._ProductoCodigoSunat;

@Mapper
public interface BancoMapper {

    @Select( "select idbanco,nombre,estado,fecharegistro from banco ;")
    public List<_ProductoCodigoSunat> retornaBanco();

    @Select( "select * from banco where estado='1';")
    public List<Banco> retornaBancoTodas();

    public List<_ProductoCodigoSunat> retornaBancoPorEstado(@Param("banco") _ProductoCodigoSunat idbanco);

    public List<_ProductoCodigoSunat> retornaBancoLikePorNombre(@Param("banco") _ProductoCodigoSunat idbanco);

    @Select( "select idbanco,nombre,estado,fecharegistro from banco where idbanco =#{idbanco}")
    public _ProductoCodigoSunat retornaObjBanco(_ProductoCodigoSunat idbanco);

    @Update("update banco set  nombre=#{nombre}, estado=#{estado} where idbanco =#{idbanco};")
    public Integer updateBanco(_ProductoCodigoSunat idbanco);

    @Insert(" insert into banco(nombre,estado)values(#{nombre},#{estado});")
    public Integer insertaBanco(_ProductoCodigoSunat idbanco);

    @Delete(" delete from  banco where idbanco=#{idbanco}; ")
    public Integer eliminaBanco(_ProductoCodigoSunat idbanco);
public Pagination retornaCantidadLike(@Param("banco") _ProductoCodigoSunat idbanco);
    public Pagination retornaCantidadList(@Param("banco") _ProductoCodigoSunat idbanco);
}