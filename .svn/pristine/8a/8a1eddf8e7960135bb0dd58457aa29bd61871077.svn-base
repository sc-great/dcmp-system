package com.great.system.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.great.base.action.BaseAction;
import com.great.base.entity.Message2Page;
import com.great.resource.StaticData;
import com.great.system.entity.SAreaHost;
import com.great.system.service.SareaHostService;
import com.great.tool.JsonCovert;
import com.great.tool.PageBean;
import com.great.tool.UUID;

import net.sf.json.JSONObject;

/**
 * @author 周界防区管理
 *
 */
@RestController
@RequestMapping(value = "/sareaHost")    
public class SareaHostAction extends BaseAction {
	
	@Autowired
	private SareaHostService sareaHostService;	

		
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 批量启用/停用
	 * @param ids
	 * @param status
	 * @return
	 */
	@PostMapping("/changeStatus")
	public Message2Page changeStatus(@RequestParam(value = "ids[]") String[] ids, @RequestParam String status) {
		sareaHostService.changeStatus(ids, status);
		String fqname = "";
		for (int i = 0; i < ids.length; i++) {
			SAreaHost rhost = sareaHostService.get(SAreaHost.class, ids[i]);
			fqname += rhost.getCode() + ",";
		}
		StringBuffer msg = new StringBuffer();
		msg.append(status.equals("1") ? "停用" : "启用").append("周界主机编号 ：").append(fqname);
		saveLogB(msg.toString(), StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	/**
	 * 获取初始化信息分页
	 * 
	 * @param name  防区名称
	 * @return JSONObject 返回分页对象，需要去掉Null转换为""
	 */
	@GetMapping("/getPage")
	public JSONObject getListByPageBean(@RequestParam Integer page, @RequestParam Integer limit,					
			@RequestParam(required = false, defaultValue = "") String name,
			@RequestParam(required = false, defaultValue = "") String status) {
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPageNumber(page);
		pageBean.setLimit(limit);
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			name= new String(name.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		param.put("name", name);
		param.put("status", status);	
		pageBean.setMap(param);
		sareaHostService.getResult(pageBean);
		JSONObject returnObject = JSONObject.fromObject(pageBean);
					
		returnObject = JsonCovert.filterNull(returnObject);
		
		return returnObject;
	}
	
	/**
	 *  删除防区
	 */
	@PostMapping("/del")
	public Message2Page deleteCon(@RequestParam(value = "ids[]") String[] ids) {
		String status=String.valueOf(StaticData.STATUS_UNUSE);
		sareaHostService.changeDelStatus(ids,status);
		String fqname = "";
		for (int i = 0; i < ids.length; i++) {
			SAreaHost rhost = sareaHostService.get(SAreaHost.class, ids[i]);
			fqname += rhost.getCode() + ",";
		}
		StringBuffer msg = new StringBuffer();
		msg.append("删除").append("周界主机编号 ：").append(fqname);
		saveLogB(msg.toString(), StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	   
	}
	
	/**
	 * 查看单条防区信息
	 * 
	 * @param id
	 *            防区编号
	 * @return
	 */
	@PostMapping("/getById")
	public Message2Page getById(@RequestParam String id) {
		logger.debug("接收到参数id：" + id);
		SAreaHost area = sareaHostService.get(SAreaHost.class, id);
		JSONObject jsonObject = JSONObject.fromObject(area);
		jsonObject = JsonCovert.filterNull(jsonObject);
		return new Message2Page(true, "200", "", jsonObject);
	}

	
	
	/**
	 * 添加
	 */
	@PostMapping("/add")
	public Message2Page add(SAreaHost areaHost) {
				
		List<SAreaHost> areaList1=sareaHostService.getSreaHostCode(areaHost.getCode());
		if(!CollectionUtils.isEmpty(areaList1)){
			return new Message2Page(false, "500", "主机编号已存在");
		}
		
		List<SAreaHost> areaList=sareaHostService.getSreaHostName(areaHost.getName());
		if(!CollectionUtils.isEmpty(areaList)){
			return new Message2Page(false, "500", "主机名称已存在");
		}
		areaHost.setHostId(UUID.randomUUID());		
		areaHost.setIsdeleted("0");
		areaHost.setCreateTime(new Date());
	    sareaHostService.save(areaHost);
		String msg = "添加周界主机编号：" + areaHost.getCode();
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}
	/**
	 * 修改
	 * @return
	 */
	@PostMapping("/update")
	public Message2Page update(SAreaHost areaHost) {

		SAreaHost areaHostSource = sareaHostService.get(SAreaHost.class, areaHost.getHostId());
		areaHostSource.setName(areaHost.getName()); 
		areaHostSource.setCode(areaHost.getCode());
		areaHostSource.setIpAddr(areaHost.getIpAddr());
		areaHostSource.setPort(areaHost.getPort());
	
		sareaHostService.update(areaHostSource);
		String msg = "更新周界防区主机：" + areaHostSource.getName()+",编号："+areaHostSource.getCode();
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
		
	}
}
