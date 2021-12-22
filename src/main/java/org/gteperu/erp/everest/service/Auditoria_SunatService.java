package org.gteperu.erp.everest.service;

import java.util.List;

import org.gteperu.erp.everest.domain.Auditoria_Sunat;
import org.gteperu.erp.everest.domain.InternalWrapper;
import org.gteperu.erp.everest.domain._TipoAfecto;

public interface Auditoria_SunatService {
	
	public Integer insertaAuditoria_Sunat(Auditoria_Sunat auditoria_Sunat);
	public Integer actualizarAuditoria_Sunat(Auditoria_Sunat auditoria_Sunat);
	public Auditoria_Sunat retornaAuditoriaSunatxiddocumento(Integer auditoria_Sunat);

	
}
