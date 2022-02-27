package com.sunzq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sunzq.ResponseStatusEnum;
import com.sunzq.bo.UpdateUserInfoBO;
import com.sunzq.entity.Users;
import com.sunzq.enums.SexEnum;
import com.sunzq.enums.UpdateUserInfoTypeEnum;
import com.sunzq.enums.YesOrNoEnum;
import com.sunzq.exception.GraceException;
import com.sunzq.mapper.UsersMapper;
import com.sunzq.service.UsersService;
import com.sunzq.utils.DesensitizationUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author sunzq
 * @since 2022-02-23
 */
@Service
public class UsersServiceImpl implements UsersService {

    private static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxF6ZUySASMbOAABBAXhjY0Y649.png";
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Users queryMobileIsExist(String mobile) {
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getMobile, mobile);
        Users users = usersMapper.selectOne(wrapper);
        return users;
    }

    @Transactional
    @Override
    public Users register(String mobile) {
        Users user = new Users();
        user.setMobile(mobile);
        user.setNickname("用户" + DesensitizationUtil.commonDisplay(mobile));
        user.setImoocNum("用户" + DesensitizationUtil.commonDisplay(mobile));
        user.setFace(USER_FACE);
        user.setSex(SexEnum.secret.getType());
        user.setBirthday(LocalDate.parse("1900-01-01"));
        user.setCountry("中国");
        user.setProvince("");
        user.setCity("");
        user.setDistrict("");
        user.setDescription("该用户很懒,什么都没有写");
        user.setBgImg("");
        user.setCanImoocNumBeUpdated(YesOrNoEnum.YES.getType());
        user.setCreatedTime(LocalDateTime.now());
        user.setUpdatedTime(LocalDateTime.now());
        usersMapper.insert(user);
        return user;
    }

    @Override
    public Users getUserById(String userId) {
        Users user = usersMapper.selectById(userId);
        return user;
    }

    @Transactional
    @Override
    public Users updateUserInfo(UpdateUserInfoBO userInfoBO, Integer type) {
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        if (UpdateUserInfoTypeEnum.NICKNAME.getType().equals(type)) {
            wrapper.eq(Users::getNickname, userInfoBO.getNickname());
            Users users = usersMapper.selectOne(wrapper);
            if (users != null) {
                GraceException.display(ResponseStatusEnum.USER_INFO_UPDATED_NICKNAME_EXIST_ERROR);
            }
        }
        if (UpdateUserInfoTypeEnum.IMOOCNUM.getType().equals(type)) {
            Users u = getUserById(userInfoBO.getId());
            if (YesOrNoEnum.NO.getType().equals(u.getCanImoocNumBeUpdated())) {
                GraceException.display(ResponseStatusEnum.USER_INFO_CANT_UPDATED_IMOOCNUM_ERROR);
            }
            wrapper.eq(Users::getImoocNum, userInfoBO.getImoocNum());
            Users users = usersMapper.selectOne(wrapper);
            if (users != null) {
                GraceException.display(ResponseStatusEnum.USER_INFO_UPDATED_IMOOCNUM_EXIST_ERROR);
            }

        }

        return updateUserInfo(userInfoBO);
    }

    @Override
    @Transactional
    public Users updateUserInfo(UpdateUserInfoBO userInfoBO) {
        Users user = new Users();
        BeanUtils.copyProperties(userInfoBO, user);
        user.setCanImoocNumBeUpdated(YesOrNoEnum.NO.getType());
        usersMapper.updateById(user);
        return getUserById(user.getId());
    }
}
