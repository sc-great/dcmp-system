package com.great.manager.dao;

import java.util.List;

import com.great.base.dao.BaseDao;
import com.great.manager.entity.CTacticsDate;

public interface TacticsDateDao extends BaseDao{

	List<CTacticsDate> getBytId(String gettId);

	void deleteByTId(String gettId);

	List<CTacticsDate> findByTdDate(String startTime, String endTime);

}
