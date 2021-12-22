package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.gteperu.erp.everest.domain.Bancoempresa;
import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain._ProductoCodigoSunat;
import org.springframework.stereotype.Repository;

@Repository("bancoempresaMapper")
public interface BancoEmpresaMapper {
		 
		@Select("select count(*) as cantidad from bancoempresa where idempresa = #{idempresa}  ")
		public Pagination retornaCantidadBancoEmpresaPorEmpresa(Bancoempresa codpro);
		
		public List<Bancoempresa> retornaBancoEmpresaxEmpresaxEstado(@Param("bancoempresa") Bancoempresa bancoempresa);
		 	
		@Update(" update bancoempresa set idbanco=#{idbanco}"
				+ ",numerocuenta=#{numerocuenta}"
				+ ",numerocuentainterbancaria=#{numerocuentainterbancaria}"
				+ ",idtipocuentabancaria=#{idtipocuentabancaria},idtipomoneda=#{idtipomoneda},breporte=#{breporte} "
		     	+ " where idbancoempresa=#{idbancoempresa}; ")
		public Integer updateBancoEmpresa(Bancoempresa codpro);
				
		@Insert(" insert into bancoempresa(idbanco,idempresa,numerocuenta,numerocuentainterbancaria"
				+ ",idtipocuentabancaria,idtipomoneda,estado ,breporte)"
				+ "values(#{idbanco},#{idempresa},#{numerocuenta},#{numerocuentainterbancaria}"
				+ ",#{idtipocuentabancaria},#{idtipomoneda},#{estado},#{breporte});")
		public Integer insertaBancoEmpresa(Bancoempresa codpro);

		@Delete(" delete from  bancoempresa where idbancoempresa=#{idbancoempresa}; ")
		public Integer eliminaBancoEmpresa(Bancoempresa codpro);
		
}