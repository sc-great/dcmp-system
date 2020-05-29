package com.great.system.dao;

import java.util.List;

import com.great.base.dao.BaseDao;
import com.great.system.entity.SAreaHost;
import com.great.tool.PageBean;

public interface SareaHostDao extends BaseDao {

	public void getResult(PageBean pageBean);

	
 	//启用停用
	public void changeStatus(String[] ids, String status);
	// 逻缉批量删除
	public void changeDelStatus(String[] ids, String status);
    //列表
	public List<SAreaHost> getSelectList();
	
	//取一个对象
	public  SAreaHost getSareaHost(String hostSelectId );
	//主机名称重复
	public List<SAreaHost> getSreaHostName(String name) ;
	
	//主机编号重复
   public List<SAreaHost> getSreaHostCode(String code) ;
}
