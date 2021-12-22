package org.gteperu.erp.everest.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.gteperu.erp.everest.domain.Banco;
import org.gteperu.erp.everest.domain.TipoCambio;
import org.gteperu.erp.everest.domain.Tipodetraccion;
import org.gteperu.erp.everest.mappers.TipoCambioMapper;
import org.gteperu.erp.everest.mappers.TipodetraccionMapper;
import org.gteperu.erp.everest.service.TipodetraccionService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("tipodetraccionService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TipodetraccionServiceImpl implements TipodetraccionService {

	@Resource(name = "tipodetraccionMapper")
	TipodetraccionMapper tipodetraccionMapper;

	@Autowired
	TipoCambioMapper tipoCambioMapper;

	@Override
	public List<Tipodetraccion> retornaBanco() {
		List<Tipodetraccion> lsdet = new ArrayList<Tipodetraccion>();
		try {
			lsdet = tipodetraccionMapper.retornaBanco();
		} catch (Exception e) {
			System.err.println("ERROR: " + this.getClass().getSimpleName()
					+ "/retornaCantidadBancoEmpresaPorEmpresa => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/retornaBancoTodas" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return lsdet;
	}

	@Override
	public void registrarTipoCambio() {
		Document doc;
		try {
			doc = Jsoup
					.connect("https://www.sbs.gob.pe/app/pp/sistip_portal/paginas/publicacion/tipocambiopromedio.aspx")
					.get();

			Elements tipCam = doc.select("#ctl00_cphContent_rgTipoCambio_ctl00__0 .APLI_fila2");
			if (!tipCam.isEmpty()) {
//				Calendar fecha = Calendar.getInstance();
//				fecha.set(Calendar.HOUR, 0);
//				fecha.set(Calendar.MINUTE, 0);
//				fecha.set(Calendar.SECOND, 0);
//				fecha.set(Calendar.MILLISECOND, 1);
				Timestamp fechaHoy = new Timestamp(new Date().getTime());
				if (!tipoCambioMapper.existePorFecha(fechaHoy)) {
					TipoCambio tc = new TipoCambio();
					tc.setFechapublicacion(fechaHoy);
					tc.setCodsunat("USD");
					tc.setCompra(Double.valueOf(tipCam.get(0).text()));
					tc.setVenta(Double.valueOf(tipCam.get(1).text()));
					tipoCambioMapper.insertar(tc);
				}

				System.out.println("Compra: " + tipCam.get(0).text() + " / Venta: " + tipCam.get(1).text() + " / "
						+ new Date().toString());

			} else {
				System.out.println("No se ha emitido el tipo de cambio: " + new Date().toString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
