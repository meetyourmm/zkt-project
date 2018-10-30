package com.zkt.project.biology.utils;

import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;

public class ExportSetInfo {
	// 导出数据存放处
	public LinkedHashMap<String, List> objsMap;

	// 大标题
	public String[] titles;

	// 每列 标题
	public List<String[]> headNames;

	// 每列 属性名称
	public List<String[]> fieldNames;

	// 输出流
	public OutputStream out;

	public LinkedHashMap<String, List> getObjsMap() {
		return objsMap;
	}

	public void setObjsMap(LinkedHashMap<String, List> objsMap) {
		this.objsMap = objsMap;
	}

	public List<String[]> getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(List<String[]> fieldNames) {
		this.fieldNames = fieldNames;
	}

	public String[] getTitles() {
		return titles;
	}

	public void setTitles(String[] titles) {
		this.titles = titles;
	}

	public List<String[]> getHeadNames() {
		return headNames;
	}

	public void setHeadNames(List<String[]> headNames) {
		this.headNames = headNames;
	}

	public OutputStream getOut() {
		return out;
	}

	public void setOut(OutputStream out) {
		this.out = out;
	}
}
