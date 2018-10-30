package com.zkt.project.biology.utils;

import java.io.ByteArrayOutputStream;
import java.util.Set;

import javax.servlet.ServletInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zkt.project.biology.constant.ReturnSimpleHandle;

import net.sf.json.JSONObject;

public class ContainerContent {

	private static Log log = LogFactory.getLog(ContainerContent.class);

	/**
	 * 服务端，响应数据
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String response(ReturnSimpleHandle handle) throws Exception {
		JSONObject jsonObject = JSONObject.fromObject(handle);
		String jsonString = jsonObject.toString();
		log.warn("server response --> " + jsonString);
		return jsonString;
	}

	/**
	 * 切面层 : 读取body的参数
	 * @return
	 * @throws Exception
	 */
	public static JSONObject clientAopReceive() throws Exception {
		ServletInputStream is = SystemContent.getRequest().getInputStream();
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		while (is.read(b) != -1) {
			bout.write(b);
			b = new byte[1024];
		}
		byte[] array = bout.toByteArray();
		String result = new String(array).trim();
		is.close();
		bout.close();
		JSONObject jsonObject = JSONObject.fromObject(result.toString().replace("\n", ""));
		return jsonObject;
	}

	/**
	 * action层得到body请求参数,因为request作用域中的参数,只能读取一次
	 * @return
	 * @throws Exception
	 */
	public static JSONObject clientReceive() throws Exception {
		JSONObject jsonObject = (JSONObject) SystemContent.getRequest().getAttribute("json");
		return jsonObject;
	}

	/**
	 * 读取请求头的参数
	 * @return
	 * @throws Exception
	 */
	public static JSONObject clientWebReceive(){
		JSONObject params = new JSONObject();
		Set<String> names = SystemContent.getRequest().getParameterMap().keySet();
		String[] array = names.toArray(new String[0]);
		// 接收参数
		for (String name : array) {
				String value = SystemContent.getRequest().getParameter(name);
				params.put(name, value);
		}
		return params;
	}
	
}
