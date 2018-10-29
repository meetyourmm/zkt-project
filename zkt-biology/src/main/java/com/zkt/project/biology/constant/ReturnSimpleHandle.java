package com.zkt.project.biology.constant;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

import org.apache.commons.logging.Log;

import net.sf.json.JSONObject;


public class ReturnSimpleHandle implements Serializable {

	private static final long serialVersionUID = 5750308905528388534L;

	private String message;
	private Boolean isSuccess;
	private Object data;
	private String code;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 创建一个返回服务端对象
	 * 
	 * @return
	 */
	public static ReturnSimpleHandle createServerHandle() {
		ReturnSimpleHandle handle = new ReturnSimpleHandle();
		handle.setMessage(SystemConstant.MESSAGE_SERVER_Z00);
		handle.setIsSuccess(true);
		handle.setCode(SystemConstant.MESSAGE_SERVER_CODE_Z00);
		return handle;
	}

	/**
	 * 返回服务端处理异常json串
	 * 
	 * @return
	 */
	public static String createServerError(Log log,Exception e) {
		if (log != null && e != null) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
			log.warn(sw.toString());
		}
		ReturnSimpleHandle handle = new ReturnSimpleHandle();
		handle.setMessage(SystemConstant.ERROR_MESSAGE_SERVER_F01);
		handle.setIsSuccess(false);
		handle.setCode(SystemConstant.ERROR_MESSAGE_SERVER_CODE_F01);
		handle.setData(new Object());
		String string = JSONObject.fromObject(handle).toString();
		return string;
	}

	/**
	 * 返回服务端处理异常json串,自定义参数
	 * 
	 * @return
	 */
	public static String createServerError(String message, String code,Log log,Exception e) {
		if (log != null && e != null) {
			log.error(e.getMessage());
		}
		ReturnSimpleHandle handle = new ReturnSimpleHandle();
		handle.setIsSuccess(false);
		handle.setCode(code);
		handle.setMessage(message);
		handle.setData(new Object());
		String string = JSONObject.fromObject(handle).toString();
		return string;
	}
	
}
