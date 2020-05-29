package com.great.system.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "s_role")
public class SRoleEntity {
	private String roleId;
	private String roleName;
	private String description;
	private Integer status;
	private String createBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private String updateBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	private Boolean isdeleted;
	private Integer extInt;
	private String extText;
	private List<SRoleMenuEntity> rmList;

	@Id
	@Column(name = "role_id")
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Basic
	@Column(name = "role_name")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Basic
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SRoleEntity that = (SRoleEntity) o;
		return status == that.status && Objects.equals(roleId, that.roleId) && Objects.equals(roleName, that.roleName)
				&& Objects.equals(description, that.description) && Objects.equals(createBy, that.createBy)
				&& Objects.equals(createTime, that.createTime) && Objects.equals(updateBy, that.updateBy)
				&& Objects.equals(updateTime, that.updateTime) && Objects.equals(isdeleted, that.isdeleted)
				&& Objects.equals(extInt, that.extInt) && Objects.equals(extText, that.extText);
	}

	@Override
	public int hashCode() {

		return Objects.hash(roleId, roleName, description, status, createBy, createTime, updateBy, updateTime,
				isdeleted, extInt, extText);
	}
}
