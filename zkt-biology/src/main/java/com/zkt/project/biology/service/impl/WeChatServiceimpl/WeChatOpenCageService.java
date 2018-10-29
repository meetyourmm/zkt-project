package com.zkt.project.biology.service.impl.WeChatServiceimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.zkt.project.biology.entity.Operatrecord;
import com.zkt.project.biology.entity.Order;
import com.zkt.project.biology.mapper.CageMapper;
import com.zkt.project.biology.mapper.LoginMapper;
import com.zkt.project.biology.mapper.ModelMapper;
import com.zkt.project.biology.mapper.OperatrecordMapper;
import com.zkt.project.biology.mapper.OrderMapper;
import com.zkt.project.biology.mapper.PicMapper;
import com.zkt.project.biology.mapper.ProblemMapper;
import com.zkt.project.biology.mapper.ProblemmonitorMapper;
import com.zkt.project.biology.mapper.RunningMapper;
import com.zkt.project.biology.mapper.SampleMapper;
import com.zkt.project.biology.mapper.WechatusersMapper;
import com.zkt.project.biology.service.CommonService;

import net.sf.json.JSONObject;

@Service
public class WeChatOpenCageService {

	/**
	 * LOG信息输出
	 */
	private final Log log = LogFactory.getLog(getClass());
	
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
	private ProblemmonitorMapper problemmonitorMapper;
	
	@Resource(name = "proper")
	private Map<?, ?> proper;
	
	//全局Message 消息保存
	MessageMap messagemap = MessageMap.newInstance();
	
	public String openCage(HttpServletRequest request) throws Exception {

		JSONObject result = new JSONObject();
		JSONObject restMap = new JSONObject();
		
		//校正参数
		String userName = request.getParameter("userName");//用户账号
		String cageno = request.getParameter("cageno");//箱子编号
		if (ValidateUtil.isEmpty(userName) || ValidateUtil.isEmpty(cageno)) {
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_MISSING_PARAMETER);
			result.put(RequestConstance.RESULT_MSG, "缺少参数");
			return result.toString();
		}
		
		log.warn("用户账号:" + userName);
		log.warn("箱子编号:" + cageno);
		
