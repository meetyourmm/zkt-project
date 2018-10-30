package com.zkt.project.biology.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkt.project.biology.constant.ReturnObjectHandle;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.entity.Model;
import com.zkt.project.biology.service.ModelService;
import com.zkt.project.biology.utils.ContainerContent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * 模板管理
 * 
 * @author
 *
 */
@RestController
@Api(value="ModelController| 模板管理")
@RequestMapping(value = "/api/model")
public class ModelController {

	private Logger log = LoggerFactory.getLogger(ModelController.class);
	
	@Autowired
	private ModelService modelService;
	
	/**
	 * 获取modelNo列表(全表参数)
	 * 
	 * @return
	 */
	@PostMapping(value = "/search")
	@ApiOperation(value="获取modelNo列表(全表参数)",tags = "获取modelNo列表(全表参数)")
	public ReturnObjectHandle search() {
		JSONObject json = ContainerContent.clientWebReceive();
		return modelService.search(json);
	}
	
	
	/**
	 * 获取modelNo列表
	 * 
	 * @return
	 */
	@PostMapping(value = "/getModelNo")
	@ApiOperation(value="获取modelNo列表",tags = "获取modelNo列表")
	public ReturnSimpleHandle getModelNo() {
		List<Model> modelnos = modelService.getModelNo();
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		returnHandle.setData(modelnos);
		return returnHandle;
	}
	
	/**
	 * 获取Model数据自动填充页面
	 * 
	 * @return
	 */
	@PostMapping(value = "/searchModel")
	@ApiOperation(value="获取Model数据自动填充页面",tags = "获取Model数据自动填充页面")
	public ReturnSimpleHandle searchModel() {
		JSONObject json = ContainerContent.clientWebReceive();
		List<Model> modelNos = modelService.searchModel(json);
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		returnHandle.setData(modelNos);
		return returnHandle;
	}
	
	/**
	 * 保存订单模板
	 * 
	 * @return
	 */
	@PostMapping(value = "/saveModel")
	@ApiOperation(value="保存订单模板",tags = "保存订单模板")
	public ReturnSimpleHandle saveModel() {
		JSONObject json = ContainerContent.clientWebReceive();
		return modelService.saveModel(json);
	}
	
	/**
	 * 修改订单模板
	 * 
	 * @return
	 */
	@PostMapping(value = "/updateModel")
	@ApiOperation(value="修改订单模板",tags = "修改订单模板")
	public ReturnSimpleHandle updateModel() {
		JSONObject json = ContainerContent.clientWebReceive();
		return modelService.updateModel(json);
	}
	
	/**
	 * 删除模板
	 * 
	 * @return
	 */
	@PostMapping(value = "/delete")
	@ApiOperation(value="删除模板",tags = "删除模板")
	public ReturnSimpleHandle delete() {
		JSONObject json = ContainerContent.clientWebReceive();
		modelService.delete(json.getString("id"));
		return ReturnSimpleHandle.createServerHandle();
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	@PostMapping(value = "/deletes")
	@ApiOperation(value="批量删除",tags = "批量删除")
	public ReturnSimpleHandle deletes() {
		JSONObject json = ContainerContent.clientWebReceive();
		modelService.deletes(json.getJSONArray("ids"));
		return ReturnSimpleHandle.createServerHandle();
	}
	
}
