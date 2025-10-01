package com.rngad33.yxsearch.model.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rngad33.yxsearch.common.PageRequest;
import lombok.Data;

/**
 * 搜索请求
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchRequest extends PageRequest {

    /**
     * 搜索词
     */
    private String searchText;

    /**
     * 类型
     */
    private String type;

}