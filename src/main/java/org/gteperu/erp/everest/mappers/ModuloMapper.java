package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Modulo;
import org.gteperu.erp.everest.domain.Pagination;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface ModuloMapper {

    @Select( "select idmodulo,descripcion,icono,estado,orden from modulo ;")
    public List<Modulo> retornaModulo();
    

    @Select( "select idmodulo,descripcion,raiz,icono,estado from modulo where estado='1';")
    public List<Modulo> retornaModuloTodas();

    public List<Modulo> retornaModuloPorEstado(@Param("modulo") Modulo idmodulo);

    public List<Modulo> retornaModuloLikePorNombre(@Param("modulo") Modulo idmodulo);

    @Select( "select idmodulo,descripcion,raiz,icono,estado,orden from modulo where idmodulo =#{idmodulo}")
    public Modulo retornaObjModulo(Modulo idmodulo);

    @Update("update modulo set  descripcion=#{descripcion},raiz=#{raiz}, icono=#{icono}, estado=#{estado} where idmodulo =#{idmodulo};")
    public Integer updateModulo(Modulo idmodulo);

    @Insert(" insert into modulo(descripcion,icono,estado,raiz,orden)values(#{descripcion},#{icono},#{estado},#{raiz},#{orden});")
    public Integer insertaModulo(Modulo idmodulo);

    @Delete(" delete from  modulo where idmodulo=#{idmodulo}; ")
    public Integer eliminaModulo(Modulo idmodulo);
    
    public Pagination retornaCantidadList(@Param("modulo") Modulo idmodulo);
}