package com.sunzq.controller;

import com.sunzq.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sunzq
 */
public class BaseInfoProperties {

    @Autowired
    public RedisOperator redis;
    public static final String MOBILE_SMSCODE = "mobile:smscode";
    public static final String USER_REDIS_TOEKN = "user:token";
}
