package com.great.manager.service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.great.base.service.impl.BaseServiceImpl;
import com.great.manager.dao.PersonnelFileInfoDao;
import com.great.manager.dao.PersonnelInfoDao;
import com.great.manager.entity.BPerson;
import com.great.manager.entity.BPersonInfo;
import com.great.manager.entity.STemperatureRecord;
import com.great.manager.service.PersonnelInfoService;

/**
 * @author ZQQ
 * */
@Service
public class PersonnelnfoServiceImpl extends BaseServiceImpl<BPersonInfo> implements PersonnelInfoService {
	@Autowired
	private PersonnelInfoDao personnelInfoDao;
	/**
	 * 手机登录数据返回接口
	 */
	@Override
	public List<BPersonInfo> getListId(String phone,String checkCode) {
		// TODO Auto-generated method stub
		return this.personnelInfoDao.getListId(phone,checkCode);
	}
	/**
	 * 用户登录查询信息接口
	 */
	@Override
	public List<BPersonInfo> getListInfoId(String userCode) {
		// TODO Auto-generated method stub
		return this.personnelInfoDao.getListInfoId(userCode);
	}

	/**
	 * 查询当前用户最新体温记录
	 */
	@Override
	public List<STemperatureRecord> getArrayStList(String uCode) {
		// TODO Auto-generated method stub
		return this.personnelInfoDao.getArrayStList(uCode);
	}
}
