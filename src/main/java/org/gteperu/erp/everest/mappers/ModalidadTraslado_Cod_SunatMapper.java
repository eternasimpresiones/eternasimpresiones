package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.gteperu.erp.everest.domain._ModalidadTrasladoCodigoSunat;
import org.gteperu.erp.everest.domain._ProductoCodigoSunat;
import org.springframework.stereotype.Repository;

@Repository("modalidadtraslado_cod_sunatMapper")
public interface ModalidadTraslado_Cod_SunatMapper {
	
		@Select("select * from modalidad_traslado_cod_sunat")
		public List<_ModalidadTrasladoCodigoSunat> retornarModalidadTrasladoCodigoSunat(_ModalidadTrasladoCodigoSunat ModalidadTrasladoCodigoSunat);
		
}