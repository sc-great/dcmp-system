package com.great.manager.dao;

import java.util.List;
import java.util.Map;

import com.great.base.dao.BaseDao;
import com.great.manager.entity.BLeave;
import com.great.manager.entity.BPerson;
import com.great.system.entity.SDictionaryValEntity;
import com.great.tool.PageBean;

public interface LeaveDao extends BaseDao {
	
	 //初始化列表
	public List<BLeave> getSelectList();
	//按条件搜索列表
	public void getResult(PageBean pageBean);
	
	// 逻缉批量删除
	public void changeDelStatus(String[] ids, String status);
  
	//添加页面中:下拉员工姓名
	public List<BPerson> getPersonNameByAll();
	//添加页面中:下拉类型"leaveType"
	public List<SDictionaryValEntity> getDictValByTypeCodeId(String typeCode);
		
	//取一个对象
	public  BLeave getBLeave(String id );
	
	//员工主键+日期不能重复
	public List<BLeave> getBLeaveDuplicate(String pId,String leaveDate) ;
	
	public List<BLeave> findByParam(String org, String startTime, String endTime);
}
