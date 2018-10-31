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
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zkt.common.core.util.DateUtils;
import com.zkt.project.biology.constant.ReturnObjectHandle;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.service.ReportSenderSelfService;
import com.zkt.project.biology.utils.ContainerContent;
import com.zkt.project.biology.utils.ExcelUtil;
import com.zkt.project.biology.utils.ExportSetInfo;
import com.zkt.project.biology.utils.SystemContent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 本院发货报表
 * 
 * @author
 *
 */
@RestController
@Api(value="ReportSenderSelfController| 各运输方发货报表")
@RequestMapping(value = "/api/bio/reportSenderSelf")
public class ReportSenderSelfController {

	private Logger log = LoggerFactory.getLogger(ReportSenderSelfController.class);

	@Autowired
	private ReportSenderSelfService reportSenderSelfService;

	/**
	 * 查询全部订单
	 * 
	 * @return
	 */
	@PostMapping(value = "/search")
	@ApiOperation(value="查询全部订单",tags = "查询全部订单")
	public ReturnObjectHandle search(@ApiParam(name="startDate",value="开始时间") String startDate,
			@ApiParam(name="endDate",value="结束时间") String endDate,
			@ApiParam(name="orderStatus",value="订单状态") String orderStatus,
			@ApiParam(name="isProblem",value="异常 0：是异常件 1：不是异常件") String isProblem,
			@ApiParam(name="draw",value="返回参数") String draw,
			@ApiParam(name="start",value="偏移量") String start,
			@ApiParam(name="pageCount",value="页宽") String pageCount) {
		Map map = new HashMap();
		map.put("startDate",startDate);
		map.put("endDate",endDate);
		map.put("orderStatus",orderStatus);
		map.put("isProblem",isProblem);
		map.put("draw",draw);
		map.put("start",start);
		map.put("pageCount",pageCount);
		JSONObject json = JSONObject.fromObject( map );
		return reportSenderSelfService.search(json);
	}

	/**
	 * 全部订单详情
	 * 
	 * @return
	 */
	@PostMapping(value = "/detail")
	@ApiOperation(value="全部订单详情",tags = "全部订单详情")
	public ReturnSimpleHandle detail(@ApiParam(name="orderNo",value="订单编号",required=true) String orderNo) {
		Map map = new HashMap();
		map.put("orderNo",orderNo);
		JSONObject json = JSONObject.fromObject( map );
		return reportSenderSelfService.detail(json);
	}

	/**
	 * 导出订单表
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/export")
	@ApiOperation(value="导出订单表",tags = "导出订单表")
	public void exportpass(@ApiParam(name="startDate",value="开始时间") String startDate,
			@ApiParam(name="endDate",value="结束时间") String endDate,
			@ApiParam(name="orderStatus",value="订单状态") String orderStatus,
			@ApiParam(name="isProblem",value="异常 0：是异常件 1：不是异常件") String isProblem,
			@ApiParam(name="draw",value="返回参数") String draw,
			@ApiParam(name="start",value="偏移量") String start,
			@ApiParam(name="pageCount",value="页宽") String pageCount) {

		String FILE_NAME = "";

		try {
			FILE_NAME = "订单表";
			Map paramMap = new HashMap();
			paramMap.put("startDate",startDate);
			paramMap.put("endDate",endDate);
			paramMap.put("orderStatus",orderStatus);
			paramMap.put("isProblem",isProblem);
			paramMap.put("draw",draw);
			paramMap.put("start",start);
			paramMap.put("pageCount",pageCount);
			paramMap.put("isExport", 'Y');
			JSONObject json = JSONObject.fromObject( paramMap );
			ReturnObjectHandle handle = reportSenderSelfService.search(json);

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

			String[] headers = { "订单号", "发货医院", "运输方", "收货医院", "装箱人员", "运输人员", "接收人员", "订单状态", "创建时间" };
			String[] fieldName = { "orderNo", "sender", "transport", "reciver", "packOperator", "transportOperator",
					"receiveOperator", "orderStatus", "createdAt" };
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
