package org.gteperu.erp.everest.mappers;


import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain._Clientes;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SecuenciaMapper {
    
    @Select( "SELECT setval('sec_secuencia',nextval('sec_secuencia'));")
    public Integer selectSecuenciaDarBaja();
    
    @Select( "SELECT setval('sec_resumenboletas',nextval('sec_resumenboletas'));")
    public Integer selectSecuenciaResumen();


}