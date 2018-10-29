package com.zkt.common.core.util;


//指令修改器
public class ModifyInstructions {
			
	public static String getNewCageno(String cageno){
		String regex = "(.{2})";
        String newCageno = cageno.replaceAll (regex, "$1 ");
		return newCageno;
	};
	
	public static String getCrc(String newCageno, String sign){
		String num = newCageno + sign;
		String crc = CrcModbus.DoCRCModBus(num);//CRC Modbus 检验码
		return crc;
	};
	
}
