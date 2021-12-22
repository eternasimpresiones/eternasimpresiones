package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.gteperu.erp.everest.domain.Tipomoneda;

@Mapper
public interface TipoMonedaMapper {

   
    @Select( "select * from tipomoneda where estado='1';")
    public List<Tipomoneda> retornaTipoMonedaTodas();

    }