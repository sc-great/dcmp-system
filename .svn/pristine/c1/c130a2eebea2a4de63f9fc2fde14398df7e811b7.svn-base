package com.great.manager.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.base.service.impl.BaseServiceImpl;
import com.great.manager.dao.PersonDao;
import com.great.manager.entity.BPerson;
import com.great.manager.service.PersonService;

@Service
public class PersonServiceImpl extends BaseServiceImpl<BPerson> implements PersonService {

	@Autowired
	private PersonDao personDao;

	@Override
	public BPerson getPersonById(String uCode) {
		return this.personDao.getPersonById(uCode);
	}

	@Override
	public BPerson getPersonByCode(String uCode) {
		return this.personDao.getPersonByCode(uCode);
	}

	@Override
	public int countNoDeleteByOrgAndHealth(String chId) {
		return this.personDao.countNoDeleteByOrgAndHealth(chId);
	}

	@Override
	public List<BPerson> findNoDeleteAlarmByTime(String todayStr) {
		return this.personDao.findNoDeleteAlarmByTime(todayStr);
	}

	@Override
	public int countNoDelete() {
		return this.personDao.countNoDelete();
	}

	@Override
	public List<BPerson> getPersonByTime(String lastTime) {
		return this.personDao.getPersonByTime(lastTime);
	}

	@Override
	public List<BPerson> getPersonByOrgAndLikeName(String org, String userName) {
		return this.personDao.getPersonByOrgAndLikeName(org, userName);
	}
}
