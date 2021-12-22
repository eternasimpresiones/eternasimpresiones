package org.gteperu.erp.everest.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gteperu.erp.everest.domain.Empresa;
import org.gteperu.erp.everest.domain.Modulo;
import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain.Tokenpassword;
import org.gteperu.erp.everest.domain.Userempresa;
import org.gteperu.erp.everest.domain.Userperfiles;
import org.gteperu.erp.everest.domain.Users;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service.TokenpasswordService;
import org.gteperu.erp.everest.service.UserempresaService;
import org.gteperu.erp.everest.service.UserperfilesService;
import org.gteperu.erp.everest.service.UsersService;
import org.gteperu.erp.everest.service._ClienteService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
public class UsersRestController {

	@Resource(name = "usersService")
	UsersService usersService;

	@Resource(name = "userempresaService")
	UserempresaService userempresaService;

	@Resource(name = "userperfilesService")
	UserperfilesService userperfilesService;

	@Resource(name = "tokenpasswordService")
	TokenpasswordService tokenpasswordService;

	@Resource(name = "clienteService")
	_ClienteService clienteService;

	Funciones fun = new Funciones();

	/*
	 * Web service para insertar usuarios() String username String password String
	 * email String name Integer estado
	 * 
	 * 
	 * Eduardo A. 22/05/2019
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	public ResponseWrapper insertUsers(@RequestBody Users users) throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		try {

			Boolean usernameexists = usersService.verificarUsername(users.getUsername());
			if (usernameexists) {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgTransaccionUsuarioUsernameRepeat);
			} else {
				Boolean emailexists = usersService.verificarEmail(users.getEmail());
				if (emailexists) {
					response.setEstado(Constantes.valTransaccionError);
					response.setMsg(Constantes.msgTransaccionUsuarioEmailRepeat);
				} else {
					Boolean insert = usersService.insertUser(users);
					if (insert) {
						response.setEstado(Constantes.valTransaccionOk);
						response.setMsg(Constantes.msgTransaccionUsuarioInsertarOk);
					} else {
						response.setEstado(Constantes.valTransaccionError);
						response.setMsg(Constantes.msgTransaccionUsuarioCantidadMax);
					}
				}
			}

			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " insertUsers. ERROR : " + e.getMessage());
			response.setEstado(Constantes.valTransaccionSinPermiso);
			response.setMsg(Constantes.msgTransaccionUsuarioInsertarError);
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/registrar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					users);
		}
	}

	@RequestMapping(value = "/listarPaginadosPorEmpresa/{idempresa}", method = RequestMethod.POST)
	public ResponseWrapper listarCuentas(@RequestBody Pagination paginas, @PathVariable("idempresa") Integer idempresa)
			throws ExceptionResponse {
		ResponseWrapper response = new ResponseWrapper();
		try {
			List<Users> lscuentas = usersService.listarUsuariosPaginadosPorEmpresa(idempresa, paginas);
			Integer cantidad = usersService.cantidadUsuariosPorEmpresa(idempresa);
			response.setEstado(Constantes.valTransaccionOk);
			response.setAaData(lscuentas);
			response.setDefaultObj(cantidad);
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listarCuentas. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listarCuentas",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					paginas);
		}
	}

	/*
	 * Listar Usuarios de una empresa Retorna name, username, email de la tabla
	 * usuarios Retorna nombres de la tabla perfiles
	 * 
	 * Parametro Integer id_empresa
	 * 
	 * RPV
	 * 
	 * 29/05/2019
	 * 
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/listarusuarios", method = RequestMethod.POST)
	public ResponseWrapper listarusuarios(@RequestBody @Validated Users users, HttpServletRequest request)
			throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		List lsUsu = new ArrayList();
		try {
			if (users.getId_empresa() != null) {
				lsUsu = usersService.retornaUsuarios(users);
			}
			if (lsUsu != null && lsUsu.size() > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lsUsu);
			} else {
				response.setEstado(Constantes.valTransaccionNoEncontro);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listarUsuarios. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/listarUsuarios" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/retornaAllUnidadesMedida",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					users);
		}
		return response;

	}

	/*
	 * Eliminar usuario Se envia el Parametro Integer id_usuarios para eliminar el
	 * registro del Usuario PM 28/05/2019
	 */

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/eliminaUsuario", method = RequestMethod.POST)
	public ResponseWrapper eliminaUsuario(@RequestBody @Validated Users users, HttpServletRequest request)
			throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			Userempresa userempresa = new Userempresa();
			Userperfiles userperfiles = new Userperfiles();
			Integer delete_userempresa = 0, delete_userperfil = 0, delete_usuario = 0;

			userempresa.setId_usuarios(users.getId_usuarios());
			delete_userempresa = userempresaService.eliminarUserEmpr(userempresa);
			userperfiles.setId_usuarios(users.getId_usuarios());
			delete_userperfil = userperfilesService.eliminarUserPerfil(userperfiles);

