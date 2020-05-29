package com.great.manager.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.great.base.action.BaseAction;
import com.great.base.entity.Message2Page2;
import com.great.manager.entity.BAttendanceTimeSetup;
import com.great.manager.entity.BLeave;
import com.great.manager.entity.BPerson;
import com.great.manager.entity.CTacticsDate;
import com.great.manager.service.AttendanceTimeSetupService;
import com.great.manager.service.LeaveService;
import com.great.manager.service.PersonService;
import com.great.manager.service.TacticsDateService;
import com.great.manager.service.TemperatureRecordService;
import com.great.system.entity.SDictionaryValEntity;
import com.great.tool.ExcelBean;
import com.great.tool.ExcelUtil;
import com.great.tool.JsonCovert;
import com.great.tool.PageBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * 考勤统计
 * 
 * @author LM
 *
 */
@RestController
@RequestMapping(value = "/api")
public class AttendanceRecordAction extends BaseAction {

	@Autowired
	private PersonService personService;
	@Autowired
	private TemperatureRecordService temperatureRecordService;

	@RequestMapping("/getRecord")
	public Message2Page2 getAttendanceRecord(String userName, String startTime, String endTime, String org) {
		JSONObject jsonObject = new JSONObject();
		// 按条件找出所有人
		List<BPerson> personList = personService.getPersonByOrgAndLikeName(org, userName);
		// 按条件找出所有考勤
		JSONObject attendanceList = temperatureRecordService.getRecordByTimeLikeName(userName, startTime, endTime, org);
		jsonObject.put("personList", personList);
		jsonObject.put("attendanceList", attendanceList);
		return new Message2Page2(true, 200, "", JsonCovert.filterNull(jsonObject));
	}

	@Autowired
	private AttendanceTimeSetupService attendanceTimeSetupService;
	@Autowired
	private TacticsDateService tacticsDateService;
	@Autowired
	private LeaveService leaveService;
	
	@RequestMapping("/getRecordToTable")
	public JSONObject getAttendanceRecordToTable(String userName, String startTime, String endTime, String org) {
		if (startTime == null || "".equals(startTime))
			return JSONObject.fromObject(new PageBean());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		endTime = endTime == null || "".equals(endTime) ? sdf.format(new Date()) : endTime;
		// 第一步，数据查询------------------------------------------------------------------------------------------------------------------
		// 查询所有的假期
		List<CTacticsDate> cTacticsDate = tacticsDateService.findByTdDate(startTime, endTime);
		List<String> cDateList = new ArrayList<>();
		for (CTacticsDate c : cTacticsDate) {
			cDateList.add(sdf.format(c.getTdDate()));
		}
		// 条件查询所有的请假和调休
		Map<String, Map<String, String>> leaveMap = leaveService.findByParam(org, startTime, endTime);
		// 按条件找出所有人
		List<BPerson> personList = personService.getPersonByOrgAndLikeName(org, userName);
		// 查出所有的日期
		int num = getDiffer(startTime, endTime) + 1;
		String date = "";
		List<String> dateList = new ArrayList<>(); // 日期集合
		// excel的表头对象
		String[] headerArray = new String[num + 3]; // 加上3为了显示序号、姓名、部门
		headerArray[0] = "序号";
		headerArray[1] = "姓名";
		headerArray[2] = "部门";
		for (int i = 0; i < num; i ++) {
			date = dateUpOneDay(startTime, i); // 加一天
			dateList.add(date); // 添加到日期集合
			headerArray[i + 3] = date; // 添加到表头
		}
		
		// 按条件找出所有考勤
		Map<String, String> param = new HashMap<>();
		param.put("userName", userName);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("num", num + "");
		param.put("org", org);
		Map<String, Map<String, String>> attendanceMap = temperatureRecordService.getRecordByTimeLikeName2(param);
		
		// 第二步，数据处理------------------------------------------------------------------------------------------------------------------ 
		List<JSONObject> list = new ArrayList<>();
		JSONObject jsonList = null;
		
		int index = 0;
		String pid = "";
		Map<String, String> a_map = null;
		String max_str = "";
		String min_str = "";
		for (BPerson b : personList) {
			jsonList = new JSONObject();

			pid = b.getPId();
			jsonList.put("index", ++ index);
			jsonList.put("id", pid);
			jsonList.put("name", b.getPName());
			jsonList.put("org", b.getOrgName());
			
			for (String d : dateList) {
				// 考勤
				if (attendanceMap.containsKey(pid) && attendanceMap.get(pid).containsKey(d + ".max")) {
					a_map = attendanceMap.get(pid);
					max_str = a_map.get(d + ".max");
					min_str = a_map.get(d + ".min");
					if (min_str.equals(max_str))
						max_str = "--";
					jsonList.put(d, min_str + "<br/>" + max_str);
				} else if (leaveMap.containsKey(pid) && leaveMap.get(pid).containsKey(d)) { // 请假和调休
					jsonList.put(d, leaveMap.get(pid).get(leaveMap.get(pid).get(d)));
				} else if (cDateList.contains(d)) { // 放假
					jsonList.put(d, "放假");
				} else {
					jsonList.put(d, "--");
				}
			}
			list.add(jsonList);
		}

		PageBean pageBean = new PageBean();
		pageBean.setData(list);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject returnObject = JSONObject.fromObject(pageBean, jsonConfig);
		returnObject = JsonCovert.filterNull(returnObject);
		return returnObject;
	}
	
