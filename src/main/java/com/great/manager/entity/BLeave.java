package com.great.manager.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.great.system.entity.SDictionaryValEntity;

/**
  * 请假换休记录表
  * @author yangxiaojun
  * 
  */
 
@Entity
@Table(name = "b_leave")
public class BLeave {
	private String id;  
	private String leaveDate;    //请假日期
	private String note;         //备注
	private SDictionaryValEntity  typeVal;  //类型（关联字典表）
	private BPerson person;                 //关联员工基本信息
	
	private Date createTime; //创建时间
	private Date updateTime; //更新时间
	
	private String isdeleted; //逻缉删除标记
	
	@Id
	@Column(name = "id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Basic
	@Column(name = "leave_date")
	public String getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}
	
	@Basic
	@Column(name = "note")
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public SDictionaryValEntity getTypeVal() {
		return typeVal;
	}
	public void setTypeVal(SDictionaryValEntity typeVal) {
		this.typeVal = typeVal;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public BPerson getPerson() {
		return person;
	}
	public void setPerson(BPerson person) {
		this.person = person;
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
	@Column(name = "update_time")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	@Basic
	@Column(name = "isdeleted")
	public String getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}
	@Override
	public String toString() {
		return "BLeave [id=" + id + ", leaveDate=" + leaveDate + ", note=" + note + ", typeVal=" + typeVal + ", person="
				+ person + ", createTime=" + createTime + ", updateTime=" + updateTime + ", isdeleted=" + isdeleted
				+ "]";
	}
	
	
}
