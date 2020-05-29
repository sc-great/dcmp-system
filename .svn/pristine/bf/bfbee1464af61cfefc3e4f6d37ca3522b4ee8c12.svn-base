package com.great.base.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import com.great.base.service.BaseService;
import com.great.resource.StaticData;
import com.great.system.entity.SLogEntity;
import com.great.system.entity.SUserEntity;
import com.great.tool.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BaseAction extends WebApplicationObjectSupport {

	@Autowired
	@SuppressWarnings("rawtypes")
	private BaseService baseService;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		logger.debug("@ModelAttribute");
	}

	protected SUserEntity getLoginUser() {
		return (SUserEntity) getSession().getAttribute(StaticData.USER_SESSION);
	}

	protected HttpServletRequest getRequest() {
		return request;
	}

	protected HttpSession getSession() {
		return session;
	}

	protected HttpServletResponse getResponse() {
		return response;
	}

	protected String getPageParameter(String arg0) {
		return getRequest().getParameter(arg0);
	}

	protected void saveLogB(String msg, String type) {
		SLogEntity logB = new SLogEntity();
		logB.setLogId(UUID.randomUUID());
		if (type.equals(StaticData.LOG_TYPE_DO)) {
			logB.setCreateBy(this.getLoginUser().getUserName());
		} else {
			logB.setCreateBy("system");
		}
		logB.setCreateTime(new Date());
		logB.setLogMsg(msg);
		logB.setLogType(type);
		baseService.save(logB);
	}

	protected void returnJsonArrayToPage(Object object) {
		JSONArray jsonArray = JSONArray.fromObject(object);
		getResponse().setContentType("textml;charset=utf-8");
		/* 设置响应头允许ajax跨域访问* */
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		getResponse().setHeader("Access-Control-Allow-Methods", "GET,POST");
		PrintWriter out = null;
		try {
			out = getResponse().getWriter();
			out.print(jsonArray);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	protected void returnJsonObjectToPage(Object object) {
		JSONObject jsonObject = JSONObject.fromObject(object);
		getResponse().setContentType("textml;charset=utf-8");
		/* 设置响应头允许ajax跨域访问* */
		getResponse().setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		getResponse().setHeader("Access-Control-Allow-Methods", "GET,POST");
		PrintWriter out = null;
		try {
			out = getResponse().getWriter();
			out.print(jsonObject);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	protected <T> void mergeObject(T origin, T destination) {
		if (origin == null || destination == null)
			return;
		if (!origin.getClass().equals(destination.getClass()))
			return;

		Field[] fields = origin.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			try {
				fields[i].setAccessible(true);
				Object value = fields[i].get(origin);
				if (null != value) {
					fields[i].set(destination, value);
				}
				fields[i].setAccessible(false);
			} catch (Exception e) {
			}
		}
	}

	/** 基于@ExceptionHandler异常处理 */
	// @ExceptionHandler
	// public String exp(HttpServletRequest request, Exception ex) {
	// request.setAttribute("ex", ex);
	// Message2Page m2p;
	// // 根据不同错误转向不同页面
	// if (ex instanceof BusinessException) {
	// logger.error("system BusinessException：" + ex.getMessage());
	// m2p = new Message2Page(false, "500", ex.getMessage());
	// } else if (ex instanceof ParameterException) {
	// logger.error("system ParameterException：" + ex.getMessage());
	// m2p = new Message2Page(false, "400", ex.getMessage());
	// } else {
	// logger.error("system OutherException：" + ex.getMessage());
	// m2p = new Message2Page(false, "401", ex.getMessage());
	// }
	// returnJsonObjectToPage(m2p);
	// return null;
	// }

}
