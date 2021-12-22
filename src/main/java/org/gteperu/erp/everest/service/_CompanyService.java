package org.gteperu.erp.everest.service;

import org.gteperu.erp.everest.domain.Ubigeo;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._Tipo_Empresa;

import java.util.List;

public interface _CompanyService {

	public List<_Company> listEmpresa(_Company company);
	
	public List<_Company> listEmpresaPaginacion(_Company company);
	
	public List<_Tipo_Empresa> listTipoEmpresa();

	public Integer insertEmpresa(_Company company);
	
	public Integer eliminarEmpresa(_Company cod_empresa);
	
	public Integer actualizarEmpresa(_Company cod_empresa);

	public Integer actualizarFirma(_Company cod_empresa);

	public _Company retornaEmpresaPorIdempresa(_Company cod_empresa);
	
	public _Company buscaEmpresaPorIdempresa(_Company cod_empresa);
	
	public Integer actualizarFechaExEmpresa(_Company company);

	public Ubigeo listar(_Company cod_ubigeo);

	public _Company consultaRucSunaPadron(_Company company);
	
	public _Company buscaEmpresaPorNroDoc(_Company cod_empresa);
	
	public _Company retornaEmpresaPorIdLocal(Integer idlocal);

}