	@ResponseBody
	@RequestMapping("exportRecordToExcel")
	public void exportRecordToExcel(String userName, String startTime, String endTime, String org, HttpServletResponse response) throws Exception {
		if (startTime == null || "".equals(startTime))
			return;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		endTime = endTime == null || "".equals(endTime) ? sdf.format(new Date()) : endTime;

		// 查询所有的假期
		List<CTacticsDate> cTacticsDate = tacticsDateService.findByTdDate(startTime, endTime);
		List<String> cDateList = new ArrayList<>();
		for (CTacticsDate c : cTacticsDate) {
			cDateList.add(sdf.format(c.getTdDate()));
		}
		// 条件查询所有的请假和调休
		Map<String, Map<String, String>> leaveMap = leaveService.findByParam(org, startTime, endTime);
		// 按条件找出所有人
		List<BPerson> personList = personService.getPersonByOrgAndLikeName(org, userName);
		// 查出所有的日期
		int num = getDiffer(startTime, endTime) + 1;
		String date = "";
		List<String> dateList = new ArrayList<>(); // 日期集合
		// excel的表头对象
		String[] headerArray = new String[num + 3]; // 加上3为了显示序号、姓名、部门
		headerArray[0] = "序号";
		headerArray[1] = "姓名";
		headerArray[2] = "部门";
		for (int i = 0; i < num; i ++) {
			date = dateUpOneDay(startTime, i); // 加一天
			dateList.add(date); // 添加到日期集合
			headerArray[i + 3] = date; // 添加到表头
		}
		
		// 按条件找出所有考勤
		Map<String, String> param = new HashMap<>();
		param.put("userName", userName);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("num", num + "");
		param.put("org", org);
		Map<String, Map<String, String>> attendanceMap = temperatureRecordService.getRecordByTimeLikeName2(param);
		// 用于传递给excel的对象
		Map<Integer, List<String>> memberMap = new TreeMap<>();
		List<String> memberList = null;
		
		int index = 0;
		String pid = "";
		Map<String, String> a_map = null;
		String max_str = "";
		String min_str = "";
		for (BPerson b : personList) {
			memberList = new ArrayList<>();

			pid = b.getPId();
			memberList.add((++ index) + "");
			memberList.add(b.getPName());
			memberList.add(b.getOrgName());
			
			for (String d : dateList) {
				// 考勤
				if (attendanceMap.containsKey(pid) && attendanceMap.get(pid).containsKey(d + ".max")) {
					a_map = attendanceMap.get(pid);
					max_str = a_map.get(d + ".max");
					min_str = a_map.get(d + ".min");
					if (min_str.equals(max_str))
						max_str = "--";
					memberList.add(min_str + " / " + max_str);
				} else if (leaveMap.containsKey(pid) && leaveMap.get(pid).containsKey(d)) { // 请假和调休
					memberList.add(leaveMap.get(pid).get(leaveMap.get(pid).get(d)));
				} else if (cDateList.contains(d)) { // 放假
					memberList.add("放假");
				} else {
					memberList.add("--");
				}
			}
			memberMap.put(index, memberList);
		}
		// 生成和导出excel表格的方法
		String title = "人员考勤记录（" + startTime + "to" + endTime +"）";
		createAndExportToExcel(title, memberMap, headerArray, response);
	}
	
