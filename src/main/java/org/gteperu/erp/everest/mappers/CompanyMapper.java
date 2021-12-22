package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._Tipo_Empresa;
import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.Ubigeo;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Delete;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CompanyMapper {

	@Select( "select count(*) as cantidad from company")
	public Pagination retornaCantidad();
	
	@Select("SELECT max_usuarios FROM company WHERE id_empresa =#{id_empresa}")
	public Integer cantidadMaximaUsuarios(@Param("id_empresa") Integer id_empresa);
	
    public List<_Company> listEmpresaPagi(@Param("param") _Company company);
    
//    @Select( "select * from company")
    public List<_Company> listEmpresa();
    
    @Select( "select * from tipo_empresa")
    public List<_Tipo_Empresa> listTipoEmpresa();
    
    @Insert(" insert into company(nro_documento_empresa,tipo_doc_empresa,"
    		+ "nombre_comercial_empresa,codigo_ubigeo_empresa,direccion_empresa,"
    		+ "ambiente_operacion, razon_social_empresa,usuario_sol_empresa,pass_sol_empresa,estado,logo,urlimagen,idubigeo,ifacturacionpse,"
    		+ "telefono,website,celular,email, max_usuarios, iidtiporeporte)"
    		+ "values(#{nro_documento_empresa},#{tipo_doc_empresa},#{nombre_comercial_empresa},"
    		+ "#{codigo_ubigeo_empresa},#{direccion_empresa},#{ambiente_operacion},#{razon_social_empresa},"
    		+ "#{usuario_sol_empresa},#{pass_sol_empresa},#{estado},#{logo},#{urlimagen},#{idubigeo},#{ifacturacionpse},"
    		+ "#{telefono},#{website},#{celular},#{email},#{max_usuarios}, #{iidtiporeporte});")

    @SelectKey(statement = "select currval('sec_company') as id_empresa", keyProperty = "id_empresa", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "id_empresa", flushCache = FlushCachePolicy.TRUE)
    public Integer insertEmpresa(_Company company);
    
    @Delete(" delete from company where id_empresa=#{id_empresa}; ")
	public Integer eliminarEmpresa(_Company company);
	
	@Update("update company set nro_documento_empresa=#{nro_documento_empresa},ambiente_operacion=#{ambiente_operacion}, tipo_doc_empresa=#{tipo_doc_empresa}, nombre_comercial_empresa=#{nombre_comercial_empresa},"
			+ "codigo_ubigeo_empresa=#{codigo_ubigeo_empresa}, direccion_empresa=#{direccion_empresa}, pass_firma_empresa=#{pass_firma_empresa}, razon_social_empresa=#{razon_social_empresa},"
			+ "usuario_sol_empresa=#{usuario_sol_empresa},pass_sol_empresa=#{pass_sol_empresa}, estado=#{estado}, logo=#{logo}, urlimagen=#{urlimagen},idubigeo=#{idubigeo},"
			+ "urlarchivo=#{urlarchivo}, archivo=#{archivo},ifacturacionpse=#{ifacturacionpse},"
			+ "telefono=#{telefono},website=#{website}, celular=#{celular}, email=#{email}, max_usuarios=#{max_usuarios}, iidtiporeporte=#{iidtiporeporte}"
			+ " where id_empresa=#{id_empresa};")
	public Integer actualizarEmpresa(_Company company);
	
	
	@Update("update company set url_firma=#{url_firma} "
			+ "where id_empresa=#{id_empresa};")
	public Integer actualizarFirma(_Company company);
	
	@Select( "select idubigeo from ubigeo where codigo=#{cod_ubigeo}")
    public Ubigeo listar(_Company cod_ubigeo);

	
	/*@Select( "select c.id_empresa, c.nro_documento_empresa, c.tipo_doc_empresa, c.nombre_comercial_empresa, c.codigo_ubigeo_empresa, c.direccion_empresa, c.razon_social_empresa, c.usuario_sol_empresa, c.pass_sol_empresa"
			+ "from company c "
			+ "where c.id_empresa=#{id_empresa};")*/
	public _Company retornaEmpresaPorIdempresa(@Param("param")_Company company);
	
	public _Company buscaEmpresaPorIdempresa(@Param("cod") _Company cod_empresa);
	
	@Update("update company set fecha_expiracion=#{fecha_expiracion} where id_empresa=#{id_empresa};")
	public Integer actualizarFechaExEmpresa(_Company company);
	
	@Select("select * from company where nro_documento_empresa = #{nro_documento_empresa}")
    public _Company listEmpresaPorNroDocumento(_Company company);

	@Select( "SELECT co.* FROM public.company co INNER JOIN public.local lo on lo.idempresa = co.id_empresa\r\n"
			+ "WHERE lo.id = #{idlocal}")
	public _Company retornaEmpresaPorIdLocal(@Param("idlocal") Integer idlocal);
}