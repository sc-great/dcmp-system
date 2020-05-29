package com.great.system.dao;

import com.great.base.dao.BaseDao;
import com.great.tool.PageBean;

public interface RoleMenuDao extends BaseDao {

	public void getResult(PageBean pageBean);

	public void changeStatus(String[] ids, String status);
	public void deleteByRoleId(String roleId);
}
