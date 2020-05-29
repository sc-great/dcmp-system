package com.great.manager.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.great.base.action.BaseAction;
import com.great.base.entity.Message2Page;
import com.great.manager.entity.CTactics;
import com.great.manager.entity.CTacticsDate;
import com.great.manager.service.TacticsDateService;
import com.great.manager.service.TacticsService;
import com.great.resource.StaticData;
import com.great.tool.JsonCovert;
import com.great.tool.PageBean;
import com.great.tool.ReplaceUtil;
import com.great.tool.UUID;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

@RestController
@RequestMapping(value = "/tactics")
public class TacticsAction extends BaseAction {
	@Autowired
	private TacticsService tacticsService;
	@Autowired
	private TacticsDateService tacticsDateService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 获取策略信息列表
	 * 
	 * @param page
	 *            当前页
	 * @param limit
	 *            每页显示记录数
	 * @param deviceName
	 *            设备名称
	 * @return
	 */
	@GetMapping("/getPage")
	public JSONObject getListByPageBean(@RequestParam Integer page, @RequestParam Integer limit,
			@RequestParam(required = false, defaultValue = "") String areaId,
			@RequestParam(required = false, defaultValue = "") String tName) {
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPageNumber(page);
		pageBean.setLimit(limit);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("tName", tName);
		pageBean.setMap(param);
		tacticsService.getResult(pageBean);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject returnObject = JSONObject.fromObject(pageBean, jsonConfig);
		returnObject = JsonCovert.filterNull(returnObject);
		return returnObject;
	}

