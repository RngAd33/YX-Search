package com.rngad33.yxsearch.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 数据源接口
 */
public interface DataSource<T> {

    /**
     * 搜索
     *
     * @param searchText
     * @return
     */
    Page<T> doSearch(String searchText);

}