package com.great.manager.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.great.manager.entity.BPerson;

import java.util.Date;

@Entity
@Table(name="s_temp_record")
@NamedQuery(name="STemperatureRecord.findAll", query="SELECT t FROM STemperatureRecord t")
public class STemperatureRecord implements Serializable, Comparable<STemperatureRecord> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="temp_id")
	private String tempId;

	@Column(name="client_id")
	private String clientId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="is_alarm")
	private int isAlarm;

	@Column(name="pic_code")
	private String picCode;

	@Column(name="temperature")
	private float temperature;

	@Column(name="u_code")
	private String uCode;

	@Column(name="p_name")
	private String pName;

	@Transient
	private BPerson person;
	
	@Transient
	private String clientName;

	public String getTempId() {
		return this.tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
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

	public String getPicCode() {
		return this.picCode;
	}

	public void setPicCode(String picCode) {
		this.picCode = picCode;
	}

	public float getTemperature() {
		return this.temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public String getUCode() {
		return this.uCode;
	}

	public void setUCode(String uCode) {
		this.uCode = uCode;
	}

	public String getPName() {
		return this.pName;
	}

	public void setPName(String pName) {
		this.pName = pName;
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

	@Override
	public int compareTo(STemperatureRecord o) {
		if (this.createTime.compareTo(o.createTime) < 0) {
			return 1;
		} else if (this.createTime.compareTo(o.createTime) > 0) {
			return -1;
		}
		return 0;
	}
}