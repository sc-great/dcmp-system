package com.great.system.entity;

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

@Entity
//@Table(name = "system_log")
@Table(name = "s_log")
public class SLogEntity {
	private String logId;
	private String logType;
	private String logMsg;
	private String createBy;
	//private SUserEntity creatUser;
	private Date createTime;

	@Id
	@Column(name = "log_id")
	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	@Basic
	@Column(name = "log_type")
	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	@Basic
	@Column(name = "log_msg")
	public String getLogMsg() {
		return logMsg;
	}

	public void setLogMsg(String logMsg) {
		this.logMsg = logMsg;
	}


	@Basic
	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/*@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="create_by")
	@NotFound(action=NotFoundAction.IGNORE)
	public SUserEntity getCreatUser() {
		return creatUser;
	}

	public void setCreatUser(SUserEntity creatUser) {
		this.creatUser = creatUser;
	}*/
	@Basic
	@Column(name = "create_by")
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
}
