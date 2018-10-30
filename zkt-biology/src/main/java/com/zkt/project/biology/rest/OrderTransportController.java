package com.zkt.project.biology.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zkt.project.biology.constant.ReturnObjectHandle;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.entity.Pic;
import com.zkt.project.biology.service.OrderTransportService;
import com.zkt.project.biology.utils.ContainerContent;
import com.zkt.project.biology.utils.SystemContent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * 装箱与发车
 * @author
 *
 */
@RestController
@Api(value="OrderTransportController|装箱与发车")
@RequestMapping(value = "/api/bio/orderTransport")
public class OrderTransportController {

	private Logger log = LoggerFactory.getLogger(OrderTransportController.class);

	@Autowired
	private OrderTransportService orderTransportService;

	/**
	 * 发送 运输查询新建和已装箱订单
	 * 
	 * @return
	 */
	@PostMapping(value = "/search")
	@ApiOperation(value="发送 运输查询新建和已装箱订单",tags = "发送 运输查询新建和已装箱订单")
	public ReturnObjectHandle search() {
		JSONObject json = ContainerContent.clientWebReceive();
			
		return orderTransportService.search(json);
	}
	
	/**
	 * 新建和已装箱订单详情
	 * 
	 * @return
	 */
	@PostMapping(value = "/detail")
	@ApiOperation(value="新建和已装箱订单详情",tags = "新建和已装箱订单详情")
	public ReturnSimpleHandle detail() {
		JSONObject json = ContainerContent.clientWebReceive();
			
		return orderTransportService.detail(json);
	}
	
	/**
	 * 发车
	 * @return
	 */
	@PostMapping(value = "/start")
	@ApiOperation(value="发车",tags = "发车")
	public ReturnSimpleHandle start() {
		JSONObject json = ContainerContent.clientWebReceive();
		orderTransportService.updateStart(json);
		return ReturnSimpleHandle.createServerHandle();
	}
	
	/**
	 * 装箱
	 * @return
	 */
	@PostMapping(value = "/zhuangXiang")
	@ApiOperation(value="装箱",tags = "装箱")
	public ReturnSimpleHandle zhuangXiang() {
		JSONObject json = ContainerContent.clientWebReceive();
		orderTransportService.updateZhuangXiang(json);
		return ReturnSimpleHandle.createServerHandle();
	}
	
	/**
	 * 上传图片
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value = "/saveImg")
	@ApiOperation(value="上传图片",tags = "上传图片")
	public ReturnSimpleHandle saveImg() {
		ReturnSimpleHandle returnHandle = null;
		try {
			Pic pics = orderTransportService.saveImg();
		
			String picturelink = pics.getPicturelink();			
			returnHandle = ReturnSimpleHandle.createServerHandle();
			returnHandle.setData(picturelink);
		} catch (Exception e) {
			ReturnSimpleHandle.createServerError(log, e);
		}
		return returnHandle;
	}
	
	/**
	 * 保存图片
	 * @return
	 */
	@PostMapping(value = "/savePic")
	@ApiOperation(value="保存图片",tags = "保存图片")
	public ReturnSimpleHandle savePic() {
		JSONObject json = ContainerContent.clientWebReceive();
		orderTransportService.savePic(json);
		return ReturnSimpleHandle.createServerHandle();
	}
	
	/**
	 * 显示图片
	 * @return
	 */
	@RequestMapping(value="/showImage", method = RequestMethod.GET)
	public void showImage() {
		String fileId = SystemContent.getRequest().getParameter("fileId");
		try {
			File file = orderTransportService.showImage(fileId);
			InputStream is = new FileInputStream(file);
			byte[] buffer = new byte[Integer.parseInt(file.length() + "")];
			is.read(buffer);
			is.close();
			ServletOutputStream outputStream = SystemContent.getResponse().getOutputStream();
			outputStream.write(buffer);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			ReturnSimpleHandle.createServerError(log, e);
		}
	}
	
}
