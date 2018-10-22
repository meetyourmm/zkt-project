package com.zkt.common.web.exception.auth;


import com.zkt.common.core.exception.BaseException;
import com.zkt.common.web.constant.ResponseConstant;

/**
 * Created by ace on 2017/9/8.
 */
public class UserInvalidException extends BaseException {
    public UserInvalidException(String message) {
        super(message, ResponseConstant.EX_USER_INVALID_CODE);
    }
}
