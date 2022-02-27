package com.sunzq.controller;

import com.sunzq.GraceJSONResult;
import com.sunzq.ResponseStatusEnum;
import com.sunzq.bo.UpdateUserInfoBO;
import com.sunzq.config.MinioConfig;
import com.sunzq.entity.Users;
import com.sunzq.enums.FileTypeEnum;
import com.sunzq.service.UsersService;
import com.sunzq.utils.MinioUtils;
import com.sunzq.vo.UserVO;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author sunzq
 */
@Api(tags = "用户信息接口")
@RequestMapping("/userInfo")
@RestController
public class UserInfoController extends BaseInfoProperties {
    @Autowired
    private UsersService usersService;
    @Autowired
    private MinioConfig minioConfig;

    @GetMapping("query")
    public GraceJSONResult getUser(@RequestParam String userId) {
        String fansCountStr = redis.get(REDIS_MY_FANS_COUNTS);
        String followsCountStr = redis.get(REDIS_MY_FOLLOWS_COUNTS);
        String vlogLikeCountStr = redis.get(REDIS_VLOG_BE_LIKED_COUNTS);
        String vlogerLikeCountStr = redis.get(REDIS_VLOGER_BE_LIKED_COUNTS);

        Integer fansCount = 0;
        Integer followsCount = 0;
        Integer vlogLikeCount = 0;
        Integer vlogerLikeCount = 0;
        Integer totalLikeMeCount = 0;

        if (!StringUtils.isBlank(fansCountStr)) {
            fansCount = Integer.valueOf(fansCountStr);
        }
        if (!StringUtils.isBlank(followsCountStr)) {
            followsCount = Integer.valueOf(followsCountStr);
        }
        if (!StringUtils.isBlank(vlogLikeCountStr)) {
            vlogLikeCount = Integer.valueOf(vlogLikeCountStr);
        }
        if (!StringUtils.isBlank(vlogerLikeCountStr)) {
            vlogerLikeCount = Integer.valueOf(vlogerLikeCountStr);
        }

        totalLikeMeCount = vlogLikeCount + vlogerLikeCount;
        Users user = usersService.getUserById(userId);

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setMyFansCounts(fansCount);
        userVO.setMyFollowsCounts(followsCount);
        userVO.setTotalLikeMeCounts(totalLikeMeCount);

        return GraceJSONResult.ok(userVO);
    }

    @PostMapping("modifyUserInfo")
    public GraceJSONResult modifyUserInfo(@RequestBody UpdateUserInfoBO userInfoBO, @RequestParam Integer type) {
        Users user = usersService.updateUserInfo(userInfoBO, type);

        return GraceJSONResult.ok(user);
    }

    @PostMapping("modifyImage")
    public GraceJSONResult modifyImage(@RequestParam String userId, @RequestParam Integer type,
                                       MultipartFile file) throws Exception {
        if (!type.equals(FileTypeEnum.FACE.type) && !type.equals(FileTypeEnum.BGIMG.type)) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.FILE_UPLOAD_FAILD);
        }

        String filename = file.getOriginalFilename();
        MinioUtils.uploadFile(minioConfig.getBucketName(), filename, file.getInputStream());
        String url = minioConfig.getEndpoint() + "/" + minioConfig.getBucketName() + "/" + filename;

        UpdateUserInfoBO userInfoBO = new UpdateUserInfoBO();
        userInfoBO.setId(userId);
        if (type.equals(FileTypeEnum.FACE.type)) {
            userInfoBO.setFace(url);
        }
        if (type.equals(FileTypeEnum.BGIMG.type)) {
            userInfoBO.setBgImg(url);
        }
        Users user = usersService.updateUserInfo(userInfoBO);

        return GraceJSONResult.ok(user);
    }
}
