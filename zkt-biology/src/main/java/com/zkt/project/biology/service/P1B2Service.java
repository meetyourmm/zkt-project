package com.zkt.project.biology.service;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkt.common.core.util.DateUtils;
import com.zkt.common.core.util.FileUtil;
import com.zkt.common.core.util.HttpRequest;
import com.zkt.common.core.util.ModifyInstructions;
import com.zkt.common.core.util.TcpConstant;
import com.zkt.common.core.util.TcpUtil;
import com.zkt.common.core.util.UserInfo;
import com.zkt.project.biology.constant.Constant;
import com.zkt.project.biology.constant.ReturnObjectHandle;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.entity.Cage;
import com.zkt.project.biology.entity.Login;
import com.zkt.project.biology.entity.Operatrecord;
import com.zkt.project.biology.entity.Order;
import com.zkt.project.biology.entity.Pic;
import com.zkt.project.biology.entity.Running;
import com.zkt.project.biology.entity.Sample;
import com.zkt.project.biology.entity.Wechatusers;
import com.zkt.project.biology.mapper.CageMapper;
import com.zkt.project.biology.mapper.LoginMapper;
import com.zkt.project.biology.mapper.OperatrecordMapper;
import com.zkt.project.biology.mapper.OrderMapper;
import com.zkt.project.biology.mapper.PicMapper;
import com.zkt.project.biology.mapper.RunningMapper;
import com.zkt.project.biology.mapper.SampleMapper;
import com.zkt.project.biology.mapper.WechatusersMapper;
import com.zkt.project.biology.utils.SystemContent;

import net.sf.json.JSONObject;

@Service
public class P1B2Service {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private CommonService commonService;

	@Autowired
	private PicMapper picMapper;

	@Autowired
	private SampleMapper sampleMapper;

	@Autowired
	private CageMapper cageMapper;
	
	@Autowired
	private OperatrecordMapper operatrecordMapper;
	
	@Autowired
	private RunningMapper runningMapper;
	
	@Autowired
	private LoginMapper loginMapper;
	
	@Autowired
	private WechatusersMapper wechatusersMapper;
	
	// 发送 运输查询新建和已装箱订单
	
	public String search(JSONObject json) throws Exception {
		
		UserInfo userInfo = null; //RedisContent.getUserInfo();
		String userId = String.valueOf(userInfo.getUserId());// 锁定权限
		String userType = userInfo.getUserType();
		
		String orderNo = json.getString("orderNo");
		String cageno = json.getString("cageno");
		String classify = json.getString("classify");

		Integer draw = Integer.parseInt(json.getString("draw"));
		Integer from = Integer.parseInt(json.getString("start"));
		Integer pageSize = Integer.parseInt(json.getString("pageCount"));
		
		Map<String, Object> map = new HashMap<>();			
		map.put("orderNo", orderNo);
		map.put("cageno", cageno);		
		map.put("classify", classify);
		map.put("pageSize", pageSize);
		map.put("from", from);
		
		if(userType.equals(Constant.USER_TYPE_HOSPITAL)){
			map.put("senderid", userId);//新建订单人员才可以进行装箱			
		}else{
			map.put("transportid", userId);//运输人员才可以进行发车
		}
		List<Order> orderList = orderMapper.selectByKeys(map);
		Integer orderListCount = orderMapper.countByKeys(map);

		// 封装返回参数
		ReturnObjectHandle returnHandle = ReturnObjectHandle.createServerHandle();
		returnHandle.setDraw(draw);
		returnHandle.setData(orderList);
		returnHandle.setPageCount(pageSize);
		returnHandle.setDataMaxCount(orderListCount);
		returnHandle.setDataMaxPage(
				orderListCount % pageSize == 0 ? orderListCount / pageSize : orderListCount / pageSize + 1);
		return JSONObject.fromObject(returnHandle).toString();
	}
	
	// 装箱订单详情
	
