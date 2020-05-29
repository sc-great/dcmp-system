package com.great.system.action;

import java.util.HashMap;
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
import com.great.system.entity.SLogEntity;
import com.great.system.service.LogService;
import com.great.tool.JsonCovert;
import com.great.tool.PageBean;

/**
 * @author LUOCHENG
 *
 */
@RestController
@RequestMapping(value = "/log")
public class LogAction extends BaseAction {
	@Autowired
	private LogService logService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 参过日志编号获取日志信息
	 * 
	 * @param id
	 *            日志编号
	 * @return
	 */
	@PostMapping("/getById")
	public Message2Page getById(@RequestParam String id) {
		logger.debug("接收到参数id：" + id);
		SLogEntity log = logService.get(SLogEntity.class, id);
		JSONObject jsonObject = JSONObject.fromObject(log);
		jsonObject = JsonCovert.filterNull(jsonObject);
		return new Message2Page(true, "200", "", jsonObject);
	}



	/**
	 * 获取日志信息分页
	 * 
	 * @param page
	 *            当前页
	 * @param limit
	 *            每页显示记录数
	 * @param startTime
	 *            查询开始时间
	 * @param endTime
	 *            查询结束时间
	 * @param logType
	 *            日志类型
	 * @return JSONObject 返回用户分页对象，需要去掉Null转换为""
	 */
	@GetMapping("/getPage")
	public JSONObject getListByPageBean(@RequestParam Integer page, @RequestParam Integer limit,
			@RequestParam(required = false, defaultValue = "") String startTime,
			@RequestParam(required = false, defaultValue = "") String endTime,
			@RequestParam(required = false, defaultValue = "") String logType) {
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPageNumber(page);
		pageBean.setLimit(limit);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("logType", logType);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		pageBean.setMap(param);
		logService.getResult(pageBean);
		JSONObject returnObject = JSONObject.fromObject(pageBean);
		returnObject = JsonCovert.filterNull(returnObject);
		return returnObject;
	}

}
