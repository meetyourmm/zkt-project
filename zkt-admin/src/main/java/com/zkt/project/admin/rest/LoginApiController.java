/*
 * Copyright(C), 2015-2018, Beifeng
 * FileName: LoginController
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * Email Address: liwei@ibeifeng.com
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zkt.project.admin.rest;

import com.zkt.common.core.constant.CommonConstant;
import com.zkt.common.core.constant.EnumUtil;
import com.zkt.common.core.context.UserContextHandler;
import com.zkt.common.core.util.JwtHelper;
import com.zkt.common.web.constant.ResponseConstant;
import com.zkt.common.web.exception.auth.UserTokenException;
import com.zkt.common.web.jwt.AccessToken;
import com.zkt.common.web.jwt.Audience;
import com.zkt.common.web.response.ApiResponse;
import com.zkt.project.admin.entity.SysUser;
import com.zkt.project.admin.service.UserService;
import com.zkt.project.admin.vo.FrontUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 登录接口
 *
 * @author liwei
 * @create 2018/10/12 0012
 */
@RestController
@Api(value="LoginApiController|一个用来验证用户登录的控制器")
@RequestMapping("/auth")
public class LoginApiController {

    private Logger logger = LoggerFactory.getLogger(LoginApiController.class);
    @Autowired
    private Audience audienceEntity;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    @ApiOperation(value="用户登录",tags = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name="clientId",value="客户端id",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name="userName",value="用户名",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name="password",value="密码",required = true,dataType = "String")
    })
    public ApiResponse getAccessToken(HttpServletRequest request) {
        String clientId = request.getParameter("clientId");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        if (StringUtils.isEmpty(clientId)
                || (clientId.compareTo(audienceEntity.getClientId()) != 0)) {
            return new ApiResponse(false,"用户参数错误");
        }

        //验证用户名密码
        SysUser user = userService.validate(userName,password);
        //拼装accessToken
        String accessToken = JwtHelper.createJWT(userName, String.valueOf(user.getId()),
                "", audienceEntity.getClientId(), audienceEntity.getName(),
                audienceEntity.getExpiresSecond() * 1000L, audienceEntity.getBase64Secret());
        String token =  CommonConstant.TOKEN_PRE +" "+accessToken;
        return new ApiResponse(token);
    }

    @PostMapping(value = "/checkToken")
    @ApiOperation(value="Token验证",tags = "Token验证")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name="token",value="token值",required = true,dataType = "String")
    })
    public ApiResponse checkAccessToken(HttpServletRequest request) {
        String token = request.getParameter("token");
        if(!StringUtils.isEmpty(token)){
            String webToken = token.substring(7, token.length());
            try{
                Claims claims = JwtHelper.parseJWT(webToken, audienceEntity.getBase64Secret());
                String userId = claims.get(CommonConstant.JWT_KEY_USER_ID).toString();
                String userName = claims.get(CommonConstant.JWT_KEY_USER_NAME).toString();
                return new ApiResponse(userId+":"+userName);
            }catch (ExpiredJwtException ex) {
                throw new UserTokenException(ResponseConstant.EX_TOKEN_EXPIRE_MSG);
            }catch (Exception ex) {
                logger.error("token错误，原因：{}", ex.getMessage());
                throw new UserTokenException(ResponseConstant.EX_TOKEN_ERROR_MSG);
            }
        }else{
            throw new UserTokenException(ResponseConstant.EX_TOKEN_NULL_MSG);
        }

    }

    @PostMapping(value = "/logout")
    @ApiOperation(value="用户后台退出",tags = "用户后台退出")
    public ApiResponse logout(HttpServletRequest request) {
        UserContextHandler.remove();
        return new ApiResponse("success");
    }
}
