package com.study.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(LoginHandlerInterceptor.class);

    /**
     * 拦截器
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getServletPath();
        logger.info("request请求地址path[{}] uri[{}]", request.getServletPath(),request.getRequestURI());
        Object admin = request.getSession().getAttribute("admin");
        if (admin != null || url.contains("Login")
                || url.contains("login")
                || url.contains("register")
                || url.contains("getImage")
                || url.contains("registerStudent")
                || url.contains("addUser")
                || url.contains("toRegiterAlumnus")
                || url.contains("addAlumnus")
        ){
            return true;
        }else{
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("<script type='text/javascript'>alert('Login failed, please log in again！');"
                    + "window.parent.location.href = 'outLogin'; </script>");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {}
}
