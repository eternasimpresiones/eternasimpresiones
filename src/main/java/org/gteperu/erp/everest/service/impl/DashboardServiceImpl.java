package org.gteperu.erp.everest.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.IntStream;

import javax.annotation.Resource;

import org.gteperu.erp.everest.domain.Dashboard; 
import org.gteperu.erp.everest.mappers.DashboardMapper;
import org.gteperu.erp.everest.service.DashboardService;
import org.springframework.stereotype.Service;

@Service("dashboardService")
public class DashboardServiceImpl implements DashboardService {

    @Resource(name = "dashboardMapper")
    DashboardMapper dashboardMapper;
    
	public List<Dashboard> facturacionMensual(Dashboard dash){
		List<Dashboard> lsMeses = new ArrayList<Dashboard>();
	 	try {
	 		
	 		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 			String fecha_inicio_str  = dateFormat.format(dash.getFecha_inicio());
			dash.setFecha_inicio_tmp(fecha_inicio_str);
	 		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
 			String fecha_fin_str  = dateFormat2.format(dash.getFecha_fin());
			dash.setFecha_fin_tmp(fecha_fin_str);
	 		
	 		
	 		Calendar calInicio = GregorianCalendar.getInstance();
			calInicio.set(Calendar.DAY_OF_MONTH, 01);// I might have the wrong Calendar constant...
			calInicio.set(Calendar.YEAR, Integer.valueOf(dash.getAno()));
 			
			Calendar calFin = GregorianCalendar.getInstance();


			calFin.set(Calendar.YEAR, Integer.valueOf(dash.getAno()));
 			
			  IntStream.range(0, 12).forEach(i -> {
				     calInicio.set(Calendar.MONTH, i);// -1 as month is zero-based
				     Timestamp tstampInicio = new Timestamp(calInicio.getTimeInMillis());
					 dash.setFecha_inicio(tstampInicio);

					 calFin.set(Calendar.MONTH, i);// -1 as month is zero-based
					 Timestamp tstampFin = new Timestamp(calFin.getTimeInMillis());
					 dash.setFecha_fin(tstampFin);
					 
					 Dashboard mes = new Dashboard();
					 System.out.print("dash.toString()" + dash.toString() +" \n ");
					 mes = dashboardMapper.totalFacturadoMes(dash);
					 if(mes==null) {
						 mes = new Dashboard();
					 }
					 System.out.print("mes.getTotal " + mes.getTotal()  +" \n ");
					 lsMeses.add(mes);
		        });
	 	} catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/facturacionMensual => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/facturacionMensual" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			
		}
		return lsMeses;
	}

 	public List<Dashboard> facturacionClientesMensual(Dashboard dash){
		List<Dashboard> lsDocumentos = new ArrayList<Dashboard>();
		List<Dashboard> lsClientes = new ArrayList<Dashboard>();

	 	try {
	 		
	 		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 			String fecha_inicio_str  = dateFormat.format(dash.getFecha_inicio());
			dash.setFecha_inicio_tmp(fecha_inicio_str);
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
 			String fecha_fin_str  = dateFormat2.format(dash.getFecha_fin());
			dash.setFecha_fin_tmp(fecha_fin_str);
	 		
	 		Calendar calInicio = GregorianCalendar.getInstance();
			calInicio.set(Calendar.DAY_OF_MONTH, 01);// I might have the wrong Calendar constant...
		    calInicio.set(Calendar.MONTH,  Integer.valueOf(dash.getMes()));// -1 as month is zero-based
			calInicio.set(Calendar.YEAR, Integer.valueOf(dash.getAno()));
		     Timestamp tstampInicio = new Timestamp(calInicio.getTimeInMillis());
			 dash.setFecha_inicio(tstampInicio);

			 Calendar calFin = GregorianCalendar.getInstance();
			 calFin.set(Calendar.DAY_OF_MONTH, 31);// I might have the wrong Calendar constant...
			 calFin.set(Calendar.MONTH,  Integer.valueOf(dash.getMes()));// -1 as month is zero-based
			 calFin.set(Calendar.YEAR, Integer.valueOf(dash.getAno()));
			 Timestamp tstampFin = new Timestamp(calFin.getTimeInMillis());
			 dash.setFecha_fin(tstampFin);

			 lsDocumentos = dashboardMapper.facturacionClientesMensual(dash);
			 
				Map<String,Dashboard> mClnt=new HashMap<String,Dashboard>();
				for(Dashboard cld:lsDocumentos){
					String clienteDireccion =cld.getIdcliente()+cld.getDireccionFiscalCliente();
					mClnt.put(clienteDireccion,cld);
				}
				 Double total=0.0;	
				 Dashboard cliente =null;
				 String direccionCliente="";
				 Integer idcliente=null;
				 String razonSocialCliente="";
				for(Entry<String,Dashboard> mC:mClnt.entrySet()){
					 total=0.0;
					 idcliente=null;
					 direccionCliente="";
					 razonSocialCliente="";
					 for(Dashboard doc:lsDocumentos){
					 String clienteDireccion =doc.getIdcliente()+doc.getDireccionFiscalCliente();
					 	if(clienteDireccion.equals(mC.getKey())){
					 		total=total+ doc.getTotal();
					 		idcliente=doc.getIdcliente();
					 		direccionCliente=doc.getDireccionFiscalCliente();
					 		razonSocialCliente=doc.getRazonSocialCliente();
							}
				 	 }	
					 cliente = new Dashboard();
					 cliente.setTotal(total);
					 cliente.setIdcliente(idcliente);
					 cliente.setDireccionFiscalCliente(direccionCliente);
					 cliente.setRazonSocialCliente(razonSocialCliente);
 					 lsClientes.add(cliente);
				}	
	  	} catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/facturacionClientesMensual => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/facturacionClientesMensual" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return lsClientes;
	}


}