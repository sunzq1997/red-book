package com.sunzq.enums;

/**
 * @author sunzq
 */

public enum FileTypeEnum {
    FACE(2, "用户头像"),
    BGIMG(1, "用户背景图");

    public final Integer type;
    public final String value;

    FileTypeEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
