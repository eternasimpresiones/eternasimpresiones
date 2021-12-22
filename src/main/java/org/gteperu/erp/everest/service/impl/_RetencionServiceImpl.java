package org.gteperu.erp.everest.service.impl;

import org.springframework.stereotype.Service;
import org.gteperu.erp.everest.domain._Cpe_RetencionPercepcionBean;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._Producto;
import org.gteperu.erp.everest.domain._Retencion;
import org.gteperu.erp.everest.domain._RetencionExcel;
import org.gteperu.erp.everest.mappers.DocumentoMapper;
import org.gteperu.erp.everest.mappers.RetencionMapper;
import org.gteperu.erp.everest.service._RetencionService;

import java.util.List;
import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service("retencionService")
public class _RetencionServiceImpl implements _RetencionService {

    @Resource(name = "retencionMapper")
    RetencionMapper retencionMapper;
    
    @Override
    public Integer insertarDocumentoRetencion(_Retencion documento) {
    		Integer auxiliar = 0;
		try {
			if(documento.getBdocborrador()==null) {
				documento.setBdocborrador(true);
			}
			
			auxiliar = retencionMapper.insertarDocumentoRetencion(documento);
		} catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/insertarDocumento => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/insertarDocumento" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return auxiliar;
    }

	@Override
	public List<_Retencion> lsRetencionPercepcion(_Retencion retencion) {
		List<_Retencion> lsRetencionPercepcion = new ArrayList<_Retencion>();
		try{
			//VALORES PAGINA
			Integer indice = retencion.getInd_pag();
			Integer cantidad = retencion.getCan_pag();
			if(indice == 1) {
				retencion.setOffset(indice - 1);
				retencion.setLimit(cantidad);
			}else {
				retencion.setOffset(((indice - 1) * cantidad));
				retencion.setLimit(cantidad);
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String fecha_documentoini  = dateFormat.format(new Date(retencion.getFecha_inicio().getTime()));
			String fecha_documentofin  = dateFormat.format(new Date(retencion.getFecha_fin().getTime()));

			retencion.setFechaini_str(fecha_documentoini);
			retencion.setFechafin_str(fecha_documentofin);
			lsRetencionPercepcion = retencionMapper.lsRetencionOPercepcion(retencion);
		}catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/lsRetencionPercepcion => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/lsRetencionPercepcion" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return lsRetencionPercepcion;
	}
	
	@Override
	public List<_RetencionExcel> lsRetencionPercepcionExcel(_Retencion retencion) {
		List<_RetencionExcel> lsRetencionPercepcion = new ArrayList<_RetencionExcel>();
		try{
			//VALORES PAGINA
			Integer indice = retencion.getInd_pag();
			Integer cantidad = retencion.getCan_pag();
			if(indice == 1) {
				retencion.setOffset(indice - 1);
				retencion.setLimit(cantidad);
			}else {
				retencion.setOffset(((indice - 1) * cantidad));
				retencion.setLimit(cantidad);
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String fecha_documentoini  = dateFormat.format(new Date(retencion.getFecha_inicio().getTime()));
			String fecha_documentofin  = dateFormat.format(new Date(retencion.getFecha_fin().getTime()));

			retencion.setFechaini_str(fecha_documentoini);
			retencion.setFechafin_str(fecha_documentofin);
			lsRetencionPercepcion = retencionMapper.lsRetencionOPercepcionExcel(retencion);
		}catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/lsRetencionPercepcionExcel => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/lsRetencionPercepcionExcel" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return lsRetencionPercepcion;
	}
	
    @Override
    public Integer actualizarRetencionEstadoRechazado(_Retencion documento) {
    		Integer auxiliar = 0;
		try {
			auxiliar = retencionMapper.actualizarRetencionEstadoRechazado(documento);
		} catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/insertarDocumento => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/insertarDocumento" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return auxiliar;
    }
    
	@Override
	public List<_Retencion> lsRetencionOPercepcionxEstado(_Retencion retencion) {
		List<_Retencion> lsRetencionPercepcion = new ArrayList<_Retencion>();
		try{
			lsRetencionPercepcion = retencionMapper.lsRetencionOPercepcionxEstado(retencion);
		}catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/lsRetencionPercepcion => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/lsRetencionPercepcion" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return lsRetencionPercepcion;
	}
	
	@Override
    public Integer cantidadRetePerc(_Retencion retencion) {
    		Integer auxiliar = 0;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			String fecha_documentoini  = dateFormat.format(new Date(retencion.getFecha_inicio().getTime()));
			String fecha_documentofin  = dateFormat.format(new Date(retencion.getFecha_fin().getTime()));
			
			retencion.setFechaini_str(fecha_documentoini);
			retencion.setFechafin_str(fecha_documentofin);
			auxiliar = retencionMapper.cantidadRetePerc(retencion);
		} catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/cantidadDocumentos => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/cantidadDocumentos" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return auxiliar;
    }
	
	   @Override
	    public Integer eliminarDocumentoRetencion(_Retencion documento) {
	    		Integer auxiliar = 0;
			try {
				auxiliar = retencionMapper.eliminarDocumentoRetencion(documento);
			} catch (Exception e) {
				System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/eliminarDocumentoRetencion => " + e.getMessage());
				System.err.println(this.getClass().getSimpleName() + "/eliminarDocumentoRetencion" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
				throw e;
			}
			return auxiliar;
	    }
}