package org.gteperu.erp.everest.service;

import java.util.List;

import org.gteperu.erp.everest.domain.Regimen_sunat;

public interface Regimen_sunatSevice {

	 public Integer insertRegimen(Regimen_sunat regimen);
	 public List<Regimen_sunat> listarRegimen(Regimen_sunat cod_cliente);
}
