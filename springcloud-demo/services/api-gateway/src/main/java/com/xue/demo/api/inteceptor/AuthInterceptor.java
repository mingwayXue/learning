/**
 * 
 */
package com.xue.demo.api.inteceptor;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xue.demo.api.common.CommonConstants;
import com.xue.demo.api.common.UserContext;
import com.xue.demo.api.dao.UserDao;
import com.xue.demo.api.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.google.common.base.Joiner;

//import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;

/**
 *自定义请求拦截器
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
  
  private static final String TOKEN_COOKIE = "token";
  
  
  @Autowired
  private UserDao userDao;

    /**
     * 请求前处理
     * @param req
     * @param res
     * @param handler
     * @return
     * @throws Exception
     */
  @Override
  public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
          throws Exception {
    Map<String, String[]> map = req.getParameterMap();
    map.forEach((k,v) ->req.setAttribute(k, Joiner.on(",").join(v)));
    String requestURI = req.getRequestURI();
    if (requestURI.startsWith("/static") || requestURI.startsWith("/error")) {      //如果请求路径uri是以“/static”或“/error”开始，则直接返回
      return true;
    }
    Cookie cookie = WebUtils.getCookie(req, TOKEN_COOKIE);

    //cors带cookie的请求
    if (cookie == null && null != req.getHeader("Cookie1")) {     //前端需要设置传递的cookie为Cookie1，且设置ajax属性，xhrFiels:{withCredentials:true}（表示请求带cookie）
      String value = StringUtils.substringAfterLast(req.getHeader("Cookie1"), "=");
      cookie = new Cookie("token", value);
    }

    if (cookie != null && StringUtils.isNoneBlank(cookie.getValue())) {
        User user = userDao.getUserByToken(cookie.getValue());      //根据token获取用户信息
        if (user != null) {
          req.setAttribute(CommonConstants.LOGIN_USER_ATTRIBUTE, user);
//          req.setAttribute(CommonConstants.USER_ATTRIBUTE, user);
          UserContext.setUser(user);        //设置线程变量user
        }
    }
    return true;
  }

    /**
     * 请求后处理
     * @param req
     * @param res
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
  @Override
  public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler,
          ModelAndView modelAndView) throws Exception {
    String requestURI = req.getRequestURI();
    if (requestURI.startsWith("/static") || requestURI.startsWith("/error")) {
      return ;
    }
    User user = UserContext.getUser();
    if (user != null && StringUtils.isNoneBlank(user.getToken())) {
       String token = requestURI.startsWith("logout")? "" : user.getToken();    //除了用户登出，其它情况均添加token信息
       Cookie cookie = new Cookie(TOKEN_COOKIE, token);
       cookie.setPath("/");
       cookie.setHttpOnly(false);
       res.addCookie(cookie);
    }
    
  }
  
  

  @Override
  public void afterCompletion(HttpServletRequest req, HttpServletResponse response, Object handler, Exception ex)
          throws Exception {
    UserContext.remove();
  }
}
