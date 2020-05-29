package com.great.base.service.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.base.dao.BaseDao;
import com.great.base.service.BaseService;
import com.great.tool.PageBean;

@Service(value = "baseService")
public class BaseServiceImpl<E> implements BaseService<E> {

	@Autowired
	protected BaseDao<E> baseDao;
	private static final boolean isdeleted = true;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Serializable save(Object object) {
		return baseDao.save(object);
	}

	@Override
	public void delete(Object object) {
		baseDao.delete(object);
	}

	@Override
	public void deleteByIds(Class<E> entityType, boolean logicDelete,String userName, String... ids) {
		baseDao.deleteByIds(entityType, logicDelete,userName, ids);
	}
	
	@Override
	public void deleteByIds(Class<E> entityType, String... ids) {
		baseDao.deleteByIds(entityType, ids);
	}
	
	@Override
	public void deleteByIds(boolean logicDelete,Class<E> entityType,  String... ids) {
		baseDao.deleteByIds(logicDelete,entityType,  ids);
	}
	
	@Override
	public void update(Object object) {
		baseDao.update(object);
	}

	@Override
	public E get(Class<E> entityType, Serializable id) {
		return baseDao.get(entityType, id);
	}

	@Override
	public List<E> findAll(Class<E> entityType) {
		return baseDao.findAll(entityType);
	}

	@Override
	public void findPage(Class<E> entityName, PageBean pageBean) {
		baseDao.findPage(entityName, pageBean);
	}

	public List<E> findByExample(E example) {
		return baseDao.findByExample(example);
	}

	public List<E> findByExample(E example, String sortName, String sortOrder) {
		return baseDao.findByExample(example, sortName, sortOrder);
	}

	@Override
	public List<E> findAllNoDelete(Class<E> entityType) {
		// TODO Auto-generated method stub
		return baseDao.findAllNoDelete(entityType);
	}
	
	@Override
	public List<E> findAllNoDeleteNoStop(Class<E> entityType) {
		// TODO Auto-generated method stub
		return baseDao.findAllNoDeleteNoStop(entityType);
	}

}
