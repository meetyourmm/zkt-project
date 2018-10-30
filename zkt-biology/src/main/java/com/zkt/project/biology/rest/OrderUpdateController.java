package com.zkt.project.biology.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkt.project.biology.constant.ReturnObjectHandle;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.service.OrderUpdateService;
import com.zkt.project.biology.utils.ContainerContent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * 修改订单
 * 
 * @author
 *
 */
@RestController
@Api(value="OrderUpdateController|修改订单")
@RequestMapping(value = "/api/orderUpdate")
public class OrderUpdateController {

	private Logger log = LoggerFactory.getLogger(OrderUpdateController.class);
	
	@Autowired
	private OrderUpdateService orderUpdateService;
	
	/**
	 * 查询可修改订单
	 * 
	 * @return
	 */
	@PostMapping(value = "/search")
	@ApiOperation(value="查询可修改订单",tags = "查询可修改订单")
	public ReturnObjectHandle search() {
		JSONObject json = ContainerContent.clientWebReceive();
		return orderUpdateService.search(json);
	}
	
	/**
	 * 订单详情
	 * 
	 * @return
	 */
	@PostMapping(value = "/detail")
	@ApiOperation(value="订单详情",tags = "订单详情")
	public ReturnSimpleHandle detail() {
		JSONObject json = ContainerContent.clientWebReceive();
		return orderUpdateService.detail(json);
	}
	
	/**
	 * 修改订单
	 * 
	 * @return
	 */
	@PostMapping(value = "/editOrder")
	@ApiOperation(value="修改订单",tags = "修改订单")
	public ReturnSimpleHandle editOrder() {
		JSONObject json = ContainerContent.clientWebReceive();
		return orderUpdateService.editOrder(json);
	}
	
}
