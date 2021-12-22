package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Userempresa;
import org.gteperu.erp.everest.domain.Users;
import org.gteperu.erp.everest.domain.Pagination;
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
public interface UserempresaMapper {


    @Insert(" insert into userempresa(id_usuarios,id_empresa) VALUES (#{id_usuarios},#{id_empresa});")
	@SelectKey(statement = "select currval('sec_userempresa') as id_user_empresa", keyProperty = "id_user_empresa", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "id_user_empresa", flushCache = FlushCachePolicy.TRUE)
    public Integer insertUserEmpr(Userempresa userempresa);
    
    @Delete(" delete from userempresa where id_usuarios=#{id_usuarios}; ")
  	public Integer eliminarUserEmpr(Userempresa userempresa); 
}