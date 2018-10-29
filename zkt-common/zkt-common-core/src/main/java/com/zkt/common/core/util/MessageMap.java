package com.zkt.common.core.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 单例工具类，保存全局Message
 * @author
 * @date
 * 
 */
public class MessageMap {

	private static MessageMap messageMap = null;

	private Map<String, String> map = new HashMap<String, String>();

	// 构造私有化 单例
	private MessageMap() {
	}

	/**
	 * @Description: 
	 * @author
	 * @date
	 */
	public static MessageMap newInstance() {
		if (messageMap == null) {
			messageMap = new MessageMap();
		}
		return messageMap;
	}

	/**
	 * @Description: 
	 * @author
	 * @date
	 */
	public void addMes(String key, String mes) {
		this.map.put(key, mes);
	}

	/**
	 * @Description: 
	 * @author
	 * @date
	 */
	public String getMes(String key) {
		return this.map.get(key);
	}

}
