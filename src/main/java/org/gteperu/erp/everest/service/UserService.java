package org.gteperu.erp.everest.service;

import org.gteperu.erp.everest.domain.User;
import org.gteperu.erp.everest.domain.Users;
import org.gteperu.erp.everest.mappers.UserMapper;
import org.gteperu.erp.everest.mappers.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import javax.annotation.Resource;

import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain.Empleado;

@Service
@Transactional
public class UserService  implements UserDetailsService {

  @Resource(name = "usersMapper")
    private UsersMapper usersMapper;

   @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    	Users users = usersMapper.findByUsername(s);
        //Empleado user = empleadoMapper.findByUsername(s);

        if (users == null) {
            throw new UnauthorizedUserException("User doesn't exist!");
        }
       UserDetails f= new org.springframework.security.core.userdetails.User(users.getUsername(),
                users.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority(users.getRole())));
        
        return f;
    }
    /*@Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Empleado user = empleadoMapper.findByUsername(s);

        if (user == null) {
            throw new UnauthorizedUserException("User doesn't exist!");
        }
       UserDetails f= new org.springframework.security.core.userdetails.User(user.getUsername(),
    		   user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
        
        return f;
    }*/
    
}
