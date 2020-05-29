package com.great.system.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.base.service.impl.BaseServiceImpl;
import com.great.system.dao.DictionaryDao;
import com.great.system.entity.SDictionaryEntity;
import com.great.system.entity.SDictionaryValEntity;
import com.great.system.service.DictionaryService;
import com.great.tool.PageBean;
import com.great.tool.TreeNode;

@Service
public class DictionaryServiceImpl extends BaseServiceImpl<SDictionaryEntity> implements DictionaryService {

	@Autowired
	private DictionaryDao dictionaryDao;

	@Override
	public void getDicResult(PageBean pageBean) {
		this.dictionaryDao.getDicResult(pageBean);
	}

	@Override
	public void getDicValueResult(PageBean pageBean) {
		this.dictionaryDao.getDicValueResult(pageBean);
	}
	@Override
	public SDictionaryValEntity getDicValueById(String dicValueId) {
		return dictionaryDao.getDicValueById(dicValueId);
	}

	@Override
	public void changeDicStatus(String[] ids, String status) {
		this.dictionaryDao.changeDicStatus(ids, status);
		
	}

	@Override
	public void changeDicValueStatus(String[] ids, String status) {

		this.dictionaryDao.changeDicValueStatus(ids, status);
		
	}

	@Override
	public void delDicByIds(String[] ids) {
		this.dictionaryDao.delDicByIds(ids);
	}

	@Override
	public void delDicValueByIds(String[] ids) {
		this.dictionaryDao.delDicValueByIds(ids);
	}

	@Override
	public List<Map> getDicTree() {
		List<Map> dicTrees = new ArrayList<Map>();
		Map<String,Object> dicTree = new HashMap<String, Object>();
		dicTree.put("name", "数据字典");
		dicTree.put("id", "0");
		dicTree.put("spread", false);
		dicTree.put("parent", true);
		List<SDictionaryEntity> dics = this.dictionaryDao.findAllNoDelete(SDictionaryEntity.class);
		if(dics!=null&&dics.size()>0){
			List<Map> dicList = new ArrayList<Map>();
			for(SDictionaryEntity dic : dics){
				Map<String,Object> dt = new HashMap<String, Object>();
				dt.put("id", dic.getDicId());
				dt.put("name", dic.getDicName());
				dicList.add(dt);
			}
			dicTree.put("children", dicList);
		}
		dicTrees.add(dicTree);
		return dicTrees;
	}

	@Override
	public List<SDictionaryValEntity> getDicValueByDicCode(String dicCode) {
		return this.dictionaryDao.getDicValueByDicCode(dicCode);
	}

	@Override
	public void delDicValueByDicId(String[] ids) {

		this.dictionaryDao.delDicValueByDicId(ids);
		
	}

	@Override
	public SDictionaryValEntity getDicValueByValue(String value) {
		return this.dictionaryDao.getDicValueByValue(value);
	}

	@Override
	public SDictionaryEntity getDicByValue(String value) {
		return this.dictionaryDao.getDicByValue(value);
	}

	@Override
	public List<Map> getDicList() {
		List<SDictionaryEntity> dics = this.dictionaryDao.getDicList();
		List<Map> dicList = new ArrayList<Map>();
		if(dics!=null&&dics.size()>0){
			for(SDictionaryEntity dic : dics){
				Map<String,Object> dt = new HashMap<String, Object>();
				dt.put("id", dic.getDicId());
				dt.put("name", dic.getDicName());
				dicList.add(dt);
			}
		}
		 return dicList;
	}

	@Override
	public SDictionaryValEntity getDicValueByName(String dvName) {
		return this.dictionaryDao.getDicValueByName(dvName);
	}

	@Override
	public boolean canDelete(SDictionaryValEntity dicVal) {
		// TODO Auto-generated method stub
		return this.dictionaryDao.canDelete(dicVal);
	}
	
}
