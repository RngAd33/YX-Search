package com.rngad33.yxsearch.manager;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rngad33.yxsearch.common.ErrorCode;
import com.rngad33.yxsearch.datasource.DataSource;
import com.rngad33.yxsearch.datasource.PictureDataSource;
import com.rngad33.yxsearch.datasource.PostDataSource;
import com.rngad33.yxsearch.datasource.UserDataSource;
import com.rngad33.yxsearch.exception.ThrowUtils;
import com.rngad33.yxsearch.model.dto.search.SearchRequest;
import com.rngad33.yxsearch.model.entity.Picture;
import com.rngad33.yxsearch.model.enums.SearchTypeEnum;
import com.rngad33.yxsearch.model.vo.PostVO;
import com.rngad33.yxsearch.model.vo.SearchVO;
import com.rngad33.yxsearch.model.vo.UserVO;
import com.rngad33.yxsearch.service.PictureService;
import com.rngad33.yxsearch.service.PostService;
import com.rngad33.yxsearch.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 搜索门面
 */
@Component
public class SearchFacade {

    @Resource
    private UserDataSource userDataSource;

    @Resource
    private PictureDataSource pictureDataSource;

    @Resource
    private PostDataSource postDataSource;

    /**
     * 聚合检索
     *
     * @param searchRequest
     * @param request
     * @return
     */
    public SearchVO searchAll(SearchRequest searchRequest, HttpServletRequest request) {
        String searchText = searchRequest.getSearchText();
        String type = searchRequest.getType();
        SearchTypeEnum searchTypeEnum = SearchTypeEnum.getEnumByValue(type);
        ThrowUtils.throwIf(!StrUtil.isAllNotBlank(searchText, type), ErrorCode.PARAMS_ERROR);
        long pageNum = searchRequest.getCurrent();
        long pageSize = searchRequest.getPageSize();
        SearchVO searchVO = new SearchVO();
        if (ObjUtil.isNull(searchTypeEnum)) {
            // - 未规定类型，搜索所有
            Page<UserVO> userVOPage = userDataSource.doSearch(searchText, pageNum, pageSize);
            Page<Picture> picturePage = pictureDataSource.doSearch(searchText, pageNum, pageSize);
            Page<PostVO> postVOPage = postDataSource.doSearch(searchText, pageNum, pageSize);
            // 聚合
            searchVO.setUserList(userVOPage.getRecords());
            searchVO.setPictureList(picturePage.getRecords());
            searchVO.setPostList(postVOPage.getRecords());
        } else {
            // - 匹配到类型，按需搜索
            Map<String, DataSource<?>> dataSourceMap = new HashMap<String, DataSource<?>>() {{
                put(SearchTypeEnum.USER.getValue(), userDataSource);
                put(SearchTypeEnum.PICTURE.getValue(), pictureDataSource);
                put(SearchTypeEnum.POST.getValue(), postDataSource);
            }};
            DataSource<?> dataSource = dataSourceMap.get(type);
            Page<?> page = dataSource.doSearch(searchText, pageNum, pageSize);
            searchVO.setDataList(page.getRecords());
        }
        return searchVO;
    }

}