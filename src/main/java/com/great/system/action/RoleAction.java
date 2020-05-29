package com.great.system.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.great.base.action.BaseAction;
import com.great.base.entity.Message2Page;
import com.great.resource.StaticData;
import com.great.system.entity.SMenuEntity;
import com.great.system.entity.SRoleEntity;
import com.great.system.entity.SRoleMenuEntity;
import com.great.system.service.MenuService;
import com.great.system.service.RoleMenuService;
import com.great.system.entity.SUserRoleEntity;
import com.great.system.service.RoleService;
import com.great.tool.JsonCovert;
import com.great.tool.PageBean;
import com.great.tool.ReflectCommon;
import com.great.tool.ReplaceUtil;
import com.great.tool.TreeNode;
import com.great.tool.UUID;
import com.mysql.fabric.xmlrpc.base.Array;

/**
 * @author LUOCHENG
 *
 */
@RestController
@RequestMapping(value = "/role")
public class RoleAction extends BaseAction {
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleMenuService roleMenuService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 用户信息添加方法
	 * 
	 * @param user
	 *            对象参数接收前端提交属性
	 * @return 返回 Message2Page 封装对象
	 */
	@PostMapping("/add")
	public Message2Page add(SRoleEntity role) {
		SRoleEntity roleEntity = new SRoleEntity();
		roleEntity.setRoleName(role.getRoleName());
		roleEntity.setIsdeleted(false);
		List<SRoleEntity> roleList = roleService.findByExample(roleEntity);
		if (!CollectionUtils.isEmpty(roleList)) {
			return new Message2Page(false, "200", "已存在该角色名，请检查");
		}
		role.setRoleId(UUID.randomUUID());
		role.setIsdeleted(false);
		role.setStatus(StaticData.STATUS_NORMAL);
		role.setCreateTime(new Timestamp(new Date().getTime()));
		role.setCreateBy(this.getLoginUser().getUserName());

		// 过滤string类型的字段内的特殊字符
		ReflectCommon<SRoleEntity> common = new ReflectCommon<SRoleEntity>();
		Map<String, Object> map = common.test(role);
		role = (SRoleEntity) ReplaceUtil.replaceSpecString(map, role);

		try {
			roleService.save(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String msg = "添加角色：" + role.getRoleName();
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	/**
	 * 角色状态修改方法
	 * 
	 * @param ids
	 *            角色编号
	 * @param status
	 *            角色状态
	 * @return
	 */
	@PostMapping("/changeStatus")
	public Message2Page changeStatus(@RequestParam(value = "ids[]") String[] ids, @RequestParam String status) {
		// 角色在关联用户时建议不能进行删除删除和停用，提示“提示改角色已关联用户信息，请选择其他角色进行操作“
		if (status.equals("1")) {
			List<SRoleEntity> urList = roleService.validate(ids);
			if (urList == null || urList.size() < 1) {
				roleService.changeStatus(ids, status);
				String rolename = "";
				for (int i = 0; i < ids.length; i++) {
					SRoleEntity user = roleService.get(SRoleEntity.class, ids[i]);
					rolename += user.getRoleName() + ",";
				}
				StringBuffer msg = new StringBuffer();
				msg.append(status.equals("1") ? "停用" : "启用").append("角色：").append(rolename);
				saveLogB(msg.toString(), StaticData.LOG_TYPE_DO);
				return new Message2Page(true, "200", null);
			} else {
				String roleName = "";
				for (int i = 0; i < urList.size(); i++) {
					// SRoleEntity user = roleService.get(SRoleEntity.class,
					// urList.get(i).getRoleId());
					roleName += urList.get(i).getRoleName() + ",";
				}
				return new Message2Page(false, "200", roleName + "已关联用户信息，请选择其他角色进行操作");
			}
		} else {
			roleService.changeStatus(ids, status);
			String rolename = "";
			for (int i = 0; i < ids.length; i++) {
				SRoleEntity user = roleService.get(SRoleEntity.class, ids[i]);
				rolename += user.getRoleName() + ",";
			}
			StringBuffer msg = new StringBuffer();
			msg.append(status.equals("1") ? "停用" : "启用").append("角色：").append(rolename);
			saveLogB(msg.toString(), StaticData.LOG_TYPE_DO);
			return new Message2Page(true, "200", null);
		}

	}

	/**
	 * 角色修改方法
	 * 
	 * @param user
	 *            前端传回来的用户修改信息
	 * @return
	 */
	@PostMapping("/update")
	public Message2Page update(SRoleEntity role) {
		SRoleEntity roleEntity = new SRoleEntity();
		roleEntity.setRoleName(role.getRoleName());
		roleEntity.setIsdeleted(false);
		List<SRoleEntity> roleList = roleService.findByExample(roleEntity);
		if (!roleList.isEmpty() && (!roleList.get(0).getRoleId().equals(role.getRoleId()))) {
			return new Message2Page(false, "200", "已存在该角色名，请检查");
		}

		SRoleEntity roleSource = roleService.get(SRoleEntity.class, role.getRoleId());
		// 复制需要修改的用户信息给原来的用户对象
		this.mergeObject(role, roleSource);
		roleSource.setUpdateBy(this.getLoginUser().getUserName());
		roleSource.setUpdateTime(new Timestamp(new Date().getTime()));

		// 过滤string类型的字段内的特殊字符
		ReflectCommon<SRoleEntity> common = new ReflectCommon<SRoleEntity>();
		Map<String, Object> map = common.test(roleSource);
		roleSource = (SRoleEntity) ReplaceUtil.replaceSpecString(map, roleSource);

		roleService.update(roleSource);
		String msg = "更新角色：" + role.getRoleName();
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	/**
	 * 参过角色编号获取角色信息
	 * 
	 * @param id
	 *            角色编号
	 * @return
	 */
	@PostMapping("/getById")
	public Message2Page getById(@RequestParam String id) {
		logger.debug("接收到参数id：" + id);
		SRoleEntity role = roleService.get(SRoleEntity.class, id);
		JSONObject jsonObject = JSONObject.fromObject(role);
		jsonObject = JsonCovert.filterNull(jsonObject);
		return new Message2Page(true, "200", "", jsonObject);
	}

	@PostMapping("/updateRoleMenus")
	public Message2Page updateRoleMenus(
			@RequestParam(required = false, value = "checkedMenuIds[]") List<String> checkedMenuIds,
			@RequestParam String roleId) {
		List<SMenuEntity> menus = roleService.getRoleMenus(roleId);
		if (menus != null && !menus.isEmpty()) {
			roleMenuService.deleteByRoleId(roleId);
		}
		List<SMenuEntity> menuLists = new ArrayList<>();
		if (checkedMenuIds != null && !checkedMenuIds.isEmpty()) {
			for (String id : checkedMenuIds) {

				List<SMenuEntity> mps = menuService.getParenMenutLists(id);
				for (SMenuEntity sme : mps) {
					if (!menuLists.contains(sme)) {
						menuLists.add(sme);
					}
				}
				SMenuEntity child = menuService.findById(id);

				if (!menuLists.contains(child)) {
					menuLists.add(child);
				}
				// SRoleMenuEntity rm=new SRoleMenuEntity();
				// rm.setCreateBy(this.getLoginUser().getUserName());
				// rm.setCreateTime(new Timestamp(new Date().getTime()));
				// rm.setrMId(UUID.randomUUID());
				// rm.setRoleId(roleId);
				// rm.setSmenu(menuService.get(SMenuEntity.class, id));
				// roleService.save(rm);
			}

		}
		if (!menuLists.isEmpty()) {
			for (SMenuEntity menu : menuLists) {
				SRoleMenuEntity rm = new SRoleMenuEntity();
				rm.setCreateBy(this.getLoginUser().getUserName());
				rm.setCreateTime(new Timestamp(new Date().getTime()));
				rm.setrMId(UUID.randomUUID());
				rm.setRoleId(roleId);
				rm.setSmenu(menu);
				roleService.save(rm);
			}
		}
		return new Message2Page(true, "200", null);
	}

	@PostMapping("/getRoleMenus")
	public List<TreeNode> getRoleMenus(String roleId) {
		List<TreeNode> treeNodes = this.menuService.getMenuTreeNode("", roleId);
		return treeNodes;
	}

	/**
	 * 删除角色信息、逻辑删除
	 * 
	 * @param ids
	 *            需要删除的角色编号数组
	 * @return
	 */
	@PostMapping("/del")
	public Message2Page deleteCon(@RequestParam(value = "ids[]") String[] ids) {
		// 角色在关联用户时建议不能进行删除删除和停用，提示“提示改角色已关联用户信息，请选择其他角色进行操作“
		List<SRoleEntity> urList = roleService.validate(ids);
		if (urList == null || urList.size() < 1) {
			logger.debug("删除角色编号 vilIds：" + Arrays.asList(ids).toString());
			roleService.deleteByIds(SRoleEntity.class, true, getLoginUser().getUserName(), ids);
			String rolename = "";
			for (int i = 0; i < ids.length; i++) {
				SRoleEntity role = roleService.get(SRoleEntity.class, ids[i]);
				rolename += role.getRoleName() + ",";
			}
			String msg = "删除角色：" + rolename;
			saveLogB(msg, StaticData.LOG_TYPE_DO);
			return new Message2Page(true, "200", null);
		} else {
			String roleName = "";
			for (int i = 0; i < urList.size(); i++) {
				roleName += urList.get(i).getRoleName() + ",";
			}
			return new Message2Page(false, "200", roleName + "已关联用户信息，请选择其他角色进行操作");
		}

	}

	/**
	 * 获取角色信息列表所有角色信息
	 * 
	 * @return
	 */
	@GetMapping("/getList")
	public List<SRoleEntity> getAllList() {
		List<SRoleEntity> roles = roleService.findAllNoDelete(SRoleEntity.class);
		return roles;
	}

	@GetMapping("/getSelectedRole")
	public List<Map<String, String>> getSelectedRole(@RequestParam String userId) {
		List<SRoleEntity> roles = roleService.getSelectList();
		List<SUserRoleEntity> selectRoles = roleService.getSelectedRole(userId);
		List<Map<String, String>> roleSelect = new ArrayList<Map<String, String>>();
		if (roles != null && roles.size() > 0) {
			for (SRoleEntity role : roles) {
				Map<String, String> roleMap = new HashMap<String, String>();
				roleMap.put("name", role.getRoleName());
				roleMap.put("value", role.getRoleId());
				if (selectRoles != null && selectRoles.size() > 0) {
					for (SUserRoleEntity ur : selectRoles) {
						if (role.getRoleId().equals(ur.getRoleId())) {
							roleMap.put("selected", "selected");
							break;
						}
					}
				}
				roleSelect.add(roleMap);
			}
		}
		return roleSelect;
	}

	@GetMapping("/getSelectList")
	public List<Map<String, String>> getSelectList() {
		logger.debug("获取用户列表");
		List<SRoleEntity> roles = roleService.getSelectList();
		List<Map<String, String>> roleSelect = new ArrayList<Map<String, String>>();
		if (roles != null && roles.size() > 0) {
			for (SRoleEntity role : roles) {
				Map<String, String> roleMap = new HashMap<String, String>();
				roleMap.put("name", role.getRoleName());
				roleMap.put("value", role.getRoleId());
				roleSelect.add(roleMap);
			}
		}
		return roleSelect;
	}

	/**
	 * 获取角色信息分页
	 * 
	 * @param page
	 *            当前页
	 * @param limit
	 *            每页显示记录数
	 * @param startTime
	 *            查询开始时间
	 * @param endTime
	 *            查询结束时间
	 * @param roleName
	 *            角色名称
	 * @return JSONObject 返回用户分页对象，需要去掉Null转换为""
	 */
	@GetMapping("/getPage")
	public JSONObject getListByPageBean(@RequestParam Integer page, @RequestParam Integer limit,
			@RequestParam(required = false, defaultValue = "") String startTime,
			@RequestParam(required = false, defaultValue = "") String endTime,
			@RequestParam(required = false, defaultValue = "") String roleName) {
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPageNumber(page);
		pageBean.setLimit(limit);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("roleName", roleName);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		pageBean.setMap(param);
		roleService.getResult(pageBean);
		JSONObject returnObject = JSONObject.fromObject(pageBean);
		returnObject = JsonCovert.filterNull(returnObject);
		return returnObject;
	}

}
