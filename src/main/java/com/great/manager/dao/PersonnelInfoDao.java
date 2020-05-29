package com.great.manager.dao;

import java.util.List;

import com.great.base.dao.BaseDao;
import com.great.manager.entity.BPersonInfo;
import com.great.manager.entity.STemperatureRecord;


/**
 * @author ZQQ
 * 手机登录数据返回接口
 * */
public interface PersonnelInfoDao extends BaseDao {
	/**
	 * 手机登录数据返回接口
	 */
	public List<BPersonInfo> getListId(String phone,String checkCode);
	/**
	 * 用户登录查询信息接口
	 */
	public List<BPersonInfo> getListInfoId(String userCode);
	/**
	 * 查询当前用户最新体温记录
	 */
	public List<STemperatureRecord> getArrayStList(String uCode);
}
