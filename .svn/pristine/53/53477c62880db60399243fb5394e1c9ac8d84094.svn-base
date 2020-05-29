package com.great.system.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.great.base.dao.impl.BaseDaoImpl;
import com.great.system.dao.SareaHostDao;
import com.great.system.entity.SAreaHost;
import com.great.tool.PageBean;

/**
 * @author LUOCHENG
 * 用户数据访问接口实现
 */
@Repository
public class SareaHostDaoImpl extends BaseDaoImpl implements SareaHostDao {


	/* 
	 * 用户数据分页获取方法
	 * 
	 */
	@Override
	public void getResult(PageBean pageBean) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		 hql.append("from  SAreaHost   where  isdeleted='0'  ");
		
		//简单处理注入
		String templet = (String) pageBean.getMap().get("name");
		String name =templet.replaceAll("\'|%", "\"");
		if (name != null && !"".equals(name)) {
			hql.append("and name like '%" + name + "%'");
		}
		String status = (String) pageBean.getMap().get("status");
		if (status != null && !"".equals(status)) {
			hql.append(" and status =" + status );
		}
		

		StringBuilder ledHql = new StringBuilder();
		ledHql.append("select count(*) " +hql.toString());
		Query ledQuery = getSession().createQuery(ledHql.toString());		
		Long countLong = (Long)ledQuery.uniqueResult();
		
	
		pageBean.setCount(countLong.intValue());
		hql.append(" order by createTime desc");		
				
		Query query = getSession().createQuery(hql.toString());
			
		query.setFirstResult(pageBean.getStartNum());
		query.setMaxResults(pageBean.getLimit());
		
		@SuppressWarnings("unchecked")
		List<SAreaHost> list = query.list();
				
		//System.out.println(list.toString());
		pageBean.setData(list);

	}

	/**
	 * 批量启用/停用
	 * @param ids
	 * @param status
	 * @return
	 */
	@Override
	public void changeStatus(String[] ids, String status) {
		StringBuilder hql = new StringBuilder();
		hql.append("update SAreaHost set status =:status  WHERE hostId IN (:list)");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("status", Integer.parseInt(status));
		query.setParameterList("list", ids);	
		query.executeUpdate();
	}

	/**
	 * 逻缉批量删除
	 */
	@Override
	public void changeDelStatus(String[] ids, String status) {
		StringBuilder hql = new StringBuilder();
		hql.append("update SAreaHost set isdeleted =:isdeletedStatus,status=1  WHERE hostId IN (:list)");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("isdeletedStatus", status);
		query.setParameterList("list", ids);	
		query.executeUpdate();

	}
	
	
	/**
	 * 据ID取主机对象
	 */
	public  SAreaHost getSareaHost(String id ){
		SAreaHost returnObj= (SAreaHost)this.get(SAreaHost.class, id);	   	
		return returnObj;
	}
	
	/**
	 * 主机名称重复
	 */
	@Override
	public List<SAreaHost> getSreaHostName(String name) {
		Criteria criteria = getSession().createCriteria(SAreaHost.class);
		criteria.add(Restrictions.and(Restrictions.eq("name", name)));	
		return criteria.list();
	}
	/**
	 * //主机编号重复
	 */
	@Override
	public List<SAreaHost> getSreaHostCode(String code) {
		Criteria criteria = getSession().createCriteria(SAreaHost.class);
		criteria.add(Restrictions.and(Restrictions.eq("code", code)));	
		return criteria.list();
	}
	
	/**
	 * 初始化查询列表
	 * */
	public List<SAreaHost> getSelectList(){
		Criteria criteria = getSession().createCriteria(SAreaHost.class);
		criteria.add(Restrictions.and(Restrictions.eq("status", 1)));
		//criteria.add(Restrictions.and(Restrictions.eq("isdeleted", false)));
		return criteria.list();
	}
	
}
