package com.great.system.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.great.base.dao.impl.BaseDaoImpl;
import com.great.system.dao.RoleMenuDao;
import com.great.system.dao.UserDao;
import com.great.system.entity.SRoleMenuEntity;
import com.great.system.entity.SUserEntity;
import com.great.system.entity.SUserRoleEntity;
import com.great.tool.PageBean;

/**
 * @author LUOCHENG
 * 用户数据访问接口实现
 */
@Repository
public class RoleMenuDaoImpl extends BaseDaoImpl implements RoleMenuDao {
	/* 
	 * 用户数据分页获取方法
	 * @see com.great.system.dao.UserDao#getResult(com.great.tool.PageBean)
	 */
	@Override
	public void getResult(PageBean pageBean) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append("from  SRoleMenuEntity  where 1=1 and isdeleted = false ");
		String username = (String) pageBean.getMap().get("userName");
		if (username != null && !"".equals(username)) {
			hql.append("and userName like '%" + username + "%'");
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
		List<SRoleMenuEntity> list = query.list();
		pageBean.setData(list);

	}

	/* 
	 * 用户状态修改方法
	 * @see com.great.system.dao.UserDao#changeStatus(java.lang.String[], java.lang.String)
	 */
	@Override
	public void changeStatus(String[] ids, String status) {
		StringBuilder hql = new StringBuilder();
		hql.append("update SRoleMenuEntity set status =:status  WHERE id IN (:list)");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("status", Integer.parseInt(status));
		query.setParameterList("list", ids);
		query.executeUpdate();
	}

	@Override
	public void deleteByRoleId(String roleId) {
		StringBuilder hql = new StringBuilder();
		hql.append("DELETE FROM SRoleMenuEntity WHERE roleId= :roleId");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("roleId", roleId);
		query.executeUpdate();
	}
	

	// public List<VmUser> getUserByDepAndHos(Integer depId) {
	// Criteria criteria = getSession().createCriteria(VmUser.class);
	// criteria.add(Restrictions.eq("depId", depId));
	// criteria.add(Restrictions.eq("isdeleted", false));
	// criteria.add(Restrictions.not(Expression.eq("loginName", "admin")));
	// criteria.add(Restrictions.isNotNull("hostpitalId"));
	// return criteria.list();
	// }
	
	//	public void getSeekHelp(PageBean pageBean) {
	//		Criteria criteria = getSession().createCriteria(VmSeekHelp.class);
	//		Map param = pageBean.getMap();
	//		VmUser vu = (VmUser) param.get("user");
	//		List<Integer> depIds = (List<Integer>) param.get("depIds");
	//		criteria.add(Restrictions.or(
	//				Restrictions.and(Restrictions.in("villageId", depIds), Restrictions.isNull("acceptUid")),
	//				Restrictions.and(Restrictions.eq("acceptUid", vu.getId()))));
	//		criteria.setProjection(Projections.rowCount());// 统计行数
	//		pageBean.setTotal(((Long) criteria.uniqueResult()).intValue());
	//		if (param.get("order").equals("asc")) {
	//			criteria.addOrder(Order.asc(param.get("sort").toString()));
	//		} else {
	//			criteria.addOrder(Order.desc(param.get("sort").toString()));
	//		}
	//		criteria.setFirstResult(pageBean.getStartNum()).setMaxResults(pageBean.getPageSize());// 设置分段
	//		criteria.setProjection(null);// .addOrder(Order.asc("villageId"));// 去除统计 添加排序
	//		pageBean.setRows(criteria.list());
	//	}

}
