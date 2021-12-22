package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.PorcentajeDetraccion;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface PorcentajeDetraccionMapper {

    @Select( "select id_porcentaje_detraccion,descripcion,porcentaje from porcentaje_detraccion")
    public List<PorcentajeDetraccion> listarPorcentajeDetraccion();

}