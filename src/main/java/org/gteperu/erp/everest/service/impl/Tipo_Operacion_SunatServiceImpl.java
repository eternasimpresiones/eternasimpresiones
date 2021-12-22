package org.gteperu.erp.everest.service.impl;

import java.util.List;

import org.gteperu.erp.everest.domain.Auditoria_Sunat;
import org.gteperu.erp.everest.domain.InternalWrapper;
import org.gteperu.erp.everest.domain.Tipo_Operacion_Sunat;
import org.gteperu.erp.everest.domain._TipoAfecto;
import org.gteperu.erp.everest.mappers.AuditoriaSunatMapper;
import org.gteperu.erp.everest.mappers.TipoOperacionSunatMapper;
import org.gteperu.erp.everest.service.Auditoria_SunatService;
import org.gteperu.erp.everest.service.Tipo_Operacion_SunatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tipoOperacionSunatService")
public class Tipo_Operacion_SunatServiceImpl implements Tipo_Operacion_SunatService {
	
	@Autowired
	TipoOperacionSunatMapper tipoOperacionSunatMapper;
	
	public Tipo_Operacion_Sunat retornaObjTipoOperacionSunatPorCodigo(Tipo_Operacion_Sunat tipo_Operacion_Sunat){
		Tipo_Operacion_Sunat operacion_Sunat = new Tipo_Operacion_Sunat(); 
		try{
			operacion_Sunat = tipoOperacionSunatMapper.retornaObjTipoOperacionSunatPorCodigo(tipo_Operacion_Sunat);
		}catch(Exception e){
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/insertaAuditoria_Sunat => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/insertaAuditoria_Sunat" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return operacion_Sunat;
	}
	
}
