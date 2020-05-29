package com.great.manager.dao.Impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.great.base.dao.impl.BaseDaoImpl;
import com.great.manager.dao.TacticsDao;
import com.great.manager.entity.CTactics;
import com.great.tool.PageBean;

@Repository
public class TacticsDaoImpl extends BaseDaoImpl implements TacticsDao {
	@Override
	public void getResult(PageBean pageBean) {
		StringBuffer hql = new StringBuffer();
		hql.append("from  CTactics  where 1=1  and isdeleted = false ");
		String templet = (String) pageBean.getMap().get("tName");
		String tName = templet.replaceAll("\'|%", "\"");
		if (tName != null && !"".equals(tName)) {
			hql.append(" and tName like '%" + tName + "%'");
		}
		String hqlString = "select count(*) " + hql.toString();
		Long countLong = (Long) getSession().createQuery(hqlString).uniqueResult();
		pageBean.setCount(countLong.intValue());
		hql.append(" order by createTime desc");
		Query query = getSession().createQuery(hql.toString());
		query.setFirstResult(pageBean.getStartNum());
		query.setMaxResults(pageBean.getLimit());
		List<CTactics> list = query.list();
		pageBean.setData(list);
	}

	@Override
	public void changeStatus(String[] ids, String status) {
		StringBuilder hql = new StringBuilder();
		hql.append("update CTactics set status =:status,updateTime=now()  WHERE vdId IN (:list)");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("status", Integer.parseInt(status));
		query.setParameterList("list", ids);
		query.executeUpdate();
	}

	@Override
	public CTactics getByName(String name) {
		StringBuffer hql = new StringBuffer();
		hql.append("from  CTactics  where 1=1 and isdeleted = false and tName =:name");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("name", name);
		return (CTactics) query.uniqueResult();
	}

}
