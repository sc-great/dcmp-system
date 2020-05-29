package com.great.system.service;

import com.great.base.service.BaseService;
import com.great.system.entity.SMenuEntity;
import com.great.system.entity.SRoleEntity;
import com.great.system.entity.SUserEntity;
import com.great.system.entity.SUserRoleEntity;

import java.util.HashSet;
import java.util.List;

import com.great.tool.PageBean;

public interface RoleService extends BaseService<SRoleEntity> {

	public void getResult(PageBean pageBean);

	public void changeStatus(String[] ids, String status);
	
	public List<SRoleEntity> getSelectList();
	public List<SMenuEntity> getRoleMenus(String roleId);
	public List<SUserRoleEntity> getSelectedRole(String userId);

	public List<SRoleEntity> validate(String[] ids);
	
}
