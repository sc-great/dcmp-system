package com.great.system.entity;

import java.io.Serializable;
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
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "s_user")
public class SUserEntity implements Serializable {
	private String userId;
	private String areaId;
	private String userName;
	private String loginName;
	private String passwd;
	private String userSex;
	private String userJob;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private String userPhone;
	private Integer status;
	private String description;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;
	//private String createBy;
	private SUserEntity createUser;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	//private String updateBy;
	private SUserEntity updateBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	private Boolean isdeleted;
	private Integer extInt;
	private String extText;
	private String userPic;
	//private List<SMenuEntity> menus;
	private Boolean mobileOpen;
	private String mobileToken;
	private String mobileMac;
	private String mobileCode;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date mobileCodeTime;
	private String mobileGis;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date mobileGisLasttime;
	private Boolean isAdmin;

	@Id
	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "area_id", length = 32)

	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	@Basic
	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Basic
	@Column(name = "login_name")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Basic
	@Column(name = "passwd")
	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Basic
	@Column(name = "user_sex")
	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	@Basic
	@Column(name = "user_job")
	public String getUserJob() {
		return userJob;
	}

	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}

	@Basic
	@Column(name = "birthday")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Basic
	@Column(name = "user_phone")
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
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
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Basic
	@Column(name = "last_login_time")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="create_by")
	@NotFound(action=NotFoundAction.IGNORE)
	public SUserEntity getCreateUser() {
		return createUser;
	}

	public void setCreateUser(SUserEntity createUser) {
		this.createUser = createUser;
	}
	
	@Basic
	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="update_by")
	@NotFound(action=NotFoundAction.IGNORE)
	public SUserEntity getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(SUserEntity updateBy) {
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

	@Basic
	@Column(name = "ext_int")
	public Integer getExtInt() {
		return extInt;
	}

	public void setExtInt(Integer extInt) {
		this.extInt = extInt;
	}

	@Basic
	@Column(name = "ext_text")
	public String getExtText() {
		return extText;
	}

	public void setExtText(String extText) {
		this.extText = extText;
	}

	@Basic
	@Column(name = "user_pic")
	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	@Column(name = "mobile_open")

	public Boolean getMobileOpen() {
		return this.mobileOpen;
	}

	public void setMobileOpen(Boolean mobileOpen) {
		this.mobileOpen = mobileOpen;
	}

	@Column(name = "mobile_token", length = 1000)

	public String getMobileToken() {
		return this.mobileToken;
	}

	public void setMobileToken(String mobileToken) {
		this.mobileToken = mobileToken;
	}

	@Column(name = "mobile_mac", length = 100)

	public String getMobileMac() {
		return this.mobileMac;
	}

	public void setMobileMac(String mobileMac) {
		this.mobileMac = mobileMac;
	}

	@Column(name = "mobile_code", length = 100)

	public String getMobileCode() {
		return this.mobileCode;
	}

	public void setMobileCode(String mobileCode) {
		this.mobileCode = mobileCode;
	}

	@Column(name = "mobile_code_time", length = 19)

	public Date getMobileCodeTime() {
		return this.mobileCodeTime;
	}

	public void setMobileCodeTime(Date mobileCodeTime) {
		this.mobileCodeTime = mobileCodeTime;
	}

	@Column(name = "mobile_gis", length = 100)

	public String getMobileGis() {
		return this.mobileGis;
	}

	public void setMobileGis(String mobileGis) {
		this.mobileGis = mobileGis;
	}

	@Column(name = "mobile_gis_lasttime", length = 19)

	public Date getMobileGisLasttime() {
		return this.mobileGisLasttime;
	}

	public void setMobileGisLasttime(Date mobileGisLasttime) {
		this.mobileGisLasttime = mobileGisLasttime;
	}

	@Column(name = "is_admin")

	public Boolean getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
