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
import com.zkt.project.biology.entity.Sample;
import com.zkt.project.biology.service.OrderSendService;
import com.zkt.project.biology.utils.ContainerContent;
import com.zkt.project.biology.utils.ExcelUtil;
import com.zkt.project.biology.utils.ExportSetInfo;
import com.zkt.project.biology.utils.SystemContent;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 新建订单
 * 
 * @author
 *
 */
@RestController
@Api(value="OrderSendController|新建订单")
@RequestMapping(value = "/api/orderSend")
public class OrderSendController {

	private Logger log = LoggerFactory.getLogger(OrderSendController.class);

	@Autowired
	private OrderSendService orderSendService;

	/**
	 * 导出样本模板
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/exportTemplate", method = RequestMethod.POST)
	public void exportTemplate() {

		String FILE_NAME = "";

		try {
			FILE_NAME = "YBXX";
			JSONObject json = ContainerContent.clientWebReceive();
			JSONObject infoList = json.getJSONObject("infoList");
			infoList.put("isExport", 'Y');
			
			ReturnObjectHandle handle = orderSendService.searchSample(infoList);
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

			String[] headers = { "样本编号", "样本类型 A和B两种情况" };
			String[] fieldName = { "sampleNumber", "sampleclassify" };
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
		}
	}

	/**
	 * 查询不含下单医院的医院名称ID集合
	 * 
	 * @return
	 */
	@PostMapping(value = "/getHospitals")
	@ApiOperation(value="查询不含下单医院的医院名称ID集合",tags = "查询不含下单医院的医院名称ID集合")
	public ReturnSimpleHandle getHospitals() {
		List<Map<String, Object>> hospitals = orderSendService.getHospitals();
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		returnHandle.setData(hospitals);
			
		return returnHandle;
	}

	/**
	 * 查询运输方与医院对应的单位名称ID集合
	 * 
	 * @return
	 */
	@PostMapping(value = "/getOffices")
	@ApiOperation(value="查询运输方与医院对应的单位名称ID集合",tags = "查询运输方与医院对应的单位名称ID集合")
	public ApiResponse getOffices() {
		JSONObject json = ContainerContent.clientWebReceive();
		return new ApiResponse(orderSendService.getOffices(json));
	}

	/**
	 * 保存订单
	 * 
	 * @return
	 */
	@PostMapping(value = "/saveOrder")
	@ApiOperation(value="保存订单",tags = "保存订单")
	public ReturnSimpleHandle saveOrder() {
		JSONObject json = ContainerContent.clientWebReceive();
		return orderSendService.saveOrder(json);
	}

	/**
	 * 保存订单样本信息
	 * 
	 * @return
	 */
	@PostMapping(value = "/saveSamples")
	@ApiOperation(value="保存订单样本信息",tags = "保存订单样本信息")
	public ApiResponse saveSamples() {
		JSONObject json = ContainerContent.clientWebReceive();
		return new ApiResponse(orderSendService.saveSamples(json));
	}

	/**
	 * 上传excel返回地址
	 * 
	 * @return
	 */
	@PostMapping(value = "/uploadExcel")
	@ApiOperation(value="上传excel返回地址",tags = "上传excel返回地址")
	public ApiResponse uploadExcel() {
		Sample samples = new Sample();
		try {
			samples = orderSendService.insertExeclInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ApiResponse(samples.getExcel());
	}
	
	/**
	 * 根据excel返回地址读取并写入库
	 * 
	 * @return
	 */
	@PostMapping(value = "/saveExcel")
	@ApiOperation(value="根据excel返回地址读取并写入库",tags = "根据excel返回地址读取并写入库")
	public ApiResponse saveExcel() {
		JSONObject json = ContainerContent.clientWebReceive();
		String jsonString = "";
		try {
			jsonString = orderSendService.saveExcel(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ApiResponse(jsonString);
	}
	
}
