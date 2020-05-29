package com.great.manager.service;

import java.util.List;

import com.great.base.service.BaseService;
import com.great.manager.entity.SMaskRecord;
import com.great.tool.PageBean;

public interface MaskRecordService extends BaseService<SMaskRecord> {
	
	public void getResult(PageBean pageBean);

	public int countNotMaskToday(String chId);

	public List<SMaskRecord> findAlarmByTime(String format);

	public List<SMaskRecord> findAlarmByCount(int i);

	public List<SMaskRecord> findAlarmDescTime();
}