		//校正账号
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		Login login = loginMapper.login(map);
		if(null == login){
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_PARAMETER_ERROR);
			result.put(RequestConstance.RESULT_MSG, "用户账号错误");
			return result.toString();
		}
		
		//校正箱子编码
		Cage cage = cageMapper.selectByCageno(cageno);
		if(null == cage){
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_PARAMETER_ERROR);
			result.put(RequestConstance.RESULT_MSG, "箱子编号错误");
			return result.toString();
		}
				
		//修正权限标记
		String userType = login.getUserType();//用户类型
		String roleId = String.valueOf(login.getRoleId());//上级ID		
		String userId = "";
		if(Constant.USER_TYPE_HOSPITAL.equals(userType)){
			userId = String.valueOf(login.getId());
		}
		if(Constant.USER_TYPE_EMPLOY.equals(userType)){
			userId = roleId;
		}
		if(Constant.USER_TYPE_HOSPITALWHITE.equals(userType)){
			userId = roleId;
		}
				
		//箱子操作记录
		Operatrecord operatrecord = new Operatrecord();
				
		//订单信息
		Order order = orderMapper.selectByCageNo(cageno);
		String orderNo = "";
		//如果订单不存在 则白卡和员工只能开自己医院的箱子
		if(null == order){
			
			//非白卡和员工不能开
		/*	if(!Constant.USER_TYPE_HOSPITALWHITE.equals(userType)&&!Constant.USER_TYPE_EMPLOY.equals(userType)){
				result.put(RequestConstance.RESULT_CODE, RequestTag.RequestTag.CODE_FAIL);
				result.put(RequestConstance.RESULT_MSG, "非白卡和员工无权开箱");
				return result.toString();
			}*/
			
			if(cage.getHospitalid().equals(userId)){
				String newCageno = ModifyInstructions.getNewCageno(cageno);
		        String sign = TcpConstant.OPEN_CAGE;//设置功能码	     
		        String crc = ModifyInstructions.getCrc(newCageno, sign);//CRC Modbus 检验码   		        
		        //进行TCP链接
		        TcpUtil.Doconnector(newCageno, sign, crc);		    	
		    	log.warn("全局Message:" + messagemap.getMes(cageno));
		    	
		    	//如果箱子无应答或者超时断开返回	请重启设备
		    	if(null == messagemap.getMes(cageno)){
		    		result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
					result.put(RequestConstance.RESULT_MSG, "请重启设备");
					return result.toString();
		    	}
		    	
		    	if(messagemap.getMes(cageno).equals(TcpConstant.OPEN_CAGE_OK)){
		    		messagemap.addMes(cageno, null);
		    		result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_OK);
		    		result.put(RequestConstance.RESULT_MSG, RequestConstance.MSG_SUCCESS);
		    		return result.toString();
		    	}		    	
		    	if(messagemap.getMes(cageno).equals(TcpConstant.OPEN_CAGE_ERROR)){
		    		messagemap.addMes(cageno, null);
		        	result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
		    		result.put(RequestConstance.RESULT_MSG, RequestConstance.MSG_FAIL);
		    		restMap.put("orderno", orderNo);
		    		result.put(RequestConstance.RESULT_DATA, restMap);
		    		return result.toString();
		        }
		    	
			}else{
				result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
				result.put(RequestConstance.RESULT_MSG, "非本医院人员无权开箱");
				return result.toString();
			}
			
		}else{
			orderNo = order.getOrderNo();
		}
		
		//其他人员屏蔽
		if(Constant.USER_TYPE_BOSS.equals(userType) || Constant.USER_TYPE_CITY.equals(userType) || Constant.USER_TYPE_AREA.equals(userType) || Constant.USER_TYPE_BOSSEMPLOY.equals(userType)){			
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
			result.put(RequestConstance.RESULT_MSG, "其他用户类型无权开箱");
			return result.toString();
		}
				
		//运输公司或人员不开箱只接受订单编号
		if(Constant.USER_TYPE_TRANSPORT.equals(userType) || Constant.USER_TYPE_TRANSPORTEMPLOY.equals(userType)){
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_OK);
    		result.put(RequestConstance.RESULT_MSG, RequestConstance.MSG_SUCCESS);
			restMap.put("orderno", order.getOrderNo());
			result.put(RequestConstance.RESULT_DATA, restMap);
			return result.toString();
		}
				
		//医院和医院员工登入
		if(Constant.USER_TYPE_HOSPITAL.equals(userType) || Constant.USER_TYPE_EMPLOY.equals(userType)||Constant.USER_TYPE_HOSPITALWHITE.equals(userType)){
			
			//非订单关联医院及人员屏蔽			
			if(!order.getSenderid().equals(userId) && !order.getReciverid().equals(userId)){
				result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
				result.put(RequestConstance.RESULT_MSG, "非订单关联人员无权开箱");
				return result.toString();
			}
			
			//新建状态只能发货方开箱
			/*if(Constant.ORDER_STATUS_NEW.equals(order.getOrderStatus()) && order.getReciverid().equals(userId)){
				result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
				result.put(RequestConstance.RESULT_MSG, "新建状态只能发货方开箱");
				return result.toString();
			}*/
						
			//装箱后发收双方都不能再开
			/*if((order.getSenderid().equals(userId) || order.getReciverid().equals(userId)) && Constant.ORDER_STATUS_PACK.equals(order.getOrderStatus())){
				result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
				result.put(RequestConstance.RESULT_MSG, "装箱后发收双方都不能再开");
				return result.toString();
			}*/
			
			//发车后发货方都不能再开
			/*if(order.getSenderid().equals(userId) && Constant.ORDER_STATUS_TRANSPORT.equals(order.getOrderStatus())){
				result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
				result.put(RequestConstance.RESULT_MSG, "发车后发货方都不能再开");
				return result.toString();
			}*/
				
			//完成后发收双方都不能开箱
			if(!order.getSenderid().equals(userId) && Constant.ORDER_STATUS_FINISH.equals(order.getOrderStatus())){
				result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
				result.put(RequestConstance.RESULT_MSG, "非本医院人员无权开箱");
				return result.toString();
			}
			
			String newCageno = ModifyInstructions.getNewCageno(cageno);
	        String sign = TcpConstant.OPEN_CAGE;//设置功能码	     
	        String crc = ModifyInstructions.getCrc(newCageno, sign);//CRC Modbus 检验码   		        
	        //进行TCP链接
	        TcpUtil.Doconnector(newCageno, sign, crc);	    	
	    	log.warn("全局Message:" + messagemap.getMes(cageno));
	    	
	    	//如果箱子无应答或者超时断开返回	请重启设备
	    	if(null == messagemap.getMes(cageno)){
	    		result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
				result.put(RequestConstance.RESULT_MSG, "请重启设备");
				return result.toString();
	    	}
	    	
	    	//开锁成功
	    	if(messagemap.getMes(cageno).equals(TcpConstant.OPEN_CAGE_OK)){
	    		//开箱发货记录开箱人
	    		if(Constant.ORDER_STATUS_NEW.equals(order.getOrderStatus())){	    			
	    			operatrecord.setCageno(cageno);
	    			operatrecord.setOrderNo(order.getOrderNo());
	    			operatrecord.setCreatedAt(new Date());
	    			operatrecord.setOperatclassify(Constant.OPERATION_TYPE_SENDOPEN);
	    			operatrecord.setSendBy(userName);
	    			operatrecordMapper.insertSelective(operatrecord);					
				}
	    		
	    		//开箱收货记录开箱人
				if(Constant.ORDER_STATUS_TRANSPORT.equals(order.getOrderStatus())){						
			    	operatrecord.setCageno(cageno);
	    			operatrecord.setOrderNo(order.getOrderNo());
	    			operatrecord.setCreatedAt(new Date());
					operatrecord.setOperatclassify(Constant.OPERATION_TYPE_COLLECTOPEN);
					operatrecord.setReceiptBy(userName);
					operatrecordMapper.insertSelective(operatrecord);
				}				
				result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_OK);
	    		result.put(RequestConstance.RESULT_MSG, RequestConstance.MSG_SUCCESS);
	    	}
			
	    	if(messagemap.getMes(cageno).equals(TcpConstant.OPEN_CAGE_ERROR)){
	    		messagemap.addMes(cageno, null);
	        	result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
	    		result.put(RequestConstance.RESULT_MSG, RequestConstance.MSG_FAIL);
	    		restMap.put("orderno", orderNo);
	    		result.put(RequestConstance.RESULT_DATA, restMap);
	    		return result.toString();
	        }	
		    				
		}
		
		//白卡和员工登入 不关联订单 但要关联医院
		/*if(Constant.USER_TYPE_HOSPITALWHITE.equals(userType)){		
			
			//非订单关联医院白卡屏蔽
			if(!order.getSenderid().equals(roleId) && !order.getReciverid().equals(roleId)){
				result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
				result.put(RequestConstance.RESULT_MSG, "非订单关联医院白卡无权开箱");
				return result.toString();
			}
			
			String newCageno = ModifyInstructions.getNewCageno(cageno);
	        String sign = TcpConstant.OPEN_CAGE;//设置功能码	     
	        String crc = ModifyInstructions.getCrc(newCageno, sign);//CRC Modbus 检验码   		        
	        //进行TCP链接
	        TcpUtil.Doconnector(newCageno, sign, crc);	    	
	    	log.warn("全局Message:" + messagemap.getMes(cageno));
	    	
	    	//如果箱子无应答或者超时断开返回	请重启设备
	    	if(null == messagemap.getMes(cageno)){
	    		result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
				result.put(RequestConstance.RESULT_MSG, "请重启设备");
				return result.toString();
	    	}
	    	
	    	if(messagemap.getMes(cageno).equals(TcpConstant.OPEN_CAGE_OK)){
	    		if (Constant.ORDER_STATUS_NEW.equals(order.getOrderStatus())) {
	    			operatrecord.setCageno(cageno);
	    			operatrecord.setOrderNo(order.getOrderNo());
	    			operatrecord.setCreatedAt(new Date());
	    			operatrecord.setSendBy(userName);	
	    			operatrecordMapper.insertSelective(operatrecord);
				} else if (Constant.ORDER_STATUS_TRANSPORT.equals(order.getOrderStatus())) {
					operatrecord.setCageno(cageno);
	    			operatrecord.setOrderNo(order.getOrderNo());
	    			operatrecord.setCreatedAt(new Date());
					operatrecord.setReceiptBy(userName);
					operatrecordMapper.insertSelective(operatrecord);
				}
	    		result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_OK);
	    		result.put(RequestConstance.RESULT_MSG, RequestConstance.MSG_SUCCESS);
	    	}
	    	
	    	if(messagemap.getMes(cageno).equals(TcpConstant.OPEN_CAGE_ERROR)){
	    		messagemap.addMes(cageno, null);
	        	result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
	    		result.put(RequestConstance.RESULT_MSG, MSG_FAIL);
	    		return result.toString();
	        }
			
		}*/
		
		messagemap.addMes(cageno, null);
		//返回数据		
		restMap.put("orderno", orderNo);
		result.put(RequestConstance.RESULT_DATA, restMap);
		return result.toString();
	}
	
}


