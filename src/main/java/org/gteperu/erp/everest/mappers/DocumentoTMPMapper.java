package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain._Documento_TMP;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface DocumentoTMPMapper {

	@Insert(" insert into documento_tmp(iid_empresa,sserie_comprobante,snro_comprobante,stipo_documento,stipo_documento_cliente,snumero_documento_cliente,srazon_social_cliente,"
			+ "sdireccion_cliente,scod_moneda,dsub_total,dtotal_inafecta,dtotal_exoneradas,dtotal_gratuitas,dtotal_igv,dtotal_percepciones,dtotal,tfecha_documento,semail_cliente,"
			+ "stipo_operacion,stipo_nota_debito,stipo_nota_credito,sdescripcion_motivo,"
			+ "stipo_comprobante_modifica,snro_documento_modifica,tfecha_documento_referencia,splaca_vehiculo,dtotal_detracciones,dporcentaje_detraccion)"
			+ "values(#{iid_empresa},#{sserie_comprobante},#{snro_comprobante},#{stipo_documento},#{stipo_documento_cliente},#{snumero_documento_cliente},"
			+ "#{srazon_social_cliente},#{sdireccion_cliente},#{scod_moneda},#{dsub_total},#{dtotal_inafecta},#{dtotal_exoneradas},#{dtotal_gratuitas},"
			+ "#{dtotal_igv},#{dtotal_percepciones},#{dtotal},#{tfecha_documento},#{semail_cliente},#{stipo_operacion},#{stipo_nota_debito},#{stipo_nota_credito},"
			+ "#{sdescripcion_motivo},#{stipo_comprobante_modifica},#{snro_documento_modifica},#{tfecha_documento_referencia},#{splaca_vehiculo},"
			+ "#{dtotal_detracciones},#{dporcentaje_detraccion});")
	@SelectKey(statement = "select currval('sec_documento_tmp') as iid_documento_tmp", keyProperty = "iid_documento_tmp", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "iid_documento_tmp", flushCache = FlushCachePolicy.TRUE)
	public Integer insertaDocumentoTMP(_Documento_TMP doc);
	
	
//	@Select("select doc.*,det.* from documento_tmp as doc \r\n" + 
//			"inner join detalle_documento_tmp det on doc.iid_documento_tmp = det.iid_documento_tmp;")
	public List<_Documento_TMP> retornaLsDocumentoTMP(); 
	
	@Delete(" delete from  documento_tmp where iid_documento_tmp=#{iid_documento_tmp}; ")
	public Integer eliminaDocumentoTMP(_Documento_TMP doc);
	
	@Update("update documento_tmp set smsj_registro=#{smsj_registro} where iid_documento_tmp=#{iid_documento_tmp};")
	public Integer actualizaMsgRegistro(_Documento_TMP doc);
}