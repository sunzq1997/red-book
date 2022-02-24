package com.sunzq.enums;

/**
 * @author sunzq
 */

public enum YesOrNoEnum {
    NO(0, "否"),
    YES(1, "是");

    private Integer type;
    private String value;

    YesOrNoEnum(Integer type, String value) {
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
