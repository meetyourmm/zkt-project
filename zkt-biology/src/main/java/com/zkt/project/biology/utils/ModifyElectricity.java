package com.zkt.project.biology.utils;

//电量修改器
public class ModifyElectricity {

	//battery 上传的电池电压数据
	//fullCapacity 电池电压区间	415-365 (3.65V-4.15V)
	
	public static int getElectricity(double battery){
		
		double fullCapacity = 50;
		double difference = battery - 365;
		int batteryPercentage = (int) ((difference/fullCapacity)*100);
		if(batteryPercentage<0){
			batteryPercentage=0;
		}
		return batteryPercentage;
	}
	
}
