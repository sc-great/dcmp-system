package com.great.system.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.great.base.dao.impl.BaseDaoImpl;
import com.great.system.dao.DictionaryDao;
import com.great.system.entity.SDictionaryEntity;
import com.great.system.entity.SDictionaryValEntity;
import com.great.tool.PageBean;

@Repository
public class DictionaryDaoImpl extends BaseDaoImpl implements DictionaryDao {
	@Override
	public void getDicResult(PageBean pageBean) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append("from  SDictionaryEntity  where 1=1 ");
		//简单处理注入
		String templet = (String) pageBean.getMap().get("dicName");
		String dicName =templet.replaceAll("\'|%", "\"");
		if (dicName != null && !"".equals(dicName)) {
			hql.append("and dicName like '%" + dicName + "%'");
		}
		String hqlString = "select count(*) " + hql.toString();
		Long countLong = (Long) getSession().createQuery(hqlString).uniqueResult();
		pageBean.setCount(countLong.intValue());
		hql.append(" order by dicOrder asc");
		Query query = getSession().createQuery(hql.toString());
		query.setFirstResult(pageBean.getStartNum());
		query.setMaxResults(pageBean.getLimit());
		List<SDictionaryEntity> list = query.list();
		pageBean.setData(list);

	}

	@Override
	public void getDicValueResult(PageBean pageBean) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append("from  SDictionaryValEntity  where 1=1 ");
		
		String dicId = (String) pageBean.getMap().get("dicId");
		if (dicId != null && !"".equals(dicId)) {
			hql.append("and dicId = '" + dicId + "'");
		}
//		String dvName = (String) pageBean.getMap().get("dvName");
		//简单处理注入
		String templet = (String) pageBean.getMap().get("dvName");
		String dvName =templet.replaceAll("\'|%", "\"");
		if (dvName != null && !"".equals(dvName)) {
			hql.append("and dvName like '%" + dvName + "%'");
		}
		String hqlString = "select count(*) " + hql.toString();
		Long countLong = (Long) getSession().createQuery(hqlString).uniqueResult();
		pageBean.setCount(countLong.intValue());
		hql.append(" order by dvOrder asc");
		Query query = getSession().createQuery(hql.toString());
		query.setFirstResult(pageBean.getStartNum());
		query.setMaxResults(pageBean.getLimit());
		List<SDictionaryValEntity> list = query.list();
		pageBean.setData(list);

	}

	@Override
	public SDictionaryValEntity getDicValueById(String dicValueId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from  SDictionaryValEntity  where 1=1 and dvId =:DvId");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("DvId", dicValueId);
		return (SDictionaryValEntity) query.uniqueResult();
	}

	@Override
	public void changeDicStatus(String[] ids, String status) {
		StringBuilder hql = new StringBuilder();
		hql.append("update SDictionaryEntity set status =:status  WHERE dicId IN (:list)");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("status", Integer.parseInt(status));
		query.setParameterList("list", ids);
		query.executeUpdate();
		
	}

	@Override
	public void changeDicValueStatus(String[] ids, String status) {
		StringBuilder hql = new StringBuilder();
		hql.append("update SDictionaryValEntity set status =:status  WHERE dvId IN (:list)");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("status", Integer.parseInt(status));
		query.setParameterList("list", ids);
		query.executeUpdate();
		
	}

	@Override
	public void delDicByIds(String[] ids) {
		StringBuilder hql = new StringBuilder();
		hql.append("Delete from SDictionaryEntity   WHERE dicId IN (:list)");
		Query query = getSession().createQuery(hql.toString());
		query.setParameterList("list", ids);
		query.executeUpdate();
	}

	@Override
	public void delDicValueByIds(String[] ids) {
		StringBuilder hql = new StringBuilder();
		hql.append("Delete from SDictionaryValEntity   WHERE dvId IN (:list)");
		Query query = getSession().createQuery(hql.toString());
		query.setParameterList("list", ids);
		query.executeUpdate();
		
	}

	@Override
	public List<SDictionaryValEntity> getDicValueByDicCode(String dicCode) {
		StringBuffer hql = new StringBuffer();
		hql.append("Select sd from  SDictionaryValEntity  sd, SDictionaryEntity sv where sv.dicId = sd.dicId and sv.dicCode=:dicCode and sd.status = 0 order by sd.dvOrder asc");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("dicCode", dicCode);
		
		return query.list();
	}

	@Override
	public void delDicValueByDicId(String[] ids) {
		StringBuilder hql = new StringBuilder();
		hql.append("Delete from SDictionaryValEntity   WHERE dicId IN (:list)");
		Query query = getSession().createQuery(hql.toString());
		query.setParameterList("list", ids);
		query.executeUpdate();
		
	}

	@Override
	public SDictionaryValEntity getDicValueByValue(String value) {
		StringBuffer hql = new StringBuffer();
		hql.append("from  SDictionaryValEntity  where 1=1 and dvValue =:dvValue");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("dvValue", value);
		return (SDictionaryValEntity) query.uniqueResult();
	}

	@Override
	public SDictionaryEntity getDicByValue(String value){
		StringBuffer hql=new StringBuffer();
		hql.append("from SDictionaryEntity where 1=1 and dicCode=:dicCode");
		Query query=getSession().createQuery(hql.toString());
		query.setParameter("dicCode", value);
		return (SDictionaryEntity) query.uniqueResult();
	}

	@Override
	public SDictionaryValEntity getDicValueByName(String dvName) {
		StringBuffer hql = new StringBuffer();
		hql.append("from  SDictionaryValEntity  where 1=1 and dvName =:dvName");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("dvName", dvName);
		return (SDictionaryValEntity) query.uniqueResult();
	}

	@Override
	public List<SDictionaryEntity> getDicList() {
		StringBuffer hql = new StringBuffer();
		hql.append("from SDictionaryEntity where 1=1    order by dicOrder asc");
		Query query = getSession().createQuery(hql.toString());
		return query.list();
	}

	@Override
	public boolean canDelete(SDictionaryValEntity dicVal) {
		//用户类型关联查询
		StringBuilder userTypeHql = new StringBuilder();
		userTypeHql.append("select count(*) from SUserType userType,SUserEntity user WHERE userType.typeCode =:typeCode and userType.userId = user.userId and user.isdeleted = false");
		Query userTypeQuery = getSession().createQuery(userTypeHql.toString());
		userTypeQuery.setParameter("typeCode", dicVal.getDvValue());
		Long countUserType = (Long)userTypeQuery.uniqueResult();
		if(countUserType!=0){
			return false;
		}

		//组织机构类型关联查询
		StringBuilder chTypeHql = new StringBuilder();
		chTypeHql.append("select count(*) from BCampusHierarchy WHERE chType =:chType and isdeleted = false");
		Query chTypeQuery = getSession().createQuery(chTypeHql.toString());
		chTypeQuery.setParameter("chType", dicVal.getDvValue());
		Long countChType = (Long)chTypeQuery.uniqueResult();
		if(countChType!=0){
			return false;
		}

		//菜单类型关联查询
		StringBuilder menuTypeHql = new StringBuilder();
		menuTypeHql.append("select count(*) from SMenuEntity WHERE menuType =:menuType and isdeleted = false");
		Query menuTypeQuery = getSession().createQuery(menuTypeHql.toString());
		menuTypeQuery.setParameter("menuType", dicVal.getDvValue());
		Long menuTypeCount = (Long)menuTypeQuery.uniqueResult();
		if(menuTypeCount!=0){
			return false;
		}

		return true;
	}
}
