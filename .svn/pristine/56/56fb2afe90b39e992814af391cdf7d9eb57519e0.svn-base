package com.great.tool;
 
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
 
public class ReflectCommon<T> {
 
	public Map test(T model) {
		Field[] fields = model.getClass().getDeclaredFields(); //获取说有属性，返回Field类型数组
		Map<String, Object> values = new HashMap<String, Object>();
 
		try {
			for (int i = 0; i < fields.length; i++) {
				String nameinit = fields[i].getName(); // 获取属性名
				String name = nameinit;
				name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性名的首字母大写，方便获取set和get方法
				String type = fields[i].getGenericType().toString(); // 获取属性类型
 
				if (type.equals("class java.lang.String")) {   //如果类型是类，要加class 并指定类路径
					Method m = model.getClass().getMethod("get" + name); // 构造get方法
					String value = (String) m.invoke(model); // 调用get方法获取属性值
					values.put(nameinit, value);
				}
//				if (type.equals("class java.lang.Integer")) {
//					Method m = model.getClass().getMethod("get" + name); // 构造get方法
//					Integer value = (Integer) m.invoke(model); // 调用get方法获取属性值
//					values.put(nameinit, value);
//				}
//				if (type.equals("int")) {
//					Method m = model.getClass().getMethod("get" + name); // 构造get方法
//					Integer value = (Integer) m.invoke(model); // 调用get方法获取属性值
//					values.put(nameinit, value);
//				}
//				if (type.equals("class java.lang.Boolean")) {
//					Method m = model.getClass().getMethod("get" + name); // 构造get方法
//					String value = (String) m.invoke(model); // 调用get方法获取属性值
//					values.put(nameinit, value);
//				}
//				if (type.equals("class java.util.Date")) {
//					Method m = model.getClass().getMethod("get" + name);
//					Date value = (Date) m.invoke(model);
//					values.put(nameinit, value);
//				}if (type.equals("class java.lang.Short")) {
//					Method m = model.getClass().getMethod("get" + name); // 构造get方法
//					String value = (String) m.invoke(model); // 调用get方法获取属性值
//					values.put(nameinit, value);
//				}
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		return values;
	}
 
}