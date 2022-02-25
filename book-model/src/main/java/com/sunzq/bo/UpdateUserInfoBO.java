package com.sunzq.bo;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author sunzq
 */
@Data
public class UpdateUserInfoBO {
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
}
