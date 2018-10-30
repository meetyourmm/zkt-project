package com.zkt.project.biology.rest;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkt.common.web.response.ApiResponse;
import com.zkt.project.biology.constant.ReturnObjectHandle;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.service.OrderReceiveService;
import com.zkt.project.biology.utils.ContainerContent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * 签收订单
 * @author 
 *
 */
@RestController
@Api(value="OrderReceiveController|签收订单")
@RequestMapping(value = "/api/bio/orderReceive")
public class OrderReceiveController {

	private Logger log = LoggerFactory.getLogger(OrderReceiveController.class);
	
	@Autowired
	private OrderReceiveService orderReceiveService;
	
	/**
	 * 查询签收订单
	 * @return
	 */
	@PostMapping(value = "/search")
	@ApiOperation(value="查询签收订单",tags = "查询签收订单")
	public ReturnObjectHandle search() {
		JSONObject json = ContainerContent.clientWebReceive();
		return orderReceiveService.search(json);
	}
	
	/**
	 * 订单签收详情
	 * @return
	 */
	@PostMapping(value = "/detail")
	@ApiOperation(value="订单签收详情",tags = "订单签收详情")
	public ReturnSimpleHandle detail() {
		JSONObject json = ContainerContent.clientWebReceive();
		return orderReceiveService.detail(json);
	}
	
	/**
	 * 样本正常，确认签收
	 * @return
	 */
	@PostMapping(value = "/normal")
	@ApiOperation(value="样本正常，确认签收",tags = "样本正常，确认签收")
	public ReturnSimpleHandle normal() {
		JSONObject json = ContainerContent.clientWebReceive();
			orderReceiveService.updateNormal(json);
		return ReturnSimpleHandle.createServerHandle();
	}
	
	/**
	 * 样本异常，提交异常
	 * @return
	 */
	@PostMapping(value = "/abnormal")
	@ApiOperation(value="样本异常，提交异常",tags = "样本异常，提交异常")
	public ReturnSimpleHandle abnormal() {
		JSONObject json = ContainerContent.clientWebReceive();
		orderReceiveService.updateAbnormal(json);
		return ReturnSimpleHandle.createServerHandle();
	}
	
}
