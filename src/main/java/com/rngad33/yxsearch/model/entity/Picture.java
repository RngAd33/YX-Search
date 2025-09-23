package com.rngad33.yxsearch.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 图片实体类
 */
@Data
public class Picture implements Serializable {

    /**
     * 图片标题
     */
    private String title;

    /**
     * 图片地址
     */
    private String url;

    private static final long serialVersionUID = 1L;

}
