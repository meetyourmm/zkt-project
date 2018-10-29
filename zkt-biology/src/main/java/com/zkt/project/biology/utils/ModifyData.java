package com.zkt.project.biology.utils;

//日期修改器
public class ModifyData {

	//报警时间追加间隔时间(分钟)
	//alarmInterval 报警时间
	//5 10 15 间隔时间
	
	public static long getNewAlarmInterval(String alarmInterval){
		
		long newAlarmInterval = 0;
		switch (alarmInterval) {
		case "5":
			newAlarmInterval = 300000;
			break;
		case "10":
			newAlarmInterval = 600000;
			break;
		case "15":
			newAlarmInterval = 900000;
			break;
		default:
			break;
		}
		return newAlarmInterval;		
	}
	
}
