package com.great.manager.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
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
import com.great.manager.entity.BCampusHierarchy;
import com.great.manager.service.CampusOrgService;
import com.great.resource.StaticData;
import com.great.system.service.DictionaryService;
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
@RequestMapping(value = "/campusOrg")
public class CampusOrgAction extends BaseAction {
	@Autowired
	private CampusOrgService orgService;
	@Autowired
	private DictionaryService dicService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 组织机构信息添加方法
	 * 
	 * @param org
	 *            对象参数接收前端提交属性
	 * @return 返回 Message2Page 封装对象
	 */
	@PostMapping("/add")
	public Message2Page add(BCampusHierarchy org) {
		org.setChId(UUID.randomUUID());
		org.setIsdeleted(false);
		org.setStatus(StaticData.STATUS_NORMAL);
		org.setCreateTime(new Date());
		org.setCreateBy(this.getLoginUser().getUserName());
		String parentId = this.getPageParameter("parentId");
		if(parentId!=null&&!parentId.equals("")){
			BCampusHierarchy parentOrg = this.orgService.get(BCampusHierarchy.class, parentId);
			org.setParentOrg(parentOrg);
		}
//		String chType = this.getPageParameter("chType");
//		if(chType!=null&&!chType.equals("")){
//			SDictionaryValEntity dvEntity = this.dicService.getDicValueByValue(chType);
//			org.setDvEntity(dvEntity);
//		}
		// 过滤string类型的字段内的特殊字符
		ReflectCommon<BCampusHierarchy> common = new ReflectCommon<BCampusHierarchy>();
		Map<String, Object> map = common.test(org);

		org = (BCampusHierarchy) ReplaceUtil.replaceSpecString(map, org);
		
		orgService.save(org);
		String msg = "添加组织机构：" + org.getChName();
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	
	

	/**
	 * 组织机构状态修改方法
	 * 
	 * @param ids
	 *            机构编号
	 * @param chStatus
	 *            机构状态
	 * @return
	 */
	@PostMapping("/changeStatus")
	public Message2Page changeStatus(@RequestParam(value = "ids[]") String[] ids, @RequestParam String chStatus) {
		orgService.changeStatus(ids, chStatus);
		String orgname = "";
		for (int i = 0; i < ids.length; i++) {
			BCampusHierarchy org = orgService.get(BCampusHierarchy.class, ids[i]);
			orgname +=org.getChName() + ",";
		}
		StringBuffer msg = new StringBuffer();
		msg.append(chStatus.equals("1") ? "停用" : "启用").append("组织机构：").append(orgname);
		saveLogB(msg.toString(), StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	/**
	 * 机构修改方法
	 * 
	 * @param org
	 *            前端传回来的组织机构修改信息
	 * @return
	 */
	@PostMapping("/update")
	public Message2Page update(BCampusHierarchy org) {
		BCampusHierarchy orgSource = orgService.get(BCampusHierarchy.class, org.getChId());
		// 复制需要修改的机构信息给原来的机构对象
		this.mergeObject(org, orgSource);
		orgSource.setUpdateBy(this.getLoginUser().getUserName());
		orgSource.setUpdateTime(new Date());
//		String chType = this.getPageParameter("chType");
//		if(chType!=null&&!chType.equals("")){
//			SDictionaryValEntity dvEntity=dicService.getDicValueByValue(chType);
//			orgSource.setDvEntity(dvEntity);
//		}
		
		// 过滤string类型的字段内的特殊字符
		ReflectCommon<BCampusHierarchy> common = new ReflectCommon<BCampusHierarchy>();
		Map<String, Object> map = common.test(orgSource);

		orgSource = (BCampusHierarchy) ReplaceUtil.replaceSpecString(map, orgSource);
		
		orgService.update(orgSource);
		String msg = "更新组织机构：" + org.getChName();
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}
	@PostMapping("/getOrgTree")
	public List<Map> getOrgTree() {
		return orgService.getOrgTree();
	}
	
	/**
	 * 参过机构编号获取区域信息
	 * 
	 * @param id
	 *            机构编号
	 * @return
	 */
	@PostMapping("/getById")
	public Message2Page getById(@RequestParam String id) {
		logger.debug("接收到参数id：" + id);
		BCampusHierarchy org = orgService.get(BCampusHierarchy.class, id);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject jsonObject = JSONObject.fromObject(org,jsonConfig);
		jsonObject = JsonCovert.filterNull(jsonObject);
		return new Message2Page(true, "200", "", jsonObject);
	}

	/**
	 * 删除机构信息、逻辑删除
	 * 
	 * @param ids
	 *            需要删除的机构编号数组
	 * @return
	 */
	@PostMapping("/del")
	public Message2Page deleteCon(@RequestParam(value = "ids[]") String[] ids) {
		logger.debug("删除组织机构编号 vilIds：" + Arrays.asList(ids).toString());
		orgService.deleteByIds(BCampusHierarchy.class, true,getLoginUser().getUserName(), ids);
		String orgname = "";
		for (int i = 0; i < ids.length; i++) {
			BCampusHierarchy org = orgService.get(BCampusHierarchy.class, ids[i]);
			orgname += org.getChName() + ",";
		}
		String msg = "删除组织机构：" + orgname;
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	/**
	 * 获取组织机构信息列表所有区域信息
	 * 
	 * @return
	 */
	@PostMapping("/getOrgList")
	public List<Map<String, String>> getAllList() {
		logger.debug("获取组织机构列表"); 
		List<BCampusHierarchy> orgs = orgService.findAllNoDelete(BCampusHierarchy.class);
		List<Map<String, String>> maps=new ArrayList<Map<String,String>>();
		if(orgs!=null&&orgs.size()>0){
			for(BCampusHierarchy org:orgs){
				Map<String, String> map=new HashMap<String, String>();
				map.put("id", org.getChId());
				map.put("name", org.getChName());
				maps.add(map);
			}
		}
		return maps;
	}

	/**
	 * 获取组织机构信息分页
	 * 
	 * @param page
	 *            当前页
	 * @param limit
	 *            每页显示记录数
	 * @param startTime
	 *            查询开始时间
	 * @param endTime
	 *            查询结束时间
	 * @param chName
	 *            机构名称
	 * @return JSONObject 返回机构分页对象，需要去掉Null转换为""
	 */
	@GetMapping("/getPage")
	public JSONObject getListByPageBean(@RequestParam Integer page, @RequestParam Integer limit,
			@RequestParam(required = false, defaultValue = "") String startTime,
			@RequestParam(required = false, defaultValue = "") String endTime,
			@RequestParam(required = false, defaultValue = "") String chName) {
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPageNumber(page);
		pageBean.setLimit(limit);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("chName", chName);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		pageBean.setMap(param);
		orgService.getResult(pageBean);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject returnObject = JSONObject.fromObject(pageBean,jsonConfig);
		returnObject = JsonCovert.filterNull(returnObject);
		return returnObject;
	}

}
