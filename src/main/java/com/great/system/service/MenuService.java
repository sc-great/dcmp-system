package com.great.system.service;

import com.great.base.service.BaseService;
import com.great.system.entity.SMenuEntity;
import com.great.system.entity.SUserEntity;

import java.util.HashSet;
import java.util.List;

import com.great.tool.PageBean;
import com.great.tool.TreeNode;

public interface MenuService extends BaseService<SMenuEntity> {

	public void getResult(PageBean pageBean);
	
	public List<SMenuEntity> getMenuList();
	
	public List<SMenuEntity> getParentMenu();
	
	public List<TreeNode> getMenuTreeNode(String parentId, String roleId);

	public void changeStatus(String[] ids, String status);

	public List<SMenuEntity> getListByParentIdAndUserId(String id, String userId);

	public boolean canDelete(String menuId);
	
	public List<SMenuEntity> getParenMenutLists(String childMenuId);
	
	public SMenuEntity findById(String menuId);
	
}
