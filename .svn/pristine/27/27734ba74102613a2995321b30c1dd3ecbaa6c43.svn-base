package com.great.manager.dao.Impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.great.base.dao.impl.BaseDaoImpl;
import com.great.manager.dao.PersonnelInfoDao;
import com.great.manager.entity.BPerson;
import com.great.manager.entity.BPersonInfo;
import com.great.manager.entity.STemperatureRecord;


/**
 * @author ZQQ
 * 手机登录数据返回接口
 * */
@Repository
public class PersonnelInfoDaoImpl extends BaseDaoImpl implements PersonnelInfoDao {
	/**
	 * 手机登录数据返回接口
	 */
	@Override
	public List<BPersonInfo> getListId(String phone,String checkCode) {
		StringBuffer hql = new StringBuffer();
		hql.append("from  BPersonInfo  where 1=1");
		if (phone != null && !"".equals(phone)) {
			hql.append("and pPhone = '" + phone + "'");
		}
		if (checkCode != null && !"".equals(checkCode)) {
			hql.append("and verificationCode = '" + checkCode + "'");
		}
		Query query = getSession().createQuery(hql.toString());
		return query.list();
	}
	
	/**
	 * 查询当前用户最新体温记录
	 */
	@Override
	public List<STemperatureRecord> getArrayStList(String uCode) {
		
		StringBuffer hql = new StringBuffer();
		hql.append("from  STemperatureRecord  where 1=1 ");
		if (uCode != null && !"".equals(uCode)) {
			hql.append("and uCode = '" + uCode + "'");
			hql.append(" order by createTime desc");
		}
		Query query = getSession().createQuery(hql.toString());
		query.setFirstResult(1);//开始索引
		query.setMaxResults(5);//取几条
		return  query.list();
	}
	
	/**
	 * 用户登录查询信息接口
	 */
	@Override
	public List<BPersonInfo> getListInfoId(String userCode) {
		StringBuffer hql = new StringBuffer();
		hql.append("from  BPersonInfo  where 1=1");
		if (userCode != null && !"".equals(userCode)) {
			hql.append("and userCode = '" + userCode + "'");
		}
		Query query = getSession().createQuery(hql.toString());
		return query.list();
	}
}
