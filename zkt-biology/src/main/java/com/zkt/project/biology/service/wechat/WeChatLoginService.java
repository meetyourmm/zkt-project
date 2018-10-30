package com.zkt.project.biology.service.wechat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zkt.project.biology.config.PropConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkt.common.core.util.ConverterUtil;
import com.zkt.common.core.util.DateUtils;
import com.zkt.common.core.util.HttpRequest;
import com.zkt.common.core.util.RequestConstance;
import com.zkt.common.core.util.RequestTag;
import com.zkt.common.core.util.UserInfo;
import com.zkt.common.core.util.ValidateUtil;
import com.zkt.project.biology.constant.Constant;
import com.zkt.project.biology.entity.Login;
import com.zkt.project.biology.entity.Wechatusers;
import com.zkt.project.biology.mapper.CageMapper;
import com.zkt.project.biology.mapper.LoginMapper;
import com.zkt.project.biology.mapper.ModelMapper;
import com.zkt.project.biology.mapper.OperatrecordMapper;
import com.zkt.project.biology.mapper.OrderMapper;
import com.zkt.project.biology.mapper.PicMapper;
import com.zkt.project.biology.mapper.ProblemMapper;
import com.zkt.project.biology.mapper.RunningMapper;
import com.zkt.project.biology.mapper.SampleMapper;
import com.zkt.project.biology.mapper.WechatusersMapper;
import com.zkt.project.biology.service.CommonService;

import net.sf.json.JSONObject;

@Service
public class WeChatLoginService {

	@Autowired
	private LoginMapper loginMapper;

	@Autowired
	private SampleMapper sampleMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private CageMapper cageMapper;

	@Autowired
	private OperatrecordMapper operatrecordMapper;

	@Autowired
	private CommonService commonServiceImpl;

	@Autowired
	private PicMapper picMapper;

	@Autowired
	private ProblemMapper problemMapper;
	
	@Autowired
	private WechatusersMapper wechatusersMapper;
	
	@Autowired
	private RunningMapper runningMapper;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PropConfiguration proper;

	
	public String login(HttpServletRequest request) throws Exception {
		
		JSONObject result = new JSONObject();
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");//提交的密码都是经过MD5加密后 无需处理
		String userid = request.getParameter("userid");//微信ID	oaPjSvy-PAphOE0_ziOO-qPUVIb
		if (ValidateUtil.isEmpty(userName) || ValidateUtil.isEmpty(password)  || ValidateUtil.isEmpty(userid)) {
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_MISSING_PARAMETER);
			result.put(RequestConstance.RESULT_MSG, "缺少参数");
			return result.toString();
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("password", password);
		map.put("userName", userName);
		Login login = loginMapper.login(map);
		if (null == login) {
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
			result.put(RequestConstance.RESULT_MSG, "账号或密码错误!");
			return result.toString();
		}
		
		String id = String.valueOf(login.getId());
		String usertype = login.getUserType();
		String operator = login.getUserOperator();
		String userIslock = login.getUserIslock();
		String roleId = String.valueOf(login.getRoleId());//关联上级单位ID
		String userHospital = login.getUserHospital();
		String userOffice = login.getUserOffice();
		String userHospitalAll = "";
		String userHospitalID = "";
		
		//医院
		if (Constant.USER_TYPE_HOSPITAL.equals(usertype)) {			
			userHospitalAll = userHospital;
			userHospitalID = id;
		}
		//医院员工	医院白卡账号
		if (Constant.USER_TYPE_EMPLOY.equals(usertype) || Constant.USER_TYPE_HOSPITALWHITE.equals(usertype)) {			
			userHospitalAll = userHospital;
			userHospitalID = roleId;
		}
		//运输方
		if (Constant.USER_TYPE_TRANSPORT.equals(usertype)) {
			userHospitalAll = userOffice;
			userHospitalID = id;
		}
		//运输方员工
		if (Constant.USER_TYPE_TRANSPORTEMPLOY.equals(usertype)) {
			userHospitalAll = userOffice;
			userHospitalID = roleId;
		}
		
		//总部 总部员工 市 区 不能登入微信
		if(Constant.USER_TYPE_BOSS.equals(usertype) || Constant.USER_TYPE_CITY.equals(usertype) || Constant.USER_TYPE_AREA.equals(usertype) || Constant.USER_TYPE_BOSSEMPLOY.equals(usertype)){
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
			result.put(RequestConstance.RESULT_MSG, "您的账号无权访问!");
			return result.toString();
		}
		
		//账号存在
		if (login != null) {			
			if (Constant.IS_USED_N.equals(userIslock)) {
				result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_IS_USED_N);
				result.put(RequestConstance.RESULT_MSG, "您的账号已被停用!");
				return result.toString();
			}
			if(Constant.IS_USED_D.equals(userIslock)){
				result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_IS_USED_D);
				result.put(RequestConstance.RESULT_MSG, "您的账号已被删除!");
				return result.toString();
			}
			
