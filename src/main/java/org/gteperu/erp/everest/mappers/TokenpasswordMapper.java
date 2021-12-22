package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Tokenpassword;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface TokenpasswordMapper {

   

    @Select( "select idtokenpassword,idusuario,fechahorainicio,fechahorafin,token,estado from tokenpassword where token=#{token} and tipo=#{tipo};")
    public Tokenpassword retornaTokenObjeto(Tokenpassword t);

    @Select( "select idtokenpassword,idusuario,fechahorainicio,fechahorafin,token,estado from tokenpassword where idtokenpassword =#{idtokenpassword}")
    public List<Tokenpassword> retornaObjTokenpassword(Tokenpassword idtokenpassword);

    @Update("update tokenpassword set  idusuario=#{idusuario}, fechahorainicio=#{fechahorainicio}, fechahorafin=#{fechahorafin}, token=#{token}, estado=#{estado} where idtokenpassword =#{idtokenpassword} ;")
    public Integer updateTokenpassword(Tokenpassword idtokenpassword);

    @Update("update tokenpassword set  idusuario=#{idusuario}, fechaconsumo=#{fechaconsumo}, fechahorafin=#{fechahorafin},  estado=#{estado} where token=#{token} and tipo=#{tipo};")
    public Integer consumoTokenpassword(Tokenpassword idtokenpassword);

    @Update("update tokenpassword set     estado='0' where idusuario=#{idusuario} and tipo=#{tipo};")
    public Integer eliminaTokenpasswordGlobal(Tokenpassword idtokenpassword);

    @Insert("insert into tokenpassword(tipo,idusuario,fechahorainicio,fechahorafin,token,estado)values(#{tipo},#{idusuario},#{fechahorainicio},#{fechahorafin},#{token},#{estado});")
    public Integer insertaTokenpassword(Tokenpassword idtokenpassword);

    @Delete("delete from  tokenpassword where idtokenpassword=#{idtokenpassword}; ")
    public Integer eliminaTokenpassword(Tokenpassword idtokenpassword);
}