package com.great.manager.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.base.service.impl.BaseServiceImpl;
import com.great.manager.dao.AttendanceRecordDao;
import com.great.manager.dao.TemperatureRecordDao;
import com.great.manager.entity.STemperatureRecord;
import com.great.manager.service.TemperatureRecordService;
import com.great.tool.PageBean;

import net.sf.json.JSONObject;

@Service
public class TemperatureRecordServiceImpl extends BaseServiceImpl<STemperatureRecord> implements TemperatureRecordService {
	
	@Autowired
	private TemperatureRecordDao temperatureRecordDao;
	
	@Autowired
	private AttendanceRecordDao attendanceRecordDao;

	public void getResult(PageBean pageBean) {
		this.temperatureRecordDao.getResult(pageBean);
	}

	@Override
	public List<STemperatureRecord> findAlarmToday(String todayStr) {
		return this.temperatureRecordDao.findAlarmToday(todayStr);
	}

	@Override
	public int countDetectionNum() {
		return this.temperatureRecordDao.countDetectionNum();
	}

	@Override
	public int countVisitorNum() {
		return this.temperatureRecordDao.countVisitorNum();
	}

	@Override
	public int countTempAlarmNum() {
		return this.temperatureRecordDao.countTempAlarmNum();
	}

	@Override
	public JSONObject getRecordByTimeLikeName(String userName, String startTime, String endTime,
			String org) {
		return this.temperatureRecordDao.getRecordByTimeLikeName(userName, startTime, endTime, org);
	}

	@Override
	public Map<String, Map<String, String>> getRecordByTimeLikeName2(Map<String, String> param) {
		return this.temperatureRecordDao.getRecordByTimeLikeName2(param);
	}
}
