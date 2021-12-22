package org.gteperu.erp.everest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.gteperu.erp.everest.domain.Detalle_plantilla_documento;
import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.Plantilla_documento;
import org.gteperu.erp.everest.mappers.PlantillaDocumentoMapper;
import org.gteperu.erp.everest.service.PlantillaDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("plantilladocmapper")
public class PlantillaDocumentoServiceImpl implements PlantillaDocumentoService{
	
	@Autowired
	PlantillaDocumentoMapper plantilladocmapper;

	@Override
	public Integer insertarPlantillaDocumento(Plantilla_documento objPlantilla) {
		Integer insert=0;
		Integer item=0;
		try {
			if(objPlantilla.getSobservacion()==null) {
				objPlantilla.setSobservacion("");
			}
			insert=plantilladocmapper.registrarPlantillaDocumento(objPlantilla);
			if(objPlantilla.getLsDetallePlantillaDocumento()!=null) {
				for (int  i= 0; i< objPlantilla.getLsDetallePlantillaDocumento().size(); i++) {
					item++;
					objPlantilla.getLsDetallePlantillaDocumento().get(i).setIitem(item);
					objPlantilla.getLsDetallePlantillaDocumento().get(i).setIid_plantilla_documento(objPlantilla.getIid_plantilla_documento());
				}
				objPlantilla.getLsDetallePlantillaDocumento().forEach(detalle->{
					plantilladocmapper.registrarDetallePlantillaDocumento(detalle);
				});
			}
			
		} catch (Exception e) {
			e.getMessage();
		}
		return insert;
	}

	@Override
	public Integer actualizarPlantillaDocumento(Plantilla_documento objPlantilla) {
		Integer update=0;
		Integer iitem=0;
		try {
			 update =plantilladocmapper.actualizarPlantillaDocumento(objPlantilla);
			plantilladocmapper.eliminarDetallePlantillaDocumento(objPlantilla.getIid_plantilla_documento());
			for (int  i= 0; i< objPlantilla.getLsDetallePlantillaDocumento().size(); i++) {
				iitem++;
				objPlantilla.getLsDetallePlantillaDocumento().get(i).setIitem(iitem);
				objPlantilla.getLsDetallePlantillaDocumento().get(i).setIid_plantilla_documento(objPlantilla.getIid_plantilla_documento());
			}
			objPlantilla.getLsDetallePlantillaDocumento().forEach(detalle->{
				plantilladocmapper.registrarDetallePlantillaDocumento(detalle);
			});
		} catch (Exception e) {
			e.getMessage();
		}
		return update;
	}

	@Override
	public Integer eliminarPlantillaDocumento(Integer objPlantilla) {
		Integer valor=0;
		try {
			valor = plantilladocmapper.eliminarDetallePlantillaDocumento(objPlantilla);
			valor = valor + plantilladocmapper.eliminarPlantillaDocumento(objPlantilla);
			
		} catch (Exception e) {
			throw e;
		}
		return valor;
	}

	@Override
	public List<Plantilla_documento> listarPlantillasTodas(Plantilla_documento objPlantilla) {
		List<Plantilla_documento> lsPlantilla=new ArrayList<Plantilla_documento>();
		try {
			lsPlantilla=plantilladocmapper.listarPlantillaTodas(objPlantilla);
		} catch (Exception e) {
			throw e;
		}
		return lsPlantilla;
	}

	@Override
	public List<Plantilla_documento> listarPlantillas(Plantilla_documento objPlantilla) {
		List<Plantilla_documento> lsPlantilla=new ArrayList<Plantilla_documento>();
		Pagination pg = new Pagination();
		try {
			pg = plantilladocmapper.retornaCantidadList(objPlantilla);
			lsPlantilla=plantilladocmapper.retornaPlantilla(objPlantilla);
			if (lsPlantilla != null && lsPlantilla.size() > 0) {
				lsPlantilla.get(0).setCantidad(pg.getCantidad());
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" listarPlantillas. ERROR : "+e.getMessage());
			throw e;
		}
		return lsPlantilla;
	}
	
	

}
