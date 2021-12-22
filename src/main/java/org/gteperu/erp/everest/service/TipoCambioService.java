package org.gteperu.erp.everest.service;

import java.sql.Timestamp;
import org.gteperu.erp.everest.domain.TipoCambio;

public interface TipoCambioService {

	public Integer insertar(TipoCambio tc);
	public TipoCambio encontrarPorFecha(Timestamp fechapublicacion);
	public boolean existePorFecha(Timestamp fechapublicacion);

}
