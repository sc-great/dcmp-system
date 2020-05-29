package com.great.tool;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceUtil {
	/**
	 * 替换<>''""%
	 * @param orgStr
	 * @return
	 */
	public static String reSpecStr(String orgStr) {
		if (null != orgStr && !"".equals(orgStr.trim())) {
			//不过滤./_:：  .坐标需要;/路径需要  _数据字典，-类型需要
	//		String regEx = "[\\s~·`!！@#￥$%^……&*（()）\\——\\=+【\\[\\]】｛{}｝\\|、\\\\；;：‘'“”\"，,《<。》>、？?]";
			String regEx = "[\\s~\\[\\]｛{}｝\\|\\\\‘'“”\"<>]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(orgStr);
			return m.replaceAll("");
		}
		return null;
	}
	//reSpecStr  replaceSpecStr

	/**
	 * 正则替换所有特殊字符
	 * 
	 * @param orgStr
	 * @return
	 */
	public static String replaceSpecStr(String orgStr) {
		if (null != orgStr && !"".equals(orgStr.trim())) {
			//不过滤./_:：  ,.坐标需要;/路径需要  _数据字典，-类型需要 ,
	//		String regEx = "[\\s~·`!！@#￥$%^……&*（()）\\——\\=+【\\[\\]】｛{}｝\\|、\\\\；;：‘'“”\"，,《<。》>、？?]";
			String regEx = "[\\s~（()）\\——\\=+【\\[\\]】\\|、\\\\；：‘'“”\"，《<。》>、？?]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(orgStr);
			return m.replaceAll("");
		}
		return null;
	}

	public static Object replaceSpecString(Map<String, Object> map, Object entity) {

		Field[] fields = entity.getClass().getDeclaredFields();

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + ",replace="+ ReplaceUtil.replaceSpecStr((String) entry.getValue()));
			entry.setValue(ReplaceUtil.replaceSpecStr((String) entry.getValue()));
		}
		for (int i = 0; i < fields.length; i++) {
			String nameinit = fields[i].getName(); // 获取属性名
			String name = nameinit;
			name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性名的首字母大写，方便获取set和get方法
			String type = fields[i].getGenericType().toString(); // 获取属性类型
			if (type.equals("class java.lang.String")) { // 如果类型是类，要加class
				Class[] parameterTypes = new Class[1];
				parameterTypes[0] = fields[i].getType();
				Method m;
				try {
					// 构造set方法
					m = entity.getClass().getMethod("set" + name, parameterTypes);
					for (Map.Entry<String, Object> entry : map.entrySet()) {
						if (entry.getKey().toString().equals(nameinit)) {
							// 调用set方法设置属性值
							m.invoke(entity, entry.getValue());
						}
					}
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} 
			}
		}
		return entity;
	}
}
