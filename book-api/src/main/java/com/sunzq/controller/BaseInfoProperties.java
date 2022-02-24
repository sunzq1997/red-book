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
    public static final String USER_REDIS_TOKEN = "user:token";

    // 我的关注总数
    public static final String REDIS_MY_FOLLOWS_COUNTS = "redis_my_follows_counts";
    // 我的粉丝总数
    public static final String REDIS_MY_FANS_COUNTS = "redis_my_fans_counts";

    // 视频和发布者获赞数
    public static final String REDIS_VLOG_BE_LIKED_COUNTS = "redis_vlog_be_liked_counts";
    public static final String REDIS_VLOGER_BE_LIKED_COUNTS = "redis_vloger_be_liked_counts";

}
