package com.zkt.project.biology.rest;

import com.alibaba.fastjson.JSONObject;
import com.zkt.common.core.util.ConverterUtil;
import com.zkt.common.web.response.ApiResponse;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.entity.Order;
import com.zkt.project.biology.service.OrderSendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: zkt-project
 * @BelongsPackage: com.zkt.project.biology.rest
 * @Author: PJK
 * @CreateTime: 2018-10-29 15:29
 */
@RestController
@Api(value = "OrderApiController|订单信息接口")
@RequestMapping("/api/bio")
public class OrderApiController {

    @Autowired
    private OrderSendService orderSendService;


    /**
     * @return
     */
    @PostMapping(value = "/getCreateOrderInfo")
    @ApiOperation(value = "创建订单", tags = "创建订单")
    public ReturnSimpleHandle getCreateOrderInfo(@RequestBody @ApiParam(name = "订单对象", value = "传入json格式", required = true) Order order) {
        return orderSendService.saveOrder(ConverterUtil.bean2JsonObject(order));
    }
}
