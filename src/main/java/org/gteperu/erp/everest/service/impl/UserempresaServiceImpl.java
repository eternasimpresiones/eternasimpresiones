package org.gteperu.erp.everest.service.impl;

import org.springframework.stereotype.Service;
import org.gteperu.erp.everest.domain.Userempresa;
import org.gteperu.erp.everest.domain.Users;
import org.gteperu.erp.everest.mappers.UserempresaMapper;
import org.gteperu.erp.everest.service.UserempresaService;

import java.util.List;
import javax.annotation.Resource;
import java.util.ArrayList;

@Service("userempresaService")
public class UserempresaServiceImpl implements UserempresaService {

    @Resource(name = "userempresaMapper")
    UserempresaMapper userempresaMapper;


    @Override
    public Integer insertUserEmpr(Userempresa userempresa) {
    	
    	Integer auxiliar = 0;

		try {
			auxiliar = userempresaMapper.insertUserEmpr(userempresa);   ////Auxiliar captura 1 si se inserta correctamente

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" insertUserEmpr. ERROR : "+e.getMessage());
			throw e;
		}
			return auxiliar;
		
    }
    
    @Override
 	public Integer eliminarUserEmpr(Userempresa userempresa) {
 		Integer user=0;
 		try {	
 			user= userempresaMapper.eliminarUserEmpr(userempresa);
 			}catch (Exception e) {
 				System.out.println(this.getClass().getSimpleName()+ " eliminarUserEmpr. ERROR : " + e.getMessage());
 				throw e;
 			}
 					return user;
 	}
    

}