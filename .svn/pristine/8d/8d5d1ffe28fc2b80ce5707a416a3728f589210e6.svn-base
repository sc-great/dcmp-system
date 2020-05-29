package com.great.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.great.base.action.BaseAction;
import com.great.manager.entity.BCampusHierarchy;
import com.great.manager.entity.BPerson;
import com.great.manager.entity.SMaskRecord;
import com.great.manager.entity.STemperatureRecord;
import com.great.manager.service.CampusOrgService;
import com.great.manager.service.MaskRecordService;
import com.great.manager.service.PersonService;
import com.great.manager.service.TemperatureRecordService;

import net.sf.json.JSONObject;

@RestController
@RequestMapping(value = "/api/report")
public class AlarmReportAction extends BaseAction {

	@Autowired
	private CampusOrgService campusOrgService;
	@Autowired
	private PersonService personService;
	@Autowired
	private TemperatureRecordService temperatureRecordService;
	@Autowired
	private MaskRecordService maskRecordService;

	// 获取人员检测报表
	@PostMapping("/report3")
	public JSONObject report3() {
		JSONObject jObj = new JSONObject();
		JSONObject jObjData = new JSONObject();
		try {
			List<BCampusHierarchy> campusOrgList = campusOrgService.findAllNoDelete(BCampusHierarchy.class);
			// 分部门查询
			List<JSONObject> orgList = new ArrayList<>();
			JSONObject jCampusOrg = null;
			for (BCampusHierarchy campusOrg : campusOrgList) {
				jCampusOrg = new JSONObject();
				// 统计体温异常
				int tempAlarm = personService.countNoDeleteByOrgAndHealth(campusOrg.getChId());
				// 统计没戴口罩
				int maskAlarm = maskRecordService.countNotMaskToday(campusOrg.getChId());
				jCampusOrg.put("id", campusOrg.getChId());
				jCampusOrg.put("name", campusOrg.getChName());
				jCampusOrg.put("tempAlarm", tempAlarm);
				jCampusOrg.put("maskAlarm", maskAlarm);
				orgList.add(jCampusOrg);
			}
			jObj.put("org", orgList);
			// 本周数据统计
			JSONObject jWeek = new JSONObject();
			String todayStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			Date today = new SimpleDateFormat("yyyy-MM-dd").parse(todayStr);
			Date mondayDate = getThisWeekMonday(today);
			Date tuesdayDate = dateUpOneDay(mondayDate, 1);
			Date wednesdayDate = dateUpOneDay(mondayDate, 2);
			Date tuesdayDateDate = dateUpOneDay(mondayDate, 3);
			Date fridayDate = dateUpOneDay(mondayDate, 4);
			Date saturdayDate = dateUpOneDay(mondayDate, 5);
			Date sundayDate = dateUpOneDay(mondayDate, 6);
			Date mondayDateNext = dateUpOneDay(mondayDate, 7);
			// 体温异常统计
			int t_monday = 0;
			int t_tuesday = 0;
			int t_wednesday = 0;
			int t_thursday = 0;
			int t_friday = 0;
			int t_saturday = 0;
			int t_sunday = 0;
			List<BPerson> personList = personService
					.findNoDeleteAlarmByTime(new SimpleDateFormat("yyyy-MM-dd").format(mondayDate));
			for (BPerson bPerson : personList) {
				if (bPerson.getLastDetection().after(mondayDate) && bPerson.getLastDetection().before(tuesdayDate)) {
					t_monday++;
				} else if (bPerson.getLastDetection().after(tuesdayDate)
						&& bPerson.getLastDetection().before(wednesdayDate)) {
					t_tuesday++;
				} else if (bPerson.getLastDetection().after(wednesdayDate)
						&& bPerson.getLastDetection().before(tuesdayDateDate)) {
					t_wednesday++;
				} else if (bPerson.getLastDetection().after(tuesdayDateDate)
						&& bPerson.getLastDetection().before(fridayDate)) {
					t_thursday++;
				} else if (bPerson.getLastDetection().after(fridayDate)
						&& bPerson.getCreateTime().before(saturdayDate)) {
					t_friday++;
				} else if (bPerson.getLastDetection().after(saturdayDate)
						&& bPerson.getCreateTime().before(sundayDate)) {
					t_saturday++;
				} else if (bPerson.getLastDetection().after(sundayDate)
						&& bPerson.getLastDetection().before(mondayDateNext)) {
					t_sunday++;
				}
			}
			JSONObject jTemp = new JSONObject();
			jTemp.put("monday", t_monday);
			jTemp.put("tuesday", t_tuesday);
			jTemp.put("wednesday", t_wednesday);
			jTemp.put("thursday", t_thursday);
			jTemp.put("friday", t_friday);
			jTemp.put("saturday", t_saturday);
			jTemp.put("sunday", t_sunday);
			jWeek.put("temp", jTemp);
			// 未佩戴口罩统计
			int m_monday = 0;
			int m_tuesday = 0;
			int m_wednesday = 0;
			int m_thursday = 0;
			int m_friday = 0;
			int m_saturday = 0;
			int m_sunday = 0;
			List<SMaskRecord> maskRecordList = maskRecordService
					.findAlarmByTime(new SimpleDateFormat("yyyy-MM-dd").format(mondayDate));
			for (SMaskRecord maskRecord : maskRecordList) {
				if (maskRecord.getCreateTime().after(mondayDate) && maskRecord.getCreateTime().before(tuesdayDate)) {
					m_monday++;
				} else if (maskRecord.getCreateTime().after(tuesdayDate)
						&& maskRecord.getCreateTime().before(wednesdayDate)) {
					m_tuesday++;
				} else if (maskRecord.getCreateTime().after(wednesdayDate)
						&& maskRecord.getCreateTime().before(tuesdayDateDate)) {
					m_wednesday++;
				} else if (maskRecord.getCreateTime().after(tuesdayDateDate)
						&& maskRecord.getCreateTime().before(fridayDate)) {
					m_thursday++;
				} else if (maskRecord.getCreateTime().after(fridayDate)
						&& maskRecord.getCreateTime().before(saturdayDate)) {
					m_friday++;
				} else if (maskRecord.getCreateTime().after(saturdayDate)
						&& maskRecord.getCreateTime().before(sundayDate)) {
					m_saturday++;
				} else if (maskRecord.getCreateTime().after(sundayDate)
						&& maskRecord.getCreateTime().before(mondayDateNext)) {
					m_sunday++;
				}
			}
			JSONObject jMask = new JSONObject();
			jMask.put("monday", m_monday);
			jMask.put("tuesday", m_tuesday);
			jMask.put("wednesday", m_wednesday);
			jMask.put("thursday", m_thursday);
			jMask.put("friday", m_friday);
			jMask.put("saturday", m_saturday);
			jMask.put("sunday", m_sunday);
			jWeek.put("mask", jMask);

			jObj.put("week", jWeek);
			jObjData.put("data", jObj);
			jObjData.put("code", 200);
			jObjData.put("message", "success");
			// System.out.println(jObj.toString());
			return jObjData;
		} catch (Exception e) {
			jObj.put("code", 500);
			jObj.put("message", "报错啦盆友");
			jObjData.put("data", jObj);
			return jObjData;
		}
	}

