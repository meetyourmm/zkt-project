package com.zkt.project.biology.utils;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SystemContent {

	//request
	private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) requestLocal.get();
	}
	public static void setRequest(HttpServletRequest request) {
		requestLocal.set(request);
	}
	
	//respose
	private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();
	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) responseLocal.get();
	}
	public static void setResponse(HttpServletResponse response) {
		responseLocal.set(response);
	}
	
	//session
	public static HttpSession getSession() {
		return (HttpSession) ((HttpServletRequest) requestLocal.get()).getSession();
	}
	
	//websocket session
	private static Map<String, HttpSession> wssMap = new Hashtable<String, HttpSession>();
	public static Map<String, HttpSession> getWssMap() {
		return wssMap;
	}
	public static HttpSession getWebSocketSession(String sessionId) {
		return wssMap.get(sessionId);
	}
	public static void putWebSocketSession(String sessionId, HttpSession session) {
		wssMap.put(sessionId, session);
	}
	public static void removeWebSocketSession(String sessionId) {
		wssMap.remove(sessionId);
	}
	
}
