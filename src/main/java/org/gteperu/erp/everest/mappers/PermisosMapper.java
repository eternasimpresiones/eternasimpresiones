package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.Permisos;
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
public interface PermisosMapper {

    @Select( "select idpermisos,idempleado,idmotivopermiso,descripcion,estado,fechapermiso,fecharegistro from permisos ;")
    public List<Permisos> retornaPermisos();

    @Select( "select idpermisos,idempleado,idmotivopermiso,descripcion,estado,fechapermiso,fecharegistro from permisos where estado='1';")
    public List<Permisos> retornaPermisosTodas();

    public List<Permisos> retornaPermisosPorEstado(@Param("permisos") Permisos idpermisos);

    public List<Permisos> retornaPermisosLikePorNombre(@Param("permisos") Permisos idpermisos);

    @Select( "select idpermisos,idempleado,aprobado,idmotivopermiso,descripcion,estado,fechapermiso,fecharegistro from permisos where idpermisos =#{idpermisos}")
    public Permisos retornaObjPermisos(Permisos idpermisos);

    @Update("update permisos set  idempleado=#{idempleado}, idmotivopermiso=#{idmotivopermiso}, descripcion=#{descripcion}, estado=#{estado}, fechapermiso=#{fechapermiso}, fecharegistro=#{fecharegistro} where idpermisos =#{idpermisos};")
    public Integer updatePermisos(Permisos idpermisos);

    @Update("update permisos set  aprobado=#{aprobado},comentarioaprobacion=#{comentarioaprobacion} ,fechaaprobacion=#{fechaaprobacion},usuario=#{usuario} where idpermisos =#{idpermisos};")
    public Integer updatePermisosAprobar(Permisos idpermisos);

    @Insert(" insert into permisos(aprobado,idempleado,idmotivopermiso,descripcion,estado,fechapermiso)values"
    + "(0,#{idempleado},#{idmotivopermiso},#{descripcion},#{estado},#{fechapermiso});")
    @SelectKey(statement = "select currval('sec_permisos') as idpermisos", keyProperty = "idpermisos", before = false, resultType = int.class)
    @Options(useGeneratedKeys = true, keyProperty = "idpermisos", flushCache = FlushCachePolicy.TRUE)
    public Integer insertaPermisos(Permisos idpermisos);

    @Delete(" delete from  permisos where idpermisos=#{idpermisos}; ")
    public Integer eliminaPermisos(Permisos idpermisos);
    
    public Pagination retornaCantidadList(@Param("permisos") Permisos idpermisos);
}