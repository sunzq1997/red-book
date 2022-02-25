package com.sunzq.enums;

/**
 * @author sunzq
 */
public enum UpdateUserInfoTypeEnum {
    NICKNAME(1, "昵称"),
    IMOOCNUM(2, "慕课号"),
    SEX(3, "性别"),
    BIRTHDAY(4, "生日"),
    LOCATION(5, "所在地"),
    DESC(6, "简介");

    private Integer type;
    private String value;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    UpdateUserInfoTypeEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
