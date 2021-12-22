package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
 
import org.gteperu.erp.everest.domain._Producto;
import org.gteperu.erp.everest.domain._TipoAfecto;
import org.springframework.stereotype.Repository;

@Repository("tipoafectoMapper")
public interface TipoAfectoMapper {
	
		@Select(" select * from tipo_afecto")
		public List<_TipoAfecto> retornarTipoAfecto();
		
}