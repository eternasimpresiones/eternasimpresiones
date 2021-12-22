
package org.gteperu.erp.everest.service;
import org.gteperu.erp.everest.domain.Perfilespaginas;
import org.gteperu.erp.everest.domain.Sunat_padron;

import java.util.List;
public interface Sunat_padronService	{

public Integer eliminaRucSunatPadron(Sunat_padron sunat);
	
	public Sunat_padron retornaTablaPorEstado(Sunat_padron sunat) ;
	
	public Integer updateEstadoSunatPadron(Sunat_padron sunat) ;
	
	 //public Integer insertaRucSunatPadronA(List<Sunat_padron> lsSunatPadron);
	public Integer insertaRucSunatPadronA(List<Sunat_padron> lsSunatPadron,Sunat_padron sunat);
	 
	 
	 public Integer insertaRucSunatPadronB(List<Sunat_padron> lsSunatPadron);
	 
	 public Sunat_padron consultaRuc(Sunat_padron sunat);


}