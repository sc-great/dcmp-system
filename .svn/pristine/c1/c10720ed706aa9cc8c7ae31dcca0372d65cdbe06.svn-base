package com.great.system.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "s_area")
public class SArea {
	 
	private String areaId;  //防区id
	private String name;    //防区名
	private Integer status; //防区状态(1启用,0停用)	
	private String isdeleted; 
	private Date createTime;

	//private  String   machId; //主机id(多个房区对一个主机）
	private SAreaHost host;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mach_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public SAreaHost getHost() {
		return host;
	}
	public void setHost(SAreaHost host) {
		this.host = host;
	}
	
	@Id
	@Column(name = "area_id")	
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
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
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
