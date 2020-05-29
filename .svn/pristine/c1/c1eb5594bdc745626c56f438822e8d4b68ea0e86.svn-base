package com.great.system.service.impl;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.great.base.service.impl.BaseServiceImpl;
import com.great.system.dao.MenuDao;
import com.great.system.dao.RoleDao;
import com.great.system.entity.SMenuEntity;
import com.great.system.entity.SRoleMenuEntity;
import com.great.system.entity.SUserRoleEntity;
import com.great.system.service.MenuService;
import com.great.tool.PageBean;
import com.great.tool.TreeNode;
import org.springframework.util.StringUtils;

@Service
public class MenuServiceImpl extends BaseServiceImpl<SMenuEntity> implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Autowired
	private RoleDao roleDao;
	@Override
	public void getResult(PageBean pageBean) {
		this.menuDao.getResult(pageBean);
	}

	@Override
	public void changeStatus(String[] ids, String status) {
		this.menuDao.changeStatus(ids, status);
	}

	@Override
	public List<SMenuEntity> getMenuList() {
		return this.menuDao.getMenuList();
	}

	@Override
	public List<TreeNode> getMenuTreeNode(String parentId,String roleId) {
		//3个固定根节点
		List<SMenuEntity> rootMenus=this.menuDao.getMenuByExtendInt(1);
		
		
//		 List<SMenuEntity> sms =  this.menuDao.getMenuByParent(parentId);
		
		 List<SMenuEntity> menus = this.roleDao.getRoleMenus(roleId);
		 StringBuffer powerIds = new StringBuffer();
		 if(menus!=null&&menus.size()>0){
			 for(SMenuEntity m:menus ){
				 powerIds.append(m.getMenuId()).append(",");
			 }
		 }
		 List<TreeNode> parentNodes = new ArrayList<TreeNode>();
//		 if(sms!=null&&sms.size()>0){
//			 for(SMenuEntity sm:sms){
		 if(rootMenus!=null&&rootMenus.size()>0){
			 for(SMenuEntity sm:rootMenus){
				 TreeNode parentNode  = new TreeNode(sm.getMenuName(), sm.getMenuId(), false);
				 if(powerIds.indexOf(parentNode.getValue())>=0){
					 parentNode.setChecked(true);
				 }
				 parentNodes.add(creatTreeNode(parentNode,powerIds));
			 }
		 }
		 return parentNodes;
	}
	private TreeNode creatTreeNode(TreeNode node,StringBuffer powerIds){
	//	List<SMenuEntity> sms =  this.menuDao.getMenuByParent(node.getValue());
		//根节点直属下级 extendtext
		List<SMenuEntity> sms =  this.menuDao.getMenuByExtendtext(node.getValue());
		if(sms!=null&&sms.size()>0){			
			 for(SMenuEntity sm:sms){		
				 TreeNode childNode  = new TreeNode(sm.getMenuName(), sm.getMenuId(), false);
				 if(powerIds.indexOf(childNode.getValue())>=0){
					 childNode.setChecked(true);
				 }
				 creatTreeNode(childNode,powerIds);
				 node.getData().add(childNode);
			 }
		 }		
		return node;
	}

	@Override
	public List<SMenuEntity> getParentMenu() {
		return this.menuDao.getParentMenu();
	}

	//删除停用菜单
	@Override
	public List<SMenuEntity> getListByParentIdAndUserId(String id,String userId) {	
		HashSet<SMenuEntity> menus = new HashSet<SMenuEntity>();
		List<SUserRoleEntity> userRoleList = this.roleDao.getMenusByRoleId(userId);
		if (userRoleList != null && userRoleList.size() > 0) {
			for (SUserRoleEntity ur : userRoleList) {
				List<SRoleMenuEntity> rms = this.menuDao.getMenusByRoleId(ur.getRoleId());
				if (rms != null && rms.size() > 0) {
					for (SRoleMenuEntity rm : rms) {
						menus.add(rm.getSmenu());
					}
				}
			}
		}
		//当前用户拥有菜单
		List<SMenuEntity> newMenus = new ArrayList<SMenuEntity>(menus);
		//当前菜单下子菜单;状态 未删除 
		List<SMenuEntity> curMenus= this.menuDao.getMenuByPid(id);
		List<SMenuEntity> list=new ArrayList<>();
		for (SMenuEntity m : curMenus) {
			if(newMenus.contains(m)){
				list.add(m);
			}
		}
		return list;
	}

	@Override
	public boolean canDelete(String menuId) {
		// TODO Auto-generated method stub
		return this.menuDao.canDelete(menuId);
	}
	
	@Override
	public List<SMenuEntity> getParenMenutLists(String childMenuId){
		//
		List<SMenuEntity> menus=new ArrayList<>();
		SMenuEntity child=this.menuDao.findById(childMenuId);
		SMenuEntity parent=child.getParentMenu();
		if(StringUtils.isEmpty(parent)){
			//logger.info("child=="+child.getMenuName());
			return menus;
		}
		getParenMenutList(menus,parent.getMenuId());
		
		return menus;
	}
	
	public void getParenMenutList(List<SMenuEntity> menus,String childMenuId){
		SMenuEntity child=this.menuDao.findById(childMenuId);
		menus.add(child);
		SMenuEntity parent=child.getParentMenu();
		if(parent==null){
			return;
		}else{
			getParenMenutList(menus,parent.getMenuId());
		}
		
	}

	@Override
	public SMenuEntity findById(String menuId) {
		SMenuEntity child=this.menuDao.findById(menuId);
		return child;
	}
	
}
