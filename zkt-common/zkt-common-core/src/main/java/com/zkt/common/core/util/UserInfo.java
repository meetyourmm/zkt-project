package com.zkt.common.core.util;

import java.util.List;

/**
 * 用户对象
 */
public class UserInfo {

	// 用户ID
	private String userId;
	// 用户名
	private String userName;
	// 密码
	private String userPswd;
	// 用户类型
	private String userType;
	// 登录标记
	private boolean isLogin;
	// 缓存对象
	private Object user;
	// 有效期时间
	private long validityTime;
	
	private List<String> authRes;
	
	public List<String> getAuthRes() {
		return authRes;
	}

	public void setAuthRes(List<String> authRes) {
		this.authRes = authRes;
	}

	public long getValidityTime() {
		return validityTime;
	}

	public void setValidityTime(long validityTime) {
		this.validityTime = validityTime;
	}

	public Object getUser() {
		return user;
	}

	public void setUser(Object user) {
		this.user = user;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPswd() {
		return userPswd;
	}

	public void setUserPswd(String userPswd) {
		this.userPswd = userPswd;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

}
