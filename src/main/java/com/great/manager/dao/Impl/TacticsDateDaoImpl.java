package com.great.manager.dao.Impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.great.base.dao.impl.BaseDaoImpl;
import com.great.manager.dao.TacticsDateDao;
import com.great.manager.entity.CTacticsDate;

@Repository
public class TacticsDateDaoImpl extends BaseDaoImpl implements TacticsDateDao {

	@Override
	public List<CTacticsDate> getBytId(String gettId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from  CTacticsDate  where 1=1  and tId=:tId  and isdeleted=false order by tdDate asc");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("tId", gettId);
		List<CTacticsDate> list = query.list();
		return list;
	}

	@Override
	public void deleteByTId(String gettId) {
		StringBuffer hql = new StringBuffer();
		hql.append("delete CTacticsDate  where 1=1 and tId = (:tId)");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("tId", gettId);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CTacticsDate> findByTdDate(String startTime, String endTime) {
		String hql = "from CTacticsDate where tdDate >= '" + startTime + "' and tdDate <= '" + endTime + "' order by tdDate asc";
		return getSession().createQuery(hql).list();
	}

}
