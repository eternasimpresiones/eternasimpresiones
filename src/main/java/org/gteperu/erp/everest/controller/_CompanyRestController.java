package org.gteperu.erp.everest.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.gteperu.erp.everest.domain.PagoEmpresa;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain.Ubigeo;
import org.gteperu.erp.everest.domain.Users;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.domain._Tipo_Empresa;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service.PagoEmpresaService;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service.UsersService;
import org.gteperu.erp.everest.service._CompanyService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/company")
public class _CompanyRestController {
	@Resource(name = "companyService")
	_CompanyService companyService;

	@Resource(name = "parametrosService")
	ParametrosService parametrosService;

	@Resource(name = "pagoEmpresaService")
	PagoEmpresaService pagoEmpresaService;
	
	@Resource(name = "usersService")
	UsersService usersService;
	

	/*
	 * @RequestMapping(value = "/insertaEmpresa", method = RequestMethod.POST)
	 * public ResponseWrapper insertaEmpresa(@RequestBody @Validated _Company
	 * company, HttpServletRequest request) throws Exception {
	 * 
	 * ResponseWrapper response = new ResponseWrapper(); try {
	 * 
	 * Integer cat = 0; cat = companyService.insertEmpresa(company);
	 * 
	 * if (cat != null && cat > 0) {
	 * response.setEstado(Constantes.valTransaccionOk);
	 * response.setMsg(Constantes.msgTransaccionInsertarClienteOk); } else {
	 * response.setEstado(Constantes.valTransaccionErrornull);
	 * response.setMsg(Constantes.msgTransaccionInsertarClienteError); } } catch
	 * (Exception e) { System.out.println(this.getClass().getSimpleName()
	 * +" insertarCliente. ERROR : "+e.getMessage());
	 * response.setMsg(Constantes.msgTransaccionInsertarClienteError);
	 * response.setEstado(Constantes.valTransaccionSinPermiso); } finally {
	 * return response; } }
	 */
 
