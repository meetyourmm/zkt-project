package com.zkt.project.biology.service.wechat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkt.common.core.util.HttpRequest;
import com.zkt.common.core.util.RequestConstance;
import com.zkt.common.core.util.RequestTag;
import com.zkt.common.core.util.ValidateUtil;
import com.zkt.project.biology.constant.Constant;
import com.zkt.project.biology.entity.Cage;
import com.zkt.project.biology.entity.Login;
import com.zkt.project.biology.entity.Operatrecord;
import com.zkt.project.biology.entity.Order;
import com.zkt.project.biology.entity.Running;
import com.zkt.project.biology.entity.Sample;
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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class WeChatOrderService {

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

	@Resource(name = "proper")
	private Map<?, ?> proper;
	
	
	public String order(HttpServletRequest request) throws Exception {
		
		JSONObject result = new JSONObject();
		
		String userName = request.getParameter("userName");
		String senderid = request.getParameter("senderid");// 发送单位ID
		String transporterid = request.getParameter("transporterid");// 承运单位ID
		String receiverid = request.getParameter("receiverid");// 医院ID
		String cageno = request.getParameter("cageno");//箱子编号
		String tlimitup = request.getParameter("tlimitup");
		String tlimitdown = request.getParameter("tlimitdown");
		String hlimitup = request.getParameter("hlimitup");
		String hlimitdown = request.getParameter("hlimitdown");
		String remarks = request.getParameter("remarks");
		
		String sampleclassify = request.getParameter("sampleclassify");
		String num = request.getParameter("num");
		String sampleNumber = request.getParameter("sampleNumber");
		
		if (ValidateUtil.isEmpty(userName) || ValidateUtil.isEmpty(senderid) || ValidateUtil.isEmpty(transporterid)
				|| ValidateUtil.isEmpty(receiverid) || ValidateUtil.isEmpty(cageno) || ValidateUtil.isEmpty(tlimitup)
				|| ValidateUtil.isEmpty(tlimitdown) || ValidateUtil.isEmpty(hlimitup)
				|| ValidateUtil.isEmpty(hlimitdown) || ValidateUtil.isEmpty(sampleclassify) 
				|| ValidateUtil.isEmpty(num) || ValidateUtil.isEmpty(sampleNumber)) {
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_MISSING_PARAMETER);
			result.put(RequestConstance.RESULT_MSG, "缺少参数");
			return result.toString();
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		Login login = loginMapper.login(map);
		if (null == login) {
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
			result.put(RequestConstance.RESULT_MSG, "账号错误!");
			return result.toString();
		}
		
		//用户下单后容易出现写入两个相同订单的问题，而且无法删除，同时导致扫码开锁出现问题
		//解决办法，在写入订单时，先判断是否有相同编号的箱体绑定的订单处于未完成状态，即状态不是完成和异常状态，如果存在这样的订单就不再写入
		Order isExistence = orderMapper.selectByCageno(cageno);
		if(null != isExistence){
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
			result.put(RequestConstance.RESULT_MSG, "此箱体已绑定了订单并开始使用，请重新选择箱体!");
			return result.toString();
		}
		
		// 建立订单号
		String orderNo = null;
		try {
			orderNo = commonServiceImpl.createSerialNumber(Constant.PRE_ORDER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 保存样本
		JSONArray sampleNumbers = JSONArray.fromObject(sampleNumber);
		if (!sampleNumbers.isEmpty()) {
			for (int i = 0; i < sampleNumbers.size(); i++) {
				String sampleNumber1 = sampleNumbers.getString(i);
				Sample sample = new Sample();
				sample.setOrderNo(orderNo);// 订单编号
				sample.setSampleNumber(sampleNumber1);// 样本编号
				sampleMapper.insertSelective(sample);
			}
		}

		// 保存订单
		Order order = new Order();
		order.setOrderNo(orderNo);
		order.setSenderid(senderid);// 发货医院ID
		order.setTransportid(transporterid);// 运输方ID
		order.setReciverid(receiverid);// 收货医院ID
		order.setSender(loginMapper.getHospital(senderid));// 发货医院名称
		order.setTransport(loginMapper.getOffice(transporterid));// 运输方名称
		order.setReciver(loginMapper.getHospital(receiverid));// 收货医院名称
		order.setCreatedBy(userName);// 订单所有关联的发 送 收 三方人员全部用账号绑定 账号不能修改锁定
		order.setCreatedAt(new Date());

		order.setCageNo(cageno);
		order.setClassify(Constant.ORDER_CLASSIFY_WEIXIN);
		order.setSampleclassify(sampleclassify);// 样本类型
		order.setNum(num);// 样本总数
		order.setTlimitup(tlimitup);
		order.setTlimitdown(tlimitdown);
		order.setHlimitup(hlimitup);
		order.setHlimitdown(hlimitdown);
		order.setRemarks(remarks);
		order.setOrderStatus(Constant.ORDER_STATUS_NEW);// 订单状态
		order.setIsProblem(Constant.IS_PROBLEM_N);// 是否异常
		order.setCity(login.getCity());
		order.setArea(login.getArea());
		orderMapper.insertSelective(order);

		// 更新箱子
		Cage cage = cageMapper.selectByCageno(cageno);
		cage.setState(Constant.CAGESTATE_ORDER);//已绑定订单
		cageMapper.updateByPrimaryKeySelective(cage);
		
		// 关联订单流水记录
		Running running = new Running();
		running.setOrderno(orderNo);
		running.setState(Constant.ORDER_STATUS_NEW);
		running.setOperator(userName);
		running.setTime(new Date());
		runningMapper.insertSelective(running);

		// 关联操作记录
		Operatrecord operatrecord = new Operatrecord();
		operatrecord.setCageno(cageno);
		operatrecord.setOrderNo(orderNo);
		operatrecord.setOperatclassify(Constant.OPERATION_TYPE_NEW);
		operatrecord.setCreatedBy(userName);
		operatrecord.setCreatedAt(new Date());
		operatrecordMapper.insertSelective(operatrecord);
				
		// 下完单推送运货方
		HashMap<String, String> maps = new HashMap<String, String>();
		maps.put("id", transporterid);
		Login login1 = loginMapper.login(maps);
		
		Wechatusers wechatusers = wechatusersMapper.selectByUsername(login1.getUserName());
		if(null != wechatusers && wechatusers.getUserhospitalid().equals(transporterid) && wechatusers.getUsertype().equals(Constant.USER_TYPE_TRANSPORT)){
			HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/handel_inform", "orderno="+orderNo+"&sender="+loginMapper.getHospital(senderid)+"&openid="+wechatusers.getUserid()+"&receiver="+loginMapper.getHospital(receiverid));	        
		}
		
		result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_OK);
		result.put(RequestConstance.RESULT_MSG, RequestConstance.MSG_SUCCESS);
		return result.toString();
	}

}
