package org.gteperu.erp.everest.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.gteperu.erp.everest.domain._Parametros;

public interface ParametrosService {

	public List<_Parametros> retornaParametrose(_Parametros id_parametros);
	//
	public List<_Parametros> retornalsPorEmpresa(_Parametros parametros);

	public _Parametros retornaValIGV(_Parametros parametros);

	public _Parametros retornaValISC(_Parametros parametros);

	public List<_Parametros> retornalsCuentasxEmpr(_Parametros parametro);

	public List<_Parametros> retornalsGlobales(_Parametros cod_empresa);
	//
	public List<_Parametros> retornaParametrosTodase(_Parametros id_parametros);

	public List<_Parametros> retornaObjParametrosPorGrupoe(_Parametros p);

	public List<_Parametros> retornaObjParametrosPorGrupoEmpresa(_Parametros p);

	public List<_Parametros> retornaParametrosPorEstadoe(_Parametros id_parametros);

	public List<_Parametros> retornaParametrosPorEstadoCorreos(_Parametros id_parametros);

	public List<_Parametros> retornaParametrosLikePorNombree(_Parametros id_parametros);
	
	public _Parametros retornaObjParametrosPorCosigoe(_Parametros id_parametros);

	public _Parametros retornaObjParametrose(_Parametros id_parametros);

	public _Parametros retornaObjParametroPorEmpresaCodigo(_Parametros id_parametros);

	public Integer updateParametrose(_Parametros id_parametros);

	public Integer insertaParametrose(_Parametros id_parametros);

	public Integer eliminaParametrose(_Parametros id_parametros);

	/*******/

	public List<_Parametros> listarUltimoRegistroGlobal(_Parametros id_parametros);

	public List<_Parametros> listarUltimoRegistroEmpresa(_Parametros id_parametros);

	public Integer insertaRegistroGlobal(_Parametros id_parametros);

	public Integer insertaRegistroEmpresa(_Parametros id_parametros);

	public Integer actualizarParamEmpGlb(_Parametros id_parametros);

	/// EFICON//
	public List<_Parametros> retornaTpoRetencion(_Parametros idparametros);

	/// -----//
	public List<_Parametros> retornaParametros();

	public List<_Parametros> retornaParametrosTodas();

	public List<_Parametros> retornaParametrosPorGrupo(_Parametros p);

	public List<_Parametros> retornaParametrosPorEstado(_Parametros idparametros);

	public _Parametros retornaObjParametrosPorCodigo(_Parametros idparametros);

	public _Parametros retornaObjParametros(_Parametros idparametros);

	public Integer updateParametros(_Parametros idparametros);

	public Integer insertaParametros(_Parametros idparametros);

	public Integer eliminaParametros(_Parametros idparametros);

	public String retornaValor(_Parametros par);

	public List<_Parametros> retornaTipCuenParametroOtros(_Parametros tipCuenParam);

	public List<_Parametros> pruebafail(_Parametros id_parametros);

	// RETENCION
	public _Parametros retornaValorPorIdEmpresaCodigoGrupo(_Parametros parametros);

	public List<_Parametros> retornaDescripcionValorPorIdEmpresaGrupo(_Parametros parametros);

	public Integer actualizarValorPorIdEmpresaIdParametros(_Parametros parametros);

	public _Parametros prueba(_Parametros idparametros);
	
	public _Parametros retornaParametroPorCodigo(@Param("codigo") String codigo);

}