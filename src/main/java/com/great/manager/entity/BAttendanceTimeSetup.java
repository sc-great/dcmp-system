package com.great.manager.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * @author zqq
 * 设置出勤时间
 */
@Entity
@Table(name="b_attendance_time_setup")
public class BAttendanceTimeSetup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="a_id")
	private String aId;

	@Column(name="end_time")
	private String endTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	@Column(name="work_time")
	private String workTime;

	public BAttendanceTimeSetup() {
	}

	public String getAId() {
		return this.aId;
	}

	public void setAId(String aId) {
		this.aId = aId;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getWorkTime() {
		return this.workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

}