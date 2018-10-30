package com.zkt.project.biology.rest;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zkt.common.core.util.DateUtils;
import com.zkt.common.web.response.ApiResponse;
import com.zkt.project.biology.constant.ReturnObjectHandle;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.service.QueryFinishedOrderService;
import com.zkt.project.biology.utils.ContainerContent;
import com.zkt.project.biology.utils.ExcelUtil;
import com.zkt.project.biology.utils.ExportSetInfo;
import com.zkt.project.biology.utils.SystemContent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 查询已完成订单
 * @author
 *
 */
@RestController
@Api(value="QueryFinishedOrderController| 查询已完成订单")
@RequestMapping(value = "/api/queryFinishedOrder")
public class QueryFinishedOrderController {

	private Logger log = LoggerFactory.getLogger(QueryFinishedOrderController.class);

	@Autowired
	private QueryFinishedOrderService queryFinishedOrderService;

	/**
	 * 查询已完成订单
	 * 
	 * @return
	 */
	@PostMapping(value = "/search")
	@ApiOperation(value="查询已完成订单",tags = "查询已完成订单")
	public ReturnObjectHandle search() {
		JSONObject json = ContainerContent.clientWebReceive();
		return queryFinishedOrderService.search(json);
	}

	/**
	 * 已完成订单详情
	 * 
	 * @return
	 */
	@PostMapping(value = "/detail")
	@ApiOperation(value="已完成订单详情",tags = "已完成订单详情")
	public ReturnSimpleHandle detail() {
		JSONObject json = ContainerContent.clientWebReceive();
		return queryFinishedOrderService.detail(json);
	}

	/**
	 * 导出订单表
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public void exportpass() {

		String FILE_NAME = "";

		try {
			FILE_NAME = "订单表";
			JSONObject json = ContainerContent.clientWebReceive();
			JSONObject infoList = json.getJSONObject("infoList");
			infoList.put("isExport", 'Y');
			ReturnObjectHandle handle = queryFinishedOrderService.search(infoList);
			JSONObject resultJson = JSONObject.fromObject(handle);

			JSONArray jsonList = resultJson.getJSONArray("data");
			List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) JSONArray.toCollection(jsonList,
					HashMap.class);

			LinkedHashMap<String, List> map = new LinkedHashMap<String, List>();
			map.put("sheet", list);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// 列表头部
			List<String[]> headNames = new ArrayList<String[]>();
			List<String[]> fieldNames = new ArrayList<String[]>();

			String[] headers = { "订单号", "发货医院", "运输方", "收货医院", "装箱人员", "运输人员", "接收人员", "订单状态" };
			String[] fieldName = { "orderNo", "sender", "transport", "reciver", "packOperator", "transportOperator",
					"receiveOperator", "orderStatus" };
			headNames.add(headers);
			fieldNames.add(fieldName);

			ExportSetInfo setInfo = new ExportSetInfo();
			// 导出数据存放处
			setInfo.setObjsMap(map);
			// 每列 属性名称
			setInfo.setFieldNames(fieldNames);
			// 大标题
			setInfo.setTitles(new String[] { FILE_NAME });
			// 每列 标题
			setInfo.setHeadNames(headNames);
			// 输出流
			setInfo.setOut(baos);

			// 将需要导出的数据输出到baos
			ExcelUtil.exportExcel(setInfo);

			InputStream fis = new ByteArrayInputStream(baos.toByteArray());
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			SystemContent.getResponse().reset();
			// 设置response的Header
			String defName = FILE_NAME + DateUtils.ymdhms4().substring(0, 8) + ".xls";
			SystemContent.getResponse().addHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode(defName, "UTF-8"));
			SystemContent.getResponse().addHeader("Content-Length", "" + baos.toByteArray().length);
			OutputStream toClient = new BufferedOutputStream(SystemContent.getResponse().getOutputStream());
			SystemContent.getResponse().setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();

		} catch (Exception e) {
			ReturnSimpleHandle.createServerError(log, e);
		}
	}

}
