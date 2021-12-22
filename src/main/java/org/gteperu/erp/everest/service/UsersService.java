package org.gteperu.erp.everest.service;

import org.gteperu.erp.everest.domain.Users;
import org.gteperu.erp.everest.domain.Empleado;
import org.gteperu.erp.everest.domain.Modulo;
import org.gteperu.erp.everest.domain.Pagination;

import java.util.List;

public interface UsersService {

	public Boolean insertUser(Users users);

	public Boolean verificarUsername(String username);
	
	public Boolean verificarEmail(String email);

	public List<Users> listUltUser();

	public List<Users> listarUsuariosPaginadosPorEmpresa(Integer idempresa, Pagination paginas);

	public Integer cantidadUsuariosPorEmpresa(Integer idempresa);

	public List<Users> retornaUsuarios(Users users);

	public Integer eliminaUsuario(Users users);

	public Integer actualizarUser(Users users);

	public Users findUsername(Users e);

	public List<Modulo> retornaModulosPaginasPorPerfil(Users id);

	/** RO 08-11-2019 **/
	public Users loginUsers(Users id);
	
	public Integer cambiarPass(Users users);
	
	public Boolean crearUsuarioApi(Users users);
	
	public  Users  retornaUserApiXEmpresa(Users users);



}