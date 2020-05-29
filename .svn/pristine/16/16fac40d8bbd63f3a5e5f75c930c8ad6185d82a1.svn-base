package com.great.system.service;

import java.util.List;

import com.great.base.service.BaseService;
import com.great.system.entity.SAreaHost;
import com.great.tool.PageBean;

public interface SareaHostService extends BaseService<SAreaHost> {

	public void getResult(PageBean pageBean);
	
	public List<SAreaHost> getSelectList();
	//批量启用/停用
	public void changeStatus(String[] ids, String status);
	// 逻缉批量删除
	public void changeDelStatus(String[] ids, String status);
	
	public SAreaHost getSareaHost(String hostSelectId);
	
	//主机名称重复
	public List<SAreaHost> getSreaHostName(String name) ;

	//主机编号重复
   public List<SAreaHost> getSreaHostCode(String code) ;
	
}
