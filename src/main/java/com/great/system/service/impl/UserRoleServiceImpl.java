package com.great.system.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.base.service.impl.BaseServiceImpl;
import com.great.system.dao.UserRoleDao;
import com.great.system.entity.SUserRoleEntity;
import com.great.system.service.UserRoleService;

@Service
public class UserRoleServiceImpl extends BaseServiceImpl<SUserRoleEntity> implements UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	public void deleteByUserId(String userId) {
		this.userRoleDao.deleteByUserId(userId);
	}


}
