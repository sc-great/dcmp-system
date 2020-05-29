package com.great.system.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.great.base.service.impl.BaseServiceImpl;
import com.great.system.dao.MenuDao;
import com.great.system.dao.RoleDao;
import com.great.system.dao.UserDao;
import com.great.system.entity.SMenuEntity;
import com.great.system.entity.SRoleMenuEntity;
import com.great.system.entity.SUserEntity;
import com.great.system.entity.SUserRoleEntity;
import com.great.system.entity.SUserType;
import com.great.system.service.UserService;
import com.great.tool.PageBean;

@Service
public class UserServiceImpl extends BaseServiceImpl<SUserEntity> implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private RoleDao roleDao;

	@Async
	@Override
	public void springAsynchronousMethod(){
		try {
	        Thread.sleep(4000);
	    } catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    logger.info("结束："+System.currentTimeMillis());
	}
	@Override
	public void getResult(PageBean pageBean) {
		this.userDao.getResult(pageBean);
	}
	@Override
	public void getResult01(PageBean pageBean) {
		this.userDao.getResult01(pageBean);
	}

	@Override
	public void changeStatus(String[] ids, String status) {
		this.userDao.changeStatus(ids, status);
	}

	@Override
	public List<SMenuEntity> getUserMenus(String userId, String menuType) {
		HashSet<SMenuEntity> menus = new HashSet<SMenuEntity>();
		List<SUserRoleEntity> userRoleList = this.roleDao.getMenusByRoleId(userId);
		if (userRoleList != null && userRoleList.size() > 0) {
			for (SUserRoleEntity ur : userRoleList) {
				List<SRoleMenuEntity> rms = this.menuDao.getMenusByRoleId(ur.getRoleId(), menuType);
				if (rms != null && rms.size() > 0) {
					for (SRoleMenuEntity rm : rms) {
						menus.add(rm.getSmenu());
					}
				}
			}
		}
		List<SMenuEntity> newMenus = new ArrayList<SMenuEntity>(menus);
		if (!newMenus.isEmpty()) {
			newMenus.sort(new Comparator<SMenuEntity>() {
				@Override
				public int compare(SMenuEntity o1, SMenuEntity o2) {
					// TODO Auto-generated method stub
					return o1.getMenuOrder() - o2.getMenuOrder();
				}
			});
		}
		return newMenus;
	}

	@Override
	public void deleteUserTypeByUserId(String userId) {
		this.userDao.deleteUserTypeByUserId(userId);
	}

	@Override
	public List<SUserType> getUserTypeByUserId(String userId) {
		return this.userDao.getUserTypeByUserId(userId);
	}

	@Override
	public List<SUserEntity> getSelectList(String userType) {
		// TODO Auto-generated method stub
		return this.userDao.getSelectList(userType);
	}

	@Override
	public List<SUserEntity> getUserByPhone(String userPhone) {
		return this.userDao.getUserByPhone(userPhone);
	}

	@Override
	public List<SUserEntity> getUserByLoginName(String loginName) {
		return this.userDao.getUserByLoginName(loginName);
	}

	//菜单列表页左侧栏
	@Override
	public List<Map> getUserMenuList(String userId) {
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
		List<SMenuEntity> newMenus = new ArrayList<SMenuEntity>(menus);
		if (!newMenus.isEmpty()) {
			newMenus.sort(new Comparator<SMenuEntity>() {
				@Override
				public int compare(SMenuEntity o1, SMenuEntity o2) {
					// TODO Auto-generated method stub
					return o1.getMenuOrder() - o2.getMenuOrder();
				}
			});
		}

		Map<String, Object> menuTree = new HashMap<String, Object>();
		List<Map> treeList = new ArrayList<>();
		for (SMenuEntity sMenuEntity : newMenus) {
			// 从null节点下开始
			if (sMenuEntity.getParentMenu() == null) {
				Map<String, Object> tree = new HashMap<String, Object>();
				tree.put("id", sMenuEntity.getMenuId());
				tree.put("name", sMenuEntity.getMenuName());
				tree.put("children", createChildrenTree(newMenus, sMenuEntity.getMenuId()));
				treeList.add(tree);
			}
		}
		// menuTree.put("children", treeList);
		return treeList;
	}

	private List<Map> createChildrenTree(List<SMenuEntity> newMenus, String id) {
		List<SMenuEntity> list = this.menuDao.getMenuByPid(id);
		if (!list.isEmpty()) {
			list.sort(new Comparator<SMenuEntity>() {
				@Override
				public int compare(SMenuEntity o1, SMenuEntity o2) {
					// TODO Auto-generated method stub
					return o1.getMenuOrder() - o2.getMenuOrder();
				}
			});
		}
		List<Map> treeList = new ArrayList<>();
		if (!list.isEmpty()) {
			for (SMenuEntity m : list) {
				if (newMenus.contains(m)) {
					Map<String, Object> tree = new HashMap<>();
					tree.put("id", m.getMenuId());
					tree.put("name", m.getMenuName());
					tree.put("children", createChildrenTree(newMenus, m.getMenuId()));
					treeList.add(tree);
				}
			}
		}
		return treeList;
	}
	@Override
	public SUserEntity getByToken(String token) {
		return userDao.getByToken(token);
	}

}
