package org.gteperu.erp.everest.service.impl;

import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.mappers.ParametrosMapper;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service._ClienteService;

import java.util.List;
import javax.annotation.Resource;
import java.util.ArrayList;

@Service("parametrosService")
public class ParametrosServiceImpl implements ParametrosService {

	@Resource(name = "parametrosMapper")
	ParametrosMapper parametrosMapper;

	@Autowired
	_ClienteService clienteService;

	/*
	 * RO
	 */
	@Override
	public List<_Parametros> listarUltimoRegistroGlobal(_Parametros id_parametros) {
		List<_Parametros> lsParametros = new ArrayList<_Parametros>();
		lsParametros = parametrosMapper.listarUltimoRegistroGlobal(id_parametros);

		return lsParametros;
	}

	@Override
	public List<_Parametros> listarUltimoRegistroEmpresa(_Parametros id_parametros) {
		List<_Parametros> lsParametros = new ArrayList<_Parametros>();
		lsParametros = parametrosMapper.listarUltimoRegistroEmpresa(id_parametros);

		return lsParametros;
	}

	@Override
	public Integer insertaRegistroGlobal(_Parametros id_parametros) {
		Integer d = 0;
		try {
			d = parametrosMapper.insertaRegistroGlobal(id_parametros);
		} catch (Exception e) {
			System.out.println(this.getClass().getName() + "insertaRegistroGlobal" + e.getMessage());
			throw e;
		}
		return d;

	}

	@Override
	public Integer insertaRegistroEmpresa(_Parametros id_parametros) {
		Integer d = 0;
		try {
			d = parametrosMapper.insertaRegistroEmpresa(id_parametros);
		} catch (Exception e) {
			throw e;
		}
		return d;

	}

	@Override
	public Integer actualizarParamEmpGlb(_Parametros id_parametros) {
		Integer d = 0;
		try {
			d = parametrosMapper.updateParamEmpGlb(id_parametros);
		} catch (Exception e) {
			throw e;
		}
		return d;

	}

	/**********************/

	@Override
	public List<_Parametros> retornaParametrose(_Parametros id_parametros) {
		List<_Parametros> lsParametros = new ArrayList<_Parametros>();
//        lsParametros = parametrosMapper.retornaParametros(id_parametros);
		return lsParametros;
	}

	//
	@Override
	public List<_Parametros> retornalsPorEmpresa(_Parametros cod_empresa) {
		return parametrosMapper.retornalsPorEmpresa(cod_empresa);
	}

	@Override
	public List<_Parametros> retornalsGlobales(_Parametros cod_empresa) {
		return parametrosMapper.retornalsGlobales(cod_empresa);
	}
	//

	@Override
	public List<_Parametros> retornaParametrosTodase(_Parametros id_parametros) {
		List<_Parametros> lsParametros = new ArrayList<_Parametros>();
//        lsParametros = parametrosMapper.retornaParametrosTodase(id_parametros);
		return lsParametros;
	}

	@Override
	public List<_Parametros> retornaObjParametrosPorGrupoEmpresa(_Parametros id_parametros) {
		List<_Parametros> lsParametros = new ArrayList<_Parametros>();
		lsParametros = parametrosMapper.retornaObjParametrosPorGrupoEmpresa(id_parametros);
		return lsParametros;
	}

	@Override
	public List<_Parametros> retornaObjParametrosPorGrupoe(_Parametros id_parametros) {
		List<_Parametros> lsParametros = new ArrayList<_Parametros>();
		lsParametros = parametrosMapper.retornaObjParametrosPorGrupo(id_parametros);
		return lsParametros;
	}

	@Override
	public List<_Parametros> retornaParametrosLikePorNombree(_Parametros id_parametros) {
		List<_Parametros> lsParametros = new ArrayList<_Parametros>();
		lsParametros = parametrosMapper.retornaParametrosLikePorNombre(id_parametros);
		return lsParametros;
	}

