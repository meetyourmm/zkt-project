package com.zkt.project.biology.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkt.common.core.util.RequestConstance;
import com.zkt.common.core.util.RequestTag;
//import com.mantu.system.content.RedisContent;
import com.zkt.common.core.util.ValidateUtil;
import com.zkt.common.web.response.ApiResponse;
import com.zkt.project.biology.mapper.LoginMapper;
import com.zkt.project.biology.mapper.WechatusersMapper;
import com.zkt.project.biology.service.wechat.WeChatAbnormalService;
import com.zkt.project.biology.service.wechat.WeChatDetailService;
import com.zkt.project.biology.service.wechat.WeChatGetCageService;
import com.zkt.project.biology.service.wechat.WeChatGetHospitalsService;
import com.zkt.project.biology.service.wechat.WeChatGetModelService;
import com.zkt.project.biology.service.wechat.WeChatGetOfficeService;
import com.zkt.project.biology.service.wechat.WeChatLoginService;
import com.zkt.project.biology.service.wechat.WeChatModifpwdService;
import com.zkt.project.biology.service.wechat.WeChatNormalService;
import com.zkt.project.biology.service.wechat.WeChatOpenCageService;
import com.zkt.project.biology.service.wechat.WeChatOrderService;
import com.zkt.project.biology.service.wechat.WeChatSearchService;
import com.zkt.project.biology.service.wechat.WeChatStartService;
import com.zkt.project.biology.service.wechat.WeChatUploadImgService;
import com.zkt.project.biology.service.wechat.WeChatZhuangXiangService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

@RestController
@Api(value="WeChatController|wechat api")
@RequestMapping(value = "/api/weChat")
public class WeChatController {

	@Autowired
	private LoginMapper loginMapper;

	@Autowired
	private WechatusersMapper wechatusersMapper;
	
	@Autowired
	private WeChatLoginService weChatLoginService;
	
	@Autowired
	private WeChatModifpwdService weChatModifpwdService;
	
	@Autowired
	private WeChatGetCageService weChatGetCageService;
	
	@Autowired
	private WeChatGetHospitalsService weChatGetHospitalsService;
	
	@Autowired
	private WeChatGetOfficeService weChatGetOfficeService;
	
	@Autowired
	private WeChatGetModelService weChatGetModelService;
	
	@Autowired
	private WeChatOrderService weChatOrderService;
	
	@Autowired
	private WeChatUploadImgService weChatUploadImgService;
	
	@Autowired
	private WeChatZhuangXiangService weChatZhuangXiangService;
	
	@Autowired
	private WeChatStartService weChatStartService;
	
	@Autowired
	private WeChatNormalService weChatNormalService;
	
	@Autowired
	private WeChatAbnormalService weChatAbnormalService;
	
	@Autowired
	private WeChatSearchService weChatSearchService;
	
	@Autowired
	private WeChatDetailService weChatDetailService;
	
	@Autowired
	private WeChatOpenCageService weChatOpenCageService;
	
