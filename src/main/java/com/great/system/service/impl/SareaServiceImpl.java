package com.great.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.base.service.impl.BaseServiceImpl;
import com.great.system.dao.SareaDao;
import com.great.system.entity.SArea;
import com.great.system.entity.SAreaHost;
import com.great.system.entity.SMovLinkVo;
import com.great.system.service.SareaService;
import com.great.tool.PageBean;

@Service
public class SareaServiceImpl extends BaseServiceImpl<SArea> implements SareaService {

	
	@Autowired
	private SareaDao sareaDao;
	
	@Override
	public void getResult(PageBean pageBean) {
		this.sareaDao.getResult(pageBean);
	}


	public List<SArea> getSelectList(){
		return this.sareaDao.getSelectList();
	}
	
	@Override
	public void changeStatus(String[] ids, String status) {
		this.sareaDao.changeStatus(ids, status);
	}
	@Override
	public void changeDelStatus(String[] ids, String status) {
		this.sareaDao.changeDelStatus(ids, status);
	}
	
	@Override
	public List<SAreaHost> getSareaHostByAll(){
		return this.sareaDao.getSareaHostByAll();
	}
	
	public SAreaHost getSareaHost(String hostSelectId){		
	  return 	sareaDao.getSareaHost(hostSelectId);
	}
	
	public List<SArea>  getSAreaLoginName(String name){		
		return sareaDao.getSAreaLoginName(name);
	}
	
	public SMovLinkVo getSMovLinkByAreaId(String areaId){
		
		return sareaDao.getSMovLinkByAreaId(areaId);
	}
}
