package com.hx.meituan.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Auther: 汪俊杰
 * @Date：2019年04月07日---20:11
 * @Description:
 */
@SpringBootConfiguration
public class SessionInterceptor implements WebMvcConfigurer {
  private static Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    HandlerInterceptor handlerInterceptor = new HandlerInterceptor() {
      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
          throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        //判断session中是否含有用户
        Object user = request.getSession().getAttribute("user");
        if (user != null) {
          return true;
        }else {
          response.sendRedirect("index.html");
          return false;
        }
      }

      @Override
      public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                             ModelAndView modelAndView) throws Exception {

      }

      @Override
      public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                  Exception ex) throws Exception {
      }
    };
    String[] excludePathPatterns = {"/admin.html",  "/user.html", "/buy.html"};
    registry.addInterceptor(handlerInterceptor).addPathPatterns(excludePathPatterns);
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("redirect:index.html");
  }
}
