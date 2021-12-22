package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.gteperu.erp.everest.domain.Dashboard;
import org.gteperu.erp.everest.domain.Documentos;

@Mapper
public interface DashboardMapper {

 
 
	 
    public  Dashboard totalFacturadoMes(@Param("dash") Dashboard dash);
	
	
    public  List<Dashboard> facturacionClientesMensual(@Param("dash") Dashboard dash);

    
 
}