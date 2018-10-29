package com.zkt.common.core.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ConverterUtil {

	/**
	 * 将Map<String, Object>对象转换成JavaBean对象
	 * 
	 * @param clz
	 *            JavaBean类型
	 * @param map
	 * @return
	 */
	public static <T> T map2Bean(Class<T> clz, Map<String, Object> map) {
		try {
			T t = clz.newInstance();
			BeanInfo beanInfo = Introspector.getBeanInfo(clz);
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (map.containsKey(propertyName)) {
					Object value = map.get(propertyName);
					// 得到property对应的setter方法
					Method setter = descriptor.getWriteMethod();
					if(value!=null){
						setter.invoke(t, value);
					}
				}
			}
			return t;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将List<Map<String, Object>>集合转换成List<JavaBean>集合
	 * 
	 * @param clz
	 *            JavaBean类型
	 * @param list
	 * @return
	 */
	public static <T> List<T> listMap2BeanList(Class<T> clz,
			List<Map<String, Object>> list) {
		List<T> resultList = new ArrayList<T>();
		for (Map<String, Object> map : list) {
			T t = map2Bean(clz, map);
			if (t != null) {
				resultList.add(t);
			}
		}
		return resultList;
	}

	/**
	 * Map转JSONObject
	 * 
	 * @param map
	 * @return
	 */
	public static JSONObject map2JsonObject(Map<String, Object> map) {
		return JSONObject.fromObject(map);
	}

	/**
	 * 将List<Map<String, Object>>集合转换成JSONArray
	 * 
	 * @param list
	 * @return
	 */
	public static JSONArray listMap2JsonArray(List<Map<String, Object>> list) {
		JSONArray array = new JSONArray();
		for (Map<String, Object> map2 : list) {
			array.add(map2JsonObject(map2));
		}
		return array;
	}

	/**
	 * JavaBean转JSONObject
	 * 
	 * @param t
	 * @return
	 */
	public static <T> JSONObject bean2JsonObject(T t) {
		return JSONObject.fromObject(t);
	}

	/**
	 * JavaBean集合转JSONArray
	 * 
	 * @param list
	 * @return
	 */
	public static <T> JSONArray listBean2JsonArray(List<T> list) {
		JSONArray array = new JSONArray();
		for (T t : list) {
			array.add(bean2JsonObject(t));
		}
		return array;
	}

}
