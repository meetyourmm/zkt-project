package com.zkt.project.biology.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.mantu.system.content.RedisContent;
import com.zkt.common.core.util.MD5Utils;
import com.zkt.common.core.util.UserInfo;
import com.zkt.project.biology.constant.Constant;
import com.zkt.project.biology.constant.ReturnObjectHandle;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.entity.AuthRes;
import com.zkt.project.biology.entity.Login;
import com.zkt.project.biology.entity.Relate;
import com.zkt.project.biology.mapper.AuthResMapper;
import com.zkt.project.biology.mapper.LoginMapper;
import com.zkt.project.biology.mapper.RelateMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class AuthRoleTransferService {

	@Autowired
	private LoginMapper loginMapper;

	@Autowired
	private AuthResMapper authResMapper;

	@Autowired
	private RelateMapper relateMapper;

	// 查询运输方账号
	public String search(JSONObject json) throws Exception {

		Integer draw = Integer.parseInt(json.getString("draw"));// datatables返回时用
		Integer from = Integer.parseInt(json.getString("start"));
		Integer pageSize = Integer.parseInt(json.getString("pageCount"));

		Map<String, Object> map = new HashMap<>();

		map.put("userName", json.getString("userName"));// 用户名
		map.put("userOperator", json.getString("userOperator"));// 联系人
		map.put("userOffice", json.getString("userOffice"));//运输单位
		
		//默认选择运输方账号
		map.put("userType", Constant.USER_TYPE_TRANSPORT);
		map.put("pageSize", pageSize);
		map.put("from", from);
		
		List<Login> ServerList = loginMapper.selectByKeys(map);
		
		//设置上级单位的类型
		for(int i=0; i<ServerList.size(); i++){
			String userId = ServerList.get(i).getRoleId();
			String roleId = loginMapper.getRoleID(userId);
			ServerList.get(i).setRoleId(roleId);
		}
		
		JSONArray jsoa = JSONArray.fromObject(ServerList);
		Integer orderListCount = loginMapper.countByKeys(map);
		// 封装返回参数
		ReturnObjectHandle returnHandle = ReturnObjectHandle.createServerHandle();
		returnHandle.setDraw(draw);
		returnHandle.setData(jsoa);
		returnHandle.setPageCount(pageSize);
		returnHandle.setDataMaxCount(orderListCount);
		returnHandle.setDataMaxPage(
				orderListCount % pageSize == 0 ? orderListCount / pageSize : orderListCount / pageSize + 1);
		return JSONObject.fromObject(returnHandle).toString();
	}

	// 冻结或者解冻:传入id,state(0为正常，1为锁定)
	
	public void updateFreeze(JSONObject json) {
		Login login = (Login) JSONObject.toBean(json, Login.class);
		loginMapper.updateByPrimaryKeySelective(login);
	}

	// 重置密码
	public void updatePassword(String id) {
		Login loginList = loginMapper.selectByPrimaryKey(id);
		if (null != loginList && !("").equals(loginList)) {
			loginList.setPassword(MD5Utils.string2MD5(Constant.INIT_PWD));
			loginMapper.updateByPrimaryKeySelective(loginList);
		}
	}

	// 批量冻结
	public void updateFreezes(JSONArray jsonArray) {
		if (jsonArray != null && jsonArray.size() > 0) {
			for (int i = 0; i < jsonArray.size(); i++) {
				String id = jsonArray.getString(i);
				JSONObject json = new JSONObject();
				json.put("id", id);
				json.put("userIslock", "1");
				updateFreeze(json);
			}
		}
	}

	// 批量重置
	
	public void updatePasswords(JSONArray jsonArray) {
		if (jsonArray != null && jsonArray.size() > 0) {
			for (int i = 0; i < jsonArray.size(); i++) {
				String id = jsonArray.getString(i);
				updatePassword(id);
			}
		}
	}

	// 保存运输方账号
	
	public String saveServer(JSONObject json) throws Exception {
		
		String userName = json.getString("userName");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		Login login = loginMapper.login(map);
		
		// 先判断userName是否唯一
		if (login != null) {
			return ReturnSimpleHandle.createServerError("账号重复", "-1", null, null);
		}
		
		// 设置账号再保存
		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String createdBy = userInfo.getUserName();
		String userId = userInfo.getUserId();// 锁定权限
		
		Login logins = (Login) JSONObject.toBean(json, Login.class);
		logins.setUserType(Constant.USER_TYPE_TRANSPORT);// 用户类型
		logins.setRoleId(userId);// 关联上级ID
		logins.setCreatedBy(createdBy);
		logins.setCreatedAt(new Date());
		logins.setPassword(MD5Utils.string2MD5(Constant.INIT_PWD));
		logins.setUserIslock(Constant.IS_USED_Y);
		loginMapper.insertSelective(logins);
		
		// 默认运输方权限
		// 订单管理:"m2", "m24" 运单信息监控:"m31", "m32", "m33", "m34" , "m35" ,"m36","m37"
		//员工管理:"m5", "m54"	异常审核信息:"m4","m45"
		// 报表信息管理:"m6", "m66" 个人信息管理:"m8", "m81", "m82"
		String[] menus = { "m2","m24","m3","m31","m32","m33","m34","m35","m36","m37","m4","m45","m5","m54","m6","m66","m8","m81",
				"m82" };
		for (int i = 0; i < menus.length; i++) {
			AuthRes authRes = new AuthRes();
			authRes.setUserName(userName);
			authRes.setResCode(menus[i]);
			authRes.setCreatedBy(createdBy);
			authRes.setCreatedAt(new Date());
			authResMapper.insertSelective(authRes);
		}

		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		return JSONObject.fromObject(returnHandle).toString();
	}

	// 修改运输方账号
	
	public String updateServer(JSONObject json) throws Exception {
		
		long id = json.getLong("id");//ID唯一
		String userName = json.getString("userName");
		
		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String updatedBy = userInfo.getUserName();
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		Login login = loginMapper.login(map);
		
		Login loginList = loginMapper.selectByPrimaryKey(id);
		
		//账号没有修改---直接修改其他信息
		if (loginList.getUserName().equals(userName)) {
			Login logins = (Login) JSONObject.toBean(json, Login.class);
			logins.setUpdatedBy(updatedBy);
			logins.setUpdatedAt(new Date());
			loginMapper.updateByPrimaryKeySelective(logins);
		}else{
			//账号不存在无重复
			if(null == login){
				//直接修改其他信息
				Login logins = (Login) JSONObject.toBean(json, Login.class);
				logins.setUpdatedBy(updatedBy);
				logins.setUpdatedAt(new Date());
				loginMapper.updateByPrimaryKeySelective(logins);
			}
			return ReturnSimpleHandle.createServerError("账号重复", "-1", null, null);
		}
			
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		return JSONObject.fromObject(returnHandle).toString();
	}

	// 查询左侧菜单权限
	
	public String searchEmployMenus(JSONObject json) throws Exception {
		List<String> menus = authResMapper.selectByUserName(json.getString("userName"));
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		returnHandle.setData(menus);
		return JSONObject.fromObject(returnHandle).toString();
	}

	// 查询接受医院名称ID集合
	
	public List<Login> getHospitals() throws Exception {
		return loginMapper.getUserHospitals1();
	}

	// 医院与运输方绑定(多对多)
	
	public String saveRelate(JSONObject json, JSONArray jsonArray) {
		
		String userName = json.getString("userName");		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		Login login = loginMapper.login(map);
		
		if (jsonArray != null && jsonArray.size() > 0) {
			for (int i = 0; i < jsonArray.size(); i++) {
				String hospitalid = jsonArray.getString(i);
				Relate relate = new Relate();
				relate.setHospitalid(hospitalid);// 医院账号ID
				relate.setTransportid(String.valueOf(login.getId()));// 运输方账号ID
				relateMapper.insertSelective(relate);
			}
		}
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		return JSONObject.fromObject(returnHandle).toString();
	}

	// 修改医院与运输方更新绑定(多对多)
	
	public String updateRelate(JSONObject json, JSONArray jsonArray) {
		
		String transportid = json.getString("id");
		relateMapper.deleteByHospitalid(transportid);
		
		if (jsonArray != null && jsonArray.size() > 0) {
			for (int i = 0; i < jsonArray.size(); i++) {
				String hospitalid = jsonArray.getString(i);
				Relate relate = new Relate();
				relate.setHospitalid(hospitalid);// 医院账号ID
				relate.setTransportid(transportid);// 运输方账号ID
				relateMapper.insertSelective(relate);
			}
		}
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		return JSONObject.fromObject(returnHandle).toString();
	}

	// 获取运输队列表
	
	public List<String> getUserOffices() throws Exception {
		return loginMapper.getUserOffices();
	}

	// 查询Relate运输方和医院绑定集合
	
	public List<Login> getRelates(JSONObject json) throws Exception {
		String id = json.getString("id");
		return relateMapper.selectByUserName(id);
	}
	
	
	public void delete(String id) {
		//删除医院账号
		Login login=new Login();
		login.setId(id);
		login.setUserIslock(Constant.IS_USED_D);
		
		loginMapper.updateByPrimaryKeySelective(login);
		
		//删除医院员工账号
		Login employLogin=new Login();
		employLogin.setRoleId(id);
		employLogin.setUserIslock(Constant.IS_USED_D);
		loginMapper.updateByRoleId(employLogin);
		
	}

	
	public void deletes(JSONArray jsonArray) {
		// TODO Auto-generated method stub
		if (jsonArray.size()>0) {
			for (int i = 0; i < jsonArray.size(); i++) {
				String id=jsonArray.getString(i);
				delete(id);
			}
		}
	}

}
