package com.rngad33.yxsearch.model.dto.picture;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rngad33.yxsearch.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图片查询请求
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PictureQueryRequest extends PageRequest {

    /**
     * 搜索词
     */
    private String searchText;

}