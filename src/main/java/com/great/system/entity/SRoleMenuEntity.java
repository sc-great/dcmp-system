package com.great.system.entity;


import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "s_role_menu")
public class SRoleMenuEntity {
	private String rMId;
	private String roleId;
	//修改为实体对象映射
	//private String menuId;
	private SMenuEntity smenu;
	private String createBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@Id
	@Column(name = "r_m_id")
	public String getrMId() {
		return rMId;
	}

	public void setrMId(String rMId) {
		this.rMId = rMId;
	}

	@Basic
	@Column(name = "role_id")
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

//	@Basic
//	@Column(name = "menu_id")
//	public String getMenuId() {
//		return menuId;
//	}
//
//	public void setMenuId(String menuId) {
//		this.menuId = menuId;
//	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="menu_id")
	public SMenuEntity getSmenu() {
		return smenu;
	}

	public void setSmenu(SMenuEntity smenu) {
		this.smenu = smenu;
	}
	
	@Basic
	@Column(name = "create_by")
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
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
