package org.gteperu.erp.everest.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;
import org.gteperu.erp.everest.domain.Codigo_qr;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;

public interface Codigo_qrMapper {

	 @Insert(" insert into codigo_qr(id_documento,"
	    		+ "valor)"
	    		+ "values(#{id_documento},#{valor});")

	    @SelectKey(statement = "select currval('sec_codigo_qr') as id", keyProperty = "id", before = false, resultType = int.class)
		@Options(useGeneratedKeys = true, keyProperty = "id", flushCache = FlushCachePolicy.TRUE)
	    public Integer insertarCodigoqr(Codigo_qr codigoqr);
}
