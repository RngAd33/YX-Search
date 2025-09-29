package com.rngad33.yxsearch.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 图片实体类
 */
@Data
@TableName("picture")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Picture {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 图片标题
     */
    private String title;

    /**
     * 图片地址
     */
    private String url;

}