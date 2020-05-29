package com.great.system.dao;

import java.util.List;

import com.great.base.dao.BaseDao;
import com.great.system.entity.SMenuEntity;
import com.great.system.entity.SRoleMenuEntity;
import com.great.tool.PageBean;

public interface MenuDao extends BaseDao {

	public void getResult(PageBean pageBean);
	
	public void changeStatus(String[] ids, String status);
	
	public List<SMenuEntity> getMenuList();
	
	public List<SMenuEntity> getParentMenu();
	
	public List<SMenuEntity> getMenuByParent(String parentId);

	public List<SRoleMenuEntity> getMenusByRoleId(String roleId, String menuType);

	public List<SRoleMenuEntity> getMenusByRoleId(String roleId);

	public List<SMenuEntity> getMenuByPid(String id);

	public List<SMenuEntity> getMenuByExtendInt(int i);

	public List<SMenuEntity> getMenuByExtendtext(String value);
	
	public boolean canDelete(String menuId);
	
	public SMenuEntity findById(String menuId);
}
