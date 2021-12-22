package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.gteperu.erp.everest.domain.Tipocuentabancaria;

@Mapper
public interface TipoCuentaBancariaMapper {

   
    @Select( "select * from tipocuentabancaria where estado='1';")
    public List<Tipocuentabancaria> retornaTipocuentabancariaTodas();

    }