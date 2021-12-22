package org.gteperu.erp.everest.service.impl;

import org.springframework.stereotype.Service;
import org.gteperu.erp.everest.domain.Userempresa;
import org.gteperu.erp.everest.domain.Userperfiles;
import org.gteperu.erp.everest.mappers.UserperfilesMapper;
import org.gteperu.erp.everest.service.UserperfilesService;

import java.util.List;
import javax.annotation.Resource;
import java.util.ArrayList;

@Service("userperfilesService")
public class UserperfilesServiceImpl implements UserperfilesService {

    @Resource(name = "userperfilesMapper")
    UserperfilesMapper userperfilesMapper;


    @Override
    public Integer insertUserperf(Userperfiles userperfiles) {
    	
    	Integer auxiliar = 0;

		try { 
			auxiliar = userperfilesMapper.insertUserperf(userperfiles);   ////Auxiliar captura 1 si se inserta correctamente

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" insertUserperf. ERROR : "+e.getMessage());
		} finally {
			return auxiliar;
		}

    }
    
    @Override
 	public Integer eliminarUserPerfil(Userperfiles userperfiles) {
 		Integer user=0;
 		try {	
 			user= userperfilesMapper.eliminarUserPerfil(userperfiles);
 			}catch (Exception e) {
 			System.out.println(this.getClass().getSimpleName()+ " eliminarUserPerfil. ERROR : " + e.getMessage());
 				}finally{
 					return user;
 	}
 	}

}