package com.zkt.project.biology.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zkt.project.biology.constant.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkt.common.core.util.UserInfo;
import com.zkt.project.biology.constant.Constant;
import com.zkt.project.biology.constant.ReturnObjectHandle;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.entity.Cage;
import com.zkt.project.biology.entity.Login;
import com.zkt.project.biology.mapper.CageMapper;
import com.zkt.project.biology.mapper.LoginMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class CageService {

	@Autowired
	private CageMapper cageMapper;
	
	@Autowired
	private LoginMapper loginMapper;

	// 查询箱体
	
	public ReturnObjectHandle search(JSONObject json) throws Exception {
		
		String cageno = json.getString("cageno");
		String state = json.getString("state");
		
		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String userId = userInfo.getUserId();
		String userType=userInfo.getUserType();
		
		Integer draw = Integer.parseInt(json.getString("draw"));// datatables返回时用
		Integer from = Integer.parseInt(json.getString("start"));
		Integer pageSize = Integer.parseInt(json.getString("pageCount"));
		
		Map<String, Object> map = new HashMap<>();		
		map.put("cageno", cageno);// 箱体编号
		map.put("state", state);// 状态
		
		if(!userType.equals(Constant.USER_TYPE_BOSS)){
			map.put("hospitalid", userId);
		}
		map.put("pageSize", pageSize);
		map.put("from", from);
		
		//根据箱体编号和状态及对应医院ID查询
		List<Cage> cageList = cageMapper.selectByKeys(map);
		JSONArray jsoa = JSONArray.fromObject(cageList);
		Integer orderListCount = cageMapper.countByKeys(map);
		// 封装返回参数
		ReturnObjectHandle returnHandle = ReturnObjectHandle.createServerHandle();
		returnHandle.setDraw(draw);
		returnHandle.setData(jsoa);
		returnHandle.setPageCount(pageSize);
		returnHandle.setDataMaxCount(orderListCount);
		returnHandle.setDataMaxPage(
				orderListCount % pageSize == 0 ? orderListCount / pageSize : orderListCount / pageSize + 1);
		return returnHandle;
	}

	// 保存箱体
	
	public ReturnSimpleHandle saveServer(JSONObject json) throws Exception {
		
		String cageno = json.getString("cageno");
		
		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String userName = userInfo.getUserName();
		String userId = String.valueOf(userInfo.getUserId());
		String userType=userInfo.getUserType();
		
		JSONObject userJson = JSONObject.fromObject(userInfo.getUser());
		String city = userJson.getString("city");
		String area = userJson.getString("area");
		String userHospital="";
		String hospitalId="";
		if(userType.equals(Constant.USER_TYPE_BOSS)){
			userHospital=json.getString("hospital");
			hospitalId=json.getString("hospitalId");
			Login login=loginMapper.selectByPrimaryKey(Long.parseLong(hospitalId));
			city=login.getCity();
			area=login.getArea();
		}else{
			userHospital = userJson.getString("userHospital");
			hospitalId=userId;
		}
		
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cageno", cageno);
		Cage cage = cageMapper.login(map);
		
		// 先判断userName是否唯一
		if (cage != null) {
			return ReturnSimpleHandle.createServerError("箱体编码重复",  SystemConstant.ERROR_MESSAGE_SERVER_CODE_F01, null, null);
		}
		
		// 保存箱体
		Cage cages = (Cage) JSONObject.toBean(json, Cage.class);		
		cages.setHospitalid(hospitalId);//医院账号ID
		cages.setState(Constant.CAGESTATE_FREE);
		cages.setCity(city);
		cages.setArea(area);
		cages.setUserHospital(userHospital);
		cages.setCreatedBy(userName);
		cages.setCreatedAt(new Date());
		cageMapper.insertSelective(cages);
		
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		return returnHandle;
	}

	
	public ReturnSimpleHandle delete(JSONObject json) {
		Long id=json.getLong("id");
		
		cageMapper.deleteByPrimaryKey(id);
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		return returnHandle;
	}

}
