package com.zkt.project.biology.service;

import org.springframework.stereotype.Service;

import com.zkt.common.core.util.DateUtils;
import com.zkt.common.core.util.SerialNumber;
import com.zkt.project.biology.service.CommonService;

//生成各种编号
@Service
public class CommonService {
	
	public String createSerialNumber(String sequePrefix){
		// 置空
		String sNo = "";

		// 组成流水号前缀 （业务流水号前缀 + 日期YYMMDD）
		String prefix = sequePrefix + DateUtils.ymdhms().substring(2, 8);

		// 生成序号信息
		SerialNumber serialNumber = create12TimeSeque(prefix);

		// 获取流水号
		sNo = serialNumber.getC_lastsn();
		return sNo;
	}
	
	// 锁住这个方法
	public synchronized SerialNumber create12TimeSeque(String prefix) {
		SerialNumber serialNumber = new SerialNumber();
		serialNumber.setC_prefix(prefix);//D170421
		long time = System.currentTimeMillis();//1492742455951 
		String string = String.format("%0" + 12 + "d", time);//1492742455951
		String sn = prefix + string.substring(4);//D170421492742455
		serialNumber.setC_lastsn(sn);
		return serialNumber;
	}
	
}
