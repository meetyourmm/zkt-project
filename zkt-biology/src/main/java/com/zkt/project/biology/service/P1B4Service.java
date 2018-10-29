package com.zkt.project.biology.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkt.common.core.util.UserInfo;
import com.zkt.project.biology.constant.ReturnObjectHandle;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.entity.Model;
import com.zkt.project.biology.mapper.LoginMapper;
import com.zkt.project.biology.mapper.ModelMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class P1B4Service {

	@Autowired
	private LoginMapper loginMapper;

	@Autowired
	private ModelMapper modelMapper;

	// 获取modelNo列表(全表参数)
	public String search(JSONObject json) throws Exception {

		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String userId = userInfo.getUserId();

		String modelNo = json.getString("modelNo");

		Integer draw = Integer.parseInt(json.getString("draw"));
		Integer from = Integer.parseInt(json.getString("start"));
		Integer pageSize = Integer.parseInt(json.getString("pageCount"));

		// 获取每个市 区 医院所对应的订单模板
		Map<String, Object> map = new HashMap<>();
		map.put("modelNo", modelNo);
		map.put("senderid", userId);
		map.put("pageSize", pageSize);
		map.put("from", from);

		List<Model> modelList = modelMapper.selectAll(map);
		Integer modelListCount = modelMapper.countAll(map);

		// 封装返回参数
		ReturnObjectHandle returnHandle = ReturnObjectHandle.createServerHandle();
		returnHandle.setDraw(draw);
		returnHandle.setData(modelList);
		returnHandle.setPageCount(pageSize);
		returnHandle.setDataMaxCount(modelListCount);
		returnHandle.setDataMaxPage(
				modelListCount % pageSize == 0 ? modelListCount / pageSize : modelListCount / pageSize + 1);
		return JSONObject.fromObject(returnHandle).toString();
	}

	// 获取modelNo列表
	public List<Model> getModelNo() throws Exception {

		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String userId = userInfo.getUserId();

		// 获取每个市 区 医院所对应的订单模板
		Map<String, Object> map = new HashMap<>();
		map.put("senderid", userId);
		return modelMapper.getModelNo(map);
	}

	// 获取Model数据自动填充页面
	public List<Model> searchModel(JSONObject json) throws Exception {

		// 根据模板ID查询
		String id = json.getString("id");
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		return modelMapper.selectModel(map);
	}

	// 保存订单模板
	public String saveModel(JSONObject json) throws Exception {

		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String userId = String.valueOf(userInfo.getUserId());

		String id = json.getString("id");// 承运单位ID
		String userHospitalId = json.getString("userHospitalId");// 医院ID
		String tlimitup = json.getString("tlimitup");
		String tlimitdown = json.getString("tlimitdown");
		String hlimitup = json.getString("hlimitup");
		String hlimitdown = json.getString("hlimitdown");
		String modelNo = json.getString("modelNo");

		Map<String, Object> map = new HashMap<>();
		map.put("modelNo", modelNo);
		map.put("senderid", userId);

		List<Model> oldModel = modelMapper.selectModel(map);
		if (oldModel != null && oldModel.size() > 0) {
			return ReturnSimpleHandle.createServerError("模板名称不能重复", "-1", null, null);
		}

		Model model = new Model();
		model.setModelNo(modelNo);
		model.setSenderid(userId);// 医院账号ID
		model.setTransportid(id);
		model.setReciverid(userHospitalId);
		model.setTlimitup(tlimitup);
		model.setTlimitdown(tlimitdown);
		model.setHlimitup(hlimitup);
		model.setHlimitdown(hlimitdown);
		model.setSender(loginMapper.getHospital(userId));
		model.setTransport(loginMapper.getOffice(id));
		model.setReciver(loginMapper.getHospital(userHospitalId));
		modelMapper.insertSelective(model);

		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		return JSONObject.fromObject(returnHandle).toString();
	}

	// 修改订单模板
	public String updateModel(JSONObject json) throws Exception {

		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String userId = String.valueOf(userInfo.getUserId());

		String id = json.getString("id");// 被修改模板ID
		String userOfficeId = json.getString("userOfficeId");// 承运单位ID
		String userHospitalId = json.getString("userHospitalId");// 医院ID
		String tlimitup = json.getString("tlimitup");
		String tlimitdown = json.getString("tlimitdown");
		String hlimitup = json.getString("hlimitup");
		String hlimitdown = json.getString("hlimitdown");
		String modelNo = json.getString("modelNo");

		Model model = new Model();
		model.setId(id);
		model.setModelNo(modelNo);
		model.setSenderid(userId);// 医院账号ID
		model.setTransportid(userOfficeId);
		model.setReciverid(userHospitalId);
		model.setTlimitup(tlimitup);
		model.setTlimitdown(tlimitdown);
		model.setHlimitup(hlimitup);
		model.setHlimitdown(hlimitdown);
		model.setSender(loginMapper.getHospital(userId));
		model.setTransport(loginMapper.getOffice(userOfficeId));
		model.setReciver(loginMapper.getHospital(userHospitalId));
		modelMapper.updateByPrimaryKeySelective(model);

		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		return JSONObject.fromObject(returnHandle).toString();
	}

	// 单个删除
	public void delete(String id) {

		modelMapper.deleteByPrimaryKey(id);
	}

	// 批量删除
	public void deletes(JSONArray jsonArray) {
		if (jsonArray != null && jsonArray.size() > 0) {
			for (int i = 0; i < jsonArray.size(); i++) {
				String id = jsonArray.getString(i);
				// 单个删除
				delete(id);
			}
		}
	}

}
