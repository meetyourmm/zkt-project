package com.zkt.project.biology.rest;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

import io.swagger.annotations.ApiParam;
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
import com.zkt.project.biology.service.AuditCityService;
import com.zkt.project.biology.utils.ContainerContent;
import com.zkt.project.biology.utils.ExcelUtil;
import com.zkt.project.biology.utils.ExportSetInfo;
import com.zkt.project.biology.utils.SystemContent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 市级仲裁
 * 
 * @author
 *
 */
@RestController
@Api(value="AuditCityController|市级仲裁")
@RequestMapping(value = "/api/bio/auditCity")
public class AuditCityController {

	private Logger log = LoggerFactory.getLogger(AuditCityController.class);

	@Autowired
	private AuditCityService auditCityService;

	/**
	 * 查看市级仲裁件
	 * 
	 * @return
	 */
	@PostMapping(value = "/search")
	@ApiOperation(value="查看市级仲裁件",tags = "查看市级仲裁件")
	public ReturnObjectHandle search(@ApiParam(name="orderNo",value="订单号") String orderNo,
									 @ApiParam(name="cageno",value="箱体编号") String cageno,
									 @ApiParam(name="classify",value="下单分类") String classify,
									 @ApiParam(name="draw",value="") String draw ,
									 @ApiParam(name="start",value="起始页") String start,
									 @ApiParam(name="pageCount",value="分页大小") String pageCount) {
		Map map = new HashMap();
		map.put("orderNo",orderNo);
		map.put("cageno",cageno);
		map.put("classify",classify);
		map.put("draw",draw);
		map.put("start",start);
		map.put("pageCount",pageCount);
		JSONObject json = JSONObject.fromObject( map );
		return auditCityService.search(json);
	}

	/**
	 * 查看市级仲裁件详情
	 * 
	 * @return
	 */
	@PostMapping(value = "/detail")
	@ApiOperation(value="查看市级仲裁件详情",tags = "查看市级仲裁件详情")
	public ReturnSimpleHandle detail(@ApiParam(name="orderNo",value="订单号",required=true) String orderNo) {
		return auditCityService.detail(orderNo);
	}

	/**
	 * 审核终了
	 * 
	 * @return
	 */
	@PostMapping(value = "/over")
	@ApiOperation(value="审核终了",tags = "审核终了")
	public ReturnSimpleHandle over(@ApiParam(name="orderNo",value="订单号") String orderNo,
								   @ApiParam(name="comments",value="市级审核意见") String comments) {
		Map map = new HashMap();
		map.put("orderNo",orderNo);
		map.put("commentsShi",comments);
		JSONObject json = JSONObject.fromObject( map );
		auditCityService.updateOver(json);
		return ReturnSimpleHandle.createServerHandle();
	}

	/**
	 * 导出订单表
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public void exportpass(@ApiParam(name="infoList",value="订单表json数据") Map infoListMap) {

		String FILE_NAME = "";

		try {
			FILE_NAME = "订单表";
			JSONObject json = JSONObject.fromObject(infoListMap);
			JSONObject infoList = json.getJSONObject("infoList");
			infoList.put("isExport", 'Y');
			ReturnObjectHandle handle = auditCityService.search(infoList);

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
