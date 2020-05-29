package com.great.manager.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.great.manager.entity.BPerson;
import com.great.manager.service.CampusOrgService;
import com.great.manager.service.PersonnelFileInfoService;
import com.great.resource.StaticData;
import com.great.tool.JsonCovert;
import com.great.tool.PageBean;
import com.great.tool.ReflectCommon;
import com.great.tool.ReplaceUtil;
import com.great.tool.UUID;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * @author LUOCHENG
 *
 */
@RestController
@RequestMapping(value = "/personnelFileInfo")
public class PersonnelFileInfoAction extends BaseAction {
	@Autowired
	private PersonnelFileInfoService personnelFileInfoService;

	@Autowired
	private CampusOrgService orgService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 获取人员信息分页
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
			@RequestParam(required = false, defaultValue = "") String startTime,
			@RequestParam(required = false, defaultValue = "") String endTime,
			@RequestParam(required = false, defaultValue = "") String pName) {
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPageNumber(page);
		pageBean.setLimit(limit);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pName", pName);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		pageBean.setMap(param);
		personnelFileInfoService.getResult(pageBean);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject returnObject = JSONObject.fromObject(pageBean, jsonConfig);
		returnObject = JsonCovert.filterNull(returnObject);
		return returnObject;
	}

	/**
	 * 人员档案查看
	 */

	@PostMapping("/getPersonneValueById")
	public Message2Page2 getPersonneValueById(@RequestParam String PId) {

		// BCampusHierarchy orgSource = orgService.get(BCampusHierarchy.class,
		// org.getChId());
		BPerson dicValue = personnelFileInfoService.getPersonneValueById(PId);
		JSONObject jsonObject = JSONObject.fromObject(dicValue);
		jsonObject = JsonCovert.filterNull(jsonObject);
		return new Message2Page2(true, 200, "", jsonObject);
	}

	/**
	 * 人员档案信息添加
	 */

	@PostMapping("/add")
	public Message2Page2 add(BPerson per, HttpServletRequest request, HttpServletResponse response) {
		// 过滤string类型的字段内的特殊字符
		// String UCode=this.getPageParameter("UCode");
		String PName = this.getPageParameter("PName");
		String PPhone = this.getPageParameter("PPhone");
		String Pic = this.getPageParameter("PPic");
		String userCode = this.getPageParameter("userCode");
		String pBirth = this.getPageParameter("PBirth");
		String idCardNo = this.getPageParameter("IdCardNo");
		String orgId = this.getPageParameter("orgId");
		//用户手机登录密码，取身份证后6位
		String verificationCode=verificationCode = idCardNo.substring(idCardNo.length()-6);
		System.out.println(verificationCode);
		String orgName = "";
		// String orgId="";
		// 查询获取组织机构的名称
		if (!orgId.equals("0")) {
			BCampusHierarchy orgSource = orgService.get(BCampusHierarchy.class, orgId);
			orgName = orgSource.getChName();
		}

		// 日期格式处理
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String description = this.getPageParameter("description");
		Date date = null;

		try {
			date = format1.parse(pBirth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		per = new BPerson();
		per.setPId(UUID.randomUUID());
		per.setPName(PName);
		per.setPPhone(PPhone);
		per.setPBirth(date);
		per.setIdCardNo(idCardNo);
		per.setUserCode(userCode);
		per.setOrgId(orgId);
		per.setOrgName(orgName);
		per.setCreateTime(new Date());
		per.setUpdateTime(new Date());
		per.setPPic(Pic);
		per.setDescription(description);
		per.setVerificationCode(verificationCode);
		personnelFileInfoService.save(per);
		return new Message2Page2(true, 200, null);
	}

	// String PName=this.getPageParameter("PName");
	// String PPhone=this.getPageParameter("PPhone");
	//
	// String Pic=this.getPageParameter("PPic");
	// SServerInfo serverinfo=sService.getConfigManager();
	// String
	// imgUrl="http://"+serverinfo.getServerIpOut()+":"+serverinfo.getServerPortOut()+Pic;
	// //图片冤死路径
	// File imgPath = new
	// File(getServletContext().getRealPath(StaticData.IMAGE_UPLOAD_PATH) + "/"
	// + new File(Pic).getName());
	// //图片处理方法
	// try {
	// URL url = new URL(imgUrl);
	// java.io.BufferedInputStream bis = new BufferedInputStream(new
	// FileInputStream(imgPath));
	// byte[] bytes = new byte[100];
	// //文件存储绝对路径
	// String finalPhotoName= request.getContextPath()+"/uploadImage/";
	//// System.out.println(finalPhotoName);
	// String upic=Pic.toString();
	//
	// OutputStream bos = new FileOutputStream(new File(
	// Pic));
	// //OutputStream bos = new FileOutputStream(new
	// File(request.getContextPath() + "\\webapp\\uploadImage\\" + new
	// File(Pic).getName()));
	// int len;
	// while ((len = bis.read(bytes)) > 0) {
	// bos.write(bytes, 0, len);
	// }
	//// File imgPath = new
	// File(getServletContext().getRealPath(StaticData.IMAGE_UPLOAD_PATH));
	//
	// System.out.println();
	// //保存
	// // 过滤string类型的字段内的特殊字符
	//
	// per=new BPerson();
	// per.setPId(UUID.randomUUID());
	// per.setPName(PName);
	// per.setPPhone(PPhone);
	// per.setCreateTime(new Date());
	// per.setPPic(Pic);
	// personnelFileInfoService.save(per);
	// //关闭流
	// bis.close();
	// bos.flush();
	// bos.close();
	// }catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	//

	/**
	 * 人员档案信息修改
	 */

	@PostMapping("/update")
	public Message2Page2 update(BPerson per) {
		// 过滤string类型的字段内的特殊字符
		String orgId = this.getPageParameter("orgId");
		String orgName = this.getPageParameter("orgName");
		// 获取当前修改个人信息ID值
		String pid = per.getPId();
		// 查询之前的体温信息记录
		BPerson perpid = personnelFileInfoService.get(BPerson.class, pid);
		// 健康状态
		per.setHealth(perpid.getHealth());
		// 最后一次体温
		per.setLastTemp(perpid.getLastTemp());
		// 最后一次检测时间
		per.setLastDetection(perpid.getLastDetection());
		// 组织ID
		per.setOrgId(orgId);
		// 组织名称
		per.setOrgName(orgName);
		//用户手机登录密码，取身份证后6位
		String verificationCode=verificationCode = per.getIdCardNo().substring(per.getIdCardNo().length()-6);
		ReflectCommon<BPerson> common = new ReflectCommon<BPerson>();
		Map<String, Object> map = common.test(per);
		per = (BPerson) ReplaceUtil.replaceSpecString(map, per);
		BPerson userSource = personnelFileInfoService.get(BPerson.class, per.getPId());
		// 复制需要修改的用户信息给原来的用户对象
		this.mergeObject(per, userSource);
		userSource.setUpdateTime(new Date());
		//修改身份证信息后修改用户手机登录密码
		userSource.setVerificationCode(verificationCode);
		personnelFileInfoService.update(userSource);
		return new Message2Page2(true, 200, null);
	}

	/**
	 * 删除人员档案信息、逻辑删除
	 * 
	 * @param ids
	 *            需要删除的机构编号数组
	 * @return
	 */
	@PostMapping("/del")
	public Message2Page2 deleteCon(@RequestParam(value = "ids[]", required = false) String[] ids) {

		String delname = "";
		byte Isdeleted = 1;
		for (int i = 0; i < ids.length; i++) {
			BPerson userSource = personnelFileInfoService.get(BPerson.class, ids[i]);
			delname += userSource.getPName() + ",";
			userSource.setIsdeleted(Isdeleted);
			userSource.setUpdateTime(new Date());
			personnelFileInfoService.update(userSource);
		}
		String msg = "删除个人档案信息：" + delname;
		saveLogB(msg, StaticData.LOG_TYPE_DO);
		return new Message2Page2(true, 200, null);
	}

}
