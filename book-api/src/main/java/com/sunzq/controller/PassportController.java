package com.sunzq.controller;


import com.sunzq.GraceJSONResult;
import com.sunzq.utils.IPUtil;
import com.sunzq.utils.SendSMSUtil;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 通行证控制器
 *
 * @author sunzq
 */
@Api(tags = "通行证接口")
@RestController
@RequestMapping("/passport")
public class PassportController extends BaseInfoProperties {

    @Autowired
    private SendSMSUtil sendSMSUtil;

    /**
     * 通过手机号获取验证码
     * @param mobile
     * @param request
     * @return
     */
    @PostMapping("getSMSCode")
    public GraceJSONResult getSMSCode(@RequestParam String mobile, HttpServletRequest request) {
        if (StringUtils.isBlank(mobile)) {
            return GraceJSONResult.ok();
        }
        String ip = IPUtil.getRequestIp(request);
        redis.setnx60s(MOBILE_SMSCODE + ":" + ip, ip);
        String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        sendSMSUtil.sendSMS(mobile, code);
        redis.set(MOBILE_SMSCODE + ":" + mobile, code, 30 * 60);

        return GraceJSONResult.ok();
    }
}
