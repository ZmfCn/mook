package com.zmf.interceptor;

import com.zmf.service.InterceptorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * the interceptor to intercepte the request when the user has no permission
 */
@Component
@SuppressWarnings("all")
public class PublicAccessInterceptor implements HandlerInterceptor {
    private Logger logger = Logger.getLogger(PublicAccessInterceptor.class);
    @Autowired
    private InterceptorService service;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("-----------> request :" + request.getRequestURI());
        // judge weather the uri is to be intercept
        String uri = request.getRequestURI();
        if (!service.isToIntercept(uri)) {
            return true;
        }

        // check whether the user login
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return true;
        }

        // if not, redirect to login
        request.getRequestDispatcher("/user/login").forward(request, response);
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
