package com.great.manager.service;

import java.util.List;
import java.util.Map;

import com.great.base.service.BaseService;
import com.great.manager.entity.BCampusHierarchy;
import com.great.tool.PageBean;

public interface CampusOrgService extends BaseService<BCampusHierarchy> {

	public void getResult(PageBean pageBean);

	public void changeStatus(String[] ids, String status);
	public List<Map> getOrgTree();
	public List<BCampusHierarchy> findAll(Class<BCampusHierarchy> entityType);
}
