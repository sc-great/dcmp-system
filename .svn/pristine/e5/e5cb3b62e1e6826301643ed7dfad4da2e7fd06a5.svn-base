package com.great.system.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.great.base.action.BaseAction;
import com.great.base.entity.Message2Page;
import com.great.resource.StaticData;
import com.great.system.entity.SArea;
import com.great.system.entity.SAreaHost;
import com.great.system.entity.SMovLink;
import com.great.system.entity.SMovLinkVo;
import com.great.system.service.SareaService;
import com.great.system.service.SmovLinkService;
import com.great.tool.JsonCovert;
import com.great.tool.PageBean;
import com.great.tool.UUID;

import net.sf.json.JSONObject;

/**
 * @author 周界防区管理
 *
 */
@RestController
@RequestMapping(value = "/sarea")    
public class SareaAction extends BaseAction {
	
	@Autowired
	private SareaService sareaService;	
	
	@Autowired
	private SmovLinkService smovlinkService;	

		
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 批量启用/停用
	 * @param ids
	 * @param status
	 * @return
	 */
	@PostMapping("/changeStatus")
	public Message2Page changeStatus(@RequestParam(value = "ids[]") String[] ids, @RequestParam String status) {
		sareaService.changeStatus(ids, status);
		String fqname = "";
		for (int i = 0; i < ids.length; i++) {
			SArea name = sareaService.get(SArea.class, ids[i]);
			fqname += name.getName() + ",";
		}
		StringBuffer msg = new StringBuffer();
		msg.append(status.equals("1") ? "停用" : "启用").append("周界防区 ：").append(fqname);
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
		sareaService.getResult(pageBean);
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
		sareaService.changeDelStatus(ids,status);
		String fqname = "";
		for (int i = 0; i < ids.length; i++) {
			SArea name = sareaService.get(SArea.class, ids[i]);
			fqname += name.getName() + ",";
		}
		StringBuffer msg = new StringBuffer();
		msg.append("删除").append("周界防区 ：").append(fqname);
		saveLogB(msg.toString(), StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	   
	}

	
	/**
	 * 添加页面中的主机编号下拉列内容	
	 */
	@PostMapping("/getHostValue")
	public List<Map<String, String>> getDicValue(@RequestParam String hostId) {
		List<SAreaHost> svs = sareaService.getSareaHostByAll();
		List<Map<String, String>> svsMaps = new ArrayList<Map<String, String>>();
		if (svs != null && svs.size() > 0) {
			for (SAreaHost sv : svs) {
				Map<String, String> svsMap = new HashMap<String, String>();
				svsMap.put("id", sv.getHostId());
				svsMap.put("name", sv.getName());
				svsMaps.add(svsMap);
			}
		}
		return svsMaps;
	}
	
	/**
	 * 添加
	 */
	@PostMapping("/add")
	public Message2Page add(SArea area) {
		//防区名称不能有重名
		List<SArea> areaList=sareaService.getSAreaLoginName(area.getName());
		if(!CollectionUtils.isEmpty(areaList)){
			return new Message2Page(false, "500", "防区名称已存在");
		}
		
		area.setAreaId(UUID.randomUUID());		
	    area.setIsdeleted("0");
	    area.setCreateTime(new Date());
		String hostSelectId = this.getPageParameter("hostSelectId");
		if(hostSelectId!=null&&!hostSelectId.equals("")){
			SAreaHost host = sareaService.getSareaHost(hostSelectId);  
			area.setHost(host);
		}

		sareaService.save(area);
		String msg = "添加周界防区：" + area.getName();
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}
	/**
	 * 修改
	 * @return
	 */
	@PostMapping("/update")
	public Message2Page update(SArea area) {


		SArea areaSource = sareaService.get(SArea.class, area.getAreaId());
		areaSource.setName(area.getName()); 
		String hostSelectId = this.getPageParameter("hostSelectId");
		if(hostSelectId!=null&&!hostSelectId.equals("")){
			SAreaHost host = sareaService.getSareaHost(hostSelectId);  
			areaSource.setHost(host);
		}
	
		sareaService.update(areaSource);
		String msg = "更新周界防区：" + area.getName();
		saveLogB(msg, StaticData.LOG_TYPE_DO);
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
		
		SArea area = sareaService.get(SArea.class, id);
		JSONObject jsonObject = JSONObject.fromObject(area);
		jsonObject = JsonCovert.filterNull(jsonObject);
		return new Message2Page(true, "200", "", jsonObject);
	
	
	}
	/**
	 * 如果已存视频联动信息就取出显示
	 * 
	 * @param id
	 *            防区编号
	 * @return
	 */
	@PostMapping("/getById2")
	public Message2Page getById2(@RequestParam String id) {
		logger.debug("接收到参数id：" + id);

		
		SMovLinkVo	returnValue = sareaService.getSMovLinkByAreaId(id);			
		JSONObject jsonObject = JSONObject.fromObject(returnValue);
		jsonObject = JsonCovert.filterNull(jsonObject);
		//System.out.println("jsonObject="+jsonObject);
		return new Message2Page(true, "200", "", jsonObject);
		
	}

	/**
	 * 增加和修改视频联动
	 */
	
	@PostMapping("/update2")
	public Message2Page update2(SMovLinkVo mov) {
		     	
		//如果修改状态				
		if(mov.getId()!=null&&mov.getId()!=""){
			SMovLink entity1 = smovlinkService.get(SMovLink.class, mov.getId());
			entity1.setAreaId(mov.getAreaId());
			entity1.setID(mov.getId());
			entity1.setFirm(mov.getFirm());
			entity1.setIpAddr(mov.getIpAddr());
			entity1.setMovName(mov.getMovName());
			entity1.setNote(mov.getNote());
			entity1.setPort(mov.getPort());
			entity1.setUpdateTime(new Date());	 
			entity1.setUserName(mov.getUserName());
			entity1.setPassWord(mov.getPassWord());
			sareaService.update(entity1);
			
		}
		if(mov.getId2()!=null&&mov.getId2()!=""){
			SMovLink entity2 = smovlinkService.get(SMovLink.class, mov.getId2());
			entity2.setID(mov.getId2());
			entity2.setAreaId(mov.getAreaId2());
			entity2.setFirm(mov.getFirm2());
			entity2.setIpAddr(mov.getIpAddr2());
			entity2.setMovName(mov.getMovName2());
			entity2.setNote(mov.getNote2());
			entity2.setPort(mov.getPort2());
			entity2.setUpdateTime(new Date());	 
			entity2.setUserName(mov.getUserName2());
			entity2.setPassWord(mov.getPassWord2());
			sareaService.update(entity2);
			
		}
		
		 //如果新增状态（可增一条,也可增二条）
		if ((mov.getId()==null||"".equals(mov.getId()))&& (!"".equals(mov.getMovName())&&mov.getMovName()!=null)) {  
			SMovLink  addEntity1=new SMovLink();
			addEntity1.setIsdeleted("0");
			addEntity1.setCreateTime(new Date());
			
			addEntity1.setID(UUID.randomUUID());
			addEntity1.setMovName(mov.getMovName());
			addEntity1.setAreaId(mov.getAreaId());
			addEntity1.setFirm(mov.getFirm());
			addEntity1.setIpAddr(mov.getIpAddr());
			addEntity1.setNote(mov.getNote());
			addEntity1.setPort(mov.getPort());
			addEntity1.setUserName(mov.getUserName());
			addEntity1.setPassWord(mov.getPassWord());
			
			sareaService.save(addEntity1);
		}
	    if((mov.getId2()==null||"".equals(mov.getId2()))&& (!"".equals(mov.getMovName2())&&mov.getMovName2()!=null)){
	    	SMovLink  addEntity2=new SMovLink();
	    	addEntity2.setIsdeleted("0");
			addEntity2.setCreateTime(new Date());
			
			addEntity2.setID(UUID.randomUUID());
			addEntity2.setMovName(mov.getMovName2());
			addEntity2.setAreaId(mov.getAreaId2());
			addEntity2.setFirm(mov.getFirm2());
			addEntity2.setIpAddr(mov.getIpAddr2());
			addEntity2.setNote(mov.getNote2());
			addEntity2.setPort(mov.getPort2());		
			addEntity2.setUserName(mov.getUserName2());
			addEntity2.setPassWord(mov.getPassWord2());
			
	    	sareaService.save(addEntity2);
		}
		
		String msg ="更新视频联动：";
		if(mov.getMovName()!=null&&mov.getMovName()!=""){
			msg=msg+ mov.getMovName();
		}
		if(mov.getMovName2()!=null&&mov.getMovName2()!=""){
			msg=msg+","+mov.getMovName2();
		}
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
		
	}
		
	
}
