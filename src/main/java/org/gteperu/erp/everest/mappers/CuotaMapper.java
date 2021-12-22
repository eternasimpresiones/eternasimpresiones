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
import org.gteperu.erp.everest.domain.Cuota; 

public interface CuotaMapper {

	@Insert("INSERT INTO cuota("
			+ " nombre, monto_pago,fecha_cuota,id_documento)"
			+ "	VALUES ( #{nombre}, #{monto_pago} ,#{fecha_cuota},#{id_documento});")
	@SelectKey(statement = "select currval('sec_cuotas') as idcuota", keyProperty = "idcuota", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "idcuota", flushCache = FlushCachePolicy.TRUE)
	public Integer insertarCuota(Cuota s);
	
	@Update("UPDATE cuota"
			+ "	SET nombre=#{nombre}, monto_pago=#{monto_pago}, fecha_cuota=#{fecha_cuota},id_documento=#{id_documento} "
			+ "	WHERE idcuota = #{idcuota}")
	public Integer actualizarCuota(Cuota s);
	
	@Delete("DELETE FROM cuota WHERE idcuota = #{idcuota}")
	public Integer eliminarCuota(@Param("idcuota") Integer idcuota);
	
	@Select("select * from cuota where id_documento = #{id_documento}")
	public List<Cuota> listarCuotaxidocumento(@Param("id_documento") Integer id_documento);
	
}
