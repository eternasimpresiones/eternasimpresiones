package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Tokenslicitaciones;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

import org.apache.ibatis.annotations.Delete;

@Mapper
// @Service("tokenlicitacionesService")
public interface TokenLicitacionesMapper {

	/*
	 * @Insert("insert into tokenslicitaciones (token) values (#{token});") public
	 * Integer insertaTokenlicitaciones(tokenslicitaciones idtokenlicitaciones);
	 */
	@Insert("insert into tokenslicitaciones"
			+ "(  idproveedor ,  token ,  estado ,  idpresupuesto ,  fecharegistro, password,duracion) values"
			+ "(#{idproveedor},#{token},#{estado},#{idpresupuesto},#{fecharegistro},#{password},#{duracion})  ;")
	public Integer insertaTokenlicitaciones(Tokenslicitaciones idtokenlicitaciones);

	@Delete("delete from  tokenslicitaciones where idtokenslicitaciones=#{idtokenslicitaciones}; ")
	public Integer eliminaTokenlicitaciones(Tokenslicitaciones idtokenlicitaciones);

	public List<Tokenslicitaciones> retornaPresupuestoPorPassword(@Param("pres") Tokenslicitaciones t);

	// @Select( "select
	// idtokenslicitaciones,fecharegistro,duracion,token,password,estado from
	// tokenslicitaciones"
	@Select("select password,estado,token,estado,idpresupuesto,fecharegistro from tokenslicitaciones"
			+ " where idproveedor=#{idproveedor} and idpresupuesto=#{idpresupuesto} and estado='1' ;")
	public Tokenslicitaciones retornaTokenPorProveedor(Tokenslicitaciones t);

	@Select("select idtokenslicitaciones, idproveedor, duracion, token, idpresupuesto,   fecharegistro, estado from tokenslicitaciones"
			+ " where password=#{password} and estado='1' ;")
	public Tokenslicitaciones retornaTokenActivo(Tokenslicitaciones t);
	// cada proveedor puede tener varios tokens en la BD pero solo 1 puede tener
	// estado = 1 , los demas tienen que tener estado=0

	@Update("update tokenslicitaciones set  estado='0' where idpresupuesto=#{idpresupuesto}  ")
	public Integer anulaTokenlicitaciones(Tokenslicitaciones idtokenpassword);

	@Update("update tokenslicitaciones set  estado=#{estado} where token=#{token};")
	public Integer consumoTokenlicitaciones(Tokenslicitaciones idtokenpassword);

	@Update("update tokenslicitaciones set  estado=#{estado} where idproveedor=#{idproveedor} and idpresupuesto=#{idpresupuesto};")
	public Integer consumoTokenlicitaciones2(Tokenslicitaciones idtokenpassword);

}
