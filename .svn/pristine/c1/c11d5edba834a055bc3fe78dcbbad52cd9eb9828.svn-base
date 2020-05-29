package com.great.manager.action;

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
import com.great.manager.entity.BLeave;
import com.great.manager.entity.BPerson;
import com.great.manager.service.LeaveService;
import com.great.manager.service.PersonnelFileInfoService;
import com.great.resource.StaticData;
import com.great.system.entity.SArea;
import com.great.system.entity.SAreaHost;
import com.great.system.entity.SDictionaryValEntity;
import com.great.system.entity.SMovLink;
import com.great.system.entity.SMovLinkVo;
import com.great.system.service.DictionaryService;
import com.great.system.service.SareaService;
import com.great.system.service.SmovLinkService;
import com.great.tool.JsonCovert;
import com.great.tool.PageBean;
import com.great.tool.UUID;

import net.sf.json.JSONObject;

/**
 * @author   请假休假管理
 *
 */
@RestController
@RequestMapping(value = "/leave")    
public class LeaveAction extends BaseAction {
	
	@Autowired
	private LeaveService leaveService;	
	
	@Autowired
	private PersonnelFileInfoService personnelFileInfoService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 获取初始化信息分页
	 * 
	 * @param name  vtg
	 * @return JSONObject 返回分页对象，需要去掉Null转换为""
	 */
	@GetMapping("/getPage")
	public JSONObject getListByPageBean(@RequestParam Integer page, @RequestParam Integer limit,					
			@RequestParam(required = false, defaultValue = "") String code,
			@RequestParam(required = false, defaultValue = "") String name,
			@RequestParam(required = false, defaultValue = "") String startTime,
			@RequestParam(required = false, defaultValue = "") String endTime
			) {
		 		
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
		param.put("code", code);	
		param.put("name", name);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		
		pageBean.setMap(param);
		leaveService.getResult(pageBean);
		JSONObject returnObject = JSONObject.fromObject(pageBean);
					
		returnObject = JsonCovert.filterNull(returnObject);
		//System.out.print("returnObject="+returnObject);
		return returnObject;
	}
	
	/**
	 *  删除
	 */
	@PostMapping("/del")
	public Message2Page deleteCon(@RequestParam(value = "ids[]") String[] ids) {
		String status=String.valueOf(StaticData.STATUS_UNUSE);
		leaveService.changeDelStatus(ids,status);
		String fqname = "";
		for (int i = 0; i < ids.length; i++) {
			BLeave leave = leaveService.get(BLeave.class, ids[i]);
			fqname += leave.getPerson().getPName() + ",";
		}
		StringBuffer msg = new StringBuffer();
		msg.append("删除").append("请假休假管理 ：").append(fqname);
		saveLogB(msg.toString(), StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	   
	}

	
	/**
	 * 添加页面中下拉员工姓名
	 */
	@PostMapping("/getPersonValue")
	public List<Map<String, String>> getPersonValue(@RequestParam String hostId) {
		List<BPerson> svs = leaveService.getPersonNameByAll();
		List<Map<String, String>> svsMaps = new ArrayList<Map<String, String>>();
		if (svs != null && svs.size() > 0) {
			for (BPerson sv : svs) {
				Map<String, String> svsMap = new HashMap<String, String>();
				svsMap.put("id", sv.getPId());
				svsMap.put("name", sv.getPName());
				svsMaps.add(svsMap);
			}
		}
		return svsMaps;
	}
	/**
	 * 添加页面中:下拉类型
	 */	
	@PostMapping("/getTypeValue")
	public List<Map<String, String>> getTypeValue(@RequestParam String typeCode) {
		List<SDictionaryValEntity> svs = leaveService.getDictValByTypeCodeId(typeCode);
		List<Map<String, String>> svsMaps = new ArrayList<Map<String, String>>();
		if (svs != null && svs.size() > 0) {
			for (SDictionaryValEntity sv : svs) {
				Map<String, String> svsMap = new HashMap<String, String>();
				svsMap.put("id", sv.getDvId());
				svsMap.put("name", sv.getDvName());
				svsMaps.add(svsMap);
			}
		}
		return svsMaps;
	}
	
	/**
	 * 添加
	 */
	@PostMapping("/add")
	public Message2Page add(BLeave leave) {
		//姓名+日期不能重复多条
		
		String personId = this.getPageParameter("personId");
		List<BLeave> areaList=leaveService.getBLeaveDuplicate(personId,leave.getLeaveDate());
		if(!CollectionUtils.isEmpty(areaList)){
			return new Message2Page(false, "500", "已存在");
		}
		
		leave.setId(UUID.randomUUID());		
		leave.setIsdeleted("0");
		leave.setCreateTime(new Date());
		
		if(personId!=null&&!personId.equals("")){
			BPerson person = personnelFileInfoService.get(BPerson.class, personId);;  
			leave.setPerson(person);
		}
        leave.setLeaveDate(leave.getLeaveDate());
        String typeId = this.getPageParameter("typeId"); //页面上对应类型
        if(typeId!=null&&!typeId.equals("")){
        	SDictionaryValEntity dicValue = dictionaryService.getDicValueById(typeId);  
			leave.setTypeVal(dicValue);
		}
        leave.setNote(leave.getNote());
        
		leaveService.save(leave);
		String msg = "添加请假换休管理：" + leave.getPerson().getPName();
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}
	
	/**
	 * 修改
	 * @return
	 */
	@PostMapping("/update")
	public Message2Page update(BLeave leave) {

		BLeave leaveSource = leaveService.get(BLeave.class, leave.getId());		 
		 String typeId = this.getPageParameter("typeId"); //页面上对应类型
	        if(typeId!=null&&!typeId.equals("")){
	        	SDictionaryValEntity dicValue = dictionaryService.getDicValueById(typeId);  
	        	leaveSource.setTypeVal(dicValue);
			}
	        leaveSource.setNote(leave.getNote());
	        leaveSource.setUpdateTime(new Date());
	        leaveService.update(leaveSource);
		String msg = "更新请假换休管理：" + leaveSource.getPerson().getPName();
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
		
	}
	
	
	/**
	 * 查看
	 * 
	 * @param id
	 *           
	 * @return
	 */
	@PostMapping("/getById")
	public Message2Page getById(@RequestParam String id) {
		logger.debug("接收到参数id：" + id);
		
		BLeave area = leaveService.get(BLeave.class, id);
		JSONObject jsonObject = JSONObject.fromObject(area);
		jsonObject = JsonCovert.filterNull(jsonObject);
		return new Message2Page(true, "200", "", jsonObject);
	
	
	}
	
		
	
}
