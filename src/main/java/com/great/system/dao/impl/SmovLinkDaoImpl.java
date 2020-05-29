package com.great.system.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.great.base.dao.impl.BaseDaoImpl;
import com.great.system.dao.SmovLinkDao;
import com.great.system.entity.SMovLink;
import com.great.system.entity.SMovLinkVo;

/**
 * @author LUOCHENG
 * 用户数据访问接口实现
 */
@Repository
public class SmovLinkDaoImpl extends BaseDaoImpl implements SmovLinkDao {

	//查找存在的视频记录
	public SMovLinkVo getSMovLinkByAreaId(String areaId) {
		
		SMovLinkVo rmv=new SMovLinkVo();
		try {
		
			Criteria criteria = getSession().createCriteria(SMovLink.class);
			criteria.add(Restrictions.and(Restrictions.eq("isdeleted", "0")));
		    List<SMovLink>	list=criteria.list();
		    
			Iterator it = list.iterator();
			int i=0;
			while (it.hasNext()) { //已定每个防区限两条视频
			    SMovLink mk = (SMovLink) it.next(); 
				if(i==0){				 
				  rmv.setId(mk.getID());
				  rmv.setAreaId(mk.getAreaId());				
				  rmv.setFirm(mk.getFirm());
				  rmv.setIpAddr(mk.getIpAddr());
				  rmv.setMovName(mk.getMovName());
				  rmv.setPassWord(mk.getPassWord());
				  rmv.setPort(mk.getPort());
				  rmv.setUserName(mk.getUserName());				  
				}else if(i==1){
				  rmv.setId2(mk.getID());
				  rmv.setAreaId2(mk.getAreaId());				
				  rmv.setFirm2(mk.getFirm());
				  rmv.setIpAddr2(mk.getIpAddr());
				  rmv.setMovName2(mk.getMovName());
				  rmv.setPassWord2(mk.getPassWord());
				  rmv.setPort2(mk.getPort());
				  rmv.setUserName2(mk.getUserName());	     	
				}else {
				   break;
				}
				i++;				
				
			}
		} catch (Exception e) {
			System.out.println("查找存在的视频记录错误信息:" + e.getMessage());
		}
		return rmv;
	   	
	}
	

}
