package com.zkt.common.core.util;

import java.net.InetSocketAddress;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;


public class TcpUtil {

	public static void Doconnector(String cageno, String sign, String crc){
				
		//创建客户端连接器(阻塞)
        NioSocketConnector connector = new NioSocketConnector();
        //设置日志处理器 
        connector.getFilterChain().addLast( "logger", new LoggingFilter() );
        //读写都空闲时间:7秒
        connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 7);
        //设置业务事件处理器 
        connector.setHandler(new HandlerOne());       
        //建立连接 192.168.0.195	 121.42.179.235	118.190.92.145
        ConnectFuture cf = connector.connect( new InetSocketAddress("121.42.179.235", 8899));
        //等待连接创建完成
        cf.awaitUninterruptibly(); 
        
        String mark = "11 ";//客户端发送指令 附加唯一标示
        String cmd = mark + cageno + sign + crc;//发送16进制字符串
        String hexstr = cmd.replaceAll(" ", "");
        
        //发送消息
        cf.getSession().write(IoBuffer.wrap(Bytes.HexString2Bytes(hexstr))); 
        //服务端不主动关闭session的话，客户端就一直处于等待状态（长连接）然后再断开连接
        cf.getSession().getCloseFuture().awaitUninterruptibly(); 
        //释放连接
        connector.dispose();
              
	}
	
}
