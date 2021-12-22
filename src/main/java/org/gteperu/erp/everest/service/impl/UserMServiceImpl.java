package org.gteperu.erp.everest.service.impl;

import java.util.List;


import javax.annotation.Resource;


import org.gteperu.erp.everest.domain.User;
import org.gteperu.erp.everest.mappers.UserMapper;
import org.gteperu.erp.everest.service.UserMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userMService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserMServiceImpl implements UserMService{
	@Resource(name = "userMapper")
	private UserMapper userMapper;
	
	@Override
	public Integer insertaUsuario(User u) {
		// TODO Auto-generated method stub
		return userMapper.insertUser(u);
	}

	@Override
	public List<User> retorna() {
		// TODO Auto-generated method stub
		return userMapper.findAllUsers();
	}

}
