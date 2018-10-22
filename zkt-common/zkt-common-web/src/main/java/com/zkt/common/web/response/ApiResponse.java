/*
 * Copyright (c) 2015. MiColor
 */

/**
 * @Title:
 * @Package initerp.entity
 * @author 钰鹏
 * @date 2014年4月2日 下午5:16:35
 * @version V1.0
 */
package com.zkt.common.web.response;
import com.zkt.common.core.response.BaseResponse;
import com.zkt.common.web.constant.ResponseConstant;

/**
 * The type Msg bean.
 *
 * @author 钰鹏
 * @ClassName: MsgBean
 * @Description: 实现层执行增删改时返回的参数
 * @date 2014年4月2日 下午5:16:35
 */
public class ApiResponse extends BaseResponse{
    private Object data; // 内容数据
    private boolean success;  //状态

    public ApiResponse() {//默认返回成功
        super(ResponseConstant.SUCCESS_CODE,ResponseConstant.OK);
        this.success = true;
    }

    /**
     * 成功返回数据
     * @param data
     */
    public ApiResponse(Object data){
        super(ResponseConstant.SUCCESS_CODE,ResponseConstant.OK);
        this.success = true;
        this.data = data;
    }
    public ApiResponse(boolean success,String message){
        super(ResponseConstant.SUCCESS_CODE,message);
        this.success = success;
    }

    public ApiResponse(boolean success,String message,int status){
        super(status,message);
        this.success = success;
    }

    public ApiResponse(boolean success,String message,int status,Object data){
        super(status,message);
        this.success = success;
        this.data = data;
    }
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
