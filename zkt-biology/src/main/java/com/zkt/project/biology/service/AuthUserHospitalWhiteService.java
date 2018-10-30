package com.zkt.project.biology.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zkt.project.biology.constant.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkt.common.core.util.MD5Utils;
import com.zkt.common.core.util.UserInfo;
import com.zkt.project.biology.constant.Constant;
import com.zkt.project.biology.constant.ReturnObjectHandle;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.entity.Login;
import com.zkt.project.biology.mapper.AuthResMapper;
import com.zkt.project.biology.mapper.LoginMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class AuthUserHospitalWhiteService {

	@Autowired
	private LoginMapper loginMapper;

	@Autowired
	private AuthResMapper authResMapper;

	// 查询医院白卡账号
	public ReturnObjectHandle search(JSONObject json){
		
		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String userType = userInfo.getUserType();
		String userId = userInfo.getUserId();
		
		Integer draw = Integer.parseInt(json.getString("draw"));// datatables返回时用
		Integer from = Integer.parseInt(json.getString("start"));
		Integer pageSize = Integer.parseInt(json.getString("pageCount"));
		
		Map<String, Object> map = new HashMap<>();
		map.put("userName", json.getString("userName"));// 用户名
		map.put("userTel", json.getString("userTel"));// 电话
		map.put("pageSize", pageSize);
		map.put("from", from);
		
		//管理员查看全部医院白卡
		if (Constant.USER_TYPE_BOSS.equals(userType)) {
			map.put("userType", Constant.USER_TYPE_HOSPITALWHITE);
		} 
		
		//医院账号查看各自对应的医院白卡
		if(Constant.USER_TYPE_HOSPITAL.equals(userType)){
			map.put("userType", Constant.USER_TYPE_HOSPITALWHITE);
			map.put("roleId", userId);//通过userType roleId 锁定医院所属员工
		}
		
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
		return returnHandle;
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

	// 保存医院白卡账号
	public ReturnSimpleHandle saveServer(JSONObject json){

		String userName = json.getString("userName");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		Login login = loginMapper.login(map);
		
		// 先判断userName是否唯一
		if (login != null) {
			return ReturnSimpleHandle.createServerError("账号重复",  SystemConstant.ERROR_MESSAGE_SERVER_CODE_F01, null, null);
		}
		
		// 设置账号再保存
		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String createdBy = userInfo.getUserName();
		String userId = userInfo.getUserId();// 锁定权限
		JSONObject userJson = JSONObject.fromObject(userInfo.getUser());
		String city = userJson.getString("city");
		String area = userJson.getString("area");
		
		Login logins = (Login) JSONObject.toBean(json, Login.class);
		logins.setUserType(Constant.USER_TYPE_HOSPITALWHITE);// 用户类型
		logins.setRoleId(userId);// 关联上级ID
		logins.setCreatedBy(createdBy);
		logins.setCreatedAt(new Date());
		logins.setPassword(MD5Utils.string2MD5(Constant.INIT_PWD));
		logins.setUserIslock(Constant.IS_USED_Y);
		logins.setCity(city);
		logins.setArea(area);
		loginMapper.insertSelective(logins);

		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		return returnHandle;
	}

	// 修改医院白卡账号
	public ReturnSimpleHandle updateServer(JSONObject json){
		
		/*String menusArr = json.getString("menusIdStr");
		json.remove("menusIdStr");*/

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
			return ReturnSimpleHandle.createServerError("账号已存在",  SystemConstant.ERROR_MESSAGE_SERVER_CODE_F01, null, null);
		}

		/*// 保存权限信息,先删除
		authResMapper.deleteByUserName(userName);
		JSONArray menusJsoa = JSONArray.fromObject(menusArr);
		if (menusArr != null && !menusJsoa.isEmpty()) {
			for (int i = 0; i < menusJsoa.size(); i++) {
				// 依次保存权限信息
				AuthRes authRes = new AuthRes();
				authRes.setUserName(userName);
				authRes.setResCode(menusJsoa.getString(i));
				authRes.setCreatedBy(updatedBy);
				authRes.setCreatedAt(new Date());
				authResMapper.insertSelective(authRes);
			}
		}*/

		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		return returnHandle;
	}

	// 查询左侧菜单权限
	
	public ReturnSimpleHandle searchEmployMenus(JSONObject json){
		List<String> menus = authResMapper.selectByUserName(json.getString("userName"));
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		returnHandle.setData(menus);
		return returnHandle;
	}

	// 单个删除
	public void delete(String id) {
		
		//删除账号不是真删除 是改变状态 权限表保留
		Login login = loginMapper.selectByPrimaryKey(Long.parseLong(id));
		login.setUserIslock(Constant.IS_USED_D);//用户状态 删除
		loginMapper.updateByPrimaryKeySelective(login);
		
		/*loginMapper.deleteByPrimaryKey(Long.parseLong(id));
		authResMapper.deleteByPrimaryKey(Long.parseLong(id));*/
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
