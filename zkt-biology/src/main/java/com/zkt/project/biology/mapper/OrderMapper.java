package com.zkt.project.biology.mapper;

import java.util.List;
import java.util.Map;

import com.zkt.project.biology.entity.Order;

import tk.mybatis.mapper.common.Mapper;

public interface OrderMapper extends Mapper<Order>{
	
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);
    
    Order selectByID(String id);
    
    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKeyWithBLOBs(Order record);

    int updateByPrimaryKey(Order record);
    
    List<Order> selectProblemOrder(Map<String, Object> map);

	Integer countByProblemOrder(Map<String, Object> map);
	
	List<Order> selectProblemOrderQu(Map<String, Object> map);

	Integer countByProblemOrderQu(Map<String, Object> map);
	
	List<Order> selectProblemOrderShi(Map<String, Object> map);

	Integer countByProblemOrderShi(Map<String, Object> map);	
	
	List<Order> selectProblemOrderOver(Map<String, Object> map);

	Integer countByProblemOrderOver(Map<String, Object> map);
	
    List<Order> selectByKeys(Map<String, Object> map);

	Integer countByKeys(Map<String, Object> map);
	
	List<Order> selectOrder(Map<String, Object> map);

	Integer countByOrder(Map<String, Object> map);
	
	List<Order> selectOver(Map<String, Object> map);

	Integer countByOver(Map<String, Object> map);
	
	List<Order> selectReportForm(Map<String, Object> map);

	Integer countReportForm(Map<String, Object> map);
	
	Order selectByOrderNo(String orderNo);
	
	
	//首页SQL
	String getYesterdayNum(Map<String, Object> map);
	
	String getTodayNum(Map<String, Object> map);
	
	List<Integer> topSevenAll(Map<String, Object> map3);
	
	List<Integer> topSevenOver(Map<String, Object> map3);

	Integer countByKeysss(Map<String, Object> map);

	List<Order> selectWeChatAll(Map<String, Object> maps);

	Order selectByCageNo(String cageno);

	Object getYesterdayAndTodaySenderNums(Map<String, Object> hospitalmap);

	List<Map<String, Object>> getYesterdayHospitalNums(Map<String, Object> map);

	List<Map<String, Object>> getTodayHospitalNums(Map<String, Object> map);

	Integer countZhuangxiang(Map<String, Object> map);

	Integer countQianshou(Map<String, Object> map4);

	Integer countStatus(Map<String, Object> map1);

	Order selectByCageNo1(String cageno);

	List<Order> selectOver1(Map<String, Object> map);

	Integer countByOver1(Map<String, Object> map);

	Order selectByCageno(String cageno);

	List<Order> selectModifyOrder(Map<String, Object> map);
	Integer countModifyOrder(Map<String, Object> map);
	
}


