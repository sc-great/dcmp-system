package com.great.system.entity;

import java.util.Date;
/**
 * 页面展示的每个防区最多两条视频信息对象vo
 * @author Administrator
 *
 */

public class SMovLinkVo {


	private String  id;               //主健 id
	private String  movName;          //视频名称
	private String  ipAddr;           //ip地址	
	private String  port;             //端口
	private String  userName;         //帐号
	private String  passWord;         //密码
	private String isdeleted;    
	private Date createTime;   
	
	private String firm ;   //厂商
	private String note ;    //备注
	private String areaId ;  //所属周界防区    
	
	/**
	 * 对应一个页面中放第二条数据
	 */
	
	private String  id2;               //主健 id
	private String  movName2;          //视频名称
	private String  ipAddr2;           //ip地址	
	private String  port2;             //端口
	private String  userName2;         //帐号
	private String  passWord2;         //密码
	private String isdeleted2;    
	private Date createTime2;   
	
	private String firm2 ;   //厂商
	private String note2 ;    //备注
	private String areaId2 ;  //所属周界防区    
	

	public String getAreaId2() {
		return areaId2;
	}
	public void setAreaId2(String areaId2) {
		this.areaId2 = areaId2;
	}
	public String getMovName() {
		return movName;
	}
	public void setMovName(String movName) {
		this.movName = movName;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getFirm() {
		return firm;
	}
	public void setFirm(String firm) {
		this.firm = firm;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getMovName2() {
		return movName2;
	}
	public void setMovName2(String movName2) {
		this.movName2 = movName2;
	}
	public String getIpAddr2() {
		return ipAddr2;
	}
	public void setIpAddr2(String ipAddr2) {
		this.ipAddr2 = ipAddr2;
	}
	public String getPort2() {
		return port2;
	}
	public void setPort2(String port2) {
		this.port2 = port2;
	}
	public String getUserName2() {
		return userName2;
	}
	public void setUserName2(String userName2) {
		this.userName2 = userName2;
	}
	public String getPassWord2() {
		return passWord2;
	}
	public void setPassWord2(String passWord2) {
		this.passWord2 = passWord2;
	}
	public String getIsdeleted2() {
		return isdeleted2;
	}
	public void setIsdeleted2(String isdeleted2) {
		this.isdeleted2 = isdeleted2;
	}
	public Date getCreateTime2() {
		return createTime2;
	}
	public void setCreateTime2(Date createTime2) {
		this.createTime2 = createTime2;
	}
	public String getFirm2() {
		return firm2;
	}
	public void setFirm2(String firm2) {
		this.firm2 = firm2;
	}
	public String getNote2() {
		return note2;
	}
	public void setNote2(String note2) {
		this.note2 = note2;
	}
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getId2() {
		return id2;
	}
	public void setId2(String id2) {
		this.id2 = id2;
	}

}