	@RequestMapping("attendanceReport")
	public JSONObject attendanceReport(String userName, String startTime, String endTime, String org, HttpServletResponse response) throws Exception {
		if (startTime == null || "".equals(startTime))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		endTime = endTime == null || "".equals(endTime) ? sdf.format(new Date()) : endTime;
		// 第一步，数据查询------------------------------------------------------------------------------------------------------------------
		// 1.1  查出上下班时间
		BAttendanceTimeSetup timeSetup = attendanceTimeSetupService.get(BAttendanceTimeSetup.class, "1");
		int sta = momentStringToLong(timeSetup.getWorkTime());
		int end = momentStringToLong(timeSetup.getEndTime());
		// 查询所有的假期
		List<CTacticsDate> cTacticsDate = tacticsDateService.findByTdDate(startTime, endTime);
		List<String> cDateList = new ArrayList<>();
		for (CTacticsDate c : cTacticsDate) {
			cDateList.add(sdf.format(c.getTdDate()));
		}
		// 1.2  条件查询所有的请假和调休，并处理数据
		Map<String, Map<String, String>> leaveMap = leaveService.findByParam(org, startTime, endTime);
		// 1.3  按条件找出所有人
		List<BPerson> personList = personService.getPersonByOrgAndLikeName(org, userName);
		// 1.4  查出所有的日期
		int num = getDiffer(startTime, endTime) + 1;
		List<String> dateList = new ArrayList<>(); // 日期集合
		for (int i = 0; i < num; i ++) {
			dateList.add(dateUpOneDay(startTime, i)); // 加一天  // 添加到日期集合
		}
		// 1.5  按条件找出所有考勤
		Map<String, String> param = new HashMap<>();
		param.put("userName", userName);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("num", num + "");
		param.put("org", org);
		Map<String, Map<String, String>> attendanceMap = temperatureRecordService.getRecordByTimeLikeName2(param);
		// 第二步，数据处理------------------------------------------------------------------------------------------------------------------
		List<JSONObject> jOList = new ArrayList<>(); // 用于返回的对象（里面包含每一行的人员对象）
		JSONObject jPList = null; // 用于返回的人员对象，一个对象即为表格中的一行
		int index = 0; // 序号，用于排序
		String pid = "";
		Map<String, String> a_map = null;
		String max_str = "";
		String min_str = "";
		// 用于统计的数据
		int holiday = 0, // 假期
				takeOff = 0, // 请假
				changeOff = 0, // 换休
				beLate = 0, // 迟到
				leaveEarly = 0, // 早退
				absent = 0; // 旷工
		for (BPerson b : personList) {
			// 针对每一个人，先初始化数据
			jPList = new JSONObject();
			holiday = 0; // 假期
			takeOff = 0; // 请假
			changeOff = 0; // 换休
			beLate = 0; // 迟到
			leaveEarly = 0; // 早退
			absent = 0; // 旷工
			
			pid = b.getPId();
			jPList.put("index", ++ index);
			jPList.put("id", b.getPId());
			jPList.put("name", b.getPName());
			jPList.put("org", b.getOrgName() == null || "".equals(b.getOrgName()) ? "--" : b.getOrgName());
			
			for (String d : dateList) {
				if (cDateList.contains(d)) { // 假期
					holiday ++;
				} else if (leaveMap.containsKey(pid) && leaveMap.get(pid).containsKey(d)) { // 请假和调休
					if (leaveMap.get(pid).get(d).equals("L01")) // 请假
						takeOff ++;
					if (leaveMap.get(pid).get(d).equals("L02")) // 换休
						changeOff ++;
				} else if (attendanceMap.containsKey(pid) && attendanceMap.get(pid).containsKey(d + ".max")) { // 打卡
					a_map = attendanceMap.get(pid);
					max_str = a_map.get(d + ".max");
					min_str = a_map.get(d + ".min");
					if (min_str.equals(max_str)) // 迟到
						max_str = "";
					if (momentStringToLong(min_str) > sta) // 早退
						beLate ++;
					if (!max_str.equals("") && momentStringToLong(max_str) < end)
						leaveEarly ++;
				} else { // 旷工
					absent ++;
				}
			}
			// 添加数据到对象
			jPList.put("holiday", holiday);
			jPList.put("takeOff", takeOff);
			jPList.put("changeOff", changeOff);
			jPList.put("beLate", beLate);
			jPList.put("leaveEarly", leaveEarly);
			jPList.put("absent", absent);
			jOList.add(jPList);
		}
		
		PageBean pageBean = new PageBean();
		pageBean.setData(jOList);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject returnObject = JSONObject.fromObject(pageBean, jsonConfig);
		returnObject = JsonCovert.filterNull(returnObject);
		return returnObject;
	}
	
