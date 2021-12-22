package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.Tipodecambio;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
public interface TipodecambioMapper {

    public ArrayList<Tipodecambio> retornaTodoTipodecambio();

    public List<Tipodecambio> retornaTipocambioPorEstado(@Param("tipocambio") Tipodecambio idtipocambio);

    public Tipodecambio retornaTodoTipodecambioPorId(@Param("tipocambio") Tipodecambio tipodecambio);

    public Tipodecambio retornaTodoTipodecambioActual(@Param("tipocambio") Tipodecambio tipodecambio);

    public Tipodecambio retornaTodoTipodecambioPorFechayMoneda(@Param("tipocambio") Tipodecambio tipodecambio);

    @Update("UPDATE tipocambio SET compra=#{compra},idcuenta=#{idcuenta},fechapublicacion=#{fechapublicacion}, monedaorigen=#{monedaorigen}, monedadestino=#{monedadestino}, precio=#{precio}, tipo=#{tipo} WHERE idtipocambio=#{idtipocambio};")
    public Integer updateTipodecambio(Tipodecambio tipodecambio);

    @Insert("INSERT INTO tipocambio( compra,monedaorigen, monedadestino,idcuenta, precio,fechapublicacion,tipo,estado) VALUES( #{compra},#{monedaorigen}, #{monedadestino},#{idcuenta}, #{precio},#{fechapublicacion},#{tipo},#{estado} );")
    public Integer insertaTipodecambio(Tipodecambio tipodecambio);

    @Delete("delete from tipocambio WHERE idtipocambio=#{idtipocambio} ;")
    public Integer eliminaTipodecambio(Tipodecambio tipodecambio);

    public ArrayList<Tipodecambio> retornaTipodecambioLikePorNombre(@Param("fil") Tipodecambio tipodecambio);
    
    public Pagination retornaCantidadList(@Param("tipocambio") Tipodecambio tipodecambio);
}
