package org.gteperu.erp.everest.service.impl;

import java.util.List;

import org.gteperu.erp.everest.domain.Auditoria_Sunat;
import org.gteperu.erp.everest.domain.InternalWrapper;
import org.gteperu.erp.everest.domain._TipoAfecto;
import org.gteperu.erp.everest.mappers.AuditoriaSunatMapper;
import org.gteperu.erp.everest.service.Auditoria_SunatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("auditoriaSunatService")
public class Auditoria_SunatServiceImpl implements Auditoria_SunatService {
	
	@Autowired
	AuditoriaSunatMapper auditoriaSunatMapper;
	
	public Integer insertaAuditoria_Sunat(Auditoria_Sunat auditoria_Sunat){
		Integer cat = 0; 
		try{
			cat = auditoriaSunatMapper.insertaAuditoria_Sunat(auditoria_Sunat);
		}catch(Exception e){
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/insertaAuditoria_Sunat => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/insertaAuditoria_Sunat" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return cat;
	}
	public Integer actualizarAuditoria_Sunat(Auditoria_Sunat auditoria_Sunat){
		Integer cat = 0; 
		try{
			cat = auditoriaSunatMapper.actualizarAuditoria_Sunat(auditoria_Sunat);
		}catch(Exception e){
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/insertaAuditoria_Sunat => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/insertaAuditoria_Sunat" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return cat;
	}
	
	public Auditoria_Sunat retornaAuditoriaSunatxiddocumento(Integer auditoria_Sunat){
		Auditoria_Sunat cat = new Auditoria_Sunat(); 
		try{
			cat = auditoriaSunatMapper.retornaAuditoriaSunatxiddocumento(auditoria_Sunat);
		}catch(Exception e){
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/retornaAuditoriaSunatxiddocumento => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/retornaAuditoriaSunatxiddocumento" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return cat;
	}
}
