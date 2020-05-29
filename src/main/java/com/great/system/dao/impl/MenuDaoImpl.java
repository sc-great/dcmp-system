package com.great.system.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.great.base.dao.impl.BaseDaoImpl;
import com.great.system.dao.MenuDao;
import com.great.system.entity.SMenuEntity;
import com.great.system.entity.SRoleEntity;
import com.great.system.entity.SRoleMenuEntity;
import com.great.system.entity.SUserEntity;
import com.great.tool.PageBean;

/**
 * @author LUOCHENG
 * 用户数据访问接口实现
 */
@Repository
public class MenuDaoImpl extends BaseDaoImpl implements MenuDao {
	/* 
	 * 用户数据分页获取方法
	 * @see com.great.system.dao.UserDao#getResult(com.great.tool.PageBean)
	 */
	@Override
	public void getResult(PageBean pageBean) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append("from  SMenuEntity  where 1=1 and isdeleted = false ");
		//简单处理注入
		String templet = (String) pageBean.getMap().get("menuName");
		String menuName =templet.replaceAll("\'|%", "\"");
		if (menuName != null && !"".equals(menuName)) {
			hql.append("and menuName like '%" + menuName + "%'");
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
		List<SMenuEntity> list = query.list();
		pageBean.setData(list);

	}
	//根据角色id和菜单类型获取 角色菜单列表
	@Override
	public List<SRoleMenuEntity> getMenusByRoleId(String roleId,String menuType){
		
		Criteria criteria = getSession().createCriteria(SRoleMenuEntity.class);
		criteria.createAlias("smenu", "sm");
		criteria.add(Restrictions.eq("roleId", roleId));
		criteria.add(Restrictions.eq("sm.menuType", menuType));
		criteria.add(Restrictions.eq("sm.isdeleted", false));
		return criteria.list();
	}
	
	//根据角色id  获取角色菜单列表
	@Override
		public List<SRoleMenuEntity> getMenusByRoleId(String roleId){
			
			Criteria criteria = getSession().createCriteria(SRoleMenuEntity.class);
			criteria.createAlias("smenu", "sm");
			criteria.add(Restrictions.eq("roleId", roleId));
			criteria.add(Restrictions.eq("sm.isdeleted", false));
			return criteria.list();
		}
	
	/* 
	 * 菜单状态修改方法
	 * @see com.great.system.dao.UserDao#changeStatus(java.lang.String[], java.lang.String)
	 */
	@Override
	public void changeStatus(String[] ids, String status) {
		StringBuilder hql = new StringBuilder();
		hql.append("update SMenuEntity set status =:status  WHERE id IN (:list)");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("status", Integer.parseInt(status));
		query.setParameterList("list", ids);
		query.executeUpdate();
	}

	@Override
	public List<SMenuEntity> getMenuList() {
		Criteria criteria = getSession().createCriteria(SMenuEntity.class);
		criteria.add(Restrictions.eq("status", 0));
		criteria.add(Restrictions.eq("isdeleted", false));
		return criteria.list();
	}

	@Override
	public List<SMenuEntity> getParentMenu() {
		Criteria criteria = getSession().createCriteria(SMenuEntity.class);
		criteria.add(Restrictions.isNull("parentMenu"));
		criteria.add(Restrictions.eq("status", 0));
		criteria.add(Restrictions.eq("isdeleted", false));
		return criteria.list();
	}

	@Override
	public List<SMenuEntity> getMenuByParent(String parentId) {
		Criteria criteria = getSession().createCriteria(SMenuEntity.class);
		criteria.add(Restrictions.eq("status", 0));
		criteria.add(Restrictions.eq("isdeleted", false));
		if(parentId==null||parentId.equals("")){
			criteria.add(Restrictions.isNull("parentMenu")); 
		}else{
			criteria.createAlias("parentMenu", "pm");
			criteria.add(Restrictions.eq("pm.menuId", parentId));
		}
		return criteria.list();
	}
	@Override
	public List<SMenuEntity> getMenuByPid(String id) {
		Criteria criteria = getSession().createCriteria(SMenuEntity.class);
		criteria.add(Restrictions.eq("isdeleted", false));
		if(id==null||id.equals("")){
			criteria.add(Restrictions.isNull("parentMenu")); 
		}else{
			criteria.createAlias("parentMenu", "pm");
			criteria.add(Restrictions.eq("pm.menuId", id));
		}
		return criteria.list();
	}
	@Override
	public List<SMenuEntity> getMenuByExtendInt(int i) {
		Criteria criteria = getSession().createCriteria(SMenuEntity.class);
		criteria.add(Restrictions.eq("status", 0));
		criteria.add(Restrictions.eq("isdeleted", false));
		criteria.add(Restrictions.eq("extInt",1));
		
		return criteria.list();
	}
	@Override
	public List<SMenuEntity> getMenuByExtendtext(String parentId) {
		Criteria criteria = getSession().createCriteria(SMenuEntity.class);
		criteria.add(Restrictions.eq("status", 0));
		criteria.add(Restrictions.eq("isdeleted", false));
		if(parentId==null||parentId.equals("")){
			criteria.add(Restrictions.isNull("extText")); 
		}else{
			criteria.add(Restrictions.eq("extText", parentId));
		}
		return criteria.list();
	}
	
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
	
	@Override
	public boolean canDelete(String menuId) {
		//公寓信息关联查询
		StringBuilder ledHql = new StringBuilder();
		ledHql.append("select count(*) from SRoleMenuEntity rm,SRoleEntity role WHERE rm.smenu.menuId = :menuId and rm.roleId = role.roleId and role.isdeleted = false ");
		Query ledQuery = getSession().createQuery(ledHql.toString());
		ledQuery.setParameter("menuId", menuId);
		Long countLed = (Long)ledQuery.uniqueResult();
		if(countLed!=0){
			return false;
		}
		return true;
	}
	
	@Override
	public SMenuEntity findById(String menuId){
		StringBuffer hql = new StringBuffer();
		hql.append("from  SMenuEntity  where 1=1 and status=0 and isdeleted=false and menuId =:menuId");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("menuId", menuId);
		return (SMenuEntity) query.uniqueResult();
	}
}
