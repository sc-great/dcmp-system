package com.great.manager.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.great.manager.entity.BPerson;

import java.util.Date;


/**
 * The persistent class for the s_mask_record database table.
 * 
 */
@Entity
@Table(name="s_mask_record")
@NamedQuery(name="SMaskRecord.findAll", query="SELECT s FROM SMaskRecord s")
public class SMaskRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="mask_id")
	private String maskId;

	@Column(name="client_id")
	private String clientId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="is_alarm")
	private int isAlarm;

	@Column(name="u_code")
	private String uCode;
	
	@Column(name="p_name")
	private String pName;

	@Column(name="pic_code")
	private String picCode;
	
	@Transient
	private BPerson person;
	
	@Transient
	private String clientName;

	public SMaskRecord() {
	}

	public String getMaskId() {
		return this.maskId;
	}

	public void setMaskId(String maskId) {
		this.maskId = maskId;
	}

	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getIsAlarm() {
		return this.isAlarm;
	}

	public void setIsAlarm(int isAlarm) {
		this.isAlarm = isAlarm;
	}

	public String getUCode() {
		return this.uCode;
	}

	public void setUCode(String uCode) {
		this.uCode = uCode;
	}

	public String getPName() {
		return pName;
	}

	public void setPName(String pName) {
		this.pName = pName;
	}

	public String getPicCode() {
		return this.picCode;
	}

	public void setPicCode(String picCode) {
		this.picCode = picCode;
	}

	public BPerson getPerson() {
		return person;
	}

	public void setPerson(BPerson person) {
		this.person = person;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

}