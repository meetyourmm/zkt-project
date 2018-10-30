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
import com.zkt.project.biology.constant.ReturnObjectHandle;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.service.AuditAreaService;
import com.zkt.project.biology.utils.ContainerContent;
import com.zkt.project.biology.utils.ExcelUtil;
import com.zkt.project.biology.utils.ExportSetInfo;
import com.zkt.project.biology.utils.SystemContent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 区级仲裁
 * 
 * @author
 *
 */
@RestController
@Api(value="AuditAreaController|区级仲裁")
@RequestMapping(value = "/api/bio/auditArea")
public class AuditAreaController {

	private Logger log = LoggerFactory.getLogger(AuditAreaController.class);

	@Autowired
	private AuditAreaService auditAreaService;
	
	/**
	 * 获取对应订单的温湿度
	 * @return
	 */
	@PostMapping(value = "/getProcess")
	@ApiOperation(value="获取对应订单的温湿度",tags = "获取对应订单的温湿度")
	public ReturnSimpleHandle getProcess() {
		JSONObject json = ContainerContent.clientWebReceive();
		return auditAreaService.getProcess(json);
	}
	
	/**
	 * 获取对应订单的运动轨迹
	 * @return
	 */
	@PostMapping(value = "/getTrajectory")
	@ApiOperation(value="获取对应订单的运动轨迹",tags = "获取对应订单的运动轨迹")
	public ReturnSimpleHandle getTrajectory() {
		JSONObject json = ContainerContent.clientWebReceive();
		return auditAreaService.getTrajectory(json);
	}
	
	/**
	 * 查看区级仲裁件
	 * 
	 * @return
	 */
	@PostMapping(value = "/search")
	@ApiOperation(value="查看区级仲裁件",tags = "查看区级仲裁件")
	public ReturnObjectHandle search() {
		JSONObject json = ContainerContent.clientWebReceive();
		return auditAreaService.search(json);
	}
	
	/**
	 * 查看区级已审核件
	 * 
	 * @return
	 */
	@PostMapping(value = "/searchAudited")
	@ApiOperation(value="查看区级已审核件",tags = "查看区级已审核件")
	public ReturnObjectHandle searchAudited() {
		JSONObject json = ContainerContent.clientWebReceive();
		return auditAreaService.searchAudited(json);
	}
	
	/**
	 * 查看区级仲裁件详情
	 * 
	 * @return
	 */
	@PostMapping(value = "/detail")
	@ApiOperation(value="查看区级仲裁件详情",tags = "查看区级仲裁件详情")
	public ReturnSimpleHandle detail() {
		JSONObject json = ContainerContent.clientWebReceive();
		return auditAreaService.detail(json);
	}
	
	/**
	 * 查看区级已审核件详情
	 * 
	 * @return
	 */
	@PostMapping(value = "/audited")
	@ApiOperation(value="查看区级已审核件详情",tags = "查看区级已审核件详情")
	public ReturnSimpleHandle audited() {
		JSONObject json = ContainerContent.clientWebReceive();
		return auditAreaService.audited(json);
	}
	
	/**
	 * 审核结束
	 * 
	 * @return
	 */
	@PostMapping(value = "/over")
	@ApiOperation(value="审核结束",tags = "审核结束")
	public ReturnSimpleHandle over() {
		JSONObject json = ContainerContent.clientWebReceive();
		auditAreaService.updateOver(json);
		return ReturnSimpleHandle.createServerHandle();
	}

	/**
	 * 待审提交上级
	 * 
	 * @return
	 */
	@PostMapping(value = "/submit")
	@ApiOperation(value="待审提交上级",tags = "待审提交上级")
	public ReturnSimpleHandle submit() {
		JSONObject json = ContainerContent.clientWebReceive();
		auditAreaService.updateSubmit(json);
		return ReturnSimpleHandle.createServerHandle();
	}

	/**
	 * 导出订单表
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public void export() {

		String FILE_NAME = "";

		try {
			FILE_NAME = "订单表";
			JSONObject json = ContainerContent.clientWebReceive();
			JSONObject infoList = json.getJSONObject("infoList");
			infoList.put("isExport", 'Y');
			ReturnObjectHandle handle = auditAreaService.search(infoList);

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
	
	/**
	 * 导出订单表
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/exportAudited", method = RequestMethod.POST)
	public void exportAudited() {

		String FILE_NAME = "";

		try {
			FILE_NAME = "订单表";
			JSONObject json = ContainerContent.clientWebReceive();
			JSONObject infoList = json.getJSONObject("infoList");
			infoList.put("isExport", 'Y');
			ReturnObjectHandle handle = auditAreaService.searchAudited(infoList);

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
