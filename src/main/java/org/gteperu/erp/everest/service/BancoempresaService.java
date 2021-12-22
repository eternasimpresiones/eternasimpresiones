
package org.gteperu.erp.everest.service;
 import org.gteperu.erp.everest.domain.Bancoempresa;
 
import java.util.List;
public interface	BancoempresaService	{
	
	public List<Bancoempresa> retornaBancoEmpresaxEmpresaxEstado(Bancoempresa producto) ;
	
	public List<Bancoempresa> retornaBancoEmpresaxEmpresaTodos(Bancoempresa producto) ;

	public Integer updateBancoEmpresa(Bancoempresa codpro) ;

	public Integer insertaBancoEmpresa(Bancoempresa codpro) ;

	public Integer eliminaBancoEmpresa(Bancoempresa codpro) ;

}