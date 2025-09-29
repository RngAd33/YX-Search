package com.rngad33.yxsearch.model.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 搜索请求
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchRequest {

    /**
     * 搜索词
     */
    private String searchText;

    /**
     * 类型
     */
    private String type;

}