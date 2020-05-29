package com.great.system.dao;

import java.util.List;

import com.great.base.dao.BaseDao;
import com.great.system.entity.SMenuEntity;
import com.great.system.entity.SRoleEntity;
import com.great.system.entity.SRoleMenuEntity;
import com.great.system.entity.SUserRoleEntity;
import com.great.tool.PageBean;

public interface RoleDao extends BaseDao {

	public void getResult(PageBean pageBean);

	public List<SUserRoleEntity> getMenusByRoleId(String userId);
	
	public void changeStatus(String[] ids, String status);
	
	public List<SRoleEntity> getSelectList();
	public List<SMenuEntity> getRoleMenus(String roleId);
	
	public List<SUserRoleEntity> getSelectedRole(String userId);

	public List<SRoleEntity> findUserRoleByIds(String[] ids);
}
