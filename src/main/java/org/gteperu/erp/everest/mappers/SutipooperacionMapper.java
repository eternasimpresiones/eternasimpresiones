package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.gteperu.erp.everest.domain.Sutipodocumento;
import org.gteperu.erp.everest.domain.Sutipooperacion;
import org.gteperu.erp.everest.domain._Producto;
import org.springframework.stereotype.Repository;

@Repository("sutipooperacionMapper")
public interface SutipooperacionMapper {
		@Select(" select * from sutipooperacion where estado=#{estado} ")
		public List<Sutipooperacion> retornaSutipooperacionPorEstado(Sutipooperacion sutipooperacion);

		@Delete(" delete from  sutipooperacion where id_sutipooperacion=#{id_sutipooperacion}; ")
		public Integer eliminaProducto(Sutipooperacion sutipooperacion );
		
}