package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain._ProductoCodigoSunat;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain.Pagination;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Delete;
import java.util.List;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface ClientesMapper {

	@Select("select idclientes,codigo,idcuenta,nombre,tipopersona,tipocliente,documentoidentidadcliente,direccionfacturacion,direccionguia,telefono,celular,email,idubigeo,estado,fecharegistro,scontrasena from clientes ;")
	public List<_Clientes> retornaClientes();

	@Select("select idclientes,idcuenta,codigo,nombre,tipopersona,tipocliente,documentoidentidadcliente,direccionfacturacion,direccionguia,telefono,celular,email,idubigeo,estado,fecharegistro,scontrasena from clientes where estado='1';")
	public List<_Clientes> retornaClientesTodas();

	public List<_Clientes> retornaProveedoresPorEstado(@Param("clientes") _Clientes idclientes);

	public Pagination retornaCantidadListProveedores(@Param("clientes") _Clientes idbanco);

	public Pagination retornaCantidadListClientes(@Param("clientes") _Clientes idbanco);

	public List<_Clientes> retornaClientesPorEstado(@Param("clientes") _Clientes idclientes);

	public List<_Clientes> retornaProveedoresLikePorNombre(@Param("clientes") _Clientes idclientes);

	public List<_Clientes> retornaClientesLikePorNombre(@Param("clientes") _Clientes idclientes);

	public Integer insertaClienteMasivo(@Param("list") List<_Clientes> idclientes);

	public _Clientes retornaObjClientesPorRucFilter(@Param("clientes") _Clientes idclientes);

	public _Clientes retornaObjClientes(@Param("clientes") _Clientes idclientes);

	@Select("select idclientes,codigo,idcuenta,nombre,tipopersona,tipocliente,documentoidentidadcliente,direccionfacturacion,direccionguia,telefono,celular,email,idubigeo,estado,fecharegistro,scontrasena from clientes where documentoidentidadcliente =#{documentoidentidadcliente} and idcuenta=#{idcuenta}")
	public _Clientes retornaObjClientesPorRuc(_Clientes idclientes);
	


	@Update("update clientes set  scontrasena=#{scontrasena},  emailcontacto=#{emailcontacto},idcuenta=#{idcuenta}, tipopersona=#{tipopersona}, codigo=#{codigo},idtipodocumento=#{idtipodocumento}, nombre=#{nombre}, tipocliente=#{tipocliente}, documentoidentidadcliente=#{documentoidentidadcliente}, direccionfacturacion=#{direccionfacturacion}, direccionguia=#{direccionguia}, telefono=#{telefono}, celular=#{celular}, email=#{email}, idubigeo=#{idubigeo}, estado=#{estado}, fecharegistro=#{fecharegistro} where idclientes =#{idclientes};")
	public Integer updateClientes(_Clientes idclientes);

	@Update("update clientes set    email=#{email} where idclientes =#{idclientes};")
	public Integer updateClientesMail(_Clientes idclientes);

	@Insert(" insert into clientes(scontrasena,emailcontacto,anexo,contacto,idcuenta,tipopersona,idtipodocumento,codigo,nombre,tipocliente,documentoidentidadcliente,direccionfacturacion,"
			+ "direccionguia,telefono,celular,email,idubigeo,estado,fecharegistro)values(#{scontrasena},#{emailcontacto},#{anexo},#{contacto},#{idcuenta},#{tipopersona},#{idtipodocumento},#{codigo},#{nombre},#{tipocliente},#{documentoidentidadcliente},#{direccionfacturacion},#{direccionguia},#{telefono},#{celular},#{email},#{idubigeo},#{estado},#{fecharegistro});")
	@SelectKey(statement = "select currval('sec_clientes') as idclientes", keyProperty = "idclientes", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "idclientes", flushCache = FlushCachePolicy.TRUE)
	public Integer insertaClientes(_Clientes idclientes);

	@Delete(" delete from  clientes where idclientes=#{idclientes}; ")
	public Integer eliminaClientes(_Clientes idclientes);
}