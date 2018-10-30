package com.zkt.project.biology.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkt.common.web.response.ApiResponse;
import com.zkt.project.biology.service.HomeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * @author
 */
@RestController
@Api(value="HomeApiController|获取市区医院运输公司数量及订单各参数数量")
@RequestMapping("/api/bio/home")
public class HomeApiController {

	//private static Logger logger = LoggerFactory.getLogger(HomeApiController.class);

	@Autowired
	private HomeService homeService;
	
	/**
	 * 获取市区 医院 运输公司数量 及订单各参数数量
	 * @return
	 */
	@PostMapping(value = "/getNums")
    @ApiOperation(value="获取市区医院运输公司数量及订单各参数数量",tags = "获取市区医院运输公司数量及订单各参数数量")
	//public ApiResponse getNums(@ApiParam(name="id",value="用户id",required=true) String id) 
	public ApiResponse getNums() {
		return new ApiResponse(homeService.getNums());
	}

}
