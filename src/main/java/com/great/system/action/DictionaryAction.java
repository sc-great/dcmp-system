package com.great.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.great.base.action.BaseAction;
import com.great.base.entity.Message2Page;
import com.great.resource.StaticData;
import com.great.system.entity.SDictionaryEntity;
import com.great.system.entity.SDictionaryValEntity;
import com.great.system.service.DictionaryService;
import com.great.tool.JsonCovert;
import com.great.tool.PageBean;
import com.great.tool.ReflectCommon;
import com.great.tool.ReplaceUtil;
import com.great.tool.UUID;

/**
 * @author LUOCHENG
 *
 */
@RestController
@RequestMapping(value = "/dic")
public class DictionaryAction extends BaseAction {
	@Autowired
	private DictionaryService dictionaryService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@PostMapping("/addDic")
	public Message2Page add(SDictionaryEntity dic) {
		// 过滤string类型的字段内的特殊字符
		ReflectCommon<SDictionaryEntity> common = new ReflectCommon<SDictionaryEntity>();
		Map<String, Object> map = common.test(dic);
		dic = (SDictionaryEntity) ReplaceUtil.replaceSpecString(map, dic);		
		
		// 数据项编码重复校验
		SDictionaryEntity dicValidate = dictionaryService.getDicByValue(dic.getDicCode());
		if (dicValidate != null) {
			return new Message2Page(false, "200", "已存在数据项编码,请重新输入");
		}
		dic.setDicId(UUID.randomUUID());
		dic.setStatus(StaticData.STATUS_NORMAL);
		dictionaryService.save(dic);
		String msg = "添加数据字典：" + dic.getDicName();
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	@PostMapping("/addDicValue")
	public Message2Page add(SDictionaryValEntity dicValue) {
		// 过滤string类型的字段内的特殊字符
		ReflectCommon<SDictionaryValEntity> common = new ReflectCommon<SDictionaryValEntity>();
		Map<String, Object> map = common.test(dicValue);
		dicValue = (SDictionaryValEntity) ReplaceUtil.replaceSpecString(map, dicValue);	
		
		// 校验添加数据值重复
		SDictionaryValEntity dvalidate = dictionaryService.getDicValueByValue(dicValue.getDvValue());
		if (dvalidate != null) {
			return new Message2Page(false, "200", "已存在数据值编码,请重新输入");
		}
		dicValue.setDvId(UUID.randomUUID());
		dicValue.setStatus(StaticData.STATUS_NORMAL);
		
		dictionaryService.save(dicValue);
		String msg = "添加数据字典数据值：" + dicValue.getDvName();
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);

	}

	@PostMapping("/updateDic")
	public Message2Page update(SDictionaryEntity dic) {
		// 过滤string类型的字段内的特殊字符
		ReflectCommon<SDictionaryEntity> common = new ReflectCommon<SDictionaryEntity>();
		Map<String, Object> map = common.test(dic);
		dic = (SDictionaryEntity) ReplaceUtil.replaceSpecString(map, dic);	
		
		// 数据项编码重复校验
		SDictionaryEntity dicValidate = dictionaryService.getDicByValue(dic.getDicCode());
		if (dicValidate != null && (!dicValidate.getDicId().equals(dic.getDicId()))) {
			return new Message2Page(false, "200", "已存在数据项编码,请重新输入");
		}
		SDictionaryEntity dicSource = dictionaryService.get(SDictionaryEntity.class, dic.getDicId());
		this.mergeObject(dic, dicSource);
		dictionaryService.update(dicSource);
		String msg = "更新数据字典：" + dicSource.getDicName();
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	@PostMapping("/updateDicValue")
	public Message2Page update(SDictionaryValEntity dicValue) {
		// 过滤string类型的字段内的特殊字符
		ReflectCommon<SDictionaryValEntity> common = new ReflectCommon<SDictionaryValEntity>();
		Map<String, Object> map = common.test(dicValue);

		dicValue = (SDictionaryValEntity) ReplaceUtil.replaceSpecString(map, dicValue);
		
		// 校验添加数据值重复
		SDictionaryValEntity dvalidate = dictionaryService.getDicValueByValue(dicValue.getDvValue());
		if (dvalidate != null && (!dvalidate.getDvId().equals(dicValue.getDvId()))) {
			return new Message2Page(false, "200", "已存在数据值,请重新输入");
		}
		SDictionaryValEntity dicValueSource = dictionaryService.getDicValueById(dicValue.getDvId());
		this.mergeObject(dicValue, dicValueSource);
		
		dictionaryService.update(dicValueSource);
		String msg = "更新数据字典数据值：" + dicValueSource.getDvName();
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);

	}