	@Override
	public List<_Parametros> retornaParametrosPorEstadoCorreos(_Parametros id_parametros) {
		List<_Parametros> lsParametros = new ArrayList<_Parametros>();
		Pagination pg = new Pagination();
		pg = parametrosMapper.retornaCantidadListCorrep(id_parametros);
		lsParametros = parametrosMapper.retornaParametrosPorEstadoCorreos(id_parametros);
		if (lsParametros != null && lsParametros.size() > 0) {
			lsParametros.get(0).setCantidad(pg.getCantidad());
		}
		return lsParametros;
	}

	@Override
	public List<_Parametros> retornaParametrosPorEstadoe(_Parametros id_parametros) {
		List<_Parametros> lsParametros = new ArrayList<_Parametros>();
		Pagination pg = new Pagination();
		pg = parametrosMapper.retornaCantidadList(id_parametros);
		lsParametros = parametrosMapper.retornaParametrosPorEstado(id_parametros);
		if (lsParametros != null && lsParametros.size() > 0) {
			lsParametros.get(0).setCantidad(pg.getCantidad());
		}
		return lsParametros;
	}

	@Override
	public _Parametros retornaObjParametrose(_Parametros id_parametros) {
		return parametrosMapper.retornaObjParametros(id_parametros);
	}

	@Override
	public _Parametros retornaObjParametroPorEmpresaCodigo(_Parametros id_parametros) {
		return parametrosMapper.retornaObjParametroPorEmpresaCodigo(id_parametros);
	}

	@Override
	public _Parametros retornaObjParametrosPorCosigoe(_Parametros id_parametros) {
		return parametrosMapper.retornaObjParametrosPorCosigo(id_parametros);
	}

	@Override
	public Integer updateParametrose(_Parametros id_parametros) {
		return parametrosMapper.updateParametros(id_parametros);
	}

	@Override
	public Integer insertaParametrose(_Parametros id_parametros) {
		return parametrosMapper.insertaParametros(id_parametros);
	}

	@Override
	public Integer eliminaParametrose(_Parametros id_parametros) {
		return parametrosMapper.eliminaParametros(id_parametros);
	}

	/// EFCION//
	@Override
	public List<_Parametros> retornaTpoRetencion(_Parametros idparametros) {
		List<_Parametros> lsParametros = new ArrayList<_Parametros>();
		lsParametros = parametrosMapper.retornaParametrosPorGrupo(idparametros);
		return lsParametros;
	}

	// ------//

	@Override
	public List<_Parametros> retornaParametros() {
		List<_Parametros> lsParametros = new ArrayList<_Parametros>();

		try {
			lsParametros = parametrosMapper.retornaParametros();

		} catch (Exception e) {
			e.getMessage();
			throw e;
		}
		return lsParametros;

	}

	@Override
	public List<_Parametros> retornaParametrosTodas() {
		List<_Parametros> lsParametros = new ArrayList<_Parametros>();
		lsParametros = parametrosMapper.retornaParametrosTodas();

		return lsParametros;
	}

	@Override
	public List<_Parametros> retornaParametrosPorGrupo(_Parametros idparametros) {
		List<_Parametros> lsParametros = new ArrayList<_Parametros>();
		Pagination pg = new Pagination();

		lsParametros = parametrosMapper.retornaParametrosPorGrupo(idparametros);

		return lsParametros;
	}

	@Override
	public List<_Parametros> retornaParametrosPorEstado(_Parametros idparametros) {
		List<_Parametros> lsParametros = new ArrayList<_Parametros>();
		Pagination pg = new Pagination();
		pg = parametrosMapper.retornaCantidadList(idparametros);
		lsParametros = parametrosMapper.retornaParametrosPorEstado(idparametros);
		if (lsParametros != null && lsParametros.size() > 0) {
			lsParametros.get(0).setCantidad(pg.getCantidad());
		}
		return lsParametros;
	}

	@Override
	public _Parametros retornaObjParametros(_Parametros idparametros) {
		return parametrosMapper.retornaObjParametrosPorCodigo(idparametros);
		// return parametrosMapper.retornaObjParametros(idparametros);
	}

	@Override
	public _Parametros retornaObjParametrosPorCodigo(_Parametros idparametros) {
		return parametrosMapper.retornaObjParametrosPorCodigo(idparametros);
	}

