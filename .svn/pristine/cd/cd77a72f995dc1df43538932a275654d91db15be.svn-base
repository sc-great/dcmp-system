package com.great.base.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.great.tool.PageBean;

public interface BaseDao<E> {

    public static final String EXAMPLE_ENTITY = "_e";

    public static final String VAGUE = "_v";

    public static final String ORDER_FIELD = "_o";

    public Session getSession();

    public void load(Object object, Serializable id);

    public Object load(String entityName, Serializable id);

    public Serializable save(Object object);

    public void update(Object object);

    public void saveOrUpdate(Object object);

    public Object merge(Object object);

    public void persist(Object object);

    public void delete(Object object);

    public void deleteByIds(Class<E> entityType, boolean logicDelete, String userName, String... ids);

    public void deleteByIds(Class<E> entityType, String... ids);

    public void deleteByIds(boolean logicDelete, Class<E> entityType, String... ids);

    public E get(Class<E> entityType, Serializable id);

    public Object get(String entityName, Serializable id);

    public List<E> findByExample(E example);

    public List<E> findByExample(E example, String sortName, String sortOrder);

    public List<E> findAll(Class<E> entityType);

    public List<E> findAllNoDelete(Class<E> entityType);

    public List<E> findAllNoDeleteNoStop(Class<E> entityType);

    /**
     * 默认不包含已删除数据 默认精确匹配 pageBean.getMap().put(BaseDao.VAGUE,true) pageBean.getMap().put(BaseDao.EXAMPLE_ENTITY,new
     * Object())
     *
     * @param pageBean
     * @param entityName
     */
    public void findPage(Class<E> entityName, PageBean pageBean);
}
