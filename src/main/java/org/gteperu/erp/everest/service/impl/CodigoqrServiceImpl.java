package org.gteperu.erp.everest.service.impl;

import org.gteperu.erp.everest.domain.Codigo_qr;
import org.gteperu.erp.everest.mappers.Codigo_qrMapper;
import org.gteperu.erp.everest.service.Codigo_qrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("codigoService")
public class CodigoqrServiceImpl implements Codigo_qrService {

	
	@Autowired
	Codigo_qrMapper codigoqrMapper;


	@Override
	public Integer insertarCodigoqr(Codigo_qr codigoqr) {
		Integer auxiliar = 0;

		try {

			auxiliar = codigoqrMapper.insertarCodigoqr(codigoqr);

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " insertarCodigoqr. ERROR : " + e.getMessage());
			throw e;
		}
			return auxiliar;
	}



}