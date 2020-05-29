package com.great.manager.entity;

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

import com.great.system.entity.SDictionaryValEntity;

@Entity
@Table(name = "b_campus_hierarchy")
public class BCampusOrgEntity {
	private String chId;
	private String chName;
	//private String parentId;
	private BCampusOrgEntity parentOrg;
	private String createBy;
	private Date createTime;
	private String updateBy;
	private Date updateTime;
	private Integer chStatus;
	private Boolean isdeleted;
	private Integer extInt;
	private String extText;
	private String chCode;
	//private String chType;
	private SDictionaryValEntity dvEntity;
	private String master;
	private String masterPhone;

	@Id
	@Column(name = "ch_id")
	public String getChId() {
		return chId;
	}

	public void setChId(String chId) {
		this.chId = chId;
	}

	@Basic
	@Column(name = "ch_name")
	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
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
	@Column(name = "ch_status")
	public Integer getChStatus() {
		return chStatus;
	}

	public void setChStatus(Integer chStatus) {
		this.chStatus = chStatus;
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
	@Column(name = "ch_code")
	public String getChCode() {
		return chCode;
	}

	public void setChCode(String chCode) {
		this.chCode = chCode;
	}

	@Basic
	@Column(name = "master")
	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	@Basic
	@Column(name = "master_phone")
	public String getMasterPhone() {
		return masterPhone;
	}

	public void setMasterPhone(String masterPhone) {
		this.masterPhone = masterPhone;
	}
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="parent_id")
	@NotFound(action=NotFoundAction.IGNORE)
	public BCampusOrgEntity getParentOrg() {
		return parentOrg;
	}

	public void setParentOrg(BCampusOrgEntity parentOrg) {
		this.parentOrg = parentOrg;
	}
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ch_type")
	@NotFound(action=NotFoundAction.IGNORE)
	public SDictionaryValEntity getDvEntity() {
		return dvEntity;
	}

	public void setDvEntity(SDictionaryValEntity dvEntity) {
		this.dvEntity = dvEntity;
	}

}
