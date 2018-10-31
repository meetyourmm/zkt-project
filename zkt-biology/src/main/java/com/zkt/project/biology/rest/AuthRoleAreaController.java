package com.zkt.project.biology.rest;

import com.zkt.common.core.util.ConverterUtil;
import com.zkt.project.biology.entity.Login;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

import java.util.HashMap;
import java.util.Map;

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
	@ApiOperation(value="获取用户已存在区列表",tags = "获取区列表")
	public ApiResponse getAreas() {
		return new ApiResponse(AuthRoleAreaService.getAreas());
	}
	
	/**
	 * 查询区监账号
	 * @return
	 */
	@PostMapping(value = "/search")
	@ApiOperation(value="查询区监账号",tags = "查询区监账号")
	public ReturnObjectHandle search(@ApiParam(name="draw",value="返回时用") String draw,
									 @ApiParam(name="start",value="起始页") String start,
									 @ApiParam(name="pageCount",value="分页大小") String pageCount,
									 @ApiParam(name="userName",value="用户名") String userName,
									 @ApiParam(name="userOperator",value="联系人") String userOperator,
									 @ApiParam(name="area",value="区") String area ) {
		Map map = new HashMap();
		map.put("draw",draw);
		map.put("start",start);
		map.put("pageCount",pageCount);
		map.put("userName",userName);
		map.put("userOperator",userOperator);
		map.put("area",area);
		JSONObject json = JSONObject.fromObject( map );
		return AuthRoleAreaService.search(json);
	}
	
	/**
	 * 删除账号
	 * @return
	 */
	@PostMapping(value = "/delete")
	@ApiOperation(value="删除账号",tags = "删除账号")
	public ReturnSimpleHandle delete(@ApiParam(name="id",value="id",required=true) String id) {
		AuthRoleAreaService.delete(id);
		return ReturnSimpleHandle.createServerHandle();
	}

	/**
	 * 冻结或者解冻:传入id,state(0为正常，1为锁定)
	 * @return
	 */
	@PostMapping(value = "/freeze")
	@ApiOperation(value="冻结或者解冻:传入id,state(0为正常，1为锁定)",tags = "冻结或者解冻:传入id,state(0为正常，1为锁定)")
	public ReturnSimpleHandle freeze(@ApiParam(name="id",value="id") String id,@ApiParam(name="state",value="状态0正常，1为锁定") String state) {
		Map map = new HashMap();
		map.put("id",id);
		map.put("state",state);
		JSONObject json = JSONObject.fromObject( map );
		AuthRoleAreaService.updateFreeze(json);
		return ReturnSimpleHandle.createServerHandle();
	}
	
	/**
	 * 重置密码
	 * @return
	 */
	@PostMapping(value = "/resetPassword")
	@ApiOperation(value="重置密码",tags = "重置密码")
	public ReturnSimpleHandle resetPassword(@ApiParam(name="id",value="id",required=true) String id) {
		AuthRoleAreaService.updatePassword(id);
		return ReturnSimpleHandle.createServerHandle();
	}
	
	/**
	 * 保存区监账号
	 * @return
	 */
	@PostMapping(value = "/saveServer")
	@ApiOperation(value="保存区监账号",tags = "保存区监账号")
	public ReturnSimpleHandle saveServer(@RequestBody @ApiParam(name="用户对象",value="传入json格式",required=true) Login user) {
		return AuthRoleAreaService.saveServer(ConverterUtil.bean2JsonObject(user));
	}
	
	/**
	 * 修改区监账号
	 * @return
	 */
	@PostMapping(value = "/updateServer")
	@ApiOperation(value="修改区监账号",tags = "修改区监账号")
	public ReturnSimpleHandle updateServer(@RequestBody @ApiParam(name="用户对象",value="传入json格式",required=true)Login user) {
		return AuthRoleAreaService.updateServer(ConverterUtil.bean2JsonObject(user));
	}
	
	/**
	 * 批量删除
	 * @return
	 */
	@PostMapping(value = "/deletes")
	@ApiOperation(value="批量删除",tags = "批量删除")
	public ReturnSimpleHandle deletes(@ApiParam(name="ids",value="ids",required=true) JSONObject json) {
		AuthRoleAreaService.deletes(json.getJSONArray("ids"));
		return ReturnSimpleHandle.createServerHandle();
	}
	
	
	/**
	 * 批量冻结
	 * @return
	 */
	@PostMapping(value = "/freezes")
	@ApiOperation(value="批量冻结",tags = "批量冻结")
	public ReturnSimpleHandle freezes(@ApiParam(name="ids",value="ids",required=true) JSONObject json) {
		AuthRoleAreaService.updateFreezes(json.getJSONArray("ids"));
		return ReturnSimpleHandle.createServerHandle();
	}
	
	/**
	 * 批量重置密码
	 * @return
	 */
	@PostMapping(value = "/resetPasswords")
	@ApiOperation(value="批量重置密码",tags = "批量重置密码")
	public ReturnSimpleHandle resetPasswords(@ApiParam(name="ids",value="ids",required=true) JSONObject json) {
		AuthRoleAreaService.updatePasswords(json.getJSONArray("ids"));
		return ReturnSimpleHandle.createServerHandle();
	}
	
	/**
	 * 查询左侧菜单权限
	 * @return
	 */
	@PostMapping(value = "/searchEmployMenus")
	@ApiOperation(value="查询左侧菜单权限",tags = "查询左侧菜单权限")
	public ReturnSimpleHandle searchEmployMenus(@ApiParam(name="userName",value="用户名",required=true) String userName) {
		return AuthRoleAreaService.searchEmployMenus(userName);
	}
	
}
