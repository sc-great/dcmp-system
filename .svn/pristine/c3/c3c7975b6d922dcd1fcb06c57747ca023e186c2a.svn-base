package com.great.manager.dao.Impl;


import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.great.base.dao.impl.BaseDaoImpl;
import com.great.manager.dao.AttendanceTimeSetupDao;
import com.great.manager.entity.BAttendanceTimeSetup;

/**
 * @author zqq
 * 设置出勤时间
 */
@Repository
public class AttendanceTimeSetupDaoImpl extends BaseDaoImpl implements AttendanceTimeSetupDao {

	//详细信息
	@Override
	public BAttendanceTimeSetup getAttendanceValueById(String aId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from  BAttendanceTimeSetup  where 1=1 and aId =:aId");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("aId", aId);
		return (BAttendanceTimeSetup) query.uniqueResult();
	}

}
