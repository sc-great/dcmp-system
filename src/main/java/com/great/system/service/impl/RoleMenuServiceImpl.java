package com.great.system.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.base.service.impl.BaseServiceImpl;
import com.great.system.dao.MenuDao;
import com.great.system.dao.RoleDao;
import com.great.system.dao.RoleMenuDao;
import com.great.system.dao.UserDao;
import com.great.system.entity.SMenuEntity;
import com.great.system.entity.SRoleMenuEntity;
import com.great.system.entity.SUserEntity;
import com.great.system.entity.SUserRoleEntity;
import com.great.system.service.RoleMenuService;
import com.great.system.service.UserService;
import com.great.tool.PageBean;

@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<SRoleMenuEntity> implements RoleMenuService {

	@Autowired
	private RoleMenuDao roleMenuDao;
	
	@Override
	public void getResult(PageBean pageBean) {
		this.roleMenuDao.getResult(pageBean);
	}

	@Override
	public void changeStatus(String[] ids, String status) {
		this.roleMenuDao.changeStatus(ids, status);
	}

	@Override
	public void deleteByRoleId(String roleId) {
		this.roleMenuDao.deleteByRoleId(roleId);
	}

	
}
