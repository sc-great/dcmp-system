package com.great.tool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class TreeNode implements Serializable {

	private static final long serialVersionUID = 1L;

	private String title;
	private String value;
	private String name;
	private String open;
	private String icon;//图标展示样式
	private boolean checked; 
	private Boolean isParent;
	private Object extend;
	private List<TreeNode> data=new ArrayList<TreeNode>();
	
	public TreeNode(String title, String value, boolean checked, List<TreeNode> data) {
		super();
		this.title = title;
		this.value = value;
		this.checked = checked;
		this.data = data;
	}
	public TreeNode(String title, String value, boolean checked) {
		super();
		this.title = title;
		this.value = value;
		this.checked = checked;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	public Object getExtend() {
		return extend;
	}
	public void setExtend(Object extend) {
		this.extend = extend;
	}
	public List<TreeNode> getData() {
		return data;
	}
	public void setData(List<TreeNode> data) {
		this.data = data;
	}

}
