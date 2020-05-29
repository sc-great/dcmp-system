package com.great.system.entity;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "s_dictionary")
public class SDictionaryEntity implements java.io.Serializable {

	// Fields

	private String dicId;
	private String dicName;
	private String dicCode;
	private Integer status;
	private Integer dicOrder;
	private String dicRemark;

	// Constructors

	/** default constructor */
	public SDictionaryEntity() {
	}

	/** minimal constructor */
	public SDictionaryEntity(String dicId) {
		this.dicId = dicId;
	}

	/** full constructor */
	public SDictionaryEntity(String dicId, String dicName, String dicCode, Integer status, Integer dicOrder,
			String dicRemark) {
		this.dicId = dicId;
		this.dicName = dicName;
		this.dicCode = dicCode;
		this.status = status;
		this.dicOrder = dicOrder;
		this.dicRemark = dicRemark;
	}

	// Property accessors
	@Id

	@Column(name = "dic_id", unique = true, nullable = false, length = 32)

	public String getDicId() {
		return this.dicId;
	}

	public void setDicId(String dicId) {
		this.dicId = dicId;
	}

	@Column(name = "dic_name", length = 64)

	public String getDicName() {
		return this.dicName;
	}

	public void setDicName(String dicName) {
		this.dicName = dicName;
	}

	@Column(name = "dic_code", length = 16)

	public String getDicCode() {
		return this.dicCode;
	}

	public void setDicCode(String dicCode) {
		this.dicCode = dicCode;
	}

	@Column(name = "status")

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "dic_order")

	public Integer getDicOrder() {
		return this.dicOrder;
	}

	public void setDicOrder(Integer dicOrder) {
		this.dicOrder = dicOrder;
	}

	@Column(name = "dic_remark", length = 1000)

	public String getDicRemark() {
		return this.dicRemark;
	}

	public void setDicRemark(String dicRemark) {
		this.dicRemark = dicRemark;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SDictionaryEntity that = (SDictionaryEntity) o;
		return Objects.equals(dicId, that.dicId) && Objects.equals(dicName, that.dicName)
				&& Objects.equals(dicCode, that.dicCode) && Objects.equals(status, that.status)&&
				Objects.equals(dicOrder, that.dicOrder)&&Objects.equals(dicRemark, that.dicRemark);
	}

	@Override
	public int hashCode() {

		return Objects.hash(dicId, dicName, dicCode, status,dicOrder,dicOrder);
	}
}