	/**
	 * 微信登录
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/login")
	@ApiOperation(value="微信登录",tags = "微信登录")
	public ApiResponse login(HttpServletRequest request) throws Exception {		
		return new ApiResponse(new ApiResponse(weChatLoginService.login(request)));
	}

	/**
	 * 微信退出系统
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/loginOut")
	@ApiOperation(value="微信退出系统",tags = "微信退出系统")
	public ApiResponse loginOut(HttpServletRequest request) throws Exception {
		
		JSONObject result = new JSONObject();
		
		String userid = request.getParameter("userid");//微信ID	oaPjSvy-PAphOE0_ziOO-qPUVIb
		if (ValidateUtil.isEmpty(userid)) {
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_MISSING_PARAMETER);
			result.put(RequestConstance.RESULT_MSG, "缺少参数");
			return new ApiResponse(result.toString());
		}
		
		// 删除wechatusers
		wechatusersMapper.deleteByUserid(userid);
		// 删除Redis缓存
//		RedisContent.removeUserInfo();
		result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_OK);
		result.put(RequestConstance.RESULT_MSG, RequestConstance.MSG_SUCCESS);
		return new ApiResponse(result.toString());
	}
	
	/**
	 * 微信修改密码
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/modifpwd")
	@ApiOperation(value="微信修改密码",tags = "微信修改密码")
	public ApiResponse modifpwd(HttpServletRequest request) throws Exception {
		return new ApiResponse(weChatModifpwdService.modifpwd(request));
	}
	
	/**
	 * 查询箱子编码
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getCage")
	@ApiOperation(value="查询箱子编码",tags = "查询箱子编码")
	public ApiResponse getCage(HttpServletRequest request) throws Exception {
		return new ApiResponse(weChatGetCageService.getCage(request));
	}

	/**
	 * 查询不含下单医院的医院名称ID集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getHospitals")
	@ApiOperation(value="查询不含下单医院的医院名称ID集合",tags = "查询不含下单医院的医院名称ID集合")
	public ApiResponse getHospitals(HttpServletRequest request) throws Exception {
		return new ApiResponse(weChatGetHospitalsService.getHospitals(request));
	}

	/**
	 * 查询运输方与医院对应的单位名称ID集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getOffice")
	@ApiOperation(value="查询运输方与医院对应的单位名称ID集合",tags = "查询运输方与医院对应的单位名称ID集合")
	public ApiResponse getOffice(HttpServletRequest request) throws Exception {
		return new ApiResponse(weChatGetOfficeService.getOffice(request));
	}
	
	/**
	 * 获取Model数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getModel")
	@ApiOperation(value="获取Model数据",tags = "获取Model数据")
	public ApiResponse getModel(HttpServletRequest request) throws Exception {
		return new ApiResponse(weChatGetModelService.getModel(request));
	}
	
	/**
	 * 微信下单
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/order")
	@ApiOperation(value="微信下单",tags = "微信下单")
	public ApiResponse order(HttpServletRequest request) throws Exception {
		return new ApiResponse(weChatOrderService.order(request));
	}
	
	/**
	 * 微信上传图片
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/uploadImg")
	@ApiOperation(value="微信上传图片",tags = "微信上传图片")
	public ApiResponse uploadImg() throws Exception {
		return new ApiResponse(weChatUploadImgService.uploadImg());
	}

	/**
	 * 微信装箱
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/zhuangXiang")
	@ApiOperation(value="微信装箱",tags = "微信装箱")
	public ApiResponse zhuangXiang(HttpServletRequest request) throws Exception {
		return new ApiResponse(weChatZhuangXiangService.zhuangXiang(request));
	}

	/**
	 * 微信发车
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/start")
	@ApiOperation(value="微信发车",tags = "微信发车")
	public ApiResponse start(HttpServletRequest request) throws Exception {
		return new ApiResponse(weChatStartService.start(request));
	}

	/**
	 * 微信样本正常，确认签收
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/normal")
	@ApiOperation(value="微信样本正常，确认签收",tags = "微信样本正常，确认签收")
	public ApiResponse normal(HttpServletRequest request) throws Exception {
		return new ApiResponse(weChatNormalService.normal(request));
	}

	/**
	 * 微信样本异常，提交异常
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/abnormal")
	@ApiOperation(value="微信样本异常，提交异常",tags = "微信样本异常，提交异常")
	public ApiResponse abnormal(HttpServletRequest request) throws Exception {
		return new ApiResponse(weChatAbnormalService.abnormal(request));
	}

	/**
	 * 微信查看所有订单
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/search")
	@ApiOperation(value="微信查看所有订单",tags = "微信查看所有订单")
	public ApiResponse search(HttpServletRequest request) throws Exception {
		return new ApiResponse(weChatSearchService.search(request));
	}

	/**
	 * 微信查看所有订单详情
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/detail")
	@ApiOperation(value="微信查看所有订单详情",tags = "微信查看所有订单详情")
	public ApiResponse detail(HttpServletRequest request) throws Exception {
		return new ApiResponse(weChatDetailService.detail(request));
	}

	/**
	 * 微信扫码开箱
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/openCage")
	@ApiOperation(value="微信扫码开箱",tags = "微信扫码开箱")
	public ApiResponse openCage(HttpServletRequest request) throws Exception {
		return new ApiResponse(weChatOpenCageService.openCage(request));
	}
	
	
	
}
