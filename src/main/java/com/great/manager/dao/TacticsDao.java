package com.great.manager.dao;

import com.great.base.dao.BaseDao;
import com.great.manager.entity.CTactics;
import com.great.tool.PageBean;

public interface TacticsDao extends BaseDao{
	void getResult(PageBean pageBean);

	void changeStatus(String[] ids, String status);

	CTactics getByName(String name);

}
