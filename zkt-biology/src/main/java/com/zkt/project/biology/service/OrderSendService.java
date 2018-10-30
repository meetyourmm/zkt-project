package com.zkt.project.biology.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import com.zkt.project.biology.constant.SystemConstant;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkt.common.core.util.DateUtils;
import com.zkt.common.core.util.HttpRequest;
import com.zkt.common.core.util.UserInfo;
import com.zkt.project.biology.constant.Constant;
import com.zkt.project.biology.constant.ReturnObjectHandle;
import com.zkt.project.biology.constant.ReturnSimpleHandle;
import com.zkt.project.biology.entity.Cage;
import com.zkt.project.biology.entity.Login;
import com.zkt.project.biology.entity.Operatrecord;
import com.zkt.project.biology.entity.Order;
import com.zkt.project.biology.entity.Running;
import com.zkt.project.biology.entity.Sample;
import com.zkt.project.biology.entity.Wechatusers;
import com.zkt.project.biology.mapper.CageMapper;
import com.zkt.project.biology.mapper.LoginMapper;
import com.zkt.project.biology.mapper.OperatrecordMapper;
import com.zkt.project.biology.mapper.OrderMapper;
import com.zkt.project.biology.mapper.RunningMapper;
import com.zkt.project.biology.mapper.SampleMapper;
import com.zkt.project.biology.mapper.WechatusersMapper;
import com.zkt.project.biology.utils.SystemContent;

import net.sf.json.JSONObject;

@Service
public class OrderSendService {

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
	private RunningMapper runningMapper;
	
	@Autowired
	private WechatusersMapper wechatusersMapper;
	
	@Resource(name = "proper")
	private Map<?, ?> proper;

	// 下载样本Excel模板
	public ReturnObjectHandle searchSample(JSONObject json) throws Exception {

		Integer draw = Integer.parseInt("10");
		Integer from = Integer.parseInt("0");
		Integer pageSize = Integer.parseInt("10");

		Map<String, Object> map = new HashMap<>();
		map.put("pageSize", pageSize);
		map.put("from", from);

		List<Sample> sampleList = sampleMapper.selectExcel(map);
		Integer sampleListCount = sampleMapper.countByExcel(map);

		// 封装返回参数
		ReturnObjectHandle returnHandle = ReturnObjectHandle.createServerHandle();
		returnHandle.setDraw(draw);
		returnHandle.setData(sampleList);
		returnHandle.setPageCount(pageSize);
		returnHandle.setDataMaxCount(sampleListCount);
		returnHandle.setDataMaxPage(
				sampleListCount % pageSize == 0 ? sampleListCount / pageSize : sampleListCount / pageSize + 1);
		return returnHandle;
	}

	// 查询不含下单医院的医院名称ID集合
	public List<Map<String, Object>> getHospitals() throws Exception {
		
		UserInfo userInfo = null; //RedisContent.getUserInfo();		
		// 操作员所属单位ID
		String userId = String.valueOf(userInfo.getUserId());
		long id = Long.parseLong(userId);
		Login login = loginMapper.selectByPrimaryKey(id);
		String userHospital=login.getUserHospital();
		if( null==userHospital){
			userHospital="";
		}
		// 查询不含下单医院的医院
		return loginMapper.getHospitals(userHospital);
	}

	// 查询运输方与医院对应的单位名称ID集合
	public List<Login> getOffices(JSONObject json) {
		// 操作员所属单位ID
		String userHospitalId = json.getString("userHospitalId");
		// 查询运输方与医院对应的单位
		return loginMapper.getOffices(userHospitalId);
	}

