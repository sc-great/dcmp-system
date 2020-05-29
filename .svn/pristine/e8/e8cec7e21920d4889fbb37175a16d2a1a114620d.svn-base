package com.great.system.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "s_user_role")
public class SUserRoleEntity {
	private String uRId;
	private String userId;
	private String roleId;
	private String createBy;
	private Date createTime;

	@Id
	@Column(name = "u_r_id")
	public String getuRId() {
		return uRId;
	}

	public void setuRId(String uRId) {
		this.uRId = uRId;
	}

	@Basic
	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Basic
	@Column(name = "role_id")
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SUserRoleEntity that = (SUserRoleEntity) o;
		return Objects.equals(uRId, that.uRId) && Objects.equals(userId, that.userId)
				&& Objects.equals(roleId, that.roleId) && Objects.equals(createBy, that.createBy)
				&& Objects.equals(createTime, that.createTime);
	}

	@Override
	public int hashCode() {

		return Objects.hash(uRId, userId, roleId, createBy, createTime);
	}
}
