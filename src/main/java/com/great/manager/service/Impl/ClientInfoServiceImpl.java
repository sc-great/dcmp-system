package com.great.manager.service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.great.base.service.impl.BaseServiceImpl;
import com.great.manager.dao.ClientInfoDao;
import com.great.manager.dao.PersonnelFileInfoDao;
import com.great.manager.entity.BClient;
import com.great.manager.entity.BPerson;
import com.great.manager.service.ClientInfoService;
import com.great.manager.service.PersonnelFileInfoService;
import com.great.system.entity.SDictionaryValEntity;
import com.great.tool.PageBean;

@Service
public class ClientInfoServiceImpl extends BaseServiceImpl<BClient> implements ClientInfoService {

	@Autowired
	private ClientInfoDao clientInfoDao;

	@Override
	public void getResult(PageBean pageBean) {
		this.clientInfoDao.getResult(pageBean);
	}


	/**
	 * 详细信息查看
	 * */
	@Override
	public BClient getPersonneValueById(String clientId) {
		// TODO Auto-generated method stub
		return clientInfoDao.getPersonneValueById(clientId);
	}
	
	

	/**
	 * 更改状态
	 * */
	@Override
	public void changeStatus(String[] ids, String status) {
		this.clientInfoDao.changeStatus(ids, status);
	}


	@Override
	public List<BClient> getCliValueById(String code) {
		// TODO Auto-generated method stub
		return clientInfoDao.getCliValueById(code);
	}
}
