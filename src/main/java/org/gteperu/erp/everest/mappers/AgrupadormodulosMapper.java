package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain._TipoDocumento;
import org.gteperu.erp.everest.domain.Empleado;
import org.gteperu.erp.everest.domain.Pagina;
import org.gteperu.erp.everest.domain.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.springframework.stereotype.Repository;

@Mapper
public interface AgrupadormodulosMapper {

	@Insert("insert into agrupadormodulos(dash,nombre,ambito,estado,orden)values(#{dash},#{nombre},#{ambito},#{estado},#{orden});")
	@SelectKey(statement = "select currval('sec_agrupadormodulos') as idagrupadormodulos", keyProperty = "idagrupadormodulos", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "idagrupadormodulos", flushCache = FlushCachePolicy.TRUE)
	public Integer insertaAgrupadormodulos(_TipoDocumento agrupadormodulos);

	@Update("update agrupadormodulos set dash=#{dash}, nombre=#{nombre}, ambito=#{ambito}, estado=#{estado}, orden=#{orden} where idagrupadormodulos =#{idagrupadormodulos};")
	public Integer actualizaAgrupadormodulos(_TipoDocumento agrupadormodulos);

	@Delete("delete from  agrupadormodulos where idagrupadormodulos =#{idagrupadormodulos};")
	public Integer eliminaAgrupadormodulos(_TipoDocumento agrupadormodulos);

	@Select("select idagrupadormodulos,dash,nombre,ambito,estado,orden from agrupadormodulos where idagrupadormodulos =#{idagrupadormodulos}")
	public _TipoDocumento retornaObjAgrupadormodulos(_TipoDocumento agrupadormodulos);

	@Select("select idagrupadormodulos,dash,nombre,ambito,estado,orden from agrupadormodulos")
	public List<_TipoDocumento> retornaAgrupadormodulos();

	@Select("select idagrupadormodulos,dash,nombre,ambito,estado,orden from agrupadormodulos where estado = '1'")
	public List<_TipoDocumento> retornaAgrupadormodulosActivas();

	@Select("SELECT a.estado,a.ambito,a.dash,a.orden,p.idagrupadormodulos,a.nombre FROM empleadoperfiles gp left join perfilespaginas p on p.idperfil=gp.idperfiles"
			+ " left join pagina pa on pa.idpagina=p.idpagina and pa.estado='1' left join modulo m on m.idmodulo=pa.idmodulo "
			+ "left join agrupadormodulos a on a.idagrupadormodulos=p.idagrupadormodulos where gp.idempleado=#{id} and gp.estado=1"
			+ "group by a.orden,p.idagrupadormodulos,a.nombre,a.estado,a.ambito,a.dash order by 4 asc; ")
	public List<_TipoDocumento> retornaAgrupadormodulosPorempleado(Empleado e);

	@Select("  SELECT a.estado,a.ambito,a.dash,a.orden,pc.idagrupadormodulos,a.nombre,a.publico FROM  empleado gp  "
			+ "left join empresa e on e.idempresa=gp.idempresa " + "left join cuenta c on c.idcuenta=e.idcuenta "
			+ "left join planescuentamodulo pc on pc.idplanescuenta=c.idplanescuenta "
			+ "left join agrupadormodulos a on a.idagrupadormodulos=pc.idagrupadormodulos  "
			+ "where gp.id=#{id} and a.estado=1 group by pc.idagrupadormodulos,a.nombre,a.estado,a.ambito,a.orden,a.dash,a.publico order by 4 asc;  ")
	public List<_TipoDocumento> retornaAgrupadormodulosPorCuentaadministrativa(Empleado e);

	@Select("  SELECT a.estado,a.ambito,a.dash,a.orden,pc.idagrupadormodulos,a.nombre,a.publico FROM  empleado gp  "
			+ "left join empresa e on e.idempresa=gp.idempresa " + "left join cuenta c on c.idcuenta=e.idcuenta "
			+ "left join planescuentamodulo pc on pc.idplanescuenta=c.idplanescuenta "
			+ "left join agrupadormodulos a on a.idagrupadormodulos=pc.idagrupadormodulos  "
			+ "where gp.id=#{id} and a.publico=1 and a.estado=1 group by pc.idagrupadormodulos,a.nombre,a.estado,a.ambito,a.orden,a.dash,a.publico order by 4 asc;  ")
	public List<_TipoDocumento> retornaAgrupadormodulosPorCuentaempleado(Empleado e);

	public List<_TipoDocumento> retornaAgrupadormodulosPorEstado(@Param("val") _TipoDocumento agrupadormodulos);

	public List<_TipoDocumento> retornaAgrupadormodulosLikePorNombre(@Param("val") _TipoDocumento agrupadormodulos);

	public List<Pagina> retornaPaginasPorAgrupadormodulos(_TipoDocumento agrupadormodulos);

	public List<Pagina> retornaAccesosPorGrupo(_TipoDocumento agrupadormodulos);

	public Pagination retornaCantidadList(@Param("val") _TipoDocumento agrupadormodulos);
}
