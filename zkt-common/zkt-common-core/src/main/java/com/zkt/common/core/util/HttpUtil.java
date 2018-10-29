package com.zkt.common.core.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

//import org.apache.log4j.Logger;

public class HttpUtil {

	/**
	 * LOG信息输出
	 */
//	protected final static Logger logger = Logger.getLogger(HttpUtil.class);

	/**
	 * 从HttpServletRequest中获取数据字符串
	 * 
	 * @param request
	 * @return
	 */
	private static String getBodyFromRequest(HttpServletRequest request) {
		ServletInputStream inputStream = null;
		ByteArrayOutputStream outStream = null;
		String inputstr = null;
		try {
			inputStream = request.getInputStream();
			outStream = new ByteArrayOutputStream();
			int len = -1;
			byte[] buffer = new byte[1024];
			while ((len = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			inputstr = new String(outStream.toByteArray(), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (outStream != null) {
				try {
					outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return inputstr;
	}

	/**
	 * 读取客户端发送过来的参数
	 * 
	 * @param request
	 * @return
	 */
	public static JSONObject readJsonFromRequest(HttpServletRequest request) {

		StringBuffer sb = new StringBuffer("");
		String param = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "utf-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();
			param = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		JSONObject mParamJSONO = JSONObject.fromObject(param);

		return mParamJSONO;
	}

	/**
	 * 获取request中的参数（以文件流方式获取）
	 * 
	 * @param request
	 * @return
	 */
	public static JSONObject readRequestData(HttpServletRequest request) {

		String body = getBodyFromRequest(request);
		JSONObject param = JSONObject.fromObject(body);
		return param;
	}

	/**
	 * @param request
	 * @return
	 */
	public static JSONObject readParamFromRequest(HttpServletRequest request) {

		JSONObject param = new JSONObject();
		Map<String, String[]> map = request.getParameterMap();
		Set<Entry<String, String[]>> set = map.entrySet();
		Iterator<Entry<String, String[]>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, String[]> entry = it.next();
			param.put(entry.getKey(), entry.getValue());
			param.put(entry.getKey(), entry.getValue()[0]);
		}
		return param;
	}

	/**
	 * 获取request中的参数（针对表单提交方式）
	 * 
	 * @param request
	 * @return
	 */
	public static JSONObject getRequestParam(HttpServletRequest request) {
		JSONObject param = readParamFromRequest(request);
		return param.optJSONObject("data");
	}

	/**
	 * 参数字符串转换成JSON对象
	 * 
	 * @param request
	 * @return
	 */
	public static JSONObject Str2Json(String data) {
		JSONObject param = JSONObject.fromObject(data);
		return param;
	}

	/**
	 * 向客户端发送数据
	 * 
	 * @param response
	 * @param data
	 */
	public static void responseData(HttpServletResponse response,
			JSONObject data) {

		responseData(response, data == null ? "" : data.toString());
	}

	/**
	 * 向客户端发送数据
	 * 
	 * @param response
	 * @param data
	 */
	public static void responseData(HttpServletResponse response, String data) {
//		logger.info("response data:" + data);
		response.setHeader("Content-Type", "text/plain;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(data);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
