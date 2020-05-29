package com.great.manager.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;


/**
 * @author ZQQ
 * */
@Entity
@Table(name = "b_person")
public class BPersonInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system_uuid")
	@GenericGenerator(name = "system_uuid", strategy = "uuid")
	@Column(name = "p_id")
	private String pId;
	
	private int health;
	@Column(name = "id_card_no")
	private String idCardNo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_detection")
	private Date lastDetection;

	@Column(name = "last_temp")
	private float lastTemp;

	@Column(name = "org_id")
	private String orgId;

	@Column(name = "org_name")
	private String orgName;

	@Column(name = "p_name")
	private String pName;

	@Column(name = "p_phone")
	private String pPhone;

	@Column(name = "p_pic")
	private String pPic;

	@Column(name = "p_sex")
	private String pSex;

	private int status;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	private Date updateTime;

	@Column(name = "u_code")
	private String userCode;

	@Column(name = "verification_code")
	private String verificationCode;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public BPersonInfo() {
	}

	public String getPId() {
		return this.pId;
	}

	public void setPId(String pId) {
		this.pId = pId;
	}


	public int getHealth() {
		return this.health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getIdCardNo() {
		return this.idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}


	public Date getLastDetection() {
		return this.lastDetection;
	}

	public void setLastDetection(Date lastDetection) {
		this.lastDetection = lastDetection;
	}

	public float getLastTemp() {
		return this.lastTemp;
	}

	public void setLastTemp(float lastTemp) {
		this.lastTemp = lastTemp;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPName() {
		return this.pName;
	}

	public void setPName(String pName) {
		this.pName = pName;
	}

	public String getPPhone() {
		return this.pPhone;
	}

	public void setPPhone(String pPhone) {
		this.pPhone = pPhone;
	}

	public String getPPic() {
		return this.pPic;
	}

	public void setPPic(String pPic) {
		this.pPic = pPic;
	}

	public String getPSex() {
		return this.pSex;
	}

	public void setPSex(String pSex) {
		this.pSex = pSex;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

}