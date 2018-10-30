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
import com.zkt.common.core.util.WeatherUtils;
import com.zkt.project.biology.constant.Constant;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.entity.Login;
import com.zkt.project.biology.mapper.AuthResMapper;
import com.zkt.project.biology.mapper.LoginMapper;
import com.zkt.project.biology.mapper.OrderMapper;
import com.zkt.project.biology.mapper.ProblemmonitorMapper;

import net.sf.json.JSONObject;

@Service
public class LoginService {

	@Autowired
	private LoginMapper loginMapper;

	@Autowired
	private AuthResMapper authResMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private ProblemmonitorMapper problemmonitorMapper;
	
	// 登入查询
	public String login(String userName, String password) {

		String jsonString = "";

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("password", MD5Utils.string2MD5(password));
		map.put("userName", userName);

		// 查Login登入表
		Login login = loginMapper.login(map);
		
		// 账户是否存在
		if(null == login){
			return ReturnSimpleHandle.createServerError("用户名或者密码错误", "-1", null, null);
		}
		
		// 医院员工 运输方员工 医院白卡账号 不能登入系统
		if (login.getUserType().equals(Constant.USER_TYPE_EMPLOY)
				|| login.getUserType().equals(Constant.USER_TYPE_TRANSPORTEMPLOY)
				|| login.getUserType().equals(Constant.USER_TYPE_HOSPITALWHITE)) {
			return ReturnSimpleHandle.createServerError("您的账号无权访问", "-1", null, null);
		}
				
		// 判断账号启用状态
		if (Constant.IS_USED_N.equals(login.getUserIslock())) {
			return ReturnSimpleHandle.createServerError("您的账号已被停用", "-1", null, null);
		}
		// 判断账号删除状态
		if (Constant.IS_USED_D.equals(login.getUserIslock())) {
			return ReturnSimpleHandle.createServerError("您的账号已被删除", "-1", null, null);
		}
		
		UserInfo userInfo = new UserInfo();
		// 资源权限
		List<String> authArr = authResMapper.selectByUserName(userName);
		userInfo.setUser(login);
		userInfo.setAuthRes(authArr);

		// 将信息刷入缓存
		userInfo.setLogin(true);
		userInfo.setUserId(login.getId());
		userInfo.setUserName(login.getUserName());
		userInfo.setUserPswd(login.getPassword());
		userInfo.setUserType(login.getUserType());
//		RedisContent.flushUserInfo(userInfo);

		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		jsonString = JSONObject.fromObject(returnHandle).toString();
		return jsonString;
	}

	// 修改密码
	public String updatepwd(JSONObject json) {

		String userName = json.getString("userName");// 用户名
		String oldpwd = json.getString("oldpwd");// 老密码
		String newpwd = json.getString("newpwd");// 新密码

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("password", MD5Utils.string2MD5(oldpwd));
		map.put("userName", userName);

		Login login = loginMapper.login(map);
		if (login != null) {
			login.setPassword(MD5Utils.string2MD5(newpwd));
			loginMapper.updateByPrimaryKeySelective(login);
			ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
			return JSONObject.fromObject(returnHandle).toString();
		} else {// 用户名或者密码错误
			return ReturnSimpleHandle.createServerError("用户名或者密码错误", "-1", null, null);
		}
	}

	// 查询左侧菜单权限
	public String searchEmployMenus(JSONObject json) throws Exception {

		// 查询左侧菜单权限
		List<String> menus = authResMapper.selectByUserName(json.getString("userName"));
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		returnHandle.setData(menus);
		return JSONObject.fromObject(returnHandle).toString();
	}

	// 首页修改个人信息
	public String updateServer1(JSONObject json) throws Exception {

		long id = json.getLong("id");// ID唯一
		String userName = json.getString("userName");

		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String updatedBy = userInfo.getUserName();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		Login login = loginMapper.login(map);

		Login loginList = loginMapper.selectByPrimaryKey(id);

		// 账号没有修改---直接修改其他信息
		if (loginList.getUserName().equals(userName)) {
			Login logins = (Login) JSONObject.toBean(json, Login.class);
			logins.setUpdatedBy(updatedBy);
			logins.setUpdatedAt(new Date());
			loginMapper.updateByPrimaryKeySelective(logins);

			// 将更改的信息刷入缓存
			userInfo.setUserName(json.getString("userName"));
			userInfo.setUser(logins);
//			RedisContent.flushUserInfo(userInfo);
		} else {
			// 账号不存在无重复
			if (null == login) {
				// 直接修改其他信息
				Login logins = (Login) JSONObject.toBean(json, Login.class);
				logins.setUpdatedBy(updatedBy);
				logins.setUpdatedAt(new Date());
				loginMapper.updateByPrimaryKeySelective(logins);

				// 将更改的信息刷入缓存
				userInfo.setUserName(json.getString("userName"));
				userInfo.setUser(logins);
//				RedisContent.flushUserInfo(userInfo);
			}
			return ReturnSimpleHandle.createServerError("账号重复", "-1", null, null);
		}

		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		return JSONObject.fromObject(returnHandle).toString();
	}

