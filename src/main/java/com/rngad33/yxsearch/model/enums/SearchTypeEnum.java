package com.rngad33.yxsearch.model.enums;

import lombok.Getter;

/**
 * 搜索类型枚举
 */
@Getter
public enum SearchTypeEnum {

    USER("用户", "user"),
    PICTURE("图片", "picture"),
    POST("帖子", "post");

    private final String text;

    private final String value;

    SearchTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

}