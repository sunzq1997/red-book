package com.sunzq.controller;


import com.sunzq.GraceJSONResult;
import com.sunzq.ResponseStatusEnum;
import com.sunzq.bo.RegistLoginBO;
import com.sunzq.entity.Users;
import com.sunzq.service.UsersService;
import com.sunzq.utils.IPUtil;
import com.sunzq.utils.SendSMSUtil;
import com.sunzq.vo.UserVO;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

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

    @Autowired
    private UsersService usersService;

    /**
     * 通过手机号获取验证码
     *
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

    @PostMapping("login")
    public GraceJSONResult login(@Validated @RequestBody RegistLoginBO registLoginBO) {
        String mobile = registLoginBO.getMobile();
        String smsCode = registLoginBO.getSmsCode();
        //验证码是否过期
        String code = redis.get(MOBILE_SMSCODE + ":" + mobile);
        if (StringUtils.isBlank(code)) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.SMS_CODE_ERROR);
        }
        //验证手机号是否存在
        Users user = usersService.queryMobileIsExist(mobile);
        //不存在则注册
        if (user == null) {
            user = usersService.register(mobile);
        }

        redis.del(MOBILE_SMSCODE + ":" + mobile);

        String token = UUID.randomUUID().toString();
        redis.set(USER_REDIS_TOEKN + ":" + user.getId(), token);

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setToken(token);
        return GraceJSONResult.ok(userVO);
    }

    @PostMapping("logout")
    public GraceJSONResult logout(@RequestParam String userId) {
        redis.del(USER_REDIS_TOEKN + ":" + userId);
        return GraceJSONResult.ok();
    }
}
