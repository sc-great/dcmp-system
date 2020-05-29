package com.great.resource;

public final class StaticData {

	private StaticData() {
	}

	public static final String USER_SESSION = "loginUser";

	public static final String USER_DEFPWD = "123456";

	public static final String IMAGE_UPLOAD_PATH = "/uploadImage/";
	// public static final String WebAdminUrl = "http://172.16.12.124:8080";
	// public static final String WebAdminUrl = "http://47.108.68.150:8092";

	/**
	 * 日志类型
	 */
	public static final String LOG_TYPE_DB = "db";
	public static final String LOG_TYPE_DO = "do";
	public static final String LOG_TYPE_LOGIN = "login";

	/**
	 * 用户状态
	 */
	public static final int STATUS_UNUSE = 1;
	public static final int STATUS_NORMAL = 0;

	/**
	 * 考勤统计项
	 * 
	 */

	public static final String Absenteeism = "absenteeism";// 旷工
	public static final String Late = "late";// 迟到
	public static final String LeaveEarly = "leaveEarly";// 早退
}
