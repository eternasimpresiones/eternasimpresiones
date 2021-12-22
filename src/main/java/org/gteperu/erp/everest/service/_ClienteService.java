package org.gteperu.erp.everest.service;

import java.util.List;

import org.gteperu.erp.everest.domain.Ubigeo;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain._Documento_TMP;

public interface _ClienteService {
	
	public Boolean insertarCliente(_Clientes cliente);
	
	public List<_Clientes> listarCliente(_Clientes cod_cliente);
	
	 public List<_Clientes> listarClientePorEmpresa(_Clientes cod_cliente);
	
	public Integer actualizarCliente(_Clientes cod_cliente);
	
	public Integer eliminarCliente(_Clientes cod_cliente);
	
	public _Clientes retornaClientePorIdcliente(_Clientes cod_cliente);
	
	public Integer actualizarClientePorNumDocumento(_Clientes cod_cliente);
	public _Clientes retornaClientexId(Integer cod_cliente);
	
	 public _Clientes pruebRetornaCliente(Integer cod_cliente) ;
	

	public Ubigeo listar(_Clientes cod_ubigeo);
	public _Clientes retornaObjClientePorRuc(_Clientes company);
	public _Clientes retornaClientexDocumentoTMP(_Documento_TMP doc);

	public Integer actualizarClientexDocTMP(_Clientes cod_cliente);

	
	public _Clientes retornaClientePorRucContrasena(_Clientes cod_cliente);

}