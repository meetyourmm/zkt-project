package com.zkt.project.biology.rest;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkt.common.core.util.UserInfo;
import com.zkt.common.web.response.ApiResponse;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.service.LoginService;
import com.zkt.project.biology.utils.ContainerContent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录、获取缓存信息、修改密码、登出
 * 
 * @author
 */
@RestController
@Api(value="LoginApiController|获取市区医院运输公司数量及订单各参数数量")
@RequestMapping(value = "/api/bio/login")
public class LoginApiController {

	private Logger log = LoggerFactory.getLogger(LoginApiController.class);

	@Autowired
	private LoginService loginService;

	/**
	 * 服务端登录
	 * 
	 * @return
	 */
	@PostMapping(value = "/login")
	@ApiOperation(value="服务端登录",tags = "服务端登录")
	public ReturnSimpleHandle login() {
		JSONObject json = ContainerContent.clientWebReceive();
		return loginService.login(json.getString("userName"), json.getString("password"));
	}

	/**
	 * 获取缓存中的用户信息
	 * 
	 * @return
	 */
	@PostMapping(value = "/getUserInfo")
	@ApiOperation(value="获取缓存中的用户信息",tags = "获取缓存中的用户信息")
	public ApiResponse getUserInfo() {
		UserInfo userInfo = null;//RedisContent.getUserInfo();
		return new ApiResponse(userInfo);
	}
	
	/**显示待办任务数量
	 * 
	 * @return
	 */
	@PostMapping(value = "/getNewMsgsNum")
	@ApiOperation(value="显示待办任务数量",tags = "显示待办任务数量")
	public ReturnSimpleHandle getNewMsgsNum() {
		return loginService.getNewMsgsNum();
	}
	
	/**右下角弹出框
	 * 
	 * @return
	 */
	@PostMapping(value = "/getWeather")
	@ApiOperation(value="右下角弹出框",tags = "右下角弹出框")
	public ReturnSimpleHandle getWeather() {
		return loginService.getWeather();
	}
	
	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@PostMapping(value = "/modifpwd")
	@ApiOperation(value="修改密码",tags = "修改密码")
	public ReturnSimpleHandle modifpwd(
			@ApiParam(name="userName",value="用户名") String userName,
			@ApiParam(name="oldpwd",value="老密码") String oldpwd,
			@ApiParam(name="newpwd",value="新密码") String newpwd
	) {
		Map map =new HashMap();
		map.put("userName",userName);
		map.put("oldpwd",oldpwd);
		map.put("newpwd",newpwd);
		JSONObject json = JSONObject.fromObject( map );
		return loginService.updatepwd(json);
	}

	/**
	 * 退出系统
	 * 
	 * @return
	 */
	@PostMapping(value = "/loginout")
	@ApiOperation(value="退出系统",tags = "退出系统")
	public ApiResponse loginout() {
		String jsonString = "";
		// 删除Redis缓存
//		RedisContent.removeUserInfo();
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		jsonString = JSONObject.fromObject(returnHandle).toString();
		return new ApiResponse(jsonString);
	}

	/**
	 * 修改个人信息
	 * @return
	 */
	@PostMapping(value = "/updateServer")
	@ApiOperation(value="修改个人信息",tags = "修改个人信息")
	public ReturnSimpleHandle updateServer(
			@ApiParam(name="id",value="id") String id,
			@ApiParam(name="userName",value="用户名") String userName
	) {
		Map map =new HashMap();
		map.put("id",id);
		map.put("userName",userName);
		JSONObject json = JSONObject.fromObject( map );
		return loginService.updateServer1(json);
	}
}
