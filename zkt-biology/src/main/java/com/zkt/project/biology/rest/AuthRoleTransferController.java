package com.zkt.project.biology.rest;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkt.common.web.response.ApiResponse;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.entity.Login;
import com.zkt.project.biology.service.AuthRoleTransferService;
import com.zkt.project.biology.utils.ContainerContent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * 查询运输方账号
 * @author 
 *
 */
@RestController
@Api(value="AuthRoleTransferController|查询运输方账号")
@RequestMapping(value = "/api/bio/authRoleTransfer")
public class AuthRoleTransferController {
	
	private Logger log = LoggerFactory.getLogger(AuthRoleTransferController.class);
	
	@Autowired
	private AuthRoleTransferService authRoleTransferService;
	
	/**
	 * 获取运输队列表
	 * 
	 * @return
	 */
	@PostMapping(value = "/getUserOffices")
	@ApiOperation(value="获取运输队列表",tags = "获取运输队列表")
	public ApiResponse getUserOffices() {
		return new ApiResponse(authRoleTransferService.getUserOffices());
	}
	
	/**
	 * 查询运输方账号
	 * @return
	 */
	@PostMapping(value = "/search")
	@ApiOperation(value="查询运输方账号",tags = "查询运输方账号")
	public ApiResponse search() {
		JSONObject json = ContainerContent.clientWebReceive();
		return new ApiResponse(authRoleTransferService.search(json));
	}
	
	/**
	 * 删除
	 * 
	 * @return
	 */
	@PostMapping(value = "/delete")
	@ApiOperation(value="删除",tags = "删除")
	public ApiResponse delete() {
		JSONObject json = ContainerContent.clientWebReceive();
		authRoleTransferService.delete(json.getString("id"));
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		return new ApiResponse(JSONObject.fromObject(returnHandle).toString());
	}

	/**
	 * 冻结或者解冻:传入id,state(0为正常，1为锁定)
	 * @return
	 */
	@PostMapping(value = "/freeze")
	@ApiOperation(value="冻结或者解冻:传入id,state(0为正常，1为锁定)",tags = "冻结或者解冻:传入id,state(0为正常，1为锁定)")
	public ApiResponse freeze() {
		JSONObject json = ContainerContent.clientWebReceive();
			authRoleTransferService.updateFreeze(json);
			ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
			
		return new ApiResponse(JSONObject.fromObject(returnHandle).toString());
	}
	
	/**
	 * 重置密码
	 * @return
	 */
	@PostMapping(value = "/resetPassword")
	@ApiOperation(value="重置密码",tags = "重置密码")
	public ApiResponse resetPassword() {
		JSONObject json = ContainerContent.clientWebReceive();
			authRoleTransferService.updatePassword(json.getString("id"));
			ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
			
		return new ApiResponse(JSONObject.fromObject(returnHandle).toString());
	}
	
	/**
	 * 保存运输方账号
	 * @return
	 */
	@PostMapping(value = "/saveServer")
	@ApiOperation(value="保存运输方账号",tags = "保存运输方账号")
	public ReturnSimpleHandle saveServer() {
		JSONObject json = ContainerContent.clientWebReceive();
		return authRoleTransferService.saveServer(json);
	}
	
	/**
	 * 修改运输方账号
	 * @return
	 */
	@PostMapping(value = "/updateServer")
	@ApiOperation(value="修改运输方账号",tags = "修改运输方账号")
	public ReturnSimpleHandle updateServer() {
		JSONObject json = ContainerContent.clientWebReceive();
		return authRoleTransferService.updateServer(json);
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
		authRoleTransferService.deletes(json.getJSONArray("ids"));
		return ReturnSimpleHandle.createServerHandle();
	}

	
	
	/**
	 * 批量冻结
	 * @return
	 */
	@PostMapping(value = "/freezes")
	@ApiOperation(value="批量冻结",tags = "批量冻结")
	public ReturnSimpleHandle freezes() {
		JSONObject json = ContainerContent.clientWebReceive();
		authRoleTransferService.updateFreezes(json.getJSONArray("ids"));
		return ReturnSimpleHandle.createServerHandle();
	}
	
	/**
	 * 批量重置密码
	 * @return
	 */
	@PostMapping(value = "/resetPasswords")
	@ApiOperation(value="批量重置密码",tags = "批量重置密码")
	public ReturnSimpleHandle resetPasswords() {
		JSONObject json = ContainerContent.clientWebReceive();
		authRoleTransferService.updatePasswords(json.getJSONArray("ids"));
		return ReturnSimpleHandle.createServerHandle();
	}
	
	/**
	 * 查询左侧菜单权限
	 * @return
	 */
	@PostMapping(value = "/searchEmployMenus")
	@ApiOperation(value="查询左侧菜单权限",tags = "查询左侧菜单权限")
	public ApiResponse searchEmployMenus() {
		JSONObject json = ContainerContent.clientWebReceive();
		return new ApiResponse(authRoleTransferService.searchEmployMenus(json));
	}
	
	/**
	 * 查询医院名称ID集合
	 * 
	 * @return
	 */
	@PostMapping(value = "/getHospitals")
	@ApiOperation(value="查询医院名称ID集合",tags = "查询医院名称ID集合")
	public ReturnSimpleHandle getHospitals() {
		List<Login> hospitals = authRoleTransferService.getHospitals();
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		returnHandle.setData(hospitals);
		return returnHandle;
	}
	
	/**
	 * 查询Relate运输方和医院绑定集合
	 * 
	 * @return
	 */
	@PostMapping(value = "/getRelates")
	@ApiOperation(value="查询Relate运输方和医院绑定集合",tags = "查询Relate运输方和医院绑定集合")
	public ApiResponse getRelates() {
		JSONObject json = ContainerContent.clientWebReceive();
		return new ApiResponse(authRoleTransferService.getRelates(json));
	}
	
	/**
	 * 医院与运输方绑定
	 * @return
	 */
	@PostMapping(value = "/saveRelate")
	@ApiOperation(value="医院与运输方绑定",tags = "医院与运输方绑定")
	public ReturnSimpleHandle saveRelate() {
		JSONObject json = ContainerContent.clientWebReceive();
		JSONObject jsons = ContainerContent.clientWebReceive();
		authRoleTransferService.saveRelate(json,jsons.getJSONArray("userHospitalIds"));
		return ReturnSimpleHandle.createServerHandle();
	}
	
	/**
	 * 修改医院与运输方绑定
	 * @return
	 */
	@PostMapping(value = "/updateRelate")
	@ApiOperation(value="修改医院与运输方绑定",tags = "修改医院与运输方绑定")
	public ReturnSimpleHandle updateRelate() {
		JSONObject json = ContainerContent.clientWebReceive();
		JSONObject jsons = ContainerContent.clientWebReceive();
		authRoleTransferService.updateRelate(json,jsons.getJSONArray("userHospitalIds"));
		return ReturnSimpleHandle.createServerHandle();
	}
	
}
