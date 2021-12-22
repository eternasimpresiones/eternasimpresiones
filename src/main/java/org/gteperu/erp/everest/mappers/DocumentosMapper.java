package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Documentos;
import org.gteperu.erp.everest.domain.Pagination;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface DocumentosMapper {

	public List<Documentos> retornaDocumentosPorDocReferenciaTipoOperacion(
			@Param("documentos") Documentos iddocumentos);

	public List<Documentos> retornaDocumentosPorEstado(@Param("documentos") Documentos iddocumentos);

	public List<Documentos> retornaDocumentosPorEstadoPorIdCliente(@Param("documentos") Documentos iddocumentos);

	public List<Documentos> retornaDocumentosPorEstadoPorIdClienteRetencion(
			@Param("documentos") Documentos iddocumentos);

	public List<Documentos> retornaDocumentosPorEstadoPorIdClientePercepcion(
			@Param("documentos") Documentos iddocumentos);

	public Documentos retornaDocumentosPorTipoVentaCompra(@Param("documentos") Documentos iddocumentos);

	public Documentos retornaCodigoMaxPorTipoDocVentaCompra(@Param("documentos") Documentos iddocumentos);

	public List<Documentos> retornaDocumentosVentasPorEstado(@Param("documentos") Documentos iddocumentos);

	public List<Documentos> retornaDocumentosCobranzaPorEstado(@Param("documentos") Documentos iddocumentos);

	public List<Documentos> retornaDocumentosPorFiltroCobrosVentas(@Param("documentos") Documentos iddocumentos);

	public List<Documentos> retornaDocumentosPagosCompras(@Param("documentos") Documentos iddocumentos);

	public List<Documentos> retornaDocumentosFiltroPagosCompras(@Param("documentos") Documentos iddocumentos);

	public List<Documentos> retornaDocumentosPorFiltroComprasReporte(@Param("documentos") Documentos iddocumentos);

	public List<Documentos> retornaDocumentosPorFiltroVentasReporte(@Param("documentos") Documentos iddocumentos);

	public Pagination retornaCantidadDocumentosComprasPorEstado(@Param("documentos") Documentos iddocumentos);
public Pagination retornaCantidadDocumentosVentasPorEstado(@Param("documentos") Documentos iddocumentos);
	public List<Documentos> retornaDocumentosComprasPorEstado(@Param("documentos") Documentos iddocumentos);

	public List<Documentos> retornaDocumentosComprasTotales(@Param("documentos") Documentos iddocumentos);

	public List<Documentos> retornaDocumentosComprasPorDocumentoProveedorGuias(
			@Param("documentos") Documentos iddocumentos);

	public List<Documentos> retornaDocumentosVentasTotales(@Param("documentos") Documentos iddocumentos);

	public Documentos retornaDocumentosComprasPorCodigoSeriearaGuias(@Param("documentos") Documentos iddocumentos);

	public Documentos retornaDocumentosVentasPorCodigoSeriearaGuias(@Param("documentos") Documentos iddocumentos);

	public List<Documentos> retornaDocumentosPorFiltroCompras(@Param("documentos") Documentos iddocumentos);

	public List<Documentos> retornaDocumentosPorFiltroVentasParaCobranza(@Param("documentos") Documentos iddocumentos);

	public List<Documentos> retornaDocumentosPorFiltroVentas(@Param("documentos") Documentos iddocumentos);

	public List<Documentos> retornaDocumentosPorFiltroCobranzaVentas(@Param("documentos") Documentos iddocumentos);

	public List<Documentos> retornaLibroVentasIngresos(@Param("documentos") Documentos iddocumentos);

	public List<Documentos> retornaLibroCompras(@Param("documentos") Documentos iddocumentos);
	public Documentos retornaObjDocumentosPorCodigoSerieCliente(@Param("documentos") Documentos iddocumentos);
	public Documentos retornaObjDocumentosPorCodigoSerie(@Param("documentos") Documentos iddocumentos);
	
	@Select(" select codigo,serie from  documentos"
			+ " where codigo=#{codigo} and serie=#{serie} "
			+ "and idsutipocomprovantepagodocumento=#{idsutipocomprovantepagodocumento} "
			+ "and idcliente=#{idcliente} and idperiodo=#{idperiodo}; ")
	public Documentos retornaObjDocumentosRepetidos(Documentos iddocumentos);
	
	
	@Select(" select * from  documentos where  iddocumentos=#{iddocumentos} and idempresa=#{idempresa} and idperiodo=#{idperiodo}; ")
	public Documentos retornaDocuemntoCabeceraPorId(Documentos iddocumentos);

	public List<Documentos> retornaObjDocumentosPorSerie(@Param("documentos") Documentos iddocumentos);

	public Documentos retornaObjDocumentosPorCodigoSerieVentas(@Param("documentos") Documentos iddocumentos);

	public Documentos retornaObjDocumentosPorCodigoSerieCompras(@Param("documentos") Documentos iddocumentos);

	public Documentos retornaObjDocumentos(@Param("documentos") Documentos iddocumentos);

	@Update("update documentos set idproyecto=#{idproyecto},estadosalida=#{estadosalida},estadotesoreria=#{estadotesoreria}, estado=#{estado}, estadodetraccion=#{estadodetraccion}, estadopercepcion=#{estadopercepcion}  where iddocumentos =#{iddocumentos};")
	public Integer anuladocumentosGeneral(Documentos iddocumentos);

	@Update("update documentos set estadodetraccion=#{estadodetraccion} where iddocumentos =#{iddocumentos};")
	public Integer updateEstadoDetraccion(Documentos iddocumentos);

	@Update("update documentos set estadopercepcion=#{estadopercepcion} where iddocumentos =#{iddocumentos};")
	public Integer updateEstadoPercepcion(Documentos iddocumentos);

	@Update("update documentos set estadoretencion=#{estadoretencion} where iddocumentos =#{iddocumentos};")
	public Integer updateEstadoRetencion(Documentos iddocumentos);

	@Update("update documentos set subtotal=#{subtotal},impuesto=#{impuesto},total=#{total} where iddocumentos =#{iddocumentos};")
	public Integer updateDocumentoImportes(Documentos iddocumentos);
	
	
	@Update("update documentos set estadoretencion=#{estadoretencion},estadopercepcion=#{estadopercepcion}, estadodetraccion=#{estadodetraccion}, estadosalida=#{estadosalida},impuestocancelado=#{impuestocancelado},subtotalcancelado=#{subtotalcancelado},porcentajeanticipo= #{porcentajeanticipo},montocancelado=#{montocancelado},idtipomoneda=#{idtipomoneda}, idcondicionespago=#{idcondicionespago}, idcliente=#{idcliente}, subtotal=#{subtotal}, impuesto=#{impuesto}, descuento=#{descuento}, total=#{total}, comentario=#{comentario}, estado=#{estado}, fecharegistro=now(), codigo=#{codigo} where iddocumentos =#{iddocumentos};")
	public Integer updateDocumentos(Documentos iddocumentos);

	@Update("update documentos set   notadebitocredito=#{documentos.notadebitocredito} where iddocumentos =#{documentos.documentoreferencia};")
	public Integer updateDocumentosCantidadNotadebitoCredito(@Param("documentos") Documentos iddocumentos);

	@Update("update documentos set  montocancelado=#{documentos.montocancelado},estadotesoreria=#{documentos.estadotesoreria},fechapagoultima=now() where iddocumentos =#{documentos.iddocumentos};")
	public Integer updateDocumentosEstadoPAgo(@Param("documentos") Documentos iddocumentos);

	@Update("update documentos set  estadosalida=#{documentos.estadosalida} where iddocumentos =#{documentos.iddocumentos};")
	public Integer updateDocumentosEstadoGuiado(@Param("documentos") Documentos iddocumentos);

	@Insert(" insert into documentos(idseriedocumentos,idproyecto,idtipoconcepto,tipodecambio,tipooperacion,idsutipooperacion,idperiodo,estadosalida,impuestocancelado,subtotalcancelado,porcentajeanticipo,montocancelado,estadotesoreria,serie,idempresa,tipodocasociado,documentoreferencia,idsutipocomprovantepagodocumento,idtipomoneda,idcondicionespago,idcliente,subtotal,"
			+ "impuesto,descuento,total,comentario,estado,codigo,fechapago,diaspago,idcreditoclientes,entregadias,fechaentrega)"
			+ "values(#{idseriedocumentos},#{idproyecto},#{idtipoconcepto},#{tipodecambio},#{tipooperacion},#{idsutipooperacion},#{idperiodo},#{estadosalida},#{impuestocancelado},#{subtotalcancelado},#{porcentajeanticipo},#{montocancelado},#{estadotesoreria},#{serie},#{idempresa},#{tipodocasociado},#{documentoreferencia},#{idsutipocomprovantepagodocumento},#{idtipomoneda},#{idcondicionespago},#{idcliente},#{subtotal},#{impuesto},#{descuento},#{total},#{comentario},#{estado},#{codigo},#{fechapago},#{diaspago},#{idcreditoclientes},#{entregadias},#{fechaentrega});")
	@SelectKey(statement = "select currval('sec_documentos') as iddocumentos", keyProperty = "iddocumentos", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "iddocumentos", flushCache = FlushCachePolicy.TRUE)
	public Integer insertaDocumentos(Documentos iddocumentos);

	@Update(" update  documentos set estado=0 where iddocumentos=#{iddocumentos}; ")
	public Integer eliminaDocumentos(Documentos iddocumentos);

	@Select(" select * from  documentos where iddocumentos=#{iddocumentos}; ")
	public Documentos retornaDocumentosPorId(Documentos iddocumentos);
}