package com.sunzq.service;

import com.sunzq.bo.UpdateUserInfoBO;
import com.sunzq.entity.Users;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author sunzq
 * @since 2022-02-23
 */
public interface UsersService {


    /**
     * 查询手机号是否存在,如果存在则返回用户信息
     * @param mobile
     * @return
     */
    Users queryMobileIsExist(String mobile);

    /**
     * 注册用户,并返回用户信息
     * @param mobile
     * @return
     */
    Users register(String mobile);

    /**
     * 通过用户id获取用户信息
     * @param userId
     * @return
     */
    Users getUserById(String userId);

    /**
     * 修改用户信息
     * @param updateUserInfoBO,type
     * @return
     */
    Users updateUserInfo(UpdateUserInfoBO updateUserInfoBO);

    /**
     * 修改用户信息
     * @param updateUserInfoBO,type
     * @return
     */
    Users updateUserInfo(UpdateUserInfoBO updateUserInfoBO,Integer type);
}
