package com.great.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.great.resource.StaticData;

public class RequestOutFilter implements Filter {

	private final static Logger logger = LoggerFactory.getLogger(RequestOutFilter.class);

	public void destroy() {

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		logMsg(request);
		String urlPath = request.getServletPath();
		Boolean filterFlag = true;
		// 过滤登录和对外的api接口不做用户登录验证
		if (urlPath.contains("/login") || urlPath.contains("/api")) {
			filterFlag = false;
		}
		// 过滤js文件和css文件不做用户登录验证
		if (urlPath.endsWith(".js") || urlPath.endsWith(".css") || urlPath.endsWith(".jpg") || urlPath.endsWith(".png")
				|| urlPath.endsWith(".ttf") || urlPath.endsWith(".woff") || urlPath.endsWith(".eot")
				|| urlPath.endsWith(".ico")) {
			filterFlag = false;
		}
		// 访问uploadImage文件夹不用做验证
		if (urlPath.contains("/uploadImage")) {
			filterFlag = false;
		}

		if (filterFlag) {
			HttpSession session = request.getSession();
			// String textPath = session.getServletContext().getRealPath("") +
			// "js\\redirect.js";
			// StringBuffer redTxtContent = TextRW.readTxtFile(textPath);
			Object user = session.getAttribute(StaticData.USER_SESSION);
			if (null == user) {
				logger.warn("用户登录超时或未登录，请重新登录！");
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<script>");
				out.println("window.open ('" + request.getContextPath() + "/login.jsp','_top')");
				// out.println("window.open ('" + redTxtContent.toString() +
				// "','_top')");
				out.println("</script>");
				out.println("</html>");
				out.flush();
				out.close();
				logger.info("跳转至登录页面!");
				arg2.doFilter(arg0, arg1);
			} else {
				arg2.doFilter(arg0, arg1);
			}
		} else {
			arg2.doFilter(arg0, arg1);
		}
	}

	private void logMsg(HttpServletRequest request) {
		// StringBuffer urlStringBuffer = request.getRequestURL();
		// String method = request.getMethod();
		// String ip = request.getRemoteAddr();
		//
		// logger.info(urlStringBuffer + " " + method + " " + ip);
		//
		// Map<String, String[]> map = (Map<String, String[]>)
		// request.getParameterMap();
		// for (String name : map.keySet()) {
		// String[] values = map.get(name);
		// logger.info(name + "=" + Arrays.toString(values));
		// }
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
