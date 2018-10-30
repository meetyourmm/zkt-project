package com.zkt.project.biology.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.service.LoginService;
import com.zkt.project.biology.utils.ContainerContent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * 修改个人信息
 * @author 
 *
 */
@RestController
@Api(value="LoginController|修改个人信息")
@RequestMapping(value = "/api/login")
public class LoginController {

	private Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;
	
	/**
	 * 修改个人信息
	 * @return
	 */
	@PostMapping(value = "/updateServer")
	@ApiOperation(value="修改个人信息",tags = "修改个人信息")
	public ReturnSimpleHandle updateServer() {
		JSONObject json = ContainerContent.clientWebReceive();
		return loginService.updateServer1(json);
	}

	
	
}
