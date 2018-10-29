package com.zkt.project.biology.service.wechat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkt.common.core.util.HttpRequest;
import com.zkt.common.core.util.MessageMap;
import com.zkt.common.core.util.ModifyInstructions;
import com.zkt.common.core.util.RequestConstance;
import com.zkt.common.core.util.RequestTag;
import com.zkt.common.core.util.TcpConstant;
import com.zkt.common.core.util.TcpUtil;
import com.zkt.common.core.util.ValidateUtil;
import com.zkt.project.biology.constant.Constant;
import com.zkt.project.biology.entity.Cage;
import com.zkt.project.biology.entity.Login;
import com.zkt.project.biology.entity.Order;
import com.zkt.project.biology.entity.Pic;
import com.zkt.project.biology.entity.Running;
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
public class WeChatNormalService {

	/**
	 * LOG信息输出
	 */
	private final Log log = LogFactory.getLog(getClass());
	
	//全局Message 消息保存
	MessageMap messagemap = MessageMap.newInstance();
	
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

	
	public String normal(HttpServletRequest request) throws Exception {

		JSONObject result = new JSONObject();

		String userName = request.getParameter("userName");
		String orderno = request.getParameter("orderno");
		String cageno = request.getParameter("cageno");
		String picturelink = request.getParameter("picturelink");// 图片链接JSON数组
		if (ValidateUtil.isEmpty(userName) || ValidateUtil.isEmpty(orderno) || ValidateUtil.isEmpty(cageno)
				) {
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_MISSING_PARAMETER);
			result.put(RequestConstance.RESULT_MSG, "缺少参数");
			return result.toString();
		}
		
		log.warn("userName" + userName);
		log.warn("orderno" + orderno);
		log.warn("cageno" + cageno);
		log.warn("picturelink" + picturelink);
		
		Order order = orderMapper.selectByOrderNo(orderno);
		if (null == order) {
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
			result.put(RequestConstance.RESULT_MSG, "订单不存在!");
			return result.toString();
		}

		if (order.getOrderStatus().equals(Constant.ORDER_STATUS_FINISH)) {
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
			result.put(RequestConstance.RESULT_MSG, "不能重复签收!");
			return result.toString();
		}

		//保存图片
		if(!picturelink.equals("")&&null!=picturelink){
			JSONArray picturelinks = JSONArray.fromObject(picturelink);
			if (!picturelinks.isEmpty()) {
				for (int i = 0; i < picturelinks.size(); i++) {
					String picturelink1 = picturelinks.getString(i);
					Pic pic = new Pic();
					pic.setOrderId(orderno);
					pic.setPicturelink(picturelink1);
					pic.setClassify(Constant.PICTURE_TYPE_RECEIVE);
					pic.setCreatedBy(userName);
					pic.setCreatedAt(new Date());
					picMapper.insertSelective(pic);
				}
			}
		}
		

		//更新订单
		order.setReceiveOperator(userName);// 写入接受人员账号
		order.setOrderStatus(Constant.ORDER_STATUS_FINISH);
		order.setReceivetime(new Date());
		orderMapper.updateByPrimaryKeySelective(order);

		//更新箱子
		Cage cage = cageMapper.selectByCageno(cageno);
		cage.setState(Constant.CAGESTATE_FREE);
		cageMapper.updateByPrimaryKeySelective(cage);

		//关联订单流水记录
		Running running = new Running();
		running.setOrderno(orderno);
		running.setState(Constant.ORDER_STATUS_FINISH);
		running.setOperator(userName);
		running.setTime(new Date());
		runningMapper.insertSelective(running);
		
		//推送三方流程结束
		String senderid = order.getSenderid();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", senderid);
		Login login = loginMapper.login(map);
		
		Wechatusers wechatusers = wechatusersMapper.selectByUsername(login.getUserName());
		if(null != wechatusers && wechatusers.getUserhospitalid().equals(senderid) && wechatusers.getUsertype().equals(Constant.USER_TYPE_HOSPITAL)){			
			HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/receive_inform", "orderno="+orderno+"&receiver="+order.getReciver()+"&openid="+wechatusers.getUserid()+"&operator="+userName);
		}
		
		//返回数据
		result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_OK);
		
		//开箱收货并结束监控 
		String newCageno1 = ModifyInstructions.getNewCageno(cageno);
        String sign1 = TcpConstant.OVER_MONITOR;	     
        String crc1 = ModifyInstructions.getCrc(newCageno1, sign1);
        TcpUtil.Doconnector(newCageno1, sign1, crc1);        
        log.warn("全局Message:" + messagemap.getMes(cageno));
        
        //如果箱子无应答或者超时断开返回	请重启设备
    	if(null == messagemap.getMes(cageno)){
    		messagemap.addMes(cageno, null);
			result.put(RequestConstance.RESULT_MSG, "请重启设备");
			return result.toString();
    	}
    	
        if(messagemap.getMes(cageno).equals(TcpConstant.OVER_MONITOR_OK)){
    		result.put(RequestConstance.RESULT_MSG, RequestConstance.MSG_SUCCESS);
        }
        
    	if(messagemap.getMes(cageno).equals(TcpConstant.MONITOR_ERROR)){
    		messagemap.addMes(cageno, null);
    		result.put(RequestConstance.RESULT_MSG, RequestConstance.MSG_FAIL);
    		return result.toString();
        }	
    	
		messagemap.addMes(cageno, null);
		return result.toString();
	}

}
