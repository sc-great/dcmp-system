package com.great.manager.dao;

import java.util.List;

import com.great.base.dao.BaseDao;
import com.great.manager.entity.STemperatureRecord;

public interface AttendanceRecordDao extends BaseDao {

	List<STemperatureRecord> getRecordByTimeLikeName(String userName, String startTime, String endTime, String org);
}
