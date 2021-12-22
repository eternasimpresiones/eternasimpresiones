package org.gteperu.erp.everest.mappers;


import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.Ubigeo;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._Documento_TMP;
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
public interface ClienteMapper {
	
	@Insert(" insert into cliente(scontrasena,id_empresa,tipo_doc,nro_doc,razon_social,razon_comercial,email"
			+ ",direccion_fiscal,direccion_fiscal2,direccion_fiscal3,movil,fijo,cuenta_detraccion,idubigeo)"
			+ "values(#{scontrasena},#{id_empresa},#{tipo_doc},#{nro_doc},#{razon_social},#{razon_comercial},#{email}"
			+ ",#{direccion_fiscal},#{direccion_fiscal2},#{direccion_fiscal3},#{movil},#{fijo},#{cuenta_detraccion},#{idubigeo});")
    @SelectKey(statement = "select currval('sec_cliente') as id_cliente", keyProperty = "id_cliente", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "id_cliente", flushCache = FlushCachePolicy.TRUE)
    public Integer insertarCliente(_Clientes cliente);
	
	@Select("SELECT COUNT(*) FROM cliente WHERE nro_doc=#{nro_doc} AND id_empresa=#{id_empresa}")
	public Integer existePorNumeroDocYEmpresa(@Param("nro_doc") String nro_doc, @Param("id_empresa") Integer id_empresa);

    @Select( "select * from cliente where id_empresa=#{id_empresa}")
    public List<_Clientes> listarCliente(_Clientes cod_cliente);
    
    @Select( "select * from cliente where id_cliente=#{id_cliente};")
    public _Clientes retornaClientexId(Integer id);
    
    @Select( "select * from cliente where id_cliente=#{id_cliente};")
    public _Clientes pruebRetornaCliente(Integer id);
    
    @Select("select count(*) as cantidad from cliente where id_empresa=#{id_empresa};")
    public Pagination retornaCantidadClientesPorEmpresa(_Clientes cod_cliente);
    
    public List<_Clientes> listarClientePorEmpresa(@Param("param") _Clientes cod_cliente);
    
    @Update("update cliente set scontrasena=#{scontrasena}, id_empresa=#{id_empresa}, tipo_doc=#{tipo_doc}, nro_doc=#{nro_doc},"
    		+ "razon_social=#{razon_social}, razon_comercial=#{razon_comercial}, email=#{email},"
    		+ "direccion_fiscal=#{direccion_fiscal},direccion_fiscal2=#{direccion_fiscal2},direccion_fiscal3=#{direccion_fiscal3},"
    		+ "movil=#{movil}, fijo=#{fijo},idubigeo=#{idubigeo}, cuenta_detraccion=#{cuenta_detraccion} "
    		+ "where id_cliente=#{id_cliente};")
	public Integer actualizarCliente(_Clientes cliente);
    
    @Delete(" delete from cliente where id_cliente=#{id_cliente}; ")
	public Integer eliminarCliente(_Clientes cliente);
    
    @Select( "select * from ubigeo where codigo=#{cod_ubigeo}")
    public Ubigeo listar(_Clientes cod_ubigeo);
    
   /* @Select( "select c.id_cliente, c.id_empresa, c.tipo_doc, c.nro_doc, c.razon_social, c.razon_comercial, c.email, c.direccion_fiscal,c.idubigeo,"
    		+ "u.idubigeo, u.departamento, u.provincia, u.distrito, u.estado, u.codigo "
    		+ "from cliente c "
    		+ "inner join ubigeo u on c.idubigeo = u.idubigeo "
    		+ "where c.id_cliente=#{id_cliente}")*/
    public _Clientes retornaClientePorIdcliente(@Param("param") _Clientes cliente);
    
    @Update("update cliente set scontrasena=#{scontrasena}, id_empresa=#{id_empresa}, tipo_doc=#{tipo_doc}, nro_doc=#{nro_doc},"
    		+ "razon_social=#{razon_social}, razon_comercial=#{razon_comercial}, email=#{email},"
    		+ "direccion_fiscal=#{direccion_fiscal}, movil=#{movil}, fijo=#{fijo}, cuenta_detraccion=#{cuenta_detraccion} where nro_doc=#{nro_doc};")
	public Integer actualizarClientePorNumDocumento(_Clientes cliente);
    	
	@Select("select * from cliente where nro_doc = #{nro_doc} and tipo_doc = #{tipo_doc} and id_empresa=#{id_empresa}")
	public _Clientes retornaObjClintePorRuc(_Clientes cliente);
	
    @Select( "select cli.id_cliente from cliente cli where cli.id_empresa=#{iid_empresa} and cli.tipo_doc=#{stipo_documento_cliente} and"
    		+ " cli.nro_doc=#{snumero_documento_cliente};")
    public _Clientes retornaClientexDocumentoTMP(_Documento_TMP doc);

    @Update("update cliente set razon_social=#{razon_social}, direccion_fiscal=#{direccion_fiscal} where id_cliente=#{id_cliente};")
	public Integer actualizarClientexDocTMP(_Clientes cliente);
    
	@Select("select * from cliente where scontrasena =#{scontrasena} and nro_doc=#{nro_doc} ")
	public _Clientes retornaClientePorRucContrasena(_Clientes idclientes);
	
}