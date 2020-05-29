package com.great.system.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.great.base.dao.impl.BaseDaoImpl;
import com.great.system.dao.MenuDao;
import com.great.system.dao.RoleDao;
import com.great.system.dao.UserDao;
import com.great.system.entity.SMenuEntity;
import com.great.system.entity.SRoleEntity;
import com.great.system.entity.SRoleMenuEntity;
import com.great.system.entity.SUserEntity;
import com.great.system.entity.SUserRoleEntity;
import com.great.tool.PageBean;

/**
 * @author LUOCHENG
 * 用户数据访问接口实现
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {
	/* 
	 * 用户数据分页获取方法
	 * @see com.great.system.dao.UserDao#getResult(com.great.tool.PageBean)
	 */
	@Override
	public void getResult(PageBean pageBean) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append("from  SRoleEntity  where 1=1 and isdeleted = false ");
//		String rolename = (String) pageBean.getMap().get("roleName");
		//简单处理注入
		String templet = (String) pageBean.getMap().get("roleName");
		String rolename =templet.replaceAll("\'|%", "\"");
		if (rolename != null && !"".equals(rolename)) {
			hql.append("and roleName like '%" + rolename + "%'");
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
		List<SRoleEntity> list = query.list();
		System.out.println(list.toString());
		pageBean.setData(list);

	}

	public List<SRoleEntity> getSelectList(){
		Criteria criteria = getSession().createCriteria(SRoleEntity.class);
		criteria.add(Restrictions.and(Restrictions.eq("status", 0)));
		criteria.add(Restrictions.and(Restrictions.eq("isdeleted", false)));
		return criteria.list();
	}
	
	
	public List<SUserRoleEntity> getSelectedRole(String userId){
		Criteria criteria = getSession().createCriteria(SUserRoleEntity.class);
		criteria.add(Restrictions.and(Restrictions.eq("userId", userId)));
		return criteria.list();
		
	}
	public List<SUserRoleEntity> getMenusByRoleId(String userId){
		Criteria criteria = getSession().createCriteria(SUserRoleEntity.class);
		criteria.add(Restrictions.eq("userId", userId));
		return criteria.list();
	}
	public List<SMenuEntity> getRoleMenus(String roleId){
		List<SMenuEntity> list=new ArrayList<SMenuEntity>();
		Criteria criteria = getSession().createCriteria(SRoleMenuEntity.class);
		criteria.add(Restrictions.eq("roleId", roleId));
		for(SRoleMenuEntity sm:(List<SRoleMenuEntity>)criteria.list()){
			list.add(sm.getSmenu());
		}
		return list;
	}
	@Override
	public void changeStatus(String[] ids, String status) {
		StringBuilder hql = new StringBuilder();
		hql.append("update SRoleEntity set status =:status  WHERE id IN (:list)");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("status", Integer.parseInt(status));
		query.setParameterList("list", ids);
		query.executeUpdate();
	}

	@Override
	public List<SRoleEntity> findUserRoleByIds(String[] ids) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder();
		hql.append("select role from SRoleEntity role where role.roleId in  (select distinct ur.roleId from SUserRoleEntity ur,SUserEntity user where ur.roleId in (:ids) and ur.userId = user.userId and user.isdeleted = false)");
		Query query=getSession().createQuery(hql.toString());
		query.setParameterList("ids", ids);
		List<SRoleEntity> urList=query.list();
		return urList;
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