	// 获取人员异常和未佩戴口罩的记录
	@PostMapping("/report8")
	public JSONObject report8() throws ParseException {
		return getReportData(8);
	}

	// 获取人员未佩戴口罩的记录
	@PostMapping("/report20")
	public JSONObject report20() {
		JSONObject jObj = new JSONObject();
		JSONObject jObjData = new JSONObject();
		try {
			// 查出所有未正确佩戴口罩的记录
			List<SMaskRecord> maskRecordList = maskRecordService.findAlarmDescTime();
			Map<String, Integer> personCount = new HashMap<>();
			Map<String, JSONObject> recordCount = new HashMap<>();
			JSONObject jMaskRecord = null;
			BPerson person = null;
			String uCode = "";
			for (SMaskRecord maskRecord : maskRecordList) {
				uCode = maskRecord.getUCode();
				if (uCode == null || "".equals(uCode)) // 外来人员
					continue;
				if (personCount.containsKey(uCode)) {
					personCount.put(uCode, personCount.get(uCode) + 1);
				} else {
					personCount.put(uCode, 1);
					jMaskRecord = new JSONObject();
					person = personService.getPersonById(uCode);
					jMaskRecord.put("pId", person.getPId());
					jMaskRecord.put("pName", person.getPName());
					jMaskRecord.put("orgName", person.getOrgName());
					jMaskRecord.put("phone", person.getPPhone());
					jMaskRecord.put("idCard", person.getIdCardNo());
					// jMaskRecord.put("image", person.getPPic());
					jMaskRecord.put("image", maskRecord.getPicCode());
					jMaskRecord.put("time",
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(maskRecord.getCreateTime()));
					jMaskRecord.put("alarm", "未正确佩戴口罩");
					jMaskRecord.put("client", maskRecord.getClientId());
					recordCount.put(uCode, jMaskRecord);
				}
			}
			// 把统计数量的personCount，按照value排序
			// 首先需要得到HashMap中的包含映射关系的视图（entrySet），将entrySet转换为List
			List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(personCount.entrySet()); // 转换为list
			// 重写比较器（此处使用list.sort()排序）
			list.sort(new Comparator<Map.Entry<String, Integer>>() {
				@Override
				public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
					return o2.getValue().compareTo(o1.getValue());
				}
			});
			Map<String, Integer> personCount_new = new LinkedHashMap<>();
			for (int i = 0; i < list.size(); i++) {
				personCount_new.put(list.get(i).getKey(), list.get(i).getValue());
			}
			jObj.put("personCount", JSONObject.fromObject(personCount_new));
			jObj.put("recordCount", JSONObject.fromObject(recordCount));
			// System.out.println(jObj);

			jObjData.put("data", jObj);
			jObjData.put("code", 200);
			jObjData.put("message", "success");
			return jObjData;
		} catch (Exception e) {
			jObj.put("code", 500);
			jObj.put("message", "报错啦盆友");
			jObjData.put("data", jObj);
			return jObjData;
		}
	}

	// 首页获取4张头像
	@PostMapping("/report4")
	public JSONObject report4() throws ParseException {
		return getReportData(4);
	}

	// 首页统计各种人数
	@PostMapping("/count")
	public JSONObject countPerson() throws ParseException {
		JSONObject jObj = new JSONObject();
		// 当前档案人数
		jObj.put("personNum", personService.countNoDelete());
		// 当日检测人数（重复的人员需要去重吗？）
		int detectionNum = temperatureRecordService.countDetectionNum();
		jObj.put("detectionNum", detectionNum);
		// 当日来访人数
		jObj.put("visitorNum", temperatureRecordService.countVisitorNum());
		// 当日检测体温异常人数
		int tempAlarmNum = temperatureRecordService.countTempAlarmNum();
		jObj.put("tempAlarmNum", tempAlarmNum);
		// 当日检测体温正常人数
		jObj.put("tempNotAlarmNum", detectionNum - tempAlarmNum);
		// 当日未佩戴口罩人数
		jObj.put("maskAlarmNum", maskRecordService.countNotMaskToday(""));

		// System.out.println(jObj);
		return jObj;
	}

	// 报表页面获取8张头像，首页获取4张头像的公共方法
	public JSONObject getReportData(int num) {
		JSONObject jOb = new JSONObject();
		List<JSONObject> jobList = new ArrayList<JSONObject>();
		try {
			String todayStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			// 获取当天体温异常的
			int recordCont = 0;
			List<STemperatureRecord> temperatureRecordList = temperatureRecordService.findAlarmToday(todayStr);
			if (temperatureRecordList.size() > 0) {
				for (STemperatureRecord str : temperatureRecordList) {
					JSONObject jTRecord_p = new JSONObject();
					if (str.getUCode() == null || str.getUCode().equals("")) {
						jTRecord_p.put("pName", "外来人员");
					} else {
						jTRecord_p.put("pName", str.getPName());
						jTRecord_p.put("orgName", str.getPerson().getOrgName());
						jTRecord_p.put("phone", str.getPerson().getPPhone());
						jTRecord_p.put("idCard", str.getPerson().getIdCardNo());
					}
					jTRecord_p.put("image", str.getPicCode());
					jTRecord_p.put("temperature", Math.round(str.getTemperature() * 10) * 0.1d);
					jTRecord_p.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(str.getCreateTime()));
					jTRecord_p.put("client", str.getClientName());
					jTRecord_p.put("alarm", "体温异常");
					jTRecord_p.put("type", "tem");
					jobList.add(jTRecord_p);
					recordCont++;
				}

			}
			// 口罩佩戴异常
			List<JSONObject> mRecordList = new ArrayList<>();
			if (recordCont < num) {
				List<SMaskRecord> maskRecordList = maskRecordService.findAlarmByCount(num - recordCont);
				for (SMaskRecord maskRecord : maskRecordList) {
					JSONObject jMRecord_p = new JSONObject();
					if (maskRecord.getUCode() == null || "".equals(maskRecord.getUCode())) { // 外来人员
						jMRecord_p.put("pName", "外来人员");
					} else { // 内部人员
						jMRecord_p.put("pName", maskRecord.getPerson().getPName());
						jMRecord_p.put("orgName", maskRecord.getPerson().getOrgName());
						jMRecord_p.put("phone", maskRecord.getPerson().getPPhone());
						jMRecord_p.put("idCard", maskRecord.getPerson().getIdCardNo());
						jMRecord_p.put("pId", maskRecord.getPerson().getPId());
					}

					// jMRecord_p.put("image",
					// maskRecord.getPerson().getPPic());
					jMRecord_p.put("image", maskRecord.getPicCode());
					jMRecord_p.put("time",
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(maskRecord.getCreateTime()));
					jMRecord_p.put("alarm", "未正确佩戴口罩");
					jMRecord_p.put("client", maskRecord.getClientName());
					jMRecord_p.put("type", "mask");
					jobList.add(jMRecord_p);
				}
			}
			// System.out.println(jObj);
			jOb.put("data", jobList);
			jOb.put("code", 200);
			jOb.put("message", "success");
			return jOb;
		} catch (Exception e) {
			jOb.put("code", 500);
			jOb.put("message", "报错啦盆友");
			jOb.put("data", "");
			return jOb;
		}
	}

	// 获取本周一
	public static Date getThisWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);

		return cal.getTime();
	}

	// 时间加一天的方法
	public static Date dateUpOneDay(Date date, int i) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, i); // 把日期往后增加i天，整数往后推，负数往前移动
		return calendar.getTime(); // 这个时间就是日期往后推一天的结果
	}
}
