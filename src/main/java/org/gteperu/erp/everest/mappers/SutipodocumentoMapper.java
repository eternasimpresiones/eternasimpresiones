package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.gteperu.erp.everest.domain.Sutipodocumento;
import org.gteperu.erp.everest.domain._Producto;
import org.springframework.stereotype.Repository;

@Repository("sutipodocumentoMapper")
public interface SutipodocumentoMapper {
		@Select(" select * from sutipodocumento where estado=#{estado} order by descripcion asc ")
		public List<Sutipodocumento> retornaSutipodocumentoPorEstado(Sutipodocumento sutipodocumento);
		
		@Select("SELECT DISTINCT ON (codigosunat) su.codigosunat,  su.* \r\n"
				+ "from (select * from sutipodocumento order by descripcionpublica asc) as su where estado=#{estado} ")
		public List<Sutipodocumento> retornaSutipodocumentoPorEstadoUnico(Sutipodocumento sutipodocumento);

		@Delete(" delete from  sutipodocumento where idsutipodocumento=#{idsutipodocumento}; ")
		public Integer eliminaProducto(Sutipodocumento sutipodocumento);
		
		@Select("select idsutipodocumentoidentidad,codigosunat,descripcion,longmax,abrv from sutipodocumentoidentidad")
		public List<Sutipodocumento> comboSutipodocumentoidentidad();
		
		
		@Select("select DISTINCT (codigosunat),descripcionpublica  "
				+ "from sutipodocumento "
				+ "where (estado=1 and descripcionpublica is not null)  order by codigosunat,descripcionpublica asc ")
		public List<Sutipodocumento> listarDocumentosPublicos();
		  
		
}