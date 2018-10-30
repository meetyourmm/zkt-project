package com.zkt.project.biology.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zkt.common.web.response.ApiResponse;
import com.zkt.project.biology.constant.ReturnObjectHandle;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.service.AuthRoleHospitalService;
import com.zkt.project.biology.utils.ContainerContent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * 查询医院账号
 * @author
 *
 */
@RestController
@Api(value="AuthRoleHospitalController|查询医院账号")
@RequestMapping(value = "/api/bio/authRoleHospital")
public class AuthRoleHospitalController {

	private Logger log = LoggerFactory.getLogger(AuthRoleHospitalController.class);

	@Autowired
	private AuthRoleHospitalService authRoleHospitalService;
	
	/**
	 * 获取医院列表
	 * 
	 * @return
	 */
	@PostMapping(value = "/getUserHospitals")
	@ApiOperation(value="获取医院列表",tags = "获取医院列表")
	public ApiResponse getUserHospitals() {
		return new ApiResponse(authRoleHospitalService.getUserHospitals());
	}
	
	/**
	 * 查询医院账号
	 * 
	 * @return
	 */
	@PostMapping(value = "/search")
	@ApiOperation(value="查询医院账号",tags = "查询医院账号")
	public ReturnObjectHandle search() {
		JSONObject json = ContainerContent.clientWebReceive();
		return authRoleHospitalService.search(json);
	}
	
	/**
	 * 删除
	 * 
	 * @return
	 */
	@PostMapping(value = "/delete")
	@ApiOperation(value="删除",tags = "删除")
	public ReturnSimpleHandle delete() {
		JSONObject json = ContainerContent.clientWebReceive();
		authRoleHospitalService.delete(json.getString("id"));
		return ReturnSimpleHandle.createServerHandle();
	}

	/**
	 * 冻结或者解冻:传入id,state(0为正常，1为锁定)
	 * 
	 * @return
	 */
	@PostMapping(value = "/freeze")
	@ApiOperation(value="冻结或者解冻:传入id,state(0为正常，1为锁定)",tags = "冻结或者解冻:传入id,state(0为正常，1为锁定)")
	public ReturnSimpleHandle freeze() {
		JSONObject json = ContainerContent.clientWebReceive();
		authRoleHospitalService.updateFreeze(json);
		return ReturnSimpleHandle.createServerHandle();
	}

	/**
	 * 重置密码
	 * 
	 * @return
	 */
	@PostMapping(value = "/resetPassword")
	@ApiOperation(value="重置密码",tags = "重置密码")
	public ReturnSimpleHandle resetPassword() {
		JSONObject json = ContainerContent.clientWebReceive();
		authRoleHospitalService.updatePassword(json.getString("id"));
		return ReturnSimpleHandle.createServerHandle();
	}

	/**
	 * 保存医院账号
	 * @return
	 */
	@PostMapping(value = "/saveServer")
	@ApiOperation(value="保存医院账号",tags = "保存医院账号")
	public ReturnSimpleHandle saveServer() {
		JSONObject json = ContainerContent.clientWebReceive();
		return authRoleHospitalService.saveServer(json);
	}

	/**
	 * 修改医院账号
	 * 
	 * @return
	 */
	@PostMapping(value = "/updateServer")
	@ApiOperation(value="修改医院账号",tags = "修改医院账号")
	public ReturnSimpleHandle updateServer() {
		JSONObject json = ContainerContent.clientWebReceive();
		return authRoleHospitalService.updateServer(json);
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
		authRoleHospitalService.deletes(json.getJSONArray("ids"));
		return ReturnSimpleHandle.createServerHandle();
	}

	/**
	 * 批量冻结
	 * 
	 * @return
	 */
	@PostMapping(value = "/freezes")
	@ApiOperation(value="批量冻结",tags = "批量冻结")
	public ReturnSimpleHandle freezes() {
		JSONObject json = ContainerContent.clientWebReceive();
		authRoleHospitalService.updateFreezes(json.getJSONArray("ids"));
		return ReturnSimpleHandle.createServerHandle();
	}

	/**
	 * 批量重置密码
	 * 
	 * @return
	 */
	@PostMapping(value = "/resetPasswords")
	@ApiOperation(value="批量重置密码",tags = "批量重置密码")
	public ReturnSimpleHandle resetPasswords() {
		JSONObject json = ContainerContent.clientWebReceive();
		authRoleHospitalService.updatePasswords(json.getJSONArray("ids"));
		return ReturnSimpleHandle.createServerHandle();
	}

	/**
	 * 查询左侧菜单权限
	 * 
	 * @return
	 */
	@PostMapping(value = "/searchEmployMenus")
	@ApiOperation(value="查询左侧菜单权限",tags = "查询左侧菜单权限")
	public ApiResponse searchEmployMenus() {
		JSONObject json = ContainerContent.clientWebReceive();
		return new ApiResponse(authRoleHospitalService.searchEmployMenus(json));
	}

}
