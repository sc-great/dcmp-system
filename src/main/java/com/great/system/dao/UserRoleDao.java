package com.great.system.dao;

import com.great.base.dao.BaseDao;
import com.great.tool.PageBean;

public interface UserRoleDao extends BaseDao {

	public void deleteByUserId(String userId);
}
