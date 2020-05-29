package com.great.manager.dao.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.great.base.dao.impl.BaseDaoImpl;
import com.great.manager.dao.CampusOrgDao;
import com.great.manager.entity.BCampusHierarchy;
import com.great.tool.PageBean;

/**
 * @author LUOCHENG
 * 组织机构数据访问接口实现
 */
@Repository
public class CampusOrgDaoImpl extends BaseDaoImpl<BCampusHierarchy> implements CampusOrgDao {
	/* 
	 * 组织机构数据分页获取方法
	 * @see com.great.system.dao.UserDao#getResult(com.great.tool.PageBean)
	 */
	@Override
	public void getResult(PageBean pageBean) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append("from  BCampusHierarchy  where 1=1  and isdeleted = false ");
//		String chName = (String) pageBean.getMap().get("chName");
		//简单处理注入
		String templet = (String) pageBean.getMap().get("chName");
		String chName =templet.replaceAll("\'|%", "\"");
		if (chName != null && !"".equals(chName)) {
			hql.append("and chName like '%" + chName + "%'");
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
		List<BCampusHierarchy> list = query.list();
		pageBean.setData(list);

	}

	/* 
	 * 组织机构状态修改方法
	 * @see com.great.system.dao.UserDao#changeStatus(java.lang.String[], java.lang.String)
	 */
	@Override
	public void changeStatus(String[] ids, String chStatus) {
		StringBuilder hql = new StringBuilder();
		hql.append("update BCampusHierarchy set status =:chStatus  WHERE id IN (:list)");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("chStatus", Integer.parseInt(chStatus));
		query.setParameterList("list", ids);
		query.executeUpdate();
	}

	@Override
	public List<BCampusHierarchy> getOrgByParent(String parentId) {
		Criteria criteria = getSession().createCriteria(BCampusHierarchy.class);
		if(parentId==null||parentId.equals("")){
			criteria.add(Restrictions.isNull("parentOrg"));
		}else{
			criteria.createAlias("parentOrg", "po");
			criteria.add(Restrictions.eq("po.chId", parentId));
		}
		return criteria.list();
	}
	

	@Override
	public List<BCampusHierarchy> findAll(Class<BCampusHierarchy> entityType) {
		Query query = getSession().createQuery("from " + entityType.getSimpleName() + " where isdeleted = 0 and status=0");
		return query.list();
	}

}
