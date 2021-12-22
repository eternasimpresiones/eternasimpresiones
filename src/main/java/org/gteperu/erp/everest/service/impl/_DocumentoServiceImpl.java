package org.gteperu.erp.everest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.gteperu.erp.everest.domain.Auditoria_Sunat;
import org.gteperu.erp.everest.domain.Cuota;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._DocumentoCpe_DetalleBean;
import org.gteperu.erp.everest.domain._DocumentoCpe_Excel;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.mappers.CompanyMapper;
import org.gteperu.erp.everest.mappers.CuotaMapper;
import org.gteperu.erp.everest.mappers.DetalleDocumentoMapper;
import org.gteperu.erp.everest.mappers.DocumentoMapper;
import org.gteperu.erp.everest.service.Auditoria_SunatService;
import org.gteperu.erp.everest.service._DocumentoService;
import org.gteperu.erp.everest.utils.Constantes;

import java.util.List;
import javax.annotation.Resource;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Service("documentoService")
public class _DocumentoServiceImpl implements _DocumentoService {

    @Resource(name = "documentoMapper")
    DocumentoMapper documentoMapper;
    @Resource(name = "detalleDocumentoMapper")
    DetalleDocumentoMapper detalleDocumentoMapper;
    @Autowired
    private Auditoria_SunatService auditoria_SunatService;

    
    @Override
    public Integer insertarDocumento(_DocumentoCpe documento) {
    		Integer auxiliar = 0;
		try {
			if(documento.getBdocborrador()==null) {
				documento.setBdocborrador(true);
			}
			
			auxiliar = documentoMapper.insertarDocumento(documento);
			if(auxiliar>0) {
				
				Auditoria_Sunat auditoria_Sunat = new Auditoria_Sunat();
				
				auditoria_Sunat.setId_documento(documento.getId_documento());
				auditoria_Sunat.setFlag_respuesta_sunat("0");
				auditoria_Sunat.setCodigo_respuesta_sunat("0");
				auditoria_Sunat.setMensaje_respuesta_sunat("SIN ENVIO");
				auditoria_Sunat.setId_usuario(documento.getId_usuario());
				auditoria_Sunat.setFecha_registro(new Timestamp(System.currentTimeMillis()));
				Integer inserta_auditoria = auditoria_SunatService.insertaAuditoria_Sunat(auditoria_Sunat);
				System.out.print("\nSE INSERTO AUDITORIA_SUNAT => " + inserta_auditoria.toString());
				

			}
			
		} catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/insertarDocumento => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/insertarDocumento" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return auxiliar;
    }
       
	 @Override
		public _DocumentoCpe retornaDocumentoId(_DocumentoCpe documento)
	 { 
		 _DocumentoCpe  doc = new _DocumentoCpe();
		 	try {
				doc = documentoMapper.retornaDocumentoId(documento);
			} catch (Exception e) {
				System.out.println(this.getClass().getSimpleName()+" retornaDocumentoId. ERROR : "+e.getMessage());
				throw e;
			}
				return doc;
			
	    }

