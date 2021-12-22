package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.gteperu.erp.everest.domain._Cpe_RetencionPercepcion_DetalleBean;
import org.gteperu.erp.everest.domain._Retencion;
import org.springframework.stereotype.Repository;

@Repository("detalle_RetencionMapper")
public interface Detalle_RetencionMapper {

				
	@Insert(" insert into detalle_retencion(cod_tipo_documento,id_retencion,nro_documento,fecha_documento,"
			+ "cod_moneda,monto_total,nro_doc_pago,fecha_pago,moneda_retenida,"
			+ "monto_retenido,fecha_retenida,moneda_pago_neto,monto_pago_neto,"
			+ "nro_doc_cobro,fecha_cobro,moneda_percibido,"
			+ "monto_percibido,fecha_percepcion,moneda_cobro_neto,monto_cobro_neto)"
			+ "values(#{COD_TIPO_DOCUMENTO},#{id_retencion},#{NRO_DOCUMENTO},#{FECHA_DOCUMENTO},#{COD_MONEDA},"
			+ "#{MONTO_TOTAL},#{NRO_DOC_PAGO},#{FECHA_PAGO},#{MONEDA_RETENIDA},"
			+ "#{MONTO_RETENIDO},#{FECHA_RETENIDA},#{MONEDA_PAGO_NETO},#{MONTO_PAGO_NETO},"
			+ "#{NRO_DOC_COBRO},#{FECHA_COBRO},#{MONEDA_PERCIBIDO},"
			+ "#{MONTO_PERCIBIDO},#{FECHA_PERCEPCION},#{MONEDA_COBRO_NETO},#{MONTO_COBRO_NETO});")
    @SelectKey(statement = "select currval('sec_detalle_retencion') as id__retencion_detalle", keyProperty = "id__retencion_detalle", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "id__retencion_detalle", flushCache = FlushCachePolicy.TRUE)
    public Integer insertarDetalleDocumentoRetencion(_Cpe_RetencionPercepcion_DetalleBean documento);

	
	
	 @Delete(" delete from detalle_retencion where id_retencion=#{id_retencion}; ")
	public Integer eliminarDetalleDocumentoRetencion(_Cpe_RetencionPercepcion_DetalleBean id_documento);
}