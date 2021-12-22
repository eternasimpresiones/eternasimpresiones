package org.gteperu.erp.everest.service;

import java.util.List;

import org.gteperu.erp.everest.domain.PagoEmpresa;

public interface PagoEmpresaService {
	
	public Integer insertPago(PagoEmpresa pagoEmpresa);
	
	public List<PagoEmpresa> listarPagoEmpresa(Integer empresa);
	
	public Integer actualizarPago(PagoEmpresa pagoEmpresa);
	
	public PagoEmpresa buscarPagoEmpresa(Integer idpagempre);
	
	
}
