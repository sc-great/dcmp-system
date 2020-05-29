package com.great.system.action;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.great.base.action.BaseAction;
import com.great.base.entity.Message2Page;
import com.great.resource.StaticData;
import com.great.system.entity.SDictionaryValEntity;
import com.great.system.entity.SMenuEntity;
import com.great.system.service.DictionaryService;
import com.great.system.service.MenuService;
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
@RequestMapping(value = "/menu")
public class MenuAction extends BaseAction {
	@Autowired
	private MenuService menuService;
	@Autowired
	private DictionaryService dicService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 用户信息添加方法
	 * 
	 * @param user
	 *            对象参数接收前端提交属性
	 * @return 返回 Message2Page 封装对象
	 */
	@PostMapping("/add")
	public Message2Page add(SMenuEntity menu) {
//		String pid = this.getPageParameter("parentId");
//		SMenuEntity m=menuService.get(SMenuEntity.class,pid);
//		if(m.getParentMenu()!=null){
//			return new Message2Page(false, "200", "暂不支持三级菜单");
//		}
		menu.setMenuId(UUID.randomUUID());
		menu.setIsdeleted(false);
		menu.setStatus(StaticData.STATUS_NORMAL);
		menu.setCreateTime(new Date());
		menu.setCreateBy(this.getLoginUser().getUserName());
		String parentId = this.getPageParameter("parentId");
		if(parentId!=null&&!parentId.equals("")){
			SMenuEntity parent = this.menuService.get(SMenuEntity.class, parentId);
			menu.setParentMenu(parent);
			menu.setExtText(parentId);
		}
		// 过滤string类型的字段内的特殊字符
		ReflectCommon<SMenuEntity> common = new ReflectCommon<SMenuEntity>();
		Map<String, Object> map = common.test(menu);
		menu = (SMenuEntity) ReplaceUtil.replaceSpecString(map, menu);
		
		
		menuService.save(menu);
		String msg = "添加菜单：" + menu.getMenuName();
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	/**
	 * 菜单状态修改方法
	 * 
	 * @param ids
	 *            菜单编号
	 * @param status
	 *            菜单状态
	 * @return
	 */
	@PostMapping("/changeStatus")
	public Message2Page changeStatus(@RequestParam(value = "ids[]") String[] ids, @RequestParam String status) {
		//有下级菜单是不能停用
		if("1".equals(status)){
		String name="";
		for (String id : ids) {
			List<SMenuEntity> list=menuService.getListByParentIdAndUserId(id,this.getLoginUser().getUserId());
			if(!list.isEmpty()){
				SMenuEntity m=menuService.get(SMenuEntity.class, id);
				name+=m.getMenuName()+",";
			}
		}
		if(!"".equals(name)){
			return new Message2Page(false,"200",name+"还存在下级菜单,不能停用");
		}
		}
		menuService.changeStatus(ids, status);
		String menuname = "";
		for (int i = 0; i < ids.length; i++) {
			SMenuEntity menu = menuService.get(SMenuEntity.class, ids[i]);
			menuname += menu.getMenuName() + ",";
		}
		StringBuffer msg = new StringBuffer();
		msg.append(status.equals("1")? "停用" : "启用").append("菜单：").append(menuname);
		saveLogB(msg.toString(), StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	/**
	 * 菜单修改方法
	 * 
	 * @param menu
	 *            前端传回来的用户修改信息
	 * @return
	 */
	@PostMapping("/update")
	public Message2Page update(SMenuEntity menu) {
		SMenuEntity menuSource = menuService.get(SMenuEntity.class, menu.getMenuId());
		// 复制需要修改的用户信息给原来的用户对象
		this.mergeObject(menu, menuSource);
		menuSource.setUpdateBy(this.getLoginUser().getUserName());
		menuSource.setUpdateTime(new Date());
		String parentId = this.getPageParameter("parentId");
		if(parentId!=null&&!parentId.equals("")){
			SMenuEntity parent = this.menuService.get(SMenuEntity.class, parentId);
			menuSource.setParentMenu(parent);
			menuSource.setExtText(parentId);
		}
		menuSource.setUpdateBy(this.getLoginUser().getUserName());
		menuSource.setUpdateTime(new Date());
		
		// 过滤string类型的字段内的特殊字符
		ReflectCommon<SMenuEntity> common = new ReflectCommon<SMenuEntity>();
		Map<String, Object> map = common.test(menuSource);
		menuSource = (SMenuEntity) ReplaceUtil.replaceSpecString(map, menuSource);
		
		menuService.update(menuSource);
		String msg = "更新菜单：" + menu.getMenuName(); 
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	/**
	 * 参过菜单编号获取菜单信息
	 * 
	 * @param id
	 *            菜单编号
	 * @return
	 */
	@PostMapping("/getById")
	public Message2Page getById(@RequestParam String id) {
		logger.debug("接收到参数id：" + id);
		SMenuEntity menu = menuService.get(SMenuEntity.class, id);
		JSONObject jsonObject = JSONObject.fromObject(menu);
		jsonObject = JsonCovert.filterNull(jsonObject);
		return new Message2Page(true, "200", "", jsonObject);
	}

	/**
	 * 删除菜单信息、逻辑删除
	 * 
	 * @param ids
	 *            需要删除的菜单编号数组
	 * @return
	 */
	@PostMapping("/del")
	public Message2Page deleteCon(@RequestParam(value = "ids[]") String[] ids) {
		//有下级菜单是不能删除
		String name="";
		for (String id : ids) {
			List<SMenuEntity> list=menuService.getListByParentIdAndUserId(id,this.getLoginUser().getUserId());
			if(!list.isEmpty()){
				SMenuEntity m=menuService.get(SMenuEntity.class, id);
				name+=m.getMenuName()+",";
			}
		}
		if(!"".equals(name)){
			return new Message2Page(false,"200",name+"还存在下级菜单,不能删除");
		}
		
		logger.debug("删除菜单编号 Ids：" + Arrays.asList(ids).toString());
		menuService.deleteByIds(SMenuEntity.class, true, getLoginUser().getUserName(), ids);
		String menuname = "";
		for (int i = 0; i < ids.length; i++) {
			SMenuEntity menu = menuService.get(SMenuEntity.class, ids[i]);
			menuname += menu.getMenuName() + ",";
		}
		String msg = "删除菜单：" + menuname;
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	/**
	 * 获取菜单信息列表所有菜单信息
	 * 
	 * @return
	 */
	@PostMapping("/getList")
	public List<Map<String,String>> getAllList() {
//		List<SMenuEntity> menus = menuService.getParentMenu();
		List<SMenuEntity> menus=menuService.findAllNoDelete(SMenuEntity.class);
		List<Map<String,String>> selectMaps =  new ArrayList<Map<String,String>>();
		if(menus!=null&&menus.size()>0){
			for(SMenuEntity menu :menus){
				Map<String,String> selectMap = new HashMap<String, String>();
				selectMap.put("id", menu.getMenuId());
				selectMap.put("name", menu.getMenuName());
				selectMaps.add(selectMap);
			}
		}
		return selectMaps;
	}

	/**
	 * 获取菜单信息分页
	 * 
	 * @param page
	 *            当前页
	 * @param limit
	 *            每页显示记录数
	 * @param startTime
	 *            查询开始时间
	 * @param endTime
	 *            查询结束时间
	 * @param menuName
	 *            菜单名称
	 * @return JSONObject 返回菜单分页对象，需要去掉Null转换为""
	 */
	@GetMapping("/getPage")
	public JSONObject getListByPageBean(@RequestParam Integer page, @RequestParam Integer limit,
			@RequestParam(required = false, defaultValue = "") String startTime,
			@RequestParam(required = false, defaultValue = "") String endTime,
			@RequestParam(required = false, defaultValue = "") String menuName) {
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPageNumber(page);
		pageBean.setLimit(limit);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuName", menuName);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		pageBean.setMap(param);
		menuService.getResult(pageBean);
		JSONObject returnObject = JSONObject.fromObject(pageBean);
		returnObject = JsonCovert.filterNull(returnObject);
		return returnObject;
	}

}
