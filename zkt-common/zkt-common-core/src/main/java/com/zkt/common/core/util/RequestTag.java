package com.zkt.common.core.util;

public interface RequestTag {

	/**
	 * 成功标志
	 */
	int CODE_OK = 0;
	/**
	 * 失败标志
	 */
	int CODE_FAIL = 1;
	/**
	 * 账号冻结
	 */
	int CODE_IS_USED_N = 2;
	/**
	 * 账号删除
	 */
	int CODE_IS_USED_D = 3;
	/**
	 * app缺少参数
	 */
	int CODE_MISSING_PARAMETER = 100001;
	/**
	 * app参数错误
	 */
	int CODE_PARAMETER_ERROR = 100002;
	/**
	 * 服务器异常
	 */
	int SERVER_EXCEPTION = 200001;
	/**
	 * 数据存储失败标志
	 */
	int CODE_DB_FAIL = 200002;
}
