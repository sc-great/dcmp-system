package com.great.manager.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.great.base.action.BaseAction;
import com.great.manager.entity.STemperatureRecord;
import com.great.manager.service.TemperatureRecordService;
import com.great.tool.JsonCovert;
import com.great.tool.PageBean;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * 体温监测记录
 * 
 * @author LM
 *
 */
@RestController
@RequestMapping(value = "/temperatureRecord")
public class TemperatureRecordAction extends BaseAction {
	@Autowired
	private TemperatureRecordService temperatureRecordService;

	@GetMapping("/getRecord")
	public JSONObject getRecordByPageBeanAll(@RequestParam Integer page, 
			@RequestParam Integer limit,
			@RequestParam(required = false, defaultValue = "") String startTime,
			@RequestParam(required = false, defaultValue = "") String endTime,
			@RequestParam(required = false, defaultValue = "") String code,
			@RequestParam(required = false, defaultValue = "") String userName) {

		return getRecordByPageBean(0, page, limit, startTime, endTime, userName,code);
	}
	
	@GetMapping("/getRecordAlarm")
	public JSONObject getRecordByPageBeanAlarm(@RequestParam Integer page, 
			@RequestParam Integer limit,
			@RequestParam(required = false, defaultValue = "") String startTime,
			@RequestParam(required = false, defaultValue = "") String endTime,
			@RequestParam(required = false, defaultValue = "") String code,
			@RequestParam(required = false, defaultValue = "") String userName) {

		return getRecordByPageBean(1, page, limit, startTime, endTime, userName,code);
	}
	
	/**
	 * 获取体温记录分页
	 *
	 * @param page      当前页
	 * @param limit     每页显示记录数
	 * @param startTime 查询开始时间
	 * @param endTime   查询结束时间
	 * @param userName  用户名称
	 * @return JSONObject 返回用户分页对象，需要去掉Null转换为""
	 */
	public JSONObject getRecordByPageBean(int isAlarm, Integer page, Integer limit, String startTime, String endTime, String userName, String code) {

		PageBean pageBean = new PageBean();
		pageBean.setCurrentPageNumber(page);
		pageBean.setLimit(limit);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("isAlarm", isAlarm);
		param.put("userName", userName);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("clientId", code);
		pageBean.setMap(param);
		temperatureRecordService.getResult(pageBean);
		List<STemperatureRecord> recordList = (List<STemperatureRecord>) pageBean.getData();
		pageBean.setData(recordList);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject returnObject = JSONObject.fromObject(pageBean, jsonConfig);
		returnObject = JsonCovert.filterNull(returnObject);
		return returnObject;
	}
}
