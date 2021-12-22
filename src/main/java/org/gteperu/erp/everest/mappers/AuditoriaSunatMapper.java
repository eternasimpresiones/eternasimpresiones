package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain._ProductoCodigoSunat;
import org.gteperu.erp.everest.domain.Auditoria_Sunat;
import org.gteperu.erp.everest.domain.Pagination;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface AuditoriaSunatMapper {

   /* @Insert(" insert into auditoria_sunat (id_documento,id_tipo_operacion,flag_respuesta_sunat,codigo_respuesta_sunat,mensaje_respuesta_sunat,id_usuario,fecha_registro)values(#{id_documento},#{id_tipo_operacion},#{flag_respuesta_sunat},#{codigo_respuesta_sunat},#{mensaje_respuesta_sunat},#{id_usuario},#{fecha_registro});")
    public Integer insertaAuditoria_Sunat(Auditoria_Sunat auditoria_sunat);*/
	
	 @Insert(" insert into auditoria_sunat (id_documento,id_tipo_operacion,codigo_respuesta_sunat,mensaje_respuesta_sunat,id_usuario,fecha_registro)values(#{id_documento},#{id_tipo_operacion},#{codigo_respuesta_sunat},#{mensaje_respuesta_sunat},#{id_usuario},#{fecha_registro});")
	    public Integer insertaAuditoria_Sunat(Auditoria_Sunat auditoria_sunat);
	 
		@Update(" update auditoria_sunat set id_tipo_operacion=#{id_tipo_operacion},codigo_respuesta_sunat=#{codigo_respuesta_sunat},mensaje_respuesta_sunat=#{mensaje_respuesta_sunat}, "
				+ "id_usuario=#{id_usuario},fecha_registro=#{fecha_registro}"
				+ " where id_documento=#{id_documento};")
		 public Integer actualizarAuditoria_Sunat(Auditoria_Sunat auditoria_sunat);
		
		@Select("select * from auditoria_sunat where id_documento=#{id}")
		public Auditoria_Sunat retornaAuditoriaSunatxiddocumento(@Param("id")Integer id);
}