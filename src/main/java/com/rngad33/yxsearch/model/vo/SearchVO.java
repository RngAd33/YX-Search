package com.rngad33.yxsearch.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rngad33.yxsearch.model.entity.Picture;
import lombok.Data;

import java.util.List;

/**
 * 搜索视图
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchVO {

    /**
     * 用户表
     */
    private List<UserVO> userList;

    /**
     * 图片表
     */
    private List<Picture> pictureList;

    /**
     * 帖子表
     */
    private List<PostVO> postList;

}