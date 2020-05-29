package com.great.manager.dao;

import java.util.List;

import com.great.base.dao.BaseDao;
import com.great.manager.entity.BPerson;
import com.great.system.entity.SDictionaryValEntity;
import com.great.tool.PageBean;

public interface PersonnelFileInfoDao extends BaseDao {

	public void getResult(PageBean pageBean);
	
	/***
	 * @author ZQQ
	 * @param pId
	 * 详细信息查看
	 * 
	 */
	public BPerson getPersonneValueById(String pId);
	
	
}
