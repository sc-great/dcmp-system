package com.great.tool;

import java.util.List;
import java.util.Map;
/**
 * Excel对象
 * @author LM
 *
 */
public class ExcelBean {

	String fileName; // excel文件名
	String sheetName; // sheet名
	String title; // 标题
	String[] header; // excel表头
	Map<Integer, List<String>> memberMap; // 元素
	String path; // 存储位置

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public Map<Integer, List<String>> getMemberMap() {
		return memberMap;
	}

	public void setMemberMap(Map<Integer, List<String>> memberMap) {
		this.memberMap = memberMap;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
