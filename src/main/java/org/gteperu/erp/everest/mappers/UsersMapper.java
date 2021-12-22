package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Modulo;
import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.Users;
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
public interface UsersMapper {

    @Insert(" insert into usuarios(username,password,email,name,estado,role,id_empresa) VALUES "
    		+ "(#{username},#{password},#{email},#{name},#{estado},#{role},#{id_empresa});")
	@SelectKey(statement = "select currval('sec_usuarios') as id_usuarios", keyProperty = "id_usuarios", before = false, resultType = int.class)
	@Options(useGeneratedKeys = true, keyProperty = "id_usuarios", flushCache = FlushCachePolicy.TRUE)
    public Integer insertUsers(Users users);
    
    @Select( "SELECT COUNT(*) FROM usuarios WHERE id_empresa = #{idempresa}")
	public Integer retornarCantidadPorEmpresa(@Param("idempresa") Integer idempresa);
    
    @Select("SELECT COUNT (username) FROM usuarios WHERE username=#{username} ;")
    public Integer verificarUsername(@Param("username") String username);
    
    @Select("SELECT COUNT (email) FROM usuarios WHERE email=#{email}")
    public Integer verificarEmail(@Param("email") String email);
    
    @Select("SELECT COUNT (*) FROM usuarios WHERE id_empresa=#{id_empresa}")
    public Integer cantidadUsuariosPorEmpresa(@Param("id_empresa") Integer id_empresa);
    
    @Select( "select * from usuarios order by id_usuarios desc limit 1;")
    public List<Users> listUltUser();
        
    public List<Users> retornaUsuarios(@Param("users") Users users);
    
    public  Users retornaUserApiXEmpresa(@Param("users") Users users);

    

    @Delete(" delete from usuarios where id_usuarios=#{id_usuarios}; ")
	public Integer eliminaUsuario(Users users);
    
	@Update("update usuarios set name=#{name},username=#{username},email=#{email} where id_usuarios=#{id_usuarios};")
	public Integer actualizarUser(Users users);
	
	/*RO 31-07-2019*/
	@Select("select *  from usuarios where username=#{username}")
	public Users findByUsername(@Param("username") String u);
	
	/**RO 08-11-2019**/
	public Users loginUsers(@Param("users") Users id);
	
	public List<Modulo> retornaModulosPaginasPorPerfil(Users id);
	
	public List<Users> listarUsuariosPaginadosPorEmpresa(@Param("idempresa") Integer idempresa, @Param("pagina") Pagination pagina);
	
	@Update("update usuarios set password=#{password} where id_usuarios=#{id_usuarios};")
	public Integer cambiarPass(Users users);

}