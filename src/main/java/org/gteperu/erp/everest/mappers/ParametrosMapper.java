package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain._Parametros;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface ParametrosMapper {

//	@Select("select id_parametros,nombre,grupo,descripcion,codigo,valor,estado from parametros where cod_empresa=#{cod_empresa};")
//	public List<Parametros> retornaParametros(Parametros id_parametros);
//	
	//
	@Select("select * from parametros where  id_empresa=#{id_empresa};")
	public List<_Parametros> retornalsPorEmpresa(_Parametros cod_empresa);

	@Select("select * from parametros where   id_empresa=#{id_empresa};")
	public List<_Parametros> retornalsGlobales(_Parametros cod_empresa);

	@Select("select * from parametros where id_empresa=#{id_empresa} AND grupo=#{grupo};")
	public List<_Parametros> retornalsCuentasxEmpr(_Parametros parametro);

	@Select("SELECT valor FROM parametros WHERE codigo=#{codigo} AND grupo=#{grupo};")
	public _Parametros retornaValIGV(_Parametros parametro);

	@Select("SELECT valor FROM parametros WHERE codigo=#{codigo} AND grupo=#{grupo};")
	public _Parametros retornaValISC(_Parametros parametro);

	@Select("SELECT valor FROM parametros WHERE codigo=#{codigo} AND grupo=#{grupo};")
	public String retornaValor(_Parametros par);

	public List<_Parametros> retornaTipCuenParametroOtros(@Param("parametros") _Parametros id_parametros);

	//

//	@Select("select grupo,id_parametros,nombre,descripcion,codigo,valor,estado from parametros where estado='1' and cod_empresa=#{cod_empresa};")
//	public List<Parametros> retornaParametrosTodas(Parametros id_parametros);

	public List<_Parametros> retornaParametrosPorEstadoCorreos(@Param("parametros") _Parametros id_parametros);

	public List<_Parametros> retornaParametrosPorEstado(@Param("parametros") _Parametros id_parametros);

	public List<_Parametros> retornaParametrosLikePorNombre(@Param("parametros") _Parametros id_parametros);

	@Select("select id_parametros,nombre,grupo,descripcion,codigo,valor,estado from parametros where id_parametros =#{id_parametros}")
	public _Parametros retornaObjParametros(_Parametros id_parametros);

	@Select("select id_parametros,nombre,grupo,descripcion,codigo,valor,estado from parametros where codigo=#{codigo} and id_empresa=#{id_empresa};")
	public _Parametros retornaObjParametroPorEmpresaCodigo(_Parametros id_parametros);

	@Select("select id_parametros,nombre,grupo,descripcion,codigo,valor,estado from parametros where grupo =#{grupo} and id_empresa=#{id_empresa}")
	public List<_Parametros> retornaObjParametrosPorGrupo(_Parametros id_parametros);

	@Select("select id_parametros,nombre,grupo,descripcion,codigo,valor,estado from parametros where grupo =#{grupo} and id_empresa=#{id_empresa}")
	public List<_Parametros> retornaObjParametrosPorGrupoEmpresa(_Parametros id_parametros);

	@Select("select id_parametros,nombre,grupo,descripcion,codigo,valor,estado from parametros where codigo =#{codigo} and id_empresa=#{id_empresa}")
	public _Parametros retornaObjParametrosPorCosigo(_Parametros id_parametros);

	@Update("update parametros set nombre=#{nombre},grupo=#{grupo}, descripcion=#{descripcion}, codigo=#{codigo}, valor=#{valor}, estado=#{estado} where id_parametros =#{id_parametros};")
	public Integer updateParametros(_Parametros id_parametros);

	@Insert(" insert into parametros(id_empresa,nombre,grupo,descripcion,codigo,valor,estado)values(#{id_empresa},#{nombre},#{grupo},#{descripcion},#{codigo},#{valor},#{estado});")
	@SelectKey(statement = "select currval('sec_parametros') as id_parametros", keyProperty = "id_parametros", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "id_parametros", flushCache = FlushCachePolicy.TRUE)
	public Integer insertaParametros(_Parametros id_parametros);

	@Delete(" delete from  parametros where id_parametros=#{id_parametros}; ")
	public Integer eliminaParametros(_Parametros id_parametros);

	public Pagination retornaCantidadList(@Param("parametros") _Parametros id_parametros);

	public Pagination retornaCantidadListCorrep(@Param("parametros") _Parametros id_parametros);

	/***************/
	@Select("select * from parametros where grupo=#{grupo} order by codigo desc limit 1")
	public List<_Parametros> listarUltimoRegistroGlobal(_Parametros id_parametros);

	@Select("select * from parametros where grupo=#{grupo} and id_empresa=#{id_empresa} order by codigo desc limit 1")
	public List<_Parametros> listarUltimoRegistroEmpresa(_Parametros id_parametros);

	@Insert(" insert into parametros(descripcion,codigo,valor,estado,grupo,nombre,id_empresa)values(#{descripcion},#{codigo},#{valor},#{estado},#{grupo},#{nombre},#{id_empresa});")
	@SelectKey(statement = "select currval('sec_parametros') as id_parametros", keyProperty = "id_parametros", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "id_parametros", flushCache = FlushCachePolicy.TRUE)
	public Integer insertaRegistroGlobal(_Parametros id_parametros);

	@Insert(" insert into parametros(descripcion,codigo,valor,estado,grupo,nombre,id_empresa)values(#{descripcion},#{codigo},#{valor},#{estado},#{grupo},#{nombre},#{id_empresa});")
	@SelectKey(statement = "select currval('sec_parametros') as id_parametros", keyProperty = "id_parametros", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "id_parametros", flushCache = FlushCachePolicy.TRUE)
	public Integer insertaRegistroEmpresa(_Parametros id_parametros);

	@Update("update parametros set nombre=#{nombre},grupo=#{grupo}, descripcion=#{descripcion}, codigo=#{codigo}, valor=#{valor}, estado=#{estado} where id_parametros =#{id_parametros};")
	public Integer updateParamEmpGlb(_Parametros id_parametros);

	////////////////////////////////// Mapper del otro backend

	@Select("select id_parametros,descripcion,codigo,valor,estado,grupo,nombre from parametros ;")
	public List<_Parametros> retornaParametros();

	@Select("select id_parametros,descripcion,codigo,valor,estado,grupo,nombre from parametros where estado='1';")
	public List<_Parametros> retornaParametrosTodas();

	@Select("select id_parametros,descripcion,codigo,valor,estado,grupo,nombre from parametros where grupo=#{grupo};")
	public List<_Parametros> retornaParametrosPorGrupo(_Parametros p);

	@Select("select id_parametros,descripcion,codigo,valor,estado,grupo,nombre from parametros where codigo=#{codigo} ;")
	public _Parametros retornaObjParametrosPorCodigo(_Parametros idparametros);
	
	@Select("select valor from parametros where codigo=#{codigo}")
	public _Parametros selecValorParametroPorCodigo(@Param("codigo") String codigo);

	// RETENCION
	@Select("select id_parametros, descripcion, id_empresa , valor from parametros where id_empresa = #{id_empresa} and grupo = #{grupo}")
	public List<_Parametros> retornaDescripcionValorPorIdEmpresaGrupo(_Parametros parametros);

	@Select("select valor from parametros where id_empresa = #{id_empresa} and grupo = #{grupo} and codigo = #{codigo}")
	public _Parametros retornaValorPorIdEmpresaGrupoCodigo(_Parametros parametros);

	@Update("update parametros set valor = #{valor}, descripcion = #{descripcion} where id_empresa = #{id_empresa} and id_parametros = #{id_parametros}")
	public Integer actualizarValorPorIdEmpresaIdParametros(_Parametros parametros);

	@Select("select ${columna} from parametros where id_empresa = #{id_empresa} and grupo = #{grupo} and codigo = #{codigo}")
	public _Parametros prueba(_Parametros parametros);
	
	@Select("select * from parametros where codigo=#{codigo}")
	public _Parametros retornaParametroPorCodigo(@Param("codigo") String codigo);
}