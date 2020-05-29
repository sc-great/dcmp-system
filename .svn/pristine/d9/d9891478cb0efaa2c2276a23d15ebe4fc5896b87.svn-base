package com.great.system.service;

import java.util.List;
import java.util.Map;

import com.great.base.service.BaseService;
import com.great.system.entity.SDictionaryEntity;
import com.great.system.entity.SDictionaryValEntity;
import com.great.tool.PageBean;

public interface DictionaryService extends BaseService<SDictionaryEntity> {

	public void getDicResult(PageBean pageBean);
	
	public void getDicValueResult(PageBean pageBean);
	
	public void changeDicStatus(String[] ids, String status);
	
	public void changeDicValueStatus(String[] ids, String status);
	
	public void delDicByIds(String[] ids);
	
	public void delDicValueByIds(String[] ids);
	
	public void delDicValueByDicId(String[] ids);
	
	public List<Map> getDicTree();

	public List<SDictionaryValEntity> getDicValueByDicCode(String dicCode);
	
	public SDictionaryValEntity getDicValueById(String dicValueId);
	
	public SDictionaryValEntity getDicValueByValue(String value);
	
	public SDictionaryEntity getDicByValue(String value);

	public List<Map> getDicList();

	public SDictionaryValEntity getDicValueByName(String dvName);
	
	public boolean canDelete(SDictionaryValEntity dicVal);
}
