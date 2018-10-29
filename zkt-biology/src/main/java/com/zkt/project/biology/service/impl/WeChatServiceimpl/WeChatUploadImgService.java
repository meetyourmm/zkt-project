package com.zkt.project.biology.service.impl.WeChatServiceimpl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkt.common.core.util.ConverterUtil;
import com.zkt.common.core.util.DateUtils;
import com.zkt.common.core.util.FileUtil;
import com.zkt.common.core.util.RequestConstance;
import com.zkt.common.core.util.RequestTag;
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
import com.zkt.project.biology.utils.SystemContent;

import net.sf.json.JSONObject;

@Service
public class WeChatUploadImgService {

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

	public String uploadImg() throws Exception {

		JSONObject result = new JSONObject();

		String base = "picters";// 图片文件夹
		String path = FileUtil.getPropertiesValue("file.upload.base");// 系统默认C盘路径
																		// C:/share_data/mk104/
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

		Map<String, Object> restMap = new HashMap<String, Object>();

		while (i.hasNext()) {

			FileItem fi = (FileItem) i.next();
			String fileName = fi.getName();// Sample.JPG

			long time = System.currentTimeMillis();
			newfileName = String.valueOf(time);

			if (fileName != null) {

				formatName = fi.getName().substring(fi.getName().lastIndexOf("."));
				File savedFile = new File(filePath, newfileName + formatName);// C:\share_data\mk104\picters\6e5d001b-6eed-4814-827a-cabdd4f32f4b.xlsx
				fi.write(savedFile);

				// 上传成功
				String fileRealResistPath = filePath + "/" + newfileName + formatName;
				restMap.put("picturelink", fileRealResistPath);
			}
		}

		// 返回数据
		result.put(RequestConstance.RESULT_CODE, RequestTag.CODE_OK);
		result.put(RequestConstance.RESULT_MSG, RequestConstance.MSG_SUCCESS);
		result.put(RequestConstance.RESULT_DATA, ConverterUtil.map2JsonObject(restMap));
		return result.toString();

	}

}
