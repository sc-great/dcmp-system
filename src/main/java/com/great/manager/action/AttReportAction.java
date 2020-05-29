package com.great.manager.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.great.base.action.BaseAction;
import com.great.base.entity.Message2Page2;
import com.great.manager.service.PersonService;
import com.great.manager.service.TemperatureRecordService;

import net.sf.json.JSONObject;

/**
 * 考勤统计
 * 
 * @author LM
 *
 */
@RestController
@RequestMapping(value = "/attReport")
public class AttReportAction extends BaseAction {

	@Autowired
	private PersonService personService;
	@Autowired
	private TemperatureRecordService temperatureRecordService;

	@PostMapping("/attReport")
	public Message2Page2 getAttendanceRecord(String orgId, String startTime, String endTime, String org) {
		JSONObject jsonObject = new JSONObject();

		return null;
	}
}
