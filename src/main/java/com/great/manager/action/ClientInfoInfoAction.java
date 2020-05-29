package com.great.manager.action;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
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
import com.great.base.entity.Message2Page2;
import com.great.manager.entity.BCampusHierarchy;
import com.great.manager.entity.BClient;
import com.great.manager.entity.BPerson;
import com.great.manager.service.ClientInfoService;
import com.great.resource.StaticData;
import com.great.system.entity.SUserEntity;
import com.great.tool.JsonCovert;
import com.great.tool.PageBean;
import com.great.tool.ReflectCommon;
import com.great.tool.ReplaceUtil;
import com.great.tool.UUID;


/**
 * @author zqq
 *
 */
@RestController
@RequestMapping(value = "/clientInfo")
public class ClientInfoInfoAction extends BaseAction {
	@Autowired
	private ClientInfoService clientInfoService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 获取客户端信息分页
	 * 
	 * @param page
	 *            当前页
	 * @param limit
	 *            每页显示记录数
	 * @param startTime
	 *            查询开始时间
	 * @param endTime
	 *            查询结束时间
	 * @param muName
	 *            人员名称
	 * @return JSONObject 返回人员分页对象，需要去掉Null转换为""
	 */
	@GetMapping("/getPage")
	public JSONObject getListByPageBean(@RequestParam Integer page, @RequestParam Integer limit,
			@RequestParam(required = false, defaultValue = "") String name,
			@RequestParam(required = false, defaultValue = "") String status) {
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPageNumber(page);
		pageBean.setLimit(limit);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", name);
		param.put("status", status);
		pageBean.setMap(param);
		clientInfoService.getResult(pageBean);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject returnObject = JSONObject.fromObject(pageBean,jsonConfig);
		returnObject = JsonCovert.filterNull(returnObject);
		return returnObject;
	}
	
	/**
	 * 客户端信息状态启动方法
	 * @return
	 */
	@PostMapping("/changeStart")
	public Message2Page2 changeStatus(@RequestParam(value = "ids[]") String[] ids, @RequestParam String status) {
		clientInfoService.changeStatus(ids, status);
		String username = "";
		for (int i = 0; i < ids.length; i++) {
			BClient muser = clientInfoService.get(BClient.class, ids[i]);
			username += muser.getName() + ",";
		}
		StringBuffer msg = new StringBuffer();
		msg.append(status.equals("1") ? "停用" : "启用").append("用户：").append(username);
		saveLogB(msg.toString(), StaticData.LOG_TYPE_DO);
		return new Message2Page2(true, 200, "");
	}
//		String musername = "";
//		if(status.equals("0")) {
//			for (int i = 0; i < ids.length; i++) {
//				BClient muser = clientInfoService.get(BClient.class, ids[i]);
//				//musername +=muser.getName() + ",";
//				muser.setClientId(ids[i]);
//				muser.setStatus(1);
//				clientInfoService.update(muser);
//			}
//		}
//		if(status.equals("1")) {
//			for (int i = 0; i < ids.length; i++) {
//				BClient muser = clientInfoService.get(BClient.class, ids[i]);
//				//musername +=muser.getName() + ",";
//				muser.setClientId(ids[i]);
//				muser.setStatus(0);
//				clientInfoService.update(muser);
//			}
//		}
//		StringBuffer msg = new StringBuffer();
//		return new Message2Page(true, "200", null);
//	}
	
	
	/**
	 * 客户端详细信息查看
	 * */
	
	@PostMapping("/getClientValueById")
	public Message2Page2 getClientValueById(@RequestParam String clientId) {
		BClient dicValue = clientInfoService.getPersonneValueById(clientId);
		JSONObject jsonObject = JSONObject.fromObject(dicValue);
		jsonObject = JsonCovert.filterNull(jsonObject);
		return new Message2Page2(true, 200, "", jsonObject);
	}
	
	
	/**
	 * 客户端信息添加
	 * */
	
	@PostMapping("/add")
	public Message2Page2 add(BClient bc,HttpServletRequest request,HttpServletResponse response) {
		String name=this.getPageParameter("name");
		String code=this.getPageParameter("code");
		String status=this.getPageParameter("status");
        //存储信息
	    bc=new BClient();
		bc.setClientId(UUID.randomUUID());
		bc.setCode(code);
		bc.setName(name);
		bc.setStatus(Integer.parseInt(status));
		clientInfoService.save(bc);
		return new Message2Page2(true, 200, null);
		}
	

	/**
	 * 客户端信息修改
	 * */
	
	@PostMapping("/update")
	public Message2Page2 update(BClient bc) {
		// 过滤string类型的字段内的特殊字符
		ReflectCommon<BClient> common = new ReflectCommon<BClient>();
		Map<String, Object> map = common.test(bc);
		bc = (BClient) ReplaceUtil.replaceSpecString(map, bc);	
		System.out.println(bc.getClientId());
		BClient userSource = clientInfoService.get(BClient.class, bc.getClientId());
		// 修改信息
		this.mergeObject(bc, userSource);
		clientInfoService.update(userSource);
		return new Message2Page2(true, 200, null);
	}
	/**
	 * 客户端信息删除
	 * */
	@PostMapping("/del")
	public Message2Page2 deleteCon(@RequestParam(value = "ids[]", required = false) String[] ids) {
		String delname = "";
		byte Isdeleted=1;
		for (int i = 0; i < ids.length; i++) {
			BClient muser = clientInfoService.get(BClient.class, ids[i]);
			delname +=muser.getName() + ",";
			muser.setStatus(1);
			muser.setIsdeleted(Isdeleted);
			clientInfoService.update(muser);
		}
		StringBuffer msg = new StringBuffer();
		return new Message2Page2(true, 200, null);
	}
}
