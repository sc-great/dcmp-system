package com.great.manager.dao.Impl;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.great.manager.dao.AttendanceRecordDao;
import com.great.manager.entity.STemperatureRecord;
import com.great.tool.PageBean;

import net.sf.json.JSONObject;

@Repository
public class AttendanceRecordDaoImpl extends HibernateDaoSupport implements AttendanceRecordDao {
	
	@Resource  
    public void setSessionFacotry(SessionFactory sessionFacotry) {  
        super.setSessionFactory(sessionFacotry);  
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<STemperatureRecord> getRecordByTimeLikeName(String userName, String startTime, String endTime, String org) {
		
		// 设置转换的日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 得到相差的天数 betweenDate
        long start = 0;
        long end = 0;
		try {
			start = sdf.parse(startTime).getTime();
			if (endTime != null && !"".equals(endTime))
	        	end = sdf.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).getTime();
			else
				end = sdf.parse(endTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int num = Integer.parseInt((end - start) / (60 * 60 * 24 * 1000) + "");
		
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT t.u_code, t.p_name");
		String date = "";
		for (int i = 0; i < num; i ++) {
			date = dateUpOneDay(startTime, i);
			hql.append(", MAX( CASE DATE_FORMAT( t.create_time, '%Y-%m-%d' ) WHEN '" + date + "' THEN t.create_time END ) as '" + date + ".max'");
			hql.append(", MIN( CASE DATE_FORMAT( t.create_time, '%Y-%m-%d' ) WHEN '" + date + "' THEN t.create_time END ) as '" + date + ".min'");
		}
		hql.append(" FROM s_temp_record t where 1 = 1");
		if (userName != null && !"".equals(userName))
			hql.append(" and t.u_code in (select p_id from b_person where p_name like '%" + userName + "%')");
		if (org != null && !"".equals(org))
			hql.append(" and t.u_code in (select p_id from b_person where org_id = '" + org + "')");
		if (startTime != null && !"".equals(startTime))
			hql.append(" and t.create_time >= '" + startTime + "'");
		if (endTime != null && !"".equals(endTime))
			hql.append(" and t.create_time <= '" + endTime + "'");
		
		hql.append(" GROUP BY t.u_code");
		System.out.println(hql.toString());
		
//		SessionFactory sf = this.getHibernateTemplate().getSessionFactory();
//		Session s = sf.getCurrentSession();
//		Query query = s.createQuery(hql.toString());
//		List<Object[]> list = query.list();
		
		List<Object[]> list = (List<Object[]>) this.getHibernateTemplate().find(hql.toString());
//		List<Object[]> list = getSession().createQuery(hql.toString()).list();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("jsonObject", list);
		System.out.println(jsonObject);
		
		StringBuilder sb = null;
		for (Object[] arr : list) {
			sb = new StringBuilder();
			for (int i = 2; i < num + 2; i ++) {
				sb.append("--" + arr[i]);
			}
			System.out.println(arr[0] + "--" + arr[1] + sb);
		}
		
		return null;
	}
	
	// 时间加一天的方法
	public static String dateUpOneDay(String date, int i) {
		Calendar calendar = new GregorianCalendar();
		try {
			calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calendar.add(calendar.DATE, i); // 把日期往后增加i天，整数往后推，负数往前移动
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()); // 这个时间就是日期往后推一天的结果
	}

	@Override
	public Session getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load(Object object, Serializable id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object load(String entityName, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serializable save(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdate(Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object merge(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persist(Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByIds(Class entityType, boolean logicDelete, String userName, String... ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByIds(Class entityType, String... ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByIds(boolean logicDelete, Class entityType, String... ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object get(Class entityType, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(String entityName, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findByExample(Object example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findByExample(Object example, String sortName, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findAll(Class entityType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findAllNoDelete(Class entityType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findAllNoDeleteNoStop(Class entityType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void findPage(Class entityName, PageBean pageBean) {
		// TODO Auto-generated method stub
		
	}

}
