package org.gteperu.erp.everest.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import org.gteperu.erp.everest.domain.PagoEmpresa;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.mappers.PagoEmpresaMapper;
import org.gteperu.erp.everest.service.PagoEmpresaService;
import org.springframework.stereotype.Service;

@Service("pagoEmpresaService")
public class PagoEmpresaServiceImpl implements PagoEmpresaService {

	@Resource(name="pagoEmpresaMapper")
	PagoEmpresaMapper pagoEmpresaMapper;
	
	@Override
	public Integer insertPago(PagoEmpresa pagoEmpresa) {
		Integer auxiliar = 0;
		try {
			auxiliar = pagoEmpresaMapper.insertPago(pagoEmpresa);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" insertPago. ERROR : "+e.getMessage());
			throw e;
		} 
			return auxiliar;
		
	}

	@Override
	public List<PagoEmpresa> listarPagoEmpresa(Integer empresa) {
		List<PagoEmpresa> lsPagoEmpresa = new ArrayList<PagoEmpresa>();
		try {
			Double deuda = 0.0;
			lsPagoEmpresa = pagoEmpresaMapper.listarPagoEmpresa(empresa);
			
			Iterator<PagoEmpresa> lstPagEmpre = lsPagoEmpresa.iterator();
			while(lstPagEmpre.hasNext()){
				PagoEmpresa pagempre = lstPagEmpre.next();
				if(pagempre.getCancelado() != null){
					deuda = pagempre.getTotal()-pagempre.getCancelado();
					pagempre.setDeuda(deuda);
				}
				else{
					pagempre.setDeuda(deuda);
				}
			}
			
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" listarPagoEmpresa. ERROR : "+e.getMessage());
			throw e;
		}
		return lsPagoEmpresa;
		
	}

	@Override
	public Integer actualizarPago(PagoEmpresa pagoEmpresa) {
		PagoEmpresa objPagoEmpresa = new PagoEmpresa();
		Integer auxiliar = 0;
		try {
			Double newcancelado;
			objPagoEmpresa = pagoEmpresaMapper.buscarPagoEmpresa(pagoEmpresa.getId_pagoempresa());
			newcancelado = objPagoEmpresa.getCancelado()+pagoEmpresa.getCancelado();
			pagoEmpresa.setCancelado(newcancelado);
			auxiliar = pagoEmpresaMapper.actualizarPago(pagoEmpresa);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" actualizarPago. ERROR : "+e.getMessage());
			throw e;
		}
		return auxiliar;
		
	}

	@Override
	public PagoEmpresa buscarPagoEmpresa(Integer idpagempre) {
		PagoEmpresa objPagoEmpresa = new PagoEmpresa();
		try {
			objPagoEmpresa = pagoEmpresaMapper.buscarPagoEmpresa(idpagempre);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" listarPagoEmpresa. ERROR : "+e.getMessage());
			throw e;
		}
		return objPagoEmpresa;
		
	}

}
