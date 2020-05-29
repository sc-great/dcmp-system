package com.great.system.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.base.service.impl.BaseServiceImpl;
import com.great.system.dao.MenuDao;
import com.great.system.dao.RoleDao;
import com.great.system.dao.UserDao;
import com.great.system.entity.SMenuEntity;
import com.great.system.entity.SRoleEntity;
import com.great.system.entity.SRoleMenuEntity;
import com.great.system.entity.SUserEntity;
import com.great.system.entity.SUserRoleEntity;
import com.great.system.service.MenuService;
import com.great.system.service.RoleService;
import com.great.system.service.UserService;
import com.great.tool.PageBean;
import com.great.tool.TreeNode;

@Service
public class RoleServiceImpl extends BaseServiceImpl<SRoleEntity> implements RoleService {

	
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private MenuService menuService;
	@Override
	public void getResult(PageBean pageBean) {
		this.roleDao.getResult(pageBean);
	}

	@Override
	public void changeStatus(String[] ids, String status) {
		this.roleDao.changeStatus(ids, status);
	}
	
	public List<SRoleEntity> getSelectList(){
		return this.roleDao.getSelectList();
	}
	public List<SMenuEntity> getRoleMenus(String roleId){
		return this.roleDao.getRoleMenus(roleId);
	};
	
	public List<SUserRoleEntity> getSelectedRole(String userId){
		return this.roleDao.getSelectedRole(userId);
	}
	public List<TreeNode> getMenuTree(String roleId){
		List<TreeNode> nodes=new ArrayList<TreeNode>();
		List<SMenuEntity> menus = getRoleMenus(roleId);
		List<SMenuEntity> allMenus=menuService.findAllNoDelete(SMenuEntity.class);
		if(allMenus!=null&&!allMenus.isEmpty()){
			for(SMenuEntity sm:allMenus){
				TreeNode node=new TreeNode(sm.getMenuName(), sm.getMenuId(), false);
				if(menus!=null&&menus.contains(sm)){
					node.setChecked(true);
				}
				nodes.add(node);
			}
		}
		return null;
	}
	public List<TreeNode> createParentTree(SMenuEntity sm){
		return null;
	}

	@Override
	public List<SRoleEntity> validate(String[] ids) {
		// TODO Auto-generated method stub
		return this.roleDao.findUserRoleByIds(ids);
	}
}
