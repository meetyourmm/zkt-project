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
import com.zkt.project.biology.mapper.AuthResMapper;
import com.zkt.project.biology.mapper.LoginMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class AuthRoleHospitalService {

	@Autowired
	private LoginMapper loginMapper;

	@Autowired
	private AuthResMapper authResMapper;

	// 查询医院账号
	
	public String search(JSONObject json) {

		Integer draw = Integer.parseInt(json.getString("draw"));// datatables返回时用
		Integer from = Integer.parseInt(json.getString("start"));
		Integer pageSize = Integer.parseInt(json.getString("pageCount"));
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("userName", json.getString("userName"));// 用户名
		map.put("userOperator", json.getString("userOperator"));// 联系人
		map.put("userHospital", json.getString("userHospital"));//医院
		
		//默认选择医院账号
		map.put("userType", Constant.USER_TYPE_HOSPITAL);
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

	// 保存医院账号
	
	public String saveServer(JSONObject json) {
		
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
		logins.setUserType(Constant.USER_TYPE_HOSPITAL);//用户类型
		logins.setRoleId(userId);//关联上级ID
		logins.setCreatedBy(createdBy);
		logins.setCreatedAt(new Date());
		logins.setPassword(MD5Utils.string2MD5(Constant.INIT_PWD));
		logins.setUserIslock(Constant.IS_USED_Y);
		loginMapper.insertSelective(logins);
		
		// 默认医院权限
		//订单管理:"m2", "m21", "m22", "m25"	运单信息监控:"m3", "m31", "m32", "m33", "m34" , "m35" 异常审核信息:"m4","m45"
		//员工管理:"m5", "m52", "m53"		报表信息管理:"m6", "m65"		箱体管理:"m7", "m71"		个人信息管理:"m8", "m81", "m82"		模板管理:"m9", "m91"		系统管理:"m10", "m101"	
		String[] menus = {"m2","m21","m22","m23","m25","m3","m31","m32","m33","m34","m35","m36","m37","m4","m45","m5","m52","m53","m6","m65","m7","m71","m8","m81","m82","m9","m91","m10","m101" };
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

	// 修改医院账号
	
	public String updateServer(JSONObject json) {
		
		String id = json.getString("id");//ID唯一
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
			
			Login employLogin=new Login();
			employLogin.setCity(json.getString("city"));
			employLogin.setArea(json.getString("area"));
			employLogin.setRoleId(id);
			loginMapper.updateByRoleId(employLogin);
		}else{
			//账号不存在无重复
			if(null == login){
				//直接修改其他信息
				Login logins = (Login) JSONObject.toBean(json, Login.class);
				logins.setUpdatedBy(updatedBy);
				logins.setUpdatedAt(new Date());
				loginMapper.updateByPrimaryKeySelective(logins);
				
				Login employLogin=new Login();
				employLogin.setCity(json.getString("city"));
				employLogin.setArea(json.getString("area"));
				employLogin.setRoleId(id);
				loginMapper.updateByRoleId(employLogin);
			}
			return ReturnSimpleHandle.createServerError("账号重复", "-1", null, null);
		}
			
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		return JSONObject.fromObject(returnHandle).toString();
	}

	// 查询左侧菜单权限
	
	public List<String> searchEmployMenus(JSONObject json) {
		return authResMapper.selectByUserName(json.getString("userName"));
	}
	
	//获取医院列表
	
	public List<String> getUserHospitals() {
		return loginMapper.getUserHospitals();
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