	/*
	 * Inserta una nueva empresa
	 */
	
 	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/insertaEmpresa", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public @ResponseBody Object handleFileUpload(@RequestPart("empresa") _Company empresa,
			@RequestPart("files") MultipartFile[] files, HttpServletRequest request) throws Exception {
		MultipartFile file = null;
		Integer empresa_registrada = 0;
 		String separador=System.getProperty("file.separator");
		ResponseWrapper response = new ResponseWrapper();
		_Parametros parametro = new _Parametros();
		List<_Parametros> lsPar = new ArrayList<>();
		parametro.setGrupo(Constantes.codigoGrupoIniParametros);
		parametro.setId_empresa(0);
		lsPar = parametrosService.retornaObjParametrosPorGrupoe(parametro);
		if (lsPar != null && lsPar.size() > 0) {
			// generamos las variables para obtener los directorios para la
			// cuenta 
			String rutamatriz="";
			for (int i = 0; i < lsPar.size(); i++) {
			  	if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
					rutamatriz=lsPar.get(i).getValor();
 				}
			}
		 	//String dir_base=directorio+separador+rutabase+separador+rutafiles;
			String dir_base=rutamatriz ;
			try {
				if (files.length != 0) {
					file = files[0];
					file.getSize();
					byte[] bytes = file.getBytes();
					File dirempresa = new File(dir_base+separador+empresa.getNro_documento_empresa());
					if (dirempresa.mkdirs()) {
						System.out.println("Directory is created "+dirempresa.getPath() +"\n");
					} else {
						System.out.println("Directory cannot be created");
					}
					// crea archivo del logo
					String fileName = empresa.getNro_documento_empresa() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
					String path = "";
					path = dir_base +separador + empresa.getNro_documento_empresa() +separador + fileName;
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
					stream.write(bytes);
					stream.close();
					empresa.setLogo(fileName);
					empresa.setUrlimagen(path);
					empresa.setEstado(1);
					empresa_registrada = companyService.insertEmpresa(empresa);
				} else {
					File dirempresa = new File(dir_base+separador+empresa.getNro_documento_empresa());
					if (dirempresa.mkdirs()) {
						System.out.println("Directory is created"+dirempresa.getPath() +"\n");
					} else {
						System.out.println("Directory cannot be created");
					}
					empresa.setEstado(1);
					empresa_registrada = companyService.insertEmpresa(empresa);
				}
				if (empresa_registrada != null && empresa_registrada > 0) {
					Users userApi = new Users();
					
					userApi.setUsername(empresa.getNro_documento_empresa());
					userApi.setName(empresa.getNombre_comercial_empresa());
					userApi.setId_empresa(empresa.getId_empresa());
					Boolean usuarioCreado=usersService.crearUsuarioApi(userApi);
					if(usuarioCreado) {
						System.out.println("Usuario API creado correctamente\n");
					}
					
					response.setEstado(Constantes.valTransaccionOk);
					response.setMsg(Constantes.msgTransaccionInsertarClienteOk);
					response.setDefaultObj(empresa);
				} else {
					response.setEstado(Constantes.valTransaccionErrornull);
					response.setMsg(Constantes.msgTransaccionInsertarClienteError);
					response.setDefaultObj(empresa);
					throw new Exception(Constantes.msgTransaccionError);
				}
			} catch (Exception e) {
				response.setMsg(Constantes.msgTransaccionInsertarClienteError);
				System.out.println(this.getClass().getSimpleName() + " insertaEmpresa. ERROR : " + e.getMessage());
				System.out.println(this.getClass().getSimpleName() + "/insertaEmpresa" + "Linea nro : "	+ e.getStackTrace()[0].getLineNumber());
				throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/insertaEmpresa",
		 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),empresa);
			}
		}
		return response;
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/subirCertificado", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public @ResponseBody Object subirCertificado(@RequestPart("empresa") _Company empresa,
			@RequestPart("files") MultipartFile[] files, HttpServletRequest request) throws Exception {
		MultipartFile file = null;
		String separador=System.getProperty("file.separator");

		Integer firma_actualizada = 0;
		ResponseWrapper response = new ResponseWrapper();
		_Parametros parametro = new _Parametros();
		List<_Parametros> lsPar = new ArrayList<>();
		parametro.setGrupo(Constantes.codigoGrupoIniParametros);
		parametro.setId_empresa(0);
		
		lsPar = parametrosService.retornaObjParametrosPorGrupoe(parametro);
		if (lsPar != null && lsPar.size() > 0) {
			String  rutamatriz="";
			for (int i = 0; i < lsPar.size(); i++) {
				if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaBase)) {
				 		if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
						rutamatriz=lsPar.get(i).getValor();
	 				}
				}
			}
			try {
				if (files.length != 0) {
					for (int i = 0; i < files.length; i++) {
						file = files[i];
						if (!file.isEmpty()) {
							file.getSize();
							byte[] bytes = file.getBytes();
							// crea archivo del certificado
							String fileName = empresa.getNro_documento_empresa() + "."
									+ FilenameUtils.getExtension(file.getOriginalFilename());
							String path = "";
							path = rutamatriz + separador+ empresa.getNro_documento_empresa() +separador+ fileName;
							BufferedOutputStream stream = new BufferedOutputStream(
									new FileOutputStream(new File(path)));
							stream.write(bytes);
							stream.close();
							empresa.setUrlfirma(path);
							firma_actualizada = companyService.actualizarFirma(empresa);
							if (firma_actualizada != null && firma_actualizada > 0) {
								response.setEstado(Constantes.valTransaccionOk);
								response.setMsg(Constantes.msgTransaccionInsertarClienteOk);
							} else {
								response.setEstado(Constantes.valTransaccionErrornull);
								response.setMsg(Constantes.msgTransaccionInsertarClienteError);
							}
						} else {
							System.out.println("Fallo al subir " + file.getOriginalFilename()
									+ " porque el archivo está vacío.\n");
							response.setEstado(0);
							return response;
						}
					}
				} else {
					System.out.println("No se ha subido ningun archivo");
				}
			} catch (Exception e) {
				response.setMsg(Constantes.msgTransaccionInsertarClienteError);
				System.out.println(this.getClass().getSimpleName() + " subirCertificado. ERROR : " + e.getMessage());
				System.out.println(this.getClass().getSimpleName() + "/subirCertificado" + "Linea nro : "
						+ e.getStackTrace()[0].getLineNumber());
				throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/subirCertificado",
		 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),empresa);
			}
		}
		return response;
	}
	/*
	 * lista las empresas registradas en la aplicación
	 * 
	 * */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/listarEmpresa", method = RequestMethod.POST)
	public ResponseWrapper listarEmpresa(@RequestBody @Validated _Company company, HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		List objtList = new ArrayList();
		
		ResponseWrapper obj = new ResponseWrapper();
		try {
			
				objtList = companyService.listEmpresa(company);
				if (objtList != null && objtList.size() > 0) {
					obj.setEstado(Constantes.valTransaccionOk);
					
					
					obj.setAaData(objtList);
				} else {
					obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
					obj.setEstado(Constantes.valTransaccionNoEncontro);
					obj.setAaData(objtList);
				}

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listarEmpresa. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/listarEmpresa",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),company);
		}
		return obj;
		
	}
	
	/*
	 * Lista las empresas activas con paginacion
	 */
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/listarEmpresaPaginacion", method = RequestMethod.POST)
	public ResponseWrapper listarEmpresaPaginacion(@RequestBody @Validated _Company company, HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		List objtList = new ArrayList();
		ResponseWrapper obj = new ResponseWrapper();
		try {
			
				objtList = companyService.listEmpresaPaginacion(company);
				if (objtList != null && objtList.size() > 0) {
					obj.setEstado(Constantes.valTransaccionOk);
					obj.setAaData(objtList);
				} else {
					obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
					obj.setEstado(Constantes.valTransaccionNoEncontro);
					obj.setAaData(objtList);
				}

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listarEmpresaPaginacion. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/listarEmpresaPaginacion",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),company);
		}
		return obj;
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/listarTipoEmpresa", method = RequestMethod.POST)
	public ResponseWrapper listarTipoEmpresa(@RequestBody @Validated _Tipo_Empresa tpoCompany, HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		List objtList = new ArrayList();
		ResponseWrapper obj = new ResponseWrapper();
		try {
				objtList = companyService.listTipoEmpresa();
				if (objtList != null && objtList.size() > 0) {
					obj.setEstado(Constantes.valTransaccionOk);
					obj.setAaData(objtList);
				} else {
					obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
					obj.setEstado(Constantes.valTransaccionNoEncontro);
					obj.setAaData(objtList);
				}

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listarTipoEmpresa. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/listarTipoEmpresa",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),tpoCompany);
		}
		return obj;
		
	}

	/*
	 * @RequestMapping(value = "/actualizarEmpresa", method =
	 * RequestMethod.POST) public ResponseWrapper
	 * actualizarEmpresa(@RequestBody @Validated _Company company,
	 * HttpServletRequest request) throws Exception { ResponseWrapper response =
	 * new ResponseWrapper(); String cod_emp; try { Integer cat = 0; cat =
	 * companyService.actualizarEmpresa(company); if (cat != null && cat>0) {
	 * response.setEstado(Constantes.valTransaccionOk);
	 * response.setMsg(Constantes.msgTransaccionActualizarClienteOk); } else {
	 * response.setEstado(Constantes.valTransaccionErrornull);
	 * response.setMsg(Constantes.msgTransaccionActualizarClienteError); } }
	 * catch (Exception e) {
	 * response.setEstado(Constantes.valTransaccionSinPermiso);
	 * response.setMsg(Constantes.msgTransaccionActualizarClienteError);
	 * System.out.println(this.getClass().getSimpleName()+
	 * " actualizarCliente. ERROR : " + e.getMessage());
	 * System.out.println(this.getClass().getSimpleName() + "/actualizarCliente"
	 * + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
	 * 
	 * } finally { return response; } }
	 */

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/actualizarEmpresa", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public @ResponseBody Object handleFileUpload1(@RequestPart("empresa") _Company empresa,
			@RequestPart("files") MultipartFile[] files, HttpServletRequest request) throws Exception {
		MultipartFile file = null;
         String separador = System.getProperty("file.separator");
		Integer empresa_actualizada = 0;
		ResponseWrapper response = new ResponseWrapper();
		_Parametros parametro = new _Parametros();
		List<_Parametros> lsPar = new ArrayList<>();
		parametro.setGrupo(Constantes.codigoGrupoIniParametros);
		parametro.setId_empresa(0);
		lsPar = parametrosService.retornaObjParametrosPorGrupoe(parametro);
		if (lsPar != null && lsPar.size() > 0) {
			// generamos las variables para obtener los directorios para la
			// cuenta
			String rutamatriz = "";
		 	for (int i = 0; i < lsPar.size(); i++) {
			  	if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
					rutamatriz=lsPar.get(i).getValor();
 				}
			}
			String dir_base = rutamatriz;
			try {
				if (files.length != 0) {
					file = files[0];
					file.getSize();
					byte[] bytes = file.getBytes();
					File dirempresa = new File(dir_base + separador + empresa.getNro_documento_empresa());
					dirempresa.mkdir();
					// crea archivo del logo
					String fileName = empresa.getNro_documento_empresa() + "."
							+ FilenameUtils.getExtension(file.getOriginalFilename());
					String path = "";
					path = dir_base + separador + empresa.getNro_documento_empresa() + separador + fileName;
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
					stream.write(bytes);
					stream.close();
					empresa.setLogo(fileName);
					empresa.setUrlimagen(path);
					empresa_actualizada = companyService.actualizarEmpresa(empresa);
				} else if (empresa.getUrlimagen() != null) {
					String path = "";
					path = dir_base + separador + empresa.getNro_documento_empresa() + separador + empresa.getLogo();
					empresa.setUrlimagen(path);
					empresa_actualizada = companyService.actualizarEmpresa(empresa);
				}else{
					empresa_actualizada = companyService.actualizarEmpresa(empresa);
				}
				empresa_actualizada = companyService.actualizarEmpresa(empresa);
				if (empresa_actualizada != null && empresa_actualizada > 0) {
					response.setEstado(Constantes.valTransaccionOk);
					response.setMsg(Constantes.msgTransaccionEmpresaActualizarOk);
					response.setDefaultObj(empresa);
					
				} else {
					response.setEstado(Constantes.valTransaccionErrornull);
					response.setMsg(Constantes.msgTransaccionEmpresaActualizarError);
					response.setDefaultObj(empresa);
					throw new Exception(Constantes.msgTransaccionInsertarDocumentoError);
					// agregar logica para eliminar carpeta de la empresa creada
				}
			} catch (Exception e) {
				response.setMsg(Constantes.msgTransaccionEmpresaActualizarError);
				System.out.println(this.getClass().getSimpleName() + " actualizarEmpresa. ERROR : " + e.getMessage());
				System.out.println(this.getClass().getSimpleName() + "/actualizarEmpresa" + "Linea nro : "	+ e.getStackTrace()[0].getLineNumber());
				throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/actualizarEmpresa",
		 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),empresa);
				
			}
		}
		return response;
	}
	
	/*
	 * Elimina datos de la empresa por su id
	 * */

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/eliminarEmpresa", method = RequestMethod.POST)
	public ResponseWrapper eliminarEmpresa(@RequestBody @Validated _Company company, HttpServletRequest request)
			throws Exception {
		ResponseWrapper response = new ResponseWrapper();
         String separador = System.getProperty("file.separator");
		String rutamatriz ="";
		 	try {
			_Parametros parametro = new _Parametros();
			List<_Parametros> lsPar = new ArrayList<>();
			parametro.setGrupo(Constantes.codigoGrupoIniParametros);
			parametro.setId_empresa(0);
			lsPar = parametrosService.retornaObjParametrosPorGrupoe(parametro);
			
			for (int i = 0; i < lsPar.size(); i++) {
		  		if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
					rutamatriz=lsPar.get(i).getValor();
 				}
			}
 			String dirName = rutamatriz + separador + company.getNro_documento_empresa();
			FileUtils.deleteDirectory(new File(dirName));
			Integer cat = 0;
			cat = companyService.eliminarEmpresa(company);

			if (cat != null && cat > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionEliminarClienteOk);
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionEliminarClienteError);
				throw new Exception(Constantes.msgTransaccionInsertarDocumentoError);
			}
		} catch (Exception e) {
			response.setEstado(Constantes.valTransaccionSinPermiso);
			response.setMsg(Constantes.msgTransaccionEliminarClienteError);
			System.out.println(this.getClass().getSimpleName() + " eliminarCliente. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/eliminarCliente" + "Linea nro : "+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/eliminarEmpresa",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),company);
		}
		return response; 
	}

	/*
	 * actualiza los datos de la empresa seleccionada
	 * 
	 * */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/retornaEmpresaPorIdempresa", method = RequestMethod.POST)
	public ResponseWrapper retornaEmpresaPorIdempresa(@RequestBody @Validated _Company company, HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		_Company emp = new _Company();
		byte[] file = null;
		ResponseWrapper obj = new ResponseWrapper();
		try {
			
				emp = companyService.retornaEmpresaPorIdempresa(company);
				if (emp.getUrlimagen() == null) {
					if (emp != null) {
						obj.setEstado(Constantes.valTransaccionOk);
						obj.setDefaultObj(emp);
						obj.setFile(file);

					} else {
						obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
						obj.setEstado(Constantes.valTransaccionNoEncontro);
						obj.setDefaultObj(emp);
						obj.setFile(file);
					}
				} else {
					
					File archivo = new File(emp.getUrlimagen());
					
					if (archivo.exists()) {
						file = Files.readAllBytes(new File(emp.getUrlimagen()).toPath());
 					}
					
					
					if (emp != null) {
						obj.setEstado(Constantes.valTransaccionOk);
						obj.setDefaultObj(emp);
						obj.setFile(file);

					} else {
						obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
						obj.setEstado(Constantes.valTransaccionNoEncontro);
						obj.setDefaultObj(emp);
						obj.setFile(file);
					}
				}

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " retornaEmpresaPorIdempresa. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/retornaEmpresaPorIdempresa",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),company);
		} 
		return obj;
		
	}
