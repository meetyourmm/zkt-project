package com.zkt.project.biology.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkt.project.biology.constant.ReturnObjectHandle;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.service.AuthUserHospitalService;
import com.zkt.project.biology.utils.ContainerContent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * 医院员工管理
 * 
 * @author
 *
 */
@RestController
@Api(value="AuthUserHospitalController|医院员工管理")
@RequestMapping(value = "/api/authUserHospital")
public class AuthUserHospitalController {

	private Logger log = LoggerFactory.getLogger(AuthUserHospitalController.class);
	
	@Autowired
	private AuthUserHospitalService authUserHospitalService;
	
	/**
	 * 获取当前人员用户类型
	 * 
	 * @return
	 */
	@PostMapping(value = "/getUserType")
	@ApiOperation(value="获取当前人员用户类型",tags = "获取当前人员用户类型")
	public ReturnSimpleHandle getUserType() {
		String usertype = authUserHospitalService.getUserType();
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		returnHandle.setData(usertype);
		return returnHandle;
	}
	
	/**
	 * 查询医院员工左侧菜单权限
	 * 
	 * @return
	 */
	@PostMapping(value = "/searchEmployMenus")
	@ApiOperation(value="查询医院员工左侧菜单权限",tags = "查询医院员工左侧菜单权限")
	public ReturnSimpleHandle searchEmployMenus() {
		JSONObject json = ContainerContent.clientWebReceive();
		return authUserHospitalService.searchEmployMenus(json);
	}

	/**
	 * 查询医院员工账号
	 * 
	 * @return
	 */
	@PostMapping(value = "/search")
	@ApiOperation(value="查询医院员工账号",tags = "查询医院员工账号")
	public ReturnObjectHandle search() {
		JSONObject json = ContainerContent.clientWebReceive();
		return authUserHospitalService.search(json);
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
		authUserHospitalService.updateFreeze(json);
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
		authUserHospitalService.updatePassword(json.getString("id"));
		return ReturnSimpleHandle.createServerHandle();
	}

	/**
	 * 保存医院员工账号
	 * 
	 * @return
	 */
	@PostMapping(value = "/saveServer")
	@ApiOperation(value="保存医院员工账号",tags = "保存医院员工账号")
	public ReturnSimpleHandle saveServer() {
		JSONObject json = ContainerContent.clientWebReceive();
		return authUserHospitalService.saveServer(json);
	}

	/**
	 * 修改医院员工账号
	 * 
	 * @return
	 */
	@PostMapping(value = "/updateServer")
	@ApiOperation(value="修改医院员工账号",tags = "修改医院员工账号")
	public ReturnSimpleHandle updateServer() {
		JSONObject json = ContainerContent.clientWebReceive();
		return authUserHospitalService.updateServer(json);
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
		authUserHospitalService.updateFreezes(json.getJSONArray("ids"));
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
		authUserHospitalService.updatePasswords(json.getJSONArray("ids"));
		return ReturnSimpleHandle.createServerHandle();
	}

	/**
	 * 删除医院员工账号
	 * 
	 * @return
	 */
	@PostMapping(value = "/delete")
	@ApiOperation(value="删除医院员工账号",tags = "删除医院员工账号")
	public ReturnSimpleHandle delete() {
		JSONObject json = ContainerContent.clientWebReceive();
		authUserHospitalService.delete(json.getString("id"));
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
		authUserHospitalService.deletes(json.getJSONArray("ids"));
		return ReturnSimpleHandle.createServerHandle();
	}
	
}
