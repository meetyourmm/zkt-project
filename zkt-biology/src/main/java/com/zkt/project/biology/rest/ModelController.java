package com.zkt.project.biology.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiParam;
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
@RequestMapping(value = "/api/bio/model")
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
	public ReturnObjectHandle search(
			@ApiParam(name="modelNo",value="模板名称") String modelNo,
			@ApiParam(name="draw",value="") String draw,
			@ApiParam(name="start",value="") String start,
			@ApiParam(name="pageCount",value="") String pageCount
	) {
		Map map =new HashMap();
		map.put("modelNo",modelNo);
		map.put("draw",draw);
		map.put("start",start);
		map.put("pageCount",pageCount);
		JSONObject json = JSONObject.fromObject( map );
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
	public ReturnSimpleHandle searchModel(
			@ApiParam(name="id",value="id") String id
	) {
		Map map =new HashMap();
		map.put("id",id);
		JSONObject json = JSONObject.fromObject( map );
		//JSONObject json = ContainerContent.clientWebReceive();
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
	public ReturnSimpleHandle saveModel(
			@ApiParam(name="id",value="承运单位ID") String id,
			@ApiParam(name="userHospitalId",value="运输公司ID") String userHospitalId,
			@ApiParam(name="tlimitup",value="温度上限值") String tlimitup,
			@ApiParam(name="tlimitdown",value="温度下限值") String tlimitdown,
			@ApiParam(name="hlimitup",value="湿度上限值") String hlimitup,
			@ApiParam(name="hlimitdown",value="湿度下限值") String hlimitdown,
			@ApiParam(name="modelNo",value="模板名称") String modelNo

	) {
		Map map =new HashMap();
		map.put("id",id);
		map.put("userHospitalId",userHospitalId);
		map.put("tlimitup",tlimitup);
		map.put("tlimitdown",tlimitdown);
		map.put("hlimitup",hlimitup);
		map.put("hlimitdown",hlimitdown);
		map.put("modelNo",modelNo);

		JSONObject json = JSONObject.fromObject(map);
		return modelService.saveModel(json);
	}
	
	/**
	 * 修改订单模板
	 * 
	 * @return
	 */
	@PostMapping(value = "/updateModel")
	@ApiOperation(value="修改订单模板",tags = "修改订单模板")
	public ReturnSimpleHandle updateModel(
			@ApiParam(name="id",value="被修改模板ID") String id,
			@ApiParam(name="userOfficeId",value="承运单位ID") String userOfficeId,
			@ApiParam(name="userHospitalId",value="运输公司ID") String userHospitalId,
			@ApiParam(name="tlimitup",value="温度上限值") String tlimitup,
			@ApiParam(name="tlimitdown",value="温度下限值") String tlimitdown,
			@ApiParam(name="hlimitup",value="湿度上限值") String hlimitup,
			@ApiParam(name="hlimitdown",value="湿度下限值") String hlimitdown,
			@ApiParam(name="modelNo",value="模板名称") String modelNo
	) {
		Map map =new HashMap();
		map.put("id",id);
		map.put("userOfficeId",userOfficeId);
		map.put("userHospitalId",userHospitalId);
		map.put("tlimitup",tlimitup);
		map.put("tlimitdown",tlimitdown);
		map.put("hlimitup",hlimitup);
		map.put("hlimitdown",hlimitdown);
		map.put("modelNo",modelNo);
		JSONObject json = JSONObject.fromObject(map);
		return modelService.updateModel(json);
	}
	
	/**
	 * 删除模板
	 * 
	 * @return
	 */
	@PostMapping(value = "/delete")
	@ApiOperation(value="删除模板",tags = "删除模板")
	public ReturnSimpleHandle delete(@ApiParam(name="id",value="ID") String id) {

		modelService.delete(id);
		return ReturnSimpleHandle.createServerHandle();
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	@PostMapping(value = "/deletes")
	@ApiOperation(value="批量删除",tags = "批量删除")
	public ReturnSimpleHandle deletes(@ApiParam(name="ids",value="ids",required=true) JSONObject json) {
		modelService.deletes(json.getJSONArray("ids"));
		return ReturnSimpleHandle.createServerHandle();
	}
	
}
