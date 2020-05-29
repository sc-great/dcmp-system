package com.great.manager.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.great.base.service.impl.BaseServiceImpl;
import com.great.manager.dao.CampusOrgDao;
import com.great.manager.entity.BCampusHierarchy;
import com.great.manager.service.CampusOrgService;
import com.great.tool.PageBean;

@Service
public class CampusOrgServiceImpl extends BaseServiceImpl<BCampusHierarchy> implements CampusOrgService {

	@Autowired
	private CampusOrgDao orgDao;

	@Override
	public void getResult(PageBean pageBean) {
		this.orgDao.getResult(pageBean);
	}

	@Override
	public void changeStatus(String[] ids, String status) {
		this.orgDao.changeStatus(ids, status);
	}

	@Override
	public List<Map> getOrgTree() {
		List<BCampusHierarchy> orgs=  this.orgDao.getOrgByParent("");
		Map<String,Object> orgTree = new HashMap<String, Object>();
		List<Map> orgList = new ArrayList<Map>();
		if(orgs!=null&&orgs.size()>0){
			for(BCampusHierarchy org : orgs){
				Map<String,Object> dt = new HashMap<String, Object>();
				dt.put("id", org.getChId());
				dt.put("name", org.getChName());
				dt.put("children", creatChildrenTree(org.getChId()));
				orgList.add(dt);
			}
			orgTree.put("children", orgList);
		}
		return orgList;
	}
	private List<Map> creatChildrenTree(String parentId){
		List<BCampusHierarchy> orgs =  this.orgDao.getOrgByParent(parentId);
		List<Map> orgList = new ArrayList<Map>();
		if(orgs!=null&&orgs.size()>0){
			 for(BCampusHierarchy org:orgs){
				Map<String,Object> dt = new HashMap<String, Object>();
				dt.put("id", org.getChId());
				dt.put("name", org.getChName());
				dt.put("children", creatChildrenTree(org.getChId()));
				orgList.add(dt);
			 }
		 }		
		return orgList;
	}
	@Override
	public List<BCampusHierarchy> findAll(Class<BCampusHierarchy> entityType) {
		return orgDao.findAllNoDelete(entityType);
	}
}
