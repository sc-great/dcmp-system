package com.great.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.base.service.impl.BaseServiceImpl;
import com.great.system.dao.SareaHostDao;
import com.great.system.entity.SAreaHost;
import com.great.system.service.SareaHostService;
import com.great.tool.PageBean;

@Service
public class SareaHostServiceImpl extends BaseServiceImpl<SAreaHost> implements SareaHostService {

	
	@Autowired
	private SareaHostDao sareaDao;
	
	@Override
	public void getResult(PageBean pageBean) {
		this.sareaDao.getResult(pageBean);
	}

	public List<SAreaHost> getSelectList(){
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
	
	public SAreaHost getSareaHost(String hostSelectId){		
	  return 	sareaDao.getSareaHost(hostSelectId);
	}
	
	public List<SAreaHost>  getSreaHostName(String name){		
		return sareaDao.getSreaHostName(name);
	}
	
	public List<SAreaHost>  getSreaHostCode(String code){		
		return sareaDao.getSreaHostCode(code);
	}
}
