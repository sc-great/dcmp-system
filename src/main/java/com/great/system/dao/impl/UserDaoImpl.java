package com.great.system.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.great.base.dao.impl.BaseDaoImpl;
import com.great.system.dao.UserDao;
import com.great.system.entity.SUserEntity;
import com.great.system.entity.SUserType;
import com.great.tool.AreaUtils;
import com.great.tool.PageBean;

/**
 * @author LUOCHENG 用户数据访问接口实现
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
	/*
	 * 用户数据分页获取方法
	 * 
	 * @see com.great.system.dao.UserDao#getResult(com.great.tool.PageBean)
	 */
	@Override
	public void getResult(PageBean pageBean) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append("from  SUserEntity  where 1=1  and isdeleted = false ");
		// String username = (String) pageBean.getMap().get("userName");
		// 简单处理注入
		String templet = (String) pageBean.getMap().get("userName");
		String username = templet.replaceAll("\'|%", "\"");
		if (username != null && !"".equals(username)) {
			hql.append("and loginName like '%" + username + "%'");
		}
		String startTime = (String) pageBean.getMap().get("startTime");
		if (startTime != null && !"".equals(startTime)) {
			hql.append(" and createTime >'" + startTime + "'");
		}
		String endTime = (String) pageBean.getMap().get("endTime");
		if (endTime != null && !"".equals(endTime)) {
			hql.append(" and createTime < '" + endTime + "'");
		}
//		BArea area = (BArea) pageBean.getMap().get("area");
//		if (area != null) {
//			hql.append(" and areaId like '" + AreaUtils.subStringAreaCode(area.getAreaCode()) + "%'");
//		}
		String hqlString = "select count(*) " + hql.toString();
		Long countLong = (Long) getSession().createQuery(hqlString).uniqueResult();
		pageBean.setCount(countLong.intValue());
		hql.append(" order by createTime desc");
		Query query = getSession().createQuery(hql.toString());
		query.setFirstResult(pageBean.getStartNum());
		query.setMaxResults(pageBean.getLimit());
		List<SUserEntity> list = query.list();
		pageBean.setData(list);

	}

	@Override
	public void getResult01(PageBean pageBean) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append("from  SUserEntity  where 1=1  and isdeleted = false ");
		// String username = (String) pageBean.getMap().get("userName");
		// 简单处理注入
		String templet = (String) pageBean.getMap().get("userName");
		String username = templet.replaceAll("\'|%", "\"");
		if (username != null && !"".equals(username)) {
			hql.append("and loginName like '%" + username + "%'");
		}
		String startTime = (String) pageBean.getMap().get("startTime");
		if (startTime != null && !"".equals(startTime)) {
			hql.append(" and createTime >'" + startTime + "'");
		}
		String endTime = (String) pageBean.getMap().get("endTime");
		if (endTime != null && !"".equals(endTime)) {
			hql.append(" and createTime < '" + endTime + "'");
		}
		/*
		 * SOrgIngo area = (SOrgIngo) pageBean.getMap().get("org"); if (area !=
		 * null) { hql.append(" and areaId like '" + area.getOrgCode() + "%'");
		 * }
		 */
		String hqlString = "select count(*) " + hql.toString();
		Long countLong = (Long) getSession().createQuery(hqlString).uniqueResult();
		pageBean.setCount(countLong.intValue());
		hql.append(" order by createTime desc");
		Query query = getSession().createQuery(hql.toString());
		query.setFirstResult(pageBean.getStartNum());
		query.setMaxResults(pageBean.getLimit());
		List<SUserEntity> list = query.list();
		pageBean.setData(list);

	}

	/*
	 * 用户状态修改方法
	 * 
	 * @see com.great.system.dao.UserDao#changeStatus(java.lang.String[],
	 * java.lang.String)
	 */
	@Override
	public void changeStatus(String[] ids, String status) {
		StringBuilder hql = new StringBuilder();
		hql.append("update SUserEntity set status =:status  WHERE id IN (:list)");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("status", Integer.parseInt(status));
		query.setParameterList("list", ids);
		query.executeUpdate();
	}

	@Override
	public void deleteUserTypeByUserId(String userId) {
		StringBuilder hql = new StringBuilder();
		hql.append("DELETE FROM SUserType ").append(" WHERE userId = :userId");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("userId", userId);
		query.executeUpdate();
	}

	@Override
	public List<SUserType> getUserTypeByUserId(String userId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" FROM SUserType ").append(" WHERE userId = :userId");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("userId", userId);
		return query.list();
	}

	@Override
	public List<SUserEntity> getSelectList(String userType) {
		StringBuilder hql = new StringBuilder();
		hql.append(" FROM SUserEntity ue").append(
				" WHERE ue.status=0 and ue.userId IN(select ut.userId from SUserType ut where ut.typeCode = :userType)");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("userType", userType);
		return query.list();
	}

	@Override
	public List<SUserEntity> getUserByPhone(String userPhone) {
		Criteria criteria = getSession().createCriteria(SUserEntity.class);
		criteria.add(Restrictions.and(Restrictions.eq("userPhone", userPhone)));
		criteria.add(Restrictions.and(Restrictions.eq("isdeleted", false)));
		return criteria.list();
	}

	@Override
	public List<SUserEntity> getUserByLoginName(String loginName) {
		Criteria criteria = getSession().createCriteria(SUserEntity.class);
		criteria.add(Restrictions.and(Restrictions.eq("loginName", loginName)));
		criteria.add(Restrictions.and(Restrictions.eq("isdeleted", false)));
		return criteria.list();
	}

	@Override
	public SUserEntity getByToken(String token) {
		StringBuffer hql = new StringBuffer();
		hql.append("from  SUserEntity  where 1=1 and status=0 and isdeleted=false and mobileToken =:token");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("token", token);
		return (SUserEntity) query.uniqueResult();
	}

	// public List<VmUser> getUserByDepAndHos(Integer depId) {
	// Criteria criteria = getSession().createCriteria(VmUser.class);
	// criteria.add(Restrictions.eq("depId", depId));
	// criteria.add(Restrictions.eq("isdeleted", false));
	// criteria.add(Restrictions.not(Expression.eq("loginName", "admin")));
	// criteria.add(Restrictions.isNotNull("hostpitalId"));
	// return criteria.list();
	// }

	// public void getSeekHelp(PageBean pageBean) {
	// Criteria criteria = getSession().createCriteria(VmSeekHelp.class);
	// Map param = pageBean.getMap();
	// VmUser vu = (VmUser) param.get("user");
	// List<Integer> depIds = (List<Integer>) param.get("depIds");
	// criteria.add(Restrictions.or(
	// Restrictions.and(Restrictions.in("villageId", depIds),
	// Restrictions.isNull("acceptUid")),
	// Restrictions.and(Restrictions.eq("acceptUid", vu.getId()))));
	// criteria.setProjection(Projections.rowCount());// 统计行数
	// pageBean.setTotal(((Long) criteria.uniqueResult()).intValue());
	// if (param.get("order").equals("asc")) {
	// criteria.addOrder(Order.asc(param.get("sort").toString()));
	// } else {
	// criteria.addOrder(Order.desc(param.get("sort").toString()));
	// }
	// criteria.setFirstResult(pageBean.getStartNum()).setMaxResults(pageBean.getPageSize());//
	// 设置分段
	// criteria.setProjection(null);// .addOrder(Order.asc("villageId"));// 去除统计
	// 添加排序
	// pageBean.setRows(criteria.list());
	// }

}
