package com.zkt.common.web.handler;


import com.zkt.common.core.exception.BaseException;
import com.zkt.common.web.constant.ResponseConstant;
import com.zkt.common.web.exception.auth.UserInvalidException;
import com.zkt.common.web.exception.auth.UserTokenException;
import com.zkt.common.web.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * 异常处理
 */
@ControllerAdvice
@ResponseBody
public class ApiExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);


    @ExceptionHandler(UserTokenException.class)
    public ApiResponse userTokenExceptionHandler(HttpServletResponse response, UserTokenException ex) {
        response.setStatus(ResponseConstant.SUCCESS_CODE);
        logger.error(ex.getMessage(),ex);
        return new ApiResponse(false,ex.getMessage(),ex.getStatus());
    }

    @ExceptionHandler(UserInvalidException.class)
    public ApiResponse userInvalidExceptionHandler(HttpServletResponse response, UserInvalidException ex) {
        response.setStatus(ResponseConstant.SUCCESS_CODE);
        logger.error(ex.getMessage(),ex);
        return new ApiResponse(false, ex.getMessage(),ex.getStatus());
    }
    @ExceptionHandler(SQLException.class)
    public ApiResponse sqlExceptionHandler(HttpServletResponse response,SQLException ex){
        logger.error(ex.getMessage(),ex);
        response.setStatus(ResponseConstant.SUCCESS_CODE);
        return new ApiResponse(false, ResponseConstant.EX_SQL_MSG,ResponseConstant.EX_COMMON_CODE);
    }

    @ExceptionHandler(BaseException.class)
    public ApiResponse baseExceptionHandler(HttpServletResponse response, BaseException ex) {
        logger.error(ex.getMessage(),ex);
        response.setStatus(ResponseConstant.SUCCESS_CODE);
        return new ApiResponse(false,ex.getMessage(),ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse otherExceptionHandler(HttpServletResponse response, Exception ex) {
        response.setStatus(ResponseConstant.SUCCESS_CODE);
        logger.error(ex.getMessage(),ex);
        return new ApiResponse(false, ResponseConstant.ERROR,ResponseConstant.EX_COMMON_CODE);
    }

}