    @Override
    public List<_DocumentoCpe> listarDocumento(_DocumentoCpe doc){
        	List<_DocumentoCpe> lsCliente = new ArrayList<_DocumentoCpe>();
		try {
			if(doc.getInd_pag() != null) {
			//VALORES PAGINA
			Integer indice = doc.getInd_pag();
			Integer cantidad = doc.getCan_pag();
			if(indice == 1) {
				doc.setOffset(indice - 1);
				doc.setLimit(cantidad);
			}else {
				doc.setOffset(((indice - 1) * cantidad));
				doc.setLimit( cantidad);
			}}
			lsCliente = documentoMapper.listarDocumento(doc);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" listarDocumento. ERROR : "+e.getMessage());
			throw e;
		}
		return lsCliente;
    }
    
    
    @Override
    public List<_DocumentoCpe> listarDocumentoPublico(_DocumentoCpe doc){
        	List<_DocumentoCpe> lsDocumento = new ArrayList<_DocumentoCpe>();
		try {
		 
			lsDocumento = documentoMapper.listarDocumentoPublico(doc);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" listarDocumentoPublico. ERROR : "+e.getMessage());
			throw e;
		}
		return lsDocumento;
    }
    
    
    @Override
    public List<_DocumentoCpe> listarDocumentoExcel(_DocumentoCpe doc){
        	List<_DocumentoCpe> lsdocumento = new ArrayList<_DocumentoCpe>();
		try {
			if(doc.getInd_pag() != null) {
			//VALORES PAGINA
			Integer indice = doc.getInd_pag();
			Integer cantidad = doc.getCan_pag();
			if(indice == 1) {
				doc.setOffset(indice - 1);
				doc.setLimit(cantidad);
			}else {
				doc.setOffset(((indice - 1) * cantidad));
				doc.setLimit( cantidad);
			}}
			lsdocumento = documentoMapper.retornaDocumentoPorIddocumentoExcel(doc);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" listarDocumentoExcel. ERROR : "+e.getMessage());
			throw e;
		}
		return lsdocumento;
    }
    
    @Override
    public List<_DocumentoCpe> listarDocumentoExcelAll(_DocumentoCpe doc){
        	List<_DocumentoCpe> lsdocumento = new ArrayList<_DocumentoCpe>();
		try {
		 	lsdocumento = documentoMapper.retornaDocumentoPorIddocumentoExcel(doc);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" listarDocumentoExcelAll. ERROR : "+e.getMessage());
			throw e;
		}
		return lsdocumento;
    }
    
    @Override
    public Integer cantidadDocumentos(_DocumentoCpe documento) {
    		Integer auxiliar = 0;
		try {
			auxiliar = documentoMapper.cantidadDocumentos(documento);
		} catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/cantidadDocumentos => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/cantidadDocumentos" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
		}
		return auxiliar;
    }
    
    @Override
    public List<_DocumentoCpe> listarDocumentoxEstado(_DocumentoCpe doc){
    	
        	List<_DocumentoCpe> lsCliente = new ArrayList<_DocumentoCpe>();

		try {
			lsCliente = documentoMapper.listarDocumentoxEstado(doc);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" listarDocumentoxEstado. ERROR : "+e.getMessage());
			throw e;
		}
		return lsCliente;
		
    }
    
    @Override
    public Integer actualizarDocumento(_DocumentoCpe id_documento) {
    	
			Integer update=0;
			
		try{
			update = documentoMapper.actualizarDocumento(id_documento);
			if(update>0) {
				_DocumentoCpe_DetalleBean detalleOld= new _DocumentoCpe_DetalleBean();
				detalleOld.setId_documento(id_documento.getId_documento());
				detalleDocumentoMapper.eliminarDocumento_DetallePorIdDocumento(detalleOld);

				for(_DocumentoCpe_DetalleBean det: id_documento.getLs_documentoCpe()) {
					detalleDocumentoMapper.insertarDocumento_Detalle(det);
				}
			}
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " actualizarDocumento. ERROR : " + e.getMessage());
			throw e;		
		}
		return update;	
		
	}
    
    @Override
    public Integer actualizarDocumentoEstadoRechazado(_DocumentoCpe id_documento) {
		Integer update=0;
		try{
			update = documentoMapper.actualizarDocumentoEstadoRechazado(id_documento);
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " actualizarDocumento. ERROR : " + e.getMessage());
			throw e;
		}
		return update;	
		
	}
    
    @Override
    public Integer eliminarDocumento(_DocumentoCpe id_documento) {
    	
			Integer delete=0;
			
		try{
			delete = documentoMapper.eliminarDocumento(id_documento);
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " eliminarDocumento. ERROR : " + e.getMessage());
			throw e;
		}
			return delete;	
			
	}
    
    @Override
    public _DocumentoCpe retornaDocumentoCPEXML(_DocumentoCpe documento){
    	_DocumentoCpe cpe = new _DocumentoCpe();
    	try{
    		cpe = documentoMapper.retornaDocumentoCPEXML(documento);
    	}catch(Exception e){
    		System.out.println(this.getClass().getSimpleName()+ " eliminarDocumento. ERROR : " + e.getMessage());
    		throw e;
    	}
    	return cpe;
    }
    
    @Override
    public String retornaultimonrocomprobante(_DocumentoCpe id_documento) {
    	
			String nro_comprobante="";
			
		try{
			nro_comprobante = documentoMapper.retornaultimonrocomprobante(id_documento);
			if(nro_comprobante == null || nro_comprobante==""){
				nro_comprobante = "0000001";
				System.out.println("Primer numero de comprobante => "+ nro_comprobante);
			}else{
				nro_comprobante = Long.toString(Long.parseLong(nro_comprobante) + 1);
				nro_comprobante = String.format("%7s",nro_comprobante).replace(' ','0');
			}
			
			System.out.println("Ultimo numero de comprobante => "+ nro_comprobante);
			
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " actualizarDocumento. ERROR : " + e.getMessage());
			throw e;
		}
		return nro_comprobante;	
		
	}
    
    @Override
    public _DocumentoCpe retornaDocumentoPorId(_DocumentoCpe id_documento) {
    	_DocumentoCpe documentoCpe = new _DocumentoCpe();
		try{
			documentoCpe = documentoMapper.retornaDocumentoPorId(id_documento);
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " retornaDocumentoPorId. ERROR : " + e.getMessage());
			throw e;
		}
			return documentoCpe;	
		
	}
    
    @Override
    public _DocumentoCpe retornaDocumentoPorSerieNum(_DocumentoCpe id_documento) {
    	_DocumentoCpe documentoCpe = new _DocumentoCpe();
		try{
			documentoCpe = documentoMapper.retornaDocumentoPorSerieNum(id_documento);
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " retornaDocumentoPorId. ERROR : " + e.getMessage());
			throw e;
		}
			return documentoCpe;	
		
	}
    
    @Override
    public List<_DocumentoCpe> retornalsNroDocumento(_DocumentoCpe id_documento) {
    	List<_DocumentoCpe> lsdocumentoCpe = new ArrayList<_DocumentoCpe>();
		try{
			lsdocumentoCpe = documentoMapper.retornalsNroDocumento(id_documento);
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " retornaDocumentoPorId. ERROR : " + e.getMessage());
			throw e;
		}
			return lsdocumentoCpe;	
		
	}
    
    @Override
    public Integer ActualizarRutaArchivo(_DocumentoCpe doc) {
    	Integer insert = 0;
    	try {
    		insert = documentoMapper.actualizarRutaArchivo(doc);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+ " ActualizarRutaArchivo. ERROR : " + e.getMessage());
			throw e;
		}
    	return insert;
    }
    
    @Override
    public _DocumentoCpe RetornaRutaArchivo(_DocumentoCpe doc) {
    	_DocumentoCpe obj = new _DocumentoCpe();
    	try {
    		obj = documentoMapper.retornaRutaArchivo(doc);
    	} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+ " RetornaRutaArchivo. ERROR : " + e.getMessage());
			throw e;
    	}
    	return obj;
    }

    //RETENCION
	@Override
	public _DocumentoCpe retornaDocumentoPorSerieNumIdEmpresa(_DocumentoCpe id_documento) {
		_DocumentoCpe documentoCpe = new _DocumentoCpe();
		try{
			documentoCpe = documentoMapper.retornaDocumentoPorSerieNumIdEmpresa(id_documento);
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " retornaDocumentoPorId. ERROR : " + e.getMessage());
		}finally{
			return documentoCpe;	
		}
	}
	
	@Override
    public List<_DocumentoCpe> retornalsNroDocumentoEstado(_DocumentoCpe id_documento) {
    	List<_DocumentoCpe> lsdocumentoCpe = new ArrayList<_DocumentoCpe>();
		try{
			lsdocumentoCpe = documentoMapper.retornalsNroDocumentoEstado(id_documento);
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " retornaDocumentoPorId. ERROR : " + e.getMessage());
		}finally{
			return lsdocumentoCpe;	
		}
	}
    
    @Override
    public List<_DocumentoCpe> retornalsNroDocumentoEstadoTipoDocu(_DocumentoCpe id_documento) {
    	List<_DocumentoCpe> lsdocumentoCpe = new ArrayList<_DocumentoCpe>();
		try{
			if (id_documento.getIdlocal() == null) {
				lsdocumentoCpe = documentoMapper.retornalsNroDocumentoEstadoTipoDocu(id_documento);				
			} else {
				lsdocumentoCpe = documentoMapper.retornalsNroDocumentoLocalEstadoTipoDocu(id_documento);
			}
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ "retornalsNroDocumentoEstadoTipoDocu. ERROR : " + e.getMessage());
		}finally{
			return lsdocumentoCpe;	
		}
	}
    
    @Override
    public List<_DocumentoCpe> retornalsSerieComprobateUnico(_DocumentoCpe id_documento) {
    	List<_DocumentoCpe> lsdocumentoCpe = new ArrayList<_DocumentoCpe>();
		try{
			lsdocumentoCpe = documentoMapper.retornalsSerieComprobateUnico(id_documento);
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " retornaDocumentoPorId. ERROR : " + e.getMessage());
		}finally{
			return lsdocumentoCpe;	
		}
	}
    
    @Override
    public Boolean validarDias(_DocumentoCpe documento,String diasLimite) {
     	Boolean rpta=null;
		try{
		 Date fechaDocumento = new Date(documento.getFecha_documento().getTime());
	 	 Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

		 Timestamp fechaDocumentoLimite = new Timestamp(this.addDias(fechaDocumento,Integer.parseInt(diasLimite)+1));
	 	 if ( fechaActual.before(fechaDocumentoLimite)) {
			 rpta= true;
		 }else {
			 rpta= false;
		 }
 		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " validarDias. ERROR : " + e.getMessage());
		} 
		return rpta;
	}
    
	private long addDias(Date date, int dias) {
	 	Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, dias);
		return calendar.getTimeInMillis();
	}

	@Override
	public List<_DocumentoCpe> retornaPorTipoDocumento(_DocumentoCpe docu) {
		return documentoMapper.retornaPorTipoDocumento(docu);
	}

	@Override
	public List<_DocumentoCpe> retornaPorSerieComprobante(_DocumentoCpe docu) {
		return documentoMapper.retornaPorSerieComprobante(docu);
	}
    
}