			if (delete_userempresa == 1 && delete_userperfil == 1) {
				delete_usuario = usersService.eliminaUsuario(users);
				if (delete_usuario == 1) {
					response.setEstado(Constantes.valTransaccionOk);
					response.setMsg(Constantes.msgTransaccionUsuarioEliminarOK);
					return response;
				} else {
					System.out.println(this.getClass().getSimpleName() + " eliminaUsuario");
					throw new Exception(Constantes.msgTransaccionUsuarioEliminarOK);
				}
			} else {
				System.out.println(this.getClass().getSimpleName() + " eliminarUserPerfil");
				throw new Exception(Constantes.msgTransaccionUsuarioEliminarOK);
			}

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " eliminaUsuario. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/eliminaUsuario" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/retornaAllUnidadesMedida",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					users);
		}
	}

	/*
	 * Actualizar usuario
	 * 
	 * Integer id_usuarios String name String username String email String password
	 * Integer id_user_perfiles Integer id_perfiles
	 * 
	 * PM 30/05/2019
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/actualizarUser", method = RequestMethod.POST)
	public ResponseWrapper actualizarUser(@RequestBody Users users, HttpServletRequest request)
			throws Exception {

		ResponseWrapper response = new ResponseWrapper();

		try {
			Integer cat = 0;
			if (users.getUsername() != null) {
				cat = usersService.actualizarUser(users);
			}

			if (cat != null && cat > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionUsuarioActualizarOK);
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionUsuarioActualizarError);
			}
		} catch (Exception e) {
			response.setEstado(Constantes.valTransaccionSinPermiso);
			response.setMsg(Constantes.msgTransaccionUsuarioActualizarError);
			System.out.println(this.getClass().getSimpleName() + " actualizarUser. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/actualizarUser" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/retornaAllUnidadesMedida",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					users);
		}
		return response;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/loginuser", method = RequestMethod.POST)
	public ResponseWrapper autentificate(@RequestBody @Validated Users users, HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		Users objtList = new Users();
		ResponseWrapper defaukt = new ResponseWrapper();

		objtList = usersService.loginUsers(users);
		if (objtList != null) {
			String token = "";
			if (objtList.getEstado() == 1) {
				if (objtList.getEstado() == 1) {
					List<Empresa> lsEmpre = new ArrayList<>();
					List<Empresa> lsUserEmpre = new ArrayList<>();

					defaukt.setDefaultObj(objtList);
					defaukt.setEstado(1);

					defaukt.setToken(token);
				} else if (objtList.getEstado() == 2) {

					java.sql.Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
					java.sql.Date date = new java.sql.Date(timeStamp.getTime());
					Tokenpassword tkp = new Tokenpassword();
					tkp.setFechahorainicio(timeStamp);
					tkp.setFechahorafin(timeStamp);
					tkp.setIdusuario(objtList.getId_usuarios());
					tkp.setEstado("1");
					tkp.setTipo(2);
					String toke = fun.getCadenaAlfanumAleatoria(50);
					tkp.setToken(toke);

					int actuli = tokenpasswordService.eliminaTokenpasswordGlobal(tkp);
					Integer in = tokenpasswordService.insertaTokenpassword(tkp);
					if (in != null && in > 0) {
						defaukt.setDefaultObj(objtList);
						defaukt.setEstado(1);

						defaukt.setMsg(toke);
					} else {
					}
				}
			} else {
			}

		} else {
			defaukt.setEstado(0);
		}
		return defaukt;

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/retornaModulosPaginasPorPerfil", method = RequestMethod.POST)
	public ResponseWrapper retornaModulosPaginasPorPerfil(@RequestBody Users user, HttpServletRequest request)
			throws Exception {
		ResponseWrapper retu = new ResponseWrapper();
		Users g = new Users();
		try {
			g.setUsername(fun.sacaid(request));
			g = usersService.findUsername(g);
			List<Modulo> objtList = usersService.retornaModulosPaginasPorPerfil(g);
			retu.setEstado(Constantes.valTransaccionOk);
			retu.setAaData(objtList);

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/retornaModulosPaginasPorPerfil",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					user);
		}
		return retu;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/cambiarPass", method = RequestMethod.POST)
	public ResponseWrapper cambiarPass(@RequestBody Users users) throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		try {
			int resp = usersService.cambiarPass(users);
			if (resp > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionUsuarioCambiarPassOk);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgTransaccionUsuarioCambiarPassError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " cambiarPass. ERROR : " + e.getMessage());
			response.setEstado(Constantes.valTransaccionSinPermiso);
			response.setMsg(Constantes.msgTransaccionUsuarioInsertarError);
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/cambiarPass",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(), users);
		}
	}
	
	

	/*
	  Parametro Integer id_empresa
	 * 
	 * SM
	  * 04-03-2021
	  */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/retornaUserApiXEmpresa", method = RequestMethod.POST)
	public ResponseWrapper retornaUserApiXEmpresa(@RequestBody @Validated Users users, HttpServletRequest request)
			throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		Users g = new Users();
		try {
			if (users.getId_empresa() != null) {
				g = usersService.retornaUserApiXEmpresa(users);
			}
			if (g != null ) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setDefaultObj(g);
			} else {
				response.setEstado(Constantes.valTransaccionNoEncontro);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " retornaUserApiXEmpresa. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/retornaUserApiXEmpresa" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/retornaUserApiXEmpresa",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					users);
		}
		return response;

	}
}
