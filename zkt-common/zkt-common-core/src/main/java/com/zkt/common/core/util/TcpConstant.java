package com.zkt.common.core.util;

/**
 * 通信静态变量
 * 
 * @author
 *
 */
public class TcpConstant {
	
	//设备监控、时间设置、上传时间间隔设置命令
	public static final String CHECK = " 06 ";
	
	public static final String CHECK_OK = " 06 ";
	
	public static final String CHECK_ERROR = " E6 ";
	
	//设备参数上传时间间隔(单位：s)设置
	public static final String UPLOAD_INTERVAL = " 01 ";
	
	public static final String UPLOAD_INTERVAL_OK = " 01 ";
	
	public static final String UPLOAD_INTERVAL_ERROR = " E1 ";
			
	
	//时间校准
	public static final String UPLOAD_TIME = " 04 ";
	
	public static final String UPLOAD_TIME_OK = " 04 ";
	
	public static final String UPLOAD_TIME_ERROR = " E4 ";
		
	
	//设备开锁命令
	public static final String OPEN_CAGE = " 02 01 ";
	
	public static final String OPEN_CAGE_OK = " 02 01 ";
	
	public static final String OPEN_CAGE_ERROR = " E2 ";
	

	//设备监控命令
	public static final String START_MONITOR = " 05 01 ";
	
	public static final String START_MONITOR_OK = " 05 01 ";
	
	public static final String OVER_MONITOR = " 05 00 ";
	
	public static final String OVER_MONITOR_OK = " 05 00 ";
	
	public static final String MONITOR_ERROR = " E5 ";
	
	
	//设备上传参数
	public static final String GET_DATA = " 11 ";
	
	public static final String GET_DATA_OK = " 11 ";
	
	public static final String GET_DATA_ERROR = " F1 ";
	
	
	//设备上传锁被非法打开报警信息
	public static final String ILLEGAL_OPEN = " 12 01 ";
	
	public static final String ILLEGAL_OPEN_OK = " 12 ";
	
	public static final String ILLEGAL_OPEN_ERROR = " F2 ";
	
}
