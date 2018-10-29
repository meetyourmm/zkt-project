package com.zkt.project.biology.constant;

/**
 * 定值静态变量
 * 
 * @author
 *
 */
public class Constant {

	public static final String INIT_PWD = "111111";

	/* 上传图片类型 */
	public static final String PICTURE_TYPE_CAGE = "1";//装箱照片
	public static final String PICTURE_TYPE_CAR = "2";//车辆照片
	public static final String PICTURE_TYPE_RECEIVE = "3";//接收照片
	public static final String PICTURE_TYPE_PROBLEM = "4";//异常照片
	
	/* 运输异常种类 */
	public static final String TLIMITUP_ERROR = "1";//温度上限异常
	public static final String TLIMITDOWN_ERROR = "2";//温度下限异常
	public static final String HLIMITUP_ERROR = "3";//湿度上限异常
	public static final String HLIMITDOWN_ERROR = "4";//湿度下限异常
	public static final String CAGE_OPEN_ERROR = "5";//箱体非正常打开
	public static final String ARRIVETIME_ERROR = "6";//预计时间未到达
	public static final String ELECTRICITY_ERROR = "7";//电池电量过低
	public static final String RETURN_TO_ZERO = "0";//异常归零
	
	/* 订单号前缀 */
	public static final String PRE_ORDER = "D";//订单

	/* 订单下单分类 */
	public static final String ORDER_CLASSIFY_PC = "1";//PC端录入
	public static final String ORDER_CLASSIFY_WEIXIN = "2";//微信录入

	/* 箱体状态 */
	public static final String CAGESTATE_FREE = "0";//空闲
	public static final String CAGESTATE_ORDER = "1";//已绑定订单
	public static final String CAGESTATE_TRANSPORT = "2";//在途
	public static final String CAGESTATE_FAULT = "3";//故障

	/* 用户类型 */
	public static final String USER_TYPE_BOSS = "1";//总公司
	public static final String USER_TYPE_CITY = "2";//市监管部门
	public static final String USER_TYPE_AREA = "3";//区监管部门
	public static final String USER_TYPE_HOSPITAL = "4";//医院
	public static final String USER_TYPE_EMPLOY = "5";//医院员工
	public static final String USER_TYPE_TRANSPORT = "6";//运输方
	public static final String USER_TYPE_TRANSPORTEMPLOY = "7";//运输方员工
	public static final String USER_TYPE_BOSSEMPLOY = "8";//总公司员工
	public static final String USER_TYPE_HOSPITALWHITE = "9";//医院白卡账号

	/* 用户状态 */
	public static final String IS_USED_Y = "0";//启用
	public static final String IS_USED_N = "1";//停用
	public static final String IS_USED_D = "2";//删除

	/* 上班状态 */
	public static final String IS_WORK_Y = "0";//上班
	public static final String IS_WORK_N = "1";//下班

	/* 司机运输状态 */
	public static final String IS_TRANSPORT_Y = "0";//闲着
	public static final String IS_TRANSPORT_N = "1";//运输中

	/* 订单状态 */
	public static final String ORDER_STATUS_NEW = "1";//新建
	public static final String ORDER_STATUS_PACK = "2";//已装箱
	public static final String ORDER_STATUS_TRANSPORT = "3";//运输中
	public static final String ORDER_STATUS_FINISH = "4";//已完成
	public static final String ORDER_STATUS_XIETIAO = "5";//待仲裁
	
	/* 微信扫码箱体操作记录状态 */
	public static final String OPERATION_TYPE_NEW = "0";//创建订单
	public static final String OPERATION_TYPE_SENDOPEN = "1";//发货开箱
	public static final String OPERATION_TYPE_SAOMA = "2";//发出扫码
	public static final String OPERATION_TYPE_COLLECTOPEN = "3";//收货开箱
	public static final String OPERATION_TYPE_ABNORMAL = "4";//异常记录
	
	/* 异常件状态 */
	public static final String IS_PROBLEM_Y = "0";//是异常件
	public static final String IS_PROBLEM_N = "1";//不是异常件
	
	/* 处理状态 */
	public static final String TREATMENT_SITUATION_ING = "0";//处理中
	public static final String TREATMENT_SITUATION_OVER = "1";//处理完毕
	
	/* 审核状态 */
	public static final String CHECK_TYPE_WAIT = "0";//待审核 
	public static final String CHECK_TYPE_ING = "1";//审核中 
	public static final String CHECK_TYPE_OVER = "2";//审核完毕 

	
}