	public String detail(JSONObject json) throws Exception {
		
		String orderNo = json.getString("orderNo");
		Order order = orderMapper.selectByOrderNo(orderNo);
		// 订单状态
		String orderStatus = "";
		switch (order.getOrderStatus()) {
		case Constant.ORDER_STATUS_NEW:
			orderStatus = "新建";
			order.setOrderStatus(orderStatus);
			break;
		case Constant.ORDER_STATUS_PACK:
			orderStatus = "已装箱";
			order.setOrderStatus(orderStatus);
			break;
		case Constant.ORDER_STATUS_TRANSPORT:
			orderStatus = "运输中";
			order.setOrderStatus(orderStatus);
			break;
		default:
			break;
		}
		// 下单分类
		String classify = "";
		switch (order.getClassify()) {
		case Constant.ORDER_CLASSIFY_PC:
			classify = "PC端录入";
			order.setClassify(classify);
			break;
		case Constant.ORDER_CLASSIFY_WEIXIN:
			classify = "微信录入";
			order.setClassify(classify);
			break;
		default:
			break;
		}
		Cage cage = cageMapper.selectByCageno(order.getCageNo());
		// 箱子状态
		String state = "";
		switch (cage.getState()) {
		case Constant.CAGESTATE_FREE:
			state = "空闲";
			cage.setState(state);
			break;
		case Constant.CAGESTATE_ORDER:
			state = "已绑定订单";
			cage.setState(state);
			break;
		case Constant.CAGESTATE_TRANSPORT:
			state = "在途";
			cage.setState(state);
			break;
		case Constant.CAGESTATE_FAULT:
			state = "故障";
			cage.setState(state);
			break;
		default:
			break;
		}
		
		List<Sample> sample = sampleMapper.selectByKeys(orderNo);
		for(int i=0; i<sample.size(); i++){
			sample.get(i).setSpare4(order.getSampleclassify());//样本类型
			sample.get(i).setSpare5(order.getNum());//样本数量
		}
		
		Map<String, Object> classify1 = new HashMap<>();
		classify1.put("orderId", orderNo);
		classify1.put("classify", "1");		
		List<Pic> picAddresList1 = picMapper.selectByClassify(classify1);
		
		Map<String, Object> classify2 = new HashMap<>();
		classify2.put("orderId", orderNo);
		classify2.put("classify", "2");		
		List<Pic> picAddresList2 = picMapper.selectByClassify(classify2);
		
		JSONObject result = new JSONObject();
		result.put("order", order);// 订单信息
		result.put("cage", cage);// 箱体信息
		result.put("sample", sample);// 样本信息
		result.put("picAddresList1", picAddresList1);
		result.put("picAddresList2", picAddresList2);
		
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		returnHandle.setData(result);
		return JSONObject.fromObject(returnHandle).toString();
	}
		
	//发车
	
	public void updateStart(JSONObject json) throws Exception {
		
		UserInfo userInfo = null; //RedisContent.getUserInfo();
		String userName = userInfo.getUserName();
		
		String orderNo = json.getString("orderNo");
		String arrivetime = json.getString("arrivetime");
		String carNo = json.getString("carNo");
		
		//更新订单
		Order order = orderMapper.selectByOrderNo(orderNo);
		order.setTransportOperator(userName);//写入运输人员账号
		order.setArrivetime(DateUtils.str2Date(arrivetime, DateUtils.YYYY_MM_DD_HH_MM));
		order.setCarNo(carNo);
		order.setOrderStatus(Constant.ORDER_STATUS_TRANSPORT);
		order.setSendtime(new Date());
		orderMapper.updateByPrimaryKeySelective(order);
		
		//更新箱子
		Cage cage = cageMapper.selectByCageno(order.getCageNo());
		cage.setState(Constant.CAGESTATE_TRANSPORT);// 在途
		cageMapper.updateByPrimaryKeySelective(cage);
		
		//关联订单流水记录
		Running running = new Running();
		running.setOrderno(orderNo);
		running.setState(Constant.ORDER_STATUS_TRANSPORT);
		running.setOperator(userName);
		running.setTime(new Date());
		runningMapper.insertSelective(running);
				
		//关联操作记录流水
		Operatrecord operatrecord = new Operatrecord();
		operatrecord.setStartBy(userName);
		operatrecord.setOperatclassify(Constant.OPERATION_TYPE_SAOMA);
		operatrecordMapper.insertSelective(operatrecord);
		
		//给三方推送启运通知	
		String reciverid = order.getReciverid();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", reciverid);
		Login login = loginMapper.login(map);
		
		Wechatusers wechatusers = wechatusersMapper.selectByUsername(login.getUserName());
		if(null != wechatusers && wechatusers.getUserhospitalid().equals(reciverid) && wechatusers.getUsertype().equals(Constant.USER_TYPE_HOSPITAL)){
			String sender = order.getSender();
			String transport = order.getTransport();
			HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/sendout_inform", "orderno="+orderNo+"&sender="+sender+"&openid="+wechatusers.getUserid()+"&transporter="+transport+"&arrivetime="+arrivetime);	     
		}
		
	}
	
	//装箱
	
