package com.great.manager.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.base.service.impl.BaseServiceImpl;
import com.great.manager.dao.TacticsDao;
import com.great.manager.entity.CTactics;
import com.great.manager.service.TacticsService;
import com.great.tool.PageBean;

@Service
public class TacticsServiceImpl extends BaseServiceImpl<CTactics> implements TacticsService{

	@Autowired
	private TacticsDao tacticsDao;
	
	
	@Override
	public void getResult(PageBean pageBean) {
		tacticsDao.getResult(pageBean);
	}

	
	@Override
	public void changeStatus(String[] ids, String status) {
		this.tacticsDao.changeStatus(ids,status);
	}


	@Override
	public CTactics getByName(String name) {
		return tacticsDao.getByName(name);
	}


}
