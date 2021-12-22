package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain._ProductoCodigoSunat;
import org.gteperu.erp.everest.domain.Auditoria_Sunat;
import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.Tipo_Operacion_Sunat;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface TipoOperacionSunatMapper {

    @Select( "select id_tipo_operacion_sunat,descripcion,codigo from tipo_operacion_sunat where codigo=#{codigo}")
    public Tipo_Operacion_Sunat retornaObjTipoOperacionSunatPorCodigo(Tipo_Operacion_Sunat tipo_Operacion_Sunat);
}