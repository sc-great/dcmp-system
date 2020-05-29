package com.great.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "s_user_type")

public class SUserType implements java.io.Serializable {

	// Fields

	private String utId;
	private String userId;
	private String typeCode;

	// Constructors

	/** default constructor */
	public SUserType() {
	}

	/** minimal constructor */
	public SUserType(String utId) {
		this.utId = utId;
	}

	/** full constructor */
	public SUserType(String utId, String userId, String typeCode) {
		this.utId = utId;
		this.userId = userId;
		this.typeCode = typeCode;
	}

	// Property accessors
	@Id

	@Column(name = "ut_id", unique = true, nullable = false, length = 32)

	public String getUtId() {
		return this.utId;
	}

	public void setUtId(String utId) {
		this.utId = utId;
	}

	@Column(name = "user_id", length = 32)

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "type_code", length = 32)

	public String getTypeCode() {
		return this.typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

}