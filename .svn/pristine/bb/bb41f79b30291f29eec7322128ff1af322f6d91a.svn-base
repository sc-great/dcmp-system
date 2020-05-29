package com.great.system.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.great.base.dao.impl.BaseDaoImpl;
import com.great.system.dao.UserRoleDao;

/**
 * @author LUOCHENG
 */
@Repository
public class UserRoleDaoImpl extends BaseDaoImpl implements UserRoleDao {

	@Override
	public void deleteByUserId(String userId) {
		StringBuilder hql = new StringBuilder();
		hql.append("DELETE FROM SUserRoleEntity ").append(" WHERE userId = :userId");
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("userId", userId);
		query.executeUpdate();
	}

}
