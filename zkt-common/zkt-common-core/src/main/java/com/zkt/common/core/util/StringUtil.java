package com.zkt.common.core.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ace on 2017/9/10.
 */
public class StringUtil {
    public static String getObjectValue(Object obj){
        return obj==null?"":obj.toString();
    }
    /**
     * 获取登录用户远程主机ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
