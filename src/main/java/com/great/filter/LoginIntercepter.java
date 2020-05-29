package com.great.filter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.great.resource.StaticData;

public class LoginIntercepter implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO Auto-generated method stub
        String requestURI = request.getRequestURI();
        String[] unDoFilter  = new String[]{"/login","/api"};
        System.out.println(requestURI);
        Boolean filterFlag = true;
        for(String uri : unDoFilter){
        	if(requestURI.contains("/login") ||requestURI.contains("/api")){
        		//filterFlag = false;
        	}
        }
        if(filterFlag){  
            //说明处在编辑的页面  
            HttpSession session = request.getSession();  
            Object user = session.getAttribute(StaticData.USER_SESSION);  
            if(user!=null){  
                //登陆成功的用户  
                return true;  
            }else{  
              //没有登陆，转向登陆界面  
              //request.getRequestDispatcher("/login.jsp").forward(request,response); 
            	PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<script>");
				out.println("window.open ('" + request.getContextPath() + "/login.jsp','_top')");
				out.println("</script>");
				out.println("</html>");
				out.flush();
				out.close();
              return false;  
            }  
        }else{  
            return true;  
        }  
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
    }

}