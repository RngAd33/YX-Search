package com.rngad33.yxsearch.model.dto.postfavour;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 帖子收藏 / 取消收藏请求
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostFavourAddRequest {

    /**
     * 帖子 id
     */
    private Long postId;

}