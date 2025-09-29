package com.rngad33.yxsearch.controller;

import com.rngad33.yxsearch.common.BaseResponse;
import com.rngad33.yxsearch.common.ResultUtils;
import com.rngad33.yxsearch.model.dto.search.SearchRequest;
import com.rngad33.yxsearch.model.vo.SearchVO;
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
    private UserService userService;

    @Resource
    private PostService postService;

    /**
     * 聚合检索
     *
     * @param searchRequest
     * @param request
     * @return
     */
    @PostMapping("/all")
    public BaseResponse<SearchVO> searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {

        return ResultUtils.success(null);
    }

}