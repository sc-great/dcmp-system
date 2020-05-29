package com.great.api;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.great.base.action.BaseAction;
import com.great.manager.entity.BPerson;
import com.great.manager.entity.SMaskRecord;
import com.great.manager.entity.STemperatureRecord;
import com.great.manager.service.MaskRecordService;
import com.great.manager.service.PersonService;
import com.great.manager.service.TemperatureRecordService;
import com.great.tool.DateConvert;
import com.great.tool.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping(value = "/api/alarmDetection")
public class AlarmDetectionAction extends BaseAction {

	@Autowired
	private PersonService personService;
	@Autowired
	private TemperatureRecordService temperatureRecordService;
	@Autowired
	private MaskRecordService maskRecordService;

	// 获取人脸库更新
	@GetMapping("/syrecord/ion")
	public JSONObject syrecord() throws ParseException {
		// Long lqt = new Long(this.getPageParameter("lastQueryTime"));
		// String lastQueryTime = DateConvert.Date2String(new Date(lqt), "");
		// List<BPerson> bps = personService.getPersonByTime(lastQueryTime);
		// JSONObject returnData = new JSONObject();
		// JSONArray list = new JSONArray();
		// if (bps != null & bps.size() > 0) {
		// for (BPerson bp : bps) {
		// JSONObject personObject = new JSONObject();
		// personObject.put("operation", bp.getIsdeleted() == (byte) 0 ? "save"
		// : "delete");
		// personObject.put("userType", "autoUser");
		// personObject.put("userId", bp.getUserCode());
		// personObject.put("userName", bp.getPName());
		// personObject.put("sex", Integer.parseInt(bp.getPSex()));
		// personObject.put("userRemark", "");
		// personObject.put("userImageUrl", "http://" +
		// this.getRequest().getLocalAddr() + ":"
		// + this.getRequest().getLocalPort() + bp.getPPic());
		// personObject.put("operateTime ", new Date().getTime());
		// list.add(personObject);
		// }
		// }
		// returnData.put("list", list);
		// returnData.put("lastQueryTime", new Date().getTime());
		// JSONObject ro = new JSONObject();
		// ro.put("serviceResponse", returnData);
		// System.out.println(ro);
		return null;// ro;
	}

	// 上传结果反馈
	@PostMapping("/syresult/{deviceid}")
	public JSONObject syresult(@PathVariable String deviceid, @RequestBody String jsonData) throws ParseException {
		System.out.println(deviceid);
		System.out.println(jsonData);
		return null;
	}

	// 设备状态心跳 syheartbeat
	@PostMapping("/syheartbeat/ion")
	public JSONObject syheartbeat(@PathVariable String deviceid, @RequestBody String jsonData) throws ParseException {
		JSONObject rob = new JSONObject();
		rob.put("ret", "0");
		rob.put("desc", "Success");
		return rob;
	}

	// 外来人员，比对失败上传体温数据
	@PostMapping("/syattribute/{deviceid}")
	public JSONObject syattribute(@PathVariable String deviceid, @RequestBody String jsonData) throws ParseException {
		/*
		 * { "Info": { "deviceid": "000mDetection", "uuid": "2936",
		 * "nationCode": "", "temperature": "36.57", "mask": false,
		 * "screenTime": 1585205885, "openCode": 102, "openMsg": "未佩戴口罩", "img":
		 * "111111111111" } }
		 */
		// 数据存储方法
		saveObject(false, deviceid, jsonData);

		JSONObject rob = new JSONObject();
		rob.put("ret", "0");
		rob.put("desc", "Success");
		return rob;
	}

	// 内部人员，比对成功上传设备编号及人员信息
	@PostMapping("/syface/{deviceid}")
	public JSONObject syface(@PathVariable String deviceid, @RequestBody String jsonData) throws ParseException {
		/*
		 * { "userId": "2.jpg", "userName": "lc", "deviceid": "000mDetection",
		 * "uuid": "2936", "nationCode": "", "actionCode": 1, "camType": "GATE",
		 * "action": "进闸", "screenTime": 1585205949, "temperature": "36.59",
		 * "mask": true, "openCode": 100, "openMsg": "开门成功", "userType": "user",
		 * "similarity": 96, "img": "111111111111"
		 */
		// 数据存储方法
		saveObject(true, deviceid, jsonData);

		JSONObject rob = new JSONObject();
		rob.put("ret", "0");
		rob.put("desc", "Success");
		return rob;
	}

	/**
	 * 
	 * @param b
	 *            false：外来人员；true：内部人员
	 * @param deviceid
	 * @param jsonData
	 */
	private void saveObject(boolean b, String deviceid, String jsonData) {
		JSONObject paramData = JSONObject.fromObject(jsonData);
		if (!b)
			paramData = JSONObject.fromObject(paramData.getString("Info"));

		String uCode = "0";
		if (b)
			uCode = paramData.getString("userName");
		float temperature = Float.parseFloat(paramData.getString("temperature"));
		int isHealth = temperature > 37 ? 1 : 0;
		boolean isAlarm = paramData.getBoolean("mask");
		Date createTime = new Date(paramData.getLong("screenTime") * 1000);
		// System.out.println(" similarity= " + paramData.getInt("similarity"));
		String picCode = "data:image/jpg;base64," + paramData.getString("img");
		String clientId = paramData.getString("uuid");

		BPerson person = null;
		if (b) {
			person = personService.getPersonByCode(uCode);
			person.setLastTemp(temperature);
			person.setHealth(isHealth);
			person.setLastDetection(createTime);
			personService.update(person);
		}

		// 没带口罩时存储数据
		if (!isAlarm) {
			SMaskRecord maskRecord = new SMaskRecord();
			maskRecord.setMaskId(UUID.randomUUID());
			maskRecord.setClientId(clientId);
			maskRecord.setCreateTime(createTime);
			maskRecord.setIsAlarm(1);
			if (b) {
				maskRecord.setUCode(person.getPId());
				maskRecord.setPName(person.getPName());
			} else {
				maskRecord.setPName("外来人员");
			}
			maskRecord.setPicCode(picCode);
			maskRecordService.save(maskRecord);
		}

		// 体温正常范围是否写死？
		// 是否设置无效值？
		STemperatureRecord temperatureRecord = new STemperatureRecord();
		temperatureRecord.setTempId(UUID.randomUUID());
		if (b) {
			temperatureRecord.setUCode(person.getPId());
			temperatureRecord.setPName(person.getPName());
		} else {
			temperatureRecord.setPName("外来人员");
		}
		temperatureRecord.setClientId(clientId);
		temperatureRecord.setCreateTime(createTime);
		temperatureRecord.setTemperature(temperature);
		temperatureRecord.setIsAlarm(isHealth);
		temperatureRecord.setPicCode(picCode);
		temperatureRecordService.save(temperatureRecord);
	}
}
