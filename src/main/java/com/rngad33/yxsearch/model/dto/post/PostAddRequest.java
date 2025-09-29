package com.rngad33.yxsearch.model.dto.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * 创建请求
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostAddRequest {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表
     */
    private List<String> tags;

}