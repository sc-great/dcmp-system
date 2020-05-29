package com.great.manager.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "c_tactics")
public class CTactics {
	private String tId;
	private String tType;
	private String tName;
	private Integer tOrder;
	private Integer status;
	private String createBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private String updateBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	private Boolean isdeleted;

	@Id
	@Column(name = "t_id")
	public String gettId() {
		return tId;
	}

	public void settId(String tId) {
		this.tId = tId;
	}

	@Basic
	@Column(name = "t_type")
	public String gettType() {
		return tType;
	}

	public void settType(String tType) {
		this.tType = tType;
	}

	@Basic
	@Column(name = "t_name")
	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	@Basic
	@Column(name = "t_order")
	public Integer gettOrder() {
		return tOrder;
	}

	public void settOrder(Integer tOrder) {
		this.tOrder = tOrder;
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

	// @Basic
	// @Column(name = "area_id")
	// public String getAreaId() {
	// return areaId;
	// }
	//
	// public void setAreaId(String areaId) {
	// this.areaId = areaId;
	// }

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CTactics cTactics = (CTactics) o;
		return Objects.equals(tId, cTactics.tId) && Objects.equals(tType, cTactics.tType)
				&& Objects.equals(tName, cTactics.tName) && Objects.equals(tOrder, cTactics.tOrder)
				&& Objects.equals(status, cTactics.status) && Objects.equals(createBy, cTactics.createBy)
				&& Objects.equals(createTime, cTactics.createTime) && Objects.equals(updateBy, cTactics.updateBy)
				&& Objects.equals(updateTime, cTactics.updateTime) && Objects.equals(isdeleted, cTactics.isdeleted);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tId, tType, tName, tOrder, status, createBy, createTime, updateBy, updateTime, isdeleted);
	}
}
