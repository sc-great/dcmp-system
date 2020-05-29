package com.great.manager.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.base.service.impl.BaseServiceImpl;
import com.great.manager.dao.LeaveDao;
import com.great.manager.entity.BLeave;
import com.great.manager.entity.BPerson;
import com.great.manager.service.LeaveService;
import com.great.system.entity.SDictionaryValEntity;
import com.great.tool.PageBean;

@Service
public class LeaveServiceImpl extends BaseServiceImpl<BLeave> implements LeaveService {

	@Autowired
	private LeaveDao leaveDao;
	
	@Override
	public void getResult(PageBean pageBean) {
		this.leaveDao.getResult(pageBean);
	}


	public List<BLeave> getSelectList(){
		return this.leaveDao.getSelectList();
	}

	@Override
	public void changeDelStatus(String[] ids, String status) {
		this.leaveDao.changeDelStatus(ids, status);
	}
	
	@Override
	public List<BPerson> getPersonNameByAll(){
		return leaveDao.getPersonNameByAll();
	}
	
	public List<SDictionaryValEntity> getDictValByTypeCodeId(String typeCode){		
	  return 	leaveDao.getDictValByTypeCodeId(typeCode);
	}
	
	public  BLeave getBLeave(String id ){		
		return leaveDao.getBLeave(id);
	}
	
	public List<BLeave> getBLeaveDuplicate(String pId,String leaveDate){		
		return leaveDao.getBLeaveDuplicate(pId,leaveDate);
	}


	@Override
	public Map<String, Map<String, String>> findByParam(String org, String startTime, String endTime) {
		List<BLeave> list = leaveDao.findByParam(org, startTime, endTime);
		Map<String, Map<String, String>> leaveMap = new HashMap<>();
		Map<String, String> map = null;
		String b_pid, b_date, b_val, b_name = "";
		for (BLeave b : list) {
			b_pid = b.getPerson().getPId();
			b_date = b.getLeaveDate();
			b_val = b.getTypeVal().getDvValue();
			b_name = b.getTypeVal().getDvName();
			if (leaveMap.containsKey(b_pid))
				map = leaveMap.get(b_pid);
			else
				map = new HashMap<>();
			map.put(b_date, b_val);
			map.put(b_val, b_name);
			leaveMap.put(b_pid, map);
		}
		return leaveMap;
	}
}
