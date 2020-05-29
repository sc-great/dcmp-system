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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "s_menu")
public class SMenuEntity{
	private String menuId;
	private String menuName;
	private String menuIcon;
	private String menuUrl;
	private Integer menuOrder;
	private Integer status;
	private String menuCode;
	//private String parentId;
	private SMenuEntity parentMenu;
	private String createBy;
	private Date createTime;
	private String updateBy;
	private Date updateTime;
	private Boolean isdeleted;
	private Integer extInt;
	private String extText;
	private String menuType;

	@Id
	@Column(name = "menu_id")
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Basic
	@Column(name = "menu_name")
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Basic
	@Column(name = "menu_icon")
	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	@Basic
	@Column(name = "menu_url")
	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	@Basic
	@Column(name = "menu_order")
	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
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
	@Column(name = "menu_code")
	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

//	@Basic
//	@Column(name = "parent_id")
//	public String getParentId() {
//		return parentId;
//	}
//
//	public void setParentId(String parentId) {
//		this.parentId = parentId;
//	}

	
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

	@Basic
	@Column(name = "update_by")
	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@Basic
	@Column(name = "update_time")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Basic
	@Column(name = "isdeleted")
	public Boolean getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	@Basic
	@Column(name = "ext_int")
	public Integer getExtInt() {
		return extInt;
	}

	public void setExtInt(Integer extInt) {
		this.extInt = extInt;
	}

	@Basic
	@Column(name = "ext_text")
	public String getExtText() {
		return extText;
	}

	public void setExtText(String extText) {
		this.extText = extText;
	}

	@Basic
	@Column(name = "menu_type")
	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="parent_id")
	@NotFound(action=NotFoundAction.IGNORE)
	public SMenuEntity getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(SMenuEntity parentMenu) {
		this.parentMenu = parentMenu;
	}

	
}
