package com.zkt.project.biology.constant;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class ReturnObjectHandle extends ReturnSimpleHandle implements Serializable {

	private static final long serialVersionUID = -4915500582417432476L;
	
	private Integer dataMaxPage;//总页数
	private Integer pageCount;//每页行数
	private Integer curPage;//当页页码
	private Integer dataMaxCount;//数据总数
	private Integer draw;
	
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public Integer getDataMaxPage() {
		return dataMaxPage;
	}
	public void setDataMaxPage(Integer dataMaxPage) {
		this.dataMaxPage = dataMaxPage;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getCurPage() {
		return curPage;
	}
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
	public Integer getDataMaxCount() {
		return dataMaxCount;
	}
	public void setDataMaxCount(Integer dataMaxCount) {
		this.dataMaxCount = dataMaxCount;
	}
	
	/**
	 * 创建一个返回服务端对象
	 * @return
	 */
	public static ReturnObjectHandle createHandle() {
		ReturnObjectHandle handle = new ReturnObjectHandle();
		handle.setMessage(SystemConstant.MESSAGE_SERVER_Z00);
		handle.setSuccess(true);
		handle.setStatus(SystemConstant.MESSAGE_SERVER_CODE_Z00);
		return handle;
	}
	
	/**
	 * 创建一个返回服务端对象
	 * @return
	 */
	public static ReturnObjectHandle createServerHandle() {
		return createHandle();
	}
	
	/**
	 * convert父类
	 * @return
	 */
	public ReturnSimpleHandle convertSimple() {
		JSONObject json = (JSONObject) getData();
		ReturnSimpleHandle retobj = new ReturnSimpleHandle();
		retobj.setSuccess(json.getBoolean("success"));
		retobj.setData(json.get("data"));
		retobj.setStatus(json.getInt("status"));
		retobj.setMessage(json.getString("message"));
		return retobj;
	}
	
}
