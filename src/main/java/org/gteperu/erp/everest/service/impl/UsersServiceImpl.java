package org.gteperu.erp.everest.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.gteperu.erp.everest.controller.Funciones;
import org.gteperu.erp.everest.domain.Modulo;
import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.Userempresa;
import org.gteperu.erp.everest.domain.Userperfiles;
import org.gteperu.erp.everest.domain.Users;
import org.gteperu.erp.everest.mappers.CompanyMapper;
import org.gteperu.erp.everest.mappers.UserempresaMapper;
import org.gteperu.erp.everest.mappers.UserperfilesMapper;
import org.gteperu.erp.everest.mappers.UsersMapper;
import org.gteperu.erp.everest.service.UsersService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.stereotype.Service;

@Service("usersService")
public class UsersServiceImpl implements UsersService {

	@Resource(name = "usersMapper")
	UsersMapper usersMapper;

	@Resource(name = "userperfilesMapper")
	UserperfilesMapper userperfilesMapper;
	
	@Resource(name="userempresaMapper")
	UserempresaMapper userempresaMapper;
	
	@Resource(name="companyMapper")
	CompanyMapper companyMapper;

	@Override
	public Boolean insertUser(Users users) {
		try {
			Integer cantidad_usuarios = cantidadUsuariosPorEmpresa(users.getId_empresa());
			if(companyMapper.cantidadMaximaUsuarios(users.getId_empresa()) <= cantidad_usuarios) {
				return false;
			}else {
				users.setRole("ROLE_ADMIN");
				usersMapper.insertUsers(users);
				Userperfiles up = new Userperfiles();
				Userempresa ue = new Userempresa();
				up.setId_perfiles(Constantes.idPerfilAdminCliente);
				up.setId_usuarios(users.getId_usuarios());
				up.setEstado(1);
				userperfilesMapper.insertUserperf(up);
				ue.setId_empresa(users.getId_empresa());
				ue.setId_usuarios(users.getId_usuarios());
				userempresaMapper.insertUserEmpr(ue);
				return true;
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " insertUsers. ERROR : " + e.getMessage());
			throw e;
		}
	}

	
	
	
	
	@Override
	public Boolean verificarUsername(String username) {
		try {
			Integer auxiliar = usersMapper.verificarUsername(username);
			return auxiliar > 0;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " verificarUsername. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Users> listUltUser() {

		Integer auxiliar = 0;
		List<Users> lsuser = new ArrayList<Users>();

		try {
			lsuser = usersMapper.listUltUser();

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listUltUser. ERROR : " + e.getMessage());
			throw e;
		}
		return lsuser;

	}

	@Override
	public List<Users> retornaUsuarios(Users users) {
		List<Users> lsUsuarios = new ArrayList<Users>();
		try {
			lsUsuarios = usersMapper.retornaUsuarios(users);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " retornaUsuarios. ERROR : " + e.getMessage());
			throw e;
		}
		return lsUsuarios;

	}
	
	
	

	/* RO 08-11-2019 */
	@Override
	public Users loginUsers(Users id) {
		Users u = new Users();
		try {
			u = usersMapper.loginUsers(id);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			System.out.println("loginUsers ");
			throw e;
		}
		return u;

	}

	@Override
	public Integer eliminaUsuario(Users users) {
		Integer user = 0;
		try {
			user = usersMapper.eliminaUsuario(users);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " eliminaUsuario. ERROR : " + e.getMessage());

			throw e;
		}
		return user;

	}

	@Override
	public Integer actualizarUser(Users users) {
		Integer usu = 0;
		Integer usu1 = 0;
		Userperfiles userperfil = new Userperfiles();
		userperfil = users.getUserperfiles();
		userperfil.setUsername(users.getUsername());
		try {
			usu = usersMapper.actualizarUser(users);
			usu1 = userperfilesMapper.actualizarUserperfil(userperfil);

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " ActualizarUser. ERROR : " + e.getMessage());
			throw e;
		}
		return usu;

	}

	///
	@Override
	public Users findUsername(Users e) {
		return usersMapper.findByUsername(e.getUsername());

	}

	@Override
	public List<Modulo> retornaModulosPaginasPorPerfil(Users idgestor) {
		return usersMapper.retornaModulosPaginasPorPerfil(idgestor);
	}

	@Override
	public List<Users> listarUsuariosPaginadosPorEmpresa(Integer idempresa, Pagination paginas) {
		try {
			return usersMapper.listarUsuariosPaginadosPorEmpresa(idempresa, paginas);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listarUsuariosPaginados. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Integer cantidadUsuariosPorEmpresa(Integer idempresa) {
		try {
			return usersMapper.retornarCantidadPorEmpresa(idempresa);
		} catch (Exception e) {
			System.out
					.println(this.getClass().getSimpleName() + " cantidadUsuariosPorEmpresa ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Boolean verificarEmail(String email) {
		try {
			Integer auxiliar = usersMapper.verificarEmail(email);
			return auxiliar > 0;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " verificarEmail. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Integer cambiarPass(Users users) {
		return usersMapper.cambiarPass(users);
	}

	@Override
	public Boolean crearUsuarioApi(Users users) {
		try {
			Integer auxiliar =0;
			auxiliar=usersMapper.verificarUsername(users.getUsername());
			if(auxiliar>0) {
				users.setUsername("api"+users.getUsername());
			}
		 
		     String pass=Funciones.randomAlphaNumeric(25);
 		     users.setPassword(pass);
		     users.setRole("ROLE_CLIENTEAPI");
			 users.setEstado(0);
			usersMapper.insertUsers(users);
			
			Userperfiles up = new Userperfiles();
			up.setId_perfiles(Constantes.idPerfilAccesoApi);
			up.setId_usuarios(users.getId_usuarios());
			up.setEstado(1);
			userperfilesMapper.insertUserperf(up);
			
			return true;
			 
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " crearUsuarioApi. ERROR : " + e.getMessage());
			throw e;
		}
	}

	
	
	@Override
	public  Users  retornaUserApiXEmpresa(Users users) {
		 Users  user = new Users ();
		try {
			
			users.setIdperfiles(Constantes.idPerfilAccesoApi);
			user = usersMapper.retornaUserApiXEmpresa(users);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " retornaUserApiXEmpresa. ERROR : " + e.getMessage());
			throw e;
		}
		return user;

	}
	
}