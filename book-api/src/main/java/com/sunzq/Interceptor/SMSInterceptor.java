package com.sunzq.Interceptor;

import com.sunzq.controller.BaseInfoProperties;
import com.sunzq.exception.GraceException;
import com.sunzq.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.sunzq.ResponseStatusEnum.SMS_NEED_WAIT_ERROR;

/**
 * 短信验证码拦截器
 *
 * @author sunzq
 */
@Slf4j
@Component
public class SMSInterceptor extends BaseInfoProperties implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = IPUtil.getRequestIp(request);
        boolean b = redis.keyIsExist(MOBILE_SMSCODE + ":" + ip);
        if (b) {
            GraceException.display(SMS_NEED_WAIT_ERROR);
            log.info("发送次数太频繁,请稍后再试");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
