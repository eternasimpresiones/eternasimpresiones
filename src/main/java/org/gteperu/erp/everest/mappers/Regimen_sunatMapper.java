package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.gteperu.erp.everest.domain.Regimen_sunat;

public interface Regimen_sunatMapper {

	@Insert(" insert into regimen_sunat(codigo,descripcion,porcentaje,grupo)"
			+ "values(#{codigo},#{descripcion},#{porcentaje},#{grupo});")
    @SelectKey(statement = "select currval('sec_regimen_sunat') as id_regimen_sunat", keyProperty = "id_regimen_sunat", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "id_cliente", flushCache = FlushCachePolicy.TRUE)
    public Integer insertRegimen(Regimen_sunat regimen);
	
	@Select( "select*from regimen_sunat where grupo=#{grupo};")
    public List<Regimen_sunat> listarRegimen(Regimen_sunat cod_cliente);
	
}
