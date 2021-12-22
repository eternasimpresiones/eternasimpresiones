package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.gteperu.erp.everest.domain.PagoEmpresa;

@Mapper
public interface PagoEmpresaMapper {
	
	@Insert("INSERT INTO pagoempresa( id_empresa, meses, fechainicio, fechafin, total, cancelado, fecharegistro)"
			+"VALUES ( #{id_empresa}, #{meses}, #{fechainicio}, #{fechafin}, #{total}, #{cancelado}, current_date);")
	public Integer insertPago(PagoEmpresa pagoEmpresa);
	
	public List<PagoEmpresa> listarPagoEmpresa(@Param("empresa") Integer empresa);
	
	@Update("update pagoempresa set cancelado=#{cancelado} where id_pagoempresa=#{id_pagoempresa};")
	public Integer actualizarPago(PagoEmpresa pagoEmpresa);
	
	public PagoEmpresa buscarPagoEmpresa(@Param("id_pagoempresa") Integer idpagempre);
	
}