	public void updateZhuangXiang(JSONObject json) throws Exception {
		
		UserInfo userInfo = null;//RedisContent.getUserInfo();
		String userName = userInfo.getUserName();
		
		String orderNo = json.getString("orderNo");		
		String cageno = json.getString("cageno");
		
		// 更新订单
		Order order = orderMapper.selectByOrderNo(orderNo);	
		order.setPackOperator(userName);//写入装箱人员账号
		order.setOrderStatus(Constant.ORDER_STATUS_PACK);
		orderMapper.updateByPrimaryKeySelective(order);
		
		// 关联订单流水记录
		Running running = new Running();
		running.setOrderno(orderNo);
		running.setState(Constant.ORDER_STATUS_PACK);
		running.setOperator(userName);
		running.setTime(new Date());
		runningMapper.insertSelective(running);
		
		//进入开启箱子监控 
		String newCageno1 = ModifyInstructions.getNewCageno(cageno);
        String sign1 = TcpConstant.START_MONITOR;	     
        String crc1 = ModifyInstructions.getCrc(newCageno1, sign1);
        TcpUtil.Doconnector(newCageno1, sign1, crc1);
        /*//全局Message 消息保存
    	MessageMap messagemap = MessageMap.newInstance();
        if(messagemap.getMes(cageno).equals(TcpConstant.START_MONITOR_OK)){
        	
        }
    	
    	if(messagemap.getMes(cageno).equals(TcpConstant.MONITOR_ERROR)){
        	
        }*/
    	
	}

	
	//批量保存图片
	
	public Pic saveImg() throws Exception {
		
		String base = "picters";//图片文件夹		
		String path = FileUtil.getPropertiesValue("file.upload.base");//系统默认C盘路径	C:/share_data/mk104/	
		String basePath = path + base;
		String filePath = basePath + "/" + DateUtils.formatDate(new Date(), DateUtils.YYYY_MM_DD_EN);
		
		File uploadFile = new File(filePath);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}
		File tempPathFile = new File(basePath + "/cache");
		if (!tempPathFile.exists()) {
			tempPathFile.mkdirs();
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
		factory.setRepository(tempPathFile);// 设置缓冲区目录
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(52428800);// 设置最大文件尺寸，这里是50MB
		
		List<?> items = upload.parseRequest(SystemContent.getRequest());// 得到所有参数 
		Iterator<?> i = items.iterator();
		
		String newfileName = "";// 新文件名称
		String formatName = "";// 获取文件后缀名
		
		Pic pic = new Pic();
		
		while (i.hasNext()) {		
			
			FileItem fi = (FileItem) i.next();			
			String fileName = fi.getName();//Sample.JPG
			
			long time = System.currentTimeMillis();//时间戳
			newfileName = String.valueOf(time);
			
			if (fileName != null) {
				
				formatName = fi.getName().substring(fi.getName().lastIndexOf("."));
				File savedFile = new File(filePath, newfileName + formatName);//C:\share_data\mk104\picters\6e5d001b-6eed-4814-827a-cabdd4f32f4b.xlsx
				fi.write(savedFile);
				
				// 上传成功
				String fileRealResistPath = filePath + "/" + newfileName + formatName;		
				// 保存
				pic.setPicturelink(fileRealResistPath);
			}
		}
		return pic;
	}
	
	//保存图片到PIC
	
	public String savePic(JSONObject json) throws Exception {
		UserInfo userInfo = null; //RedisContent.getUserInfo();
		String userName = userInfo.getUserName();
		String orderNo = json.getString("orderNo");
		String picturelink = json.getString("picturelink");
		String type = json.getString("type");
		
		if(type.equals(Constant.PICTURE_TYPE_CAGE)){//装箱
			Pic pic = new Pic();
			pic.setOrderId(orderNo);
			pic.setPicturelink(picturelink);
			pic.setClassify(Constant.PICTURE_TYPE_CAGE);
			pic.setCreatedBy(userName);
			pic.setCreatedAt(new Date());	
			picMapper.insertSelective(pic);
		}else if(type.equals(Constant.PICTURE_TYPE_CAR)){//发车
			Pic pic = new Pic();
			pic.setOrderId(orderNo);
			pic.setPicturelink(picturelink);
			pic.setClassify(Constant.PICTURE_TYPE_CAR);
			pic.setCreatedBy(userName);
			pic.setCreatedAt(new Date());	
			picMapper.insertSelective(pic);
		}else if(type.equals(Constant.PICTURE_TYPE_RECEIVE)){//接受
			Pic pic = new Pic();
			pic.setOrderId(orderNo);
			pic.setPicturelink(picturelink);
			pic.setClassify(Constant.PICTURE_TYPE_RECEIVE);
			pic.setCreatedBy(userName);
			pic.setCreatedAt(new Date());	
			picMapper.insertSelective(pic);
		}else{//异常
			Pic pic = new Pic();
			pic.setOrderId(orderNo);
			pic.setPicturelink(picturelink);
			pic.setClassify(Constant.PICTURE_TYPE_PROBLEM);
			pic.setCreatedBy(userName);
			pic.setCreatedAt(new Date());	
			picMapper.insertSelective(pic);
		}
		
		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		return JSONObject.fromObject(returnHandle).toString();
	}
	
	//显示图片
	public File showImage(String id) throws Exception {
		File file = null;		
		String c_filePath = id;
		file = new File(c_filePath);
		return file;
	}

}


