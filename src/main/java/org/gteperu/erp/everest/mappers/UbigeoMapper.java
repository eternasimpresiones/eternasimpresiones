package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Ubigeo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface UbigeoMapper {
	
	@Select( "select distinct departamento from ubigeo order by departamento asc;")
    public List<Ubigeo> retornaObjDepartamento();

    @Select( "select distinct provincia from ubigeo where departamento=#{departamento} order by provincia asc;")
    public List<Ubigeo> retornaObjProvincia(Ubigeo idubigeo);

    @Select( "select distinct distrito ,codigo from ubigeo where departamento=#{departamento} and provincia=#{provincia} order by distrito asc")
    public List<Ubigeo> retornaObjDistrito(Ubigeo idubigeo);
        
    @Select( "select idubigeo,departamento,provincia,distrito,estado from ubigeo ;")
    public List<Ubigeo> retornaUbigeo();

    @Select( "select idubigeo,departamento,provincia,distrito,estado from ubigeo where estado='1';")
    public List<Ubigeo> retornaUbigeoTodas();

    public List<Ubigeo> retornaUbigeoPorEstado(@Param("ubigeo") Ubigeo idubigeo);

    public List<Ubigeo> retornaUbigeoLikePorNombre(@Param("ubigeo") Ubigeo idubigeo);

    @Select( "select idubigeo,departamento,provincia,distrito,estado from ubigeo where idubigeo =#{idubigeo}")
    public Ubigeo retornaObjUbigeo(Ubigeo idubigeo);
    
    @Select( "select idubigeo,departamento,provincia,distrito from ubigeo where codigo =#{codigo}")
    public Ubigeo retornaObjUbigeoxCodigo(String codigo);

    @Update("update ubigeo set  departamento=#{departamento}, provincia=#{provincia}, distrito=#{distrito}, estado=#{estado} where idubigeo =#{idubigeo};")
    public Integer updateUbigeo(Ubigeo idubigeo);

    @Insert(" insert into ubigeo(departamento,provincia,distrito,estado)values(#{departamento},#{provincia},#{distrito},#{estado});")
    public Integer insertaUbigeo(Ubigeo idubigeo);

    @Delete(" delete from  ubigeo where idubigeo=#{idubigeo}; ")
    public Integer eliminaUbigeo(Ubigeo idubigeo);
}