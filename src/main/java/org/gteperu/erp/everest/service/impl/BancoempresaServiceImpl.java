package org.gteperu.erp.everest.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain._Detalle_Documento_TMP;
import org.gteperu.erp.everest.domain.Bancoempresa;
import org.gteperu.erp.everest.mappers.BancoEmpresaMapper;
import org.gteperu.erp.everest.service.BancoempresaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("bancoempresaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class BancoempresaServiceImpl implements BancoempresaService{
	@Resource(name = "bancoempresaMapper")
	BancoEmpresaMapper bancoempresaMapper;



	@Override
	public List<Bancoempresa> retornaBancoEmpresaxEmpresaxEstado(Bancoempresa producto) {
		Pagination pg = new Pagination();
		List<Bancoempresa> lsProducto = new ArrayList<Bancoempresa>();
		try{
			pg = bancoempresaMapper.retornaCantidadBancoEmpresaPorEmpresa(producto);
			lsProducto = bancoempresaMapper.retornaBancoEmpresaxEmpresaxEstado(producto);
			if (lsProducto != null && lsProducto.size() > 0) {
				lsProducto.get(0).setCantidad(pg.getCantidad());
			}
		}catch(Exception e){
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/retornaCantidadBancoEmpresaPorEmpresa => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/retornaCantidadBancoEmpresaPorEmpresa" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return lsProducto;
	}
	
	@Override
	public List<Bancoempresa> retornaBancoEmpresaxEmpresaTodos(Bancoempresa producto) {
		List<Bancoempresa> lsProducto = new ArrayList<Bancoempresa>();
		try{
			lsProducto = bancoempresaMapper.retornaBancoEmpresaxEmpresaxEstado(producto);
		}catch(Exception e){
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/retornaBancoEmpresaxEmpresaxEstado => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/retornaBancoEmpresaxEmpresaxEstado" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return lsProducto;
	}

	@Override
	public Integer updateBancoEmpresa(Bancoempresa codpro) {
		Integer d = 0;
		try{
			d = bancoempresaMapper.updateBancoEmpresa(codpro);
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " updateBancoEmpresa. ERROR : " + e.getMessage());
			throw e;
		}
		return d;			
		
	}
	
	 

	@Override
	public Integer insertaBancoEmpresa(Bancoempresa codpro) {
		Integer d = 0;
		try {
			codpro.setEstado(1);
			d = bancoempresaMapper.insertaBancoEmpresa(codpro);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+ " insertaBancoEmpresa. ERROR : " + e.getMessage());
			throw e;
		} 
		return d;
		
	}

	@Override
	public Integer eliminaBancoEmpresa(Bancoempresa codpro) {
		Integer d = 0;
		try {
			d = bancoempresaMapper.eliminaBancoEmpresa(codpro);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+ " eliminaBancoEmpresa. ERROR : " + e.getMessage());
			throw e;
		}
			return d;
	
	}
	 
}
