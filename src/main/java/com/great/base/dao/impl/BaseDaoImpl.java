package com.great.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.great.base.dao.BaseDao;
import com.great.tool.PageBean;

@Repository(value = "baseDao")
@Transactional
public class BaseDaoImpl<E> implements BaseDao<E> {

	@Autowired
	private SessionFactory sessionFactory;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected Session openSeeion() {
		return sessionFactory.openSession();
	}

	public void load(Object object, Serializable id) {
		getSession().load(object, id);
	}

	public Object load(String entityName, Serializable id) {
		return getSession().load(entityName, id);
	}

	public Serializable save(Object object) {
		return getSession().save(object);
	}

	public Serializable save(String entityName, Object object) {
		return getSession().save(entityName, object);
	}

	public void update(Object object) {
		getSession().update(object);
	}

	public void update(String entityName, Object object) {
		getSession().update(entityName, object);
	}

	public void saveOrUpdate(Object object) {
		getSession().saveOrUpdate(object);
	}

	public void saveOrUpdate(String entityName, Object object) {
		getSession().saveOrUpdate(entityName, object);
	}

	public Object merge(Object object) {
		return getSession().merge(object);
	}

	public Object merge(String entityName, Object object) {
		return getSession().merge(entityName, object);
	}

	public void persist(Object object) {
		getSession().persist(object);
	}

	public void persist(String entityName, Object object) {
		getSession().persist(entityName, object);
	}

	public void delete(Object object) {
		getSession().delete(object);
	}

	public void delete(String entityName, Object object) {
		getSession().delete(entityName, object);
	}
	/**
	 * 逻辑删除 根据id 设置isdeleted为1 id isdeleted 为必须参数
	 */
	public void deleteByIds(Class<E> entityType, boolean logicDelete,String userName, String... ids) {
		StringBuilder hql = new StringBuilder();
		if (logicDelete) {
			hql.append("UPDATE FROM ").append(entityType.getSimpleName()).append(" AS o SET o.isdeleted = 1, o.updateBy = '")
			.append(userName).append("', o.updateTime = now()")
			.append(" WHERE id IN (:list)");
		} else {
			hql.append("DELETE FROM ").append(entityType.getSimpleName()).append(" WHERE id IN (:list)");
		}
		Query query = getSession().createQuery(hql.toString());
		query.setParameterList("list", ids);
		query.executeUpdate();
	}
	
	public void deleteByIds(Class<E> entityType, String... ids) {
		StringBuilder hql = new StringBuilder();
			hql.append("UPDATE FROM ").append(entityType.getSimpleName()).append(" AS o SET o.isdeleted = 1")
			.append(" WHERE id IN (:list)");
		Query query = getSession().createQuery(hql.toString());
		query.setParameterList("list", ids);
		query.executeUpdate();
	}
	
	/**
	 * 老版本
	 * 逻辑删除 根据id 设置isdeleted为1 id isdeleted 为必须参数
	 */
	public void deleteByIds( boolean logicDelete,Class<E> entityType, String... ids) {
		StringBuilder hql = new StringBuilder();
		if (logicDelete) {
			hql.append("UPDATE FROM ").append(entityType.getSimpleName()).append(" AS o SET o.isdeleted = 1")
					.append(" WHERE id IN (:list)");
		} else {
			hql.append("DELETE FROM ").append(entityType.getSimpleName()).append(" WHERE id IN (:list)");
		}
		Query query = getSession().createQuery(hql.toString());
		query.setParameterList("list", ids);
		query.executeUpdate();
	}
	public void logicalDelete(Class<E> entityType,  String userName, String... ids){
		
	}

	public E get(Class<E> entityType, Serializable id) {
		return getSession().get(entityType, id);
	}

	public Object get(String entityName, Serializable id) {
		return getSession().get(entityName, id);
	}

	@SuppressWarnings("unchecked")
	public List<E> findByExample(E example) {
		Criteria criteria = getSession().createCriteria(example.getClass());
		criteria.add(Example.create(example));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<E> findByExample(E example, String sortName, String sortOrder) {
		Criteria criteria = getSession().createCriteria(example.getClass());
		criteria.add(Example.create(example));
		// 添加排序
		if (sortOrder.equals("asc")) {
			criteria.addOrder(Order.asc(sortName));
		} else {
			criteria.addOrder(Order.desc(sortName));
		}
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll(Class<E> entityType) {
		Query query = getSession().createQuery("from " + entityType.getSimpleName());
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<E> findAllNoDelete(Class<E> entityType) {
		Query query = getSession().createQuery("from " + entityType.getSimpleName() + " where isdeleted = 0 ");
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<E> findAllNoDeleteNoStop(Class<E> entityType) {
		Query query = getSession().createQuery("from " + entityType.getSimpleName() + " where isdeleted = 0 and status = 0 ");
		return query.list();
	}
	/**
	 * @param pageBean
	 * @param entityName
	 *            此参数已弃用
	 */
	public void findPage(Class<E> entityName, PageBean pageBean) {
		findPage(pageBean);
	}

	public void findPage(PageBean pageBean) {

		Map<String, Object> map = pageBean.getMap();
		Object exampleEntity = map.get(BaseDao.EXAMPLE_ENTITY);// 实体样本
		Object vague = map.get(BaseDao.VAGUE);// 模糊查询标识
		@SuppressWarnings("unchecked")
		Map<String, Boolean> order = (Map<String, Boolean>) map.get(BaseDao.ORDER_FIELD);// 排序字段 key="id" , value ： true
																							// 代表正序排序 false : 代表倒序

		if (vague == null) {
			vague = false;
		}

		Class<? extends Object> entityName = exampleEntity.getClass();

		Criteria criteria = getSession().createCriteria(entityName);

		if ((boolean) vague) {
			criteria.add(Restrictions.and(retPredicates(exampleEntity)));
		} else {
			criteria.add(Example.create(exampleEntity));
		}

		criteria.setProjection(Projections.rowCount());
		pageBean.setTotal(((Long) criteria.uniqueResult()).intValue());

		if (null != order) {
			Set<String> keys = order.keySet();
			for (String key : keys) {
				if (order.get(key)) {
					criteria.addOrder(Order.asc(key));
				} else {
					criteria.addOrder(Order.desc(key));
				}
			}
		}

		criteria.setFirstResult(pageBean.getStartNum()).setMaxResults(pageBean.getLimit()).setProjection(null);
		pageBean.setData(criteria.list());
	}

	/**
	 * 提取根据ID精确、其他模糊的查询Criterion[]
	 * 
	 * @param object
	 * @return
	 */
	public Criterion[] retPredicates(Object object) {
		List<Criterion> criterions = new ArrayList<>();
		Field[] fields = object.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);

			String name = fields[i].getName();
			Type type = fields[i].getGenericType();

			try {
				Object value = fields[i].get(object);
				if (value != null && !"".equals(value)) {// 反射的时候基本数据类型像Boolean Short等会自动赋值
					if (type.equals(String.class) && !name.toUpperCase().endsWith("ID")
							&& !name.toUpperCase().startsWith("IS")) {// 过滤以id结尾或者is开头的属性
						criterions.add(Restrictions.like(name, value.toString(), MatchMode.ANYWHERE));
					} else {
						if (type.equals(boolean.class) && name.equals("isdeleted") || name.equals("villageId")) {
							criterions.add(Restrictions.eq(name, value));
						}
					}
				}
			} catch (Exception e) {
				logger.error("模糊查询发生异常：" + object.getClass(), e);
			}

			fields[i].setAccessible(false);
		}
		return criterions.toArray(new SimpleExpression[criterions.size()]);
	}
}
