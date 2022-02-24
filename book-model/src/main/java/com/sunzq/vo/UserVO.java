package com.sunzq.vo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author sunzq
 */
@Data
public class UserVO {
    private String id;
    private String mobile;
    private String nickname;
    private String imoocNum;
    private String face;
    private Integer sex;
    private LocalDate birthday;
    private String country;
    private String province;
    private String city;
    private String district;
    private String description;
    private String bgImg;
    private Integer canImoocNumBeUpdated;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    private String token;

    private Integer myFollowsCounts;
    private Integer myFansCounts;
    private Integer totalLikeMeCounts;
}
