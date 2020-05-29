package com.great.system.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.base.service.impl.BaseServiceImpl;
import com.great.system.dao.LogDao;
import com.great.system.dao.MenuDao;
import com.great.system.dao.RoleDao;
import com.great.system.dao.UserDao;
import com.great.system.entity.SLogEntity;
import com.great.system.entity.SMenuEntity;
import com.great.system.entity.SRoleMenuEntity;
import com.great.system.entity.SUserEntity;
import com.great.system.entity.SUserRoleEntity;
import com.great.system.service.LogService;
import com.great.system.service.UserService;
import com.great.tool.PageBean;

@Service
public class LogServiceImpl extends BaseServiceImpl<SLogEntity> implements LogService {

	@Autowired
	private LogDao logDao;
	

	@Override
	public void getResult(PageBean pageBean) {
		this.logDao.getResult(pageBean);
	}


}
