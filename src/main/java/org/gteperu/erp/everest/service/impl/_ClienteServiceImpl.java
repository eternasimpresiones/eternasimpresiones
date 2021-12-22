package org.gteperu.erp.everest.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.Ubigeo;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain._Documento_TMP;
import org.gteperu.erp.everest.mappers.ClienteMapper;
import org.gteperu.erp.everest.service._ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clienteService")
public class _ClienteServiceImpl implements _ClienteService {

	@Autowired
    ClienteMapper clienteMapper;
    
    @Override
    public Boolean insertarCliente(_Clientes cliente) {
    	
		try {
			Integer count = clienteMapper.existePorNumeroDocYEmpresa(cliente.getNro_doc(),cliente.getId_empresa());
			if(count==0) {
				clienteMapper.insertarCliente(cliente);
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" insertarCliente. ERROR : "+e.getMessage());
			throw e;
		} 		
    }

    @Override
    public List<_Clientes> listarCliente(_Clientes cod_cliente) {
        List<_Clientes> lsCliente = new ArrayList<_Clientes>();
        Pagination pg = new Pagination();
		try {
			pg = clienteMapper.retornaCantidadClientesPorEmpresa(cod_cliente);
			lsCliente = clienteMapper.listarClientePorEmpresa(cod_cliente);
			if (lsCliente != null && lsCliente.size() > 0) {
				lsCliente.get(0).setCantidad(pg.getCantidad());
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" listarCliente. ERROR : "+e.getMessage());
			throw e;
		}
		return lsCliente;
		
    }
    
    @Override
    public List<_Clientes> listarClientePorEmpresa(_Clientes cod_cliente) {
    	
        	List<_Clientes> lsCliente = new ArrayList<_Clientes>();

		try {
			lsCliente = clienteMapper.listarCliente(cod_cliente);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" listarCliente. ERROR : "+e.getMessage());
			throw e;
		} 
		return lsCliente;
		
    }
    
    @Override
	public Integer actualizarCliente(_Clientes cod_cliente) {
    	
			Integer update=0;
			
		try{
			update = clienteMapper.actualizarCliente(cod_cliente);
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " actualizarCliente. ERROR : " + e.getMessage());
			throw e;
		}
		return update;	
		
	}
    
    @Override
	public Integer eliminarCliente(_Clientes cod_cliente) {
    	
			Integer delete=0;
			
		try{
			delete = clienteMapper.eliminarCliente(cod_cliente);
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " eliminarCliente. ERROR : " + e.getMessage());
			throw e;
		}
			return delete;	
			
	}
    
    @Override
    public _Clientes retornaClientePorIdcliente(_Clientes cod_cliente) {
        _Clientes cliente = new _Clientes();
		try {
			cliente = clienteMapper.retornaClientePorIdcliente(cod_cliente);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" listarCliente. ERROR : "+e.getMessage());
			throw e;
		} 
		return cliente;
		
    }
    
	   @Override
		public _Clientes retornaClientePorRucContrasena(_Clientes cod_cliente) {
	        _Clientes cliente = new _Clientes();
			try {
				cliente = clienteMapper.retornaClientePorRucContrasena(cod_cliente);
			} catch (Exception e) {
				System.out.println(this.getClass().getSimpleName()+" retornaClientePorRucContrasena. ERROR : "+e.getMessage());
				throw e;
			} 
			return cliente;
			
	    }
	    
	
	
    @Override
	public Integer actualizarClientePorNumDocumento(_Clientes cod_cliente) {
    	
			Integer update=0;
			
		try{
			update = clienteMapper.actualizarClientePorNumDocumento(cod_cliente);
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " actualizarCliente. ERROR : " + e.getMessage());
			throw e;
		}
		return update;	
		
	}
    
    @Override
    public _Clientes retornaClientexId(Integer cod_cliente) {
        _Clientes cliente = new _Clientes();
		try {
			cliente = clienteMapper.retornaClientexId(cod_cliente);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" listarCliente. ERROR : "+e.getMessage());
			throw e;
		}
		return cliente;
		
    }
    
    @Override
    public _Clientes pruebRetornaCliente(Integer cod_cliente) {
        _Clientes cliente = new _Clientes();
        Integer cat = 0;
		try {
			//cliente = clienteMapper.pruebRetornaCliente(cat);
			Integer catp = null;
    		cat = catp +5;
		} catch (Exception e) {
			throw e;
		}
		return cliente;
    }
    
	public Ubigeo listar(_Clientes cliente) {
		
		 Ubigeo ubigeo = new Ubigeo();
		 
			try {
				
				ubigeo = clienteMapper.listar(cliente);

			} catch (Exception e) {
				System.out.println(this.getClass().getSimpleName()+" Ubigeo. ERROR : "+e.getMessage());
				throw e;
			} 
			return ubigeo;
			
	}
	
	@Override
	public _Clientes retornaObjClientePorRuc(_Clientes company) {
        _Clientes cliente = new _Clientes();
        cliente = clienteMapper.retornaObjClintePorRuc(company);
		return cliente;
	}
	
	@Override
	public _Clientes retornaClientexDocumentoTMP(_Documento_TMP doc) {
        _Clientes cliente = new _Clientes();
        cliente = clienteMapper.retornaClientexDocumentoTMP(doc);
		return cliente;
	}
	
    @Override
	public Integer actualizarClientexDocTMP(_Clientes cod_cliente) {
    	
			Integer update=0;
			
		try{
			update = clienteMapper.actualizarClientexDocTMP(cod_cliente);
		}catch (Exception e){
			System.out.println(this.getClass().getSimpleName()+ " actualizarCliente. ERROR : " + e.getMessage());
			throw e;
		}
		return update;	
		
	}
}