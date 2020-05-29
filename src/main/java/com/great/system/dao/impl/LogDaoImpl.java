package com.great.system.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.great.base.dao.impl.BaseDaoImpl;
import com.great.system.dao.LogDao;
import com.great.system.dao.UserDao;
import com.great.system.entity.SLogEntity;
import com.great.system.entity.SUserEntity;
import com.great.tool.PageBean;

/**
 * @author LUOCHENG
 * 用户数据访问接口实现
 */
@Repository
public class LogDaoImpl extends BaseDaoImpl implements LogDao {
	/* 
	 * 用户数据分页获取方法
	 * @see com.great.system.dao.UserDao#getResult(com.great.tool.PageBean)
	 */
	@Override
	public void getResult(PageBean pageBean) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append("from  SLogEntity  where 1=1");
		String logType = (String) pageBean.getMap().get("logType");
		if (logType != null && !"".equals(logType)) {
			hql.append("and logType like '%" + logType + "%'");
		}
		String startTime = (String) pageBean.getMap().get("startTime");
		if (startTime != null && !"".equals(startTime)) {
			hql.append(" and createTime >'" + startTime + "'");
		}
		String endTime = (String) pageBean.getMap().get("endTime");
		if (endTime != null && !"".equals(endTime)) {
			hql.append(" and createTime < '" + endTime + "'");
		}
		String hqlString = "select count(*) " + hql.toString();
		Long countLong = (Long) getSession().createQuery(hqlString).uniqueResult();
		pageBean.setCount(countLong.intValue());
		hql.append(" order by createTime desc");
		Query query = getSession().createQuery(hql.toString());
		query.setFirstResult(pageBean.getStartNum());
		query.setMaxResults(pageBean.getLimit());
		List<SLogEntity> list = query.list();
		pageBean.setData(list);

	}




}
