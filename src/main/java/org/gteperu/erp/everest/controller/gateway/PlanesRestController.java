package org.gteperu.erp.everest.controller.gateway;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import org.gteperu.erp.everest.controller.Funciones;
import org.gteperu.erp.everest.domain.BaseResponseWrapper;
import org.gteperu.erp.everest.domain.ClienteRuc;
import org.gteperu.erp.everest.domain.EmpresaSunat;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain.Ubigeo;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service._ClienteService;
import org.gteperu.erp.everest.service._CompanyService;
import org.gteperu.erp.everest.service._UbigeoService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value = "/public/listplanes")
public class PlanesRestController {

	@Resource(name = "parametrosService")
	ParametrosService parametrosService;
	
	@Resource(name = "companyService")
	_CompanyService companyService;
	
	@Resource(name = "clienteService")
	_ClienteService clienteService;

 	@Resource(name = "ubigeoService")
 	_UbigeoService ubigeoService;
	
	//Funciones fun = new Funciones();

	@RequestMapping(value = "/consultarsunatpadron", method = RequestMethod.POST)
//	public BaseResponseWrapper retornaDatosSunatDesdePadron(@RequestParam("ruc") String ruc) throws IOException {
	public BaseResponseWrapper retornaDatosSunatDesdePadron(@RequestBody @Validated _Clientes cliente, HttpServletRequest request) throws IOException {

		String ruc = cliente.getNro_doc();
		BaseResponseWrapper baseResponseWrapper = new BaseResponseWrapper();

		EmpresaSunat empsun = new EmpresaSunat();
		BufferedReader br = null;
		String rutabase = "";
		List<_Parametros> lsPar = new ArrayList<>();

		String directorio=System.getProperty("user.dir");
		String separador=System.getProperty("file.separator");
		_Parametros parametro = new _Parametros();
		Map<String, Object> dataretorna = new HashMap<>();
		
		parametro.setGrupo(Constantes.codigoGrupoIniParametros);
		parametro.setId_empresa(Constantes.parametroIdEmpresaGlobal);
		lsPar = parametrosService.retornaObjParametrosPorGrupoe(parametro);
		String rutafiles = Constantes.rutaArchivoFacturacion;
		String txtruc = Constantes.padronreduidoruc;

		for (int i = 0; i < lsPar.size(); i++) {
			if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaBaseSunatPadron)) {
				String[] dir=lsPar.get(i).getValor().split("\\|");
				//rutabase = dir[0] +separador+ dir[1] +separador+ dir[2];
				rutabase=lsPar.get(i).getValor();

			}
	 	}
		
		String ruta = rutabase;
		if (ruta != null) {
			try {

				br = new BufferedReader(new FileReader(ruta));
				String line = "";
				try {
					while ((line = br.readLine()) != null) {
						if (line.contains(ruc)) {
							String[] data = line.split("\\|");
							empsun = new EmpresaSunat();
							empsun.setRuc(data[0]);
							empsun.setNombreorazonsocial(data[1]);
							empsun.setEstadodelcontribuyente(data[2]);
							empsun.setcondiciondedomicilio(data[3]);
							empsun.setUbigeo(data[4]);
							empsun.setTipodevia(data[5]);
							empsun.setNombredevia(data[6]);
							empsun.setCodigodezona(data[6]);
							empsun.setTipodezona(data[8]);
							empsun.setNumero(data[9]);
							empsun.setInterior(data[10]);
							empsun.setLote(data[11]);
							empsun.setDepartamento(data[12]);
							empsun.setManzana(data[13]);
							empsun.setKilometro(data[14]);
							dataretorna.put("cliente", (Object) empsun);
							baseResponseWrapper.setData(dataretorna);
							baseResponseWrapper.setOk(true);
							baseResponseWrapper.setMessage("Consulta correcta");
							break;
						}
					}
				} catch (FileNotFoundException ex) {
					System.out.println(ex.getMessage());
					baseResponseWrapper.setOk(false);
					baseResponseWrapper.setMessage("Error en la Consulta");
					return baseResponseWrapper;

				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				baseResponseWrapper.setOk(false);
				baseResponseWrapper.setMessage("Error en la Consulta");
				e.printStackTrace();
			} finally {
				return baseResponseWrapper;
			}
		} else {
			baseResponseWrapper.setOk(false);
			baseResponseWrapper.setMessage("Parametro de ubicación de padron no existe");
			return baseResponseWrapper;
		}

	}
	
	
	@RequestMapping(value = "/consulta_rucCliente", method = RequestMethod.POST)
	public ResponseWrapper consulta_rucCliente(@RequestBody @Validated _Clientes cliente, HttpServletRequest request)
			throws Exception {
 		ResponseWrapper obj = new ResponseWrapper();
		_Clientes  c = new _Clientes();
		try {

			c = clienteService.retornaObjClientePorRuc(cliente);
			if (c != null) {
				obj.setDefaultObj(c);
				obj.setEstado(1);
				obj.setOk(Boolean.TRUE);
				obj.setMsg("Ya existe un cliente con ese documento");
			} else {
				obj.setEstado(2);
				obj.setOk(Boolean.FALSE);
				obj.setMsg("Ruc disponible");
			}

		} catch (Exception e) {
			obj.setEstado(Constantes.valTransaccionError);
			obj.setMsg(Constantes.msgTransaccionError);
		} finally {
			return obj;
		}

	}
	
	@RequestMapping(value = "/consulta_ruc_empresa", method = RequestMethod.POST)
	public ResponseWrapper consultaRucEmpresa(@RequestBody @Validated _Company compamy, HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		ResponseWrapper obj = new ResponseWrapper();
		_Company  c = new _Company();
		try {

			c = companyService.buscaEmpresaPorNroDoc(compamy);
			if (c != null) {
				obj.setDefaultObj(c);
				obj.setEstado(1);
				obj.setOk(Boolean.TRUE);
				obj.setMsg("El RUC ingresado ya se encuentra dado de alta");
			} else {
				obj.setEstado(2);
				obj.setOk(Boolean.FALSE);
				obj.setMsg("Ruc disponible");
			}

		} catch (Exception e) {
			obj.setEstado(Constantes.valTransaccionError);
			obj.setMsg(Constantes.msgTransaccionError);
		} finally {
			return obj;
		}

	}
	
	

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@GetMapping("/consultarsunatpadron/{ruc}")
	//@RequestMapping(value = "/consultarsunatpadron", method = RequestMethod.POST)
