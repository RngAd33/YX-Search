package com.rngad33.yxsearch.model.dto.postthumb;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 帖子点赞请求
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostThumbAddRequest {

    /**
     * 帖子 id
     */
    private Long postId;

}