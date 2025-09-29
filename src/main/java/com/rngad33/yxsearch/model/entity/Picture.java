package com.rngad33.yxsearch.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 图片实体类
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Picture {

    /**
     * 图片标题
     */
    private String title;

    /**
     * 图片地址
     */
    private String url;

}