	@Override
	public Integer updateParametros(_Parametros idparametros) {
		Integer d = 0;
		try {
			d = parametrosMapper.updateParametros(idparametros);
		} catch (Exception e) {
			throw e;
		}
		return d;

	}

	@Override
	public Integer insertaParametros(_Parametros idparametros) {
		Integer d = 0;
		try {
			d = parametrosMapper.insertaParametros(idparametros);
		} catch (Exception e) {
			throw e;
		}
		return d;

	}

	@Override
	public Integer eliminaParametros(_Parametros idparametros) {
		Integer d = 0;
		try {
			d = parametrosMapper.eliminaParametros(idparametros);
		} catch (Exception e) {
			throw e;
		}
		return d;

	}

	@Override
	public List<_Parametros> retornalsCuentasxEmpr(_Parametros parametro) {
		return parametrosMapper.retornalsCuentasxEmpr(parametro);
	}

	@Override
	public _Parametros retornaValIGV(_Parametros parametros) {
		return parametrosMapper.retornaValIGV(parametros);
	}

	@Override
	public _Parametros retornaValISC(_Parametros parametros) {
		return parametrosMapper.retornaValISC(parametros);
	}

	@Override
	public String retornaValor(_Parametros parametros) {
		String rpta = null;
		try {
			rpta = parametrosMapper.retornaValor(parametros);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " retornaValor. ERROR : " + e.getMessage());
			throw e;
		}
		return rpta;

	}

	@Override
	public List<_Parametros> retornaTipCuenParametroOtros(_Parametros tipCuenParam) {
		List<_Parametros> lsTipCuenParametro = new ArrayList<_Parametros>();
		try {
			lsTipCuenParametro = parametrosMapper.retornaTipCuenParametroOtros(tipCuenParam);
		} catch (Exception e) {
			System.out.println(
					this.getClass().getSimpleName() + " retornaTipCuenParametroOtros. ERROR : " + e.getMessage());
			throw e;
		}
		return lsTipCuenParametro;

	}

	@Override
	public List<_Parametros> pruebafail(_Parametros id_parametros) {
		try {
			// String[] lista_prueba = new String[2];
			// String pp = lista_prueba[3];
			// _DocumentoCpe dd = new _DocumentoCpe();
			// Double dda = dd.getPorcentaje_descuento() + dd.getPor_igv();
			Integer cat = 0;
			clienteService.pruebRetornaCliente(cat);
			// Integer cat = null;
			// cat = cat +5;

		} catch (Exception e) {
			throw e;
		}
		return null;
	}

	@Override
	public _Parametros retornaValorPorIdEmpresaCodigoGrupo(_Parametros parametros) {
		_Parametros obj = new _Parametros();
		try {
			obj = parametrosMapper.retornaValorPorIdEmpresaGrupoCodigo(parametros);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " retornaValorPorIdEmpresaCodigoGrupo. ERROR : "
					+ e.getMessage());
			throw e;
		}
		return obj;
	}

	@Override
	public List<_Parametros> retornaDescripcionValorPorIdEmpresaGrupo(_Parametros parametros) {
		List<_Parametros> lsta = new ArrayList<_Parametros>();
		try {
			lsta = parametrosMapper.retornaDescripcionValorPorIdEmpresaGrupo(parametros);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " retornaDescripcionValorPorIdEmpresaGrupo. ERROR : "
					+ e.getMessage());
			throw e;
		}
		return lsta;
	}

	@Override
	public Integer actualizarValorPorIdEmpresaIdParametros(_Parametros parametros) {
		Integer upd = 0;
		try {
			upd = parametrosMapper.actualizarValorPorIdEmpresaIdParametros(parametros);
		} catch (Exception e) {
			throw e;
		}
		return upd;
	}

	@Override
	public _Parametros prueba(_Parametros parametros) {
		_Parametros upd = new _Parametros();
		try {

			upd = parametrosMapper.prueba(parametros);
		} catch (Exception e) {
			throw e;
		}
		return upd;
	}

	@Override
	public _Parametros retornaParametroPorCodigo(String codigo) {
		return parametrosMapper.retornaParametroPorCodigo(codigo);
	}

}