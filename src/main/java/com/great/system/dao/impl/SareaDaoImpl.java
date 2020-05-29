package com.great.system.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.great.base.dao.impl.BaseDaoImpl;
import com.great.system.dao.SareaDao;
import com.great.system.entity.SArea;
import com.great.system.entity.SAreaHost;
import com.great.system.entity.SMovLink;
import com.great.system.entity.SMovLinkVo;
import com.great.system.entity.SRoleEntity;
import com.great.tool.PageBean;

/**
 * @author LUOCHENG
 * 用户数据访问接口实现
 */
@Repository
public class SareaDaoImpl extends BaseDaoImpl implements SareaDao {

	/**
	 * 初始化查询列表
	 * */
	public List<SArea> getSelectList(){
		Criteria criteria = getSession().createCriteria(SRoleEntity.class);
		criteria.add(Restrictions.and(Restrictions.eq("status", 1)));
		//criteria.add(Restrictions.and(Restrictions.eq("isdeleted", false)));
		return criteria.list();
	}
	
	/* 
	 * 用户数据分页获取方法
	 * @see com.great.system.dao.UserDao#getResult(com.great.tool.PageBean)
	 */
	@Override
	public void getResult(PageBean pageBean) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		 hql.append("from  SArea   where  isdeleted='0'  ");
		
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
		List<SArea> list = query.list();
				
		//System.out.println(list.toString());
		pageBean.setData(list);

	}
	
public SMovLinkVo getSMovLinkByAreaId(String areaId) {
		
		SMovLinkVo rmv=new SMovLinkVo();
		try {
			
			Criteria criteria = getSession().createCriteria(SMovLink.class);
			criteria.add(Restrictions.and(Restrictions.eq("areaId", areaId)));
			criteria.addOrder(Order.asc("createTime"));
		    List<SMovLink>	list=criteria.list();
		    
			Iterator it = list.iterator();
			int i=0;
			while (it.hasNext()) { //已定每个防区限两条视频
			    SMovLink mk = (SMovLink) it.next(); 
				if(i==0){	
					  rmv.setId(mk.getID());
					  rmv.setAreaId(mk.getAreaId());				
					  rmv.setFirm(mk.getFirm());
					  rmv.setIpAddr(mk.getIpAddr());
					  rmv.setMovName(mk.getMovName());
					  rmv.setPassWord(mk.getPassWord());
					  rmv.setPort(mk.getPort());
					  rmv.setUserName(mk.getUserName());	
					  rmv.setNote(mk.getNote());																		
				}else if(i==1){
					  rmv.setId2(mk.getID());
					  rmv.setAreaId2(mk.getAreaId());				
					  rmv.setFirm2(mk.getFirm());
					  rmv.setIpAddr2(mk.getIpAddr());
					  rmv.setMovName2(mk.getMovName());
					  rmv.setPassWord2(mk.getPassWord());
					  rmv.setPort2(mk.getPort());
					  rmv.setUserName2(mk.getUserName());
					  rmv.setNote2(mk.getNote());	 
				}else {
				   break;
				}
				i++;				
				
			}
		} catch (Exception e) {
			System.out.println("查找存在的视频记录错误信息:" + e.getMessage());
		}
		return rmv;
	   	
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
		hql.append("update SArea set status =:status  WHERE areaId IN (:list)");
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
		hql.append("update SArea set isdeleted =:status  WHERE areaId IN (:list)");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("status", status);
		query.setParameterList("list", ids);	
		query.executeUpdate();
		//关联删除(逻辑删除)视频联动信息表
		StringBuilder hqlsub = new StringBuilder();
		hqlsub.append("update SMovLink set isdeleted =:status  WHERE areaId IN (:list)");
		Query querysub = getSession().createQuery(hqlsub.toString());
		querysub.setParameter("status", status);
		querysub.setParameterList("list", ids);	
		querysub.executeUpdate();
		
	}
	
	/**
	 * 添加页面中:主机下拉内容
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SAreaHost> getSareaHostByAll(){		
		Criteria criteria = getSession().createCriteria(SAreaHost.class);
		criteria.add(Restrictions.and(Restrictions.eq("status", 0)));
				
		return criteria.list();
		
	}
	/**
	 * 据ID取主机对象
	 */
	public  SAreaHost getSareaHost(String id ){
		SAreaHost returnObj= (SAreaHost)this.get(SAreaHost.class, id);	   	
		return returnObj;
	}
	
	/**
	 * 防区名重复
	 */
	@Override
	public List<SArea> getSAreaLoginName(String name) {
		Criteria criteria = getSession().createCriteria(SArea.class);
		criteria.add(Restrictions.and(Restrictions.eq("name", name)));	
		return criteria.list();
	}
}
