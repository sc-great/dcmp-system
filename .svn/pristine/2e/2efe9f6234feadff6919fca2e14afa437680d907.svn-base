package com.great.system.dao;

import java.util.List;

import com.great.base.dao.BaseDao;
import com.great.system.entity.SDictionaryEntity;
import com.great.system.entity.SDictionaryValEntity;
import com.great.tool.PageBean;

public interface DictionaryDao extends BaseDao {

	public void getDicResult(PageBean pageBean);

	public void getDicValueResult(PageBean pageBean);

	public void changeDicStatus(String[] ids, String status);

	public void changeDicValueStatus(String[] ids, String status);

	public void delDicByIds(String[] ids);

	public void delDicValueByIds(String[] ids);
	
	public void delDicValueByDicId(String[] ids);

	public SDictionaryValEntity getDicValueById(String dicValueId);
	
	public List<SDictionaryValEntity> getDicValueByDicCode(String dicCode);
	
	public SDictionaryValEntity getDicValueByValue(String value);

	public SDictionaryEntity getDicByValue(String value);

	public SDictionaryValEntity getDicValueByName(String dvName);

	public List<SDictionaryEntity> getDicList();
	
	public boolean canDelete(SDictionaryValEntity dicVal);
}
