package com.ldps.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ldps.model.UserModel;

public class AdminLoginInterceptor extends HandlerInterceptorAdapter {
	
	private static Logger logger = Logger
			.getLogger(AdminLoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.info("request: "+request);
		UserModel user =  (UserModel)request.getSession().getAttribute("user");   
        if(user == null||user.getId()!=1000){  
        	logger.info("Interceptor：跳转到login页面！");  
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);  
            return false;  
        }else  
            return true;     
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info(request);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
}
