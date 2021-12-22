package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.Serie; 

public interface SerieMapper {

	@Insert("INSERT INTO serie("
			+ "	idsutipodocumento, sserie, snumero,idlocal)"
			+ "	VALUES (#{idsutipodocumento}, #{sserie}, #{snumero} ,#{idlocal});")
	@SelectKey(statement = "select currval('sec_serie') as iidserie", keyProperty = "iidserie", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "iidserie", flushCache = FlushCachePolicy.TRUE)
	public Integer insertarSerie(Serie s);
	
	@Update("UPDATE serie"
			+ "	SET idsutipodocumento=#{idsutipodocumento}, sserie=#{sserie}, snumero=#{snumero} "
			+ "	WHERE iidserie = #{iidserie}")
	public Integer actualizarSerie(Serie s);
	
	@Delete("DELETE FROM serie WHERE iidserie = #{iidserie}")
	public Integer eliminarSerie(@Param("iidserie") Integer id);
	
//	@Select("SELECT * FROM serie")
	public List<Serie> listarSerie(@Param("val") Serie val);
	
	@Select("select count(*) as cantidad from serie where idlocal = #{idlocal}")
	public Pagination listarcantidadSerie(Serie val);
	
	@Select("SELECT * FROM serie where idsutipodocumento = #{id}")
	public Serie retornaSeriexTipoDoc(@Param("id") Integer id);
	
	public Serie retornaSeriexTipoDocLocal(@Param("idlocal") Integer idlocal,@Param("idsutipodocumento") Integer idsutipodocumento);
	
	
	@Select("select * from serie where idlocal = #{idlocal} and idsutipodocumento=#{idsutipodocumento}")
	public List<Serie> listarSerieRepedita(Serie val);
	
	@Select("SELECT * FROM serie where idsutipodocumento = #{idsutipodocumento} and idlocal = #{idlocal}")
	public Serie retornaSeriexTipoDocyLocal(Serie val);

}
