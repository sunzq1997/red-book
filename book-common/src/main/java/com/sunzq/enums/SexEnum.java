package com.sunzq.enums;

/**
 * @author sunzq
 */

public enum SexEnum {
    woman(0, "女"),

    man(1, "男"),

    secret(2, "保密");

    private Integer type;
    private String value;

    SexEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

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
}
