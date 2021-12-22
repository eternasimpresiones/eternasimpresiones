package org.gteperu.erp.everest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.gteperu.erp.everest.domain.Ubigeo;
import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.Sunat_padron;

import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._Tipo_Empresa;
import org.gteperu.erp.everest.mappers.CompanyMapper;
import org.gteperu.erp.everest.service.Sunat_padronService;
import org.gteperu.erp.everest.service._CompanyService;
import org.jfree.chart.axis.CategoryLabelWidthType;
import org.jfree.chart.axis.SegmentedTimeline;

import java.util.List;
import javax.annotation.Resource;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Service("companyService")
public class _CompanyServiceImpl implements _CompanyService {

	@Resource(name = "companyMapper")
	CompanyMapper companyMapper;
	
	@Autowired
	Sunat_padronService sunat_padronService; 

	@Override
	public List<_Company> listEmpresa(_Company company) {
		List<_Company> lsEmpresa = new ArrayList<_Company>();
		try {
			lsEmpresa = companyMapper.listEmpresa();
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listEmpresa. ERROR : " + e.getMessage());
			throw e;
		} 
		return lsEmpresa;
		

    }
	
	@Override
	public List<_Company> listEmpresaPaginacion(_Company company) {

		List<_Company> lsEmpresa = new ArrayList<_Company>();
		Pagination pg = new Pagination();
		try {
			pg = companyMapper.retornaCantidad();
			lsEmpresa = companyMapper.listEmpresaPagi(company);
			if (lsEmpresa != null && lsEmpresa.size() > 0) {
				lsEmpresa.get(0).setCantidad(pg.getCantidad());
			}

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listEmpresaPaginacion. ERROR : " + e.getMessage());
			throw e;
		} 
		return lsEmpresa;
		

    }
	