			//微信账号管理表
			Wechatusers wechatusers1 = wechatusersMapper.selectByUsername(userName);			
			if(wechatusers1 == null){
				Wechatusers wechatusers = new Wechatusers();
				wechatusers.setUserid(userid);
				wechatusers.setUsername(userName);
				wechatusers.setPassword(password);
				wechatusers.setUsertype(usertype);
				wechatusers.setOperator(operator);
				wechatusers.setLasttime(DateUtils.formatDate(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS));
				
				//医院
				if (Constant.USER_TYPE_HOSPITAL.equals(usertype)) {			
					wechatusers.setUserhospital(userHospital);
					wechatusers.setUserhospitalid(id);//医院单位ID
					wechatusersMapper.insertSelective(wechatusers);
				}
				//医院员工	医院白卡账号
				if (Constant.USER_TYPE_EMPLOY.equals(usertype) || Constant.USER_TYPE_HOSPITALWHITE.equals(usertype)) {			
					wechatusers.setUserhospital(userHospital);
					wechatusers.setUserhospitalid(roleId);//关联上级单位ID
					wechatusersMapper.insertSelective(wechatusers);
				}
				//运输方
				if (Constant.USER_TYPE_TRANSPORT.equals(usertype)) {
					wechatusers.setUserhospital(userOffice);
					wechatusers.setUserhospitalid(id);//运输单位ID
					wechatusersMapper.insertSelective(wechatusers);
				}
				//运输方员工
				if (Constant.USER_TYPE_TRANSPORTEMPLOY.equals(usertype)) {
					wechatusers.setUserhospital(userOffice);
					wechatusers.setUserhospitalid(roleId);//关联上级单位ID
					wechatusersMapper.insertSelective(wechatusers);
				}
				
				// 将信息刷入缓存
				UserInfo userInfo = new UserInfo();
				userInfo.setUser(login);
				userInfo.setLogin(true);
				userInfo.setUserId(login.getId());
				userInfo.setUserName(login.getUserName());
				userInfo.setUserPswd(login.getPassword());
				userInfo.setUserType(login.getUserType());
//				RedisContent.flushUserInfo(userInfo);
				
			}else{
				//更新 Wechatusers
				if(!wechatusers1.getUserid().equals(userid)){
					
					String first = "您的账号被他人登陆，您的登陆状态已被注销。";
					String end = "如非本人操作，请重新登入后修改密码";
					String account = userName;//当前登录的账号
					String openid = wechatusers1.getUserid();//上一次登陆的userid
					String lasttime = wechatusers1.getLasttime();//上一次登陆的时间
					//发送 POST 请求
			        HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/unlogin_inform", "first="+first+"&end="+end+"&account="+account+"&openid="+openid+"&lasttime="+lasttime);			        
					wechatusers1.setUserid(userid);
					wechatusers1.setOperator(operator);
					wechatusers1.setUserhospital(userHospital);
					wechatusers1.setLasttime(DateUtils.formatDate(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS));
					wechatusersMapper.updateByPrimaryKeySelective(wechatusers1);
				}				
			}
						
		}

		// 返回数据
		Map<String, Object> restMap = new HashMap<String, Object>();
		restMap.put("userName", userName);// 账号
		restMap.put("userOperator", operator);// 操作员姓名			
		restMap.put("userHospital", userHospitalAll);// 操作员单位，可能是医院也有可能是运输单位
		restMap.put("userHospitalID", userHospitalID);// 操作员单位ID
		restMap.put("userType", usertype);// 操作员类型 
		result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_OK);
		result.put(RequestConstance.RESULT_MSG, RequestConstance.MSG_SUCCESS);
		result.put(RequestConstance.RESULT_DATA, ConverterUtil.map2JsonObject(restMap));
		return result.toString();
	}
	
}
