package com.zkt.project.biology.service.wechat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkt.common.core.util.RequestConstance;
import com.zkt.common.core.util.RequestTag;
import com.zkt.project.biology.entity.Model;
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
public class WeChatGetModelService {

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

	
	public String getModel(HttpServletRequest request) throws Exception {

		JSONObject result = new JSONObject();

		// 操作员所属单位ID
		String userHospitalID = request.getParameter("userHospitalID");

		Map<String, Object> map = new HashMap<>();
		map.put("senderid", userHospitalID);
		List<Model> models = modelMapper.selectWeChatModel(map);

		if (null == models) {
			result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_FAIL);
			result.put(RequestConstance.RESULT_MSG, "不存在此医院关联模板!");
			return result.toString();
		}

		JSONArray restMap = new JSONArray();
		JSONObject all = new JSONObject();

		for (int i = 0; i < models.size(); i++) {
			all.put("modelNo", models.get(i).getModelNo());
			all.put("senderid", models.get(i).getSenderid());
			all.put("transportid", models.get(i).getTransportid());
			all.put("reciverid", models.get(i).getReciverid());
			all.put("sender", models.get(i).getSender());
			all.put("transport", models.get(i).getTransport());
			all.put("reciver", models.get(i).getReciver());
			all.put("tlimitup", models.get(i).getTlimitup());
			all.put("tlimitdown", models.get(i).getTlimitdown());
			all.put("hlimitup", models.get(i).getHlimitup());
			all.put("hlimitdown", models.get(i).getHlimitdown());
			restMap.add(all);
		}
		// 返回数据
		result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_OK);
		result.put(RequestConstance.RESULT_MSG, RequestConstance.MSG_SUCCESS);
		result.put(RequestConstance.RESULT_DATA, restMap);
		return result.toString();
		
	}

}
