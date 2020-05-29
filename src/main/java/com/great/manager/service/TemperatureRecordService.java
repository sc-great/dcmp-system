package com.great.manager.service;

import java.util.List;
import java.util.Map;

import com.great.base.service.BaseService;
import com.great.manager.entity.STemperatureRecord;
import com.great.tool.PageBean;

import net.sf.json.JSONObject;

public interface TemperatureRecordService extends BaseService<STemperatureRecord> {
	
	public void getResult(PageBean pageBean);

	public List<STemperatureRecord> findAlarmToday(String todayStr);

	public int countDetectionNum();

	public int countVisitorNum();

	public int countTempAlarmNum();

	public JSONObject getRecordByTimeLikeName(String userName, String startTime, String endTime,
			String org);

	public Map<String, Map<String, String>> getRecordByTimeLikeName2(Map<String, String> param);
}
