package com.great.system.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "s_area_host")
public class SAreaHost {

	private String hostId;  //主机id
	private String name;    //主机名
	private String code;    //主机编号
	
	private Integer status; //状态(1启用,0停用)
	private String ipAddr;  //IP地址
	private String port;   //端口
	private String isdeleted; 
	private Date createTime;

	
	@Id
	@Column(name = "host_id")
	public String getHostId() {
		return hostId;
	}
	public void setHostId(String hostId) {
		this.hostId = hostId;
	}
	
	@Basic
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Basic
	@Column(name = "code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Basic
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Basic
	@Column(name = "ip_addr")
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	
	
	@Basic
	@Column(name = "port")
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}

	@Basic
	@Column(name = "isdeleted")
	public String getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	@Basic
	@Column(name = "create_time")	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
