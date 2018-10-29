package com.zkt.project.biology.service.wechat;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkt.common.core.util.RequestConstance;
import com.zkt.common.core.util.RequestTag;
import com.zkt.common.core.util.ValidateUtil;
import com.zkt.project.biology.entity.Login;
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
public class WeChatGetHospitalsService {

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

	
	public String getHospitals(HttpServletRequest request) throws Exception {

		JSONObject result = new JSONObject();

		// 操作员所属单位ID
		String userHospitalID = request.getParameter("userHospitalID");
		if (ValidateUtil.isEmpty(userHospitalID)) {
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_MISSING_PARAMETER);
			result.put(RequestConstance.RESULT_MSG, "缺少参数");
			return result.toString();
		}

		long id = Long.parseLong(userHospitalID);
		Login login = loginMapper.selectByPrimaryKey(id);

		// 查询不含下单医院的医院
		List<Map<String, Object>> hospitals = loginMapper.getHospitals(login.getUserHospital());

		JSONArray restMap = new JSONArray();
		JSONObject all = new JSONObject();

		for (int i = 0; i < hospitals.size(); i++) {
			all.put("Hospital", hospitals.get(i).get("userHospital"));
			all.put("Hospitalid", hospitals.get(i).get("userHospitalId"));
			restMap.add(all);
		}
		// 返回数据
		result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_OK);
		result.put(RequestConstance.RESULT_MSG, RequestConstance.MSG_SUCCESS);
		result.put(RequestConstance.RESULT_DATA, restMap);
		return result.toString();

	}

}
