package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Param;
import org.gteperu.erp.everest.domain.Pagodocumento;

public interface PagodocumentoMapper {

	@Insert("INSERT INTO public.pago_documento( referencia, monto, fechapago, idmetodopago, iddocumento) "
			+ "	VALUES (#{referencia}, #{monto}, #{fechapago}, #{idmetodopago}, #{iddocumento});")
    @SelectKey(statement = "select currval('sec_pago_documento') as idpagodocumento", keyProperty = "idpagodocumento", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "idpagodocumento", flushCache = FlushCachePolicy.TRUE)
    public Integer insertar(Pagodocumento pagodocumento);
	
	@Update("UPDATE public.pago_documento "
			+ "	SET referencia=#{referencia}, monto=#{monto}, fechapago=#{fechapago}, idmetodopago=#{idmetodopago}, iddocumento=#{iddocumento} "
			+ "	WHERE idpagodocumento = #{idpagodocumento}")
	public Integer actualizar(Pagodocumento pagodocumento);
	
	@Delete("DELETE from pago_documento where idpagodocumento=#{idpagodocumento};")
	public Integer eliminar(Pagodocumento pagodocumento);
	
//	@Select( "SELECT * from pago_documento where iddocumento=#{iddocumento}")
    public List<Pagodocumento> listarPorDocumento(@Param("val") Pagodocumento pagodocumento);
}
