package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.gteperu.erp.everest.domain.Tipodetraccion;

@Mapper
public interface TipodetraccionMapper {

    @Select( "select * from tipodetraccion ;")
    public List<Tipodetraccion> retornaBanco();

}