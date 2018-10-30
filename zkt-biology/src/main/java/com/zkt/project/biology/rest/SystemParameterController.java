package com.zkt.project.biology.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkt.common.web.response.ApiResponse;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.service.SystemParameterService;
import com.zkt.project.biology.utils.ContainerContent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * 系统的全局参数设置
 * 
 * @author
 */
@RestController
@Api(value="SystemParameterController|系统的全局参数设置")
@RequestMapping(value = "/api/systemParameter")
public class SystemParameterController {

	private Logger log = LoggerFactory.getLogger(SystemParameterController.class);
	
	@Autowired
	private SystemParameterService systemParameterService;
	
	/**
	 * 显示报警间隔
	 * @return
	 */
	@PostMapping(value = "/searchAlarmInterval")
	@ApiOperation(value="显示报警间隔",tags = "显示报警间隔")
	public ReturnSimpleHandle searchAlarmInterval() {
		return systemParameterService.searchAlarmInterval();
	}
	
	/**
	 * 设置报警间隔
	 * @return
	 */
	@PostMapping(value = "/saveAlarmInterval")
	@ApiOperation(value="设置报警间隔",tags = "设置报警间隔")
	public ReturnSimpleHandle saveAlarmInterval() {
		JSONObject json = ContainerContent.clientWebReceive();
		return systemParameterService.saveAlarmInterval(json);
	}
	
}
