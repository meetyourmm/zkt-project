package com.zkt.common.web.exception.auth;

import com.zkt.common.core.exception.BaseException;
import com.zkt.common.web.constant.ResponseConstant;

/**
 * Created by ace on 2017/9/8.
 */
public class UserTokenException extends BaseException {
    public UserTokenException(String message) {
        super(message, ResponseConstant.EX_TOKEN_INVALID_CODE);
    }
}
