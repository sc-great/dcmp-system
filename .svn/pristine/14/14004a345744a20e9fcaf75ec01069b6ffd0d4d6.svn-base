package com.great.manager.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.base.service.impl.BaseServiceImpl;
import com.great.manager.dao.AttendanceTimeSetupDao;
import com.great.manager.entity.BAttendanceTimeSetup;
import com.great.manager.service.AttendanceTimeSetupService;


/**
 * @author zqq
 * 设置出勤时间
 */
@Service
public class AttendanceTimeSetupServiceImpl extends BaseServiceImpl<BAttendanceTimeSetup> implements AttendanceTimeSetupService {

	@Autowired
	private AttendanceTimeSetupDao attendanceTimeSetupDao;
	
	//详细信息
	@Override
	public BAttendanceTimeSetup getAttendanceValueById(String aId) {
		// TODO Auto-generated method stub
		return attendanceTimeSetupDao.getAttendanceValueById(aId);
	}
}