	@ResponseBody
	@RequestMapping("exportReportToExcel")
	public void exportReportToExcel(String userName, String startTime, String endTime, String org, HttpServletResponse response) throws Exception {
		if (startTime == null || "".equals(startTime))
			return;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		endTime = endTime == null || "".equals(endTime) ? sdf.format(new Date()) : endTime;
		// 第一步，数据查询------------------------------------------------------------------------------------------------------------------
		// 1.1  查出上下班时间
		BAttendanceTimeSetup timeSetup = attendanceTimeSetupService.get(BAttendanceTimeSetup.class, "1");
		int sta = momentStringToLong(timeSetup.getWorkTime());
		int end = momentStringToLong(timeSetup.getEndTime());
		// 查询所有的假期
		List<CTacticsDate> cTacticsDate = tacticsDateService.findByTdDate(startTime, endTime);
		List<String> cDateList = new ArrayList<>();
		for (CTacticsDate c : cTacticsDate) {
			cDateList.add(sdf.format(c.getTdDate()));
		}
		// 1.2  条件查询所有的请假和调休，并处理数据
		Map<String, Map<String, String>> leaveMap = leaveService.findByParam(org, startTime, endTime);
		// 1.3  按条件找出所有人
		List<BPerson> personList = personService.getPersonByOrgAndLikeName(org, userName);
		// 1.4  查出所有的日期
		int num = getDiffer(startTime, endTime) + 1;
		List<String> dateList = new ArrayList<>(); // 日期集合
		for (int i = 0; i < num; i ++) {
			dateList.add(dateUpOneDay(startTime, i)); // 加一天  // 添加到日期集合
		}
		// 1.5  按条件找出所有考勤
		Map<String, String> param = new HashMap<>();
		param.put("userName", userName);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("num", num + "");
		param.put("org", org);
		Map<String, Map<String, String>> attendanceMap = temperatureRecordService.getRecordByTimeLikeName2(param);
		// 第二步，数据处理------------------------------------------------------------------------------------------------------------------
		// 用于传递给excel的对象
		Map<Integer, List<String>> memberMap = new TreeMap<>();
		List<String> memberList = null;
		int index = 0; // 序号，用于排序
		String pid = "";
		Map<String, String> a_map = null;
		String max_str = "";
		String min_str = "";
		// 用于统计的数据
		int holiday = 0, // 假期
				takeOff = 0, // 请假
				changeOff = 0, // 换休
				beLate = 0, // 迟到
				leaveEarly = 0, // 早退
				absent = 0; // 旷工
		for (BPerson b : personList) {
			// 针对每一个人，先初始化数据
			memberList = new ArrayList<>();
			holiday = 0; // 假期
			takeOff = 0; // 请假
			changeOff = 0; // 换休
			beLate = 0; // 迟到
			leaveEarly = 0; // 早退
			absent = 0; // 旷工
			
			pid = b.getPId();
			memberList.add(++ index + "");
			memberList.add(b.getPName());
			memberList.add(b.getOrgName() == null || "".equals(b.getOrgName()) ? "--" : b.getOrgName());
			
			for (String d : dateList) {
				if (cDateList.contains(d)) { // 假期
					holiday ++;
				} else if (leaveMap.containsKey(pid) && leaveMap.get(pid).containsKey(d)) { // 请假和调休
					if (leaveMap.get(pid).get(d).equals("L01")) // 请假
						takeOff ++;
					if (leaveMap.get(pid).get(d).equals("L02")) // 换休
						changeOff ++;
				} else if (attendanceMap.containsKey(pid) && attendanceMap.get(pid).containsKey(d + ".max")) { // 打卡
					a_map = attendanceMap.get(pid);
					max_str = a_map.get(d + ".max");
					min_str = a_map.get(d + ".min");
					if (min_str.equals(max_str)) // 迟到
						max_str = "";
					if (momentStringToLong(min_str) > sta) // 早退
						beLate ++;
					if (!max_str.equals("") && momentStringToLong(max_str) < end)
						leaveEarly ++;
				} else { // 旷工
					absent ++;
				}
			}
			// 添加数据到对象
			memberList.add(holiday + "");
			memberList.add(takeOff + "");
			memberList.add(changeOff + "");
			memberList.add(beLate + "");
			memberList.add(leaveEarly + "");
			memberList.add(absent + "");
			memberMap.put(index, memberList);
		}

		// excel的表头对象
		String[] headerArray = {"序号", "姓名", "部门", "假期", "请假", "换休", "迟到", "早退", "旷工"};
		// 生成和导出excel表格的方法
		String title = "人员考勤统计（" + startTime + "to" + endTime +"）";
		createAndExportToExcel(title, memberMap, headerArray, response);
	}
	
