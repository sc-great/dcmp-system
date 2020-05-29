package com.great.base.service;

import java.io.Serializable;
import java.util.List;

import com.great.tool.PageBean;

public interface BaseService<E> {

	Serializable save(Object object);

	void delete(Object object);

	void deleteByIds(Class<E> entityType, boolean logicDelete,String userName, String... ids);
	void deleteByIds(Class<E> entityType, String... ids);
	
	void deleteByIds( boolean logicDelete,Class<E> entityType, String... ids);

	void update(Object object);

	E get(Class<E> entityType, Serializable id);

	List<E> findAll(Class<E> entityType);
	List<E> findAllNoDelete(Class<E> entityType);
	List<E> findAllNoDeleteNoStop(Class<E> entityType);
	void findPage(Class<E> entityName, PageBean pageBean);

	List<E> findByExample(E example);

	public List<E> findByExample(E example, String sortName, String sortOrder);

}
