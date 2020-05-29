package com.great.manager.action;



import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.great.base.action.BaseAction;
import com.great.base.entity.Message2Page2;
import com.great.manager.entity.BAttendanceTimeSetup;
import com.great.manager.entity.BClient;
import com.great.manager.entity.BPerson;
import com.great.manager.service.AttendanceTimeSetupService;
import com.great.tool.JsonCovert;

import net.sf.json.JSONObject;

/**
 * @author zqq
 * 设置出勤时间
 */
@RestController
@RequestMapping(value = "/attendanceTimeSetupInfo")
public class AttendanceTimeSetupAction extends BaseAction {
	@Autowired
	private AttendanceTimeSetupService attendanceTimeSetupService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	
	
	
	/**
	 * 出勤时间设置
	 * */
	
	@PostMapping("/getClientValueById")
	public Message2Page2 getClientValueById(BAttendanceTimeSetup timeSetup) {
		String aId="1";
		//判断当前数据库是否有值
		BAttendanceTimeSetup baValue = attendanceTimeSetupService.getAttendanceValueById(aId);
		//没有值就默认给一个参数值
		if(baValue==null) {
			timeSetup.setAId("1");
			timeSetup.setWorkTime("9:00");
			timeSetup.setEndTime("18:00");
			timeSetup.setUpdateTime(new Date());
			attendanceTimeSetupService.save(timeSetup);
		}
		//查询详细
		BAttendanceTimeSetup battValue = attendanceTimeSetupService.getAttendanceValueById(aId);
		JSONObject jsonObject = JSONObject.fromObject(battValue);
		jsonObject = JsonCovert.filterNull(jsonObject);
		return new Message2Page2(true, 200, "", jsonObject);
	}
	

	/**
	 * 设置出勤时间
	 */

	@PostMapping("/update")
	public Message2Page2 update(BAttendanceTimeSetup timeSetup) {
		// 获取上班时间和下班时间
		String workTime = this.getPageParameter("workTime");
		String endTime = this.getPageParameter("endTime");
		//修改时间
		timeSetup.setAId("1");
		timeSetup.setWorkTime(workTime);
		timeSetup.setEndTime(endTime);
		timeSetup.setUpdateTime(new Date());
		attendanceTimeSetupService.update(timeSetup);
		return new Message2Page2(true, 200, null);
	}


}
