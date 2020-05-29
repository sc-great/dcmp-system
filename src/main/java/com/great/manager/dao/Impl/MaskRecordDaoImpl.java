package com.great.manager.dao.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.great.base.dao.impl.BaseDaoImpl;
import com.great.manager.dao.ClientInfoDao;
import com.great.manager.dao.MaskRecordDao;
import com.great.manager.dao.PersonDao;
import com.great.manager.entity.BPerson;
import com.great.manager.entity.SMaskRecord;
import com.great.tool.PageBean;

/**
 * @author LM
 */
@Repository
public class MaskRecordDaoImpl extends BaseDaoImpl implements MaskRecordDao {
	
	@Autowired
	private PersonDao personDao;
	@Autowired
	private ClientInfoDao clientInfoDao;
	
	public void getResult(PageBean pageBean) {
		StringBuffer hql = new StringBuffer();
		hql.append("from SMaskRecord where 1=1 ");
		
		// 简单处理注入
		String templet = (String) pageBean.getMap().get("userName");
		String clientId = (String) pageBean.getMap().get("code");
		String userName = templet.replaceAll("\'|%", "\"");
		if (userName != null && !"".equals(userName)) {
			hql.append("and pName like '%" + userName + "%'");
		}
		if (clientId != null && !"".equals(clientId)) {
			hql.append("and clientId like '%" + clientId + "%'");
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
		List<SMaskRecord> list = query.list();
		
		List<SMaskRecord> list_new = new ArrayList<>();
		String uCode = "";
		BPerson person = null;
		for (SMaskRecord maskRecord : list) {
			maskRecord.setClientName(clientInfoDao.getClientByCode(maskRecord.getClientId()).getName());
			uCode = maskRecord.getUCode();
			if (uCode == null || "".equals(uCode)) {
				list_new.add(maskRecord);
				continue;
			}
			person = personDao.getPersonById(uCode);
			if (person == null) {
				list_new.add(maskRecord);
				continue;
			}
			maskRecord.setPerson(person);
			list_new.add(maskRecord);
		}
		pageBean.setData(list_new);
	}

	@Override
	public int countNotMaskToday(String chId) {
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String sql = "";
		if ("".equals(chId))
			sql = "select count(*) from SMaskRecord where isAlarm = 1 and createTime >= '" + today + "'";
		else
			sql = "select count(*) from SMaskRecord where uCode in (select pId from BPerson where orgId = '" + chId + "' and isdeleted = 0) and isAlarm = 1 and createTime >= '" + today + "'";
		return Integer.parseInt((Long) getSession().createQuery(sql).uniqueResult() + "");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SMaskRecord> findAlarmByTime(String format) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from SMaskRecord ").append(" where createTime >= '" + format + "'");
		Query query = getSession().createQuery(hql.toString());
		return (List<SMaskRecord>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SMaskRecord> findAlarmByCount(int i) {
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		StringBuilder hql = new StringBuilder();
		hql.append(" from SMaskRecord where createTime >= '" + today + "' order by createTime desc");
		Query query = getSession().createQuery(hql.toString());
		// hibernate不识别limit
		query.setMaxResults(i);
		List<SMaskRecord> maskRecordList = (List<SMaskRecord>) query.list();
		List<SMaskRecord> list_new = new ArrayList<>();
		String uCode = "";
		BPerson person = null;
		for (SMaskRecord maskRecord : maskRecordList) {
			maskRecord.setClientName(clientInfoDao.getClientByCode(maskRecord.getClientId()).getName());
			uCode = maskRecord.getUCode();
			if (uCode == null || "".equals(uCode)) {
				list_new.add(maskRecord);
				continue;
			}
			person = personDao.getPersonById(uCode);
			if (person == null) {
				list_new.add(maskRecord);
				continue;
			}
			maskRecord.setPerson(person);
			list_new.add(maskRecord);
		}
		return list_new;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SMaskRecord> findAlarmDescTime() {
		StringBuilder hql = new StringBuilder();
		hql.append(" from SMaskRecord order by createTime desc");
		Query query = getSession().createQuery(hql.toString());
		return (List<SMaskRecord>) query.list();
	}
}