	/**
	 * 添加策略
	 * 
	 * @param sad
	 *            对象接收前端提交属性
	 * @return
	 */
	@PostMapping("/add")
	public Message2Page add(@RequestParam String tName, @RequestParam(value = "dataList[]") String[] dataList) {
		String name = tName;
		if (StringUtils.isEmpty(name)) {
			return new Message2Page(false, "400", "请输入策略名称");
		}
		if (dataList.length == 0) {
			return new Message2Page(false, "400", "请选择日期");
		}
		if (!StringUtils.isEmpty(name)) {
			CTactics device = tacticsService.getByName(name);
			if (!StringUtils.isEmpty(device)) {
				return new Message2Page(false, "400", "策略名称已存在");
			}
		}
		if (!StringUtils.isEmpty(name)) {
			CTactics device = tacticsService.getByName(ReplaceUtil.reSpecStr(tName));
			if (!StringUtils.isEmpty(device)) {
				return new Message2Page(false, "400", "策略名称已存在");
			}
		}
		CTactics tactics = new CTactics();
		tactics.settId(UUID.randomUUID());
		tactics.setIsdeleted(false);
		tactics.setStatus(StaticData.STATUS_NORMAL);
		tactics.settName(ReplaceUtil.reSpecStr(tName));
		tactics.setCreateBy(this.getLoginUser().getUserName());
		tactics.setCreateTime(new Date());
		tactics.setUpdateBy(this.getLoginUser().getUserName());
		tactics.setUpdateTime(new Date());
		tacticsService.save(tactics);

		// String dateList = this.getPageParameter("dateList");
		// if (!StringUtils.isEmpty(dateList)) {
		// String[] dates = dateList.split(",");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		for (String time : dataList) {
			CTacticsDate tacticsDate = new CTacticsDate();

			try {
				tacticsDate.settId(tactics.gettId());
				tacticsDate.setTdId(UUID.randomUUID());
				tacticsDate.setCreateBy(this.getLoginUser().getUserName());
				tacticsDate.setCreateTime(new Date());
				tacticsDate.setUpdateTime(new Date());
				tacticsDate.setUpdateBy(this.getLoginUser().getUserName());
				tacticsDate.setTdDate(sdf.parse(time));
				tacticsDate.setIsdeleted(false);

				tacticsService.save(tacticsDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		// }
		String msg = "添加策略：" + tactics.gettName();
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", tactics.gettId());
	}

	/**
	 * 根据id获取吧策略信息
	 * 
	 * @param id
	 *            策略id
	 * @return
	 */
	@PostMapping("/getById")
	public Message2Page getById(@RequestParam String id) {
		logger.debug("接收到参数id：" + id);
		CTactics tactics = tacticsService.get(CTactics.class, id);
		List<CTacticsDate> dates = tacticsDateService.getBytId(tactics.gettId());
		List<String> times = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (CTacticsDate date : dates) {

			times.add(sdf.format(date.getTdDate()));
		}
		JsonConfig jsonConfig = new JsonConfig();
		JSONObject jsonObject = JSONObject.fromObject(tactics, jsonConfig);
		jsonObject.put("dateList", times.toString());
		jsonObject = JsonCovert.filterNull(jsonObject);
		return new Message2Page(true, "200", "", jsonObject);
	}

	/**
	 * 更新策略信息
	 * 
	 * @param sad
	 *            对象接收前端提交更新数据
	 * @return
	 */
	@PostMapping("/update")
	public Message2Page update(@RequestParam String tId, @RequestParam String tName,
			@RequestParam(value = "dataList[]") String[] dataList) {
		if (StringUtils.isEmpty(tName)) {
			return new Message2Page(false, "400", "请输入策略名称");
		}
		if (dataList.length == 0) {
			return new Message2Page(false, "400", "请选择日期");
		}
		CTactics tacticsSource = tacticsService.get(CTactics.class, tId);
		String name = tName;
		if (!StringUtils.isEmpty(name)) {
			CTactics device = tacticsService.getByName(name);
			if (!StringUtils.isEmpty(device) && !device.gettId().equals(tId)) {
				return new Message2Page(false, "400", "策略名称已存在");
			}
		}
		if (!StringUtils.isEmpty(name)) {
			CTactics device = tacticsService.getByName(ReplaceUtil.reSpecStr(tName));
			if (!StringUtils.isEmpty(device) && !device.gettId().equals(tId)) {
				return new Message2Page(false, "400", "策略名称已存在");
			}
		}
		tacticsSource.settName(ReplaceUtil.reSpecStr(tName));
		tacticsSource.setUpdateBy(this.getLoginUser().getUserName());
		tacticsSource.setUpdateTime(new Date());
		tacticsService.update(tacticsSource);

		// String dateList = this.getPageParameter("dateList");
		// if (!StringUtils.isEmpty(dateList)) {
		// 清除策略时间
		tacticsDateService.deleteByTId(tacticsSource.gettId());
		// 重新添加
		// String[] dates = dateList.split(",");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		for (String time : dataList) {
			CTacticsDate tacticsDate = new CTacticsDate();

			try {
				tacticsDate.settId(tacticsSource.gettId());
				tacticsDate.setTdId(UUID.randomUUID());
				tacticsDate.setCreateBy(this.getLoginUser().getUserName());
				tacticsDate.setCreateTime(new Date());
				tacticsDate.setTdDate(sdf.parse(time));
				tacticsDate.setIsdeleted(false);
				tacticsService.save(tacticsDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		// }

		String msg = "更新策略：" + tacticsSource.gettName();
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	/**
	 * 修改策略状态
	 * 
	 * @param ids
	 *            策略id数组
	 * @param status
	 *            策略状态
	 * @return
	 */
	@PostMapping("/changeStatus")
	public Message2Page changeStatus(@RequestParam(value = "ids[]") String[] ids, @RequestParam String status) {
		tacticsService.changeStatus(ids, status);
		String Name = "";
		for (int i = 0; i < ids.length; i++) {
			CTactics sad = tacticsService.get(CTactics.class, ids[i]);
			Name += sad.gettName() + ",";
		}
		StringBuffer msg = new StringBuffer();
		msg.append(status.equals("1") ? "停用" : "启用").append("策略：").append(Name);
		saveLogB(msg.toString(), StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	/**
	 * 逻辑删除策略
	 * 
	 * @param ids
	 *            策略id数组
	 * @return
	 */
	@PostMapping("/del")
	public Message2Page deleteCon(@RequestParam(value = "ids[]") String[] ids) {
		String sname = "";
		for (int i = 0; i < ids.length; i++) {
			CTactics sad = tacticsService.get(CTactics.class, ids[i]);
			sname += sad.gettName() + ",";
		}
		tacticsService.deleteByIds(CTactics.class, false, getLoginUser().getUserName(), ids);
		logger.debug("删除策略编号 vilIds：" + Arrays.asList(ids).toString());
		String msg = "删除策略：" + sname;
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page(true, "200", null);
	}

	/**
	 * 获取策略信息,下拉菜单
	 * 
	 * @return
	 */
	@PostMapping("/gettacticsList")
	public List<Map<String, String>> getAllList() {
		logger.debug("获取策略列表");
		List<CTactics> tactics = tacticsService.findAllNoDelete(CTactics.class);
		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		if (tactics != null && tactics.size() > 0) {
			for (CTactics bs : tactics) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", bs.gettId());
				map.put("name", bs.gettName());
				maps.add(map);
			}
		}
		return maps;
	}
}
