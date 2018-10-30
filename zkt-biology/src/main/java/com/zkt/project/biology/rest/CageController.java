package com.zkt.project.biology.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkt.project.biology.constant.ReturnObjectHandle;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.service.CageService;
import com.zkt.project.biology.utils.ContainerContent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * 箱体管理
 * @author 
 *
 */
@RestController
@Api(value="CageController|箱体管理")
@RequestMapping(value = "/api/cage")
public class CageController {

	private Logger log = LoggerFactory.getLogger(CageController.class);
	
	@Autowired
	private CageService cageService;
	
	/**
	 * 查询箱体
	 * @return
	 */
	@PostMapping(value = "/search")
	@ApiOperation(value="查询箱体",tags = "查询箱体")
	public ReturnObjectHandle search() {
		JSONObject json = ContainerContent.clientWebReceive();
		return cageService.search(json);
	}
	
	/**
	 * 保存箱体
	 * @return
	 */
	@PostMapping(value = "/saveServer")
	@ApiOperation(value="保存箱体",tags = "保存箱体")
	public ReturnSimpleHandle saveServer() {
		JSONObject json = ContainerContent.clientWebReceive();
		return cageService.saveServer(json);
	}
	
	/**
	 * 删除箱体
	 * @return
	 */
	@PostMapping(value = "/delete")
	@ApiOperation(value="删除箱体",tags = "删除箱体")
	public ReturnSimpleHandle delete() {
		JSONObject json = ContainerContent.clientWebReceive();
		return cageService.delete(json);
	}
	
}
