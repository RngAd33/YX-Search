package com.rngad33.yxsearch.controller;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rngad33.yxsearch.common.BaseResponse;
import com.rngad33.yxsearch.common.ErrorCode;
import com.rngad33.yxsearch.common.ResultUtils;
import com.rngad33.yxsearch.exception.ThrowUtils;
import com.rngad33.yxsearch.manager.SearchFacade;
import com.rngad33.yxsearch.model.dto.post.PostQueryRequest;
import com.rngad33.yxsearch.model.dto.search.SearchRequest;
import com.rngad33.yxsearch.model.dto.user.UserQueryRequest;
import com.rngad33.yxsearch.model.entity.Picture;
import com.rngad33.yxsearch.model.enums.SearchTypeEnum;
import com.rngad33.yxsearch.model.vo.PostVO;
import com.rngad33.yxsearch.model.vo.SearchVO;
import com.rngad33.yxsearch.model.vo.UserVO;
import com.rngad33.yxsearch.service.PictureService;
import com.rngad33.yxsearch.service.PostService;
import com.rngad33.yxsearch.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 搜索接口
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @Resource
    private SearchFacade searchFacade;

    /**
     * 聚合搜索
     *
     * @param searchRequest
     * @param request
     * @return searchVO
     */
    @PostMapping("/all")
    public BaseResponse<SearchVO> searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(ObjUtil.isNull(searchRequest), ErrorCode.PARAMS_ERROR);
        SearchVO searchVO = searchFacade.searchAll(searchRequest, request);
        return ResultUtils.success(searchVO);
    }

}