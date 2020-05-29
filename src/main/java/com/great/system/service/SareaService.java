package com.great.system.service;

import java.util.List;

import com.great.base.service.BaseService;
import com.great.system.entity.SArea;
import com.great.system.entity.SAreaHost;
import com.great.system.entity.SMovLinkVo;
import com.great.tool.PageBean;

public interface SareaService extends BaseService<SArea> {

	public void getResult(PageBean pageBean);
	
	public List<SArea> getSelectList();
	//批量启用/停用
	public void changeStatus(String[] ids, String status);
	// 逻缉批量删除
	public void changeDelStatus(String[] ids, String status);
	//下拉主机列表
	public List<SAreaHost> getSareaHostByAll();   
	public SAreaHost getSareaHost(String hostSelectId);
	public List<SArea>  getSAreaLoginName(String name);
	
	
	public SMovLinkVo getSMovLinkByAreaId(String areaId);
	
}
