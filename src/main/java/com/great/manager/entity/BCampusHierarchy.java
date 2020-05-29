package com.great.manager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * BCampusHierarchy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "b_campus_hierarchy")
public class BCampusHierarchy implements java.io.Serializable {

	private String chId;
	private String chName;
	private BCampusHierarchy parentOrg;
	private String createBy;
	private Date createTime;
	private String updateBy;
	private Date updateTime;
	private Integer status;
	private Boolean isdeleted;
	private String chCode;
	private String chType;
	private String master;
	private String masterPhone;
	private Integer extInt;
	private String extText;

	/** default constructor */
	public BCampusHierarchy() {
	}

	/** minimal constructor */
	public BCampusHierarchy(String chId) {
		this.chId = chId;
	}

	/** full constructor */
	public BCampusHierarchy(String chId, String chName, BCampusHierarchy parentOrg, String createBy, Date createTime,
			String updateBy, Date updateTime, Integer status, Boolean isdeleted, String chCode, String chType,
			String master, String masterPhone, Integer extInt, String extText) {
		this.chId = chId;
		this.chName = chName;
		this.parentOrg = parentOrg;
		this.createBy = createBy;
		this.createTime = createTime;
		this.updateBy = updateBy;
		this.updateTime = updateTime;
		this.status = status;
		this.isdeleted = isdeleted;
		this.chCode = chCode;
		this.chType = chType;
		this.master = master;
		this.masterPhone = masterPhone;
		this.extInt = extInt;
		this.extText = extText;
	}

	// Property accessors
	@Id
	@Column(name = "ch_id", unique = true, nullable = false, length = 32)
	public String getChId() {
		return this.chId;
	}

	public void setChId(String chId) {
		this.chId = chId;
	}

	@Column(name = "ch_name", length = 64)
	public String getChName() {
		return this.chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="parent_id")
	@NotFound(action=NotFoundAction.IGNORE)
	public BCampusHierarchy getParentOrg() {
		return parentOrg;
	}

	public void setParentOrg(BCampusHierarchy parentOrg) {
		this.parentOrg = parentOrg;
	}

	@Column(name = "create_by", length = 32)
	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_by", length = 32)
	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@Column(name = "update_time", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "isdeleted")
	public Boolean getIsdeleted() {
		return this.isdeleted;
	}

	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	@Column(name = "ch_code", length = 100)
	public String getChCode() {
		return this.chCode;
	}

	public void setChCode(String chCode) {
		this.chCode = chCode;
	}

	@Column(name = "master", length = 64)
	public String getMaster() {
		return this.master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	@Column(name = "master_phone", length = 64)
	public String getMasterPhone() {
		return this.masterPhone;
	}

	public void setMasterPhone(String masterPhone) {
		this.masterPhone = masterPhone;
	}

	@Column(name = "ext_int")
	public Integer getExtInt() {
		return this.extInt;
	}

	public void setExtInt(Integer extInt) {
		this.extInt = extInt;
	}

	@Column(name = "ext_text", length = 65535)
	public String getExtText() {
		return this.extText;
	}

	public void setExtText(String extText) {
		this.extText = extText;
	}
	
	@Column(name = "ch_type", length = 65535)
	public String getChType() {
		return chType;
	}

	public void setChType(String chType) {
		this.chType = chType;
	}
}