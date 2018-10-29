package com.zkt.project.biology.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
public class AuthRoleMonitorService {

	@Autowired
	private LoginMapper loginMapper;

	@Resource(name = "authResMapper")
	private AuthResMapper authResMapper;

	// 查询市监账号
	public String search(JSONObject json) throws Exception {
		
		Integer draw = Integer.parseInt(json.getString("draw"));// datatables返回时用
		Integer from = Integer.parseInt(json.getString("start"));
		Integer pageSize = Integer.parseInt(json.getString("pageCount"));
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("userName", json.getString("userName"));// 用户名
		map.put("userOperator", json.getString("userOperator"));// 联系人
		map.put("city", json.getString("city"));//市	
		
		//默认查询市级账号
		map.put("userType", Constant.USER_TYPE_CITY);
		map.put("pageSize", pageSize);
		map.put("from", from);
		
		List<Login> ServerList = loginMapper.selectByKeys(map);
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
		Login loginList = loginMapper.selectByPrimaryKey(Long.parseLong(id));
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

	// 保存市监账号
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
		logins.setUserType(Constant.USER_TYPE_CITY);//用户类型
		logins.setRoleId(userId);//关联上级ID
		logins.setCreatedBy(createdBy);
		logins.setCreatedAt(new Date());
		logins.setPassword(MD5Utils.string2MD5(Constant.INIT_PWD));
		logins.setUserIslock(Constant.IS_USED_Y);
		loginMapper.insertSelective(logins);
		
		//默认市级权限	
		//运单信息监控:"m3","m31","m32","m33","m35","m36","m37"	异常件仲裁(市级):"m4","m42","m44","m45"	报表信息管理:"m6","m61","m62"	个人信息管理:"m8","m81","m82"
		String[] menus = {"m3","m31","m32","m35","m36","m37","m4","m42","m44","m45","m6","m61","m62","m8","m81","m82"};
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

	// 修改市监账号
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
	
	//获取城市列表
	public List<String> getCitys() throws Exception {
		return loginMapper.getCitys();
	}

	//删除账号
	public void delete(String id) {
		// TODO Auto-generated method stub
		Login login = new Login();
		
		login.setId(id);
		login.setUserIslock(Constant.IS_USED_D);
		
		loginMapper.updateByPrimaryKeySelective(login);
	}

	public void deletes(JSONArray jsonArray) {
		// TODO Auto-generated method stub
		if(jsonArray.size()>0){
			for(int i=0; i<jsonArray.size();i++){
				String id=jsonArray.getString(i);
				delete(id);
			}
		}
	}

}