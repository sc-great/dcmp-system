package com.great.system.dao;

import java.util.List;

import com.great.base.dao.BaseDao;
import com.great.system.entity.SUserEntity;
import com.great.system.entity.SUserType;
import com.great.tool.PageBean;

public interface UserDao extends BaseDao {

	public void getResult(PageBean pageBean);

	public void getResult01(PageBean pageBean);

	public void changeStatus(String[] ids, String status);
	
	public void deleteUserTypeByUserId(String userId);

	public List<SUserType> getUserTypeByUserId(String userId);	

	public List<SUserEntity> getSelectList(String userType);
	public List<SUserEntity> getUserByPhone(String userPhone);
	public List<SUserEntity> getUserByLoginName(String loginName);

	public SUserEntity getByToken(String token);
}
