package com.zkt.common.core.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class HandlerOne extends IoHandlerAdapter {
	
	/**
	 * LOG信息输出
	 */
	private final Log log = LogFactory.getLog(getClass());
		
	//全局Message 消息储存器
	MessageMap messagemap = MessageMap.newInstance();
	
	//当服务端发送的消息到达时
	@Override
    public void messageReceived(IoSession session, Object message) throws Exception {
		
		IoBuffer ioBuffer = (IoBuffer) message;	
		String messageStorage = ioBuffer.getHexDump();
		log.warn("本地客户端收到服务端反馈指令 ：" + messageStorage);
		
		
		//写入硬件返回的失败指令后的回调函数
		if(TcpConstant.OPEN_CAGE_ERROR.equals(messageStorage.substring(35, 39))){
			//开锁失败
			//比对CRC
			String comparison = messageStorage.substring(0, messageStorage.length()-6);
			String crc = CrcModbus.DoCRCModBus(comparison);//CRC Modbus 检验码			
			if(crc.equals(messageStorage.substring(messageStorage.length()-5))){				
				messagemap.addMes(messageStorage.substring(0, 35).replaceAll(" ", ""), TcpConstant.OPEN_CAGE_ERROR);				
			}
		}
		
		if(TcpConstant.MONITOR_ERROR.equals(messageStorage.substring(35, 39))){
			//开关监控失败
			//比对CRC
			String comparison = messageStorage.substring(0, messageStorage.length()-6);
			String crc = CrcModbus.DoCRCModBus(comparison);//CRC Modbus 检验码			
			if(crc.equals(messageStorage.substring(messageStorage.length()-5))){				
				messagemap.addMes(messageStorage.substring(0, 35).replaceAll(" ", ""), TcpConstant.MONITOR_ERROR);				
			}
		}
		
		if(TcpConstant.UPLOAD_INTERVAL_ERROR.equals(messageStorage.substring(35, 39))){
			//上传时间间隔失败
			//比对CRC
			String comparison = messageStorage.substring(0, messageStorage.length()-6);
			String crc = CrcModbus.DoCRCModBus(comparison);//CRC Modbus 检验码			
			if(crc.equals(messageStorage.substring(messageStorage.length()-5))){				
				messagemap.addMes(messageStorage.substring(0, 35).replaceAll(" ", ""), TcpConstant.UPLOAD_INTERVAL_ERROR);				
			}
		}
		
		
		
		//写入硬件返回的成功指令后的回调函数	06 6F FF 54 48 48 66 72 67 14 22 35 02 01 98 76
		if(TcpConstant.OPEN_CAGE_OK.equals(messageStorage.substring(35, 42))){
			//开锁成功
			//比对CRC
			String comparison = messageStorage.substring(0, messageStorage.length()-6);
			String crc = CrcModbus.DoCRCModBus(comparison);//CRC Modbus 检验码			
			if(crc.equals(messageStorage.substring(messageStorage.length()-5))){				
				messagemap.addMes(messageStorage.substring(0, 35).replaceAll(" ", ""), TcpConstant.OPEN_CAGE_OK);				
			}
		}
		
		if(TcpConstant.START_MONITOR_OK.equals(messageStorage.substring(35, 42))){
			//监控开启成功
			//比对CRC
			String comparison = messageStorage.substring(0, messageStorage.length()-6);
			String crc = CrcModbus.DoCRCModBus(comparison);//CRC Modbus 检验码			
			if(crc.equals(messageStorage.substring(messageStorage.length()-5))){
				messagemap.addMes(messageStorage.substring(0, 35).replaceAll(" ", ""), TcpConstant.START_MONITOR_OK);
			}
		}
		
		if(TcpConstant.OVER_MONITOR_OK.equals(messageStorage.substring(35, 42))){
			//监控关闭成功
			//比对CRC
			String comparison = messageStorage.substring(0, messageStorage.length()-6);
			String crc = CrcModbus.DoCRCModBus(comparison);//CRC Modbus 检验码			
			if(crc.equals(messageStorage.substring(messageStorage.length()-5))){
				messagemap.addMes(messageStorage.substring(0, 35).replaceAll(" ", ""), TcpConstant.OVER_MONITOR_OK);
			}
		}
		
		if(TcpConstant.UPLOAD_INTERVAL_OK.equals(messageStorage.substring(35, 39))){
			//上传时间间隔成功
			//比对CRC
			String comparison = messageStorage.substring(0, messageStorage.length()-6);
			String crc = CrcModbus.DoCRCModBus(comparison);//CRC Modbus 检验码			
			if(crc.equals(messageStorage.substring(messageStorage.length()-5))){
				messagemap.addMes(messageStorage.substring(0, 35).replaceAll(" ", ""), TcpConstant.UPLOAD_INTERVAL_OK);
			}
		}
		
		//加上这句话实现短连接的效果，向服务端成功发送数据后断开连接
        session.close(true);
    }
	
	
	
	//当一个服务端关闭时
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        log.warn("本地客户端已关闭");
    }
    
    
    
    //当连接空闲时触发此方法
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
        log.warn("本地客户端连接空闲");
        //客户端7秒内没有收到服务端消息就转入空闲 并触发关闭事件        
        session.close(true);
    }
    
    
    
    //当信息已经传送给服务端后触发此方法
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        log.warn("本地客户端发送到服务端指令是："+message.toString());
        
    }
    
    
    
    //当一个服务端连接后触发此方法
    @Override
    public void sessionCreated(IoSession session) throws Exception {        
        super.sessionCreated(session);
        log.warn("本地客户端已连接上服务端");
    }
    
    
    
    //当一个服务端连结进入时
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        log.warn("本地客户端已进入服务端");
    }
    
    
    
}
