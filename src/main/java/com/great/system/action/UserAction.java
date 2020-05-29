package com.great.system.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import com.great.manager.service.OrgService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.great.base.action.BaseAction;
import com.great.base.entity.Message2Page;
import com.great.resource.StaticData;
import com.great.system.entity.SMenuEntity;
import com.great.system.entity.SUserEntity;
import com.great.system.entity.SUserRoleEntity;
import com.great.system.entity.SUserType;
import com.great.system.service.UserRoleService;
import com.great.system.service.UserService;
import com.great.tool.DigitalSign;
import com.great.tool.JsonCovert;
import com.great.tool.PageBean;
import com.great.tool.ReflectCommon;
import com.great.tool.ReplaceUtil;
import com.great.tool.UUID;

/**
 * @author LUOCHENG
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserAction extends BaseAction {
	@Autowired
	private UserService userService;
//	@Autowired
//	private OrgService orgService;
	@Autowired
	private UserRoleService userRoleService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 用户信息添加方法
	 * 
	 * @param user
	 *            对象参数接收前端提交属性
	 * @return 返回 Message2Page 封装对象
	 */
	@PostMapping("/add")
	public Message2Page add(SUserEntity user) {
		// 过滤string类型的字段内的特殊字符
		ReflectCommon<SUserEntity> common = new ReflectCommon<SUserEntity>();
		Map<String, Object> map = common.test(user);
		user = (SUserEntity) ReplaceUtil.replaceSpecString(map, user);	
		
		List<SUserEntity> users=userService.getUserByLoginName(user.getLoginName());
		if(!CollectionUtils.isEmpty(users)){
			return new Message2Page(false, "500", "登录名已存在");
		}
		users=userService.getUserByPhone(user.getUserPhone());
		if(!CollectionUtils.isEmpty(users)){
			return new Message2Page(false, "500", "手机号已存在");
		}
		String areaCode=this.getPageParameter("areaCode");
//		if(!StringUtils.isEmpty(areaCode)){
////			BArea area=areaService.get(BArea.class, areaCode);
////			if(area!=null){
////				user.setAreaId(area.getAreaCode());
////			}
//			SOrgIngo org=orgService.get(SOrgIngo.class, areaCode);
//			if(org!=null){
//				user.setAreaId(org.getOrgCode());
//			}
//		}
		user.setUserId(UUID.randomUUID());
		user.setIsdeleted(false);
		user.setPasswd(DigitalSign.getMD5(user.getPasswd()));
		user.setStatus(StaticData.STATUS_NORMAL);
		user.setCreateTime(new Date());
		user.setCreateUser(this.getLoginUser());
		
		
		userService.save(user);
		String userType = this.getPageParameter("userType");
		if(userType!=null&&!userType.equals("")){
			String[] userTypes = userType.split(",");
			this.addUserType(user.getUserId(),userTypes);
		}
		String roles = this.getPageParameter("role");
		if(roles!=null&&!roles.equals("")){
			String[] roleString = roles.split(",");
			this.addUserRole(user.getUserId(),roleString);
		}
		String msg = "添加用户：" + user.getUserName();
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}
	
	@PostMapping("/getLoginUsers")
	public Message2Page getLoginUsers() {
		SUserEntity user = this.getLoginUser();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject jsonObject = JSONObject.fromObject(user,jsonConfig);
		jsonObject = JsonCovert.filterNull(jsonObject);
		return new Message2Page(true, "200", "", jsonObject);
	}

	/**
	 * 用户密码修改方法
	 * 
	 * @param password1
	 *            老密码
	 * @param password2
	 *            新密码
	 * @return
	 */
	@PostMapping("/changePassword")
	public Message2Page changePassword(@RequestParam String password1, @RequestParam String password2, @RequestParam String password3) {
		SUserEntity vmUser = this.getLoginUser();
		if(DigitalSign.getMD5(password1).equals(vmUser.getPasswd())) {
			if(password2.equals(password3)){
				vmUser.setPasswd(DigitalSign.getMD5(password2));
				userService.update(vmUser);
				String msg = "修改用户密码：" + vmUser.getUserName();
				saveLogB(msg, StaticData.LOG_TYPE_DO);
				return new Message2Page(true, "200", null);
			} else {
				return new Message2Page(false, "200", "两次密码输入不同,请重新输入");
			}
		} else {
			return new Message2Page(false, "200", "旧密码输入不正确,请重新输入");
		}
	}

	@PostMapping("/rechangePassword")
	public Message2Page rechangePassword(@RequestParam String userId,@RequestParam String password1, @RequestParam String password2) {
		SUserEntity vmUser = userService.get(SUserEntity.class,userId);
		if(DigitalSign.getMD5(password1).equals(vmUser.getPasswd())) {
				vmUser.setPasswd(DigitalSign.getMD5(password2));
				userService.update(vmUser);
				String msg = "修改用户密码：" + vmUser.getUserName();
				saveLogB(msg, StaticData.LOG_TYPE_DO);
				return new Message2Page(true, "200", null);
		} else {
			return new Message2Page(false, "200", "旧密码输入不正确,请重新输入");
		}
	}
	
	
	@PostMapping("/getUsermenu")
	public List<SMenuEntity> getUsermenu(@RequestParam String menuType) {
		List<SMenuEntity> menus = userService.getUserMenus(this.getLoginUser().getUserId(),menuType);
		return menus;
	}
	
	@PostMapping("/getUsermenuByToken")
	public List<SMenuEntity> getUsermenuByToken(@RequestParam String token) {
		if(StringUtils.isEmpty(token)){
			return null;
		}
		SUserEntity user=userService.getByToken(token);
		this.getSession().setAttribute(StaticData.USER_SESSION, user);
		List<SMenuEntity> menus = userService.getUserMenus(user.getUserId(),"system");
		return menus;
	}
	
	@PostMapping("/getUserMenuList")
	public List<Map> getUserMenuList(){
		return userService.getUserMenuList(this.getLoginUser().getUserId());
	}

	/**
	 * 用户状态修改方法
	 * 
	 * @param ids
	 *            用户编号
	 * @param status
	 *            用户状态
	 * @return
	 */
	@PostMapping("/changeStatus")
	public Message2Page changeStatus(@RequestParam(value = "ids[]") String[] ids, @RequestParam String status) {
		userService.changeStatus(ids, status);
		String username = "";
		for (int i = 0; i < ids.length; i++) {
			SUserEntity user = userService.get(SUserEntity.class, ids[i]);
			username += user.getUserName() + ",";
		}
		StringBuffer msg = new StringBuffer();
		msg.append(status.equals("1") ? "停用" : "启用").append("用户：").append(username);
		saveLogB(msg.toString(), StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	/**
	 * 用户修改方法
	 * 
	 * @param user
	 *            前端传回来的用户修改信息
	 * @return
	 */
	@PostMapping("/update")
	public Message2Page update(SUserEntity user) {
		// 过滤string类型的字段内的特殊字符
		ReflectCommon<SUserEntity> common = new ReflectCommon<SUserEntity>();
		Map<String, Object> map = common.test(user);
		user = (SUserEntity) ReplaceUtil.replaceSpecString(map, user);	
		
		List<SUserEntity> users=userService.getUserByLoginName(user.getLoginName());
		if(!CollectionUtils.isEmpty(users)){
			if(users.size()==1&&users.get(0).getUserId().equals(user.getUserId())){
				
			}else{
				return new Message2Page(false, "500", "登录名已存在");
			}
		}
		users=userService.getUserByPhone(user.getUserPhone());
		if(!CollectionUtils.isEmpty(users)){
			if(users.size()==1&&users.get(0).getUserId().equals(user.getUserId())){
				
			}else{
				return new Message2Page(false, "500", "手机号已存在");
			}
		}
		SUserEntity userSource = userService.get(SUserEntity.class, user.getUserId());
		// 复制需要修改的用户信息给原来的用户对象
		this.mergeObject(user, userSource);
		userSource.setUpdateBy(this.getLoginUser());
		userSource.setUpdateTime(new Date());
//		userSource.setPasswd(DigitalSign.getMD5(user.getPasswd()));
		
		
		userService.update(userSource);
		
		this.userService.deleteUserTypeByUserId(user.getUserId());
		String userType = this.getPageParameter("userType");
		if(userType!=null&&!userType.equals("")){
			String[] userTypes = userType.split(",");
			this.addUserType(user.getUserId(),userTypes);
		}
		//清除用户所有角色
		this.userRoleService.deleteByUserId(user.getUserId());
		String roles = this.getPageParameter("role");
		if(roles!=null&&!roles.equals("")){
			String[] roleString = roles.split(",");
			//添加用户角色
			this.addUserRole(user.getUserId(),roleString);
		}
		String msg = "更新用户：" + user.getUserName();
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	
	/**
	 * 保存用户角色关联信息
	 * @param userId
	 * @param roles
	 */
	private void addUserRole(String userId,String[] roles){
		if(roles!=null&&roles.length>0){
			for(String roleId : roles){
				SUserRoleEntity ur = new SUserRoleEntity();
				ur.setuRId(UUID.randomUUID());
				ur.setRoleId(roleId);
				ur.setUserId(userId);
				this.userService.save(ur);
			}
		}
	}
	
	private void addUserType(String userId,String[] types){
		if(types!=null&&types.length>0){
			for(String type : types){
				SUserType ut = new SUserType();
				ut.setUtId(UUID.randomUUID());
				ut.setTypeCode(type);
				ut.setUserId(userId);
				this.userService.save(ut);
			}
		}
	}
	
	@PostMapping("/getUserTypeByUserId")
	public List<SUserType> getUserTypeByUserId(@RequestParam String userId) {
		List<SUserType> userTypes = this.userService.getUserTypeByUserId(userId);
		return userTypes;
	}
	
	/**
	 * 获取用户信息列表
	 * 
	 */
	@PostMapping("/getSelectList")
	public List<Map<String,String>> getSelectList(@RequestParam String userType) {
		logger.debug("获取用户列表");
		List<SUserEntity> users = userService.getSelectList(userType);
		List<Map<String,String>> userSelect =  new ArrayList<Map<String,String>>();
		if(users!=null&&users.size()>0){
			for(SUserEntity user : users){
				Map<String,String> userMap = new HashMap<String, String>();
				userMap.put("id", user.getUserId());
				userMap.put("name", user.getUserName());
				userSelect.add(userMap);
			}
		}
		return userSelect;
	}
	
	
	/**
	 * 参过用户编号获取用户信息
	 * 
	 * @param id
	 *            用户编号
	 * @return
	 */
	@PostMapping("/getById")
	public Message2Page getById(@RequestParam String id) {
		logger.debug("接收到参数id：" + id);
		SUserEntity user = userService.get(SUserEntity.class, id);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject jsonObject = JSONObject.fromObject(user,jsonConfig);
		jsonObject = JsonCovert.filterNull(jsonObject);
		//SOrgIngo area=orgService.getByOrgCode(user.getAreaId());
		return new Message2Page(true, "200", null, jsonObject);
//		BArea area=areaService.getByAreaCode(user.getAreaId());
//		return new Message2Page(true, "200", area==null?"":area.getAreaName(), jsonObject);
	//	return null;
	}

	/**
	 * 删除用户信息、逻辑删除
	 * 
	 * @param ids
	 *            需要删除的用户编号数组
	 * @return
	 */
	@PostMapping("/del")
	public Message2Page deleteCon(@RequestParam(value = "ids[]") String[] ids) {
		logger.debug("删除用户编号 vilIds：" + Arrays.asList(ids).toString());
		userService.deleteByIds(SUserEntity.class, true, getLoginUser().getUserName(), ids);
		
		String username = "";
		for (int i = 0; i < ids.length; i++) {
			//删除用户角色关联信息
			userRoleService.deleteByUserId(ids[i]);
			SUserEntity user = userService.get(SUserEntity.class, ids[i]);
			username += user.getUserName() + ",";
		}
		String msg = "删除用户：" + username;
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	/**
	 * 获取用户信息列表所有用户信息
	 * 
	 * @return
	 */
	@GetMapping("/getList")
	public List<SUserEntity> getAllList() {
		logger.debug("获取用户列表");
		List<SUserEntity> users = userService.findAllNoDelete(SUserEntity.class);
		return users;
	}

	/**
	 * 获取用户信息分页
	 * 
	 * @param page
	 *            当前页
	 * @param limit
	 *            每页显示记录数
	 * @param startTime
	 *            查询开始时间
	 * @param endTime
	 *            查询结束时间
	 * @param userName
	 *            用户名称
	 * @return JSONObject 返回用户分页对象，需要去掉Null转换为""
	 */
	@GetMapping("/getPage")
	public JSONObject getListByPageBean(@RequestParam Integer page, @RequestParam Integer limit,
			//@RequestParam String areaId,
			@RequestParam(required = false, defaultValue = "") String startTime,
			@RequestParam(required = false, defaultValue = "") String endTime,
			@RequestParam(required = false, defaultValue = "") String userName) {
		//BArea area=areaService.get(BArea.class, areaId);
		
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPageNumber(page);
		pageBean.setLimit(limit);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userName", userName);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		//param.put("area", area);
		pageBean.setMap(param);
		userService.getResult(pageBean);
		List<SUserEntity> userList=(List<SUserEntity>) pageBean.getData();
		List<SUserEntity> list=new ArrayList<>();
		for (SUserEntity user : userList) {
//			BArea barea=new BArea();
//			barea.setAreaCode(user.getAreaId());
//			barea.setIsdeleted(false);
//			List<BArea> areaList=areaService.findByExample(barea);	
//			if(!areaList.isEmpty()){
//			user.setAreaId(areaList.get(0).getAreaName());
//			}
			list.add(user);
		}
		pageBean.setData(list);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject returnObject = JSONObject.fromObject(pageBean,jsonConfig);
		returnObject = JsonCovert.filterNull(returnObject);
		return returnObject;
	}
	/**
	 * 获取用户信息分页
	 *
	 * @param page
	 *            当前页
	 * @param limit
	 *            每页显示记录数
	 * @param startTime
	 *            查询开始时间
	 * @param endTime
	 *            查询结束时间
	 * @param userName
	 *            用户名称
	 * @return JSONObject 返回用户分页对象，需要去掉Null转换为""
	 */
	@GetMapping("/getPage01")
	public JSONObject getListByPageBean01(@RequestParam Integer page, @RequestParam Integer limit,
			//@RequestParam String areaId,
			@RequestParam(required = false, defaultValue = "") String startTime,
			@RequestParam(required = false, defaultValue = "") String endTime,
			@RequestParam(required = false, defaultValue = "") String userName) {
		//SOrgIngo org=orgService.get(SOrgIngo.class, areaId);

		PageBean pageBean = new PageBean();
		pageBean.setCurrentPageNumber(page);
		pageBean.setLimit(limit);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userName", userName);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		//param.put("org", org);
		pageBean.setMap(param);
		userService.getResult01(pageBean);
		List<SUserEntity> userList=(List<SUserEntity>) pageBean.getData();
		List<SUserEntity> list=new ArrayList<>();
		for (SUserEntity user : userList) {
//			SOrgIngo barea=new SOrgIngo();
//			//barea.setOrgCode(user.getAreaId());
//			barea.setIsdeleted(false);
			//List<SOrgIngo> areaList=orgService.findByExample(barea);
//			if(!areaList.isEmpty()){
//			user.setAreaId(areaList.get(0).getOrgName());
//			}
		list.add(user);
		}
		pageBean.setData(list);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject returnObject = JSONObject.fromObject(pageBean,jsonConfig);
		returnObject = JsonCovert.filterNull(returnObject);
		return returnObject;
	}

}
