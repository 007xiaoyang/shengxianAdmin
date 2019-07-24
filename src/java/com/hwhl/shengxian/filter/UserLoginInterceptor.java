package com.hwhl.shengxian.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lujingjing on 2017/9/9.
 */
public class UserLoginInterceptor extends HandlerInterceptorAdapter{
    /**
     * 不进行拦截的url
     */
    private List<String> excludedUrls;

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
        //以下地址不拦截
        String requestUri = request.getRequestURI();
        for (String url : excludedUrls) {
            if (requestUri.endsWith(url)) {
                return true;
            }
        }
        //检测session是否存在，是否过期
        HttpSession session=request.getSession();
        HashMap admin= (HashMap) session.getAttribute("admin");
        if(admin==null){
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
            return false;
        }
        return true;
    }

    /**
     * 错误url
     */
    @Value("${ERROR_URL}")
    private String ERROR_URL;

    public List<String> getExcludedUrls() {
        return excludedUrls;
    }

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

}