	// 字符串时刻转Long，用于比较大小
	public int momentStringToLong(String moment) {
		if (moment.indexOf(".") != -1)
			moment = moment.split("\\.")[0];
		String[] m_array = moment.split(":");
		int h = Integer.parseInt(m_array[0]) * 60 * 60;
		int m = Integer.parseInt(m_array[1]) * 60;
		int s = 0;
		if (m_array.length == 3)
			s = Integer.parseInt(m_array[2]);
		return h + m + s;
	}
	
	// 获取两个时间相隔的天数
	public int getDiffer(String startTime, String endTime) {
		// 设置转换的日期格式
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 得到相差的天数 betweenDate
	    long start = 0, end = 0;
		try {
			start = sdf.parse(startTime).getTime();
			if (endTime == null && "".equals(endTime))
	        	end = sdf.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).getTime();
			else
				end = sdf.parse(endTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Integer.parseInt((end - start) / (60 * 60 * 24 * 1000) + "");
	}
	
	// 时间加一天的方法
	public String dateUpOneDay(String date, int i) {
		Calendar calendar = new GregorianCalendar();
		try {
			calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calendar.add(calendar.DATE, i); // 把日期往后增加i天，整数往后推，负数往前移动
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()); // 这个时间就是日期往后推一天的结果
	}
	
	// 生成和导出excel表格的方法
	public void createAndExportToExcel(String title, Map<Integer, List<String>> memberMap, String[] headerArray, HttpServletResponse response) throws Exception {
		// Excel表格
		ExcelBean excelBean = new ExcelBean();
		String nowTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String t = title.split("（")[0];
		excelBean.setFileName(t + nowTime + ".xls"); // 文件名
		excelBean.setSheetName(t); // sheet名
		excelBean.setTitle(title); // 标题
		excelBean.setHeader(headerArray); // 表头
		excelBean.setMemberMap(memberMap); // 内容
				
		// ================================== 将Excel文件生成到根目录 ===========================================
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		File path = new File(request.getSession().getServletContext().getRealPath("") + "/download/");
		if (!path.exists())
			path.mkdirs();
		String filePath = path.getPath() + "\\" + excelBean.getFileName(); // 路径
		excelBean.setPath(filePath); // 路径
		// 生成Excel文件
		ExcelUtil.createExcel(excelBean);
		// ================================== 下载文件 ========================================================
		// 告诉浏览器以附件形式下载
		// 不同浏览器的编码不同，对中文进行编码，下载时输出的名称是文件名
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(excelBean.getFileName().getBytes("utf-8"), "ISO8859-1"));
		// 获取文件的mimetype，如123.txt，它的mimetype是txt，那么我们下载时，就以txt形式下载
//		String mimiType = "xls";
//		String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"; // xlsx
		String contentType = "application/vnd.ms-excel"; // xls
		// 设置相应的mimetype
		response.setContentType(contentType);
		
		// 获取response的输出流，用来输出文件
		ServletOutputStream out = null;
		FileInputStream fis = null;
		try {
			out = response.getOutputStream();
			fis = new FileInputStream(new File(excelBean.getPath()));
			byte[] bs = new byte[1024];
			int len;
			while ((len = fis.read(bs)) != -1) {
				out.write(bs, 0, len);
			}
			fis.close();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// 删除临时文件
		new File(filePath).delete();
	}
	
	/*
		@RequestMapping("/getRecordToTable")
	public JSONObject getAttendanceRecordToTable(String userName, String startTime, String endTime, String org) {
		if (startTime == null || "".equals(startTime))
			return JSONObject.fromObject(new PageBean());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		endTime = endTime == null || "".equals(endTime) ? sdf.format(new Date()) : endTime;
		// 按条件找出所有人
		List<BPerson> personList = personService.getPersonByOrgAndLikeName(org, userName);
		// 按条件找出所有考勤
		JSONObject attendanceList = temperatureRecordService.getRecordByTimeLikeName(userName, startTime, endTime, org);
		
		List<JSONArray> jsonObject = (List<JSONArray>) attendanceList.get("jsonObject");
		List<String> jsonDate = (List<String>) attendanceList.get("jsonDate");
		
		List<JSONObject> list = new ArrayList<>();
		JSONObject jsonList = null;
		int index = 0;
		List<String> pId_list = new ArrayList<>();
		for (JSONArray obj : jsonObject) {
			pId_list.add(obj.get(0).toString());
		}
		for (BPerson b : personList) {
			jsonList = new JSONObject();

			jsonList.put("index", ++ index);
			jsonList.put("id", b.getPId());
			jsonList.put("name", b.getPName());
			
			if (!pId_list.contains(b.getPId())) {
				for (String s : jsonDate) {
					jsonList.put(s, "--<br/>--");
				}
				list.add(jsonList);
				continue;
			}

			Date max = null;
			Date min = null;
			String max_str = "";
			String min_str = "";
			for (JSONArray obj : jsonObject) {
				for (int i = 2; i < obj.size(); i = i + 2) {
					if (obj.get(0).equals(b.getPId())) {
						max = (Date) JSONObject.toBean((JSONObject) obj.get(i), Date.class);
						if (max == null) {
							max_str = "--";
						} else {
							max_str = sdf2.format(max);
						}
						min = (Date) JSONObject.toBean((JSONObject) obj.get(i + 1), Date.class);
						if (min == null) {
							min_str = max_str;
							max_str = "--";
						} else {
							min_str = sdf2.format(min);
						}
						if (min_str.equals(max_str))
							max_str = "--";
						jsonList.put(jsonDate.get(i / 2 - 1), min_str + "<br/>" + max_str);
					}
				}
			}
			list.add(jsonList);
		}

		PageBean pageBean = new PageBean();
		pageBean.setData(list);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject returnObject = JSONObject.fromObject(pageBean, jsonConfig);
		returnObject = JsonCovert.filterNull(returnObject);
		return returnObject;
	}
	
	// 查询记录导出为Excel
	@ResponseBody
	@RequestMapping("exportRecordToExcel")
	public void exportRecordToExcel(String userName, String startTime, String endTime, String org, HttpServletResponse response) throws Exception {
		if (startTime == null || "".equals(startTime))
			return;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		
		endTime = endTime == null || "".equals(endTime) ? sdf.format(new Date()) : endTime;
		
		// 按条件找出所有人
		List<BPerson> personList = personService.getPersonByOrgAndLikeName(org, userName);
		// 按条件找出所有考勤
		JSONObject attendanceList = temperatureRecordService.getRecordByTimeLikeName(userName, startTime, endTime, org);
		
		List<JSONArray> jsonObject = (List<JSONArray>) attendanceList.get("jsonObject");
		List<String> jsonDate = (List<String>) attendanceList.get("jsonDate");
		
		Map<Integer, List<String>> memberMap = new TreeMap<>();
		List<String> memberList = null;
		
		List<String> pId_list = new ArrayList<>();
		for (JSONArray obj : jsonObject) {
			pId_list.add(obj.get(0).toString());
		}
		int index = 0;
		for (BPerson b : personList) {
			memberList = new ArrayList<>();
			memberList.add((index + 1) + "");
			memberList.add(b.getPName());
			
			if (!pId_list.contains(b.getPId())) {
				for (String s : jsonDate) {
					memberList.add("--");
					memberList.add("--");
				}
				memberMap.put(index ++, memberList);
				continue;
			}
			
			Date max = null;
			Date min = null;
			String max_str = "";
			String min_str = "";
			for (JSONArray obj : jsonObject) {
				for (int i = 2; i < obj.size(); i = i + 2) {
					if (obj.get(0).equals(b.getPId())) {
						max = (Date) JSONObject.toBean((JSONObject) obj.get(i), Date.class);
						if (max == null) {
							max_str = "--";
						} else {
							max_str = sdf2.format(max);
						}
						min = (Date) JSONObject.toBean((JSONObject) obj.get(i + 1), Date.class);
						if (min == null) {
							min_str = max_str;
							max_str = "--";
						} else {
							min_str = sdf2.format(min);
						}
						if (min_str.equals(max_str))
							max_str = "--";
						memberList.add(min_str);
						memberList.add(min_str);
					}
				}
			}
			memberMap.put(index, memberList);
		}
		int size = jsonDate.size() * 2; // 乘以2是为了分开显示上下班打卡
		String[] headerArray = new String[size + 2]; // 加上2是为了显示序号和姓名
		headerArray[0] = "序号";
		headerArray[1] = "姓名";
		int m = 0;
		for (int i = 2; i < size; i = i + 2) {
			headerArray[i] = jsonDate.get(m) + "上班";
			headerArray[i + 1] = jsonDate.get(m) + "下班";
			m ++;
		}
		// 生成和导出excel表格的方法
		String title = "人员考勤记录（" + startTime + "to" + endTime +"）";
		createAndExportToExcel(title, memberMap, headerArray, response);
	}
	 */
}
