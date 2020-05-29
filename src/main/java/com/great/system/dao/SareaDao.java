package com.great.system.dao;

import java.util.List;

import com.great.base.dao.BaseDao;
import com.great.system.entity.SArea;
import com.great.system.entity.SAreaHost;
import com.great.system.entity.SMovLinkVo;
import com.great.tool.PageBean;

public interface SareaDao extends BaseDao {

	public void getResult(PageBean pageBean);

	
 	//启用停用
	public void changeStatus(String[] ids, String status);
	// 逻缉批量删除
	public void changeDelStatus(String[] ids, String status);
    //列表
	public List<SArea> getSelectList();
	//添加页面中:主机编号下拉内容
	public List<SAreaHost> getSareaHostByAll();
	//取一个对象
	public  SAreaHost getSareaHost(String hostSelectId );
	//防区名称重复
	public List<SArea> getSAreaLoginName(String name) ;
	
	public SMovLinkVo getSMovLinkByAreaId(String areaId);
}
