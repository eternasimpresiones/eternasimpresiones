package org.gteperu.erp.everest.service;

import org.gteperu.erp.everest.domain.Empresa;
 
import java.util.List;

public interface EmpresaService {

	public List<Empresa> retornaEmpresa(Empresa w);
	
	public List<Empresa> retornaEmpresaSelect();

	public List<Empresa> retornaEmpresaPorCuenta(Empresa e);

	public List<Empresa> retornaEmpresaPorEmpleado(Integer idempleado);

	public List<Empresa> retornaEmpresaTodas(Empresa w);

	public List<Empresa> retornaEmpresaLikePorNombre(Empresa idempresa);

	public Empresa retornaObjEmpresaPorRuc(Empresa idempresa);

	public Empresa retornaObjEmpresaPorId(Empresa idempresa);

	public Empresa retornaEmpresaCuenta(Empresa idempresa);

	public Empresa retornaObjEmpresa(Empresa idempresa);

	public Integer updateEmpresa(Empresa idempresa);

	public Integer insertaEmpresa(Empresa idempresa);

 
	public Integer eliminaEmpresa(Empresa idempresa);
}