//	public BaseResponseWrapper retornaDatosSunatDesdePadron(@RequestParam("ruc") String ruc) throws IOException {
	//public BaseResponseWrapper retornaDatosSunatDesdePadron(@RequestBody @Validated _Clientes cliente, HttpServletRequest request) throws IOException {
	public ResponseWrapper retornaSeriexTipoDocLocal(@PathVariable("ruc") String ruc ,HttpServletRequest request) throws Exception {
	//	String ruc = cliente.getNro_doc();
 		 ResponseWrapper baseResponseWrapper = new ResponseWrapper();

		EmpresaSunat empsun = new EmpresaSunat();
		ClienteRuc clienteRuc = new ClienteRuc();
		String codigoUbigeo;
		Ubigeo ubigeo = new Ubigeo();
		BufferedReader br = null;
		String rutabase = "";
		List<_Parametros> lsPar = new ArrayList<>();
 		String separador=System.getProperty("file.separator");
		_Parametros parametro = new _Parametros();
 		parametro.setGrupo(Constantes.codigoGrupoIniParametros);
		parametro.setId_empresa(Constantes.parametroIdEmpresaGlobal);
		lsPar = parametrosService.retornaObjParametrosPorGrupoe(parametro);
		
		Map<String, Object> dataretorna = new HashMap<>();

		for (int i = 0; i < lsPar.size(); i++) {
			if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaBaseSunatPadron)) {
			//	String[] dir=lsPar.get(i).getValor().split("\\|");
			//	rutabase = dir[0] +separador+ dir[1] +separador+ dir[2];
				rutabase=lsPar.get(i).getValor();
				
			}
	 	}
		
		String ruta = rutabase;
		if (ruta != null) {
			try {
			 	br = new BufferedReader(new FileReader(ruta));
				String line = "";
			 	
				try {
					while ((line = br.readLine()) != null) {
						if (line.contains(ruc)) {
							
							String[] data = line.split("\\|");
							empsun = new EmpresaSunat();
							
							//empsun.setRuc(data[0]);
							clienteRuc.setRuc(data[0]);
						//	empsun.setNombreorazonsocial(data[1]);
							clienteRuc.setNombre_o_razon_social(data[1]);
							
							clienteRuc.setEstadodelcontribuyente(data[2]);
							
							clienteRuc.setCondicion_de_domicilio(data[3]);
							clienteRuc.setTipodevia(data[5]);
							clienteRuc.setNombredevia(data[6]);
							clienteRuc.setCodigodezona(data[6]);
							clienteRuc.setTipodezona(data[8]);
							clienteRuc.setNumero(data[9]);
							clienteRuc.setInterior(data[10]);
							clienteRuc.setLote(data[11]);
							clienteRuc.setDepartamento(data[12]);
							clienteRuc.setManzana(data[13]);
							clienteRuc.setKilometro(data[14]);
							clienteRuc.setUbigeo(data[4]);
							codigoUbigeo=data[4];
							if(codigoUbigeo!=null) {
						  		clienteRuc.setUbigeo(codigoUbigeo);
/*
								ubigeo = new Ubigeo();
							 	ubigeo=ubigeoService.retornaObjUbigeoxCodigo(codigoUbigeo);
							  	if(ubigeo!=null) {
							  		clienteRuc.setDepartamento(ubigeo.getDepartamento());
							  		clienteRuc.setDistrito(ubigeo.getDistrito());
							  		clienteRuc.setProvincia(ubigeo.getProvincia());
							 	}
							 	*/
							} 
							
  							baseResponseWrapper.setClienteRuc(clienteRuc);
  							baseResponseWrapper.setOk(true);
							 baseResponseWrapper.setMsg("Consulta correcta");
								baseResponseWrapper.setEstado(1);
								
							System.out.print("Ruc encontrado \n");
							System.out.print(clienteRuc.toString() +"\n");
							return baseResponseWrapper;
							// break;
						}
					}
					System.out.print("Ruc no encontrado \n");

					 
				} catch (FileNotFoundException ex) {
					System.out.println(ex.getMessage());
					baseResponseWrapper.setOk(false);
				//	baseResponseWrapper.setMessage("Error en la Consulta");
					return baseResponseWrapper;

				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				baseResponseWrapper.setOk(false);
				//baseResponseWrapper.setMessage("Error en la Consulta");
				e.printStackTrace();
			}
		} else {
			baseResponseWrapper.setOk(false);
		//	baseResponseWrapper.setMessage("Parametro de ubicación de padron no existe");
			return baseResponseWrapper;
		}
		 
			return baseResponseWrapper;
		 
	}
	
}