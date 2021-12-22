


package org.gteperu.erp.everest.batch;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.gteperu.erp.everest.controller._FacturadorSunatRestController;
import org.gteperu.erp.everest.documento.DocumentoDTO;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain._Detalle_Documento_TMP;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.domain._DocumentoCpe_DetalleBean;
import org.gteperu.erp.everest.domain._Documento_TMP;
import org.gteperu.erp.everest.domain._Producto;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service._ClienteService;
import org.gteperu.erp.everest.service._Detalle_DocumentoService;
import org.gteperu.erp.everest.service._DocumentoService;
import org.gteperu.erp.everest.service._Documento_TMPService;
import org.gteperu.erp.everest.service._ProductoService;
import org.gteperu.erp.everest.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;



public class CargaMasivaWritter implements ItemWriter<List<_Documento_TMP>>, StepExecutionListener  {
	
	//ELEMENTO RESPONSABLE DE TRATAR LA INFORMACIÓN OBTENIDA POR EL READER O PROCESSOR. 
	//SI HAY UN READER DEBE HABER UN WRITTER
	
	
	private static final Logger LOG = LoggerFactory.getLogger(Sunat_padronWritterChunk.class);
	
	_ClienteService clienteService;
	_ProductoService productoService;
	_DocumentoService documentoService;
	_Detalle_DocumentoService detalle_DocumentoService;
	_Documento_TMPService documento_TMPService;
	_FacturadorSunatRestController facturador;
	Integer insersionesOk = 0;
	Integer insersionesError = 0;

	_Clientes cliente = new _Clientes();
	_Producto prod = new _Producto();
	public CargaMasivaWritter(_ClienteService clienteService,_ProductoService productoService,_DocumentoService documentoService,
			_Detalle_DocumentoService detalle_DocumentoService,_Documento_TMPService documento_TMPService,_FacturadorSunatRestController facturador){
		this.clienteService = clienteService;
		this.productoService = productoService;
		this.documentoService=documentoService;
		this.detalle_DocumentoService = detalle_DocumentoService;
		this.documento_TMPService = documento_TMPService;
		this.facturador=facturador;

	}
	
	@Override
	public void beforeStep(StepExecution stepExecution) {

		LOG.info("Line Writer initialized.");
	}

	@Override
	public void write(List<? extends List<_Documento_TMP>> items) throws Exception {
		Integer insertaCliente=0;
		Integer insertaProducto=0;
		Integer del=0;
		Integer upd=0;
		List<_Documento_TMP> lsdocumentos = new ArrayList<_Documento_TMP>();
		DocumentoDTO dto = new DocumentoDTO();
		_DocumentoCpe cpe = new _DocumentoCpe();
		ResponseWrapper response = new ResponseWrapper();
		try{
			lsdocumentos.addAll(items.get(0));
				
			for(_Documento_TMP doc: lsdocumentos) {
				cliente = clienteService.retornaClientexDocumentoTMP(doc);
				if(cliente!=null) {
					insertaCliente=this.actualizaCliente(doc);
				}else {
					insertaCliente=this.insertaCliente(doc);
				}
				for(_Detalle_Documento_TMP det:doc.getLsdetalle()) {
					det.setIid_empresa(doc.getIid_empresa());
					prod = productoService.retornaProductoxDocumentoTMP(det);
					if(prod!=null) {
						insertaProducto=this.actualizaProducto(det);
					}else {
						insertaProducto=this.insertaProducto(det, doc);
					}
				}
				cpe =this.armaObjCPE(doc);
				dto.setDocumentoCpe(cpe);
				try {
					response = facturador.generaDocumento(dto, null);
				}catch(ExceptionResponse e) {
					response.setEstado(Constantes.valTransaccionErrornull);
					response.setMsg(e.getDetalles());
				}
				doc.setSmsj_registro(response.getMsg());
				if(response.getEstado()==1) {
					insersionesOk++;
					del=documento_TMPService.eliminaDocumentoTMP(doc);

				}else {insersionesError++;
					upd=documento_TMPService.actualizaMsgRegistro(doc);
				}
			}
			System.out.println("TERMINO DE INSERTAR LA LISTA");
			System.out.println("Insersiones correctas :" + insersionesOk);
			System.out.println("Insersion fallidas :" + insersionesError);

		}catch(Exception e){
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/write => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/write" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw new RuntimeException();
		}
	}
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		//se pasa el parametro al listener

