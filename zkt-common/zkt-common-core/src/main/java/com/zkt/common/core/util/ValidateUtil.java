package com.zkt.common.core.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ValidateUtil {

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断List集合是否为空
	 * 
	 * @param list
	 * @return
	 */
	public static boolean isEmpty(List<String> list) {
		if (list == null || list.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断str是否包含在list中
	 * 
	 * @param list
	 * @param str
	 * @return
	 */
	public static boolean isContain(List<String> list, String str) {
		if (isEmpty(list) || isEmpty(str)) {
			return false;
		}
		for (String string : list) {
			if (str.contains(string)) {
				return true;
			}
		}
		return false;
	}

	public static boolean absenceParam(String name, String mobile,
			String number, String time, String day, String service, String guide) {

		if (isEmpty(name)) {
			return true;
		}
		if (isEmpty(mobile)) {
			return true;
		}
		if (isEmpty(number)) {
			return true;
		}
		if (isEmpty(time)) {
			return true;
		}
		if (isEmpty(day)) {
			return true;
		}
		if (isEmpty(service)) {
			return true;
		}
		if (isEmpty(guide)) {
			return true;
		}
		return false;
	}

	public synchronized static String getValidateCode() {
		String[] beforeShuffle = new String[] { "0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9" };
		List<String> list = Arrays.asList(beforeShuffle);
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}
		String afterShuffle = sb.toString();
		String time = String.valueOf(System.currentTimeMillis());
		time = time.substring(time.length() - 4);
		String result = time + afterShuffle.substring(6, 10);
		return result;
	}

	/**
	 * 获取随机生成验证码（6位纯数字）
	 * 
	 * @return
	 */
	public static String gainValidateCode() {
		String[] beforeShuffle = new String[] { "0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9" };
		List<String> list = Arrays.asList(beforeShuffle);
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}
		String afterShuffle = sb.toString();
		return afterShuffle.substring(4, 10);
	}
}