	// 保存订单
	public ReturnSimpleHandle saveOrder(JSONObject json){
		
		UserInfo userInfo = null;// RedisContent.getUserInfo();
		String userName = userInfo.getUserName();
		String userId = String.valueOf(userInfo.getUserId());
		JSONObject userJson = JSONObject.fromObject(userInfo.getUser());
		String city = userJson.getString("city");
		String area = userJson.getString("area");
		
		String userOfficeId = json.getString("userOfficeId");// 承运单位ID
		String userHospitalId = json.getString("userHospitalId");// 医院ID
		String transport = json.getString("transport");// 承运单位
		String reciver = json.getString("reciver");// 医院
		String cageno = json.getString("cageno");
		String tlimitup = json.getString("tlimitup");
		String tlimitdown = json.getString("tlimitdown");
		String hlimitup = json.getString("hlimitup");
		String hlimitdown = json.getString("hlimitdown");
		String remarks = json.getString("remarks");
		
		String sign = json.getString("sign");
		String sampleclassify = json.getString("sampleclassify");
		String counter = json.getString("counter");
		
		String sign1 = json.getString("sign1");
		String sampleclassify1 = json.getString("sampleclassify1");
		String counter1 = json.getString("counter1");
		String totalNumber=json.getString("totalNumber");
		
		//用户下单后容易出现写入两个相同订单的问题，而且无法删除，同时导致扫码开锁出现问题
		//解决办法，在写入订单时，先判断是否有相同编号的箱体绑定的订单处于未完成状态，即状态不是完成和异常状态，如果存在这样的订单就不再写入
		Order isExistence = orderMapper.selectByCageno(cageno);
		if(null != isExistence){
			return ReturnSimpleHandle.createServerError("此箱体已绑定了订单并开始使用，请重新选择箱体!", SystemConstant.ERROR_MESSAGE_SERVER_CODE_F01, null, null);
		}
			
		// 建立订单号
		String orderNo = null;
		try {
			orderNo = commonServiceImpl.createSerialNumber(Constant.PRE_ORDER);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 保存订单
		Order order = new Order();
		order.setOrderNo(orderNo);
		order.setSenderid(userId);// 发货医院ID
		order.setTransportid(userOfficeId);// 运输方ID
		order.setReciverid(userHospitalId);// 收货医院ID
		order.setSender(loginMapper.getHospital(userId));//发货医院名称
		if(transport.equals("") && reciver.equals("")){
			order.setTransport(loginMapper.getOffice(userOfficeId));
			order.setReciver(loginMapper.getHospital(userHospitalId));
		}else{
			order.setTransport(transport);//运输方名称
			order.setReciver(reciver);//收货医院名称
		}
		order.setCreatedBy(userName);//订单所有关联的发 送 收 三方人员全部用账号绑定 账号不能修改锁定
		order.setCreatedAt(new Date());
		order.setCageNo(cageno);
		order.setClassify(Constant.ORDER_CLASSIFY_PC);
		order.setTlimitup(tlimitup);
		order.setTlimitdown(tlimitdown);
		order.setHlimitup(hlimitup);
		order.setHlimitdown(hlimitdown);
		order.setRemarks(remarks);
		order.setOrderStatus(Constant.ORDER_STATUS_NEW);// 订单状态
		order.setIsProblem(Constant.IS_PROBLEM_N);// 是否异常
		order.setCity(city);
		order.setArea(area);
		order.setNum(totalNumber);
		if(!sampleclassify.isEmpty() && !counter.isEmpty()){//订单表关联样本表(表单上传)
			order.setSampleclassify(sampleclassify);//样本类型
		}
		if(!sampleclassify1.isEmpty() && !counter1.isEmpty()){//订单表关联样本表(Excel导入)
			order.setSampleclassify(sampleclassify1);//样本类型
		}		
		orderMapper.insertSelective(order);
		
		Sample upSample = new Sample();
		List<Sample> sample = sampleMapper.selectBySign(sign);
		List<Sample> sample1 = sampleMapper.selectBySign(sign1);
		
		if(!sample.isEmpty()){
			for(int i=0; i<sample.size(); i++){
				upSample.setId(sample.get(i).getId());
				upSample.setOrderNo(orderNo);
				sampleMapper.updateByPrimaryKeySelective(upSample);
			}
		}
		if(!sample1.isEmpty()){
			for(int i=0; i<sample1.size(); i++){
				upSample.setId(sample1.get(i).getId());
				upSample.setOrderNo(orderNo);
				sampleMapper.updateByPrimaryKeySelective(upSample);
			}
		}
		
		// 更新箱子
		Cage cage = cageMapper.selectByCageno(cageno);
		cage.setState(Constant.CAGESTATE_ORDER);// 已绑定订单
		cageMapper.updateByPrimaryKeySelective(cage);
				
		// 关联订单流水
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
		maps.put("id", userOfficeId);
		Login login1 = loginMapper.login(maps);
		
		Wechatusers wechatusers = wechatusersMapper.selectByUsername(login1.getUserName());
		if(null != wechatusers && wechatusers.getUserhospitalid().equals(userOfficeId) && wechatusers.getUsertype().equals(Constant.USER_TYPE_TRANSPORT)){
			HttpRequest.sendPost("http://m.jerbio.com/index.php/Z017/Message/handel_inform", "orderno="+orderNo+"&sender="+loginMapper.getHospital(userId)+"&openid="+wechatusers.getUserid()+"&receiver="+reciver);
		}

		ReturnSimpleHandle returnHandle = ReturnSimpleHandle.createServerHandle();
		return returnHandle;
	}

	// 批量保存样本信息
	public String saveSamples(JSONObject json) throws Exception {
		
		JSONObject sample = json.getJSONObject("sample");
		int counter = json.getInt("counter");//样本总数
		
		String sign = UUID.randomUUID().toString();
		Sample sample1 = new Sample();
		for (int i = 1; i <= counter; i++) {
			
			String sampleNumber = sample.getString("sampleNumber" + i);
			
			if(!sampleNumber.equals("")){
				sample1.setSampleNumber(sampleNumber);//样本编码
				sample1.setSign(sign);
				sampleMapper.insertSelective(sample1);
			}			
		}
		return sign;
	}

	// 上传样本execl
	public Sample insertExeclInfo() throws Exception {
		
		String base = "execl";//execl文件夹
		String path = (String) proper.get("file.upload.base");//系统默认C盘路径		
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
		
		Sample samples = new Sample();
		
		while (i.hasNext()) {

			FileItem fi = (FileItem) i.next();
			String fileName = fi.getName();// Sample.xlsx
			newfileName = "YBXX" + DateUtils.ymdhms4();// 文件名称
			
			if (fileName != null) {
				
				formatName = fi.getName().substring(fi.getName().lastIndexOf("."));// 获取文件后缀名
				File savedFile = new File(filePath, newfileName + formatName);// 
				fi.write(savedFile);
				
				// 上传成功
				String fileRealResistPath = filePath + "/" + newfileName + formatName;
				// 保存
				samples.setExcel(fileRealResistPath);
			}
		}
		return samples;
	}

	// 样本excel导入保存
	public String saveExcel(JSONObject json) throws Exception {
		
		JSONObject result = new JSONObject();
		
		String filePath = json.getString("excel");
		
		FileInputStream inputStream = new FileInputStream(filePath);

		Sheet sheet;
		String sign1;
		String sampleclassify;
		Sample samples;
		int rowNum;
		try (Workbook workbook = new XSSFWorkbook(inputStream)) {
			sheet = workbook.getSheetAt(0);
		}

		sign1 = UUID.randomUUID().toString();
		sampleclassify = "";
		samples = new Sample();

		for (Iterator<Row> iterator = sheet.iterator(); iterator.hasNext();) {
			
			Row row = (Row) iterator.next();
			if (row.getRowNum() < 1) {
				continue;
			}							
			if (row.getCell(0) != null) {
				row.getCell(0).setCellType(CellType.STRING);
				samples.setSampleNumber(row.getCell(0).getStringCellValue());//样本编号
			}
			if (row.getCell(1) != null) {
				row.getCell(1).setCellType(CellType.STRING);
				sampleclassify = row.getCell(1).getStringCellValue();//样本类型
			}
			samples.setSign(sign1);//特征码
			sampleMapper.insertSelective(samples);			
		}

		rowNum = sheet.getLastRowNum();
		result.put("sampleclassify1", sampleclassify);
		result.put("counter1", rowNum);
		result.put("sign1", sign1);
		return result.toString();
	}

}