/*
 * Busca empresa y retorna datos de la misma
 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/buscaEmpresa", method = RequestMethod.POST)
	public ResponseWrapper buscaEmpresa(@RequestBody @Validated _Company company, HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		_Company emp = new _Company();
		byte[] file = null;
		ResponseWrapper obj = new ResponseWrapper();
		try {
			
				emp = companyService.buscaEmpresaPorIdempresa(company);
				obj.setDefaultObj(emp);

			

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " buscaEmpresa. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/buscaEmpresa",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),company);
		} 
		return obj;
		
	}

	/*
	 * Web service que permite subir archivos a la empresa seleccionada
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/cargarArchivoEmpresa", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public @ResponseBody Object handleFileUpload3(@RequestPart("empresa") _Company empresa,
			@RequestPart("files") MultipartFile[] files, HttpServletRequest request) throws Exception {
		MultipartFile file = null;
		Integer empresa_actualizada = 0;
		ResponseWrapper response = new ResponseWrapper();
		_Parametros parametro = new _Parametros();
		List<_Parametros> lsPar = new ArrayList<>();
         String separador = System.getProperty("file.separator");
		parametro.setGrupo(Constantes.codigoGrupoIniParametros);
		parametro.setId_empresa(0);
		lsPar = parametrosService.retornaObjParametrosPorGrupoe(parametro);
		if (lsPar != null && lsPar.size() > 0) {
			// generamos las variables para obtener los directorios para la
			// cuenta
			String rutamatriz = "";
 			for (int i = 0; i < lsPar.size(); i++) {
 		 		if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
					rutamatriz=lsPar.get(i).getValor();
 				}
			 }
			String dir_base = rutamatriz;

			try {
				if (files.length != 0) {
					file = files[0];
					file.getSize();
					byte[] bytes = file.getBytes();
					File dirempresa = new File(dir_base+separador+ empresa.getNro_documento_empresa());
					dirempresa.mkdir();
					if (empresa.getUrlarchivo() != null) {
						Files.deleteIfExists(Paths.get(empresa.getUrlarchivo()));
					}
					String fileName = file.getOriginalFilename();
					String path = "";
					path = dir_base +separador+ empresa.getNro_documento_empresa() + separador + fileName;
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
					stream.write(bytes);
					stream.close();
					empresa.setArchivo(fileName);
					empresa.setUrlarchivo(path);
					empresa_actualizada = companyService.actualizarEmpresa(empresa);
				} else if (empresa.getUrlimagen() != null) {
					String path = "";
					path = dir_base + separador+empresa.getNro_documento_empresa() + separador + empresa.getLogo();
					empresa.setUrlimagen(path);
					empresa_actualizada = companyService.actualizarEmpresa(empresa);
				}

				if (empresa_actualizada != null && empresa_actualizada > 0) {
					response.setEstado(Constantes.valTransaccionOk);
					response.setMsg(Constantes.msgTransaccionInsertarClienteOk);
					response.setDefaultObj(empresa);
				} else {
					response.setEstado(Constantes.valTransaccionErrornull);
					response.setMsg(Constantes.msgTransaccionInsertarClienteError);
					response.setDefaultObj(empresa);
					throw new Exception(Constantes.msgTransaccionInsertarDocumentoError);
					// agregar logica para eliminar carpeta de la empresa creada
				}
			} catch (Exception e) {
				response.setMsg(Constantes.msgTransaccionInsertarClienteError);
				System.out.println(this.getClass().getSimpleName() + " cargarArchivoEmpresa. ERROR : " + e.getMessage());
				System.out.println(this.getClass().getSimpleName() + "/cargarArchivoEmpresa" + "Linea nro : "	+ e.getStackTrace()[0].getLineNumber());
				throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/cargarArchivoEmpresa",
		 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
		 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),empresa);
			}
		}
		return response;
	}
	
	/*
	 * Descarga datos de la empresa seleccionada
	 */

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/descargaArchivo", method = RequestMethod.POST)
	public @ResponseBody byte[] DescargarArchivos(@RequestBody @Validated _Company empresa, HttpServletRequest request)
			throws Exception {
		byte[] data = null;
		try {
	        String separador = System.getProperty("file.separator");

			List<_Parametros> lsPar = new ArrayList<>();
			ResponseWrapper obj = new ResponseWrapper();
			_Parametros parametro = new _Parametros();
			String file = "";
			ServletContext context = request.getServletContext();
			parametro.setGrupo(Constantes.codigoGrupoIniParametros);
			parametro.setId_empresa(0);
			lsPar = parametrosService.retornaObjParametrosPorGrupoe(parametro);
			String rutamatriz = "";
 			for (int i = 0; i < lsPar.size(); i++) {
				if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
					rutamatriz=lsPar.get(i).getValor();
 				}
			}
			String appPath2 = rutamatriz + separador + empresa.getNro_documento_empresa() + separador + empresa.getArchivo();
			// String appPath2 = "C:/Files/CUENTAS/20523799623/PruebaAA.txt";
			Path path = Paths.get(appPath2);
			data = Files.readAllBytes(path);
			return data;

		} catch (IOException e) {
			System.out.println("Error en el PFX   => " + e.getMessage());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/descargaArchivo",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),empresa);
		}
	}

	/*
	 * Inserta Pago a la empresa selecccionada  
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/insertaPagoEmpresa", method = RequestMethod.POST)
	public ResponseWrapper insertPago(@RequestBody @Validated PagoEmpresa pagoEmpresa, HttpServletRequest request)
			throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		try {
			Integer cat = 0;
			cat = pagoEmpresaService.insertPago(pagoEmpresa);
			if (cat != null && cat > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionInsertarClienteOk);
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionInsertarClienteError);
				throw new Exception(Constantes.msgTransaccionInsertarDocumentoError);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " insertaPagoEmpresa. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/insertaPagoEmpresa",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),pagoEmpresa);
		} 
		return response;
		
	}
	/*
	 * Lista los Pagos de las Empresas 
	 */

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/listaPagoEmpresa", method = RequestMethod.POST)
	public ResponseWrapper listarPagoEmpresa(@RequestBody @Validated Integer empresa, HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		List objtList = new ArrayList();
		ResponseWrapper obj = new ResponseWrapper();
		try {
			
				objtList = pagoEmpresaService.listarPagoEmpresa(empresa);
				if (objtList != null && objtList.size() > 0) {
					obj.setEstado(Constantes.valTransaccionOk);
					obj.setAaData(objtList);
				} else {
					obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
					obj.setEstado(Constantes.valTransaccionNoEncontro);
					obj.setAaData(objtList);
				}
			

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listaPagoEmpresa. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/listaPagoEmpresa",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),empresa);
		}
		return obj;
		
	}

	/////////////////////////////////////////////////////// public Integer
	/////////////////////////////////////////////////////// actualizarPago(PagoEmpresa
	/////////////////////////////////////////////////////// pagoEmpresa);
	/*
	 * Actualiza datos de Pago de la empresa selecccionada 
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/actualizarPagoEmpresa", method = RequestMethod.POST)
	public ResponseWrapper actualizarPago(@RequestBody @Validated PagoEmpresa pagoEmpresa, HttpServletRequest request)
			throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		try {
			Integer cat = 0;
			cat = pagoEmpresaService.actualizarPago(pagoEmpresa);
			if (cat != null && cat > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionInsertarClienteOk);
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionInsertarClienteError);
				throw new Exception(Constantes.msgTransaccionError);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " actualizarPagoEmpresa. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/actualizarPagoEmpresa",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),pagoEmpresa);
		}
		return response;
		
	}
	
	/*
	 * Actualiza la fecha de expiracion de la empresa seleccionada
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/actualizarFechaExEmpresa", method = RequestMethod.POST)
	public ResponseWrapper actualizarFechaExEmpresa(@RequestBody @Validated _Company company, HttpServletRequest request)
			throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		try {
			Integer cat = 0;
			cat = companyService.actualizarFechaExEmpresa(company);
			if (cat != null && cat > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionInsertarClienteOk);
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionInsertarClienteError);
				throw new Exception(Constantes.msgTransaccionError);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " actualizarFechaExEmpresa. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/actualizarFechaExEmpresa",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),company);
		}
		return response;
		
	}
	
	/*
	 * Lista IdUbigeo por cod_ubigeo 
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@RequestMapping(value = "/listar", method = RequestMethod.POST)
	public ResponseWrapper listar(@RequestBody @Validated _Company company, HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		Ubigeo ubigeo =new Ubigeo();
		ResponseWrapper obj = new ResponseWrapper();
		try {
			
				ubigeo = companyService.listar(company);
				if (ubigeo != null) {
					obj.setEstado(Constantes.valTransaccionOk);
					obj.setDefaultObj(ubigeo);
				} else {
					obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
					obj.setEstado(Constantes.valTransaccionNoEncontro);
					obj.setDefaultObj(ubigeo);
				}

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/listar",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),company);
		}
		return obj;
		
	}

	
	
	
	/*
	 * 
	 * Consulta de RUC
	 * 
	 * MAD 14/02/2020 
	 * 
	 * Parametros:
	 * 	- nro_documento_empresa
	 * 
	 */
	@RequestMapping(value = "/consultaRuc", method = RequestMethod.POST)
	public ResponseWrapper consultaRuc(@RequestBody @Validated _Company company, HttpServletRequest request)
			throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		_Company comp = new _Company();
		try {
			comp = companyService.consultaRucSunaPadron(company);
			if (comp != null && comp.getNro_documento_empresa()!=null) {
				if(comp.getEstadoEmpresa()!=null && comp.getEstadoEmpresa()){
					response.setDefaultObj(comp);
					response.setEstado(Constantes.valTransaccionOk);
					//response.setMsg(Constantes.msgTransaccionInsertarClienteOk);
				}else{
					response.setDefaultObj(comp);
					response.setEstado(Constantes.valTransaccionOk);
					response.setMsg(Constantes.msgConsultaRucEstadoBaja);
				}
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgConsultaRUCNoEncontrada);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " consultaRuc. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(),	this.getClass().getSimpleName() + "/consultaRuc",
	 				e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => " + e.getClass() + " => message: " + 
	 				e.getMessage() + "=> linea nro: " + e.getStackTrace()[0].getLineNumber(),company);
		}
		return response;
		
	}
}