    @Override
    public List<_Tipo_Empresa> listTipoEmpresa() {
    	
        List<_Tipo_Empresa> lsEmpresa = new ArrayList<_Tipo_Empresa>();

		try {
			lsEmpresa = companyMapper.listTipoEmpresa();

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" insertUserperf. ERROR : "+e.getMessage());
			throw e;
		}
		return lsEmpresa;
		

    }
    
    @Override
    public Integer insertEmpresa(_Company company) {
    	
    	Integer auxiliar = 0;

		try {

			auxiliar = companyMapper.insertEmpresa(company);

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " insertEmpresa. ERROR : " + e.getMessage());
			throw e;
		}
			return auxiliar;
		
	}

	@Override
	public Integer eliminarEmpresa(_Company cod_empresa) {
		Integer delete = 0;
		try {
			delete = companyMapper.eliminarEmpresa(cod_empresa);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " EliminarEmpresa. ERROR : " + e.getMessage());
			throw e;
		}
			return delete;
		
	}

	@Override
	public Integer actualizarEmpresa(_Company cod_empresa) {
		Integer update = 0;
		try {
			update = companyMapper.actualizarEmpresa(cod_empresa);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " ActualizarEmpresa. ERROR : " + e.getMessage());
			throw e;
		}
			return update;
	
	}

	@Override
	public Integer actualizarFirma(_Company cod_empresa) {
		Integer update = 0;
		try {
			update = companyMapper.actualizarFirma(cod_empresa);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " actualizarFirma. ERROR : " + e.getMessage());
			throw e;
		}
			return update;
		
		
	}

	@Override
	public _Company retornaEmpresaPorIdempresa(_Company cod_empresa) {
		_Company company = new _Company();
		try {
			company = companyMapper.retornaEmpresaPorIdempresa(cod_empresa);
			
		} catch (Exception e) {
			System.out.println(	this.getClass().getSimpleName() + " retornaEmpresaPorIdempresa. ERROR : " + e.getMessage());
			throw e;
		} 
		return company;
		
	}

	@Override
	public _Company buscaEmpresaPorIdempresa(_Company cod_empresa) {
		_Company company = new _Company();
		try {
			company = companyMapper.retornaEmpresaPorIdempresa(cod_empresa);

			Timestamp fecempresa = company.getFecha_expiracion();
			if(fecempresa != null) {
				Date fechaexpira = new Date(fecempresa.getTime());
				Calendar calendarioexpiracion = Calendar.getInstance();
				calendarioexpiracion.setTime(fechaexpira);

				Calendar calendariohoy = Calendar.getInstance();

				if (fechaexpira.before(calendariohoy.getTime())) {
					calendariohoy.add(Calendar.DATE, 1);
					Timestamp fec = new Timestamp(calendariohoy.getTimeInMillis());
					company.setFecha_expiracion(fec);
				} else {
					calendarioexpiracion.add(Calendar.DATE, 1);
					Timestamp fec = new Timestamp(calendarioexpiracion.getTimeInMillis());
					company.setFecha_expiracion(fec);
				}

			}
		} catch (Exception e) {
			System.out.println(	this.getClass().getSimpleName() + " buscaEmpresaPorIdempresa. ERROR : " + e.getMessage());
			throw e;
		} 
		return company;
		
	}

	@Override
	public Integer actualizarFechaExEmpresa(_Company company) {
		Integer update = 0;
		try {
			update = companyMapper.actualizarFechaExEmpresa(company);
			if(update != 0){
				System.out.println("actualizarFechaExEmpresa CORRECTO");
			}else{
				System.out.println("actualizarFechaExEmpresa INCORRECTO");
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " actualizarFechaExEmpresa. ERROR : " + e.getMessage());
			throw e;
		}
			return update;
		
	}

	@Override
	public Ubigeo listar(_Company company) {
		
		 Ubigeo ubigeo = new Ubigeo();
		 
			try {
				
				ubigeo = companyMapper.listar(company);

			} catch (Exception e) {
				System.out.println(this.getClass().getSimpleName()+" Ubigeo. ERROR : "+e.getMessage());
				throw e;
			}
			return ubigeo;
			
	}
	@Override
	public _Company consultaRucSunaPadron(_Company company) {
		_Company comp = new _Company();
		Sunat_padron su = new Sunat_padron();
		Sunat_padron sunat_padron = new Sunat_padron();
		StringBuilder direccion = new StringBuilder();
		try {
			su.setEstado(true);
			su = sunat_padronService.retornaTablaPorEstado(su);
			if(su!=null){
				su.setId_sunat_padron(company.getNro_documento_empresa());
				sunat_padron = sunat_padronService.consultaRuc(su);
			}
			if(sunat_padron!=null && sunat_padron.getId_sunat_padron()!=null){
				String[] consulta_parts = sunat_padron.getContenido().split("\\|");
				if(consulta_parts[2]!=null){
					comp.setNro_documento_empresa(consulta_parts[0]);
					comp.setRazon_social_empresa(consulta_parts[1]);
					comp.setNombre_comercial_empresa(consulta_parts[1]);
					if(!consulta_parts[5].equals("-")){
						direccion.append(consulta_parts[5]);
						if(!consulta_parts[6].equals("-")){
							direccion.append(consulta_parts[6]);
						}
					}
					
					if(!direccion.equals("")){
						direccion.append(",");
					}
					if(!consulta_parts[7].equals("-")){
						direccion.append(consulta_parts[7]);
						if(!consulta_parts[8].equals("-")){
							direccion.append(consulta_parts[8]);
							if(!consulta_parts[9].equals("-")){
								direccion.append(" "+consulta_parts[9]);
							}	
						}
					}
					
					comp.setDireccion_empresa(direccion.toString());
					
					if(!consulta_parts[2].equals("ACTIVO")){
						comp.setEstadoEmpresa(false);
					}else{
						comp.setEstadoEmpresa(true);
					}
				}
			}
			
//			String[] data = line.split("\\|");
//			empsun = new EmpresaSunat();
//			empsun.setRuc(data[0]);
//			empsun.setNombreorazonsocial(data[1]);
//			empsun.setEstadodelcontribuyente(data[2]);
//			empsun.setcondiciondedomicilio(data[3]);
//			empsun.setUbigeo(data[4]);
//			empsun.setTipodevia(data[5]);
//			empsun.setNombredevia(data[6]);
//			empsun.setCodigodezona(data[6]);
//			empsun.setTipodezona(data[8]);
//			empsun.setNumero(data[9]);
//			empsun.setInterior(data[10]);
//			empsun.setLote(data[11]);
//			empsun.setDepartamento(data[12]);
//			empsun.setManzana(data[13]);
//			empsun.setKilometro(data[14]);
//			
			
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " actualizarFechaExEmpresa. ERROR : " + e.getMessage());
			throw e;
		}
		return comp;
		
	}

	@Override
	public _Company buscaEmpresaPorNroDoc(_Company nrodoc) {
		_Company objEmpresa = new _Company();
		try {
			objEmpresa = companyMapper.listEmpresaPorNroDocumento(nrodoc);			
		} catch (Exception e) {
			throw e;
		}
		return objEmpresa;
	}

	@Override
	public _Company retornaEmpresaPorIdLocal(Integer idlocal) {
		_Company company = new _Company();
		try {
			company = companyMapper.retornaEmpresaPorIdLocal(idlocal);
		} catch (Exception e) {
			System.out.println(	this.getClass().getSimpleName() + " retornaEmpresaPorIdLocal. ERROR : " + e.getMessage());
			throw e;
		} 
		return company;
	}
	


}