        return ExitStatus.COMPLETED;
	}

	public _DocumentoCpe armaObjCPE(_Documento_TMP doc) {
		_DocumentoCpe docCPE=new _DocumentoCpe();
		try{
			
			docCPE.setId_documento(doc.getIid_documento_tmp());
			docCPE.setId_empresa(doc.getIid_empresa());
			docCPE.setId_cliente_cpe(cliente.getId_cliente());
			docCPE.setTotal(doc.getDtotal());
			docCPE.setSub_total(doc.getDsub_total());
			docCPE.setTotal_igv(doc.getDtotal_igv());
			docCPE.setPor_igv(Constantes.porcentajeIGV);
			docCPE.setTotal_isc(0.0);
			docCPE.setNro_comprobante(doc.getSnro_comprobante());
			docCPE.setFecha_documento(new Timestamp(System.currentTimeMillis()));
			docCPE.setFecha_vto(new Timestamp(System.currentTimeMillis()));
			docCPE.setCod_tipo_documento(doc.getStipo_documento());
			docCPE.setTipo_operacion(doc.getStipo_operacion());
			docCPE = this.retornaOperacionesGravInafExo(doc.getLsdetalle(), docCPE);

			docCPE.setTotal_exportacion(0);
			docCPE.setTotal_gratuitas(doc.getDtotal_gratuitas());
			docCPE.setTotal_percepcion(doc.getDtotal_percepciones());
			docCPE.setTotal_retenciones(0);
			docCPE.setValor_percepcion(0);
			docCPE.setTotal_detracciones(0);
			docCPE.setTotal_bonificaciones(0);
			docCPE.setDescuento_global(0);
			docCPE.setTotal_descuento(0);
			docCPE.setTotal_otr_imp(0);
			docCPE.setSerie_comprobante(doc.getSserie_comprobante());
			

			docCPE.setCod_moneda(doc.getScod_moneda());

			List<_DocumentoCpe_DetalleBean> list = new ArrayList<_DocumentoCpe_DetalleBean>();
			for(_Detalle_Documento_TMP detalle : doc.getLsdetalle()) {
				_DocumentoCpe_DetalleBean dd = new _DocumentoCpe_DetalleBean();
				dd.setCantidad(detalle.getDcantidad());
				dd.setPrecio(detalle.getDprecio());
				dd.setImporte(detalle.getDimporte());
				dd.setIgv(detalle.getDigv());
				dd.setCodigo(detalle.getScodigo());
				dd.setDescripcion(detalle.getSdescripcion());
				dd.setUnidad_medida(detalle.getSunidad_medida());
				dd.setPrecio_tipo_codigo("01");
				dd.setCod_tipo_operacion(detalle.getScod_tipo_operacion());
				if(detalle.getScod_sunat()!=null) {
					dd.setCod_sunat(detalle.getScod_sunat());					
				}else {
					dd.setCod_sunat("");					
				}
				dd.setAumento_unitario(0);
				dd.setIgv_aumento(0);
				list.add(dd);
			}
			docCPE.setDetalle(list);
			
	 	} catch(Exception e){
			System.out.println(this.getClass().getSimpleName() + "/armaObjCPE" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw e;
	 	}
		return docCPE;
	}
	
	public Integer actualizaCliente(_Documento_TMP doc) {
		Integer val = 0;
		cliente.setRazon_social(doc.getSrazon_social_cliente());
		cliente.setDireccion_fiscal(doc.getSdireccion_cliente());
		val = clienteService.actualizarClientexDocTMP(cliente);
        return val;
	}
	public Integer insertaCliente(_Documento_TMP doc) {
		cliente = new _Clientes();
		cliente.setTipo_doc(doc.getStipo_documento_cliente());
		cliente.setNro_doc(doc.getSnumero_documento_cliente());
		cliente.setRazon_social(doc.getSrazon_social_cliente());
		cliente.setDireccion_fiscal(doc.getSdireccion_cliente());
		cliente.setId_empresa(doc.getIid_empresa());
		cliente.setIdubigeo(Constantes.CodUbigeoGenerico);
		Boolean insert = clienteService.insertarCliente(cliente);
        if(insert) {
        	return 1;
        }else {
        	return 0;
        }
	}
	
	public Integer actualizaProducto(_Detalle_Documento_TMP det) {
		Integer val = 0;
		prod.setDescripcion(det.getSdescripcion());
		prod.setIgv_afecto(det.getScod_tipo_operacion());
		val = productoService.actualizarProducto(prod);
        return val;
	}
	public Integer insertaProducto(_Detalle_Documento_TMP det,_Documento_TMP doc) {
		Integer val = 0;
		prod = new _Producto();
		prod.setId_empresa(doc.getIid_empresa());
		prod.setCodigo_producto_interno(det.getScodigo());
		prod.setCodigo_sunat(det.getScod_sunat());
		prod.setDescripcion(det.getSdescripcion());
		prod.setIgv_afecto(det.getScod_tipo_operacion());
		prod.setEstado(1);
		prod.setValor_precio_venta(det.getDprecio());
		val = productoService.insertarProducto(prod);
        return val;
	}
	public _DocumentoCpe retornaOperacionesGravInafExo(List<_Detalle_Documento_TMP> lsdocumentos,_DocumentoCpe docu) {
		double gravadas =0;
		double inafectas = 0;
		double exoneradas=0;
		for(_Detalle_Documento_TMP det : lsdocumentos) {
			
			switch(det.getScod_tipo_operacion()) {
			case Constantes.Codigogravadooperaciónonerosa:
			case Constantes.Codigogravadooperaciónporpremio:
			case Constantes.Codigogravadoretiropordonacion:
			case Constantes.Codigogravadoretiro:
			case Constantes.Codigogravadoretiroporpublicidad:
			case Constantes.Codigogravadobonificaciones:
			case Constantes.Codigogravadoretiroporentregaatrabajadores:
			case Constantes.CodigogravadoIVAP:
				gravadas = gravadas + det.getDimporte();
				break;
			case Constantes.Codigoexoneradooperaciónonerosa:
			case Constantes.Codigoexoneradotransferenciagratuita:
				exoneradas = exoneradas + det.getDimporte();
				break;
			case Constantes.Codigoinafectooperaciónonerosa:
			case Constantes.Codigoinafectoretiroporbonificacion:
			case Constantes.Codigoinafectoretiro:
			case Constantes.Codigoinafectoretiropormuestrasmedicas:
			case Constantes.Codigoinafectoretiroporconveniocolectivo:
			case Constantes.CodigoinafectoRetiroporpremio:
			case Constantes.Codigoinafectoretiroporpublicidad:
				inafectas = inafectas + det.getDimporte();
				break;

			}
		}
		docu.setTotal_gravadas(gravadas);
		docu.setTotal_inafecta(inafectas);
		docu.setTotal_exoneradas(exoneradas);
		
		return docu;

	}
}
