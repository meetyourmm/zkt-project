package com.zkt.project.biology.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkt.project.biology.constant.ReturnObjectHandle;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.service.AuthUserTransportService;
import com.zkt.project.biology.utils.ContainerContent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * 运输方员工管理
 * 
 * @author
 *
 */
@RestController
@Api(value="AuthUserTransportController|运输方员工管理")
@RequestMapping(value = "/api/bio/authUserTransport")
public class AuthUserTransportController {

	private Logger log = LoggerFactory.getLogger(AuthUserTransportController.class);
	
	@Autowired
	private AuthUserTransportService authUserTransportService;
	
	/**
	 * 查询运输方员工左侧菜单权限
	 * 
	 * @return
	 */
	@PostMapping(value = "/searchEmployMenus")
	@ApiOperation(value="查询运输方员工左侧菜单权限",tags = "查询运输方员工左侧菜单权限")
	public ReturnSimpleHandle searchEmployMenus() {
		JSONObject json = ContainerContent.clientWebReceive();
		return authUserTransportService.searchEmployMenus(json);
	}

	/**
	 * 查询运输方员工账号
	 * 
	 * @return
	 */
	@PostMapping(value = "/search")
	@ApiOperation(value="查询运输方员工账号",tags = "查询运输方员工账号")
	public ReturnObjectHandle search() {
		JSONObject json = ContainerContent.clientWebReceive();
		return authUserTransportService.search(json);
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
		authUserTransportService.updateFreeze(json);
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
		authUserTransportService.updatePassword(json.getString("id"));
		return ReturnSimpleHandle.createServerHandle();
	}

	/**
	 * 保存运输方员工账号
	 * 
	 * @return
	 */
	@PostMapping(value = "/saveServer")
	@ApiOperation(value="保存运输方员工账号",tags = "保存运输方员工账号")
	public ReturnSimpleHandle saveServer() {
		JSONObject json = ContainerContent.clientWebReceive();
		return authUserTransportService.saveServer(json);
	}

	/**
	 * 修改运输方员工账号
	 * 
	 * @return
	 */
	@PostMapping(value = "/updateServer")
	@ApiOperation(value="修改运输方员工账号",tags = "修改运输方员工账号")
	public ReturnSimpleHandle updateServer() {
		JSONObject json = ContainerContent.clientWebReceive();
		return authUserTransportService.updateServer(json);
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
		authUserTransportService.updateFreezes(json.getJSONArray("ids"));
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
		authUserTransportService.updatePasswords(json.getJSONArray("ids"));
		return ReturnSimpleHandle.createServerHandle();
	}

	/**
	 * 删除运输方员工账号
	 * 
	 * @return
	 */
	@PostMapping(value = "/delete")
	@ApiOperation(value="删除运输方员工账号",tags = "删除运输方员工账号")
	public ReturnSimpleHandle delete() {
		JSONObject json = ContainerContent.clientWebReceive();
		authUserTransportService.delete(json.getString("id"));
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
		authUserTransportService.deletes(json.getJSONArray("ids"));
		return ReturnSimpleHandle.createServerHandle();
	}
	
}
