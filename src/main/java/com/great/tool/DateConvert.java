package com.great.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvert {

	private DateConvert() {
		super();
	}

	/**
	 * 字符串转日期
	 * 
	 * @param dateString
	 *            日期字符串
	 * @param pattern
	 *            日期格式 2018-01-01 13:00:00 yyyy-MM-dd HH:mm:ss
	 * @return
	 * @throws ParseException
	 */
	public static Date String2Date(String dateString, String pattern) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.parse(dateString);
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            日期格式：年yyyy月MM日dd小时(24)HH(12)hh分钟mm秒ss毫秒SSS 英文星期EEE英文月份MMM上下午a
	 * @return
	 * @throws ParseException
	 */

	public static String Date2String(Date date, String pattern) {
		if (pattern == null || pattern.equals("")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}
}
