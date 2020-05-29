package com.great.system.service;

import com.great.base.service.BaseService;
import com.great.system.entity.SMenuEntity;
import com.great.system.entity.SUserEntity;
import com.great.system.entity.SUserType;

import java.util.List;
import java.util.Map;

import com.great.tool.PageBean;

public interface UserService extends BaseService<SUserEntity> {

	public void getResult(PageBean pageBean);

	public void getResult01(PageBean pageBean);

	public void changeStatus(String[] ids, String status);
	
	public List<SMenuEntity> getUserMenus(String userId,String menuType);
	
	public void deleteUserTypeByUserId(String userId);
	
	public List<SUserType> getUserTypeByUserId(String userId);

	public List<SUserEntity> getSelectList(String userType);
	
	public List<SUserEntity> getUserByPhone(String userPhone);
	
	public List<SUserEntity> getUserByLoginName(String loginName);

	public List<Map> getUserMenuList(String userId);
	
	public void springAsynchronousMethod();

	public SUserEntity getByToken(String token);
}
