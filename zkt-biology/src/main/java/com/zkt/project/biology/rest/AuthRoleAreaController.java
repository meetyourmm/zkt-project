package com.zkt.project.biology.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkt.common.web.response.ApiResponse;
import com.zkt.project.biology.constant.ReturnObjectHandle;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.service.AuthRoleAreaService;
import com.zkt.project.biology.utils.ContainerContent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * 查询区监账号
 * @author 
 *
 */

@RestController
@Api(value="AuthRoleAreaController|查询区监账号")
@RequestMapping(value = "/api/bio/authRoleArea")
public class AuthRoleAreaController {
	
	private Logger log = LoggerFactory.getLogger(AuthRoleAreaController.class);
	
	@Autowired
	private AuthRoleAreaService AuthRoleAreaService;
	
	/**
	 * 获取区列表
	 * 
	 * @return
	 */
	@PostMapping(value = "/getAreas")
	@ApiOperation(value="获取区列表",tags = "获取区列表")
	public ApiResponse getAreas() {
		return new ApiResponse(AuthRoleAreaService.getAreas());
	}
	
	/**
	 * 查询区监账号
	 * @return
	 */
	@PostMapping(value = "/search")
	@ApiOperation(value="查询区监账号",tags = "查询区监账号")
	public ReturnObjectHandle search() {
		JSONObject json = ContainerContent.clientWebReceive();
		return AuthRoleAreaService.search(json);
	}
	
	/**
	 * 删除账号
	 * @return
	 */
	@PostMapping(value = "/delete")
	@ApiOperation(value="删除账号",tags = "删除账号")
	public ReturnSimpleHandle delete() {
		JSONObject json = ContainerContent.clientWebReceive();
		AuthRoleAreaService.delete(json.getString("id"));
		return ReturnSimpleHandle.createServerHandle();
	}

	/**
	 * 冻结或者解冻:传入id,state(0为正常，1为锁定)
	 * @return
	 */
	@PostMapping(value = "/freeze")
	@ApiOperation(value="冻结或者解冻:传入id,state(0为正常，1为锁定)",tags = "冻结或者解冻:传入id,state(0为正常，1为锁定)")
	public ReturnSimpleHandle freeze() {
		JSONObject json = ContainerContent.clientWebReceive();
		AuthRoleAreaService.updateFreeze(json);
		return ReturnSimpleHandle.createServerHandle();
	}
	
	/**
	 * 重置密码
	 * @return
	 */
	@PostMapping(value = "/resetPassword")
	@ApiOperation(value="重置密码",tags = "重置密码")
	public ReturnSimpleHandle resetPassword() {
		JSONObject json = ContainerContent.clientWebReceive();
		AuthRoleAreaService.updatePassword(json.getString("id"));
		return ReturnSimpleHandle.createServerHandle();
	}
	
	/**
	 * 保存区监账号
	 * @return
	 */
	@PostMapping(value = "/saveServer")
	@ApiOperation(value="保存区监账号",tags = "保存区监账号")
	public ReturnSimpleHandle saveServer() {
		JSONObject json = ContainerContent.clientWebReceive();
		return AuthRoleAreaService.saveServer(json);
	}
	
	/**
	 * 修改区监账号
	 * @return
	 */
	@PostMapping(value = "/updateServer")
	@ApiOperation(value="修改区监账号",tags = "修改区监账号")
	public ReturnSimpleHandle updateServer() {
		JSONObject json = ContainerContent.clientWebReceive();
		return AuthRoleAreaService.updateServer(json);
	}
	
	/**
	 * 批量删除
	 * @return
	 */
	@PostMapping(value = "/deletes")
	@ApiOperation(value="批量删除",tags = "批量删除")
	public ReturnSimpleHandle deletes() {
		JSONObject json = ContainerContent.clientWebReceive();
		AuthRoleAreaService.deletes(json.getJSONArray("ids"));
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
		AuthRoleAreaService.updateFreezes(json.getJSONArray("ids"));
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
		AuthRoleAreaService.updatePasswords(json.getJSONArray("ids"));
		return ReturnSimpleHandle.createServerHandle();
	}
	
	/**
	 * 查询左侧菜单权限
	 * @return
	 */
	@PostMapping(value = "/searchEmployMenus")
	@ApiOperation(value="查询左侧菜单权限",tags = "查询左侧菜单权限")
	public ReturnSimpleHandle searchEmployMenus() {
			JSONObject json = ContainerContent.clientWebReceive();
		return AuthRoleAreaService.searchEmployMenus(json);
	}
	
}
