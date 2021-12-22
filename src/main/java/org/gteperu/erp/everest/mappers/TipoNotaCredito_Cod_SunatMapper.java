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
import org.gteperu.erp.everest.domain._TipoNotaCreditoCodigoSunat;
import org.gteperu.erp.everest.domain._TipoNotaDebitoCodigoSunat;
import org.springframework.stereotype.Repository;

@Repository("tipo_nota_credito_cod_sunatMapper")
public interface TipoNotaCredito_Cod_SunatMapper {
	
		@Select("select * from tipo_nota_credito_cod_sunat")
		public List<_TipoNotaCreditoCodigoSunat> retornarTipoNotaCredito_Cod_Sunat();
		
}