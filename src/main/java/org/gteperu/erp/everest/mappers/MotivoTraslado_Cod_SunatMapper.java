package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.gteperu.erp.everest.domain._ModalidadTrasladoCodigoSunat;
import org.gteperu.erp.everest.domain._MotivoTrasladoCodigoSunat;
import org.gteperu.erp.everest.domain._ProductoCodigoSunat;
import org.springframework.stereotype.Repository;

@Repository("motivotraslado_cod_sunatMapper")
public interface MotivoTraslado_Cod_SunatMapper {
	
		@Select("select * from motivos_traslado_cod_sunat")
		public List<_MotivoTrasladoCodigoSunat> retornarMotivoTrasladoCodigoSunat(_MotivoTrasladoCodigoSunat MotivoTrasladoCodigoSunat);
		
}