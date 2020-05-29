package com.great.manager.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.base.service.impl.BaseServiceImpl;
import com.great.manager.dao.MaskRecordDao;
import com.great.manager.entity.SMaskRecord;
import com.great.manager.service.MaskRecordService;
import com.great.tool.PageBean;

@Service
public class MaskRecordServiceImpl extends BaseServiceImpl<SMaskRecord> implements MaskRecordService {
	
	@Autowired
	private MaskRecordDao maskRecordDao;

	public void getResult(PageBean pageBean) {
		this.maskRecordDao.getResult(pageBean);
	}

	@Override
	public int countNotMaskToday(String chId) {
		return this.maskRecordDao.countNotMaskToday(chId);
	}

	@Override
	public List<SMaskRecord> findAlarmByTime(String format) {
		return this.maskRecordDao.findAlarmByTime(format);
	}

	@Override
	public List<SMaskRecord> findAlarmByCount(int i) {
		return this.maskRecordDao.findAlarmByCount(i);
	}

	@Override
	public List<SMaskRecord> findAlarmDescTime() {
		return this.maskRecordDao.findAlarmDescTime();
	}
}
