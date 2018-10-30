package com.zkt.project.biology.constant;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

import org.apache.commons.logging.Log;

import net.sf.json.JSONObject;


public class ReturnSimpleHandle implements Serializable {

	private static final long serialVersionUID = 5750308905528388534L;

	private String message;
	private Boolean success;
	private Object data;
	private Integer status = 200;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 创建一个返回服务端对象
	 * 
	 * @return
	 */
	public static ReturnSimpleHandle createServerHandle() {
		ReturnSimpleHandle handle = new ReturnSimpleHandle();
		handle.setMessage(SystemConstant.MESSAGE_SERVER_Z00);
		handle.setSuccess(true);
		handle.setStatus(SystemConstant.MESSAGE_SERVER_CODE_Z00);
		return handle;
	}

	/**
	 * 返回服务端处理异常json串
	 * 
	 * @return
	 */
	public static ReturnSimpleHandle createServerError(Log log,Exception e) {
		if (log != null && e != null) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
			log.warn(sw.toString());
		}
		ReturnSimpleHandle handle = new ReturnSimpleHandle();
		handle.setMessage(SystemConstant.ERROR_MESSAGE_SERVER_F01);
		handle.setSuccess(false);
		handle.setStatus(SystemConstant.ERROR_MESSAGE_SERVER_CODE_F01);
		handle.setData(new Object());
		return handle;
	}

	/**
	 * 返回服务端处理异常json串,自定义参数
	 * 
	 * @return
	 */
	public static ReturnSimpleHandle createServerError(String message, Integer code,Log log,Exception e) {
		if (log != null && e != null) {
			log.error(e.getMessage());
		}
		ReturnSimpleHandle handle = new ReturnSimpleHandle();
		handle.setSuccess(false);
		handle.setStatus(code);
		handle.setMessage(message);
		handle.setData(new Object());
		return handle;
	}
	
}
