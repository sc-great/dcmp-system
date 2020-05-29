package com.great.manager.dao;


import java.util.List;

import com.great.base.dao.BaseDao;
import com.great.manager.entity.SMaskRecord;
import com.great.tool.PageBean;

public interface MaskRecordDao extends BaseDao {
	public void getResult(PageBean pageBean);

	public int countNotMaskToday(String chId);

	public List<SMaskRecord> findAlarmByTime(String format);

	public List<SMaskRecord> findAlarmByCount(int i);

	public List<SMaskRecord> findAlarmDescTime();
}