	@PostMapping("/getDicById")
	public Message2Page getDicById(@RequestParam String id) {
		SDictionaryEntity dic = dictionaryService.get(SDictionaryEntity.class, id);
		JSONObject jsonObject = JSONObject.fromObject(dic);
		jsonObject = JsonCovert.filterNull(jsonObject);
		return new Message2Page(true, "200", "", jsonObject);
	}

	@PostMapping("/getDicValueById")
	public Message2Page getDicValueById(@RequestParam String id) {
		SDictionaryValEntity dicValue = dictionaryService.getDicValueById(id);
		JSONObject jsonObject = JSONObject.fromObject(dicValue);
		jsonObject = JsonCovert.filterNull(jsonObject);
		return new Message2Page(true, "200", "", jsonObject);
	}

	@PostMapping("/getDicValueByValue")
	public SDictionaryValEntity getDicValueByValue(@RequestParam String dvValue) {
		SDictionaryValEntity dicValue = dictionaryService.getDicValueByValue(dvValue);
		return dicValue;
	}

	@PostMapping("/delDic")
	public Message2Page deleteDic(@RequestParam(value = "ids[]") String[] ids) {
		String delName = "";
		for (int i = 0; i < ids.length; i++) {
			SDictionaryEntity dic = dictionaryService.get(SDictionaryEntity.class, ids[i]);
			delName += dic.getDicName() + ",";
		}
		dictionaryService.delDicByIds(ids);
		dictionaryService.delDicValueByDicId(ids);
		String msg = "删除数据字典：" + delName;
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	@PostMapping("/delDicValue")
	public Message2Page deleteDicValue(@RequestParam(value = "ids[]") String[] ids) {
		if(ids==null||ids.length==0){
			return new Message2Page(false, "500", null);
		}
		SDictionaryValEntity dv = dictionaryService.getDicValueById(ids[0]);
		if(!dictionaryService.canDelete(dv)){
			return new Message2Page(false, "500", "该数据值已有模块关联，不允许删除");
		}
		String delName = dv.getDvName();
		/*for (int i = 0; i < ids.length; i++) {
			SDictionaryValEntity dicValue = dictionaryService.getDicValueById(ids[i]);
			delName += dicValue.getDvName() + ",";
		}*/
		dictionaryService.delDicValueByIds(ids);
		String msg = "删除数据字典数据值：" + delName;
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	@GetMapping("/getDicPage")
	public JSONObject getDicListByPageBean(@RequestParam Integer page, @RequestParam Integer limit,
			@RequestParam(required = false, defaultValue = "") String dicName) {
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPageNumber(page);
		pageBean.setLimit(limit);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("dicName", dicName);
		pageBean.setMap(param);
		dictionaryService.getDicResult(pageBean);
		JSONObject returnObject = JSONObject.fromObject(pageBean);
		returnObject = JsonCovert.filterNull(returnObject);
		return returnObject;
	}

	@PostMapping("/changeDicStatus")
	public Message2Page changeDicStatus(@RequestParam(value = "ids[]") String[] ids, @RequestParam String status) {
		dictionaryService.changeDicStatus(ids, status);
		String cName = "";
		for (int i = 0; i < ids.length; i++) {
			SDictionaryEntity dic = dictionaryService.get(SDictionaryEntity.class, ids[i]);
			cName += dic.getDicName() + ",";
		}
		StringBuffer msg = new StringBuffer();
		msg.append(status.equals("1") ? "停用" : "启用").append("用户：").append(cName);
		saveLogB(msg.toString(), StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	@PostMapping("/changeDicValueStatus")
	public Message2Page changeDicValueStatus(@RequestParam(value = "ids[]") String[] ids, @RequestParam String status) {
		dictionaryService.changeDicValueStatus(ids, status);
		String cName = "";
		for (int i = 0; i < ids.length; i++) {
			SDictionaryValEntity dicValue = dictionaryService.getDicValueById(ids[i]);
			cName += dicValue.getDvName() + ",";
		}
		StringBuffer msg = new StringBuffer();
		msg.append(status.equals("1") ? "停用" : "启用").append("用户：").append(cName);
		saveLogB(msg.toString(), StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	@GetMapping("/getDicValuePage")
	public JSONObject getDicValueByPageBean(@RequestParam Integer page, @RequestParam Integer limit,
			@RequestParam String dicId, @RequestParam(required = false, defaultValue = "") String dvName) {
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPageNumber(page);
		pageBean.setLimit(limit);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("dvName", dvName);
		param.put("dicId", dicId);
		pageBean.setMap(param);
		dictionaryService.getDicValueResult(pageBean);
		JSONObject returnObject = JSONObject.fromObject(pageBean);
		returnObject = JsonCovert.filterNull(returnObject);
		return returnObject;
	}

	@PostMapping("/getDicValue")
	public List<Map<String, String>> getDicValue(@RequestParam String dicCode) {
		List<SDictionaryValEntity> svs = this.dictionaryService.getDicValueByDicCode(dicCode);
		List<Map<String, String>> svsMaps = new ArrayList<Map<String, String>>();
		if (svs != null && svs.size() > 0) {
			for (SDictionaryValEntity sv : svs) {
				Map<String, String> svsMap = new HashMap<String, String>();
				svsMap.put("id", sv.getDvValue());
				svsMap.put("name", sv.getDvName());
				svsMaps.add(svsMap);
			}
		}
		//System.out.print(svsMaps.toString());
		return svsMaps;
	}
	@PostMapping("/getLinkTypeDicValue")
	public List<Map<String, String>> getLinkTypeDicValue(@RequestParam String linkAction) {
		List<Map<String, String>> svsMaps = new ArrayList<Map<String, String>>();
		switch(linkAction){
			case "1000":
				Map<String, String> svsMap01 = new HashMap<String, String>();
				SDictionaryValEntity sv01 = this.dictionaryService.getDicValueByValue("ALARM_ENENT_CAMERA");
				svsMap01.put("id", sv01.getDvValue());
				svsMap01.put("name", sv01.getDvName());
				svsMaps.add(svsMap01);
				break;
			case "1001":
				Map<String, String> svsMap02 = new HashMap<String, String>();
				SDictionaryValEntity sv02 = this.dictionaryService.getDicValueByValue("ALARM_ENENT_LED");
				svsMap02.put("id", sv02.getDvValue());
				svsMap02.put("name", sv02.getDvName());
				svsMaps.add(svsMap02);
				break;
			case "1002":
				Map<String, String> svsMap03 = new HashMap<String, String>();
				SDictionaryValEntity sv03 = this.dictionaryService.getDicValueByValue("ALARM_ENENT_RADIO");
				svsMap03.put("id", sv03.getDvValue());
				svsMap03.put("name", sv03.getDvName());
				svsMaps.add(svsMap03);
				break;
		}
		
		return svsMaps;
	}
	@PostMapping("/getLinkAction")
	public List<Map<String, String>> getLinkAction(@RequestParam String dtId) {
		List<Map<String, String>> svsMaps = new ArrayList<Map<String, String>>();
		switch(dtId){
		case "ALARM_ENENT_MEDIA_CONTROL"://多媒体中控
			Map<String, String> svsMap01 = new HashMap<String, String>();
			SDictionaryValEntity sv01 = this.dictionaryService.getDicValueByValue("TEACHER_MACHINE_OPEN");
			svsMap01.put("id", sv01.getDvValue());
			svsMap01.put("name", sv01.getDvName());
			svsMaps.add(svsMap01);
			Map<String, String> svsMap02 = new HashMap<String, String>();
			SDictionaryValEntity sv02 = this.dictionaryService.getDicValueByValue("TEACHER_MACHINE_SHUT");
			svsMap02.put("id", sv02.getDvValue());
			svsMap02.put("name", sv02.getDvName());
			svsMaps.add(svsMap02);
			Map<String, String> svsMap03 = new HashMap<String, String>();
			SDictionaryValEntity sv03 = this.dictionaryService.getDicValueByValue("PROJECTOR_OPEN");
			svsMap03.put("id", sv03.getDvValue());
			svsMap03.put("name", sv03.getDvName());
			svsMaps.add(svsMap03);
			Map<String, String> svsMap04 = new HashMap<String, String>();
			SDictionaryValEntity sv04 = this.dictionaryService.getDicValueByValue("PROJECTOR_SHUT");
			svsMap04.put("id", sv04.getDvValue());
			svsMap04.put("name", sv04.getDvName());
			svsMaps.add(svsMap01);
			Map<String, String> svsMap05 = new HashMap<String, String>();
			SDictionaryValEntity sv05 = this.dictionaryService.getDicValueByValue("CURTAIN_OPEN");
			svsMap05.put("id", sv05.getDvValue());
			svsMap05.put("name", sv05.getDvName());
			svsMaps.add(svsMap05);
			Map<String, String> svsMap06 = new HashMap<String, String>();
			SDictionaryValEntity sv06 = this.dictionaryService.getDicValueByValue("CURTAIN_SHUT");
			svsMap06.put("id", sv06.getDvValue());
			svsMap06.put("name", sv06.getDvName());
			svsMaps.add(svsMap06);
			break;
		case "ALARM_ENENT_AIR"://空调
			Map<String, String> svsMap11 = new HashMap<String, String>();
			SDictionaryValEntity sv11 = this.dictionaryService.getDicValueByValue("OPEN");
			svsMap11.put("id", sv11.getDvValue());
			svsMap11.put("name", sv11.getDvName());
			svsMaps.add(svsMap11);
			Map<String, String> svsMap12 = new HashMap<String, String>();
			SDictionaryValEntity sv12 = this.dictionaryService.getDicValueByValue("SHUT");
			svsMap12.put("id", sv12.getDvValue());
			svsMap12.put("name", sv12.getDvName());
			svsMaps.add(svsMap12);
			break;
		case "ALARM_ENENT_LIGHT"://照明
			Map<String, String> svsMap21 = new HashMap<String, String>();
			SDictionaryValEntity sv21 = this.dictionaryService.getDicValueByValue("OPEN");
			svsMap21.put("id", sv21.getDvValue());
			svsMap21.put("name", sv21.getDvName());
			svsMaps.add(svsMap21);
			Map<String, String> svsMap22 = new HashMap<String, String>();
			SDictionaryValEntity sv22 = this.dictionaryService.getDicValueByValue("SHUT");
			svsMap22.put("id", sv22.getDvValue());
			svsMap22.put("name", sv22.getDvName());
			svsMaps.add(svsMap22);
			break;
		case "ALARM_ENENT_VOICE"://音频
			Map<String, String> svsMap31 = new HashMap<String, String>();
			SDictionaryValEntity sv31 = this.dictionaryService.getDicValueByValue("VOICE_PLAY");
			svsMap31.put("id", sv31.getDvValue());
			svsMap31.put("name", sv31.getDvName());
			svsMaps.add(svsMap31);
			break;
		}
		
		return svsMaps;
	}

	@PostMapping("/getSelectList")
	public List<Map<String, String>> getSelectList(@RequestParam String dicCode) {
		List<SDictionaryValEntity> svs = this.dictionaryService.getDicValueByDicCode(dicCode);
		List<Map<String, String>> svsMaps = new ArrayList<Map<String, String>>();
		if (svs != null && svs.size() > 0) {
			for (SDictionaryValEntity sv : svs) {
				Map<String, String> svsMap = new HashMap<String, String>();
				svsMap.put("value", sv.getDvValue());
				svsMap.put("name", sv.getDvName());
				svsMaps.add(svsMap);
			}
		}
		return svsMaps;
	}

	@PostMapping("/getDicTree")
	public List<Map> getDicTree() {
		return dictionaryService.getDicTree();
	}

	@PostMapping("getDicList")
	public List<Map> getDicList() {
		return dictionaryService.getDicList();
	}
}
