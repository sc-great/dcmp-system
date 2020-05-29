package com.great.tool;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author: Max
 * @description:分页属性
 * 
 */
public class PageBean implements Serializable {

	private static final long serialVersionUID = 7018710965546387049L;
	// 当前页号
	private int currentPageNumber = 1;
	// 每页显示记录数 默认 一页显示15条记录
	private int limit = 15;
	// 总共的数量
	private int total;
	// 是否是首页
	private boolean pageIsFirst;
	// 是否是最后一页
	private boolean pageIsEnd;
	// 总页数
	private int count;
	// 开始序号
	private int startNum = 0;
	// 存储条件查询结果集
	private List<?> data = null;

	private Map<String, Object> map = new HashMap<String, Object>();

	private int code = 0;

	private String msg = "";

	public PageBean() {
		init();
	}

	public PageBean(Map<String, Object> map, int total, int limit) {
		super();
		this.map = map;
		this.total = total;
		this.limit = limit;
		init();
	}

	// 初始化数据
	private void init() {
		this.startNum = (this.currentPageNumber - 1) * this.limit;
		this.count = this.total % this.limit == 0 ? this.total / this.limit : this.total / this.limit + 1;
		this.pageIsFirst = (this.currentPageNumber == 1);
		this.pageIsEnd = (this.currentPageNumber == this.count);
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
		init();
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		init();
	}

	public int getStartNum() {
		return (this.currentPageNumber - 1) * this.limit;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
		init();
	}

	public boolean isPageIsFirst() {
		return pageIsFirst;
	}

	public void setPageIsFirst(boolean pageIsFirst) {
		this.pageIsFirst = pageIsFirst;
	}

	public boolean isPageIsEnd() {
		return pageIsEnd;
	}

	public void setPageIsEnd(boolean pageIsEnd) {
		this.pageIsEnd = pageIsEnd;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
