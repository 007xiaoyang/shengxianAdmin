package com.hwhl.shengxian.filter;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 编码拦截器
 * @author SHZ
 *
 */
public class InterceptorEncoding extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		return true;
	}
}
