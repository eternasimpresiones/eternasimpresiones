package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Userperfiles;
import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.Userempresa;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Delete;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserperfilesMapper {


    @Insert(" insert into userperfiles(id_perfiles,id_usuarios,estado) VALUES (#{id_perfiles},#{id_usuarios},#{estado});")
	@SelectKey(statement = "select currval('sec_userperfiles') as id_user_perfiles", keyProperty = "id_user_perfiles", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "id_user_perfiles", flushCache = FlushCachePolicy.TRUE)
    public Integer insertUserperf(Userperfiles userperfiles);
    
    @Update("update userperfiles set id_perfiles=#{id_perfiles} where id_usuarios=#{id_usuarios};")
	public Integer actualizarUserperfil(Userperfiles userperfiles);
    
    
    @Delete(" delete from userperfiles where id_usuarios=#{id_usuarios}; ")
  	public Integer eliminarUserPerfil(Userperfiles userperfiles);
    

}