	//显示待办任务数量
	public String getNewMsgsNum() {
		
		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String userType = userInfo.getUserType();
		String userID = String.valueOf(userInfo.getUserId());// 锁定权限
		JSONObject userJson = JSONObject.fromObject(userInfo.getUser());
		String city = userJson.getString("city");
		String area = userJson.getString("area");
		
		JSONObject json = new JSONObject();
		
		Map<String, Object> map = new HashMap<>();//待装箱
		Map<String, Object> map1 = new HashMap<>();//待发车
		Map<String, Object> map2 = new HashMap<>();//处理运输异常
		Map<String, Object> map3 = new HashMap<>();//处理接收异常
		Map<String, Object> map4 = new HashMap<>();//待签收
		Map<String, Object> map5 = new HashMap<>();//异常仲裁(区级)
		Map<String, Object> map6 = new HashMap<>();//异常仲裁(市级)
		
		if(Constant.USER_TYPE_CITY.equals(userType)){
			map6.put("city", city);
			Integer problemordershi = orderMapper.countByProblemOrderShi(map6);
			json.put("num7", problemordershi);
		}
		
		if(Constant.USER_TYPE_AREA.equals(userType)){
			map5.put("city", city);
			map5.put("area", area);
			Integer problemorderqu = orderMapper.countByProblemOrderQu(map5);
			json.put("num6", problemorderqu);
		}
		
		if(Constant.USER_TYPE_HOSPITAL.equals(userType)){
			map.put("senderid", userID);
			Integer zhuangxiang = orderMapper.countZhuangxiang(map);
			
			map2.put("senderid", userID);
			Integer problemmonitor = problemmonitorMapper.countAll(map2);
			
			map3.put("senderid", userID);
			Integer problemorder = orderMapper.countByProblemOrder(map3);
			
			map4.put("reciverid", userID);
			Integer qianshou = orderMapper.countQianshou(map4);
			
			json.put("num1", zhuangxiang);
			json.put("num3", problemmonitor);
			json.put("num4", problemorder);
			json.put("num5", qianshou);
		}
		
		if(Constant.USER_TYPE_TRANSPORT.equals(userType)){
			map1.put("transportid", userID);
			Integer status = orderMapper.countStatus(map1);
			
			map2.put("transportid", userID);
			Integer problemmonitor1 = problemmonitorMapper.countAll(map2);
			
			map3.put("transportid", userID);
			Integer problemorder2 = orderMapper.countByProblemOrder(map3);
			
			json.put("num2", status);
			json.put("num3", problemmonitor1);
			json.put("num4", problemorder2);
		}
		
		json.put("userType", userType);//区别用户类型显示不同待办任务
		ReturnSimpleHandle handle = ReturnSimpleHandle.createServerHandle();
		handle.setData(json);		
		return JSONObject.fromObject(handle).toString();
	}
	
	//右下角弹出框
	public String getWeather() {
		
		JSONObject json = new JSONObject();
		
		//getWeather	免费用户24小时内不超过50次
		//getWeatherbyCityName	免费用户24小时内不超过250次
		String weatherInfo = WeatherUtils.getWeather("上海");
		String[] sourceStrArray = weatherInfo.split("#");
        
		String city = sourceStrArray[1];				
		String bbb = sourceStrArray[4];
		String[] ccc = bbb.split("；");		
		String ddd = ccc[0];
		String[] eee = ddd.split("：");
		
		String title = eee[0]+"：";
		String temperature = eee[1]+"："+eee[2];
		String humidity = ccc[2];
		String WindDirection = ccc[1];
		
		json.put("city", city);
		json.put("title", title);
		json.put("temperature", temperature);
		json.put("humidity", humidity);
		json.put("WindDirection", WindDirection);
		
		ReturnSimpleHandle handle = ReturnSimpleHandle.createServerHandle();
		handle.setData(json);		
		return JSONObject.fromObject(handle).toString();
		
	}

}
