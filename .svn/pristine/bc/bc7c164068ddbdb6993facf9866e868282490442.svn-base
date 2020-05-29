package com.great.system.action;

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
import com.great.base.entity.Message2Page;
import com.great.resource.StaticData;
import com.great.system.entity.SUserEntity;
import com.great.system.service.UserService;
import com.great.tool.DigitalSign;

@RestController
@RequestMapping(value = "/sys")
public class LoginAction extends BaseAction {

	@Autowired
	private UserService userService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @param userName
	 * @param passWord
	 * @return
	 */
	@PostMapping("/login")
	public Message2Page login(@RequestParam String userName, @RequestParam String passWord) {
		String msg = "";
		SUserEntity user = new SUserEntity();
		user.setLoginName(userName);
		user.setStatus(StaticData.STATUS_NORMAL);
		user.setIsdeleted(false);
		List<SUserEntity> users = null;
		try {
			users = userService.findByExample(user);
		} catch (Exception e) {
			logger.error(e.getMessage());
			users = userService.findByExample(user);
		}
		// 判断用户密码
		if (users != null && users.size() > 0) {
			user = users.get(0);
			if (user.getPasswd().equals(DigitalSign.getMD5(passWord))) {
				this.getSession().setAttribute(StaticData.USER_SESSION, user);
				user.setLastLoginTime(new Date());
				this.userService.update(user);
				;
				String loginmsg = "用户登录：" + user.getUserName() + ",登录后台管理系统";
				saveLogB(loginmsg, StaticData.LOG_TYPE_LOGIN);
				return new Message2Page(true, "200", msg);
			} else {
				msg = "用户密码错误！";
				return new Message2Page(false, "201", msg);
			}
		} else {
			msg = "该用户不存在！";
			return new Message2Page(false, "201", msg);
		}
	}

	@PostMapping("/loginOut")
	public Message2Page loginOut() {
		this.getSession().removeAttribute(StaticData.USER_SESSION);
		return new Message2Page(true, "200", "");
	}
}
