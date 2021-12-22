package org.gteperu.erp.everest.service;

import java.util.List;

import org.gteperu.erp.everest.domain.Dashboard;

public interface DashboardService {

	 

	public List<Dashboard> facturacionMensual(Dashboard dash);

	public List<Dashboard> facturacionClientesMensual(Dashboard dash);

	
}
