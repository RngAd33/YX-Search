package com.rngad33.yxsearch.datasource;

import com.rngad33.yxsearch.model.enums.SearchTypeEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源注册器
 */
@Component
public class DataSourceRegistry {

    @Resource
    private UserDataSource userDataSource;

    @Resource
    private PictureDataSource pictureDataSource;

    @Resource
    private PostDataSource postDataSource;

    Map<String, DataSource<?>> typeDataSourceMap = new HashMap<String, DataSource<?>>() {{
        put(SearchTypeEnum.USER.getValue(), userDataSource);
        put(SearchTypeEnum.PICTURE.getValue(), pictureDataSource);
        put(SearchTypeEnum.POST.getValue(), postDataSource);
    }};

    /**
     * 根据类型获取数据源
     *
     * @param type
     * @return
     */
    public DataSource<?> getDataSourceByType(String type) {
        return typeDataSourceMap.get(type);
    }

}