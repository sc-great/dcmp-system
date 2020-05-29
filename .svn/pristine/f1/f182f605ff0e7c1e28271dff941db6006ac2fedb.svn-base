package com.great.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.base.service.impl.BaseServiceImpl;
import com.great.system.dao.SmovLinkDao;
import com.great.system.entity.SMovLink;
import com.great.system.entity.SMovLinkVo;
import com.great.system.service.SmovLinkService;

@Service
public class SmovLinkServiceImpl extends BaseServiceImpl<SMovLink> implements SmovLinkService {

	@Autowired
	private SmovLinkDao movLinkDao;
	
	public SMovLinkVo getSMovLinkByAreaId(String areaId){
		 
		return movLinkDao.getSMovLinkByAreaId(areaId);
	}
	
}
