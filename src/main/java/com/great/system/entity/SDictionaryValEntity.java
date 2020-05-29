package com.great.system.entity;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "s_dictionary_val")
public class SDictionaryValEntity implements java.io.Serializable {

	// Fields

	private String dvId;
	private String dicId;
	private String dvName;
	private String dvValue;
	private Integer status;
	private Integer dvOrder;
	private String dvRemark;

	@Id
	@Column(name = "dv_id")
	public String getDvId() {
		return dvId;
	}

	public void setDvId(String dvId) {
		this.dvId = dvId;
	}

	@Basic
	@Column(name = "dic_id")
	public String getDicId() {
		return dicId;
	}

	public void setDicId(String dicId) {
		this.dicId = dicId;
	}

	@Basic
	@Column(name = "dv_name")
	public String getDvName() {
		return dvName;
	}

	public void setDvName(String dvName) {
		this.dvName = dvName;
	}

	@Basic
	@Column(name = "dv_value")
	public String getDvValue() {
		return dvValue;
	}

	public void setDvValue(String dvValue) {
		this.dvValue = dvValue;
	}

	@Basic
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "dv_order")

	public Integer getDvOrder() {
		return this.dvOrder;
	}

	public void setDvOrder(Integer dvOrder) {
		this.dvOrder = dvOrder;
	}

	@Column(name = "dv_remark", length = 1000)

	public String getDvRemark() {
		return this.dvRemark;
	}

	public void setDvRemark(String dvRemark) {
		this.dvRemark = dvRemark;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SDictionaryValEntity that = (SDictionaryValEntity) o;
		return Objects.equals(dvId, that.dvId) && Objects.equals(dicId, that.dicId)
				&& Objects.equals(dvName, that.dvName) && Objects.equals(dvValue, that.dvValue)
				&& Objects.equals(status, that.status)&&Objects.equals(dvOrder, that.dvOrder)&&Objects.equals(dvRemark, that.dvRemark);
	}

	@Override
	public int hashCode() {

		return Objects.hash(dvId, dicId, dvName, dvValue, status,dvOrder,dvRemark);
	}
}
