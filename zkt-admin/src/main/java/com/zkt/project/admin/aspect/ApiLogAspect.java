package com.zkt.project.admin.aspect;


import com.zkt.common.core.context.UserContextHandler;
import com.zkt.common.core.exception.BaseException;
import com.zkt.common.core.util.StringUtil;
import com.zkt.common.web.constant.ResponseConstant;
import com.zkt.project.admin.entity.SysApiLog;
import com.zkt.project.admin.service.ApiLogService;
import com.zkt.project.admin.service.MenuService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.logs.aspect
 *
 * @Author: Evangoe
 * @Description: 用于对Controller中所有的进行日志记录的切面
 *  每个项目中配置，后期写入网关中
 * @Date:11/05/17
 * @Modified:
 */
@Aspect
@Component
public class ApiLogAspect {
    private static Logger logger = LoggerFactory.getLogger(ApiLogAspect.class);

    private ThreadLocal<SysApiLog> tlocal = new ThreadLocal<SysApiLog>();

    @Autowired
    private ApiLogService apiLogService;

    @Autowired
    private MenuService menuService;

    /*
     * 切面定义
     */
    @Pointcut("(execution(* com.zkt.project.admin.rest..*.*(..))) ") //排除com.micolor.commoncore.exception.controller下面的Controller
    public void apiRequestLog() {}



    @Before("apiRequestLog()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            String uri = request.getRequestURI();
            String name = menuService.getMenuByUri(uri);
            String remoteAddr = StringUtil.getIpAddr(request);
            String params = request.getQueryString();
            String userId = UserContextHandler.getUserID();

            SysApiLog optLog = new SysApiLog();
            optLog.setName(name);
            optLog.setUri(uri);
            optLog.setParams(params);
            optLog.setCreateHost(remoteAddr);
            optLog.setCreateUser(userId);
            optLog.setCreateTime(new Date());
            tlocal.set(optLog);
        } catch (Exception e) {
            logger.error("***操作请求日志记录失败doBefore()***", e);
        }
    }

    @SuppressWarnings("unchecked")
    @AfterReturning(value="apiRequestLog()",argNames = "result",returning = "result")
    public void doAfterReturning(Object result) {
        try {
            // 处理完请求，返回内容
            SysApiLog optLog = tlocal.get();
            optLog.setState(ResponseConstant.SUCCESS_CODE);
            optLog.setResult(ResponseConstant.OK);
            apiLogService.saveLog(optLog);
            tlocal.remove();
        } catch (Exception e) {
            logger.error("***操作请求日志记录失败doAfterReturning()***", e);
        }
    }

    @AfterThrowing(value="apiRequestLog()",throwing = "ex")
    public void afterThrowing(JoinPoint jp, Exception ex){
        String strLog ="afterThrowing:log Ending method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName();
        logger.error(strLog+"["+ex+"]");
        SysApiLog optLog = tlocal.get();
        if(ex instanceof BaseException){
            optLog.setState(((BaseException) ex).getStatus());
        }else{
            optLog.setState(ResponseConstant.EX_COMMON_CODE);
        }
        optLog.setResult(ex.getMessage());
        apiLogService.saveLog(optLog);
        tlocal.remove();
    }
}
