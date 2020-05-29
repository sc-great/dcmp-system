package com.great.manager.dao;

import java.util.List;

import com.great.base.dao.BaseDao;
import com.great.manager.entity.BCampusHierarchy;
import com.great.tool.PageBean;

public interface CampusOrgDao extends BaseDao<BCampusHierarchy> {

	public void getResult(PageBean pageBean);

	public void changeStatus(String[] ids, String status);
	public List<BCampusHierarchy> getOrgByParent(String parentId);

	
	@Override
	public List<BCampusHierarchy> findAll(Class<BCampusHierarchy> entityType);
}
